
package Server.database.models;
import Messages.Object.Serializable.Message;
import Messages.Object.Serializable.MessageSignup;
import java.sql.PreparedStatement;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import Client.Views.MainInterface;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import java.util.Date;

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


public class models {
    
Statement stm;
String req;
   Connection con;
   java.sql.PreparedStatement pst;
    public models() {
      DB db=new DB();
     con=db.obtenirconnexion();  
        
    }
    
    
    
  
             

   
    
   
    public int userExists(String username){
        int exist=0;
        
         try{
              System.out.println("avant le lancement de la requette ");
              stm=con.createStatement();
               
               System.out.println("avant le lancement de la requette "+username);
             req="select * from user where nomUtilisateur='"+username+"'";
              ResultSet Rs =stm.executeQuery(req);
              System.out.println("le resultat de la requete est ");
          
               while(Rs.next()){    if(Rs.getString("nomUtilisateur")!=null){
                        exist= 1;
                  
                   }else exist= 0;} 
           
         }
        catch(Exception ex){
            System.out.println("Database exception : userExists()"+ex);
            
        }
         return exist;
    }
    
    public boolean checkLogin(String username, String password){
         boolean exist=false;
        System.out.println("je suis dans debut de check login");
        if(userExists(username)==0){ return false; }else{
        System.out.println("l'utilisateur exist, je rentre dans verification usr mpds");
      
        try{
                  
            
            
               
             req="select * from user where nomUtilisateur='"+username+"'";
              ResultSet Rs =stm.executeQuery(req);
           while(Rs.next()){
                   if(Rs.getString("nomUtilisateur").equals(username) && Rs.getString("MotDePasse").equals(password) ){
                       System.out.println("motde passe exist "+Rs.getString("MotDePasse")); 
                       exist= true;
                    }
           }
            
                
            
            System.out.println("Hippie");
        }
        catch(Exception ex){
            System.out.println("Database exception : userExists()"+ex);
        }}
    return exist;
    }
    
    public boolean addUser(MessageSignup msg){
        
        try {
            
        
                String req="insert into user(nomUtilisateur,motDePasse,nom,prenom,secteur,email,image)  values(?,?,?,?,?,?,?)";
       pst=con.prepareStatement(req);
       
       pst.setString(1,msg.nomUtilisateur );
       pst.setString(2,msg.motDePasse );
       pst.setString(3,msg.nom);
       pst.setString(4,msg.prenom );
       pst.setString(5,msg.secteur );
       pst.setString(6,msg.email);
       pst.setBytes(7,msg.image);
       
       pst.execute();
       pst.close();
      
            System.out.println("le requette dajoout est fini avec succes");
      return true;
        } catch(Exception ex){
		System.out.println("ExceptionOnAddUser "+ex);
	   return false;
        }
	}
    
    public void setEtat(int e,String user){
   try {
       String etat;
       stm=con.createStatement();
        if (e==1){ 
            etat="enligne";
          req=  "update user set etat='"+etat+"' where nomUtilisateur='"+user+"'";
        
        stm.executeUpdate(req);
            System.out.println("la requete d'etat à 1 executé");
        }
        
        if(e==0){etat="horligne";
        System.out.println("la requete d'etat à 0 executé");    
        req=  "update user set etat='"+etat+"' where nomUtilisateur='"+user+"'";
        
        stm.executeUpdate(req);     }
	  
        } catch(Exception ex){
		System.out.println("ExceptionOnInsert" +ex);
	   }
	
    }
    
    
    public String[] afficherEnLigne(String user ) throws SQLException{
    String username=user;
   int nbramis=getNbrAmisEnLigne(username);
    String[] amis=new String[nbramis];
   if(nbramis==0){return null;}else{
             stm=con.createStatement();
               
              
             req="select amisDe from user u,listeamis l where nomUser='"+username+"' and etat='enligne' and u.nomUtilisateur=l.amisDe" ;
              ResultSet Rs =stm.executeQuery(req);
              System.out.println("la requette afficherenligne est executer ");
          
                    int k=0;
               while(Rs.next()){ amis[k]=Rs.getString("amisDe"); k++;}
                      
            System.out.println("le resultat de la requete "+amis[0]);
                       
          return amis;}
           
    }
    
