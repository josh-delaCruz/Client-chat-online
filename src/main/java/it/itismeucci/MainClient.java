package tpsit.testclientserver.client;
import java.io.IOException;

public class MainClient {
    
    public static String address = "localhost";
    public static int port = 5000;
    
    public static void main(String[] args) {
        
        ClientChat client = new ClientChat(port, address);
            
        try {
            client.connect();
            client.comunicate();
        } catch (IOException ex) { System.out.println("Errore nel client main\n" + ex.getMessage());}

    }
}
