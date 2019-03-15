package com.cornerstone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Lottie4 {

    public static WebDriver driver;


    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));


        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); //无浏览器模式
        options.merge(caps);
        driver = new ChromeDriver(options);

        for (int i = 0; i < 20; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
        }
        System.out.println("end");
        executor.shutdown();
//        driver.quit();


    }


    static class MyTask implements Runnable {
        private int taskNum;


        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行task " + taskNum);


            long start = System.currentTimeMillis();

            driver.get("http://localhost:9090/index.html");


            (new WebDriverWait(driver, 1000)).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver d) {
                    return d.getTitle().toLowerCase().startsWith("cheese");
                }
            });
            String fileName = "/Users/yiruike/IdeaProjects/my_project/mp-test-two/src/main/resources/data_" + taskNum + ".txt";

            File file = new File(fileName);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
                LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
                for (LogEntry entry : logEntries) {
//                System.out.println("chrome.console====" + " " + entry.getLevel() + " " + entry.getMessage());
                    out.write(entry.getMessage());
                    out.newLine();  //注意\n不一定在各种计算机上都能产生换行的效果
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            WebElement body = driver.findElement(By.tagName("body"));
            System.out.println(body.getText());


            driver.close();
            System.out.println("task " + taskNum + "执行完毕 time:" + (System.currentTimeMillis() - start));
        }
    }

}
