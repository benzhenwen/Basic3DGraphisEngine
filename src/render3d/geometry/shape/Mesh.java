package render3d.geometry.shape;

import render3d.geometry.math.RotationMatrix;

import java.io.File;
import java.nio.file.Files;

public abstract class Mesh {

    // load types

    public enum FILETYPE {RAW, OBJ};


    // constructor

    protected Mesh() {} // for specialized constructors

    /**
     * creates a mesh from a file
     * @param file file
     */
    public Mesh(File file) {
        fileToMesh(file, this);
    }

    /**
     * creates a mesh from a string
     * @param fileString string
     * @param format format of the file
     */
    public Mesh(String fileString, FILETYPE format) {
        fileToMesh(fileString, format, this);
    }


    // accessors

    /**
     * gets a list of all vertices in the mesh
     * @return vertices
     */
    public abstract Vertex3[] getVertexList();

    /**
     * gets a list of all edges in the mesh
     * @return edges
     */
    public abstract Edge[] getEdgeList();

    /**
     * gets a list of all triangle faces in the mesh
     * @return faces
     */
    public abstract Triangle[] getFaceList();

    /**
     * adds a triangle face to the mesh
     * @param face face
     */
    public abstract void addFace(Triangle face);


    // modification methods

    /**
     * rotates all vertices on the mesh by m
     * @param m matrix
     */
    public abstract void applyRotationMatrix(RotationMatrix m);

    /**
     * translates all vertices of the mesh by a vector
     * @param translation translation
     */
    public abstract void translate(Vector3 translation);


    // loading methods

    /**
     * load a file to a mesh format and applies it to the given mesh
     * @param file the source to load from
     * @param mesh the mesh to override
     */
    public static void fileToMesh(File file, Mesh mesh) {
        if(file == null || !file.isFile()) throw new RuntimeException("File cannot be read");
        String[] fileSplit = file.getName().split("\\.");
        String fileExt = fileSplit[fileSplit.length-1].toLowerCase();

        FILETYPE f = switch (fileExt) {
            case "raw" -> FILETYPE.RAW;
            case "obj" -> FILETYPE.OBJ;
            default -> throw new RuntimeException("file extension not supported");
        }; // get the file format

        try {
            String stringIn = Files.readString(file.toPath());
            fileToMesh(stringIn, f, mesh);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * load a string to a mesh
     * @param fileString string of the mesh
     * @param format the format of the file
     * @param mesh the mesh to override
     */
    public static void fileToMesh(String fileString, FILETYPE format, Mesh mesh) {

        switch(format) {
            case RAW:
                mesh.loadRAW(fileString);
                break;
            case OBJ:
                mesh.loadOBJ(fileString);
                break;
        }

    }


    // loading methods for individual types

    protected abstract void loadRAW(String loadString);

    protected abstract void loadOBJ(String loadString);



}
