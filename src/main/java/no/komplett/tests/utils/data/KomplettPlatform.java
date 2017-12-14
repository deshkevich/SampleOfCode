package no.komplett.tests.utils.data;

/**
 * Created by a.dziashkevich on 2/10/15.
 */
public enum KomplettPlatform {
    DEV_BLUSH_NO("https://dev.blush.no/", "no"),
    TEST_BLUSH_NO("https://test.blush.no/", "no"),
    DEV_MPX_NO("https://dev.mpx.no/", "no"),
    TEST_MPX_NO("https://test.mpx.no/", "no"),
    DEV_KOMPLETT_NO("https://dev.komplett.no/", "no"),
    TEST_KOMPLETT_NO("https://test.komplett.no/", "no"),
    DEV_KOMPLETT_SE("https://dev.komplett.se", "se"),
    DEV_KOMPLETT_DK("https://dev.komplett.dk", "dk"),
    TEST_KOMPLETT_SE("https://test.komplett.se", "se"),
    TEST_KOMPLETT_DK("https://test.komplett.dk", "dk"),
    BETA_KOMPLETT_NO("https://test.beta.komplett.no", "no");

    private String platformHost;
    private String country;

    private KomplettPlatform(String platformHost, String country) {
        this.platformHost = platformHost;
        this.country = country;
    }

    public String getPlatformHost() {
        return platformHost;
    }

    public String getCountry() {
        return country;
    }

    public static KomplettPlatform toPlatform(String url) {
        for (KomplettPlatform platform : KomplettPlatform.values()) {
            if (url.endsWith(platform.platformHost))
                return platform;
        }
        throw new RuntimeException("Unknown host: " + url);
    }

}
