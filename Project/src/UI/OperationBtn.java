package UI;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Salma
 */
public class OperationBtn extends JButton {

    public OperationBtn() {
        setContentAreaFilled(false);
    }

    public OperationBtn(String text) {
        super(text);
        setBackground(new Color(195, 219, 217));
        setForeground(new Color(28, 101, 140));
        setVerticalTextPosition(JButton.BOTTOM);
        setHorizontalTextPosition(JButton.CENTER);
    }

    public OperationBtn(String text, String path, int i1, int i2) {
        super(text);
        setIcon(img.CreateImageIcon(path, i1, i2));
        setBackground(new Color(195, 219, 217));
        setForeground(new Color(28, 101, 140));
        setVerticalTextPosition(JButton.BOTTOM);
        setHorizontalTextPosition(JButton.CENTER);
    }
}
