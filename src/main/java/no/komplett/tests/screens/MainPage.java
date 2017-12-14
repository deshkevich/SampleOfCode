package no.komplett.tests.screens;

import no.komplett.tests.utils.common.ThreadLogger;
import no.komplett.tests.utils.data.KomplettPlatform;
import no.komplett.tests.utils.data.customer.BusinessCustomer;
import no.komplett.tests.utils.data.customer.PrivateCustomer;

/**
 * Created by a.dziashkevich on 2/9/15.
 */
public class MainPage extends BasePage {
    protected static final String LOGIN_AND_REGISTER_BUTTON_XPATH = "//button[@id='login-button']";

    protected static final String USERNAME_INPUT_XPATH = "//input[@id='username']";

    protected static final String PASSWORD_INPUT_XPATH = "//input[@id='password']";

    protected static final String LOGIN_BUTTON_XPATH = "//div[@class='form-group row']/button[@type='submit']";

    protected static final String PRIVATE_CUSTOMER_REGISTRATION_LINK_XPATH =
            "//a[contains(@data-viewmodel, 'RegisterPrivate')]";

    protected static final String BUSINESS_CUSTOMER_REGISTRATION_LINK_XPATH =
            "//a[contains(@data-viewmodel, 'RegisterBusiness')]";

    protected static final String RESTORE_PASSWORD_LINK_XPATH =
            "//a[contains(@data-viewmodel, 'ForgotPassword')]";

    protected static final String MESSAGE_THAT_RESTORE_PASSWORD_MAIL_SEND =
            "//div[@class='row mAlert no-gutter mAlert-success']//div[contains(text(), '%s')]";

    protected static final String FIRST_NAME_REGISTRATION_INPUT_XPATH = "//input[@id='firstName']";

    protected static final String LAST_NAME_REGISTRATION_INPUT_XPATH = "//input[@id='lastName']";

    protected static final String ADDRESS_REGISTRATION_INPUT_XPATH = "//input[@id='street']";

    protected static final String POSTNUMBER_REGISTRATION_INPUT_XPATH = "//input[@id='postnr']";

    protected static final String LOCATION_REGISTRATION_INPUT_XPATH = "//input[@id='location']";

    protected static final String MOBILE_NUMBER_REGISTRATION_INPUT_XPATH = "//input[@id='mobileNumber']";

    protected static final String TELEPHONE_NUMBER_REGISTRATION_INPUT_XPATH = "//input[@id='tlf']";

    protected static final String EMAIL_REGISTRATION_INPUT_XPATH =
            "//form[@id='registerPrivateForm']//input[@id='username']";

    protected static final String PASSWORD_REGISTRATION_INPUT_XPATH =
            "//form[@id='registerPrivateForm']//input[@id='password']";

    protected static final String ACCEPT_REGISTRATION_INPUT_XPATH = "//label[@for='accept']";

    protected static final String REGISTER_BUTTON_XPATH = "//div[contains(@class, 'button-wrapper')]/button[@type='submit']";

    /*search elements*/
    protected static final String SEARCH_INPUT_FIELD_XPATH = "//input[@id='searchDesktopAutocomplete']";

    protected static final String SEARCH_BUTTON_XPATH = "//button[@class='btn btn-default search-form-button']";

    protected static final String RESTORE_PASSWORD_EMAIL_FIELD_XPATH =
            "//form[contains(@data-bind, 'requestPassword')]//input[@type='Email']";

    protected static final String RESTORE_PASSWORD_BUTTON_XPATH = "//button[@class='btn-medium secondary pull-right']";

    protected static final String FAILED_REGISTRATION_TEXT_XPATH =
            "//div[@class='row mAlert no-gutter mAlert-error']/div[@class='alert-content']/div";

    protected static final String FAILED_REGISTRATION_CLOSE_BUTTON_XPATH =
            "//div[@class='row mAlert no-gutter mAlert-error']//span[@class='close-icon']";

    protected static final String BUSINESS_NAME_INPUT_XPATH = "//input[@id='businessName']";

    protected static final String ORGANIZATION_NUMBER_INPUT_XPATH = "//input[@id='organizationNr']";

    protected static final String BUSINESS_ADDRESS_INPUT_XPATH = "//input[@id='businessAddress']";

    protected static final String BUSINESS_REGISTRATION_NEXT_BUTTON_XPATH = "//button[@data-bind='click:next']";

    protected static final String GIFT_CARD_LINK_XPATH = "//a[@title='Gavekort']";

