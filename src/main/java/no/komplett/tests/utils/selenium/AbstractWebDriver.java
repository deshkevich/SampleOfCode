package no.komplett.tests.utils.selenium;

import no.komplett.tests.utils.common.PropertiesReader;
import no.komplett.tests.utils.common.ThreadLogger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by a.dziashkevich on 2/3/15.
 */
public class AbstractWebDriver extends RemoteWebDriver {

    public AbstractWebDriver(URL remoteAddress, Capabilities desiredCapabilities) {
        super(remoteAddress, desiredCapabilities);
    }

    public static void deleteScreenshots() {
        try {
            String dir = PropertiesReader.getProperty("screenshot.path");
            FileUtils.deleteDirectory(new File(dir));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void doScreenshot(String driverCommand) {
        Snapshoter snapshoter = new Snapshoter();
        String filePath = snapshoter.getSnapshotName(driverCommand) + ".png";
        String base64 = execute(DriverCommand.SCREENSHOT).getValue().toString();
        File scrFile = OutputType.FILE.convertFromBase64Png(base64);
        try {
            FileUtils.copyFile(scrFile, new File(filePath));
        } catch (IOException e) {
            ThreadLogger.getThreadLogger().error(String.format("Error copy screenshot file from %s to %s", scrFile.getAbsolutePath(), filePath));
        }
    }
}