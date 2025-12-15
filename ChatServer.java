import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ChatServer {
  private JFrame frame;
private JTextArea chatArea;
private JTextField inputField;
private JButton sendButton;

private ChatServer() {
    buildGUI();

}
private void buildGUI() {
    frame = new JFrame("Chat Server");
    frame.setSize(300, 300);
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      chatArea = new JTextArea();
        chatArea.setEditable(false);

        JScrollPane scroll = new JScrollPane(chatArea);

        inputField = new JTextField();
        sendButton = new JButton("Send");

        frame.setLayout(new BorderLayout());
        frame.add(scroll, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(inputField, BorderLayout.CENTER);
        bottom.add(sendButton, BorderLayout.EAST);

        frame.add(bottom, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

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