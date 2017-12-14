package no.komplett.tests.screens;

import no.komplett.tests.utils.common.ThreadLogger;

import java.util.List;

/**
 * Created by a.dziashkevich on 7/2/15.
 */
public class ContactPage extends MainPage {
    protected static final String LIST_OF_CUSTOMERS_XPATH = "//a[contains(@href, 'editContact&ProfileId')]";

    protected static final String EDIT_CONTACT_DATA_LINK = "//a[text()='%s']";

    protected static final String ADD_NEW_CONTACT_DATA_LINK = "//a[contains(@href, 'addContact')]";

    public EditContactDataPage goToEditContactData(String fullName) {
        ThreadLogger.getThreadLogger().info("Go to edit contact data page");
        for(String s : getCustomersName())
            if(s.contains(fullName))
                driver.clickByXpath(String.format(EDIT_CONTACT_DATA_LINK, s));
        return new EditContactDataPage();
    }

    public List<String> getCustomersName() {
        ThreadLogger.getThreadLogger().info("Get name field value");
        List<String> result = driver.getTextOfElements(LIST_OF_CUSTOMERS_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is: %s", result));
        return result;
    }

    public Boolean isCustomerPresentInCustomersList(String fullName) {
        for(String s : getCustomersName())
            if(s.contains(fullName)) return true;
        return false;
    }

    public EditContactDataPage addNewContact() {
        ThreadLogger.getThreadLogger().info("Click 'add new contact' and go to edit contact data page");
        driver.clickByXpath(ADD_NEW_CONTACT_DATA_LINK);
        return new EditContactDataPage();
    }
}
