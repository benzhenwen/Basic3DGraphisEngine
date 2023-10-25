package main;

import displayframe.DisplayFrame;
import render3d.engine.BasicForwardsRenderEngine;
import render3d.geometry.math.AxisAngle;
import render3d.geometry.math.Matrix;
import render3d.geometry.math.RotationMatrix;
import render3d.geometry.shape.FaceVertexMesh;
import render3d.geometry.shape.Mesh;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BasicOrthogonalDemo {

    public static DisplayFrame f;

    public static void main(String[] args) {

        f = new DisplayFrame(800, 800);

        Mesh m = new FaceVertexMesh();

        Mesh.fileToMesh(new File("test.obj"), m);

        RotationMatrix scalarMatrix = new RotationMatrix();
        Matrix iMatrix = new Matrix(3);
        iMatrix.multiply(6);
        scalarMatrix.add(iMatrix);

        m.applyRotationMatrix(scalarMatrix);

        AxisAngle ax = new AxisAngle(Math.PI, 1, 0, 0);
        m.applyRotationMatrix(ax.toRotationMatrix());

        AxisAngle rax1 = new AxisAngle(Math.PI/45, 1, 0, 0);
        RotationMatrix rax1Matrix = rax1.toRotationMatrix();

        Runnable runnable = new Runnable() {
            public void run() {
                f.draw(BasicForwardsRenderEngine.renderLineMesh(m, -400, -400, 400, 400));
                m.applyRotationMatrix(rax1Matrix);
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(runnable, 0, 25, TimeUnit.MILLISECONDS);
    }

}
