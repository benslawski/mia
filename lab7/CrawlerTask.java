import java.util.*;

//FIX

/** CrawlerTask implements the Runnable interface.  Each instance has a
 * reference to an instance of the URLPool class.  Gets a URL depth pair from
 * the pool (waiting if not available), retrieves the web page, gets all
 * the URLs from the page, and adds a new URLDepth pair to the URL pool for
 * each URL found.
 */
public class CrawlerTask implements Runnable {
    
    /** A field for the given depth pair */
    public URLDepthPair depthPair;
    
    public int threads;
    
    /** A method to run the tasks of CrawlerTask */
    public void run(URLPool pool) {

        // Get the next depth pair from the pool.
        depthPair = pool.get();
        
        // The depth of the depth pair.
        int myDepth = depthPair.getDepth();
        
        // Get all links from the site and store them in a new linked list.
        LinkedList<String> linksList = new LinkedList<String>();
        linksList = Crawler.getAllLinks(depthPair);
        
        // Iterate through links from site.
        for (int i=0;i<linksList.size();i++) {
            String newURL = linksList.get(i);
            
            // Create a new depth pair for each link found and add to pool.
            URLDepthPair newDepthPair = new URLDepthPair(newURL, myDepth + 1);
            pool.put(newDepthPair);
        }
    }
}