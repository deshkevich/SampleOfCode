package no.komplett.tests.screens;

import no.komplett.tests.utils.common.ThreadLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.dziashkevich on 4/16/15.
 */
public class AccountPage extends MainPage {
    protected static final String DIGITAL_PRODUCT_INPUT_XPATH = "//a[@href='account.aspx?mode=digitalproducts']";

    protected static final String ADDRESS_LINK_XPATH = "//td[@id='account-options']//a[@href='account.aspx?mode=dlyaddr']";

    protected static final String CONTACTS_LINK_XPATH = "//td[@id='account-options']//a[@href='account.aspx?mode=contacts']";

    protected static final String CLIENT_LINKS_SET_XPATH = "//string[@id='orderList.extraSearchCustomerHtml']/ul[1]/li/a";

    protected static final String CLIENT_LINK_XPATH = "//a[text()='%s']";

    protected static final String HISTORY_ITEM_NAME_INPUT_XPATH = "//input[@name='historyItemName']";

    protected static final String HISTORY_ITEM_NUMBER_INPUT_XPATH = "//input[@name='historyItemNo']";

    protected static final String HISTORY_ORDER_NUMBER_INPUT_XPATH = "//input[@name='orderId']";

    protected static final String SEARCH_IN_ORDERS_BUTTON_XPATH = "//input[@class='button-secondary button-medium']";

    protected static final String ORDER_NUMBER_RADIO_BUTTON_XPATH = "//input[@id='orderListKeyOrderId']";

    public void goToDigitalProductPage() {
        ThreadLogger.getThreadLogger().info("Click to digital products");
        driver.clickByXpath(DIGITAL_PRODUCT_INPUT_XPATH);
    }

    public ContactPage goToContactPage() {
        ThreadLogger.getThreadLogger().info("Go to contacts page");
        driver.clickByXpath(CONTACTS_LINK_XPATH);
        return new ContactPage();
    }

    public AddressPage goToAddressPage() {
        ThreadLogger.getThreadLogger().info("Go to address page");
        driver.clickByXpath(ADDRESS_LINK_XPATH);
        return new AddressPage();
    }

    public List<String> getLinksFromClientPart() {
        List<String> linkSet = new ArrayList<>();
        for(String e : driver.getTextOfElements(CLIENT_LINKS_SET_XPATH))
            linkSet.add(driver.getHrefByXpath(String.format(CLIENT_LINK_XPATH, e)));
        return linkSet;
    }

    public ViewOrderPage searchByOrderItemName(String itemName) {
        driver.inputTextByXpath(HISTORY_ITEM_NAME_INPUT_XPATH, itemName);
        driver.clickByXpath(SEARCH_IN_ORDERS_BUTTON_XPATH);
        return new ViewOrderPage();
    }

    public ViewOrderPage searchByOrderItemNumber(String itemNumber) {
        driver.inputTextByXpath(HISTORY_ITEM_NUMBER_INPUT_XPATH, itemNumber);
        driver.clickByXpath(SEARCH_IN_ORDERS_BUTTON_XPATH);
        return new ViewOrderPage();
    }

    public ViewOrderPage searchByOrderNumber(String orderNumber) {
        driver.inputTextByXpath(HISTORY_ORDER_NUMBER_INPUT_XPATH, orderNumber);
        driver.clickByXpath(ORDER_NUMBER_RADIO_BUTTON_XPATH);
        driver.clickByXpath(SEARCH_IN_ORDERS_BUTTON_XPATH);
        return new ViewOrderPage();
    }

}
