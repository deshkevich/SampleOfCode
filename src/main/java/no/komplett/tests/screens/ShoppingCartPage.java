package no.komplett.tests.screens;

import no.komplett.tests.utils.common.ThreadLogger;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by a.dziashkevich on 2/27/2015.
 */
public class ShoppingCartPage extends MainPage {
    protected static final String TOTAL_PRICE_TEXT_XPATH = "//*[@class='total-sum']/span";

    protected static final String INCREASE_COUNT_OF_STUFF_BUTTON_XPATH = "//*[@class='adjust-button inc']";

    protected static final String COUNT_OF_STUFF_INPUT_XPATH = "//input[@class='adjust-input']";

    protected static final String GO_TO_CHECKOUT_XPATH = "//button[contains(@data-bind, 'gotoCheckout')]";

    protected static final String ERROR_MESSAGE_DIV_XPATH =  "//div[@data-bind='html:message']";

    protected static final String INSURANCE_POPUP_XPATH = "//div[contains(@class, 'box')]//button[contains(@class, 'safetydeal-option')]";

    protected static final String CLOSE_INSURANCE_POPUP_XPATH = "//a[@class='close-button']";

    protected static final String SAVE_CART_LINK_XPATH = "//a[@data-bind='click:openWithSavingCartList']";

    protected static final String CREATE_SHOPPING_LIST_NAME_INPUT_XPATH ="//input[@name='shoppingListName']";
    protected static final String SAVE_SHOPPING_LIST_BUTTON_XPATH ="//div[@class='medium-ubo-width']//button";

    protected static final String SAVE_EXISTING_SHOPPING_LIST_DROPDOWN_XPATH = "";

    protected static final String IS_ON_OLD_CART_IDENTIFICATOR_XPATH = "//td[@class='slett']";


    public int getTotalPriceText() {
        ThreadLogger.getThreadLogger().info(String.format("Getting the total price of products by xpath = '%s'",
                TOTAL_PRICE_TEXT_XPATH));
        String result = driver.getTextByXpath(TOTAL_PRICE_TEXT_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is '%s'", result));
        return Integer.parseInt(result.substring(0, result.length() - 3).replace(".", ""));
    }

    public ShoppingCartPage increaseCount(int numberOfStuff) throws InterruptedException {
        ThreadLogger.getThreadLogger().info("Increase count of item in cart");
        while(numberOfStuff > Integer.parseInt(driver.getValueOfElementByXpath(COUNT_OF_STUFF_INPUT_XPATH))) {
            driver.clickByXpath(INCREASE_COUNT_OF_STUFF_BUTTON_XPATH);
            driver.waitForElementClickable(TOTAL_PRICE_TEXT_XPATH, 10);
            Thread.sleep(2000);
        }
        return this;
    }

    public boolean checkInsurancePopup() {
        ThreadLogger.getThreadLogger().info("Check to see if insurance popup is open");
        boolean result = driver.isElementPresentAndVisibleOnPage(INSURANCE_POPUP_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is: %s", result));
        return result;
    }

    public ShoppingCartPage closeInsuancePopup() {
        ThreadLogger.getThreadLogger().info("Closing insurance popup");
        driver.clickByXpath(CLOSE_INSURANCE_POPUP_XPATH);
        return this;
    }

    public CheckoutPage goToCheckout() {
        ThreadLogger.getThreadLogger().info("Press 'Go to check out button'");
        driver.clickByXpath(GO_TO_CHECKOUT_XPATH);
        return new CheckoutPage();
    }

    public String getErrorMessageText() {
        ThreadLogger.getThreadLogger().info(String.format("Getting text of error message by xpath = '%s'",
                ERROR_MESSAGE_DIV_XPATH));
        String result = driver.getTextByXpath(ERROR_MESSAGE_DIV_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is '%s'", result));
        return result;
    }

    public ShoppingCartPage clickOnSaveCartNotLoggedIn () {
        ThreadLogger.getThreadLogger().info("Click on save cart not logged in");
        driver.clickByXpath(SAVE_CART_LINK_XPATH);

        return this;
    }
    public ShoppingListPage createNewShoppingList(String name) {
        ThreadLogger.getThreadLogger().info("Input shopping list name");
        driver.inputTextByXpath(CREATE_SHOPPING_LIST_NAME_INPUT_XPATH, name);
        ThreadLogger.getThreadLogger().info("Save shopping list");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.clickByXpath(SAVE_SHOPPING_LIST_BUTTON_XPATH);
        return new ShoppingListPage();
    }

    public ShoppingListPage appendToExistingShoppingList(String name) {
        ThreadLogger.getThreadLogger().info("Selecting existing shopping list with name: " + name);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Select dropdownList = new Select(driver.findElementByXPath("//select[@name='shopppingListId']"));
        dropdownList.selectByVisibleText(name);
        driver.clickByXpath(SAVE_SHOPPING_LIST_BUTTON_XPATH);
        return new ShoppingListPage();
    }

    public boolean isOnOldCart() {
        return driver.isElementPresentAndVisibleOnPage(IS_ON_OLD_CART_IDENTIFICATOR_XPATH);
    }

}
