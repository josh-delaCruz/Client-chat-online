package it.itismeucci;

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
    
    
    public Socket connetti(){
        
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
    
    //invio messaggio al server
    public void comunica() {
        for(;;){
            try {
                System.out.println("Invia un messaggio");
                stringaUtente = tastiera.readLine();
                //invio messaggio al server
                outputVersoServer.writeBytes(stringaUtente + "\n");
                //se arriva una risposta
                stringaDalServer = inDalServer.readLine();
                System.out.println(stringaDalServer + "\n");
                
            
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.err.println("Errore durante la comunicazione con server");
                System.exit(1);
            }
        }
    }
}
