package com.van.test.selenium.util;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class ChromeDriverUtil {

    private final static String path = "/Users/mac/Desktop/driver/chromedriver";

    private static RemoteWebDriver webDriver;

    public static RemoteWebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", path);
        if (null == webDriver) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setHeadless(false);
            webDriver = new ChromeDriver(chromeOptions);
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return webDriver;
    }
}
