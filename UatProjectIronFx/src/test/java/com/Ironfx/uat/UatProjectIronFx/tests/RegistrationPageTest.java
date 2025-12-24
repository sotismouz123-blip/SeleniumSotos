package com.Ironfx.uat.UatProjectIronFx.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

public class RegistrationPageTest {

    private WebDriver driver;
    private Faker faker;
    private RegistrationPage registrationPage;
    private DashboardPage dashboardPage;

    private static final String password = "Password1!";

    // ✅ List to store results for ALL countries (in order)
    // Format per entry: "✅ Country → email" or "❌ Country → email"
    private List<String> registrationResults = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        ReportManager.initReport();
        ReportManager.startTest("Registration Page Test");

        faker = new Faker();
        System.setProperty("webdriver.chrome.driver", "C://Users/smouzoulas/Desktop/nnnn/chromedriver.exe");
    }

    // ✅ Method to start a fresh browser each time
    private WebDriver startNewBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://ironfx-com.cp-uat.ironfx.local/en/register");

        // Bypass SSL (if present)
        try {
            driver.findElement(By.id("details-button")).click();
            driver.findElement(By.id("proceed-link")).click();
        } catch (Exception ignored) {}

        // Zoom out
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='60%'");

        return driver;
    }

    @Test
    public void testSuccessfulRegistrationForAllCountries() {

    	String[] countries = {
    		    "Afghanistan",
    		    "Albania",
    		    "Algeria",
    		    "Andorra",
    		    "Angola",
    		    "Anguilla",
    		    "Antarctica",
    		    "Antigua & Barbuda",
    		    "Argentina",
    		    "Armenia",
    		    "Aruba",
    		    "Australia",
    		    "Austria",
    		    "Azerbaijan",
    		    "Bahamas",
    		    "Bahrain",
    		    "Bangladesh",
    		    "Barbados",
    		    "Belarus",
    		    "Belgium",
    		    "Belize",
    		    "Benin",
    		    "Bhutan",
    		    "Bolivia",
    		    "Bosnia and Herzegovina",
    		    "Botswana",
    		    "Bouvet Island",
    		    "Brazil",
    		    "British Indian Ocean Territory",
    		    "British Virgin Islands",
    		    "Brunei",
    		    "Bulgaria",
    		    "Burkina Faso",
    		    "Burundi",
    		    "Cambodia",
    		    "Cameroon",
    		    "Cape Verde",
    		    "Cayman Islands",
    		    "Central African Republic",
    		    "Chad",
    		    "Chile",
    		    "China",
    		    "Christmas Island",
    		    "Cocos (Keeling) Islands",
    		    "Colombia",
    		    "Comoros",
    		    "Congo - Brazzaville",
    		    "Congo - Kinshasa",
    		    "Cook Islands",
    		    "Costa Rica",
    		    "Cote d'Ivoire",
    		    "Croatia",
    		    "Curaçao",
    		    "Czech Republic",
    		    "Denmark",
    		    "Djibouti",
    		    "Dominica",
    		    "Dominican Republic",
    		    "Dutch Antilles",
    		    "Ecuador",
    		    "Egypt",
    		    "El Salvador",
    		    "Equatorial Guinea",
    		    "Eritrea",
    		    "Estonia",
    		    "Eswatini",
    		    "Ethiopia",
    		    "Falkland Islands",
    		    "Faroe Islands",
    		    "Fiji",
    		    "Finland",
    		    "France",
    		    "French Guiana",
    		    "French Polynesia",
    		    "French Southern Territories",
    		    "Gabon",
    		    "Gambia",
    		    "Georgia",
    		    "Germany",
    		    "Ghana",
    		    "Gibraltar",
    		    "Greece",
    		    "Greenland",
    		    "Grenada",
    		    "Guadeloupe",
    		    "Guatemala",
    		    "Guinea",
    		    "Guinea-Bissau",
    		    "Guyana",
    		    "Heard Island and McDonald Islands",
    		    "Honduras",
    		    "Hong Kong",
    		    "Hungary",
    		    "Iceland",
    		    "India",
    		    "Indonesia",
    		    "Iraq",
    		    "Ireland",
    		    "Israel",
    		    "Italy",
    		    "Jamaica",
    		    "Japan",
    		    "Jordan",
    		    "Kazakhstan",
    		    "Kenya",
    		    "Kiribati",
    		    "Kosovo",
    		    "Kuwait",
    		    "Kyrgyzstan",
    		    "Laos",
    		    "Latvia",
    		    "Lebanon",
    		    "Lesotho",
    		    "Liberia",
    		    "Libya",
    		    "Liechtenstein",
    		    "Lithuania",
    		    "Luxembourg",
    		    "Macao",
    		    "Madagascar",
    		    "Malawi",
    		    "Malaysia",
    		    "Maldives",
    		    "Mali",
    		    "Malta",
    		    "Marshall Islands",
    		    "Martinique",
    		    "Mauritania",
    		    "Mauritius",
    		    "Mayotte",
    		    "Mexico",
    		    "Micronesia",
    		    "Moldova",
    		    "Monaco",
    		    "Mongolia",
    		    "Montenegro",
    		    "Montserrat",
    		    "Morocco",
    		    "Mozambique",
    		    "Namibia",
    		    "Nauru",
    		    "Nepal",
    		    "Netherlands",
    		    "New Caledonia",
    		    "New Zealand",
    		    "Nicaragua",
    		    "Niger",
    		    "Nigeria",
    		    "Niue",
    		    "Norfolk Island",
    		    "North Macedonia",
    		    "Norway",
    		    "Oman",
    		    "Pakistan",
    		    "Palau",
    		    "Palestine",
    		    "Panama",
    		    "Papua New Guinea",
    		    "Paraguay",
    		    "Peru",
    		    "Philippines",
    		    "Pitcairn",
    		    "Poland",
    		    "Portugal",
    		    "Qatar",
    		    "Reunion",
    		    "Romania",
    		    "Russia",
    		    "Rwanda",
    		    "Saint Helena, Ascension and Tristan da Cunha",
    		    "Saint Kitts and Nevis",
    		    "Saint Lucia",
    		    "Saint Pierre and Miquelon",
    		    "Saint Vincent and the Grenadines",
    		    "Samoa",
    		    "San Marino",
    		    "São Tomé & Príncipe",
    		    "Saudi Arabia",
    		    "Senegal",
    		    "Serbia",
    		    "Seychelles",
    		    "Sierra Leone",
    		    "Singapore",
    		    "Slovakia",
    		    "Slovenia",
    		    "Solomon Islands",
    		    "Somalia",
    		    "South Africa",
    		    "South Georgia & South Sandwich Islands",
    		    "South Korea",
    		    "Sri Lanka",
    		    "St. Martin",
    		    "Suriname",
    		    "Svalbard & Jan Mayen",
    		    "Sweden",
    		    "Switzerland",
    		    "Taiwan",
    		    "Tajikistan",
    		    "Tanzania",
    		    "Thailand",
    		    "Timor-Leste",
    		    "Togo",
    		    "Tokelau",
    		    "Tonga",
    		    "Trinidad & Tobago",
    		    "Tunisia",
    		    "Turkey",
    		    "Turkmenistan",
    		    "Turks & Caicos Islands",
    		    "Tuvalu",
    		    "Uganda",
    		    "Ukraine",
    		    "United Arab Emirates",
    		    "Uruguay",
    		    "Uzbekistan",
    		    "Vatican City",
    		    "Venezuela",
    		    "Vietnam",
    		    "Wallis & Futuna",
    		    "Western Sahara",
    		    "Zambia",
    		    "Zimbabwe"
    		};

       
        for (String country : countries) {

            try {
                // ✅ Close previous browser
                if (driver != null) {
                    driver.quit();
                }

                // ✅ Start new browser
                driver = startNewBrowser();

                // ✅ Recreate Page Objects
                registrationPage = new RegistrationPage(driver);
                dashboardPage = new DashboardPage(driver);

                // ✅ Generate data
                String name = faker.regexify("Test" + "[A-Za-z]{8}");
                String expectedEmail = "nickchigg+" + country.replace(" ", "") + name + "@gmail.com";
                TestData.generatedEmail = expectedEmail;

                // ✅ Fill form
                registrationPage.enterFirstName(name);
                registrationPage.enterLastName(name);
                registrationPage.enterEmail(expectedEmail);
                registrationPage.enterCountry(country);
                registrationPage.enterPhone("12341234");
                registrationPage.selectAccountType("Standard Floating");
                registrationPage.selectBonusScheme("Not applicable");
                registrationPage.selectCurrency("USD");
                registrationPage.selectLeverage("1:500");
                registrationPage.enterPassword(password);
                registrationPage.enterConfirmPassword(password);

                // Screenshot of form
                String ssForm = ScreenshotUtils.takeScreenshot(driver, "Registration_form_" + country);
                ReportManager.getTest().info("Form filled for " + country)
                        .addScreenCaptureFromPath(ssForm);

                // Scroll & Submit
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

                registrationPage.clickSubmit();
                WaitUtils.waitForSeconds(5);

                System.out.println("✅ SUCCESS for: (" + country + ") : " + expectedEmail);
                ReportManager.logInfo("✅ SUCCESS for: (" + country + ") : " + expectedEmail);

                // ✅ Save success result (keeps order)
                registrationResults.add("✅ " + country + " → " + expectedEmail);

            } catch (Exception e) {

                // ✅ Screenshot on fail
                String ssFail = ScreenshotUtils.takeScreenshot(driver, "FAIL_" + country);

                ReportManager.getTest()
                        .fail("❌ FAILED for country: " + country + " — " + e.getMessage())
                        .addScreenCaptureFromPath(ssFail);

                System.out.println("❌ FAILED for: " + country);

                // ✅ Save fail result (email may still be in TestData)
                String emailForLog = TestData.generatedEmail != null ? TestData.generatedEmail : "N/A";
                registrationResults.add("❌ " + country + " → " + emailForLog);
            }
        }
    }

    @AfterClass
    public void tearDown() {

        // ✅ Print summary in console
        System.out.println("\n==============================");
        System.out.println("✅ FINAL REGISTRATION SUMMARY");
        System.out.println("==============================");

        for (String result : registrationResults) {
            System.out.println(result);
        }

        System.out.println("==============================\n");

        // ✅ Export CSV + HTML
        exportCSV();
        exportHTML();

        ReportManager.flushReport();

        if (driver != null) {
            driver.quit();
        }
    }

    // ✅ CSV export
    private void exportCSV() {
        try {
            FileWriter writer = new FileWriter("registration_results.csv");

            writer.write("Status,Country,Email\n");

            for (String result : registrationResults) {
                // result format: "✅ Greece → email"
                String status = result.startsWith("✅") ? "SUCCESS" : "FAIL";

                String withoutIcon = result.substring(2).trim(); // remove emoji + space
                String[] parts = withoutIcon.split("→");
                String country = parts[0].trim();
                String email = parts.length > 1 ? parts[1].trim() : "";

                writer.write(status + "," + country + "," + email + "\n");
            }

            writer.close();
            System.out.println("✅ CSV exported: registration_results.csv");

        } catch (Exception e) {
            System.out.println("❌ CSV export failed: " + e.getMessage());
        }
    }

    // ✅ HTML export
    private void exportHTML() {
        try {
            FileWriter writer = new FileWriter("registration_results.html");

            writer.write("<html><head><title>Registration Results</title>");
            writer.write("<style>");
            writer.write("body { font-family: Arial, sans-serif; }");
            writer.write("table { width: 100%; border-collapse: collapse; }");
            writer.write("th, td { border: 1px solid #444; padding: 8px; text-align: left; }");
            writer.write("th { background-color: #222; color: white; }");
            writer.write(".success { background-color: #c8e6c9; }");
            writer.write(".fail { background-color: #ffcdd2; }");
            writer.write("</style></head><body>");

            writer.write("<h2>Registration Results</h2>");
            writer.write("<table>");
            writer.write("<tr><th>Status</th><th>Country</th><th>Email</th></tr>");

            for (String result : registrationResults) {
                boolean success = result.startsWith("✅");
                String status = success ? "SUCCESS" : "FAIL";
                String cssClass = success ? "success" : "fail";

                String withoutIcon = result.substring(2).trim(); // remove emoji + space
                String[] parts = withoutIcon.split("→");
                String country = parts[0].trim();
                String email = parts.length > 1 ? parts[1].trim() : "";

                writer.write("<tr class='" + cssClass + "'>");
                writer.write("<td>" + status + "</td>");
                writer.write("<td>" + country + "</td>");
                writer.write("<td>" + email + "</td>");
                writer.write("</tr>");
            }

            writer.write("</table></body></html>");
            writer.close();

            System.out.println("✅ HTML exported: registration_results.html");

        } catch (Exception e) {
            System.out.println("❌ HTML export failed: " + e.getMessage());
        }
    }
}
