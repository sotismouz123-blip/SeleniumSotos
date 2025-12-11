package com.Ironfx.uat.UatProjectIronFx.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

	private WebDriver driver;
	

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

	// 🔹 Locators
	private By myProfileBtn = By.xpath("/html/body/div[1]/aside/div/nav/ul/li[3]/a");
	private By VerifyProfileBtn = By.xpath("/html/body/div[1]/aside/div/nav/ul/li[3]/ul/li[1]/a/p");
	private By LangBtn = By.xpath("/html/body/div[1]/nav/ul[2]/li[1]/a");

	
	
	public void clickMyProfileBtn() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    WebElement myProfileButton = wait.until(ExpectedConditions.elementToBeClickable(myProfileBtn));
	    myProfileButton.click();
	}
	

	public void clickVerifyProfileBtn() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    WebElement verifyButton = wait.until(ExpectedConditions.elementToBeClickable(VerifyProfileBtn));
	   verifyButton.click();
	}
	
	public void clickLangBtn() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    WebElement langBnt = wait.until(ExpectedConditions.elementToBeClickable(LangBtn));
	    langBnt.click();
	}
}


