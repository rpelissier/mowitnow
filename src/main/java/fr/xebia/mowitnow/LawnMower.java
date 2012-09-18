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
    Point point;
    CardinalDirection direction;
    StateChangedEventHandler stateChangedEH = StateChangedEventHandler.NULL;

    public LawnMower(Lawn lawn, Point initPoint, CardinalDirection initDirection) {
        this.lawn = lawn;
        this.point = initPoint;
        this.direction = initDirection;
    }

    public LawnMower(Lawn lawn, CardinalDirection direction) {
        this(lawn, Point.ZERO, direction);
    }

    public void setStateChangedEH(StateChangedEventHandler stateChangedEH) {
        if (stateChangedEH == null) {
            this.stateChangedEH = StateChangedEventHandler.NULL;
        } else {
            this.stateChangedEH = stateChangedEH;
        }
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
        stateChangedEH.stateChanged(point, direction);

    }

    public interface StateChangedEventHandler {

        public static final StateChangedEventHandler NULL = new StateChangedEventHandler() {

            public void stateChanged(Point newPoint, CardinalDirection newDirection) {
                //Do nothing
            }
        };

        public void stateChanged(Point newPoint, CardinalDirection newDirection);
    }

    public enum Command {

        G,
        D,
        A;
    }
}
