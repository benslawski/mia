public class CrawlerTask implements Runnable {
    
    public URLDepthPair depthPair;
    
    public CrawlerTask(URLPool pool) {
        
    }
    public void run() {
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
    
}