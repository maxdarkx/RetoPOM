package com.sofka.juancarlosmaya.page.login;

import com.sofka.juancarlosmaya.page.common.CommonActionOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;

import java.util.ArrayList;
import java.util.List;

public class StoreDashboardPage extends CommonActionOnPages {
    private static final Logger LOGGER = Logger.getLogger(StoreDashboardPage.class);
    private ArrayList<String> itemInfo = new ArrayList<String>();
    private ArrayList<Float> itemValue = new ArrayList<Float>();
    private int listSize=0;
    private WebElement buttonAddToCartItem;
    private WebElement labelItemName;
    private WebElement labelItemValue;
    private WebElement buttonRemoveFromCartItem;
    private List<WebElement> cellItems;


    @FindBy(id="react-burger-menu-btn")
    private WebElement mainMenu;

    @FindBy(id="logout_sidebar_link")
    private WebElement logout;

    @FindBy(className="inventory_item")
    private List<WebElement> cellItem;

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
        cellItems = new ArrayList<WebElement>();
        cellItems.addAll(cellItem);
    }

    public int addGridItemToCart(int i)
    {
        buttonAddToCartItem = cellItems.get(i).findElement(By.tagName("button"));
        labelItemName = cellItems.get(i).findElement(By.className("inventory_item_name"));
        labelItemValue = cellItems.get(i).findElement(By.className("inventory_item_price"));

        itemInfo.add(labelItemName.getText());
        itemValue.add(Float.valueOf
                (
                        labelItemValue
                            .getText()
                            .replace("$","")
                )
        );
        withExplicitWaitClickOn(buttonAddToCartItem);

        return itemInfo.size();
    }

    public int removeItemAccordingToOrderFromCart(int grid, int i)
    {
        buttonRemoveFromCartItem = cellItems.get(grid).findElement(By.tagName("button"));
        itemInfo.remove(i);
        itemValue.remove(i);
        withExplicitWaitClickOn(buttonRemoveFromCartItem);
        return  itemInfo.size();
    }

    public ArrayList<String> getItemInfo() {
        return itemInfo;
    }

    public ArrayList<Float> getItemValue() {
        return itemValue;
    }
}
