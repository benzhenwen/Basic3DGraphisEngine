package render3d.geometry.shape;

import render3d.geometry.math.RotationMatrix;

import java.util.*;

public class FaceVertexMesh extends Mesh {

    private HashMap<Vertex3, Vertex3> vertices;
    private HashMap<Triangle, Triangle> faces;


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

    protected void loadRAW(String loadString) {
        String[] triangleStrings = loadString.split("\n");

        for(String triangleString : triangleStrings) {
            String[] coordinates = triangleString.split(" ");

            this.addFace(new Triangle(
                    new Vertex3(
                            Double.parseDouble(coordinates[0]),
                            Double.parseDouble(coordinates[1]),
                            Double.parseDouble(coordinates[2])
                    ),
                    new Vertex3(
                            Double.parseDouble(coordinates[3]),
                            Double.parseDouble(coordinates[4]),
                            Double.parseDouble(coordinates[5])
                    ),
                    new Vertex3(
                            Double.parseDouble(coordinates[6]),
                            Double.parseDouble(coordinates[7]),
                            Double.parseDouble(coordinates[8])
                    )
            ));
        }
    }

    protected void loadOBJ(String loadString) {
        String[] lines = loadString.split("\n"); // removes all duplicate spaces
        for(int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].trim().replaceAll("\\s+", " ");
        }

        int vertexCount = 0;
        for(String line : lines) {
            if(line.startsWith("v")) vertexCount++;
            else if(line.startsWith("f")) break; // break once faces are hit for optimization
        }

        Vertex3[] vertexIDs = new Vertex3[vertexCount];
        int vertexIDsIndex = 0;

        int breakpoint = -1;
        for(int i = 0; i < lines.length; i++) { // main loop for adding vertices
            String line = lines[i];
            if(line.startsWith("v")) {
                String[] lineValues = line.split(" ");

                Vertex3 newVertex = new Vertex3(
                        Double.parseDouble(lineValues[1]),
                        Double.parseDouble(lineValues[2]),
                        Double.parseDouble(lineValues[3])
                );

                vertexIDs[vertexIDsIndex++] = newVertex;
                vertices.put(newVertex, newVertex);
            }
            else if(line.startsWith("f")) {
                breakpoint = i;
                break; // break once faces are hit for optimization
            }
        }

        for(int i = breakpoint; i < lines.length; i++) { // main loop for adding faces
            String line = lines[i];
            if(line.startsWith("f")) {
                String[] lineValues = line.split(" ");
                rawAddFace(
                        vertexIDs[Integer.parseInt(lineValues[1])-1],
                        vertexIDs[Integer.parseInt(lineValues[2])-1],
                        vertexIDs[Integer.parseInt(lineValues[3])-1]
                );
            }
        }


    }


    // modification

    public void addFace(Triangle face) {
        Vertex3 v1 = face.getV1();
        Vertex3 v2 = face.getV2();
        Vertex3 v3 = face.getV3();
        vertices.put(v1, v1);
        vertices.put(v2, v2);
        vertices.put(v3, v3);
        Triangle newFace = new Triangle(
                vertices.get(v1),
                vertices.get(v2),
                vertices.get(v3)
        );
        faces.put(newFace, newFace);
    }

    private void rawAddFace(Vertex3 v1, Vertex3 v2, Vertex3 v3) {
        Triangle t = new Triangle(v1, v2, v3);
        faces.put(t, t);
    }

    public void applyRotationMatrix(RotationMatrix m) {
        for(Vertex3 v : vertices.values()) {
            v.applyRotationMatrix(m);
        }
    }


    // accessors

    public Vertex3[] getVertexList() {
        return vertices.values().toArray(new Vertex3[0]);
    }

    public Edge[] getEdgeList() {
        HashSet<Edge> output = new HashSet<>();

        for(Triangle t : faces.values()) {
            output.add(t.getEdge1());
            output.add(t.getEdge2());
            output.add(t.getEdge3());
        }

        return output.toArray(new Edge[0]);
    }

    public Triangle[] getFaceList() {
        return faces.values().toArray(new Triangle[0]);
    }

    public void clear() {
        vertices = new HashMap<>();
        faces = new HashMap<>();
    }

}
