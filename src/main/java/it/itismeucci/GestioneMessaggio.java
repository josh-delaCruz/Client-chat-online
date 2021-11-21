package it.fi.itismeucci.delaCruz;

public class GestioneMessaggio{
    
    // lista protocolli
    public static final String GLOBALE = "/g"; //messaggio a tutti gli utenti  //usa @
    public static final String CONNESSIONE = "/usr_con"; // messaggio utente nuovo connesso    //usa @
    public static final String DISCONNESSIONE = "/usr_dsc"; //messaggio se un utente si disconnesse    //usa @
    public static final String MESSAGGIO_PRV = "/msg"; // privato messaggio da un utente   //usa @
    public static final String LISTA = "/list"; // lista client connessi
    public static final String UTENTEOFFLINE = "/msg_offline"; // messaggio se un utente è offline //usa @
    public static final String LEAVE = "/quit"; //uscita dalla chat
    public static final String SET_NOME = "/name"; //per settare il nome
    public static final String VALIDITA_NOME = "/name_validity"; //per settare il nome
    
    //ricava il protocollo
    public static String trovaProtocollo(String msg) {
        return msg.split(" ")[0];
    }

    //ricava il nome utente se il messaggio lo richiede
    public static String ricavoNome(String msg){
        return msg.split(" ")[1].split("@")[0];
    }

    //ricava il messaggio
    private static String creaMessaggio(String msg){
        String corpo = "";
        String[] ar = msg.split(" ");
        
        for(int i = 2; i < ar.length; i++){
            corpo += ar[i] + " ";
        }
        
        return corpo; //messaggio privato
    }
    
    private static String trovaListaUtenti(String msg){
        String lista = "Lista Utenti:\n";
        
        String elenco = msg.split(" ")[1];
        String[] listaNomi = elenco.split(",");
        
        for(int i = 0; i < listaNomi.length; i++){
            lista += "\n- " + listaNomi[i];
        }
        return lista;
    }
    
    public static String eseguiComando(String comandoCompleto) {
        
        String nome = "";
        String messaggio = "";
        
        switch(trovaProtocollo(comandoCompleto)) { //controllo il tipo di messaggio
            
            case GLOBALE:
                nome = ricavoNome(comandoCompleto);
                messaggio += "Global("+nome+")--> ";
                messaggio += creaMessaggio(comandoCompleto);
                break;
            
            case CONNESSIONE:
                nome = ricavoNome(comandoCompleto);
                messaggio += "Connected--> "+nome;
                break;
            
            case DISCONNESSIONE:
                nome = ricavoNome(comandoCompleto);
                messaggio += "Disconnected--> "+nome;
                break;
            
            case MESSAGGIO_PRV:
                nome = ricavoNome(comandoCompleto);
                messaggio += "Private("+nome+")--> ";
                messaggio += creaMessaggio(comandoCompleto);
                break;
            
            case UTENTEOFFLINE:
                nome = ricavoNome(comandoCompleto);
                messaggio += nome+" is offline";
            
            case LISTA:
                messaggio += trovaListaUtenti(comandoCompleto);  
                break;    
            
            case LEAVE:
                messaggio = "Disconnection...";
                break;
            
            default:
                break;
        }
        return messaggio;
    }
}