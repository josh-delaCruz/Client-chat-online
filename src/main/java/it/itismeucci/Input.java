
package it.fi.itismeucci.delaCruz;

import java.io.*;
import java.net.*;

public class Input extends Thread {
    
    private Socket socket;
    private BufferedReader ricezione;
    private GestioneMessaggio messaggioGlobale;
    private boolean continua = true;
    
    private ClientChatGUI chat;
    
    public Input(Socket socket){
       super();
       this.socket = socket;
       try{
           ricezione = new BufferedReader(new InputStreamReader(socket.getInputStream()));
       }catch(Exception e){ System.out.println("Errore nella creazione del canale di ricezione"); }
    }
   
    @Override
    public void run(){
        try{
            while(continua){
                //ricezione messaggio
                String msg = GestioneMessaggio.eseguiComando(riceviMessaggio());
                
                System.out.println("stampa per console: " + msg);
                chat.riceviMessaggio(msg);
                
            }
            //socket chiuso
            System.out.println("Alla prossima!");

        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    //cattura messaggio
    public String riceviMessaggio() throws IOException{
        return ricezione.readLine();
    }

    public BufferedReader getRicezione() {
        return ricezione;
    }

    public void setFinestra(ClientChatGUI finestra) {
        this.chat = finestra;
    }
    
}
