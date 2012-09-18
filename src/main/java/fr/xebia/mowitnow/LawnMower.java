/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.xebia.mowitnow;

import fr.xebia.mowitnow.Lawn.Point;
import org.apache.log4j.Logger;

/**
 *
 * @author Renaud
 */
public class LawnMower {

    private final Logger logger = Logger.getLogger(LawnMower.class);
    final Lawn lawn;
    CardinalDirection direction;
    Point point;

    public LawnMower(Lawn lawn, CardinalDirection direction, Point point) {
        this.lawn = lawn;
        this.direction = direction;
        this.point = point;
    }

    public LawnMower(Lawn lawn, CardinalDirection direction) {
        this(lawn, direction, Point.ZERO);
    }

    public CardinalDirection getDirection() {
        return direction;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return point.toString() + ' ' + direction.name().charAt(0);
    }

    protected void execute(Command cmd) {
        switch (cmd) {
            case G:
                direction = direction.antiClockwiseRotation();
                break;
            case D:
                direction = direction.clockwiseRotation();
                break;
            case A:
                try {
                    point = lawn.existingPointAt(direction, point);
                } catch (Lawn.UnexistingPointException ex) {
                    logger.warn("An attempt to move to an unexisting point was ignored.", ex);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown " + Command.class.getSimpleName() + " '" + cmd + "'.");
        }
    }

    public interface StateChangedEventHandler {

        public void stateChanged(Point previousPoint, CardinalDirection previousDirection, Point currentPoint, CardinalDirection currentDirection);
    }

    public enum Command {

        G,
        D,
        A;
    }
}
