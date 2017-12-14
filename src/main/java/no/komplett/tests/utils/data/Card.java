package no.komplett.tests.utils.data;

/**
 * Created by a.dziashkevich on 4/8/15.
 */
public class Card {
    private String cardNumber;

    private String expiredMonth;

    private String expiredYear;

    private String cvvCode;

    public Card() {
        this.cardNumber = "4111111111111111";
        this.expiredMonth = "11";
        this.expiredYear = "2015";
        this.cvvCode = "123";
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiredMonth() {
        return expiredMonth;
    }

    public void setExpiredMonth(String expiredMonth) {
        this.expiredMonth = expiredMonth;
    }

    public String getExpiredYear() {
        return expiredYear;
    }

    public void setExpiredYear(String expiredYear) {
        this.expiredYear = expiredYear;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }
}
