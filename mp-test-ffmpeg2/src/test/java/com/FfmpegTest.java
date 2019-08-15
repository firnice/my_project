package com;

import cn.hutool.core.util.RuntimeUtil;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.builder.FFmpegOutputBuilder;

import java.io.IOException;
import java.util.List;

public class FfmpegTest {

    public static void main(String[] args) throws IOException {

        String str = RuntimeUtil.execForStr("ffmpeg -loop 1 -r 10 -i /Users/yiruike/Documents/picture_material/video_concat/3.jpg -vcodec libx264 -vf scale=2*trunc(iw/2):-2,setsar=1 -pix_fmt yuv420p -ss 0 -t 1.8 -y /Users/yiruike/Documents/picture_material/video_concat/1ab.mp4");
        System.out.println(str);
//        List<String> strs = RuntimeUtil.execForLines("ffmpeg -loop 1 -r 10 -i /Users/yiruike/Documents/picture_material/video_concat/3.jpg -vcodec libx264 -vf \"scale=2*trunc(iw/2):-2,setsar=1\" -pix_fmt yuv420p -ss 0 -t 1.8 -y /Users/yiruike/Documents/picture_material/video_concat/1ab.mp4");
//        for (String s : strs) {
//            System.out.println(s);
//        }

    }
}
