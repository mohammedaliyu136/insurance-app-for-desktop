/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mohammed.aliyu1
 */
public class Manager extends javax.swing.JFrame {
  private CardLayout laycard;
        String currentID;
        int idd;
        DefaultTableModel dm;
        Vector originalTableModel_Customer;
        Vector originalTableModel_Agent;
        Vector originalTableModel_Policy;
        String var;
    

    /**
     * Creates new form Manager
     */
    public Manager() throws SQLException, ClassNotFoundException {
        initComponents();
        
        laycard = new CardLayout();
        main.setLayout(laycard);
        main.add("policyPanel", policyPanel);
        main.add("agentPanel", agentPanel);
        main.add("customerPanel", customerPanel);
        main.add("profilePanel", profilePanel);
        laycard.show(main, "agentPanel");
        
        CreateColumnsCustomer();
        comBoOption();
        CreateColumnsAgent();
        CreateColumnsPolicy();
        
        originalTableModel_Customer = (Vector) ((DefaultTableModel) jTable2.getModel()).getDataVector().clone();
        originalTableModel_Agent = (Vector) ((DefaultTableModel) jTable1.getModel()).getDataVector().clone();
       }

     private void CreateColumnsCustomer() throws SQLException, ClassNotFoundException
    {
        //connection
            Connection conn = (Connection) DriverManager.getConnection(
                     "jdbc:mysql://localhost:3306/projectdb",
                     "root",
                     "");
       
        
        //get table model
        dm=(DefaultTableModel) jTable2.getModel();
        
        //add cols
        dm.addColumn("ID");
        dm.addColumn("Name");
        dm.addColumn("Contact Address");
//        dm.addColumn("Date");
        dm.addColumn("Nationality");
        dm.addColumn("Email Address");
        dm.addColumn("Phone Number");
        dm.addColumn("Gender");
        dm.addColumn("occupation");
        dm.addColumn("bloodgroup");
        dm.addColumn("policy");
       
        
        
        Statement stmt = conn.createStatement();
        
        // the query
        ResultSet rs = 
        stmt.executeQuery("SELECT id, name, contact_address,  nationality, email_address, phone_number, gender, occupation, bloodgroup, policy FROM customerbio");
        

     // Loop through the ResultSet and transfer in the Model
        java.sql.ResultSetMetaData rsmd = rs.getMetaData();
        int colNo = rsmd.getColumnCount();
        while(rs.next()){
           Object[] objects = new Object[colNo];
      // tanks to umit ozkan for the bug fix!
           for(int i=0;i<colNo;i++){
              objects[i]=rs.getObject(i+1);
           }
         dm.addRow(objects);
        }
        jTable2.setModel(dm);
//        
//        ResultSet profile = stmt.executeQuery("select * from agent_bio where id=1 ");     //+agentID.getText()+";");
//
//         agentName.setText(profile.getString("name"));
//         agentContact.setText(profile.getString("contact_address"));
//         agentNationality.setText(profile.getString("nationality"));
//         agentEmail.setText(profile.getString("email_address"));
//         agentPhone.setText(profile.getString("phone_number"));
////         agentGender.setText("");
////         agentPassword.setText("");
         
         
        ResultSet idMax = stmt.executeQuery("select max(id) from customerbio");
           idd = 0; 
          if ( idMax.next() ){
             idd = idMax.getInt(1);  
                 }
          
//        conn.close();
       
    }
    
      public void searchTableContentss(String searchString, JTable table, Vector originalTableModel) {
    DefaultTableModel currtableModel = (DefaultTableModel) table.getModel();
    //To empty the table before search
    currtableModel.setRowCount(0);
    //To search for contents from original table content
    for (Object rows : originalTableModel) {
        Vector rowVector = (Vector) rows;
        for (Object column : rowVector) {
            if (column.toString().contains(searchString)) {
                //content found so adding to table
                currtableModel.addRow(rowVector);
                break;
            }
        }

    }
}
     
