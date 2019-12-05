package Client.Controller;

import Messages.Object.Serializable.MessageResultSet;
import Messages.Object.Serializable.MessageSignup;
import Messages.Object.Serializable.Message;
import Messages.Object.Serializable.Message2;
import com.sun.org.apache.bcel.internal.generic.LNEG;
import Client.Views.MainInterface;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import Server.database.models.models;

/**
 *****
 * *****
 
                         ****  @author BOUCHEBAH  AGHILAS  *****
                 
                 
       
                 **             *************                       |************|            *
                *  *            |           *                       |            | 
               *    *           |                                   |            |
              *      *          |    *********                      |************|            *
             **********         |    |       *      ********        |            |
            *          *        |            *       ******         |            |
           *            *       **************        ****          **************
***
***|
*/ 

public class SocketClient implements Runnable{
    
    public int port;
    public String serverAddr;
    public Socket socket;
    public MainInterface ui;
    public ObjectInputStream In;
    public ObjectOutputStream Out;

    private Cipher cipher1;
    private Cipher ClientDecryptCipher;
	private Cipher cipher2;
	int i = 0;
	Message msg;
        MessageSignup msgSignup;
        MessageResultSet messageResultSet;
	SecretKey AESkey;
	Message2 msg2;
        static String IV = "AAAAAAAAAAAAAAAA";
        models db;
      
         String jaibloque[]=new String[50];
         int s,k=0;
         String jesuisbloque[]=new String[50];
         int nbrjaiBloque=0;
         int nbrjesuisBloque=0;
         boolean connect;
    
    public SocketClient(MainInterface frame) throws IOException, NoSuchAlgorithmException{
        db=new models();
        ui = frame; this.serverAddr = ui.serverAddr; this.port = ui.port;
        socket = new Socket(InetAddress.getByName(serverAddr), port);
        System.out.println("ladresse du serveur"+InetAddress.getAllByName(serverAddr).toString());
            generateAESkey();
            msg2 = new Message2(encryptAESKey());
		
        Out = new ObjectOutputStream(socket.getOutputStream());
        Out.flush();
        Out.writeObject(msg2);
        Out.flush();
        In = new ObjectInputStream(socket.getInputStream());
       
    }

