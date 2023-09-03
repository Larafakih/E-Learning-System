package UI;

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Salma
 */
public class ShowPasswordListener extends MouseAdapter {

    public ShowPasswordListener(JLabel show, JPasswordField Password) {
        show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Password.setEchoChar((char) 0);
                show.setIcon(img.CreateImageIcon("icons/show-1.png", 40, 40));
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Password.setEchoChar('*');
                show.setIcon(img.CreateImageIcon("icons/show.png", 40, 40));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                Password.setEchoChar('*');
                show.setIcon(img.CreateImageIcon("icons/show.png", 40, 40));
            }
        });
    }

}
