package Users;

import javax.swing.*;

/**
 *
 * @author Salma
 */
public class UserDeleteAccount {

    private int input, UserID;
    private String UserRole, email;

    public UserDeleteAccount(String Role, int ID) {
        UserID = ID;
        UserRole = Role;
        input = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Select an Option...",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

        if (input == 0) {
            Controller.UserController.DeleteAccount(UserRole, UserID);
            System.out.println("Account Deleted");
        }
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }
}
