package com.github.tonydeng.fmj.runner;

import com.github.tonydeng.fmj.BaseTest;
import com.github.tonydeng.fmj.model.HLS;
import com.github.tonydeng.fmj.model.VideoFile;
import com.github.tonydeng.fmj.model.VideoInfo;
import com.github.tonydeng.fmj.utils.FileUtils;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by tonydeng on 15/4/16.
 */
//@Ignore
public class FFmpegCommandRunnerTest2 extends BaseTest {

    private static final String PATH = "/Users/yiruike/Documents/picture_material/video_concat/";

    private static final List<File> inputs_mac = Lists.newArrayList(
//            new File("/Users/tonydeng/temp/m3u8/2013.flv"),
//
            new File(PATH + "a1.ts"),
            new File(PATH + "a2.ts")
//            new File(PATH + "3.mp4")
//            new File("/Users/tonydeng/temp/m3u8/IMG_1666.MOV")

    );
    private static List<File> inputs;

    @Before
    public void init() {
        String env = System.getProperty("os.name");
        if (null != env) {
            String os = env.toLowerCase();
            if (os.indexOf("mac") >= 0) {
                log.info("****************** is MAC OS");
                inputs = inputs_mac;
            }
        }
    }

    @Test
    public void getVideoInfoTest() {

        for (File input : inputs) {
            VideoInfo vi = FFmpegCommandRunner.getVideoInfo(input);
            log.info("{} video info: {}", input.getAbsoluteFile(), vi.toString());
        }
    }

    //        @Test
    public void screenshotTest() {
        for (File input : inputs) {
            File output = FFmpegCommandRunner.screenshot(input,
                    10);
            log.info("input : {}, output:{}", input.getAbsoluteFile(), output.getAbsoluteFile());
        }
    }

    @Test
    public void generationHlsTest() {
        for (File input : inputs) {
            HLS hls = FFmpegCommandRunner.generationHls(input, 3, "http://dl.duoquyuedu.com/m3u8/" + FileUtils.getFileName(input) + "/");
            if (hls != null) {
                log.info("m3u8 path:'{}'", hls.getM3u8().getAbsolutePath());
                for (File ts : hls.getTs()) {
                    log.info("ts path:'{}'", ts.getAbsolutePath());
                }
            }
        }
    }

    @Test
    public void coverToMp4Test() {
        for (File input : inputs) {
            VideoFile vf = FFmpegCommandRunner.coverToMp4(input);
            if (vf.isSuccess())
                log.info(vf.toString());
        }
    }

    @Test
    public void parallelCoverMp4Test() {
        for (File input : inputs) {
            VideoInfo vi = FFmpegCommandRunner.getVideoInfo(input);
            List<String> commands = Lists.newArrayList(
                    "parallel",
                    "--will-cite",
                    "-j", "64",
                    "ffmpeg",
                    "-i", "{}",
                    "-vf", "transpose=1",
                    "-c:v", "ibx264",
                    "-c:v", "libx264", "-c:a", "aac",
                    " -strict", "-2", "-threads", "8",
                    "{.}_paraller.mp4",
                    ":::", input.getAbsolutePath()
            );

//            log.info(FFmpegUtils.ffmpegCmdLine(commands));
            FFmpegCommandRunner.runProcess(commands);
        }


//        commands.addAll(BaseCommandOption.toMP4CmdArrays(,"/Users/tonydeng/temp/m3u8/MG_1666.mp4"));

    }


    @Test
    public void parallelCoverTs() {
//ffmpeg -loop 1 -r 10 -i 1051559805990_.pic.jpg -vcodec libx264 -r 10 -b 200k -ss 0 -t 1.7 -y a2.mp4

//ffmpeg  -loop 1  -r 10 -i /Users/yiruike/Documents/picture_material/video concat/1.png -vcodec libx264 -r 10 -b 200k -ss 0 -t 1.8 -y 1.mp4 '

        File input = new File(PATH + "3.jpg");
        VideoInfo vi = FFmpegCommandRunner.getVideoInfo(input);
//        List<String> commands = Lists.newArrayList(BaseCommandOption.getFFmpegBinary());


//        PATH +"2.jpg";
//        PATH +"3.jpg";
//        PATH +"4.png";
        List<String> commands = Lists.newArrayList(
//                "parallel",
//                "--will-cite",
//                "-j", "64",
                "ffmpeg",
                "-loop", "1",
                "-r", "10",
                "-i", input.getAbsolutePath(),
                "-vcodec", "libx264",
                "-vf", "scale=2*trunc(iw/2):-2,setsar=1",
                "-pix_fmt","yuv420p",
//                "-r", "10",
//                "-b", "200k",
                "-ss", "0",
                "-t", "1.8",
                "-y", PATH+"1ab.ts"

        );
//        commands.addAll(commands2);
        FFmpegCommandRunner.runProcess(commands);
    }
}
