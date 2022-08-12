package com.sofka.juancarlosmaya.pages.shop;

import com.google.common.base.Predicate;
import com.sofka.juancarlosmaya.models.GridItem;
import com.sofka.juancarlosmaya.pages.common.CommonActionOnPages;
import net.serenitybdd.core.Reportable;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.sofka.juancarlosmaya.forms.login.LoginForm.*;
import static com.sofka.juancarlosmaya.forms.shop.ShoppingCartForm.BUTTON_GO_TO_CHECKOUT;

public class StoreDashboardPage extends PageObject {
    private static final Logger LOGGER = Logger.getLogger(StoreDashboardPage.class);
    private List<GridItem> shopItems = new ArrayList<>();
    private List<WebElement> cellItems;


    public StoreDashboardPage(WebDriver driver) {
        super(driver);
    }

    //@Step("User do logout from the application")
    public void doLogout()
    {
        $(BUTTON_MAIN_MENU).click();
        $(OPT_LOGOUT).click();
        LOGGER.info("Logout Successfull");
    }


    //@Step("el usuario observa todos los items disponibles en la tienda")
    public void findItems()
    {
        cellItems = new ArrayList<>();
        cellItems.addAll($$(TABLE_CELLITEM).stream().map(WebElementFacade::getElement).collect(Collectors.toList()));
    }

    //@Step("Anadir un item al carro de compras")
    public void addGridItemToCartList(int i) {
        GridItem item = new GridItem(cellItems.get(i));
        shopItems.add(item);
        item.getButton().click();
        Serenity.recordReportData().withTitle("Se anade un item al carro de compras").andContents(item.toString());
    }

    //@Step("Quitar un elemento del carrito de compras")
    public void removeItemFromCartList(int i)
    {
        shopItems.get(i).getButton().click();
        shopItems.remove(i);
    }

    //@Step("Se guarda un item en el carrito de compras")
    public List<GridItem> addAnItemToShoppingCart(int i)
    {
        findItems();
        addGridItemToCartList(i);
        return getShopItems();
    }

    //@Step("Se hace click para mostrar el carro de compras")
    public void goToCartCheckout()
    {
        $(BUTTON_GO_TO_CHECKOUT).click();
    }

    public List<GridItem> getShopItems() {
        return shopItems;
    }


    public List<GridItem> addTwoRandomItemsToShoppingCart() {
        findItems();
        List <Integer> listItems = Arrays.asList(0,1,2,3,4,5);
        Collections.shuffle(listItems);

        addAnItemToShoppingCart(listItems.get(5));
        addAnItemToShoppingCart(listItems.get(1));

        return getShopItems();
    }

    public List<GridItem> addAllItemsToShoppingCart() {
        findItems();

        for (int i = 0; i < 6 ; i++) {
            addAnItemToShoppingCart(i);
        }
        return getShopItems();
    }

}
