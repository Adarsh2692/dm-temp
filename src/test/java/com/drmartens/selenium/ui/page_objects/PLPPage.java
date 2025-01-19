package com.drmartens.selenium.ui.page_objects;

import com.drmartens.selenium.ui.drivermanager.DriverManger;
import com.drmartens.selenium.ui.utils.GenerateNumber;
import dataProvider.ProductProperties;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class PLPPage extends DriverManger {


    PDPPage pdpPage = new PDPPage();
    SearchResultPage searchResultPage = new SearchResultPage();

    ProductProperties productProperties = new ProductProperties();


    public PLPPage () {
        PageFactory.initElements(getDriver(), this);
    }







    public void addAnItemToCartAndNavigateToCartPage()
    {
        searchResultPage.productSearch(productProperties.getTestProduct());
        pdpPage.clickOnFirstAvailableSize();
        pdpPage.clickOnAddToBagButton();
        pdpPage.clickOnCheckOutButton();
    }






    }









