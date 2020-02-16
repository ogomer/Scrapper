package com.org.scrapper;

import com.google.common.collect.Lists;
import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class RatingAutomation {
    private static final String URL = "https://pmc.platts.com/Login.aspx?ReturnUrl=%2FMQT%2FMQTHome.aspx%3Fnl%3DMarket%2520Data%2520Snapshot%26nl2%3DSnapshot%26nl4%3DMore%2520Products&nl=Market%20Data%20Snapshot&nl2=Snapshot&nl4=More%20Products";
   // private static final String PATH = "C:/sources/";
    private static final String EXT = ".txt";
    private static Logger log = Logger.getLogger(RatingAutomation.class.getName());
   // private static List<String> listOfValues = Lists.newArrayList("WEABE20", "WEACJ20", "AAMBQ20", "AAMBW20", "WEABF20", "WEACL20", "WEACT20", "WEACC20");


    public static void main(String[] args) throws InterruptedException, IOException, ConfigurationException {
        executeScrapper();




    }




    private static void executeScrapper() throws InterruptedException, IOException, ConfigurationException {
        PropertiesConfiguration properties = new PropertiesConfiguration("properties");
        WebDriver driver = getWebDriver();
        ElementFinder(driver);
        writeFiles(properties.getList("values"), driver);
        finalStep(driver);
    }

    private static void finalStep(WebDriver driver) {
        driver.findElement(By.id("ctl00_Profile1_lbLogout")).click();
        driver.close();
        log.info("Done");
    }

    private static void writeFiles(List<Object> listOfValues, WebDriver driver) throws IOException, ConfigurationException {
        PropertiesConfiguration properties = new PropertiesConfiguration("properties");
        for (Object value : listOfValues) {
            driver.findElement(By.id("ctl00_cphMain_txtSymbol")).sendKeys(String.valueOf(value));
            driver.findElement(By.id("btnDrawChart")).click();
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            driver.findElement(By.id("aPrice")).click();
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            List<WebElement> elem = driver.findElements(By.id("gbox_gpPrice"));
            WebElement webElement = elem.get(0);
            String s = webElement.getText().replaceFirst(" {2}", "");
            Files.write(Paths.get(properties.getString("path") + value + EXT), s.getBytes());
            driver.findElement(By.id("ctl00_cphMain_txtSymbol")).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        }
    }

    private static void ElementFinder(WebDriver driver) throws InterruptedException {
        driver.findElement(By.id("ctl00_cphMain_txtEmail")).click();
        driver.findElement(By.id("ctl00_cphMain_txtEmail")).sendKeys("EWarner@ormat.com");
        Thread.sleep(300);
        driver.findElement(By.id("ctl00_cphMain_txtPassword")).click();
        driver.findElement(By.id("ctl00_cphMain_txtPassword")).sendKeys("Ormatb34");
        Thread.sleep(100);
        driver.findElement(By.id("ctl00_cphMain_btnLogin")).click();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.id("ctl00_ucMySubscription_rptProd2_ctl01_liTab")).click();
        driver.findElement(By.id("ctl00_ucMySubscription_rptProd2_ctl01_liTab")).click();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.id("ctl00_cphMain_txtSymbol")).click();
    }

    private static WebDriver getWebDriver() throws ConfigurationException {
        PropertiesConfiguration properties = new PropertiesConfiguration("properties");
        System.setProperty("webdriver.chrome.driver", properties.getString("driver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
        WebDriver driver = new ChromeDriver(options);
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        return driver;
    }

}