    private void CreateColumnsAgent() throws SQLException, ClassNotFoundException
    {
        //connection
        Connection conn = (Connection) DriverManager.getConnection(
                     "jdbc:mysql://localhost:3306/projectdb",
                     "root",
                     "");
       
        
        //get table model
        dm=(DefaultTableModel) jTable1.getModel();
        
        //add cols
        dm.addColumn("ID");
        dm.addColumn("Name");
        dm.addColumn("Contact Address");
        dm.addColumn("Nationality");
        dm.addColumn("Email Address");
        dm.addColumn("Phone Number");
        dm.addColumn("Gender");
       
       
        
        
        Statement stmt = conn.createStatement();
        
        // the query
        ResultSet rs = 
        stmt.executeQuery("SELECT id, name, contact_address,  nationality, email_address, phone_number, gender FROM agent_bio");
        

     // Loop through the ResultSet and transfer in the Model
        java.sql.ResultSetMetaData rsmd = rs.getMetaData();
        int colNo = rsmd.getColumnCount();
        while(rs.next()){
           Object[] objects = new Object[colNo];
      // tanks to umit ozkan for the bug fix!
           for(int i=0;i<colNo;i++){
              objects[i]=rs.getObject(i+1);
           }
         dm.addRow(objects);
        }
        jTable1.setModel(dm);
//        
//        ResultSet profile = stmt.executeQuery("select * from agent_bio where id=1 ");     //+agentID.getText()+";");
//
//         agentName.setText(profile.getString("name"));
//         agentContact.setText(profile.getString("contact_address"));
//         agentNationality.setText(profile.getString("nationality"));
//         agentEmail.setText(profile.getString("email_address"));
//         agentPhone.setText(profile.getString("phone_number"));
////         agentGender.setText("");
////         agentPassword.setText("");
         
         
        ResultSet idMax = stmt.executeQuery("select max(id) from customerbio");
           idd = 0; 
          if ( idMax.next() ){
             idd = idMax.getInt(1);  
                 }
          
//        conn.close();
       
    }
    
    private void CreateColumnsPolicy() throws SQLException, ClassNotFoundException
    {
        //connection
        Connection conn = (Connection) DriverManager.getConnection(
                     "jdbc:mysql://localhost:3306/projectdb",
                     "root",
                     "");
       
        
        //get table model
        dm=(DefaultTableModel) jTable3.getModel();
        
        //add cols
        dm.addColumn("ID");
        dm.addColumn("Name");
        dm.addColumn("Discription");
        dm.addColumn("Price");
       
        
        
        Statement stmt = conn.createStatement();
        
        // the query
        ResultSet rs = 
        stmt.executeQuery("SELECT * FROM policy");
        

     // Loop through the ResultSet and transfer in the Model
        java.sql.ResultSetMetaData rsmd = rs.getMetaData();
        int colNo = rsmd.getColumnCount();
        while(rs.next()){
           Object[] objects = new Object[colNo];
      // tanks to umit ozkan for the bug fix!
           for(int i=0;i<colNo;i++){
              objects[i]=rs.getObject(i+1);
           }
         dm.addRow(objects);
        }
        jTable3.setModel(dm);
//        
//        ResultSet profile = stmt.executeQuery("select * from agent_bio where id=1 ");     //+agentID.getText()+";");
//
//         agentName.setText(profile.getString("name"));
//         agentContact.setText(profile.getString("contact_address"));
//         agentNationality.setText(profile.getString("nationality"));
//         agentEmail.setText(profile.getString("email_address"));
//         agentPhone.setText(profile.getString("phone_number"));
////         agentGender.setText("");
////         agentPassword.setText("");
         
         
        ResultSet idMax = stmt.executeQuery("select max(id) from customerbio");
           idd = 0; 
          if ( idMax.next() ){
             idd = idMax.getInt(1);  
                 }
          
//        conn.close();
       
    }
    
    
    // add row data
    
