package no.komplett.tests.utils.common;

import no.komplett.tests.utils.selenium.AbstractWebDriver;
import no.komplett.tests.utils.selenium.CommonWebDriver;
import org.testng.*;

/**
 * Created by a.dziashkevich on 2/11/15.
 */
public class TestngEventsListiner implements ITestListener, IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        ThreadLogger.getThreadLogger().info("TEST METHOD STARTED: " + iTestResult.getMethod());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ThreadLogger.getThreadLogger().info("TEST METHOD SUCCESSFULLY FINISHED: " + iTestResult.getMethod());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ThreadLogger.getThreadLogger().error("TEST METHOD FAILED: " + iTestResult.getMethod());
        handleFailure(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        ThreadLogger.getThreadLogger().warn("TEST METHOD SKIPPED: " + iTestResult.getMethod());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        AbstractWebDriver.deleteScreenshots();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private void handleFailure(ITestResult result) {
        if (CommonWebDriver.getCurrentDriver() != null) {
            CommonWebDriver driver = CommonWebDriver.getCurrentDriver();
            String url = driver.getCurrentUrl().replace("https://", "").replace("/", ".");
            if(url.length() > 43) {
                url = url.substring(0, 43);
            }
            CommonWebDriver.getCurrentDriver().doScreenshot(String.format("%s - Test Fail %s", url , getCaseName(result)));
        }
    }

    protected String getCaseName(ITestResult result) {
        return result.getTestClass().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();
    }
}
