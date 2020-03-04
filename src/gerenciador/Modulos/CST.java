/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.Modulos;

import gerenciador.TelaGestão;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Marcos
 */
public class CST extends javax.swing.JFrame {

    String origem = null;
    String codigo = null;
    String aa = "Selecione";
        String a = "101 - Tributada pelo Simples Nacional com permissão de crédito";
        String b = "102 - Tributada pelo Simples Nacional sem permissão de crédito";
        String c = "103 - Isenção do ICMS no Simples Nacional para faixa de receita bruta";
        String d = "201 - Tributada pelo Simples Nacional com permissão de crédito e com cobrança do ICMS por substituição tributária";
        String e = "202 - Tributada pelo Simples Nacional sem permissão de crédito e com cobrança do ICMS por substituição tributária";
        String f = "203 - Isenção do ICMS no Simples Nacional para faixa de receita bruta e com cobrança do ICMS por substituição tributária";
        String g = "300 - Imune";
        String h = "400 - Não tributada pelo Simples Nacional";
        String i = "500 - ICMS cobrado anteriormente por substituição tributária (substituído) ou por antecipação";
        String j = "900 - Outros";
        
        String a1 = "Tributada integralmente";
        String b1 = "Tributada e com cobrança do ICMS por substituição tributária";
        String c1 = "Com redução da BC";
        String d1 = "Isenta / não tributada e com cobrança do ICMS por substituição tributária";
        String e1 = "Isenta";
        String f1 = "Não tributada";
        String g1 = "Com suspensão";
        String h1 = "Com diferimento";
        String i1 = "ICMS cobrado anteriormente por substituição tributária";
        String j1 = "Com redução da BC e cobrança do ICMS por substituição tributária";
        String l = "Outras";
    
