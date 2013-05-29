public class CrawlerTask implements Runnable {
    // implements Runnable
    
    public URLDepthPair depthPair;
    
    public CrawlerTask(URLPool pool) {
        
    }
    public void run() {
        while ()
        depthPair = pool.get();
        int myDepth = depthPair.getDepth();
        
        // Get all links from the site and store them in a new linked list.
        LinkedList<String> linksList = new LinkedList<String>();
        linksList = Crawler.getAllLinks(depthPair);
        
        // Iterate through links from site.
        for (int i=0;i<linksList.size();i++) {
            String newURL = linksList.get(i);
                                
            URLDepthPair newDepthPair = new URLDepthPair(newURL, myDepth + 1);
            pool.put(newDepthPair);
                
            
        }
    }
    
    public static void main(String[] args) {
        // Get initial URL, max depth, number of threads from command-line parameters
        // Create a URL pool, add the initial URL to pool
        // Create and start the requested number of threads
        // Check pool every 0.1 to 1s for completion
        // When finished, print URLs in the pool's "seen" list
        
        // A variable for current depth.
        int depth = 0;
        int threads = 0;
        
        // Checks to see if the input was the correct length.  If not, prints
        // out a usage message and exits.
        if (args.length != 3) {
            System.out.println("usage: java Crawler <URL> <depth> <number of crawler threads");
            System.exit(1);
        }
        // If correct input, continue.
        else {
            try {
                // Parse the string argument into an integer value.
                depth = Integer.parseInt(args[1]);
                threads = Integer.parseInt(args[2]);
            }
            catch (NumberFormatException nfe) {
                // The second or third argument isn't a valid integer.  Stop
                // and print out a usage message.
                System.out.println("usage: java Crawler <URL> <depth> <number of crawler threads");
                System.exit(1);
            }
        }

        
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