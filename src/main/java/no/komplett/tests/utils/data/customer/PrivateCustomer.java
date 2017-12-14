package no.komplett.tests.utils.data.customer;

import no.komplett.tests.utils.common.UniqueGenerator;
import no.komplett.tests.utils.data.KomplettPlatform;

/**
 * Created by a.dziashkevich on 2/10/15.
 */
public class PrivateCustomer {
    private String firstName;

    private String lastName;

    private String address;

    private int postNumber;

    private String location;

    private String mobileNumber;

    private String email;

    private String password;

    private KomplettPlatform platform;

    private int customerId;

    public PrivateCustomer(KomplettPlatform platform) {
        this.firstName = UniqueGenerator.getRandomText(8);
        this.lastName = UniqueGenerator.getRandomText(8);
        this.address = "Kruthusgatan 17";
        switch(platform.getCountry()) {
            case "no":
                this.postNumber = 3241;
                this.location = "SANDEFJORD";
                this.mobileNumber = "90806334";
                break;
            case "se":
                this.postNumber = 20001;
                this.location = "MALMÖ";
                this.mobileNumber = "0705718017";
                break;
            case "dk":
                this.postNumber = 2840;
                this.location = "Holte";
                this.mobileNumber = "81511571";
                break;
            default:
                break;
        }
        this.email = firstName + "@" + lastName + ".com";
        this.password = "123123";
        this.platform = platform;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public KomplettPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(KomplettPlatform platform) {
        this.platform = platform;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
