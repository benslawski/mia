import java.net.*;
import java.util.*;

public class Crawler {
    
    public static void main(String[] args) {
        int depth = 0;
        if (args.length =! 2) {
            System.out.println("usage: java Crawler <URL> <depth>");
            System.exit(1);
        }
        else {
            try {
                // Parse the string argument into an integer value.
                depth = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException nfe) {
                // The second argument isn't a valid integer.  Stop and print
                // out a usage message.
                System.out.println("usage: java Crawler <URL> <depth>");
                System.exit(1);
            }

        }
        LinkedList<URLDepthPair> pendingURLs = newLinkedList<URLDepthPair>();
        LinkedList<URLDepthPair> processedURLs = newLinkedList<URLDepthPair>();
        URLDepthPair currentDepthPair = new URLDepthPair(args[0], 0);
        pendingURLs.add(currentDepthPair);
        List<String> seenURLs = new List<String>;
        while (pendingURLs.size != 0) {
            depthPair = pendingURLs.pop();
            seenURLs.add(depthPair.getURL());
            processedURLs.add(depthPair);
            myDepth = depthPair.getDepth();
            linksList = getAllLinks(depthPair.getURL());
            if (myDepth < depth) {
                for (int i=0);i<linksList.size();i++) {
                    newURL = list.get(i);
                    if seenURLs.contains(newURL) {
                        continue;
                    }
                    else {
                        URLDepthPair newDepthPair = new URLDepthPair(newURL, myDepth + 1);
                        pendingURLs.add(newDepthPair);
                    }
                }
            }
    }
    
}

public class URLDepthPair {
    private int currentDepth;
    private String currentURL;
    
    public URLDepthPair(String URL, int depth) {
        currentDepth = depth;
        currentURL = URL;
    }
    public getURL() {
        return currentURL;
    }
    public getDepth() {
        return currentDepth;
    }
    public toString() {
        return currentDepth.toString() + currentURL;
    }
    // URL parsing and manipulation
}