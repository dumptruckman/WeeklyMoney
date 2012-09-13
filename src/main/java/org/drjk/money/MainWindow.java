package org.drjk.money;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.BorderUIResource;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import com.toedter.calendar.JCalendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

    private final JFrame frame;

    private OpeningCountWindow openingCountFrame;
    private ClosingCountWindow closingCountFrame;
    
    private final JCalendar calendar;

    /**
     * @wbp.parser.entryPoint
     */
    public MainWindow() {
        frame = new JFrame();
        calendar = new JCalendar();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private final void initialize() {
        frame.setBounds(100, 100, 450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(tabbedPane, "cell 0 0,grow");
        
        JPanel panel = new JPanel();
        tabbedPane.addTab("Daily Count", null, panel, null);
        panel.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
        
        panel.add(calendar, "cell 0 0 2 1,grow");
        
        JPanel panel_1 = new JPanel();
        panel.add(panel_1, "cell 0 1,grow");
        panel_1.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
        
        JButton btnOpeningCount = new JButton("Opening Count");
        btnOpeningCount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            if (openingCountFrame == null) {
                                openingCountFrame = new OpeningCountWindow();
                            }
                            Rectangle rect = getFrame().getBounds();
                            openingCountFrame.setLocation((int) rect.getMinX(), (int) rect.getMinY());
                            openingCountFrame.open(calendar.getDate());
                        } catch (Exception e) {
                            ErrorDialog.show("Error opening window", e);
                        }
                    }
                });
            }
        });
        panel_1.add(btnOpeningCount, "cell 0 0,grow");
        
        JButton btnClosingCount = new JButton("Closing Count");
        btnClosingCount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            if (closingCountFrame == null) {
                                closingCountFrame = new ClosingCountWindow();
                            }
                            Rectangle rect = getFrame().getBounds();
                            closingCountFrame.setLocation((int) rect.getMinX(), (int) rect.getMinY());
                            closingCountFrame.open(calendar.getDate());
                        } catch (Exception e) {
                            ErrorDialog.show("Error opening window", e);
                        }
                    }
                });
            }
        });
        panel_1.add(btnClosingCount, "cell 1 0,grow");

        setLookAndFeel();
    }
    
    private void setLookAndFeel() {
        UIManager.put("TitledBorder.border", new BorderUIResource(new EtchedBorder()));
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignore) { }
    }
    
    JFrame getFrame() {
        return frame;
    }

}
