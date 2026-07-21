package com.saucedemo.questions;

import com.saucedemo.ui.LoginPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class MensajeLogin {

    private MensajeLogin() {
    }

    public static Question<Boolean> usuarioBloqueado() {
        return actor -> Text.of(LoginPage.ERROR_MESSAGE)
                .answeredBy(actor)
                .contains("Sorry, this user has been locked out");
    }
}
