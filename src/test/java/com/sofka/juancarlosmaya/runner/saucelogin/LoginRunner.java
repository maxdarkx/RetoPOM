package com.sofka.juancarlosmaya.runner.saucelogin;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = true,
        features = "src/test/resources/features/sauceLogin.feature",
        glue = "com.sofka.juancarlosmaya.stepdefinition.saucelogin"
        //tags = "@incorrectLogin"
)
public class LoginRunner {
}
