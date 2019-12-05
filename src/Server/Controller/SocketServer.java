package Server.Controller;


import Messages.Object.Serializable.Message;
import Messages.Object.Serializable.Message2;
import Messages.Object.Serializable.MessageResultSet;
import Messages.Object.Serializable.MessageSignup;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.table.DefaultTableModel;
import Server.database.models.models;
import Server.View.ServerInterface;

/**
 *****
 * *****
 
                         ****  @author AGHILAS BOUCHEBBAH  *****
                 
                 
       
                 **             *************                       |************|            *
                *  *            |           *                       |            | 
               *    *           |                                   |            |
              *      *          |    *********                      |************|            *
             **********         |    |       *      ********        |            |
            *          *        |            *       ******         |            |
           *            *       **************        ****          **************

 *****
 * *****/



class ServerThread extends Thread {

    public SocketServer server = null;
    public Socket socket = null;
    public int ID = -1;
    public String username = "";
    public ObjectInputStream streamIn = null;
    public ObjectOutputStream streamOut = null;
    public ServerInterface ui;

    private Cipher keyDecipher;
    private Cipher ServerDecryptCipher;
    private Cipher ServerEncryptCipher;
    SecretKey AESKey;
    int i;
    byte[] input;
    static String IV = "AAAAAAAAAAAAAAAA";
   public boolean login=false;
    

    public ServerThread(SocketServer _server, Socket _socket) {
        super();
        server = _server;
        socket = _socket;
        ID = socket.getPort();
        ui = _server.ui;
        System.out.println("serverThred" + socket);
    }

