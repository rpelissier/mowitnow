/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.xebia.mowitnow;

import org.apache.log4j.Logger;

/**
 *
 * @author Renaud
 */
public class Lawn {

    private final int sizeX;
    private final int sizeY;

    public Lawn(int xMax, int yMax) {
        this.sizeX = xMax + 1;
        this.sizeY = yMax + 1;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Point northPoint(Point point) throws UnexistingPointException {
        return existingPointAt(CardinalDirection.North, point);
    }

    public Point southPoint(Point point) throws UnexistingPointException {
        return existingPointAt(CardinalDirection.South, point);
    }

    public Point estPoint(Point point) throws UnexistingPointException {
        return existingPointAt(CardinalDirection.Est, point);
    }

    public Point westPoint(Point point) throws UnexistingPointException {
        return existingPointAt(CardinalDirection.West, point);
    }

    protected Point existingPointAt(CardinalDirection direction, Point point) throws UnexistingPointException {
        Point newPoint = pointAt(direction, point);
        if (isExisting(newPoint)) {
            throw new UnexistingPointException("Moving from " + point + " to " + direction + " "
                    + "drives to and unexisting point " + newPoint + ".");

        }
        return newPoint;
    }

    protected boolean isExisting(Point point) {
        return point.getX() > sizeX || point.getX() < 0 || point.getY() > sizeY || point.getY() < 0;
    }

    protected Point pointAt(CardinalDirection direction, Point point) {
        int x = point.getX();
        int y = point.getY();

        switch (direction) {
            case North:
                y++;
                break;
            case South:
                y--;
                break;
            case Est:
                x++;
                break;
            case West:
                x--;
                break;
            default:
                throw new IllegalArgumentException("Unknown " + CardinalDirection.class.getSimpleName() + " '" + direction + "'.");
        }

        return new Point(x, y);
    }

    public static class Point {

        public static final Point ZERO = new Point(0, 0);
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                Point otherPoint = (Point) obj;
                return otherPoint.x == this.x && otherPoint.y == this.y;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 29 * hash + this.x;
            hash = 29 * hash + this.y;
            return hash;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ')';
        }
    }

    public static class UnexistingPointException extends RuntimeException {

        public UnexistingPointException(String message) {
            super(message);
        }

        public UnexistingPointException() {
        }
    }
}
