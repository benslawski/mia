import java.util.*;

/** 
 * A URL pool class to store a list of URLs to be searched with depth.
 * Stored as a URLDepthPair instance.
 */
public class URLPool {
    
    /** A linked list to represent pending URLs. */
    private LinkedList<URLDepthPair> pendingURLs;
    
    /** A linked list to represent processed URLs. */
    public LinkedList<URLDepthPair> processedURLs;
    
    /** An array list to represent URLs that have been seen. */
    private ArrayList<String> seenURLs = new ArrayList<String>();
    
    /** An int to keep track of number of threads waiting. */
    public int waitingThreads;
    
    /** Constructer to initialize waiting threads, processed URLs, and pending
     * URLs.
     */
    public URLPool() {
        waitingThreads = 0;
        pendingURLs = new LinkedList<URLDepthPair>();
        processedURLs = new LinkedList<URLDepthPair>();
    }
    
    /** Synchronized method to get the number of waiting threads. */
    public synchronized int getWaitThreads() {
        return waitingThreads;
    }
    
    /** Synchronized method to return the size of the pool. */
    public synchronized int size() {
        return pendingURLs.size();
    }
    
    /** Synchronized method to add a depth pair to the pool. */
    public synchronized boolean put(URLDepthPair depthPair) {
        
        // A variable to keep track of if the depth pair was added.
        boolean added = false;

        // If the depth is less than the max depth, add the depth pair to
        // the pool. 
        if (depthPair.getDepth() < depthPair.getDepth()) {
            pendingURLs.addLast(depthPair);
            added = true;
                
            // Added something, so wake up a consumer.  Decrement number of
            // waiting threads.
            waitingThreads--;
            this.notify();
        }
        // If the depth is not less than the max depth, just add the depth pair
        // to seen list.
        else {
            seenURLs.add(depthPair.getURL());
        }
        // Return boolean added.
        return added;
        }

    /**
     * A synchronized method to get the next depth pair from the pool.
     */
    public synchronized URLDepthPair get() {
        
        // Set depth pair to null.
        URLDepthPair myDepthPair = null;
        
        // While the pool is empty, wait.
        if (pendingURLs.size() == 0) {
            waitingThreads++;
            try {
                this.wait();
            }
            catch (InterruptedException e) {
                System.err.println("MalformedURLException: " + e.getMessage());
                return null;
            }
        } 
        // Remove the first depth pair, add to seen URLs and processed URLs,
        // and return it.
        myDepthPair = pendingURLs.removeFirst();
        seenURLs.add(myDepthPair.getURL());
        processedURLs.add(myDepthPair);
        return myDepthPair;
    }
    /** 
     * A synchronized method to get the list of seen URLs.
     */
    public synchronized ArrayList<String> getSeenList() {
        return seenURLs;
    }
}