import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField secondNameField;
    private JTextField surnameField;
    private JLabel nameLabel;
    private JLabel secondNameLabel;
    private JLabel surnameLabel;
    private JButton collapseButton;
    private JButton expandButton;
    private JTextField fioField;
    private JLabel fioLabel;

    public MainForm() {
        fioField.setVisible(false);
        expandButton.setVisible(false);
        fioLabel.setVisible(false);

        collapseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String secondName = secondNameField.getText();
                String surname = surnameField.getText();

                if (name.contains(" ") || name.isEmpty() ||
                        secondName.isEmpty() || secondName.contains(" ") ||
                        surname.isEmpty() || surname.contains(" ")) {
                    JOptionPane.showMessageDialog(mainPanel, "Неправильный формат ввода", "Ошибка",JOptionPane.PLAIN_MESSAGE);
                } else {
                    fioField.setText(name + " " + secondName + " " + surname);
                    fioField.setVisible(true);
                    expandButton.setVisible(true);
                    fioLabel.setVisible(true);

                    nameField.setVisible(false);
                    nameLabel.setVisible(false);
                    secondNameField.setVisible(false);
                    secondNameLabel.setVisible(false);
                    surnameField.setVisible(false);
                    surnameLabel.setVisible(false);
                    collapseButton.setVisible(false);
                }

            }
        });

        expandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String[] fio = fioField.getText().split(" ");
                if (fio.length <3) {
                    JOptionPane.showMessageDialog(mainPanel, "Неправильный формат ввода", "Ошибка",JOptionPane.PLAIN_MESSAGE);
                } else {
                    nameField.setText(fio[0]);
                    secondNameField.setText(fio[1]);
                    surnameField.setText(fio[2]);

                    fioField.setVisible(false);
                    expandButton.setVisible(false);
                    fioLabel.setVisible(false);

                    nameField.setVisible(true);
                    nameLabel.setVisible(true);
                    secondNameField.setVisible(true);
                    secondNameLabel.setVisible(true);
                    surnameField.setVisible(true);
                    surnameLabel.setVisible(true);
                    collapseButton.setVisible(true);
                }

            }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
