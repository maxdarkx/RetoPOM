#language: es
Característica: Compras en la pagina
  Yo como usuario
  Deseo ingresar a la pagina de SauceLabs
  para comprar items de la pagina

  Escenario: El usuario agrega un item al carrito y lo compra
    Dado Que el usuario se encuentra registrado en el sistema con el nombre de usuario "standard_user" y la contraseña "secret_sauce" y hace login
    Cuando el usuario ingresa a la plataforma, añade el primer articulo del inventario al carrito, y hace checkout
    Entonces se verifica que el precio es correcto y se realiza la compra

  Escenario: El usuario agrega dos items al azar al carrito y los compra
    Dado Que el usuario se encuentra registrado en el sistema con el nombre de usuario "standard_user" y la contraseña "secret_sauce" y hace login
    Cuando el usuario ingresa a la plataforma, añade dos articulos del inventario al carrito, y hace checkout
    Entonces se verifica que el precio es correcto y se realiza la compra

    Escenario: El usuario agrega todos los items de la lista al carrito y los compra
      Dado Que el usuario se encuentra registrado en el sistema con el nombre de usuario "standard_user" y la contraseña "secret_sauce" y hace login
      Cuando el usuario ingresa a la plataforma, añade todos los articulos del inventario al carrito, y hace checkout
      Entonces se verifica que el precio es correcto y se realiza la compra