    public String[] getAmis(String username) throws SQLException{
    
    String[] amis=new String[getNbrAmis(username)];
      
           
              stm=con.createStatement();
              
            req="select amisDe from listeamis where nomUser='"+username+"' " ;
              ResultSet Rs =stm.executeQuery(req);
              System.out.println("la requette est executée");
              int i=0;int j=0;
               while(Rs.next()){ amis[i]=Rs.getString("amisDe"); i++; }
                       for (int k = 0; k < amis.length; k++) {
            System.out.println("le resultat de la requete "+amis[k]);}
                      
                       
          return amis;
          
        
      
    }
    
    public boolean getEtat(String user) throws SQLException{
        stm=con.createStatement();
        String etat=null;
            
          req=  "select etat from user where nomUtilisateur='"+user+"'";
        
        ResultSet Rs =stm.executeQuery(req);
         while(Rs.next()){etat=Rs.getString("etat");}
                        
                  
                   
    if(etat.equals(etat)){return true;}else{return false;}
    } 
 
    
    public int getNbrAmis(String username) throws SQLException{
        int nbrAmis=0;
     stm=con.createStatement();
        
            req="select amisDe from listeamis where nomUser='"+username+"' ";
         
        ResultSet Rs =stm.executeQuery(req);
         while(Rs.next()){nbrAmis++;}
         return nbrAmis;
    }
    
    
    public int getNbrAmisEnLigne(String username) throws SQLException{
        int nbrAmis=0;
     stm=con.createStatement();
        
           req="select amisDe from user u,listeamis l where nomUser='"+username+"' and etat='enligne' and u.nomUtilisateur=l.amisDe" ;
              ResultSet Rs =stm.executeQuery(req);
              
         
               while(Rs.next()){nbrAmis++; }
               System.out.println("le resultat de la requete getNbrAmisEnLigne est "+nbrAmis);
         
         return nbrAmis;
    }
    
    
      public MessageSignup getUserInfo(String username) throws SQLException{
    stm=con.createStatement();
        MessageSignup msg=null;
            req="select * from user where nomUtilisateur='"+username+"'";
         
        ResultSet Rs =stm.executeQuery(req);
        
        while (Rs.next()) {
msg=new MessageSignup(Rs.getString("nom"),Rs.getString("prenom"),Rs.getString("secteur") , Rs.getString("email"), Rs.getString("nomUtilisateur"),Rs.getString("motDePasse") , Rs.getBytes("image"));
                
            
        }
    return msg;
    
    
    }
    
      
      public MessageSignup getUserInfoUpdate(String username) throws SQLException{
    stm=con.createStatement();
        MessageSignup msg=null;
            req="select secteur,email,motDePasse,image from user where nomUtilisateur='"+username+"'";
         
        ResultSet Rs =stm.executeQuery(req);
        
        while (Rs.next()) {
msg=new MessageSignup("","",Rs.getString("secteur") , Rs.getString("email"), "",Rs.getString("motDePasse") , Rs.getBytes("image"));
                
            
        }
    return msg;
    
    
    }
      
      
      
      public DefaultTableModel getAmisInfo(String username, String ami){
      DefaultTableModel model=new DefaultTableModel();
                       
model.addColumn("nom");
model.addColumn("prenom");
model.addColumn("secteur");
model.addColumn("email");
model.addColumn("etat");
model.addColumn("image");
    try {
        stm=con.createStatement();
        
        
        req="select nom,prenom,secteur,email,etat,image from user u,listeamis l where nomUser='"+username+"' and amisDE='"+ami+"' and u.nomUtilisateur=l.amisDe" ;
        ResultSet Rs =stm.executeQuery(req);
       while(Rs.next()){model.addRow(new Object[]{Rs.getString("nom"),Rs.getString("prenom"),Rs.getString("secteur"),
       Rs.getString("email"),Rs.getString("etat"),Rs.getBytes("image")});}
              
        return model;
        
    } catch (SQLException ex) {
       
               System.out.println("erreur getAmisInfo "+ex);
        return null;
    }
          
      }
      
      
      
      public void addMessage(Message msg, Date d,int m){
        String emeteur,recepteur,contenue;
        emeteur=msg.sender;
        recepteur=msg.recipient;
        contenue=msg.content;
        
        System.out.println("je me trouve dans la fnct addmessage");
        try {
            stm=con.createStatement();
            req=  "insert into historique(contenue,emeteur,recepteur,time,manque)VALUES('"+
                contenue+"','"+emeteur+"','"+recepteur+"',NOW(),'"+m+"')";
        
        stm.executeUpdate(req);
            System.out.println("la requette d'ajout du message a lhistorique effectué");
          
	   } 
           catch(Exception ex){
		System.out.println("Exceptionnel addMessage "+ex);
	   }
	}
      
      
      
