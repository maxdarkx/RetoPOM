package com.sofka.juancarlosmaya.page.login;

import com.sofka.juancarlosmaya.page.common.CommonActionOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginFormPage extends CommonActionOnPages {
    private static final Logger LOGGER = Logger.getLogger(LoginFormPage.class);
    @FindBy(id = "user-name")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement login;

    @FindBy(className = "title")
    private WebElement title;

    public LoginFormPage(WebDriver driver, int seconds, boolean explicitTime) {
        super(driver, seconds, explicitTime);
        pageFactoryInitElement(driver, this);
    }

    public LoginFormPage(WebDriver driver) {
        super(driver);
        pageFactoryInitElement(driver, this);
    }

    public void fillLoginWith(String user,String pass ) throws InterruptedException
    {
        withExplicitWaitScrollOn(userName);
        withExplicitWaitTypeOn(userName, user);
        withExplicitWaitScrollOn(password);
        withExplicitWaitTypeOn(password, pass);
        withExplicitWaitDoSubmit(login);
        LOGGER.info("Login Successfull");
    }

    public String isLoginDone()
    {
        return getText(title).trim();
    }


    /*
    @FindBy()
    private WebElement;

    @FindBy()
    private WebElement;

     */

}
