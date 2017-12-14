package no.komplett.tests.suits.responsive;

import no.komplett.tests.screens.MainPage;
import no.komplett.tests.utils.data.KomplettPlatform;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by a.dziashkevich on 6/9/15.
 */
public class ResponsiveBeta {

    private static final String BETA_USER_EMAIL = "betatester@gmail.com";

    private static final String BETA_USER_PASSWORD = "123123";

    @Test
    public void testResponsiveDesign() {
        KomplettPlatform platform = KomplettPlatform.BETA_KOMPLETT_NO;
        MainPage mainPage = new MainPage(platform);
        mainPage.login(BETA_USER_EMAIL, BETA_USER_PASSWORD);
        mainPage.resizeBrowser(800, 1000);
        Assert.assertTrue(mainPage.isResponsiveSM(), "SM design is not presence");
        Assert.assertFalse(mainPage.isResponsiveXS(), "Extra small design presence but not should");
        mainPage.resizeBrowser(500, 1000);
        Assert.assertTrue(mainPage.isResponsiveXS(), "Extra small design is not presence");
        mainPage.closeBrowser();
    }
}
