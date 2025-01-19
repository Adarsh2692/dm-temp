package com.drmartens.selenium.ui.step_def;

import com.drmartens.selenium.ui.page_objects.CheckoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutPageStepDef {
    CheckoutPage checkoutPage = new CheckoutPage();


    @And("user clicks on 'Checkout with Apply Pay'")
    public void userClicksOnCheckoutWithApplyPay(){
        checkoutPage.clickOnPayWithApplePay();
    }

    @Then("user places the order with Apple pay")
    public void userPlacesTheOrderWithApplePay() {
        checkoutPage.enterAddressAndUserDetailsInApplePayWindow();
        checkoutPage.confirmApplePayPayment();
        checkoutPage.enterPasscodeForApplePay();
    }
}
