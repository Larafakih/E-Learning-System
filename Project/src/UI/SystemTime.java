package UI;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.Calendar;
import javax.swing.*;

/**
 *
 * @author Salma
 */
public class SystemTime {

    public static void clock(JLabel clock) {
        
        clock.setHorizontalAlignment(JLabel.RIGHT);
        DateFormat df = new SimpleDateFormat("dd-MMMMMMM-yyyy  hh:mm:ss aa");
        Calendar calobj = Calendar.getInstance();
        clock.setText(df.format(calobj.getTime()));
        clock.setFont(new Font(Font.SERIF, Font.BOLD, 19));
        clock.setForeground(new Color(252, 79, 79));//(28, 101, 140));
        Timer t = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateFormat df = new SimpleDateFormat("dd-MMMMMMM-yyyy  hh:mm:ss aa");
                Calendar calobj = Calendar.getInstance();
                clock.setText(df.format(calobj.getTime()));
            }
        });
        t.setRepeats(true);
        t.setCoalesce(true);
        t.setInitialDelay(0);
        t.start();
    }
}
