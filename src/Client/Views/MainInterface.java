package Client.Views;

import Messages.Object.Serializable.Message;
import Client.Controller.SocketClient;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import oracle.jrockit.jfr.JFR;


import Messages.Object.Serializable.MessageSignup;
import com.sun.java.swing.plaf.motif.MotifBorders;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

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

public class MainInterface extends javax.swing.JFrame {

    public SocketClient client;
    public int port;
    public String serverAddr, username, password;
    public Thread clientThread;
    public DefaultListModel model,model5;
    public File file;
    public String amisSelectionné;
    byte[] photo=null;
    String filename=null;
    String utilisateur=null;
 
    
    public MainInterface() {
        initComponents();
        this.setTitle("Messenger");
      
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("443.png")));
     
  
        serverAddr = "localhost"; port = 13000;
        

       
        this.addWindowListener(new WindowListener() {

            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosing(WindowEvent e) { try{ client.send(new Message("message", username, ".bye", "SERVER")); clientThread.stop();  }catch(Exception ex){} }
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
        
    }
    
    public boolean isWin32(){
        return System.getProperty("os.name").startsWith("Windows");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMenu = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jPanelInfoPerso = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTextFieldSecteur = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jPasswordField2 = new javax.swing.JPasswordField();
        jTextFieldImage = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jLabePrenom = new javax.swing.JLabel();
        jLabelNom = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanelAjoutAmis = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabelphoto = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanelMessage = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        Lstatut = new javax.swing.JLabel();
        Lemail = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jAppel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        jLabelnameAmi = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jPanelInvitationRecu = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabel17 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Lstatut1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanelAutentif = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Messagerie");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelMenu.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelMenu.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 280, 10));

        jPanelInfoPerso.setBackground(java.awt.Color.white);
        jPanelInfoPerso.setLayout(null);

        jPanel6.setBackground(java.awt.Color.white);
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Information personnel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 12), java.awt.Color.white)); // NOI18N
        jPanel6.setForeground(java.awt.Color.white);
        jPanel6.setOpaque(false);
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldSecteur.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldSecteur.setForeground(java.awt.Color.white);
        jTextFieldSecteur.setToolTipText("");
        jTextFieldSecteur.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Secteur", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Traditional Arabic", 3, 17), java.awt.Color.white)); // NOI18N
        jTextFieldSecteur.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextFieldSecteur.setOpaque(false);
        jTextFieldSecteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSecteurActionPerformed(evt);
            }
        });
        jPanel6.add(jTextFieldSecteur, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 220, -1));

        jTextFieldEmail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldEmail.setForeground(java.awt.Color.white);
        jTextFieldEmail.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Email", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Traditional Arabic", 3, 16), java.awt.Color.white)); // NOI18N
        jTextFieldEmail.setCaretColor(java.awt.Color.white);
        jTextFieldEmail.setOpaque(false);
        jTextFieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmailActionPerformed(evt);
            }
        });
        jPanel6.add(jTextFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 220, 50));

        jButton10.setBackground(java.awt.Color.white);
        jButton10.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jButton10.setText("Enregistrer");
        jButton10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 430, 100, 30));

        jPasswordField2.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField2.setAutoscrolls(false);
        jPasswordField2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mot de passe", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Traditional Arabic", 3, 16), java.awt.Color.white)); // NOI18N
        jPasswordField2.setCaretColor(java.awt.Color.white);
        jPasswordField2.setOpaque(false);
        jPasswordField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField2ActionPerformed(evt);
            }
        });
        jPanel6.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 220, -1));

        jTextFieldImage.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldImage.setForeground(java.awt.Color.white);
        jTextFieldImage.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Photo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Traditional Arabic", 3, 16), java.awt.Color.white)); // NOI18N
        jTextFieldImage.setOpaque(false);
        jPanel6.add(jTextFieldImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 220, -1));

        jButton16.setBackground(new java.awt.Color(255, 0, 51));
        jButton16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton16.setText("...");
        jButton16.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, 30, 35));

        jLabePrenom.setBackground(new java.awt.Color(252, 252, 252));
        jLabePrenom.setFont(new java.awt.Font("Meiryo UI", 3, 14)); // NOI18N
        jLabePrenom.setForeground(java.awt.Color.white);
        jLabePrenom.setText("    dfgfdgggggggg");
        jPanel6.add(jLabePrenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 0, 10));

        jLabelNom.setBackground(new java.awt.Color(252, 252, 252));
        jLabelNom.setFont(new java.awt.Font("Meiryo UI", 3, 14)); // NOI18N
        jLabelNom.setForeground(java.awt.Color.white);
        jLabelNom.setText("    fffffffffffffffffffffffff");
        jPanel6.add(jLabelNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 130, 0, 20));

        jLabel16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 180, 130));

        jPanelInfoPerso.add(jPanel6);
        jPanel6.setBounds(50, 40, 410, 480);

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/action-2277292_1280.jpg"))); // NOI18N
        jPanelInfoPerso.add(jLabel22);
        jLabel22.setBounds(0, 0, 520, 630);

        jPanelMenu.add(jPanelInfoPerso, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 520, 630));

        jPanelAjoutAmis.setBackground(java.awt.Color.white);
        jPanelAjoutAmis.setLayout(null);

        jButton11.setBackground(java.awt.Color.white);
        jButton11.setText("Envoyer invitation");
        jButton11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton11.setEnabled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanelAjoutAmis.add(jButton11);
        jButton11.setBounds(100, 530, 130, 30);

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Supprimer invitation");
        jButton12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton12.setEnabled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanelAjoutAmis.add(jButton12);
        jButton12.setBounds(290, 530, 130, 30);

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        jTable2.setRowHeight(25);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);

        jPanelAjoutAmis.add(jScrollPane4);
        jScrollPane4.setBounds(20, 300, 480, 160);

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 255));
        jLabel19.setText("      Invitation Envoyée");
        jLabel19.setToolTipText("");
        jLabel19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel19.setOpaque(true);
        jPanelAjoutAmis.add(jLabel19);
        jLabel19.setBounds(160, 500, 0, 0);

        jLabel23.setFont(new java.awt.Font("Traditional Arabic", 3, 28)); // NOI18N
        jLabel23.setForeground(java.awt.Color.white);
        jLabel23.setText(" Ajout d'Amis");
        jPanelAjoutAmis.add(jLabel23);
        jLabel23.setBounds(160, 240, 270, 60);
        jPanelAjoutAmis.add(jSeparator4);
        jSeparator4.setBounds(170, 290, 160, 10);
        jPanelAjoutAmis.add(jLabelphoto);
        jLabelphoto.setBounds(10, 10, 180, 130);

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel36.setForeground(java.awt.Color.white);
        jLabel36.setText("Email :");
        jPanelAjoutAmis.add(jLabel36);
        jLabel36.setBounds(220, 110, 130, 30);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel37.setForeground(java.awt.Color.white);
        jPanelAjoutAmis.add(jLabel37);
        jLabel37.setBounds(270, 110, 160, 30);

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel38.setForeground(java.awt.Color.white);
        jPanelAjoutAmis.add(jLabel38);
        jLabel38.setBounds(260, 10, 150, 30);

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel39.setForeground(java.awt.Color.white);
        jLabel39.setText("Secteur :");
        jPanelAjoutAmis.add(jLabel39);
        jLabel39.setBounds(220, 80, 130, 20);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel40.setForeground(java.awt.Color.white);
        jPanelAjoutAmis.add(jLabel40);
        jLabel40.setBounds(280, 40, 130, 30);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel41.setForeground(java.awt.Color.white);
        jPanelAjoutAmis.add(jLabel41);
        jLabel41.setBounds(280, 80, 170, 20);

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel42.setForeground(java.awt.Color.white);
        jLabel42.setText("Prenom :");
        jPanelAjoutAmis.add(jLabel42);
        jLabel42.setBounds(220, 40, 60, 30);

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel43.setForeground(java.awt.Color.white);
        jLabel43.setText("Nom :");
        jPanelAjoutAmis.add(jLabel43);
        jLabel43.setBounds(220, 10, 40, 30);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/action-2277292_1280.jpg"))); // NOI18N
        jPanelAjoutAmis.add(jLabel18);
        jLabel18.setBounds(0, 0, 520, 630);

        jPanelMenu.add(jPanelAjoutAmis, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 520, 630));

        jPanelMessage.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMessage.setLayout(null);

        jButton4.setBackground(java.awt.Color.white);
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("Envoyer");
        jButton4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanelMessage.add(jButton4);
        jButton4.setBounds(410, 550, 80, 30);

        jTextField4.setFont(new java.awt.Font("Arial Unicode MS", 0, 12)); // NOI18N
        jTextField4.setToolTipText("");
        jTextField4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelMessage.add(jTextField4);
        jTextField4.setBounds(70, 550, 320, 30);

        jTextField5.setFont(new java.awt.Font("Arial Unicode MS", 0, 11)); // NOI18N
        jTextField5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanelMessage.add(jTextField5);
        jTextField5.setBounds(70, 590, 290, 30);

        jButton5.setBackground(java.awt.Color.white);
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setText("...");
        jButton5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanelMessage.add(jButton5);
        jButton5.setBounds(360, 590, 30, 30);

        jButton6.setBackground(java.awt.Color.white);
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setText("Envoyer");
        jButton6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton6.setEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanelMessage.add(jButton6);
        jButton6.setBounds(410, 590, 80, 30);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(246, 246, 246));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jScrollPane1.setViewportView(jTextArea1);

        jPanelMessage.add(jScrollPane1);
        jScrollPane1.setBounds(10, 200, 490, 330);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/icons8_Male_User_100px_2.png"))); // NOI18N
        jPanelMessage.add(jLabel7);
        jLabel7.setBounds(10, 20, 140, 130);
        jPanelMessage.add(Lstatut);
        Lstatut.setBounds(230, 125, 50, 30);

        Lemail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Lemail.setForeground(java.awt.Color.white);
        Lemail.setText("email");
        jPanelMessage.add(Lemail);
        Lemail.setBounds(210, 50, 140, 20);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setForeground(java.awt.Color.white);
        jLabel12.setText("Statut :");
        jPanelMessage.add(jLabel12);
        jLabel12.setBounds(170, 125, 50, 30);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/121113.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanelMessage.add(jButton3);
        jButton3.setBounds(160, -400, 40, 40);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Message : ");
        jPanelMessage.add(jLabel5);
        jLabel5.setBounds(10, 550, 60, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("File :");
        jPanelMessage.add(jLabel6);
        jLabel6.setBounds(20, 590, 25, 20);

        jAppel.setBackground(new java.awt.Color(153, 255, 153));
        jAppel.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jAppel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/call.png"))); // NOI18N
        jAppel.setText("Appeler");
        jAppel.setOpaque(true);
        jAppel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jAppelMouseClicked(evt);
            }
        });
        jPanelMessage.add(jAppel);
        jAppel.setBounds(290, 170, 90, 25);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("Secteur :");
        jPanelMessage.add(jLabel8);
        jLabel8.setBounds(170, 90, 60, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("secteur");
        jPanelMessage.add(jLabel4);
        jLabel4.setBounds(230, 90, 140, 20);

        jButton14.setBackground(java.awt.Color.white);
        jButton14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/Chat-Delete_1-512.png"))); // NOI18N
        jButton14.setText("Supp msgs");
        jButton14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanelMessage.add(jButton14);
        jButton14.setBounds(385, 150, 122, 30);

        jButton15.setBackground(java.awt.Color.white);
        jButton15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/téléchargement (1).png"))); // NOI18N
        jButton15.setText("Bloquer");
        jButton15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanelMessage.add(jButton15);
        jButton15.setBounds(390, 20, 114, 30);

        jButton17.setBackground(java.awt.Color.white);
        jButton17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/user-remove-512.png"))); // NOI18N
        jButton17.setText("Supprime");
        jButton17.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanelMessage.add(jButton17);
        jButton17.setBounds(390, 110, 115, 30);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setForeground(java.awt.Color.white);
        jLabel26.setText("Email :");
        jPanelMessage.add(jLabel26);
        jLabel26.setBounds(170, 50, 40, 20);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanelMessage.add(jSeparator3);
        jSeparator3.setBounds(382, 10, 10, 180);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(java.awt.Color.white);
        jPanelMessage.add(jLabel34);
        jLabel34.setBounds(170, 20, 180, 20);
        jPanelMessage.add(jLabelnameAmi);
        jLabelnameAmi.setBounds(90, 170, 0, 0);

        jButton19.setBackground(java.awt.Color.white);
        jButton19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/téléchargement (1).png"))); // NOI18N
        jButton19.setText("Débloque");
        jButton19.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanelMessage.add(jButton19);
        jButton19.setBounds(390, 65, 114, 30);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/action-2277292_1280.jpg"))); // NOI18N
        jPanelMessage.add(jLabel21);
        jLabel21.setBounds(0, 0, 510, 630);

        jPanelMenu.add(jPanelMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 510, 630));

        jPanelInvitationRecu.setLayout(null);

        jTable3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        jTable3.setRowHeight(25);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable3);

        jPanelInvitationRecu.add(jScrollPane5);
        jScrollPane5.setBounds(20, 310, 480, 160);
        jPanelInvitationRecu.add(jLabel15);
        jLabel15.setBounds(10, 10, 180, 130);

        jButton13.setBackground(java.awt.Color.white);
        jButton13.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jButton13.setText("Accepter");
        jButton13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanelInvitationRecu.add(jButton13);
        jButton13.setBounds(140, 480, 83, 30);

        jButton18.setBackground(java.awt.Color.white);
        jButton18.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jButton18.setText("Refuser");
        jButton18.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanelInvitationRecu.add(jButton18);
        jButton18.setBounds(280, 480, 80, 30);

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel25.setForeground(java.awt.Color.white);
        jLabel25.setText("Email :");
        jPanelInvitationRecu.add(jLabel25);
        jLabel25.setBounds(220, 110, 130, 30);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel27.setForeground(java.awt.Color.white);
        jPanelInvitationRecu.add(jLabel27);
        jLabel27.setBounds(260, 10, 150, 30);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel28.setForeground(java.awt.Color.white);
        jPanelInvitationRecu.add(jLabel28);
        jLabel28.setBounds(270, 110, 160, 30);

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel29.setForeground(java.awt.Color.white);
        jLabel29.setText("Secteur :");
        jPanelInvitationRecu.add(jLabel29);
        jLabel29.setBounds(220, 80, 130, 20);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel30.setForeground(java.awt.Color.white);
        jPanelInvitationRecu.add(jLabel30);
        jLabel30.setBounds(280, 40, 130, 30);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel31.setForeground(java.awt.Color.white);
        jPanelInvitationRecu.add(jLabel31);
        jLabel31.setBounds(280, 80, 170, 20);

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel32.setForeground(java.awt.Color.white);
        jLabel32.setText("Prenom :");
        jPanelInvitationRecu.add(jLabel32);
        jLabel32.setBounds(220, 40, 60, 30);

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel33.setForeground(java.awt.Color.white);
        jLabel33.setText("Nom :");
        jPanelInvitationRecu.add(jLabel33);
        jLabel33.setBounds(220, 10, 40, 30);
        jPanelInvitationRecu.add(jLabel10);
        jLabel10.setBounds(270, 200, 10, 0);

        jLabel44.setFont(new java.awt.Font("Traditional Arabic", 3, 28)); // NOI18N
        jLabel44.setForeground(java.awt.Color.white);
        jLabel44.setText("Invitations Recus");
        jPanelInvitationRecu.add(jLabel44);
        jLabel44.setBounds(140, 260, 270, 50);
        jPanelInvitationRecu.add(jSeparator6);
        jSeparator6.setBounds(140, 300, 220, 10);

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/action-2277292_1280.jpg"))); // NOI18N
        jPanelInvitationRecu.add(jLabel24);
        jLabel24.setBounds(0, 0, 520, 630);

        jPanelMenu.add(jPanelInvitationRecu, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 520, 630));

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 11)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/icons8_Exit_32px.png"))); // NOI18N
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanelMenu.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 30, 30));

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setAlignmentX(1.0F);
        jSeparator1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSeparator1.setOpaque(true);
        jPanelMenu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 10, 630));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jList1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jList1.setFont(new java.awt.Font("Euphemia", 0, 20)); // NOI18N
        jList1.setForeground(new java.awt.Color(102, 102, 102));
        jList1.setModel((model = new DefaultListModel()));
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setFixedCellHeight(50);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jTabbedPane1.addTab("Contact", jScrollPane2);

        jList2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jList2.setFont(new java.awt.Font("Euphemia", 0, 20)); // NOI18N
        jList2.setModel((model5 = new DefaultListModel()));
        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jList2);

        jTabbedPane1.addTab("Messages manqués", jScrollPane3);

        jPanelMenu.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 280, 450));
        jTabbedPane1.getAccessibleContext().setAccessibleName("Contact");

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/add user.png"))); // NOI18N
        jLabel17.setText("Invitations recus");
        jLabel17.setToolTipText("");
        jLabel17.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 1, true));
        jLabel17.setOpaque(true);
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel17MouseExited(evt);
            }
        });
        jPanelMenu.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 160, -1));
        jPanelMenu.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 72, 280, 10));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/add user.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
        });
        jPanelMenu.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 82, 40, 40));

        jLabel13.setBackground(java.awt.Color.white);
        jLabel13.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 13)); // NOI18N
        jLabel13.setForeground(java.awt.Color.red);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/history.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel13MouseExited(evt);
            }
        });
        jPanelMenu.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 85, 50, 40));

        jLabel14.setBackground(java.awt.Color.white);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/house.png"))); // NOI18N
        jLabel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel14MouseExited(evt);
            }
        });
        jPanelMenu.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        Lstatut1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/En ligne.png"))); // NOI18N
        jPanelMenu.add(Lstatut1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 70, 60));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 204, 0));
        jLabel2.setText("Hello Aghilas");
        jPanelMenu.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 190, 40));

        jLabel35.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 13)); // NOI18N
        jLabel35.setForeground(java.awt.Color.red);
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanelMenu.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 142, 30, 30));

        jLabel3.setText("  Exit");
        jPanelMenu.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 40, 30, -1));

        getContentPane().add(jPanelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanelAutentif.setBackground(java.awt.Color.white);
        jPanelAutentif.setLayout(null);

        jTextField3.setBackground(new java.awt.Color(240, 240, 240));
        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nom utilisateur", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Traditional Arabic", 3, 16))); // NOI18N
        jTextField3.setOpaque(false);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanelAutentif.add(jTextField3);
        jTextField3.setBounds(480, 130, 210, 50);

        jPasswordField1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mot de passe", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Traditional Arabic", 3, 16))); // NOI18N
        jPasswordField1.setOpaque(false);
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        jPanelAutentif.add(jPasswordField1);
        jPasswordField1.setBounds(480, 190, 210, 50);

        jLabel9.setFont(new java.awt.Font("Traditional Arabic", 3, 17)); // NOI18N
        jLabel9.setText("S'inscrire ?");
        jLabel9.setEnabled(false);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanelAutentif.add(jLabel9);
        jLabel9.setBounds(540, 300, 100, 30);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Traditional Arabic", 3, 18)); // NOI18N
        jButton2.setText("Login");
        jButton2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanelAutentif.add(jButton2);
        jButton2.setBounds(520, 260, 130, 30);

        jLabel1.setFont(new java.awt.Font("Traditional Arabic", 3, 28)); // NOI18N
        jLabel1.setText(" Autentification ");
        jPanelAutentif.add(jLabel1);
        jLabel1.setBounds(480, 50, 220, 90);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI Symbol", 2, 11)); // NOI18N
        jButton1.setText("se connecter au server");
        jButton1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanelAutentif.add(jButton1);
        jButton1.setBounds(530, 30, 110, 30);

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Views/656665.jpg"))); // NOI18N
        jPanelAutentif.add(jLabel20);
        jLabel20.setBounds(0, 0, 830, 630);

        getContentPane().add(jPanelAutentif, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        serverAddr = "localhost"; port = 13000;
        
            try{
                client = new SocketClient(this);
                System.out.println("la socket est crée");
                clientThread = new Thread(client);
                System.out.println("le thread est crée");
                clientThread.start();
                client.send(new Message("test", "testUser", "testContent", "SERVER"));
            }
            catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Serveur non trouvé");    
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        username = jTextField3.getText();
        password = jPasswordField1.getText();
        
        if(!username.isEmpty() && !password.isEmpty()){
            client.send(new Message("login", username, password, "SERVER"));
        }else{JOptionPane.showMessageDialog(null,"veuillez remplire les champs !");    
            }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String msg = jTextField4.getText();
      
        String target = jList1.getSelectedValue().toString();
        
        if(!msg.isEmpty() && !target.isEmpty()){
            jTextField4.setText("");
            client.send(new Message("message", username, msg, target));
            //jList1.isSelectionEmpty();
        }
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showDialog(this, "Select File");
        file = fileChooser.getSelectedFile();
        
        if(file != null){
            if(!file.getName().isEmpty()){
                jButton6.setEnabled(true); String str;
                
                if(jTextField5.getText().length() > 30){
                    String t = file.getPath();
                    str = t.substring(0, 20) + " [...] " + t.substring(t.length() - 20, t.length());
                }
                else{
                    str = file.getPath();
                }
                jTextField5.setText(str);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
            long size = file.length();
            if(size < 120 * 1024 * 1024){
                client.send(new Message("upload_req", username, file.getName(), jList1.getSelectedValue().toString()));
            }
            else{
                jTextArea1.append("[Application > Me] : File is size too large\n");
            }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
      if(JOptionPane.showConfirmDialog(this, ("etes vous sur de vouloir se deconnecter ?")) == 0){
          client.send(new Message("message", username, ".bye", "SERVER"));
          
          jTextField3.setText(null);
          jPasswordField1.setText(null);
          
    }//GEN-LAST:event_jButton7ActionPerformed
    }
    
    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jAppelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAppelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jAppelMouseClicked

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jLabel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseEntered
      jLabel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
     
    }//GEN-LAST:event_jLabel14MouseEntered

    private void jLabel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseExited
jLabel14.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 1, true));
    }//GEN-LAST:event_jLabel14MouseExited

    private void jLabel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseEntered
    jLabel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0,0), 1, true));
     
    }//GEN-LAST:event_jLabel13MouseEntered

    private void jLabel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseExited
  jLabel13.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 1, true));
    }//GEN-LAST:event_jLabel13MouseExited

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
     jLabel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
     
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
      jLabel11.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 1, true));
    }//GEN-LAST:event_jLabel11MouseExited

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
     jLabel17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
     
    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseExited
    jLabel17.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 1, true));
    }//GEN-LAST:event_jLabel17MouseExited

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
       jPanelInfoPerso.setVisible(true);
       jPanelMessage.setVisible(false);
       jPanelAjoutAmis.setVisible(false);
        jPanelInvitationRecu.setVisible(false);
      
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
       amisSelectionné =(String) jList1.getSelectedValue();
       jPanelMessage.setVisible(true);
       jPanelInfoPerso.setVisible(false);
       jPanelAjoutAmis.setVisible(false);
        jPanelInvitationRecu.setVisible(false);
        jLabelnameAmi.setText(amisSelectionné);
       
       jTextArea1.setText(null);
       client.send(new Message("getInfoAmis",username,amisSelectionné,"Server"));
        System.out.println(amisSelectionné);
    }//GEN-LAST:event_jList1MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        Inscription insc=new Inscription(this);
        insc.setVisible(true);
        insc.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
       
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
      
     
      if(JOptionPane.showConfirmDialog(this, ("voulez vous vraiment envoyer cette invitation ?")) == 0){
      client.send(new Message("sendInvitation",username,utilisateur,"Serveur"));
      
      }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        
      if(JOptionPane.showConfirmDialog(this, ("voulez vous vraiment suprimmer cette invitation ?")) == 0){
      client.send(new Message("deleteInvitation",username,utilisateur,"Serveur"));
      
      }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
       jPanelAjoutAmis.setVisible(true);
       jPanelMessage.setVisible(false);
       jPanelInfoPerso.setVisible(false);
        jPanelInvitationRecu.setVisible(false);
        jLabel19.setSize(0,0);
        client.send(new Message("getUser",username,"","Server"));
       
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
    String secteur=jTextFieldSecteur.getText();
    String email=jTextFieldEmail.getText();
    String mdps =jPasswordField2.getText();
    if(JOptionPane.showConfirmDialog(this, ("voulez vous vraiment enregistrer les modifications ?")) == 0){
        if(jTextFieldImage.getText().isEmpty()){
        
    client.sendSignup(new MessageSignup("sansImage", "", secteur, email, username, mdps, null), "");
        }else{
            
    client.sendSignup(new MessageSignup("avecImage", "", secteur, email, username, mdps, photo), "");}
    
    }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter =new FileNameExtensionFilter("IMAGE" , "jpg" , "png" , "gif");
       chooser.addChoosableFileFilter(filter);
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        filename=f.getAbsolutePath();
        jTextFieldImage.setText(filename);
        
        try{
        File image=new File(filename);
            FileImageInputStream fis=new FileImageInputStream(image);
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            byte[] buf=new byte[1024];
            for (int i; (i =fis.read(buf))!=-1; ) {
                    bos.write(buf, 0, i);
            }
        photo=bos.toByteArray();
        
        
        }catch(Exception e){JOptionPane.showConfirmDialog(null, e);}
       
        
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
      try {
               String f=jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString();
               System.out.println("je suis dans jtable2Click pour afficher les info "+f);
                 byte[] img =    (byte[]) jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 5);
               if(img !=null){
                ByteArrayInputStream bais =new ByteArrayInputStream(img);
                BufferedImage bImg=ImageIO.read(bais);
                ImageIcon icon=new ImageIcon(bImg);
                
               jLabelphoto.setIcon(icon);
                bais.close();
    
               }
                        
               jLabel41.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 2).toString());
               jLabel37.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString());
               jLabel28.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString());
               
               jLabel40.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
               jLabel38.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
               
               
               } catch (Exception ex) {
                System.out.println("erreur afficher image ajout amis "+ex);;
            }
        
        
        utilisateur=jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 4).toString();
        System.out.println(utilisateur);
        client.send(new Message("verifyInvitation",username,utilisateur,"Server"));
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void jPasswordField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField2ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
      
       
            try {
                
                 byte[] img =    (byte[]) jTable3.getModel().getValueAt(jTable3.getSelectedRow(), 5);
               if(img !=null){
                ByteArrayInputStream bais =new ByteArrayInputStream(img);
                BufferedImage bImg=ImageIO.read(bais);
                ImageIcon icon=new ImageIcon(bImg);
                
               jLabel15.setIcon(icon);
                bais.close();
    
               }
   
               jLabel30.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 2).toString());
               jLabel31.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 3).toString());
               jLabel28.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 4).toString());
               
               jLabel27.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 1).toString());
               jLabel10.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString());
               
               
               } catch (Exception ex) {
                System.out.println("erreur afficher image invitation "+ex);;
            }

    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
