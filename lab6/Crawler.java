// need to have main print URLs

import java.net.*;
import java.util.*;
import java.io.*;

public class Crawler {
    
    
    public static final String URL_INDICATOR = "a href=\"";
    
    public static final String END_URL = "\"";
    
    public static void main(String[] args) {
        int depth = 0;
        if (args.length != 2) {
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
        
        
        LinkedList<URLDepthPair> pendingURLs = new LinkedList<URLDepthPair>();
        LinkedList<URLDepthPair> processedURLs = new LinkedList<URLDepthPair>();
        URLDepthPair currentDepthPair = new URLDepthPair(args[0], 0);
        pendingURLs.add(currentDepthPair);
        ArrayList<String> seenURLs = new ArrayList<String>();
        seenURLs.add(currentDepthPair.getURL());
        
        while (pendingURLs.size() != 0) {
            URLDepthPair depthPair = pendingURLs.pop();
            processedURLs.add(depthPair);
            int myDepth = depthPair.getDepth();
            LinkedList<String> linksList = new LinkedList<String>();
            linksList = Crawler.getAllLinks(depthPair);
            if (myDepth < depth) {
                for (int i=0;i<linksList.size();i++) {
                    String newURL = linksList.get(i);
                    if (seenURLs.contains(newURL)) {
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
        Iterator<URLDepthPair> iter = processedURLs.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
    private static LinkedList<String> getAllLinks(URLDepthPair myDepthPair) {
        try {
            Socket sock = new Socket(myDepthPair.getURL(), 80);
        }
        catch (UnknownHostException e) {
            
        }
        try {
            sock.setSoTimeout(3000);
        }
        catch (SocketException e) {
            
        }
        String docPath = myDepthPair.getDocPath();
        String webHost = myDepthPair.getWebHost();
        
        OutputStream outStream = sock.getOutputStream();
        
        // true means PrintWriter will flush after every output
        PrintWriter myWriter = new PrintWriter(outStream, true);
        
        if (!webHost.startsWith(URLDepthPair.URL_PREFIX)) {
            throw new MalformedURLException();
        }
        
        myWriter.println("GET" + docPath + " HTTP:/1.1");
        myWriter.println("Host: " + webHost);
        myWriter.println("Connection: close");
        myWriter.println();
        
        // Request is sent!  Server will start responding now.
        
        InputStream inStream = sock.getInputStream();
        InputStreamReader inStreamReader = new InputStreamReader(inStream);
        BufferedReader BuffReader = new BufferedReader(inStreamReader);
        LinkedList<String> URLs = new LinkedList<String>();
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
                String newLink = line.substring(beginIndex, endIndex);
                URLs.add(newLink);
            }
            
        }
        return URLs;
    }
    
}

