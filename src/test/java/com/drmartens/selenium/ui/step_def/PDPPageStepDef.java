package com.drmartens.selenium.ui.step_def;

import com.drmartens.selenium.ui.page_objects.PDPPage;
import dataProvider.UserProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class PDPPageStepDef {
    PDPPage pdpPage = new PDPPage();
    public static String actualSizeGuideHeadingText;

    public static String actualDeliveryReturnsReadMoreClassValue;

    @Then("size guide banner should be displayed")
    public void sizeGuideBannerShouldBeDisplayed() {
        assertThat("Text not found", actualSizeGuideHeadingText, anyOf(containsString("SIZE GUIDE"),
                containsString("GUIDE DES TAILLES"), containsString("GUIDA ALLE TAGLIE")));
    }

    @Then("delivery and returns information should be displayed")
    public void deliveryAndReturnsInformationShouldBeDisplayed() {
        assertThat(actualDeliveryReturnsReadMoreClassValue, containsString("hide"));
    }
    @And("user clicks on first OOS size")
    public void userClicksOnFirstOOSSize() {
        pdpPage.clickOnFirstUnavailableSize();
    }



    @And("user click on Checkout Button")
    public void userClickOnCheckoutButton() {
        pdpPage.clickOnCheckOutButton();
    }
}
