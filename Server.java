import java.io.*;
import java.net.*;
public class Server{
    public static void main(String[]args)throws Exception{
ServerSocket server=new ServerSocket(5000);
System.out.println("Waiting...");
Socket socket=server.accept();
System.out.println("Client connected.");

 BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
       BufferedReader keyboard=new BufferedReader(new InputStreamReader(System.in));
PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
    }
}