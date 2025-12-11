package com.Ironfx.uat.UatProjectIronFx.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
public class RegistrationPage {
	private WebDriver driver;

	// 🔹 Locators
	private By firstNameField = By.xpath("/html/body/div[1]/form/div[2]/div[2]/input");
	private By lastNameField = By.id("last_name");
	private By emailField = By.id("email");
	private By countryField = By.id("country");
	private By phoneField = By.id("phone_mobile");
	private By accountTypeField = By.id("account_type");
	private By bonusSchemeField = By.id("bonus_scheme");
	private By currencyField = By.id("currency");
	private By leverageField = By.id("leverage");
	private By passwordField = By.id("password");
	private By confirmPasswordField = By.id("confirm_password");
	private By submitButton = By.xpath("//*[@id=\"registration-form\"]/div[11]/div/button");
	private By messageLabel = By.id("message");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
	}

	// 🔹 Actions
	public void enterFirstName(String firstName) {
		driver.findElement(firstNameField).sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		driver.findElement(lastNameField).sendKeys(lastName);
	}

	public void enterEmail(String email) {
		driver.findElement(emailField).sendKeys(email);
	}

	public void enterPhone(String phone) {
		driver.findElement(phoneField).sendKeys(phone);
	}

	// ✅ Dropdowns με Select
	public void enterCountry(String country) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(countryField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(countryField));
		new Select(dropdown).selectByVisibleText(country);
	}

	public void selectAccountType(String accountType) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(accountTypeField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(accountTypeField));
		new Select(dropdown).selectByVisibleText(accountType);
	}

	public void selectBonusScheme(String bonusScheme) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(bonusSchemeField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(bonusSchemeField));
		new Select(dropdown).selectByVisibleText(bonusScheme);
	}

	public void selectCurrency(String currency) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(currencyField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(currencyField));
		 new Select(dropdown).selectByVisibleText(currency);
	}

	public void selectLeverage(String leverage) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(leverageField));
		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(leverageField));
		  new Select(dropdown).selectByVisibleText(leverage);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordField).sendKeys(password);
	}

	public void enterConfirmPassword(String password) {
		driver.findElement(confirmPasswordField).sendKeys(password);
	}

	public void clickSubmit() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

	    try {
	        btn.click();
	    } catch (Exception e) {
	        // fallback σε JS click αν το overlay δεν φεύγει
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
	    }
	}



	
	//public void clicKToGoHome() {
		
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// WebElement goBtn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		// goBtn.click();
	//}


}
