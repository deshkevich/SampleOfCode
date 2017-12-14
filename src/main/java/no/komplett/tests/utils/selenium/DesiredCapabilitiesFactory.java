package no.komplett.tests.utils.selenium;

import no.komplett.tests.utils.common.PropertiesReader;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

/**
 * Created by a.dziashkevich on 2/4/15.
 */
public class DesiredCapabilitiesFactory {
    public static DesiredCapabilities provideDesiredCapabilities() {
        DesiredCapabilities capabilities;
        String browser = PropertiesReader.getProperty("browser");
        switch (browser) {
            case "FIREFOX":
                FirefoxProfile fp = new FirefoxProfile();
                capabilities = DesiredCapabilities.firefox();
                capabilities.setBrowserName("firefox");
                capabilities.setCapability(FirefoxDriver.PROFILE, fp);
                break;
            case "OPERA":
                capabilities = DesiredCapabilities.opera();
                capabilities.setBrowserName("opera");
                capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
                capabilities.setCapability(CapabilityType.VERSION, "11.62");
                capabilities.setCapability("opera.binary", "C:\\Program Files (x86)\\Opera\\launcher.exe");
                break;
            case "SAFARI":
                capabilities = DesiredCapabilities.safari();
                capabilities.setBrowserName("safari");
                break;
            case "IE10":
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setVersion(Integer.toString(10));
                capabilities.setBrowserName("internet explorer");
                break;
            case "IE11":
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setVersion(Integer.toString(11));
                capabilities.setBrowserName("internet explorer");
                break;
            default:
                capabilities = DesiredCapabilities.chrome();
                capabilities.setBrowserName("chrome");
                capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        }
        String platform = PropertiesReader.getProperty("platform");
        switch(platform) {
            case "LINUX":
                capabilities.setPlatform(Platform.LINUX);
                break;
            case "MAC":
                capabilities.setPlatform(Platform.MAC);
                break;
            default:
                System.setProperty("os.name","windows");
                capabilities.setPlatform(Platform.WINDOWS);
                break;
        }
        capabilities.setJavascriptEnabled(true);
        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, Level.ALL);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
        return capabilities;
    }
}
