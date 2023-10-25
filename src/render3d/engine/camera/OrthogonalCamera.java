package render3d.engine.camera;

import render3d.engine.camera.Camera;

public abstract class OrthogonalCamera extends Camera {

    protected double cameraWidth, cameraHeight;


    // accessors

    /**
     * sets the viewing window width
     * @param width width
     */
    public void setViewWidth(double width) {
        if(width <= 0) throw new IllegalArgumentException("width of " + width + " not allowed for OrthogonalCamera");
        cameraWidth = width;
    }

    /**
     * sets the viewing window height
     * @param height height
     */
    public void setViewHeight(double height) {
        if(height <= 0) throw new IllegalArgumentException("height of " + height + " not allowed for OrthogonalCamera");
        cameraHeight = height;
    }

    public double getViewWidth() {
        return cameraWidth;
    }

    public double getCameraHeight() {
        return cameraHeight;
    }

}
