package no.komplett.tests.screens;

import no.komplett.tests.utils.common.ThreadLogger;

/**
 * Created by a.dziashkevich on 7/6/15.
 */
public class AddressPage extends MainPage {
    protected static final String EDIT_ADDRESS_INPUT_XPATH = "//a[contains(@href, 'dlyaddr-edit')]";

    protected static final String ADD_ADDRESS_INPUT_XPATH = "//a[contains(@href, 'addresses-add')]";

    public EditAddressPage goToEditAddress() {
        ThreadLogger.getThreadLogger().info("Go to edit address page");
        driver.clickByXpath(EDIT_ADDRESS_INPUT_XPATH);
        return new EditAddressPage();
    }

    public EditAddressPage addNewAddress() {
        ThreadLogger.getThreadLogger().info("Add new address");
        driver.clickByXpath(ADD_ADDRESS_INPUT_XPATH);
        return new EditAddressPage();
    }
}