      private void update(
//                          String name, 
//                          String contact_address,
//                          String date_of_birth,
//                          String nationality,
//                          String email_address,
//                          String phone_number,
//                          String gender,
//                          String occupation,
//                          String bloodgroup,
//                          String policy
      ) 
    {
        
 
        //connection
        
//
//         String insertSQL = "UPDATE customerbio SET id=?, name=?, contact_address=?, date_of_birth=?, nationality=?, email_address=?, phone_number=?, gender=?, occupation=?, bloodgroup=?, policy=? WHERE id = ? ";
//         PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);
//                 preparedStatement.setString(1, name);
//                 preparedStatement.setString(2, contact_address);
//                 preparedStatement.setString(3, date_of_birth);
//                 preparedStatement.setString(4, nationality);
//                 preparedStatement.setString(5, email_address);
//                 preparedStatement.setString(6, phone_number);
//                 preparedStatement.setString(7, gender); 
//                 preparedStatement.setString(8, occupation);
//                 preparedStatement.setString(9, bloodgroup);
//                 preparedStatement.setString(10, policy);
//                 preparedStatement.setString(11, currentID);
//         preparedStatement.executeUpdate();
//       String sql = "UPDATE Customers SET ContactName='Alfred Schmidt', City='Hamburg' WHERE CustomerName='Alfreds Futterkiste'; ";
       
        
          dm.setValueAt(nameTxt.getText(), jTable1.getSelectedRow(), 1);
          dm.setValueAt(caddressTxt.getText(), jTable1.getSelectedRow(), 2);
//          dm.setValueAt(dateTxt.getText(), jTable1.getSelectedRow(), 3);
          dm.setValueAt(nationalityTxt.getText(), jTable1.getSelectedRow(), 3);
          dm.setValueAt(emailTxt.getText(), jTable1.getSelectedRow(), 4);
          dm.setValueAt(phoneTxt.getText(), jTable1.getSelectedRow(), 5);
          dm.setValueAt(bloodgroup.getSelectedItem(), jTable1.getSelectedRow(), 8);
          dm.setValueAt(gender.getSelectedItem(), jTable1.getSelectedRow(), 6);
          dm.setValueAt(policyBox.getSelectedItem(), jTable1.getSelectedRow(), 9);
         
       clearFields();
        
   }
      
   private void clearFields(){
         idTxt.setText("automated");
        nameTxt.setText("");
        caddressTxt.setText("");
        nationalityTxt.setText("");
        emailTxt.setText("");
        phoneTxt.setText("");
   }
      
       private void comBoOption() throws ClassNotFoundException, SQLException {
        
        //connection
        Class.forName("org.sqlite.JDBC");
         Connection conn =
         DriverManager.getConnection("jdbc:sqlite:C:\\Users\\mohammed.aliyu1\\Documents\\cie231\\ProgWiz_v2\\AgentBio.sqlite");
        
    Statement stmt = conn.createStatement();
        
        // the query
        ResultSet rs = 
        stmt.executeQuery("SELECT policy FROM policyinfo");
        
    while(rs.next())
    {
        policyBox.addItem(rs.getString("policy"));
        }
    }
       
       
     private void delete() {
//       Class.forName("org.sqlite.JDBC");
//         Connection conn =
//         DriverManager.getConnection("jdbc:sqlite:C:\\Users\\mohammed.aliyu1\\Documents\\cie231\\ProgWiz_v2\\AgentBio.sqlite");
//       
//       String sql = "DELETE FROM customerBio WHERE customerID=? ;";
//        PreparedStatement preparedStatement = conn.prepareStatement(sql);
//                 preparedStatement.setString(1, currentID);
//                 preparedStatement.executeUpdate();
        dm.removeRow(jTable1.getSelectedRow());
        clearFields();
       
   }
     
