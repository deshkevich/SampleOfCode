package no.komplett.tests.screens;

import no.komplett.tests.utils.common.ThreadLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.dziashkevich on 2/27/2015.
 */
public class ProductPage extends MainPage {
    protected static final String PRODUCT_NAME_TEXT_XPATH = "//h1[@itemprop='name']";

    protected static final String ALT_PRODUCT_NAME_TEXT_XPATH = "//h1[@class='product-main-info-webtext1']";

    protected static final String PRODUCT_PRICE_TEXT_XPATH = "//div[@class='price-now']/span";

    protected static final String ADD_ITEM_BUTTON_XPATH = "//a[contains(@data-bind, 'addProductLink') and contains(@class, 'large')]";

    protected static final String MANUFACTURED_NUMBER_OF_PRODUCT_XPATH = "//span[@itemprop='mpn']";

    protected static final String ALT_MANUFACTURED_NUMBER_OF_PRODUCT_XPATH =
            "//div[@class='product-main-info-partnumber-manufacturer']";

    protected static final String MENU_TREE_XPATH = "//a[@class='breadcrumb-item-link']";

    protected static final String NO_IN_DATABASE_ERROR_TEXT_XPATH = "//div[@class='row mAlert no-gutter mAlert-info']";

    protected static final String POSITIVE_REVIEW_VOTE_BUTTON_XPATH = "//button[contains(@class, 'review-useful-yes')]";

    protected static final String NEGATIVE_REVIEW_WOTE_BUTTON_XPATH = "//button[contains(@data-bind, 'click: downVote')]";

    protected static final String NUMBER_OF_POSITIVE_VOTES_TEXT_XPATH = "//span[@data-bind='text: countOfLikes']";

    protected static final String TOTAL_NUMBER_OF_VOTES_TEXT_XPATH = "//span[@data-bind='text: totalVotes']";

    protected static final String PRODUCT_HAS_REVIEWS_XPATH = "//h3[@class='review-title']";

    protected static final String PRODUCT_REVIEW_AUTHOR_LINK_XPATH = " //a[contains(@href, '/user/')][1]";

    protected static final String USER_REVIEW_LIST_DIV_XPATH = "//div[contains(@class, 'review-list')]";

    protected static final String RESPONSIVE_REVIEW_BUTTON_XPATH = "//section[contains(@class, 'reviews')]//button";

    protected static final String RESPONSIVE_REVIEW_AREA_XPATH = "//section[contains(@class, 'reviews') and contains(@class, 'isopen')]";

    protected static final String START_ADD_REVIEW_BUTTON_XPATH = "//div[contains(@class, 'review-rating-average')]//button";

    protected static final String REVIEW_TITLE_INPUT_XPATH = "//div[@class='review-input-set']//input[@data-bind='textInput:fields.title']";

    protected static final String REVIEW_AUTHOR_INPUT_XPATH = "//div[@class='review-input-set']//input[@data-bind='textInput:fields.nickname']";

    protected static String REVIEW_STARS_BUTTONS_XPATH = "//label[@title='4']";

    protected static final String REVIEW_CONTENT_INPUT_XPATH = "//textarea[@id='WriteReview']";

    protected static final String SUBMIT_REVIEW_BUTTON_XPATH = "//button[contains(@class, 'write-review-publish-button')]";

    protected static final String REVIEW_SUCCESS_MESSAGE_XPATH = "//div[contains(@class, 'mAlert-success')]";

    protected static final String ADD_TO_COMPARISON_LINK_XPATH = "//span[@class='addToCompare']//a";

    protected static final String COMPARISON_PAGE_DIV_XPATH = "//div[@class='prod-compare']";

    protected static final String PRODUCT_MANUFACTURER_LINK_XPATH = "//a[@class='product-manufacturer']";

    public String getProductNameText() {
        ThreadLogger.getThreadLogger().info(String.format("Getting the name of product by xpath = '%s'",
                PRODUCT_NAME_TEXT_XPATH));
        String result = "";
        if(driver.isElementPresentAndVisibleOnPage(PRODUCT_NAME_TEXT_XPATH)) {
            result = driver.getTextByXpath(PRODUCT_NAME_TEXT_XPATH);
        }
        else {
            result = driver.getTextByXpath(ALT_PRODUCT_NAME_TEXT_XPATH);
        }
        ThreadLogger.getThreadLogger().info(String.format("Result is '%s'", result));
        return result;
    }

    public ShoppingCartPage addItemToShoppingCart() {
        ThreadLogger.getThreadLogger().info("Add a product to shopping cart");
        driver.clickByXpath(ADD_ITEM_BUTTON_XPATH);
        return new ShoppingCartPage();
    }

    public ProductPage fillInReviewFormAndSubmit(String title, String author, String content, String username, String password) {
        ThreadLogger.getThreadLogger().info("Fill in review form and submit the review");
        driver.clickByXpath(START_ADD_REVIEW_BUTTON_XPATH);
        BasePage base = new BasePage();
        MainPage main = new MainPage();
        if(base.isLoginPopupOpen()) {
            main.login(username, password);
        }

        driver.inputTextByXpath(REVIEW_TITLE_INPUT_XPATH, title);
        driver.inputTextByXpath(REVIEW_AUTHOR_INPUT_XPATH, author);
        driver.clickByXpath(REVIEW_STARS_BUTTONS_XPATH);
        driver.inputTextByXpath(REVIEW_CONTENT_INPUT_XPATH, content);
        driver.clickByXpath(SUBMIT_REVIEW_BUTTON_XPATH);
        driver.waitForJQueryProcessing(10);
        return this;
    }

