public class URLPool {
    // List of all URLs to be searched with search depth.  Store as URLDepthPair instance
    
    // A linked list to represent pending URLs.
    private LinkedList<URLDepthPair> pendingURLs;
    
    // A linked list to represent processed URLs.
    private LinkedList<URLDepthPair> processedURLs;
    
    public synchronized int waitingThreads;
    
    public URLPool(int size) {
        waitingThreads = 0;
        pendingURLs = new LinkedList<URLDepthPair>();
        processedURLs = new LinkedList<URLDepthPair>();
    }
    
    public synchronized int getWaitThreads() {
        return waitingThreads;
    }
    
    public boolean put(URLDepthPair depthPair) {
        boolean added = false;
        
        synchronized (pendingURLs) {
            items.addLast(depthPair);
            added = true;
            // Add to seen list
            // only add to pending list if depth is less than max depth, then notify
                
            // Added something, so wake up a consumer.
            waitingThreads--;
            pendingURLs.notify();
            
        }
        
        return added;
    }
    
    public synchronized URLDepthPair get() {
        URLDepthPair item = null;
        while (pendingURLs.size() == 0) {
            waitingThreads++;
            wait();
            
        return URLDepthPair.removeFirst();
    }
}