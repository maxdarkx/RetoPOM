package com.sofka.juancarlosmaya.page.shop;

import com.sofka.juancarlosmaya.model.GridItem;
import com.sofka.juancarlosmaya.page.common.CommonActionOnPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.DecimalFormat;
import java.util.List;

public class ShoppingCartPage extends CommonActionOnPages {

    private String sFirstName;
    private String sLastName;
    private String sZipCode;

    @FindBy(id = "checkout")
    private WebElement goToCheckout;

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement zipCode;

    @FindBy(id = "continue")
    private WebElement buttonContinue;

    @FindBy(className = "cart_item")
    private List<WebElement> shopItem;

    @FindBy(className = "summary_total_label")
    private WebElement cartTotalPrice;


    public ShoppingCartPage(WebDriver driver, int seconds, boolean explicitTime) {
        super(driver, seconds, explicitTime);
        pageFactoryInitElement(driver, this);
    }

    public void fillCartInfo(String firstName, String lastName, String zipCode)
    {
        sFirstName = firstName;
        sLastName = lastName;
        sZipCode = zipCode;
    }
    public void doCheckout() {
        withExplicitWaitClickOn(goToCheckout);
        withExplicitWaitTypeOn(firstName, sFirstName);
        withExplicitWaitTypeOn(lastName, sLastName);
        withExplicitWaitTypeOn(zipCode, sZipCode);
        withExplicitWaitClickOn(buttonContinue);
    }

    public Boolean verifyCheckout(List<GridItem> itemsToCompare)
    {
        Boolean result = false;
        String itemName;
        String itemPrice;
        String itemFromShoppingCartName;
        String itemFromShoppingCartPrice;
        double partialPrice = 0.0;
        double taxes;
        double totalPrice = 0.0;
        String finalPrice;
        String sTotalCartPrice = cartTotalPrice.getText().substring(8);
        int i = 0;
        for (WebElement item: shopItem)
        {;
            itemName = item.findElement(By.className("inventory_item_name")).getText().trim();
            itemPrice = item.findElement(By.className("inventory_item_price")).getText().trim();
            itemFromShoppingCartName =  itemsToCompare.get(i).getLabelName();
            itemFromShoppingCartPrice = itemsToCompare.get(i).getLabelPrice();
            result = itemName.equals(itemFromShoppingCartName)
                    && itemPrice.equals(itemFromShoppingCartPrice);
            if(!result)
                break;
            i++;
        }
        if(result)
        {
            for (GridItem item: itemsToCompare)
                partialPrice += item.getPrice();
            taxes = partialPrice * 0.08;
            totalPrice = partialPrice + taxes;
            finalPrice = new DecimalFormat("#.0#")
                    .format(totalPrice)
                    .replace(",", ".");
            result = finalPrice.equals(sTotalCartPrice);
        }
        return result;
    }
}
