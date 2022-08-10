package com.sofka.juancarlosmaya.pages.login;

import com.sofka.juancarlosmaya.pages.common.CommonActionOnPages;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static com.sofka.juancarlosmaya.forms.shop.DashboardForm.*;

public class LoginFormPage extends PageObject {
    private static final Logger LOGGER = Logger.getLogger(LoginFormPage.class);

    @Managed
    private WebDriver driver;

    public LoginFormPage() {
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

    public Boolean isLoginWrong(){
        return $(LABEL_ERROR_LOGIN).isDisplayed();
    }

    protected void scrollOn(WebElementFacade element){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", element);
    }
}
