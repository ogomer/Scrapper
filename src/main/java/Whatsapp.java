import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Whatsapp {


    public static void main(String[] args) throws InterruptedException {
        List<String> listOfValues = new ArrayList<>();
        listOfValues.add("WEABE20");
        listOfValues.add("WEACJ20");
        listOfValues.add("AAMBQ20");
        listOfValues.add("AAMBW20");
        listOfValues.add("WEABF20");
        listOfValues.add("WEACL20");
        listOfValues.add("WEACT20");
        listOfValues.add("WEACC20");

        System.setProperty("webdriver.chrome.driver", "chrome/chromedriver2.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://pmc.platts.com/Login.aspx?ReturnUrl=%2FMQT%2FMQTHome.aspx%3Fnl%3DMarket%2520Data%2520Snapshot%26nl2%3DSnapshot%26nl4%3DMore%2520Products&nl=Market%20Data%20Snapshot&nl2=Snapshot&nl4=More%20Products");
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);


        // String PERSON_NAME = "Oleg";
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
        for (String value :listOfValues) {
            driver.findElement(By.id("ctl00_cphMain_txtSymbol")).sendKeys(value);
            driver.findElement(By.id("btnDrawChart")).click();

            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            driver.findElement(By.id("aPrice")).click();
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            List<WebElement> elem = driver.findElements(By.id("gbox_gpPrice"));
            WebElement webElement = elem.get(0);
            String s = webElement.getText().replaceFirst("  ", "");
            System.out.println(s);
            driver.findElement(By.id("ctl00_cphMain_txtSymbol")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Keys.DELETE);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        }
        //       System.out.println(elem.size());


        //list.add(webElement.getText().replaceFirst("  ", ""));
        //}


        //     for (String value : blackList) {
        //       removeAll(list,value);
        // System.out.println(value);

        // }
        // System.out.println(list);
    }
    //river.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

    // List<WebElement> elem1 = driver.findElements(By.className("input"));
    //for (int i = 0; i < 1; i++) {
    //  elem1.get(1).sendKeys("Robot");//
    //driver.findElement(By.className("send-container")).click();


    //   String tagName = elementByXPath.getTagName();
    //  System.out.println(tagName);
    // String currentUrl = driver.getCurrentUrl();

    public static void removeAll(List<String> list, String element) {
        int index;
        while ((index = list.indexOf(element)) >= 0) {
            list.remove(index);
        }
    }
//}
}
