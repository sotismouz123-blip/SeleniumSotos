package com.Ironfx.uat.UatProjectIronFx.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.Ironfx.uat.UatProjectIronFx.utilities.EmailReportSender;
import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.Ironfx.uat.UatProjectIronFx.utilities.TestData;
import com.Ironfx.uat.UatProjectIronFx.utilities.ScreenshotUtils;
import com.Ironfx.uat.UatProjectIronFx.utilities.ReportManager;
import com.Ironfx.uat.UatProjectIronFx.pages.RegistrationPage;
import com.Ironfx.uat.UatProjectIronFx.pages.DashboardPage; 
import com.Ironfx.uat.UatProjectIronFx.utilities.WaitUtils;

public class RegistrationPageTest {

    private WebDriver driver;
    private Faker faker;
    private RegistrationPage registrationPage;
    private DashboardPage dashboardPage;

    private static final String password = "Password1!";

    @BeforeClass
    public void setUp() {
        // Init report
        ReportManager.initReport();
        ReportManager.startTest("Registration Page Test");

        System.setProperty("webdriver.chrome.driver", "C://Users/smouzoulas/Desktop/nnnn/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://ironfx-com.cp-uat.ironfx.local/en/register");
        registrationPage = new RegistrationPage(driver);
        dashboardPage = new DashboardPage(driver);
        faker = new Faker();

        driver.findElement(By.id("details-button")).click();
        driver.findElement(By.id("proceed-link")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='60%'");

        String ssOpen = ScreenshotUtils.takeScreenshot(driver, "Registration_page_Open");
        ReportManager.getTest().info("Opened Registration Page")
                     .addScreenCaptureFromPath(ssOpen);
    }

    @Test
    public void testSuccessfulRegistration() {
        String Name = faker.regexify("Test"+"[a-zA-Z]{8}");
        String country = "Vietnam";
        String expectedEmail = "nickchigg+" + country + Name + "@gmail.com";
        TestData.generatedEmail = expectedEmail;
        
        registrationPage.enterFirstName(Name + country);
        registrationPage.enterLastName(Name + country);
        registrationPage.enterEmail(expectedEmail);
        registrationPage.enterCountry(country);
        registrationPage.enterPhone("12341234");
        registrationPage.selectAccountType("Standard Floating");
        registrationPage.selectBonusScheme("Not applicable");
        registrationPage.selectCurrency("USD");
        registrationPage.selectLeverage("1:500");
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);

        String ssForm = ScreenshotUtils.takeScreenshot(driver, "Registration_form_populated");
        ReportManager.getTest().info("Registration form populated")
                     .addScreenCaptureFromPath(ssForm);


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        registrationPage.clickSubmit();
        ReportManager.logInfo("Clicked Submit");

        dashboardPage.clickMyProfileBtn();
        dashboardPage.clickVerifyProfileBtn();
        js.executeScript("document.body.style.zoom='60%'");

        String ssVerify = ScreenshotUtils.takeScreenshot(driver, "Email_Verification");
        ReportManager.getTest().info("Navigated to Email Verification")
                     .addScreenCaptureFromPath(ssVerify);


        WaitUtils.waitForSeconds(5);

        String actualEmail = driver.findElement(By.id("email")).getAttribute("value");
        System.out.println("🔹 Actual Email : " + actualEmail);

        try {
            assertEquals(actualEmail, expectedEmail, "The Emails are not the same. Failed Registration.");
            ReportManager.getTest().pass("Registration successful. Email verified correctly");
                         
        } catch (AssertionError e) {
            String ssFail = ScreenshotUtils.takeScreenshot(driver, "Registration_Failed");
            ReportManager.getTest().fail("Registration failed. Expected email: " + expectedEmail + " but found: " + actualEmail)
                         .addScreenCaptureFromPath(ssFail);
            throw e;
    }}

    @AfterClass
    public void tearDown() {
        //ReportManager.flushReport();
       if (driver != null) {
           driver.quit();
       }
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
	    String timestamp = LocalDateTime.now().format(formatter);
	String reportPath = "C:\\Users\\smouzoulas\\Desktop\\nnnn\\UatProjectIronFx\\Reports\\TestReport"+timestamp+".html";

        // Ορίζεις το email παραλήπτη
       // String recipientEmail = "nickchigg@gmail.com";

        // Στέλνεις το report με email
       // EmailReportSender.sendReport(recipientEmail, reportPath);
    }
}
