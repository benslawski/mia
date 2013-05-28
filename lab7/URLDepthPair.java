/**
 * A class to represent [URL, depth] pairs for our Crawler.
 */
public class URLDepthPair {
    
    /**
     * A constant to represent the URL prefix.
     */
    public static final String URL_PREFIX = "http://";
    
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
        int index = 0;
        // Search for the end of the host and set index.
        index = currentURL.indexOf(Crawler.END_URL);
        // Get substring after the end of the host.
        String docPath = currentURL.substring(index);
        return docPath;
    }
    /**
     * A method which returns the webHost of the current URL.
     */
    public String getWebHost() {
        int index = 0;
        int beginIndex = 0;
        int endIndex = 0;
        // Search for the URL prefix.
        index = currentURL.indexOf(URL_PREFIX, index);
        // Advance index length of URL prefix.
        index += URL_PREFIX.length();
        // Set begin index to current index.
        beginIndex = index;
        // Search for end of host.
        index = currentURL.indexOf(Crawler.END_URL);
        // Set end index to end of host - 1.
        endIndex = index - 1;
        // Get substring between URL prefix and end of host.
        String webHost = currentURL.substring(beginIndex, endIndex);
        return webHost;
    }
    
    
}