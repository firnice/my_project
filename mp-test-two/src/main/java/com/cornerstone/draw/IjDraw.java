package com.cornerstone.draw;


import ij.IJ;
import ij.ImagePlus;
import ij.gui.OvalRoi;
import ij.gui.Roi;
import ij.process.ImageProcessor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IjDraw {


    public static void main(String[] args) throws IOException {

        long l = System.currentTimeMillis();
        int imageWidth = 544;
        int imageHeight = 960;

        ImagePlus imp = IJ.createImage("My new image", "RGB black", imageWidth, imageHeight, 16);


        ImagePlus imp_2 = IJ.openImage("/Users/yiruike/IdeaProjects/my_project/mp-test-two/src/main/resources/static/images/img_2.png");

        ImageProcessor ip = imp_2.getProcessor();
//        Roi roi = new Roi(0, 0, 100, 100); // x, y, width, height of the rectangle
//        ip.setRoi(roi);
////        ip.setValue(255);
//        ip.fill(imp_2.getProcessor());

        OvalRoi ovalRoi = new OvalRoi(0, 0, 100, 150);
        ovalRoi.setImage(imp_2);
        ip.setRoi(ovalRoi);
//        ip.setValue(255);
        ip.fill(ip.getMask());


        imp.updateAndDraw();
        System.out.println("draw " + (System.currentTimeMillis() - l));
        long l1 = System.currentTimeMillis();
        IJ.save(imp, "/Users/yiruike/IdeaProjects/my_project/mp-test-two/src/main/resources/image.jpeg");
        System.out.println("save " + (System.currentTimeMillis() - l1));


    }


}
