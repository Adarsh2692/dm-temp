package com.drmartens.selenium.ui.page_objects;

import static org.junit.jupiter.api.Assertions.*;

import com.drmartens.selenium.ui.drivermanager.DriverManger;
import dataProvider.ConfigFileReader;
import dataProvider.ProductProperties;
import dataProvider.SiteTextProperties;
import dataProvider.UserProperties;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class HomePage extends DriverManger {



    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }
    @FindBy(css = ".country-select .drop-down-icon")
    WebElement countryPopUpSelector;
    @FindBy(className = "country-flag")
    WebElement countryFlag;
    @FindBy(css = ".country-name")
    List<WebElement> countriesNames;
    @FindBy(css = ".radio-inline")
    List<WebElement> countriesLanguage;
    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement cookieAccept;
    @FindBy(xpath = "//*[@class='dm-logo']//img")
    WebElement drAirWairMartensLogo;
    @FindBy(id = "dismissbutton2")
    WebElement signUpPercentageOffPopup;
    @FindBy(id = "country-select-submit")
    WebElement goShoppingButton;


    public void clickGoShoppingButton() {
        waitForBtnClickable(goShoppingButton).click();
        //Good to wait for page load to reduce failures due to delay in homepage load
        waitUntilPageLoad();
    }

    public void clickSignUpPopUP() {
        try {
            sleep(2000); //sometimes popup is coming with delay
            getDriver().switchTo().frame("attentive_creative");
            waitUntilElementsVisibleWithTime(signUpPercentageOffPopup, 10).click();
            getDriver().switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("10% banner is not displayed");
        }
    }

    public void setCookieAccept() {
        try {
            //Using JS click for mobile to minimize the click failures
            waitUntilElementsVisibleWithTime(cookieAccept, 10);
            if (isMobile()) {
                clickHiddenElementByJavaScriptExecutor(cookieAccept);
            } else {
                cookieAccept.click();
            }
        } catch (Exception e) {
            System.out.println("A cookie consent banner is not displayed");
        }
    }

    public void setCountrySelection(String country) {
        waitUntilPageLoad();
        waitUntilElementsVisible(countryFlag);             // Wait for home page to get load

        sleep(2000);
        click(countryPopUpSelector);
        sleep(2000);
        try {
            for (WebElement countryName : countriesNames) {
                String countryNameText = countryName.getText();
                if (countryNameText.equalsIgnoreCase(country)) {
                    waitForBtnClickable(countryName).click();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        } finally {
            setLocale(country);       // Setting country for global access
            System.out.println("Set default country to '" + getLocale() + "'");
        }
    }
    public boolean seeDrAirWairMartensLogo() {
        waitUntilPageLoad();
        sleep(500);
        waitUntilElementsVisible(drAirWairMartensLogo);
        return isDisplayed(drAirWairMartensLogo);
    }

    public void setCountriesLanguage(String language) {
        for (WebElement countryLanguage : countriesLanguage) {
            String countryLanguageText = countryLanguage.getText();
            if (countryLanguageText.equalsIgnoreCase(language)) {
                countryLanguage.click();
            }
        }
    }

}