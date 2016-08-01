package gui;

import basicClasses.Company;
import dataManagement.ConnectionManager;
import dataManagement.SystemData;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class HomePage extends javax.swing.JPanel {
    JFrame  panelHolder;
    SystemData systemData;
    DefaultListModel model;
    
    public HomePage(JFrame  panelHolder, SystemData systemData, boolean isFirstLogin) {
        this.panelHolder = panelHolder;
        this.systemData = systemData;        
        initComponents();    
        ConnectionManager cm = new ConnectionManager();
        EntityManager em = cm.getEntityManager();
        Query query = em.createQuery("Select c  from Company c");
        List<Company> list = query.getResultList();
        Company comp = list.get(0);        
        
        name.setText(comp.getName());
        addressLine.setText("<html>"+ comp.getAddressLine1()
                +(comp.getAddressLine2()==null || comp.getAddressLine2().trim().equals("") 
                        ? "":", "+ comp.getAddressLine2()    )          
                +"<br>"+comp.getCity()+", "+comp.getState() + " " + comp.getZip()
                + "</html>");
        
        cm.close();
        
        
        try{
            Logo.setIcon(new ImageIcon(ImageIO.read( new File("data/eagle.JPG")).
                getScaledInstance(150, 120, Image.SCALE_SMOOTH)));
        }catch(IOException e){
            System.out.println("Company Logo not Found!");
        }
                
        JMenuBar menuBar = new JMenuBar();
        panelHolder.setJMenuBar(menuBar);

        JMenu mnMaintain = new JMenu("Maintain");
        menuBar.add(mnMaintain);
        
        JMenuItem mntmCompany = new JMenuItem("Company");
        mnMaintain.add(mntmCompany);        
        mntmCompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Company Maintenance");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new CompanyMaintenance(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
        
        JMenuItem mntmClient = new JMenuItem("Client");
        mnMaintain.add(mntmClient);
        mntmClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Client Maintenance");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new ClientMaintenance(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });               
        
        JMenuItem mntmEmployee = new JMenuItem("Employee");
        mnMaintain.add(mntmEmployee);
        mntmEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Employee Maintenance");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new EmployeeMaintenance(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
        
        JMenuItem mntmProject = new JMenuItem("Project");
        mnMaintain.add(mntmProject);
        mntmProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Project Maintenance");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new ProjectMaintenance(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
                
        
        JMenu mnReport = new JMenu("Report");
        menuBar.add(mnReport);
        JMenuItem mntmGenerateSchedule = new JMenuItem("Available Employees");
        mnReport.add(mntmGenerateSchedule);
        mntmGenerateSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                panelHolder.setTitle("Available Employees");
//                panelHolder.getContentPane().removeAll();
//		panelHolder.getContentPane().add(new AvailableEmployees(panelHolder, systemData));
//		panelHolder.getContentPane().revalidate();        
            }
        });
        JMenuItem mntmNewMenuItem = new JMenuItem("Hours Clocked");
        mnReport.add(mntmNewMenuItem);
        mntmNewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                panelHolder.setTitle("Hours Clocked");
//                panelHolder.getContentPane().removeAll();
//		panelHolder.getContentPane().add(new HoursClocked(panelHolder, systemData));
//		panelHolder.getContentPane().revalidate();        
            }
        });
        JMenuItem mntmProjectReport= new JMenuItem("Project Report");
        mnReport.add(mntmProjectReport);
        mntmProjectReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                panelHolder.setTitle("Project Report");
//                panelHolder.getContentPane().removeAll();
//		panelHolder.getContentPane().add(new ProjectReport(panelHolder, systemData));
//		panelHolder.getContentPane().revalidate();        
            }
        });

        JMenu mnManage = new JMenu("Manage");
        menuBar.add(mnManage);
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Developers");
        mnManage.add(mntmNewMenuItem_1);
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
//                panelHolder.setTitle("Manage Developers");
//                panelHolder.getContentPane().removeAll();
//		panelHolder.getContentPane().add(new ManageDevelopers(panelHolder, systemData));
//		panelHolder.getContentPane().revalidate();        
            }
        });
        
        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Approve Hours");
        mnManage.add(mntmNewMenuItem_2);
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
//                panelHolder.setTitle("Approve Hours");
//                panelHolder.getContentPane().removeAll();
//		panelHolder.getContentPane().add(new ApproveHours(panelHolder, systemData));
//		panelHolder.getContentPane().revalidate();        
            }
        });
        JMenuItem mntmManageProject = new JMenuItem("Project");
        mnManage.add(mntmManageProject);
        mntmManageProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
//                panelHolder.setTitle("Manage Project");
//                panelHolder.getContentPane().removeAll();
//		panelHolder.getContentPane().add(new ManageProject(panelHolder, systemData));
//		panelHolder.getContentPane().revalidate();        
            }
        });
        
        JMenu mnInvoice = new JMenu("Invoice");
        menuBar.add(mnInvoice);
        JMenuItem mntmGenerateInvoice = new JMenuItem("Generate / Mail");
        mnInvoice.add(mntmGenerateInvoice);
        mntmGenerateInvoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                panelHolder.setTitle("Import Student");
//                panelHolder.getContentPane().removeAll();
//		panelHolder.getContentPane().add(new GenerateInvoice(panelHolder, systemData));
//		panelHolder.getContentPane().revalidate();        
            }
        });
        
        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Save as PDF");
        mnInvoice.add(mntmNewMenuItem_3);
        mntmNewMenuItem_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                panelHolder.setTitle("Save as PDF");
//                panelHolder.getContentPane().removeAll();
//		panelHolder.getContentPane().add(new SaveInvoice(panelHolder, systemData));
//		panelHolder.getContentPane().revalidate();        
            }
        });
                
        JMenu mnCurUser = new JMenu(systemData.getCurrentUser().getName());
        menuBar.add(mnCurUser);
        
        if(true){//user is not accountant
            JMenuItem mntmClockHours = new JMenuItem("Clock Hours");
            mnCurUser.add(mntmClockHours);
            mntmClockHours.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    panelHolder.setTitle("Clock Hours");
//                    panelHolder.getContentPane().removeAll();
//                    panelHolder.getContentPane().add(new ClockHours(panelHolder, systemData));
//                    panelHolder.getContentPane().revalidate();      
                }
            }); 
        }        
        
        JMenuItem mntmChangePassword = new JMenuItem("Change Password");
        mnCurUser.add(mntmChangePassword);
        mntmChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Change Password");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new ChangePassword(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();      
            }
        });   
        
        JMenuItem mntmLogout = new JMenuItem("Logout");
        mnCurUser.add(mntmLogout);
        mntmLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Login Page");
                panelHolder.setJMenuBar(null);
                panelHolder.getContentPane().removeAll();                                
		panelHolder.getContentPane().add(new LogInPanel(panelHolder, systemData.getUserCredentials()));
		panelHolder.getContentPane().revalidate();      
            }
        });     
      
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        addressLine = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        name.setText("Eagle Consulting");

        jLabel3.setText("Address:");

        addressLine.setText("2501 E. Memorial Rd");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(addressLine, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                        .addGap(39, 39, 39)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addressLine, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Welcome to Invoice Generation System");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 30, 28, 0);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel addressLine;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel name;
    // End of variables declaration                   
}
