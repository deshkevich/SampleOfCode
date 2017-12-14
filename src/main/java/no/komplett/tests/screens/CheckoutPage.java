package no.komplett.tests.screens;

import no.komplett.tests.utils.common.ThreadLogger;
import no.komplett.tests.utils.data.Card;

/**
 * Created by a.dziashkevich on 4/8/15.
 */
public class CheckoutPage extends MainPage {
    protected static final String CARD_NUMBER_FIELD_XPATH = "//input[@name='CCNum']";

    protected static final String EXPIRED_MONTH_SELECT_XPATH = "//select[@name='ExpMo']";

    protected static final String EXPIRED_YEAR_SELECT_XPATH = "//select[@name='ExpYr']";

    protected static final String CVV_CODE_INPUT_XPATH = "//input[@name='cvv']";

    protected static final String PLACE_YOUR_ORDER_INPUT_XPATH = "//input[@name='PlaceYourOrderUp']";

    protected static final String LEASING_CREDITCHECK_INPUT_XPATH = "//input[@name='LeasingCreditCheck']";

    protected static final String CHECK_ACCEPT_TERMS_INPUT_XPATH =
            "//*[@id=\"checkout_main\"]/div[2]/div/label[1]";

    protected static final String BANK_ACCOUNT_RADIO_LABEL_XPATH_DEV = "//label[@for='Leasing' and @class='text-label']";

    protected static final String BANK_ACCOUNT_RADIO_LABEL_XPATH = "//label[@for='FIN' and @class='text-label']";

    protected static final String DEFERRAL_RADIO_LABEL_XPATH = "//label[@for='DEFERRAL' and @class='text-label']";

    protected static final String UPON_RECEIPT_RADIO_LABEL_XPATH = "//label[@for='COD' and @class='text-label']";

    protected static final String INVOICE_RADIO_LABEL_XPATH = "//label[@for='KLARNA' and @class='text-label']";

    protected static final String PERSONAL_NUMBER_INPUT_XPATH = "//input[@name='klarnaSsn']";

    protected static final String ALERT_MOBILE_LABEL_XPATH = "//label[@for='ALERT.1']";

    protected static final String ALERT_FINANCING_ACCEPT_XPATH =  "//html/body/div[2]/div/div[1]/div/div[2]/div/div/p/button[1]";

    protected static final String NAME_OF_CUSTOMER_XPATH = "//div[@id='lblName']";

    protected static final String ADDRESS_OF_CUSTOMER_XPATH = "//div[@id='lblAddress']";

    protected static final String CONTACT_PERSON_NAME_XPATH = "//span[@id='transportLabelName']";

    protected static final String CONTACT_PERSON_PHONE_XPATH = "//span[@id='transportLabelPhone']";

    protected static final String ADDRESS_SELECT_XPATH = "//select[@name='abAddressId']";

    protected static final String DELIVERY_PERSON_FIRSTNAME_TEXT_XPATH = "//div[@id='lblName']";

    protected static final String CHANGE_CURRENT_DELIVERY_ADDRESS_LINK_XPATH = "//a[@id='EditCurrentAddress']";

    protected static final String CHANGE_CURRENT_DELIVERY_ADDRESS_NAME1_INPUT_XPATH = "//input[@id='NAME1']";

    protected static final String UPDATE_CURRENT_DELIVERY_ADDRESS_BUTTON_XPATH = "//input[@id='updateCurrentAddress']";

    protected static final String CHANGE_TO_LEGAL_ADDRESS_INVOICE_BUTTON_XPATH = "//input[@id='UseKlarnaAddress']";