    @Override
    public void run() {
        boolean keepRunning = true;
        int j=0;
        boolean msgSimple=false;
        while(keepRunning){
            try {
               System.out.println("la methode run de clien est demarré "); 
             Message2    msg2 = (Message2) In.readObject();
              System.out.println(" le message en byte est recu"+msg2.getData());
              
               try {
                            System.out.println("je suis dans la premiere partie de run = Message");
                            decryptMessage(msg2.getData());
                            
                            msgSimple=true;
                            
                        } catch (Exception e) {
                            j++;
                             System.out.println("je suis dans la deuxiemme partie de run = Messagesignup j= " +j );
                            
                            if(j==1) { decryptMessageSignup(msg2.getData());
                           
                            
                           handleSignup(msgSignup);}
                            else if(j>1){
                                
                                try {
                                    
                                
                                System.out.println("je suis dans la troisieme partie de run = resultset j= " +j );
                            
                                decryptMessageResultSet(msg2.getData());
                          //  i=i+1;
                                   
                                        System.out.println("resultset invitation model2 ");
                                   
                          if(messageResultSet.model2.getColumnCount()>1){
                               System.out.println("je suis dans resultset de amis et message " );
                           handleResultSet(messageResultSet);}
                          else if(messageResultSet.model2.getColumnCount()==1){
                              System.out.println("je suis dans invitation - getUserAll " ); 
                          String type=messageResultSet.model2.getValueAt(0,0).toString();
                              System.out.println("le type de resultset "+type);
                              if(type.equals("getAllUser")){handleGetAllUsers(messageResultSet);}
                             
                              else{
                              handleInvitation(messageResultSet);}
                         
                            
                            }
                          
                         
                       
                         } catch (Exception ex) {
                                    System.out.println("erreur resultset invit - getalluser "+ex);
                               decryptMessageSignup(msg2.getData());
                           
                            if(msgSignup.nom.equals("")){handleSignupUpdate(msgSignup);}else{
                           handleSignup(msgSignup);     }
                             
                             System.out.println("handle signup est appelé avec succes  "+ex);
                                  
                         }
                               
                            
                            }}
               

              
        
                System.out.println("le message est decrypté "+msg);
                
                if(msgSimple){
                
                if(msg.type.equals("message")){
                    if(msg.recipient.equals(ui.username)){
                        System.out.println("le message est "+msg.content);
                        String user=(String)ui.jList1.getSelectedValue();
                        
                        if(ui.jPanelMessage.isVisible() && user.equals(msg.sender) ){
                                            if(ui.jTextArea1.getText().isEmpty()){
                                            }

              String msgTime = (new Date()).toString();
                String time=msgTime.substring(11, 16);
               ui.jTextArea1.append("  "+time+"\n  "+msg.sender +" : " + msg.content + "\n \n");
               
                    }
                        
                        }
                        
                       
                    else{
                        ui.jTextArea1.append("["+ msg.sender +" > "+ msg.recipient +"] : " + msg.content + "\n");
                    }
                                            
                    if(!msg.content.equals(".bye") && !msg.sender.equals(ui.username)){
                        String msgTime = (new Date()).toString();
                        
                        try{
                            
                        }
                        catch(Exception ex){}  
                    }
                }
                else if(msg.type.equals("login")){
                    if(msg.content.equals("TRUE")){
                    JOptionPane.showMessageDialog(null,"Authentification reussi ");  
                   
                      ui.jPanelMenu.setVisible(true);
                      ui.jPanelAutentif.setVisible(false);
                      ui.jPanelMessage.setVisible(false);
                      ui.jPanelAjoutAmis.setVisible(false);
                      ui.jPanelInfoPerso.setVisible(true);
                      ui.jPanelInvitationRecu.setVisible(false);
                      
                    }
                    else{
                    JOptionPane.showMessageDialog(null,"Authentification echoué. ");  

                    }
                }
                else if(msg.type.equals("test")){
                    ui.jLabel9.setEnabled(true);
                    ui.jButton1.setEnabled(false);
                    ui.jButton2.setEnabled(true);
                    ui.jTextField3.setEnabled(true); ui.jPasswordField1.setEnabled(true);
                    

                }
                
                else if(msg.type.equals("sendInvitation")){
                    if(msg.content.equals("TRUE")){
                     JOptionPane.showMessageDialog(null,"Invitation envoyée avec succés");
                 ui.    jButton11.setEnabled(false);
   ui.   jButton12.setEnabled(true);
   ui.   jLabel19.setSize(170, 20);
                    }else{ JOptionPane.showMessageDialog(null,"Impossible d'envoyer cette invitation !");}
                }
                
                
                else if(msg.type.equals("deleteInvitation")){
                    if(msg.content.equals("TRUE")){
                     JOptionPane.showMessageDialog(null,"Invitation supprimer avec succés");
                 ui.    jButton11.setEnabled(true);
                ui.   jButton12.setEnabled(false);
                ui.   jLabel19.setSize(0,0);
                    }else{ JOptionPane.showMessageDialog(null,"Impossible de suprimmer cette invitation !");}
                }
                
                
                 else if(msg.type.equals("deleteHistorique")){
                    if(msg.content.equals("TRUE")){
                     JOptionPane.showMessageDialog(null,"l'historique supprimer avec succés");
                     ui.jTextArea1.setText(null);
                 
                    
                    }else{ JOptionPane.showMessageDialog(null,"Impossible de suprimmer l'historique !");}
                }
                
                
                else if(msg.type.equals("updateProfil")){
                    if(msg.content.equals("TRUE")){
                     JOptionPane.showMessageDialog(null,"enregistrement fait avec succés !");
                    }else{ JOptionPane.showMessageDialog(null,"Erreur lors de l'enregistrement !");}
                }
                
                else if(msg.type.equals("accepteInvitation")){
                    try {
                        
                    if(msg.content.equals("TRUE")){
                        ui.model.clear();
                     JOptionPane.showMessageDialog(null,"l'invitation est acceptée, vous etes désormais amis !");
                    ui.jLabel27.setText("");
                    ui.jLabel28.setText("");ui.jLabel30.setText("");ui.jLabel31.setText("");
                    ui.jLabel15.setIcon(null);
                    int nbr=Integer.parseInt(ui.jLabel35.getText());
                            nbr--;
                         ui.jLabel35.setText(String.valueOf(nbr));
                   
                    }else{ JOptionPane.showMessageDialog(null,"Erreur lors de l'enregistrement !");}
                     } catch (Exception e) {System.out.println("erreur dans acceteInvitation "+e);};
                    }
                
                
                
                  else if(msg.type.equals("invitationAccepté")){
                    ui.model.clear();
                   
                     JOptionPane.showMessageDialog(null,"l'invitation est acceptée, vous etes désormais ami avec "+msg.content);

                }

                  
                  
                  else if(msg.type.equals("refuserInvitation")){
                   if(msg.content.equals("TRUE")){
                    int nbr=Integer.parseInt(ui.jLabel35.getText());
       nbr--;
       ui.jLabel35.setText(String.valueOf(nbr));
                    }else{ JOptionPane.showMessageDialog(null,"Erreur lors de l'enregistrement !");}
                  }
                  
                  
                  
                  
                else if(msg.type.equals("newuser")){try {
                        
                    
                    if(!msg.content.equals(ui.username)){
                         boolean bloq=false;
                  if(nbrjaiBloque>0){for (int l = 0; l < nbrjaiBloque; l++) {
                        if(jaibloque[l].equals(msg.content))bloq=true;
                      }
                    }
                  
                   if(nbrjesuisBloque>0){for (int n = 0; n < nbrjesuisBloque; n++) {
                        if(jesuisbloque[n].equals(msg.content))bloq=true;
                      }
                    }
                  
                  if(!bloq){
              JOptionPane.showMessageDialog(null,msg.content+" vient de se connecter ");
              String user=(String)ui.jList1.getSelectedValue();
              if(ui.jPanelMessage.isVisible() && user.equals(msg.content ) ){
              
             
             ui.Lstatut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/En ligne.png")));
  ui.jButton4.setEnabled(true);
              
              }
                  }}
                }  catch (Exception e) {System.out.println("erreur dans newuser "+e);
                    }  
                    
                }
                
                
                else if(msg.type.equals("liste1")){
                
                     
                      if(msg.sender.equals("0") && msg.recipient.equals("0")){
                
                      ui.model.addElement(msg.content); }
                
                      else if(msg.sender.equals("1")){ 
                          
                          //je l'ai bloqué
                            System.out.println("jai bloqué cet ami "+msg.content);
                         jaibloque[k]=msg.content;
                      k++;
                      nbrjaiBloque++;
                      ui.model.addElement(msg.content);
                      }
                    
                      else if(msg.recipient.equals("1")){
                        // je suis bloqué par lui
                        jesuisbloque[s]=msg.content;
                        s++;
                       nbrjesuisBloque++;
                          System.out.println("je suis bloqué par "+msg.content);
                      }
                
                }
                
                
                else if(msg.type.equals("liste2")){if(msg.sender.equals("0") && msg.recipient.equals("0")){
               
                      ui.model5.addElement(msg.content); }
                
                      else if(msg.sender.equals("1")){ 
                          
                          //je l'ai bloqué
                            System.out.println("jai bloqué cet ami "+msg.content);
                        
                      ui.model5.addElement(msg.content);
                      }
                    
                      else if(msg.recipient.equals("1")){
                       
                          System.out.println("je suis bloqué par "+msg.content);
                      }}
                
                
                
                else if(msg.type.equals("nbrInvitation")){ui.jLabel35.setText(msg.content);}
                
                        
                else if(msg.type.equals("messageManqués")){ui.jLabel13.setText(msg.content);}
                
                
                else if(msg.type.equals("signup")){
                    if(msg.content.equals("TRUE")){
                       
                    
                        JOptionPane.showMessageDialog(null,"l'enregistrement fait avec succes");
                      
                    }
                    else{
                         JOptionPane.showMessageDialog(null,"nom d'utilisateur existe daja, veuillez choisir un autre. ");  

                       
                    }
                }
                else if(msg.type.equals("verifyInvitation")){
                    if(msg.content.equals("TRUE")){
                  ui.jButton11.setEnabled(false);
                  ui.jButton12.setEnabled(true);
                    ui.jLabel19.setSize(170, 20);
                    }else{ ui.jButton11.setEnabled(true);
                  ui.jButton12.setEnabled(false);
                       ui.jLabel19.setSize(0, 0);}
                }
                
                 else if(msg.type.equals("deleteFriend")){
                     
                    if(msg.content.equals("TRUE")){
                         ui.model.clear();
                        ui.jPanelMessage.setVisible(false);
                        ui.jPanelInfoPerso.setVisible(true);
                           JOptionPane.showMessageDialog(null,"suppression avec succés");
           
                    }else{
                       JOptionPane.showMessageDialog(null,"Erreur lors de la suppression");}
                }
                
                  else if(msg.type.equals("Iam deleted")){
                        ui.model.clear();
           
                  if(ui.jPanelMessage.isVisible() && ui.jLabelnameAmi.getText().equals(msg.content) ){
                                           ui.jPanelMessage.setVisible(false);
                        ui.jPanelInfoPerso.setVisible(true);
                JOptionPane.showMessageDialog(null,ui.jLabelnameAmi.getText()+" vous a supprimer de sa liste d'amis");
               
                                            }
                  else if(!ui.jPanelMessage.isVisible() || !ui.jLabelnameAmi.getText().equals(msg.content)){
                  JOptionPane.showMessageDialog(null,msg.content+" vous a supprimer de sa liste d'amis");
               
                  }
                    }
                  
                
                
                   else if(msg.type.equals("bloqueFriend")){
                     
                    if(msg.content.equals("TRUE")){
                         ui.model.clear();
                        ui.jPanelMessage.setVisible(false);
                        ui.jPanelInfoPerso.setVisible(true);
                        
                           JOptionPane.showMessageDialog(null,"l'operation de bloquage est faite avec succes");
           
                    }else{
                       JOptionPane.showMessageDialog(null,"Erreur lors du bloque");}
                }
                
                  else if(msg.type.equals("Iam bloqued")){
                        ui.model.clear();
           
                  if(ui.jPanelMessage.isVisible() && ui.jLabelnameAmi.getText().equals(msg.content) ){
                                           ui.jPanelMessage.setVisible(false);
                        ui.jPanelInfoPerso.setVisible(true);
                JOptionPane.showMessageDialog(null,ui.jLabelnameAmi.getText()+" vous a bloqué");
               
                                            }
                  else if(!ui.jPanelMessage.isVisible() || !ui.jLabelnameAmi.getText().equals(msg.content)){
                  JOptionPane.showMessageDialog(null,msg.content+" vous a bloqué de sa liste d'amis");
               
                  }
                    }
                  
                  
                  else if(msg.type.equals("debloqueFriend")){
                     
                    if(msg.content.equals("TRUE")){
                       for (int f = 0; f < nbrjaiBloque; f++) {
                        jaibloque[f]="";
                    }
                       nbrjaiBloque=0;
                        
                          JOptionPane.showMessageDialog(null,"l'operation de débloquage est faite avec succes");
           
  ui.jButton5.setEnabled(true);
  ui.jButton14.setEnabled(true);ui.jButton17.setEnabled(true);ui.jButton19.setEnabled(false);
  ui.jButton15.setEnabled(true);
   ui.jButton4.setEnabled(true);
  if(connect){
  ui.Lstatut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/En ligne.png")));
 }
                        
                          
                    }else{
                       JOptionPane.showMessageDialog(null,"Erreur lors du bloque");}
                }
                
                  else if(msg.type.equals("Iam debloqued")){
                      
                     
                        for (int f = 0; f < nbrjesuisBloque; f++) {
                        jesuisbloque[f]="";
                    }
                    nbrjesuisBloque=0;
                    
                    
                      ui.model.clear();
                     
           
                  if(ui.jPanelMessage.isVisible() && ui.jLabelnameAmi.getText().equals(msg.content) ){
                                           ui.jPanelMessage.setVisible(false);
                        ui.jPanelInfoPerso.setVisible(true);
                JOptionPane.showMessageDialog(null,ui.jLabelnameAmi.getText()+" vous a debloqué");
               
                                            }
                  else if(!ui.jPanelMessage.isVisible() || !ui.jLabelnameAmi.getText().equals(msg.content)){
                  JOptionPane.showMessageDialog(null,msg.content+" vous a debloqué ");
               
                  }
                    }
                  
                
                else if(msg.type.equals("signout")){
                    if(msg.recipient.equals(ui.username)){
                        System.out.println("je suis dans signout");
                        ui.jButton1.setEnabled(true);
                      
                        ui.jButton2.setEnabled(false);
                        ui.jLabel9.setEnabled(false);
                        
                          ui.jPanelMenu.setVisible(false);
                      ui.jPanelAutentif.setVisible(true);
                      ui.jPanelMessage.setVisible(false);
                      ui.jPanelAjoutAmis.setVisible(false);
                         
                            ui.model.clear();
                      ui. jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/icons8_Male_User_100px_2.png")));
                    
                        ui.clientThread.stop();
                    }
                    else{
       
                  boolean bloq=false;
                  if(nbrjaiBloque>0){for (int l = 0; l < nbrjaiBloque; l++) {
                        if(jaibloque[l].equals(msg.content))bloq=true;
                      }
                    }
                   if(nbrjesuisBloque>0){for (int n = 0; n < nbrjesuisBloque; n++) {
                        if(jesuisbloque[n].equals(msg.content))bloq=true;
                      }
                    }
                  
                  if(!bloq){
                      JOptionPane.showMessageDialog(null,msg.content+" vient de se deconnecter");
                       String user=(String)ui.jList1.getSelectedValue();
                    if(ui.jPanelMessage.isVisible() && user.equals(msg.content ) ){
              
             
             ui.Lstatut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/images/hors-ligne-icon.png")));
  ui.jButton4.setEnabled(false);
              
              }}
              
              }
   
                    }
                
                else if(msg.type.equals("upload_req")){
                    
                    if(JOptionPane.showConfirmDialog(ui, ("Acceptez vous '"+msg.content+"' de "+msg.sender+" ?")) == 0){
                        
                        JFileChooser jf = new JFileChooser();
                        jf.setSelectedFile(new File(msg.content));
                        int returnVal = jf.showSaveDialog(ui);
                    
                        String saveTo = jf.getSelectedFile().getPath();
                        System.out.println("le path recu pour telecharger est ? "+saveTo);
                        if(saveTo != null && returnVal == JFileChooser.APPROVE_OPTION){
                            DownloadFile dwn = new DownloadFile(saveTo, ui);
                            Thread t = new Thread(dwn);
                            t.start();
                            send(new Message("upload_res", ui.username, (""+dwn.port), msg.sender));
                        }
                        else{
                            send(new Message("upload_res", ui.username, "NO", msg.sender));
                        }
                    }
                    else{
                        send(new Message("upload_res", ui.username, "NO", msg.sender));
                    }
                }
                else if(msg.type.equals("upload_res")){
                    if(!msg.content.equals("NO")){
                        int port  = Integer.parseInt(msg.content);
                        String addr = msg.sender;
                        
                        ui.jButton5.setEnabled(false); ui.jButton6.setEnabled(false);
                        UploadFile upl = new UploadFile(addr, port, ui.file, ui);
                        Thread t = new Thread(upl);
                        t.start();
                    }
                    else{
                    JOptionPane.showMessageDialog(null,msg.sender+" refuse de recevoir le fichier");  
                    }
                }
                else{
                      JOptionPane.showMessageDialog(null," message inconnu");  

                }
           
              msgSimple=false;   
                }
        }
            
            catch(Exception ex) {
                keepRunning = false;
                JOptionPane.showMessageDialog(null," Connexion echoué");  

               
                ui.jButton1.setEnabled(true); 
                ui.jButton4.setEnabled(false); ui.jButton5.setEnabled(false); ui.jButton5.setEnabled(false);
                
                for(int i = 0; i < ui.model.size(); i++){
                    ui.model.removeElementAt(i);
                }
                
                ui.clientThread.stop();
                
                System.out.println("Exception SocketClient run() "+ex);
                ex.printStackTrace();
            }
        }
    }
    
