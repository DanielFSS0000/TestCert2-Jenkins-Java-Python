package com.saucedemo.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutPage {

    public static final Target FIRST_NAME = Target.the("nombre")
            .locatedBy("[data-test='firstName']");
    public static final Target LAST_NAME = Target.the("apellido")
            .locatedBy("[data-test='lastName']");
    public static final Target POSTAL_CODE = Target.the("código postal")
            .locatedBy("[data-test='postalCode']");
    public static final Target CONTINUE_BUTTON = Target.the("botón continuar")
            .locatedBy("[data-test='continue']");
    public static final Target FINISH_BUTTON = Target.the("botón finalizar")
            .locatedBy("[data-test='finish']");
    public static final Target COMPLETE_HEADER = Target.the("confirmación de compra")
            .locatedBy("[data-test='complete-header']");

    private CheckoutPage() {
    }
}
