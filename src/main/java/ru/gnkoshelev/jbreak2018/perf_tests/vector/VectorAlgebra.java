package ru.gnkoshelev.jbreak2018.perf_tests.vector;

/**
 * Created by kgn on 20.03.2018.
 */
public class VectorAlgebra {
    public static double computeWithRawScalars(
            double x1, double y1, double z1,
            double x2, double y2, double z2) {
        double x = y1 * z2 - z1 * y2;
        double y = z1 * x2 - x1 * z2;
        double z = x1 * y2 - y1 * x2;
        return x * x + y * y + z * z;
    }

    public static double computeWithVectors(
            double x1, double y1, double z1,
            double x2, double y2, double z2) {
        Vector v1 = new Vector(x1, y1, z1);
        Vector v2 = new Vector(x2, y2, z2);
        return v1.crossProduct(v2).squared();
    }

    public final static class Vector {
        private final double x, y, z;

        public Vector(double x, double y, double z) {
            this.x = x; this.y = y; this.z = z;
        }

        public double squared() {
            return x * x + y * y + z * z;
        }

        public Vector crossProduct(Vector v) {
            return new Vector(
                    y * v.z - z * v.y,
                    z * v.x - x * v.z,
                    x * v.y - y * v.x);
        }
    }

    public static double computeJittedPseudocode(
            double x1, double y1, double z1,
            double x2, double y2, double z2) {
        double v1_x = x1, v1_y = y1, v1_z = z1;// new Vector(x1, y1, z1);
        double v2_x = x2, v2_y = y2, v2_z = z2;// new Vector(x2, y2, z2);
        double x = v1_y * v2_z - v1_z * v2_y; // inline crossProduct
        double y = v1_z * v2_x - v1_x * v2_z; // inline crossProduct
        double z = v1_x * v2_y - v1_y * v2_x; // inline crossProduct
        // new Vector(x, y, z);
        return x * x + y * y + z * z;// inline squared
    }}
