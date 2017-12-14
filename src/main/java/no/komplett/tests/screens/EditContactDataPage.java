package no.komplett.tests.screens;

import no.komplett.tests.utils.common.ThreadLogger;

/**
 * Created by a.dziashkevich on 7/2/15.
 */
public class EditContactDataPage extends MainPage {
    protected static final String FIRSTNAME_INPUT_XPATH = "//input[@name='NAME1']";

    protected static final String LASTNAME_INPUT_XPATH = "//input[@name='NAMEV']";

    protected static final String EMAIL_INPUT_XPATH = "//input[@name='Username']";

    protected static final String NEW_PASS_INPUT_XPATH = "//input[@name='Password1']";

    protected static final String REPEAT_PASS_INPUT_XPATH = "//input[@name='Password2']";

    protected static final String SEND_INFORMATION_ABOUT_CHECKBOX_XPATH = "//input[@name='PCL1']";

    protected static final String MOBILE_INPUT_XPATH = "//input[@name='MobileNumber']";

    protected static final String DAYTIME_NUMBER_XPATH = "//input[@name='PhoneNumber']";

    protected static final String SAVE_CHANGES_BUTTON_XPATH = "//center/input[1]";

    public String getFirstnameValue() {
        ThreadLogger.getThreadLogger().info("Get first name field value");
        String result = driver.getValueOfElementByXpath(FIRSTNAME_INPUT_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is: %s", result));
        return result;
    }

    public String getLastnameValue() {
        ThreadLogger.getThreadLogger().info("Get last name field value");
        String result = driver.getValueOfElementByXpath(LASTNAME_INPUT_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is: %s", result));
        return result;
    }

    public String getEmailValue() {
        ThreadLogger.getThreadLogger().info("Get email field value");
        String result = driver.getValueOfElementByXpath(EMAIL_INPUT_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is: %s", result));
        return result;
    }

    public Boolean isSendInformationAboutCheckboxActive() {
        ThreadLogger.getThreadLogger().info("Is send information about checkbox is active");
        Boolean result = driver.isSelectedByXpath(SEND_INFORMATION_ABOUT_CHECKBOX_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is: %s", result));
        return result;
    }

    public EditContactDataPage editFirstName(String newValue) {
        ThreadLogger.getThreadLogger().info(String.format("Input new value '%s' user first name", newValue));
        driver.inputTextByXpath(FIRSTNAME_INPUT_XPATH, newValue);
        return this;
    }

    public EditContactDataPage editLastName(String newValue) {
        ThreadLogger.getThreadLogger().info(String.format("Input new value '%s' user last name", newValue));
        driver.inputTextByXpath(LASTNAME_INPUT_XPATH, newValue);
        return this;
    }

    public EditContactDataPage editEmail(String newValue) {
        ThreadLogger.getThreadLogger().info(String.format("Input new value '%s' user email", newValue));
        driver.inputTextByXpath(EMAIL_INPUT_XPATH, newValue);
        return this;
    }

    public EditContactDataPage editPassword(String newValue) {
        ThreadLogger.getThreadLogger().info(String.format("Input new value '%s' user password and repeat it", newValue));
        driver.inputTextByXpath(NEW_PASS_INPUT_XPATH, newValue);
        driver.inputTextByXpath(REPEAT_PASS_INPUT_XPATH, newValue);
        return this;
    }

    public EditContactDataPage editMobile(String newValue) {
        ThreadLogger.getThreadLogger().info(String.format("Input new value '%s' user mobile number", newValue));
        driver.inputTextByXpath(MOBILE_INPUT_XPATH, newValue);
        return this;
    }

    public EditContactDataPage editPhone(String newValue) {
        ThreadLogger.getThreadLogger().info(String.format("Input new value '%s' user daytime phone number", newValue));
        driver.inputTextByXpath(DAYTIME_NUMBER_XPATH, newValue);
        return this;
    }

    public EditContactDataPage changeValueOfSendInformationCheckbox() {
        ThreadLogger.getThreadLogger().info("Change value of 'send information about' checkbox");
        driver.changeCheckboxValue(SEND_INFORMATION_ABOUT_CHECKBOX_XPATH);
        return this;
    }

    public ContactPage saveChanges() {
        ThreadLogger.getThreadLogger().info("Click to 'Save changes'");
        driver.clickByXpath(SAVE_CHANGES_BUTTON_XPATH);
        return new ContactPage();
    }

}
