package render3d.geometry.shape;

import java.util.Objects;

public class Triangle {

    private Vertex3 v1, v2, v3;


    // constructor

    /**
     * creates a triangle with vertices v1, v2, v3
     * @param v1 v1
     * @param v2 v2
     * @param v3 v3
     */
    public Triangle(Vertex3 v1, Vertex3 v2, Vertex3 v3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }


    // accessor methods

    public void setV1(Vertex3 v) {
        v1 = v;
    }

    public void setV2(Vertex3 v) {
        v2 = v;
    }

    public void setV3(Vertex3 v) {
        v3 = v;
    }

    public Vertex3 getV1() {
        return v1;
    }

    public Vertex3 getV2() {
        return v2;
    }

    public Vertex3 getV3() {
        return v3;
    }

    public Edge getEdge1() {
        return new Edge(v1, v2);
    }

    public Edge getEdge2() {
        return new Edge(v2, v3);
    }

    public Edge getEdge3() {
        return new Edge(v3, v1);
    }


    // comparison

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(v1, triangle.v1) && Objects.equals(v2, triangle.v2) && Objects.equals(v3, triangle.v3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1, v2, v3);
    }


    // other

    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("Triangle: \n").append(v1).append('\n').append(v2).append('\n').append(v3).toString();
    }


}
