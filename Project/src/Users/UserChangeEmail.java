package Users;

import UI.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Salma
 */
public class UserChangeEmail extends JFrame implements ActionListener, FocusListener {

    JLabel newEmaillbl, emailValidation, Title,image;
    JTextField newEmail;
    JButton updateEmailbtn;
    int UserID;
    JPanel Panel;
    String UserRole;

    public UserChangeEmail(String Role, int id) {
        super("Change Email");
        setIconImage(img.SystemIcon());
        setSize(600, 600);
        setLocation(10, 10);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UserID = id;
        UserRole=Role;

        Title = new TitleLbl("Change Email");
        add(Title, BorderLayout.NORTH);

        Panel = new JPanel();
        Panel.setLayout(null);
        add(Panel, BorderLayout.CENTER);
        
        image = new JLabel(img.CreateImageIcon("icons/email.png",100 , 100));
        image.setBounds(200,200,100,100);
        Panel.add(image);
        
        newEmaillbl = new TxtLabel("New Email");
        newEmaillbl.setBounds(50, 40, 110, 29);
        Panel.add(newEmaillbl);

        newEmail = new TxtField();
        newEmail.setBounds(210, 30, 350, 50);
        Panel.add(newEmail);
        newEmail.setColumns(20);

        emailValidation = new ValidLabel();
        newEmail.addFocusListener(this);
        emailValidation.setBounds(214, 100, 150, 10);
        Panel.add(emailValidation);

        updateEmailbtn = new CircleBtn("Update");
        updateEmailbtn.setBounds(214, 100, 150, 30);
        updateEmailbtn.addActionListener((ActionListener) this);
        Panel.add(updateEmailbtn);

        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateEmailbtn) {
            String newemail = newEmail.getText();
            if (emailValidation.getText().isEmpty()) {
                if (!newemail.isEmpty()) {
                    Controller.UserController.ChangeEmail(UserRole, UserID, newemail);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Please Fill Field !");
                }
            } else{
                JOptionPane.showMessageDialog(null, "Please Enter Email !");
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == newEmail) {
            emailValidation.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == newEmail) {
            Validation.IfEmail(newEmail, emailValidation);
        }
    }
}
