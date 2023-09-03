package Users;

import UI.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Salma
 */
public class UserChangeName extends JFrame implements ActionListener, FocusListener {

    private JLabel newFNamelbl, Title, fnameValidation,image;
    private JTextField newFName;
    private JButton updateNameButton, back;
    private int UserID;
    private String UserRole;
    private JPanel Panel;
    private JFrame frame;

    public UserChangeName(String Role, int c, JFrame fn) {
        super("Change Name");
        setIconImage(img.SystemIcon());
        UserID = c;
        UserRole = Role;
        frame = fn;

        setSize(600, 600);
        setLocation(10, 10);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Title = new TitleLbl("Change Your Name");
        add(Title, BorderLayout.NORTH);

        Panel = new JPanel();
        Panel.setLayout(null);
        add(Panel, BorderLayout.CENTER);

        newFNamelbl = new TxtLabel("Full Name:");
        newFNamelbl.setBounds(10, 152, 200, 43);
        Panel.add(newFNamelbl);

        newFName = new TxtField();
        newFName.setBounds(210, 151, 300, 50);
        Panel.add(newFName);

        fnameValidation = new ValidLabel();
        newFName.addFocusListener(this);
        fnameValidation.setBounds(150, 205, 150, 10);
        Panel.add(fnameValidation);

        updateNameButton = new CircleBtn("Update");
        updateNameButton.setBounds(200, 240, 150, 30);
        updateNameButton.addActionListener((ActionListener) this);
        Panel.add(updateNameButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateNameButton) {
            String newName = newFName.getText();
            if (fnameValidation.getText().isEmpty()) {
                if (!newName.isEmpty()) {
                    Controller.UserController.ChangeName(UserRole, UserID, newName, frame);
                    {
                        this.dispose();
                        frame.dispose();                     
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please Fill Field !");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please Fill accurate Info !");
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == newFName) {
            fnameValidation.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == newFName) {
            Validation.IfFullName(newFName, fnameValidation);
        }
    }
}
