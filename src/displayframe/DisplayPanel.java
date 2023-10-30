package displayframe;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/* package */ class DisplayPanel extends JPanel {

    BufferedImage b;

    public void paintComponent(Graphics g) {
        g.drawImage(b, 0, 0, null);
    }

    public void draw(BufferedImage b) {
        this.b = b;
        this.repaint();
    }

}
