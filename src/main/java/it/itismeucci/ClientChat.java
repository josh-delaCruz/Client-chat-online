package tpsit.testclientserver.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class ClientChat {
    
    protected String name;
    
    //per la connessione
    protected int port;
    protected String address;
    protected Socket server;
    
    //per la comunicazione
    protected BufferedReader receive;
    protected DataOutputStream send;
    protected ChatInputHandler receiver;
    
    protected String message;
    protected boolean open;
    protected boolean logged;
    
    //per input da tastiera
    private Scanner keyboard = null;

    public ClientChat(int port, String address){
        this.port = port;
        this.address = address;
    }
    
    public Socket connect(){
        try{
            server = new Socket(address, port);
        
            receive = new BufferedReader(new InputStreamReader(server.getInputStream()));
            send = new DataOutputStream(server.getOutputStream());

            open = true;
            logged = false;

            return server;
        }catch(Exception e){
            System.out.println("Error during the connection");
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public void comunicate() throws IOException{
        keyboard = new Scanner(System.in);
        
        System.out.println("Write your username:");
        login();
        System.out.println("Login completed");
        
        receiver = new ChatInputHandler(this);
        receiver.start();
        
        while(open){
            
            String keyboardInp = keyboard.nextLine();
            String command = "";
            
            if(keyboardInp.startsWith("@")){
                command = "/msg ";
            }else if(!keyboardInp.startsWith("/")){
                command = "/g @" + name + " ";
            }
            
            message = command + keyboardInp;
            
            sendMessage(message);
            
            if(message.equals("/quit")){
                break;
            }
        }
        keyboard.close();
        close();
    }
    
    public void login(){
        
        while(!logged){
            
            String typedName = keyboard.nextLine();
            
            sendMessage("/name @" + typedName);
            message =  receiveMessage();
            
            if(message.equals("/name_validity true")){
                logged = true;
                name = typedName;
            }else if(message.equals("/name_validity false")){
                System.out.println("The inserted name is not valid, try again");
            }else{
                System.out.println("Error: the name is not valid, try again");
            }
        }
        
    }
    
    public void close() throws IOException{
        System.out.println("Chiusura in corso...");
        receiver.open = false;
        receive.close();
        send.close();
        server.close();
        if(keyboard != null) keyboard.close();
    }
    
    public String receiveMessage(){
        try {
            return receive.readLine();
        } catch (IOException ex) { System.out.println("Errore nella ricezione:\n" + ex.getMessage()); }
        return null;
    }
    
    public void sendMessage(String msg){
        try {
            send.writeBytes(msg + '\n');
        } catch (IOException ex) { System.out.println("Errore nel'invio:\n" + ex.getMessage()); }
    }
    
}
