package com.drmartens.selenium.ui.step_def;

import com.drmartens.selenium.ui.page_objects.ApplePay;
import com.drmartens.selenium.ui.drivermanager.IOSDriverManager;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplePayStepDef {
    private static final Logger LOG = LoggerFactory.getLogger(ApplePayStepDef.class);
    private final IOSDriverManager driverManager = new IOSDriverManager();
    private final ApplePay applePay = new ApplePay(driverManager);

    @Given("the user executes the Apple Pay flow")
    public void theUserExecutesTheApplePayFlow() {
        System.out.println("hello");
        // LOG.info("Starting Apple Pay flow");
        applePay.executeApplePayFlow();
    }
}