    public void send(Message msgh) {
        try {
            byte[] b=getBytes(msgh);
          
           Message2 msg=new Message2(encryptMessage(b));
           
            streamOut.writeObject(msg);
            streamOut.flush();
           
                System.out.println("le msg est envoyer avec succes pour le client "+msgh.recipient+"  "+msg.getData());
            
        } catch (IOException ex) {
            System.out.println("Exception [SocketClient : send(...)] "+ex);
       
   }    catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
    
    public void sendSignup(MessageSignup msg){
        try {
            System.out.println("le msg avnt cyptage "+msg.toString());
            
           Message2 msg2 = new Message2(encryptMessageSignup(msg));
        
            streamOut.writeObject(msg2);
            streamOut.flush();
            System.out.println("le message a ete envoyer");
          
        } 
        catch (IOException ex) {
            System.out.println("Exception SocketClient send()");
        
    }   catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
    
    public void sendAmisInfo(MessageResultSet Rs){
        
        
        try {
           
           System.out.println("le msg RESULTSET avnt cyptage en byte "+Rs);
          
           
           Message2 msg2 = new Message2(encryptMessageResultSet(Rs));
         
          
            try {
                streamOut.writeObject(msg2);
                
                 streamOut.flush();
                 System.out.println("le message Resultset a ete envoyer");
            } catch (IOException ex) {
                System.out.println("erreur sendResultSet "+ex);
            }
           
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    

    public int getID() {
        return ID;
    }

    @SuppressWarnings("deprecation")

    public void run() {
        ui.jTextArea1.append("\nServer Thread " + ID + " running.");
        while (true) {
            try {

              Message2 msgByte = (Message2) streamIn.readObject();
              
                System.out.println("le message crypté est bien recu");

                if (i == 0) {
                    if (msgByte.getData() != null) {
                        decryptAESKey(msgByte.getData());
                        System.out.println();
                        i++;
                    } else {
                        System.out.println("Error in decrypting AES key in clientThread.run()");
                        System.exit(1);
                    }
                } else if (msgByte.getData() != null) {
                    
                    if(i==2){
                    System.out.println("je suis dans la premiere partie de run");
                    server.handle(ID,decryptMessage(msgByte.getData())); i++;
                    }
                    
                    else if(i>2){
                        try {
                            System.out.println("je suis dans la deuxieme partie de run");
                            server.handle(ID,decryptMessage(msgByte.getData()));
                        } catch (Exception e) {
                            server.handleSignup(ID,decryptMessageSignup(msgByte.getData()));
                        }
                        
                        
                        }
                    }
                    
                

            } catch (Exception ioe) {
                System.out.println(ID + " ERROR reading: " + ioe);
                server.remove(ID);
                stop();
            }
        }
    }

    public void open() throws IOException {
        streamOut = new ObjectOutputStream(socket.getOutputStream());
        streamOut.flush();
        System.err.println("open");
        streamIn = new ObjectInputStream(socket.getInputStream());
    }

    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
        if (streamIn != null) {
            streamIn.close();
        }
        if (streamOut != null) {
            streamOut.close();
        }
    }

    /*
	   * // ====== Receive the encrypted AES key from server and decipher it
	   * 
	   * 
	   * decryptAESKey method
	   * 					will use RSA private key from the public - private key pair to
	   * 					decipher the AES key encrypted using public key and sent by the client.
	   * 
	   * @param byte[] encryptedData
	   * 							The encrypted key as byte array.
	   * 
	   * 
     */
  
       private void decryptAESKey(byte[] encryptedKey) {
        SecretKey key = null;
        PrivateKey privKey = null;
        keyDecipher = null;
        try {
            privKey = readPrivateKeyFromFile("private.key"); 			//  private key
            keyDecipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); 		// initialize the cipher...
            keyDecipher.init(Cipher.DECRYPT_MODE, privKey);
            key = new SecretKeySpec(keyDecipher.doFinal(encryptedKey), "AES");
            System.out.println();
            System.out.println(" AES key after decryption : " + key);
            i = 1;
            AESKey = key;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("exception decrypting the aes key: " + e.getMessage());
        }

    }

    /*
	 * //=========== Decipher/decrypt the encrypted message using AES key =================
	 * 
	 * decryptMessage method.
	 * 						Deciphers the encrypted message received from the client.
	 * 						Takes byte array of the encrypted message as input.
	 * 
	 * 
     */
  

       private Message decryptMessage(byte[] encryptedMessage) throws NoSuchAlgorithmException, BadPaddingException, IOException, ClassNotFoundException,
               IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        ServerDecryptCipher = null;
           System.out.println("la taille du message "+encryptedMessage.length);
           
            ServerDecryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            ServerDecryptCipher.init(Cipher.DECRYPT_MODE, AESKey, new IvParameterSpec(IV.getBytes()));
            System.out.println("le message en byte est "+encryptedMessage);
            byte[] msg = ServerDecryptCipher.doFinal(encryptedMessage);
            Message lmm=getObet(msg);
            
            System.out.println("le message décrypté en objet message est "+lmm);
          
       
       return lmm;}
        
       
       
       private byte[] encryptMessageSignup(MessageSignup s) throws NoSuchAlgorithmException, NoSuchPaddingException, 
							InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, 
											BadPaddingException{
		byte [] obj=getBytesSignup(s);
        ServerEncryptCipher = null;
    	byte[] cipherText = null;
    	ServerEncryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    	
    	ServerEncryptCipher.init(Cipher.ENCRYPT_MODE, AESKey, new IvParameterSpec(IV.getBytes()) );
    	long time3 = System.nanoTime();
    	cipherText = ServerEncryptCipher.doFinal(obj);
          System.out.println("le message crypté en byte est  " + cipherText);
        
    	long time4 = System.nanoTime();
		long totalAES = time4 - time3;
		System.out.println("Time taken by AES Encryption (Nano Seconds) " + totalAES);
   	   return cipherText;
	}
	
       
       
       
       
        private MessageSignup decryptMessageSignup(byte[] encryptedMessage) throws NoSuchAlgorithmException, BadPaddingException, IOException, ClassNotFoundException,
               IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        ServerDecryptCipher = null;
           System.out.println("la taille du message "+encryptedMessage.length);
           
            ServerDecryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            ServerDecryptCipher.init(Cipher.DECRYPT_MODE, AESKey, new IvParameterSpec(IV.getBytes()));
            System.out.println("le message en byte est "+encryptedMessage);
            byte[] msg = ServerDecryptCipher.doFinal(encryptedMessage);
            MessageSignup lmm=getObetSignup(msg);
            
            System.out.println("le message décrypté en objet message est "+lmm);
          
       
       return lmm;}
       
        
        
