package displayframe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class DisplayFrame extends JFrame {

    private DisplayPanel panel;


    // constructor

    /**
     * creates a frame with width and height and a name of "Display"
     * @param width width
     * @param height height
     */
    public DisplayFrame(int width, int height) {
        this(width, height, "Display");
    }

    /**
     * creates a frame with width and height and name
     * @param width width
     * @param height height
     * @param name name
     */
    public DisplayFrame(int width, int height, String name) {
        this.setName(name);
        this.setSize(width, height);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new DisplayPanel();
        panel.setSize(width, height);

        this.setContentPane(panel);
        this.setVisible(true);

    }


    // drawing

    /**
     * draws an image
     * @param image image
     */
    public void draw(BufferedImage image) {
        panel.draw(image);
    }

}
