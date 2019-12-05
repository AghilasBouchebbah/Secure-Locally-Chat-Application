package Messages.Object.Serializable;

import java.io.Serializable;




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






/*
 * public class message
 * 
 * 					Creates an serializable object with contains the message 
 * 					to be exchanged between Client and Server.
 */


public class Message2 implements Serializable{
		
		public static final long serialVersionUID = 1L;
			public byte[] data; 
			
			public Message2(byte[] data){
				this.data = data;
			}
			
			/*
			 *getData method
			 *				returns the byte array.
			 */
			
			public byte[] getData(){
				return data;
			}
			
		}	