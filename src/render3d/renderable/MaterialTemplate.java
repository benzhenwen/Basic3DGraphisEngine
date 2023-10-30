package render3d.renderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MaterialTemplate {

    private Color Ka; // ambient color
    private Color Kd; // diffuse color
    private Color Ks; // specular color
    private double Ns; // specular highlights
    private double d; // dissolve
    private int illuminationModel;
    BufferedImage map_Kd;


    // constructor

    public MaterialTemplate() {

    }

}
