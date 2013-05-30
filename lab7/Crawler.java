import java.net.*;
import java.util.*;
import java.io.*;

/**
 * Class crawler processes command-line arguments, creates an instance of the 
 * URL pool, adds the inputted URL to the pool, and creates the number of crawler 
 * tasks inputted with threads to run them.  Then when crawling is complete,
 * prints out the list of URLs found.
 */
public class Crawler {
    
    
    /**
     * A method to perform Crawler's tasks.
     */
    public static void main(String[] args) {
        // Get initial URL, max depth, number of threads from command-line parameters
        // Create a URL pool, add the initial URL to pool
        // Create and start the requested number of threads
        // Check pool every 0.1 to 1s for completion
        // When finished, print URLs in the pool's "seen" list
        
        // Variables for current depth and requested number of threads.
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
        
        // Create a URL pool and add the user inputted website.
        URLPool pool = new URLPool();
        pool.put(currentDepthPair);
        
        
        // while pool is empty and all threads are waiting
            // if number of threads (actievecount) < inputted number
                // make new threads
            // else
        
        int totalThreads = 0;
        int initialActive = Thread.activeCount();
        
        while (pool.getWaitThreads() != numThreads) {
            if (Thread.activeCount() - initialActive < numThreads) {
                CrawlerTask crawler = new CrawlerTask(pool);
                new Thread(crawler).start();
            }
            else {
                try {
                    Thread.sleep(100);  // 0.1 second
                }
                // Catch InterruptedException.
                catch (InterruptedException ie) {
                    System.out.println("Caught unexpected " +
                                       "InterruptedException, ignoring...");
                }

            }
        }
                
        // When all threads are waiting, print out all processed URLs with
        // depth.
        // Print out all processed URLs with depth.
        Iterator<URLDepthPair> iter = pool.processedURLs.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }        // Exit.
        System.exit(0);
        


    }

    /**
     * A method which takes a URLDepthPair and returns a LinkedList of String
     * type.  Connects to the website in the URLDepthPair, finds all links
     * on the site, and adds them to a new LinkedList which gets returned.
     */
    public static LinkedList<String> getAllLinks(URLDepthPair myDepthPair) {
        
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

