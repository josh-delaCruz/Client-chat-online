package it.itismeucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Output {

    private Socket socket;
    private DataOutputStream invio;
    private String nomeUtente; //nome utente
    private boolean nomeDisponibile = false; //controllo se il nome è disponibili
    private String messaggio; //messaggio dell'utente

    public Output(Socket socket) throws IOException {
        this.socket = socket;
        invio = new DataOutputStream(socket.getOutputStream());
    }

    public void invioAlServer() {
        try {

            //controllo nome
            while (!nomeDisponibile) {
                System.out.println("Benvenuto!!");
                System.out.println("Inserisci un nome");
                invio.writeBytes(messaggio);
                if(nomeDisponibile == false) {
                    System.out.println("Nome non Valido");
                }
            }
            System.out.println("Scrivi un messaggio");
            for (;;) {
                invio.writeBytes(messaggio);
                if (messaggio.substring(1, 4).equals("/quit")) {
                    break;
                }

            }

        } catch (Exception e) {
        }
        
        
    }

    public void setNomeDisponibile(boolean nomeDisponibile) {
        this.nomeDisponibile = nomeDisponibile;
    }

}
