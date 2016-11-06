package receiver;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {
	
	//  get the IP, port and test file
    private final static String serverIP = "127.0.0.1";
    private final static int serverPort = 3248;
    private final static String fileOutput = "testout.txt";

    public static void main(String args[]) {
    	System.out.println("in client");
    	//  set up the streams
        byte[] aByte = new byte[1];
        int bytesRead;

        Socket clientSocket = null;
        InputStream is = null;

        
        try {
            clientSocket = new Socket( serverIP , serverPort );
            System.out.println("client socket: " + clientSocket.getPort());
            System.out.println("client socket receiving? " + clientSocket.getInetAddress());
            is = clientSocket.getInputStream();
        } catch (IOException ex) {
            // Do exception handling
        	System.out.println("error in setting up sockets");
        	ex.printStackTrace();
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        while(true)
        if (is != null) {

            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            try {
            	System.out.println("testing 1");
                fos = new FileOutputStream( fileOutput );
                bos = new BufferedOutputStream(fos);
                bytesRead = is.read(aByte, 0, aByte.length);

                do {
                        baos.write(aByte);
                        System.out.println("writing " + aByte);
                        bytesRead = is.read(aByte);
                } while (bytesRead != -1);
                
                System.out.println("testing 2");
                bos.write(baos.toByteArray());
                bos.flush();
                bos.close();
                clientSocket.close();
                is = null;
            } catch (IOException ex) {
                // Do exception handling
            	ex.printStackTrace();
            	System.out.println("error receiving files");
            }
        }
    }
}
