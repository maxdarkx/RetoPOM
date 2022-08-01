package com.sofka.juancarlosmaya.page.login;

import com.sofka.juancarlosmaya.page.common.CommonActionOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.Locale;

public class LoginFormPage extends CommonActionOnPages {
    private static final Logger LOGGER = Logger.getLogger(LoginFormPage.class);
    @FindBy(id = "user-name")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "title")
    private WebElement title;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorLogin;

    public LoginFormPage(WebDriver driver, Duration duration, boolean explicitTime) {
        super(driver, duration, explicitTime);
        pageFactoryInitElement(driver, this);
    }

    public void fillLoginWith(String user,String pass ) throws InterruptedException
    {
        scrollOn(userName);
        typeOn(userName, user);
        scrollOn(password);
        typeOn(password, pass);
        doSubmit(loginButton);
        LOGGER.info("Login Successfull");
    }

    public String isLoginDone()
    {
        return getText(title).trim().toLowerCase();
    }

    public Boolean isLogoutDone(){
        return loginButton.isDisplayed();
    }

    public Boolean isLoginWrong(){
        return errorLogin.isDisplayed();
    }
}
