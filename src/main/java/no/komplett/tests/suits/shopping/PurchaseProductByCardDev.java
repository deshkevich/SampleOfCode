package no.komplett.tests.suits.shopping;

import no.komplett.tests.screens.*;
import no.komplett.tests.utils.common.ExcelReader;
import no.komplett.tests.utils.data.KomplettPlatform;
import no.komplett.tests.utils.data.customer.BusinessCustomer;
import no.komplett.tests.utils.data.customer.PrivateCustomer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by a.dziashkevich on 4/28/15.
 */
public class PurchaseProductByCardDev {
    PrivateCustomer user;

    BusinessCustomer businessCustomer;

    MainPage mainPage;

    protected static final String DATA_PRODUCTS = "products";
    protected static final String USER_PASSWORD = "123123";
    protected static final String DATA_PRIVATE_USERS = "privateUsers";
    protected static final String DATA_BUSINESS_USERS = "businessUsers";


    @DataProvider
    public static Object[][] platforms() {
        return new Object[][] {
                {KomplettPlatform.DEV_KOMPLETT_NO, "Takk for bestillingen"},
                {KomplettPlatform.DEV_KOMPLETT_DK, "Dit ordrenummer"},
                {KomplettPlatform.DEV_KOMPLETT_SE, "Ditt ordernummer"}
        };
    }

    @Test(description = "Purchase a product with credit card early authorization", dataProvider = "platforms")
    public void purchaseProductEarlyRegistration(KomplettPlatform platform, String confirmation)
            throws InterruptedException {

        mainPage = new MainPage(platform);

        for (String productId : ExcelReader.getDataSet(platform, DATA_PRODUCTS)) {
            String userEmail = ExcelReader.getRandomValueFromDataSet(platform, DATA_PRIVATE_USERS);
            mainPage.login(userEmail, USER_PASSWORD);
            Assert.assertTrue(mainPage.isUserLoggedIn(), "User is not logged in, cant continue with test");
            ProductPage productPage = mainPage.searchByIdNumber(productId);
            ShoppingCartPage cartPage = productPage.addItemToShoppingCart();
            if(cartPage.isOnOldCart()) {
                Assert.fail("Platform uses the old cart, cannot proceed with test. Platform: " + platform.getPlatformHost());
            }
            if(cartPage.isOnOldCart()) {
                Assert.fail("Platform uses the old cart, cannot proceed with test. Platform: " + platform.getPlatformHost());
            }
            if(cartPage.checkInsurancePopup()) {
                cartPage.closeInsuancePopup();
            }
            CheckoutPage checkoutPage = cartPage.goToCheckout();
            ConfirmationPurchasePage confirmationPurchasePage = checkoutPage.fillCardDataAndGo();
            String confirmationMessage = confirmationPurchasePage.getConfirmationMessage();
            Assert.assertTrue(confirmationMessage.contains(confirmation),
                    "Verify that product buying by credit card successfully");
            ViewOrderPage viewOrderPage = confirmationPurchasePage.goToViewOrderPage();
            Assert.assertTrue(viewOrderPage.waitWhenOrderStatusBecameOpen(productId, 60), "Status not became 'Open'");
            Assert.assertTrue(viewOrderPage.cancelOrdersAndWaitChangeStatus(productId, 60), "Order was not cancelled");
            mainPage.logout();
        }
    }

    @Test(description = "Purchase a product with credit card late authorization", dataProvider = "platforms")
    public void purchaseProductLateRegistration(KomplettPlatform platform, String confirmation)
            throws InterruptedException {
        mainPage = new MainPage(platform);

        for (String productId : ExcelReader.getDataSet(platform, DATA_PRODUCTS)) {
            String userEmail = ExcelReader.getRandomValueFromDataSet(platform, DATA_PRIVATE_USERS);
            ProductPage productPage = mainPage.searchByIdNumber(productId);
            ShoppingCartPage cartPage = productPage.addItemToShoppingCart();
            if(cartPage.isOnOldCart()) {
                Assert.fail("Platform uses the old cart, cannot proceed with test. Platform: " + platform.getPlatformHost());
            }
            if(cartPage.checkInsurancePopup()) {
                cartPage.closeInsuancePopup();
            }
            CheckoutPage checkoutPage = cartPage.goToCheckout();
            mainPage.login(userEmail, USER_PASSWORD);
            Assert.assertTrue(mainPage.isUserLoggedIn(), "User is not logged in, cant continue with test");
            ConfirmationPurchasePage confirmationPurchasePage = checkoutPage.fillCardDataAndGo();
            String confirmationMessage = confirmationPurchasePage.getConfirmationMessage();
            Assert.assertTrue(confirmationMessage.contains(confirmation),
                    "Verify that product buying by credit card successfully");
            ViewOrderPage viewOrderPage = confirmationPurchasePage.goToViewOrderPage();
            Assert.assertTrue(viewOrderPage.waitWhenOrderStatusBecameOpen(productId, 60), "Status not became 'Open'");
            Assert.assertTrue(viewOrderPage.cancelOrdersAndWaitChangeStatus(productId, 60), "Order was not cancelled");
            mainPage.logout();
        }
    }

    @Test(description = "Purchase a product with credit card early authorization business customer",
            dataProvider = "platforms")
    public void purchaseProductEarlyRegistrationBusinessCustomer(KomplettPlatform platform, String confirmation)
            throws InterruptedException {

        mainPage = new MainPage(platform);

        for (String productId : ExcelReader.getDataSet(platform, DATA_PRODUCTS)) {
            String userEmail = ExcelReader.getRandomValueFromDataSet(platform, DATA_BUSINESS_USERS);
            mainPage.login(userEmail, USER_PASSWORD);
            Assert.assertTrue(mainPage.isUserLoggedIn(), "User is not logged in, cant continue with test");
            ProductPage productPage = mainPage.searchByIdNumber(productId);
            ShoppingCartPage cartPage = productPage.addItemToShoppingCart();
            if(cartPage.isOnOldCart()) {
                Assert.fail("Platform uses the old cart, cannot proceed with test. Platform: " + platform.getPlatformHost());
            }
            if(cartPage.checkInsurancePopup()) {
                cartPage.closeInsuancePopup();
            }
            CheckoutPage checkoutPage = cartPage.goToCheckout();
            ConfirmationPurchasePage confirmationPurchasePage = checkoutPage.fillCardDataAndGo();
            String confirmationMessage = confirmationPurchasePage.getConfirmationMessage();
            Assert.assertTrue(confirmationMessage.contains(confirmation),
                    "Verify that product buying by credit card successfully");
            ViewOrderPage viewOrderPage = confirmationPurchasePage.goToViewOrderPage();
            Assert.assertTrue(viewOrderPage.waitWhenOrderStatusBecameOpen(productId, 60), "Status not became 'Open'");
            Assert.assertTrue(viewOrderPage.cancelOrdersAndWaitChangeStatus(productId, 60), "Order was not cancelled");
            mainPage.logout();
        }
    }

    @AfterMethod
    public void tearDown() {
        if(mainPage!=null)
            mainPage.closeBrowser();
    }
}