   public void  handleSignup(MessageSignup msg) throws IOException {
   
       System.out.println("je suis dans handle signup "+msg.nom+" "+msg.prenom);
       
            ui.jLabel2.setText("HeLLO "+msg.nom+" "+msg.prenom);
           ui.jLabelNom.setText(msg.nom);
                     ui.jLabePrenom.setText(msg.prenom);
                                     
                                     if(msg.image.length !=0){
                                       ByteArrayInputStream bais =new ByteArrayInputStream(msg.image);
                                       BufferedImage bImg=ImageIO.read(bais);
                                       ImageIcon icon=new ImageIcon(bImg);
                                       
                                       ui.jLabel16.setIcon(icon);
                                       bais.close();}
                               ui.jTextFieldSecteur.setText(msg.secteur);
                               ui.jPasswordField2.setText(msg.motDePasse);
                               ui.jTextFieldEmail.setText(msg.email);
   
   }
    
   
    public void  handleSignupUpdate(MessageSignup msg)  {
        try {
            ui.jTextFieldImage.setText(null);
       System.out.println("je suis dans handle signup update "+msg.nom+" "+msg.prenom);
       
        byte[] img =    (byte[]) msg.image;
                                    if(img.length !=0){
                                       ByteArrayInputStream bais =new ByteArrayInputStream(img);
                                       BufferedImage bImg=ImageIO.read(bais);
                                       ImageIcon icon=new ImageIcon(bImg);
                                       
                                       ui.jLabel16.setIcon(icon);
                                       bais.close();}
                               ui.jTextFieldSecteur.setText(msg.secteur);
                               ui.jPasswordField2.setText(msg.motDePasse);
                               ui.jTextFieldEmail.setText(msg.email);
           } catch (Exception e) {System.out.println("erreur dans handlsignup update "+e);
        }

   }
   
   
   
