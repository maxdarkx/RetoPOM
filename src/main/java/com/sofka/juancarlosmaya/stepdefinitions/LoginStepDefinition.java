package com.sofka.juancarlosmaya.stepdefinitions;

import com.sofka.juancarlosmaya.actions.LoginActions;
import com.sofka.juancarlosmaya.stepdefinitions.setup.Configuration;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;


public class LoginStepDefinition extends Configuration {

    private static final Logger LOGGER = Logger.getLogger(LoginStepDefinition.class);
    private String usuario;
    private String contrasena;

    @Managed
    public WebDriver driver;

    //@Steps
    private LoginActions loginActions;

    @Dado("Que el usuario se encuentra registrado en el sistema con el nombre de usuario {string} y la contraseña {string}")
    public void queElUsuarioSeEncuentraRegistradoEnElSistemaConElNombreDeUsuarioYLaContrasena(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        loginActions = new LoginActions(driver);
        loginActions.openPage();

    }

    @Cuando("el usuario ingresa a la plataforma")
    public void elUsuarioIngresaALaPlataforma() {
        loginActions.fillLogin(usuario, contrasena);
    }

    @Entonces("se muestra la pagina de la tienda")
    public void seMuestraLaPaginaDeLaTienda() {
        loginActions.isLoginDone();
    }

    @Y("hace logout")
    public void haceLogout() {
        loginActions.doLogout();
    }


    @Entonces("se muestra la pagina de login")
    public void seMuestraLaPaginaDeLogin() {
        loginActions.isLogoutDone();
    }

    @Cuando("el usuario intenta ingresar a la plataforma, pero se da cuenta que su contraseña esta incorrecta")
    public void elUsuarioIntentaIngresarALaPlataformaPeroSeDaCuentaQueSuContraseñaEstaIncorrecta() {
        loginActions.fillLogin(usuario, contrasena);

    }

    @Entonces("se muestra un mensaje de error")
    public void seMuestraUnMensajeDeError() {
        Assertions.assertTrue(loginActions.isLoginWrong());
    }
}