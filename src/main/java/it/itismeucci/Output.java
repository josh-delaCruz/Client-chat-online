package it.fi.itismeucci.delaCruz;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Output {

    private Socket socket;
    private DataOutputStream invio;
    private String nomeUtente; //nome utente
    private boolean isNomeDisponibile = false; //controllo se il nome è disponibili
    private String messaggio; //messaggio dell'utente

    public Output(Socket socket) throws IOException {
        this.socket = socket;
        invio = new DataOutputStream(socket.getOutputStream());
    }

    public void invioAlServer(String msg) {
        try {
            invio.writeBytes(GestioneMessaggio.GLOBALE + " " + msg + '\n');
            
        } catch (IOException ex) {
            System.out.println("Errore nell'invio del messaggio al server");
            System.out.println(ex.getMessage());
        }
    }

    public void setNomeDisponibile(boolean nomeDisponibile) {
        this.isNomeDisponibile = nomeDisponibile;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataOutputStream getInvio() {
        return invio;
    }

    public void setInvio(DataOutputStream invio) {
        this.invio = invio;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public boolean isIsNomeDisponibile() {
        return isNomeDisponibile;
    }

    public void setIsNomeDisponibile(boolean isNomeDisponibile) {
        this.isNomeDisponibile = isNomeDisponibile;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

}
