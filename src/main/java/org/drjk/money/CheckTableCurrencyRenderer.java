package org.drjk.money;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class CheckTableCurrencyRenderer extends JLabel implements TableCellRenderer {

    private static final long serialVersionUID = 5373094514348923120L;

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        // 'value' is value contained in the cell located at
        // (rowIndex, vColIndex)

        if (isSelected) {
            // cell (and perhaps other cells) are selected
        }

        if (hasFocus) {
            // this cell is the anchor and the table has the focus
        }

        // Configure the component with the specified value
        if (value != null) {
            try {
                value = Double.parseDouble(value.toString());
                setText(CurrencyFormat.get().format(value));
            } catch (NumberFormatException ignore) { }
        }

        // Since the renderer is a component, return itself
        return this;
    }

}
