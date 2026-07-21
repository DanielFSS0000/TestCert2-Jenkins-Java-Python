package com.saucedemo.tasks;

import com.saucedemo.ui.LoginPage;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IniciarSesion {

    private IniciarSesion() {
    }

    public static Task abrirPagina() {
        return Task.where("{0} abre la pÃ¡gina de SauceDemo",
                Open.url("https://www.saucedemo.com/")
        );
    }

    public static Task conCredenciales(String usuario, String clave) {
        return instrumented(LoginConCredenciales.class, usuario, clave);
    }

    public static class LoginConCredenciales implements Task {
        private final String usuario;
        private final String clave;

        public LoginConCredenciales(String usuario, String clave) {
            this.usuario = usuario;
            this.clave = clave;
        }

        @Override
        public <T extends net.serenitybdd.screenplay.Actor> void performAs(T actor) {
            actor.attemptsTo(
                    WaitUntil.the(LoginPage.USERNAME, isVisible()).forNoMoreThan(10).seconds(),
                    Enter.theValue(usuario).into(LoginPage.USERNAME),
                    Enter.theValue(clave).into(LoginPage.PASSWORD),
                    Click.on(LoginPage.LOGIN_BUTTON)
            );
        }
    }
}
