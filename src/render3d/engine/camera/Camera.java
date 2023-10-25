package render3d.engine.camera;

import render3d.geometry.math.Quaternion;
import render3d.geometry.shape.Mesh;
import render3d.geometry.shape.Vector3;
import render3d.geometry.shape.Vertex3;

import java.awt.image.BufferedImage;

public abstract class Camera {

    protected Vertex3 position;
    protected Quaternion rotation;


    // accessors

    public void setPosition(Vertex3 position) {
        this.position = position;
    }

    public void setRotation(Quaternion rotation) {
        this.rotation = rotation;
    }

    public void move(Vector3 movement) {
        this.position.move(movement);
    }

    //TODO
    public void rotate(Quaternion rotation) {

    }

    public Vertex3 getPosition() {
        return this.position;
    }

    public Quaternion getRotation() {
        return this.rotation;
    }


    // rendering

    public abstract void renderTo(BufferedImage image, Mesh mesh, Vector3 light);

}
