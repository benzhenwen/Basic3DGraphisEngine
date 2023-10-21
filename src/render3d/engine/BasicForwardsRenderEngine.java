package render3d.engine;

import render3d.geometry.shape.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class BasicForwardsRenderEngine {

    public static BufferedImage renderLineMesh(Mesh m, int xMin, int yMin, int xMax, int yMax) {

        BufferedImage b = new BufferedImage(xMax-xMin, yMax-yMin, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) b.getGraphics();

        g2.setBackground(Color.black);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));

        for(Edge e : m.getEdgeList()) {
            g2.drawLine((int) e.getV1().x - xMin, (int) e.getV1().y - yMin, (int) e.getV2().x - xMin, (int) e.getV2().y - yMin);
        }

        return b;
    }

}
