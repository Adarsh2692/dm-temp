package com.drmartens.selenium.ui.page_objects;

import com.drmartens.selenium.ui.drivermanager.DriverManger;
import dataProvider.ConfigFileReader;
import dataProvider.UserProperties;
import org.openqa.selenium.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutPage extends DriverManger {
    UserProperties userProperties = new UserProperties();

    public CheckoutPage() {
        PageFactory.initElements(getDriver(), this);
    }


    @FindBy(css = "div[id='applepay-container'] button")
    WebElement applePayOnCheckoutPage;


    public void clickOnPayWithApplePay()
    {
        waitUntilPageLoad();
        sleep(3000);
        scrollUpToViewByJavaScriptExecutor(applePayOnCheckoutPage);
        scrollUpUsingLoop(1);
        sleep(2000);
        clickHiddenElementByJavaScriptExecutor(waitForBtnClickable(applePayOnCheckoutPage));
        waitUntilPageLoad();
    }

    public void enterAddressAndUserDetailsInApplePayWindow()
    {
        waitUntilPageLoad();
        sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("browserstack_executor: {action:applePayDetails," +
                "arguments: {" +
                "shippingDetails: {" +
                "firstName: " + userProperties.getApplePayFirstname() + "," +
                "lastName: " + userProperties.getApplePayLastname() + "," +
                "street: " + userProperties.getApplePayStreet() + "," +
                "postalCode: " + userProperties.getApplePayPostCode() + "," +
                "city: " + userProperties.getApplePayCity() + "," +
                "country: " + userProperties.getApplePayCountry() +
                "}," +
                "billingDetails: {" +
                "firstName: " + userProperties.getApplePayFirstname() + "," +
                "lastName: " + userProperties.getApplePayLastname() + "," +
                "street: " + userProperties.getApplePayStreet() + "," +
                "postalCode: " + userProperties.getApplePayPostCode() + "," +
                "city: " + userProperties.getApplePayCity() + "," +
                "country: " + userProperties.getApplePayCountry() +
                "}," +
                "contact: {" +
                "email: " + userProperties.userNewEmail() + "," +
                "phone: " + enterPhoneNumber() +
                "}" +
                "}}"
        );
    }

    public void confirmApplePayPayment()
    {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript(
                "browserstack_executor: {" +
                        "action: applePay," +
                        "arguments: {" +
                        "confirmPayment: true" +
                        "}" +
                        "}"
        );
    }

    public void enterPasscodeForApplePay()
    {
        // getKeyboard() is deprecated, mentioned in BS support page.
        // Trying work around to enter passcode. If it doesn't work then need to connect with BS team

        // getDriver().getKeyboard().sendKeys("123456");

        Actions actions = new Actions(getDriver());
        actions.sendKeys("123456").perform();
    }

//    public void ifPopupNotAppearing()
//    {
//        AppiumDriver appiumDriver = (AppiumDriver) getDriver();
//        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
//        wait.until(new ExpectedCondition<Boolean>()
//        {
//            public Boolean apply(WebDriver driver)
//            {
//                String title = driver.getTitle();
//                return title != null && title.matches(".*Apple.*");
//            }
//        });
//
//        // Switch context to NATIVE_APP
//        appiumDriver.context("NATIVE_APP");
//
//        // Find the button with name "Apple Pay" and click it
//        WebElement button = appiumDriver.findElement(By.name("Apple Pay"));
//        button.click();
//    }


}