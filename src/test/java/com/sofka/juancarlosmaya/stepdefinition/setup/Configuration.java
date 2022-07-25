package com.sofka.juancarlosmaya.stepdefinition.setup;

import com.sofka.juancarlosmaya.stepdefinition.saucelogin.LoginStepDefinition;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.google.common.base.StandardSystemProperty.USER_DIR;
import static com.sofka.juancarlosmaya.util.Log4jValues.LOG4J_LINUX_PROPERTIES_FILE_PATH;
import static com.sofka.juancarlosmaya.util.Log4jValues.LOG4J_WINDOWS_PROPERTIES_FILE_PATH;

public class Configuration {

    private String os;
    private static final Logger LOGGER = Logger.getLogger(Configuration.class);

    private static final String RETO_URL = "https://www.saucedemo.com/";

    protected WebDriver driver;

    protected void setUpWebDriver(){
        WebDriverManager.chromedriver().setup();
    }

    protected void generalSetUp(){
        driver = new ChromeDriver();
        driver.get(RETO_URL);
        driver.manage().window().maximize();
    }

    protected void setUpLog4j2(){
        os = System.getProperty("os.name").toLowerCase();
        switch (os.substring(0,3))
        {
            case "win":
                PropertyConfigurator.configure(USER_DIR.value() + LOG4J_WINDOWS_PROPERTIES_FILE_PATH.getValue());
                LOGGER.info("Runnning on Windows OS: " + os);
                break;
            case "lin":
                PropertyConfigurator.configure(USER_DIR.value() + LOG4J_LINUX_PROPERTIES_FILE_PATH.getValue());
                LOGGER.info("Runnning on linux based OS: " + os);

                break;
            default:
                LOGGER.error("Runnning on no configured OS: " + os);
                break;
        }

    }

    protected void quitDriver(){
        driver.quit();
    }

    protected void closeDriver()
    {
        driver.close();
    }
}
