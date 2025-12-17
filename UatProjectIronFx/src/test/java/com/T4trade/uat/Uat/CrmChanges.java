package com.T4trade.uat.Uat;


import org.testng.annotations.*;

import com.Ironfx.uat.UatProjectIronFx.utilities.ReportManager;


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

public class CrmChanges {
    private WebDriver driver;
    private JavascriptExecutor js;
    private Set<Cookie> crmCookies;
    private String crmHandle;
    private String t4tradeHandle;

    private String crmAccountUrl = "https://crm.ironfx.com/index.php?module=Accounts&offset=1&stamp=1765953258015905800&return_module=Accounts&action=DetailView&record=193fa461-857c-e4db-cc27-691c25f5ae4b";
    

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C://Users/smouzoulas/Desktop/nnnn/chromedriver.exe");
        
        // --- Incognito Mode για όλα τα tabs ---
        ChromeOptions options = new ChromeOptions();
       

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;

       
        // --- CRM Login μία φορά ---
        driver.get("https://crm.ironfx.com/index.php?module=Accounts&offset=1&stamp=1765953258015905800&return_module=Accounts&action=DetailView&record=193fa461-857c-e4db-cc27-691c25f5ae4b");
      //  driver.findElement(By.id("user_name")).sendKeys("accountopeningqa");
      //  driver.findElement(By.id("password")).sendKeys("accountopeningqa");
       // driver.findElement(By.id("login_button")).click();

        crmCookies = driver.manage().getCookies();
        crmHandle = driver.getWindowHandle();

       

        // Αν εμφανιστεί SSL warning, κάνε bypass
        try {
            driver.findElement(By.id("details-button")).click();
            driver.findElement(By.id("proceed-link")).click();
        } catch (Exception e) {
            // ignore αν δεν υπάρχει
        }}

       

    @DataProvider(name = "countries")
    public Object[][] countriesProvider() {
        return new Object[][] {
            {"Greece"}, {"Cyprus"}
        };
    }

    @Test(dataProvider = "countries")
    public void testDepWdCountries(String country) throws Exception {
    	
        ReportManager.startTest("The country that we are checking is  " + country);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // --- CRM Account update ---
        driver.switchTo().window(crmHandle);

        // Πρώτα πήγαινε στην αρχική CRM για να φορτωθούν cookies
        driver.get("https://crm.ironfx.com/index.php?module=Accounts&offset=1&stamp=1765953258015905800&return_module=Accounts&action=DetailView&record=193fa461-857c-e4db-cc27-691c25f5ae4b");
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
