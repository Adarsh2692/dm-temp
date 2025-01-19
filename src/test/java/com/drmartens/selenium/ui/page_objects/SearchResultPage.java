package com.drmartens.selenium.ui.page_objects;

import com.drmartens.selenium.ui.drivermanager.DriverManger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends DriverManger {
    HomePage homePage = new HomePage();

    public SearchResultPage () {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(id = "js-site-search-input")
    public WebElement searchBox;
    @FindBy(css = ".search-mobile #js-site-search-input")
    public WebElement searchBoxMobile;
    @FindBy(css = ".g-search-bar-icon__header-search")
    public WebElement searchIcon;

    @FindBy(css = ".product__list__item__name.atb-hover-item")
    List<WebElement> productTitles;
    @FindBy(css = "button.needsclick.klaviyo-close-form.kl-private-reset-css-Xuajs1")
    WebElement newRegistrationPopupCloseBtn;

    public void searchAndClickAnItem(String product) {
        if(getCurrentURL().contains("https://www.drmartens.com/")){
            try{
                if(isDisplayed(newRegistrationPopupCloseBtn)){
                    newRegistrationPopupCloseBtn.click();
                }
            }catch(Exception e){
                System.out.println("Exception: newRegistrationPopup is not displayed");
            }
        }
        homePage.seeDrAirWairMartensLogo();
        searchAProduct(product);
        clickOnProduct(product);
        waitUntilPageLoad();
    }

    public void searchAProduct(String product) {
        if (product.equalsIgnoreCase("invalid Product"))
            product = "ghhh";

        try {
            if (isMobile()) {
                waitUntilElementsVisible(searchIcon).click();
                waitForBtnClickable(searchBoxMobile).click();
                waitUntilElementsVisible(searchBoxMobile).sendKeys(product, Keys.ENTER);
            } else {
                waitForBtnClickable(searchBox).click();
                waitUntilElementsVisible(searchBox).sendKeys(product, Keys.ENTER);
            }
            waitUntilPageLoad();
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
    }

    public void clickOnProduct(String itemNumber)
    {
        sleep(2000);
        waitUntilPageLoad();
        clickHiddenElementByJavaScriptExecutor(waitUntilAllElementsVisible(productTitles)
                .stream()
                .filter(item -> item.getAttribute("href").contains(itemNumber))
                .findAny()
                .orElseThrow(() -> new NotFoundException("Product not found")));
    }

    public void productSearch(String product) {
        if (product.equalsIgnoreCase("invalid Product")) {
            searchAProduct("ghhh");
        } else {
            searchAndClickAnItem(product);
        }
    }


}