  public void  handleResultSet(MessageResultSet msg) throws IOException{
  
      System.out.println("je suis dans handleResultSet"+ msg.model1.getValueAt(0, 2));
    Boolean bloquer=false;
      try {
          if(nbrjaiBloque>0){
          for (int j = 0; j < nbrjaiBloque; j++) {
              if(ui.jLabelnameAmi.getText().equals(jaibloque[j])){bloquer=true;}
          }}
          if(bloquer){
          
           ui.jLabel34.setText(msg.model1.getValueAt(0,0).toString()+"  "+msg.model1.getValueAt(0,1).toString());
           ui.jLabel4.setText(msg.model1.getValueAt(0,2).toString());
           
              byte[] img =    (byte[]) msg.model1.getValueAt(0, 5);
             
       if(img !=null){
                                       ByteArrayInputStream bais =new ByteArrayInputStream(img);
                                       BufferedImage bImg=ImageIO.read(bais);
                                       ImageIcon icon=new ImageIcon(bImg);
                                       ui.jLabel7.setIcon(icon);
                                       bais.close();}
         ui.Lemail.setText(msg.model1.getValueAt(0,3).toString());
         
         ui.Lstatut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/images/hors-ligne-icon.png")));
         
  ui.jButton4.setEnabled(false);
  ui.jButton5.setEnabled(false);
  ui.jButton14.setEnabled(false);ui.jButton17.setEnabled(false);ui.jButton19.setEnabled(true);
  ui.jButton15.setEnabled(false);
          
  String statut=msg.model1.getValueAt(0,4).toString();

  
          System.out.println("juste avant le check statut "+statut);
  if(statut.equals("enligne")){connect=true;}
  else if(statut.equals("horligne")){connect=false;}
  
          }
          
          
          else{
 
              ui.jButton4.setEnabled(true);
  ui.jButton5.setEnabled(true);
  ui.jButton14.setEnabled(true);ui.jButton17.setEnabled(true);ui.jButton19.setEnabled(false);
  ui.jButton15.setEnabled(true);
              
              
              
              ui.jLabel34.setText(msg.model1.getValueAt(0,0).toString()+"  "+msg.model1.getValueAt(0,1).toString());
           ui.jLabel4.setText(msg.model1.getValueAt(0,2).toString());
           
              byte[] img =    (byte[]) msg.model1.getValueAt(0, 5);
             
       if(img !=null){
                                       ByteArrayInputStream bais =new ByteArrayInputStream(img);
                                       BufferedImage bImg=ImageIO.read(bais);
                                       ImageIcon icon=new ImageIcon(bImg);
                                       ui.jLabel7.setIcon(icon);
                                       bais.close();}
  
  String statut=msg.model1.getValueAt(0,4).toString();
 
  
          System.out.println("juste avant le check statut "+statut);
  if(statut.equals("enligne")){ui.Lstatut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/En ligne.png")));
 }
  else if(statut.equals("horligne")){ui.Lstatut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/hors-ligne-icon.png")));
   }
 
  ui.Lemail.setText(msg.model1.getValueAt(0,3).toString());
  
  
   
     
       for (int j = 0; j < msg.model2.getRowCount(); j++) {
          DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String time=df.format(msg.model2.getValueAt(j,3)).substring(0, 16);
          System.out.println("l'emeteur "+msg.model2.getValueAt(j, 0)+ time);
          if(msg.model2.getValueAt(j, 0).equals(ui.username)){
                ui.jTextArea1.append("                                      "+time+"\n                                  Me :" +msg.model2.getValueAt(j,2) + "\n \n");
   
              }
          else{
          
               ui.jTextArea1.append("  "+time+"\n  "+msg.model2.getValueAt(j,0) +"  : " + msg.model2.getValueAt(j,2) + "\n \n");
          }
                 
      
      }
          }
   } catch (Exception e) {System.out.println("erreur dans hadleResultSet "+e);
      }
  }
  
  
  
  
 public void   handleGetAllUsers(MessageResultSet msg) throws IOException{
  
   try {
  ui.jLabel19.setSize(0, 0);
  ui.jTable2.setModel(msg.model1);
   ui.jTable2.getColumnModel().getColumn(4).setMinWidth(0);
      ui. jTable2.getColumnModel().getColumn(4).setMaxWidth(0);
       ui.jTable2.getColumnModel().getColumn(5).setMinWidth(0);
      ui. jTable2.getColumnModel().getColumn(5).setMaxWidth(0);
  
       } catch (Exception e) {System.out.println("erreur dans hadleGetAllUser "+e);
      }
 }
 
  
    
