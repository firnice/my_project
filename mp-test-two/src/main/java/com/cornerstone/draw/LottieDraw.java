package com.cornerstone.draw;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LottieDraw {


    public static void main(String[] args) throws IOException {


        int imageWidth = 720;
        int imageHeight = 1280;


//
//        Canvas canvas = new Canvas();
//        canvas.createBufferStrategy(3);
//
//        canvas.setSize(imageWidth, imageHeight);
//        canvas.setBackground(Color.BLACK);
//        canvas.setVisible(true);
//        canvas.setFocusable(false);

        BufferedImage image_b = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        BufferedImage image = ImageIO.read(new File("/Users/yiruike/IdeaProjects/my_project/mp-test-two/src/main/resources/static/images/img_2.png"));
        Graphics graphics = image_b.getGraphics();
//        int fontSize = 100;
//        Font font = new Font("楷体", Font.PLAIN, fontSize);
//        graphics.setFont(font);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,imageWidth,imageHeight);
//        graphics.fillRect(0, 0, imageWidth, imageHeight);
        graphics.drawImage(image,0,0,null);
//        graphics.setColor(new Color(255, 255, 255));
//        int strWidth = graphics.getFontMetrics().stringWidth("好");
//        graphics.drawString("好", fontSize - (strWidth / 2), fontSize + 30);


        ImageIO.write(image_b, "png", new File("/Users/yiruike/IdeaProjects/my_project/mp-test-two/src/main/resources/abc.png"));
        graphics.dispose();
    }


}
