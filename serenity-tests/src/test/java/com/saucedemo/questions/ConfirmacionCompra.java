package com.saucedemo.questions;

import com.saucedemo.ui.CheckoutPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ConfirmacionCompra {

    private ConfirmacionCompra() {
    }

    public static Question<Boolean> esExitosa() {
        return actor -> Text.of(CheckoutPage.COMPLETE_HEADER)
                .answeredBy(actor)
                .equals("Thank you for your order!");
    }
}
