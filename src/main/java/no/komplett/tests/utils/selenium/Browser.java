package no.komplett.tests.utils.selenium;

/**
 * Created by a.dziashkevich on 2/4/15.
 */
public enum Browser {
    FIREFOX(0),
    IE6(1),
    IE7(2),
    IE8(3),
    IE9(4),
    OPERA(5),
    CHROME(6),
    SAFARI(7),
    DEFAULT(8);

    private final int id;

    private Browser(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }
}
