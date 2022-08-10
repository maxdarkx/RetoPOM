package com.sofka.juancarlosmaya.forms.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCartForm {
    public static final By BUTTON_GO_TO_CHECKOUT = By.id("checkout");
    public static final By TEXT_FIRST_NAME= By.id("first-name");
    public static final By TEXT_LAST_NAME= By.id("last-name");
    public static final By TEXT_POSTAL_CODE = By.id("postal-code");
    public static final By BUTTON_CONTINUE_TRANSACTION = By.id("continue");
    public static final By LIST_CART_ITEM= By.className("cart_item");
    public static final By LABEL_TOTAL_PRICE= By.className("summary_total_label");
    public static final By BUTTON_FINISH_CHECKOUT= By.id("finish");
    public static final By LABEL_GREETINGS = By.id("complete-header");
}
