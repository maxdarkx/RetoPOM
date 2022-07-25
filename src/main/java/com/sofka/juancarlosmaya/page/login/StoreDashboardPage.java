package com.sofka.juancarlosmaya.page.login;

import com.sofka.juancarlosmaya.page.common.CommonActionOnPages;
import org.apache.hc.client5.http.impl.classic.MainClientExec;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StoreDashboardPage extends CommonActionOnPages {
    private static final Logger LOGGER = Logger.getLogger(StoreDashboardPage.class);
    @FindBy(id="react-burger-menu-btn")
    private WebElement mainMenu;

    @FindBy(id="logout_sidebar_link")
    private WebElement logout;

    /*
    @FindBy()
    private WebElement ;
     */

    public StoreDashboardPage(WebDriver driver, int seconds, boolean explicitTime) {
        super(driver, seconds, explicitTime);
        pageFactoryInitElement(driver, this);
    }

    public void doLogout()
    {
        withExplicitWaitClickOn(mainMenu);
        withExplicitWaitClickOn(logout);
        LOGGER.info("Logout Successfull");
    }

}
