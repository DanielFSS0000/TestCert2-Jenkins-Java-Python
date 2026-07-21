# language: es
@serenity @login
Característica: Inicio de sesión en SauceDemo

  Escenario: Inicio de sesión con usuario bloqueado
    Dado que el usuario bloqueado se encuentra en la página de inicio de sesión
    Cuando intenta iniciar sesión con sus credenciales
    Entonces debe visualizar el mensaje que indica que el usuario está bloqueado
