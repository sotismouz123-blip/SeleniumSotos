package com.Ironfx.uat.UatProjectIronFx.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {

	private WebDriver driver;

	// 🔹 Locators
	private By NationalityField = By.id("nationality");
	private By GenderField = By.id("gender");
	private By DdField = By.id("dob_dd");
	private By MmField = By.id("dob_mm");
	private By YyField = By.id("dob_yy");
	private By TaxCountryField = By.id("tax_country");
	private By TaxTinNoField = By.xpath("/html/body/div[1]/div[1]/div/div[3]/section/div/div/form/div[2]/div[2]/div/div[2]/div[2]/input");
	private By AndressOneField = By.xpath("/html/body/div[1]/div[1]/div/div[3]/section/div/div/form/div[3]/div[2]/div[1]/input");
	private By AndressTwoField = By.xpath("/html/body/div[1]/div[1]/div/div[3]/section/div/div/form/div[3]/div[2]/div[2]/input");
	private By TownField = By.xpath("/html/body/div[1]/div[1]/div/div[3]/section/div/div/form/div[3]/div[2]/div[3]/input");
	private By PostCodeField = By.xpath("/html/body/div[1]/div[1]/div/div[3]/section/div/div/form/div[3]/div[2]/div[4]/input");
	private By CoRField = By.id("country");
	private By LandlineField = By.id("phone_landline_prefix");
	private By PhoneOneField = By.xpath("/html/body/div[1]/div[1]/div/div[3]/section/div/div/form/div[3]/div[2]/div[6]/div/div[2]/input");
	private By MobileField = By.id("phone_mobile_prefix");
	private By PhoneTwoField = By.id("phone_mobile");
	private By EmpStatusField = By.id("employment_status");
	private By NatureOfBuusinessField = By.id("business_nature");
	private By SourceOfFundField = By.id("funds_source");
	private By ExpDepIdField = By.id("expected_deposit_id");
	private By AnnualIncField = By.id("annual_income");
	private By NetWorthField = By.id("net_worth");
	private By FreqOfTransactionsOneField = By.id("traded_forex_frequency");
	private By AverageVolumeOneField = By.id("traded_forex_volume");
    private By FreqOfTransactionsThreeField = By.id("traded_products_frequency");
	private By AverageVolumeThreeField = By.id("traded_products_volume");
	private By FreqOfTransactionsTwoField = By.id("traded_bonds_frequency");
	private By AverageVolumeTwoField = By.id("traded_bonds_volume");
	private By SeminarCheckBoxField = By.id("seminar_experience_yes");
	private By SeminarDdlField = By.id("seminar_course_experience");
	private By WorkExpNoField = By.id("work_experience_no");
	private By UpdateBtn = By.xpath("/html/body/div[1]/div[1]/div/div[3]/section/div/div/form/div[7]/div[3]/div/button");
	

	public ProfilePage(WebDriver driver) {
		this.driver = driver;
	}


	// Nationality field 
	public void enterNationality(String Nationality) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(NationalityField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(NationalityField));
		new Select(dropdown).selectByVisibleText(Nationality);
	}
	
	// Gender field 
	public void enterGender(String Gender) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(GenderField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(GenderField));
		new Select(dropdown).selectByVisibleText(Gender);
	}
	
	// DateDD field 
	public void enterDateDd(String DateDd) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(DdField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(DdField));
		new Select(dropdown).selectByVisibleText(DateDd);
	}
	
	// DateMM field 
	public void enterDateMm(String DateMm) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(MmField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(MmField));
		new Select(dropdown).selectByVisibleText(DateMm);
	}
	
	// DateYY field 
	public void enterDateYy(String DateYy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(YyField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(YyField));
		new Select(dropdown).selectByVisibleText(DateYy);
	}
	
	// Tax Country field 
	public void enterTaxCountry(String TaxCountry) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(TaxCountryField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(TaxCountryField));
		new Select(dropdown).selectByVisibleText(TaxCountry);
	}
	
	// TIN field 
	public void enterTinNo(String tinNo) {
		driver.findElement(TaxTinNoField).sendKeys(tinNo);
	}
	
	// Address 1 field 
	public void enterAddressOne(String AddressOne) {
		driver.findElement(AndressOneField).sendKeys(AddressOne);
	}
	
	// Address 2 field 
	public void enterAddressTwo(String AddressTwo) {
		driver.findElement(AndressTwoField).sendKeys(AddressTwo);
	}
	
	// Town field 
	public void enterTown(String Town) {
		driver.findElement(TownField).sendKeys(Town);
	}
	
	// PostCode field 
	public void enterPostcode(String PostCode) {
		driver.findElement(PostCodeField).sendKeys(PostCode);
	}
	
	// Country of Residence field 
	public void enterCoR(String CountryOfResidence) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(CoRField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(CoRField));
		new Select(dropdown).selectByVisibleText(CountryOfResidence);
	}
	
	// Landline DropDown 
	public void enterLandLine(String LandLine) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(LandlineField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(LandlineField));
		new Select(dropdown).selectByVisibleText(LandLine);
	}
	
	// PhoneOne  field 
	public void enterPhoneOne(String PhoneOne) {
		driver.findElement(PhoneOneField).sendKeys(PhoneOne);
	}
	
	// Mobile Dropdown 
	public void enterMobileCode(String MobileCode) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(MobileField));
		new Select(dropdown).selectByVisibleText(MobileCode);
	}
	
	//PhoneTwo  field 
	public void enterMobile(String PhoneTwo) {
		driver.findElement(PhoneTwoField).sendKeys(PhoneTwo);
	}
	
	//Employment status  Dropdown 
	public void enterEmpStatus(String EmpStatus) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(EmpStatusField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(EmpStatusField));
		new Select(dropdown).selectByVisibleText(EmpStatus);
	}
	
	//Nature of business  Dropdown 
		public void enterNatureOfBusiness (String NatBusiness) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(NatureOfBuusinessField));
			 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(NatureOfBuusinessField));
			new Select(dropdown).selectByVisibleText(NatBusiness);
		}
	
	
	//Source of fund  Dropdown 
	public void enterSourceOfFund(String SourceOfFund) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(SourceOfFundField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(SourceOfFundField));
		new Select(dropdown).selectByVisibleText(SourceOfFund);
	}
	
	//Expected deposit Dropdown 
	public void enterExpDepId(String ExpDepId) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(ExpDepIdField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(ExpDepIdField));
		new Select(dropdown).selectByVisibleText(ExpDepId);
	}
	
	//Annual income Dropdown
	public void enterAnnualInc(String AnnualInc) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(AnnualIncField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(AnnualIncField));
		new Select(dropdown).selectByVisibleText(AnnualInc);
	}
	

	//Enter net worth Dropdown
	public void enterNetWorth(String NetWorth) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(NetWorthField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(NetWorthField));
		new Select(dropdown).selectByVisibleText(NetWorth);
	}
	
	// Seminar dropdown
	public void enterSeminarCourse(String seminarCourse) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(SeminarDdlField));
	    new Select(dropdown).selectByVisibleText(seminarCourse);
	}

	// Seminar checkbox
	public void checkSeminarExperience() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(SeminarCheckBoxField));
	    if (!checkbox.isSelected()) {
	        checkbox.click();
	    }
	}

	// Work Experience No checkbox
	public void checkWorkExperienceNo() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(WorkExpNoField));
	    if (!checkbox.isSelected()) {
	        checkbox.click();
	    }
	}

	// Frequency of Transactions One (custom div)

	public void selectFreqOfTransactionsOne(String FrequencyOne) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(FreqOfTransactionsOneField));
	    new Select(dropdown).selectByVisibleText(FrequencyOne);
	}

	// Average Volume One (custom div)
	public void selectAverageVolumeOne(String AvgOne) {
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(AverageVolumeOneField));
		    new Select(dropdown).selectByVisibleText(AvgOne);
		}

	// Frequency of Transactions Two
	public void selectFreqOfTransactionsTwo(String FrequencyTwo) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(FreqOfTransactionsTwoField));
	    new Select(dropdown).selectByVisibleText(FrequencyTwo);
	}
	// Average Volume Two
	public void selectAverageVolumeTwo(String AvgTwo) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(AverageVolumeTwoField));
	    new Select(dropdown).selectByVisibleText(AvgTwo);
	}

	// Frequency of Transactions Three
	public void selectFreqOfTransactionsThree(String FrequencyThree) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(FreqOfTransactionsThreeField));
	    new Select(dropdown).selectByVisibleText(FrequencyThree);
	}
	
	

	// Average Volume Three
	public void selectAverageVolumeThree(String AvgThree) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(AverageVolumeThreeField));
	    new Select(dropdown).selectByVisibleText(AvgThree);
	}

	//Update Btn
	public void clickUpdateButton() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(UpdateBtn));
	    updateButton.click();
	}
	
	
}
