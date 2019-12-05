/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.database.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


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


public class DB {
    
    Connection con;
    public DB(){
    try{
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    }catch(ClassNotFoundException e){
    System.err.println(e);
    }   catch (InstantiationException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    try{
    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/chat","root","");
    }catch(SQLException e){System.err.println(e);}
    }
   public Connection obtenirconnexion(){return con;}
    
}
