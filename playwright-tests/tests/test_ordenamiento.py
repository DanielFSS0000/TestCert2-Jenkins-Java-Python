import pytest
from pytest_bdd import given, scenario, then, when

from questions.inventory_questions import prices_are_sorted_ascending
from tasks.login import login_as_standard_user
from tasks.sorting import sort_products_by_price_low_to_high


@pytest.mark.playwright
@pytest.mark.sorting
@scenario("../features/ordenamiento.feature", "Ordenamiento de productos")
def test_ordenamiento_de_productos():
    pass


@given("que el usuario estándar inició sesión")
def usuario_estandar_inicio_sesion(page):
    login_as_standard_user(page)


@when("ordena los productos por precio de menor a mayor")
def ordena_por_precio_menor_a_mayor(page):
    sort_products_by_price_low_to_high(page)


@then("los precios deben aparecer realmente ordenados de forma ascendente")
def precios_ordenados_ascendente(page):
    assert prices_are_sorted_ascending(page)
