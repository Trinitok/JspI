
About
----

Java special Integration (JspI) is a remote global system hook that can capture the keystrokes of the
user and send them to the intended receiver.  


Use
---

I am not condoning any illegal use of this application.  This is intended for educational purposes only.  In order to use, go into the `src/receiver/Client.java` and change the address that you are trying to send to.  The current address that I have loaded in the code is `127.0.0.1` which is the home address of any computer.  Keep the `Server.java` on your computer.  Modify the IP in `Client.java` to be your IP.  Then run all the programs using some script or through the terminal.  Once they are running, run `Server.java` and you will get the text file (currently represented as `testout.txt`) on your local machine.  It will contain all keystrokes made by the person on the other end.

Features
--
- There is a Windows binary executable to be included on the target system that will execute if the client is not running.  It has a timeout system to wait for if the user on the server side wishes to pause or capture multiple instances.
- It cannot be easily detected in the task manager.  It will be listed as a Java process in both Task Manager and tasklist.  It will show up as a javaw.  There are ways to detect it using other built in executables such as JPS, or WMIC, but this requires some knowledge of use on the target's part.
-  Full keyboard capture.  Will capture and record the client's keyboard input.  Accomplishes this using the Java System Hook.  A link to the page on GitHub is listed below.
-  Remote sending allows you to capture the client's file from anywhere at anytime assuming the client has the correct IP address and the server socket is not taken up.


Future Updates
---

- Always run
- Memory Management
- Not be in any Task Manager
- Sending commands to the Server's OS
- Run on startup


Recognition
---

The reason this is possible is due to the work by kristian's Java Low-level System Hook.  This can be found <a href="https://github.com/kristian/system-hook">here</a>
