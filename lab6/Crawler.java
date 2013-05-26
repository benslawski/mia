// need to catch MalformedURLException for non http:// starting links

import java.net.*;
import java.util.*;

public class Crawler {
    
    
    public static final String URL_INDICATOR = "a href=\"";
    
    public static final String END_URL = "\"";
    
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
        seenURLs.add(currentDepthPair.getURL());
        
        while (pendingURLs.size != 0) {
            depthPair = pendingURLs.pop();
            processedURLs.add(depthPair);
            myDepth = depthPair.getDepth();
            linksList = getAllLinks(depthPair.getURL());
            if (myDepth < depth) {
                for (int i=0;i<linksList.size();i++) {
                    newURL = list.get(i);
                    if seenURLs.contains(newURL) {
                        continue;
                    }
                    else {
                        URLDepthPair newDepthPair = new URLDepthPair(newURL, myDepth + 1);
                        pendingURLs.add(newDepthPair);
                        seenURLs.add(newURL);
                    }
                }
            }
        }
        
        
        
        public LinkedList<URLDEpthPair> getSites() {
            return processedURLs;
        }
    }
    
    public LinkedList<String> getAllLinks(String URL) {
        Socket sock = new Socket(URL, 80);
        sock.setSoTimeout(3000);
        
        OutputStream outStream = sock.getOutputStream();
        
        // true means PrintWriter will flush after every output
        PrintWriter myWriter = new PrintWriter(outStream, true);
        
        myWriter.println("GET" + URLDepthPair.docPath + " HTTP:/1.1")
        myWriter.println("Host: " + URLDepthPair.webHost);
        myWriter.println("Connection: close")
        myWriter.println();
        
        // Request is sent!  Server will start responding now.
        
        InputStream inStream = sock.getInputStream();
        InputStreamReader inStreamReader = new InputStreamReader(inStream);
        BufferedReader BuffReader = new BufferedReader(inStreamReader);
        LinkedList<String> URLs = newLinkedList<String>();
        while (true) {
            String line = BuffReader.readLine();
            if (line == null)
                break;  // Done reading document!
            String start = URLDepthPair.URL_PREFIX;
            String end = END_URL;
            
            //Search for our start in the current line.
            int beginIndex = 0;
            int endIndex = 0;
            int index = 0;
            while (true) {
                index = line.indexOf(URL_INDICATOR, index);
                if (index == -1) // No more copies of start in this line
                    break;
                index += URL_INDICATOR.length();
                beginIndex = index;
                index = line.indexOf(END_URL);
                endIndex = index;
                index += END_URL.length();
                String newLink = substring(beginIndex, endIndex);
                URLs.add(newLink);
            }
            
        }
        return URLs;
    }
}

public class URLDepthPair {
    
    public static final String URL_PREFIX = "http://";

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
    public getDocPath() {
        index = 0
        index = currentURL.indexOf(Crawler.END_URL);
        String docPath = substring(index);
        return docPath;
    }
    public getwebHost() {
        index = 0;
        index = currentURL.indexOf(URL_PREFIX, index);
        index += URL_PREFIX.length();
        beginIndex = index;
        index = currentURL.indexOf(Crawler.END_URL);
        endIndex = index - 1;
        String webHost = substring(beginIndex, endIndex);
        return webHost;
    }
        

}