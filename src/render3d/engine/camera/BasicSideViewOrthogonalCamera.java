package render3d.engine.camera;

import render3d.geometry.math.*;
import render3d.geometry.shape.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Arrays;

public class BasicSideViewOrthogonalCamera extends OrthogonalCamera {


    // constructor

    public BasicSideViewOrthogonalCamera(Vertex3 position, double width, double height) {
        this.position = position;
        this.cameraWidth = width;
        this.cameraHeight = height;
    }


    // rendering

    public void renderTo(BufferedImage image, Mesh mesh, Vector3 light) {

        light.normalize();

        Triangle[] meshTriangles = mesh.getFaceList();

        // maybe I want to sort triangles by depth closest to furthest for optimization

        int w = image.getWidth();
        int h = image.getHeight();

        int[] pixelVector = new int[w*h];

        double[] zBuffer = new double[w*h];
        Arrays.fill(zBuffer, Double.MAX_VALUE);

        for(Triangle t : meshTriangles) {
            double xMin = Math.min(Math.min(t.getV1().x, t.getV2().x), t.getV3().x);
            double xMax = Math.max(Math.max(t.getV1().x, t.getV2().x), t.getV3().x);
            double yMin = Math.min(Math.min(t.getV1().y, t.getV2().y), t.getV3().y);
            double yMax = Math.max(Math.max(t.getV1().y, t.getV2().y), t.getV3().y);

            int pixelColMin = Math.max(0, (int) Math.floor(((xMin - position.x) / cameraWidth) * w));
            int pixelColMax = Math.min(w-1, (int) Math.ceil(((xMax - position.x) / cameraWidth) * w));
            int pixelRowMin = Math.max(0, (int) Math.floor(((yMin - position.y) / cameraHeight) * h));
            int pixelRowMax = Math.min(h-1, (int) Math.ceil(((yMax - position.y) / cameraHeight) * h)); // "bounding box" calculations

            double faceDepth = t.getV1().z/3 + t.getV2().z/3 + t.getV3().z/3;
            Vector3 faceNorm = t.getNormal();
            faceNorm.normalize();
            int shade = (int) (Math.pow(((light.dot(faceNorm) + 1)/2), 2) * 255);

            if(faceDepth > this.position.z) { // is the face behind the camera?
                for(int row = pixelRowMin; row <= pixelRowMax; row++) {
                    for(int col = pixelColMin; col <= pixelColMax; col++) { // steps through every pixel within the bounding box
                        if(zBuffer[row * h + col] > faceDepth) { // the face is the upmost rendered face so far

                            double xLocation = (col / (double) w) * cameraWidth + position.x;
                            double yLocation = (row / (double) h) * cameraHeight + position.y; // the world position
                            Vertex3 pos = new Vertex3(xLocation, yLocation, 0);

                            Vector3 edge1Vector = t.getEdge1().toVector();
                            Vector3 edge2Vector = t.getEdge2().toVector();
                            Vector3 edge3Vector = t.getEdge3().toVector();

                            edge1Vector.cross(t.getV1().toVector(pos));
                            edge2Vector.cross(t.getV2().toVector(pos));
                            edge3Vector.cross(t.getV3().toVector(pos));

                            if(
                                    ( edge1Vector.z >= 0 && edge2Vector.z >= 0 && edge3Vector.z >= 0 ) ||
                                    ( edge1Vector.z <= 0 && edge2Vector.z <= 0 && edge3Vector.z <= 0 ) // check if inside the triangle
                            ) {
                                zBuffer[row * h + col] = faceDepth;

                                pixelVector[row * h + col] = new Color(shade, shade, shade).getRGB();
                            }
                        }
                    }
                }
            }
        }
        image.getRaster().setDataElements(0, 0, w, h, pixelVector); // write the image
    }


    // override rotation to be not possible
    @Override
    public void setRotation(Quaternion rotation) {
        throw new UnsupportedOperationException("Cannot rotate a statically rotated camera");
    }

    @Override
    public void rotate(Quaternion rotation) {
        throw new UnsupportedOperationException("Cannot rotate a statically rotated camera");
    }

    @Override
    public Quaternion getRotation() {
        throw new UnsupportedOperationException("Cannot get rotation of a fixed rotation camera");
    }



}
