public class URLDepthPair {
    
    public static final String URL_PREFIX = "http://";
    
    private int currentDepth;
    private String currentURL;
    
    public URLDepthPair(String URL, int depth) {
        currentDepth = depth;
        currentURL = URL;
    }
    public String getURL() {
        return currentURL;
    }
    public int getDepth() {
        return currentDepth;
    }
    public String toString() {
        String stringDepth = Integer.toString(currentDepth);
        return stringDepth + currentURL;
    }
    public String getDocPath() {
        int index = 0;
        index = currentURL.indexOf(Crawler.END_URL);
        String docPath = currentURL.substring(index);
        return docPath;
    }
    public String getWebHost() {
        int index = 0;
        int beginIndex = 0;
        int endIndex = 0;
        index = currentURL.indexOf(URL_PREFIX, index);
        index += URL_PREFIX.length();
        beginIndex = index;
        index = currentURL.indexOf(Crawler.END_URL);
        endIndex = index - 1;
        String webHost = currentURL.substring(beginIndex, endIndex);
        return webHost;
    }
    
    
}