package com.sofka.juancarlosmaya.stepdefinition.saucelogin;

import com.sofka.juancarlosmaya.page.login.LoginFormPage;
import com.sofka.juancarlosmaya.stepdefinition.setup.Configuration;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.junit.Before;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.util.Locale;


public class LoginStepDefinition extends Configuration {

    private static final Logger LOGGER = Logger.getLogger(LoginStepDefinition.class);
    private static final boolean IS_EXPLICIT_WAIT = true;
    private static final int  DEFAULT_EXPLICIT_WAIT = 30;
    private String usuario;
    private String contraseña;
    private LoginFormPage loginFormPage;

    @Before
    public void setup()
    {

    }

    @Dado("Que el usuario se encuentra registrado en el sistema con el nombre de usuario {string} y la contraseña {string}")
    public void queElUsuarioSeEncuentraRegistradoEnElSistemaConElNombreDeUsuarioYLaContraseña(String usuario, String contraseña) {
        try {
            setUpWebDriver();
            setUpLog4j2();
            generalSetUp();
            this.usuario = usuario;
            this.contraseña = contraseña;
        } catch (Exception e) {
            LOGGER.info("Error message DADO:" + e.getMessage());
        }
    }

    @Cuando("el usuario ingresa a la plataforma")
    public void elUsuarioIngresaALaPlataforma() {
        try {
            loginFormPage = new LoginFormPage(driver, DEFAULT_EXPLICIT_WAIT,  IS_EXPLICIT_WAIT);
            loginFormPage.fillLoginWith(usuario, contraseña);
        } catch (Exception e) {
            LOGGER.info("Error message CUANDO:" + e.getMessage());
        }
    }
    @Entonces("se muestra la pagina de la tienda")
    public void seMuestraLaPaginaDeLaTienda() {
        String result = "";
        try {
            result = loginFormPage.isLoginDone();
            LOGGER.info("Esperado: products, Obtenido: " + result.toLowerCase());
            Assertions.assertEquals("products" , result.toLowerCase());
            closeDriver();
        } catch (Exception e) {
            LOGGER.info("Error message ENTONCES:" + e.getMessage());
        }
    }
}
