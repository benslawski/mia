import java.io.*;


/**
 * This class houses the method main.
 */
public class Lab1 {

    /**
     * Obtains one double-precision floating point number from
     * standard input prompted with a message of what that number represents.
     *
     * @return return the inputted double, or 0 on error.
     */
    public static double getDouble(String message) {

        System.out.println(message);

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

    /**
     * Takes three ordered triples from the user, generates three Point3d 
     * objects, and prints the area of the triangle bounded by those 
     * three points.
     */
    public static void main(String [] args) {
        
        // Generates three Point3d objects.
        Point3d point0 = new Point3d(getDouble("Point 0 x coord"), 
        getDouble("Point 0 y coord"), getDouble("Point 0 z coord"));

        Point3d point1 = new Point3d(getDouble("Point 1 x coord"), 
        getDouble("Point 1 y coord"), getDouble("Point 1 z coord"));
       
        Point3d point2 = new Point3d(getDouble("Point 2 x coord"), 
        getDouble("Point 2 y coord"), getDouble("Point 2 z coord"));
        
        // Checks to see if any of the points are the same. 
        // If they are, prints an error message and exits.
        if (point0.equals(point1) || point1.equals(point2) || point0.equals(point2)) {
            System.out.println("Two or more points are the same.");
            System.exit(0);
    }
        // Computes and prints the area of the triangle bounded by the points. 
        double area = computeArea(point0, point1, point2);
        System.out.printf("area: %.4f\n", area);
    }

    /** 
     * Takes three Point3ds and returns the area 
     * within the triangle bounded by them. 
     */
    public static double computeArea(Point3d point0, Point3d point1, Point3d point2) {

        // Solves for variables a, b, and c.
        double a = point0.distanceTo(point1);
        double b = point1.distanceTo(point2);
        double c = point0.distanceTo(point2);

        // Solves for area using a, b, and c.
        double s = (a + b + c)/2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}
