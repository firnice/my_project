package com;

import cn.hutool.core.util.ZipUtil;

import java.io.File;

public class Ziptest {
    public static void main(String[] args) {
        File unzip = ZipUtil.unzip("/Users/yiruike/Downloads/使用过小红书.zip", "/Users/yiruike/Downloads/aaa");

    }
}
