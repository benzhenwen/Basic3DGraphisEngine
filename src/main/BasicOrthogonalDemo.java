package main;

import displayframe.DisplayFrame;
import render3d.engine.BasicForwardsRenderEngine;
import render3d.geometry.math.AxisAngle;
import render3d.geometry.math.Matrix;
import render3d.geometry.math.RotationMatrix;
import render3d.geometry.shape.FaceVertexMesh;
import render3d.geometry.shape.Mesh;
import render3d.geometry.shape.Triangle;
import render3d.geometry.shape.Vertex3;

import java.io.File;

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

        AxisAngle rax1 = new AxisAngle(Math.PI/45, 0, 1, 0);
        RotationMatrix rax1Matrix = rax1.toRotationMatrix();

        while(true) {
            f.draw(BasicForwardsRenderEngine.renderLineMesh(m, -400, -400, 400, 400));

            m.applyRotationMatrix(rax1Matrix);

            try{Thread.sleep(1000/30);}catch(Exception e){};
        }

    }

}
