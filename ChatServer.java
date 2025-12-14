import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatServer{
private JFrame frame;
private JTextArea chatArea;
private JTextField inputField;
private JButton sendButton;

    public static void main(String[]args)throws Exception{
ServerSocket server=new ServerSocket(5000);
System.out.println("Waiting...");
Socket socket=server.accept();
System.out.println("Client connected.");

 BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
       BufferedReader keyboard=new BufferedReader(new InputStreamReader(System.in));
PrintWriter out=new PrintWriter(socket.getOutputStream(),true);

String msg1,msg2;
 while ((msg1 = in.readLine()) != null) {

            if (msg1.equalsIgnoreCase("exit")) {
                System.out.println("Client closed connection.");
                break;
            }
            System.out.println("From Client: " + msg1);
            System.out.print("Type text: ");
msg2=keyboard.readLine();

            out.println(msg2);
            
        }
        server.close();
        socket.close();
        System.out.println("Server closed.");
    }
}