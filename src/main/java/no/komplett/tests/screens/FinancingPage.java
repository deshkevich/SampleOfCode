package no.komplett.tests.screens;

/**
 * Created by a.dziashkevich on 4/14/15.
 */
public class FinancingPage extends BasePage {
    protected static final String FINANSING_CONFIRM_XPATH = "//div[@class='mainContent']";

    public boolean isFinancingIdentificationPageLoaded() {
        return driver.isElementPresentAndVisibleOnPage(FINANSING_CONFIRM_XPATH);
    }
}
