package com.mygit.controller;

import com.mygit.utils.HttpClientUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.List;
import java.util.UUID;

/**
 * Created by yxf on 2017/12/5.
 */
public class PhantomjsController {

    public static void main(String[] args) throws Exception{
        phantomjsDriver();
    }

    public static void phantomjsDriver() throws Exception{
        System.setProperty("phantomjs.binary.path", "D:\\phantomjs-2.1.1\\bin\\phantomjs.exe");
        //    WebDriver driver = new FirefoxDriver();
        WebDriver driver = new PhantomJSDriver();
        driver.get("http://tieba.baidu.com/p/3771448443#!/l/p1");
        List<WebElement> images = driver.findElements(By.tagName("img"));
        for (WebElement element:images) {
            System.out.println(element.getAttribute("src"));
            HttpClientHello3img.downloadImg(element.getAttribute("src"), UUID.randomUUID().toString()+".png");
        }
    }

    public static void ById(){
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver  = new ChromeDriver();
        driver.get("http://www.baidu.com");
        //在Selenium/WebDriver 中通过ID查找元素的Java示例代码如下
        WebElement searchBox = driver.findElement(By.id("kw"));
        searchBox.sendKeys("小坦克 博客园");
        WebElement searchButton = driver.findElement(By.id("su"));
        searchButton.submit();
    }

    public static void ByName(){
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver  = new ChromeDriver();
        driver.get("http://www.baidu.com");
        //在Selenium/WebDriver 中通过ID查找元素的Java示例代码如下
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("小坦克");
        searchBox.submit();
    }

    public static void ByTagName(){
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver  = new ChromeDriver();
        driver.get("http://tieba.baidu.com/p/3771448443#!/l/p1");
        List<WebElement> images = driver.findElements(By.tagName("image"));
        System.out.println(images.size());
        for (WebElement webElement : images) {
            System.out.println(webElement.getTagName());
            if ("text".equals(webElement.getAttribute("type"))) {
                System.out.println("input text is :" + webElement.getText());
            }
        }
    }


    public static void ByClassName() throws Exception{
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver  = new ChromeDriver();
        driver.get("http://www.taobao.com");
        Thread.sleep(15000);
        WebElement searchBox = driver.findElement(By.className("search-combobox-input"));

        searchBox.sendKeys("羽绒服");
        searchBox.submit();
    }

    public static void BylinkText(){
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver  = new ChromeDriver();
        driver.get("http://www.baidu.com");
        WebElement loginLink = driver.findElement(By.linkText("登录"));
        loginLink.click();
    }

    public static void BypartialLinkText(){
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver  = new ChromeDriver();
        driver.get("http://www.baidu.com");
        WebElement loginLink = driver.findElement(By.partialLinkText("登"));
        loginLink.click();
    }





}
