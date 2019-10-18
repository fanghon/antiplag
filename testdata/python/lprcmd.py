"""
Author: fanghong
edited: 2019.4.23
"""
# coding=utf-8

import sys
import os
from hyperlpr_py3 import pipline as pp
import cv2
import numpy as np
import time
from PIL import Image, ImageDraw, ImageFont

Sheng = ["京", "沪", "津", "渝", "冀", "晋", "蒙", "辽", "吉", "黑", "苏", "浙", "皖", "闽", "赣", "鲁", "豫", "鄂", "湘", "粤", "桂",
             "琼", "川", "贵", "云", "藏", "陕", "甘", "青", "宁", "新"]

plateSheng = {"京":"JING","津":"JINA","沪":"HU","渝":"YUA","蒙":"MENG","新":"XIN","藏":"ZANG","宁":"NING",
                 "桂":"GUIA","黑":"HEI","吉":"JIB","辽":"LIAO","晋":"JINB","冀":"JIA","青":"QING","鲁":"LU",
                 "豫":"YUB","苏":"SU","皖":"WAN","浙":"ZHE","闽":"MIN","赣":"GANA","湘":"XIANG","鄂":"E",
                 "粤":"YUE","琼":"QIONG","甘":"GANB","陕":"SHAN","贵":"GUIB","云":"YUN","川":"CHUAN"}
plateTypeName = ["蓝", "黄", "绿", "白", "黑 "]
fontC = ImageFont.truetype("Font/platech.ttf", 20, 0)  # 加载中文字体，20表示字体大小，0表示unicode编码
# 画车牌定位框及识别出来的车牌字符，返回标记过的图片
def drawPred(frame, label, left, top, right, bottom):
    # 画车牌定位边框.左上点，右下点,红色，边框粗细：2
    cv2.rectangle(frame, (left, top), (right, bottom), (0, 0, 255), 2)
    # 画车牌字符
    img = Image.fromarray(frame)
    draw = ImageDraw.Draw(img)
    draw.text((left + 1, top - 38), label, (0, 0, 255), font=fontC)    # 车牌框上方红色汉字
    imagex = np.array(img)
    return imagex

# 判断车牌字符是否有效
def isValidPlate(plate,confidence):
    # 置信度大于0.8，长度等于7或8（绿牌） ， 车牌第一个字符应是省名
    if confidence > 0.8 and (len(plate) == 7 or len(plate) == 8) and plate[0]  in Sheng:
        return True
    return False

# 在输入图片中定位并识别车牌字符，返回绘制的图片及检测结果
def SimpleRecognizePlate(image):
    # t0 = time.time()
    # 粗定位
    images = pp.detect.detectPlateRough(
        image, image.shape[0], top_bottom_padding_rate=0.02)
    # t1 = time.time()-t0
    # print("初定位时间：", t1)

    res_set = []

    # 循环遍历发现的每个车牌
    for j, plate in enumerate(images):
        plate, rect, origin_plate = plate
        # 调整车牌到统一大小
        plate = cv2.resize(plate, (136, 36 * 2))
        # cv2.imshow("test", plate);
        # cv2.waitKey(0)
        # 判断车牌颜色
        plate_type = pp.td.SimplePredict(plate)
        plate_color = plateTypeName[plate_type]

        if (plate_type > 0) and (plate_type < 5):
            plate = cv2.bitwise_not(plate)


        # 精定位，倾斜校正
        # t2 = time.time()
        image_rgb = pp.fm.findContoursAndDrawBoundingBox(plate)
        # cv2.imshow("test", image_rgb);
        # cv2.waitKey(0);
        # print("精定位时间：", time.time() - t2)
        # 车牌左右边界修正
        # t3 = time.time()
        image_rgb = pp.fv.finemappingVertical(image_rgb)
        # print("左右修正时间：", time.time() - t3)

        # e2e 车牌字符识别
        # t4 = time.time()
        e2e_plate, e2e_confidence = pp.e2e.recognizeOne(image_rgb)
        # print("e2e识别时间：", time.time() - t4)
        # t5 = time.time() - t0
        # print(e2e_plate, e2e_confidence, t5, "s")
        if isValidPlate(e2e_plate, e2e_confidence):  # 判断是否是有效车牌
            # 在原图中绘制定位框及车牌信息，传入定位框左上点和右下点xy坐标
            image = drawPred(image, e2e_plate, int(rect[0]),int(rect[1]),int(rect[0]+rect[2]),int(rect[1]+rect[3]))
            # 设置检测结果
            res_set.append([e2e_plate,  # 结果车牌号
                            plate_color,  # 车牌颜色
                            e2e_confidence,  # 车牌字符置信度
                            (rect[0], rect[1])])  # 车牌定位框左上点坐标
    return image, res_set

test_dir = "./test-imgs"   # 图片读入路径
fw = open("./test-results/No14007mresults.txt", 'w+')  # 以覆盖写方式打开结果文件，如果不存在，则新建一个
# 循环遍历文件夹下所有的文件
for f in os.listdir(test_dir):
    try:
        path = os.path.join(test_dir, f)  # 生成完整文件路径
        # t0 = time.time();
        image = cv2.imdecode(np.fromfile(path, dtype=np.uint8), -1)  # 读取图片文件
        # print("img load time:",time.time()-t0)
        h = 1024   # 720 ,image.shape[0] ，指定缩放高度
        scale = image.shape[1] / float(image.shape[0])   # 原图宽高比
        w = int(scale * h)
        image = cv2.resize(image, (w, h))   # 将原图像缩放到指定高度，保持原图像高宽比

        t0 = time.time()
        framedrawed, res = SimpleRecognizePlate(image)   # 针对缩放后的图片，检测识别车牌；返回的是缩放后的图片
        tlabel = '%.0f ms' % ((time.time() - t0) * 1000)
        # 输出车牌检测信息
        info = f + "\n"  # 输出信息,文件名+换行符
        # 循环遍历检测结果，将车牌省名替换为相应拼音
        # print(res)
        for r in res:
            py = plateSheng[r[0][0]]  # 获取结果中车牌的第一个字符省名，获取省名对应的拼音
            plate = r[0].replace(r[0][0], py)  # 将省名替换为拼音
            info = info + plate + "\n"  # 拼接结果字符串

        fw.write(info)  # 写入检测信息到结果文本文件
        # cv2.imwrite("./test-results/" + f, framedrawed.astype(np.uint8))  # 保存图片
        print(info[:-1]) # 屏幕输出结果
        print(tlabel) # 输出处理时间
    except Exception as e:
        print(e)      # 输出异常信息，调试用，发布时应注释掉
        continue       # 出现异常则继续循环读取

fw.close()
cv2.destroyAllWindows()