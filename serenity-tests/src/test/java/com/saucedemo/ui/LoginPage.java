package com.saucedemo.ui;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {

    public static final Target USERNAME = Target.the("campo usuario")
            .locatedBy("[data-test='username']");
    public static final Target PASSWORD = Target.the("campo contraseña")
            .locatedBy("[data-test='password']");
    public static final Target LOGIN_BUTTON = Target.the("botón de login")
            .locatedBy("[data-test='login-button']");
    public static final Target ERROR_MESSAGE = Target.the("mensaje de error")
            .locatedBy("[data-test='error']");

    private LoginPage() {
    }
}
