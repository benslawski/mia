public class URLPool {
    // List of all URLs to be searched with search depth.  Store as URLDepthPair instance
    
    // A linked list to represent pending URLs.
    private LinkedList<URLDepthPair> pendingURLs;
    
    // A linked list to represent processed URLs.
    private LinkedList<URLDepthPair> processedURLs;
    
    // An array list to represent URLs that have been seen. Add current
    // website.
    private ArrayList<String> seenURLs = new ArrayList<String>();
    
    public synchronized int waitingThreads;
    
    public URLPool(int size) {
        waitingThreads = 0;
        pendingURLs = new LinkedList<URLDepthPair>();
        processedURLs = new LinkedList<URLDepthPair>();
    }
    
    public synchronized int getWaitThreads() {
        return waitingThreads;
    }
    
    public synchronized boolean put(URLDepthPair depthPair) {
        boolean added = false;

            if (depthPair.getDepth() < depth) {
                pendingURLs.addLast(depthPair);
                added = true;
                
                // Added something, so wake up a consumer.
                waitingThreads--;
                pendingURLs.notify();
            }
            else {
                seenURLs.add(depthPair.getURL());
            }

        }
        
        return added;
    }
    
    public synchronized URLDepthPair get() {
        URLDepthPair myDepthPair = null;
        while (pendingURLs.size() == 0) {
            waitingThreads++;
            wait();
        } 

        myDepthPair = pendingURLs.removeFirst();
        seenURLs.add(myDepthPair.getURL());
        processedURLs.add(myDepthPair);
        return myDepthPair;
    }
}