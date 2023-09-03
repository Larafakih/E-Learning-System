package UI;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import project.*;

/**
 *
 * @author Salma
 */
public class img {

    public static Image SystemIcon() {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/System.png"));
        Image i = icon.getImage();
        return i;
    }

    public static ImageIcon CreateImageIcon(String Path, int i, int j) {
        ImageIcon signUpImg1 = new ImageIcon(ClassLoader.getSystemResource(Path));
        Image signUpImg2 = signUpImg1.getImage().getScaledInstance(i, j, Image.SCALE_DEFAULT);
        ImageIcon signUpImg3 = new ImageIcon(signUpImg2);
        return signUpImg3;
    }

    public static ReturnDifferentType.ImageBuffer setProfileUser(byte[] bytImage, BufferedImage bufferedImage) {
        ImageIcon i = null;
        bufferedImage = null;

        if (bytImage != null) {
            InputStream is = new ByteArrayInputStream(bytImage);
            try {
                bufferedImage = ImageIO.read(is);
                i = img.resizeImage(bufferedImage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            i = new ImageIcon(ClassLoader.getSystemResource("icons/user2.png"));
        }
        return new ReturnDifferentType.ImageBuffer(i, bufferedImage);
    }

    // This code use to resize image to fit lable
    public static ImageIcon resizeImage(String imagePath) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int width = bufferedImage.getWidth();
        BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleBuffer.createGraphics();
        g2.setClip(new Ellipse2D.Float(0, 0, width, width));
        g2.drawImage(bufferedImage, 0, 0, width, width, null);
        ImageIcon icon = new ImageIcon(circleBuffer);
        Image i2 = icon.getImage().getScaledInstance(96, 96, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        return i3;
    }

    // This code use to resize image to fit lable
    public static ImageIcon resizeImage(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleBuffer.createGraphics();
        g2.setClip(new Ellipse2D.Float(0, 0, width, width));
        g2.drawImage(bufferedImage, 0, 0, width, width, null);
        ImageIcon icon = new ImageIcon(circleBuffer);
        Image i2 = icon.getImage().getScaledInstance(96, 96, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        return i3;
    }

    public static ImageIcon getImageIcon(byte[] byteImage) {
        InputStream is = new ByteArrayInputStream(byteImage);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        ImageIcon icon = new ImageIcon(bufferedImage);
        Image i2 = icon.getImage().getScaledInstance(96, 96, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        return i3;
    }

    //this function is to upload image for Student,Admin,Teacher
    public static ReturnDifferentType.Im upload() {
        String fname1 = null;
        FileInputStream fis1 = null;
        File f1 = null;
        JFileChooser fchoser = new JFileChooser();
        fchoser.showOpenDialog(null);
        f1 = fchoser.getSelectedFile();
        fname1 = f1.getAbsolutePath();
        System.out.println("fname: " + fname1);
        System.out.println("file: " + f1);

        ImageIcon micon = new ImageIcon(fname1);
        try {
            File image = new File(fname1);
            fis1 = new FileInputStream(image);
            System.out.println("fname: " + fname1);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ReturnDifferentType.Im(fis1, f1, fname1);
    }
}
