package com.Ironfx.uat.UatProjectIronFx.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DepositPage {

		private WebDriver driver;
		 public DepositPage(WebDriver driver) {
		        this.driver = driver;
		    }
		// 🔹 Locators
	
		private By cardTypeField = By.xpath("/html/body/div[1]/div[1]/div/div[3]/section/div/div/form/div/div[1]/div[6]/select");
		private By currencyField = By.xpath("/html/body/div[1]/div[1]/div/div[3]/section/div/div/form/div/div[1]/div[7]/select");
		private By ammountField = By.xpath("/html/body/div[1]/div[1]/div/div[3]/section/div/div/form/div/div[1]/div[8]/select");
		
		
		// 🔹 Actions
		// CardType field 
		public void enterCardType(String CardType) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(cardTypeField));
			 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(cardTypeField));
			new Select(dropdown).selectByVisibleText(CardType);
		}
		
		// Currency field 
		public void enterCurrence(String Currency) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(currencyField));
			 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(currencyField));
			new Select(dropdown).selectByVisibleText(Currency);
		}
		
		
		// Currency field 
				public void enterAmount(String Ammount) {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
					wait.until(ExpectedConditions.presenceOfElementLocated(ammountField));
					 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(ammountField));
					new Select(dropdown).selectByVisibleText(Ammount);
				}
		
		
}
