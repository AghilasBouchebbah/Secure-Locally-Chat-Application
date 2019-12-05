
package Messages.Object.Serializable;

import java.io.Serializable;

/**
 *****
 * *****
 
                         ****  @author  AGHILAS BOUCHEBBAH  *****
                 
                 
       
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

public class MessageSignup implements Serializable{
    
    private static final long serialVersionUID = 1L;
    public String email,nom,prenom,secteur,nomUtilisateur,motDePasse,etat;
    public byte[] image;
    
     public MessageSignup(String nom, String prenom, String secteur,String email, String nomUtilisateur,String motdepass,byte[] image){
        this.email = email; this.nom = nom; this.prenom = prenom; this.secteur = secteur;this.nomUtilisateur=nomUtilisateur;
        this.motDePasse=motdepass;this.image=image;
    }
     
     @Override
    public String toString(){
        return "{nom='"+nom+"', prenom='"+prenom+"', secteur='"+secteur+"', nomUtili='"+nomUtilisateur+"'}";
    }
}
