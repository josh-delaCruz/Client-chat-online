package it.itismeucci;

import java.util.ArrayList;

public class ThreadRicezione implements Runnable{

    protected String messaggioGlobale;

    //parti di stringa
    private String parte1 = null;
    private String parte2 = null;
    private String parte3 = null;
    

    //lista comandi
    public final String CONNESSIONE = "/usr_con"; //messaggio utente nuovo connesso
    public final String DISCONNESSIONE = "/usr_dsc"; ////messaggio se un utente si disconnesse
    public final String MESSAGGIO = "/msg"; //privato messaggio da un utente
    public final String LISTA = "/list"; //lista client connessi 
    public final String UTENTEOFFLINE = "/msg_offline"; //messaggio se un utente è offline



    public String protocollo(){
        String[]parti; //salva le parti per poi dividerle

        if(messaggioGlobale.substring(0,7).equals(CONNESSIONE)){   
            parti = messaggioGlobale.split(" ");
            parte1 = parti[0]; //protocollo
            parte2 = parti[1]; //nome
            parte3 = parti[2]; //messaggio                                                                                                               
            return parte1;

        } 

        if(messaggioGlobale.substring(0,7).equals(DISCONNESSIONE)){   
            parti = messaggioGlobale.split(" ");
            parte1 = parti[0]; //protocollo
            parte2 = parti[1]; //nome
            parte3 = parti[2]; //messaggio                                                                                                               
            return parte1;

        } 

        if(messaggioGlobale.substring(0,4).equals(MESSAGGIO)){   
            parti = messaggioGlobale.split(" ");
            parte1 = parti[0]; //protocollo
            parte2 = parti[1]; //nome
            parte3 = parti[2]; //messaggio                                                                                                               
            return parte1;

        } 

        if(messaggioGlobale.substring(0,5).equals(LISTA)){   
            parti = messaggioGlobale.split(" ");
            parte1 = parti[0]; //protocollo
            parte2 = parti[1]; //nome
            parte3 = parti[2]; //messaggio                                                                                                               
            return parte1;

        } 

        if(messaggioGlobale.substring(0,11).equals(CONNESSIONE)){   
            parti = messaggioGlobale.split(" ");
            parte1 = parti[0]; //protocollo
            parte2 = parti[1]; //nome
            parte3 = parti[2]; //messaggio                                                                                                               
            return parte1;

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
