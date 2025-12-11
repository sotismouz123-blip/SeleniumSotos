/**package com.Ironfx.uat.UatProjectIronFx.tests;



import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.Ironfx.uat.UatProjectIronFx.utilities.EmailReportSender;
import com.Ironfx.uat.UatProjectIronFx.utilities.CodePoints;
import com.Ironfx.uat.UatProjectIronFx.utilities.ScreenshotUtils;
import com.Ironfx.uat.UatProjectIronFx.utilities.ReportManager;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.text.Normalizer;

import com.Ironfx.uat.UatProjectIronFx.pages.DashboardPage;
import com.Ironfx.uat.UatProjectIronFx.pages.LoginPage;
import com.Ironfx.uat.UatProjectIronFx.utilities.WaitUtils;

public class UpdateProfilePageTest {

    private WebDriver driver;
    private DashboardPage dashboardPage;
    private LoginPage loginPage;

    private static final String password = "Password1!";
    private static final String expectedEmail = "nickchigg+CroatiaElCCnzhI@gmail.com";

    private String normalizeText(String text) {
        return Normalizer.normalize(text.trim(), Normalizer.Form.NFKC);
    }

    private String toCodePoints(String text) {
        return text.codePoints()
                   .mapToObj(cp -> String.format("U+%04X", cp))
                   .collect(Collectors.joining(" "));
    }

    @BeforeClass
    public void setUp() {
        // Init report
        ReportManager.initReport();
        ReportManager.startTest("Verify Correct Languages Test");

        System.setProperty("webdriver.chrome.driver", "C://Users/smouzoulas/Desktop/nnnn/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);

        driver.manage().window().maximize();
        driver.get("https://ironfx-com.cp-uat.ironfx.local/en/client-portal");

        // bypass SSL warning
        driver.findElement(By.id("details-button")).click();
        driver.findElement(By.id("proceed-link")).click();

        // zoom out for visibility
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='60%'");

        String ssOpen = ScreenshotUtils.takeScreenshot(driver, "LoginPage_Open");
        ReportManager.getTest().info("Opened Client Portal and navigated to login page")
                     .addScreenCaptureFromPath(ssOpen);
    }

    @Test
    public void verifyDropdownOptions() {
        ReportManager.getTest().info("Starting language dropdown verification");

        loginPage.loginProcedure(expectedEmail, password);
        WaitUtils.waitForSeconds(5);
     
        dashboardPage.clickMyProfileBtn();
        dashboardPage.clickVerifyProfileBtn();
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        js.executeScript("document.body.style.zoom='60%'");
        
        

        
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

}
**/