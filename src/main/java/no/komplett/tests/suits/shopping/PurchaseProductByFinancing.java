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
import org.testng.annotations.Test;

/**
 * Created by a.dziashkevich on 4/14/15.
 */
public class PurchaseProductByFinancing {
    PrivateCustomer user;

    BusinessCustomer businessCustomer;

    MainPage mainPage;

    protected static final String DATA_PRODUCTS = "products";
    protected static final String USER_PASSWORD = "123123";
    protected static final String DATA_PRIVATE_USERS = "privateUsers";
    protected static final String DATA_BUSINESS_USERS = "businessUsers";

    @Test(description = "Purchase a product with bank account early authorization")
    public void purchaseProductUsingBankAccountEarlyRegistration()
            throws InterruptedException {
        KomplettPlatform platform = KomplettPlatform.DEV_KOMPLETT_NO;
        for(String productId : ExcelReader.getDataSet(platform, DATA_PRODUCTS)) {
            mainPage = new MainPage(platform);

            String userEmail = ExcelReader.getRandomValueFromDataSet(platform, DATA_PRIVATE_USERS);
            mainPage.login(userEmail, USER_PASSWORD);
            Assert.assertTrue(mainPage.isUserLoggedIn(), "User is not logged in, cant continue with test");

            ProductPage productPage = mainPage.searchByIdNumber(productId);
            productPage.addItemToShoppingCart();
            ShoppingCartPage cartPage = new ShoppingCartPage();
            if(cartPage.isOnOldCart()) {
                Assert.fail("Platform uses the old cart, cannot proceed with test. Platform: " + platform.getPlatformHost());
            }

            if(cartPage.checkInsurancePopup()) {
                cartPage.closeInsuancePopup();
            }

            int support = 1;
            while(cartPage.getTotalPriceText() < 1000) {
                support += 1;
                cartPage.increaseCount(support);
            }

            CheckoutPage checkoutPage = cartPage.goToCheckout();
            Assert.assertTrue(checkoutPage.switchToBankAccountMethodAndGo().isFinancingIdentificationPageLoaded(),
                    "Verify that product buying successfully");
            mainPage.closeBrowser();
        }
    }

    /*
    //TODO: This failes because of illegal organization number
    @Test(description = "Purchase a product with bank account early authorization business")
    public void purchaseProductUsingBankAccountEarlyRegistrationBusinessUser()
            throws InterruptedException {
        KomplettPlatform platform = KomplettPlatform.DEV_KOMPLETT_NO;
        for(String productId : ExcelReader.getDataSet(platform, DATA_PRODUCTS)) {
            mainPage = new MainPage(platform);

            String userEmail = ExcelReader.getRandomValueFromDataSet(platform, DATA_BUSINESS_USERS);
            mainPage.login(userEmail, USER_PASSWORD);
            Assert.assertTrue(mainPage.isUserLoggedIn(), "User is not logged in, cant continue with test");

            ProductPage productPage = mainPage.searchByIdNumber(productId);
            productPage.addItemToShoppingCart();
            ShoppingCartPage cart = new ShoppingCartPage();

            if(cart.checkInsurancePopup()) {
                cart.closeInsuancePopup();
            }

            int support = 1;
            while(cart.getTotalPriceText() < 6000) {
                support += 1;
                cart.increaseCount(support);
            }

            CheckoutPage checkoutPage = cart.goToCheckout();
            Assert.assertTrue(checkoutPage.switchToBankAccountMethodAndGoBusiness().isFinancingIdentificationPageLoaded(),
                    "Verify that product buying successfully");
            mainPage.closeBrowser();
        }
    }

    */

    @AfterMethod
    public void tearDown() {
        if(mainPage!=null)
            mainPage.closeBrowser();
    }
}
