package com.sofka.juancarlosmaya.stepdefinitions;

import com.sofka.juancarlosmaya.forms.login.LoginForm;
import com.sofka.juancarlosmaya.models.GridItem;
import com.sofka.juancarlosmaya.pages.login.LoginFormPage;
import com.sofka.juancarlosmaya.pages.shop.ShoppingCartPage;
import com.sofka.juancarlosmaya.pages.shop.StoreDashboardPage;
import com.sofka.juancarlosmaya.stepdefinitions.setup.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static com.sofka.juancarlosmaya.utils.dictionary.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SaleStepDefinition extends Configuration {
    private static final Logger LOGGER = Logger.getLogger(SaleStepDefinition.class);
    private String usuario;
    private String contrasena;

    @Steps(shared = true)
    private LoginFormPage loginFormPage;

    @Steps(shared = true)
    private StoreDashboardPage storeDashboardPage;

    @Steps(shared = true)
    private ShoppingCartPage shoppingCartPage;

    @Steps(shared = true)
    private List<GridItem> shopItemsList;


    @Before
    public void setup() {
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
        loginFormPage = new LoginFormPage();
        storeDashboardPage = new StoreDashboardPage();
        shoppingCartPage = new ShoppingCartPage();

        try {
            this.usuario = usuario;
            this.contrasena = contrasena;
            loginFormPage.fillLoginWith(usuario, contrasena);

        } catch (Exception e) {
            Assertions.fail();
            LOGGER.error("Error message SALE DADO:" + e.getMessage(), e);
        }
    }


    @Cuando("el usuario ingresa a la plataforma, añade el primer articulo del inventario al carrito, y hace checkout")
    public void elUsuarioIngresaALaPlataformaAñadeElPrimerArticuloDelInventarioAlCarritoYHaceCheckout() {
        int listSize = 0;
        shopItemsList = new ArrayList<GridItem>();
        try {
            shopItemsList = storeDashboardPage.addAnItemToShoppingCart(0);
            Serenity.takeScreenshot();
            storeDashboardPage.goToCartCheckout();
            Serenity.takeScreenshot();
            shoppingCartPage.fillCartInfo(FIRST_NAME, LAST_NAME, ZIP_CODE);
            Serenity.takeScreenshot();
            shoppingCartPage.doCheckout();
            Serenity.takeScreenshot();

        } catch (Exception e) {
            Assertions.fail();
            LOGGER.error("Error message SALE CUANDO:" + e.getMessage(), e);
        }

    }


    @Entonces("se verifica que el precio es correcto y se realiza la compra")
    public void seVerificaQueElPrecioEsCorrectoYSeRealizaLaCompra() {
        boolean resultShoppingCart;
        boolean resultGreetings;
        try {

            resultShoppingCart = shoppingCartPage.verifyCheckout(shopItemsList);
            shoppingCartPage.finalizeCheckout();
            resultGreetings = shoppingCartPage.verifyGreetingsPage();

            assertThat(resultShoppingCart).isTrue();
            assertThat(resultGreetings).isTrue();

        } catch (Exception e) {
            Assertions.fail();
            LOGGER.error("Error message SALE ENTONCES:" + e.getMessage(), e);
        }
    }

    @Cuando("el usuario ingresa a la plataforma, añade dos articulos del inventario al carrito, y hace checkout")
    public void elUsuarioIngresaALaPlataformaAñadeDosArticulosDelInventarioAlCarritoYHaceCheckout() {
        int listSize = 0;
        shopItemsList = new ArrayList<GridItem>();
        try {
            Serenity.takeScreenshot();
            shopItemsList = storeDashboardPage.addTwoRandomItemsToShoppingCart();
            Serenity.takeScreenshot();
            storeDashboardPage.goToCartCheckout();
            Serenity.takeScreenshot();
            shoppingCartPage.fillCartInfo(FIRST_NAME, LAST_NAME, ZIP_CODE);
            Serenity.takeScreenshot();
            shoppingCartPage.doCheckout();
            Serenity.takeScreenshot();

        } catch (Exception e) {
            Assertions.fail();
            LOGGER.error("Error message SALE CUANDO:" + e.getMessage(), e);
        }
    }

    @Cuando("el usuario ingresa a la plataforma, añade todos los articulos del inventario al carrito, y hace checkout")
    public void elUsuarioIngresaALaPlataformaAñadeTodosLosArticulosDelInventarioAlCarritoYHaceCheckout() {
        int listSize = 0;
        shopItemsList = new ArrayList<GridItem>();
        try {
            shopItemsList = storeDashboardPage.addAllItemsToShoppingCart();
            storeDashboardPage.goToCartCheckout();
            shoppingCartPage.fillCartInfo(FIRST_NAME, LAST_NAME, ZIP_CODE);
            shoppingCartPage.doCheckout();

        } catch (Exception e) {
            Assertions.fail();
            LOGGER.error("Error message SALE CUANDO:" + e.getMessage(), e);
        }
    }
}
