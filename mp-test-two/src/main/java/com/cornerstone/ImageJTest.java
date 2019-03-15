package com.cornerstone;


import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.awt.image.IndexColorModel;
import java.io.IOException;

public class ImageJTest {


    public static void main(String[] args) {


        String path = "/Users/yiruike/IdeaProjects/my_project/mp-test-two/src/main/resources/static/images/1.jpg";

        long l = System.currentTimeMillis();
        ImagePlus imp = IJ.openImage(path);

        ImageProcessor ip = imp.getProcessor();

        IndexColorModel icm = ip.getDefaultColorModel();

        int mapSize = icm.getMapSize();

        byte[] Rmap = new byte[mapSize];
        icm.getReds(Rmap);
        byte[] Gmap = new byte[mapSize];
        icm.getGreens(Gmap);
        byte[] Bmap = new byte[mapSize];
        icm.getBlues(Bmap);


        System.out.println(System.currentTimeMillis() - l);

    }


}
