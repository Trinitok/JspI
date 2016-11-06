
About
----

Java special Integration (JspI) is a remote global system hook that can capture the keystrokes of the
user and send them to the intended receiver.  


Use
---

I am not condoning any illegal use of this application.  This is intended for educational purposes only.  In order to use, go into the `src/receiver/Server.java` and change the address that you are trying to send to.  The current address that I have loaded in the code is `127.0.0.1` which is the home address of any computer.  Keep the `Client.java` on your computer.  Modify the IP in `Server.java` to be your IP.  Then run all the programs using some script or through the terminal.  Once they are running, run `Client.java` and you will get the text file (currently represented as `testout.txt`) on your local machine.


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
