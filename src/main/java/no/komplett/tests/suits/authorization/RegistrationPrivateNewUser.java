package no.komplett.tests.suits.authorization;

import no.komplett.tests.screens.MainPage;
import no.komplett.tests.utils.data.KomplettPlatform;
import no.komplett.tests.utils.data.customer.PrivateCustomer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by a.dziashkevich on 2/3/15.
 */
public class RegistrationPrivateNewUser {
    MainPage mainPage;

    @DataProvider
    public static Object[][] platforms() {
        return new Object[][] {
                {KomplettPlatform.DEV_BLUSH_NO},
                {KomplettPlatform.DEV_KOMPLETT_SE},
                {KomplettPlatform.DEV_KOMPLETT_DK},
                {KomplettPlatform.DEV_KOMPLETT_NO}
        };
    }

    @Test(description = "Register new customer, logout and login using specified credentials", dataProvider = "platforms")
    public void positiveRegistrationAndLogin(KomplettPlatform platform) {
        PrivateCustomer user = new PrivateCustomer(platform);
        mainPage = new MainPage(platform);
        mainPage.registerNewPrivateCustomer(user);
        Assert.assertEquals(mainPage.getPresentlyLoggedUser(), user.getFirstName() + " " + user.getLastName(),
                "Registration was not successfully - actual logged customer first and last names not equal to specified");
        mainPage.logout();
    }

    @AfterMethod
    public void tearDown() {
        if(mainPage!=null)
            mainPage.closeBrowser();
    }
}
