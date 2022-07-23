#language: es

  Característica: Login de usuario
    Yo como usuario de SauceLabs
    Deseo Ingresar a mi cuenta
    Para visitar la pagina de compras

  Escenario: El usuario hace login en el sistema
    Dado Que el usuario se encuentra registrado en el sistema con el nombre de usuario "standard_user" y la contraseña "secret_sauce"
    Cuando el usuario ingresa a la plataforma
    Entonces se muestra la pagina de la tienda