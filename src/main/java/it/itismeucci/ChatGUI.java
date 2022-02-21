package tpsit.testclientserver.client;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JTextArea;

public class ChatGUI extends javax.swing.JFrame {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelDesc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textChat = new javax.swing.JTextArea();
        textSend = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelDesc.setText("Username: " + chat.name);

        textChat.setColumns(20);
        textChat.setRows(5);
        jScrollPane1.setViewportView(textChat);

        textSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textSendActionPerformed(evt);
            }
        });
        textSend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textSendKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 36, Short.MAX_VALUE))
                    .addComponent(textSend))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDesc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSendActionPerformed
        //sendMessage(textSend.getText());
    }//GEN-LAST:event_textSendActionPerformed

    private void textSendKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textSendKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            sendMessage(textSend.getText());
        }
    }//GEN-LAST:event_textSendKeyPressed

    //-------------------------------------------------------< MAIN >-------------------------------------------------------//
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDesc;
    private javax.swing.JTextArea textChat;
    private javax.swing.JTextField textSend;
    // End of variables declaration//GEN-END:variables

    private ClientChat chat;
    private WindowInputHandler receiver;
    
    public ChatGUI(ClientChat chat) {
        super("Chat Online");
        this.chat = chat;
        initComponents();
        
        setResizable(false);
        setLocationRelativeTo(null);
        textChat.setEditable(false);
        
        receiver = new WindowInputHandler(chat, this);
        receiver.start();
    }

    public void sendMessage(String message){
        writeToChat("You: " + message);
        
        String command = "";
        
        if(message.startsWith("@")){
            command = "/msg ";
        }else if(!message.startsWith("/")){
            command = "/g @" + chat.name + " ";
        }
        
        chat.sendMessage(command + message);
        textSend.setText("");
        
        if(message.equals("/quit")){
            try {
                chat.close();
                this.setVisible(false);
            } catch (IOException ex) {System.out.println("Error when closing\n" + ex.getMessage()); }
        }
    }
    
    public void writeToChat(String txt){
        appendChatArea(txt + '\n');
    }
    
    public void appendChatArea(String txt){
        textChat.append(txt);
    }

    public JTextArea getTextChat() {
        return textChat;
    }

}