
package it.itismeucci;

import java.util.ArrayList;

public class GestioneMessaggio{

    protected String messaggioGlobale; // messaggio del utente
    protected ArrayList<String> messaggio = new ArrayList<>(); // messaggio diviso

    // lista protocolli
    public final String GLOBALE = "/g"; //messaggio a tutti gli utenti  //usa @
    public final String CONNESSIONE = "/usr_con"; // messaggio utente nuovo connesso    //usa @
    public final String DISCONNESSIONE = "/usr_dsc"; //messaggio se un utente si disconnesse    //usa @
    public final String MESSAGGIO_PRV = "/msg"; // privato messaggio da un utente   //usa @
    public final String LISTA = "/list"; // lista client connessi
    public final String UTENTEOFFLINE = "/msg_offline"; // messaggio se un utente è offline //usa @
    public final String LEAVE = "/quit"; //uscita dalla chat

    public GestioneMessaggio(String messaggioGlobale) {
        this.messaggioGlobale = messaggioGlobale;
    }
    
    //ricava il protocollo
    public String protocollo() {

        messaggio = splitString();
        String protocollo = messaggio.get(1);

        messaggio.remove(1);

        return protocollo;
    }
    
    //divide il messaggio globale nelle su parti
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

        if(messaggio.get(1).substring(1).equals("@")){
            nome = messaggio.get(1).split("@")[1];
            messaggio.remove(1);
        }

        return nome; //nome dell'utente
    }

    //ricava il messaggio
    private String creaMessaggio(){
        String mess = "";
        for(int i = 0; i<messaggio.size(); i++){
            mess += messaggio.get(i)+" ";
        }

        
        return mess; //messaggio privato
    }

    
    private String listaUtenti(){
        String lista = "Lista Utenti:";
        String utenti = gestioneLista();
        String[]contenuto;
        String tmp = ""; //variabile di appoggio
         
        contenuto = utenti.split(",");
       
        for(int i=0; i<contenuto.length; i++){
            if(contenuto[i].startsWith("[") || contenuto[i].endsWith("]")){
                lista += "- "+contenuto[i].substring(0)+"\n";
            }else{
                lista += "- "+contenuto[i]+"\n";
            }
        }
        return lista;
    }
    
    //componente per listaUtenti()
    private String gestioneLista(){
        String s = "";
        for(int i = 0; i<messaggio.size(); i++){
            s += messaggio.get(i);
        }
        return s;
    }
    
    public String mess() {
        
        String nome; //nome utente
        String messaggio = ""; //messaggio
        
        switch(protocollo()) { //controllo il tipo di messaggio
            
            case GLOBALE:
                nome = ricavoNome();
                messaggio += "Global("+nome+")--> ";
                messaggio += creaMessaggio();
            break;
            
            case CONNESSIONE:
                nome = ricavoNome();
                messaggio += "Connected--> "+nome;

            break;
            
            case LEAVE:
                messaggio = "Disconnection..";
            break;
            
            case DISCONNESSIONE:
                nome = ricavoNome();
                messaggio += "Disconnected--> "+nome;
            break;
            
            case MESSAGGIO_PRV:
                nome = ricavoNome();
                messaggio += "Private("+nome+")--> ";
                messaggio += creaMessaggio();
            break;
            
            case UTENTEOFFLINE:
                nome = ricavoNome();
                messaggio += nome+" is offline";
            
            case LISTA:
                messaggio += listaUtenti();  
            break;    

            default:
                messaggio = "ERRORE PROTOCOLLO";
            break;


            
        }
        
        return messaggio;
    }

}
