import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;

public class MainForm {
    private JPanel mainPanel;
    private JButton expandButton;
    private JButton collapseButton;
    private JPanel expandPanel;
    private JTextField secondName;
    private JTextField name;
    private JTextField surname;
    private JPanel subExPanel;
    private JTextArea имяTextArea;
    private JTextArea фамилияTextArea;

    public MainForm()
    {


    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