String ami=jLabelnameAmi.getText();
if(jTextArea1.getText().isEmpty()){
    JOptionPane.showMessageDialog(null,"Aucun message à supprimer !");
}else{
       if(JOptionPane.showConfirmDialog(this, (" voulez vous vraiment supprimer l'historique des messages ?")) == 0){
           client.send(new Message("deleteHistorique", username,ami, "Server"));
       }}
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
     String ami=jLabelnameAmi.getText();
        if(JOptionPane.showConfirmDialog(this, (" voulez vous vraiment supprimer "+ami+" de votre liste d'amis ?")) == 0){
           client.send(new Message("deleteFriend", username,ami, "Server"));
       }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
      String ami=jLabelnameAmi.getText();
        if(JOptionPane.showConfirmDialog(this, (" voulez vous vraiment bloquer votre ami "+ami+" ?")) == 0){
           client.send(new Message("bloqueFriend", username,ami, "Server"));
       }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
       jPanelInvitationRecu.setVisible(true);
        jPanelAjoutAmis.setVisible(false);
       jPanelMessage.setVisible(false);
       jPanelInfoPerso.setVisible(false);
        client.send(new Message("getInvitation",username,"","Server"));
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
      String futurAmi=jLabel30.getText();
       if(JOptionPane.showConfirmDialog(this, (" etes vous sur de vouloir accepter cette invitation ?")) == 0){
           client.send(new Message("accepteInvitation", username,futurAmi, "Server"));
       }
       
      
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed

       String refuseInvit=jLabel30.getText();
       if(JOptionPane.showConfirmDialog(this, (" etes vous sur d'accepter cette invitation et devenir amis ?")) == 0){
           client.send(new Message("refuserInvitation", username,refuseInvit, "Server"));
       }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jTextFieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmailActionPerformed

    private void jTextFieldSecteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSecteurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSecteurActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
          String ami=jLabelnameAmi.getText();
           if(JOptionPane.showConfirmDialog(this, (" voulez vous vraiment debloquer votre ami "+ami+" ?")) == 0){
           client.send(new Message("debloqueFriend", username,ami, "Server"));
       }    }//GEN-LAST:event_jButton19ActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