    private void populate(){
         idd = idd+1;  
        String name_var = nameTxt.getText();
        String address_var = caddressTxt.getText();
        String nationality_var = nationalityTxt.getText();
        String email_var = emailTxt.getText();
        String phone_var = phoneTxt.getText();
        String bloodgroup_var = bloodgroup.getSelectedItem().toString();
        String gender_var = gender.getSelectedItem().toString();
        String policy_var = policyBox.getSelectedItem().toString();
        String agentid = Integer.toString(idd);
        
        String[] rowData={agentid,name_var,address_var,nationality_var,email_var,phone_var,gender_var,bloodgroup_var,policy_var};
        dm.addRow(rowData);
        clearFields();
    }

    
       public void close(){
        WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
                }
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        main = new javax.swing.JPanel();
        agentPanel = new javax.swing.JPanel();
        tablePanel = new javax.swing.JPanel();
        searchAgent = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();
        idTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        nameTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        caddressTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        nationalityTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        emailTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        phoneTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        addRow = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        agentGender = new javax.swing.JComboBox();
        profilePanel = new javax.swing.JPanel();
        policyPanel = new javax.swing.JPanel();
        tablePanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        formPanel3 = new javax.swing.JPanel();
        idTxt3 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        policyName = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        policyDiscription = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        policyPrice = new javax.swing.JTextField();
        clearButton2 = new javax.swing.JButton();
        addRow2 = new javax.swing.JButton();
        updateBtn3 = new javax.swing.JButton();
        removeBtn2 = new javax.swing.JButton();
        customerPanel = new javax.swing.JPanel();
        tablePanel1 = new javax.swing.JPanel();
        customerSearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        formPanel2 = new javax.swing.JPanel();
        idTxt2 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        nameTxt2 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        caddressTxt2 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        nationalityTxt2 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        occupationTxt2 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        emailTxt2 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        phoneTxt2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        policyBox = new javax.swing.JComboBox();
        clearButton1 = new javax.swing.JButton();
        addRow1 = new javax.swing.JButton();
        updateBtn2 = new javax.swing.JButton();
        removeBtn1 = new javax.swing.JButton();
        gender = new javax.swing.JComboBox();
        bloodgroup = new javax.swing.JComboBox();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        agentMenu = new javax.swing.JMenuItem();
        customerMenu = new javax.swing.JMenuItem();
        policyMenu = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        updateProfileMenu = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        main.setLayout(new java.awt.CardLayout());

        tablePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        searchAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAgentActionPerformed(evt);
            }
        });
        searchAgent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchAgentKeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Export");

        jLabel4.setText("Agent information");

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 105, Short.MAX_VALUE))
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(tablePanelLayout.createSequentialGroup()
                        .addComponent(searchAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        formPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        idTxt.setEditable(false);
        idTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTxtActionPerformed(evt);
            }
        });

        jLabel11.setText("ID");

        nameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTxtActionPerformed(evt);
            }
        });

        jLabel1.setText("Name");

        jLabel2.setText("Contact Address");

        caddressTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caddressTxtActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel5.setText("Nationality");

        nationalityTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nationalityTxtActionPerformed(evt);
            }
        });

        jLabel7.setText("Email Address");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel8.setText("Phone Number");

        jLabel9.setText("Sex");

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        addRow.setText("Add");
        addRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRowActionPerformed(evt);
            }
        });

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        removeBtn.setText("Remove");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        agentGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F" }));

        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                        .addGap(0, 180, Short.MAX_VALUE)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                                .addComponent(addRow, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(clearButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(removeBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(formPanelLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phoneTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(emailTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nationalityTxt, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(formPanelLayout.createSequentialGroup()
                                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)))
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(formPanelLayout.createSequentialGroup()
                                        .addComponent(agentGender, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(nameTxt)
                                    .addComponent(idTxt)
                                    .addComponent(caddressTxt))))))
                .addContainerGap())
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idTxt)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(caddressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nationalityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(phoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agentGender, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addRow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout agentPanelLayout = new javax.swing.GroupLayout(agentPanel);
        agentPanel.setLayout(agentPanelLayout);
        agentPanelLayout.setHorizontalGroup(
            agentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1154, Short.MAX_VALUE)
            .addGroup(agentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(agentPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        agentPanelLayout.setVerticalGroup(
            agentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
            .addGroup(agentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(agentPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(agentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(19, Short.MAX_VALUE)))
        );

        main.add(agentPanel, "card2");

        javax.swing.GroupLayout profilePanelLayout = new javax.swing.GroupLayout(profilePanel);
        profilePanel.setLayout(profilePanelLayout);
        profilePanelLayout.setHorizontalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1154, Short.MAX_VALUE)
        );
        profilePanelLayout.setVerticalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );

        main.add(profilePanel, "card3");

        tablePanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jButton4.setText("Export");

        javax.swing.GroupLayout tablePanel2Layout = new javax.swing.GroupLayout(tablePanel2);
        tablePanel2.setLayout(tablePanel2Layout);
        tablePanel2Layout.setHorizontalGroup(
            tablePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablePanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
            .addGroup(tablePanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 36, Short.MAX_VALUE))
        );
        tablePanel2Layout.setVerticalGroup(
            tablePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
        );

        formPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        idTxt3.setEditable(false);
        idTxt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTxt3ActionPerformed(evt);
            }
        });

        jLabel34.setText("ID");

        jLabel35.setText("Name");

        jLabel36.setText("Discription");

        policyDiscription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                policyDiscriptionActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel41.setText("Price");

        clearButton2.setText("Clear");
        clearButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton2ActionPerformed(evt);
            }
        });

        addRow2.setText("Add");
        addRow2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRow2ActionPerformed(evt);
            }
        });

        updateBtn3.setText("Update");
        updateBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtn3ActionPerformed(evt);
            }
        });

        removeBtn2.setText("Remove");
        removeBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formPanel3Layout = new javax.swing.GroupLayout(formPanel3);
        formPanel3.setLayout(formPanel3Layout);
        formPanel3Layout.setHorizontalGroup(
            formPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanel3Layout.createSequentialGroup()
                .addGroup(formPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanel3Layout.createSequentialGroup()
                        .addGap(0, 180, Short.MAX_VALUE)
                        .addGroup(formPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanel3Layout.createSequentialGroup()
                                .addComponent(addRow2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(updateBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(clearButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(removeBtn2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(formPanel3Layout.createSequentialGroup()
                        .addGroup(formPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanel3Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(formPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(formPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(policyPrice, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(policyName)
                            .addComponent(policyDiscription)
                            .addComponent(idTxt3))))
                .addContainerGap())
        );
        formPanel3Layout.setVerticalGroup(
            formPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(formPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idTxt3)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(policyName, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(policyDiscription, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(formPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(policyPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addRow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateBtn3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removeBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout policyPanelLayout = new javax.swing.GroupLayout(policyPanel);
        policyPanel.setLayout(policyPanelLayout);
        policyPanelLayout.setHorizontalGroup(
            policyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1154, Short.MAX_VALUE)
            .addGroup(policyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(policyPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tablePanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(formPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        policyPanelLayout.setVerticalGroup(
            policyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
            .addGroup(policyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(policyPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(policyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(formPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tablePanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        main.add(policyPanel, "card2");

        tablePanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        customerSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                customerSearchKeyReleased(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton3.setText("Export");

        jLabel3.setText("Customer information");

        javax.swing.GroupLayout tablePanel1Layout = new javax.swing.GroupLayout(tablePanel1);
        tablePanel1.setLayout(tablePanel1Layout);
        tablePanel1Layout.setHorizontalGroup(
            tablePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 105, Short.MAX_VALUE))
            .addGroup(tablePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tablePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablePanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(tablePanel1Layout.createSequentialGroup()
                        .addComponent(customerSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tablePanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tablePanel1Layout.setVerticalGroup(
            tablePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        formPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        idTxt2.setEditable(false);
        idTxt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTxt2ActionPerformed(evt);
            }
        });

        jLabel23.setText("ID");

        jLabel24.setText("Name");

        jLabel25.setText("Contact Address");

        caddressTxt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caddressTxt2ActionPerformed(evt);
            }
        });

        jLabel26.setText("Date");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel27.setText("Nationality");

        nationalityTxt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nationalityTxt2ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel28.setText("Occupation");

        occupationTxt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                occupationTxt2ActionPerformed(evt);
            }
        });

        jLabel29.setText("Email Address");

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel30.setText("Phone Number");

        jLabel31.setText("Sex");

        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel32.setText("Blood Group");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel33.setText("Policy");

        policyBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                policyBoxActionPerformed(evt);
            }
        });

        clearButton1.setText("Clear");
        clearButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton1ActionPerformed(evt);
            }
        });

        addRow1.setText("Add");
        addRow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRow1ActionPerformed(evt);
            }
        });

        updateBtn2.setText("Update");
        updateBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtn2ActionPerformed(evt);
            }
        });

        removeBtn1.setText("Remove");
        removeBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtn1ActionPerformed(evt);
            }
        });

        gender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F" }));

        bloodgroup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "AB", "B", "O" }));

        javax.swing.GroupLayout formPanel2Layout = new javax.swing.GroupLayout(formPanel2);
        formPanel2.setLayout(formPanel2Layout);
        formPanel2Layout.setHorizontalGroup(
            formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanel2Layout.createSequentialGroup()
                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanel2Layout.createSequentialGroup()
                        .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanel2Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(formPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel25)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTxt2)
                            .addComponent(idTxt2)
                            .addComponent(caddressTxt2)))
                    .addGroup(formPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phoneTxt2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(emailTxt2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(occupationTxt2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nationalityTxt2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)))
                            .addGroup(formPanel2Layout.createSequentialGroup()
                                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bloodgroup, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(formPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(policyBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanel2Layout.createSequentialGroup()
                        .addComponent(addRow1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(updateBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(clearButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        formPanel2Layout.setVerticalGroup(
            formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idTxt2)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameTxt2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(formPanel2Layout.createSequentialGroup()
                        .addComponent(caddressTxt2)
                        .addGap(5, 5, 5)))
                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(formPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nationalityTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(occupationTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phoneTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(formPanel2Layout.createSequentialGroup()
                        .addComponent(bloodgroup)
                        .addGap(4, 4, 4)))
                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(policyBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clearButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addRow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateBtn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removeBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout customerPanelLayout = new javax.swing.GroupLayout(customerPanel);
        customerPanel.setLayout(customerPanelLayout);
        customerPanelLayout.setHorizontalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1154, Short.MAX_VALUE)
            .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(customerPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tablePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(formPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        customerPanelLayout.setVerticalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
            .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(customerPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(formPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tablePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        main.add(customerPanel, "card2");

        jMenu1.setText("File");

        agentMenu.setText("Agent");
        agentMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agentMenuActionPerformed(evt);
            }
        });
        jMenu1.add(agentMenu);

        customerMenu.setText("Customer");
        customerMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerMenuActionPerformed(evt);
            }
        });
        jMenu1.add(customerMenu);

        policyMenu.setText("Policy");
        policyMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                policyMenuActionPerformed(evt);
            }
        });
        jMenu1.add(policyMenu);

        jMenuItem1.setText("Logout");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        updateProfileMenu.setText("Update Profile");
        updateProfileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProfileMenuActionPerformed(evt);
            }
        });
        jMenu2.add(updateProfileMenu);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAgentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchAgentActionPerformed

    private void searchAgentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchAgentKeyReleased
        //        searchTableContents(TextField.getText());
        searchTableContentss(searchAgent.getText(), jTable1, originalTableModel_Agent );
    }//GEN-LAST:event_searchAgentKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        currentID = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
        idTxt.setText(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString());
        nameTxt.setText(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString());
        caddressTxt.setText(jTable1.getValueAt(jTable1.getSelectedRow(),2).toString());
        nationalityTxt.setText(jTable1.getValueAt(jTable1.getSelectedRow(),3).toString());
        emailTxt.setText(jTable1.getValueAt(jTable1.getSelectedRow(),4).toString());
        phoneTxt.setText(jTable1.getValueAt(jTable1.getSelectedRow(),5).toString());
        agentGender.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(),6).toString());
        policyBox.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(),8).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void idTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxtActionPerformed
        // TODO add your handling code here:
        //        String id = id.getText();
    }//GEN-LAST:event_idTxtActionPerformed

    private void caddressTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caddressTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caddressTxtActionPerformed

    private void nationalityTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nationalityTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nationalityTxtActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        clearFields();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void addRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRowActionPerformed

        //        try {
            //            Populate(nameTxt.getText(),caddressTxt.getText(),dateTxt.getText(),nationalityTxt.getText(),occupationTxt.getText(),emailTxt.getText(),phoneTxt.getText(),sexTxt.getText(),bgroupTxt.getText(),policyBox.getSelectedItem().toString());
            //        } catch (ClassNotFoundException ex) {
            //            Logger.getLogger(AgentFrame111.class.getName()).log(Level.SEVERE, null, ex);
            //        } catch (SQLException ex) {
            //            Logger.getLogger(AgentFrame111.class.getName()).log(Level.SEVERE, null, ex);
            //        }
    }//GEN-LAST:event_addRowActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        //dm.setValueAt(idTxt.getText(), jTable1.getSelectedRow(), 1);
        //
        //

        //        try {
            //            update(nameTxt.getText(),caddressTxt.getText(),dateTxt.getText(),nationalityTxt.getText(),occupationTxt.getText(),emailTxt.getText(),phoneTxt.getText(),sexTxt.getText(),bgroupTxt.getText(),policyBox.getSelectedItem().toString());
            //        } catch (ClassNotFoundException ex) {
            //            Logger.getLogger(AgentFrame111.class.getName()).log(Level.SEVERE, null, ex);
            //        } catch (SQLException ex) {
            //            Logger.getLogger(AgentFrame111.class.getName()).log(Level.SEVERE, null, ex);
            //        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        //        try {
            //            // TODO add your handling code here:
            //
            //            delete();
            //        } catch (ClassNotFoundException ex) {
            //            Logger.getLogger(AgentFrame111.class.getName()).log(Level.SEVERE, null, ex);
            //        } catch (SQLException ex) {
            //            Logger.getLogger(AgentFrame111.class.getName()).log(Level.SEVERE, null, ex);
            //        }
    }//GEN-LAST:event_removeBtnActionPerformed

    private void customerSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customerSearchKeyReleased

        searchTableContentss(customerSearch.getText(), jTable2, originalTableModel_Customer );                 
    }//GEN-LAST:event_customerSearchKeyReleased

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        currentID = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
        idTxt.setText(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString());
        nameTxt.setText(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString());
        caddressTxt.setText(jTable1.getValueAt(jTable1.getSelectedRow(),2).toString());
