
package Messages.Object.Serializable;
import java.io.Serializable;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

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


public class MessageResultSet implements Serializable{
    
 private static final long serialVersionUID = 1L;
   public ResultSet RsInfo, RsMsg;
   public DefaultTableModel model1,model2;
    public MessageResultSet(DefaultTableModel rs1,DefaultTableModel rs2){
    this.model1=rs1;this.model2=rs2;
    }

}


