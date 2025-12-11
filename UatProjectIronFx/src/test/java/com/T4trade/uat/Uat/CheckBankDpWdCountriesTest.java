package com.T4trade.uat.Uat;

import com.Ironfx.uat.UatProjectIronFx.utilities.ReportManager;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import org.apache.commons.io.FileUtils;

public class CheckBankDpWdCountriesTest {
    private WebDriver driver;
    private JavascriptExecutor js;
    private Set<Cookie> crmCookies;
    private String crmHandle;
    private String t4tradeHandle;

    private String crmAccountUrl = "http://crm-staging.ironfx.local/index.php?module=Accounts&offset=1&stamp=1764837589081416300&return_module=Accounts&action=DetailView&record=8545ffed-fb7f-7fa5-ca4b-691b0af18308";
    private String baseUrlT4Trade = "https://t4trade-com.cp-uat.ironfx.local/en/client-portal";

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C://Users/smouzoulas/Desktop/nnnn/chromedriver.exe");

        // --- Incognito Mode για όλα τα tabs ---
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;

        ReportManager.initReport();

        // --- CRM Login μία φορά ---
        driver.get("http://crm-staging.ironfx.local/index.php");
        driver.findElement(By.id("user_name")).sendKeys("accountopeningqa");
        driver.findElement(By.id("login_button")).click();

        crmCookies = driver.manage().getCookies();
        crmHandle = driver.getWindowHandle();

        // --- Άνοιξε νέο tab για T4Trade (και αυτό incognito) ---
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(baseUrlT4Trade);
        t4tradeHandle = driver.getWindowHandle();

        // Αν εμφανιστεί SSL warning, κάνε bypass
        try {
            driver.findElement(By.id("details-button")).click();
            driver.findElement(By.id("proceed-link")).click();
        } catch (Exception e) {
            // ignore αν δεν υπάρχει
        }

        // --- T4Trade Login μία φορά ---
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("inlineFieldLogin")).sendKeys("nickchigg+test2@gmail.com");
        driver.findElement(By.id("inlineFieldPassword")).sendKeys("Enadyo3!");
        driver.findElement(By.xpath("//form[@id='headerLoginForm']/div[2]/div/button")).click();
    }

    @DataProvider(name = "countries")
    public Object[][] countriesProvider() {
        return new Object[][] {
            {"Greece"}, {"United Kingdom"}, {"Cyprus"}
        };
    }

    @Test(dataProvider = "countries")
    public void testDepWdCountries(String country) throws Exception {
        ReportManager.startTest("Check Bank Deposit/Withdraw for " + country);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // --- CRM Account update ---
        driver.switchTo().window(crmHandle);

        // Πρώτα πήγαινε στην αρχική CRM για να φορτωθούν cookies
        driver.get("http://crm-staging.ironfx.local/index.php");
        for (Cookie cookie : crmCookies) {
            driver.manage().addCookie(cookie);
        }

        // Τώρα πήγαινε απευθείας στο συγκεκριμένο account URL
        driver.navigate().to(crmAccountUrl);

        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit_button")));
        editBtn.click();
        new Select(driver.findElement(By.id("country_c"))).selectByVisibleText(country);
        driver.findElement(By.id("SAVE_HEADER")).click();
        js.executeScript("document.body.style.zoom='60%'");
        String crmSS = takeScreenshot(country + "_crm");
        ReportManager.logInfo("CRM updated for " + country);
        ReportManager.getTest().addScreenCaptureFromPath(crmSS);

        // --- T4Trade checks ---
        driver.switchTo().window(t4tradeHandle);

        // Deposit
        driver.findElement(By.xpath("//a[contains(text(),'Deposit')]")).click();
        String pageTextDp = driver.findElement(By.tagName("body")).getText();
        String depSS = takeScreenshot(country + "_dep");
        if (!pageTextDp.contains("Local bank transfer")) {
            ReportManager.logPass("Local bank transfer is NOT visible in Deposit page");
        } else {
            ReportManager.logFail("Local bank transfer IS visible in Deposit page (should not be)");
        }
        ReportManager.getTest().addScreenCaptureFromPath(depSS);

        // Withdraw
        driver.findElement(By.xpath("//p[contains(text(),'Withdraw')]")).click();
        String pageTextWd = driver.findElement(By.tagName("body")).getText();
        String wdSS = takeScreenshot(country + "_wd");
        if (!pageTextWd.contains("Local bank transfer")) {
            ReportManager.logPass("Local bank transfer is NOT visible in Withdraw page");
        } else {
            ReportManager.logFail("Local bank transfer IS visible in Withdraw page (should not be)");
        }
        ReportManager.getTest().addScreenCaptureFromPath(wdSS);

        // Payment Provider
        driver.get("https://t4trade-com.cp-uat.ironfx.local/client-portal/transactions/deposits/mpsa");
        String pageTextNija = driver.findElement(By.tagName("body")).getText();
        String paySS = takeScreenshot(country + "_pmpage");
        if (pageTextNija.contains("Payment Method Not Available")) {
            ReportManager.logPass("Payment Method Not Available IS visible in Payment Provider page");
        } else {
            ReportManager.logFail("Payment Method Not Available is NOT visible in Payment Provider page");
        }
        ReportManager.getTest().addScreenCaptureFromPath(paySS);

        // Logout
        driver.findElement(By.id("logout")).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        ReportManager.flushReport();
    }

    private String takeScreenshot(String name) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "C:/Users/smouzoulas/Desktop/nnnn/UatProjectIronFx/Reports/screenshots/" + name + ".png";
        File dest = new File(path);
        FileUtils.copyFile(scrFile, dest);
        return dest.getAbsolutePath();
    }
}