model5.clear();
jTabbedPane1.setSelectedIndex(1);
 client.send(new Message("getMessageManque",username,"","Server"));
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
 int index;
        try{if(jList2.getSelectedValue()!=null){
            index=jList2.getSelectedIndex();
            System.out.println("lindex est "+index);
        amisSelectionné =(String) jList2.getSelectedValue();
       jPanelMessage.setVisible(true);
       jPanelInfoPerso.setVisible(false);
       jPanelAjoutAmis.setVisible(false);
        jPanelInvitationRecu.setVisible(false);
        jLabelnameAmi.setText(amisSelectionné);
       
       jTextArea1.setText(null);
       client.send(new Message("getInfoAmis",username,amisSelectionné,"Server"));
       client.send(new Message("eleverMessageManque",username,amisSelectionné,"Server"));
       model5.remove(index);
       int nbr=Integer.parseInt(jLabel13.getText());
       nbr--;
       jLabel13.setText(String.valueOf(nbr));
        System.out.println(amisSelectionné);
        }
        }catch(Exception e){System.out.println("erreur dans jlist2Click "+e);}// TODO add your handling code here:
    }//GEN-LAST:event_jList2MouseClicked
    
    public void send(MessageSignup msg)
    {  
 client.sendSignup(msg,"signup");
    }
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch(Exception ex){
            System.out.println("Look & Feel exception");
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              MainInterface ch=  new MainInterface();
              //ch.setSize(450, 360);
              ch.setVisible(true);
              ch.jPanelMenu.setVisible(false);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel Lemail;
    public javax.swing.JLabel Lstatut;
    private javax.swing.JLabel Lstatut1;
    private javax.swing.JLabel jAppel;
    public javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    public javax.swing.JButton jButton11;
    public javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    public javax.swing.JButton jButton14;
    public javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    public javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    public javax.swing.JButton jButton19;
    public javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    public javax.swing.JButton jButton4;
    public javax.swing.JButton jButton5;
    public javax.swing.JButton jButton6;
    public javax.swing.JButton jButton7;
    public javax.swing.JLabel jLabePrenom;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    public javax.swing.JLabel jLabel27;
    public javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel30;
    public javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    public javax.swing.JLabel jLabel34;
    public javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    public javax.swing.JLabel jLabel37;
    public javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel40;
    public javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JLabel jLabelNom;
    public javax.swing.JLabel jLabelnameAmi;
    public javax.swing.JLabel jLabelphoto;
    public javax.swing.JList jList1;
    public javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel6;
    public javax.swing.JPanel jPanelAjoutAmis;
    public javax.swing.JPanel jPanelAutentif;
    public javax.swing.JPanel jPanelInfoPerso;
    public javax.swing.JPanel jPanelInvitationRecu;
    public javax.swing.JPanel jPanelMenu;
    public javax.swing.JPanel jPanelMessage;
    public javax.swing.JPasswordField jPasswordField1;
    public javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable jTable2;
    public javax.swing.JTable jTable3;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField jTextField3;
    public javax.swing.JTextField jTextField4;
    public javax.swing.JTextField jTextField5;
    public javax.swing.JTextField jTextFieldEmail;
    public javax.swing.JTextField jTextFieldImage;
    public javax.swing.JTextField jTextFieldSecteur;
    // End of variables declaration//GEN-END:variables
}
