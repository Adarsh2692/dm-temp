package com.drmartens.selenium.ui.step_def;

import com.drmartens.selenium.ui.page_objects.HomePage;
import com.drmartens.selenium.ui.page_objects.PLPPage;
import com.drmartens.selenium.ui.page_objects.SearchResultPage;
import dataProvider.ProductProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class PLPPageStepDef {

    PLPPage plpPage = new PLPPage();


    @And("user add an item to cart and navigate to cart page")
    public void addAnItemToCartAndNavigateToCartPage() {
        plpPage.addAnItemToCartAndNavigateToCartPage();
    }

}