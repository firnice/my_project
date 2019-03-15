package com.cornerstone;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Lottie7 {

    public static void main(String[] args)  {

        String url = "http://localhost:8033/wx/ft/index.html?w=720&h=1280&json=https://wechat-mini-program-beta.oss-cn-beijing.aliyuncs.com/model/1/std.json&path=https://wechat-mini-program-beta.oss-cn-beijing.aliyuncs.com/wx/ft/u/30/";

        //设置必要参数
        DesiredCapabilities dcaps = new DesiredCapabilities();
        //ssl证书支持
        dcaps.setCapability("acceptSslCerts", true);
        //截屏支持
        dcaps.setCapability("takesScreenshot", true);
        //css搜索支持
        dcaps.setCapability("cssSelectorsEnabled", true);
        //js支持
        dcaps.setJavascriptEnabled(true);
        //驱动支持（第二参数表明的是你的phantomjs引擎所在的路径）
//        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
//                "/Users/hetiantian/SoftWares/phantomjs/bin/phantomjs");
        //创建无界面浏览器对象
        PhantomJSDriver driver = new PhantomJSDriver(dcaps);

        //设置隐性等待（作用于全局）
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        long start = System.currentTimeMillis();
        //打开页面
        driver.get(url);

        (new WebDriverWait(driver, 1000)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese");
            }
        });
//
//
        String fileName = "/Users/yiruike/IdeaProjects/my_project/mp-test-two/src/main/resources/data.txt";
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));

            LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
            for (LogEntry entry : logEntries) {
//                System.out.println("chrome.console====" + " " + entry.getLevel() + " " + entry.getMessage());
                out.write(entry.getMessage());
                out.newLine();  //注意\n不一定在各种计算机上都能产生换行的效果
            }
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
