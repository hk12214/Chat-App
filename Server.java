import java.net.*;
public class Server{
    public static void main(String[]args)throws Exception{
ServerSocket server=new ServerSocket(5000);
System.out.println("Waiting...");
Socket socket=server.accept();
System.out.println("Client connected.");

    }
}