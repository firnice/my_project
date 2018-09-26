package com.firnice.test.file;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

public class FileTest1 {

    public static void main(String[] args) {

            try {
                File file = new File("/Users/yiruike/Downloads/8big.txt");     //文件路径（路径+文件名）
                if (!file.exists()) {   //文件不存在则创建文件，先创建目录
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                FileOutputStream outStream = new FileOutputStream(file);    //文件输出流用于将数据写入文件
                for(int i =0 ; i<10;i++){
                    UUID uuid = UUID.randomUUID();
                    byte[] sourceByte = uuid.toString().getBytes();
                    outStream.write(sourceByte);
                    outStream.write('\n');
                }
                outStream.close();  //关闭文件输出流
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

}