    protected static final String UPPER_MENU_POINT = "//ul[@class='nav-bar']/li/a/span[contains(text(), '%s')]";

    protected static final String RESPONSIVE_MENU_BUTTON = "//span[@class='icon fa-darkgrey-bars-large center-block']";

    protected static final String RESPONSIVE_ARROW =
            "//div[@class='column-button no-gutter hidden-sm hidden-md hidden-lg']";

    protected static final String SHOPPINGLIST_LINK_XPATH = "//a[@href='/k/shoplist.aspx']";

    protected static final String FIRSTNAME_REGISTATION_ERROR_XPATH = "//div[@data-bind='text:fields.firstName.error']";
    protected static final String LASTNAME_REGISTRATION_ERROR_XPATH = "//div[@data-bind='text:fields.lastName.error']";
    protected static final String ADDRESS_REGISTRATION_ERROR_XPATH = "//div[@data-bind='text:fields.street.error']";
    protected static final String POSTNUMBER_REGISTRATION_ERROR_XPATH = "//div[@data-bind='text:fields.postnr.error']";
    protected static final String LOCATION_REGISTRATION_ERROR_XPATH = "//div[@data-bind='text:fields.location.error']";
    protected static final String MOBILE_REGISTRATION_ERROR_XPATH = "//div[@data-bind='text:fields.mobile.error']";
    protected static final String EMAIL_REGISTRATION_ERROR_XPATH = "//form[@id='registerPrivateForm']//div[@data-bind='text:fields.username.error']";
    protected static final String PASSWORD_REGISTRATION_ERROR_XPATH = "//form[@id='registerPrivateForm']//div[@data-bind='text:fields.password.error']";
    protected static final String ACCEPT_REGISTRATION_ERROR_XPATH = "//div[@data-bind='text:fields.accept.error']";

    protected static final String LOGIN_VALIDATOR_XPATH = "//div[@data-viewmodel='Components/LoginButton/AccountDropdownViewModel']";


    public MainPage() {
    }

    public MainPage(KomplettPlatform platform) {
        ThreadLogger.getThreadLogger().info(String.format("Open main page by url: %s", platform.getPlatformHost()));
        driver.get(platform.getPlatformHost());
    }

    public MainPage login(String username, String password) {
        ThreadLogger.getThreadLogger().info(String.format("Login using username '%s' and password '%s'",
                username, password));
        driver.waitForPageToLoad();
        if(!isLoginPopupOpen()) {
            driver.clickByXpath(LOGIN_AND_REGISTER_BUTTON_XPATH);
        }
        driver.inputTextByXpath(USERNAME_INPUT_XPATH, username);
        driver.inputTextByXpath(PASSWORD_INPUT_XPATH, password);
        driver.clickByXpath(LOGIN_BUTTON_XPATH);
        return this;
    }

    public MainPage inputRegistrationFormField(String field, String value) {
        switch (field) {
            case "firstname":
                driver.inputTextByXpath(FIRST_NAME_REGISTRATION_INPUT_XPATH, value);
                break;
            case "lastname":
                driver.inputTextByXpath(LAST_NAME_REGISTRATION_INPUT_XPATH, value);
                break;
            case "address":
                driver.inputTextByXpath(ADDRESS_REGISTRATION_INPUT_XPATH, value);
                break;
            case "postcode":
                driver.inputTextByXpath(POSTNUMBER_REGISTRATION_INPUT_XPATH, value);
                break;
            case "city":
                driver.inputTextByXpath(LOCATION_REGISTRATION_INPUT_XPATH, value);
                break;
            case "mobile":
                driver.inputTextByXpath(MOBILE_NUMBER_REGISTRATION_INPUT_XPATH, value);
                break;
            case "email":
                driver.inputTextByXpath(EMAIL_REGISTRATION_INPUT_XPATH, value);
                break;
            case "password":
                driver.inputTextByXpath(PASSWORD_REGISTRATION_INPUT_XPATH, value);
                break;
            case "accept":
                driver.clickByXpath(ACCEPT_REGISTRATION_INPUT_XPATH);
        }
        return this;
    }

    public MainPage submitRegistrationForm() {
        driver.clickByXpath(REGISTER_BUTTON_XPATH);
        return this;
    }

