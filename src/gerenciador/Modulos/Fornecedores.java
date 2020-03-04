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
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import model.bean.Fornecedor;
import model.dao.FornecedorDAO;

/**
 *
 * @author Marcos
 */
public class Fornecedores extends javax.swing.JInternalFrame {

    /**
     * Creates new form Fornecedores
     */
    public Fornecedores() {
        
        initComponents();
        estilojpanel();
        buscarfornecedores();
        
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
        SwingUtilities.updateComponentTreeUI(PainelFornecimento);
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
    
     public void buscarfornecedores(){
    
        DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        modelo.setNumRows(0);
        
       FornecedorDAO fdao = new FornecedorDAO();
        for(Fornecedor f: fdao.read()){
            modelo.addRow(new Object[]{
                f.getNome(),
                f.getEmail(),
                f.getEmpresa(),
                f.getProdutos(),
                f.getTelefone1(),
                f.getTelefone2(),
                f.getEndereçoempresa()
                        
                    });
    }
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelFornecimento = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton29 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jPanel62 = new javax.swing.JPanel();
        JTProdutoFornecedor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        JTTelefone1Fornecedor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        JTTelefone2Fornecedor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        JTEndereçoEFornecedor = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        BCFornecedor = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        BUFornecedor = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        BRFornecedor = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        JTNomeFornecedor = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        JTEmailFornecedor = new javax.swing.JTextField();
        JTNomeEFornecedor = new javax.swing.JTextField();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel50 = new javax.swing.JLabel();

        PainelFornecimento.setBackground(new java.awt.Color(255, 255, 255));
        PainelFornecimento.setToolTipText("");

        jTabbedPane3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(150, 0, 20));
        jLabel2.setText("Lista de fornecedores");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "E-mail", "Empresa", "Produtos", "Telefone1", "Telefone2", "Endereço Empresa"
            }
        ));
        jScrollPane14.setViewportView(jTable1);

        jButton29.setBackground(new java.awt.Color(150, 0, 20));
        jButton29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton29.setForeground(new java.awt.Color(255, 255, 255));
        jButton29.setText("Atualizar Tabela");
        jButton29.setBorderPainted(false);
        jButton29.setContentAreaFilled(false);
        jButton29.setOpaque(true);
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton29)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Fornecedores", jPanel15);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setToolTipText("");

        jPanel62.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Nome *");

        jLabel5.setText("Email");

        jLabel6.setText("Empresa *");

        jLabel34.setText("Produto");

        BCFornecedor.setBackground(new java.awt.Color(150, 0, 20));
        BCFornecedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BCFornecedor.setForeground(new java.awt.Color(255, 255, 255));
        BCFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/add-button-with-plus-symbol-in-a-black-circle.png"))); // NOI18N
        BCFornecedor.setText("Adcionar");
        BCFornecedor.setToolTipText("Completo os campos obrigatorios e clique para adcionar");
        BCFornecedor.setBorderPainted(false);
        BCFornecedor.setContentAreaFilled(false);
        BCFornecedor.setOpaque(true);
        BCFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCFornecedorActionPerformed(evt);
            }
        });

        jLabel35.setText("Telefone 1 *");

        BUFornecedor.setBackground(new java.awt.Color(150, 0, 20));
        BUFornecedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BUFornecedor.setForeground(new java.awt.Color(255, 255, 255));
        BUFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/pencil.png"))); // NOI18N
        BUFornecedor.setText("Alterar");
        BUFornecedor.setToolTipText("Forneça ao menos Nome ou Empresa e clique para atualizar cadastro");
        BUFornecedor.setBorderPainted(false);
        BUFornecedor.setContentAreaFilled(false);
        BUFornecedor.setOpaque(true);
        BUFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUFornecedorActionPerformed(evt);
            }
        });

        jLabel36.setText("Telefone 2");

        BRFornecedor.setBackground(new java.awt.Color(150, 0, 20));
        BRFornecedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BRFornecedor.setForeground(new java.awt.Color(255, 255, 255));
        BRFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/icon.png"))); // NOI18N
        BRFornecedor.setText("Remover");
        BRFornecedor.setToolTipText("Forceça ao menos Nome ou Empresa e clique para remover");
        BRFornecedor.setBorderPainted(false);
        BRFornecedor.setContentAreaFilled(false);
        BRFornecedor.setOpaque(true);
        BRFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRFornecedorActionPerformed(evt);
            }
        });

        jLabel37.setText("Endereço da Empresa");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(150, 0, 20));
        jLabel38.setText("Informações Pessoais:");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(150, 0, 20));
        jLabel46.setText("Informações da Empresa:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane20.setViewportView(jTextArea1);

        jLabel50.setText("Informações adicionais");

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel62Layout.createSequentialGroup()
                            .addComponent(BCFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BUFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BRFornecedor))
                        .addGroup(jPanel62Layout.createSequentialGroup()
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(JTTelefone2Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel62Layout.createSequentialGroup()
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(JTTelefone1Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel62Layout.createSequentialGroup()
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(JTProdutoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel62Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(JTNomeEFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel62Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(JTEmailFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel62Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(JTNomeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel62Layout.createSequentialGroup()
                            .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane20)
                                .addComponent(JTEndereçoEFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))))
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(578, Short.MAX_VALUE))
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JTNomeFornecedor)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JTEmailFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JTTelefone1Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JTTelefone2Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JTNomeEFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JTProdutoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(JTEndereçoEFornecedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BCFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BUFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BRFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane19.setViewportView(jPanel62);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Cadastro Fornecedores", jPanel16);

        javax.swing.GroupLayout PainelFornecimentoLayout = new javax.swing.GroupLayout(PainelFornecimento);
        PainelFornecimento.setLayout(PainelFornecimentoLayout);
        PainelFornecimentoLayout.setHorizontalGroup(
            PainelFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        PainelFornecimentoLayout.setVerticalGroup(
            PainelFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelFornecimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1018, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelFornecimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        buscarfornecedores();
    }//GEN-LAST:event_jButton29ActionPerformed

    private void BCFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCFornecedorActionPerformed
        Fornecedor f = new Fornecedor();
        FornecedorDAO dao = new FornecedorDAO();

        f.setNome(JTNomeFornecedor.getText());
        f.setEmail(JTEmailFornecedor.getText());
        f.setEmpresa(JTNomeEFornecedor.getText());
        f.setProdutos(JTProdutoFornecedor.getText());
        f.setTelefone1(JTTelefone1Fornecedor.getText());
        f.setTelefone2(JTTelefone2Fornecedor.getText());
        f.setEndereçoempresa(JTEndereçoEFornecedor.getText());

        dao.creat(f);

        JTNomeFornecedor.setText("");
        JTEmailFornecedor.setText("");
        JTNomeEFornecedor.setText("");
        JTProdutoFornecedor.setText("");
        JTTelefone1Fornecedor.setText("");
        JTTelefone2Fornecedor.setText("");
        JTEndereçoEFornecedor.setText("");
    }//GEN-LAST:event_BCFornecedorActionPerformed

    private void BUFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUFornecedorActionPerformed

        if(JTNomeFornecedor.getText().equals("")){

            javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
            javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
            javax.swing.JTable jTable1 = new javax.swing.JTable();
            javax.swing.JButton jButton1 = new javax.swing.JButton();

            JFrame sefra = new JFrame();
            sefra.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            sefra.setTitle("Atualização de Fornecedores");
            sefra.setAlwaysOnTop(true);
            sefra.setSize(600,500);
            sefra.setLocationRelativeTo(null);
            sefra.setVisible(true);
            sefra.getRootPane().setDefaultButton(jButton1);

            jLabel1.setText("Escolha o Fornecedor");

            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Nome", "Email", "Empresa","Produtos","Telefone 1", "Telefone 2"
                }
            ));
            jScrollPane1.setViewportView(jTable1);
            DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
            modelo.setNumRows(0);
            FornecedorDAO fdao = new FornecedorDAO();

            for(Fornecedor p: fdao.read()){

                modelo.addRow(new Object[]{
                    p.getNome(),
                    p.getEmail(),
                    p.getEmpresa(),
                    p.getProdutos(),
                    p.getTelefone1(),
                    p.getTelefone2(),
                    p.getEndereçoempresa()
                });
            }
            jButton1.setText("Atualizar");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    Fornecedor f = new Fornecedor();
                    FornecedorDAO dao = new FornecedorDAO();
                    DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
                    int a = jTable1.getSelectedRow();
                    f.setNome(String.valueOf(jTable1.getValueAt(a,0)));
                    f.setEmail(String.valueOf(jTable1.getValueAt(a,1)));
                    f.setEmpresa(String.valueOf(jTable1.getValueAt(a,2)));
                    f.setProdutos(String.valueOf(jTable1.getValueAt(a,3)));
                    f.setTelefone1(String.valueOf(jTable1.getValueAt(a,4)));
                    f.setTelefone2(String.valueOf(jTable1.getValueAt(a,5)));

                    sefra.dispose();
                    dao.update(f);

                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(sefra.getContentPane());
            sefra.getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addGap(0, 473, Short.MAX_VALUE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addContainerGap())))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(jButton1)
                    .addContainerGap())
            );
        }else{

            Fornecedor f = new Fornecedor();
            FornecedorDAO dao = new FornecedorDAO();

            f.setNome(JTNomeFornecedor.getText());
            f.setEmail(JTEmailFornecedor.getText());
            f.setEmpresa(JTNomeEFornecedor.getText());
            f.setProdutos(JTProdutoFornecedor.getText());
            f.setTelefone1(JTTelefone1Fornecedor.getText());
            f.setTelefone2(JTTelefone2Fornecedor.getText());
            f.setEndereçoempresa(JTEndereçoEFornecedor.getText());

            dao.update(f);

            JTNomeFornecedor.setText("");
            JTEmailFornecedor.setText("");
            JTNomeEFornecedor.setText("");
            JTProdutoFornecedor.setText("");
            JTTelefone1Fornecedor.setText("");
            JTTelefone2Fornecedor.setText("");
            JTEndereçoEFornecedor.setText("");

        }
    }//GEN-LAST:event_BUFornecedorActionPerformed

    private void BRFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRFornecedorActionPerformed

        if(JTNomeFornecedor.getText().equals("")){

            javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
            javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
            javax.swing.JTable jTable1 = new javax.swing.JTable();
            javax.swing.JButton jButton1 = new javax.swing.JButton();

            JFrame sefra = new JFrame();
            sefra.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            sefra.setTitle("Atualização de Fornecedores");
            sefra.setAlwaysOnTop(true);
            sefra.setSize(600,500);
            sefra.setLocationRelativeTo(null);
            sefra.setVisible(true);
            sefra.getRootPane().setDefaultButton(jButton1);

            jLabel1.setText("Escolha o Fornecedor");

            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Nome", "Email", "Empresa","Produtos","Telefone 1", "Telefone 2"
                }
            ));
            jScrollPane1.setViewportView(jTable1);
            DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
            modelo.setNumRows(0);
            FornecedorDAO fdao = new FornecedorDAO();

            for(Fornecedor p: fdao.read()){

                modelo.addRow(new Object[]{
                    p.getNome(),
                    p.getEmail(),
                    p.getEmpresa(),
                    p.getProdutos(),
                    p.getTelefone1(),
                    p.getTelefone2(),
                    p.getEndereçoempresa()
                });
            }
            jButton1.setText("Atualizar");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    Fornecedor f = new Fornecedor();
                    FornecedorDAO dao = new FornecedorDAO();
                    DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();

                    int a = jTable1.getSelectedRow();
                    f.setNome(String.valueOf(jTable1.getValueAt(a,0)));
                    f.setEmail(String.valueOf(jTable1.getValueAt(a,1)));

                    sefra.dispose();
                    dao.delete(f);

                    

                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(sefra.getContentPane());
            sefra.getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addGap(0, 473, Short.MAX_VALUE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addContainerGap())))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(jButton1)
                    .addContainerGap())
            );

        }else{
            Fornecedor f = new Fornecedor();
            FornecedorDAO dao = new FornecedorDAO();

            f.setNome(JTNomeFornecedor.getText());
            f.setEmail(JTEmailFornecedor.getText());
            dao.delete(f);

            JTNomeFornecedor.setText("");
            JTEmailFornecedor.setText("");
            JTNomeEFornecedor.setText("");
            JTProdutoFornecedor.setText("");
            JTTelefone1Fornecedor.setText("");
            JTTelefone2Fornecedor.setText("");
            JTEndereçoEFornecedor.setText("");

            
        }
    }//GEN-LAST:event_BRFornecedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCFornecedor;
    private javax.swing.JButton BRFornecedor;
    private javax.swing.JButton BUFornecedor;
    private javax.swing.JTextField JTEmailFornecedor;
    private javax.swing.JTextField JTEndereçoEFornecedor;
    private javax.swing.JTextField JTNomeEFornecedor;
    private javax.swing.JTextField JTNomeFornecedor;
    private javax.swing.JTextField JTProdutoFornecedor;
    private javax.swing.JTextField JTTelefone1Fornecedor;
    private javax.swing.JTextField JTTelefone2Fornecedor;
    private javax.swing.JPanel PainelFornecimento;
    private javax.swing.JButton jButton29;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
