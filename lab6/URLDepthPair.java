import java.net.*;

/**
 * A class to represent [URL, depth] pairs for our Crawler. 
 */
public class URLDepthPair {
    

    
    /**
     * Fields to represent the current URL and current depth.
     */
    private int currentDepth;
    private String currentURL;
    
    /**
     * A constructor that sets the input to the current URL and depth.
     */
    public URLDepthPair(String URL, int depth) {
        currentDepth = depth;
        currentURL = URL;
    }
    /**
     * A method which returns the current URL.
     */
    public String getURL() {
        return currentURL;
    }
    /** 
     * A method which returns the current depth.
     */
    public int getDepth() {
        return currentDepth;
    }
    /**
     * A method which returns the current URL and current depth in string 
     * format.
     */
    public String toString() {
        String stringDepth = Integer.toString(currentDepth);
        return stringDepth + '\t' + currentURL;
    }
    /** 
     * A method which returns the docPath of the current URL. 
     */
    public String getDocPath() {
        try {
            URL url = new URL(currentURL);
            return url.getPath();
        }
        catch (MalformedURLException e) {
            System.err.println("MalformedURLException: " + e.getMessage());
            return null;
        }
    }
    /**
     * A method which returns the webHost of the current URL.
     */
    public String getWebHost() {
        try {
            URL url = new URL(currentURL);
            return url.getHost();
        }
        catch (MalformedURLException e) {
            System.err.println("MalformedURLException: " + e.getMessage());
            return null;
        }
    }
    
    
}