      public boolean checkDeleteHistorique(String supprimeur,String echangeAvec){
      
         try {
               stm=con.createStatement();
               String req="select * from messagesupp where supprimeur='"+supprimeur+"' and echangeAvec='"+echangeAvec+"' ";
            ResultSet RS=   stm.executeQuery(req);
               
                while(RS.next()){    if(RS.getString("Supprimeur")!=null){
                        return true;
                  
                   }else {return false;}} 
       
           } catch (Exception e) {
               System.out.println("problemme dans checkDeleteHistorique "+e);
               return false;
           }
       return false;
      }
      
      
       public DefaultTableModel getMsg(String username, String ami){
           String reqq=null;Date d=null;
           if(checkDeleteHistorique(username,ami)){
               
               try {
                   stm=con.createStatement();
                ResultSet rs=   stm.executeQuery("select time from messagesupp where supprimeur='"+username+"' and echangeAvec='"+ami+"'  ");
                
                while (rs.next()){d=rs.getTimestamp("time");}
                
             reqq="select * from historique where emeteur='"+username+"' and recepteur='"+ami+"' and time>'"+d+"' or emeteur='"+ami+"' and recepteur='"+username+"' and time>'"+d+"' order by time";
               } catch (Exception e) {
                   System.out.println("erreur getMsg GetTime "+e);
               }
               
               }
           else{reqq="select * from historique where emeteur='"+username+"' and recepteur='"+ami+"' or emeteur='"+ami+"' and recepteur='"+username+"' order by time";}
           
         DefaultTableModel model=new DefaultTableModel();
                       
model.addColumn("emeteur");
model.addColumn("recepteur");
model.addColumn("contenue");
model.addColumn("time");

           try{
    
             stm=con.createStatement();
               
                ResultSet Rs =stm.executeQuery(reqq);
              while(Rs.next()){model.addRow(new Object[]{Rs.getString("emeteur"),Rs.getString("recepteur"),Rs.getString("contenue"),Rs.getTimestamp("time")});}
              
             return model;
             
}   catch (SQLException ex) {
               System.out.println("erreur getMsg "+ex);
               return null;
               
    }
       }
      
       
       public DefaultTableModel getAllUser(String username){
           DefaultTableModel model=new DefaultTableModel();
                       
model.addColumn("nom");
model.addColumn("prenom");
model.addColumn("secteur");
model.addColumn("email");
model.addColumn("nomUtilisateur");
model.addColumn("image");
    try {
        stm=con.createStatement();
        
    req="select u.nom,u.prenom,u.secteur,u.email,u.nomUtilisateur,u.image from user u where u.nomUtilisateur not in "
            + "(select nomUser from listeamis where amisDe='"+username+"') and u.nomUtilisateur not in('"+username+"') "
            + "and u.nomUtilisateur not in (select demandeur from invitation where recepteur='"+username+"')  " ;
        ResultSet Rs =stm.executeQuery(req);
       while(Rs.next()){model.addRow(new Object[]{Rs.getString("nom"),Rs.getString("prenom"),Rs.getString("secteur"),
       Rs.getString("email"), Rs.getString("nomUtilisateur"),Rs.getBytes("image")});}
              
        return model;
        
    } catch (SQLException ex) {
       
               System.out.println("erreur getUserAll "+ex);
        return null;
    }
           
       }
    
       
       
       public boolean setInvitation(String username, String utilisateur){
       
           try {
               stm=con.createStatement();
               String req="insert into invitation values ('"+username+"','"+utilisateur+"')";
               stm.executeUpdate(req);
               
               return true;
       
           } catch (Exception e) {
               System.out.println("problemme dans setInvitation "+e);
               return false;
           }
       }
       
       
       
