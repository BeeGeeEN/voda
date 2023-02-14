



import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.List;


public class Main {


    public String urlPath = "https://www.vodacom.co.za/";
    public String TITLE_XPATH = "//a[@href='/']";
    WebDriver webDriver;
    public String seeMoreLink;
    public String DealDetailsPage;



    @AfterTest
    void setupChromeDriver() {
        webDriver.quit();
    }

    @Test
    public void site_header_is_on_home_page() {

        System.setProperty("webdriver.chrome.driver","src/driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        System.out.println("----------STARTING----------");
        System.out.println("----------CHROME LOADED----------");
        webDriver.manage().window().maximize();
        System.out.println("----------WINDOW MAXIMIZED----------");


        //PATH ONE
        System.out.println("----------OPENING SITE: "+urlPath);
        webDriver.get(urlPath);
        System.out.println("----------SITE LOADED: "+urlPath);
        String clicklink= Keys.chord(Keys.CONTROL,Keys.ENTER);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("----------WAITING TO PAGE ON OF SITE: "+urlPath);
        System.out.println("----------title of page is: " + webDriver.getTitle());



        //Creating object of an Actions class
        Actions actions = new Actions(webDriver);

        //Checking if Cookie prompt pops out
        List<WebElement> l=webDriver.findElements(By.xpath("//*[text()='Cookie Policy']"));
        if(l.size()== 0){
            // empty list if no matching element
            System.out.println("----------Cookie prompt not present, appearing "+l.size()+ " time");
        } else {
            System.out.println("----------Cookie prompt present, appearing "+l.size()+ " time");
            webDriver.findElement(By.id("onetrust-accept-btn-handler")).sendKeys(clicklink);
        }

        //Retrieve WebElement to perform mouse hover
        WebElement menuShop = webDriver.findElement(By.xpath("//h4[contains(text(),'Shop')]"));


        //Mouse hover menuOption 'Music'
        actions.moveToElement(menuShop).perform();
        System.out.println("----------Done Mouse hover on 'Shop' from Menu");

        WebElement menuShopOED = webDriver.findElement(By.partialLinkText("Online Exclusive Deals"));
        actions.moveToElement(menuShopOED).perform();
        System.out.println("----------Done Mouse hover on 'Online Exclusive Deals' from Menu");
        menuShopOED.click();

        //actions.click(menuShopOED).perform();
        System.out.println("----------Done Click on 'Online Exclusive Deals' from Menu");
        System.out.println("----------Title of page is: " + webDriver.getTitle());



        //DealCard_image-text-content__13qd9
        //DealGridListContainer_dealcard-link__3WnSM
       // WebElement dealSeeMore = webDriver.findElement(By.xpath("//span[text()='Samsung Galaxy Z FLIP4 256GB 5G + Samsung…']"));
        webDriver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

        WebElement dealSeeMore =  webDriver.findElement(By.xpath("//a[@href='/shopping/deal-details-page/DV3A21DIVJ?contractSkus=DV3A21DHWD&contractSkus=DV3A21DIVJ']"));
        System.out.println("----------OPENING: "+dealSeeMore);
        dealSeeMore.click();


        //webDriver.findElement(By.xpath("//a[@href='https://www.vodacom.co.za/shopping/deal-details-page/DV3A21DIVJ?contractSkus=DV3A21DHWD&contractSkus=DV3A21DIVJ']")).click();

       // webDriver.findElement(By.xpath("//a[@href ='https://www.vodacom.co.za/shopping/deal-details-page/DV3A21DIVJ?contractSkus=DV3A21DHWD&contractSkus=DV3A21DIVJ']")).click();
       // WebElement dealSeeMore =  webDriver.findElement(By.xpath("//div[@class='DealCard_image-text-content__13qd9']/span[@class='title'] and contains(text(),'Z FLIP4 256GB 5G')"));
      //  By by = By.xpath("//span[text()='Settings']");
       // actions.click(dealSeeMore).perform();
        System.out.println("----------Selected More for ----");


        List<WebElement> detailTilte=webDriver.findElements(By.xpath("//h1[contains(text(),'Deal details')]"));
        if(detailTilte.size()== 0){
            System.out.println("---------Title not present:  FAIL");
        } else {
            System.out.println("---------Title present:  FAIL");
            webDriver.findElement(By.id("onetrust-accept-btn-handler")).sendKeys(clicklink);
        }



        //Samsung Galaxy Z FLIP4 256GB 5G + Samsung…



        //Performing the mouse hover action on the target element.
       // action.moveToElement(ele).perform();


        //Actions a = new Actions(webDriver);
       // a.moveToElement(webDriver.findElement(By.xpath("//h2[contains(text(),'Shop')]"));

        //webDriver.findElement(By.xpath("//span[contains(text(),'My account'")).click();//

       // webDriver.findElement(By.linkText("Login")).click();

        //webDriver.findElement(By.id("menu-item-57")).sendKeys(clicklink);//click myaccount

        //webDriver.findElement(By.xpath("//span[contains(text(),'My account'")).click();//click myaccount
        //webDriver.findElement(By.xpath("//input[@id='username']")).sendKeys("TestUser");


        //PATH TWO


    }






}
