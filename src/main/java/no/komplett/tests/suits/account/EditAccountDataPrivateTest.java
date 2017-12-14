package no.komplett.tests.suits.account;

import no.komplett.tests.screens.*;
import no.komplett.tests.utils.common.ExcelReader;
import no.komplett.tests.utils.common.UniqueGenerator;
import no.komplett.tests.utils.data.KomplettPlatform;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by a.dziashkevich on 7/2/15.
 */
public class EditAccountDataPrivateTest {

    MainPage mainPage;

    protected static final String USER_PASSWORD = "123123";
    protected static final String DATA_TEST = "privateUsers";
    protected static final String DATA_TEST_PRODUCT = "products";

    @DataProvider
    public static Object[][] platformsPrivate() {
        return new Object[][] {
                {KomplettPlatform.TEST_KOMPLETT_SE},
                {KomplettPlatform.TEST_KOMPLETT_DK},
                {KomplettPlatform.TEST_BLUSH_NO},
                {KomplettPlatform.TEST_KOMPLETT_NO}
        };
    }

    @Test(description = "Edit account details", dataProvider = "platformsPrivate")
    public void editAccountDetailsPrivateCustomer(KomplettPlatform platform) {
        String userEmail = ExcelReader.getRandomValueFromDataSet(platform, DATA_TEST);
        String oldFname = userEmail.substring(0, userEmail.indexOf("@"));
        String oldLname = userEmail.substring(userEmail.indexOf("@") + 1,  userEmail.indexOf("."));
        mainPage = new MainPage(platform);

        mainPage.login(userEmail, USER_PASSWORD);
        Assert.assertTrue(mainPage.isUserLoggedIn(), "User is not logged in, cant continue with test");
        EditContactDataPage editPage = mainPage
                .goToAccountPage()
                .goToContactPage()
                .goToEditContactData(oldFname + " " + oldLname);

        String newFname = UniqueGenerator.getRandomText(8);
        String newLname = UniqueGenerator.getRandomText(8);
        String newPass = UniqueGenerator.getRandomText(6);

        editPage.editFirstName(newFname)
                .editLastName(newLname)
                .editEmail(newFname + "@" + newLname + ".com")
                .editPassword(newPass)
                .changeValueOfSendInformationCheckbox();
        Boolean isSendInformationActive = editPage.isSendInformationAboutCheckboxActive();
        ContactPage contactPage = editPage.saveChanges();
        Assert.assertTrue(contactPage.isCustomerPresentInCustomersList(newFname + " " + newLname),
                "Name not changed on 'contacts' page");
        editPage = contactPage.goToEditContactData(newFname + " " + newLname);
        Assert.assertEquals(editPage.getFirstnameValue(), newFname, "First name not saved on 'edit contacts' page");
        Assert.assertEquals(editPage.getLastnameValue(), newLname, "Last name not saved on 'edit contacts' page");
        Assert.assertEquals(editPage.getEmailValue(), newFname + "@" + newLname + ".com",
                "Email not saved on 'edit contacts' page");
        Assert.assertEquals(editPage.isSendInformationAboutCheckboxActive(), isSendInformationActive,
                "Checkbox 'send information about' not saved on 'edit contacts' page");

        editPage.goToAccountPage()
                .goToContactPage()
                .goToEditContactData(newFname + " " + newLname)
                .editFirstName(oldFname)
                .editLastName(oldLname)
                .editEmail(userEmail)
                .editPassword(USER_PASSWORD)
                .saveChanges()
                .logout();
        mainPage.logout();
    }

    @Test(description = "Add new contact", dataProvider = "platformsPrivate")
    public void addNewContactPrivateCustomer(KomplettPlatform platform) {
        String userEmail = ExcelReader.getRandomValueFromDataSet(platform, DATA_TEST);
        mainPage = new MainPage(platform);
        mainPage.login(userEmail, USER_PASSWORD);
        Assert.assertTrue(mainPage.isUserLoggedIn(), "User is not logged in, cant continue with test");
        EditContactDataPage editPage = mainPage
                .goToAccountPage()
                .goToContactPage()
                .addNewContact();

        String newFname = UniqueGenerator.getRandomText(8);
        String newLname = UniqueGenerator.getRandomText(8);
        String newPass = UniqueGenerator.getRandomText(6);
        String mobileNumber = UniqueGenerator.getRandomNumber(8);
        String phoneNumber = UniqueGenerator.getRandomNumber(8);

        editPage.editFirstName(newFname)
                .editLastName(newLname)
                .editEmail(newFname + "@" + newLname + ".com")
                .editPassword(newPass)
                .editMobile(mobileNumber)
                .editPhone(phoneNumber);
        ContactPage contactPage = editPage.saveChanges();
        Assert.assertTrue(contactPage.isCustomerPresentInCustomersList(newFname + " " + newLname),
                "Name not changed on 'contacts' page");
        contactPage.logout();
        mainPage.login(newFname + "@" + newLname + ".com", newPass);
        Assert.assertTrue(userEmail.replace('@', ' ').contains(mainPage.getPresentlyLoggedUser()),
                "Login was not successfully - actual logged private customer first and last names not equal to specified");
        mainPage.logout();
    }

