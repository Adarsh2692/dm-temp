package com.drmartens.selenium.ui.page_objects;

import com.drmartens.selenium.ui.drivermanager.IOSDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.ios.IOSDriver;

import java.time.Duration;

public class ApplePay {
    private static final Logger LOG = LoggerFactory.getLogger(ApplePay.class);
    private final IOSDriverManager driverManager;
    private final WebDriverWait wait;

    public ApplePay(IOSDriverManager driverManager) {
        this.driverManager = driverManager;
        this.wait = new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(10));
    }

    public void executeApplePayFlow() {
        try {
            // Navigate to the product page
            navigateToUrl("https://www.drmartens.com/uk/en_gb/1460-smooth-leather-lace-up-boots-black/p/11822006");

            // Accept cookies
            clickElement(By.xpath("//*[@id='onetrust-accept-btn-handler']"), "Accept Cookies");

            // Execute BrowserStack executor for Apple Pay details
            executeApplePayDetails();

            Thread.sleep(5000); // Wait for the Apple Pay details to process

            // Add to cart
            clickElement(By.xpath("//*[@id='addToCartButton']"), "Add to Cart");

            // Select size
            clickElement(By.xpath("//*[@id='sizeSelectorPopupList']/li[1]/button"), "Select Size");

            // Click add to cart again
            clickElement(By.xpath("//*[@id='addToCartButton']"), "Add to Cart Again");

            Thread.sleep(5000);

            // Navigate to the cart
            navigateToUrl("https://www.drmartens.com/uk/en_gb/cart");

            // Find and scroll to the Apple Pay container with adjusted offset
            scrollToElementWithOffset(By.cssSelector("#applepay-container"), "Apple Pay Container");

            Thread.sleep(5000);

            // Perform tap action using TouchAction
            tapOnCoordinates(100, 150);

            Thread.sleep(5000); // Wait before executing the payment confirmation

            // Execute BrowserStack executor for Apple Pay payment confirmation
            confirmApplePay();

        } catch (Exception e) {
            LOG.error("Error executing Apple Pay flow", e);
        }
    }

    private void navigateToUrl(String url) {
        try {
            driverManager.getDriver().get(url);
            LOG.info("Navigated to URL: {}", url);
        } catch (Exception e) {
            LOG.error("Failed to navigate to URL: {}", url, e);
        }
    }

    private void clickElement(By locator, String elementName) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            LOG.info("Clicked on: {}", elementName);
        } catch (TimeoutException e) {
            LOG.warn("Element not found or not clickable: {}", elementName);
        } catch (Exception e) {
            LOG.error("Error clicking element: {}", elementName, e);
        }
    }

    private void scrollToElementWithOffset(By locator, String elementName) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

            // Dynamically calculate the height of any fixed headers
            String script =
                "const element = arguments[0];" +
                "const rect = element.getBoundingClientRect();" +
                "const headerHeight = document.querySelector('header')?.offsetHeight || 0;" +
                "window.scrollTo({top: rect.top + window.scrollY - headerHeight - 50, behavior: 'smooth'});";

            ((JavascriptExecutor) driverManager.getDriver()).executeScript(script, element);
            LOG.info("Scrolled to: {} with dynamic offset adjustment", elementName);
        } catch (Exception e) {
            LOG.error("Error scrolling to element: {}", elementName, e);
        }
    }

    private void tapOnCoordinates(int x, int y) {
        try {
            IOSDriver iosDriver = (IOSDriver) driverManager.getDriver();
            TouchAction<?> touchAction = new TouchAction<>(iosDriver);
            touchAction.tap(PointOption.point(x, y)).perform();
            LOG.info("Executed tap action at coordinates: ({}, {})", x, y);
        } catch (Exception e) {
            LOG.error("Error performing tap action at coordinates: ({}, {})", x, y, e);
        }
    }

    private void executeApplePayDetails() {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driverManager.getDriver();
            jsExecutor.executeScript("browserstack_executor: {\"action\":\"applePayDetails\", " +
                "\"arguments\": {" +
                "  \"shippingDetails\": {" +
                "      \"firstName\": \"John\"," +
                "      \"lastName\": \"Doe\"," +
                "      \"street\": \"123 Elm Street\"," +
                "      \"addressLine2\": \"Suite 456\"," +
                "      \"city\": \"London\"," +
                "      \"state\": \"England\"," +
                "      \"zip\": \"SW1A 1AA\"," +
                "      \"province\": \"Greater London\"," +
                "      \"islandName\": \"N/A\"," +
                "      \"country\": \"United Kingdom\"" +
                "  }," +
                "  \"billingDetails\": {" +
                "      \"firstName\": \"John\"," +
                "      \"lastName\": \"Doe\"," +
                "      \"street\": \"123 Elm Street\"," +
                "      \"addressLine2\": \"Suite 456\"," +
                "      \"city\": \"London\"," +
                "      \"state\": \"England\"," +
                "      \"zip\": \"SW1A 1AA\"," +
                "      \"province\": \"Greater London\"," +
                "      \"islandName\": \"N/A\"," +
                "      \"country\": \"United Kingdom\"" +
                "  }," +
                "  \"contact\": {" +
                "      \"email\": \"john.doe@example.com\"," +
                "      \"phone\": \"+441234567890\"" +
                "  }" +
                "}}");
            LOG.info("Executed BrowserStack Apple Pay details setup.");
        } catch (Exception e) {
            LOG.error("Error executing Apple Pay details setup", e);
        }
    }

    private void confirmApplePay() {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driverManager.getDriver();
            jsExecutor.executeScript("browserstack_executor: {\"action\":\"applePay\", " +
                "\"arguments\": {" +
                "  \"confirmPayment\": \"true\"" +
                "}}");
            LOG.info("Executed BrowserStack Apple Pay payment confirmation.");
        } catch (Exception e) {
            LOG.error("Error executing Apple Pay payment confirmation", e);
        }
    }
}
