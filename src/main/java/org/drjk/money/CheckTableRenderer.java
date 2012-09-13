package org.drjk.money;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class CheckTableRenderer extends JLabel implements TableCellRenderer {

    private static final long serialVersionUID = 5373094514348923120L;

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        // Since the renderer is a component, return itself
        return this;
    }

}
