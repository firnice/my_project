package com.cornerstone.lottie;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class Lottie8 {
    


    public static void main(String[] args) throws InterruptedException, MalformedURLException {

        String url = "http://localhost:8033/wx/ft/index.html?w=720&h=1280&json=https://wechat-mini-program-beta.oss-cn-beijing.aliyuncs.com/model/1/std.json&path=https://wechat-mini-program-beta.oss-cn-beijing.aliyuncs.com/wx/ft/u/30/";

        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        // You can optionally pass a Settings object here,
        // constructed using Settings.Builder
//        JBrowserDriver driver = new JBrowserDriver(caps);
//        JBrowserDriver driver = new JBrowserDriver(Settings.builder().timezone(Timezone.ASIA_SHANGHAI).build());
//        WebDriver driver = new JBrowserDriver(Settings.builder().userAgent(UserAgent.CHROME).build());


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); //无浏览器模式
        options.merge(caps);
//        WebDriver driver = new ChromeDriver(options);
        WebDriver driver = new RemoteWebDriver(new URL("http://172.17.210.100:8039/wd/hub"),caps);


        // This will block for the page load and any
        // associated AJAX requests
//        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
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


                if (entry.getMessage().indexOf("data:image/jpeg;base64") > 0) {
                    String substring = entry.getMessage().substring(entry.getMessage().indexOf("\"data:image/jpeg;base64"));
                    if (substring.endsWith("\"") && substring.startsWith("\"")) {
                        substring = substring.substring(1, substring.length() - 1);
                    } else if (substring.startsWith("\"")) {
                        substring = substring.substring(1, substring.length());
                    } else if (substring.endsWith("\"")) {
                        substring = substring.substring(0, substring.length() - 1);
                    }
                    String[] split = substring.split("###");
                    for (String s : split) {
                        out.write(s);
                        out.newLine();  //注意\n不一定在各种计算机上都能产生换行的效果
                    }

                }
            }
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // You can get status code unlike other Selenium drivers.
        // It blocks for AJAX requests and page loads after clicks
        // and keyboard events.
//        System.out.println(driver.getStatusCode());

        // Returns the page source in its current state, including
        // any DOM updates that occurred after page load
//        System.out.println(driver.getPageSource());


        WebElement body = driver.findElement(By.tagName("body"));
        System.out.println(body.getText());

        // Close the browser. Allows this thread to terminate.
        driver.quit();

    }



}
