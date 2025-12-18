
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ChatServer {

    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private ServerSocket server;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private ChatServer() throws Exception {
        buildGUI();
        StartServer();
    }

    private void buildGUI() {
        frame = new JFrame("Chat Server");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowclosing(WindowEvent e) throws Exception {
                close();
                frame.dispose();
            }
        });

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

        if (out == null) {
            chatArea.append("No client connected yet.\n");
            return;
        }

        String msg = inputField.getText().trim();
        if (msg.isEmpty()) {
            return;
        }

        out.println(msg);
        chatArea.append("You : " + msg + "\n");
        inputField.setText("");
    }

    private void close() throws Exception {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
        if (socket != null) {
            socket.close();
        }
    }

    private void StartServer() {
        new Thread(() -> {
            try {
                server = new ServerSocket(5000);
                chatArea.append("Waiting for client...\n");
                socket = server.accept();
                chatArea.setText("");
                chatArea.append("Client connected!\n");
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                listenFromClient();

            } catch (Exception e) {
                chatArea.append("Server error\n");
            }

        }).start();
    }

    private void listenFromClient() throws Exception {
        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null) {
                   chatArea.append("Client: " + msg + "\n");
                }
            } catch (Exception e) {
                chatArea.append("Client disconnected.\n");
            }
        }).start();
    }

    public static void main(String[] args) throws Exception {
        new ChatServer();

    }
}
