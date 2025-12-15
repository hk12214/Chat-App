import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;


public class ChatClient {

private JFrame frame;
private JTextArea chatArea;
private JTextField inputField;
private JButton sendButton;

private Socket socket;
private BufferedReader in;
private PrintWriter out;

private ChatClient() throws Exception {
  buildGUI();
  StartClient();

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
  sendButton.addActionListener(e -> sendMessage());
  inputField.addActionListener(e -> sendMessage());

}
    private void sendMessage() {
    try {
        if (out == null) {
            chatArea.append("Not connected yet.\n");
            return;
        }

        String msg = inputField.getText().trim();
        if (msg.isEmpty()) return;

        out.println(msg);
        chatArea.append("You: " + msg + "\n");
        inputField.setText("");

        if (msg.equalsIgnoreCase("exit")) {
            frame.dispose();
        }

    } catch (Exception e) {
        chatArea.append("Send failed\n");
    }
}


    private void StartClient() 
    {new Thread(() -> {
        try {
       socket=new Socket("localhost",5000);
       chatArea.append("Connected!\n");
      
      in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
      
      out=new PrintWriter(socket.getOutputStream(),true);
        listenFromServer();

        } catch (Exception e) {
            chatArea.append("Connection failed\n");
        }
    }).start();
}
private void listenFromServer() {
    new Thread(() -> {
        try {
            String msg;
            while ((msg = in.readLine()) != null) {
                chatArea.append("Server: " + msg + "\n");
            }
        } catch (Exception e) {
            chatArea.append("Server disconnected\n");
        }
    }).start();
}

public static void main(String[] args) throws Exception {
      new ChatClient();
      
}
}