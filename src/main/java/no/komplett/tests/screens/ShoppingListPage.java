package no.komplett.tests.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by kema on 03.07.2015.
 */
public class ShoppingListPage extends MainPage {
    protected static final String SHOPPING_LIST_TITLE_TEXT_XPATH = "//div[@id='block-content']//h1[1]";

    protected static final String PROUDCTS_IN_SHOPPINGLIST_XPATH = "//form[@action='shoplist.aspx'][1]//table//tr[position() >1]";

    protected static final String UPDATE_SHOPPINGLIST_BUTTON_XPATH = "//td[@colspan='3']//input[@name='Save']";

    protected static final String AVAILABLE_SHOPPINGLISTS_XPATH = "//table//tr[position() >1]";

    protected static final String DELETE_ITEM_IN_SHOPPING_LIST_BUTTON_XPATH = "//input[@name='deleteItems']";

    public ShoppingListPage clickOnShoppingListWithName(String name) {

        List<WebElement> shoppingLists = driver.findElementsByXPath("//form[@action='shoplist.aspx'][1]//table//tr[position() >1]//td[2]//strong");
        By shoppingListsHelper = By.xpath("//td[2]//strong");
        //TODO: Fint out why this does not work, argh!
        for(WebElement list : shoppingLists) {
            String test = list.findElement(shoppingListsHelper).getText();
            if(list.getText().equals(name)) {
                list.click();
                break;
            }
        }
        return this;
    }

    public String getShoppingListTitle() {
        return driver.getTextByXpath(SHOPPING_LIST_TITLE_TEXT_XPATH);
    }

    public ShoppingListPage checkProductInList(int numberOfProducts) {
        List<WebElement> products = driver.findElementsByXPath(PROUDCTS_IN_SHOPPINGLIST_XPATH);

        int counter = 0;
        for(WebElement product : products) {
            if(counter < numberOfProducts) {
                product.findElement(By.xpath("//input[@type='checkbox']")).click();
            }
            counter += 1;
        }
        return this;
    }

    public int getNumberOfProductsInShoppingList() {
        // Minus one since the last tr in table is save and cancel buttons
        return driver.findElementsByXPath(PROUDCTS_IN_SHOPPINGLIST_XPATH).size()-1;
    }

    public ShoppingListPage deleteShoppingListItem() {
        driver.clickByXpath(DELETE_ITEM_IN_SHOPPING_LIST_BUTTON_XPATH);
        return this;
    }

    public ShoppingListPage saveShoppingList() {
        driver.clickByXpath(UPDATE_SHOPPINGLIST_BUTTON_XPATH);
        return this;
    }
}