 public void   handleInvitation  (MessageResultSet msg) throws IOException{
  
   try {
  
      ui.jTable3.setModel(msg.model1);
       ui.jTable3.getColumnModel().getColumn(0).setMinWidth(0);
      ui. jTable3.getColumnModel().getColumn(0).setMaxWidth(0);
      ui.jTable3.getColumnModel().getColumn(5).setMinWidth(0);
      ui. jTable3.getColumnModel().getColumn(5).setMaxWidth(0);
  
   } catch (Exception e) {System.out.println("erreur dans hadleInvitation "+e);
      }
 }     
 
 
 
 
    
    public void send(Message msg){
        try {
            System.out.println("le msg avnt cyptage "+msg.toString());
           msg2 = new Message2(encryptMessage(msg));
           
            Out.writeObject(msg2);
            Out.flush();
            System.out.println("le message a ete envoyer");
            if(msg.type.equals("message")){
                if(ui.jTextArea1.getText().isEmpty()){
                }
                String msgTime = (new Date()).toString();
                String time=msgTime.substring(11, 16);
            ui.jTextArea1.append("                                       "+time+"\n                                      Me  : " +msg.content + "\n \n");}
           
        } 
        catch (IOException ex) {
            System.out.println("Exception SocketClient send()");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void sendSignup(MessageSignup msg,String type){
        try {
            System.out.println("le msg avnt cyptage "+msg.toString());
           msg2 = new Message2(encryptMessageSignup(msg));
           
            Out.writeObject(msg2);
            Out.flush();
            System.out.println("le message a ete envoyer");
           
        } 
        catch (IOException ex) {
            System.out.println("Exception SocketClient send()");
        
    }   catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
    /*
	 * //============== Create AES Key =================================
	 * 
	 * generateAESkey method
	 * 
	 *	Called by main method, generates the AES key for encryption / decryption of the messages
                                                                exchanged between client and server.
	 */
    
    void generateAESkey() throws NoSuchAlgorithmException{
	AESkey = null;
	KeyGenerator Gen = KeyGenerator.getInstance("AES");
	Gen.init(128);
	AESkey = Gen.generateKey();
	System.out.println("Genereated the AES key : " + AESkey);
	}
	
    
    /*
	 * // ====== Read RSA Public key to Encrypt the AES key  ==================
	 * 
	 * encryptAESKey method.
	 * 
	 *          Will encrypt the AES key generated by generateAESkey method. 
                    It will also calculate the time taken for encrypting the AES key using RSA encryption method.
	 * 
	 *          To encrypt the AES key, this method will read RSA public key from the RSA public = private key pairs 
                                                                                     saved in the same directory.
	 * 							
	 *          Dependency: the public key  file "public.key" should be saved in the same directory. 
                                                                        (Performed by server.java class)
	 * 	
	 */
    
    private byte[] encryptAESKey (){
		cipher1 = null;
    	byte[] key = null;
  	  try
  	  {
		 PublicKey pK = readPublicKeyFromFile("public.key");
	 	  System.out.println("Encrypting the AES key using RSA Public Key" + pK);
   	     // initialize the cipher with the user's public key
   	     cipher1 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
   	    
   	     cipher1.init(Cipher.ENCRYPT_MODE, pK );
   	     long time1 = System.nanoTime();
   	     key = cipher1.doFinal(AESkey.getEncoded());   // this encrypted key will be sent to the server.
   	     long time2 = System.nanoTime();
   	     long totalRSA = time2 - time1;
   	     System.out.println("Time taken by RSA Encryption (Nano Seconds) : " + totalRSA);
   	     i = 1;
   	 	}
  	  
   	 catch(Exception e ) {
    	    System.out.println ( "exception encoding key: " + e.getMessage() );
    	    e.printStackTrace();
   	 		}
  	  return key;
  	  } 
	
    
    PublicKey readPublicKeyFromFile(String fileName) throws IOException {
		
	 	FileInputStream in = new FileInputStream(fileName);
	  	ObjectInputStream oin =  new ObjectInputStream(new BufferedInputStream(in));

	  	try {
	  	  BigInteger m = (BigInteger) oin.readObject();
	  	  BigInteger e = (BigInteger) oin.readObject();
	  	  RSAPublicKeySpec keySpecifications = new RSAPublicKeySpec(m, e);
	  	  
	  	  KeyFactory kF = KeyFactory.getInstance("RSA");
	  	  PublicKey pubK = kF.generatePublic(keySpecifications);
	  	  return pubK;
	  	} catch (Exception e) {
	  		  throw new RuntimeException("Some error in reading public key", e);
	  	} finally {
	 	   oin.close();
	 	 }
		}
    
    
    
    /*
		 * //============= Encrypt Data to send =================
		 * 
		 * encryptMessage method
		 * 						Encrypts the string input using AES encryption with AES key generated by generateAESkey method.
		 * 
		 * @param 	String s 
		 * 					Input string to encrypt
		 * 
		 * Returns byte array as output.
		 * 
		 */

	
	
    private byte[] encryptMessage(Message s) throws NoSuchAlgorithmException, NoSuchPaddingException, 
							InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, 
											BadPaddingException{
		byte [] obj=getBytes(s);
        cipher2 = null;
    	byte[] cipherText = null;
    	cipher2 = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    	
    	cipher2.init(Cipher.ENCRYPT_MODE, AESkey, new IvParameterSpec(IV.getBytes()) );
    	long time3 = System.nanoTime();
    	cipherText = cipher2.doFinal(obj);
          System.out.println("le message crypté en byte est  " + cipherText);
        
    	long time4 = System.nanoTime();
		long totalAES = time4 - time3;
		System.out.println("Time taken by AES Encryption (Nano Seconds) " + totalAES);
   	   return cipherText;
	}
    
    
     private byte[] encryptMessageSignup(MessageSignup s) throws NoSuchAlgorithmException, NoSuchPaddingException, 
							InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, 
											BadPaddingException{
		byte [] obj=getBytesSignup(s);
        cipher2 = null;
    	byte[] cipherText = null;
    	cipher2 = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    	
    	cipher2.init(Cipher.ENCRYPT_MODE, AESkey, new IvParameterSpec(IV.getBytes()) );
    	long time3 = System.nanoTime();
    	cipherText = cipher2.doFinal(obj);
          System.out.println("le message crypté en byte est  " + cipherText);
        
    	long time4 = System.nanoTime();
		long totalAES = time4 - time3;
		System.out.println("Time taken by AES Encryption (Nano Seconds) " + totalAES);
   	   return cipherText;
	}
	
		
	/*
	 * //=========== Decipher the received message with AES key =================
	 * 
	 * decryptMessage method, will decrypt the cipher text received from server. Currently disabled, can be enabled for two way communication.
	 * 
	 * @param byte[] data
	 * 					takes the byte array of encrypted message as input. Returns plain text.
	 * 
	 * 	
	 */
	
		
    
     private void decryptMessage(byte[] encryptedMessage) throws NoSuchAlgorithmException, BadPaddingException, IOException, ClassNotFoundException,
               IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchPaddingException {
	        ClientDecryptCipher = null;
	        System.out.println("la taille du msg"+encryptedMessage.length);
               
	            ClientDecryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	            ClientDecryptCipher.init(Cipher.DECRYPT_MODE, AESkey, new IvParameterSpec(IV.getBytes()));
                      System.out.println("le decryptage 222 est fini ");
	             byte[] msgs = ClientDecryptCipher.doFinal(encryptedMessage);		            
	             System.out.println("le decryptage 333 est fini ");
                     msg=getObet(msgs);
                
	    }
     
                      


    public void  decryptMessageResultSet(byte[] encryptedMessage)throws NoSuchAlgorithmException,
              NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
              IllegalBlockSizeException, IOException, BadPaddingException, ClassNotFoundException {
        ClientDecryptCipher = null;
           System.out.println("la taille du message "+encryptedMessage.length);
           
            ClientDecryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            ClientDecryptCipher.init(Cipher.DECRYPT_MODE, AESkey, new IvParameterSpec(IV.getBytes()));
            System.out.println("le message en byte est "+encryptedMessage);
            byte[] msg = ClientDecryptCipher.doFinal(encryptedMessage);
            messageResultSet =getObetResultSet(msg);
            
       
       }

    
    

      private void decryptMessageSignup(byte[] encryptedMessage) throws NoSuchAlgorithmException,
              NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
              IllegalBlockSizeException, IOException, BadPaddingException, ClassNotFoundException {
        ClientDecryptCipher = null;
           System.out.println("la taille du message signup "+encryptedMessage.length);
           
            ClientDecryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            ClientDecryptCipher.init(Cipher.DECRYPT_MODE, AESkey, new IvParameterSpec(IV.getBytes()));
            System.out.println("le message signup en byte est "+encryptedMessage);
            byte[] msg = ClientDecryptCipher.doFinal(encryptedMessage);
            msgSignup =getObetSignup(msg);
            
            System.out.println("le message décrypté en objet message est "+msgSignup);
          
       
       }
    
     public byte[] getBytes(Message m){
                byte[] data=null;
                
        try {
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(bos);
            oos.writeObject(m);
            oos.flush();
            oos.close();
            bos.close();
            data=bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                return data;
                
                }
    
      public byte[] getBytesSignup(MessageSignup m){
                byte[] data=null;
                
        try {
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(bos);
            oos.writeObject(m);
            oos.flush();
            oos.close();
            bos.close();
            data=bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                return data;
                
                }
    
    
    public void closeThread(Thread t){
        t = null;
    }

    private Message getObet(byte[] msg) throws IOException, ClassNotFoundException {
        Message obj=null;
        ByteArrayInputStream bis=null;
        ObjectInputStream ois=null;
        try {
            bis=new ByteArrayInputStream(msg);
        ois=new ObjectInputStream(bis);
        
        obj=(Message)ois.readObject();
        
            
        } finally {
            if (ois!=null){bis.close();}
        if(ois!=null){ois.close();}
        }
        
    return obj;
    }
    
    
     private MessageSignup getObetSignup(byte[] msg) throws IOException, ClassNotFoundException {
        MessageSignup obj=null;
        ByteArrayInputStream bis=null;
        ObjectInputStream ois=null;
        try {
            bis=new ByteArrayInputStream(msg);
        ois=new ObjectInputStream(bis);
        
        obj=(MessageSignup)ois.readObject();
        
            
        } finally {
            if (ois!=null){bis.close();}
        if(ois!=null){ois.close();}
        }
        
    return obj;
    }
     
     
     private MessageResultSet getObetResultSet(byte[] msg) throws IOException, ClassNotFoundException {
        MessageResultSet obj=null;
        ByteArrayInputStream bis=null;
        ObjectInputStream ois=null;
        try {
            bis=new ByteArrayInputStream(msg);
        ois=new ObjectInputStream(bis);
        
        obj=(MessageResultSet)ois.readObject();
        
            
        } finally {
            if (ois!=null){bis.close();}
        if(ois!=null){ois.close();}
        }
        
    return obj;
    }
     
}