       public boolean updateProfil (String username,MessageSignup msg){
       String req;
           try {
            
        if(msg.nom.equals("sansImage")){
            stm=con.createStatement();
       req ="update user set secteur='"+msg.secteur+"',email='"+msg.email+"',motDePasse='"+msg.motDePasse+"' where nomUtilisateur='"+username+"' ";
        stm.executeUpdate(req);
   
       return true;
       
}else{
               
      req="update user set secteur=?,email=?,motDePasse=?,image=? where nomUtilisateur=? ";}

       pst=con.prepareStatement(req);
       
       pst.setString(1,msg.secteur );
       pst.setString(2,msg.email );
       pst.setString(3,msg.motDePasse);
       pst.setBytes(4,msg.image );
       pst.setString(5,username );
       
       
       pst.execute();
       pst.close();
       
       return true;
      
         } catch (Exception e) {
               System.out.println("probleme dans updateProfil de database "+e);
               return false;
           }
       
       }
       
       
      public boolean verifyInvitation(String username, String utilisateur){
     Boolean b=null;
          try {
              stm=con.createStatement();
              req="select count(*) nbr from invitation where demandeur='"+username+"' and recepteur='"+utilisateur+"' ";
             ResultSet Rs= stm.executeQuery(req);
              while(Rs.next()){if(Rs.getInt("nbr")>0  ){ b=true;}else{b=false;}}
              
              System.out.println("verification invitation avec succe "+b);
              
          } catch (Exception e) {
              System.out.println("erreur verifyInvitation "+e);
              return false;
          }
      return b;
      }

      
      
      public boolean deleteInvitation(String username, String utilisateur){
       
           try {
               stm=con.createStatement();
               String req="delete from invitation where demandeur='"+username+"' and recepteur='"+utilisateur+"' ";
               stm.executeUpdate(req);
               
               return true;
       
           } catch (Exception e) {
               System.out.println("problemme dans setInvitation "+e);
               return false;
           }
       }
      
 
      
      public DefaultTableModel getInvit(String username){
          
         DefaultTableModel model=new DefaultTableModel();
                       
model.addColumn("demandeur");
model.addColumn("nom");
model.addColumn("prenom");
model.addColumn("secteur");
model.addColumn("email");
model.addColumn("image");


           try{
    
             stm=con.createStatement();
               
              
             req="select i.demandeur,u.nom,u.prenom,u.secteur,u.email,u.image from invitation i,user u where i.recepteur='"+username+"' and i.demandeur=u.nomUtilisateur ";
              ResultSet Rs =stm.executeQuery(req);
              
              while(Rs.next()){model.addRow(new Object[]{Rs.getString("demandeur"),Rs.getString("nom"),Rs.getString("prenom"),Rs.getString("secteur"),Rs.getString("email"),Rs.getBytes("image")});}
               System.out.println("le modele invitation"+ model); 
             return model;
             
}   catch (SQLException ex) {
               System.out.println("erreur getInvitatoin "+ex);
               return null;
               
    }
       }
       
      
       public boolean accepteInvitation(String username, String utilisateur){
       
           try {
               stm=con.createStatement();
               String req1="insert into listeAmis(nomUser,amisDe) values ('"+username+"','"+utilisateur+"')";
               String req2="insert into listeAmis (nomUser,amisDe) values ('"+utilisateur+"','"+username+"')";
               stm.executeUpdate(req1);
                stm.executeUpdate(req2);
               return true;
       
           } catch (Exception e) {
               System.out.println("problemme dans accepteInvitation "+e);
               return false;
           }
       }
       
       
      
        
      public boolean  deleteHistorique(String supprimeur, String echangeAvec){
       String reqq;
           try {
               if(checkDeleteHistorique(supprimeur, echangeAvec)){
               reqq="update messagesupp set time=NOW() where supprimeur='"+supprimeur+"' and echangeAvec='"+echangeAvec+"' ";
               
               }else{
               reqq="insert into messageSupp (supprimeur,echangeAvec,time)values('"+supprimeur+"','"+echangeAvec+"',NOW()) ";
              
               }
               
               stm=con.createStatement();
               stm.executeUpdate(reqq);
               
               return true;
       
           } catch (Exception e) {
               System.out.println("problemme dans deleteHistorique "+e);
               return false;
           }
       }
      
      
      public boolean  deleteFriend(String username, String ami){
       String reqq1,reqq2;
           try {
              
               reqq1="delete from listeamis where nomUser='"+username+"' and amisDe='"+ami+"'";
               reqq2="delete from listeamis where nomUser='"+ami+"' and amisDe='"+username+"'";
               
             
               
               stm=con.createStatement();
               stm.executeUpdate(reqq1); stm.executeUpdate(reqq2);
               
               return true;
       
           } catch (Exception e) {
               System.out.println("problemme dans deleteFriend "+e);
               return false;
           }
       }
      
      
      
