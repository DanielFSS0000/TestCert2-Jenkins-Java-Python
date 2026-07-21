package com.saucedemo.stepdefinitions;

import com.saucedemo.questions.ConfirmacionCompra;
import com.saucedemo.questions.MensajeLogin;
import com.saucedemo.tasks.AgregarProductos;
import com.saucedemo.tasks.CompletarCheckout;
import com.saucedemo.tasks.IniciarSesion;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.is;

public class SauceDemoSteps {

    @Dado("que el usuario estándar se encuentra en la página de inicio de sesión")
    public void usuarioEstandarEnLogin() {
        theActorCalled("Usuario estándar").wasAbleTo(IniciarSesion.abrirPagina());
    }

    @Cuando("inicia sesión con credenciales válidas")
    public void iniciaSesionValida() {
        theActorInTheSpotlight().attemptsTo(
                IniciarSesion.conCredenciales("standard_user", "secret_sauce")
        );
    }

    @Cuando("agrega productos al carrito")
    public void agregaProductosAlCarrito() {
        theActorInTheSpotlight().attemptsTo(
                AgregarProductos.alCarrito("Sauce Labs Backpack", "Sauce Labs Bike Light")
        );
    }

    @Cuando("completa el proceso de checkout")
    public void completaCheckout() {
        theActorInTheSpotlight().attemptsTo(
                CompletarCheckout.conDatos("Daniel", "QA", "110111")
        );
    }

    @Entonces("debe visualizar la confirmación de compra exitosa")
    public void validaConfirmacionCompra() {
        theActorInTheSpotlight().should(
                seeThat(ConfirmacionCompra.esExitosa(), is(true))
        );
    }

    @Dado("que el usuario bloqueado se encuentra en la página de inicio de sesión")
    public void usuarioBloqueadoEnLogin() {
        theActorCalled("Usuario bloqueado").wasAbleTo(IniciarSesion.abrirPagina());
    }

    @Cuando("intenta iniciar sesión con sus credenciales")
    public void intentaLoginBloqueado() {
        theActorInTheSpotlight().attemptsTo(
                IniciarSesion.conCredenciales("locked_out_user", "secret_sauce")
        );
    }

    @Entonces("debe visualizar el mensaje que indica que el usuario está bloqueado")
    public void validaMensajeUsuarioBloqueado() {
        theActorInTheSpotlight().should(
                seeThat(MensajeLogin.usuarioBloqueado(), is(true))
        );
    }
}
