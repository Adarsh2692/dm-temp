package com.drmartens.selenium.ui.step_def;

import com.drmartens.selenium.ui.page_objects.HomePage;
import com.drmartens.selenium.ui.page_objects.PLPPage;
import dataProvider.SiteTextProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class HomePageStepDef
{
    HomePage homePage = new HomePage();

    public String actualURL;

    @Given("user choose shippingTo {string} and {string}")
    public void userChooseShippingToAnd(String country, String language) {
        homePage.setCountrySelection(country);
        homePage.setCountriesLanguage(language);
        homePage.clickGoShoppingButton();
        homePage.waitUntilPageLoad();
        homePage.setCookieAccept();
        actualURL = homePage.getCurrentURL();
        homePage.clickSignUpPopUP();
    }





}