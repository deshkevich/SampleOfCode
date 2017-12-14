package no.komplett.tests.utils.selenium;

import no.komplett.tests.utils.common.PropertiesReader;
import no.komplett.tests.utils.common.ThreadLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.internal.Nullable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.dziashkevich on 2/3/15.
 */
public class CommonWebDriver extends AbstractWebDriver {
    private int timeForWaiting = Integer.parseInt(PropertiesReader.getProperty("timeout"));

    private static ThreadLocal<CommonWebDriver> webDriverHolder = new ThreadLocal<CommonWebDriver>();

    public CommonWebDriver(URL remoteAddress, Capabilities desiredCapabilities) {
        super(remoteAddress, desiredCapabilities);
    }

    public static CommonWebDriver getCommonWebDriver() {
        CommonWebDriver driver;
        if ((driver = webDriverHolder.get()) == null) {
            DesiredCapabilities capabilities = DesiredCapabilitiesFactory.provideDesiredCapabilities();
            URL remoteDriverUrl = null;
            try {
                remoteDriverUrl = new URL(PropertiesReader.getProperty("remote.driver.url"));
            } catch (MalformedURLException e) {
                ThreadLogger.getThreadLogger().error(e.getLocalizedMessage());
                e.printStackTrace();
            }
            driver = new CommonWebDriver(remoteDriverUrl, capabilities);
            webDriverHolder.set(driver);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static CommonWebDriver getCurrentDriver() {
        return webDriverHolder.get();
    }

    public static void clearDriverHolder() {
        webDriverHolder.remove();
    }

    /**
     * Waits until element with specified xpath locator is present and visible
     * Use explicit wait by selenium library WebDriverWait
     *
     * @param locator the element xpath-style locator
     * @param timeout the maximum waiting time in seconds
     */
    public void waitForElementPresentAndVisible(String locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(this, timeout);
        ThreadLogger.getThreadLogger().info(String.format("Driver action: wait to presence ant visibility of element" +
                " with xpath = '%s'", locator));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        } catch (Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }
    }

    public boolean isElementPresentVisibleClickable(String locator) {
        WebDriverWait wait = new WebDriverWait(this, timeForWaiting);
        ThreadLogger.getThreadLogger().info(String.format("Driver action: wait to presence ant visibility of element" +
                " with xpath = '%s'", locator));
        boolean b = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).isEnabled() &&
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator))).isEnabled();
        return b;
    }

    public boolean isElementPresentAndVisibleOnPage(String locator) {
        waitForPageToLoad();
        waitForJQueryProcessing(timeForWaiting);
        try {
            Boolean result = findElementsByXPath(locator).size() > 0 && findElementByXPath(locator).isDisplayed();
            ThreadLogger.getThreadLogger().info(String.format("Driver action: element with xpath '%s'" +
                    " present and visible: %s", locator, result));

            return result;
        } catch (Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }
        return false;
    }

    /**
     * Waits when element with specified xpath locator became clickable
     * Use explicit wait by selenium library WebDriverWait
     *
     * @param locator the element xpath-style locator
     * @param timeout the maximum waiting time in seconds
     */
    public void waitForElementClickable(String locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(this, timeout);
        ThreadLogger.getThreadLogger().info(String.format("Driver action: wait to clickability of element" +
                " with xpath = '%s'", locator));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        } catch (Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }

    }

    /**
     * Waits when javascript on page return message that page is load
     * Use explicit wait by selenium library WebDriverWait
     *
     * @param timeout the maximum waiting time in seconds
     */
    public void waitForPageToLoad(int timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(this, timeout);
        webDriverWait.until(new ExpectedCondition<Boolean>() {
            Object result;

            @Override
            public Boolean apply(@Nullable WebDriver webdriver) {
                String script = "return document.readyState";
                result = ((JavascriptExecutor) webdriver).executeScript(script);
                if (result != null) {
                    if (!(result instanceof String)) {
                        ThreadLogger.getThreadLogger().warn(String.format("Executing script: %s return not string" +
                                " result, current result type is: %s", script, result.getClass().getName()));
                        return false;
                    }
                    if (!((String) result).equalsIgnoreCase("complete")) {
                        ThreadLogger.getThreadLogger().info(String.format("Page not loaded yet, current readyState" +
                                " value is: %s", result));
                        return false;
                    } else {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * Waits until javascript on page return message that page is load with default value
     * Use explicit wait by selenium library WebDriverWait
     */
    public void waitForPageToLoad() {
        waitForPageToLoad(timeForWaiting);
    }

    /**
     * Waits until jQuery on page is complete
     * Use explicit wait by selenium library WebDriverWait
     *
     * @param timeout the maximum waiting time in seconds
     */
    public void waitForJQueryProcessing(int timeout) {
        Boolean aBoolean = (Boolean) executeScript("return window.jQuery != undefined");
        if (aBoolean) {
            new WebDriverWait(this, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driverObject) {
                    return (Boolean) ((JavascriptExecutor) driverObject)
                            .executeScript("var j = window.jQuery; if (j != undefined ) {" +
                                    " return j.active == 0} else {return true}");
                }
            });
        }
    }

    /**
     * Click on element by specified xpath locator with default waiting time
     *
     * @param locator the element xpath-style locator
     */
    public void clickByXpath(String locator) {
        clickByXpath(locator, timeForWaiting);
    }

    /**
     * Click on element by specified xpath locator
     *
     * @param locator the element xpath-style locator
     * @param timeout the maximum waiting time in seconds
     */
    public void clickByXpath(String locator, int timeout) {
        ThreadLogger.getThreadLogger().info(String.format("Driver action: click to element with xpath = '%s'", locator));
        waitForPageToLoad();
        waitForJQueryProcessing(timeForWaiting);

        try {
            waitForElementPresentAndVisible(locator, timeout);
            waitForElementClickable(locator, timeout);
            findElementByXPath(locator).click();
            //waitForPageToLoad();
        } catch(Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }

    }

    public void selectIfNotSelectedByXpath(String locator) {
        ThreadLogger.getThreadLogger().info(String.format("Driver action: select element with xpath = '%s'", locator));
        waitForPageToLoad();
        try {
            waitForJQueryProcessing(timeForWaiting);
            waitForElementPresentAndVisible(locator, timeForWaiting);
            waitForElementClickable(locator, timeForWaiting);
            for(int i=0; i<5; i++) {
                if(!findElementByXPath(locator).isSelected()) {
                    findElementByXPath(locator).click();
                }
                else break;
            }
            waitForPageToLoad();
        } catch (Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }
    }

    public void changeCheckboxValue(String locator) {
        ThreadLogger.getThreadLogger().info(String.format("Driver action: select element with xpath = '%s'", locator));
        waitForPageToLoad();
        waitForJQueryProcessing(timeForWaiting);
        try {
            waitForElementPresentAndVisible(locator, timeForWaiting);
            waitForElementClickable(locator, timeForWaiting);
            findElementByXPath(locator).click();
            waitForPageToLoad();
        } catch (Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }
    }

    public Boolean isSelectedByXpath(String locator) {
        waitForPageToLoad();
        waitForJQueryProcessing(timeForWaiting);
        try {
            return findElementByXPath(locator).isSelected();
        } catch(Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }
        return false;
    }

    /**
     * Open page by specified URL
     *
     * @param url URL page opens
     */
    public void get(String url) {
        super.get(url);
        waitForPageToLoad();
        waitForJQueryProcessing(timeForWaiting);
    }

    /**
     * Get a text from web-element by specified xpath
     *
     * @param locator the element xpath-style locator
     */
    public String getTextByXpath(String locator) {
        ThreadLogger.getThreadLogger().info(String.format("Driver action: Get the text from element with xpath %s'",
                locator));
        waitForPageToLoad();
        waitForJQueryProcessing(timeForWaiting);
        try {
            waitForElementPresentAndVisible(locator, timeForWaiting);
            String result = findElementByXPath(locator).getText();
            ThreadLogger.getThreadLogger().info(String.format("Driver action: Result is: '%s'", result));
            return result;
        } catch (Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }
        return null;
    }

    /**
     * Input text to web-element by specified xpath
     *
     * @param locator the element xpath-style locator
     * @param text    input text
     */
    public void inputTextByXpath(String locator, String text) {
        ThreadLogger.getThreadLogger().info(String.format("Driver action: Input text '%s' to element with xpath %s'",
                text, locator));
        try {
            waitForElementPresentAndVisible(locator, timeForWaiting);
            findElementByXPath(locator).clear();
            findElementByXPath(locator).sendKeys(text);
        } catch (Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }
    }

    public void switchToFrameByXpath(String locator) {
        ThreadLogger.getThreadLogger().info(String.format("Driver action: Switch to frame with xpath %s'", locator));
        try {
            waitForElementPresentAndVisible(locator, timeForWaiting);
            switchTo().frame(findElementByXPath(locator));
        } catch (Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }

    }

    public String getContentOfElementByXpath(String locator) {
        ThreadLogger.getThreadLogger().info(String.format("Driver action: get content from element %s'", locator));
        try {
            return findElementByXPath(locator).getAttribute("content");
        } catch (Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }
        return null;
    }

    public String getValueOfElementByXpath(String locator) {
        ThreadLogger.getThreadLogger().info(String.format("Driver action: get value from element %s'", locator));
        try {
            return findElementByXPath(locator).getAttribute("value");
        } catch(Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }
        return null;
    }

    public Boolean isTextPresentOnPage(String text) {
        ThreadLogger.getThreadLogger().info(String.format("Driver action: find on page the next text %s'", text));
        Boolean result = getPageSource().contains(text);
        ThreadLogger.getThreadLogger().info(String.format("Result is %s'", result));
        return result;
    }

    public void selectOptionByValue(String locator, String option) {
        try {
            Select dropdown = new Select(findElementByXPath(locator));
            dropdown.selectByValue(option);
        } catch (Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }
    }

    public void selectOptionByPartOfText(String locator, String option) {
        try {
            Select dropdown = new Select(findElementByXPath(locator));
            for(WebElement e : dropdown.getOptions())
            {
                if(e.getText().contains(option)) {
                    dropdown.selectByVisibleText(e.getText());
                    break;
                }
            }
        } catch (Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }
    }

    public List<String> getTextOfElements(String locator) {
        List<String> textArray = new ArrayList<>();
        try {
            for(WebElement e : findElementsByXPath(locator)) {
                textArray.add(e.getText());
            }
        } catch (Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }
        return textArray;
    }

    public void submitByXpath(String locator) {
        ThreadLogger.getThreadLogger().info(String.format("Driver action: submit to element with xpath = '%s'", locator));
        waitForPageToLoad();
        waitForJQueryProcessing(timeForWaiting);
        try {
            waitForElementPresentAndVisible(locator, timeForWaiting);
            waitForElementClickable(locator, timeForWaiting);
            findElementByXPath(locator).submit();
            waitForPageToLoad();
        } catch (Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }
    }

    public String getHrefByXpath(String locator) {
        ThreadLogger.getThreadLogger().info(String.format("Driver action: get href from element %s'", locator));

        try {
            return findElementByXPath(locator).getAttribute("href");
        } catch (Exception e) {
            Assert.fail("Could not find element using locator: " + locator + "");
        }
        return null;
    }
}
