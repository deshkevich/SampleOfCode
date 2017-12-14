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
public class RegistrationPrivateNewUserTest {
    MainPage mainPage;

    @DataProvider
    public static Object[][] platforms() {
        return new Object[][] {
                {KomplettPlatform.TEST_BLUSH_NO},
                {KomplettPlatform.TEST_KOMPLETT_DK},
                {KomplettPlatform.TEST_KOMPLETT_SE},
                {KomplettPlatform.TEST_KOMPLETT_NO}
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
        mainPage.login(user.getEmail(), user.getPassword());
        Assert.assertEquals(mainPage.getPresentlyLoggedUser(), user.getFirstName() + " " + user.getLastName(),
                "Login was not successfully - actual logged customer first and last names not equal to specified");
        mainPage.logout();
        Assert.assertTrue(mainPage.isMailRestorePasswordSend(user.getEmail()),
                "Message about sending of password restore not appear");
    }

    @Test(description = "Register new customer, logout and login using invalid credentials", dataProvider = "platforms")
    public void negativeRegistrationAndLogin(KomplettPlatform platform) {
        PrivateCustomer user = new PrivateCustomer(platform);
        mainPage = new MainPage(platform);
        mainPage.registerNewPrivateCustomer(user);
        Assert.assertEquals(mainPage.getPresentlyLoggedUser(), user.getFirstName() + " " + user.getLastName(),
                "Registration was not successfully - actual logged customer first and last names not equal to specified");
        mainPage.logout();
        mainPage.login(user.getEmail(), "");
        Assert.assertTrue(mainPage.isFailedLoginTextAppear(), "Message about failed authorization not appear" +
                " in case null password");
        mainPage.closeFailedLoginMassage().login("", "");
        Assert.assertTrue(mainPage.isFailedLoginTextAppear(), "Message about failed authorization not appear" +
                " in case null email");
        mainPage.closeFailedLoginMassage().login(user.getEmail() + "wrong", user.getPassword());
        Assert.assertTrue(mainPage.isFailedLoginTextAppear(), "Message about failed authorization not appear" +
                " in case wrong email");
        mainPage.closeFailedLoginMassage().login(user.getEmail(), user.getPassword() + "wrong");
        Assert.assertTrue(mainPage.isFailedLoginTextAppear(), "Message about failed authorization not appear" +
                " in case wrong password");
        Assert.assertTrue(mainPage.isMailRestorePasswordSend(user.getEmail()),
                "Message about sending of password restore not appear");
    }

    @AfterMethod
    public void tearDown() {
        if(mainPage!=null)
            mainPage.closeBrowser();
    }
}
