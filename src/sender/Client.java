package sender;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
	private final static String fileToSend = "testFile.txt";
	
	public static void main(String args[]) {

        while (true) {
            ServerSocket welcomeSocket = null;
            Socket connectionSocket = null;
            BufferedOutputStream outToClient = null;

            try {
                welcomeSocket = new ServerSocket(3248);
                connectionSocket = welcomeSocket.accept();
                outToClient = new BufferedOutputStream(connectionSocket.getOutputStream());
            } catch (IOException ex) {
                // Do exception handling
            }

            if (outToClient != null) {
                File myFile = new File( fileToSend );
                if(!myFile.exists()){
                	try{
                		System.out.println("file did not exist.  writing...");
                	    PrintWriter writer = new PrintWriter(myFile, "UTF-8");
                	    File[] roots = File.listRoots();
                	    for(File root : roots){
                	    	writer.println(root.getAbsolutePath());
                	    	writer.println(root.getTotalSpace());
                	    	writer.println(root.getFreeSpace());
                	    	writer.println(root.getUsableSpace());
                	    }
                	    writer.close();
                	} catch (Exception e){
                		e.printStackTrace();
                	}
                }
                byte[] mybytearray = new byte[(int) myFile.length()];

                FileInputStream fis = null;

                try {
                    fis = new FileInputStream(myFile);
                } catch (FileNotFoundException ex) {
                    // Do exception handling
                }
                BufferedInputStream bis = new BufferedInputStream(fis);

                try {
                    bis.read(mybytearray, 0, mybytearray.length);
                    outToClient.write(mybytearray, 0, mybytearray.length);
//                    System.out.println("sending: " + mybytearray);
                    outToClient.flush();
                    outToClient.close();
                    connectionSocket.close();

                    // File sent, exit the main method
                    return;
                } catch (IOException ex) {
                    // Do exception handling
                }
            }
        }
        
        
    }
}
