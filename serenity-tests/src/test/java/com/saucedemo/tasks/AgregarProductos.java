package com.saucedemo.tasks;

import com.saucedemo.ui.InventoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.Arrays;
import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AgregarProductos implements Task {
    private final List<String> productos;

    public AgregarProductos(List<String> productos) {
        this.productos = productos;
    }

    public static AgregarProductos alCarrito(String... productos) {
        return instrumented(AgregarProductos.class, Arrays.asList(productos));
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(InventoryPage.TITLE, isVisible()).forNoMoreThan(10).seconds());

        productos.forEach(producto ->
                actor.attemptsTo(Click.on(InventoryPage.ADD_PRODUCT_BUTTON.of(slug(producto))))
        );

    }

    private String slug(String producto) {
        return producto.toLowerCase().replace(" ", "-");
    }
}
