package com.cornerstone;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class FileRead {

    public static void main(String[] args) throws IOException {
        //BufferedReader是可以按行读取文件
        FileInputStream inputStream = new FileInputStream("/Users/yiruike/Downloads/xiaonian.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


        File file = new File("/Users/yiruike/Downloads/xiaonian.data");
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);


        String str = null;
        int frame = 0;
        while ((str = bufferedReader.readLine()) != null) {
            String arr = "";
            if (str.startsWith("I/LottieAnimationView: ")) {
                String substring = str.substring(str.indexOf("frame:") + "frame:".length(), str.length());
                frame = Integer.valueOf(substring);
//                System.out.println(frame);
            } else if (str.startsWith("I/B612: ")) {
                str = str.substring("I/B612: ".length(), str.length());
                JSONObject json_test = JSON.parseObject(str);
//                System.out.println(frame);
//                System.out.println(json_test.getString("layerId"));
//                System.out.println(json_test.getString("img_id"));
//                System.out.println(json_test.getString("Matrix"));

                arr = String.format("%s %s %s %s", frame, json_test.getString("layerId"), json_test.getString("img_id"), json_test.getString("Matrix"));
                bw.write(arr + "\r\n");

            } else if (str.indexOf("layerId") > 0) {
                JSONObject json_test = JSON.parseObject(str.trim());
//                System.out.println(frame);
//                System.out.println(json_test.getString("layerId"));
//                System.out.println(json_test.getString("img_id"));
//                System.out.println(json_test.getString("Matrix"));
                arr = String.format("%s %s %s %s", frame, json_test.getString("layerId"), json_test.getString("img_id"), json_test.getString("Matrix"));
                bw.write(arr + "\r\n");

            } else {
                System.out.println(str);

            }

        }

        bw.close();
        fw.close();

        //close
        inputStream.close();
        bufferedReader.close();
    }
}