    public ConfirmationPurchasePage fillCardDataAndGo() {
        Card card = new Card();
        if(driver.isElementPresentAndVisibleOnPage(CHECK_ACCEPT_TERMS_INPUT_XPATH)) {
            driver.clickByXpath(CHECK_ACCEPT_TERMS_INPUT_XPATH);
        }
        driver.inputTextByXpath(CARD_NUMBER_FIELD_XPATH, card.getCardNumber());
        driver.selectOptionByValue(EXPIRED_MONTH_SELECT_XPATH, card.getExpiredMonth());
        driver.selectOptionByValue(EXPIRED_YEAR_SELECT_XPATH, card.getExpiredYear());
        driver.inputTextByXpath(CVV_CODE_INPUT_XPATH, card.getCvvCode());
        if(driver.isElementPresentAndVisibleOnPage(ALERT_MOBILE_LABEL_XPATH)) {
            driver.clickByXpath(ALERT_MOBILE_LABEL_XPATH);
        }
        driver.clickByXpath(PLACE_YOUR_ORDER_INPUT_XPATH);
        return new ConfirmationPurchasePage();
    }

    public FinancingPage switchToBankAccountMethodAndGo() {
        driver.waitForPageToLoad();
        driver.inputTextByXpath(CARD_NUMBER_FIELD_XPATH, "");
        if(driver.isElementPresentAndVisibleOnPage(CHECK_ACCEPT_TERMS_INPUT_XPATH)) {
            driver.clickByXpath(CHECK_ACCEPT_TERMS_INPUT_XPATH);
        }
        driver.clickByXpath(BANK_ACCOUNT_RADIO_LABEL_XPATH);
        if(driver.isElementPresentAndVisibleOnPage(ALERT_MOBILE_LABEL_XPATH)) {
            driver.clickByXpath(ALERT_MOBILE_LABEL_XPATH);
        }
        driver.clickByXpath(PLACE_YOUR_ORDER_INPUT_XPATH);
        return new FinancingPage();
    }

    public FinancingPage switchToBankAccountMethodAndGoBusiness() {
        driver.waitForPageToLoad();
        driver.inputTextByXpath(CARD_NUMBER_FIELD_XPATH, "");
        if(driver.isElementPresentAndVisibleOnPage(CHECK_ACCEPT_TERMS_INPUT_XPATH)) {
            driver.clickByXpath(CHECK_ACCEPT_TERMS_INPUT_XPATH);
        }
        driver.clickByXpath(BANK_ACCOUNT_RADIO_LABEL_XPATH_DEV);
        if(driver.isElementPresentAndVisibleOnPage(ALERT_MOBILE_LABEL_XPATH)) {
            driver.clickByXpath(ALERT_MOBILE_LABEL_XPATH);
        }
        driver.clickByXpath(LEASING_CREDITCHECK_INPUT_XPATH);
        driver.clickByXpath(ALERT_FINANCING_ACCEPT_XPATH);
        return new FinancingPage();
    }

    public FinancingPage switchToDeferralMethodAndGo() {
        driver.waitForPageToLoad();
        if(driver.isElementPresentAndVisibleOnPage(ALERT_MOBILE_LABEL_XPATH)) {
            driver.clickByXpath(ALERT_MOBILE_LABEL_XPATH);
        }
        driver.inputTextByXpath(CARD_NUMBER_FIELD_XPATH, "");
        if(driver.isElementPresentAndVisibleOnPage(CHECK_ACCEPT_TERMS_INPUT_XPATH)) {
            driver.clickByXpath(CHECK_ACCEPT_TERMS_INPUT_XPATH);
        }
        driver.clickByXpath(DEFERRAL_RADIO_LABEL_XPATH);
        driver.clickByXpath(PLACE_YOUR_ORDER_INPUT_XPATH);
        return new FinancingPage();
    }

    public ConfirmationPurchasePage switchToUponReceiptMethodAndGo() {
        driver.waitForPageToLoad();
        driver.waitForJQueryProcessing(10);
        driver.inputTextByXpath(CARD_NUMBER_FIELD_XPATH, "");
/*        if(driver.isElementPresentVisibleClickable(CHECK_ACCEPT_TERMS_INPUT_XPATH)) {
            driver.selectIfNotSelectedByXpath(CHECK_ACCEPT_TERMS_INPUT_XPATH);
        }*/
        driver.clickByXpath(UPON_RECEIPT_RADIO_LABEL_XPATH);
        if(driver.isElementPresentAndVisibleOnPage(ALERT_MOBILE_LABEL_XPATH)) {
            driver.clickByXpath(ALERT_MOBILE_LABEL_XPATH);
        }
        driver.clickByXpath(PLACE_YOUR_ORDER_INPUT_XPATH);
        if(driver.isElementPresentAndVisibleOnPage(CHECK_ACCEPT_TERMS_INPUT_XPATH)) {
            driver.clickByXpath(CHECK_ACCEPT_TERMS_INPUT_XPATH);
            driver.clickByXpath(PLACE_YOUR_ORDER_INPUT_XPATH);
        }
        return new ConfirmationPurchasePage();
    }

