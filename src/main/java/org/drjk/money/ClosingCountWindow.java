package org.drjk.money;

import java.awt.Component;
import java.awt.Container;
import java.awt.ContainerOrderFocusTraversalPolicy;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClosingCountWindow extends JFrame implements TotalWindow {

    private static final long serialVersionUID = 4248453651052743487L;

    private final JPanel contentPane;
    
    private final DateBorder dateBorder = new DateBorder();
    private final JPanel moneyPanel;

    private final HighlightingCurrencyField txtPennies;
    private final HighlightingCurrencyField txtNickles;
    private final HighlightingCurrencyField txtDimes;
    private final HighlightingCurrencyField txtQuarters;
    private final HighlightingCurrencyField txtHalfdollars;
    private final HighlightingCurrencyField txtOnes;
    private final HighlightingCurrencyField txtFives;
    private final HighlightingCurrencyField txtTens;
    private final HighlightingCurrencyField txtTwenties;
    private final HighlightingCurrencyField txtFifties;
    private final HighlightingCurrencyField txtHundreds;
    private final HighlightingCurrencyField txtReceipts;
    private final JTable checkTable;
    private final DefaultTableModel checkTableModel = new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "Name", "Check #", "Amount"
            }
        );
    private final JButton btnAddCheck;
    private final JButton btnRemoveCheck;
    private final JFormattedTextField txtTotalCash;
    private final JFormattedTextField txtTotalDrawer;
    private final JFormattedTextField formattedTextField;
    private final JFormattedTextField txtCreditCards;
    private final JButton btnSubmit;
    private final JButton btnCancel;
    
    private volatile boolean closing;
    private JLabel lblHfCash;
    private JFormattedTextField formattedTextField_1;
    private JLabel lblHfChecks;
    private JFormattedTextField formattedTextField_3;
    private JLabel lblHfCc;
    private JFormattedTextField formattedTextField_4;

    /**
     * @wbp.parser.entryPoint
     */
    public ClosingCountWindow() {
        setUndecorated(true);      
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new TitledBorder("Closing Count"));
        contentPane.setFocusable(false);
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("", "[grow]", "[][][]"));
        
        moneyPanel = new JPanel();
        moneyPanel.setBorder(dateBorder);
        moneyPanel.setFocusable(false);
        contentPane.add(moneyPanel, "cell 0 0");
        moneyPanel.setLayout(new MigLayout("", "[][30%,grow][][30%,grow][25%,grow][25%,grow]", "[][][][][][][][]"));
        
        final JLabel lblPennies = new JLabel("Pennies:");
        lblPennies.setFocusable(false);
        moneyPanel.add(lblPennies, "cell 0 0,alignx trailing");
        
        txtPennies = new HighlightingCurrencyField(this);
        moneyPanel.add(txtPennies, "cell 1 0,growx");
        
        setFocusTraversalPolicy(new ContainerOrderFocusTraversalPolicy() {
            private static final long serialVersionUID = 7528408083838160711L;

            public Component getFirstComponent(Container aContainer) {
                return txtPennies;
            }
        });

        final JLabel lblNickles = new JLabel("Nickles:");
        lblNickles.setFocusable(false);
        moneyPanel.add(lblNickles, "cell 0 1,alignx trailing");

        txtNickles = new HighlightingCurrencyField(this);
        moneyPanel.add(txtNickles, "cell 1 1,growx");
        
        final JLabel lblDimes = new JLabel("Dimes:");
        lblDimes.setFocusable(false);
        moneyPanel.add(lblDimes, "cell 0 2,alignx trailing");

        txtDimes = new HighlightingCurrencyField(this);
        moneyPanel.add(txtDimes, "cell 1 2,growx");
        
        final JLabel lblQuarters = new JLabel("Quarters:");
        lblQuarters.setFocusable(false);
        moneyPanel.add(lblQuarters, "cell 0 3,alignx trailing");
        
        txtQuarters = new HighlightingCurrencyField(this);
        moneyPanel.add(txtQuarters, "cell 1 3,growx");
        
        final JLabel lblHalfdollars = new JLabel("Half-Dollars:");
        lblHalfdollars.setFocusable(false);
        moneyPanel.add(lblHalfdollars, "cell 0 4,alignx trailing");
        
        txtHalfdollars = new HighlightingCurrencyField(this);
        moneyPanel.add(txtHalfdollars, "cell 1 4,growx");
        
        final JLabel lblOnes = new JLabel("Ones:");
        lblOnes.setFocusable(false);
        moneyPanel.add(lblOnes, "cell 0 5,alignx trailing");
        
        txtOnes = new HighlightingCurrencyField(this);
        moneyPanel.add(txtOnes, "cell 1 5,growx");
        
        final JLabel lblFives = new JLabel("Fives:");
        lblFives.setFocusable(false);
        moneyPanel.add(lblFives, "cell 2 0,alignx trailing");
        
        txtFives = new HighlightingCurrencyField(this);
        moneyPanel.add(txtFives, "cell 3 0,growx,aligny top");
        
        final JLabel lblTens = new JLabel("Tens:");
        lblTens.setFocusable(false);
        moneyPanel.add(lblTens, "cell 2 1,alignx trailing");
        
        txtTens = new HighlightingCurrencyField(this);
        moneyPanel.add(txtTens, "cell 3 1,growx,aligny top");

        final JLabel lblTwenties = new JLabel("Twenties:");
        lblTwenties.setFocusable(false);
        moneyPanel.add(lblTwenties, "cell 2 2,alignx trailing");
        
        txtTwenties = new HighlightingCurrencyField(this);
        moneyPanel.add(txtTwenties, "cell 3 2,growx");

        final JLabel lblFifties = new JLabel("Fifties:");
        lblFifties.setFocusable(false);
        moneyPanel.add(lblFifties, "cell 2 3,alignx trailing");
        
        txtFifties = new HighlightingCurrencyField(this);
        moneyPanel.add(txtFifties, "cell 3 3,growx");

        final JLabel lblHundreds = new JLabel("Hundreds:");
        lblHundreds.setFocusable(false);
        moneyPanel.add(lblHundreds, "cell 2 4,alignx trailing");
        
        txtHundreds = new HighlightingCurrencyField(this);
        moneyPanel.add(txtHundreds, "cell 3 4,growx");
        
        final JLabel lblReceipts = new JLabel("Receipts:");
        lblReceipts.setFocusable(false);
        moneyPanel.add(lblReceipts, "cell 2 5,alignx trailing");
        
        txtReceipts = new HighlightingCurrencyField(this);
        moneyPanel.add(txtReceipts, "cell 3 5,growx");
        
        final JLabel lblCreditCards = new JLabel("Credit Cards:");
        lblReceipts.setFocusable(true);
        moneyPanel.add(lblCreditCards, "cell 0 6,alignx trailing");
        
        txtCreditCards = new HighlightingCurrencyField(this);
        moneyPanel.add(txtCreditCards, "cell 1 6,growx");
        
        checkTable = new JTable();
        checkTable.setModel(checkTableModel);
        checkTable.getColumnModel().getColumn(2).setCellRenderer(new CheckTableCurrencyRenderer());
        checkTable.getColumnModel().getColumn(1).setCellRenderer(new CheckTableNumberRenderer());
        checkTable.getColumnModel().getColumn(0).setCellRenderer(new CheckTableRenderer());
        final JScrollPane scrollPane = new JScrollPane(checkTable);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        moneyPanel.add(scrollPane, "cell 4 0 2 6,grow");
        
        btnAddCheck = new JButton("Add");
        btnAddCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkTableModel.addRow(new Object[] {null, null, null});
            }
        });
        moneyPanel.add(btnAddCheck, "cell 4 6,growx");
        
        btnRemoveCheck = new JButton("Remove");
        btnRemoveCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkTableModel.removeRow(checkTableModel.getRowCount() - 1);
            }
        });
        moneyPanel.add(btnRemoveCheck, "cell 5 6,growx");
        
        final JPanel _panel = new JPanel();
        _panel.setFocusable(false);
        contentPane.add(_panel, "cell 0 1,grow");
        _panel.setLayout(new MigLayout("", "[][grow][][grow][][grow]", "[][]"));
        
        lblHfCash = new JLabel("HF Cash:");
        _panel.add(lblHfCash, "cell 0 0,alignx trailing");
        
        formattedTextField_1 = new JFormattedTextField();
        _panel.add(formattedTextField_1, "cell 1 0,growx");
        
        lblHfChecks = new JLabel("HF Checks:");
        _panel.add(lblHfChecks, "cell 2 0,alignx trailing");
        
        formattedTextField_3 = new JFormattedTextField();
        _panel.add(formattedTextField_3, "cell 3 0,growx");
        
        lblHfCc = new JLabel("HF CC:");
        _panel.add(lblHfCc, "cell 4 0,alignx trailing");
        
        formattedTextField_4 = new JFormattedTextField();
        _panel.add(formattedTextField_4, "cell 5 0,growx");

        final JPanel panel = new JPanel();
        panel.setFocusable(false);
        contentPane.add(panel, "cell 0 2,growx");
        panel.setLayout(new MigLayout("", "[][grow][][grow][]", "[][][][][]"));
        
        final JLabel lblTotalCash = new JLabel("Total Cash:");
        lblTotalCash.setFocusable(false);
        panel.add(lblTotalCash, "cell 0 0,alignx trailing");
        
        txtTotalCash = new JFormattedTextField(NumberFormat.getCurrencyInstance());
        txtTotalCash.setEditable(false);
        txtTotalCash.setFocusable(false);
        panel.add(txtTotalCash, "cell 1 0,growx");
        txtTotalCash.setColumns(10);
        
        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final String initials = JOptionPane.showInputDialog(ClosingCountWindow.this, "Please enter your initials");
                if (initials != null) {
                    updateAndClose(initials.toUpperCase());
                }
            }
        });
        panel.add(btnSubmit, "cell 4 0");
        
        final JLabel lblTotalChecks = new JLabel("Total Checks:");
        panel.add(lblTotalChecks, "cell 0 1,alignx trailing");
        
        formattedTextField = new JFormattedTextField();
        panel.add(formattedTextField, "cell 1 1,growx");
        
        final JLabel lblTotalDrawer = new JLabel("Total Drawer:");
        lblTotalDrawer.setFocusable(false);
        panel.add(lblTotalDrawer, "cell 0 2,alignx trailing");
        
        txtTotalDrawer = new JFormattedTextField(NumberFormat.getCurrencyInstance());
        txtTotalDrawer.setEditable(false);
        txtTotalDrawer.setFocusable(false);
        panel.add(txtTotalDrawer, "cell 1 2,growx");
        txtTotalDrawer.setColumns(10);
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowEvent wev = new WindowEvent(ClosingCountWindow.this, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
            }
        });
        panel.add(btnCancel, "cell 4 2");
        
        updateTotals();
    }
    
    public Date getDate() {
        return dateBorder.getDate();
    }
    
    public void setDate(final Date date) {
        dateBorder.setDate(date);
        try {
            Connection conn = MySql.getConnection();
            PreparedStatement statement = conn.prepareStatement(MySql.OPENING_COUNT_QUERY);
            statement.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                txtPennies.setValue(result.getDouble("Pennies"));
                txtNickles.setValue(result.getDouble("Nickles"));
                txtDimes.setValue(result.getDouble("Dimes"));
                txtQuarters.setValue(result.getDouble("Quarters"));
                txtHalfdollars.setValue(result.getDouble("Halfdollars"));
                txtOnes.setValue(result.getDouble("Ones"));
                txtFives.setValue(result.getDouble("Fives"));
                txtTens.setValue(result.getDouble("Tens"));
                txtTwenties.setValue(result.getDouble("Twenties"));
                txtFifties.setValue(result.getDouble("Fifties"));
                txtHundreds.setValue(result.getDouble("Hundreds"));
                txtReceipts.setValue(result.getDouble("Receipt Totals"));
            }
            result.close();
            conn.close();
        } catch (SQLException e) {
            ErrorDialog.show("Error in data", e);
        }
        updateTotals();
    }

    @Override
    public void setVisible(boolean visible) {
        txtPennies.requestFocusInWindow();
        super.setVisible(visible);
    }

    public void updateTotals() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                double total = 0D;
                for (Component comp : moneyPanel.getComponents()) {
                    if (comp instanceof JTextField) {
                        JTextField field = (JTextField) comp;
                        try {
                            total += Double.parseDouble(field.getText().replaceFirst("\\$", ""));
                        } catch (NumberFormatException ignore) { }
                    }
                }
                txtTotalDrawer.setValue(total);
                try {
                    txtTotalCash.setValue(total - Double.parseDouble(txtReceipts.getValue().toString()));
                } catch (NumberFormatException ignore) {
                    txtTotalCash.setText("ERROR");
                }
            }
        });
    }
    
    private void updateAndClose(final String initials) {
        if (!closing && getDate() != null) {
            closing = true;
            try {
                Connection conn = MySql.getConnection();
                PreparedStatement statement = conn.prepareStatement(MySql.OPENING_COUNT_UPDATE);
                final java.sql.Date date = new java.sql.Date(getDate().getTime());
                statement.setDate(1, date);
                int i = 2;
                for (Component comp : moneyPanel.getComponents()) {
                    if (comp instanceof HighlightingCurrencyField) {
                        statement.setBigDecimal(i, ((HighlightingCurrencyField) comp).getBigDecimalValue());
                        i++;
                    }
                }
                statement.setString(14, initials);
                statement.setDate(15, date);
                i = 16;
                for (Component comp : moneyPanel.getComponents()) {
                    if (comp instanceof HighlightingCurrencyField) {
                        statement.setBigDecimal(i, ((HighlightingCurrencyField) comp).getBigDecimalValue());
                        i++;
                    }
                }
                statement.setString(28, initials);
                statement.executeUpdate();
                conn.close();
                WindowEvent wev = new WindowEvent(ClosingCountWindow.this, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
            } catch (SQLException e) {
                ErrorDialog.show("Error in data", e);
            }
            closing = false;
        }
    }
    
    public void open(final Date date) {
        for (Component comp : moneyPanel.getComponents()) {
            if (comp instanceof HighlightingCurrencyField) {
                ((HighlightingCurrencyField) comp).resetValue();
            }
        }
        setDate(date);
        setVisible(true);
        toFront();
    }
    
    public void highlight(final JTextComponent textComp) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                textComp.setSelectionStart(0);
                textComp.setSelectionEnd(textComp.getText().length());
            }
        });
    }
}
