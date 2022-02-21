package tpsit.testclientserver.client;
import java.awt.event.KeyEvent;

public class LoginGUI extends javax.swing.JFrame {
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelDesc = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        textName = new javax.swing.JTextField();
        buttonSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelDesc.setText("Write your username");

        labelName.setText("Name:");

        textName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNameActionPerformed(evt);
            }
        });
        textName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textNameKeyPressed(evt);
            }
        });

        buttonSend.setText("Send");
        buttonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSend))
                    .addComponent(labelDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSend))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNameActionPerformed

    }//GEN-LAST:event_textNameActionPerformed

    private void buttonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSendActionPerformed
        login();
    }//GEN-LAST:event_buttonSendActionPerformed

    private void textNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNameKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            login();
        }
    }//GEN-LAST:event_textNameKeyPressed

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
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginGUI gui = new LoginGUI();
                gui.setVisible(true);
                gui.setLocationRelativeTo(null);    
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSend;
    private javax.swing.JLabel labelDesc;
    private javax.swing.JLabel labelName;
    private javax.swing.JTextField textName;
    // End of variables declaration//GEN-END:variables

    protected ClientChat chat;
    
    public LoginGUI() {
        super("Login");
        initComponents();
        setResizable(false);

        chat = new ClientChat(MainClient.port, MainClient.address);
        chat.connect();
    }
    
    public void login(){
        System.out.println("Tentativo login");
        
        if(!chat.logged){
           if(!isUsernameInvalid(textName.getText())){    //controllo validità caratteri

            //controllo disponibilità del nome
            chat.sendMessage("/name @" + textName.getText());
            chat.message = chat.receiveMessage();

            if(chat.message.equals("/name_validity true")){
                chat.logged = true;
                chat.name = textName.getText();
                createChatWindow();
                
            }else if(chat.message.equals("/name_validity false")){
                labelDesc.setText("The inserted name is already in use, try again");
            }else{
                labelDesc.setText("Error: the name is not valid, try again");
            }

            }else{
                labelDesc.setText("The name contains prohibited characters, try again");
            } 
        }      
    }
    
    public void createChatWindow(){
        System.out.println("Login completato, creazione finestra...");
        
        ChatGUI chatGui = new ChatGUI(chat);
        chatGui.setVisible(true);
        this.setVisible(false);
    }
    
    public boolean isUsernameInvalid(String username){
        return username.contains("/") || username.contains("@") || username.contains(",") || username.contains(" ");
    }
    
}
