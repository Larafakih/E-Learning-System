package UI;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Salma
 */
public class Table extends JTable {

    public Table(int i) {
        super();
        setSelectionBackground(new Color(230, 57, 95));
        getTableHeader().setBackground(new Color(32, 136, 203));
        getTableHeader().setForeground(new Color(255, 255, 255));
        setRowHeight(i);
        setSelectionBackground(new Color(230, 57, 95));
    }

    public Table(int i, String[] columnName) {
        super();
        setSelectionBackground(new Color(230, 57, 95));
        getTableHeader().setBackground(new Color(32, 136, 203));
        getTableHeader().setForeground(new Color(255, 255, 255));
        setRowHeight(i);
        setSelectionBackground(new Color(230, 57, 95));
        setModel(new DefaultTableModel(
                new Object[][]{},
                columnName
        ));
    }

    public Table(DefaultTableModel tm, int i) {
        super(tm);
        setSelectionBackground(new Color(230, 57, 95));
        getTableHeader().setBackground(new Color(32, 136, 203));
        getTableHeader().setForeground(new Color(255, 255, 255));
        setRowHeight(i);
        setSelectionBackground(new Color(230, 57, 95));
    }

    public boolean editCellAt(int row, int column, java.util.EventObject e) {
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        JTableHeader header = getTableHeader();
        header.setBackground(Color.LIGHT_GRAY);
        getTableHeader().setFont(new Font("segoe ui", Font.BOLD, 15));
        getTableHeader().setOpaque(false);
        getTableHeader().setBackground(new Color(32, 136, 203));
        getTableHeader().setForeground(new Color(255, 255, 255));
        Dimension d = new Dimension(0, 0);
        setIntercellSpacing(d);
        setShowVerticalLines(false);
        setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        setFont(new Font("SansSerif", Font.PLAIN, 15));
        setPreferredScrollableViewportSize(getPreferredSize());
        super.paintComponent(g);
    }

    //        JTableHeader header = table.getTableHeader();
//        header.setBackground(Color.LIGHT_GRAY);
//        table.getTableHeader().setFont(new Font("segoe ui", Font.BOLD, 15));
//        table.getTableHeader().setOpaque(false);
//        table.getTableHeader().setBackground(new Color(32, 136, 203));
//        table.getTableHeader().setForeground(new Color(255, 255, 255));
//        Dimension d = new Dimension(0, 0);
//        table.setIntercellSpacing(d);
//        table.setSelectionBackground(new Color(230, 57, 95));//new Color(246, 137, 138)
//        table.setShowVerticalLines(false);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//        table.setFont(new Font("SansSerif", Font.PLAIN, 15));
//        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
//        table.setPreferredScrollableViewportSize(table.getPreferredSize());
//        table.setRowHeight(90);
}