    public boolean doesRegistrationFieldHaveError(String field) {
        switch (field) {
            case "firstname":
                return driver.findElementByXPath(FIRSTNAME_REGISTATION_ERROR_XPATH).getText().isEmpty();
            case "lastname":
                return driver.findElementByXPath(LASTNAME_REGISTRATION_ERROR_XPATH).getText().isEmpty();
            case "address":
                return driver.findElementByXPath(ADDRESS_REGISTRATION_ERROR_XPATH).getText().isEmpty();
            case "postcode":
                return driver.findElementByXPath(POSTNUMBER_REGISTRATION_ERROR_XPATH).getText().isEmpty();
            case "city":
                return driver.findElementByXPath(LOCATION_REGISTRATION_ERROR_XPATH).getText().isEmpty();
            case "mobile":
                return driver.findElementByXPath(MOBILE_REGISTRATION_ERROR_XPATH).getText().isEmpty();
            case "email":
                return driver.findElementByXPath(EMAIL_REGISTRATION_ERROR_XPATH).getText().isEmpty();
            case "password":
                return driver.findElementByXPath(PASSWORD_REGISTRATION_ERROR_XPATH).getText().isEmpty();
            case "accept":
                return driver.findElementByXPath(ACCEPT_REGISTRATION_ERROR_XPATH).getText().isEmpty();
            default:
                return false;
        }
    }

    public MainPage openRegistrationPopup() {
        ThreadLogger.getThreadLogger().info("Opening registration popup");
        driver.clickByXpath(LOGIN_AND_REGISTER_BUTTON_XPATH);
        driver.clickByXpath(PRIVATE_CUSTOMER_REGISTRATION_LINK_XPATH);
        return this;
    }

    public MainPage registerNewPrivateCustomer(PrivateCustomer user) {
        ThreadLogger.getThreadLogger().info("Register new private user");
        driver.clickByXpath(LOGIN_AND_REGISTER_BUTTON_XPATH);
        driver.clickByXpath(PRIVATE_CUSTOMER_REGISTRATION_LINK_XPATH);
        driver.inputTextByXpath(FIRST_NAME_REGISTRATION_INPUT_XPATH, user.getFirstName());
        driver.inputTextByXpath(LAST_NAME_REGISTRATION_INPUT_XPATH, user.getLastName());
        driver.inputTextByXpath(ADDRESS_REGISTRATION_INPUT_XPATH, user.getAddress());
        driver.inputTextByXpath(POSTNUMBER_REGISTRATION_INPUT_XPATH, new Integer(user.getPostNumber()).toString());
        driver.waitForJQueryProcessing(10);
        driver.inputTextByXpath(LOCATION_REGISTRATION_INPUT_XPATH, user.getLocation());
        driver.inputTextByXpath(MOBILE_NUMBER_REGISTRATION_INPUT_XPATH, user.getMobileNumber());
        driver.inputTextByXpath(EMAIL_REGISTRATION_INPUT_XPATH, user.getEmail());
        driver.inputTextByXpath(PASSWORD_REGISTRATION_INPUT_XPATH, user.getPassword());
        driver.clickByXpath(ACCEPT_REGISTRATION_INPUT_XPATH);
        driver.clickByXpath(REGISTER_BUTTON_XPATH);
        return this;
    }

    public MainPage registerNewBusinessCustomer(BusinessCustomer user) {
        ThreadLogger.getThreadLogger().info("Register new business user");
        driver.clickByXpath(LOGIN_AND_REGISTER_BUTTON_XPATH);
        driver.clickByXpath(BUSINESS_CUSTOMER_REGISTRATION_LINK_XPATH);
        driver.inputTextByXpath(BUSINESS_NAME_INPUT_XPATH, user.getBusinessName());
        driver.inputTextByXpath(ORGANIZATION_NUMBER_INPUT_XPATH, user.getOrganizationNumber());
        driver.inputTextByXpath(BUSINESS_ADDRESS_INPUT_XPATH, user.getBusinessAddress());
        driver.inputTextByXpath(POSTNUMBER_REGISTRATION_INPUT_XPATH, new Integer(user.getPostNumber()).toString());
        driver.inputTextByXpath(LOCATION_REGISTRATION_INPUT_XPATH, user.getLocation());
        driver.clickByXpath(BUSINESS_REGISTRATION_NEXT_BUTTON_XPATH);
        driver.inputTextByXpath(FIRST_NAME_REGISTRATION_INPUT_XPATH, user.getFirstName());
        driver.inputTextByXpath(LAST_NAME_REGISTRATION_INPUT_XPATH, user.getLastName());
        driver.inputTextByXpath(TELEPHONE_NUMBER_REGISTRATION_INPUT_XPATH, user.getTelephoneNumber());
        driver.inputTextByXpath(MOBILE_NUMBER_REGISTRATION_INPUT_XPATH, user.getMobileNumber());
        driver.inputTextByXpath(EMAIL_REGISTRATION_INPUT_XPATH, user.getEmail());
        driver.inputTextByXpath(PASSWORD_REGISTRATION_INPUT_XPATH, user.getPassword());
        driver.clickByXpath(ACCEPT_REGISTRATION_INPUT_XPATH);
        driver.clickByXpath(REGISTER_BUTTON_XPATH);
        return this;
    }

