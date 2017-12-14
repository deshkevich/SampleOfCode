package no.komplett.tests.suits.shopping;

import no.komplett.tests.screens.CheckoutPage;
import no.komplett.tests.screens.MainPage;
import no.komplett.tests.screens.ProductPage;
import no.komplett.tests.screens.ShoppingCartPage;
import no.komplett.tests.utils.common.ExcelReader;
import no.komplett.tests.utils.data.KomplettPlatform;
import no.komplett.tests.utils.data.customer.BusinessCustomer;
import no.komplett.tests.utils.data.customer.PrivateCustomer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by a.dziashkevich on 4/15/15.
 */
public class PurchaseProductUponReceiptTest {
    PrivateCustomer user;

    BusinessCustomer businessCustomer;

    MainPage mainPage;

    protected static final String DATA_TEST = "products";

    @DataProvider
    public static Object[][] platforms() {
        return new Object[][] {
                {KomplettPlatform.TEST_KOMPLETT_NO, "Takk for bestillingen"},
                {KomplettPlatform.TEST_KOMPLETT_SE, "Ditt ordernummer är"},
                {KomplettPlatform.TEST_KOMPLETT_DK, "Dit ordrenummer"}
        };
    }

    @Test(description = "Purchase a product with payment invoice early authorization", dataProvider = "platforms")
         public void purchaseProductUsingPaymentDeferralEarlyRegistration(KomplettPlatform platform, String confirmation)
            throws InterruptedException {
        user = new PrivateCustomer(platform);
        mainPage = new MainPage(platform);
        mainPage.registerNewPrivateCustomer(user)
                .logout()
                .closeBrowser();
        mainPage = new MainPage(platform);
        for(String productId : ExcelReader.getDataSet(platform, DATA_TEST)) {
            mainPage.login(user.getEmail(), user.getPassword());
            ProductPage productPage = mainPage.searchByIdNumber(productId);
            productPage.addItemToShoppingCart();
            ShoppingCartPage cartPage = new ShoppingCartPage();

            if(cartPage.isOnOldCart()) {
                Assert.fail("Platform uses the old cart, cannot proceed with test. Platform: " + platform.getPlatformHost());
            }

            if(cartPage.checkInsurancePopup()) {
                cartPage.closeInsuancePopup();
            }

            CheckoutPage checkoutPage = cartPage.increaseCount(2).goToCheckout();
            Assert.assertTrue(checkoutPage.switchToUponReceiptMethodAndGo().getConfirmationMessage().contains(confirmation),
                    "Verify that product buying successfully");
            mainPage.logout();
        }
    }

    @Test(description = "Purchase a product with payment invoice early authorization business", dataProvider = "platforms")
    public void purchaseProductUsingPaymentDeferralEarlyRegistrationBusinessCustomer
            (KomplettPlatform platform, String confirmation) throws InterruptedException {
        businessCustomer = new BusinessCustomer(platform);
        if(platform.getCountry().equals("se")) {
            businessCustomer.setMobileNumber("0705718017");
        }
        mainPage = new MainPage(platform);
        mainPage.registerNewBusinessCustomer(businessCustomer)
                .logout()
                .closeBrowser();
        mainPage = new MainPage(platform);
        for(String productId : ExcelReader.getDataSet(platform, DATA_TEST)) {
            mainPage.login(businessCustomer.getEmail(), businessCustomer.getPassword());
            ProductPage productPage = mainPage.searchByIdNumber(productId);
            productPage.addItemToShoppingCart();
            ShoppingCartPage cartPage = new ShoppingCartPage();

            if(cartPage.isOnOldCart()) {
                Assert.fail("Platform uses the old cart, cannot proceed with test. Platform: " + platform.getPlatformHost());
            }

            if(cartPage.checkInsurancePopup()) {
                cartPage.closeInsuancePopup();
            }

            CheckoutPage checkoutPage = cartPage.increaseCount(3).goToCheckout();
            Assert.assertTrue(checkoutPage.switchToUponReceiptMethodAndGo().getConfirmationMessage().contains(confirmation),
                    "Verify that product buying successfully");
            mainPage.logout();
        }
    }

    @AfterMethod
    public void tearDown() {
        if(mainPage!=null)
            mainPage.closeBrowser();
    }
}
