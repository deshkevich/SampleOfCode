package no.komplett.tests.screens;

import no.komplett.tests.utils.common.ThreadLogger;

/**
 * Created by a.dziashkevich on 7/6/15.
 */
public class EditAddressPage extends MainPage {
    protected static final String NAME_INPUT_XPATH = "//input[@name='NAME1']";

    protected static final String ADDRESS_INPUT_XPATH = "//input[@name='STRAS']";

    protected static final String POSTNUM_INPUT_XPATH = "//input[@name='PSTLZ']";

    protected static final String POSTADDRESS_INPUT_XPATH = "//input[@name='ORT01']";

    protected static final String CONTACTPERSON_INPUT_XPATH = "//input[@name='BNAME']";

    protected static final String PHONENUMBER_INPUT_XPATH = "//input[@name='TELF1']";

    protected static final String ORGANISATION_RADIO_INPUT_XPATH = "//input[@name='ConsumerAddr'][2]";

    protected static final String ADDRESS_TYPE__RADIO_INPUT_XPATH = "//input[@name='AddressType'][1]";

    protected static final String SAVE_BUTTON_XPATH = "//input[@name='Addresses.Save']";

    public EditAddressPage editName(String newValue) {
        ThreadLogger.getThreadLogger().info(String.format("Input new value '%s' user name", newValue));
        driver.inputTextByXpath(NAME_INPUT_XPATH, newValue);
        return this;
    }

    public EditAddressPage editAddress(String newValue) {
        ThreadLogger.getThreadLogger().info(String.format("Input new value '%s' user adress", newValue));
        driver.inputTextByXpath(ADDRESS_INPUT_XPATH, newValue);
        return this;
    }

    public EditAddressPage editPostNumberAndCheckIsCorrectAutofill(String postnum, String postaddress) {
        ThreadLogger.getThreadLogger().info(String.format("Input new value '%s' user post number", postnum));
        driver.inputTextByXpath(POSTNUM_INPUT_XPATH, postnum);
        driver.clickByXpath(POSTNUM_INPUT_XPATH);
        driver.waitForJQueryProcessing(10);
        driver.inputTextByXpath(POSTADDRESS_INPUT_XPATH, postaddress);
        return this;
    }

    public EditAddressPage editContactPerson(String newValue) {
        ThreadLogger.getThreadLogger().info(String.format("Input new value '%s' contact person", newValue));
        driver.inputTextByXpath(CONTACTPERSON_INPUT_XPATH, newValue);
        return this;
    }

    public EditAddressPage editPhoneNumber(String newValue) {
        ThreadLogger.getThreadLogger().info(String.format("Input new value '%s' phone number", newValue));
        driver.inputTextByXpath(PHONENUMBER_INPUT_XPATH, newValue);
        return this;
    }

    public EditAddressPage checkOrganisationRadio() {
        driver.clickByXpath(ORGANISATION_RADIO_INPUT_XPATH);
        return this;
    }

    public EditAddressPage checkAddressType() {
        driver.clickByXpath(ADDRESS_TYPE__RADIO_INPUT_XPATH);
        return this;
    }

    public ContactPage saveChanges() {
        ThreadLogger.getThreadLogger().info("Click to 'Save changes'");
        driver.clickByXpath(SAVE_BUTTON_XPATH);
        return new ContactPage();
    }
}
