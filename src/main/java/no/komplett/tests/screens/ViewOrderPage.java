package no.komplett.tests.screens;

import org.openqa.selenium.Alert;

/**
 * Created by a.dziashkevich on 6/10/15.
 */
public class ViewOrderPage extends MainPage{

    protected static final String ORDER_OPEN_STATUS_CELL_XPATH = "//table[@id='OrderLines']//td[text()='%s']/../td/img[contains(@src, 'stocked')]";

    protected static final String ORDER_CANCELLED_STATUS_CELL_XPATH = "//table[@id='OrderLines']//td[text()='%s']/../td/img[contains(@src, 'outofstock')]";

    protected static final String ORDER_NOT_CANCELLED_CELL_XPATH = "//table[@id='OrderLines']//td[text()='%s']/../td/img[contains(@src, 'stockwarn')]";

    protected static final String CANCEL_ALL_ORDERS_BUTTON_XPATH =
            "//form[@id='formOrderCancel']/input[@class='button-secondary button-medium']";

    public boolean isStatusOfProductOpen(String productNumber) {
        return driver.isElementPresentAndVisibleOnPage(String.format(ORDER_OPEN_STATUS_CELL_XPATH, productNumber));
    }

    public boolean isStatusOfProductCancelled(String productNumber) {
        return driver.isElementPresentAndVisibleOnPage(String.format(ORDER_CANCELLED_STATUS_CELL_XPATH, productNumber));
    }

    public boolean isStatusOfProductRejected(String productNumber) {
        return driver.isElementPresentAndVisibleOnPage(String.format(ORDER_CANCELLED_STATUS_CELL_XPATH, productNumber));
    }

    public boolean waitWhenOrderStatusBecameOpen(String productNumber, int timeout) {
        for(int i = 0; i < timeout/2; i++) {
            if(isStatusOfProductOpen(productNumber)) {
                return true;
            }
            try {
                Thread.sleep(2000);
                driver.navigate().refresh();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean cancelOrdersAndWaitChangeStatus(String productNumber, int timeout) {
        driver.clickByXpath(CANCEL_ALL_ORDERS_BUTTON_XPATH);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        for(int i = 0; i < timeout/2; i++) {
            if(isStatusOfProductCancelled(productNumber) || isStatusOfProductRejected(productNumber)) {
                return true;
            }
            try {
                Thread.sleep(2000);
                driver.navigate().refresh();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