    public ConfirmationPurchasePage switchToInvoiceMethodAndGo(String personalNumber) throws InterruptedException {
        driver.waitForPageToLoad();
        driver.inputTextByXpath(CARD_NUMBER_FIELD_XPATH, "");
        driver.clickByXpath(INVOICE_RADIO_LABEL_XPATH);
        driver.inputTextByXpath(PERSONAL_NUMBER_INPUT_XPATH, personalNumber);
        if(driver.isElementPresentAndVisibleOnPage(ALERT_MOBILE_LABEL_XPATH)) {
            driver.clickByXpath(ALERT_MOBILE_LABEL_XPATH);
        }
        driver.clickByXpath(PLACE_YOUR_ORDER_INPUT_XPATH);
        if(driver.isElementPresentAndVisibleOnPage(CHECK_ACCEPT_TERMS_INPUT_XPATH)) {
            driver.clickByXpath(CHECK_ACCEPT_TERMS_INPUT_XPATH);
            driver.clickByXpath(PLACE_YOUR_ORDER_INPUT_XPATH);
        }
        if(driver.isElementPresentAndVisibleOnPage(CHANGE_TO_LEGAL_ADDRESS_INVOICE_BUTTON_XPATH)) {
            driver.clickByXpath(CHANGE_TO_LEGAL_ADDRESS_INVOICE_BUTTON_XPATH);
            driver.clickByXpath(PLACE_YOUR_ORDER_INPUT_XPATH);
        }
        return new ConfirmationPurchasePage();
    }

    public String getNameOfCustomer() {
        ThreadLogger.getThreadLogger().info("Get customer name value");
        String result = driver.getTextByXpath(NAME_OF_CUSTOMER_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is: %s", result));
        return result;
    }

    public String getAddressOfCustomer() {
        ThreadLogger.getThreadLogger().info("Get customer address value");
        String result = driver.getTextByXpath(ADDRESS_OF_CUSTOMER_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is: %s", result));
        return result;
    }

    public String getContactPersonName() {
        ThreadLogger.getThreadLogger().info("Get contact name value");
        String result = driver.getTextByXpath(CONTACT_PERSON_NAME_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is: %s", result));
        return result;
    }

    public String getContactPersonPhone() {
        ThreadLogger.getThreadLogger().info("Get contact phone value");
        String result = driver.getTextByXpath(CONTACT_PERSON_PHONE_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is: %s", result));
        return result;
    }

    public CheckoutPage selectAddress(String contactPerson) {
        ThreadLogger.getThreadLogger().info("Select address from addresses select box");
        driver.selectOptionByPartOfText(ADDRESS_SELECT_XPATH, contactPerson);
        return this;
    }

    public boolean isDeliveryPersonTheSameAsInvoice(String firsname, String lastname) {
        return driver.getTextByXpath(DELIVERY_PERSON_FIRSTNAME_TEXT_XPATH).equals(firsname + " " + lastname);
    }

    public CheckoutPage changeDeliveryPersonName(String firstname, String lastname) {
        driver.clickByXpath(CHANGE_CURRENT_DELIVERY_ADDRESS_LINK_XPATH);
        driver.inputTextByXpath(CHANGE_CURRENT_DELIVERY_ADDRESS_NAME1_INPUT_XPATH, firstname + " " + lastname);
        driver.clickByXpath(UPDATE_CURRENT_DELIVERY_ADDRESS_BUTTON_XPATH);
        return this;
    }
}
