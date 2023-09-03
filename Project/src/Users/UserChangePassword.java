package Users;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import UI.*;

/**
 *
 * @author Salma
 */
public class UserChangePassword extends JFrame implements ActionListener, FocusListener {

    JLabel newPasswordlbl, passwordValidation, Title, image, show;
    JPasswordField newPassword;
    JButton updatePassbtn, Cancelbtn;
    int UserID;
    String UserRole;
    JPanel Panel;

    public UserChangePassword(String Role, int c) {
        super("Change Password");
        setIconImage(img.SystemIcon());
        setSize(650, 600);
        setLocation(10, 10);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UserID = c;
        UserRole = Role;
        setLayout(new BorderLayout());
        Title = new TitleLbl("Change Password");
        add(Title, BorderLayout.NORTH);

        Panel = new JPanel();
        Panel.setLayout(null);
        add(Panel, BorderLayout.CENTER);

        image = new JLabel(img.CreateImageIcon("icons/secure.png", 100, 100));
        image.setBounds(100, 200, 100, 100);
        Panel.add(image);

        newPasswordlbl = new TxtLabel("New Password: ");
        newPasswordlbl.setBounds(100, 30, 250, 30);
        Panel.add(newPasswordlbl);

        newPassword = new PasswrdField();
        newPassword.setBounds(270, 30, 300, 40);
        Panel.add(newPassword);
        newPassword.setColumns(20);
        
        show = new JLabel();
        show.setToolTipText("Click here to show password !!");
        show.setIcon(img.CreateImageIcon("icons/show.png", 40, 40));
        show.setBounds(570, 28, 150, 50);
        Panel.add(show);
        show.addMouseListener(new ShowPasswordListener(show, newPassword));

        passwordValidation = new ValidLabel();
        newPassword.addFocusListener(this);
        passwordValidation.setBounds(270, 80, 150, 10);
        Panel.add(passwordValidation);

        updatePassbtn = new CircleBtn("Update");
        updatePassbtn.setBounds(120, 106, 150, 50);
        updatePassbtn.addActionListener((ActionListener) this);
        Panel.add(updatePassbtn);
        
        Cancelbtn = new CircleBtn("Cancel");
        Cancelbtn.setBounds(300, 106, 150, 50);
        Cancelbtn.addActionListener((ActionListener) this);
        Panel.add(Cancelbtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updatePassbtn) 
        {   
            Validation.IfPassword(newPassword, passwordValidation);
            if (passwordValidation.getText().isEmpty()) 
            {
                    String newpass = String.valueOf(newPassword.getPassword()); 
                    Controller.UserController.ChangePassword(UserRole, UserID, newpass);
                    setVisible(false);
            }
        }
        else if (ae.getSource() == Cancelbtn)
        {
            setVisible(false);
        }
    }


    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == newPassword) {
            passwordValidation.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == newPassword) {
        //    Validation.IfPassword(newPassword, passwordValidation);
        }
    }
}
