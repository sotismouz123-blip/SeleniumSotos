package com.Ironfx.uat.UatProjectIronFx.pages;



import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
	
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
	
	
	public class LoginPage {
		private WebDriver driver;
		 public LoginPage(WebDriver driver) {
		        this.driver = driver;
		    }
		// 🔹 Locators
	
		private By locEmailField = By.id("inlineFieldLogin");
		private By locPasswordField = By.id("inlineFieldPassword");
		private By locForgotPassword = By.xpath("//*[@id=\"headerLoginForm\"]/div[1]/div[2]/div/div[2]/a");
		private By locLoginButton = By.xpath("//*[@id=\"headerLoginForm\"]/div[2]/div/button");
		private By locOpenTradingAccBtn = By.xpath("/html/body/div[1]/div/div/div/section/div/div/div[5]/div/div/div[1]/a");
		private By locOpenDemoAccBtn = By.xpath("/html/body/div[1]/div/div/div/section/div/div/div[5]/div/div/div[1]/a");
		private By locRegisterTopBtn = By.id("btn-navbar-register");
		private By locContactTopBtn = By.xpath("/html/body/div[1]/nav/ul[2]/li[1]/a");
		
		
		
		// 🔹 Actions
		public void enterEmail(String email ) {
			driver.findElement(locEmailField).sendKeys(email);
		}
		
		public void enterPassword(String password) {
			driver.findElement(locPasswordField).sendKeys(password);
		}
	

		public void clickLoginBtn() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(locLoginButton));
		    loginButton.click();
		}
		
		public void clickMyForgotPassBtn() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    WebElement forgotPass = wait.until(ExpectedConditions.elementToBeClickable(locForgotPassword));
		    forgotPass.click();
		}
		
		public void clickMyOpenTradingAccBtn() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    WebElement openTradingAccBtn = wait.until(ExpectedConditions.elementToBeClickable(locOpenTradingAccBtn));
		    openTradingAccBtn.click();
		}
		
		public void clickMyOpenDemoAccBtn() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    WebElement openDemoAccBtn = wait.until(ExpectedConditions.elementToBeClickable(locOpenDemoAccBtn));
		    openDemoAccBtn.click();
		}
		
		public void clickMyRegisterTopBtn() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    WebElement registerTopBtn = wait.until(ExpectedConditions.elementToBeClickable(locRegisterTopBtn));
		    registerTopBtn.click();
		}
		
		public void clickMyContactTopBtn() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    WebElement contactTopBtn = wait.until(ExpectedConditions.elementToBeClickable(locContactTopBtn));
		    contactTopBtn.click();
		}
		
		 public static void verifyDropdownOptions(WebDriver driver, By locator, List<String> expectedOptions) {
			 WebElement dropdownLang = driver.findElement(By.xpath("/html/body/div[1]/nav/ul[2]/li[1]/div"));
			 List<String> actualOptions = dropdownLang.findElements(By.tagName("option"))
			                                         .stream()
			                                         .map(WebElement::getText)
			                                         .collect(Collectors.toList());

		        System.out.println("🔹 Actual Options: " + actualOptions);
		        System.out.println("🔹 Expected Options: " + expectedOptions);

		        // Επαλήθευση ότι οι επιλογές είναι ακριβώς αυτές που περιμένουμε
		      //  assertEquals(actualOptions, expectedOptions, "Οι επιλογές του dropdown δεν ταιριάζουν!");
		    }
		
		 
		 // Μέθοδος login με email και password
		    public void loginProcedure(String email, String password) {
		        driver.findElement(locEmailField).sendKeys(email);
		        driver.findElement(locPasswordField).sendKeys(password);
		        driver.findElement(locLoginButton).click();
		    }
}
