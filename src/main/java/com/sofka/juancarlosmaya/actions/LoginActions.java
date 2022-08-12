package com.sofka.juancarlosmaya.actions;

import com.sofka.juancarlosmaya.pageobjects.SauceLabsPage;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.sofka.juancarlosmaya.forms.login.LoginForm.BUTTON_MAIN_MENU;
import static com.sofka.juancarlosmaya.forms.login.LoginForm.OPT_LOGOUT;
import static com.sofka.juancarlosmaya.forms.shop.DashboardForm.*;


public class LoginActions extends PageObject {

    private static final Logger LOGGER = Logger.getLogger(LoginActions.class);
    private final SauceLabsPage page;

    public WebDriver driver;

    public LoginActions(WebDriver driver) {
        page = new SauceLabsPage();
        this.driver = driver;
        this.setDriver(driver);
    }


    //@Step("fill login form with credentials: user: #user, password: #pass")
    public void fillLogin(String user, String pass) {
        this.
        $(TEXT_USER_NAME).click();
        $(TEXT_USER_NAME).type(user);
        $(TEXT_USER_NAME).click();
        $(TEXT_PASSWORD).type(pass);
        $(BUTTON_LOGIN).submit();
        LOGGER.info("Login Successfull");
    }

    //@Step("user should see the dashboard title")
    public void isLoginDone() {
        String result = "";
        result = $(LABEL_TITLE).getText();
        LOGGER.info("Esperado: products, Obtenido: " + result);
        Assertions.assertEquals("PRODUCTS", result);
    }

    //@Step("Once the login process is done, user should see the login page")
    public void doLogout() {
        $(BUTTON_MAIN_MENU).click();
        $(OPT_LOGOUT).click();
        LOGGER.info("Logout Successfull");
        $(BUTTON_LOGIN).isDisplayed();
    }


    //@Step("Once the login process is done, user should see the login page")
    public void isLogoutDone() {
        $(BUTTON_LOGIN).isDisplayed();
        //LOGGER.info("Hace logout");
    }


    //@Step("The login process failed")
    public Boolean isLoginWrong() {
        return $(LABEL_ERROR_LOGIN).isDisplayed();
        //LOGGER.info("Esperado: true, Obtenido: " + result.toString());

    }

    //@Step("open saucelabs page")
    public void openPage() {
        page.open();
    }
}
