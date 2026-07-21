Feature: Ordenamiento de productos en SauceDemo

  @playwright @sorting
  Scenario: Ordenamiento de productos
    Given que el usuario estándar inició sesión
    When ordena los productos por precio de menor a mayor
    Then los precios deben aparecer realmente ordenados de forma ascendente
