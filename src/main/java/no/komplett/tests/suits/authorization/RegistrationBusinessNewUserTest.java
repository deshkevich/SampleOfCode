package no.komplett.tests.suits.authorization;

import no.komplett.tests.screens.MainPage;
import no.komplett.tests.utils.data.KomplettPlatform;
import no.komplett.tests.utils.data.customer.BusinessCustomer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by a.dziashkevich on 3/26/15.
 */
public class RegistrationBusinessNewUserTest {
    MainPage mainPage;

    @DataProvider
    public static Object[][] platforms() {
        return new Object[][] {
                {KomplettPlatform.TEST_MPX_NO},
                {KomplettPlatform.TEST_KOMPLETT_DK},
                {KomplettPlatform.TEST_KOMPLETT_SE},
                {KomplettPlatform.TEST_BLUSH_NO},
                {KomplettPlatform.TEST_KOMPLETT_NO}
        };
    }

    @Test(description = "Register new customer, logout and login using specified credentials", dataProvider = "platforms")
    public void positiveRegistrationAndLogin(KomplettPlatform platform) {
        BusinessCustomer user = new BusinessCustomer(platform);
        mainPage = new MainPage(platform);
        mainPage.registerNewBusinessCustomer(user);
        Assert.assertEquals(mainPage.getPresentlyLoggedUser(), user.getBusinessName(), "Registration was not" +
                " successfully - actual logged business customer first and last names not equal to specified");
        mainPage.logout();
    }

    @AfterMethod
    public void tearDown() {
        if(mainPage!=null)
            mainPage.closeBrowser();
    }
}
