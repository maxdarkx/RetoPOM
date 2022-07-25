package com.sofka.juancarlosmaya.runner.saucelogin;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = true,
        features = "src/test/resources/features/sauceLogin.feature",
        glue = "com.sofka.juancarlosmaya.stepdefinition.saucelogin"
        //tags = "@incorrectLogin"
)
public class LoginRunner {
}
