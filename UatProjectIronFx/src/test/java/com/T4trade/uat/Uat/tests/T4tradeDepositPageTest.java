package com.T4trade.uat.Uat.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Ironfx.uat.UatProjectIronFx.utilities.ScreenshotUtils;
import com.Ironfx.uat.UatProjectIronFx.utilities.ReportManager;
import com.Ironfx.uat.UatProjectIronFx.pages.*;
import com.Ironfx.uat.UatProjectIronFx.utilities.WaitUtils;
import com.T4trade.UAT.pages.DepositPageT4trade;

import java.time.Duration;

public class T4tradeDepositPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private DepositPageT4trade depositPage;

    String password = "Password1!";

    @BeforeClass
    public void setUp() {

        // MUST BE FIRST
        ReportManager.initReport();
        ReportManager.startTest("Login Page Test");

        System.setProperty("webdriver.chrome.driver", "C://Users/smouzoulas/Desktop/nnnn/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);

        driver.get("https://t4trade-com.cp-uat.ironfx.local/en/client-portal");

        driver.findElement(By.id("details-button")).click();
        driver.findElement(By.id("proceed-link")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='60%'");

       // String ssOpen = ScreenshotUtils.takeScreenshot(driver, "LoginPage_Open");
       // ReportManager.getTest().info("Opened Client Portal Login Page")
         //       .addScreenCaptureFromPath(ssOpen);
    }

    @Test
    public void testLoginForMultipleUsers() {

        Map<String, List<String>> countryCurrenciesSummary = new LinkedHashMap<>();

        String[] testEmails =  {

        		 "nickchigg+Afghanistan@gmail.com",
        		        		    "nickchigg+Albania@gmail.com",
        		        		    "nickchigg+Algeria@gmail.com",
        		        		    "nickchigg+Andorra@gmail.com",
        		        		    "nickchigg+Angola@gmail.com",
        		        		    "nickchigg+Anguilla@gmail.com",
        		        		    "nickchigg+Antarctica@gmail.com",
        		        		    "nickchigg+AntiguaBarbuda@gmail.com",
        		        		    "nickchigg+Argentina@gmail.com",
        		        		    "nickchigg+Armenia@gmail.com",
        		        		    "nickchigg+Aruba@gmail.com",
        		        		    "nickchigg+Australia@gmail.com",
        		        		    "nickchigg+Austria@gmail.com",
        		        		    "nickchigg+Azerbaijan@gmail.com",
        		        		    "nickchigg+Bahamas@gmail.com",
        		        		    "nickchigg+Bahrain@gmail.com",
        		        		    "nickchigg+Bangladesh@gmail.com",
        		        		    "nickchigg+Barbados@gmail.com",
        		        		    "nickchigg+Belgium@gmail.com",
        		        		    "nickchigg+Belize@gmail.com",
        		        		    "nickchigg+Benin@gmail.com",
        		        		    "nickchigg+Bhutan@gmail.com",
        		        		    "nickchigg+Bolivia@gmail.com",
        		        		    "nickchigg+BosniaandHerzegovina@gmail.com",
        		        		    "nickchigg+Botswana@gmail.com",
        		        		    "nickchigg+BouvetIsland@gmail.com",
        		        		    "nickchigg+Brazil@gmail.com",
        		        		    "nickchigg+BritishIndianOceanTerritory@gmail.com",
        		        		    "nickchigg+BritishVirginIslands@gmail.com",
        		        		    "nickchigg+Brunei@gmail.com",
        		        		    "nickchigg+Bulgaria@gmail.com",
        		        		    "nickchigg+Burundi@gmail.com",
        		        		    "nickchigg+Cambodia@gmail.com",
        		        		    "nickchigg+Cameroon@gmail.com",
        		        		    "nickchigg+Canada@gmail.com",
        		        		    "nickchigg+CapeVerde@gmail.com",
        		        		    "nickchigg+CaymanIslands@gmail.com",
        		        		    "nickchigg+CentralAfricanRepublic@gmail.com",
        		        		    "nickchigg+Chad@gmail.com",
        		        		    "nickchigg+Chile@gmail.com",
        		        		    "nickchigg+China@gmail.com",
        		        		    "nickchigg+ChristmasIsland@gmail.com",
        		        		    "nickchigg+CocosIslands@gmail.com",
        		        		    "nickchigg+Colombia@gmail.com",
        		        		    "nickchigg+Comoros@gmail.com",
        		        		    "nickchigg+CookIslands@gmail.com",
        		        		    "nickchigg+CostaRica@gmail.com",
        		        		    "nickchigg+Coted'Ivoire@gmail.com",
        		        		    "nickchigg+Croatia@gmail.com",
        		        		    "nickchigg+Curacao@gmail.com",
        		        		    "nickchigg+CzechRepublic@gmail.com",
        		        		    "nickchigg+Denmark@gmail.com",
        		        		    "nickchigg+Djibouti@gmail.com",
        		        		    "nickchigg+Dominica@gmail.com",
        		        		    "nickchigg+DominicanRepublic@gmail.com",
        		        		    "nickchigg+Ecuador@gmail.com",
        		        		    "nickchigg+Egypt@gmail.com",
        		        		    "nickchigg+ElSalvador@gmail.com",
        		        		    "nickchigg+EquatorialGuinea@gmail.com",
        		        		    "nickchigg+Eritrea@gmail.com",
        		        		    "nickchigg+Estonia@gmail.com",
        		        		    "nickchigg+Eswatini@gmail.com",
        		        		    "nickchigg+Ethiopia@gmail.com",
        		        		    "nickchigg+FalklandIslands@gmail.com",
        		        		    "nickchigg+FaroeIslands@gmail.com",
        		        		    "nickchigg+Fiji@gmail.com",
        		        		    "nickchigg+FrenchGuiana@gmail.com",
        		        		    "nickchigg+FrenchPolynesia@gmail.com",
        		        		    "nickchigg+FrenchSouthernTerritories@gmail.com",
        		        		    "nickchigg+Gabon@gmail.com",
        		        		    "nickchigg+Gambia@gmail.com",
        		        		    "nickchigg+Georgia@gmail.com",
        		        		    "nickchigg+Germany@gmail.com",
        		        		    "nickchigg+Ghana@gmail.com",
        		        		    "nickchigg+Gibraltar@gmail.com",
        		        		    "nickchigg+Greece@gmail.com",
        		        		    "nickchigg+Greenland@gmail.com",
        		        		    "nickchigg+Grenada@gmail.com",
        		        		    "nickchigg+Guadeloupe@gmail.com",
        		        		    "nickchigg+Guatemala@gmail.com",
        		        		    "nickchigg+Guinea@gmail.com",
        		        		    "nickchigg+Guinea-Bissau@gmail.com",
        		        		    "nickchigg+Guyana@gmail.com",
        		        		    "nickchigg+HeardIsland@gmail.com",
        		        		    "nickchigg+Honduras@gmail.com",
        		        		    "nickchigg+HongKong@gmail.com",
        		        		    "nickchigg+Hungary@gmail.com",
        		        		    "nickchigg+Iceland@gmail.com",
        		        		    "nickchigg+India@gmail.com",
        		        		    "nickchigg+Indonesia@gmail.com",
        		        		    "nickchigg+Ireland@gmail.com",
        		        		    "nickchigg+Israel@gmail.com",
        		        		    "nickchigg+Italy@gmail.com",
        		        		    "nickchigg+Jamaica@gmail.com",
        		        		    "nickchigg+Jersey@gmail.com",
        		        		    "nickchigg+Jordan@gmail.com",
        		        		    "nickchigg+Kazakhstan@gmail.com",
        		        		    "nickchigg+Kenya@gmail.com",
        		        		    "nickchigg+Kiribati@gmail.com",
        		        		    "nickchigg+Kuwait@gmail.com",
        		        		    "nickchigg+Kyrgyzstan@gmail.com",
        		        		    "nickchigg+Laos@gmail.com",
        		        		    "nickchigg+Latvia@gmail.com",
        		        		    "nickchigg+Lebanon@gmail.com",
        		        		    "nickchigg+Lesotho@gmail.com",
        		        		    "nickchigg+Liberia@gmail.com",
        		        		    "nickchigg+Libya@gmail.com",
        		        		    "nickchigg+Liechtenstein@gmail.com",
        		        		    "nickchigg+Lithuania@gmail.com",
        		        		    "nickchigg+Luxembourg@gmail.com",
        		        		    "nickchigg+Macao@gmail.com",
        		        		    "nickchigg+Madagascar@gmail.com",
        		        		    "nickchigg+Malawi@gmail.com",
        		        		    "nickchigg+Malaysia@gmail.com",
        		        		    "nickchigg+Maldives@gmail.com",
        		        		    "nickchigg+Mali@gmail.com",
        		        		    "nickchigg+Malta@gmail.com",
        		        		    "nickchigg+Martinique@gmail.com",
        		        		    "nickchigg+Mauritania@gmail.com",
        		        		    "nickchigg+Mauritius@gmail.com",
        		        		    "nickchigg+Mayotte@gmail.com",
        		        		    "nickchigg+Mexico@gmail.com",
        		        		    "nickchigg+Micronesia@gmail.com",
        		        		    "nickchigg+Moldova@gmail.com",
        		        		    "nickchigg+Monaco@gmail.com",
        		        		    "nickchigg+Mongolia@gmail.com",
        		        		    "nickchigg+Montenegro@gmail.com",
        		        		    "nickchigg+Montserrat@gmail.com",
        		        		    "nickchigg+Morocco@gmail.com",
        		        		    "nickchigg+Mozambique@gmail.com",
        		        		    "nickchigg+Namibia@gmail.com",
        		        		    "nickchigg+Nauru@gmail.com",
        		        		    "nickchigg+Nepal@gmail.com",
        		        		    "nickchigg+Netherlands@gmail.com",
        		        		    "nickchigg+NewCaledonia@gmail.com",
        		        		    "nickchigg+NewZealand@gmail.com",
        		        		    "nickchigg+Nicaragua@gmail.com",
        		        		    "nickchigg+Niger@gmail.com",
        		        		    "nickchigg+Nigeria@gmail.com",
        		        		    "nickchigg+Niue@gmail.com",
        		        		    "nickchigg+NorfolkIsland@gmail.com",
        		        		    "nickchigg+NorthMacedonia@gmail.com",
        		        		    "nickchigg+Norway@gmail.com",
        		        		    "nickchigg+Oman@gmail.com",
        		        		    "nickchigg+Pakistan@gmail.com",
        		        		    "nickchigg+Palau@gmail.com",
        		        		    "nickchigg+Palestine@gmail.com",
        		        		    "nickchigg+Panama@gmail.com",
        		        		    "nickchigg+PapuaNewGuinea@gmail.com",
        		        		    "nickchigg+Paraguay@gmail.com",
        		        		    "nickchigg+Peru@gmail.com",
        		        		    "nickchigg+Philippines@gmail.com",
        		        		    "nickchigg+Pitcairn@gmail.com",
        		        		    "nickchigg+Poland@gmail.com",
        		        		    "nickchigg+Qatar@gmail.com",
        		        		    "nickchigg+Reunion@gmail.com",
        		        		    "nickchigg+Romania@gmail.com",
        		        		    "nickchigg+Rwanda@gmail.com",
        		        		    "nickchigg+SaintHelena@gmail.com",
        		        		    "nickchigg+SaintKittsandNevis@gmail.com",
        		        		    "nickchigg+SaintLucia@gmail.com",
        		        		    "nickchigg+SaintPierreandMiquelon@gmail.com",
        		        		    "nickchigg+SaintVincentandtheGrenadines@gmail.com",
        		        		    "nickchigg+SanMarino@gmail.com",
        		        		    "nickchigg+SaoTomePrincipe@gmail.com",
        		        		    "nickchigg+SaudiArabia@gmail.com",
        		        		    "nickchigg+Serbia@gmail.com",
        		        		    "nickchigg+Seychelles@gmail.com",
        		        		    "nickchigg+SierraLeone@gmail.com",
        		        		    "nickchigg+Singapore@gmail.com",
        		        		    "nickchigg+SintMaarten@gmail.com",
        		        		    "nickchigg+Slovakia@gmail.com",
        		        		    "nickchigg+Slovenia@gmail.com",
        		        		    "nickchigg+SolomonIslands@gmail.com",
        		        		    "nickchigg+Somalia@gmail.com",
        		        		    "nickchigg+SouthGeorgia@gmail.com",
        		        		    "nickchigg+SouthKorea@gmail.com",
        		        		    "nickchigg+Spain@gmail.com",
        		        		    "nickchigg+SriLanka@gmail.com",
        		        		    "nickchigg+St.Martin@gmail.com",
        		        		    "nickchigg+Suriname@gmail.com",
        		        		    "nickchigg+SvalbardJanMayen@gmail.com",
        		        		    "nickchigg+Sweden@gmail.com",
        		        		    "nickchigg+Switzerland@gmail.com",
        		        		    "nickchigg+Taiwan@gmail.com",
        		        		    "nickchigg+Tajikistan@gmail.com",
        		        		    "nickchigg+Tanzania@gmail.com",
        		        		    "nickchigg+Thailand@gmail.com",
        		        		    "nickchigg+Timor-Leste@gmail.com",
        		        		    "nickchigg+Togo@gmail.com",
        		        		    "nickchigg+Tokelau@gmail.com",
        		        		    "nickchigg+Tonga@gmail.com",
        		        		    "nickchigg+TrinidadTobago@gmail.com",
        		        		    "nickchigg+Tunisia@gmail.com",
        		        		    "nickchigg+Turkey@gmail.com",
        		        		    "nickchigg+Turkmenistan@gmail.com",
        		        		    "nickchigg+TurksCaicosIslands@gmail.com",
        		        		    "nickchigg+Tuvalu@gmail.com",
        		        		    "nickchigg+Uganda@gmail.com",
        		        		    "nickchigg+UnitedArabEmirates@gmail.com",
        		        		    "nickchigg+UnitedKingdom@gmail.com",
        		        		    "nickchigg+Uruguay@gmail.com",
        		        		    "nickchigg+Uzbekistan@gmail.com",
        		        		    "nickchigg+VaticanCity@gmail.com",
        		        		    "nickchigg+Venezuela@gmail.com",
        		        		    "nickchigg+Vietnam11@gmail.com",
        		        		    "nickchigg+WallisFutuna@gmail.com",
        		        		    "nickchigg+Zambia@gmail.com"
        	};


        for (String email : testEmails) {

            ReportManager.startTest("Login Test for: " + email);

            // NEW DRIVER FOR EACH USER
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            WebDriver localDriver = new ChromeDriver(options);
            localDriver.manage().window().maximize();

            LoginPage loginPage = new LoginPage(localDriver);
            DepositPageT4trade depositPage = new DepositPageT4trade(localDriver);

            try {
                localDriver.get("https://t4trade-com.cp-uat.ironfx.local/en/client-portal");

                localDriver.findElement(By.id("details-button")).click();
                localDriver.findElement(By.id("proceed-link")).click();

                loginPage.loginProcedure(email, password);
                WaitUtils.waitForSeconds(4);

       

                // GO TO DEPOSIT PAGE
                localDriver.get("https://t4trade-com.cp-uat.ironfx.local/en/client-portal/transactions/deposits/deposits");
                WaitUtils.waitForSeconds(3);

                depositPage.enterCardType("Visa");
                WaitUtils.waitForSeconds(2);

                localDriver.findElement(By.id("currency")).click();

                // WAIT FOR CURRENCIES TO LOAD
                new WebDriverWait(localDriver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.numberOfElementsToBeMoreThan(
                                By.id("currency"), 0));

                List<WebElement> currencyElements = localDriver.findElements(By.id("currency"));
                List<String> currencies = currencyElements.stream()
                        .map(el -> el.getText().trim())
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());

                ReportManager.getTest().info("Available currencies: " + currencies);

                countryCurrenciesSummary.put(email, currencies);

                String ssDeposit = ScreenshotUtils.takeScreenshot(localDriver, "Deposit_" + email);
                ReportManager.getTest().info("Deposit page screenshot").addScreenCaptureFromPath(ssDeposit);
                
               // depositPage.enterCurrence("CHF");
              //  depositPage.enterAmount("50");
             //   String ssDeposit_CHF = ScreenshotUtils.takeScreenshot(localDriver, "Deposit_CHF_" + email);
             //   ReportManager.getTest().info("Deposit page screenshot CHF").addScreenCaptureFromPath(ssDeposit_CHF);
                
             //   depositPage.enterCurrence("CZK");
              //  depositPage.enterAmount("1000");
             //   String ssDeposit_CZK = ScreenshotUtils.takeScreenshot(localDriver, "Deposit_CZK_" + email);
             //   ReportManager.getTest().info("Deposit page screenshot CZK").addScreenCaptureFromPath(ssDeposit_CZK);
              //  WaitUtils.waitForSeconds(1);

            } catch (Exception e) {
            	// DO NOT STOP TEST 
            	String ssFail = ScreenshotUtils.takeScreenshot(localDriver, "Fail_" + email); 
            	ReportManager.getTest().fail("Test failed for: " + email) .addScreenCaptureFromPath(ssFail);
            	countryCurrenciesSummary.put(email, Arrays.asList("FAILED"));

            } finally {
                localDriver.quit();
            }
        }

        // SUMMARY
        ReportManager.startTest("Summary of Available Currencies per Country");

        for (Map.Entry<String, List<String>> entry : countryCurrenciesSummary.entrySet()) {
            ReportManager.getTest().info(
                    "Country: " + entry.getKey() + " → Currencies: " + entry.getValue()
            );
        }
    }

    @AfterClass
    public void tearDown() {
        ReportManager.flushReport();
        if (driver != null) {
            driver.quit();
        }
    }
}
