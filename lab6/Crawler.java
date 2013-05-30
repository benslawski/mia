import java.net.*;
import java.util.*;
import java.io.*;

/**
 * This class implements the main functionality of our web crawler application. 
 * It has a getAllLinks method to store all links on a given webpage in 
 * addition to the main method which keeps track of important variables.
 */
public class Crawler {
    

    
    /**
     * The crawler's main method.  The program should accept a string 
     * representing the URL at which to start browing and a positive integer
     * representing a maximum search depth.  Stores the URL as a string with
     * depth as URLDepthPair.  Keeps track of links processed, links pending,
     * links seen, and depth.  Prints out a list of all links processed
     * with their depths. Iterates through pendingURLs to getAllLinks and adds
     * them to processedURLs and seenURLs.
     */
    public static void main(String[] args) {
        
        // A variable for current depth.
        int depth = 0;
        
        // Checks to see if the input was the correct length.  If not, prints
        // out a usage message and exits.
        if (args.length != 2) {
            System.out.println("usage: java Crawler <URL> <depth>");
            System.exit(1);
        }
        // If correct input, continue.
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
        
        // A linked list to represent pending URLs.
        LinkedList<URLDepthPair> pendingURLs = new LinkedList<URLDepthPair>();
        
        // A linked list to represent processed URLs.
        LinkedList<URLDepthPair> processedURLs = new LinkedList<URLDepthPair>();
        
        // A URL Depth Pair to represent the website that the user inputted
        // with depth 0. 
        URLDepthPair currentDepthPair = new URLDepthPair(args[0], 0);
        
        // Add the current website from user input to pending URLs.
        pendingURLs.add(currentDepthPair);
        
        // An array list to represent URLs that have been seen. Add current
        // website.
        ArrayList<String> seenURLs = new ArrayList<String>();
        seenURLs.add(currentDepthPair.getURL());
        
        // While pendingURLs is not empty, iterate through, visit each website,
        // and get all links from each.
        while (pendingURLs.size() != 0) {
            
            // Get the next URL from pendingURLs, add to processed URLs, and
            // store its depth.
            URLDepthPair depthPair = pendingURLs.pop();
            processedURLs.add(depthPair);
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
                        pendingURLs.add(newDepthPair);
                        seenURLs.add(newURL);
                    }
                }
            }
        }
        // Print out all processed URLs with depth. 
        Iterator<URLDepthPair> iter = processedURLs.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
    /**
     * A method which takes a URLDepthPair and returns a LinkedList of String
     * type.  Connects to the website in the URLDepthPair, finds all links
     * on the site, and adds them to a new LinkedList which gets returned.
     */
    private static LinkedList<String> getAllLinks(URLDepthPair myDepthPair) {
        
        // Initialize the linked list of strings which will store the links
        // that we find.
        LinkedList<String> URLs = new LinkedList<String>();
        
        // Initialize the socket.
        Socket sock;
        
        // Try to create a new socket with the URL passed to the method in
        // the URLDepthPair and port 80.
        try {
            sock = new Socket(myDepthPair.getWebHost(), 80);
        }
        // Catch UnknownHostException and return empty list.
        catch (UnknownHostException e) {
            System.err.println("UnknownHostException: " + e.getMessage());
            return URLs;
        }
        // Catch IOException and return empty list.
        catch (IOException ex) {
            System.err.println("IOException: " + ex.getMessage());
            return URLs;
        }
        
        // Try to set the socket to timeout after 3s.
        try {
            sock.setSoTimeout(3000);
        }
        // Catch SocketException and return empty list.
        catch (SocketException exc) {
            System.err.println("SocketException: " + exc.getMessage());
            return URLs;
        }
        
        // A string to represent the docPath from the URL passed in as
        // URLDepthPair and a string to represent the webHost.
        String docPath = myDepthPair.getDocPath();
        String webHost = myDepthPair.getWebHost();
        
        // Initialize the OutputStream.
        OutputStream outStream;
        
        // Try to getOutputStream from the socket. 
        try {
            outStream = sock.getOutputStream();
        }
        // Catch IOException and return blank list.
        catch (IOException exce) {
            System.err.println("IOException: " + exce.getMessage());
            return URLs;
        }
        
        // Initializes a PrintWriter. True means PrintWriter will flush after
        // every output.
        PrintWriter myWriter = new PrintWriter(outStream, true);
        
        // Send request to server.  
        myWriter.println("GET " + docPath + " HTTP/1.1");
        myWriter.println("Host: " + webHost);
        myWriter.println("Connection: close");
        myWriter.println();

        // Initialize the InputStream.
        InputStream inStream;
        
        // Try to getInputStream from socket.  
        try {
            inStream = sock.getInputStream();
        }
        // Catch IOException and return blank list. 
        catch (IOException excep){
            System.err.println("IOException: " + excep.getMessage());
            return URLs;
        }
        // Create a new InputStreamReader and BufferedReader to read lines
        // from the server.  
        InputStreamReader inStreamReader = new InputStreamReader(inStream);
        BufferedReader BuffReader = new BufferedReader(inStreamReader);
        
        // Try to read line from Buffered reader. 
        while (true) {
            String line;
            try {
                line = BuffReader.readLine();
            }
            // Catch IOException and return blank list.
            catch (IOException except) {
                System.err.println("IOException: " + except.getMessage());
                return URLs;
            }
            // Done reading document!
            if (line == null)
                break;
        
            
            // Variables to represent indices where the links begin and end as
            // well as current index.
            int beginIndex = 0;
            int endIndex = 0;
            int index = 0;
            
            while (true) {

                /**
                 * A constant for the string indicating a link.
                 */
                String URL_INDICATOR = "a href=\"";
                
                /**
                 * A constant for the string indicating the end of the webhost and
                 * beginning of docpath.
                 */
                String END_URL = "\"";
                
                
                // Search for our start in the current line.
                index = line.indexOf(URL_INDICATOR, index);
                if (index == -1) // No more copies of start in this line
                    break;
                
                // Advance the current index and set to beginIndex.
                index += URL_INDICATOR.length();
                beginIndex = index;
                
                // Search for our end in the current line and set to endIndex.
                endIndex = line.indexOf(END_URL, index);
                index = endIndex;
                
                // Set the link to the substring between the begin index
                // and end index.  Add to our URLs list.
                String newLink = line.substring(beginIndex, endIndex);
                URLs.add(newLink);
            }
            
        }
        // Return the list of URLs.
        return URLs;
    }
    
}

