Feature: Validación de precios en SauceDemo

  @playwright @prices
  Scenario: Validación de precios
    Given que el usuario estándar inició sesión
    When consulta los productos y agrega uno al carrito
    Then el precio mostrado en el carrito debe coincidir con el precio del inventario
