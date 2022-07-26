package com.sofka.juancarlosmaya.stepdefinition.saucesale;

import com.sofka.juancarlosmaya.model.GridItem;
import com.sofka.juancarlosmaya.page.login.LoginFormPage;
import com.sofka.juancarlosmaya.page.shop.ShoppingCartPage;
import com.sofka.juancarlosmaya.page.shop.StoreDashboardPage;
import com.sofka.juancarlosmaya.stepdefinition.setup.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static com.sofka.juancarlosmaya.util.dictionary.DEFAULT_EXPLICIT_WAIT;
import static com.sofka.juancarlosmaya.util.dictionary.IS_EXPLICIT_WAIT;

public class SaleStepDefinition extends Configuration {
    private static final Logger LOGGER = Logger.getLogger(SaleStepDefinition.class);
    private String usuario;
    private String contrasena;
    private LoginFormPage loginFormPage;
    private StoreDashboardPage storeDashboardPage;
    private ShoppingCartPage shoppingCartPage;
    private List<GridItem> shopItemsList;


    @Before
    public void setup() {
        setUpWebDriver();
        setUpLog4j2();
        generalSetUp();
    }

    @After
    public void finishedTest() {
        closeDriver();
        quitDriver();
    }

    @Dado("Que el usuario se encuentra registrado en el sistema con el nombre de usuario {string} y la contraseña {string} y hace login")
    public void queElUsuarioSeEncuentraRegistradoEnElSistemaConElNombreDeUsuarioYLaContrasenaYHaceLogin(String usuario, String contrasena) {
        loginFormPage = new LoginFormPage(driver, DEFAULT_EXPLICIT_WAIT, IS_EXPLICIT_WAIT);
        storeDashboardPage = new StoreDashboardPage(driver, DEFAULT_EXPLICIT_WAIT, IS_EXPLICIT_WAIT);
        shoppingCartPage = new ShoppingCartPage(driver, DEFAULT_EXPLICIT_WAIT, IS_EXPLICIT_WAIT);

        try {
            this.usuario = usuario;
            this.contrasena = contrasena;
            loginFormPage.fillLoginWith(usuario, contrasena);

        } catch (Exception e) {
            Assertions.fail();
            quitDriver();
            LOGGER.error("Error message SALE DADO:" + e.getMessage(), e);
        }
    }


    @Cuando("el usuario ingresa a la plataforma, añade el primer articulo del inventario al carrito, y hace checkout")
    public void elUsuarioIngresaALaPlataformaAñadeElPrimerArticuloDelInventarioAlCarritoYHaceCheckout() {
        int listSize = 0;
        try {
            String firstName = "Juan";
            String lastName = "Maya";
            String zipCode = "050044";

            shopItemsList = new ArrayList<GridItem>();
            shopItemsList = storeDashboardPage.addAnItemToShoppingCart(1);
            shopItemsList = storeDashboardPage.addAnItemToShoppingCart(2);
            shopItemsList = storeDashboardPage.addAnItemToShoppingCart(4);
            storeDashboardPage.goToCartCheckout();
            shoppingCartPage.fillCartInfo(firstName, lastName, zipCode);
            shoppingCartPage.doCheckout();

        } catch (Exception e) {
            Assertions.fail();
            quitDriver();
            LOGGER.error("Error message SALE CUANDO:" + e.getMessage(), e);
        }

    }

    @Entonces("se verifica que el precio es correcto y se realiza la compra")
    public void seVerificaQueElPrecioEsCorrectoYSeRealizaLaCompra() {
        Boolean  result;
        try {
            result = shoppingCartPage.verifyCheckout(shopItemsList);
            Assertions.assertEquals(true, result);
        } catch (Exception e) {
            Assertions.fail();
            quitDriver();
            LOGGER.error("Error message SALE ENTONCES:" + e.getMessage(), e);
        }
    }
}