    @Test(description = "Edit deliver address", dataProvider = "platformsPrivate")
    public void editDeliverAddressPrivateCustomer(KomplettPlatform platform) {
        String userEmail = ExcelReader.getRandomValueFromDataSet(platform, DATA_TEST);
        mainPage = new MainPage(platform);
        mainPage.login(userEmail, USER_PASSWORD);
        Assert.assertTrue(mainPage.isUserLoggedIn(), "User is not logged in, cant continue with test");
        EditAddressPage editAddressPage = mainPage
                .goToAccountPage()
                .goToAddressPage()
                .goToEditAddress();

        String newContactName = UniqueGenerator.getRandomText(8);
        String newAddress = UniqueGenerator.getRandomText(8);
        String phoneNumber = UniqueGenerator.getRandomNumber(8);

        editAddressPage.editName(newContactName)
                .editAddress(newAddress)
                .editContactPerson(newContactName)
                .editPhoneNumber(phoneNumber)
                .checkAddressType()
                .checkOrganisationRadio()
                .saveChanges();

        String productId = ExcelReader.getRandomValueFromDataSet(platform, DATA_TEST_PRODUCT);
        ProductPage productPage = mainPage.searchByIdNumber(productId);
        ShoppingCartPage cartPage = productPage.addItemToShoppingCart();
        if(cartPage.isOnOldCart()) {
            Assert.fail("Platform uses the old cart, cannot proceed with test. Platform: " + platform.getPlatformHost());
        }
        if(cartPage.checkInsurancePopup()) {
            cartPage.closeInsuancePopup();
        }
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        Assert.assertEquals(checkoutPage.getNameOfCustomer(), newContactName, "Name of customer not changed or saved");
        Assert.assertEquals(checkoutPage.getAddressOfCustomer(), newAddress, "Address of customer not changed or saved");
        Assert.assertEquals(checkoutPage.getContactPersonName(), newContactName, "Contact name not changed or saved");
        Assert.assertTrue(checkoutPage.getContactPersonPhone().contains(phoneNumber), "Contact phone not changed or saved");
        mainPage.logout();
    }

    @Test(description = "New deliver address", dataProvider = "platformsPrivate")
    public void newDeliverAddressPrivateCustomer(KomplettPlatform platform) {
        String userEmail = ExcelReader.getRandomValueFromDataSet(platform, DATA_TEST);
        mainPage = new MainPage(platform);
        mainPage.login(userEmail, USER_PASSWORD);
        Assert.assertTrue(mainPage.isUserLoggedIn(), "User is not logged in, cant continue with test");
        EditAddressPage editAddressPage = mainPage
                .goToAccountPage()
                .goToAddressPage()
                .addNewAddress();

        String newContactName = UniqueGenerator.getRandomText(8);
        String newAddress = UniqueGenerator.getRandomText(8);
        String phoneNumber = UniqueGenerator.getRandomNumber(8);
        String postNumber = null;
        String location = null;
        switch(platform.getCountry()) {
            case "no":
                postNumber = "3241";
                location = "SANDEFJORD";
                break;
            case "se":
                postNumber = "20001";
                location = "MALMÖ";
                break;
            case "dk":
                postNumber = "2840";
                location = "Holte";
                break;
            default:
                break;
        }

       editAddressPage.editName(newContactName)
                .editAddress(newAddress)
                .editContactPerson(newContactName)
                .editPhoneNumber(phoneNumber)
                .editPostNumberAndCheckIsCorrectAutofill(postNumber, location)
                .checkAddressType()
                .checkOrganisationRadio()
                .saveChanges();

        String productId = ExcelReader.getRandomValueFromDataSet(platform, DATA_TEST_PRODUCT);
        ProductPage productPage = mainPage.searchByIdNumber(productId);
        ShoppingCartPage cartPage = productPage.addItemToShoppingCart();
        if(cartPage.isOnOldCart()) {
            Assert.fail("Platform uses the old cart, cannot proceed with test. Platform: " + platform.getPlatformHost());
        }
        if(cartPage.checkInsurancePopup()) {
            cartPage.closeInsuancePopup();
        }
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectAddress(newContactName);
        Assert.assertEquals(checkoutPage.getNameOfCustomer(), newContactName, "Name of customer not changed or saved");
        Assert.assertEquals(checkoutPage.getAddressOfCustomer(), newAddress, "Address of customer not changed or saved");
        Assert.assertEquals(checkoutPage.getContactPersonName(), newContactName, "Contact name not changed or saved");
        Assert.assertTrue(checkoutPage.getContactPersonPhone().contains(phoneNumber), "Contact phone not changed or saved");
        mainPage.logout();
    }

    @Test(description = "Check links on 'My account' page", dataProvider = "platformsPrivate")
    public void checkLinksPrivateCustomer(KomplettPlatform platform) {
        String userEmail = ExcelReader.getRandomValueFromDataSet(platform, DATA_TEST);

        mainPage = new MainPage(platform);
        mainPage.login(userEmail, USER_PASSWORD);
        Assert.assertTrue(mainPage.isUserLoggedIn(), "User is not logged in, cant continue with test");
        AccountPage accountPage = mainPage.goToAccountPage();

        for(String link : accountPage.getLinksFromClientPart()) {
            if(!link.equals("https://test.komplett.no/k/account.aspx?mode=viewLoanAccount")) //this page skipped))
                Assert.assertTrue(accountPage.linkExists(link), String.format("Link '%s' not gives HTTP 200", link));
        }
        mainPage.logout();
    }

    @AfterMethod
    public void tearDown() {
        if(mainPage!=null)
            mainPage.closeBrowser();
    }
}
