package render3d.geometry.shape;

import render3d.geometry.math.Matrix;

import java.util.Objects;

public class Vector3 {

    public double x, y, z;


    // constructor

    /**
     * creates a 0 vector
     */
    public Vector3() {
        this(0, 0, 0);
    }

    /**
     * creates a vector with magnitude x, y, z
     * @param x x
     * @param y y
     * @param z z
     */
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    // methods for basic calculations

    /**
     * calculates the magnitude of the vector
     * @return magnitude
     */
    public double getMagnitude() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    /**
     * calculates the dot product of the vector and vector other
     * @param other vector
     * @return      dot product
     */
    public double dot(Vector3 other) {
        return this.x*other.x + this.y*other.y + this.z*other.z;
    }

    /**
     * calculates the angle between this vector and vector other
     * @param other vector
     * @return      angle (rad)
     */
    public double angleFrom(Vector3 other) {
        return Math.acos(this.dot(other) / (this.getMagnitude()*other.getMagnitude()));
    }


    // methods for vector calculations

    /**
     * adds c to every component of the vector
     * @param c constant
     */
    public void add(double c) {
        x += c;
        y += c;
        z += c;
    }

    /**
     * add vector v to the vector
     * @param v vector to add
     */
    public void add(Vector3 v) {
        x += v.x;
        y += v.y;
        z += v.z;
    }

    /**
     * multiplies the vector by a constant c
     * @param c constant
     */
    public void multiply(double c) {
        x *= c;
        y *= c;
        z *= c;
    }

    /**
     * multiply vector v to the vector using the cross product
     * @param v vector to multiply by
     */
    public void cross(Vector3 v) {
        double newX = (this.y * v.z) - (this.z * v.y);
        double newY = (this.x * v.z) - (this.z * v.x);
        double newZ = (this.x * v.y) - (this.y * v.x);
        x = newX; y = -newY; z = newZ;
    }

    /**
     * sets vector to the normalized form of the vector (i, j, k)
     */
    public void normalize() {
        double mag = this.getMagnitude();
        if(mag == 0) return;
        this.multiply(1/mag);
    }


    // methods for comparison

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3 vector3 = (Vector3) o;
        return Double.compare(x, vector3.x) == 0 && Double.compare(y, vector3.y) == 0 && Double.compare(z, vector3.z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }


    // conversion methods

    /**
     * converts the vector3 into a 3x1 matrix
     * @return matrix
     */
    public Matrix toVerticalMatrix() {
        Matrix m = new Matrix(3, 1);
        m.set(0, 0, x);
        m.set(1, 0, y);
        m.set(2, 0, z);
        return m;
    }

    /**
     * converts the vector to the cross product matrix form
     * @return matrix
     */
    public Matrix toCrossProductMatrix() {
        Matrix output = new Matrix(3, 3);
        output.set(0, 1, -z);
        output.set(0, 2, y);
        output.set(1, 0, z);
        output.set(1, 2, -x);
        output.set(2, 0, -y);
        output.set(2, 1, x);
        return output;
    }

    // other

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append('<').append(x).append(", ").append(y).append(", ").append(z).append('>').toString();
    }

}
