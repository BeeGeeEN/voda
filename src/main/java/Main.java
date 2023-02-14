


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import java.util.concurrent.TimeUnit;
import java.util.List;


public class Main {


    public String urlPath = "https://www.vodacom.co.za/";
    public String TITLE_XPATH = "//a[@href='/']";
    WebDriver webDriver;
    public String seeMoreLink;
    public String DealDetailsPage;


    ExtentHtmlReporter htmlReporter;

    ExtentReports extent;
    //helps to generate the logs in the test report.
    ExtentTest test;


    public void screenShot(WebDriver webdriver,String fileWithPath) throws Exception{
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile=new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }

    @AfterTest
    void setupChromeDriver() {
        webDriver.quit();
        //to write or update test information to reporter
        extent.flush();
    }


    @AfterMethod
    void getResult() {

        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getTestName());
        } else {
            test.log(Status.SKIP, result.getTestName());
        }
    }

    @BeforeTest()
    public void startReport() {
        // initialize the HtmlReporter
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/testReport.html");

        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);


        //configuration items to change the look and feel
        //add content, manage tests etc
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Simple Automation Report");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    @Test
    public void pathOne() throws Exception {

        test = extent.createTest("Test Case1", "The test case 1 has passed");
        Assert.assertTrue(true);

        System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        System.out.println("----------STARTING----------");
        System.out.println("----------CHROME LOADED----------");
        webDriver.manage().window().maximize();
        System.out.println("----------WINDOW MAXIMIZED----------");
        this.screenShot(webDriver, "src/test/resources/screenshots/test_maximize_window.png");


        //PATH ONE
        System.out.println("----------OPENING SITE: " + urlPath);
        webDriver.get(urlPath);
        System.out.println("----------SITE LOADED: " + urlPath);
        this.screenShot(webDriver, "src/test/resources/screenshots/test_site_loaded.png");

        String clicklink = Keys.chord(Keys.CONTROL, Keys.ENTER);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("----------WAITING TO PAGE ON OF SITE: " + urlPath);
        System.out.println("----------title of page is: " + webDriver.getTitle());
        this.screenShot(webDriver, "src/test/resources/screenshots/test_page_loaded.png");



        //Creating object of an Actions class
        Actions actions = new Actions(webDriver);

        //Checking if Cookie prompt pops out
        List<WebElement> l = webDriver.findElements(By.xpath("//*[text()='Cookie Policy']"));
        if (l.size() == 0) {
            // empty list if no matching element
            System.out.println("----------Cookie prompt not present, appearing " + l.size() + " time");
        } else {
            this.screenShot(webDriver, "src/test/resources/screenshots/test_cookie_prompt.png");
            System.out.println("----------Cookie prompt present, appearing " + l.size() + " time");
            webDriver.findElement(By.id("onetrust-accept-btn-handler")).sendKeys(clicklink);
        }

        //Retrieve WebElement to perform mouse hover
        WebElement menuShop = webDriver.findElement(By.xpath("//h4[contains(text(),'Shop')]"));


        //Mouse hover menuOption 'Shop'
        actions.moveToElement(menuShop).perform();
        System.out.println("----------Done Mouse hover on 'Shop' from Menu");
        this.screenShot(webDriver, "src/test/resources/screenshots/test_shop_menu_hover.png");


        WebElement menuShopOED = webDriver.findElement(By.partialLinkText("Online Exclusive Deals"));
        actions.moveToElement(menuShopOED).perform();
        System.out.println("----------Done Mouse hover on 'Online Exclusive Deals' from Menu");
        this.screenShot(webDriver, "src/test/resources/screenshots/test_Exclusive deals_window.png");

        menuShopOED.click();

        //actions.click(menuShopOED).perform();
        System.out.println("----------Done Click on 'Online Exclusive Deals' from Menu");
        System.out.println("----------Title of page is: " + webDriver.getTitle());


        webDriver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

        WebElement dealSeeMore = webDriver.findElement(By.xpath("//a[@href='/shopping/deal-details-page/DV3A21DIVJ?contractSkus=DV3A21DHWD&contractSkus=DV3A21DIVJ']"));
        System.out.println("----------OPENING: " + dealSeeMore);
        this.screenShot(webDriver, "src/test/resources/screenshots/test_device_window.png");

        dealSeeMore.click();


        //webDriver.findElement(By.xpath("//a[@href='https://www.vodacom.co.za/shopping/deal-details-page/DV3A21DIVJ?contractSkus=DV3A21DHWD&contractSkus=DV3A21DIVJ']")).click();

        // webDriver.findElement(By.xpath("//a[@href ='https://www.vodacom.co.za/shopping/deal-details-page/DV3A21DIVJ?contractSkus=DV3A21DHWD&contractSkus=DV3A21DIVJ']")).click();
        // WebElement dealSeeMore =  webDriver.findElement(By.xpath("//div[@class='DealCard_image-text-content__13qd9']/span[@class='title'] and contains(text(),'Z FLIP4 256GB 5G')"));
        //  By by = By.xpath("//span[text()='Settings']");
        // actions.click(dealSeeMore).perform();
        System.out.println("----------Selected More for ----");


        List<WebElement> detailTilte = webDriver.findElements(By.xpath("//h1[contains(text(),'Deal details')]"));
        if (detailTilte.size() == 0) {
            System.out.println("---------Title not present:  FAIL");
            Assert.assertTrue(false);
        } else {
            System.out.println("---------Title present:  CONTINUE");
            Assert.assertTrue(true);

            //Samsung Galaxy Z FLIP4 256GB 5G + Samsung Tab A8 10.5 Grey


            //Get this deal on a new contract
            //36
            //R828 PM x 36

            //Get this deal | button


            //Samsung Tab A 8 10.5" Gray
            //
            //Samsung Galaxy Flip4 Graphite 256gb 5g Ds
            //

            //Contract cover has been added Remove Item


            //RED 500MB 50min + with min,undefinedMB data & SMSs (36 Months)
            //
            //1GB Data Top Up + with min,undefinedMB data & SMSs (36 Months)


            List<WebElement> deviceDetails = webDriver.findElements(By.xpath("//h1[contains(text(),'Samsung Galaxy Z FLIP4 256GB 5G + Samsung Tab A8 10.5 Grey')]"));
            if (detailTilte.size() == 0) {
                System.out.println("---------Device does not Match:  FAIL");
            } else {
                System.out.println("---------Device does not Match:  CONTINUE");

            }

            webDriver.findElement(By.id("onetrust-accept-btn-handler")).sendKeys(clicklink);
        }


    }


    @Test
    public void pathTwo() {


        test = extent.createTest("Test Case 2", "The test case 2 has failed due to incorrect deal");


        System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        System.out.println("----------STARTING----------");
        System.out.println("----------CHROME LOADED----------");
        webDriver.manage().window().maximize();
        System.out.println("----------WINDOW MAXIMIZED----------");


        //PATH ONE
        System.out.println("----------OPENING SITE: " + urlPath);
        webDriver.get(urlPath);
        System.out.println("----------SITE LOADED: " + urlPath);
        String clicklink = Keys.chord(Keys.CONTROL, Keys.ENTER);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("----------WAITING TO PAGE ON OF SITE: " + urlPath);
        System.out.println("----------title of page is: " + webDriver.getTitle());


        //Creating object of an Actions class
        Actions actions = new Actions(webDriver);

        //Checking if Cookie prompt pops out
        List<WebElement> l = webDriver.findElements(By.xpath("//*[text()='Cookie Policy']"));
        if (l.size() == 0) {
            // empty list if no matching element
            System.out.println("----------Cookie prompt not present, appearing " + l.size() + " time");
        } else {
            System.out.println("----------Cookie prompt present, appearing " + l.size() + " time");
            webDriver.findElement(By.id("onetrust-accept-btn-handler")).sendKeys(clicklink);
        }

        //Retrieve WebElement to perform mouse hover
        WebElement menuShop = webDriver.findElement(By.xpath("//h4[contains(text(),'Shop')]"));


        //Mouse hover menuOption 'Shop'
        actions.moveToElement(menuShop).perform();
        System.out.println("----------Done Mouse hover on 'Shop' from Menu");

        WebElement menuShopOED = webDriver.findElement(By.partialLinkText("Online Exclusive Deals"));
        actions.moveToElement(menuShopOED).perform();
        System.out.println("----------Done Mouse hover on 'Online Exclusive Deals' from Menu");
        menuShopOED.click();

        //actions.click(menuShopOED).perform();
        System.out.println("----------Done Click on 'Online Exclusive Deals' from Menu");
        System.out.println("----------Title of page is: " + webDriver.getTitle());


        webDriver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

        WebElement dealSeeMore = webDriver.findElement(By.xpath("//a[@href='/shopping/deal-details-page/DV3A21DIVJ?contractSkus=DV3A21DHWD&contractSkus=DV3A21DIVJ']"));
        System.out.println("----------OPENING: " + dealSeeMore);
        dealSeeMore.click();


        //webDriver.findElement(By.xpath("//a[@href='https://www.vodacom.co.za/shopping/deal-details-page/DV3A21DIVJ?contractSkus=DV3A21DHWD&contractSkus=DV3A21DIVJ']")).click();

        // webDriver.findElement(By.xpath("//a[@href ='https://www.vodacom.co.za/shopping/deal-details-page/DV3A21DIVJ?contractSkus=DV3A21DHWD&contractSkus=DV3A21DIVJ']")).click();
        // WebElement dealSeeMore =  webDriver.findElement(By.xpath("//div[@class='DealCard_image-text-content__13qd9']/span[@class='title'] and contains(text(),'Z FLIP4 256GB 5G')"));
        //  By by = By.xpath("//span[text()='Settings']");
        // actions.click(dealSeeMore).perform();
        System.out.println("----------Selected More for ----");


        List<WebElement> detailTilte = webDriver.findElements(By.xpath("//h1[contains(text(),'Deal details')]"));
        if (detailTilte.size() == 0) {
            System.out.println("---------Title not present:  FAIL");
        } else {
            System.out.println("---------Title present:  CONTINUE");

            //Samsung Galaxy Z FLIP4 256GB 5G + Samsung Tab A8 10.5 Grey


            //Get this deal on a new contract
            //36
            //R828 PM x 36

            //Get this deal | button


            //Samsung Tab A 8 10.5" Gray
            //
            //Samsung Galaxy Flip4 Graphite 256gb 5g Ds
            //

            //Contract cover has been added Remove Item


            //RED 500MB 50min + with min,undefinedMB data & SMSs (36 Months)
            //
            //1GB Data Top Up + with min,undefinedMB data & SMSs (36 Months)


            List<WebElement> deviceDetails = webDriver.findElements(By.xpath("//h1[contains(text(),'Samsung Galaxy Z FLIP4 256GB 5G + Samsung Tab A8 10.5 Grey')]"));
            if (detailTilte.size() == 0) {
                System.out.println("---------Device does not Match:  FAIL");
                Assert.assertTrue(false);
            } else {
                System.out.println("---------Device does not Match:  CONTINUE");
                Assert.assertTrue(true);

            }

            webDriver.findElement(By.id("onetrust-accept-btn-handler")).sendKeys(clicklink);

            //PATH TWO


            //incorrect Deal Price
        }


    }

}
