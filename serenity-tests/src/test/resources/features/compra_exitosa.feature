# language: es
@serenity @compra
Característica: Compra de productos en SauceDemo

  Escenario: Compra completa exitosa
    Dado que el usuario estándar se encuentra en la página de inicio de sesión
    Cuando inicia sesión con credenciales válidas
    Y agrega productos al carrito
    Y completa el proceso de checkout
    Entonces debe visualizar la confirmación de compra exitosa
