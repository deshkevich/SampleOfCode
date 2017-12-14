package no.komplett.tests.screens;

import no.komplett.tests.utils.common.ThreadLogger;

/**
 * Created by a.dziashkevich on 4/30/15.
 */
public class SearchResultsPage extends MainPage{
    protected static final String SEARCH_RESULTS_TABLE_DIV = "//div[@class='search-result']";

    protected static final String SEARCH_RESULT_DIV = "//div//*[contains(text(), '%s')]";

    protected static final String RIGHT_MENU_POINT = "//div[@class='browse-nodes']/ul/li/a[contains(text(), '%s')]";

    protected static final String SUBCATEGORY_MENU_POINT =
            "//div[contains(@class, 'subcategory-facet')]//li/a/span[contains(text(), '%s')]";

    protected static final String AUTOCORRECTION_WARNING_TEXT_XPATH = "//div[contains(@class,'row mAlert no-gutter mAlert')]";

    public boolean isSearchedProductInResults(String productHeader) {
        ThreadLogger.getThreadLogger().info(String.format("Is product with '%s' precence on page", productHeader));
        driver.waitForPageToLoad();
        driver.waitForJQueryProcessing(10);
        boolean result = driver.isElementPresentAndVisibleOnPage(String.format(SEARCH_RESULT_DIV, productHeader));
        ThreadLogger.getThreadLogger().info(String.format("Result is '%s'", result));
        return result;
    }

    public SearchResultsPage goToRightMenuPoint(String menuPoint) {
        ThreadLogger.getThreadLogger().info(String.format("Press '%s' right menu point", menuPoint));
        driver.clickByXpath(String.format(RIGHT_MENU_POINT, menuPoint));
        return this;
    }

    public SearchResultsPage goToSubcategoryMenuPoint(String menuPoint) {
        ThreadLogger.getThreadLogger().info(String.format("Press '%s' subcategory menu point", menuPoint));
        driver.clickByXpath(String.format(SUBCATEGORY_MENU_POINT, menuPoint));
        return this;
    }

    public boolean isAutocorrectionWarningPresent() {
        ThreadLogger.getThreadLogger().info(String.format("Is autocorrection warning present by xpath = '%s'",
                AUTOCORRECTION_WARNING_TEXT_XPATH));
        boolean result = driver.isElementPresentAndVisibleOnPage(AUTOCORRECTION_WARNING_TEXT_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is '%s'", result));
        return result;
    }

    public boolean isOnListAllProductByManufacturerPage() {
        return driver.getCurrentUrl().contains("manufacturer");
    }
}
