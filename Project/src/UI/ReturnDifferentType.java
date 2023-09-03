package UI;

import java.awt.image.*;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author Salma
 */
public class ReturnDifferentType {

    // A class that is used to store and return three members of different types
    public static class Im {

        FileInputStream fis = null;
        File f = null;
        String fname;

        public Im(FileInputStream fis, File f, String fname) {
            this.fis = fis;
            this.f = f;
            this.fname = fname;
        }

        public Im(FileInputStream fis, File f) {
            this.fis = fis;
            this.f = f;
        }

        public FileInputStream getFis() {
            return fis;
        }

        public void setFis(FileInputStream fis) {
            this.fis = fis;
        }

        public File getF() {
            return f;
        }

        public void setF(File f) {
            this.f = f;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

    }

    //class to return ImageIcon and BufferedImage
    public static class ImageBuffer {

        ImageIcon ic = null;
        BufferedImage bufferedImage = null;

        public ImageBuffer() {
        }

        public ImageBuffer(ImageIcon ic, BufferedImage bufferedImage) {
            this.bufferedImage = bufferedImage;
            this.ic = ic;
        }

        public ImageIcon getIc() {
            return ic;
        }

        public void setIc(ImageIcon ic) {
            this.ic = ic;
        }

        public BufferedImage getBufferedImage() {
            return bufferedImage;
        }

        public void setBufferedImage(BufferedImage bufferedImage) {
            this.bufferedImage = bufferedImage;
        }

    }
}
