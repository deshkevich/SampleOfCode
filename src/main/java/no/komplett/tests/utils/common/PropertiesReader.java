package no.komplett.tests.utils.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by a.dziashkevich on 2/5/15.
 */
public class PropertiesReader {
    private static Properties properties;

    private static synchronized Properties loadProperties() {
        if (!(properties == null)) {
            return properties;
        }
        properties = new Properties();
        try {
            System.out.println("Loading from 'file:'conf.properties'");
            properties.load(new FileInputStream("src//main//resources//conf.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String propertyName : properties.stringPropertyNames()) {
            String propertyValue = System.getProperty(propertyName);
            if (propertyValue != null) {
                properties.put(propertyName, propertyValue);
            }
        }
        return properties;
    }

    public static synchronized String getProperty(String key) {
        return loadProperties().getProperty(key);
    }

}
