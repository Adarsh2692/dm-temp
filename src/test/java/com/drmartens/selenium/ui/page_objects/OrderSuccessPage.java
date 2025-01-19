package com.drmartens.selenium.ui.page_objects;

import com.drmartens.selenium.ui.drivermanager.DriverManger;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class OrderSuccessPage extends DriverManger {

    public OrderSuccessPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(css = ".checkout-success__body__headline-box")
    WebElement orderSuccessMessage;

    public String getOrderSuccessMessage() {
        waitUntilPageLoad();
        return waitUntilElementsVisible(orderSuccessMessage).getText();
    }
}