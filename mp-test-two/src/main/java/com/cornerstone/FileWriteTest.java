package com.cornerstone;


import com.google.common.collect.Lists;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


/**
 * 文件写入
 */
public class FileWriteTest {

    public static void main(String[] args) throws IOException {

        List<String> list = Lists.newArrayList();
        for (int i = 0; i < 10000000; i++) {
            list.add("0e7d1e44f4069848c9469bfb593265b4|1|2|4|");
        }

        writeFile(list);
    }

    public static void writeFile(List<String> list) {
        long index = 0;
        BufferedWriter bw;
        try {
            FileWriter fw = new FileWriter("/Users/yiruike/IdeaProjects/tmp/bigdata/fw-"+System.currentTimeMillis()+".txt", true);
            bw = new BufferedWriter(fw);
            for (String s : list) {
                bw.write(s);
                index += 1;
                if (index % 1000 == 0) {
                    bw.newLine();
                    bw.flush();
                } else {
                    bw.write("#");
                }
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
