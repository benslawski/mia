public class CrawlerTask implements Runnable {
    // implements Runnable
    
    public CrawlerTask(URLPool name) {
        
    }
    public void run() {
        // Get a URL from the pool, download the web page, looking for new URLs, stick new URLs back into the pool, go back to the beginning!
        // Process each URL in a helper method (from last week)
        // Handle exceptions gracefully. If a problem occurs with a URL, go to the next one!
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