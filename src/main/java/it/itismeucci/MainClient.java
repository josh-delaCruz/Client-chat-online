package it.fi.itismeucci.delaCruz;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class MainClient {

    public static Socket socket;
    public static BufferedReader tastiera;
    public static Input in; //ricezione messaggi
    public static Output out; //invio messaggio

    public static void main(String[] args) throws IOException {
        socket = new Socket("localhost", 5000); //socket
        out = new Output(socket); //output
        in = new Input(socket); //input
        
        System.out.println("Connesso al server");
    }
}
