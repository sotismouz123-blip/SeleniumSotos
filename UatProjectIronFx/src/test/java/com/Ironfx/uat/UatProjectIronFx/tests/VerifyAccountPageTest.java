package com.Ironfx.uat.UatProjectIronFx.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.Ironfx.uat.UatProjectIronFx.utilities.EmailReportSender;
import com.Ironfx.uat.UatProjectIronFx.utilities.CodePoints;
import com.Ironfx.uat.UatProjectIronFx.utilities.ScreenshotUtils;
import com.Ironfx.uat.UatProjectIronFx.utilities.TestData;
import com.Ironfx.uat.UatProjectIronFx.utilities.ReportManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.Ironfx.uat.UatProjectIronFx.pages.ProfilePage;
import com.Ironfx.uat.UatProjectIronFx.pages.RegistrationPage;

public class VerifyAccountPageTest {

	private WebDriver driver;
	private DashboardPage dashboardPage;
	private LoginPage loginPage;
	private ProfilePage profilePage;

	private static final String password = "Password1!";


	private String normalizeText(String text) {
		return Normalizer.normalize(text.trim(), Normalizer.Form.NFKC);
	}

	private String toCodePoints(String text) {
		return text.codePoints().mapToObj(cp -> String.format("U+%04X", cp)).collect(Collectors.joining(" "));
	}

	@BeforeClass
	public void setUp() {
		// Init report
	
		ReportManager.startTest("Profile page test ");

		System.setProperty("webdriver.chrome.driver", "C://Users/smouzoulas/Desktop/nnnn/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		driver.get("  https://ironfx-com.cp-uat.ironfx.local/en/client-portal");
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		profilePage = new ProfilePage(driver);

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
		String Nationality = "Croatian";
		String Gender = "Male";
		String DateDd = "15";
		String DateMm = "07 - July";
		String DateYy = "1990";
		String TaxCountry = "Croatia";
		String TinNo = "HR123456789";
		String AddressOne = "123 Main Street";
		String AddressTwo = "Apt 4B";
		String Town = "Zagreb";
		String PostCode = "10000";
		String CountryOfResidence = "Croatia";
		String LandLine = "(+385) Croatia";
		String PhoneOne = "12341234";
		String MobileCode = "(+385) Croatia";
		String PhoneTwo = "12341234";
		String EmpStatus = "Employed";
		String NatBusiness = "HR";
		String SourceOfFund = "Employment";
		String ExpDepId = "< 10,000";
		String AnnualInc = "$50,000 – $99,999";
		String NetWorth = "$50,000 – $99,999";
		String FrequencyOne = "Regularly";
		String FrequencyTwo = "Regularly";
		String FrequencyThree = "Regularly";
		String AvgOne = "Less than $10,000";
		String AvgTwo = "Less than $10,000";
		String AvgThree = "Less than $10,000";
		String SeminarCourse = "Seminars/Courses of at least 30 minutes or more";

		ReportManager.getTest().info("Starting Profile page testing");
		// Login procedure
		loginPage.loginProcedure(TestData.generatedEmail, password);
		WaitUtils.waitForSeconds(5);

		dashboardPage.clickMyProfileBtn();
		dashboardPage.clickVerifyProfileBtn();
		// Profile page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		js.executeScript("document.body.style.zoom='25%'");
		String ssVerify = ScreenshotUtils.takeScreenshot(driver, "Email_Verification");
		// Nationality
		profilePage.enterNationality(Nationality);

		// Gender
		profilePage.enterGender(Gender);

		// Date of Birth (Day, Month, Year)
		profilePage.enterDateDd(DateDd);
		profilePage.enterDateMm(DateMm);
		profilePage.enterDateYy(DateYy);

		// Tax Country
		profilePage.enterTaxCountry(TaxCountry);

		// Tax Identification Number
		profilePage.enterTinNo(TinNo);

		// Address
		profilePage.enterAddressOne(AddressOne);
		profilePage.enterAddressTwo(AddressTwo);
		profilePage.enterTown(Town);
		profilePage.enterPostcode(PostCode);

		// Country of Residence
		profilePage.enterCoR(CountryOfResidence);

		// Landline
		profilePage.enterLandLine(LandLine);
		profilePage.enterPhoneOne(PhoneOne);

		// Mobile
		profilePage.enterMobileCode(MobileCode);
		profilePage.enterMobile(PhoneTwo);
		 ReportManager.getTest().pass("The fisrt fields populated").addScreenCaptureFromPath(ssVerify);
        
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		// Employment Status
		profilePage.enterEmpStatus(EmpStatus);

		// Employment Status
		profilePage.enterNatureOfBusiness(NatBusiness);

		// Source of Funds
		profilePage.enterSourceOfFund(SourceOfFund);

		// Expected Deposit
		profilePage.enterExpDepId(ExpDepId);

		// Annual Income
		profilePage.enterAnnualInc(AnnualInc);

		// Net Worth
		profilePage.enterNetWorth(NetWorth);
		ReportManager.getTest().pass("The pending fields populated").addScreenCaptureFromPath(ssVerify);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		WaitUtils.waitForSeconds(2);
		// Trading experience

		profilePage.selectFreqOfTransactionsOne(FrequencyOne);
		WaitUtils.waitForSeconds(1);
		profilePage.selectAverageVolumeOne(AvgOne);
		profilePage.selectFreqOfTransactionsTwo(FrequencyTwo);
		WaitUtils.waitForSeconds(1);
		profilePage.selectAverageVolumeTwo(AvgTwo);
		profilePage.selectFreqOfTransactionsThree(FrequencyThree);
		WaitUtils.waitForSeconds(1);
		profilePage.selectAverageVolumeThree(AvgThree);
		// Seminar

		profilePage.checkSeminarExperience();
		WaitUtils.waitForSeconds(1);
		profilePage.enterSeminarCourse(SeminarCourse);

		// Work Experience
		profilePage.checkWorkExperienceNo();

		
		//driver.findElement(By.xpath("/html/body/div[71]/div/div/button/svg")).click();
		
		// Update button
		WaitUtils.waitForSeconds(2);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		profilePage.clickUpdateButton();

		// Verification
		String pageSource = driver.getPageSource();
		Assert.assertTrue(pageSource.contains("How to verify your identity?"),
				"The text 'How to verify your identity?' is not visible ");
		String ssOpen = ScreenshotUtils.takeScreenshot(driver, "LoginPage_Open");
		ReportManager.getTest().info("The data verification done succesffully").addScreenCaptureFromPath(ssOpen);

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
		//String recipientEmail = "nickchigg@gmail.com";

		// Στέλνεις το report με email
		//EmailReportSender.sendReport(recipientEmail, reportPath);
	}
}
