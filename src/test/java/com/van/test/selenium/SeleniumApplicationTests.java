package com.van.test.selenium;

import cn.hutool.core.util.RandomUtil;
import com.van.test.selenium.util.ChromeDriverUtil;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@SpringBootTest
class SeleniumApplicationTests {

    @Test
    void contextLoads() throws InterruptedException {
//        long wait = RandomUtil.randomInt(2, 5) * 1000L;
        long wait = 0L;

        RemoteWebDriver browser = ChromeDriverUtil.getChromeDriver();
        browser.get("http://www.baidu.com");

        Thread.sleep(wait);

        WebElement login = browser.findElementById("s-top-loginbtn");
        login.click();

        Thread.sleep(wait);

        WebElement usernameLogin = browser.findElementById("TANGRAM__PSP_11__footerULoginBtn");
        usernameLogin.click();

        WebElement usernameInput = browser.findElementById("TANGRAM__PSP_11__userName");
        usernameInput.sendKeys("18795900698");
        WebElement passwordInput = browser.findElementById("TANGRAM__PSP_11__password");
        passwordInput.sendKeys("gaofan.1994");

        Thread.sleep(wait);

        WebElement submit = browser.findElementById("TANGRAM__PSP_11__submit");
        submit.click();

        Thread.sleep(wait);

        WebElement slide = browser.findElementByClassName("vcode-spin-button");

        // 获取ID的随机数
        WebElement vcodesElemet = browser.findElement(By.className("mod-vcodes"));
        String num = vcodesElemet.getAttribute("id");
        num = num.split("mod-vcodes")[num.split("mod-vcodes").length - 1];
        WebElement imgElemet = browser.findElement(By.id("vcode-spin-img" + num));
        File img = getImgFile(browser, imgElemet.getLocation().getX() - 8, imgElemet.getLocation().getY());


//        move(browser, slide, 50);
    }

    public static void move(WebDriver driver, WebElement element, int distance) throws InterruptedException {
        int moveX = new Random().nextInt(10) - 5;
        int moveY = 1;
        Actions actions = new Actions(driver);
        new Actions(driver).clickAndHold(element).perform();//click and hold the moveButton
        Thread.sleep(2000);//slow down
        actions.moveByOffset((distance + moveX) / 2, moveY).perform();
        Thread.sleep((int) (Math.random() * 2000));
        actions.moveByOffset((distance + moveX) / 2, moveY).perform();//double move,to slow down the move and escape check
        Thread.sleep(500);
        actions.release(element).perform();
    }

    /**
     * 截图（验证码） 这里的 152 是页面显示图片的实际宽高
     */
    private File getImgFile(WebDriver driver, int i, int j) {
        BufferedImage imgbuff = null;
        File srcFile, imgFile = null;
        try {
            srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            imgbuff = ImageIO.read(srcFile).getSubimage(i, j, 152, 152);
            imgFile = new File("/Users/mac/Desktop/screenshot/" + System.currentTimeMillis() + ".png");
            ImageIO.write(imgbuff, "png", imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgFile;
    }

}
