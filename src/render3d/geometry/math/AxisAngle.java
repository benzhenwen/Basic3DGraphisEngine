package render3d.geometry.math;

import render3d.geometry.shape.Vector3;

import java.util.Objects;

public class AxisAngle {

    private double angle;
    private Vector3 axis;


    // constructor

    /**
     * creates an axis angle
     * @param angle angle
     * @param x x part of axis
     * @param y y part of axis
     * @param z z part of axis
     */
    public AxisAngle(double angle, double x, double y, double z) {
        this(angle, new Vector3(x, y, z));
    }

    /**
     * creates a quaternion
     * @param angle the real part
     * @param axis the imaginary part as a Vector3
     */
    public AxisAngle(double angle, Vector3 axis) {
        this.angle = angle;
        this.axis = axis;
        axis.normalize();
    }


    // accessor methods



    // transform methods

    /**
     * calculates the quaternion representation of the axis angle
     * @return quaternion
     */
    public Quaternion toQuaternion() {
        double q0 = Math.cos(angle/2);
        double q1 = axis.x * Math.sin(angle/2);
        double q2 = axis.y * Math.sin(angle/2);
        double q3 = axis.z * Math.sin(angle/2);
        return new Quaternion(q0, q1, q2, q3);
    }

    /**
     * calculates the euler angle representation of the axis angle
     * @return euler angle
     */
    //TODO
    public EulerAngle toEulerAngle() {
        return null;
    }

    /**
     * calculates the rotation matrix representation of the axis angle
     * @return rotation matrix
     */
    public RotationMatrix toRotationMatrix() {
        double x = axis.x;
        double y = axis.y;
        double z = axis.z;

        double c = Math.cos(angle);
        double s = Math.sin(angle);
        double C = 1 - c;

        RotationMatrix m = new RotationMatrix();
        m.set(0, 0, x*x*C+c);
        m.set(0, 1, x*y*C-z*s);
        m.set(0, 2, x*z*C+y*s);
        m.set(1, 0, y*x*C+z*s);
        m.set(1, 1, y*y*C+c);
        m.set(1, 2, y*z*C-x*s);
        m.set(2, 0, z*x*C-y*s);
        m.set(2, 1, z*y*C+x*s);
        m.set(2, 2, z*z*C+c);

        return m;
    }



    // operations

    /**
     * normalizes the axis so that the quaternion becomes a rotation quaternion
     */
    public void normalize() {
        axis.normalize();
    }


    // comparison

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AxisAngle axisAngle = (AxisAngle) o;
        return Double.compare(angle, axisAngle.angle) == 0 && Objects.equals(axis, axisAngle.axis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(angle, axis);
    }


    // other

    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("AxisAngle: ").append(angle).append(" rad ").append(axis).toString();
    }

}
