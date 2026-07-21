package com.saucedemo.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class InventoryPage {

    public static final Target TITLE = Target.the("titulo de productos")
            .locatedBy("[data-test='title']");
    public static final Target ADD_PRODUCT_BUTTON = Target.the("boton agregar producto {0}")
            .locatedBy("[data-test='add-to-cart-{0}']");
    public static final Target CART_LINK = Target.the("enlace del carrito")
            .located(By.cssSelector("a.shopping_cart_link"));

    private InventoryPage() {
    }
}