package render3d.geometry.shape;

import render3d.geometry.math.RotationMatrix;

import java.util.Arrays;
import java.util.HashSet;

public class FaceVertexMesh extends Mesh {

    private HashSet<Vertex3> vertices;
    private HashSet<Triangle> faces;


    // constructor

    /**
     * creates a face vertex mesh
     */
    public FaceVertexMesh() {
        clear();
    }

    /**
     * creates a face vertex mesh from a list of faces
     * @param faces face list
     */
    public FaceVertexMesh(Triangle[] faces) {
        for(Triangle t : faces) this.addFace(t);
    }


    // file loading

    //TODO
    protected void loadRAW(String loadString) {
        String[] triangleStrings = loadString.split("\n");

        for(String triangleString : triangleStrings) {
            String[] coordinates = triangleString.split(" ");

            this.addFace(new Triangle(
                    new Vertex3(
                            Integer.parseInt(coordinates[0]),
                            Integer.parseInt(coordinates[1]),
                            Integer.parseInt(coordinates[2])
                    ),
                    new Vertex3(
                            Integer.parseInt(coordinates[3]),
                            Integer.parseInt(coordinates[4]),
                            Integer.parseInt(coordinates[5])
                    ),
                    new Vertex3(
                            Integer.parseInt(coordinates[6]),
                            Integer.parseInt(coordinates[7]),
                            Integer.parseInt(coordinates[8])
                    )
            ));
        }
    }


    // modification

    public void addFace(Triangle face) {
        faces.add(face);
        vertices.add(face.getV1());
        vertices.add(face.getV2());
        vertices.add(face.getV3());
    }

    public void rotate(RotationMatrix m) {
        for(Vertex3 v : vertices) {
            v.applyRotationMatrix(m);
        }
    }


    // accessors

    public Vertex3[] getVertexList() {
        return vertices.toArray(new Vertex3[0]);
    }

    public Edge[] getEdgeList() {
        HashSet<Edge> output = new HashSet<>();

        for(Triangle t : faces) {
            output.add(t.getEdge1());
            output.add(t.getEdge2());
            output.add(t.getEdge3());
        }

        return output.toArray(new Edge[0]);
    }

    public Triangle[] getFaceList() {
        return faces.toArray(new Triangle[0]);
    }

    public void clear() {
        vertices = new HashSet<>();
        faces = new HashSet<>();
    }

}
