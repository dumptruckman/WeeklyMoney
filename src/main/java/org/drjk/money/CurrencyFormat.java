package org.drjk.money;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Currency;
import java.util.Locale;

class CurrencyFormat extends Format {

    private static final long serialVersionUID = 5016661698650457341L;
    private static final CurrencyFormat instance;
    
    static {
        instance = new CurrencyFormat();
    }
    
    private final DecimalFormat decimalFormat;
    
    CurrencyFormat() {
        decimalFormat = new DecimalFormat();
        decimalFormat.setCurrency(Currency.getInstance(Locale.getDefault()));
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setMaximumFractionDigits(2);
    }
    
    public static CurrencyFormat get() {
        return instance;
    }

    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo,
            FieldPosition pos) {
        // TODO Auto-generated method stub
        return new StringBuffer("$").append(decimalFormat.format(obj, toAppendTo, pos));
    }

    @Override
    public Object parseObject(String source, ParsePosition pos) {
        // TODO Auto-generated method stub
        return decimalFormat.parseObject(source.replaceAll("\\$", ""), pos);
    }
    
}
