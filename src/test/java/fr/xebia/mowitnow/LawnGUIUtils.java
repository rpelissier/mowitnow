/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.xebia.mowitnow;

/**
 *
 * @author Renaud
 */
public class LawnGUIUtils {

    public static void setupGUI(Lawn lawn, LawnMower mower, final long moveDelayMS) {
        final LawnGUI display = new LawnGUI(lawn);
        mower.setStateChangedEH(new LawnMower.StateChangedEventHandler() {

            public void stateChanged(Lawn.Point newPoint, CardinalDirection newDirection) {
                display.refresh();
                try {
                    Thread.sleep(moveDelayMS);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        display.load(mower);
    }
}
