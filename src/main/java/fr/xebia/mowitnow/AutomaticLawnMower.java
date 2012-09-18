/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.xebia.mowitnow;

import fr.xebia.mowitnow.Lawn.Point;
import java.util.List;

/**
 *
 * @author Renaud
 */
public class AutomaticLawnMower extends LawnMower implements Runnable {

    protected final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AutomaticLawnMower.class);
    protected final List<Command> commands;

    public AutomaticLawnMower(Lawn lawn, Point initialPoint, CardinalDirection initialDirection, List<Command> commands) {
        super(lawn, initialPoint, initialDirection);
        this.commands = commands;
    }

    public void run() {
        logger.info("Starting");
        logger.info(toString());
        for (Command command : commands) {
            logger.info("CMD " + command.name());
            execute(command);
            logger.info(toString());
        }
        logger.info("Done");
    }
}
