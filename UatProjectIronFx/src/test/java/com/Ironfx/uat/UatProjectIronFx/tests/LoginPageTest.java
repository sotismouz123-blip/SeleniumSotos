package com.Ironfx.uat.UatProjectIronFx.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import com.Ironfx.uat.UatProjectIronFx.utilities.EmailReportSender;
import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.Ironfx.uat.UatProjectIronFx.utilities.ScreenshotUtils;
import com.Ironfx.uat.UatProjectIronFx.utilities.TestData;
import com.Ironfx.uat.UatProjectIronFx.utilities.ReportManager;
import com.Ironfx.uat.UatProjectIronFx.pages.RegistrationPage;
import com.Ironfx.uat.UatProjectIronFx.pages.DashboardPage;
import com.Ironfx.uat.UatProjectIronFx.utilities.WaitUtils;
import com.Ironfx.uat.UatProjectIronFx.pages.LoginPage;

public class LoginPageTest {

    private WebDriver driver;
    private Faker faker;
    private RegistrationPage registrationPage;
    private DashboardPage dashboardPage;
    private LoginPage loginPage;

    String password = "Password1!";
    String expectedEmail = "nickchigg+CroatiaVkuKCiSQ@gmail.com";

    @BeforeClass
    public void setUp() {
        // Init report
        ReportManager.initReport();
        ReportManager.startTest("Login Page Test");

        System.setProperty("webdriver.chrome.driver", "C://Users/smouzoulas/Desktop/nnnn/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();

        driver.get("https://ironfx-com.cp-uat.ironfx.local/en/client-portal");
        registrationPage = new RegistrationPage(driver);
        dashboardPage = new DashboardPage(driver);

        driver.findElement(By.id("details-button")).click();
        driver.findElement(By.id("proceed-link")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='60%'");

        String ssOpen = ScreenshotUtils.takeScreenshot(driver, "LoginPage_Open");
        ReportManager.getTest().info("Opened Client Portal Login Page")
                     .addScreenCaptureFromPath(ssOpen);
    }

    @Test
    public void testSuccessfulLogin() {
        ReportManager.getTest().info("Starting login procedure");

        loginPage.enterEmail(TestData.generatedEmail);
        loginPage.enterPassword(password);
        String ssLogin = ScreenshotUtils.takeScreenshot(driver, "Login_Form_Submitted");
        ReportManager.getTest().info("Login form submitted")
                     .addScreenCaptureFromPath(ssLogin);

        WaitUtils.waitForSeconds(5);
        String ssDashboard = ScreenshotUtils.takeScreenshot(driver, "Dashboard_Page_Open");
        ReportManager.getTest().info("Dashboard page opened")
                     .addScreenCaptureFromPath(ssDashboard);

        dashboardPage.clickMyProfileBtn();
        dashboardPage.clickVerifyProfileBtn();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='60%'");

        WaitUtils.waitForSeconds(5);
        String ssVerify = ScreenshotUtils.takeScreenshot(driver, "Email_Verification");

        String actualEmail = driver.findElement(By.id("email")).getAttribute("value");
        System.out.println("🔹 Actual Email : " + actualEmail);

        try {
            assertEquals(actualEmail, expectedEmail, "The Emails are not the same. Failed Login.");
            ReportManager.getTest().pass("Login successful. Email verified correctly.")
                         .addScreenCaptureFromPath(ssVerify);
        } catch (AssertionError e) {
            String ssFail = ScreenshotUtils.takeScreenshot(driver, "Login_Failed");
            ReportManager.getTest().fail("Login failed. Expected email: " + expectedEmail + " but found: " + actualEmail)
                         .addScreenCaptureFromPath(ssFail);
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        ReportManager.flushReport();
        if (driver != null) {
            driver.quit();
        }
        String reportPath = "C:\\Users\\smouzoulas\\Desktop\\nnnn\\UatProjectIronFx\\Reports\\TestReport.html";

        // Ορίζεις το email παραλήπτη
        String recipientEmail = "nickchigg@gmail.com";

        // Στέλνεις το report με email
        EmailReportSender.sendReport(recipientEmail, reportPath);
    }
}
