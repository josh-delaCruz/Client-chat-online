package it.itismeucci;

public class WindowInputHandler extends Thread{
    
    private String message;
    protected boolean open;
    private ClientChat chat;

    private ChatGUI window;
    
    public WindowInputHandler(ClientChat chat, ChatGUI window) {
        super();
        this.chat = chat;
        open = false;
        this.window = window;
    }

    @Override
    public void run() {
        open = true;
        System.out.println("---Waiting for a message---");
        while(open){
            message = chat.receiveMessage();
            executeCommand(message);
        }
        
    }
    
    public void executeCommand(String message){
        switch(findCommand(message)){
            case "/g":
                window.writeToChat(findUsername(message) + ": " + findMessageBody(message));
                break;
                
            case "/msg":
                window.writeToChat("@" + findUsername(message) + ": " + findMessageBody(message));
                break;
                
            case "/usr_con":
                window.writeToChat(findUsername(message) + " has connected");
                break;
                
            case "/usr_dsc":
                window.writeToChat(findUsername(message) + " has disconnected");
                break;
                
            case "/list":
                window.writeToChat(makeList(message));
                break;
            
            case "/msg_offline":
                window.writeToChat(findUsername(message) + " is offline");
                break;
                
            default:
                break;
        }
    }
    
    public String makeList(String command){
        String[] list = findListBody(command).split(",");
        
        String msg = "Connected users:";
        for (int i = 0; i < list.length; i++) {
            msg += "\n - " + list[i];
        }
        
        return msg;
    }
    
    public String findCommand(String msg){
        return msg.split(" ")[0];
    }
    
    public String findUsername(String msg){
        return msg.split(" ")[1].substring(1);
    }
    
    public String findMessageBody(String msg){
        String body = "";
        String[] list = msg.split(" ");
        
        for (int i = 2; i < list.length; i++) {
            body += " " + list[i];
        }
        
        if(body.equals("")) return "";
        return body.substring(1);
    }
    
    public String findListBody(String msg){
        String body = "";
        
        String[] list = msg.split(" ");
        for (int i = 1; i < list.length; i++) {
            body += " " + list[i];
        }
        
        return body.substring(1);
    }
}
