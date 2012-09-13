package org.drjk.money;

import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;

import javax.swing.JFormattedTextField;
import javax.swing.text.JTextComponent;

class HighlightingCurrencyField extends JFormattedTextField {

    private static final long serialVersionUID = 1433042616020998509L;
    
    HighlightingCurrencyField(final TotalWindow countWindow) {
        super(CurrencyFormat.get());
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                highlight(HighlightingCurrencyField.this);
            }
            @Override
            public void focusLost(FocusEvent e) {
                countWindow.updateTotals();
            }
        });
        resetValue();
    }
    
    public void highlight(final JTextComponent textComp) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                textComp.setSelectionStart(0);
                textComp.setSelectionEnd(textComp.getText().length());
            }
        });
    }
    
    public void resetValue() {
        setValue(0D);
    }
    
    public BigDecimal getBigDecimalValue() {
        return BigDecimal.valueOf(Double.parseDouble(getValue().toString()));
    }
}
