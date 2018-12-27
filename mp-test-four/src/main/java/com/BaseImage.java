package com;

import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BaseImage {

    public static void main(String[] args) throws IOException {


        File file = new File("/Users/yiruike/IdeaProjects/my_project/mp-test-four/src/main/java/com/image");
        String s = IOUtils.toString(new FileInputStream(file), "UTF-8");
        String partSeparator = ",";
        if (s.contains(partSeparator) ){
            String encodedImg = s.split(partSeparator)[1];
            byte[] decodedImg = Base64.getDecoder().decode(encodedImg.getBytes(StandardCharsets.UTF_8));
            ByteArrayInputStream bais = new ByteArrayInputStream(decodedImg);
            BufferedImage bi1 = ImageIO.read(bais);
            try {
                File w2 = new File("./03.jpg");//可以是jpg,png,gif格式
                ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                bais.close();
            }

        }


    }
}
