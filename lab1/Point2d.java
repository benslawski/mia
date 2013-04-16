/**
 * A two-dimensional point class.
 */
public class Point2d {
    
    /** X coordinate of the point */
    private double xCoord;
    
    /** Y coordinate of the point */
    private double yCoord;

    /** Constructor to initialize point to (x, y) value. */
    public Point2d(double x, double y) {
        xCoord = x;
        yCoord = y;
    }

    /** No-argument constructor:  defaults to a point at the origin. */
    public Point2d() {
        // Call two-argument constructor and specify the origin.
        this(0, 0);
    }

    /** Return the X coordinate of the point. */
    public double getX() {
        return xCoord;
    }

    /** Return the Y coordinate of the point. */
    public double getY() {
        return yCoord;
    }

    /** Set the X coordinate of the point. */
    public void setX(double val) {
        xCoord = val;
    }

    /** Set the Y coordinate of the point. */
    public void setY(double val) {
        yCoord = val;
    }
}

