"""
Author: fanghong
edited: 2019.4.23
"""
# coding=utf-8

import sys
import os
import time
import HyperLPRLite as pr
import cv2
import numpy as np


model = pr.LPR("model/cascade.xml","model/model12.h5","model/ocr_plate_all_gru.h5")
imgdir = "./anpimages"
name_list = os.listdir(imgdir)  # 列出文件夹下所有的目录与文件
for i in range(0, len(name_list)):
    path = os.path.join(imgdir, name_list[i])
    print(path)
    # grr = cv2.imread(path)
    # 读图片文件，支持中文文件名
    grr = cv2.imdecode(np.fromfile(path, dtype=np.uint8), -1)
    t0 = time.time()
    for pstr, confidence, rect in model.SimpleRecognizePlateByE2E(grr):
        if confidence > 0.7:
            print(pstr, round(confidence, 3), round(time.time()-t0, 4), "s")
    print("----------------------------------")


