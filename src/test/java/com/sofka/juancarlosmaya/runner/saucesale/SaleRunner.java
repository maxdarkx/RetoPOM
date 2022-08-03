package com.sofka.juancarlosmaya.runner.saucesale;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
         snippets = CucumberOptions.SnippetType.CAMELCASE,
         publish = true,
         features = "src/test/resources/features/sales.feature",
         glue = "com.sofka.juancarlosmaya.stepdefinition.saucesale",
        tags = "@TwoItems"
)

public class SaleRunner {
}
