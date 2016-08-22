package gui;

import basicClasses.Project;
import basicClasses.ClockedHours;

import dataManagement.SystemData;
import dataManagement.ConnectionManager;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClockHours extends javax.swing.JPanel {
    JFrame  panelHolder;
    SystemData systemData;
    DefaultTableModel tableModel;
    List<Project> projectList;
    List<Date> dates;
    Calendar selectedWeekStartDate;
    
    public ClockHours(JFrame  panelHolder, SystemData systemData) {
        this.panelHolder = panelHolder;
        this.systemData = systemData;  
        initComponents();   
        welcome.setText("Welcome "+ systemData.getCurrentUser().getEmployee().getName());
        selectedWeekStartDate = Calendar.getInstance();
        selectedWeekStartDate.set(Calendar.DAY_OF_WEEK, 2);
        
        Calendar calendar = (Calendar)selectedWeekStartDate.clone();
	calendar.set(Calendar.DAY_OF_WEEK, 2);
        Date weekStartDate = new Date(calendar.getTimeInMillis());
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndDate = new Date(calendar.getTimeInMillis());
        jLabel10.setText("Clock Hours for: "+weekStartDate+" to "+weekEndDate);
        
        Object[] columnNames =  {"", 
                                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dates = new ArrayList<>();
        for (int i = 7; i >0; ) {
            Date date = new Date(calendar.getTimeInMillis());
            //columnNames[i] = date.toString();
            dates.add(0, date);
            //System.out.println(date);//"<html>"+ date+"<br>"+ "Monday"+"</html>";;
            --i;
            calendar.add(Calendar.DAY_OF_WEEK, -1);           
        }
        
        ConnectionManager cm = new ConnectionManager();
        EntityManager em = cm.getEntityManager();
        Query query = em.createQuery("Select pp.projectNumber  from ProjectPerson pp "
                + "where pp.isActivated='Yes' and "
                + "pp.personName='"+systemData.getCurrentUser().getEmployee().getName() +"'");
        projectList = query.getResultList();
        for (int i = projectList.size()-1; i >=0; --i) {
            if(projectList.get(i).getStatus().equals("Closed")){
                projectList.remove(i);
            }
        }
        
        Object[][] rowData = new Object[projectList.size()][8];
        int i =0;
        boolean[] isApproved = new boolean[projectList.size()];
        for(Project project: projectList){
            query = em.createQuery("Select ch from ClockedHours ch where ch.projectID= '"+project.getId()
                    +"' and ch.empName='"+  systemData.getCurrentUser().getEmployee().getName()
                    +"' and ch.date between '"+weekStartDate+"' and '"+ weekEndDate+"' order by ch.date");
            List<ClockedHours> hoursList = query.getResultList();
            isApproved[i]= (hoursList==null || hoursList.isEmpty() ? false: hoursList.get(0).isIsApproved());
            rowData[i][0] = project.getName()+" ("+ project.getId()+")";
            if(!hoursList.isEmpty()){
                rowData[i][1] = hoursList.get(0).getHoursWorked();
                rowData[i][2] = hoursList.get(1).getHoursWorked();
                rowData[i][3] = hoursList.get(2).getHoursWorked();
                rowData[i][4] = hoursList.get(3).getHoursWorked();
                rowData[i][5] = hoursList.get(4).getHoursWorked();
                rowData[i][6] = hoursList.get(5).getHoursWorked();
                rowData[i][7] = hoursList.get(6).getHoursWorked();
            } else{
                rowData[i][1] = "0";
                rowData[i][2] = "0";
                rowData[i][3] = "0";
                rowData[i][4] = "0";
                rowData[i][5] = "0";
                rowData[i][6] = "0";
                rowData[i][7] = "0";
            }   
            ++i;
        }
        
        tableModel = new DefaultTableModel(rowData, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return !isApproved[row];
            }
         };
        
        jTable1 = new JTable(tableModel);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.getSelectedRow();
                if(!tableModel.isCellEditable(row, 0)){
                    JOptionPane.showMessageDialog(null, "Hours in this is row already Approved!"
                            + "\nHence, can not be edited!");
                    return;
                }
            }
        }); 
        cm.close();
        
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
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        welcome = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Clock Worked Hours Page");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setName(""); // NOI18N
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addContainerGap())
        );

        welcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcome.setText("Welcome Cecily Hollack ");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Clock hours for  07/18/2016 to 07/22/2016");

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Previous Week");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Next Week");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cancelButton)
                .addGap(18, 18, 18)
                .addComponent(saveButton)
                .addGap(24, 24, 24))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(welcome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        ConnectionManager cm = new ConnectionManager();
        EntityManager em = cm.getEntityManager();
        for (int i = 0; i < projectList.size(); i++) {
            for (int j = 1; j <8 ; j++) {
                int hours = 0;
                try{
                    if(tableModel.getValueAt(i, j).toString()==null ||
                            tableModel.getValueAt(i, j).toString().equals("")){
                        hours =0;                    
                    }
                    else{                        
                        hours = Integer.parseInt(tableModel.getValueAt(i, j).toString());  
                    }                  
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Please enter Integer value for hours");
                    return;
                }

                ClockedHours ch = em.find(ClockedHours.class, projectList.get(i).getId()
                        +systemData.getCurrentUser().getEmployee().getName()+dates.get(j-1));
                if(ch==null){
                    ch = new ClockedHours(projectList.get(i).getId()+systemData.getCurrentUser().getEmployee().getName()
                        +dates.get(j-1), systemData.getCurrentUser().getEmployee(), 
                        systemData.getCurrentUser().getEmployee().getName(), projectList.get(i),
                        projectList.get(i).getId(), hours, dates.get(j-1), false, false, null);
                    em.persist(ch); 
                }
                if(!ch.isIsApproved()){
                    ch.setHoursWorked(hours);                    
                }
            }    
        }
        cm.close();
        JOptionPane.showMessageDialog(null, "Hours saved sucessfully");
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        panelHolder.setTitle("Home Page");
        panelHolder.getContentPane().removeAll();
        panelHolder.getContentPane().add(new HomePage(panelHolder, systemData, false));
        panelHolder.getContentPane().revalidate();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        selectedWeekStartDate.add(Calendar.DAY_OF_WEEK, 7);
        System.out.println("hiiii"+ new Date(selectedWeekStartDate.getTimeInMillis()));
        refreshDisplay();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        selectedWeekStartDate.add(Calendar.DAY_OF_WEEK, -7);
        System.out.println("hiiii"+ new Date(selectedWeekStartDate.getTimeInMillis()));
        refreshDisplay();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void refreshDisplay(){
        Calendar calendar = (Calendar)selectedWeekStartDate.clone();
	calendar.set(Calendar.DAY_OF_WEEK, 2);
        Date weekStartDate = new Date(calendar.getTimeInMillis());
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndDate = new Date(calendar.getTimeInMillis());
        jLabel10.setText("Clock Hours for: "+weekStartDate+" to "+weekEndDate);
        
        Object[] columnNames =  {"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dates = new ArrayList<>();
        for (int i = 7; i >0; ) {
            Date date = new Date(calendar.getTimeInMillis());
            //columnNames[i] = date.toString();
            dates.add(0, date);
            //System.out.println(date);//"<html>"+ date+"<br>"+ "Monday"+"</html>";;
            --i;
            calendar.add(Calendar.DAY_OF_WEEK, -1);           
        }
        
        ConnectionManager cm = new ConnectionManager();
        EntityManager em = cm.getEntityManager();
        Query query = em.createQuery("Select pp.projectNumber  from ProjectPerson pp "
                + "where pp.isActivated='Yes' and "
                + "pp.personName='"+systemData.getCurrentUser().getEmployee().getName() +"'");
        projectList = query.getResultList();
        for (int i = projectList.size()-1; i >=0; --i) {
            if(projectList.get(i).getStatus().equals("Closed")){
                projectList.remove(i);
            }
        }
        
        Object[][] rowData = new Object[projectList.size()][8];
        int i =0;
        boolean[] isApproved = new boolean[projectList.size()];
        for(Project project: projectList){
            query = em.createQuery("Select ch from ClockedHours ch where ch.projectID= '"+project.getId()
                    +"' and ch.empName='"+  systemData.getCurrentUser().getEmployee().getName()
                    +"' and ch.date between '"+weekStartDate+"' and '"+ weekEndDate+"' order by ch.date");
            List<ClockedHours> hoursList = query.getResultList();
            isApproved[i]= (hoursList==null || hoursList.isEmpty() ? false: hoursList.get(0).isIsApproved());
            rowData[i][0] = project.getName()+" ("+ project.getId()+")";
            if(!hoursList.isEmpty()){
                rowData[i][1] = hoursList.get(0).getHoursWorked();
                rowData[i][2] = hoursList.get(1).getHoursWorked();
                rowData[i][3] = hoursList.get(2).getHoursWorked();
                rowData[i][4] = hoursList.get(3).getHoursWorked();
                rowData[i][5] = hoursList.get(4).getHoursWorked();
                rowData[i][6] = hoursList.get(5).getHoursWorked();
                rowData[i][7] = hoursList.get(6).getHoursWorked();
            } else{
                rowData[i][1] = "0";
                rowData[i][2] = "0";
                rowData[i][3] = "0";
                rowData[i][4] = "0";
                rowData[i][5] = "0";
                rowData[i][6] = "0";
                rowData[i][7] = "0";
            }   
            ++i;
        }
        
        tableModel = new DefaultTableModel(rowData, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return !isApproved[row];
            }
         };
        
        jTable1 = new JTable(tableModel);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.getSelectedRow();
                if(!tableModel.isCellEditable(row, 0)){
                    JOptionPane.showMessageDialog(null, "Hours in this is row already Approved!"
                            + "\nHence, can not be edited!");
                    return;
                }
            }
        }); 
        cm.close();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel welcome;
    // End of variables declaration//GEN-END:variables
}
