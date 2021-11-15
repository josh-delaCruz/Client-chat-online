ppackage it.itismeucci;
    
import java.io.*;
import java.net.*;

public class Client {
    
    protected String ipServer = "localhost"; //indirizzo server locale
    protected int porta = 6789; //porta logica
    protected Socket mioSocket; 
    protected BufferedReader tastiera;
    protected String stringaUtente; //stringa da inviare
    protected String stringaDalServer; //stringa ricevuta
    protected DataOutputStream outputVersoServer; //stream di output
    protected BufferedReader inDalServer; //stream di input
    
    //costruttori
    public Client(String stringaUtente){
        this.stringaUtente = stringaUtente;
    }
    
    public Client(){}

    public Client(String ipServer, int porta, String stringaUtente){
        this.ipServer = ipServer;
        this. porta = porta;
        this.stringaUtente = stringaUtente;
    }

    public Socket connetti(){
        System.out.println("2) Client Partito esecuzione..");
        try{
            //input da tastiera
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            mioSocket = new Socket(ipServer, porta); //ip server= InetAddress.getLocalHOst(), Porta= 6789 creazione socket
            outputVersoServer= new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader (mioSocket.getInputStream()));
            
            
        }
        catch (UnknownHostException e){
            System.err.println("Host sconosciuto"); 
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore di connessione");
            System.exit(1);
            
        }

        return mioSocket;
    }
    
    //invio stringa al server
    public void comunica() {
        for(;;){
            try {
                System.out.println("4) inserisci la stringa da\n");
                stringaUtente = tastiera.readLine();
                System.out.println("5) invio al server e attendo\n");
                //invio messaggio al server
                outputVersoServer.writeBytes(stringaUtente + "\n");
                //leggo risposta dal server
                stringaDalServer = inDalServer.readLine();
                System.out.println("8) risposta dal server" + stringaDalServer + "\n");
                //chiudo connessione
            
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.err.println("Errore durante la comunicazione con server");
                System.exit(1);
            }
        }
    }

}