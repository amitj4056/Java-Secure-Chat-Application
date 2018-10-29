


import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.Key;
import java.security.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
public class Launcher extends javax.swing.JFrame {

    private final Socket readSocket;
     static long takenTimeE,takenTimeD;
    private static int flag=0;
    private final Socket writeSocket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private PublicKey publicKeyServer;
    private PrivateKey keyRSAPrivate;
    private static Socket socket;
    public final static String UPDATE_USERS = "updateuserslist:";
    public static String sessionUsername = null;
    private Key AESKey,DESKey;
    static int val=1;
     File file;
     String fileName1="C:\\Users\\hp\\Desktop\\plain";
    public Launcher(Socket readSocket,Socket writeSocket,final Key AESKey,final Key DESKey,final ObjectInputStream  ois,ObjectOutputStream oos) throws Exception {
        
        initComponents();
        
        this.readSocket = readSocket;
        this.writeSocket = writeSocket;
        this.AESKey = AESKey;
        this.DESKey = DESKey;
        
        this.oos=oos;
        this.ois=ois;
        new Thread(){
            public void run(){
                try {
                    while(true){
                        Message encryptedMessage= (Message) ois.readObject();
                        String type = encryptedMessage.getType();
                        MessageDecryption mess=null;
                        if(type.equals("AES")){
                            {
                                mess = new MessageDecryption(encryptedMessage.getMessage(),AESKey,type);
                                consoleTextArea.append("Decryption Time(AES): "+takenTimeD+"\n");
                            }
                        }else{
                            mess = new MessageDecryption(encryptedMessage.getMessage(),DESKey,type);
                            consoleTextArea.append("Decryption Time(DES): "+takenTimeD+"\n");
                        }
                        String plainMessageString = mess.getMessage();
                        if(flag==1)
                        {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName1, true));
                            //writer.append(' ');
                            writer.append(plainMessageString+"\n");
                            writer.close();
                            flag=0;
                        }
                        System.out.println(plainMessageString + " FROM using "+encryptedMessage.getType());
                        chatBoxTextArea.append(plainMessageString+"\n");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
        
        

    }
    public void AesMessage() throws FileNotFoundException, IOException
    {
         JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        file = chooser.getSelectedFile();
        String fileName = file.getAbsolutePath();
        System.out.println(fileName);
        String msg;
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        
        while((msg=br.readLine())!=null)
        {
            
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        msgTextField = new javax.swing.JTextField();
        sendDESBtn = new javax.swing.JButton();
        sendAESBtn = new javax.swing.JButton();
        graphButton = new javax.swing.JButton();
        sendFileDESBtn = new javax.swing.JButton();
        sendFileAESBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatBoxTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        consoleTextArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(32, 100, 142));

        headerLabel.setBackground(new java.awt.Color(27, 157, 191));
        headerLabel.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        headerLabel.setForeground(new java.awt.Color(255, 255, 255));
        headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel.setText("Crypto Project");
        headerLabel.setOpaque(true);

        jPanel4.setBackground(new java.awt.Color(60, 134, 169));

        msgTextField.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        msgTextField.setForeground(new java.awt.Color(24, 118, 183));
        msgTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msgTextFieldActionPerformed(evt);
            }
        });

        sendDESBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        sendDESBtn.setText("Send (DES)");
        sendDESBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendDESBtnActionPerformed(evt);
            }
        });

        sendAESBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        sendAESBtn.setText("Send (AES)");
        sendAESBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendAESBtnActionPerformed(evt);
            }
        });

        graphButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        graphButton.setText("Graph");
        graphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphButtonActionPerformed(evt);
            }
        });

        sendFileDESBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        sendFileDESBtn.setText("Send File (DES)");
        sendFileDESBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendFileDESBtnActionPerformed(evt);
            }
        });

        sendFileAESBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        sendFileAESBtn.setText("Send File (AES)");
        sendFileAESBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendFileAESBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("Enter Message");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(sendDESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendAESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(graphButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendFileDESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sendFileAESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(msgTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msgTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendDESBtn)
                    .addComponent(sendAESBtn)
                    .addComponent(graphButton)
                    .addComponent(sendFileDESBtn)
                    .addComponent(sendFileAESBtn)))
        );

        jPanel3.setBackground(new java.awt.Color(42, 141, 158));

        chatBoxTextArea.setColumns(20);
        chatBoxTextArea.setFont(new java.awt.Font("Monospaced", 3, 18)); // NOI18N
        chatBoxTextArea.setRows(5);
        jScrollPane1.setViewportView(chatBoxTextArea);

        consoleTextArea.setColumns(20);
        consoleTextArea.setFont(new java.awt.Font("Monospaced", 2, 16)); // NOI18N
        consoleTextArea.setRows(5);
        jScrollPane2.setViewportView(consoleTextArea);

        jLabel2.setBackground(new java.awt.Color(78, 141, 167));
        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel2.setText("Console");

        jLabel4.setBackground(new java.awt.Color(78, 141, 167));
        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel4.setText("ChatBox");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(headerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 816, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendFileDESBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendFileDESBtnActionPerformed
       
        
        JFileChooser chooser = new JFileChooser();
        int values1 = chooser.showOpenDialog(null);
        file = chooser.getSelectedFile();
        val=1;
        if (values1 == JFileChooser.APPROVE_OPTION)
        {
            try {
                 
                System.out.println(file.getPath());
                String fileName = file.getAbsolutePath();
                System.out.println(fileName);
                String msgStr;
                BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                chatBoxTextArea.append("file: "+fileName+" has been sent to another client at time "+new Date());
                while((msgStr=br.readLine())!=null)
                {

                    MessageEncryption messEncryption = new MessageEncryption(msgStr,DESKey,"DES");
                    String encryptedMsgStr = messEncryption.getMessageString();
                    Message encryptedMsg = new Message(encryptedMsgStr,"DES"); 
                    chatBoxTextArea.append(msgStr+" \n");
                    oos.writeObject(encryptedMsg);
                    flag=1;
               
                }
            } catch (Exception ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            }    
        } else if (values1 == JFileChooser.CANCEL_OPTION) {
            System.out.println("No file is selected");
        } else if (values1 == JFileChooser.ERROR_OPTION) {
            System.out.println("Error!");
        } else if (file == null) {
            System.out.println("No File is chosen");
        }
            
    }//GEN-LAST:event_sendFileDESBtnActionPerformed

    private void msgTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msgTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_msgTextFieldActionPerformed

    private void sendAESBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendAESBtnActionPerformed

        try {
            
            String msgStr = msgTextField.getText();
            chatBoxTextArea.append("Me: "+msgStr+"\n");
            MessageEncryption messEncryption = new MessageEncryption(msgStr,AESKey,"AES");
            String encryptedMsgStr = messEncryption.getMessageString();
            Message encryptedMsg = new Message(encryptedMsgStr,"AES");
            oos.writeObject(encryptedMsg);
            System.out.println("mes sent");
            //oos.writeInt(0);
             consoleTextArea.append("Encryption Time(AES): "+takenTimeE+"\n");
             consoleTextArea.append("Encrypted Msg(AES): "+encryptedMsgStr+"\n");
            System.out.println("int sent");

        } catch (Exception ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_sendAESBtnActionPerformed

    private void sendDESBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendDESBtnActionPerformed
        try {
            String msgStr = msgTextField.getText();
            chatBoxTextArea.append("Me: "+msgStr+"\n");
            MessageEncryption messEncryption = new MessageEncryption(msgStr,DESKey,"DES");
            String encryptedMsgStr = messEncryption.getMessageString();
            Message encryptedMsg = new Message(encryptedMsgStr,"DES");
             consoleTextArea.append("Encryption Time(DES): "+takenTimeE+"\n");
             consoleTextArea.append("Encrypted Msg(DES): "+encryptedMsgStr+"\n");
            oos.writeObject(encryptedMsg);
            
           // oos.writeInt(0);
        } catch (Exception ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sendDESBtnActionPerformed

    private void graphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphButtonActionPerformed
       
            Frame f = new Frame();
        f.setBounds(200,200,800,300);
        f.add(new Graph());
        f.setResizable(false);
        f.setVisible(true);
        
        
    }//GEN-LAST:event_graphButtonActionPerformed

    private void sendFileAESBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendFileAESBtnActionPerformed
       
        //String fileName=fileName.replace("\" , '/');
        JFileChooser chooser = new JFileChooser();
        int values1 = chooser.showOpenDialog(null);
        file = chooser.getSelectedFile();
        val=1;
        if (values1 == JFileChooser.APPROVE_OPTION)
        {
            try {
                 
                System.out.println(file.getPath());
                String fileName = file.getAbsolutePath();
                System.out.println(fileName);
                String msgStr;
                BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                chatBoxTextArea.append("file: "+fileName+" has been sent to another client at time "+new Date());
                while((msgStr=br.readLine())!=null)
                {

                    MessageEncryption messEncryption = new MessageEncryption(msgStr,AESKey,"AES");
                    String encryptedMsgStr = messEncryption.getMessageString();
                    Message encryptedMsg = new Message(encryptedMsgStr,"AES"); 
                    chatBoxTextArea.append(msgStr+" \n");
                    flag=1;
                    oos.writeObject(encryptedMsg);
                    
                  // oos.writeInt(1);
                   // System.out.println("int sent");
                }
               /* if(val!=0)
                {
                    File f = new File(fileName1);
                    if(f.exists()){
                            f.delete();
                            try {
                                    f.createNewFile();
                                    
                            } catch (IOException e) {
                                    e.printStackTrace();
                            }
                    }
                    val=0;
                }*/
            } catch (Exception ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            }    
        } else if (values1 == JFileChooser.CANCEL_OPTION) {
            System.out.println("No file is selected");
        } else if (values1 == JFileChooser.ERROR_OPTION) {
            System.out.println("Error!");
        } else if (file == null) {
            System.out.println("No File is chosen");
        }
            
    }//GEN-LAST:event_sendFileAESBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatBoxTextArea;
    private javax.swing.JTextArea consoleTextArea;
    private javax.swing.JButton graphButton;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField msgTextField;
    private javax.swing.JButton sendAESBtn;
    private javax.swing.JButton sendDESBtn;
    private javax.swing.JButton sendFileAESBtn;
    private javax.swing.JButton sendFileDESBtn;
    // End of variables declaration//GEN-END:variables
}
