package no.komplett.tests.screens;

import no.komplett.tests.utils.common.ThreadLogger;
import no.komplett.tests.utils.selenium.CommonWebDriver;
import org.openqa.selenium.Dimension;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by a.dziashkevich on 2/9/15.
 */
public class BasePage {
    protected static final String ACCOUNT_BUTTON_XPATH = "//button[@class='btn btn-default text-right']";

    protected static final String CUSTOMER_NAME_XPATH = ACCOUNT_BUTTON_XPATH + "/span[@class='uppercase customer-name']";

    protected static final String LOGOUT_BUTTON_XPATH = "//a[@href='/k/signout.aspx']";

    protected static final String LOGIN_POPUP_XPATH = "//form[@id='loginForm']/../../..";

    protected static final String MY_PAGE_ACCOUNT_BUTTON_XPATH = "//a[@href='/k/account.aspx']";

    protected CommonWebDriver driver = CommonWebDriver.getCommonWebDriver();

    public BasePage() {
    }

    /**
     * Return 'anonymous' in case no one is logged
     */
    public String getPresentlyLoggedUser() {
        ThreadLogger.getThreadLogger().info(String.format("Get the name of presently logged user from element with" +
                " xpath: %s", CUSTOMER_NAME_XPATH));
        driver.waitForPageToLoad();
        if(driver.isElementPresentAndVisibleOnPage(ACCOUNT_BUTTON_XPATH)) {
            return driver.getTextByXpath(CUSTOMER_NAME_XPATH).trim();
        }
        return "anonymous";
    }

    public boolean isLoginPopupOpen() {
        ThreadLogger.getThreadLogger().info("Is login popup opened?");
        Boolean result = driver.isElementPresentAndVisibleOnPage(LOGIN_POPUP_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is: %s", result));
        return result;
    }

    public MainPage logout() {
        ThreadLogger.getThreadLogger().info("Logout from currently registered customer");
        if(driver.isElementPresentAndVisibleOnPage(ACCOUNT_BUTTON_XPATH)) {
            driver.clickByXpath(ACCOUNT_BUTTON_XPATH);
            driver.clickByXpath(LOGOUT_BUTTON_XPATH);
        }
        return new MainPage();
    }

    public void closeBrowser() {
        ThreadLogger.getThreadLogger().info("Close all browser windows");
        driver.clearDriverHolder();
        if(driver!=null)
            driver.quit();
    }

    public void closeWindow() {
        ThreadLogger.getThreadLogger().info("Close active browser window");
        driver.clearDriverHolder();
        driver.close();
    }

    public Boolean isTextPresentOnPage(String text) {
        ThreadLogger.getThreadLogger().info("Check that text present on page");
        return driver.isTextPresentOnPage(text);
    }

    public AccountPage goToAccountPage() {
        ThreadLogger.getThreadLogger().info("Go to account page");
        if(driver.isElementPresentAndVisibleOnPage(ACCOUNT_BUTTON_XPATH)) {
            driver.clickByXpath(ACCOUNT_BUTTON_XPATH);
            driver.clickByXpath(MY_PAGE_ACCOUNT_BUTTON_XPATH);
        }
        return new AccountPage();
    }

    public void resizeBrowser(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public static boolean linkExists(String URLName){
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con =
                    (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            ThreadLogger.getThreadLogger()
                    .info(String.format("By link %s response code is " + con.getResponseCode(), URLName));
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
