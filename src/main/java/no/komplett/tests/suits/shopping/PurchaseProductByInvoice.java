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
 * Created by a.dziashkevich on 4/15/15.
 */
public class PurchaseProductByInvoice {
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
                /* {KomplettPlatform.DEV_KOMPLETT_NO, "Takk for bestillingen", "01121579533"},
                {KomplettPlatform.DEV_KOMPLETT_SE, "Ditt ordernummer", "410321-9202"}, */
                {KomplettPlatform.DEV_KOMPLETT_DK, "Dit ordrenummer", "0801363945"}
        };
    }

    @Test(description = "Purchase a product with payment invoice early authorization", dataProvider = "platforms")
    public void purchaseProductInvoiceEarlyRegistration(KomplettPlatform platform, String confirmation, String personalNumber)
            throws InterruptedException {
        mainPage = new MainPage(platform);
        for(String productId : ExcelReader.getDataSet(platform, DATA_PRODUCTS)) {
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

            String[] name = userEmail.split("@");
            String firstname = name[0];
            String lastname = name[1].replace(".com", "");

            if(!checkoutPage.isDeliveryPersonTheSameAsInvoice(firstname, lastname)) {
                checkoutPage.changeDeliveryPersonName(firstname, lastname);
            }

            ConfirmationPurchasePage confirmationPurchasePage = checkoutPage.switchToInvoiceMethodAndGo(personalNumber);
            Assert.assertTrue(confirmationPurchasePage.getConfirmationMessage().contains(confirmation), "Verify that" +
                    " product buying successfully");
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
