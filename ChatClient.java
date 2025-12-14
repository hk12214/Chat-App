import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ChatClient {

  private JFrame frame;
private JTextArea chatArea;
private JTextField inputField;
private JButton sendButton;

  
    public static void main(String[]args)throws Exception{
       Socket socket=new Socket("localhost",5000);
       System.out.println("Connected!"); 
      
       BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
       BufferedReader keyboard=new BufferedReader(new InputStreamReader(System.in));
       PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
       String msg1,msg2;
       while (true) {
            System.out.print("Type text: ");
        msg1 = keyboard.readLine();
            
 
            // stop with "exit"
            if (msg1.equalsIgnoreCase("exit")) {
                System.out.println("Closing connection...");
                break;
            }
              out.println(msg1);
            msg2 = in.readLine();
            System.out.println("From Server: "+msg2);
          

            
    }
      socket.close();  // <-- now it runs
        System.out.println("Client closed.");
}
}