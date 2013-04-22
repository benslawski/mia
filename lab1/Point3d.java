/**
 * A three-dimensional point class.
 */
public class Point3d {

    /** X coordinate of the point. */
    private double xCoord;

    /** Y coordinate of the point. */
    private double yCoord;

    /** Z coordinate of the point. */
    private double zCoord; 

    /** Constructor to initialize point (x, y, z) value. */
    public Point3d(double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    /** No-argument constructor: defaults to a point at the origin. */
    public Point3d() {
        // Call three-argument constructor and specify the origin.
        this (0, 0, 0);
    }

    /** Return the X coordinate of the point. */
    public double getX() {
        return xCoord;
    }

    /** Return the Y coordinate of the point. */
    public double getY() {
        return yCoord;
    }

    /** Return the Z coordinate of the point. */
    public double getZ() {
        return zCoord;
    }

    /** Set the X coordinate of the point. */
    public void setX(double val) {
        xCoord = val;
    }

    /** Set the Y coordinate of the point. */
    public void setY(double val) {
        yCoord = val;
    }

    /** Set the Z coordinate of the point. */
    public void setZ(double val) {
        zCoord = val;
    }

    /** Compares this Point3d to another. */
    public boolean equals(Object obj) {
        // Is obj a Point3d?
        if (obj instanceof Point3d) {
            // Cast another object to Point3d type,
            // then compare.
            Point3d other = (Point3d) obj;
            if (xCoord == other.getX() &&
                yCoord == other.getY() &&
                zCoord == other.getZ()) {
                return true;
            }
        }
        
        // If we got here then they're not equal.
        return false;
    }

    /** Computes the straight-line distance between this Point3d and another. */
    public double distanceTo(Point3d other_point) {
        return Math.sqrt(Math.pow(xCoord - other_point.getX(), 2) + 
        Math.pow(yCoord - other_point.getY(), 2) + 
        Math.pow(zCoord - other_point.getZ(), 2));
    }
}