    public ProductPage searchByIdNumber(String id) {
        ThreadLogger.getThreadLogger().info("Search product by id");
        driver.inputTextByXpath(SEARCH_INPUT_FIELD_XPATH, id);
        driver.clickByXpath(SEARCH_BUTTON_XPATH);
        return new ProductPage();
    }

    public SearchResultsPage searchByManufacturedNumber(String num) {
        ThreadLogger.getThreadLogger().info("Search product by manufactured number");
        driver.inputTextByXpath(SEARCH_INPUT_FIELD_XPATH, num);
        driver.clickByXpath(SEARCH_BUTTON_XPATH);
        return new SearchResultsPage();
    }

    public SearchResultsPage searchByDescription(String description) {
        ThreadLogger.getThreadLogger().info("Search product by description");
        driver.inputTextByXpath(SEARCH_INPUT_FIELD_XPATH, description);
        driver.clickByXpath(SEARCH_BUTTON_XPATH);
        return new SearchResultsPage();
    }

    public Boolean isFailedLoginTextAppear() {
        ThreadLogger.getThreadLogger().info("Check that message about failed authorization");
        driver.isElementPresentAndVisibleOnPage(FAILED_REGISTRATION_TEXT_XPATH);
        return driver.findElementByXPath(FAILED_REGISTRATION_TEXT_XPATH).isDisplayed();
    }

    public MainPage closeFailedLoginMassage() {
        ThreadLogger.getThreadLogger().info("Close message about failed authorization");
        driver.isElementPresentAndVisibleOnPage(FAILED_REGISTRATION_TEXT_XPATH);
        driver.clickByXpath(FAILED_REGISTRATION_CLOSE_BUTTON_XPATH);
        return this;
    }

    public GiftCardPage goToGiftCard() {
        ThreadLogger.getThreadLogger().info("Press 'Gavekort' and go to GiftCard page");
        driver.clickByXpath(GIFT_CARD_LINK_XPATH);
        return new GiftCardPage();
    }

    public SearchResultsPage goToUpperMenuPoint(String menuPoint) {
        ThreadLogger.getThreadLogger().info(String.format("Press '%s' upper menu point", menuPoint));
        driver.clickByXpath(String.format(UPPER_MENU_POINT, menuPoint));
        return new SearchResultsPage();
    }

    public boolean isResponsiveSM() {
        ThreadLogger.getThreadLogger().info("Check that page responsive in SM size");
        return driver.isElementPresentAndVisibleOnPage(RESPONSIVE_MENU_BUTTON);
    }

    public boolean isResponsiveXS() {
        ThreadLogger.getThreadLogger().info("Check that page responsive in XS size");
        return driver.isElementPresentAndVisibleOnPage(RESPONSIVE_ARROW);
    }

    public boolean isMailRestorePasswordSend(String email) {
        ThreadLogger.getThreadLogger().info("Open restore password popup");
        driver.waitForPageToLoad();
        if(!isLoginPopupOpen()) {
            driver.clickByXpath(LOGIN_AND_REGISTER_BUTTON_XPATH);
        }
        driver.clickByXpath(RESTORE_PASSWORD_LINK_XPATH);
        driver.inputTextByXpath(RESTORE_PASSWORD_EMAIL_FIELD_XPATH, email);
        driver.clickByXpath(RESTORE_PASSWORD_BUTTON_XPATH);
        String f = driver.getTextByXpath(String.format(MESSAGE_THAT_RESTORE_PASSWORD_MAIL_SEND, email));
        return driver.isElementPresentAndVisibleOnPage(String.format(MESSAGE_THAT_RESTORE_PASSWORD_MAIL_SEND, email));
    }

    public ShoppingListPage goToShoppingListPage() {
        driver.clickByXpath(SHOPPINGLIST_LINK_XPATH);
        return new ShoppingListPage();
    }

    public boolean isUserLoggedIn() {
        return driver.isElementPresentAndVisibleOnPage(LOGIN_VALIDATOR_XPATH);
    }
}