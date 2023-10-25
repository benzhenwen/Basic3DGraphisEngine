package main;

import displayframe.DisplayFrame;
import render3d.engine.camera.BasicSideViewOrthogonalCamera;
import render3d.engine.camera.Camera;
import render3d.geometry.math.*;
import render3d.geometry.shape.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CameraOrthogonalDemo {

    public static void main(String[] args) {

        DisplayFrame f = new DisplayFrame(800, 800);

        Mesh m = new FaceVertexMesh();

        Mesh.fileToMesh(new File("tinker.obj"), m);

        RotationMatrix scalarMatrix = new RotationMatrix();
        Matrix iMatrix = new Matrix(3);
        iMatrix.multiply(0.3);
        scalarMatrix.add(iMatrix);

        m.applyRotationMatrix(scalarMatrix);
        m.translate(new Vector3(3, 3, 0));

        AxisAngle ax = new AxisAngle(Math.PI, 1, 0, 0);
        m.applyRotationMatrix(ax.toRotationMatrix());

        AxisAngle rotate1 = new AxisAngle(Math.PI/60, 1, 0, 0);
        RotationMatrix rotMatrix1 = rotate1.toRotationMatrix();

        AxisAngle rotate2 = new AxisAngle(Math.PI/60, 0, 1, 0);
        RotationMatrix rotMatrix2 = rotate2.toRotationMatrix();

        AxisAngle rotate3 = new AxisAngle(Math.PI/60, 0, 0, 1);
        RotationMatrix rotMatrix3 = rotate3.toRotationMatrix();

        Camera c = new BasicSideViewOrthogonalCamera(new Vertex3(-8, -8, -1000000), 16, 16);

        BufferedImage out = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) out.getGraphics();
        g2.setBackground(Color.black);

        Runnable runnable = new Runnable() {
            public void run() {
                c.renderTo(out, m, new Vector3(0.5, -0.7, -1));
                f.draw(out);

                m.applyRotationMatrix(rotMatrix1);
                m.applyRotationMatrix(rotMatrix2);
                m.applyRotationMatrix(rotMatrix3);
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(8);
        executor.scheduleAtFixedRate(runnable, 0, 1000/30, TimeUnit.MILLISECONDS);
    }
}