        public byte [] encryptMessageResultSet(MessageResultSet rs) throws NoSuchAlgorithmException, NoSuchPaddingException, 
					InvalidKeyException, InvalidAlgorithmParameterException, 
                                 IllegalBlockSizeException ,BadPaddingException{
		byte [] obj=getBytesResultSet(rs);
        ServerEncryptCipher = null;
    	byte[] cipherText = null;
    	ServerEncryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    	
    	ServerEncryptCipher.init(Cipher.ENCRYPT_MODE, AESKey, new IvParameterSpec(IV.getBytes()) );
    	long time3 = System.nanoTime();
    	cipherText = ServerEncryptCipher.doFinal(obj);
          System.out.println("le message crypté en byte est  " + cipherText);
        
    	long time4 = System.nanoTime();
		long totalAES = time4 - time3;
		System.out.println("Time taken by AES Encryption (Nano Seconds) " + totalAES);
   	   return cipherText;
        
        }
        
          
    /*
		 * //===========  Encrypted message using AES key =================
		 * 
		 * encryptMessage method
		 * 						Takes the message string as input and encrypts it.
		 * 
		 * 
     */
 
    private byte [] encryptMessage(byte [] ec) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
            BadPaddingException {
           System.out.println("japprete a crypté le msg  "+ ec);
        ServerEncryptCipher = null;
        byte[] cipherText = null;
        ServerEncryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        ServerEncryptCipher.init(Cipher.ENCRYPT_MODE, AESKey, new IvParameterSpec(IV.getBytes()));
        cipherText = ServerEncryptCipher.doFinal(ec);
                System.out.println("la taille du message a envoyer "+cipherText.length);
        return cipherText;
    }

