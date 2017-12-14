package no.komplett.tests.screens;

import no.komplett.tests.utils.common.ThreadLogger;

/**
 * Created by a.dziashkevich on 4/8/15.
 */
public class ConfirmationPurchasePage extends MainPage {
    protected static final String CONFIRMATION_TEXT_XPATH = "//div[@class='alertGood']";

    protected static final String ORDER_NUMBER_TEXT_XPATH = "//div[@class='alertGood']/a";

    protected static final String GO_TO_ORDER_VIEW_LINK_XPATH = "//a[contains(@href, 'viewOrder')]";

    public String getConfirmationMessage() {
        ThreadLogger.getThreadLogger().info(String.format("Getting the text of confirmation by xpath = '%s'",
                CONFIRMATION_TEXT_XPATH));
        String result = driver.getTextByXpath(CONFIRMATION_TEXT_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is '%s'", result));
        return result;
    }

    public ViewOrderPage goToViewOrderPage() {
        ThreadLogger.getThreadLogger().info(String.format("Go to view order page clicking by xpath = '%s'",
                GO_TO_ORDER_VIEW_LINK_XPATH));
        driver.clickByXpath(GO_TO_ORDER_VIEW_LINK_XPATH);
        return new ViewOrderPage();
    }

    public String getOrderNumber() {
        ThreadLogger.getThreadLogger().info(String.format("Getting the ordernumber of confirmation by xpath = '%s'",
                ORDER_NUMBER_TEXT_XPATH));
        String result = driver.getTextByXpath(ORDER_NUMBER_TEXT_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is '%s'", result));
        return result;
    }
}