    public boolean isReviewSuccessVisible() {
        ThreadLogger.getThreadLogger().info("Is review success message visible?");
        boolean visible = driver.isElementPresentAndVisibleOnPage(REVIEW_SUCCESS_MESSAGE_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("The result is '%s'", visible));
        return visible;
    }

    public boolean hasReviews() {
        ThreadLogger.getThreadLogger().info("Does the product have any reviews?");
        boolean reviews = driver.isElementPresentAndVisibleOnPage(PRODUCT_HAS_REVIEWS_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("The result is '%s'", reviews));
        return reviews;
    }

    public ProductPage rateReviewPositive() {
        ThreadLogger.getThreadLogger().info("Giving positive vote on a review");
        driver.clickByXpath(POSITIVE_REVIEW_VOTE_BUTTON_XPATH);
        return this;
    }

    public ProductPage rateReviewNegative() {
        ThreadLogger.getThreadLogger().info("Giving negative vote on a review");
        driver.clickByXpath(NEGATIVE_REVIEW_WOTE_BUTTON_XPATH);
        return this;
    }

    public ProductPage clickOnAuthor() {
        ThreadLogger.getThreadLogger().info("Click on review author");
        driver.clickByXpath(PRODUCT_REVIEW_AUTHOR_LINK_XPATH);
        return this;
    }

    public boolean isUserSharedContentVisible() {
        ThreadLogger.getThreadLogger().info("Are all reviews by user displayed on user shared content page?");
        boolean userReviews = driver.isElementPresentAndVisibleOnPage(USER_REVIEW_LIST_DIV_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("The result is '%s'", userReviews));
        return userReviews;
    }

    public boolean isReviewsVisibleOnMDSize() {
        BasePage base = new BasePage();
        base.resizeBrowser(817, 974);
        driver.clickByXpath(RESPONSIVE_REVIEW_BUTTON_XPATH);

        return driver.isElementPresentAndVisibleOnPage(RESPONSIVE_REVIEW_AREA_XPATH);
    }

    public int getNumberOfPositiveVotesForReview() {
        ThreadLogger.getThreadLogger().info("Getting the number of good votes for review");
        if(driver.isElementPresentAndVisibleOnPage(NUMBER_OF_POSITIVE_VOTES_TEXT_XPATH)) {
            return Integer.parseInt(driver.getTextByXpath(NUMBER_OF_POSITIVE_VOTES_TEXT_XPATH));
        }
        return 0;
    }

    public int getTotalNumberOfVotesForReview() {
        ThreadLogger.getThreadLogger().info("Getting the total number of votes for review");
        if(driver.isElementPresentAndVisibleOnPage(TOTAL_NUMBER_OF_VOTES_TEXT_XPATH)) {
            return Integer.parseInt(driver.getTextByXpath(TOTAL_NUMBER_OF_VOTES_TEXT_XPATH));
        }
        return 0;
    }

    public String getManufacturedNumber() {
        String result;
        if(driver.isElementPresentAndVisibleOnPage(MANUFACTURED_NUMBER_OF_PRODUCT_XPATH)) {
            ThreadLogger.getThreadLogger().info(String.format("Getting the manufactured number of product by xpath = '%s'",
                    MANUFACTURED_NUMBER_OF_PRODUCT_XPATH));
            result = driver.getTextByXpath(MANUFACTURED_NUMBER_OF_PRODUCT_XPATH);
        }
        else {
            result = driver.getTextByXpath(ALT_MANUFACTURED_NUMBER_OF_PRODUCT_XPATH);
            result = result.split(" ")[2];
        }
        ThreadLogger.getThreadLogger().info(String.format("Result is '%s'", result));
        return result;
    }

    public List<String> getMenuTree() {
        ThreadLogger.getThreadLogger().info("Getting the menu tree to the product");
        List<String> result = new ArrayList<>();
        result = driver.getTextOfElements(MENU_TREE_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is '%s'", result));
        return result;
    }

    public boolean isNoInDatabaseErrorPresent() {
        ThreadLogger.getThreadLogger().info(String.format("Is product absence in database error present by xpath = '%s'",
                NO_IN_DATABASE_ERROR_TEXT_XPATH));
        boolean result = driver.isElementPresentAndVisibleOnPage(NO_IN_DATABASE_ERROR_TEXT_XPATH);
        ThreadLogger.getThreadLogger().info(String.format("Result is '%s'", result));
        return result;
    }

    /* Comparison */
    public ProductPage addToComparison() {
        ThreadLogger.getThreadLogger().info("Adding product to comparison");
        driver.clickByXpath(ADD_TO_COMPARISON_LINK_XPATH);
        return this;
    }

    public boolean isOnComparisonPage() {
        ThreadLogger.getThreadLogger().info("Are we on comparison page?");
        return driver.isElementPresentAndVisibleOnPage(COMPARISON_PAGE_DIV_XPATH);
    }

    public ProductPage clickToSeeAllProductsByManufacturer() {
        ThreadLogger.getThreadLogger().info("Clicking to see all product from manufacturer");
        driver.clickByXpath(PRODUCT_MANUFACTURER_LINK_XPATH);
        return this;
    }
}
