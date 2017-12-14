package no.komplett.tests.suits.search;

import no.komplett.tests.screens.MainPage;
import no.komplett.tests.screens.ProductPage;
import no.komplett.tests.utils.data.KomplettPlatform;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.dziashkevich on 4/30/15.
 */
public class PositiveSearch {

    MainPage mainPage;

    @DataProvider
    public static Object[][] input() {
        return new Object[][] {
                {KomplettPlatform.DEV_KOMPLETT_NO, "803060", "GP CR2032 Lithium, 3V, 10 pack"},
                {KomplettPlatform.DEV_KOMPLETT_NO, "778748", "EVGA GeForce GTX 680 2GB Mac Edition"},
                {KomplettPlatform.DEV_KOMPLETT_NO, "629084", "HDMI 1.4 til micro HDMI med Ethernet 2m"},
                {KomplettPlatform.DEV_KOMPLETT_SE, "803060", "GP CR2032 Lithium, 3V, 10 pack"},
                {KomplettPlatform.DEV_KOMPLETT_SE, "778748", "EVGA GeForce GTX 680 2GB Mac Edition"},
                {KomplettPlatform.DEV_KOMPLETT_SE, "629084", "HDMI 1.4 til micro HDMI med Ethernet 2m"},
                {KomplettPlatform.DEV_KOMPLETT_DK, "803060", "GP CR2032 Lithium, 3V, 10 pack"},
                {KomplettPlatform.DEV_KOMPLETT_DK, "778748", "EVGA GeForce GTX 680 2GB Mac Edition"},
                {KomplettPlatform.DEV_KOMPLETT_DK, "629084", "HDMI 1.4 til micro HDMI med Ethernet 2m"},
                {KomplettPlatform.DEV_MPX_NO, "803060", "GP CR2032 Lithium, 3V, 10 pack"},
                {KomplettPlatform.DEV_MPX_NO, "778748", "EVGA GeForce GTX 680 2GB Mac Edition"},
                {KomplettPlatform.DEV_MPX_NO, "629084", "HDMI 1.4 til micro HDMI med Ethernet 2m"}
        };
    }

    @DataProvider
    public static Object[][] platforms() {
        return new Object[][] {
                {KomplettPlatform.DEV_KOMPLETT_NO},
                {KomplettPlatform.DEV_KOMPLETT_SE},
                {KomplettPlatform.DEV_KOMPLETT_DK},
                {KomplettPlatform.DEV_MPX_NO}
        };
    }

    @Test(description = "Search stuff different ways", dataProvider = "input")
    public void positiveSearch(KomplettPlatform platform, String productId, String description) {
        mainPage = new MainPage(platform);
        ProductPage productPage = mainPage.searchByIdNumber(productId);
        String upperMenuItem = null;
        String rightMenuItem = null;
        String subcategoryItem = null;
        String manufacturedNumber = productPage.getManufacturedNumber();
        List<String> menuItems = new ArrayList<>();
        //if(!platform.getPlatformHost().contains("mpx")) {
        if(false) {
                for(String item : productPage.getMenuTree()) {
                    menuItems.add(item);
                }
            upperMenuItem = menuItems.get(1);
            rightMenuItem = menuItems.get(2);
            subcategoryItem = menuItems.get(3);
        }
        Assert.assertTrue(productPage.getProductNameText().contains(description),
                "Verify product was searched by productId");
        mainPage = new MainPage(platform);
        Assert.assertTrue(mainPage.searchByDescription(description).
                isSearchedProductInResults(description), "Verify product was searched by description");
        mainPage = new MainPage(platform);
        Assert.assertTrue(mainPage.searchByManufacturedNumber(manufacturedNumber)
                .isSearchedProductInResults(description), "Verify product was searched by manufactured number");
        //if(!platform.getPlatformHost().contains("mpx")) {
        if(false) {
            mainPage = new MainPage(platform);
            boolean result = mainPage
                    .goToUpperMenuPoint(upperMenuItem)
                    .goToRightMenuPoint(rightMenuItem)
                    .goToSubcategoryMenuPoint(subcategoryItem)
                    .isSearchedProductInResults(description);
            Assert.assertTrue(result, "Verify product was searched by menu points");
        }
        mainPage.closeBrowser();
    }

/*    @Test(description = "Negative search no in database error", dataProvider = "platforms")
    public void negativeSearchNoInDatabaseError(KomplettPlatform platform) {
        String productId = "772256";
        MainPage mainPage = new MainPage(platform);
        ProductPage productPage = mainPage.searchByIdNumber(productId);
        Assert.assertTrue(productPage.isNoInDatabaseErrorPresent(),
                "Is error absence product in database present on page");
        mainPage.closeBrowser();
    }

    @Test(description = "Negative search autocorrection warning", dataProvider = "platforms")
    public void negativeSearchAutocorrectionWarning(KomplettPlatform platform) {
        String description = "smasung";
        MainPage mainPage = new MainPage(platform);
        SearchResultsPage searchResultsPage = mainPage.searchByDescription(description);
        Assert.assertTrue(searchResultsPage.isAutocorrectionWarningPresent(),
                "Is autocorrection search warning present on page");
        mainPage.closeBrowser();
    }*/

    @AfterMethod
    public void tearDown() {
        if(mainPage!=null)
            mainPage.closeBrowser();
    }
}
