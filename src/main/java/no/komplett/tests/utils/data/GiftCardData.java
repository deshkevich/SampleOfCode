package no.komplett.tests.utils.data;

/**
 * Created by a.dziashkevich on 4/8/15.
 */
public class GiftCardData {
    private String amount;

    private String quantity;

    private String toAddress;

    private String fromAddress;

    private String email;

    private String reserveEmail;

    private String message;

    public GiftCardData() {
        this.amount = "50";
        this.quantity = "1";
        this.toAddress = "Autotest";
        this.fromAddress = "Autotest";
        this.email = "test@post.test";
        this.reserveEmail = "test@post.test";
        this.message = "test message";
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReserveEmail() {
        return reserveEmail;
    }

    public void setReserveEmail(String reserveEmail) {
        this.reserveEmail = reserveEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
