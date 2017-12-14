package no.komplett.tests.suits.authorization;

import no.komplett.tests.screens.MainPage;
import no.komplett.tests.utils.common.ExcelReader;
import no.komplett.tests.utils.data.KomplettPlatform;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by a.dziashkevich on 6/22/15.
 */
public class LoginBusinessUserTest {
    MainPage mainPage;

    protected static final String USER_PASSWORD = "123123";
    protected static final String DATA_TEST = "businessUsers";

    @DataProvider
    public static Object[][] platforms() {
        return new Object[][] {
                {KomplettPlatform.TEST_MPX_NO},
                {KomplettPlatform.TEST_KOMPLETT_SE},
                {KomplettPlatform.TEST_KOMPLETT_DK},
                {KomplettPlatform.TEST_BLUSH_NO},
                {KomplettPlatform.TEST_KOMPLETT_NO}
        };
    }

    @Test(description = "Login using specified credentials", dataProvider = "platforms")
    public void loginTest(KomplettPlatform platform) {
        String userEmail = ExcelReader.getRandomValueFromDataSet(platform, DATA_TEST);
        mainPage = new MainPage(platform);
        mainPage.login(userEmail, USER_PASSWORD);
        Assert.assertTrue(mainPage.isUserLoggedIn(), "User was not logged in. Username: " + userEmail + " Password: " + USER_PASSWORD);
        mainPage.logout();
    }

    @Test(description = "Restore password", dataProvider = "platforms", dependsOnMethods = "loginTest")
    public void restoreForgottenPassword(KomplettPlatform platform){
        mainPage = new MainPage(platform);
        Assert.assertTrue(mainPage.isMailRestorePasswordSend(ExcelReader.getRandomValueFromDataSet(platform, DATA_TEST)),
                "Message about sending of password restore not appear");
        mainPage.logout();
    }

    @Test(description = "Negative test: try login with invalid credentials", dataProvider = "platforms",
            dependsOnMethods = "restoreForgottenPassword")
    public void negativeRegistrationAndLogin(KomplettPlatform platform) {
        String userEmail = ExcelReader.getRandomValueFromDataSet(platform, DATA_TEST);
        mainPage = new MainPage(platform);
        mainPage.login(userEmail, "");
        Assert.assertTrue(mainPage.isFailedLoginTextAppear(), "Message about failed authorization not appear" +
                " in case null password");
        mainPage.closeFailedLoginMassage().login("", "");
        Assert.assertTrue(mainPage.isFailedLoginTextAppear(), "Message about failed authorization not appear" +
                " in case null email");
        mainPage.closeFailedLoginMassage().login(userEmail + "wrong", USER_PASSWORD);
        Assert.assertTrue(mainPage.isFailedLoginTextAppear(), "Message about failed authorization not appear" +
                " in case wrong email");
        mainPage.closeFailedLoginMassage().login(userEmail, USER_PASSWORD + "wrong");
        Assert.assertTrue(mainPage.isFailedLoginTextAppear(), "Message about failed authorization not appear" +
                " in case wrong password");
    }

    @AfterMethod
    public void tearDown() {
        if(mainPage!=null)
            mainPage.closeBrowser();
    }
}
