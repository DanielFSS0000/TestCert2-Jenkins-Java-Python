package com.saucedemo.tasks;

import com.saucedemo.ui.CartPage;
import com.saucedemo.ui.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CompletarCheckout implements Task {
    private final String nombre;
    private final String apellido;
    private final String codigoPostal;

    public CompletarCheckout(String nombre, String apellido, String codigoPostal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigoPostal = codigoPostal;
    }

    public static CompletarCheckout conDatos(String nombre, String apellido, String codigoPostal) {
        return instrumented(CompletarCheckout.class, nombre, apellido, codigoPostal);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url("https://www.saucedemo.com/cart.html"),
                WaitUntil.the(CartPage.CHECKOUT_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Open.url("https://www.saucedemo.com/checkout-step-one.html"),
                WaitUntil.the(CheckoutPage.FIRST_NAME, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(nombre).into(CheckoutPage.FIRST_NAME),
                Enter.theValue(apellido).into(CheckoutPage.LAST_NAME),
                Enter.theValue(codigoPostal).into(CheckoutPage.POSTAL_CODE),
                Click.on(CheckoutPage.CONTINUE_BUTTON),
                Open.url("https://www.saucedemo.com/checkout-step-two.html"),
                WaitUntil.the(CheckoutPage.FINISH_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(CheckoutPage.FINISH_BUTTON)
        );
    }
}