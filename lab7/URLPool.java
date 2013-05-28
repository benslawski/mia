public class URLPool {
    // List of all URLs to be searched with search depth.  Store as URLDepthPair instance
    private LinkedList items;
    // One list for URLs to crawl, another for URLs seen
    public synchronized int waitingThreads;
    
    public URLPool(int size) {
        waitingThreads = 0;
        items = new LinkedList();
    }
    
    public boolean put(Object obj) {
        boolean added = false;
        
        synchronized (items) {
            items.addLast(obj);
            added = true;
            // Add to seen list
            // only add to pending list if depth is less than max depth, then notify
                
            // Added something, so wake up a consumer.
            waitingThreads--;
            items.notify();
            
        }
        
        return added;
    }
    
    public synchronized Object get() {
        Object item = null;
        while (items.size() == 0) {
            waitingThreads++;
            wait();
            
        return items.removeFirst();
    }
}