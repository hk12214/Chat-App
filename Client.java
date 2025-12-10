import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[]args)throws Exception{
       Socket socket=new Socket("localhost",5000);
       System.out.println("Connected!"); 
      
       BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
       BufferedReader keyboard=new BufferedReader(new InputStreamReader(System.in));
PrintWriter out=new PrintWriter(socket.getOutputStream(),true);

    }
}
