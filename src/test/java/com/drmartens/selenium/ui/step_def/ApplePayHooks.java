package com.drmartens.selenium.ui.step_def;

import com.drmartens.selenium.ui.drivermanager.IOSDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplePayHooks {

    private static final Logger LOG = LoggerFactory.getLogger(ApplePayHooks.class);
    private final IOSDriverManager iosDriverManager = new IOSDriverManager();

    @Before
    public void setUp(Scenario scenario) {
        LOG.info("Starting scenario: {}", scenario.getName());
        try {
            if (iosDriverManager.getDriver() == null) {
                iosDriverManager.getWebdriver();
                LOG.info("iOS driver initialized successfully.");
            } else {
                LOG.info("iOS driver already initialized.");
            }
        } catch (Exception e) {
            LOG.error("Error initializing iOS driver for scenario: {}", scenario.getName(), e);
            throw new RuntimeException("Driver initialization failed", e); // Failing early if initialization fails
        }
    }

    @After
    public void postExecutionActivity(Scenario scenario) {
        try {
            captureExecutionStatus(scenario);
        } catch (Exception e) {
            LOG.error("Error during post-execution activities for scenario: {}", scenario.getName(), e);
        } finally {
            iosDriverManager.quitWebDriver();
            LOG.info("iOS driver quit successfully.");
        }
    }

    private void captureExecutionStatus(Scenario scenario) {
        if (scenario.isFailed()) {
            LOG.warn("Scenario failed: {}", scenario.getName());
            captureScreenshot(scenario);
        } else {
            LOG.info("Scenario passed: {}", scenario.getName());
        }
    }

    private void captureScreenshot(Scenario scenario) {
        try {
            byte[] screenshot = ((TakesScreenshot) iosDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
            LOG.info("Screenshot captured for failed scenario: {}", scenario.getName());
        } catch (WebDriverException e) {
            LOG.error("Error capturing screenshot for scenario: {}", scenario.getName(), e);
        }
    }
}
