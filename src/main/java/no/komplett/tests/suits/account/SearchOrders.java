package no.komplett.tests.suits.account;

import no.komplett.tests.screens.*;
import no.komplett.tests.utils.common.ExcelReader;
import no.komplett.tests.utils.data.KomplettPlatform;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by a.dziashkevich on 7/8/15.
 */
public class SearchOrders {

    MainPage mainPage;

    protected static final String USER_PASSWORD = "123123";
    protected static final String DATA_TEST = "privateUsers";
    protected static final String DATA_TEST_PRODUCT = "products";

    @DataProvider
    public static Object[][] platformsPrivate() {
        return new Object[][] {
                {KomplettPlatform.DEV_KOMPLETT_SE},
                {KomplettPlatform.DEV_KOMPLETT_DK},
                {KomplettPlatform.DEV_BLUSH_NO},
                {KomplettPlatform.DEV_KOMPLETT_NO}
        };
    }

    @Test(description = "Edit account details", dataProvider = "platformsPrivate")
    public void searchOrdersByText(KomplettPlatform platform) {
        String userEmail = ExcelReader.getRandomValueFromDataSet(platform, DATA_TEST);
        String productId = ExcelReader.getRandomValueFromDataSet(platform, DATA_TEST_PRODUCT);
        mainPage = new MainPage(platform);
        mainPage.login(userEmail, USER_PASSWORD);
        ProductPage productPage = mainPage.searchByIdNumber(productId);
        String productNameText = productPage.getProductNameText();
        ShoppingCartPage cartPage = productPage.addItemToShoppingCart();
        if(cartPage.isOnOldCart()) {
            Assert.fail("Platform uses the old cart, cannot proceed with test. Platform: " + platform.getPlatformHost());
        }
        if(cartPage.checkInsurancePopup()) {
            cartPage.closeInsuancePopup();
        }
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        ConfirmationPurchasePage confirmationPurchasePage = checkoutPage.fillCardDataAndGo();
        String ordernumber = confirmationPurchasePage.getOrderNumber();
        AccountPage accountPage = confirmationPurchasePage.goToAccountPage();
        Assert.assertTrue(accountPage.searchByOrderItemNumber(productId).isTextPresentOnPage(productId),"Search by item" +
                "number not performed");
        mainPage.goToAccountPage();
        Assert.assertTrue(accountPage.searchByOrderItemName(productNameText).isTextPresentOnPage(productNameText),
                "Search by item name not performed");
        mainPage.goToAccountPage();
        Assert.assertTrue(accountPage.searchByOrderNumber(ordernumber).isTextPresentOnPage(ordernumber),
                "Search by order number not performed");
        mainPage.logout();
        mainPage.closeBrowser();

        // Cancel the order
        mainPage.goToAccountPage();
        ViewOrderPage viewOrderPage = accountPage.searchByOrderNumber(ordernumber);
        Assert.assertTrue(viewOrderPage.waitWhenOrderStatusBecameOpen(productId, 20), "Status not became 'Open'");
        Assert.assertTrue(viewOrderPage.cancelOrdersAndWaitChangeStatus(productId, 20), "Order was not cancelled");
    }

    @AfterMethod
    public void tearDown() {
        if(mainPage!=null)
            mainPage.closeBrowser();
    }
}
