package com.sofka.juancarlosmaya.runner.saucesale;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
         snippets = CucumberOptions.SnippetType.CAMELCASE,
         publish = true,
         features = "src/test/resources/features/sauceSale.feature",
         glue = "com.sofka.juancarlosmaya.stepdefinition.saucesale"
        //tags = "@incorrectLogin"
)

public class SaleRunner {
}
