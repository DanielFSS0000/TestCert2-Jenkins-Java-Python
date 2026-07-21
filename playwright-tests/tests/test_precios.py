import pytest
from pytest_bdd import given, scenario, then, when

from questions.cart_questions import cart_product_matches
from tasks.cart import capture_first_product_and_add_to_cart
from tasks.login import login_as_standard_user


@pytest.mark.playwright
@pytest.mark.prices
@scenario("../features/precios.feature", "Validación de precios")
def test_validacion_de_precios():
    pass


@given("que el usuario estándar inició sesión")
def usuario_estandar_inicio_sesion(page):
    login_as_standard_user(page)


@when("consulta los productos y agrega uno al carrito", target_fixture="selected_product")
def consulta_productos_y_agrega_uno(page):
    return capture_first_product_and_add_to_cart(page)


@then("el precio mostrado en el carrito debe coincidir con el precio del inventario")
def precio_carrito_coincide(page, selected_product):
    assert cart_product_matches(page, selected_product)
