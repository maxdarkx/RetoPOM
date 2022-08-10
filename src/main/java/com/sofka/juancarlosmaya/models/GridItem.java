package com.sofka.juancarlosmaya.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GridItem{
    private WebElement button;

    private WebElement cellItem;
    private String labelPrice;
    private Float price;
    private String labelName;


    public GridItem(WebElement cellItem) {
        this.cellItem = cellItem;
        button = cellItem.findElement(By.tagName("button"));
        labelName = cellItem.findElement(By.className("inventory_item_name")).getText();
        labelPrice = cellItem.findElement(By.className("inventory_item_price")).getText();
        price = Float.valueOf(
                labelPrice.replace("$","")
        );
    }

    public WebElement getButton() {
        button = cellItem.findElement(By.tagName("button"));
        return button;
    }

    public String getLabelPrice() {
        return labelPrice;
    }

    public Float getPrice() {
        return price;
    }

    public String getLabelName() {
        return labelName;
    }

    @Override
    public String toString() {
        return "Se obtiene el elemento de la tienda con nombre: "+ labelName+
                " con precio: "+labelPrice+" y con boton "+button.toString();
    }
}
