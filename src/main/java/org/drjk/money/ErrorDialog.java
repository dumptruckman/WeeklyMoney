package org.drjk.money;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JLabel;

public class ErrorDialog extends JDialog {

    private static final long serialVersionUID = -1537953914248700398L;

    private final JPanel contentPanel = new JPanel();

    public static void show(String message, Throwable throwable) {
        try {
            ErrorDialog dialog = new ErrorDialog(message, throwable);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @wbp.parser.entryPoint
     */
    public ErrorDialog(String message, Throwable throwable) {
        setBounds(100, 100, 500, 400);
        centerDialogOnTheScreen();
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new MigLayout("", "[20%,grow][80%,grow]", "[20%,grow][80%,grow]"));
        
        JLabel iconError = new JLabel("");
        iconError.setIcon(createImageIcon("/icon-error.png", "test"));
        contentPanel.add(iconError, "cell 0 0");
        {
            final JTextArea txtMessage = new JTextArea();
            txtMessage.setText(message);
            txtMessage.setEditable(false);  
            txtMessage.setCursor(null);  
            txtMessage.setOpaque(false);  
            txtMessage.setFocusable(false);  
            txtMessage.setFont(UIManager.getFont("Label.font"));      
            txtMessage.setWrapStyleWord(true);  
            txtMessage.setLineWrap(true);
            contentPanel.add(txtMessage, "cell 1 0,grow");
            
            final JTextArea textPane = new JTextArea();
            textPane.setText(getStackTraceAsString(throwable));
            textPane.setBorder(new EtchedBorder());
            textPane.setEditable(false);
            final JScrollPane scrollBar = new JScrollPane(textPane);
            scrollBar.setBorder(new EtchedBorder());
            contentPanel.add(scrollBar, "flowx,cell 0 1 2 1,grow");
        }
        
        
        {
            final JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                final JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        WindowEvent wev = new WindowEvent(ErrorDialog.this, WindowEvent.WINDOW_CLOSING);
                        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        }
    }
    
    private void centerDialogOnTheScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dialogSize = this.getSize();
        int centerPosX = (screenSize.width - dialogSize.width) / 2;
        int centerPosY = (screenSize.height - dialogSize.height) / 2;
        setLocation(centerPosX, centerPosY);
    }
    
    private String getStackTraceAsString(Throwable exception) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        exception.printStackTrace(printWriter);
        return result.toString();
    }
    
    protected ImageIcon createImageIcon(String path, String description) {
        InputStream stream = getClass().getResourceAsStream(path);
        try {
            BufferedImage image = ImageIO.read(stream);
            if (image != null) {
                return new ImageIcon(image, description);
            } else {
                System.err.println("Couldn't find file: " + path);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
