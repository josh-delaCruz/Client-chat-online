
package it.itismeucci;

import java.io.*;
import java.net.*;

public class Input implements Runnable {
    
    private Socket socket;
    private BufferedReader ricezione;
    private GestioneMessaggio messaggioGlobale;
   
    public Input(Socket socket){
       this.socket = socket;
    }
   
    @Override
    public void run(){
        try 
        {
          for(;;)
          {  
            messaggioGlobale = new GestioneMessaggio(connetti()); 
            if(messaggioGlobale.protocollo().equals("/quit")){
                System.out.println(messaggioGlobale.mess());
                break;
            }
          }
            System.out.println("Alla prossima!");
        } catch (Exception e) {        
            System.err.println("Errore della comunicazione");
        }
        
    }
    
    //cattura messaggio
    public String connetti() throws IOException{
        String messaggio = "";
        ricezione = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        messaggio = ricezione.readLine();
        
        return messaggio;
    }
}
