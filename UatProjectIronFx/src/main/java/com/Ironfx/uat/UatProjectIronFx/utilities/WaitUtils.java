package com.Ironfx.uat.UatProjectIronFx.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 10; // seconds

    // Περιμένει μέχρι να εμφανιστεί το στοιχείο
    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Περιμένει μέχρι να είναι clickable το στοιχείο
    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Περιμένει μέχρι να εξαφανιστεί το στοιχείο
    public static boolean waitForElementInvisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Περιμένει μέχρι να φορτωθεί συγκεκριμένο κείμενο σε στοιχείο
    public static boolean waitForTextPresent(WebDriver driver, By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    


        // Απλή καθυστέρηση για Χ δευτερόλεπτα
        public static void waitForSeconds(int seconds) {
            try {
                Thread.sleep(seconds * 1000); // μετατροπή σε milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        
    }
}
