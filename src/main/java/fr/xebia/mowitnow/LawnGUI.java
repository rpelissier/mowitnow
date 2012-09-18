/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.xebia.mowitnow;

import java.awt.Component;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Renaud
 */
public class LawnGUI {

    Lawn lawn;
    final JFrame frame;
    final ImageIcon grassImageIcon = new ImageIcon(getClass().getResource("grass.jpg"));

    /**
     * Creates new form UI
     */
    public LawnGUI(Lawn lawn) {
        this.lawn = lawn;
        this.frame = new JFrame("Lawn Display");
    }

    public void load(final LawnMower mower) {

        JTable table = new JTable(new MowerTableModel(mower));
        table.setDefaultRenderer(Object.class, new TableCellRenderer());
        table.setRowHeight(grassImageIcon.getIconHeight());
        Enumeration<TableColumn> columns = table.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            columns.nextElement().setWidth(grassImageIcon.getIconWidth());
        }
        frame.setResizable(false);
        frame.setSize(lawn.getSizeX() * grassImageIcon.getIconWidth(), lawn.getSizeY() * grassImageIcon.getIconHeight());
        frame.getContentPane().removeAll();
        frame.getContentPane().add(table);

        frame.pack();
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                frame.setVisible(true);
            }
        });
    }

    public void refresh() {
        frame.repaint();
    }

    private class MowerTableModel extends AbstractTableModel {

        LawnMower mower;

        public MowerTableModel(LawnMower mower) {
            this.mower = mower;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Lawn.Point mowerPoint = mower.getPoint();
            int mowerRowIndex = getRowCount() - 1 - mowerPoint.getY();
            int mowerColIndex = mowerPoint.getX();
            if (rowIndex == mowerRowIndex && columnIndex == mowerColIndex) {
                return mower;
            } else {
                return null;
            }
        }

        public int getColumnCount() {
            return lawn.getSizeX();
        }

        public int getRowCount() {
            return lawn.getSizeY();
        }
    }

    private class TableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            ImageIcon icon;
            if (value != null) {
                LawnMower mower = (LawnMower) value;
                icon = new ImageIcon(getClass().getResource("mower" + mower.getDirection().name() + ".jpg"));
            } else {
                icon = new ImageIcon(getClass().getResource("grass.jpg"));

            }
            setIcon(icon);
            setSize(icon.getIconWidth(), icon.getIconHeight());

            return this;
        }
    }
}
