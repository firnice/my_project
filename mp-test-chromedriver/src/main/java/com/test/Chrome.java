package com.test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class Chrome {

    public static void main(String[] args) throws InterruptedException {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu"); //无浏览器模式
        options.merge(caps);
        ChromeDriver driver = new ChromeDriver(options);

        for (int i = 0; i < 10; i++) {
            driver.get("https://www.cnblogs.com/zhouyang209117/p/5100977.html");
            System.out.println(driver.getTitle());
            Thread.sleep(2000);
            driver.get("https://www.baidu.com");
            System.out.println(driver.getTitle());
        }
        driver.close();
        driver.quit();
    }
}
