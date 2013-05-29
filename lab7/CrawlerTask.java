public class CrawlerTask implements Runnable {
    // implements Runnable
    
    public URLDepthPair depthPair;
    
    public CrawlerTask(URLPool name) {
        
    }
    public void run() {
        depthPair = URLDepthPool.get();
        int myDepth = depthPair.getDepth();
        
        // Get all links from the site and store them in a new linked list.
        LinkedList<String> linksList = new LinkedList<String>();
        linksList = Crawler.getAllLinks(depthPair);
        
        // If we haven't reached the maximum depth, add links from the site
        // that haven't been seen before to pendingURLs and seenURLs.
        if (myDepth < depth) {
            // Iterate through links from site.
            for (int i=0;i<linksList.size();i++) {
                String newURL = linksList.get(i);
                // If we've already seen the link, continue.
                if (seenURLs.contains(newURL)) {
                    continue;
                }
                // If we haven't seen the link, create a new URLDepthPair
                // with depth one greater than current depth, and add
                // to pendingURLs and seenURLs.
                else {
                    URLDepthPair newDepthPair = new URLDepthPair(newURL, myDepth + 1);
                    pool.add(newDepthPair);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        // Get initial URL, max depth, number of threads from command-line parameters
        // Create a URL pool, add the initial URL to pool
        // Create and start the requested number of threads
        // Check pool every 0.1 to 1s for copletion
        // When finished, print URLs in the pool's "seen" list
        System.exit(0);
    }
}



CrawlerTask c = new CrawlerTask(pool);
Thread t = new Thread(c);
t.start();

while (pool.getWaitCount() != numThreads) {
    try {
        Thread.sleep(100);  // 0.1 second
    } catch (InterruptedException ie) {
        System.out.println("Caught unexpected " +
            "InterruptedException, ignoring...");
    }
}