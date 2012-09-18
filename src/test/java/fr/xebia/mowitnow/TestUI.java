/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.xebia.mowitnow;

import org.junit.Test;

/**
 *
 * @author Renaud
 */
public class TestUI {

    Lawn lawn = new Lawn(3, 3);
    LawnMower mowner = new LawnMower(lawn, CardinalDirection.North);

    @Test
    public void testOne() throws InterruptedException {

        LawnDisplay display = new LawnDisplay(lawn);
        display.load(mowner);
        Thread.sleep(2000);
        mowner.execute(LawnMower.Command.A);
        display.refresh();
        Thread.sleep(2000);
        mowner.execute(LawnMower.Command.D);
        display.refresh();
        Thread.sleep(2000);
        mowner.execute(LawnMower.Command.A);
        display.refresh();
        Thread.sleep(2000);
    }
}
