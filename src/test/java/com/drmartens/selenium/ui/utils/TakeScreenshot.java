package com.drmartens.selenium.ui.utils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
public class TakeScreenshot {

    public static void getScreenShot(WebDriver driver, String screenShotName) throws Exception {
        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        String timeStamp = time.toString().replace(":","").replace(" ","");
        File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File dest = new File((screenShotName) + timeStamp + ".png");
        FileUtils.copyFile(scr, dest);
        Thread.sleep(3000);
    }
}
