package com.drmartens.selenium.ui.drivermanager;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.MutableCapabilities;
import java.net.URL;

public class IOSDriverManager {

    private static final ThreadLocal<IOSDriver> driver = ThreadLocal.withInitial(() -> null);

    public IOSDriver getDriver() {
        return driver.get();
    }

    public void quitWebDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    public IOSDriverManager getWebdriver() {
        MutableCapabilities capabilities = new MutableCapabilities();
        try {
            driver.set(new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities));
        } catch (Exception e) {
            System.out.println("Exception in remotedriver: " + e);
        }
        return this;
    }

    public void close() {
        if (getDriver() != null) {
            getDriver().close();
        }
    }
}
