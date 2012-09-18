/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.xebia.mowitnow;

import fr.xebia.mowitnow.Lawn.Point;
import fr.xebia.mowitnow.LawnMower.Command;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author Renaud
 */
public class TestAutomaticLawnMower {

    @Test
    public void testFromSpecWithGUI() {
        LoggerUtils.createGlobalConsoleLogger();

        Lawn lawn = new Lawn(5, 5);

        String cmds1 = "GAGAGAGAA";
        AutomaticLawnMower mower1 = new AutomaticLawnMower(lawn, new Point(1, 2), CardinalDirection.North, parseCommands(cmds1));
        LawnGUIUtils.setupGUI(lawn, mower1, 500);
        mower1.run();
        TestCase.assertEquals(new Lawn.Point(1, 3), mower1.getPoint());
        TestCase.assertEquals(CardinalDirection.North, mower1.getDirection());

        String cmds2 = "AADAADADDA";
        AutomaticLawnMower mower2 = new AutomaticLawnMower(lawn, new Point(3, 3), CardinalDirection.Est, parseCommands(cmds2));
        LawnGUIUtils.setupGUI(lawn, mower2, 500);
        mower2.run();
        TestCase.assertEquals(new Lawn.Point(5, 1), mower2.getPoint());
        TestCase.assertEquals(CardinalDirection.Est, mower2.getDirection());
    }

    private List<LawnMower.Command> parseCommands(String s) {
        ArrayList cmdList = new ArrayList(s.length());
        for (char c : s.toCharArray()) {
            cmdList.add(Command.valueOf(Character.toString(c)));
        }
        return cmdList;
    }
}
