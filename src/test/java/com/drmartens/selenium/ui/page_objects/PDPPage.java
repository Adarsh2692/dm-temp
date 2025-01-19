package com.drmartens.selenium.ui.page_objects;

import com.drmartens.selenium.ui.drivermanager.DriverManger;
import dataProvider.ConfigFileReader;
import dataProvider.SiteTextProperties;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;
import java.util.stream.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PDPPage extends DriverManger {

    public PDPPage () {
        PageFactory.initElements(getDriver(), this);
    }







    @FindBy(css = "#sizeSelector button")
    List<WebElement> prodSizes;

    @FindBy(id = "addToCartButton")
    WebElement addToBagButton;

    @FindBy(css = "a[class*='add-to-cart-button']")
    WebElement checkoutButton;


    public void clickOnFirstAvailableSize() {
        if (isIphone())
            sleep(3000);
        waitUntilPageLoad();
        scrollByJavaScriptExecutor(0, 450);
        sleep(3000);
        for (WebElement prodSize : prodSizes) {
            if (prodSize.getAttribute("class").contains("stock-inStock")) {
                click(prodSize);
                break;
            }
        }
    }

    public void clickOnFirstUnavailableSize() {
        if (isIphone())
            sleep(3000);
        waitUntilPageLoad();
        scrollByJavaScriptExecutor(0, 450);
        sleep(3000);
        for (WebElement prodSize : prodSizes) {
            if (prodSize.getAttribute("class").contains("stock-outStock"))
            {
                if (isMobile())
                    clickHiddenElementByJavaScriptExecutor(waitForBtnClickable(prodSize));
                else
                    waitForBtnClickable(prodSize).click();
                break;
            }
        }
    }



    public void clickOnAddToBagButton() {
        clickHiddenElementByJavaScriptExecutor(waitForBtnClickable(addToBagButton));
    }


    public void clickOnCheckOutButton() {
        waitUntilPageLoad();
        if (!isMobile())
            waitUntilElementsVisible(checkoutButton);
        if (isMobile() && isFranceLocale()) {
            clickHiddenElementByJavaScriptExecutor(waitForBtnClickable(checkoutButton));
        } else {
            click(checkoutButton);
        }
        waitUntilPageLoad();
        sleep(4000);
    }
}