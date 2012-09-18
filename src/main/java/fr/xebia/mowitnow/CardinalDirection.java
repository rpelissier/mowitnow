/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.xebia.mowitnow;

/**
 *
 * @author Renaud
 */
public enum CardinalDirection {

    North,
    South,
    Est,
    West;

    public CardinalDirection clockwiseRotation() {
        switch (this) {
            case North:
                return Est;
            case South:
                return West;
            case Est:
                return South;
            case West:
                return North;
            default:
                throw new IllegalArgumentException("Unknown " + CardinalDirection.class.getSimpleName() + " '" + this + "'.");
        }
    }

    public CardinalDirection antiClockwiseRotation() {
        switch (this) {
            case North:
                return West;
            case South:
                return Est;
            case Est:
                return North;
            case West:
                return South;
            default:
                throw new IllegalArgumentException("Unknown " + CardinalDirection.class.getSimpleName() + " '" + this + "'.");
        }
    }
}
