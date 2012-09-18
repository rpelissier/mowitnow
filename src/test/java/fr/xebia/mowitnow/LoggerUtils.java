/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.xebia.mowitnow;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 *
 * @author Renaud
 */
public class LoggerUtils {

    public static String PATTERN = "%d{yyyy/MM/dd-HH:mm:ss} [%20.20t] %-5p %c %x \t\t%m%n";

    /**
     * Create a console appender related to every package of the application.
     */
    public static void createGlobalConsoleLogger() {
        createConsoleLogger(Logger.getRootLogger());
    }

    private static void createConsoleLogger(Logger logger) {
        if (logger.getAppender("CONSOLE") == null) {
            Layout layout = new PatternLayout(PATTERN);
            ConsoleAppender consoleAppender = new ConsoleAppender(layout);
            consoleAppender.setName("CONSOLE");
            logger.addAppender(consoleAppender);
        }
    }
}
