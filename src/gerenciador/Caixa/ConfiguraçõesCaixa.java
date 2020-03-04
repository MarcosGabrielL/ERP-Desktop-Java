/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.Caixa;

import static gerenciador.Modulos.ConfiguraçãoGeral.jTextField10;
import static gerenciador.Modulos.ConfiguraçãoGeral.recebepeso;
import gerenciador.TelaGestão;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.bean.Caixa;
import model.dao.CaixaDAO;
import static org.junit.runner.Computer.serial;

/**
 *
 * @author Marcos
 */
public class ConfiguraçõesCaixa extends javax.swing.JFrame {

    SerialComm serial = new SerialComm();
    Caixa x = new Caixa();
    private JFrame parent;
     
    public ConfiguraçõesCaixa(JFrame f,Caixa c) {
        this.parent = f;
        
        
        x=c;
        
        initComponents();
        estilojpanel();
        
        jTextField11.setText(x.getLoja());
        jTextField12.setText(""+x.getIdCaixa());
        
        this.setIconImage(new ImageIcon(getClass().getResource("/gerenciador/Imagens/Logo 1.png")).getImage());
        
        jTable2.setFont(new java.awt.Font("Tahoma", 1, 14));
        JTableHeader Theader1 = jTable2.getTableHeader();
        Theader1.setBackground(new java.awt.Color(51,51,51)); // change the Background color
        Theader1.setForeground(Color.BLACK); // change the Foreground
        Theader1.setFont(new java.awt.Font("Tahoma", 1, 14));
        
        initPorts();
        LookImpressora();
        carregacaixa();
        
        addWindowListener(new WindowAdapter() {
	public void windowClosing(WindowEvent evt) {
		
                if (JOptionPane.showConfirmDialog(null,"Deseja sair")==JOptionPane.OK_OPTION){
                    Tela_Caixa.TCodigo.requestFocusInWindow();
                    dispose();	
                }
                
                
                
        }
        });
    }

    
    //Pega qual caixa e loja
    public void carregacaixa(){
    
       DefaultTableModel modelo = (DefaultTableModel)jTable2.getModel();
        modelo.setNumRows(0);
        
        String preço = null;
        
        CaixaDAO cdao = new CaixaDAO();
        for(Caixa c : cdao.read()){
            if(c.getIdCaixa()==x.getIdCaixa() && c.getLoja().equals(x.getLoja())){
            
             modelo.addRow(new Object[]{
                c.getLoja(),
                c.getIdCaixa(),
                c.getNomeImpressora(),
                c.getPortaBalanca(),
                c.getOperador()
                        
                    });
            }  
        }
    
    }
    public void LookImpressora(){
     jComboBox3.removeAllItems();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null,null);  
        for(PrintService service:services){  
                  jComboBox3.addItem(service.getName().trim());         
                                  
            }  
        
    }
    private void estilojpanel(){
    
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(confi);
           
        
        
        
    }
     public void recebepeso(){
        
     //float b = Float.parseFloat(recebepeso.getText());
       // b=b/1000;
        //jTextField10.setText(String.valueOf(b));
     }
      private String portSelect() {
        if (PortasBalança.getSelectedItem() != null) {
            String itemSel = (String) PortasBalança.getSelectedItem();
            return itemSel;
        } else {
            //JOptionPane.showMessageDialog(this, "Selecione uma porta serial disponivel");
            return null;
        }
    }
     private void initPorts() {
        PortasBalança.setModel(new javax.swing.DefaultComboBoxModel(SerialComm.getListPortIdentifier()));
    }
     
     public void iniciacomunicabalanca(){
     serial.close();
     serial.execute(portSelect());
     
     }
     
     public void escolherModelo(){
     jComboBox2.removeAllItems();
      if(String.valueOf(jComboBox1.getSelectedItem()).equals("BEMATECH")){
      jComboBox2.addItem("MP - 4200 TH");
      jComboBox2.addItem("MP - 5100 TH - Falta");
      jComboBox2.addItem("MP - 100S ");
      jComboBox2.addItem("MP - 2032 TH");
      jComboBox2.addItem("MP - 2500 TH");
      jComboBox2.addItem("MP - 4000 TH");
      }
      if(String.valueOf(jComboBox1.getSelectedItem()).equals("EPSON")){
      jComboBox2.addItem("TM - T20");
      jComboBox2.addItem("TM - T20II");
      jComboBox2.addItem("TM - T88V");
      }
      if(String.valueOf(jComboBox1.getSelectedItem()).equals("DARUMA")){
      jComboBox2.addItem("DR800 L");
      jComboBox2.addItem("DR800 H");
      }
      


     }
     public void escolherBalança(){
     
         jComboBox6.removeAllItems();
      if(String.valueOf(FabricantesBalança.getSelectedItem()).equals("TOLEDO")){
      jComboBox6.addItem("8217 Checkout");
      jComboBox6.addItem("Megellan 8400");
      jComboBox6.addItem("Megellan 9400i");
      jComboBox6.addItem("Megellan 2300 HS");
      }
      if(String.valueOf(FabricantesBalança.getSelectedItem()).equals("FILIZOLA")){
      jComboBox6.addItem("");
      jComboBox6.addItem("");
      jComboBox6.addItem("");
      }
      
     
     
     }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        confi = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        FabricantesBalança = new javax.swing.JComboBox<>();
        jLabel89 = new javax.swing.JLabel();
        TesteBalança = new javax.swing.JButton();
        IniciaComunicaçãoBalança = new javax.swing.JButton();
        jComboBox6 = new javax.swing.JComboBox<>();
        PortasBalança = new javax.swing.JComboBox<>();
        jLabel88 = new javax.swing.JLabel();
        jRadioButton8 = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jComboBox8 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configurações Caixa");

        confi.setBackground(new java.awt.Color(255, 255, 255));
        confi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Loja", "Caixa", "Impressora", "Balança", "Leitor código"
            }
        ));
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(10);
        }

        confi.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 896, 70));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Caixa");
        confi.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 168, 27));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Loja");
        confi.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 168, 27));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(150, 0, 20));
        jLabel9.setText("Balança");
        confi.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 11, 168, 27));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Fabricante");
        confi.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 168, 27));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Modelo");
        confi.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 168, 27));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Impressora");
        confi.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 168, 27));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BEMATECH", "EPSON", "DARUMA", "OUTROS" }));
        jComboBox1.setToolTipText("");
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        confi.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 125, 27));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MP - 4200 TH", "MP - 5100 TH", "MP - 100S TH", "MP - 2032 TH", "MP - 2500 TH", "MP - 4000 TH" }));
        jComboBox2.setToolTipText("");
        confi.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 125, 27));

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox3.setToolTipText("");
        confi.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 190, 27));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(150, 0, 20));
        jLabel14.setText("Identificação");
        confi.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 168, 27));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(150, 0, 20));
        jLabel15.setText("Impressora");
        confi.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 168, 27));

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel87.setText("Comunicação");
        confi.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 90, 27));

        FabricantesBalança.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        FabricantesBalança.setForeground(new java.awt.Color(51, 51, 51));
        FabricantesBalança.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TOLEDO", "FILIZOLA" }));
        FabricantesBalança.setToolTipText("");
        FabricantesBalança.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FabricantesBalançaMouseClicked(evt);
            }
        });
        confi.add(FabricantesBalança, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 159, 27));

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel89.setText("Fabricante");
        confi.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 90, 27));

        TesteBalança.setBackground(new java.awt.Color(150, 0, 20));
        TesteBalança.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TesteBalança.setForeground(new java.awt.Color(255, 255, 255));
        TesteBalança.setText("Testar");
        TesteBalança.setToolTipText("");
        TesteBalança.setBorderPainted(false);
        TesteBalança.setContentAreaFilled(false);
        TesteBalança.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TesteBalança.setDefaultCapable(false);
        TesteBalança.setFocusPainted(false);
        TesteBalança.setOpaque(true);
        TesteBalança.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TesteBalançaActionPerformed(evt);
            }
        });
        confi.add(TesteBalança, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 93, 28));

        IniciaComunicaçãoBalança.setBackground(new java.awt.Color(150, 0, 20));
        IniciaComunicaçãoBalança.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        IniciaComunicaçãoBalança.setForeground(new java.awt.Color(255, 255, 255));
        IniciaComunicaçãoBalança.setText("Iniciar Comunicação");
        IniciaComunicaçãoBalança.setToolTipText("");
        IniciaComunicaçãoBalança.setBorderPainted(false);
        IniciaComunicaçãoBalança.setContentAreaFilled(false);
        IniciaComunicaçãoBalança.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        IniciaComunicaçãoBalança.setDefaultCapable(false);
        IniciaComunicaçãoBalança.setFocusPainted(false);
        IniciaComunicaçãoBalança.setOpaque(true);
        IniciaComunicaçãoBalança.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciaComunicaçãoBalançaActionPerformed(evt);
            }
        });
        confi.add(IniciaComunicaçãoBalança, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, -1, 27));

        jComboBox6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox6.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox6.setToolTipText("");
        confi.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 160, 27));

        PortasBalança.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PortasBalança.setForeground(new java.awt.Color(51, 51, 51));
        confi.add(PortasBalança, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 159, 27));

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel88.setText("Porta");
        confi.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 90, 27));

        buttonGroup1.add(jRadioButton8);
        jRadioButton8.setText("USB");
        confi.add(jRadioButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, -1, 27));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Modelo");
        confi.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 72, 27));

        buttonGroup1.add(jRadioButton7);
        jRadioButton7.setText("Paralelo");
        confi.add(jRadioButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, 27));

        buttonGroup1.add(jRadioButton6);
        jRadioButton6.setSelected(true);
        jRadioButton6.setText("Serial");
        confi.add(jRadioButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, -1, 27));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(150, 0, 20));
        jLabel16.setText("Leitor Código Barras");
        confi.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(691, 11, -1, 27));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Fabricante");
        confi.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(691, 44, -1, 27));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Modelo");
        confi.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(691, 77, 63, 27));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Porta");
        confi.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(691, 110, 63, 27));

        jComboBox5.setEditable(true);
        jComboBox5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox5.setToolTipText("");
        confi.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(772, 44, 125, 27));

        jComboBox7.setEditable(true);
        jComboBox7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox7.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox7.setToolTipText("");
        confi.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(772, 82, 125, 27));

        jComboBox8.setEditable(true);
        jComboBox8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox8.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox8.setToolTipText("");
        confi.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(772, 120, 125, 27));

        jButton4.setBackground(new java.awt.Color(150, 0, 20));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/actualize-arrows-couple-in-circle.png"))); // NOI18N
        jButton4.setText("Atualizar");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setFocusPainted(false);
        jButton4.setOpaque(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        confi.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        jButton5.setBackground(new java.awt.Color(51, 51, 51));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/info.png"))); // NOI18N
        jButton5.setText("Teclas de Atalho");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setFocusPainted(false);
        jButton5.setOpaque(true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        confi.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(826, 398, -1, -1));

        jTextField11.setEditable(false);
        jTextField11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField11.setForeground(new java.awt.Color(51, 51, 51));
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        confi.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 125, 27));

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField12.setForeground(new java.awt.Color(51, 51, 51));
        confi.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 125, 27));

        jTextField10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField10.setForeground(new java.awt.Color(255, 0, 0));
        confi.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 160, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(confi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(confi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TesteBalançaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TesteBalançaActionPerformed
        recebepeso();
        try {
            serial.enviaComando(05);
        } catch (IOException ex) {
            Logger.getLogger(IniciaImpressora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TesteBalançaActionPerformed

    private void IniciaComunicaçãoBalançaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciaComunicaçãoBalançaActionPerformed
        serial.close();
        serial.execute(portSelect());
        JOptionPane.showMessageDialog(null, "Comunicação Iniciada!");
    }//GEN-LAST:event_IniciaComunicaçãoBalançaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       Caixa c = new Caixa();
       CaixaDAO cdao = new CaixaDAO();
       
       c.setLoja(x.getLoja());
       c.setIdCaixa(x.getIdCaixa());
       c.setMarcaImpressora(String.valueOf(jComboBox1.getSelectedItem()));
       c.setModeloImpressora(String.valueOf(jComboBox2.getSelectedItem()));
       c.setNomeImpressora(String.valueOf(jComboBox3.getSelectedItem()));
        if(jRadioButton6.isSelected()){
            c.setComunicaçãoBalança("Serial");
        }
         if(jRadioButton7.isSelected()){
            c.setComunicaçãoBalança("Paralelo");
        }
         if(jRadioButton8.isSelected()){
            c.setComunicaçãoBalança("USB");
        }
        
       c.setFabricanteBalanca(String.valueOf(FabricantesBalança.getSelectedItem()));
       c.setModeloBalanca(String.valueOf(jComboBox6.getSelectedItem()));
       c.setPortaBalanca(String.valueOf(PortasBalança.getSelectedItem()));
       
       cdao.atualizaComponentes(c);
       carregacaixa();
       
      // Tela_Caixa frame = (Tela_Caixa)parent;
                     //   frame.setVisible(true);
                      //  frame.pegaimpressora(c);
                        x=c;
       
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeclasAtalho().setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
     escolherModelo();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
       escolherModelo();
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void FabricantesBalançaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FabricantesBalançaMouseClicked
      escolherBalança();
    }//GEN-LAST:event_FabricantesBalançaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> FabricantesBalança;
    private javax.swing.JButton IniciaComunicaçãoBalança;
    private javax.swing.JComboBox<String> PortasBalança;
    private javax.swing.JButton TesteBalança;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel confi;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    public static javax.swing.JTextField jTextField10;
    public static javax.swing.JTextField jTextField11;
    public static javax.swing.JTextField jTextField12;
    // End of variables declaration//GEN-END:variables
}
