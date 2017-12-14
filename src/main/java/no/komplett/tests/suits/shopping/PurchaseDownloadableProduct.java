package no.komplett.tests.suits.shopping;

import no.komplett.tests.screens.*;
import no.komplett.tests.utils.common.ExcelReader;
import no.komplett.tests.utils.data.KomplettPlatform;
import no.komplett.tests.utils.data.customer.BusinessCustomer;
import no.komplett.tests.utils.data.customer.PrivateCustomer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by a.dziashkevich on 4/16/15.
 */
public class PurchaseDownloadableProduct {
    PrivateCustomer user;

    BusinessCustomer businessCustomer;

    MainPage mainPage;

    protected static final String DATA_TEST = "downloadableProduct";

    protected static final String CONFIRMATION_MESSAGE = "Takk for bestillingen";

    private static final String ERROR_MESSAGE = "Du har allerede kjøpt spillet du forsøkte å legge til handlevognen";

    @Test(description = "Purchase a downloadable product")
    public void purchaseDownloadableProduct()
            throws InterruptedException {
        KomplettPlatform platform = KomplettPlatform.DEV_KOMPLETT_NO;
        user = new PrivateCustomer(platform);
        mainPage = new MainPage(platform);
        mainPage.registerNewPrivateCustomer(user)
                .logout()
                .closeBrowser();
        mainPage = new MainPage(platform);
        for(String productId : ExcelReader.getDataSet(platform, DATA_TEST)) {
            mainPage.login(user.getEmail(), user.getPassword());
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
            Assert.assertTrue(confirmationMessage.contains(CONFIRMATION_MESSAGE),
                    "Verify that product buying by credit card successfully");
            Thread.sleep(1000*60*5);
            confirmationPurchasePage.goToAccountPage().goToDigitalProductPage();
            mainPage.closeBrowser();
            mainPage = new MainPage(platform);
            mainPage.login(user.getEmail(), user.getPassword());
            String errorMessage = mainPage
                    .searchByIdNumber(productId)
                    .addItemToShoppingCart()
                    .getErrorMessageText();
            Assert.assertEquals(errorMessage, ERROR_MESSAGE, "Verify that error message displayed with repeatable" +
                    " purchase of downloadable product");
            mainPage.logout();
        }
    }

    @Test(description = "Purchase a downloadable product business customer")
    public void purchaseDownloadableProductBusiness()
            throws InterruptedException {
        KomplettPlatform platform = KomplettPlatform.DEV_KOMPLETT_NO;
        businessCustomer = new BusinessCustomer(platform);
        mainPage = new MainPage(platform);
        mainPage.registerNewBusinessCustomer(businessCustomer)
                .logout()
                .closeBrowser();
        mainPage = new MainPage(platform);
        for(String productId : ExcelReader.getDataSet(platform, DATA_TEST)) {
            mainPage.login(businessCustomer.getEmail(), businessCustomer.getPassword());
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
            Assert.assertTrue(confirmationMessage.contains(CONFIRMATION_MESSAGE),
                    "Verify that product buying by credit card successfully");
            confirmationPurchasePage.goToAccountPage().goToDigitalProductPage();
            Thread.sleep(1000*60*5);
            mainPage.closeBrowser();
            mainPage = new MainPage(platform);
            mainPage.login(businessCustomer.getEmail(), businessCustomer.getPassword());
            String errorMessage = mainPage
                    .searchByIdNumber(productId)
                    .addItemToShoppingCart()
                    .getErrorMessageText();
            Assert.assertEquals(errorMessage, ERROR_MESSAGE, "Verify that error message displayed with repeatable" +
                    " purchase of downloadable product");
            mainPage.logout();
        }
    }

    @AfterMethod
    public void tearDown() {
        if(mainPage!=null)
            mainPage.closeBrowser();
    }
}
