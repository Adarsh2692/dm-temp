package com.drmartens.selenium.ui.step_def;

import com.drmartens.selenium.ui.drivermanager.DriverManger;
import com.drmartens.selenium.ui.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Hooks {
    public static final Logger LOG = LoggerFactory.getLogger(Hooks.class);
    public DriverManger driverManger = new DriverManger();

    // @Before
    public void setUp(Scenario scenario) {
        if (driverManger.getDriver() == null || ((RemoteWebDriver) driverManger.getDriver()).getSessionId() == null)
            try {
                driverManger.getWebdriver();
            } catch (Exception e) {
                System.out.println("Error in initiliazing webdriver");
                System.out.println("WEBDRIVER : " + e);
            }

        driverManger.navigateToURL();
        driverManger.implicitlyWait();
        if (driverManger.isBrowserstackPlatform())
            bsSessionUtil(scenario);

        PageFactory.initElements(driverManger.getDriver(), this);
    }

    // @After
    public void postExecutionActivity(final Scenario scenario) {
        try {
            bsExecutionStatus(scenario);
        } catch (final WebDriverException | ClassCastException wde) {
            LOG.info(wde.getMessage());
        } finally {
            driverManger.quitWebDriver();
        }
    }

    public void bsSessionUtil(Scenario scenario) {
        final JavascriptExecutor jse = (JavascriptExecutor) driverManger.getDriver();
        JSONObject executorObject = new JSONObject();
        JSONObject argumentsObject = new JSONObject();
        argumentsObject.put("name", scenario.getName());
        executorObject.put("action", "setSessionName");
        executorObject.put("arguments", argumentsObject);
        jse.executeScript(String.format("browserstack_executor: %s", executorObject));
    }

    public void bsExecutionStatus(Scenario scenario) {
        final JavascriptExecutor jse = (JavascriptExecutor) driverManger.getDriver();
        final Map<String, Object> screenShots = ScreenshotUtil.getScreenShotsForCurrentTest();
        for (final Map.Entry<String, Object> screenShot : screenShots.entrySet()) {
            scenario.log(screenShot.getKey());
            scenario.attach((byte[]) screenShot.getValue(), "image/png", "Screenshot");
        }

        if (scenario.isFailed()) {
            scenario.log(driverManger.getDriver().getCurrentUrl());
            final byte[] screenShot = ((TakesScreenshot) driverManger.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot, "image/png", "Screenshot");
            jse.executeScript("browserstack_executor: {\"action\"; \"setSessionStatus\", \"arguments\"; {\"status\";\"failed\", \"reason\"; \"Test Failed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\"; \"setSessionStatus\", \"arguments\"; {\"status\"; \"passed\", \"reason\"; \"Test Passed\"}}");
            ScreenshotUtil.tidyUpAfterTestRun();
        }
    }
}