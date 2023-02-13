



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.List;


public class Main {

    @Test
    public void site_header_is_on_home_page() {
        System.out.println("----------STARTING----------");
        System.setProperty("webdriver.chrome.driver","src/driver/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        System.out.println("----------CHROME LOADED----------");
        webDriver.manage().window().maximize();
        System.out.println("----------WINDOW MAXIMIZED----------");
        String urlPath = "https://www.vodacom.co.za/";
        System.out.println("----------LOADING SITE: "+urlPath);
        webDriver.get(urlPath);
        String clicklink= Keys.chord(Keys.CONTROL,Keys.ENTER);
        webDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
        System.out.println("----------WAITING TO LOAD: "+urlPath);


        //Creating object of an Actions class
        Actions action = new Actions(webDriver);

        //Retrieve WebElement 'Shop' to perform mouse hover
        WebElement menuOption = webDriver.findElement(By.xpath(".//h4[contains(text(),'Shop')]"));

        //Mouse hover menuOption 'Music'

        action.moveToElement(menuOption).perform();

        WebElement ele = webDriver.findElement(By.xpath("<xpath>"));



        List<WebElement> l=webDriver.findElements(By.xpath("//*[text()='Cookie Policy']"));
        if(l.size()== 0){
            // empty list if no matching element
            System.out.println("Element not present, appearing "+l.size()+ " time");
        } else {
            System.out.println("Element present, appearing "+l.size()+ " time");
            webDriver.findElement(By.id("onetrust-accept-btn-handler")).sendKeys(clicklink);//click myaccount
        }





        //Performing the mouse hover action on the target element.
        action.moveToElement(ele).perform();


        webDriver.findElement(By.xpath("//h4[contains(text(),'Shop'")).click();
        //Actions a = new Actions(webDriver);
       // a.moveToElement(webDriver.findElement(By.xpath("//h2[contains(text(),'Shop')]"));

        //webDriver.findElement(By.xpath("//span[contains(text(),'My account'")).click();//

       // webDriver.findElement(By.linkText("Login")).click();

        //webDriver.findElement(By.id("menu-item-57")).sendKeys(clicklink);//click myaccount

        //webDriver.findElement(By.xpath("//span[contains(text(),'My account'")).click();//click myaccount
        //webDriver.findElement(By.xpath("//input[@id='username']")).sendKeys("TestUser");

        webDriver.quit();
    }






}
