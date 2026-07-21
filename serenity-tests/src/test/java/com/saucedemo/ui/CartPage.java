package com.saucedemo.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CartPage {

    public static final Target CHECKOUT_BUTTON = Target.the("botón checkout")
            .locatedBy("[data-test='checkout']");

    private CartPage() {
    }
}
