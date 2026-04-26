/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fee_management_system;

import java.awt.Color;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author souls
 */
public class AddFees extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AddFees.class.getName());
    private String sql;

    /**
     * Creates new form AddFees
     */
    public AddFees() {
        initComponents();
        displayCashFirst();
        fillComboBox();
        int receiptNo = getReceiptNo();
        txt_receiptNo.setText(Integer.toString(receiptNo));
        
    }
    
    public void displayCashFirst()
    {
        lbl_DDno.setVisible(false);
        lbl_chequeno.setVisible(false);
        lbl_bankName.setVisible(false);
        
        txt_DDNo.setVisible(false);
        txt_ChequeNo.setVisible(false);
        txt_bankName.setVisible(false);
    }
    
    public boolean Validation()
    {
        if (txt_recievedFrom.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "please enter username");
            return false;
        }
        if (dateChooser.getDate() ==null)
        {
            JOptionPane.showMessageDialog(this, "please select a date");
            return false;
        }
        if (txt_amount.getText().equals("") | txt_amount.getText().matches("[0-9]+")==false)
        {
            JOptionPane.showMessageDialog(this, "please enter amount(in numbers)");
            return false;
        }
        if (combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("cheque"))
        {
             if (txt_ChequeNo.getText().equals(""))
             {
                 JOptionPane.showMessageDialog(this, "please enter cheque number");
                 return false;
             }
             if (txt_bankName.getText().equals(""))
             {
                 JOptionPane.showMessageDialog(this, "please enter bank number");
                 return false;
             }
        } 
        if (combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("dd"))
        {
            if (txt_DDNo.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "please enter dd no");
                return false;
            }
            if (txt_DDNo.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "please enter bank name");
                return false;
            }
        }
        if (combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("card"))
        {
            if (txt_bankName.getText().equals(""))
             {
                 JOptionPane.showMessageDialog(this, "please enter bank number");
                 return false;
             }
        }
        return true;
    }
    
    public void fillComboBox()
    {
        try{
            Class.forName("org.apache.derby.iapi.jdbc.AutoloadedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/fee_management","soham","2986");
            java.sql.PreparedStatement pst=con.prepareStatement("Select cname from course");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                comboCourse.addItem(rs.getString("cname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int getReceiptNo()
    {
        int receiptNo = 0;
        try
        {
            Connection con = DBConnection.getconnection();
            java.sql.PreparedStatement pst = con.prepareStatement("select max(reciept_no) from fees_Details");
            ResultSet rs = pst.executeQuery();
            
            if (rs.next() == true){
                receiptNo = rs.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return receiptNo+1;
    }
    
    public String insertdata()
    {
        String status = "";
        
        int recieptNo = Integer.parseInt(txt_receiptNo.getText());
        String studentName = txt_recievedFrom.getText();
        String rollNo = txt_rollNo.getText();
        String paymentMode = combo_PaymentMode.getSelectedItem().toString();
        String chequeNo = txt_ChequeNo.getText();
        String bankName = txt_bankName.getText();
        String ddNo = txt_DDNo.getText();
        String courseName = txt_courseName.getText();
        String gstin = txt_GSTNo.getText();
        float totalAmount = Float.parseFloat(txt_total.getText());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(dateChooser.getDate());
        float initialAmount = Float.parseFloat(txt_amount.getText());
        float cgst = Float.parseFloat(txt_cgst.getText());
        float sgst = Float.parseFloat(txt_sgst.getText());
        String totalInWords = txt_total_in_words.getText();
        String remark = txt_remark.getText();
        int year1 = Integer.parseInt(txt_year1.getText());
        int year2 = Integer.parseInt(txt_year2.getText());
        
        
        try {
            Connection con = DBConnection.getconnection();
            PreparedStatement pst = con.prepareStatement("insert into fees_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            pst.setInt(1, recieptNo);
            pst.setString(2, studentName);
            pst.setString(3, rollNo);
            pst.setString(4, paymentMode);
            pst.setString(5, chequeNo);
            pst.setString(6, bankName);
            pst.setString(7, ddNo);
            pst.setString(8, courseName);
            pst.setString(9, gstin);
            pst.setFloat(10, totalAmount);
            pst.setString(11, date);
            pst.setFloat(12, initialAmount);
            pst.setFloat(13, cgst);
            pst.setFloat(14, sgst);
            pst.setString(15, totalInWords);
            pst.setString(16, remark);
            pst.setInt(17, year1);
            pst.setInt(18, year2);
            
            int rowCount = pst.executeUpdate();
              if(rowCount == 1) {
                  status="success";
              }else{
                  status="failed";
              }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelsideBar = new javax.swing.JPanel();
        panelHome = new javax.swing.JPanel();
        btnHome1 = new javax.swing.JLabel();
        btnHome2 = new javax.swing.JLabel();
        panelSearch = new javax.swing.JPanel();
        btnSearch = new javax.swing.JLabel();
        btnHome3 = new javax.swing.JLabel();
        panelEdit = new javax.swing.JPanel();
        btnEdit = new javax.swing.JLabel();
        btnHome4 = new javax.swing.JLabel();
        panelCourseList = new javax.swing.JPanel();
        btnCourseList = new javax.swing.JLabel();
        btnHome5 = new javax.swing.JLabel();
        panelViewAllRecord = new javax.swing.JPanel();
        btnViewAllRecord = new javax.swing.JLabel();
        btnHome6 = new javax.swing.JLabel();
        panelBack = new javax.swing.JPanel();
        btnBack = new javax.swing.JLabel();
        btnHome7 = new javax.swing.JLabel();
        panelLogout = new javax.swing.JPanel();
        btnLogout = new javax.swing.JLabel();
        btnHome = new javax.swing.JLabel();
        panelParent = new javax.swing.JPanel();
        txt_GSTNo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_DDno = new javax.swing.JLabel();
        lbl_chequeno = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_receiptNo = new javax.swing.JTextField();
        txt_DDNo = new javax.swing.JTextField();
        txt_ChequeNo = new javax.swing.JTextField();
        dateChooser = new com.toedter.calendar.JDateChooser();
        panelChild = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_rollNo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        lbl_recievedFrom = new javax.swing.JLabel();
        comboCourse = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_year1 = new javax.swing.JTextField();
        txt_total_in_words = new javax.swing.JTextField();
        txt_amount = new javax.swing.JTextField();
        txt_cgst = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txt_sgst = new javax.swing.JTextField();
        txt_courseName = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_remark = new javax.swing.JTextArea();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        btn_print = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_recievedFrom = new javax.swing.JTextField();
        txt_year2 = new javax.swing.JTextField();
        lbl_bankName = new javax.swing.JLabel();
        combo_PaymentMode = new javax.swing.JComboBox<>();
        txt_bankName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelsideBar.setBackground(new java.awt.Color(0, 102, 102));
        panelsideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelHome.setBackground(new java.awt.Color(0, 102, 102));
        panelHome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelHomeMouseExited(evt);
            }
        });
        panelHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnHome1.setForeground(new java.awt.Color(255, 255, 255));
        btnHome1.setText("Home");
        panelHome.add(btnHome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 180, 50));

        btnHome2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnHome2.setForeground(new java.awt.Color(255, 255, 255));
        btnHome2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/home.png"))); // NOI18N
        panelHome.add(btnHome2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 180, 70));

        panelsideBar.add(panelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 400, 70));

        panelSearch.setBackground(new java.awt.Color(0, 102, 102));
        panelSearch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelSearchMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelSearchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelSearchMouseExited(evt);
            }
        });
        panelSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search Record");
        panelSearch.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 260, 60));

        btnHome3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnHome3.setForeground(new java.awt.Color(255, 255, 255));
        btnHome3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/search2.png"))); // NOI18N
        panelSearch.add(btnHome3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 100, 70));

        panelsideBar.add(panelSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 400, 70));

        panelEdit.setBackground(new java.awt.Color(0, 102, 102));
        panelEdit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelEditMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelEditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelEditMouseExited(evt);
            }
        });
        panelEdit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEdit.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit Course");
        panelEdit.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 220, 40));

        btnHome4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnHome4.setForeground(new java.awt.Color(255, 255, 255));
        btnHome4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/edit2.png"))); // NOI18N
        panelEdit.add(btnHome4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 80, 70));

        panelsideBar.add(panelEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 400, 70));

        panelCourseList.setBackground(new java.awt.Color(0, 102, 102));
        panelCourseList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelCourseList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelCourseListMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelCourseListMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelCourseListMouseExited(evt);
            }
        });
        panelCourseList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCourseList.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnCourseList.setForeground(new java.awt.Color(255, 255, 255));
        btnCourseList.setText("Course List");
        panelCourseList.add(btnCourseList, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 210, -1));

        btnHome5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnHome5.setForeground(new java.awt.Color(255, 255, 255));
        btnHome5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/list.png"))); // NOI18N
        panelCourseList.add(btnHome5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 90, 70));

        panelsideBar.add(panelCourseList, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 400, 70));

        panelViewAllRecord.setBackground(new java.awt.Color(0, 102, 102));
        panelViewAllRecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelViewAllRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelViewAllRecordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelViewAllRecordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelViewAllRecordMouseExited(evt);
            }
        });
        panelViewAllRecord.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnViewAllRecord.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnViewAllRecord.setForeground(new java.awt.Color(255, 255, 255));
        btnViewAllRecord.setText("View All Records");
        panelViewAllRecord.add(btnViewAllRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 290, 50));

        btnHome6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnHome6.setForeground(new java.awt.Color(255, 255, 255));
        btnHome6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/view all record.png"))); // NOI18N
        panelViewAllRecord.add(btnHome6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, -10, 80, 90));

        panelsideBar.add(panelViewAllRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, 400, 70));

        panelBack.setBackground(new java.awt.Color(0, 102, 102));
        panelBack.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBackMouseExited(evt);
            }
        });
        panelBack.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBack.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        panelBack.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 130, 70));

        btnHome7.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnHome7.setForeground(new java.awt.Color(255, 255, 255));
        btnHome7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/left-arrow.png"))); // NOI18N
        panelBack.add(btnHome7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 70, 70));

        panelsideBar.add(panelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 580, 400, 70));

        panelLogout.setBackground(new java.awt.Color(0, 102, 102));
        panelLogout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelLogoutMouseExited(evt);
            }
        });
        panelLogout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogout.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        panelLogout.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 150, 70));

        btnHome.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/logout.png"))); // NOI18N
        panelLogout.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 80, 70));

        panelsideBar.add(panelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 680, 400, 70));

        getContentPane().add(panelsideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 850));

        panelParent.setBackground(new java.awt.Color(0, 153, 153));
        panelParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_GSTNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_GSTNo.setText("22HVSJH55");
        panelParent.add(txt_GSTNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 110, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Receipt no : SOCS -");
        panelParent.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 160, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Mode of Payment :");
        panelParent.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 170, 30));

        lbl_DDno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_DDno.setText("DD no :");
        panelParent.add(lbl_DDno, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 110, 30));

        lbl_chequeno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_chequeno.setText("Cheque No :");
        panelParent.add(lbl_chequeno, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 110, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Date :");
        panelParent.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, 110, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("GSTIN :");
        panelParent.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 110, 30));

        txt_receiptNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_receiptNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_receiptNoActionPerformed(evt);
            }
        });
        panelParent.add(txt_receiptNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 140, 30));

        txt_DDNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_DDNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_DDNoActionPerformed(evt);
            }
        });
        panelParent.add(txt_DDNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 140, 30));

        txt_ChequeNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_ChequeNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ChequeNoActionPerformed(evt);
            }
        });
        panelParent.add(txt_ChequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 140, 30));
        panelParent.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, 140, 30));

        panelChild.setBackground(new java.awt.Color(0, 153, 153));
        panelChild.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("to");
        panelChild.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 40, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Roll No :");
        panelChild.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 80, 20));

        txt_rollNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_rollNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rollNoActionPerformed(evt);
            }
        });
        panelChild.add(txt_rollNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 60, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("the following school office for the year");
        panelChild.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 320, 30));

        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });
        panelChild.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, 140, 30));

        lbl_recievedFrom.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_recievedFrom.setText("Recieved From :");
        panelChild.add(lbl_recievedFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, -1));

        comboCourse.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCourseActionPerformed(evt);
            }
        });
        panelChild.add(comboCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 220, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Amount");
        panelChild.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, 80, 20));
        panelChild.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 550, 10));
        panelChild.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 150, 10));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Course");
        panelChild.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 80, 20));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Remark :");
        panelChild.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 130, 20));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Head");
        panelChild.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 80, 20));

        txt_year1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_year1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_year1ActionPerformed(evt);
            }
        });
        panelChild.add(txt_year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 70, 30));

        txt_total_in_words.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_total_in_wordsActionPerformed(evt);
            }
        });
        panelChild.add(txt_total_in_words, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 280, 30));

        txt_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amountActionPerformed(evt);
            }
        });
        panelChild.add(txt_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 140, 30));

        txt_cgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cgstActionPerformed(evt);
            }
        });
        panelChild.add(txt_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 140, 30));
        panelChild.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 550, 10));

        txt_sgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sgstActionPerformed(evt);
            }
        });
        panelChild.add(txt_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 240, 140, 30));

        txt_courseName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_courseNameActionPerformed(evt);
            }
        });
        panelChild.add(txt_courseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 280, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Sr No");
        panelChild.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 80, 20));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Reciever Signature");
        panelChild.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 340, 160, -1));

        txt_remark.setColumns(20);
        txt_remark.setRows(5);
        jScrollPane1.setViewportView(txt_remark);

        panelChild.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 270, 90));
        panelChild.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, 160, 10));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("SGST 9 %");
        panelChild.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 130, 20));

        btn_print.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_print.setText("Print");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        panelChild.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, 100, 30));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Total in words :");
        panelChild.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 130, 20));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("CGST 9 %");
        panelChild.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 130, 20));

        txt_recievedFrom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_recievedFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_recievedFromActionPerformed(evt);
            }
        });
        panelChild.add(txt_recievedFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 280, -1));

        txt_year2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_year2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_year2ActionPerformed(evt);
            }
        });
        panelChild.add(txt_year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 70, 30));

        panelParent.add(panelChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 590, 440));

        lbl_bankName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_bankName.setText("Bank Name :");
        panelParent.add(lbl_bankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 110, 30));

        combo_PaymentMode.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        combo_PaymentMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "Cheque", "Cash", "Card" }));
        combo_PaymentMode.setSelectedIndex(2);
        combo_PaymentMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_PaymentModeActionPerformed(evt);
            }
        });
        panelParent.add(combo_PaymentMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 140, 30));

        txt_bankName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_bankName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bankNameActionPerformed(evt);
            }
        });
        panelParent.add(txt_bankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 310, 30));

        getContentPane().add(panelParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 1330, 850));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panelHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMouseEntered
        Color clr= new Color(0,153,153);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_panelHomeMouseEntered

    private void panelHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMouseExited
        Color clr= new Color(0,103,103);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_panelHomeMouseExited

    private void panelSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchMouseEntered
        Color clr= new Color(0,153,153);
        panelSearch.setBackground(clr);
    }//GEN-LAST:event_panelSearchMouseEntered

    private void panelSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchMouseExited
        Color clr= new Color(0,103,103);
        panelSearch.setBackground(clr);
    }//GEN-LAST:event_panelSearchMouseExited

    private void panelEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditMouseEntered
        Color clr= new Color(0,153,153);
        panelEdit.setBackground(clr);
    }//GEN-LAST:event_panelEditMouseEntered

    private void panelEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditMouseExited
        Color clr= new Color(0,103,103);
        panelEdit.setBackground(clr);
    }//GEN-LAST:event_panelEditMouseExited

    private void panelCourseListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCourseListMouseEntered
       Color clr= new Color(0,153,153);
        panelCourseList.setBackground(clr);
    }//GEN-LAST:event_panelCourseListMouseEntered

    private void panelCourseListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCourseListMouseExited
        Color clr= new Color(0,103,103);
        panelCourseList.setBackground(clr);
    }//GEN-LAST:event_panelCourseListMouseExited

    private void panelViewAllRecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewAllRecordMouseEntered
        Color clr= new Color(0,153,153);
        panelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_panelViewAllRecordMouseEntered

    private void panelViewAllRecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewAllRecordMouseExited
        Color clr= new Color(0,103,103);
        panelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_panelViewAllRecordMouseExited

    private void panelBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBackMouseEntered
       Color clr= new Color(0,153,153);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_panelBackMouseEntered

    private void panelBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBackMouseExited
        Color clr= new Color(0,103,103);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_panelBackMouseExited

    private void panelLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseEntered
        Color clr= new Color(0,153,153);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_panelLogoutMouseEntered

    private void panelLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseExited
        Color clr= new Color(0,103,103);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_panelLogoutMouseExited

    private void txt_rollNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rollNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rollNoActionPerformed

    private void txt_receiptNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_receiptNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_receiptNoActionPerformed

    private void txt_DDNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_DDNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_DDNoActionPerformed

    private void txt_ChequeNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ChequeNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ChequeNoActionPerformed

    private void txt_recievedFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_recievedFromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_recievedFromActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void txt_year1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_year1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_year1ActionPerformed

    private void txt_total_in_wordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_total_in_wordsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_total_in_wordsActionPerformed

    private void txt_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amountActionPerformed
        Float amnt = Float.parseFloat(txt_amount.getText());
        Float cgst = (float)(amnt * 0.09);
        Float sgst = (float)(amnt * 0.09);
        txt_cgst.setText(cgst.toString());
        txt_sgst.setText(sgst.toString());
        float total = amnt + cgst + sgst;
        txt_total.setText(Float.toString(total));
        
        txt_total_in_words.setText(number_to_word_converter.convert((int)total) + " only ");
    }//GEN-LAST:event_txt_amountActionPerformed

    private void txt_cgstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cgstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cgstActionPerformed

    private void txt_sgstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sgstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sgstActionPerformed

    private void txt_courseNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_courseNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_courseNameActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        if (Validation() ==true)
        {
            String result = insertdata();
            
            if(result.equals("success")) {
                JOptionPane.showMessageDialog(this, "record inserted successfully");
                PrintReciept p = new PrintReciept();
                p.setVisible(true);
                this.dispose();
            }else {
                JOptionPane.showMessageDialog(this, "record insertion failed");
            }
        }
    }//GEN-LAST:event_btn_printActionPerformed

    private void txt_bankNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bankNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bankNameActionPerformed

    private void combo_PaymentModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_PaymentModeActionPerformed
        if (combo_PaymentMode.getSelectedIndex() == 0)
        {
            lbl_DDno.setVisible(true);
            txt_DDNo.setVisible(true);
            lbl_chequeno.setVisible(false);
            txt_ChequeNo.setVisible(false);
            lbl_bankName.setVisible(true);
            txt_bankName.setVisible(true);
        }
        if (combo_PaymentMode.getSelectedIndex() == 1)
        {
            lbl_DDno.setVisible(false);
            txt_DDNo.setVisible(false);
            lbl_chequeno.setVisible(true);
            txt_ChequeNo.setVisible(true);
            lbl_bankName.setVisible(true);
            txt_bankName.setVisible(true);
        }
        if (combo_PaymentMode.getSelectedIndex() == 2)
        {
            lbl_DDno.setVisible(false);
            txt_DDNo.setVisible(false);
            lbl_chequeno.setVisible(false);
            txt_ChequeNo.setVisible(false);
            lbl_bankName.setVisible(false);
            txt_bankName.setVisible(false);
        }
        if (combo_PaymentMode.getSelectedItem().equals("Card"))
        {
            lbl_DDno.setVisible(false);
            txt_DDNo.setVisible(false);
            lbl_chequeno.setVisible(false);
            txt_ChequeNo.setVisible(false);
            lbl_bankName.setVisible(true);
            txt_bankName.setVisible(true);
        }
    }//GEN-LAST:event_combo_PaymentModeActionPerformed

    private void comboCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCourseActionPerformed
        txt_courseName.setText(comboCourse.getSelectedItem().toString());
    }//GEN-LAST:event_comboCourseActionPerformed

    private void txt_year2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_year2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_year2ActionPerformed

    private void panelHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMouseClicked
        home home = new home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelHomeMouseClicked

    private void panelSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchMouseClicked
        SearchRecord search = new SearchRecord();
        search.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelSearchMouseClicked

    private void panelEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditMouseClicked
        EditCourse course = new EditCourse();
        course.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelEditMouseClicked

    private void panelCourseListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCourseListMouseClicked
        EditCourse edit = new EditCourse();
        edit.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelCourseListMouseClicked

    private void panelViewAllRecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewAllRecordMouseClicked
        ViewAllRecords record = new ViewAllRecords();
        record.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_panelViewAllRecordMouseClicked

    private void panelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBackMouseClicked
        home home = new home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelBackMouseClicked

    private void panelLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseClicked
        System.exit(0);
    }//GEN-LAST:event_panelLogoutMouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new AddFees().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnCourseList;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnHome1;
    private javax.swing.JLabel btnHome2;
    private javax.swing.JLabel btnHome3;
    private javax.swing.JLabel btnHome4;
    private javax.swing.JLabel btnHome5;
    private javax.swing.JLabel btnHome6;
    private javax.swing.JLabel btnHome7;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JLabel btnViewAllRecord;
    private javax.swing.JButton btn_print;
    private javax.swing.JComboBox<String> comboCourse;
    private javax.swing.JComboBox<String> combo_PaymentMode;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbl_DDno;
    private javax.swing.JLabel lbl_bankName;
    private javax.swing.JLabel lbl_chequeno;
    private javax.swing.JLabel lbl_recievedFrom;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelChild;
    private javax.swing.JPanel panelCourseList;
    private javax.swing.JPanel panelEdit;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelParent;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JPanel panelViewAllRecord;
    private javax.swing.JPanel panelsideBar;
    private javax.swing.JTextField txt_ChequeNo;
    private javax.swing.JTextField txt_DDNo;
    private javax.swing.JLabel txt_GSTNo;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_bankName;
    private javax.swing.JTextField txt_cgst;
    private javax.swing.JTextField txt_courseName;
    private javax.swing.JTextField txt_receiptNo;
    private javax.swing.JTextField txt_recievedFrom;
    private javax.swing.JTextArea txt_remark;
    private javax.swing.JTextField txt_rollNo;
    private javax.swing.JTextField txt_sgst;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_total_in_words;
    private javax.swing.JTextField txt_year1;
    private javax.swing.JTextField txt_year2;
    // End of variables declaration//GEN-END:variables
}
