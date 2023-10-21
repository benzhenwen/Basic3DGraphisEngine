package main;

import displayframe.DisplayFrame;
import render3d.engine.BasicForwardsRenderEngine;
import render3d.geometry.math.AxisAngle;
import render3d.geometry.math.RotationMatrix;
import render3d.geometry.shape.FaceVertexMesh;
import render3d.geometry.shape.Mesh;
import render3d.geometry.shape.Triangle;
import render3d.geometry.shape.Vertex3;

public class BasicOrthogonalDemo {

    public static DisplayFrame f;
    public static Vertex3 v1, v2, v3, v4;

    public static void main(String[] args) {

        f = new DisplayFrame(400, 400);

        v1 = new Vertex3(-100, -100, -100);
        v2 = new Vertex3(-100, -100, 100);
        v3 = new Vertex3(100, -100, 100);
        v4 = new Vertex3(-100, 100, 100);

        Triangle t1 = new Triangle(v1, v2, v3);
        Triangle t2 = new Triangle(v1, v2, v4);
        Triangle t3 = new Triangle(v2, v3, v4);

        Mesh m = new FaceVertexMesh();
        m.addFace(t1);
        m.addFace(t2);
        m.addFace(t3);

        AxisAngle e = new AxisAngle(Math.PI/30, 1, 0.5, 0.2);
        RotationMatrix rm = e.toRotationMatrix();

        while(true) {
            m.rotate(rm);
            f.draw(BasicForwardsRenderEngine.renderLineMesh(m, -200, -200, 200, 200));

            try {
                Thread.sleep(1000/30);
            } catch (Exception ex) {};
        }


    }

}
