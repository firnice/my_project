package com.firnice.test.file;

import java.io.*;
import java.util.UUID;

public class FileTest3 {

    public static void main(String[] args) {

            try {
                File file = new File("/Users/yiruike/Downloads/1tianmao.txt");     //文件路径（路径+文件名）
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(file));
                    StringBuffer tempString = new StringBuffer();
                    // 一次读入一行，直到读入null为文件结束
                    int i =0;
                    int j =0;
                    while (reader.readLine() != null) {
                        tempString.append(reader.readLine()+"\n");
                        i++;
                        if(i==1){
                            System.out.print(tempString.toString());
                            tempString.setLength(0);
//                            tempString= new StringBuffer();
                            i=0;
                            j++;
                            if(j>2){
                                break;
                            }
                        }
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e1) {
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
}
