package com.sofka.juancarlosmaya.pages.shop;

import com.sofka.juancarlosmaya.models.GridItem;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import static com.sofka.juancarlosmaya.forms.shop.ShoppingCartForm.*;


public class ShoppingCartPage extends PageObject {

    private String sFirstName;
    private String sLastName;
    private String sZipCode;

    private List<GridItem> itemsToCompare;

    @Managed
    private WebDriver driver;


    @Step("Se obtienen los datos del cliente al carrito de compras")
    public void fillCartInfo(String firstName, String lastName, String zipCode)
    {
        sFirstName = firstName;
        sLastName = lastName;
        sZipCode = zipCode;
    }

    @Step("El usuario se desplaza al carrito de compras e ingresa sus datos personales")
    public void doCheckout() {
        $(BUTTON_GO_TO_CHECKOUT).click();
        $(TEXT_FIRST_NAME).type(sFirstName);
        $(TEXT_LAST_NAME).type(sLastName);
        $(TEXT_POSTAL_CODE).type(sZipCode);
        $(BUTTON_CONTINUE_TRANSACTION).click();
    }

    @Step("Se anade cada item a la lista del usuario para verificar despues el carrito de compras")
    public Boolean verifyCheckout(List<GridItem> itemsToCompare)
    {
        boolean result = false;

        this.itemsToCompare = itemsToCompare;
        int i = 0;
        for (WebElement item: $$(LIST_CART_ITEM).stream().map(WebElementFacade::getElement).collect(Collectors.toList()))
        {;
            result = verifyItemString(i, item);
            if(!result)
                break;
            i++;
        }

        if(result)
            result = verifySalePrice();

        return result;
    }

    private boolean verifySalePrice() {
        double partialPrice = 0.0;
        double taxes;
        double totalPrice;
        String sTotalCartPrice = $(LABEL_TOTAL_PRICE).getText().substring(8);
        boolean result;
        String finalPrice;

        for (GridItem item: itemsToCompare)
            partialPrice += item.getPrice();

        taxes = partialPrice * 0.08;
        totalPrice = partialPrice + taxes;
        finalPrice = new DecimalFormat("#.0#")
                .format(totalPrice)
                .replace(",", ".");
        result = finalPrice.equals(sTotalCartPrice);
        return result;
    }

    private boolean verifyItemString(int i, WebElement item) {
        String itemPrice;
        String itemFromShoppingCartPrice;
        boolean result;
        String itemName;
        String itemFromShoppingCartName;
        itemName = item.findElement(By.className("inventory_item_name")).getText().trim();
        itemPrice = item.findElement(By.className("inventory_item_price")).getText().trim();
        itemFromShoppingCartName =  itemsToCompare.get(i).getLabelName();
        itemFromShoppingCartPrice = itemsToCompare.get(i).getLabelPrice();
        result = itemName.equals(itemFromShoppingCartName)
                && itemPrice.equals(itemFromShoppingCartPrice);
        return result;
    }


    @Step("El usuario hace click en el boton de finalizar la transaccion")
    public void finalizeCheckout() {

        $(BUTTON_FINISH_CHECKOUT).click();
    }

    @Step("Se verifica que el usuario se encuentre en la pagina de felicitacion por la compra realizada")
    public boolean verifyGreetingsPage() {
        boolean result = false;
        String greetingsMessage = "THANK YOU FOR YOUR ORDER".toLowerCase();
        result = greetingsMessage.equals(
                $(LABEL_GREETINGS).getText()
                .trim()
                .toLowerCase()
        );
        return result;
    }
}