//        dateTxt.setText(jTable1.getValueAt(jTable1.getSelectedRow(),3).toString());
        nationalityTxt.setText(jTable1.getValueAt(jTable1.getSelectedRow(),3).toString());
        emailTxt.setText(jTable1.getValueAt(jTable1.getSelectedRow(),4).toString());
        phoneTxt.setText(jTable1.getValueAt(jTable1.getSelectedRow(),5).toString());
        gender.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(),6).toString());
    }//GEN-LAST:event_jTable2MouseClicked

    private void idTxt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTxt2ActionPerformed

    private void caddressTxt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caddressTxt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caddressTxt2ActionPerformed

    private void nationalityTxt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nationalityTxt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nationalityTxt2ActionPerformed

    private void occupationTxt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_occupationTxt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_occupationTxt2ActionPerformed

    private void policyBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_policyBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_policyBoxActionPerformed

    private void clearButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearButton1ActionPerformed

    private void addRow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRow1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addRow1ActionPerformed

    private void updateBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateBtn2ActionPerformed

    private void removeBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeBtn1ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        
        currentID = jTable3.getValueAt(jTable3.getSelectedRow(),0).toString();
        idTxt3.setText(jTable3.getValueAt(jTable3.getSelectedRow(),0).toString());
        policyName.setText(jTable3.getValueAt(jTable3.getSelectedRow(),1).toString());
        policyDiscription.setText(jTable3.getValueAt(jTable3.getSelectedRow(),2).toString());
        policyPrice.setText(jTable3.getValueAt(jTable3.getSelectedRow(),3).toString());
        
    }//GEN-LAST:event_jTable3MouseClicked

    private void removeBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeBtn2ActionPerformed

    private void updateBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateBtn3ActionPerformed

    private void addRow2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRow2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addRow2ActionPerformed

    private void clearButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearButton2ActionPerformed

    private void policyDiscriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_policyDiscriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_policyDiscriptionActionPerformed

    private void idTxt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxt3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTxt3ActionPerformed

    private void agentMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agentMenuActionPerformed
        laycard.show(main, "agentPanel");
    }//GEN-LAST:event_agentMenuActionPerformed

    private void policyMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_policyMenuActionPerformed
        laycard.show(main, "policyPanel");
    }//GEN-LAST:event_policyMenuActionPerformed

    private void customerMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerMenuActionPerformed
       laycard.show(main, "customerPanel");
    }//GEN-LAST:event_customerMenuActionPerformed

    private void updateProfileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProfileMenuActionPerformed
        laycard.show(main, "profilePanel");
    }//GEN-LAST:event_updateProfileMenuActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
           new Login().setVisible(true);
        close();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void nameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTxtActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Manager().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRow;
    private javax.swing.JButton addRow1;
    private javax.swing.JButton addRow2;
    private javax.swing.JComboBox agentGender;
    private javax.swing.JMenuItem agentMenu;
    private javax.swing.JPanel agentPanel;
    private javax.swing.JComboBox bloodgroup;
    private javax.swing.JTextField caddressTxt;
    private javax.swing.JTextField caddressTxt2;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton clearButton1;
    private javax.swing.JButton clearButton2;
    private javax.swing.JMenuItem customerMenu;
    private javax.swing.JPanel customerPanel;
    private javax.swing.JTextField customerSearch;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JTextField emailTxt2;
    private javax.swing.JPanel formPanel;
    private javax.swing.JPanel formPanel2;
    private javax.swing.JPanel formPanel3;
    private javax.swing.JComboBox gender;
    private javax.swing.JTextField idTxt;
    private javax.swing.JTextField idTxt2;
    private javax.swing.JTextField idTxt3;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JPanel main;
    private javax.swing.JTextField nameTxt;
    private javax.swing.JTextField nameTxt2;
    private javax.swing.JTextField nationalityTxt;
    private javax.swing.JTextField nationalityTxt2;
    private javax.swing.JTextField occupationTxt2;
    private javax.swing.JTextField phoneTxt;
    private javax.swing.JTextField phoneTxt2;
    private javax.swing.JComboBox policyBox;
    private javax.swing.JTextField policyDiscription;
    private javax.swing.JMenuItem policyMenu;
    private javax.swing.JTextField policyName;
    private javax.swing.JPanel policyPanel;
    private javax.swing.JTextField policyPrice;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JButton removeBtn;
    private javax.swing.JButton removeBtn1;
    private javax.swing.JButton removeBtn2;
    private javax.swing.JTextField searchAgent;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JPanel tablePanel1;
    private javax.swing.JPanel tablePanel2;
    private javax.swing.JButton updateBtn;
    private javax.swing.JButton updateBtn2;
    private javax.swing.JButton updateBtn3;
    private javax.swing.JMenuItem updateProfileMenu;
    // End of variables declaration//GEN-END:variables
}
