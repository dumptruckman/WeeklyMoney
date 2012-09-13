package org.drjk.money;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.TitledBorder;

class DateBorder extends TitledBorder {
    
    private static final long serialVersionUID = 3204697290132328824L;

    private volatile Date date = null;
    private final DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy");

    public DateBorder() {
        super("");
        // TODO Auto-generated constructor stub
    }

    public Date getDate() {
        return date;
    }
    
    public void setDate(final Date date) {
        this.date = date;
        setTitle(dateFormat.format(date).toString());
    }
    
}
