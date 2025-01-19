package com.drmartens.selenium.ui.step_def;

import com.drmartens.selenium.ui.drivermanager.DriverManger;
import com.drmartens.selenium.ui.page_objects.OrderSuccessPage;
import io.cucumber.java.en.Then;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrderSuccessPageStepDef extends DriverManger {

    OrderSuccessPage orderSuccessPage = new OrderSuccessPage();

    @Then("user should see order placed successfully message")
    public void userShouldSeeOrderPlacedSuccessfullyMessage() {
        assertThat(orderSuccessPage.getOrderSuccessMessage(), anyOf(containsString("THANK YOU FOR YOUR ORDER"),
                containsString("GRAZIE PER IL TUO ORDINE"), containsString("MERCI POUR VOTRE COMMANDE")));
    }

    @Then("an Order should be placed successfully.")
    public void anOrderShouldBePlacedSuccessfully() {
        assertThat(orderSuccessPage.getOrderSuccessMessage(), anyOf(containsString("THANK YOU FOR YOUR ORDER"),
                containsString("GRAZIE PER IL TUO ORDINE"), containsString("MERCI POUR VOTRE COMMANDE"),
                containsString("DANKE FÜR DEINE BESTELLUNG"), containsString("KIITOS TILAUKSESTASI"),
                containsString("BEDANKT VOOR JE BESTELLING")));
    }
}