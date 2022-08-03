package com.sofka.juancarlosmaya.page.shop;

import com.sofka.juancarlosmaya.model.GridItem;
import com.sofka.juancarlosmaya.page.common.CommonActionOnPages;
import net.serenitybdd.core.Reportable;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
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


    public StoreDashboardPage(WebDriver driver, Duration duration, boolean explicitTime) {
        super(driver, duration, explicitTime);
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

    @Step("Anadir un item al carro de compras")
    public void addGridItemToCartList(int i)
    {
        GridItem item = new GridItem(cellItems.get(i));
        shopItems.add(item);
        withExplicitWaitClickOn(item.getButton());
        Serenity.recordReportData().withTitle("Se anade un item al carro de compras").andContents(item.toString());
Serenity.reportThat(" ", new Reportable() {
    @Override
    public void perform() {

    }
});
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

    public List<GridItem> addAllItemsToShoppingCart() {
        findItems();

        for (int i = 0; i < 6 ; i++) {
            addAnItemToShoppingCart(i);
        }
        return getShopItems();
    }
}
