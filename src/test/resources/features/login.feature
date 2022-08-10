#language: es

Característica: Login de usuario
  Yo como usuario de SauceLabs
  Deseo Ingresar a mi cuenta
  Para visitar la pagina de compras

  @Login
  Escenario: El usuario hace login en el sistema
    Dado Que el usuario se encuentra registrado en el sistema con el nombre de usuario "standard_user" y la contraseña "secret_sauce"
    Cuando el usuario ingresa a la plataforma
    Entonces se muestra la pagina de la tienda

  @Logout
  Escenario: El usuario hace login y logout del sistema
    Dado Que el usuario se encuentra registrado en el sistema con el nombre de usuario "standard_user" y la contraseña "secret_sauce"
    Cuando el usuario ingresa a la plataforma
    Y hace logout
    Entonces  se muestra la pagina de login

  @incorrectLogin
  Escenario: El usuario hace login con credenciales incorrectas
    Dado Que el usuario se encuentra registrado en el sistema con el nombre de usuario "standard_user" y la contraseña "original_sauce"
    Cuando el usuario intenta ingresar a la plataforma, pero se da cuenta que su contraseña esta incorrecta
    Entonces se muestra un mensaje de error