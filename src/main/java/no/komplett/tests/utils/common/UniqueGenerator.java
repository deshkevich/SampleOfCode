package no.komplett.tests.utils.common;

import java.util.Random;

/**
 * Created by a.dziashkevich on 2/10/15.
 */
public class UniqueGenerator {

    public static String getRandomText(int len) {
        StringBuilder b = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i<len;i++) {
            char c = (char)(65+r.nextInt(25));
            b.append(c);
        }
        return b.toString();
    }

    public static String getRandomNumber(int len) {
        String s = "1";
        Random r = new Random();
        for (int i = 0; i<len-1;i++) {
            s += String.valueOf(r.nextInt(9));
        }
        return s;
    }
}
