package com.Ironfx.uat.UatProjectIronFx.tests;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.Ironfx.uat.UatProjectIronFx.pages.DashboardPage;
import com.Ironfx.uat.UatProjectIronFx.pages.LoginPage;
import com.Ironfx.uat.UatProjectIronFx.utilities.WaitUtils;

public class VerifyCorrectLangTest {

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
        //ReportManager.initReport();
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
        dashboardPage.clickLangBtn();
        WaitUtils.waitForSeconds(5);

        String ssLang = ScreenshotUtils.takeScreenshot(driver, "Lang_Verification");
        ReportManager.getTest().info("Language dropdown opened and screenshot taken")
                     .addScreenCaptureFromPath(ssLang);

        List<String> expectedLangs = List.of(
            "\u0627\u0644\u0639\u0631\u0628\u064A\u0629", // العربية
            "Deutsch",
            "English",
            "Español",
            "\u067E\u0627\u0631\u0633\u06CC", // فارسی
            "Français",
            "Magyar Nyelv",
            "Dutch",
            "Italiano",
            "\u65E5\u672C\u8A9E", // 日本語
            "\uD55C\uAD6D\uC5B4", // 한국어
            "Bahasa Melayu",
            "Português",
            "\u0420\u0443\u0441\u0441\u043A\u0438\u0439", // Русский
            "Tiếng Việt",
            "\u4E2D\u6587" // 中文
        );

        WebElement dropdownLang = driver.findElement(By.cssSelector("div.dropdown-menu.dropdown-menu-right"));
        List<String> actualOptions = dropdownLang.findElements(By.cssSelector("a.dropdown-item"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        // Debug print με codepoints
        actualOptions.forEach(opt -> {
            System.out.println("Actual option: [" + opt + "] | Codepoints: " + CodePoints.toCodePoints(opt));
        });

        // Έλεγχος με βάση codepoints
        List<String> missingLangs = CodePoints.missingByCodePoints(actualOptions, expectedLangs);

        if (missingLangs.isEmpty()) {
            ReportManager.getTest().pass("All expected languages are present in the dropdown");
        } else {
            String ssFail = ScreenshotUtils.takeScreenshot(driver, "Missing_Languages");
            ReportManager.getTest().fail("Missing languages: " + missingLangs)
                         .addScreenCaptureFromPath(ssFail);
        }

        assertTrue(missingLangs.isEmpty(),
            "❌ Missing languages: " + missingLangs);
    }

    @AfterClass
    public void tearDown() {
        ReportManager.flushReport();
        if (driver != null) {
            driver.quit();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
 	    String timestamp = LocalDateTime.now().format(formatter);
 	String reportPath = "C:\\Users\\smouzoulas\\Desktop\\nnnn\\UatProjectIronFx\\Reports\\TestReport"+timestamp+".html";

        // Ορίζεις το email παραλήπτη
       // String recipientEmail = "nickchigg@gmail.com";

        // Στέλνεις το report με email
        //EmailReportSender.sendReport(recipientEmail, reportPath);
    }
}
