package no.komplett.tests.utils.selenium;

import no.komplett.tests.utils.common.PropertiesReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by a.dziashkevich on 2/3/15.
 */
public class Snapshoter {
    private static String SNAPSHOTS_FOLDER = "logs/snapshot";

    public static String getSnapshotName(String driverCommand) {
        String timestamp = nowAsString("yyyy-MM-dd");
        try {
            SNAPSHOTS_FOLDER = PropertiesReader.getProperty("screenshot.path");

            SNAPSHOTS_FOLDER = SNAPSHOTS_FOLDER + "/" + timestamp;
        } catch (Exception e) {
            e.printStackTrace();
        }

        File folder = new File(SNAPSHOTS_FOLDER);

        if (!folder.exists()) {
            try {
                folder.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return folder.getAbsolutePath() + "/" + timestamp + "_" + driverCommand;
    }

    public static String nowAsString(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        return sdf.format(cal.getTime());
    }

    public static void saveSourceToFile(String filename, String source) {
        try {
            PrintWriter output = new PrintWriter(filename + ".html");

            if (source.length() >= "OK,".length()) {
                source = source.substring("OK,".length());
            }


            output.println(source);
            output.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
