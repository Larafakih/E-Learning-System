package project;

import UI.*;
import java.awt.*;
import javax.swing.*;
import util.CreateTable;
import util.DBConnection;

public class LoadingScreen extends JFrame {

    JPanel mainPanel;
    JProgressBar loadingBar;
    JLabel backgroundPic, upperPic, textLbl, loadingLbl, percentageLbl;

    public LoadingScreen() {
        super("E-Learning System");
        setIconImage(img.SystemIcon());
        setSize(1280, 720);
        setLocation(10, 10);
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        add(mainPanel, BorderLayout.CENTER);

        upperPic = new JLabel(img.CreateImageIcon("icons/elearningLoadingIcon.png", 400, 400));
        upperPic.setBounds(460, 50, 400, 400);
        mainPanel.add(upperPic);

        loadingLbl = new JLabel("Loading...");
        loadingLbl.setHorizontalAlignment(JLabel.LEFT);
        loadingLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        loadingLbl.setForeground(Color.WHITE);
        loadingLbl.setBounds(5, 630, 250, 30);

        percentageLbl = new JLabel("0 %");
        percentageLbl.setHorizontalAlignment(JLabel.RIGHT);
        percentageLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        percentageLbl.setForeground(Color.WHITE);
        percentageLbl.setBounds(1160, 630, 100, 30);

        mainPanel.add(loadingLbl);
        mainPanel.add(percentageLbl);

        textLbl = new JLabel("Welcome To E-Learning System");
        textLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 55));
        textLbl.setForeground(Color.WHITE);
        textLbl.setBounds(245, 420, 850, 80);
        mainPanel.add(textLbl);

        backgroundPic = new JLabel(img.CreateImageIcon("icons/backgroundLoadingPage.jpg", 1280, 720));
        backgroundPic.setBounds(0, 0, 1280, 720);
        mainPanel.add(backgroundPic);

        loadingBar = new JProgressBar();
        loadingBar.setPreferredSize(new Dimension(0, 25));
        loadingBar.setValue(0);
        loadingBar.setStringPainted(true);
        loadingBar.setForeground(Color.GREEN);
        add(loadingBar, BorderLayout.SOUTH);

        setVisible(true);
        Run();
    }

    public void Run() {
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(50);
                loadingBar.setValue(i);
                percentageLbl.setText(i + " %");
                if (i == 10) {
                    loadingLbl.setText("Loading...");
                } else if (i == 20) {
                    loadingLbl.setText("Loading...");
                } else if (i == 50) {
                    loadingLbl.setText("Connecting to Database...");
                    DBConnection c1=new DBConnection();
                } else if (i == 70) {
                    loadingLbl.setText("Connection Successfull !");
                } else if (i == 80) {
                    loadingLbl.setText("Lauching Application...");
                } else if (i == 100) {
                    new Main();
                    dispose();
                }
            }
        } catch (Exception exception) {
           exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //CreateTable.CreateTable();
        new LoadingScreen();
    }

}
