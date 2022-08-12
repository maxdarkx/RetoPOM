package com.sofka.juancarlosmaya.pages.login;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static com.sofka.juancarlosmaya.forms.shop.DashboardForm.*;

public class LoginFormPage extends UIInteractions {
    private static final Logger LOGGER = Logger.getLogger(LoginFormPage.class);


    public LoginFormPage(WebDriver driver) {
        super.setDriver(driver);
    }

    @Step("fill login form with credentials: user: #user, password: #pass")
    public void fillLoginWith(String user,String pass ) throws InterruptedException
    {
        scrollOn($(TEXT_USER_NAME));
        $(TEXT_USER_NAME).type(user);
        scrollOn($(TEXT_PASSWORD));
        $(TEXT_PASSWORD).type(pass);
        $(BUTTON_LOGIN).submit();
        LOGGER.info("Login Successfull");
    }

    @Step("user should see the dashboard title")
    public String isLoginDone()
    {
        return $(LABEL_TITLE).getText();
    }

    @Step("Once the login process is done, user should see the login page")
    public Boolean isLogoutDone(){
        return $(BUTTON_LOGIN).isDisplayed();
    }

    @Step("The login process failed")
    public Boolean isLoginWrong(){
        return $(LABEL_ERROR_LOGIN).isDisplayed();
    }

    protected void scrollOn(WebElementFacade element){
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].scrollIntoView();", element);
    }
}
