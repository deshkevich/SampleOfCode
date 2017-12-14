package no.komplett.tests.suits.responsive;

import no.komplett.tests.screens.MainPage;
import no.komplett.tests.utils.data.KomplettPlatform;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by a.dziashkevich on 7/1/15.
 */
public class ResponsiveTest {

    @DataProvider
    public static Object[][] platforms() {
        return new Object[][] {
                {KomplettPlatform.TEST_KOMPLETT_NO},
                {KomplettPlatform.TEST_KOMPLETT_SE}
        };
    }

    @Test(dataProvider = "platforms")
    public void testResponsiveDesign(KomplettPlatform platform) {
        MainPage mainPage = new MainPage(platform);
        mainPage.resizeBrowser(800, 1000);
        Assert.assertTrue(mainPage.isResponsiveSM(), "SM design is not presence");
        Assert.assertFalse(mainPage.isResponsiveXS(), "Extra small design presence but not should");
        mainPage.resizeBrowser(500, 1000);
        Assert.assertTrue(mainPage.isResponsiveXS(), "Extra small design is not presence");
        mainPage.closeBrowser();
    }
}
