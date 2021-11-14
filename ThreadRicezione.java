package it.itismeucci;

import java.util.ArrayList;

public class ThreadRicezione implements Runnable{

    protected String messaggioGlobale; //messaggio del utente
    protected ArrayList<String>messaggio = new ArrayList<>();
    
   
    //lista protocolli
    public final String CONNESSIONE = "/usr_con"; //messaggio utente nuovo connesso
    public final String DISCONNESSIONE = "/usr_dsc"; ////messaggio se un utente si disconnesse
    public final String MESSAGGIO = "/msg"; //privato messaggio da un utente
    public final String LISTA = "/list"; //lista client connessi 
    public final String UTENTEOFFLINE = "/msg_offline"; //messaggio se un utente è offline

    public ThreadRicezione(String messaggioGlobale){
        this.messaggioGlobale = messaggioGlobale;
    }

    public String protocollo(){
        

        if(messaggioGlobale.substring(0,7).equals(CONNESSIONE)){   
            this.messaggio = messaggioGlobale.split(" ");
                                                                                                                           
            return 

        } 

        if(messaggioGlobale.substring(0,7).equals(DISCONNESSIONE)){   
           

        } 

        if(messaggioGlobale.substring(0,4).equals(MESSAGGIO)){   
           

        } 

        if(messaggioGlobale.substring(0,5).equals(LISTA)){   
           

        } 

        if(messaggioGlobale.substring(0,11).equals(CONNESSIONE)){   
          

        } 

        return null;
    }

    @Override
    public void run() {
        switch(protocollo()) {
            
            case CONNESSIONE:
            
            break;
            
            case DISCONNESSIONE:
            
            break;
            
            case MESSAGGIO:
    
            break;
            
            case LISTA:
    
            break;
            
            case UTENTEOFFLINE:
   
            break;

            default:
                System.err.println("Errore protocollo non riconosciuto");
            break;



        }
    }
    
}