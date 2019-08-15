package com;

import org.opencv.videoio.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.*;

public class ExtractionVideoKeyFrames {

    Mat computeColorHist3D(Mat im, Size size)
    {
        Mat dst = new Mat(), hist = new Mat();
        Imgproc.resize(im, dst, size, 0, 0, Imgproc.INTER_NEAREST);
        Mat hsv_src = new Mat();
        Imgproc.cvtColor(im, hsv_src, Imgproc.COLOR_BGR2HSV);
        //		Mat gray = new Mat(im.size(),CvType.CV_8UC1);
        //      Imgproc.cvtColor(im,gray,Imgproc.COLOR_RGB2GRAY);
        //
        //		List<Mat> images = new ArrayList<Mat>();
        //      images.add(gray);

        List<Mat> images=new ArrayList<Mat>(3);
        images.add(hsv_src);
        //Core.split(hsv_src, images);

        MatOfInt channels= new MatOfInt(0);
        MatOfInt hSize = new MatOfInt(256);
        MatOfFloat ranges= new MatOfFloat(0,255);
        Imgproc.calcHist(images, channels, new Mat(), hist, hSize, ranges);
        //System.out.println(""+hist.rows()+","+hist.cols()+","+ hist.channels());
        return hist;
    }

    Mat computeColorHist(Mat im, Size size)
    {
        Mat dst = new Mat(), hist = new Mat();
        Imgproc.resize(im, dst, size, 0, 0, Imgproc.INTER_NEAREST);
        Mat gray = new Mat(im.size(),CvType.CV_8UC1);
        Imgproc.cvtColor(im,gray,Imgproc.COLOR_RGB2GRAY);

        List<Mat> images = new ArrayList<Mat>();
        images.add(gray);

        MatOfInt channels= new MatOfInt(0);
        MatOfInt hSize = new MatOfInt(128);
        MatOfFloat ranges= new MatOfFloat(0,128);
        Imgproc.calcHist(images, channels, new Mat(), hist, hSize, ranges);
        return hist;
    }

    double computeASE(Mat a, Mat b)
    {
        double sumV = 1 - Imgproc.compareHist(a, b, 0);
        return sumV;

        //		Mat dst = new Mat();
        //		Core.absdiff(a, b, dst);
        //
        //		Scalar sumV = Core.sumElems(dst);
        //		return sumV.val[0];
    }

    int ExtractKeyframes(String videoPath, double rate, String outImgPath){
        VideoCapture cap = new VideoCapture();
        System.out.println(cap.isOpened());
        if (!cap.open(videoPath)) {

            System.out.println("Open video file error!");
            return -1;
        }

        File file = new File(outImgPath);
        if(!file.exists()){
            file.mkdirs();
        }

        int width = (int)cap.get(Videoio.CV_CAP_PROP_FRAME_WIDTH);
        int height = (int)cap.get(Videoio.CV_CAP_PROP_FRAME_HEIGHT);
        float fps = (float)cap.get(Videoio.CV_CAP_PROP_FPS);
        int nframes = (int)cap.get(Videoio.CV_CAP_PROP_FRAME_COUNT);
        System.out.println("W:"+width+";H:"+height+";FPS:"+fps+";N:"+nframes);

        int nscale = 1;

        if (width%4==0 && height%4==0)
            nscale = 4;
        else if (width%2==0 && height%2==0)
            nscale = 2;
        Size size = new Size(width/nscale, height/nscale);
        //double fnorm = size.area() * 2;

        Mat frame = new Mat();
        Mat lastFrame = new Mat();
        Mat histA = new Mat();
        Mat histB = new Mat();
        long nCount = 0;
        int frameID = 1; int lastFrameID = 1;
        double tic = (double)Core.getTickCount();
        while (cap.read(frame))
        {
            if (frame.empty()){
                break;
            }

            if (lastFrameID==1)
            {
                frameID = (int)cap.get(Videoio.CV_CAP_PROP_POS_FRAMES);
                lastFrameID = frameID;
                frame.copyTo(lastFrame);
                continue;
            }

            if (++nCount % 15 != 0) continue;	// Processing one frame in each second

            frameID = (int)cap.get(Videoio.CV_CAP_PROP_POS_FRAMES);

            histA = computeColorHist3D(frame, size);

            double aseV = rate;
            if (histB.empty())
                System.out.println(aseV);
            else{
                aseV = computeASE(histA, histB);
                System.out.println(aseV);
            }

            if (aseV >= rate) {
                String imagePath = outImgPath+"/"+lastFrameID+".jpg";
                if (!Imgcodecs.imwrite(imagePath, lastFrame))
                    return -2;
                //if(g_OnSavedKeyFrame != NULL) {
                //sprintf(imagePath,"%d.jpg", lastFrameID);
                //g_OnSavedKeyFrame(inVidPath, imagePath, (int)(lastFrameID/fps),(int)((frameID-lastFrameID)/fps), g_User, g_Data);
                //}
                lastFrameID = frameID;
                frame.copyTo(lastFrame);
            }

            histA.copyTo(histB);
        }

        if (!lastFrame.empty())
        {
            //char imagePath[1024];
            String imagePath=outImgPath+"/"+ lastFrameID+".jpg";
            if (!Imgcodecs.imwrite(imagePath, lastFrame))
                return -2;
            //if(g_OnSavedKeyFrame != NULL) {
            //	sprintf(imagePath,"%d.jpg", lastFrameID);
            //	g_OnSavedKeyFrame(inVidPath, imagePath, (int)(lastFrameID/fps),(int)((frameID-lastFrameID)/fps), g_User, g_Data);
            //}
        }
        System.out.println("NFRAMES(in reality)="+nCount);

        double toc = (double)Core.getTickCount();
        System.out.println("ELAPSED TIME="+(toc-tic)/Core.getTickFrequency());
        return 0;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        Runtime.getRuntime().load("/Users/yiruike/Documents/picture_material/libopencv_java340.so");

//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        ExtractionVideoKeyFrames evf = new ExtractionVideoKeyFrames();
        evf.ExtractKeyframes("/Users/yiruike/Documents/picture_material/B612_1562152124.mp4",0.5,"/Users/yiruike/Documents/picture_material/VideosOutput/");
    }

}