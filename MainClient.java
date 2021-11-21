package it.itismeucci;

import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainClient {

    private static Socket socket;
    private static BufferedReader tastiera;
    private static Input in; //ricezione messaggi
    private static Output out; //invio messaggio

    public static void main(String[] args) throws IOException {
        socket = new Socket("localhost", 6789); //socket
        out = new Output(socket); //output
        in = new Input(socket); //input

        try {
           in.start();
        } catch (Exception e) {
            System.err.println("Errore");
        }
    }

}
