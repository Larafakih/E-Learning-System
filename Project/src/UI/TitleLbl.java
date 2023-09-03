package UI;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Salma
 */
public class TitleLbl extends JLabel {

    public TitleLbl() {

    }

    public TitleLbl(String text) {
        super(text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        setForeground(new Color(0, 142, 204));
        setBackground(Color.LIGHT_GRAY);
        setFont (new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 40));
        setHorizontalAlignment(JLabel.CENTER);
        setOpaque(true);
        super.paintComponent(g);
    }
}
