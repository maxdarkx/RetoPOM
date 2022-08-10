package com.sofka.juancarlosmaya.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
         snippets = CucumberOptions.SnippetType.CAMELCASE,
         publish = true,
         features = "src/test/resources/features/sales.feature",
         glue = "com.sofka.juancarlosmaya.stepdefinitions",
        tags = "@TwoItems"
)

public class SaleRunner {
}
