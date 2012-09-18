/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.xebia.mowitnow;

import fr.xebia.mowitnow.Lawn.Point;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author Renaud
 */
public class TestLawnMower {

    Lawn lawn = new Lawn(3, 3);
    LawnMower mower = new LawnMower(lawn, CardinalDirection.North);

    @Test
    public void simpleTest() throws InterruptedException {
        LoggerUtils.createGlobalConsoleLogger();

        mower.execute(LawnMower.Command.A);
        mower.execute(LawnMower.Command.D);
        mower.execute(LawnMower.Command.A);

        TestCase.assertEquals(new Point(1, 1), mower.getPoint());
        TestCase.assertEquals(CardinalDirection.Est, mower.getDirection());
    }

    @Test
    public void guiTest() throws InterruptedException {
        LoggerUtils.createGlobalConsoleLogger();

        LawnGUIUtils.setupGUI(lawn, mower, 500);

        mower.execute(LawnMower.Command.A);
        mower.execute(LawnMower.Command.D);
        mower.execute(LawnMower.Command.A);

        TestCase.assertEquals(new Point(1, 1), mower.getPoint());
        TestCase.assertEquals(CardinalDirection.Est, mower.getDirection());
    }
}
