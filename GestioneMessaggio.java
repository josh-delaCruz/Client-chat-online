package it.itismeucci;

import java.util.ArrayList;

public class GestioneMessaggio{

    protected String messaggioGlobale; // messaggio del utente
    protected ArrayList<String> messaggio = new ArrayList<>(); // messaggio diviso

    // lista protocolli
    public final String CONNESSIONE = "/usr_con"; // messaggio utente nuovo connesso    //usa @
    public final String DISCONNESSIONE = "/usr_dsc"; //// messaggio se un utente si disconnesse     //usa @
    public final String MESSAGGIO = "/msg"; // privato messaggio da un utente   //usa @
    public final String LISTA = "/list"; // lista client connessi
    public final String UTENTEOFFLINE = "/msg_offline"; // messaggio se un utente è offline //usa @

    public GestioneMessaggio(String messaggioGlobale) {
        this.messaggioGlobale = messaggioGlobale;
    }

    public String protocollo() {

        messaggio = splitString();
        String protocollo = messaggio.get(1);

        messaggio.remove(1);

        return protocollo;
    }
    
    //ricava il protocollo
    private ArrayList<String> splitString() { 
        ArrayList<String> temp = new ArrayList<>();
        String[] part = messaggioGlobale.split(" ");

        for (int i = 0; i < part.length; i++) {
            temp.add(part[i]);
        }

        return temp;
    }

    //ricava il nome utente se il messaggio lo richiede
    private String ricavoNome(){
        String nome = ""; 

        if(messaggio.get(1).substring(0).equals("@")){
            nome = messaggio.get(1).split("@")[1];
            messaggio.remove(1);
        }

        return nome;
    }

    //ricava il messaggio
    private String prvmessaggio(){
        String mess = "";
        for(int i = 0; i<messaggio.size(); i++){
            mess += messaggio.get(i)+" ";
        }

        
        return mess;
    }

    /*
    private String listaUtenti(){
        
    }
    */
    public void mess() {
        
        String nome; //nome utente
        String messaggio = ""; //messaggio
        
        switch(protocollo()) { //controllo il tipo di messaggio
            
            case CONNESSIONE:
                nome = ricavoNome();
                messaggio += "Connected--> "+nome;

            break;
                
            case DISCONNESSIONE:
                nome = ricavoNome();
                messaggio += "Disconnected--> "+nome;
            break;
            
            case MESSAGGIO:
                nome = ricavoNome();
                messaggio += nome+" --> ";
                messaggio += prvmessaggio();
            break;
            
            case LISTA:
            
            break;
            
            case UTENTEOFFLINE:
                nome = ricavoNome();


            break;

            default:
                System.err.println("Errore protocollo non riconosciuto");
            break;



        }
    }

}