    /*
		 * // ================= Read private Key from the file======================= 
		 * 
		 * readPrivateKeyFromFile method
		 * 								reads the RSA private key from private.key file saved in same directory.
		 * 								the private key is used to decrypt/decipher the AES key sent by Client.
		 * 
		 * 
		 * 
     */
    PrivateKey readPrivateKeyFromFile(String fileName) throws IOException {

        FileInputStream in = new FileInputStream(fileName);
        ObjectInputStream readObj = new ObjectInputStream(new BufferedInputStream(in));

        try {
            BigInteger m = (BigInteger) readObj.readObject();
            BigInteger d = (BigInteger) readObj.readObject();
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, d);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PrivateKey priKey = fact.generatePrivate(keySpec);
            return priKey;
        } catch (Exception e) {
            throw new RuntimeException("Some error in reading private key", e);
        } finally {
            readObj.close();
        }
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
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                return data;
                
                }
    
      public byte[] getBytesResultSet(MessageResultSet m){
                byte[] data=null;
                
        try {
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(bos);
            oos.writeObject(m);
            oos.flush();
            oos.close();
            bos.close();
            data=bos.toByteArray();
        } catch (Exception ex) {
              System.out.println("error getByteresultset "+ex);
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
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                return data;
                
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
    
}





public class SocketServer implements Runnable {
    
    public ServerThread clients[];
    public ServerSocket server = null;
    public Thread       thread = null;
    public int clientCount = 0, port = 13000;
    public ServerInterface ui;
    public models db;
    
    public SocketServer(ServerInterface frame){
       
        clients = new ServerThread[50];
        ui = frame;
        db = new models();
     
        
	try{  
	    server = new ServerSocket(port);
            port = server.getLocalPort();
	    ui.jTextArea1.append("Server demmaré. IP : " + InetAddress.getLocalHost() + ", Port : " + server.getLocalPort());
	    start(); 
        }
	catch(IOException ioe){  
            ui.jTextArea1.append("impossible de lier au : " + port + "\nRetrying"); 
            ui.RetryStart(0);
	}
    }
    
    public SocketServer(ServerInterface frame, int Port){
       
        clients = new ServerThread[50];
        ui = frame;
        port = Port;
        db = new models();
        
	try{  
	    server = new ServerSocket(port);
            port = server.getLocalPort();
	    ui.jTextArea1.append("Server démmaré. IP : " + InetAddress.getLocalHost() + ", Port : " + server.getLocalPort());
	    start(); 
        }
	catch(IOException ioe){  
            ui.jTextArea1.append("\nimpossible de lier " + port + ": " + ioe.getMessage()); 
	}
    }
	
    public void run(){  
	while (thread != null){  
            try{  
		ui.jTextArea1.append("\nEn attente d'un client ..."); 
	        addThread(server.accept()); 
	    }
	    catch(Exception ioe){ 
                ui.jTextArea1.append("\nServer accept error: \n");
                ui.RetryStart(0);
	    }
        }
    }
	
    public void start(){  
    	if (thread == null){  
            thread = new Thread(this); 
	    thread.start();
	}
    }
    
    @SuppressWarnings("deprecation")
    public void stop(){  
        if (thread != null){  
            thread.stop(); 
	    thread = null;
	}
    }
    
    private int findClient(int ID){  
    	for (int i = 0; i < clientCount; i++){
        	if (clients[i].getID() == ID){
                    return i;
                }
	}
	return -1;
    }
	
    public synchronized void handle(int ID, Message msg){  
	if (msg.content.equals(".bye")){
            db.setEtat(0, msg.sender);
            Announce("signout", "SERVER", msg.sender);
            clients[findClient(ID)].send(new Message("signout", "SERVER", "TRUE", msg.sender));
            remove(ID); 
	}
	else{
            if(msg.type.equals("login")){
                //null c'est a dire ce client na pas été deja autentifié
                if(findUserThread(msg.sender) == null){
                    if(db.checkLogin(msg.sender, msg.content)){
                        clients[findClient(ID)].username = msg.sender;
                     try {
                        clients[findClient(ID)].sendSignup(db.getUserInfo(msg.sender));
                        
                        clients[findClient(ID)].send(new Message("login", "SERVER", "TRUE", msg.sender));
                       db.setEtat(1, msg.sender);
                       
                      SendUserList(msg.sender);       
                Announce("newuser", "SERVER", msg.sender);
               
                 
                             int nbrMsg=db.getNbrMessageManquee(msg.sender);
                             int nbrInvit=db.getNbrInvitation(msg.sender);
                clients[findClient(ID)].send(new Message("messageManqués", "SERVER", String.valueOf(nbrMsg), msg.sender));
                  clients[findClient(ID)].send(new Message("nbrInvitation", "SERVER", String.valueOf(nbrInvit), msg.sender));
                
                     
                        } catch (Exception ex) {
                            System.out.println("erreur dans login - senSignup "+ex);;
                        }
                
                        
                    }
                    else{
                        clients[findClient(ID)].send(new Message("login", "SERVER", "FALSE", msg.sender));
                    } 
                }
                else{
                    clients[findClient(ID)].send(new Message("login", "SERVER", "FALSE", msg.sender));
                }
            }
            else if(msg.type.equals("message")){
                if(msg.recipient.equals("All")){
                    Announce("message", msg.sender, msg.content);
                }
                else{
                     
                  
                     ServerThread t=findUserThread(msg.recipient);
                     if(t != null){
                         try{
                            String msgTime = (new Date()).toString();
                  
                  Date d=new Date();
                       db.addMessage(msg, d,0);
                            
                        }
                        catch(Exception ex){}  
                    findUserThread(msg.recipient).send(new Message(msg.type, msg.sender, msg.content, msg.recipient));
                     }else{
                      try{
                            String msgTime = (new Date()).toString();
                  
                  Date d=new Date();
                       db.addMessage(msg, d,1);
                            
                        }
                        catch(Exception ex){}  
                     }
                        
                }
            }
            
            
            else if(msg.type.equals("eleverMessageManque")){db.enleverMsgManq(msg.sender,msg.content);}
            
            
            
            else if(msg.type.equals("getMessageManque")){
                 try {
             
             System.out.println("jappele la fnct denvoyer les message manque pour "+msg.sender);
            String [] amis2=new String[db.getNbrMessageManquee(msg.sender)];
            
                   amis2= db.getAmisMessageManque(msg.sender);
            System.out.println("la taille de tableau "+amis2.length);
            if(amis2!=null){
            System.out.println("la fnct getamis pour la methode SendUserList avec succes"+ amis2[0]);
            for(int i = 0; i < amis2.length; i++){
                //envoyer tout les nom des clients deja connecté a ce nouveau client pour les ajouter a sa liste
                
                String jelaibloquer=db.checkBloque1(msg.sender,amis2[i]);
                String jesuisbloque=db.checkBloque2(msg.sender,amis2[i]);
                System.out.println("je lai bloque= "+jelaibloquer+" et je suis bloque= "+jesuisbloque);
                findUserThread(msg.sender).send(new Message("liste2",jelaibloquer , amis2[i],jesuisbloque ));
            }}
        } catch(Exception ex){
		System.out.println("Exceptionn SendMessageManque "+ex);}
                 
                }
            
            
            
            
            
            
            else if(msg.type.equals("test")){
                clients[findClient(ID)].send(new Message("test", "SERVER", "OK", msg.sender));
                System.out.println("j'envoi la reponce positive pour le test de clien" +msg.sender);
            }
            
            else if(msg.type.equals("upload_req")){
                if(msg.recipient.equals("All")){
                    clients[findClient(ID)].send(new Message("message", "SERVER", "L'envoie du fichier pour tout le monde est interdit", msg.sender));
                }
                else{
                    findUserThread(msg.recipient).send(new Message("upload_req", msg.sender, msg.content, msg.recipient));
                }
            }
            else if(msg.type.equals("upload_res")){
                if(!msg.content.equals("NO")){
                    String IP = findUserThread(msg.sender).socket.getInetAddress().getHostAddress();
                    findUserThread(msg.recipient).send(new Message("upload_res", IP, msg.content, msg.recipient));
                }
                else{
                    findUserThread(msg.recipient).send(new Message("upload_res", msg.sender, msg.content, msg.recipient));
                }
            }
            else if(msg.type.equals("getInfoAmis")){
            
                DefaultTableModel rsInfo,rsMsg;
              
                rsInfo=db.getAmisInfo(msg.sender,msg.content);
                rsMsg=db.getMsg(msg.sender,msg.content);
                  MessageResultSet rs=new MessageResultSet(rsInfo, rsMsg);
                  clients[findClient(ID)].sendAmisInfo(rs);
                  
            
            }else if(msg.type.equals("getUser"))  {
            
                DefaultTableModel rsInfo;
                              DefaultTableModel model=new DefaultTableModel();
                       
model.addColumn("type");
             model.addRow(new Object[]{"getAllUser"});
              
                rsInfo=db.getAllUser(msg.sender);
                
                  MessageResultSet rs=new MessageResultSet(rsInfo,model);
                  clients[findClient(ID)].sendAmisInfo(rs);
                  
            }
            
            
            else if(msg.type.equals("sendInvitation"))  {
            
               
                   if( db.setInvitation(msg.sender,msg.content)){
                    
                        clients[findClient(ID)].send(new Message("sendInvitation", "SERVER", "TRUE", msg.sender));}
                   else{
                 
                        clients[findClient(ID)].send(new Message("sendInvitation", "SERVER", "FALSE", msg.sender));}
                    
                }
            
            else if(msg.type.equals("deleteInvitation"))  {
            
               
                   if( db.deleteInvitation(msg.sender,msg.content)){
                    
                        clients[findClient(ID)].send(new Message("deleteInvitation", "SERVER", "TRUE", msg.sender));}
                   else{
                 
                        clients[findClient(ID)].send(new Message("deleteInvitation", "SERVER", "FALSE", msg.sender));}
                    
                }
            
            
             else if(msg.type.equals("verifyInvitation"))  {
            
               
                   if( db.verifyInvitation(msg.sender,msg.content)){
                    
                        clients[findClient(ID)].send(new Message("verifyInvitation", "SERVER", "TRUE", msg.sender));}
                   else{
                 
                        clients[findClient(ID)].send(new Message("verifyInvitation", "SERVER", "FALSE", msg.sender));}
                    
                }

            else if(msg.type.equals("getInvitation"))  {
            
               
                  DefaultTableModel rsInvit;
                  DefaultTableModel model=new DefaultTableModel();
                       
model.addColumn("type");
             model.addRow(new Object[]{"invitation"});
                rsInvit=db.getInvit(msg.sender);
                   
                MessageResultSet rs=new MessageResultSet(rsInvit,model );
                  clients[findClient(ID)].sendAmisInfo(rs);
                     
                }
            
            
            
            
            
            
            else if(msg.type.equals("accepteInvitation"))  {
            
               
                   if( db.accepteInvitation(msg.sender,msg.content)){
                    
                        clients[findClient(ID)].send(new Message("accepteInvitation", "SERVER", "TRUE", msg.sender));
                        db.deleteInvitation(msg.content,msg.sender);
                        
                        DefaultTableModel rsInvit;
                  DefaultTableModel model=new DefaultTableModel();
                       
model.addColumn("type");
             model.addRow(new Object[]{"invitation"});
                rsInvit=db.getInvit(msg.sender);
                   
                MessageResultSet rs=new MessageResultSet(rsInvit,model );
                  clients[findClient(ID)].sendAmisInfo(rs);
                  
                       SendUserList(msg.sender);
                       
             //Repondre a celui qui a envoyé l'invitation
                       try {
                           
                       
                       findUserThread(msg.content).send(new Message("invitationAccepté", "SERVER", msg.sender, msg.content));
                       SendUserList(msg.content);
                       
                      
                   } catch (Exception e) {
                       }}
                   else{
                 
                        clients[findClient(ID)].send(new Message("accepteInvitation", "SERVER", "FALSE", msg.sender));}
                    
                }
            
            
            
            else if(msg.type.equals("refuserInvitation")){
            if(db.refuserInvitation(msg.sender,msg.content)){
            clients[findClient(ID)].send(new Message("refuserInvitation", "SERVER", "TRUE", msg.sender));
                   
            }else{clients[findClient(ID)].send(new Message("refuserInvitation", "SERVER", "FALSE", msg.sender));}
                   }
            
            
            
            
            else if(msg.type.equals("deleteHistorique"))  {
            
               
                   if( db.deleteHistorique(msg.sender,msg.content)){
                    
                        clients[findClient(ID)].send(new Message("deleteHistorique", "SERVER", "TRUE", msg.sender));}
                   else{
                 
                        clients[findClient(ID)].send(new Message("deleteHistorique", "SERVER", "FALSE", msg.sender));}
                    
                }
	
             else if(msg.type.equals("deleteFriend"))  {
            
               
                   if( db.deleteFriend(msg.sender,msg.content)){
                    
                        clients[findClient(ID)].send(new Message("deleteFriend", "SERVER", "TRUE", msg.sender));
                        SendUserList(msg.sender);
                        
                        try {
                           
                       
                    findUserThread(msg.content).send(new Message("Iam deleted", "SERVER", msg.sender, msg.content));
                 
                SendUserList(msg.content); 
                   } catch (Exception e) {
                       }
                   }
                   else{
                 
                        clients[findClient(ID)].send(new Message("deleteFriend", "SERVER", "FALSE", msg.sender));}
                    
                }
            
            
              else if(msg.type.equals("bloqueFriend"))  {
            
               
                   if( db.bloqueFriend(msg.sender,msg.content)){
                    
                        clients[findClient(ID)].send(new Message("bloqueFriend", "SERVER", "TRUE", msg.sender));
                        SendUserList(msg.sender);
                       try {
                           
                 findUserThread(msg.content).send(new Message("Iam bloqued", "SERVER", msg.sender, msg.content));
                 
                SendUserList(msg.content); 
                       } catch (Exception e) {
                       }
 
                   
                   }
                   else{
                 
                        clients[findClient(ID)].send(new Message("bloqueFriend", "SERVER", "FALSE", msg.sender));}
                    
                }
            
            
            
               else if(msg.type.equals("debloqueFriend"))  {
            
               
                   if( db.debloqueFriend(msg.sender,msg.content)){
                    
                        clients[findClient(ID)].send(new Message("debloqueFriend", "SERVER", "TRUE", msg.sender));
                        try {
                    findUserThread(msg.content).send(new Message("Iam debloqued", "SERVER", msg.sender, msg.content));
                SendUserList(msg.content); 
                          
                       } catch (Exception e) {
                       }
                    
                   }
                   else{
                 
                        clients[findClient(ID)].send(new Message("debloqueFriend", "SERVER", "FALSE", msg.sender));}
                    
                }
            
            
    }
    }
    
     public synchronized void handleSignup(int ID, MessageSignup msg){
         System.out.println("je suis dans handle signup");
     if(msg.prenom.equals("")){ 
         System.out.println("je suis dans handle signup partie update profil");
         
            if( db.updateProfil(clients[findClient(ID)].username,msg)){
            
                        clients[findClient(ID)].send(new Message("updateProfil", "SERVER", "TRUE", msg.nomUtilisateur));
             try {
                            
                      clients[findClient(ID)].sendSignup(db.getUserInfoUpdate(msg.nomUtilisateur));
                        } catch (SQLException ex) {
                            System.out.println("erreur dans login - senSignup" +ex);;
                        }
            }
            else{
        
                        clients[findClient(ID)].send(new Message("updateProfil", "SERVER", "FALSE", msg.nomUtilisateur));
                    }
     }

     else {
                    try {
                if(findUserThread(msg.nomUtilisateur) == null){
                    if(db.userExists(msg.nomUtilisateur)==0){
                      if(db.addUser(msg)){
                        clients[findClient(ID)].send(new Message("signup", "SERVER", "TRUE", msg.nomUtilisateur));
                      
                    }
                    else{
                        clients[findClient(ID)].send(new Message("signup", "SERVER", "FALSE", msg.nomUtilisateur));
                    }
                
                }
            }{
                        clients[findClient(ID)].send(new Message("signup", "SERVER", "FALSE", msg.nomUtilisateur));
                    }
     }
 catch (Exception e) {System.out.println("problemme handleSignup addUser "+e);
     clients[findClient(ID)].send(new Message("signup", "SERVER", "FALSE", msg.nomUtilisateur));}
}}
    
    public synchronized void Announce(String type, String sender, String content){
        try {
            Message msg = new Message(type, sender, content, "All");
            
             String [] amis2=new String[db.getNbrAmis(content)];
                   amis2= db.getAmis(content);
            
            System.out.println("la fnct getamis pour la methode ANNOUNCE avec succes");
           
            if(amis2!=null){
               for(int i = 0; i < amis2.length; i++){
                   
                //envoyer tout les nom des clients deja connecté a ce nouveau client pour les ajouter a sa liste
                
                ServerThread t=findUserThread(amis2[i]);
                if(t!=null){
              t.send(new Message(type,"SERVER" , content,amis2[i] ));}
            }
                       
                    }  
        } catch (Exception ex) {
            System.out.println("Announce error "+ex);;
        }
    }
    
    public  synchronized void  SendUserList(String toWhom){
        try {
           
            
             System.out.println("jappele la fnct afficherEnLigne pour "+toWhom);
            String [] amis2=new String[db.getNbrAmis(toWhom)];
                   amis2= db.getAmis(toWhom);
            System.out.println("la taille de tableau "+amis2.length);
            if(amis2!=null){
            System.out.println("la fnct getamis pour la methode SendUserList avec succes"+ amis2[0]);
            for(int i = 0; i < amis2.length; i++){
                //envoyer tout les nom des clients deja connecté a ce nouveau client pour les ajouter a sa liste
                
                String jelaibloquer=db.checkBloque1(toWhom,amis2[i]);
                String jesuisbloque=db.checkBloque2(toWhom,amis2[i]);
                System.out.println("je lai bloque= "+jelaibloquer+" et je suis bloque= "+jesuisbloque);
                findUserThread(toWhom).send(new Message("liste1",jelaibloquer , amis2[i],jesuisbloque ));
            }}
        } catch(Exception ex){
		System.out.println("ExceptionnSendUserList "+ex);}
    }
    
    public ServerThread findUserThread(String usr){
        for(int i = 0; i < clientCount; i++){
            if(clients[i].username.equals(usr)){
                return clients[i];
            }
        }
        return null;
    }
	
    @SuppressWarnings("deprecation")
    
    public synchronized void remove(int ID){  
    int pos = findClient(ID);
        if (pos >= 0){  
            ServerThread toTerminate = clients[pos];
            ui.jTextArea1.append("\nRemoving client thread " + ID + " at " + pos);
	    if (pos < clientCount-1){
                for (int i = pos+1; i < clientCount; i++){
                    clients[i-1] = clients[i];
	        }
	    }
	    clientCount--;
	    try{  
	      	toTerminate.close(); 
	    }
	    catch(IOException ioe){  
	      	ui.jTextArea1.append("\nerreur fermeture du thread: " + ioe); 
	    }
	    toTerminate.stop(); 
	}
    }
    
    private void addThread(Socket socket){  
        System.out.println(socket);
	if (clientCount < clients.length){  
            ui.jTextArea1.append("\nClient accepté: " + socket);
	    clients[clientCount] = new ServerThread(this, socket);
	    try{  
	      	clients[clientCount].open(); 
	        clients[clientCount].start();  
	        clientCount++; 
	    }
	    catch(IOException ioe){  
	      	ui.jTextArea1.append("\nerreur ouverture du thread: " + ioe); 
	    } 
	}
	else{
            ui.jTextArea1.append("\nClient refusé: maximum " + clients.length + " attein.");
	}
    }



}
