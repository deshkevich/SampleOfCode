package no.komplett.tests.suits.shopping;

import no.komplett.tests.screens.CheckoutPage;
import no.komplett.tests.screens.MainPage;
import no.komplett.tests.utils.data.KomplettPlatform;
import no.komplett.tests.utils.data.customer.BusinessCustomer;
import no.komplett.tests.utils.data.customer.PrivateCustomer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by a.dziashkevich on 4/8/15.
 */
public class GiftCardTest {
    PrivateCustomer user;

    BusinessCustomer businessCustomer;

    MainPage mainPage;

    @Test(description = "Buy a GiftCard with early authorization")
    public void giftCardPurchase() {
        user = new PrivateCustomer(KomplettPlatform.TEST_KOMPLETT_NO);
        mainPage = new MainPage(KomplettPlatform.TEST_KOMPLETT_NO);
        mainPage.registerNewPrivateCustomer(user);
        mainPage.logout();
        mainPage.closeBrowser();
        mainPage = new MainPage(KomplettPlatform.TEST_KOMPLETT_NO);
        mainPage.login(user.getEmail(), user.getPassword());
        String confirmationMessage = mainPage
                .goToGiftCard()
                .buyGiftCard()
                .fillDataToGiftCardPurchase()
                .fillCardDataAndGo()
                .getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("Takk for bestillingen"),
                "Verify that gift card buying successfully");
        mainPage.logout();
    }

    @Test(description = "Buy a GiftCard with late authorization")
    public void giftCardPurchaseLateLogin() {
        user = new PrivateCustomer(KomplettPlatform.TEST_KOMPLETT_NO);
        mainPage = new MainPage(KomplettPlatform.TEST_KOMPLETT_NO);
        mainPage.registerNewPrivateCustomer(user);
        mainPage.logout();
        mainPage.closeBrowser();
        mainPage = new MainPage(KomplettPlatform.TEST_KOMPLETT_NO);
        CheckoutPage checkoutPage = (CheckoutPage) mainPage
                .goToGiftCard()
                .buyGiftCard()
                .fillDataToGiftCardPurchase()
                .login(user.getEmail(), user.getPassword());
        String confirmationMessage = checkoutPage
                .fillCardDataAndGo()
                .getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("Takk for bestillingen"),
                "Verify that gift card buying successfully with late login");
        mainPage.logout();
    }

    @Test(description = "Buy a GiftCard with early authorization business customer")
    public void giftCardPurchaseBusinessCustomer() {
        businessCustomer = new BusinessCustomer(KomplettPlatform.TEST_KOMPLETT_NO);
        mainPage = new MainPage(KomplettPlatform.TEST_KOMPLETT_NO);
        mainPage.registerNewBusinessCustomer(businessCustomer);
        mainPage.logout();
        mainPage.closeBrowser();
        mainPage = new MainPage(KomplettPlatform.TEST_KOMPLETT_NO);
        mainPage.login(businessCustomer.getEmail(), businessCustomer.getPassword());
        String confirmationMessage = mainPage
                .goToGiftCard()
                .buyGiftCard()
                .fillDataToGiftCardPurchase()
                .fillCardDataAndGo()
                .getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("Takk for bestillingen"),
                "Verify that gift card buying successfully");
        mainPage.logout();
    }

    @Test(description = "Buy a GiftCard with late authorization business customer")
    public void giftCardPurchaseLateLoginBusinessCustomer() {
        businessCustomer = new BusinessCustomer(KomplettPlatform.TEST_KOMPLETT_NO);
        mainPage = new MainPage(KomplettPlatform.TEST_KOMPLETT_NO);
        mainPage.registerNewBusinessCustomer(businessCustomer);
        mainPage.logout();
        mainPage.closeBrowser();
        mainPage = new MainPage(KomplettPlatform.TEST_KOMPLETT_NO);
        CheckoutPage checkoutPage = (CheckoutPage) mainPage
                .goToGiftCard()
                .buyGiftCard()
                .fillDataToGiftCardPurchase()
                .login(businessCustomer.getEmail(), businessCustomer.getPassword());
        String confirmationMessage = checkoutPage
                .fillCardDataAndGo()
                .getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("Takk for bestillingen"),
                "Verify that gift card buying successfully with late login");
        mainPage.logout();
    }

    @AfterMethod
    public void tearDown() {
        if(mainPage!=null)
            mainPage.closeBrowser();
    }
}
