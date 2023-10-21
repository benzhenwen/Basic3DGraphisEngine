package render3d.geometry.shape;

import render3d.geometry.math.*;

import java.util.Objects;

/**
 * simple vertex class to represent a point in 3d space.
 */

public class Vertex3 {

    public double x, y, z;


    // constructor

    /**
     * creates a vertex at 0, 0, 0
     */
    public Vertex3() {
        this(0, 0, 0);
    }

    /**
     * creates a vertex at x, y, z
     * @param x x
     * @param y y
     * @param z z
     */
    public Vertex3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    // methods

    public void applyRotationMatrix(RotationMatrix m) {
        Matrix thism = new Matrix(3, 1);
        thism.set(0, 0, x);
        thism.set(1, 0, y);
        thism.set(2, 0, z);

        Matrix otherm = m.clone();

        otherm.multiply(thism);

        x = otherm.get(0, 0);
        y = otherm.get(1, 0);
        z = otherm.get(2, 0);
    }


    // transform methods

    /**
     * returns a vector with starting a start point of this and an end point of v2
     * @param v2 end vertex
     * @return   vector from this to vertex2
     */
    public Vector3 toVector(Vertex3 v2) {
        return new Vector3(v2.x - this.x, v2.y - this.y, v2.z - this.z);
    }


    // methods for comparison

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex3 vertex = (Vertex3) o;
        return Double.compare(x, vertex.x) == 0 && Double.compare(y, vertex.y) == 0 && Double.compare(z, vertex.z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }


    // other

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append('(').append(x).append(", ").append(y).append(", ").append(z).append(')').toString();
    }

}
