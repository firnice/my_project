package com.firnice.test.httpclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: liyl
 * @date: 2017/10/20 上午9:47
 * @since 1.0.0
 */
public class HuajiaoFile {
    public static String[] file = {
        "huajiao_2017-10-19 20:50:27.txt",
        "huajiao_2017-10-19 20:51:26.txt",
        "huajiao_2017-10-19 20:52:26.txt",
        "huajiao_2017-10-19 20:53:26.txt",
        "huajiao_2017-10-19 20:54:26.txt",
        "huajiao_2017-10-19 20:55:28.txt",
        "huajiao_2017-10-19 20:56:26.txt",
        "huajiao_2017-10-19 20:57:26.txt",
        "huajiao_2017-10-19 20:58:26.txt",
        "huajiao_2017-10-19 20:59:26.txt",
        "huajiao_2017-10-19 21:00:26.txt",
        "huajiao_2017-10-19 21:01:28.txt",
        "huajiao_2017-10-19 21:02:26.txt",
        "huajiao_2017-10-19 21:03:26.txt",
        "huajiao_2017-10-20 08:45:01.txt",
        "huajiao_2017-10-20 08:47:20.txt",
        "huajiao_2017-10-20 08:52:51.txt",
        "huajiao_2017-10-20 08:57:48.txt",
        "huajiao_2017-10-20 09:02:02.txt",
        "huajiao_2017-10-20 09:08:33.txt",
        "huajiao_2017-10-20 09:13:33.txt",
        "huajiao_2017-10-20 09:18:32.txt",
        "huajiao_2017-10-20 09:23:32.txt",
        "huajiao_2017-10-20 09:28:33.txt"};

    public static void main(String[] args) {
        try {
            String split_str = "@@@###&&&";
            for (int i = 0; i < file.length; i++) {
                String filepath = "/Users/firnice/Downloads/" + file[i];
                FileInputStream fileInputStream = new FileInputStream(filepath);
                File file2 = new File("/Users/firnice/Downloads/huajiao_log/"+file[i] + ".txt");
                try {
                    file2.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                FileOutputStream out = new FileOutputStream(file2);
                BufferedReader in = new BufferedReader(new InputStreamReader(fileInputStream));

                String line = "";
                while ((line = in.readLine()) != null) {
                    StringBuffer tmp = new StringBuffer();
                    tmp.append(line.substring(0, line.indexOf(" ")) + split_str);

                    String str1 = line.substring(line.indexOf(" ") + 1, line.length());
                    tmp.append(str1.substring(0, str1.indexOf(" ")) + split_str);

                    String str2 = str1.substring(str1.indexOf(" ") + 1, str1.length());
                    tmp.append(str2.substring(0, str2.indexOf(" ")) + split_str);

                    String str3 = str2.substring(str2.indexOf(" ") + 1, str2.length());
                    tmp.append(str3.substring(0, str3.indexOf("2017-10") - 1) + split_str);

                    String str4 = str3.substring(str3.indexOf("2017-10"), str3.length());
                    tmp.append(str4);
                    tmp.append("\r\n");
                    out.write(tmp.toString().getBytes());
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