    public CST() {
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
        SwingUtilities.updateComponentTreeUI(jPanel1);
        
        
    }
   
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tabela CST");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(150, 0, 20));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Preecha os campos");

        jComboBox1.setMaximumRowCount(10);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Simples Nacional", "Lucro Real", "Lucro Presumido" }));
        jComboBox1.setFocusable(false);
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jComboBox2.setMaximumRowCount(10);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "0 - Nacional, exceto as indicadas nos códigos 3 a 5", "1 - Estrangeira - Importação direta, exceto a indicada no código 6", "2 - Estrangeira - Adquirida no mercado interno, exceto a indicada no código 7", "3 - Nacional, mercadoria ou bem com Conteúdo de Importação superior a 40%", "4 - Nacional, cuja produção tenha sido feita em conformidade com os processos produtivos básicos de que tratam o Decreto-Lei nº 288/67 e as Leis nºs 8.248/91", "5 - Nacional, mercadoria ou bem com Conteúdo de Importação inferior ou igual a 40%", "6 - Estrangeira - Importação direta, sem similar nacional, constante em lista de Resolução CAMEX", "7 - Estrangeira - Adquirida no mercado interno, sem similar nacional, constante em lista de Resolução CAMEX", " ", " " }));
        jComboBox2.setFocusable(false);
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jComboBox3.setMaximumRowCount(10);
        jComboBox3.setFocusable(false);
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(150, 0, 20));
        jLabel1.setText("Regime tributário");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(150, 0, 20));
        jLabel2.setText("Origem do Produto");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(150, 0, 20));
        jLabel3.setText("Código da Situação Tributária");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setText("CST :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox2, 0, 0, Short.MAX_VALUE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jComboBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox3)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
      
        
        
        String[] ports = new String[11];
        ports[0]=aa;
        ports[1]=a;
        ports[2]=b;
        ports[3]=c;
        ports[4]=d;
        ports[5]=e;
        ports[6]=f;
        ports[7]=g;
        ports[8]=h;
        ports[9]=i;
        ports[10]=j;
        
        
        
        String[] ports1 = new String[11];
        ports1[0]=aa;
        ports1[1]=a1;
        ports1[2]=b1;
        ports1[3]=c1;
        ports1[4]=d1;
        ports1[5]=e1;
        ports1[6]=f1;
        ports1[7]=g1;
        ports1[8]=h1;
        ports1[9]=i1;
        ports1[10]=j1;
    
        if(String.valueOf(jComboBox1.getSelectedItem()).equals("Simples Nacional")){
            
            jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(ports));
       }if(String.valueOf(jComboBox1.getSelectedItem()).equals("Selecione")){
           jComboBox3.removeAll();
       }if(String.valueOf(jComboBox1.getSelectedItem()).equals("Lucro Real")){
           jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(ports1));
       }if(String.valueOf(jComboBox1.getSelectedItem()).equals("Lucro Presumido")){
           jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(ports1));
       }
       
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged


        if(String.valueOf(jComboBox2.getSelectedItem()).equals("Selecione")){
            origem = "Selecione uma origem";
        }if(String.valueOf(jComboBox2.getSelectedItem()).equals("0 - Nacional, exceto as indicadas nos códigos 3 a 5")){
            origem = "0";
        }if(String.valueOf(jComboBox2.getSelectedItem()).equals("1 - Estrangeira - Importação direta, exceto a indicada no código 6")){
            origem = "1";
        }if(String.valueOf(jComboBox2.getSelectedItem()).equals("2 - Estrangeira - Adquirida no mercado interno, exceto a indicada no código 7")){
            origem = "2";
        }if(String.valueOf(jComboBox2.getSelectedItem()).equals("3 - Nacional, mercadoria ou bem com Conteúdo de Importação superior a 40%")){
            origem = "3";
        }if(String.valueOf(jComboBox2.getSelectedItem()).equals("4 - Nacional, cuja produção tenha sido feita em conformidade com os processos produtivos básicos de que tratam o Decreto-Lei nº 288/67 e as Leis nºs 8.248/91")){
            origem = "4";
        }if(String.valueOf(jComboBox2.getSelectedItem()).equals("5 - Nacional, mercadoria ou bem com Conteúdo de Importação inferior ou igual a 40%")){
            origem = "5";
        }if(String.valueOf(jComboBox2.getSelectedItem()).equals("6 - Estrangeira - Importação direta, sem similar nacional, constante em lista de Resolução CAMEX")){
            origem = "6";
        }if(String.valueOf(jComboBox2.getSelectedItem()).equals("7 - Estrangeira - Adquirida no mercado interno, sem similar nacional, constante em lista de Resolução CAMEX")){
            origem = "7";
        }
        
        if(codigo!=null && String.valueOf(jComboBox3.getSelectedItem()).equals("Selecione")==false){
            jTextField1.setText(origem+codigo);
        }
        
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        
          if(String.valueOf(jComboBox3.getSelectedItem()).equals("Selecione")){
            codigo = "Selecione uma origem";
        }
           if(String.valueOf(jComboBox3.getSelectedItem()).equals("101 - Tributada pelo Simples Nacional com permissão de crédito")){
            codigo = "101";
        }
            if(String.valueOf(jComboBox3.getSelectedItem()).equals("102 - Tributada pelo Simples Nacional sem permissão de crédito")){
            codigo = "102";
        }
             if(String.valueOf(jComboBox3.getSelectedItem()).equals("103 - Isenção do ICMS no Simples Nacional para faixa de receita bruta")){
            codigo = "103";
        }if(String.valueOf(jComboBox3.getSelectedItem()).equals("201 - Tributada pelo Simples Nacional com permissão de crédito e com cobrança do ICMS por substituição tributária")){
            codigo = "201";
        }if(String.valueOf(jComboBox3.getSelectedItem()).equals("202 - Tributada pelo Simples Nacional sem permissão de crédito e com cobrança do ICMS por substituição tributária")){
            codigo = "202";
        }if(String.valueOf(jComboBox3.getSelectedItem()).equals("203 - Isenção do ICMS no Simples Nacional para faixa de receita bruta e com cobrança do ICMS por substituição tributária")){
            codigo = "203";
        }if(String.valueOf(jComboBox3.getSelectedItem()).equals("300 - Imune")){
            codigo = "300";
        }if(String.valueOf(jComboBox3.getSelectedItem()).equals("400 - Não tributada pelo Simples Nacional")){
            codigo = "400";
        }if(String.valueOf(jComboBox3.getSelectedItem()).equals("500 - ICMS cobrado anteriormente por substituição tributária (substituído) ou por antecipação")){
            codigo = "500";
        }if(String.valueOf(jComboBox3.getSelectedItem()).equals("900 - Outros")){
            codigo = "900";
        }
        if(String.valueOf(jComboBox3.getSelectedItem()).equals("Tributada integralmente")){
            codigo = "00";
        }
            if(String.valueOf(jComboBox3.getSelectedItem()).equals("Tributada e com cobrança do ICMS por substituição tributária")){
            codigo = "10";
        }
             if(String.valueOf(jComboBox3.getSelectedItem()).equals("Com redução da BC")){
            codigo = "20";
        }if(String.valueOf(jComboBox3.getSelectedItem()).equals("Isenta / não tributada e com cobrança do ICMS por substituição tributária")){
            codigo = "30";
        }if(String.valueOf(jComboBox3.getSelectedItem()).equals("Isenta")){
            codigo = "40";
        }if(String.valueOf(jComboBox3.getSelectedItem()).equals("Não tributada")){
            codigo = "41";
        }if(String.valueOf(jComboBox3.getSelectedItem()).equals("Com suspensão")){
            codigo = "50";
        }if(String.valueOf(jComboBox3.getSelectedItem()).equals("Com diferimento")){
            codigo = "51";
        }if(String.valueOf(jComboBox3.getSelectedItem()).equals("ICMS cobrado anteriormente por substituição tributária")){
            codigo = "60";
        }if(String.valueOf(jComboBox3.getSelectedItem()).equals("Com redução da BC e cobrança do ICMS por substituição tributária")){
            codigo = "70";
        }if(String.valueOf(jComboBox3.getSelectedItem()).equals("Outras")){
            codigo = "90";
        }
       
        if(origem!=null){
            jTextField1.setText(origem+codigo);
        }
        
    }//GEN-LAST:event_jComboBox3ItemStateChanged

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
            java.util.logging.Logger.getLogger(CST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CST().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
