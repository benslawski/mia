public class Crawler {
    public static void main(String[] args) {
        // Get initial URL, max depth, number of threads from command-line parameters
        // Create a URL pool, add the initial URL to pool
        // Create and start the requested number of threads
        // Check pool every 0.1 to 1s for completion
        // When finished, print URLs in the pool's "seen" list
        
        // A variable for current depth.
        int depth = 0;
        int numThreads = 0;
        
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
                numThreads = Integer.parseInt(args[2]);
            }
            catch (NumberFormatException nfe) {
                // The second or third argument isn't a valid integer.  Stop
                // and print out a usage message.
                System.out.println("usage: java Crawler <URL> <depth> <number of crawler threads");
                System.exit(1);
            }
        }
        
        // A URL Depth Pair to represent the website that the user inputted
        // with depth 0.
        URLDepthPair currentDepthPair = new URLDepthPair(args[0], 0);
        
        URLPool pool = new URLPool();
        
        pool.put(currentDepthPair);
        
        for (int i=0;i<=numThreads;i++) {
            CrawlerTask crawler = new CrawlerTask(pool);
            new Thread(crawler).start();
        }
        
        while (pool.getWaitThreads() != numThreads) {
            try {
                Thread.sleep(100);  // 0.1 second
            } catch (InterruptedException ie) {
                System.out.println("Caught unexpected " +
                                   "InterruptedException, ignoring...");
            }
        }
        
        // Print out all processed URLs with depth.
        Iterator<URLDepthPair> iter = processedURLs.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        
        System.exit(0);
    }

}