package com.sofka.juancarlosmaya.stepdefinition.setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.google.common.base.StandardSystemProperty.USER_DIR;
import static com.sofka.juancarlosmaya.util.Log4jValues.LOG4J_WINDOWS_PROPERTIES_FILE_PATH;

public class Configuration {

    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";


    private String os;

    private static final String RETO_URL = "https://www.saucedemo.com/";

    protected WebDriver driver;

    protected void setUpWebDriver(){
        os = System.getProperty("os.name").toLowerCase();
        WebDriverManager.chromedriver().setup();
    }

    protected void generalSetUp(){
        driver = new ChromeDriver();
        driver.get(RETO_URL);
        driver.manage().window().maximize();
    }

    protected void setUpLog4j2(){
        PropertyConfigurator.configure(USER_DIR.value() + LOG4J_WINDOWS_PROPERTIES_FILE_PATH.getValue());
    }

    protected void quitDriver(){
        driver.quit();
    }

    protected void closeDriver()
    {
        driver.close();
    }
}
