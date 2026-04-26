/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fee_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author souls
 */
public class Signup_Page extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Signup_Page.class.getName());

    /**
     * Creates new form Signup_Page
     */
       String fname,lname,uname,password,con_pass,contact_no;
            Date dob;
            int id=0;
    public Signup_Page() {
        initComponents();
    }
    
    public int getId()
    {
        ResultSet rs=null;
        try{
            Class.forName("org.apache.derby.iapi.jdbc.AutoloadedDriver");
                Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/fee_management","soham","2986");
                String sql="select max(id) from signup";
                Statement st=con.createStatement();
                rs=(ResultSet) st.executeQuery(sql);
              while (rs.next())
              {
                  id=rs.getInt(1);
                  id++;
                  
              }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
               
        boolean validation()
        {
         
            fname=txt_firstname.getText();
            lname=txt_lastname.getText();
            uname=txt_username.getText();
            password=txt_password.getText();
            con_pass=txt_con_password.getText();
            contact_no=txt_conatactno.getText();
            dob=txt_dob.getDate();
            
            if (fname.equals(""))
            {
                JOptionPane.showMessageDialog(this, "please enter firstname");
                return false;
            }
            
            if (lname.equals(""))
            {
                JOptionPane.showMessageDialog(this, "please enter lastname");
                return false;
            }
            
            if (uname.equals(""))
            {
                JOptionPane.showMessageDialog(this, "please enter username");
                return false;
            }
            
            if (password.equals(""))
            {
                JOptionPane.showMessageDialog(this, "please enter password");
                return false;
            }
            
            if (con_pass.equals(""))
            {
                JOptionPane.showMessageDialog(this, "please confirm the password");
                return false;
            }
            
            if (dob.equals(null))
            {
                JOptionPane.showMessageDialog(this, "please enter date of birth");
                return false;
            }
            
            if (contact_no.equals(""))
            {
                JOptionPane.showMessageDialog(this, "please enter contact no");
                return false;
            }
            
            if (password.length()<8)
            {
                lbl_password_error.setText("password should be of 8 digits");
            }
            
            if (!password.equals(con_pass))
            {
                JOptionPane.showMessageDialog(this, "password not matched");
                return false;
            }
            
            return true;
        }
        
        public void checkPassword()
        {
            password=txt_password.getText();
            if (password.length()<8)
            {
                lbl_password_error.setText("password should be of 8 digits");
            }
            else
            {
                lbl_password_error.setText("");
            }
        }
        
        public void checkContactNo()
        {
            contact_no=txt_conatactno.getText();
            if (contact_no.length()==10)
            {
                lbl_contact_error.setText("");
            }
            else
            {
                lbl_contact_error.setText("contact no should be of 10 digits");
            }
        }
        
        void insertDetails()
        {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            String myDob=format.format(dob);
            try
            {
                Class.forName("org.apache.derby.iapi.jdbc.AutoloadedDriver");
                Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/fee_management","soham","2986");
                String sql="insert into signup values(?,?,?,?,?,?,?)";
                java.sql.PreparedStatement stmt=con.prepareStatement(sql);
                stmt.setInt(1,getId() );
                stmt.setString(2, fname);
                stmt.setString(3, lname);
                stmt.setString(4, uname);
                stmt.setString(5, password);
                stmt.setString(6, myDob);
                stmt.setString(7, contact_no);
                int i=stmt.executeUpdate();
                   if (i>0)
                   {
                       JOptionPane.showMessageDialog(this, "record inserted");
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(this, "record not inserted");
                   }
                    
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_firstname = new javax.swing.JTextField();
        txt_lastname = new javax.swing.JTextField();
        txt_username = new javax.swing.JTextField();
        txt_conatactno = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        txt_con_password = new javax.swing.JPasswordField();
        txt_dob = new com.toedter.calendar.JDateChooser();
        btn_signup = new javax.swing.JButton();
        btn_login = new javax.swing.JButton();
        lbl_password_error = new javax.swing.JLabel();
        lbl_contact_error = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/new-user.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 133, 70));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Signup");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 30, 133, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 100));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("First Name :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 52, 110, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Username :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 147, 110, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Last Name :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 98, 110, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Password :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 202, 110, 31));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Confirm Password :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 251, 164, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("D.O.B :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 300, 110, 31));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Contact no :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 346, 110, -1));

        txt_firstname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(txt_firstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 46, 192, 37));

        txt_lastname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_lastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_lastnameActionPerformed(evt);
            }
        });
        jPanel2.add(txt_lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 98, 192, 37));

        txt_username.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        jPanel2.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 147, 192, 37));

        txt_conatactno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_conatactno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_conatactnoActionPerformed(evt);
            }
        });
        txt_conatactno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_conatactnoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_conatactnoKeyReleased(evt);
            }
        });
        jPanel2.add(txt_conatactno, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 343, 192, -1));

        txt_password.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passwordKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_passwordKeyReleased(evt);
            }
        });
        jPanel2.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 202, 192, -1));

        txt_con_password.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_con_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_con_passwordActionPerformed(evt);
            }
        });
        jPanel2.add(txt_con_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 251, 192, -1));
        jPanel2.add(txt_dob, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 300, 192, 31));

        btn_signup.setBackground(new java.awt.Color(0, 51, 51));
        btn_signup.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_signup.setForeground(new java.awt.Color(255, 255, 255));
        btn_signup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/signup.png"))); // NOI18N
        btn_signup.setText("SIGNUP");
        btn_signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_signupActionPerformed(evt);
            }
        });
        jPanel2.add(btn_signup, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 417, -1, -1));

        btn_login.setBackground(new java.awt.Color(0, 51, 51));
        btn_login.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_login.setForeground(new java.awt.Color(255, 255, 255));
        btn_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/login.png"))); // NOI18N
        btn_login.setText("LOGIN");
        btn_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_loginMouseClicked(evt);
            }
        });
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        jPanel2.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 417, 130, 40));

        lbl_password_error.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_password_error.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lbl_password_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(467, 202, 237, 19));

        lbl_contact_error.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_contact_error.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lbl_contact_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(467, 343, 237, 21));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 710, 480));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_lastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lastnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lastnameActionPerformed

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_conatactnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_conatactnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_conatactnoActionPerformed

    private void txt_con_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_con_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_con_passwordActionPerformed

    private void btn_signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_signupActionPerformed
       if ( validation())
       {
           insertDetails();
       }
    }//GEN-LAST:event_btn_signupActionPerformed

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_loginActionPerformed

    private void txt_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyPressed
        checkPassword();
    }//GEN-LAST:event_txt_passwordKeyPressed

    private void txt_passwordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyReleased
        checkPassword();
    }//GEN-LAST:event_txt_passwordKeyReleased

    private void txt_conatactnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_conatactnoKeyReleased
        checkContactNo();
    }//GEN-LAST:event_txt_conatactnoKeyReleased

    private void txt_conatactnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_conatactnoKeyPressed
        checkContactNo();
    }//GEN-LAST:event_txt_conatactnoKeyPressed

    private void btn_loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseClicked
        Login_page login = new Login_page();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_loginMouseClicked

    /**
     * @param args the command line arguments
     */
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Signup_Page().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JButton btn_signup;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_contact_error;
    private javax.swing.JLabel lbl_password_error;
    private javax.swing.JPasswordField txt_con_password;
    private javax.swing.JTextField txt_conatactno;
    private com.toedter.calendar.JDateChooser txt_dob;
    private javax.swing.JTextField txt_firstname;
    private javax.swing.JTextField txt_lastname;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables


}
