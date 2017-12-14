package no.komplett.tests.screens;

import no.komplett.tests.utils.common.ThreadLogger;

/**
 * Created by a.dziashkevich on 4/8/15.
 */
public class GiftCardPage extends MainPage {
    protected static final String BUY_GIFT_CARD_BUTTON_XPATH = "//input[@class='image_buy']";

    public GiftCardFillingDataPage buyGiftCard() {
        ThreadLogger.getThreadLogger().info(String.format("Buying gift card clicking by xpath = '%s'",
                BUY_GIFT_CARD_BUTTON_XPATH));
        driver.clickByXpath(BUY_GIFT_CARD_BUTTON_XPATH);
        return new GiftCardFillingDataPage();
    }
}
