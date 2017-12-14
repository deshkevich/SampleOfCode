package no.komplett.tests.utils.common;

import org.apache.log4j.Logger;

/**
 * Created by a.dziashkevich on 2/6/15.
 */
public class ThreadLogger {
    private static ThreadLocal<Logger> loggerHolder = new ThreadLocal<Logger>();
    private static ThreadLocal<Logger> browserLoggerHolder = new ThreadLocal<Logger>();

    public static void setLogger(Logger logger) {
        loggerHolder.set(logger);
    }

    public static Logger getThreadLogger() {
        if (loggerHolder.get() == null) {
            loggerHolder.set(Logger.getLogger(String.valueOf(Thread.currentThread().getId())));
        }
        return loggerHolder.get();
    }

    public static void setBrowserLogger(Logger logger) {
        browserLoggerHolder.set(logger);
    }

    public static Logger getBrowserLogger() {
        if (browserLoggerHolder.get() == null) {
            browserLoggerHolder.set(Logger.getLogger(String.valueOf(Thread.currentThread().getId())));
        }
        return browserLoggerHolder.get();
    }
}
