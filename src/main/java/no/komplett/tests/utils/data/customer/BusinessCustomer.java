package no.komplett.tests.utils.data.customer;

import no.komplett.tests.utils.common.UniqueGenerator;
import no.komplett.tests.utils.data.KomplettPlatform;

/**
 * Created by a.dziashkevich on 3/26/15.
 */
public class BusinessCustomer  {
    private String businessName;

    private String organizationNumber;

    private String businessAddress;

    private int postNumber;

    private String location;

    private String firstName;

    private String lastName;

    private String mobileNumber;

    private String telephoneNumber;

    private String email;

    private String password;

    private KomplettPlatform platform;

    private int customerId;

    public BusinessCustomer(KomplettPlatform platform) {
        this.businessName = UniqueGenerator.getRandomText(8);
        this.businessAddress = "Kruthusgatan 17";
        switch(platform.getCountry()) {
            case "no":
                this.organizationNumber = "979642121";
                this.postNumber = 3241;
                this.location = "SANDEFJORD";
                break;
            case "se":
                this.organizationNumber = "2620001194";
                this.postNumber = 20001;
                this.location = "MALMÖ";
                break;
            case "dk":
                this.organizationNumber = "56443452";
                this.postNumber = 2840;
                this.location = "Holte";
                break;
            default:
                break;
        }
        this.firstName = UniqueGenerator.getRandomText(8);
        this.lastName = UniqueGenerator.getRandomText(8);
        this.mobileNumber = "33005000";
        this.telephoneNumber = "33005000";
        this.email = businessName + "@com";
        this.password = "123123";
        this.platform = platform;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getOrganizationNumber() {
        return organizationNumber;
    }

    public void setOrganizationNumber(String organizationNumber) {
        this.organizationNumber = organizationNumber;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public KomplettPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(KomplettPlatform platform) {
        this.platform = platform;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
