/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fee_management_system;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import static javax.management.Query.value;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author souls
 */
public class GenerateReport extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GenerateReport.class.getName());

    /**
     * Creates new form GenerateReport
     */
    DefaultTableModel model;
    public GenerateReport() {
        initComponents();
        fillComboBox();
    }
    public void fillComboBox()
    {
        try{
            Class.forName("org.apache.derby.iapi.jdbc.AutoloadedDriver");
            Connection con = java.sql.DriverManager.getConnection("jdbc:derby://localhost:1527/fee_management","soham","2986");
            java.sql.PreparedStatement pst=con.prepareStatement("Select cname from course");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                combo_courseDetails.addItem(rs.getString("cname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setRecordsToTable()
            
    {
        String cname = combo_courseDetails.getSelectedItem().toString();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = dateFormat.format(dateChooser_from.getDate());
        String toDate = dateFormat.format(dateChooser_to.getDate());
        
        Float totalamount = 0.0f;
        try {
            Connection con = DBConnection.getconnection();
            PreparedStatement pst = con.prepareStatement("select * from fees_details where date between ? and ? and course_name =?");
            pst.setString(1, fromDate);
            pst.setString(2, toDate);
            pst.setString(3, cname);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                String receiptNo = rs.getString("reciept_no");
                String rollNo = rs.getString("roll_no");
                String studentName = rs.getString("student_name");
                String courseName = rs.getString("course_name");
                float amount = rs.getFloat("total_amount");
                String remark = rs.getString("remark");
                
                totalamount = totalamount + amount;
                
                
                Object[] obj = {receiptNo,rollNo,studentName,courseName,amount,remark};
     
                model = (DefaultTableModel)tbl_feesDetails.getModel();
                model.addRow(obj);
            }
            lbl_course.setText(cname);
            lbl_totalAmount.setText(totalamount.toString());
            lbl_totalInWords.setText(number_to_word_converter.convert(totalamount.intValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void clearTable()
    {
        DefaultTableModel model = (DefaultTableModel)tbl_feesDetails.getModel();
        model.setRowCount(1);
    }
    
    public void exportToExcel(){
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet ws = wb.createSheet();
        DefaultTableModel model = (DefaultTableModel)tbl_feesDetails.getModel();
        TreeMap<String,Object[]> map = new TreeMap<>();
        
        map.put("0", new Object[]{model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),model.getColumnName(3),
        model.getColumnName(4),model.getColumnName(5)});
        for (int i = 1; i < model.getRowCount(); i++) {
            map.put(Integer.toString(i), new Object[]{model.getValueAt(i, 0),model.getValueAt(i, 1),model.getValueAt(i, 2),
            model.getValueAt(i, 3),model.getValueAt(i, 4),model.getValueAt(i, 5)});
        }
        
        Set<String> id = map.keySet();
        
        XSSFRow fRow;
        
        int rowId = 0;
        
        for(String key : id){
            fRow = ws.createRow(rowId++);
            
            Object[] value = map.get(key);
            
            int cellId = 0;
            
            for (Object object : value) {
                XSSFCell cell = fRow.createCell(cellId++);
                cell.setCellValue(object.toString());
            }
            
        }
        try(FileOutputStream fos = new FileOutputStream(new File(txt_filePath.getText()))) {
            
            wb.write(fos);
            JOptionPane.showMessageDialog(this, "File exported successfully : "+txt_filePath.getText());
        } catch(Exception e) {
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
        jPanel1 = new javax.swing.JPanel();
        combo_courseDetails = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateChooser_to = new com.toedter.calendar.JDateChooser();
        dateChooser_from = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txt_filePath = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_feesDetails = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lbl_totalInWords = new javax.swing.JLabel();
        lbl_totalAmount = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

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

        btnHome1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnHome1.setForeground(new java.awt.Color(255, 255, 255));
        btnHome1.setText("Home");
        panelHome.add(btnHome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 70, 40));

        btnHome2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHome2.setForeground(new java.awt.Color(255, 255, 255));
        btnHome2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/home.png"))); // NOI18N
        panelHome.add(btnHome2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 70));

        panelsideBar.add(panelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 240, 70));

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

        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search Record");
        panelSearch.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 160, 40));

        btnHome3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHome3.setForeground(new java.awt.Color(255, 255, 255));
        btnHome3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/search2.png"))); // NOI18N
        panelSearch.add(btnHome3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 70));

        panelsideBar.add(panelSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 240, 70));

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

        btnEdit.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit Course");
        panelEdit.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 140, 40));

        btnHome4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHome4.setForeground(new java.awt.Color(255, 255, 255));
        btnHome4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/edit2.png"))); // NOI18N
        panelEdit.add(btnHome4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 70));

        panelsideBar.add(panelEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 240, 70));

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

        btnCourseList.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnCourseList.setForeground(new java.awt.Color(255, 255, 255));
        btnCourseList.setText("Course List");
        panelCourseList.add(btnCourseList, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 130, 40));

        btnHome5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHome5.setForeground(new java.awt.Color(255, 255, 255));
        btnHome5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/list_1.png"))); // NOI18N
        panelCourseList.add(btnHome5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 70));

        panelsideBar.add(panelCourseList, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 240, 70));

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

        btnViewAllRecord.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnViewAllRecord.setForeground(new java.awt.Color(255, 255, 255));
        btnViewAllRecord.setText("View All Records");
        panelViewAllRecord.add(btnViewAllRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 180, 40));

        btnHome6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHome6.setForeground(new java.awt.Color(255, 255, 255));
        btnHome6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/view all record.png"))); // NOI18N
        panelViewAllRecord.add(btnHome6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 70));

        panelsideBar.add(panelViewAllRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 240, 70));

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

        btnBack.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        panelBack.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 70, 40));

        btnHome7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHome7.setForeground(new java.awt.Color(255, 255, 255));
        btnHome7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/left-arrow.png"))); // NOI18N
        panelBack.add(btnHome7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 70));

        panelsideBar.add(panelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 580, 240, 70));

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

        btnLogout.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        panelLogout.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 100, 40));

        btnHome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management_system/images/logout.png"))); // NOI18N
        panelLogout.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 70));

        panelsideBar.add(panelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 680, 240, 70));

        getContentPane().add(panelsideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 850));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combo_courseDetails.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(combo_courseDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 280, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Select Course :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 160, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Select Date:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 160, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("From Date:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 110, 30));
        jPanel1.add(dateChooser_to, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 180, 30));
        jPanel1.add(dateChooser_from, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 180, 30));

        jButton1.setBackground(new java.awt.Color(0, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("EXPORT TO EXCEL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, 190, 40));

        jButton2.setBackground(new java.awt.Color(0, 51, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("SUBMIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 130, 40));
        jPanel1.add(txt_filePath, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 340, 40));

        jButton3.setBackground(new java.awt.Color(0, 51, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("PRINT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 130, 40));

        jButton4.setBackground(new java.awt.Color(0, 51, 51));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("BROWSE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 270, 130, 40));

        tbl_feesDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Receipt no", "Roll no", "Student name", "Course", "Amount", "Remark"
            }
        ));
        jScrollPane1.setViewportView(tbl_feesDetails);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 1130, 440));

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_totalInWords.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_totalInWords.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_totalInWords, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 440, 30));

        lbl_totalAmount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_totalAmount.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_totalAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 220, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText(" Course Selected :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 160, 30));

        lbl_course.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_course.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 150, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText(" Total Amount Collected :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 220, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText(" Total Amount in Words :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 480, 230));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("To Date:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, 90, 30));

        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Generate Report");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 390, 70));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 520, 10));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 1230, 850));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panelHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMouseClicked
        home home = new home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelHomeMouseClicked

    private void panelHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMouseEntered
        Color clr= new Color(0,153,153);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_panelHomeMouseEntered

    private void panelHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMouseExited
        Color clr= new Color(0,103,103);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_panelHomeMouseExited

    private void panelSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchMouseClicked
        SearchRecord record = new SearchRecord();
        record.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelSearchMouseClicked

    private void panelSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchMouseEntered
        Color clr= new Color(0,153,153);
        panelSearch.setBackground(clr);
    }//GEN-LAST:event_panelSearchMouseEntered

    private void panelSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchMouseExited
        Color clr= new Color(0,103,103);
        panelSearch.setBackground(clr);
    }//GEN-LAST:event_panelSearchMouseExited

    private void panelEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditMouseClicked
        EditCourse course = new EditCourse();
        course.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelEditMouseClicked

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

    private void panelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBackMouseClicked
        home home = new home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelBackMouseClicked

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        exportToExcel();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clearTable();
        setRecordsToTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        SimpleDateFormat Date_Format = new SimpleDateFormat("YYYY-MM-dd"); 
        String datefrom=  Date_Format.format(dateChooser_from.getDate());
      String dateto=  Date_Format.format(dateChooser_to.getDate());
       
        MessageFormat header=new MessageFormat("Report From "+datefrom+" To " +dateto);
MessageFormat footer=new MessageFormat("page{0,number,integer}");
        try {
            tbl_feesDetails.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            
        } catch (Exception e) {
            e.getMessage();
        } 

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
        
        try {
            File f = fileChooser.getSelectedFile();
            String path = f.getAbsolutePath();
            path = path+"_"+date+".xlsx";
            txt_filePath.setText(path);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void panelCourseListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCourseListMouseClicked
        EditCourse course = new EditCourse();
        course.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelCourseListMouseClicked

    private void panelViewAllRecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewAllRecordMouseClicked
        ViewAllRecords view = new ViewAllRecords();
        view.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelViewAllRecordMouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new GenerateReport().setVisible(true));
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
    private javax.swing.JComboBox<String> combo_courseDetails;
    private com.toedter.calendar.JDateChooser dateChooser_from;
    private com.toedter.calendar.JDateChooser dateChooser_to;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_totalAmount;
    private javax.swing.JLabel lbl_totalInWords;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelCourseList;
    private javax.swing.JPanel panelEdit;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JPanel panelViewAllRecord;
    private javax.swing.JPanel panelsideBar;
    private javax.swing.JTable tbl_feesDetails;
    private javax.swing.JTextField txt_filePath;
    // End of variables declaration//GEN-END:variables
}
