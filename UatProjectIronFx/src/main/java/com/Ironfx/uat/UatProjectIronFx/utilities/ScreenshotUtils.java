package com.Ironfx.uat.UatProjectIronFx.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    private static final String DIRECTORY =
        "C:\\Users\\smouzoulas\\Desktop\\nnnn\\UatProjectIronFx\\Reports\\screenshots\\";

    /**
     * Αποθηκεύει screenshot και επιστρέφει το path του αρχείου
     */
    public static String takeScreenshot(WebDriver driver, String comment) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String safeComment = comment.replaceAll("[^a-zA-Z0-9-_]", "_");
        String fileName = "screenshot_" + safeComment + "_" + timestamp + ".png";

        try {
            Path dirPath = Path.of(DIRECTORY);
            Files.createDirectories(dirPath);

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destPath = dirPath.resolve(fileName);
            Files.copy(srcFile.toPath(), destPath);

            System.out.println("✅ Screenshot saved: " + destPath);
            return destPath.toString(); // επιστρέφει το path
        } catch (IOException e) {
            System.err.println("❌ Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }
}
