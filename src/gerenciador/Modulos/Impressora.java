/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.Modulos;

import gerenciador.TelaGestão;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import model.dao.CaixaDAO;

/**
 *
 * @author Marcos
 */
public class Impressora extends javax.swing.JInternalFrame {

    /**
     * Creates new form Impressora
     */
    public Impressora() {
        initComponents();
        estilojpanel();
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
        SwingUtilities.updateComponentTreeUI(PainelImpressora);
           UIManager.put("PainelEstatistica.borderColor", Color.BLACK);
            UIManager.put("PainelEstatistica.darkShadow", ColorUIResource.BLACK);
            UIManager.put("PainelEstatistica.light", ColorUIResource.BLACK);
            UIManager.put("jPainelEstatistica.highlight", ColorUIResource.BLACK);
            UIManager.put("PainelEstatistica.focus", ColorUIResource.BLACK);
            UIManager.put("PainelEstatistica.unselectedBackground", ColorUIResource.BLACK);
            UIManager.put("PainelEstatistica.selectHighlight", ColorUIResource.BLACK);
            UIManager.put("PainelEstatistica.tabAreaBackground", ColorUIResource.BLACK);
            UIManager.put("PainelEstatistica.borderHightlightColor", ColorUIResource.BLACK);
        
        
        
    }
   
       public void definirImpressora(){
    
        int impressora;
         CaixaDAO cdao = new CaixaDAO();
        
        if(jRadioButton1.isSelected()){
         impressora = 1; //Bematech MP-4200TH
         cdao.atualizarImpressora(impressora);
        }
        if(jRadioButton2.isSelected()){
         impressora = 2; //Bematech MP-100STH
         cdao.atualizarImpressora(impressora);
        }
        if(jRadioButton3.isSelected()){
         impressora = 3; //Epson TM-T88V
         cdao.atualizarImpressora(impressora);
        }
        if(jRadioButton4.isSelected()){
         impressora = 4; //Epson TM-T20
         cdao.atualizarImpressora(impressora);
        }
        
       
        
       
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelImpressora = new javax.swing.JPanel();
        jTabbedPane16 = new javax.swing.JTabbedPane();
        jPanel21 = new javax.swing.JPanel();
        PainelAcessoCaixa = new javax.swing.JPanel();
        LSenha1 = new javax.swing.JLabel();
        LSenha2 = new javax.swing.JLabel();
        TUsuario1 = new javax.swing.JTextField();
        TUsuario2 = new javax.swing.JTextField();
        LSenha3 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        LSenha4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton33 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel42 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jPanel43 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();

        PainelImpressora.setBackground(new java.awt.Color(255, 255, 255));
        PainelImpressora.setMaximumSize(new java.awt.Dimension(810, 462));
        PainelImpressora.setMinimumSize(new java.awt.Dimension(810, 462));
        PainelImpressora.setName(""); // NOI18N

        jTabbedPane16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        PainelAcessoCaixa.setBackground(new java.awt.Color(255, 255, 255));
        PainelAcessoCaixa.setMaximumSize(new java.awt.Dimension(271, 355));

        LSenha1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha1.setForeground(new java.awt.Color(150, 0, 20));
        LSenha1.setText("Caixa");

        LSenha2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha2.setForeground(new java.awt.Color(150, 0, 20));
        LSenha2.setText("Loja");

        TUsuario1.setEditable(false);
        TUsuario1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TUsuario1.setText("1");
        TUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TUsuario1ActionPerformed(evt);
            }
        });

        TUsuario2.setEditable(false);
        TUsuario2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TUsuario2.setText("Sede");
        TUsuario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TUsuario2ActionPerformed(evt);
            }
        });

        LSenha3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha3.setForeground(new java.awt.Color(150, 0, 20));
        LSenha3.setText("Impressora Não Fiscal");

        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton1.setText("Bematech MP-4200 TH");

        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton2.setText("Bematech MP-100STH");

        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton3.setText("Epson TM-T20");

        jRadioButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton4.setText("Epson TM-T88V");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jRadioButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton5.setText("Bematech MP-2500 TH");

        jRadioButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton6.setText("Bematech MP-4000 TH");

        LSenha4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha4.setForeground(new java.awt.Color(150, 0, 20));
        LSenha4.setText("Comunicação");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Serial", "Paralela", "USB", "Ethernet", "Wi-Fi" }));
        jComboBox1.setSelectedIndex(1);

        jButton33.setBackground(new java.awt.Color(150, 0, 20));
        jButton33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton33.setForeground(new java.awt.Color(255, 255, 255));
        jButton33.setText("Atualizar");
        jButton33.setBorderPainted(false);
        jButton33.setContentAreaFilled(false);
        jButton33.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton33.setOpaque(true);
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelAcessoCaixaLayout = new javax.swing.GroupLayout(PainelAcessoCaixa);
        PainelAcessoCaixa.setLayout(PainelAcessoCaixaLayout);
        PainelAcessoCaixaLayout.setHorizontalGroup(
            PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                        .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(LSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(TUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LSenha2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LSenha3, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                                .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jRadioButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jRadioButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jRadioButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jRadioButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                                .addComponent(LSenha4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelAcessoCaixaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(198, 198, 198))
        );
        PainelAcessoCaixaLayout.setVerticalGroup(
            PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                        .addComponent(LSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(LSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(LSenha3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton6)
                .addGap(18, 18, 18)
                .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LSenha4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelAcessoCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelAcessoCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane16.addTab("Não Fiscal", jPanel21);

        jTextField1.setText("Opção não disponivel para este Sistema");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 364, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(0, 376, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane16.addTab("ECF", jPanel22);

        jTextField2.setText("Opção não disponivel para este Sistema");

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 364, Short.MAX_VALUE))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addGap(0, 376, Short.MAX_VALUE)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane16.addTab("NFC-e", jPanel42);

        jTextField3.setText("Opção não disponivel para este Sistema");

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 364, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addGap(0, 376, Short.MAX_VALUE)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane16.addTab("SAT / NFC-e", jPanel43);

        javax.swing.GroupLayout PainelImpressoraLayout = new javax.swing.GroupLayout(PainelImpressora);
        PainelImpressora.setLayout(PainelImpressoraLayout);
        PainelImpressoraLayout.setHorizontalGroup(
            PainelImpressoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
        );
        PainelImpressoraLayout.setVerticalGroup(
            PainelImpressoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane16, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelImpressora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 484, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelImpressora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TUsuario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TUsuario1ActionPerformed

    private void TUsuario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TUsuario2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TUsuario2ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        definirImpressora();
    }//GEN-LAST:event_jButton33ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LSenha1;
    private javax.swing.JLabel LSenha2;
    private javax.swing.JLabel LSenha3;
    private javax.swing.JLabel LSenha4;
    private javax.swing.JPanel PainelAcessoCaixa;
    private javax.swing.JPanel PainelImpressora;
    private javax.swing.JTextField TUsuario1;
    private javax.swing.JTextField TUsuario2;
    private javax.swing.JButton jButton33;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JTabbedPane jTabbedPane16;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
