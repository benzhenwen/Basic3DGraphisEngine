package render3d.geometry.math;

import java.util.Objects;

public class EulerAngle {

    private double z, y, x;


    // constructor

    /**
     * creates a zyx euler angle
     * @param z z
     * @param y y
     * @param x x
     */
    public EulerAngle(double z, double y, double x) {
        this.z = z;
        this.y = y;
        this.x = x;
    }


    // transform methods

    //TODO
    public RotationMatrix toRotationMatrix() {
        return null;
    }




    // comparison

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EulerAngle that = (EulerAngle) o;
        return Double.compare(z, that.z) == 0 && Double.compare(y, that.y) == 0 && Double.compare(x, that.x) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(z, y, x);
    }


    // other

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EulerAngle: (y x z) ");
        sb.append(y).append(' ').append(x).append(' ').append(z);
        return sb.toString();
    }

}