       public boolean  bloqueFriend(String username, String ami){
       String reqq1,reqq2;
           try {
              
            reqq2="update listeamis  set bloque = 1 where nomUser='"+username+"' and amisDe='"+ami+"'";
               
             
               
               stm=con.createStatement();
               stm.executeUpdate(reqq2);
               
               return true;
       
           } catch (Exception e) {
               System.out.println("problemme dans bloqueFriend "+e);
               return false;
           }
       }
      
       
       public String checkBloque1(String username,String utilisateur){
       
           try {
               
               String req="select bloque from listeamis where nomUser='"+username+"' and amisDe='"+utilisateur+"' ";
               
               
               stm=con.createStatement();
               ResultSet Rs=stm.executeQuery(req);
               
               while(Rs.next()){
                               if(Rs.getBoolean("bloque")){return "1";}else{return "0";}
               
               }
           } catch (Exception e) {
               
               System.out.println("Erreur dans checkBloque1 "+e);
           
           }
           return "0";
       }

       
       public String checkBloque2(String username,String utilisateur){
       
           try {
               
               String req="select bloque from listeamis where nomUser='"+utilisateur+"' and amisDe='"+username+"' ";
               
               
               stm=con.createStatement();
               ResultSet Rs=stm.executeQuery(req);
               
               while(Rs.next()){
                               if(Rs.getBoolean("bloque")){return "1";}else{return "0";}
               
               }
           } catch (Exception e) {
               
               System.out.println("Erreur dans checkBloque2 "+e);
           
           }
           return "0";
       }
       
       
        public boolean  debloqueFriend(String username, String ami){
       String reqq1,reqq2;
           try {
              
            reqq2="update listeamis  set bloque = 0 where nomUser='"+username+"' and amisDe='"+ami+"'";
               
             
               
               stm=con.createStatement();
               stm.executeUpdate(reqq2);
               
               return true;
       
           } catch (Exception e) {
               System.out.println("problemme dans debloqueFriend "+e);
               return false;
           }
       }
 
        
        
      public int  getNbrMessageManquee(String username) throws SQLException{
         int nbrMsgManq=0;
         
     stm=con.createStatement();
        
           req="select emeteur from historique where recepteur='"+username+"' and manque=1 group by emeteur" ;
              ResultSet Rs =stm.executeQuery(req);
              
         
               while(Rs.next()){nbrMsgManq++; }
             
         
         return nbrMsgManq;
}
      
      public void enleverMsgManq(String username,String ami){
      
          try {
               stm=con.createStatement();
        
           req="update historique set manque=0 where recepteur='"+username+"' and emeteur ='"+ami+"' " ;
              stm.executeUpdate(req);
          } catch (Exception e) {
              System.out.println("erreur dans enleverMsgManque");
          
          }
      }
      
      
      
     
public String[] getAmisMessageManque(String username) throws SQLException{
    
    String[] amis=new String[getNbrMessageManquee(username)];
      
           
              stm=con.createStatement();
              
            req="select emeteur from historique where recepteur='"+username+"' and manque=1 group by emeteur " ;
              ResultSet Rs =stm.executeQuery(req);
              int i=0;int j=0;
               while(Rs.next()){ amis[i]=Rs.getString("emeteur"); i++; }
                       for (int k = 0; k < amis.length; k++) {
                       }
                       
          return amis;
          
    
}


  public int  getNbrInvitation(String username) throws SQLException{
         int nbrInvitation=0;
         
     stm=con.createStatement();
        
           req="select demandeur from invitation where recepteur='"+username+"' " ;
              ResultSet Rs =stm.executeQuery(req);
              
         
               while(Rs.next()){nbrInvitation++; }
             
         
         return nbrInvitation;
}

  
  public boolean refuserInvitation(String username , String utilisateur){
  
   try {
               stm=con.createStatement();
               String req="delete from invitation where demandeur='"+utilisateur+"' and recepteur='"+username+"' ";
               stm.executeUpdate(req);
               
               return true;
       
           } catch (Exception e) {
               System.out.println("problemme dans RefuserInvitation "+e);
               return false;
           }
  }
}