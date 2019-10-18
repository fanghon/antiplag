# coding=utf-8
#导入包
from hyperlpr import *
#导入OpenCV库
import cv2
#读入图片
image = cv2.imread("anpimages/outdoorAA-1.jpg")
#识别结果
print(HyperLPR_PlateRecogntion(image))