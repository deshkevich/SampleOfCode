package no.komplett.tests.screens;

import no.komplett.tests.utils.common.ThreadLogger;
import no.komplett.tests.utils.data.GiftCardData;

/**
 * Created by a.dziashkevich on 4/8/15.
 */
public class GiftCardFillingDataPage extends MainPage {
    protected static final String AMOUNT_FIELD_XPATH = "//input[@name='amt']";

    protected static final String QUANTITY_FIELD_XPATH = "//input[@name='qty']";

    protected static final String TO_FIELD_XPATH = "//input[@name='to']";

    protected static final String FROM_FIELD_XPATH = "//input[@name='from']";

    protected static final String EMAIL_ONE_FIELD_XPATH = "//input[@name='email1']";

    protected static final String EMAIL_TWO_FIELD_XPATH = "//input[@name='email2']";

    protected static final String MESSAGE_FIELD_XPATH = "//textarea[@name='msg']";

    protected static final String BUY_BUTTON_XPATH = "//input[@alt='Gå til kassen']";

    public CheckoutPage fillDataToGiftCardPurchase() {
        ThreadLogger.getThreadLogger().info("Fill buy gift card fields");
        GiftCardData giftCardData = new GiftCardData();
        driver.inputTextByXpath(AMOUNT_FIELD_XPATH, giftCardData.getAmount());
        driver.inputTextByXpath(QUANTITY_FIELD_XPATH, giftCardData.getQuantity());
        driver.inputTextByXpath(TO_FIELD_XPATH, giftCardData.getToAddress());
        driver.inputTextByXpath(FROM_FIELD_XPATH, giftCardData.getFromAddress());
        driver.inputTextByXpath(EMAIL_ONE_FIELD_XPATH, giftCardData.getEmail());
        driver.inputTextByXpath(EMAIL_TWO_FIELD_XPATH, giftCardData.getReserveEmail());
        driver.inputTextByXpath(MESSAGE_FIELD_XPATH, giftCardData.getMessage());
        driver.clickByXpath(BUY_BUTTON_XPATH);
        return new CheckoutPage();
    }

}
