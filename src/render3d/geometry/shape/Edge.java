package render3d.geometry.shape;

import java.util.Objects;

public class Edge {

    private Vertex3 v1, v2;


    // constructor

    public Edge(Vertex3 v1, Vertex3 v2) {
        this.v1 = v1;
        this.v2 = v2;
    }


    // accessor methods

    public void setV1(Vertex3 v) {
        v1 = v;
    }

    public void setV2(Vertex3 v) {
        v2 = v;
    }

    public Vertex3 getV1() {
        return v1;
    }

    public Vertex3 getV2() {
        return v2;
    }

    public Vector3 toVector() {
        return v1.toVector(v2);
    }


    // comparison

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(v1, edge.v1) && Objects.equals(v2, edge.v2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1, v2);
    }


    // other

    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("Edge: ").append(v1).append(" -> ").append(v2).toString();
    }

}
