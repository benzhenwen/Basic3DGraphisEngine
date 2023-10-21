package render3d.geometry.math;

import java.util.Objects;

public class Quaternion {

    private double w, x, y, z;


    // constructor

    /**
     * creates the identity quaternion
     */
    public Quaternion() {
        this(1, 0, 0, 0);
    }

    /**
     * creates a quaternion
     * @param w w
     * @param x x
     * @param y y
     * @param z z
     */
    public Quaternion(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }


    // compare

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quaternion that = (Quaternion) o;
        return Double.compare(w, that.w) == 0 && Double.compare(x, that.x) == 0 && Double.compare(y, that.y) == 0 && Double.compare(z, that.z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(w, x, y, z);
    }


    // other

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Quaternion: ");
        sb.append(w).append('+').
                append(x).append("i+").
                append(y).append("j+").
                append(z).append('k');
        return sb.toString();
    }

}
