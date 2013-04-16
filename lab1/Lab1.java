import java.io.*;
import Point3d.java;

/**
 * omething
 */
public class Lab1 {

    /**
     * Obtains one double-precision floating point number from
     * standard input.
     *
     * @return return the inputted double, or 0 on error.
     */
    public static double getDouble(String message) {

        System.out.printIn(message);

        // There's potential for the input operation to "fail"; hard with a
        // keyboard, though!
        try {
            // Set up a reader tied to standard input.
            BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

            // Read in a whole line of text.
            String s = br.readLine();

            // Conversion is more likely to fail, of course, if there's a typo.
            try {
                double d = Double.parseDouble(s);

                //Return the inputted double.
                return d;
            }
            catch (NumberFormatException e) {
                // Bail with a 0.  (Simple solution for now.)
                return 0.0;
            }
        }
        catch (IOException e) {
            // This should never happen with the keyboard, but we'll
            // conform to the other exception case and return 0 here,
            // too.
            return 0.0;
        }
    }

    /** Takes three ordered triples from the user and generates three Point3d objects. */
    public static void main(String [] args) {
        Point3d point0 = new Point3d(getDouble(Point 0 x coord), getDouble(Point 0 y coord), getDouble(Point 0 z coord));
        Point3d point1 = new Point3d(getDouble(Point 1 x coord), getDouble(Point 1 y coord), getDouble(Point 1 z coord));
        Point3d point2 = new Point3d(getDouble(Point 2 x coord), getDouble(Point 2 y coord), getDouble(Point 2 z coord));
        if (point0.equals(point1) || point1.equals(point2) || point0.equals(point2)) {
            System.out.printI("Two or more points are the same. Please enter three different points.");
            System.exit(0);
    }
        double area = computeArea(point0, point1, point2);
        System.out.printf("area: %.4f", area);
    }

    /** Takes three Point3ds and computes the area within the triangle bounded by them. */
    public static double computeArea(point0, point1, point2) {
        double a = point0.distanceTo(point1);
        double b = point1.distanceTo(point2);
        double c = point0.distanceTo(point2);
        double s = (a + b + c)/2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

}
