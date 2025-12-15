import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ChatClient {

private JFrame frame;
private JTextArea chatArea;
private JTextField inputField;
private JButton sendButton;

public ChatClient() {
  buildGUI();

}
      private void buildGUI() {
        frame = new JFrame("Chat Client");
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