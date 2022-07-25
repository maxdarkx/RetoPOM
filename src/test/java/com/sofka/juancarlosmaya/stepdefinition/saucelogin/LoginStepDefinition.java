package com.sofka.juancarlosmaya.stepdefinition.saucelogin;

import com.sofka.juancarlosmaya.page.login.LoginFormPage;
import com.sofka.juancarlosmaya.page.login.StoreDashboardPage;
import com.sofka.juancarlosmaya.stepdefinition.setup.Configuration;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;


public class LoginStepDefinition extends Configuration {

    private static final Logger LOGGER = Logger.getLogger(LoginStepDefinition.class);
    private static final boolean IS_EXPLICIT_WAIT = true;
    private static final int DEFAULT_EXPLICIT_WAIT = 30;
    private String usuario;
    private String contrasena;
    private LoginFormPage loginFormPage;
    private StoreDashboardPage storeDashboardPage;

    @Before
    public void setup() {
        setUpWebDriver();
        setUpLog4j2();
        generalSetUp();
    }

    @Dado("Que el usuario se encuentra registrado en el sistema con el nombre de usuario {string} y la contraseña {string}")
    public void queElUsuarioSeEncuentraRegistradoEnElSistemaConElNombreDeUsuarioYLaContrasena(String usuario, String contrasena) {
        try {
            this.usuario = usuario;
            this.contrasena = contrasena;
        } catch (Exception e) {
            Assertions.fail();
            quitDriver();
            LOGGER.error("Error message DADO:" + e.getMessage(), e);
        }
    }

    @Cuando("el usuario ingresa a la plataforma")
    public void elUsuarioIngresaALaPlataforma() {
        try {
            loginFormPage = new LoginFormPage(driver, DEFAULT_EXPLICIT_WAIT, IS_EXPLICIT_WAIT);
            storeDashboardPage = new StoreDashboardPage(driver, DEFAULT_EXPLICIT_WAIT, IS_EXPLICIT_WAIT);
            loginFormPage.fillLoginWith(usuario, contrasena);
        } catch (Exception e) {
            Assertions.fail();
            quitDriver();
            LOGGER.error("Error message CUANDO:" + e.getMessage(), e);
        }
    }

    @Entonces("se muestra la pagina de la tienda")
    public void seMuestraLaPaginaDeLaTienda() {
        String result = "";
        try {
            result = loginFormPage.isLoginDone();
            LOGGER.info("Esperado: products, Obtenido: " + result);
            Assertions.assertEquals("products", result);
            closeDriver();
            quitDriver();
        } catch (Exception e) {
            Assertions.fail();
            quitDriver();
            LOGGER.error("Error message ENTONCES:" + e.getMessage(), e);
        }
    }

    @Y("hace logout")
    public void haceLogout() {
        try {
            storeDashboardPage.doLogout();

        } catch (Exception e) {
            Assertions.fail();
            quitDriver();
            LOGGER.error("Error message CUANDO hace logout: " + e.getMessage(), e);
        }
    }


    @Entonces("se muestra la pagina de login")
    public void seMuestraLaPaginaDeLogin()
    {
        Boolean result = false;
        try {
            result = loginFormPage.isLogoutDone();
            LOGGER.info("Esperado: true, Obtenido: "+ result.toString());
            Assertions.assertEquals(true, result);
            closeDriver();
            quitDriver();
        }
        catch (Exception e)
        {
            Assertions.fail();
            quitDriver();
            LOGGER.error("Error message ENTONCES hace logout:" + e.getMessage(), e);
        }
    }

    @Cuando("el usuario intenta ingresar a la plataforma, pero se da cuenta que su contraseña esta incorrecta")
    public void elUsuarioIntentaIngresarALaPlataformaPeroSeDaCuentaQueSuContraseñaEstaIncorrecta() {
        try {
            loginFormPage = new LoginFormPage(driver, DEFAULT_EXPLICIT_WAIT, IS_EXPLICIT_WAIT);
            loginFormPage.fillLoginWith(usuario, contrasena);
            LOGGER.info("Login incorrecto, contaseña: "+ contrasena);
        } catch (Exception e) {
            Assertions.fail();
            quitDriver();
            LOGGER.error("Error message CUANDO:" + e.getMessage(), e);
        }
    }

    @Entonces("se muestra un mensaje de error")
    public void seMuestraUnMensajeDeError() {
        Boolean result = false;
        try {
            result = loginFormPage.isLoginWrong();
            LOGGER.info("Esperado: true, Obtenido: "+ result.toString());
            Assertions.assertEquals(true, result);
            closeDriver();
            quitDriver();
        }
        catch (Exception e)
        {
            Assertions.fail();
            quitDriver();
            LOGGER.error("Error message ENTONCES hace login incorrecto:" + e.getMessage(), e);
        }
    }
}
