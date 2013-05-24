import java.net.*;
import java.util.*;

public class Crawler {
    
    public static void main(String[] args) {
        int depth = 0;
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
}
public class Socket {
    
    public Socket(String host, int port) {
        //Creates a new socket from a string representing the host and a port
        // number, and makes the connection.
    }
    
    void setSoTimeout(int timeout) {
        // Sets the timeout of the Socket in milliseconds.  Call this after
        // creating the socket so it knows how long to wait for data transfer
        // from the other side.
        
    }
    
    InputStream getInputStream() {
        //returns an inputstream associated with the socket.  this allows
        // the socket to receive data from the other side of the connection.
        
    }
    
    OutputStream getOutputStream() {
        // Returns an outputstream associated with the socket.  this allows the
        // socket to send data to the other side of the connection
    }
    
    getSites() {
        
    }
    void close() {
        //closes the socket
    }
}

GET /people.html HTTP/1.1
Host: www.cs.caltech.edu
Connection: close

Socket sock = new Socket(webHost, webPort);
sock.setSoTimeout(3000); //Time-out after 3 seconds

OutputStream os = sock.getOutputStream();

// true tells PrintWrtier to flush after every output
PrintWriter writer = new PrintWriter(os,true);

writer.println("GET " + docPath + " HTTP/1.1");
writer.println("Host: " + webHost);
writer.println("Connection: close");
writer.println();

//Request is sent! Server will start responding now.

//Response receiving code
InputStream is = sock.getInputStream();
InputStreamReader isr = new InpurStreamReader(is);
BufferedReader br = new BufferedReader(isr);

while (true) {
    String line = br.readLine();
    if (line == null)
        break; // done reading document!
    // Do something with this line of text.
    System.out.println(line);

}


