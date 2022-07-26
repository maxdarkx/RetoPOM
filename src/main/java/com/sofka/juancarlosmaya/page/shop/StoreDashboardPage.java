package com.sofka.juancarlosmaya.page.shop;

import com.sofka.juancarlosmaya.model.GridItem;
import com.sofka.juancarlosmaya.page.common.CommonActionOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StoreDashboardPage extends CommonActionOnPages {
    private static final Logger LOGGER = Logger.getLogger(StoreDashboardPage.class);
    private List<GridItem> shopItems = new ArrayList<>();
    private List<WebElement> cellItems;

    @FindBy(id="react-burger-menu-btn")
    private WebElement mainMenu;

    @FindBy(id="logout_sidebar_link")
    private WebElement logout;

    @FindBy(className="inventory_item")
    private List<WebElement> cellItem;

    @FindBy(className = "shopping_cart_link")
    private WebElement goToCart;


    public StoreDashboardPage(WebDriver driver, int seconds, boolean explicitTime) {
        super(driver, seconds, explicitTime);
        pageFactoryInitElement(driver, this);
    }

    public void doLogout()
    {
        withExplicitWaitClickOn(mainMenu);
        withExplicitWaitClickOn(logout);
        LOGGER.info("Logout Successfull");
    }

    public void findItems()
    {
        cellItems = new ArrayList<>();
        cellItems.addAll(cellItem);
    }

    public void addGridItemToCartList(int i)
    {
        GridItem item = new GridItem(cellItems.get(i));
        shopItems.add(item);
        withExplicitWaitClickOn(item.getButton());
    }

    public void removeItemFromCartList(int i)
    {
        withExplicitWaitClickOn(shopItems.get(i).getButton());
        shopItems.remove(i);
    }

    public List<GridItem> addAnItemToShoppingCart(int i)
    {
        findItems();
        addGridItemToCartList(i);
        return getShopItems();
    }

    public void goToCartCheckout()
    {
        withExplicitWaitClickOn(goToCart);
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
}
