package com.Ironfx.uat.UatProjectIronFx.tests;

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

import java.time.Duration;

public class IronDepositPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private DepositPage depositPage;

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

        driver.get("https://ironfx-com.cp-uat.ironfx.local/en/client-portal");

        driver.findElement(By.id("details-button")).click();
        driver.findElement(By.id("proceed-link")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='60%'");

        String ssOpen = ScreenshotUtils.takeScreenshot(driver, "LoginPage_Open");
        ReportManager.getTest().info("Opened Client Portal Login Page")
                .addScreenCaptureFromPath(ssOpen);
    }

    @Test
    public void testLoginForMultipleUsers() {

        Map<String, List<String>> countryCurrenciesSummary = new LinkedHashMap<>();

        String[] testEmails =  {
        		
        		    "nickchigg+AfghanistanTestDNlbXjDX@gmail.com",
        		    "nickchigg+AlbaniaTestlDwQKRzf@gmail.com",
        		    "nickchigg+AlgeriaTestDbxHjITo@gmail.com",
        		    "nickchigg+AndorraTestjDrYXleQ@gmail.com",
        		    "nickchigg+AngolaTestTYknUymY@gmail.com",
        		    "nickchigg+AnguillaTestzQaugPXg@gmail.com",
        		    "nickchigg+AntarcticaTestibNhkyFG@gmail.com",
        		    "nickchigg+SotTestAntigua@gmail.com",
        		    "nickchigg+ArgentinaTestNEBYNZOd@gmail.com",
        		    "nickchigg+ArmeniaTestsxPZBziB@gmail.com",
        		    "nickchigg+ArubaTestiJiAPmcL@gmail.com",
        		    "nickchigg+AustraliaTestmILhZqQb@gmail.com",
        		    "nickchigg+AustriaTestUCppGQKs@gmail.com",
        		    "nickchigg+AzerbaijanTestICVseGDq@gmail.com",
        		    "nickchigg+BahamasTestNLCjdfjC@gmail.com",
        		    "nickchigg+BahrainTestLxDQowbW@gmail.com",
        		    "nickchigg+BangladeshTestAsDsjrUP@gmail.com",
        		    "nickchigg+BarbadosTesttuueBqUl@gmail.com",
        		    "nickchigg+BelarusTestPGdEFpKT@gmail.com",
        		    "nickchigg+BelgiumTestHwVnwkCN@gmail.com",
        		    "nickchigg+BelizeTestCqalmGQE@gmail.com",
        		    "nickchigg+BeninTestCTdyGxxq@gmail.com",
        		    "nickchigg+BhutanTestgATlvDBj@gmail.com",
        		    "nickchigg+BoliviaTestPyZXHzoa@gmail.com",
        		    "nickchigg+SottestBosnia@gmail.com",
        		    "nickchigg+BotswanaTestJhgUGuQI@gmail.com",
        		    "nickchigg+BouvetIslandTestQvlQImWh@gmail.com",
        		    "nickchigg+BrazilTestKZOBmuyk@gmail.com",
        		    "nickchigg+sottestBritishInd@gmail.com",
        		    "nickchigg+sottestBritishverg@gmail.com",
        		    "nickchigg+BruneiTestBqLAEulx@gmail.com",
        		    "nickchigg+BulgariaTestJGjwZxYW@gmail.com",
        		    "nickchigg+BurkinaFasoTestDZIDqqEt@gmail.com",
        		    "nickchigg+BurundiTesteSjVWJzY@gmail.com",
        		    "nickchigg+CambodiaTestMOWCWKpm@gmail.com",
        		    "nickchigg+CameroonTestPUfItYrg@gmail.com",
        		    "nickchigg+CapeVerdeTestOACtAsDt@gmail.com",
        		    "nickchigg+CaymanIslandsTesthqYyGidE@gmail.com",
        		    "nickchigg+CentralAfricanRepublicTest@gmail.com",
        		    "nickchigg+ChadTestJZBDJyaI@gmail.com",
        		    "nickchigg+ChileTestmKVURVcE@gmail.com",
        		    "nickchigg+ChinaTestXhWieAFS@gmail.com",
        		    "nickchigg+ChristmasIslandTestPfKLBAFA@gmail.com",
        		    "nickchigg+COCOTestSkuGKSlS@gmail.com",
        		    "nickchigg+ColombiaTestKfPVyKGp@gmail.com",
        		    "nickchigg+ComorosTestqATDzKtL@gmail.com",
        		    "nickchigg+sottestcongo@gmail.com",
        		    "nickchigg+Congo-KinshasaTestSqAlJzKi@gmail.com",
        		    "nickchigg+CookIslandsTestpeAudkcF@gmail.com",
        		    "nickchigg+CostaRicaTestJFmRUNPF@gmail.com",
        		    "nickchigg+Coted'IvoireTestGgwMKbMh@gmail.com",
        		    "nickchigg+CroatiaTestdhtcvwiL@gmail.com",
        		    "nickchigg+CuracaoTestSkuGKSlS@gmail.com",
        		    "nickchigg+CzechRepublicTestziXBEmuv@gmail.com",
        		    "nickchigg+DenmarkTestqYTECZoS@gmail.com",
        		    "nickchigg+DjiboutiTestnOLkeMlM@gmail.com",
        		    "nickchigg+DominicaTestktIXckrO@gmail.com",
        		    "nickchigg+sottestdome@gmail.com",
        		    "nickchigg+DutchAntillesTestNCMYvTlz@gmail.com",
        		    "nickchigg+EcuadorTestfUbjmyFy@gmail.com",
        		    "nickchigg+EgyptTestUSuMAwaG@gmail.com",
        		    "nickchigg+ElSalvadorTestpVRTWOlA@gmail.com",
        		    "nickchigg+EquatorialGuineaTestlTfaniXH@gmail.com",
        		    "nickchigg+EritreaTestnMXUUNjH@gmail.com",
        		    "nickchigg+EstoniaTestreDVxXXN@gmail.com",
        		    "nickchigg+EswatiniTestlMlhqeDr@gmail.com",
        		    "nickchigg+EthiopiaTestEHAEqFLh@gmail.com",
        		    "nickchigg+FalklandIslandsTestTUfLCRwP@gmail.com",
        		    "nickchigg+FaroeIslandsTestDPhmYGzv@gmail.com",
        		    "nickchigg+FijiTestMAOWAAWR@gmail.com",
        		    "nickchigg+FinlandTestXbTaouxs@gmail.com",
        		    "nickchigg+FranceTestCMYuDBkw@gmail.com",
        		    "nickchigg+FrenchGuianaTestYxPEKGzH@gmail.com",
        		    "nickchigg+FrenchPolynesiaTestgTkVYwvW@gmail.com",
        		    "nickchigg+StotestFrsouth@gmail.com",
        		    "nickchigg+GabonTestlvMXqinF@gmail.com",
        		    "nickchigg+GambiaTestcYsEidQF@gmail.com",
        		    "nickchigg+GeorgiaTestyFmSakdU@gmail.com",
        		    "nickchigg+GermanyTestJUKaqmgA@gmail.com",
        		    "nickchigg+GhanaTestcSwLOwYu@gmail.com",
        		    "nickchigg+GibraltarTestHFSufLom@gmail.com",
        		    "nickchigg+GreeceTestNqfZBSgs@gmail.com",
        		    "nickchigg+GreenlandTestsdtqVxUe@gmail.com",
        		    "nickchigg+GrenadaTestsnPSoMeq@gmail.com",
        		    "nickchigg+GuadeloupeTestEtfbLnws@gmail.com",
        		    "nickchigg+GuatemalaTestdPyRAjkn@gmail.com",
        		    "nickchigg+GuineaTestgXOQBbUH@gmail.com",
        		    "nickchigg+Guinea-BissauTestisczZtvZ@gmail.com",
        		    "nickchigg+GuyanaTestlQNZqqYn@gmail.com",
        		    "nickchigg+HondurasTestYndhluqm@gmail.com",
        		    "nickchigg+HongKongTestqUkEyyOl@gmail.com",
        		    "nickchigg+HungaryTestBOmawgsD@gmail.com",
        		    "nickchigg+IcelandTestumHKPwRk@gmail.com",
        		    "nickchigg+IndiaTestYvhuXGeF@gmail.com",
        		    "nickchigg+IndonesiaTestvGvcqWKx@gmail.com",
        		    "nickchigg+IraqTestxksQQwFK@gmail.com",
        		    "nickchigg+IrelandTestZdjScYjU@gmail.com",
        		    "nickchigg+IsraelTestscJqBTFy@gmail.com",
        		    "nickchigg+ItalyTestGIHlqsYv@gmail.com",
        		    "nickchigg+JamaicaTestBNgaiEPY@gmail.com",
        		    "nickchigg+JapanTestILTVpaaI@gmail.com",
        		    "nickchigg+JordanTestzKdblSgp@gmail.com",
        		    "nickchigg+KazakhstanTestinzFJjLM@gmail.com",
        		    "nickchigg+KenyaTestqjRstcPp@gmail.com",
        		    "nickchigg+KiribatiTestnjLhQeBD@gmail.com",
        		    "nickchigg+KuwaitTestUHJNFCei@gmail.com",
        		    "nickchigg+KyrgyzstanTestfEhoBRFC@gmail.com",
        		    "nickchigg+LaosTestFsUQzrib@gmail.com",
        		    "nickchigg+LatviaTestiyawEzUV@gmail.com",
        		    "nickchigg+LebanonTestyRkBppXj@gmail.com",
        		    "nickchigg+LesothoTestdWXIPbtl@gmail.com",
        		    "nickchigg+LiberiaTestJiRZSzRP@gmail.com",
        		    "nickchigg+LibyaTestzShoHDRT@gmail.com",
        		    "nickchigg+LiechtensteinTestMplLgKzk@gmail.com",
        		    "nickchigg+LithuaniaTestJdrJfFbQ@gmail.com",
        		    "nickchigg+LuxembourgTestNebtHAZX@gmail.com",
        		    "nickchigg+MacaoTestimcLYdBN@gmail.com",
        		    "nickchigg+MadagascarTestOdJimcmV@gmail.com",
        		    "nickchigg+MalawiTestbDrrNwoW@gmail.com",
        		    "nickchigg+MalaysiaTestKyUXELnv@gmail.com",
        		    "nickchigg+MaldivesTestSDIXaCgu@gmail.com",
        		    "nickchigg+MaliTestBWrLIMYc@gmail.com",
        		    "nickchigg+MaltaTestEGsVQACh@gmail.com",
        		    "nickchigg+sotTestMarshal@gmail.com",
        		    "nickchigg+MauritaniaTestvzqWQoUQ@gmail.com",
        		    "nickchigg+MauritiusTesteoSIuhOb@gmail.com",
        		    "nickchigg+MayotteTestghHKNLQZ@gmail.com",
        		    "nickchigg+MexicoTestTStiGscu@gmail.com",
        		    "nickchigg+MicronesiaTestXuYAVeio@gmail.com",
        		    "nickchigg+MoldovaTestnMPJCOio@gmail.com",
        		    "nickchigg+MonacoTestxzVZyrCM@gmail.com",
        		    "nickchigg+MongoliaTestBeJTpDTT@gmail.com",
        		    "nickchigg+MontenegroTestyBQnTfAm@gmail.com",
        		    "nickchigg+MontserratTestvWkGRzjS@gmail.com",
        		    "nickchigg+MoroccoTestKfgtuKbu@gmail.com",
        		    "nickchigg+MozambiqueTestrCNnDEhC@gmail.com",
        		    "nickchigg+NamibiaTestcJTpvoDl@gmail.com",
        		    "nickchigg+NauruTestjAYzPTKo@gmail.com",
        		    "nickchigg+NepalTestuoPYqjST@gmail.com",
        		    "nickchigg+NetherlandsTestEFMqTVoz@gmail.com",
        		    "nickchigg+NewZealandTestqEULejBK@gmail.com",
        		    "nickchigg+NicaraguaTestaKqBCLzY@gmail.com",
        		    "nickchigg+NigerTestojbAcEnM@gmail.com",
        		    "nickchigg+NigeriaTestKcFPkSUR@gmail.com",
        		    "nickchigg+NiueTestvtDHHAKd@gmail.com",
        		    "nickchigg+NorfolkIslandTestkulfFmYo@gmail.com",
        		    "nickchigg+NorthMacedoniaTestpxypepGY@gmail.com",
        		    "nickchigg+NorwayTestZuiDvTjU@gmail.com",
        		    "nickchigg+OmanTestTkBKiiLt@gmail.com",
        		    "nickchigg+PakistanTestPKjEGIyV@gmail.com",
        		    "nickchigg+PalestineTestfXveoQaY@gmail.com",
        		    "nickchigg+PanamaTestChOtweau@gmail.com",
        		    "nickchigg+PapuaNewGuineaTestbEzWKqxi@gmail.com",
        		    "nickchigg+ParaguayTestifbOUvzJ@gmail.com",
        		    "nickchigg+PeruTestInDwvxtL@gmail.com",
        		    "nickchigg+PhilippinesTestAvrShMWa@gmail.com",
        		    "nickchigg+PitcairnTestGZiVOlTi@gmail.com",
        		    "nickchigg+PolandTesttHactPGl@gmail.com",
        		    "nickchigg+PortugalTesthpLJfVAE@gmail.com",
        		    "nickchigg+QatarTestZuccjdAu@gmail.com",
        		    "nickchigg+ReunionTestLXfqfmdu@gmail.com",
        		    "nickchigg+RomaniaTestksHqaHVG@gmail.com",
        		    "nickchigg+RussiaTesthCmPIfqZ@gmail.com",
        		    "nickchigg+RwandaTestzTGVCDSx@gmail.com",
        		    "nickchigg+sottesthelena@gmail.com",
        		    "nickchigg+SotTestStKitt@gmail.com",
        		    "nickchigg+SotTestStPier@gmail.com",
        		    "nickchigg+SotTestStVin@gmail.com",
        		    "nickchigg+SamoaTestTKeRbTkY@gmail.com",
        		    "nickchigg+SanMarinoTestAvgVsVhk@gmail.com",
        		    "nickchigg+sotTestSaoTom@gmail.com",
        		    "nickchigg+SaudiArabiaTestLAjKcNuU@gmail.com",
        		    "nickchigg+SenegalTestpHSXJPTp@gmail.com",
        		    "nickchigg+SerbiaTestqujGYOqj@gmail.com",
        		    "nickchigg+SeychellesTestfQYDJkkW@gmail.com",
        		    "nickchigg+SierraLeoneTestzvNYjHbm@gmail.com",
        		    "nickchigg+SingaporeTestxxiCvISf@gmail.com",
        		    "nickchigg+SlovakiaTestisGKJqYN@gmail.com",
        		    "nickchigg+SloveniaTestjbHQWtgR@gmail.com",
        		    "nickchigg+SolomonIslandsTestfcDUQFNC@gmail.com",
        		    "nickchigg+SomaliaTesttDoHzTcl@gmail.com",
        		    "nickchigg+SouthAfricaTestNksYhFjz@gmail.com",
        		    "nickchigg+SotTestSouthGeo@gmail.com",
        		    "nickchigg+SouthKoreaTestGZNtHaeT@gmail.com",
        		    "nickchigg+SriLankaTestqrcZlCrn@gmail.com",
        		    "nickchigg+St.MartinTestxamStVXY@gmail.com",
        		    "nickchigg+SurinameTestYhzQtivh@gmail.com",
        		    "nickchigg+SotTestSvand@gmail.com",
        		    "nickchigg+SwedenTestEJtNpFdY@gmail.com",
        		    "nickchigg+SwitzerlandTestMyehROiY@gmail.com",
        		    "nickchigg+SotTesttaiwan@gmail.com",
        		    "nickchigg+TajikistanTestnmaleIfk@gmail.com",
        		    "nickchigg+TanzaniaTestIpHsHDAY@gmail.com",
        		    "nickchigg+ThailandTestTAENZeKk@gmail.com",
        		    "nickchigg+TogoTestDRtLiuIb@gmail.com",
        		    "nickchigg+TokelauTestRrEmnMWG@gmail.com",
        		    "nickchigg+TongaTestMXZtXZAv@gmail.com",
        		    "nickchigg+SotTeststrin@gmail.com",
        		    "nickchigg+TunisiaTestMzWfgykN@gmail.com",
        		    "nickchigg+TurkeyTestQCexFGsn@gmail.com",
        		    "nickchigg+TurkmenistanTestLUAijueD@gmail.com",
        		    "nickchigg+Turks&CaicosIslands",
        		    "nickchigg+Antigua&BarbudaTestvULnaoGY@gmail.com",
        		    "nickchigg+CaymanIslandsTestlFpbmlAe@gmail.com",
        		    "nickchigg+sotTestHeard@gmail.com",
        		    "nickchigg+KosovoTestbErqgvTi@gmail.com",
        		    "nickchigg+MartiniqueTestgLNYaZRp@gmail.com",
        		    "nickchigg+MauritaniaTestfrSfFTKO@gmail.com",
        		    "nickchigg+NewCaledoniaTestaelvqmDz@gmail.com",
        		    "nickchigg+PalauTestFVstKDUy@gmail.com",
        		    "nickchigg+SaintLuciaTestxvfbgiiH@gmail.com",
        		    "nickchigg+Timor-LesteTestailClXtq@gmail.com",
        		    "nickchigg+TuvaluTestoBuKzsvf@gmail.com",
        		    "nickchigg+Wallis&FutunaTestazrhtMIU@gmail.com",
        		    "nickchigg+WesternSaharaTestCOGlDCwo@gmail.com",
        		    "nickchigg+SotTestZamb@gmail.com",
        		    "nickchigg+SotTestZIMB@gmail.com",
        		    "nickchigg+UgandaTestBlnSuNtE@gmail.com",
        		    "nickchigg+UkraineTestBAQoMYbL@gmail.com",
        		    "nickchigg+UnitedArabEmiratesTestuAZTtQiB@gmail.com",
        		    "nickchigg+UruguayTestmWTEcUlB@gmail.com",
        		    "nickchigg+UzbekistanTestYHGJwOHe@gmail.com",
        		    "nickchigg+VaticanCityTestfySOQAel@gmail.com",
        		    "nickchigg+VenezuelaTestJGIJvJiV@gmail.com",
        		    "nickchigg+VietnamTestGoPhVyQs@gmail.com",
        		
        	};


        for (String email : testEmails) {

            ReportManager.startTest("Login Test for: " + email);

            // NEW DRIVER FOR EACH USER
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            WebDriver localDriver = new ChromeDriver(options);
            localDriver.manage().window().maximize();

            LoginPage loginPage = new LoginPage(localDriver);
            DepositPage depositPage = new DepositPage(localDriver);

            try {
                localDriver.get("https://ironfx-com.cp-uat.ironfx.local/en/client-portal");

                localDriver.findElement(By.id("details-button")).click();
                localDriver.findElement(By.id("proceed-link")).click();

                loginPage.loginProcedure(email, password);
                WaitUtils.waitForSeconds(4);

       

                // GO TO DEPOSIT PAGE
                localDriver.get("https://ironfx-com.cp-uat.ironfx.local/en/client-portal/transactions/deposits/deposit");
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
                
                depositPage.enterCurrence("CHF");
                depositPage.enterAmount("50");
                String ssDeposit_CHF = ScreenshotUtils.takeScreenshot(localDriver, "Deposit_CHF_" + email);
                ReportManager.getTest().info("Deposit page screenshot CHF").addScreenCaptureFromPath(ssDeposit_CHF);
                
                depositPage.enterCurrence("CZK");
                depositPage.enterAmount("1000");
                String ssDeposit_CZK = ScreenshotUtils.takeScreenshot(localDriver, "Deposit_CZK_" + email);
                ReportManager.getTest().info("Deposit page screenshot CZK").addScreenCaptureFromPath(ssDeposit_CZK);
                WaitUtils.waitForSeconds(1);

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
