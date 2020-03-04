/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.Modulos;

import br.com.samuelweb.certificado.exception.CertificadoException;
import gerenciador.AcoesNFCe.CancelarNFCe;
import gerenciador.AcoesNFCe.CartaCorrecaoNFCe;
import gerenciador.AcoesNFCe.ConsultarNFCe;
import gerenciador.AcoesNFCe.InutilizacaoNFCe;
import gerenciador.AcoesNfe.CancelarNFeNFCe;
import gerenciador.AcoesNfe.CartaCorrecaoEletronica;
import gerenciador.AcoesNfe.ConsultaCadastro;

import gerenciador.AcoesNfe.ConsultarNFeSefaz;
import gerenciador.AcoesNfe.InutilizacaoNFe;
import gerenciador.AcoesNfe.ManifestaçãoNfe;
import gerenciador.TelaGestão;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Marcos
 */
public class NotasFiscais extends javax.swing.JInternalFrame {

    /**
     * Creates new form NotasFiscais
     */
    public NotasFiscais() {
        initComponents();
        estilojpanel();
        limpatela();
        limpatela1();
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
        SwingUtilities.updateComponentTreeUI(PainelNotasFiscais);
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
    
    public void limpatela(){
    InutilizaçãoNfe.setVisible(false);
       CancelarNfe.setVisible(false);
       ManifestarNFe.setVisible(false);
     //Consultarnfe.setVisible(false);
      Correção.setVisible(false);
    
    }
    
    public void limpatela1(){
        
        InutilizaçãoNFCE.setVisible(false);
        CorreçãoNFCE.setVisible(false);
        CancelarNFCE.setVisible(false);
        //ConsultarNFCE.setVisible(false);
        
    }
    
    public void consulta(){
        new Thread() {
                @Override
                public void run() {
                    ConsultaCadastro a = new ConsultaCadastro();
                }
            }.start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelNotasFiscais = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        Nfe = new javax.swing.JPanel();
        Botões = new javax.swing.JPanel();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        Consultarnfe = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        chavenfe = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        Retornonfe = new javax.swing.JTextArea();
        jButton13 = new javax.swing.JButton();
        ManifestarNFe = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        chavenfe1 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        retornoManifestação = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel36 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        Correção = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        chavenfe5 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        chavenfe6 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextPane6 = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel48 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        CancelarNfe = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        chavenfe7 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        chavenfe8 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        chavenfe9 = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton17 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextPane7 = new javax.swing.JTextPane();
        InutilizaçãoNfe = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        chavenfe4 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton19 = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        RetornoInutilização = new javax.swing.JTextPane();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextPane10 = new javax.swing.JTextPane();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextPane11 = new javax.swing.JTextPane();
        jLabel57 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        chavenfe10 = new javax.swing.JTextField();
        Nfce = new javax.swing.JPanel();
        Botões1 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        ConsultarNFCE = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        chavenfe2 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        Retornonfe1 = new javax.swing.JTextArea();
        jButton16 = new javax.swing.JButton();
        InutilizaçãoNFCE = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        chavenfe11 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jButton20 = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        RetornoInutilização1 = new javax.swing.JTextPane();
        jLabel61 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTextPane12 = new javax.swing.JTextPane();
        CorreçãoNFCE = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        chavenfe12 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        chavenfe13 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextPane8 = new javax.swing.JTextPane();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextPane5 = new javax.swing.JTextPane();
        jLabel66 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jLabel67 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        chavenfe14 = new javax.swing.JTextField();
        CancelarNFCE = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        chavenfe15 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        chavenfe16 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        chavenfe17 = new javax.swing.JTextField();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jButton21 = new javax.swing.JButton();
        jLabel71 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jScrollPane24 = new javax.swing.JScrollPane();
        jTextPane9 = new javax.swing.JTextPane();

        PainelNotasFiscais.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        Nfe.setBackground(new java.awt.Color(255, 255, 255));

        Botões.setBackground(new java.awt.Color(255, 255, 255));

        b1.setBackground(new java.awt.Color(51, 51, 51));
        b1.setForeground(new java.awt.Color(255, 255, 255));
        b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/text-document-information.png"))); // NOI18N
        b1.setText("Consultar NFe");
        b1.setBorderPainted(false);
        b1.setContentAreaFilled(false);
        b1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b1.setDefaultCapable(false);
        b1.setFocusPainted(false);
        b1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b1.setOpaque(true);
        b1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        b2.setBackground(new java.awt.Color(150, 0, 20));
        b2.setForeground(new java.awt.Color(255, 255, 255));
        b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/report.png"))); // NOI18N
        b2.setText("Manifestar NFe");
        b2.setBorderPainted(false);
        b2.setContentAreaFilled(false);
        b2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b2.setDefaultCapable(false);
        b2.setFocusPainted(false);
        b2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b2.setOpaque(true);
        b2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        b3.setBackground(new java.awt.Color(150, 0, 20));
        b3.setForeground(new java.awt.Color(255, 255, 255));
        b3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/blocked-text-file.png"))); // NOI18N
        b3.setText("Inutilização Nfe");
        b3.setBorderPainted(false);
        b3.setContentAreaFilled(false);
        b3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b3.setDefaultCapable(false);
        b3.setFocusPainted(false);
        b3.setOpaque(true);
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        b4.setBackground(new java.awt.Color(150, 0, 20));
        b4.setForeground(new java.awt.Color(255, 255, 255));
        b4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/text-file-editor.png"))); // NOI18N
        b4.setText("Carta Correção Nfe");
        b4.setBorderPainted(false);
        b4.setContentAreaFilled(false);
        b4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b4.setDefaultCapable(false);
        b4.setFocusPainted(false);
        b4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b4.setOpaque(true);
        b4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });

        b5.setBackground(new java.awt.Color(150, 0, 20));
        b5.setForeground(new java.awt.Color(255, 255, 255));
        b5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/text-document-cancel-button.png"))); // NOI18N
        b5.setText("Cancelar NFe");
        b5.setBorderPainted(false);
        b5.setContentAreaFilled(false);
        b5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b5.setDefaultCapable(false);
        b5.setFocusPainted(false);
        b5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b5.setOpaque(true);
        b5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(150, 0, 20));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/data-privacy.png"))); // NOI18N
        jButton2.setText("Consultar Cadastro");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setDefaultCapable(false);
        jButton2.setFocusPainted(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setOpaque(true);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BotõesLayout = new javax.swing.GroupLayout(Botões);
        Botões.setLayout(BotõesLayout);
        BotõesLayout.setHorizontalGroup(
            BotõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BotõesLayout.createSequentialGroup()
                .addComponent(b1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(181, Short.MAX_VALUE))
        );
        BotõesLayout.setVerticalGroup(
            BotõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(b5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Consultarnfe.setBackground(new java.awt.Color(255, 255, 255));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Chave de acesso da Nota Fiscal ");

        chavenfe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe.setForeground(new java.awt.Color(102, 102, 102));

        jTextPane1.setContentType("text/html"); // NOI18N
        jTextPane1.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N
        jTextPane1.setText("<html>\r\n<p>A Chave de Acesso de um Documento Fiscal: NF-e, CT-e, NFC-e e MDF-e &eacute; formada&nbsp;pelas&nbsp;seguintes informa&ccedil;&otilde;es:</p>\n\n<ul>\n\t<li><strong>cUF -</strong>&nbsp;C&oacute;digo da UF do emitente do Documento Fiscal;</li>\n\t<li><strong>AAMM -</strong>&nbsp;Ano e M&ecirc;s de emiss&atilde;o da NF-e;</li>\n\t<li><strong>CNPJ -</strong>&nbsp;CNPJ do emitente;</li>\n\t<li><strong>mod -</strong>&nbsp;Modelo do Documento Fiscal;</li>\n\t<li><strong>serie -</strong>&nbsp;S&eacute;rie do Documento Fiscal;</li>\n\t<li><strong>nNF -</strong>&nbsp;N&uacute;mero do Documento Fiscal;</li>\n\t<li><strong>tpEmis &ndash;</strong>&nbsp;forma de emiss&atilde;o da NF-e;</li>\n\t<li><strong>cNF -</strong>&nbsp;C&oacute;digo Num&eacute;rico que comp&otilde;e a Chave de Acesso;</li>\n\t<li><strong>cDV -</strong>&nbsp;D&iacute;gito Verificador da Chave de Acesso.</li>\n</ul>\n\n<p> Ex: 29180713695713000169650040000202631000237946 </p>\n\n</html>\r\n");
        jScrollPane2.setViewportView(jTextPane1);

        Retornonfe.setColumns(20);
        Retornonfe.setRows(5);
        jScrollPane1.setViewportView(Retornonfe);

        jButton13.setBackground(new java.awt.Color(150, 0, 20));
        jButton13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/magnifieruuyty.png"))); // NOI18N
        jButton13.setText("Consultar");
        jButton13.setBorderPainted(false);
        jButton13.setContentAreaFilled(false);
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.setDefaultCapable(false);
        jButton13.setFocusPainted(false);
        jButton13.setOpaque(true);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ConsultarnfeLayout = new javax.swing.GroupLayout(Consultarnfe);
        Consultarnfe.setLayout(ConsultarnfeLayout);
        ConsultarnfeLayout.setHorizontalGroup(
            ConsultarnfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsultarnfeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ConsultarnfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConsultarnfeLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(ConsultarnfeLayout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chavenfe, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jButton13)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ConsultarnfeLayout.setVerticalGroup(
            ConsultarnfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsultarnfeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ConsultarnfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chavenfe)
                    .addComponent(jButton13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConsultarnfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        ManifestarNFe.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Chave de acesso da Nota Fiscal ");

        chavenfe1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe1.setForeground(new java.awt.Color(102, 102, 102));

        jButton14.setBackground(new java.awt.Color(150, 0, 20));
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("Manifestar");
        jButton14.setContentAreaFilled(false);
        jButton14.setDefaultCapable(false);
        jButton14.setFocusPainted(false);
        jButton14.setOpaque(true);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(retornoManifestação);

        jTextPane2.setContentType("text/html"); // NOI18N
        jTextPane2.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N
        jTextPane2.setText("<html>\r\n<h2><strong>O que &eacute; e para que serve a Manifesta&ccedil;&atilde;o do Destinat&aacute;rio?</strong></h2>\n\n<p>Manifesta&ccedil;&atilde;o do Destinat&aacute;rio&nbsp;&eacute; um conjunto de eventos que permitem que o destinat&aacute;rio da NFe possa se manifestar sobre a sua participa&ccedil;&atilde;o comercial descrita no documento fiscal, confirmando e controlando as opera&ccedil;&otilde;es e informa&ccedil;&otilde;es prestadas pelo seu fornecedor, que &eacute; o emissor do documento.</p>\n\n<p>Esses eventos servem para apontar para a&nbsp;Secretaria da Fazenda&nbsp;se aquela opera&ccedil;&atilde;o representada pela nota foi completada com sucesso, se n&atilde;o ocorreu ou se voc&ecirc; desconhece completamente aquela&nbsp;Nota Fiscal eletr&ocirc;nica (NFe).</p>\n\n<h2><strong>Os quatro eventos da Manifesta&ccedil;&atilde;o do Destinat&aacute;rio</strong></h2>\n\n<h3><strong>1. Ci&ecirc;ncia da emiss&atilde;o</strong></h3>\n\n<p>&Eacute; um evento opcional que pode ser utilizado pelo destinat&aacute;rio para declarar que tem ci&ecirc;ncia da exist&ecirc;ncia da opera&ccedil;&atilde;o, mas ainda n&atilde;o tem elementos suficientes para apresentar uma manifesta&ccedil;&atilde;o conclusiva, por isso &eacute; denominado de um evento &ldquo;n&atilde;o conclusivo&rdquo;.</p>\n\n<p>Ap&oacute;s esta manifesta&ccedil;&atilde;o ter sido efetuada, &eacute; habilitada a funcionalidade de&nbsp;download do arquivo XML.</p>\n\n<p>&Eacute; importante ressaltar que, uma vez tomada ci&ecirc;ncia da exist&ecirc;ncia da nota, o destinat&aacute;rio &eacute; obrigado a emitir um dos pareceres seguintes em at&eacute; 180 dias: confirma&ccedil;&atilde;o, desconhecimento ou opera&ccedil;&atilde;o n&atilde;o realizada.</p>\n\n<h3><strong>2. Confirma&ccedil;&atilde;o da opera&ccedil;&atilde;o</strong></h3>\n\n<p>Significa que a opera&ccedil;&atilde;o ocorreu conforme informado na&nbsp;NFe.&nbsp;Uma nota transmitida, baixada e confirmada gera seguran&ccedil;a jur&iacute;dica no uso do cr&eacute;dito fiscal correspondente. Depois desses procedimentos, quem emitiu n&atilde;o pode mais cancelar a NFe.</p>\n\n<p>Mesmo que a opera&ccedil;&atilde;o tenha se realizado, mas o conte&uacute;do da NFe n&atilde;o descreva corretamente a opera&ccedil;&atilde;o, o destinat&aacute;rio dever&aacute; se manifestar utilizando o evento &ldquo;Confirma&ccedil;&atilde;o da Opera&ccedil;&atilde;o&rdquo; e adotar os procedimentos fiscais cab&iacute;veis de acordo com a legisla&ccedil;&atilde;o da unidade federada onde estiver estabelecido.</p>\n\n<h3><strong>3. Opera&ccedil;&atilde;o n&atilde;o realizada</strong></h3>\n\n<p>Este evento ser&aacute; informado pelo destinat&aacute;rio quando, por algum motivo, a opera&ccedil;&atilde;o n&atilde;o se realizou.</p>\n\n<p>Exemplos: devolu&ccedil;&atilde;o sem entrada f&iacute;sica da mercadoria no estabelecimento do destinat&aacute;rio, recusa do recebimento, sinistro da carga durante seu transporte, etc.</p>\n\n<p>Ap&oacute;s esta manifesta&ccedil;&atilde;o &eacute; preciso fazer a justificativa do porqu&ecirc; a opera&ccedil;&atilde;o n&atilde;o se realizou.</p>\n\n<h3><strong>4. Desconhecimento da opera&ccedil;&atilde;o</strong></h3>\n\n<p>Este evento tem como finalidade possibilitar ao destinat&aacute;rio se manifestar quando houver utiliza&ccedil;&atilde;o indevida de sua Inscri&ccedil;&atilde;o Estadual por parte do&nbsp;emitente da NFe. Geralmente, o uso de Inscri&ccedil;&atilde;o Estadual divergentes em documentos fiscais &nbsp;pode estar ligado &agrave;&nbsp;fraudes.</p>\n\n<h2><strong>Aten&ccedil;&atilde;o!</strong></h2>\n\n<p>&Eacute; importante ressaltar que cada evento de Manifesta&ccedil;&atilde;o do Destinat&aacute;rio pode ser efetuado apenas uma vez.</p>\n\n\n</html>\r\n");
        jScrollPane4.setViewportView(jTextPane2);

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Motivo");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Confirmação da Operação", "Desconhecimento da Operação", "Operação Não Realizada", "Ciência da Emissão" }));

        javax.swing.GroupLayout ManifestarNFeLayout = new javax.swing.GroupLayout(ManifestarNFe);
        ManifestarNFe.setLayout(ManifestarNFeLayout);
        ManifestarNFeLayout.setHorizontalGroup(
            ManifestarNFeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManifestarNFeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ManifestarNFeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ManifestarNFeLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ManifestarNFeLayout.createSequentialGroup()
                        .addGroup(ManifestarNFeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ManifestarNFeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chavenfe1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ManifestarNFeLayout.createSequentialGroup()
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton14)))))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        ManifestarNFeLayout.setVerticalGroup(
            ManifestarNFeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManifestarNFeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ManifestarNFeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chavenfe1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ManifestarNFeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14))
                .addGap(16, 16, 16)
                .addGroup(ManifestarNFeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
                .addContainerGap())
        );

        Correção.setBackground(new java.awt.Color(255, 255, 255));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Chave de acesso da Nota Fiscal ");

        chavenfe5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe5.setForeground(new java.awt.Color(102, 102, 102));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("CNPJ");

        chavenfe6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe6.setForeground(new java.awt.Color(102, 102, 102));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("Sequencia");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "001", "002", "003", "004", "005", "006", "007", "008", "009", "010", "011", "012", "013", "014", "015", "016", "017", "018", "019", "020", "021", "022", "023", "024", "025", "026", "027", "028", "029", "030", "031", "032", "033", "034", "035", "036", "037", "038", "039", "040", "041", "042", "043", "044", "045", "046", "047", "048", "049", "050", "051", "052", "053", "054", "055", "056", "057", "058", "059", "060", "061", "062", "063", "064", "065", "066", "067", "068", "069", "070", "071", "072", "073", "074", "075", "076", "077", "078", "079", "080", "081", "082", "083", "084", "085", "086", "087", "088", "089", "090", "091", "092", "093", "094", "095", "096", "097", "098", "099", "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149", "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160", "161", "162", "163", "164", "165", "166", "167", "168", "169", "170", "171", "172", "173", "174", "175", "176", "177", "178", "179", "180", "181", "182", "183", "184", "185", "186", "187", "188", "189", "190", "191", "192", "193", "194", "195", "196", "197", "198", "199", "200", "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211", "212", "213", "214", "215", "216", "217", "218", "219", "220", "221", "222", "223", "224", "225", "226", "227", "228", "229", "230", "231", "232", "233", "234", "235", "236", "237", "238", "239", "240", "241", "242", "243", "244", "245", "246", "247", "248", "249", "250", "251", "252", "253", "254", "255", "256", "257", "258", "259", "260", "261", "262", "263", "264", "265", "266", "267", "268", "269", "270", "271", "272", "273", "274", "275", "276", "277", "278", "279", "280", "281", "282", "283", "284", "285", "286", "287", "288", "289", "290", "291", "292", "293", "294", "295", "296", "297", "298", "299", "300", "301", "302", "303", "304", "305", "306", "307", "308", "309", "310", "311", "312", "313", "314", "315", "316", "317", "318", "319", "320", "321", "322", "323", "324", "325", "326", "327", "328", "329", "330", "331", "332", "333", "334", "335" }));

        jTextPane6.setContentType("text/html"); // NOI18N
        jTextPane6.setText("<html>\r\n<h2><strong>Objetivo da Carta de Corre&ccedil;&atilde;o Eletr&ocirc;nica e regulamenta&ccedil;&atilde;o</strong></h2>\n\n<p>A Carta de Corre&ccedil;&atilde;o &eacute; um evento para corrigir as informa&ccedil;&otilde;es da NF-e, prevista na cl&aacute;usula<br />\nd&eacute;cima quarta-A do Ajuste SINIEF 07/05.</p>\n\n<h2><strong>O que pode ser corrigido pela carta de corre&ccedil;&atilde;o de NF-e?</strong></h2>\n\n<p>A Carta Corre&ccedil;&atilde;o eletr&ocirc;nica (CC-e) poder&aacute; ser usada para corrigir erros que sejam relacionados com:</p>\n\n<ul>\n\t<li>CFOP &ndash;&nbsp;<strong>C&oacute;digo Fiscal de Opera&ccedil;&atilde;o</strong>, desde que n&atilde;o mude a natureza dos impostos;</li>\n\t<li><strong>Descri&ccedil;&atilde;o</strong>&nbsp;da Mercadoria;</li>\n\t<li><strong>C&oacute;digos Fiscais</strong>&nbsp;&ndash; C&oacute;digo de Situa&ccedil;&atilde;o Tribut&aacute;ria (desde que n&atilde;o altere valores fiscais);</li>\n\t<li><strong>Peso, Volume, Acondicionamento</strong>, desde que n&atilde;o interfira na quantidade faturada do produto, como por exemplo alterar o volume de 01 palete para 01 container;</li>\n\t<li><strong>Data da Emiss&atilde;o ou de Sa&iacute;da</strong>&nbsp;(desde que n&atilde;o altere o per&iacute;odo de apura&ccedil;&atilde;o do&nbsp;<strong>ICMS</strong>);</li>\n\t<li><strong>Dados do Transportador</strong>&nbsp;&ndash; Endere&ccedil;o do destinat&aacute;rio (desde que n&atilde;o na sua totalidade);</li>\n\t<li><strong>Raz&atilde;o Social do Destinat&aacute;rio</strong>&nbsp;(desde que n&atilde;o altere por completo);</li>\n\t<li>Omiss&atilde;o ou Erro na&nbsp;<strong>Fundamenta&ccedil;&atilde;o Legal</strong>&nbsp;que Amparou a Sa&iacute;da com algum Benef&iacute;cio Fiscal, ou Opera&ccedil;&atilde;o que Contemple a sua Necessidade (dados adicionais);</li>\n\t<li><strong>Inserir ou alterar dados adicionais</strong>&nbsp;na&nbsp;nota fiscal,&nbsp;como por exemplo, transportadora para redespacho, nome do vendedor, pedido do cliente, at&eacute; mesmo trocar um fundamento legal mencionado indevidamente.</li>\n</ul>\n\n<h2><strong>O que n&atilde;o pode ser corrigido pela carta de corre&ccedil;&atilde;o eletr&ocirc;nica da&nbsp;NFe?</strong></h2>\n\n<p>Nos termos da cl&aacute;usula d&eacute;cima quarta do Ajuste SINIEF 07/05, a emiss&atilde;o da carta de corre&ccedil;&atilde;o n&atilde;o pode estar relacionada a corre&ccedil;&atilde;o de erros como:</p>\n\n<ul>\n\t<li><strong>Valores fiscais que determinam o valor do imposto,</strong>&nbsp;tais como:&nbsp;base de c&aacute;lculo,&nbsp;al&iacute;quota, diferen&ccedil;a de pre&ccedil;o, quantidade, valor da opera&ccedil;&atilde;o ou da presta&ccedil;&atilde;o; para estas situa&ccedil;&otilde;es se faz necess&aacute;rio a emiss&atilde;o de&nbsp;nota fiscalcomplementar de imposto;</li>\n\t<li>Corre&ccedil;&atilde;o de dados cadastrais que implique mudan&ccedil;a do remetente ou do destinat&aacute;rio ou descri&ccedil;&atilde;o da mercadoria que&nbsp;<strong>altere a al&iacute;quota do imposto;</strong></li>\n\t<li><strong>Data de emiss&atilde;o ou de sa&iacute;da</strong>, pois o fisco pode entender que a altera&ccedil;&atilde;o da data de emiss&atilde;o pode ter o objetivo de reaproveitar a mesma em outras entregas;</li>\n\t<li>Destaque de Impostos ou&nbsp;<strong>quaisquer outros dados que alterem o C&aacute;lculo ou a Opera&ccedil;&atilde;o do Imposto;</strong></li>\n</ul>\n\n<p>Se n&atilde;o for poss&iacute;vel emitir uma carta de corre&ccedil;&atilde;o eletr&ocirc;nica para corrigir os erros de uma&nbsp;nota fiscal&nbsp;autorizada&nbsp;<strong>&eacute; necess&aacute;rio realizar o cancelamento de n&uacute;mero de NFe</strong>, pois a carta de corre&ccedil;&atilde;o somente pode corrigir erros simples.</p>\n\n<h2><strong>Prazo para a transmiss&atilde;o da carta de corre&ccedil;&atilde;o eletr&ocirc;nica</strong></h2>\n\n<p>A CC-e poder&aacute; ser transmitida at&eacute; 720 horas (30 dias) a partir da autoriza&ccedil;&atilde;o de uso da NF-e que ser&aacute; corrigida.&nbsp;<strong>Ela somente poder&aacute; ser transmitida para uma&nbsp;NFe&nbsp;autorizada</strong>, pois n&atilde;o &eacute; poss&iacute;vel corrigir uma NF-e cancelada.</p>\n\n<p>Uma NF-e poder&aacute; ter at&eacute; 20 CC-e&rsquo;s, por&eacute;m, a &uacute;ltima carta de corre&ccedil;&atilde;o deve contemplar&nbsp;<strong>todas as altera&ccedil;&otilde;es.</strong></p>\n\n</html>\r\n");
        jScrollPane11.setViewportView(jTextPane6);

        jScrollPane8.setViewportView(jTextPane4);

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("Retorno Sefaz");

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("CFOP:\nDescrição da Mercadoria:\nCódigos Fiscais:\nPeso: \nVolume:\nAcondicionamento:\nData da Emissão:\nData de Saída:\nDados do Transportador:\nRazão Social do Destinatário:\nEtc...");
        jScrollPane9.setViewportView(jTextArea1);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setText("Carta de Correção");

        jButton15.setBackground(new java.awt.Color(120, 0, 20));
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Enviar");
        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);
        jButton15.setDefaultCapable(false);
        jButton15.setFocusPainted(false);
        jButton15.setOpaque(true);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CorreçãoLayout = new javax.swing.GroupLayout(Correção);
        Correção.setLayout(CorreçãoLayout);
        CorreçãoLayout.setHorizontalGroup(
            CorreçãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CorreçãoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CorreçãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CorreçãoLayout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chavenfe5, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CorreçãoLayout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CorreçãoLayout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chavenfe6, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CorreçãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(CorreçãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CorreçãoLayout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 246, Short.MAX_VALUE))
                    .addComponent(jScrollPane8)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        CorreçãoLayout.setVerticalGroup(
            CorreçãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CorreçãoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CorreçãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CorreçãoLayout.createSequentialGroup()
                        .addGroup(CorreçãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chavenfe5, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CorreçãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chavenfe6, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CorreçãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CorreçãoLayout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        CancelarNfe.setBackground(new java.awt.Color(255, 255, 255));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setText("Chave de acesso da Nota Fiscal ");

        chavenfe7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe7.setForeground(new java.awt.Color(102, 102, 102));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setText("Motivo");

        chavenfe8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe8.setForeground(new java.awt.Color(102, 102, 102));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setText("CNPJ");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setText("Protocolo");

        chavenfe9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe9.setForeground(new java.awt.Color(102, 102, 102));

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jScrollPane10.setViewportView(jTextArea2);

        jButton17.setBackground(new java.awt.Color(150, 0, 20));
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("Cancelar NFe");
        jButton17.setBorderPainted(false);
        jButton17.setContentAreaFilled(false);
        jButton17.setDefaultCapable(false);
        jButton17.setFocusPainted(false);
        jButton17.setOpaque(true);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setText("Retorno Sefaz");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane12.setViewportView(jTextArea3);

        jTextPane7.setContentType("text/html"); // NOI18N
        jTextPane7.setText("<html>\r\n <h2><strong>Legisla&ccedil;&atilde;o sobre o cancelamento de NFe:</strong></h2>\n\n<p>A legisla&ccedil;&atilde;o disp&otilde;e que, ap&oacute;s a concess&atilde;o de Autoriza&ccedil;&atilde;o de Uso da NFe, o emitente poder&aacute; solicitar o cancelamento de NF-e,&nbsp;<strong>desde que n&atilde;o tenha havido a circula&ccedil;&atilde;o do respectivo produto ou a presta&ccedil;&atilde;o de servi&ccedil;o.</strong></p>\n\n<p>Quando n&atilde;o &eacute; poss&iacute;vel emitir uma carta de corre&ccedil;&atilde;o que repare os erros de uma nota fiscal autorizada &eacute; necess&aacute;rio realizar o cancelamento de n&uacute;mero de NFe,&nbsp;<strong>pois a carta de corre&ccedil;&atilde;o somente pode corrigir erros simples.</strong></p>\n\n<h2><strong>Principais motivos de cancelamento de NFe:</strong></h2>\n\n<p>Listamos os principais motivos que resultam na escolha por cancelar uma nota fiscal:</p>\n\n<ul>\n\t<li>Desist&ecirc;ncia do Comprador</li>\n\t<li>Cancelamento das vendas,</li>\n\t<li>C&aacute;lculos de Pre&ccedil;os e Impostos Incorretos,</li>\n\t<li>Erros cadastrais, como no CNPJ ou na Raz&atilde;o Social.</li>\n</ul>\n\n<h2><strong>Prazos para cancelamento de NFe:</strong></h2>\n\n<p>Nos termos da Cl&aacute;usula d&eacute;cima segunda do Ajuste SINIEF 07/05,&nbsp;<strong>o prazo legal de cancelamento de NFe, &eacute; de 24hs</strong>&nbsp;contado a partir do momento da autoriza&ccedil;&atilde;o de uso da mesma. Caso n&atilde;o seja poss&iacute;vel realizar o cancelamento neste prazo o contribuinte poder&aacute; faz&ecirc;-lo de forma extempor&acirc;nea.</p>\n\n<h2><strong>Cancelamento de NFe fora do prazo:</strong></h2>\n\n<p>O prazo previsto para o cancelamento extempor&acirc;neo de uma NFe &eacute; de at&eacute; 168 horas, mas devido a alguns problemas pontuais no sistema da SEFAZ (Secretaria da Fazenda),&nbsp;<strong>n&atilde;o h&aacute; bloqueio de solicita&ccedil;&otilde;es feitas</strong>, mesmo que tenha ultrapassado o prazo previsto para o cancelamento extempor&acirc;neo de 168 horas.</p>\n\n<p>A solicita&ccedil;&atilde;o para o cancelamento extempor&acirc;neo&nbsp;<strong>dever&aacute; ser realizada no aplicativo da Receita SIARE</strong>, onde deve ser informado a chave de acesso da nota, o CNPJ do Solicitante e a justificativa do pedido de cancelamento fora do prazo.</p>\n\n<p><strong>Ap&oacute;s a autoriza&ccedil;&atilde;o dever&aacute; ser reenviado e transmitido o pedido de cancelamento de NFe utilizando a funcionalidade de cancelamento dispon&iacute;vel no sistema</strong></p>\n\n<p>Ap&oacute;s a gera&ccedil;&atilde;o do protocolo de autoriza&ccedil;&atilde;o do SIARE a empresa tem at&eacute; 30 dias para transmitir o cancelamento extempor&acirc;neo. Passado este prazo n&atilde;o &eacute; mais poss&iacute;vel solicitar o cancelamento para a mesma NFe.</p>\n\n</html>\r\n");
        jScrollPane13.setViewportView(jTextPane7);

        javax.swing.GroupLayout CancelarNfeLayout = new javax.swing.GroupLayout(CancelarNfe);
        CancelarNfe.setLayout(CancelarNfeLayout);
        CancelarNfeLayout.setHorizontalGroup(
            CancelarNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CancelarNfeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CancelarNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CancelarNfeLayout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chavenfe8, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CancelarNfeLayout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chavenfe9, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CancelarNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton17)
                        .addGroup(CancelarNfeLayout.createSequentialGroup()
                            .addGroup(CancelarNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(CancelarNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(chavenfe7, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                                .addComponent(jScrollPane10))))
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );
        CancelarNfeLayout.setVerticalGroup(
            CancelarNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CancelarNfeLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(CancelarNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CancelarNfeLayout.createSequentialGroup()
                        .addGroup(CancelarNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(chavenfe7, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CancelarNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chavenfe8, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CancelarNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chavenfe9, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CancelarNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        InutilizaçãoNfe.setBackground(new java.awt.Color(255, 255, 255));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setText("Id da Nota Fiscal ");

        chavenfe4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe4.setForeground(new java.awt.Color(102, 102, 102));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("Motivo");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quebra de Sequência", "Rejeição Incorrigivel" }));

        jButton19.setBackground(new java.awt.Color(150, 0, 20));
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setText("Inutilizar");
        jButton19.setBorderPainted(false);
        jButton19.setContentAreaFilled(false);
        jButton19.setDefaultCapable(false);
        jButton19.setFocusPainted(false);
        jButton19.setOpaque(true);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jScrollPane17.setViewportView(RetornoInutilização);

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText("Retorno Sefaz");

        jTextPane10.setContentType("text/html"); // NOI18N
        jTextPane10.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N
        jTextPane10.setText("<html>\r\n<p>A inutiliza&ccedil;&atilde;o de NFe tem como principal finalidade&nbsp;<strong>permitir que o emissor comunique &agrave; SEFAZ os n&uacute;meros de NFe que n&atilde;o ser&atilde;o utilizados em raz&atilde;o de ter ocorrido uma quebra de sequ&ecirc;ncia da numera&ccedil;&atilde;o da nota fiscal eletr&ocirc;nica ou uma rejei&ccedil;&atilde;o que n&atilde;o seja poss&iacute;vel de ser corrigida.</strong></p>\n\n<p>A inutiliza&ccedil;&atilde;o de NFe&nbsp;<strong>s&oacute; &eacute; poss&iacute;vel caso a numera&ccedil;&atilde;o ainda n&atilde;o tenha sido utilizada</strong>&nbsp;em nenhuma nota fiscal (Autorizada, <strong>Cancelada&nbsp;</strong>ou Denegada).</p>\n\n<p>A inutiliza&ccedil;&atilde;o de NFe tem car&aacute;ter de den&uacute;ncia espont&acirc;nea do contribuinte de irregularidades no processo de emiss&atilde;o das notas e deve ser realizada at&eacute; o d&eacute;cimo dia do m&ecirc;s subsequente a emiss&atilde;o.</p>\n\n\n</html>\r\n");
        jScrollPane18.setViewportView(jTextPane10);

        jScrollPane19.setViewportView(jTextPane11);

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setText("Notas Geradas");

        javax.swing.GroupLayout InutilizaçãoNfeLayout = new javax.swing.GroupLayout(InutilizaçãoNfe);
        InutilizaçãoNfe.setLayout(InutilizaçãoNfeLayout);
        InutilizaçãoNfeLayout.setHorizontalGroup(
            InutilizaçãoNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InutilizaçãoNfeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InutilizaçãoNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InutilizaçãoNfeLayout.createSequentialGroup()
                        .addGroup(InutilizaçãoNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(InutilizaçãoNfeLayout.createSequentialGroup()
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(InutilizaçãoNfeLayout.createSequentialGroup()
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chavenfe4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton19)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(InutilizaçãoNfeLayout.createSequentialGroup()
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(InutilizaçãoNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InutilizaçãoNfeLayout.createSequentialGroup()
                                .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(205, 205, 205)
                                .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(88, 88, 88))
                            .addGroup(InutilizaçãoNfeLayout.createSequentialGroup()
                                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        InutilizaçãoNfeLayout.setVerticalGroup(
            InutilizaçãoNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InutilizaçãoNfeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InutilizaçãoNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chavenfe4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InutilizaçãoNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InutilizaçãoNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(jLabel57))
                .addGap(8, 8, 8)
                .addGroup(InutilizaçãoNfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane19)
                    .addComponent(jScrollPane17)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(132, 132, 132))
        );

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setText("Chave de acesso da Nota Fiscal ");

        chavenfe10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe10.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chavenfe10, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(471, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chavenfe10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(517, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(Consultarnfe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(ManifestarNFe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Correção, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(CancelarNfe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(InutilizaçãoNfe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Consultarnfe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ManifestarNFe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Correção, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(CancelarNfe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(InutilizaçãoNfe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Consultarnfe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ManifestarNFe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Correção, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(CancelarNfe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(InutilizaçãoNfe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout NfeLayout = new javax.swing.GroupLayout(Nfe);
        Nfe.setLayout(NfeLayout);
        NfeLayout.setHorizontalGroup(
            NfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NfeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Botões, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(150, 150, 150))
            .addComponent(jLayeredPane1)
        );
        NfeLayout.setVerticalGroup(
            NfeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NfeLayout.createSequentialGroup()
                .addComponent(Botões, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLayeredPane1))
        );

        jTabbedPane4.addTab("NF-e 4.0", Nfe);

        Botões1.setBackground(new java.awt.Color(255, 255, 255));

        jButton7.setBackground(new java.awt.Color(150, 0, 20));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/iiiiiiiii.png"))); // NOI18N
        jButton7.setText("Consultar NFCe");
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setDefaultCapable(false);
        jButton7.setFocusPainted(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setOpaque(true);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(150, 0, 20));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Inutilização NfCe");
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.setDefaultCapable(false);
        jButton10.setFocusPainted(false);
        jButton10.setOpaque(true);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(150, 0, 20));
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Carta Correção NFCe");
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.setDefaultCapable(false);
        jButton11.setFocusPainted(false);
        jButton11.setOpaque(true);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(150, 0, 20));
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Cancelar NFCe");
        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.setDefaultCapable(false);
        jButton12.setFocusPainted(false);
        jButton12.setOpaque(true);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton1.setText("Notas Emitidas");

        javax.swing.GroupLayout Botões1Layout = new javax.swing.GroupLayout(Botões1);
        Botões1.setLayout(Botões1Layout);
        Botões1Layout.setHorizontalGroup(
            Botões1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Botões1Layout.createSequentialGroup()
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Botões1Layout.setVerticalGroup(
            Botões1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ConsultarNFCE.setBackground(new java.awt.Color(255, 255, 255));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Chave de acesso da Nota Fiscal ");

        chavenfe2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe2.setForeground(new java.awt.Color(102, 102, 102));

        jTextPane3.setContentType("text/html"); // NOI18N
        jTextPane3.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N
        jTextPane3.setText("<html>\r\n<p>A Chave de Acesso de um Documento Fiscal: NF-e, CT-e, NFC-e e MDF-e &eacute; formada&nbsp;pelas&nbsp;seguintes informa&ccedil;&otilde;es:</p>\n\n<ul>\n\t<li><strong>cUF -</strong>&nbsp;C&oacute;digo da UF do emitente do Documento Fiscal;</li>\n\t<li><strong>AAMM -</strong>&nbsp;Ano e M&ecirc;s de emiss&atilde;o da NF-e;</li>\n\t<li><strong>CNPJ -</strong>&nbsp;CNPJ do emitente;</li>\n\t<li><strong>mod -</strong>&nbsp;Modelo do Documento Fiscal;</li>\n\t<li><strong>serie -</strong>&nbsp;S&eacute;rie do Documento Fiscal;</li>\n\t<li><strong>nNF -</strong>&nbsp;N&uacute;mero do Documento Fiscal;</li>\n\t<li><strong>tpEmis &ndash;</strong>&nbsp;forma de emiss&atilde;o da NF-e;</li>\n\t<li><strong>cNF -</strong>&nbsp;C&oacute;digo Num&eacute;rico que comp&otilde;e a Chave de Acesso;</li>\n\t<li><strong>cDV -</strong>&nbsp;D&iacute;gito Verificador da Chave de Acesso.</li>\n</ul>\n\n<p> Ex: 29180713695713000169650040000202631000237946 </p>\n\n</html>\r\n");
        jScrollPane5.setViewportView(jTextPane3);

        Retornonfe1.setColumns(20);
        Retornonfe1.setRows(5);
        jScrollPane6.setViewportView(Retornonfe1);

        jButton16.setBackground(new java.awt.Color(150, 0, 20));
        jButton16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/magnifieruuyty.png"))); // NOI18N
        jButton16.setText("Consultar");
        jButton16.setBorderPainted(false);
        jButton16.setContentAreaFilled(false);
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton16.setDefaultCapable(false);
        jButton16.setFocusPainted(false);
        jButton16.setOpaque(true);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ConsultarNFCELayout = new javax.swing.GroupLayout(ConsultarNFCE);
        ConsultarNFCE.setLayout(ConsultarNFCELayout);
        ConsultarNFCELayout.setHorizontalGroup(
            ConsultarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsultarNFCELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ConsultarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConsultarNFCELayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6))
                    .addGroup(ConsultarNFCELayout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chavenfe2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jButton16)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ConsultarNFCELayout.setVerticalGroup(
            ConsultarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsultarNFCELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ConsultarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chavenfe2)
                    .addComponent(jButton16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConsultarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setText("Id da Nota Fiscal ");

        chavenfe11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe11.setForeground(new java.awt.Color(102, 102, 102));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setText("Motivo");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quebra de Sequência", "Rejeição Incorrigivel" }));

        jButton20.setBackground(new java.awt.Color(150, 0, 20));
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("Inutilizar");
        jButton20.setBorderPainted(false);
        jButton20.setContentAreaFilled(false);
        jButton20.setDefaultCapable(false);
        jButton20.setFocusPainted(false);
        jButton20.setOpaque(true);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jScrollPane20.setViewportView(RetornoInutilização1);

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setText("Retorno Sefaz");

        jTextPane12.setContentType("text/html"); // NOI18N
        jTextPane12.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N
        jTextPane12.setText("<html>\r\n<p>A inutiliza&ccedil;&atilde;o de NFe tem como principal finalidade&nbsp;<strong>permitir que o emissor comunique &agrave; SEFAZ os n&uacute;meros de NFe que n&atilde;o ser&atilde;o utilizados em raz&atilde;o de ter ocorrido uma quebra de sequ&ecirc;ncia da numera&ccedil;&atilde;o da nota fiscal eletr&ocirc;nica ou uma rejei&ccedil;&atilde;o que n&atilde;o seja poss&iacute;vel de ser corrigida.</strong></p>\n\n<p>A inutiliza&ccedil;&atilde;o de NFe&nbsp;<strong>s&oacute; &eacute; poss&iacute;vel caso a numera&ccedil;&atilde;o ainda n&atilde;o tenha sido utilizada</strong>&nbsp;em nenhuma nota fiscal (Autorizada, <strong>Cancelada&nbsp;</strong>ou Denegada).</p>\n\n<p>A inutiliza&ccedil;&atilde;o de NFe tem car&aacute;ter de den&uacute;ncia espont&acirc;nea do contribuinte de irregularidades no processo de emiss&atilde;o das notas e deve ser realizada at&eacute; o d&eacute;cimo dia do m&ecirc;s subsequente a emiss&atilde;o.</p>\n\n\n</html>\r\n");
        jScrollPane21.setViewportView(jTextPane12);

        javax.swing.GroupLayout InutilizaçãoNFCELayout = new javax.swing.GroupLayout(InutilizaçãoNFCE);
        InutilizaçãoNFCE.setLayout(InutilizaçãoNFCELayout);
        InutilizaçãoNFCELayout.setHorizontalGroup(
            InutilizaçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InutilizaçãoNFCELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InutilizaçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InutilizaçãoNFCELayout.createSequentialGroup()
                        .addGroup(InutilizaçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(InutilizaçãoNFCELayout.createSequentialGroup()
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(InutilizaçãoNFCELayout.createSequentialGroup()
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chavenfe11, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton20)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(InutilizaçãoNFCELayout.createSequentialGroup()
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(InutilizaçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InutilizaçãoNFCELayout.createSequentialGroup()
                                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(InutilizaçãoNFCELayout.createSequentialGroup()
                                .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                                .addGap(380, 380, 380))))))
        );
        InutilizaçãoNFCELayout.setVerticalGroup(
            InutilizaçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InutilizaçãoNFCELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InutilizaçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chavenfe11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InutilizaçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel61)
                .addGap(8, 8, 8)
                .addGroup(InutilizaçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane20)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        CorreçãoNFCE.setBackground(new java.awt.Color(255, 255, 255));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setText("Chave de acesso da Nota Fiscal ");

        chavenfe12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe12.setForeground(new java.awt.Color(102, 102, 102));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel64.setText("CNPJ");

        chavenfe13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe13.setForeground(new java.awt.Color(102, 102, 102));

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel65.setText("Sequencia");

        jTextPane8.setContentType("text/html"); // NOI18N
        jTextPane8.setText("<html>\r\n<h2><strong>Objetivo da Carta de Corre&ccedil;&atilde;o Eletr&ocirc;nica e regulamenta&ccedil;&atilde;o</strong></h2>\n\n<p>A Carta de Corre&ccedil;&atilde;o &eacute; um evento para corrigir as informa&ccedil;&otilde;es da NF-e, prevista na cl&aacute;usula<br />\nd&eacute;cima quarta-A do Ajuste SINIEF 07/05.</p>\n\n<h2><strong>O que pode ser corrigido pela carta de corre&ccedil;&atilde;o de NF-e?</strong></h2>\n\n<p>A Carta Corre&ccedil;&atilde;o eletr&ocirc;nica (CC-e) poder&aacute; ser usada para corrigir erros que sejam relacionados com:</p>\n\n<ul>\n\t<li>CFOP &ndash;&nbsp;<strong>C&oacute;digo Fiscal de Opera&ccedil;&atilde;o</strong>, desde que n&atilde;o mude a natureza dos impostos;</li>\n\t<li><strong>Descri&ccedil;&atilde;o</strong>&nbsp;da Mercadoria;</li>\n\t<li><strong>C&oacute;digos Fiscais</strong>&nbsp;&ndash; C&oacute;digo de Situa&ccedil;&atilde;o Tribut&aacute;ria (desde que n&atilde;o altere valores fiscais);</li>\n\t<li><strong>Peso, Volume, Acondicionamento</strong>, desde que n&atilde;o interfira na quantidade faturada do produto, como por exemplo alterar o volume de 01 palete para 01 container;</li>\n\t<li><strong>Data da Emiss&atilde;o ou de Sa&iacute;da</strong>&nbsp;(desde que n&atilde;o altere o per&iacute;odo de apura&ccedil;&atilde;o do&nbsp;<strong>ICMS</strong>);</li>\n\t<li><strong>Dados do Transportador</strong>&nbsp;&ndash; Endere&ccedil;o do destinat&aacute;rio (desde que n&atilde;o na sua totalidade);</li>\n\t<li><strong>Raz&atilde;o Social do Destinat&aacute;rio</strong>&nbsp;(desde que n&atilde;o altere por completo);</li>\n\t<li>Omiss&atilde;o ou Erro na&nbsp;<strong>Fundamenta&ccedil;&atilde;o Legal</strong>&nbsp;que Amparou a Sa&iacute;da com algum Benef&iacute;cio Fiscal, ou Opera&ccedil;&atilde;o que Contemple a sua Necessidade (dados adicionais);</li>\n\t<li><strong>Inserir ou alterar dados adicionais</strong>&nbsp;na&nbsp;nota fiscal,&nbsp;como por exemplo, transportadora para redespacho, nome do vendedor, pedido do cliente, at&eacute; mesmo trocar um fundamento legal mencionado indevidamente.</li>\n</ul>\n\n<h2><strong>O que n&atilde;o pode ser corrigido pela carta de corre&ccedil;&atilde;o eletr&ocirc;nica da&nbsp;NFe?</strong></h2>\n\n<p>Nos termos da cl&aacute;usula d&eacute;cima quarta do Ajuste SINIEF 07/05, a emiss&atilde;o da carta de corre&ccedil;&atilde;o n&atilde;o pode estar relacionada a corre&ccedil;&atilde;o de erros como:</p>\n\n<ul>\n\t<li><strong>Valores fiscais que determinam o valor do imposto,</strong>&nbsp;tais como:&nbsp;base de c&aacute;lculo,&nbsp;al&iacute;quota, diferen&ccedil;a de pre&ccedil;o, quantidade, valor da opera&ccedil;&atilde;o ou da presta&ccedil;&atilde;o; para estas situa&ccedil;&otilde;es se faz necess&aacute;rio a emiss&atilde;o de&nbsp;nota fiscalcomplementar de imposto;</li>\n\t<li>Corre&ccedil;&atilde;o de dados cadastrais que implique mudan&ccedil;a do remetente ou do destinat&aacute;rio ou descri&ccedil;&atilde;o da mercadoria que&nbsp;<strong>altere a al&iacute;quota do imposto;</strong></li>\n\t<li><strong>Data de emiss&atilde;o ou de sa&iacute;da</strong>, pois o fisco pode entender que a altera&ccedil;&atilde;o da data de emiss&atilde;o pode ter o objetivo de reaproveitar a mesma em outras entregas;</li>\n\t<li>Destaque de Impostos ou&nbsp;<strong>quaisquer outros dados que alterem o C&aacute;lculo ou a Opera&ccedil;&atilde;o do Imposto;</strong></li>\n</ul>\n\n<p>Se n&atilde;o for poss&iacute;vel emitir uma carta de corre&ccedil;&atilde;o eletr&ocirc;nica para corrigir os erros de uma&nbsp;nota fiscal&nbsp;autorizada&nbsp;<strong>&eacute; necess&aacute;rio realizar o cancelamento de n&uacute;mero de NFe</strong>, pois a carta de corre&ccedil;&atilde;o somente pode corrigir erros simples.</p>\n\n<h2><strong>Prazo para a transmiss&atilde;o da carta de corre&ccedil;&atilde;o eletr&ocirc;nica</strong></h2>\n\n<p>A CC-e poder&aacute; ser transmitida at&eacute; 720 horas (30 dias) a partir da autoriza&ccedil;&atilde;o de uso da NF-e que ser&aacute; corrigida.&nbsp;<strong>Ela somente poder&aacute; ser transmitida para uma&nbsp;NFe&nbsp;autorizada</strong>, pois n&atilde;o &eacute; poss&iacute;vel corrigir uma NF-e cancelada.</p>\n\n<p>Uma NF-e poder&aacute; ter at&eacute; 20 CC-e&rsquo;s, por&eacute;m, a &uacute;ltima carta de corre&ccedil;&atilde;o deve contemplar&nbsp;<strong>todas as altera&ccedil;&otilde;es.</strong></p>\n\n</html>\r\n");
        jScrollPane14.setViewportView(jTextPane8);

        jScrollPane15.setViewportView(jTextPane5);

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel66.setText("Retorno Sefaz");

        jTextArea4.setColumns(20);
        jTextArea4.setLineWrap(true);
        jTextArea4.setRows(5);
        jTextArea4.setText("CFOP:\nDescrição da Mercadoria:\nCódigos Fiscais:\nPeso: \nVolume:\nAcondicionamento:\nData da Emissão:\nData de Saída:\nDados do Transportador:\nRazão Social do Destinatário:\nEtc...");
        jScrollPane16.setViewportView(jTextArea4);

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setText("Carta de Correção");

        jButton18.setBackground(new java.awt.Color(120, 0, 20));
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setText("Enviar");
        jButton18.setBorderPainted(false);
        jButton18.setContentAreaFilled(false);
        jButton18.setDefaultCapable(false);
        jButton18.setFocusPainted(false);
        jButton18.setOpaque(true);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        chavenfe14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe14.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout CorreçãoNFCELayout = new javax.swing.GroupLayout(CorreçãoNFCE);
        CorreçãoNFCE.setLayout(CorreçãoNFCELayout);
        CorreçãoNFCELayout.setHorizontalGroup(
            CorreçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CorreçãoNFCELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CorreçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CorreçãoNFCELayout.createSequentialGroup()
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chavenfe12, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CorreçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CorreçãoNFCELayout.createSequentialGroup()
                        .addGroup(CorreçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CorreçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chavenfe14, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chavenfe13, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(CorreçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CorreçãoNFCELayout.createSequentialGroup()
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 246, Short.MAX_VALUE))
                    .addComponent(jScrollPane15)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        CorreçãoNFCELayout.setVerticalGroup(
            CorreçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CorreçãoNFCELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CorreçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CorreçãoNFCELayout.createSequentialGroup()
                        .addGroup(CorreçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chavenfe12, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CorreçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chavenfe13, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CorreçãoNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chavenfe14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CorreçãoNFCELayout.createSequentialGroup()
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        CancelarNFCE.setBackground(new java.awt.Color(255, 255, 255));

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setText("Chave de acesso da Nota Fiscal ");

        chavenfe15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe15.setForeground(new java.awt.Color(102, 102, 102));

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setText("Motivo");

        chavenfe16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe16.setForeground(new java.awt.Color(102, 102, 102));

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel69.setText("CNPJ");

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel70.setText("Protocolo");

        chavenfe17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chavenfe17.setForeground(new java.awt.Color(102, 102, 102));

        jTextArea5.setColumns(20);
        jTextArea5.setLineWrap(true);
        jTextArea5.setRows(5);
        jScrollPane22.setViewportView(jTextArea5);

        jButton21.setBackground(new java.awt.Color(150, 0, 20));
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setText("Cancelar NFe");
        jButton21.setBorderPainted(false);
        jButton21.setContentAreaFilled(false);
        jButton21.setDefaultCapable(false);
        jButton21.setFocusPainted(false);
        jButton21.setOpaque(true);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel71.setText("Retorno Sefaz");

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jScrollPane23.setViewportView(jTextArea6);

        jTextPane9.setContentType("text/html"); // NOI18N
        jTextPane9.setText("<html>\r\n <h2><strong>Legisla&ccedil;&atilde;o sobre o cancelamento de NFe:</strong></h2>\n\n<p>A legisla&ccedil;&atilde;o disp&otilde;e que, ap&oacute;s a concess&atilde;o de Autoriza&ccedil;&atilde;o de Uso da NFe, o emitente poder&aacute; solicitar o cancelamento de NF-e,&nbsp;<strong>desde que n&atilde;o tenha havido a circula&ccedil;&atilde;o do respectivo produto ou a presta&ccedil;&atilde;o de servi&ccedil;o.</strong></p>\n\n<p>Quando n&atilde;o &eacute; poss&iacute;vel emitir uma carta de corre&ccedil;&atilde;o que repare os erros de uma nota fiscal autorizada &eacute; necess&aacute;rio realizar o cancelamento de n&uacute;mero de NFe,&nbsp;<strong>pois a carta de corre&ccedil;&atilde;o somente pode corrigir erros simples.</strong></p>\n\n<h2><strong>Principais motivos de cancelamento de NFe:</strong></h2>\n\n<p>Listamos os principais motivos que resultam na escolha por cancelar uma nota fiscal:</p>\n\n<ul>\n\t<li>Desist&ecirc;ncia do Comprador</li>\n\t<li>Cancelamento das vendas,</li>\n\t<li>C&aacute;lculos de Pre&ccedil;os e Impostos Incorretos,</li>\n\t<li>Erros cadastrais, como no CNPJ ou na Raz&atilde;o Social.</li>\n</ul>\n\n<h2><strong>Prazos para cancelamento de NFe:</strong></h2>\n\n<p>Nos termos da Cl&aacute;usula d&eacute;cima segunda do Ajuste SINIEF 07/05,&nbsp;<strong>o prazo legal de cancelamento de NFe, &eacute; de 24hs</strong>&nbsp;contado a partir do momento da autoriza&ccedil;&atilde;o de uso da mesma. Caso n&atilde;o seja poss&iacute;vel realizar o cancelamento neste prazo o contribuinte poder&aacute; faz&ecirc;-lo de forma extempor&acirc;nea.</p>\n\n<h2><strong>Cancelamento de NFe fora do prazo:</strong></h2>\n\n<p>O prazo previsto para o cancelamento extempor&acirc;neo de uma NFe &eacute; de at&eacute; 168 horas, mas devido a alguns problemas pontuais no sistema da SEFAZ (Secretaria da Fazenda),&nbsp;<strong>n&atilde;o h&aacute; bloqueio de solicita&ccedil;&otilde;es feitas</strong>, mesmo que tenha ultrapassado o prazo previsto para o cancelamento extempor&acirc;neo de 168 horas.</p>\n\n<p>A solicita&ccedil;&atilde;o para o cancelamento extempor&acirc;neo&nbsp;<strong>dever&aacute; ser realizada no aplicativo da Receita SIARE</strong>, onde deve ser informado a chave de acesso da nota, o CNPJ do Solicitante e a justificativa do pedido de cancelamento fora do prazo.</p>\n\n<p><strong>Ap&oacute;s a autoriza&ccedil;&atilde;o dever&aacute; ser reenviado e transmitido o pedido de cancelamento de NFe utilizando a funcionalidade de cancelamento dispon&iacute;vel no sistema</strong></p>\n\n<p>Ap&oacute;s a gera&ccedil;&atilde;o do protocolo de autoriza&ccedil;&atilde;o do SIARE a empresa tem at&eacute; 30 dias para transmitir o cancelamento extempor&acirc;neo. Passado este prazo n&atilde;o &eacute; mais poss&iacute;vel solicitar o cancelamento para a mesma NFe.</p>\n\n</html>\r\n");
        jScrollPane24.setViewportView(jTextPane9);

        javax.swing.GroupLayout CancelarNFCELayout = new javax.swing.GroupLayout(CancelarNFCE);
        CancelarNFCE.setLayout(CancelarNFCELayout);
        CancelarNFCELayout.setHorizontalGroup(
            CancelarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CancelarNFCELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CancelarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CancelarNFCELayout.createSequentialGroup()
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chavenfe16, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CancelarNFCELayout.createSequentialGroup()
                        .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chavenfe17, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CancelarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton21)
                        .addGroup(CancelarNFCELayout.createSequentialGroup()
                            .addGroup(CancelarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel68, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(CancelarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(chavenfe15, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                                .addComponent(jScrollPane22))))
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );
        CancelarNFCELayout.setVerticalGroup(
            CancelarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CancelarNFCELayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(CancelarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CancelarNFCELayout.createSequentialGroup()
                        .addGroup(CancelarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63)
                            .addComponent(chavenfe15, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CancelarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chavenfe16, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CancelarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chavenfe17, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CancelarNFCELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        jLayeredPane3.setLayer(ConsultarNFCE, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(InutilizaçãoNFCE, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(CorreçãoNFCE, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(CancelarNFCE, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InutilizaçãoNFCE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(CorreçãoNFCE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(CancelarNFCE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ConsultarNFCE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InutilizaçãoNFCE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(CorreçãoNFCE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(CancelarNFCE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ConsultarNFCE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout NfceLayout = new javax.swing.GroupLayout(Nfce);
        Nfce.setLayout(NfceLayout);
        NfceLayout.setHorizontalGroup(
            NfceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Botões1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLayeredPane3)
        );
        NfceLayout.setVerticalGroup(
            NfceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NfceLayout.createSequentialGroup()
                .addComponent(Botões1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane3))
        );

        jTabbedPane4.addTab("NFC-e 4.0", Nfce);

        javax.swing.GroupLayout PainelNotasFiscaisLayout = new javax.swing.GroupLayout(PainelNotasFiscais);
        PainelNotasFiscais.setLayout(PainelNotasFiscaisLayout);
        PainelNotasFiscaisLayout.setHorizontalGroup(
            PainelNotasFiscaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );
        PainelNotasFiscaisLayout.setVerticalGroup(
            PainelNotasFiscaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelNotasFiscais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelNotasFiscais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        Consultarnfe.setVisible(true);
        ManifestarNFe.setVisible(false);
         InutilizaçãoNfe.setVisible(false);
         Correção.setVisible(false);
         CancelarNfe.setVisible(false);
         
         b1.setBackground(new java.awt.Color(51,51,51));
         b2.setBackground(new java.awt.Color(150,0,20));
         b3.setBackground(new java.awt.Color(150,0,20));
         b4.setBackground(new java.awt.Color(150,0,20));
         b5.setBackground(new java.awt.Color(150,0,20));
    }//GEN-LAST:event_b1ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
    ConsultarNFeSefaz a =  new ConsultarNFeSefaz();
        try {
            a.recebechave(chavenfe.getText());
        } catch (CertificadoException ex) {
            Logger.getLogger(NotasFiscais.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     
     
    jButton2.setBackground(new java.awt.Color(51,51,51));
    
    consulta();
     
     
    }//GEN-LAST:event_jButton2ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        Correção.setVisible(true);
        
        ManifestarNFe.setVisible(false);
      Consultarnfe.setVisible(false);
       InutilizaçãoNfe.setVisible(false);
       CancelarNfe.setVisible(false);
       
        b1.setBackground(new java.awt.Color(150,0,20));
         b2.setBackground(new java.awt.Color(150,0,20));
         b3.setBackground(new java.awt.Color(150,0,20));
         b4.setBackground(new java.awt.Color(51,51,51));
         b5.setBackground(new java.awt.Color(150,0,20));    
       
       
    }//GEN-LAST:event_b4ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
      ManifestarNFe.setVisible(true);
      
      Consultarnfe.setVisible(false);
       InutilizaçãoNfe.setVisible(false);
       Correção.setVisible(false);
       CancelarNfe.setVisible(false);
       
       b1.setBackground(new java.awt.Color(150,0,20));
         b2.setBackground(new java.awt.Color(51,51,51));
         b3.setBackground(new java.awt.Color(150,0,20));
         b4.setBackground(new java.awt.Color(150,0,20));
         b5.setBackground(new java.awt.Color(150,0,20));
    }//GEN-LAST:event_b2ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed

        String Motivo = String.valueOf(jComboBox2.getSelectedItem());
        
        ManifestaçãoNfe a = new ManifestaçãoNfe();
        try {
            a.recebechave(chavenfe1.getText(), Motivo);
        } catch (CertificadoException ex) {
            Logger.getLogger(NotasFiscais.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
       InutilizaçãoNfe.setVisible(true);
       
       ManifestarNFe.setVisible(false);
      Consultarnfe.setVisible(false);
      Correção.setVisible(false);
      CancelarNfe.setVisible(false);
      
      b1.setBackground(new java.awt.Color(150,0,20));
         b2.setBackground(new java.awt.Color(150,0,20));
         b3.setBackground(new java.awt.Color(51,51,51));
         b4.setBackground(new java.awt.Color(150,0,20));
         b5.setBackground(new java.awt.Color(150,0,20));
    }//GEN-LAST:event_b3ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
      String chave = chavenfe5.getText();
            String cnpj = chavenfe6.getText();
            String sequencia = String.valueOf(jComboBox6.getSelectedItem());
            String motivo = jTextArea1.getText();
            
            CartaCorrecaoEletronica a = new CartaCorrecaoEletronica();
            a.recebe(chave, cnpj, sequencia, motivo);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
      CancelarNfe.setVisible(true);
      
      InutilizaçãoNfe.setVisible(false);
       ManifestarNFe.setVisible(false);
      Consultarnfe.setVisible(false);
      Correção.setVisible(false);
      
      b1.setBackground(new java.awt.Color(150,0,20));
         b2.setBackground(new java.awt.Color(150,0,20));
         b3.setBackground(new java.awt.Color(150,0,20));
         b4.setBackground(new java.awt.Color(150,0,20));
         b5.setBackground(new java.awt.Color(51,51,51)); 
    }//GEN-LAST:event_b5ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
       
        String chave = chavenfe7.getText();
        String cnpj = chavenfe8.getText();
        String protocolo = chavenfe9.getText();
        String motivo = jTextArea2.getText();
        
        CancelarNFeNFCe a = new CancelarNFeNFCe();
        a.recebe(chave, protocolo, cnpj, motivo);
        
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        InutilizacaoNFe a = new InutilizacaoNFe();

        try {
            a.recebechave(chavenfe4.getText(), String.valueOf(jComboBox4.getSelectedItem()));
        } catch (CertificadoException ex) {
            Logger.getLogger(NotasFiscais.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
       String chave = chavenfe2.getText();
       
       ConsultarNFCe a = new ConsultarNFCe();
       a.recebechave(chave);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        InutilizaçãoNFCE.setVisible(true);
        CorreçãoNFCE.setVisible(false);
        CancelarNFCE.setVisible(false);
        ConsultarNFCE.setVisible(false);
       
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
       String id = chavenfe11.getText();
       String motivo = String.valueOf(jComboBox7.getSelectedItem());
       
       InutilizacaoNFCe a = new InutilizacaoNFCe();
       a.recebechave(id, motivo);
       
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        InutilizaçãoNFCE.setVisible(false);
        CorreçãoNFCE.setVisible(true);
        CancelarNFCE.setVisible(false);
        ConsultarNFCE.setVisible(false);
        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
      String chave = chavenfe12.getText();
      String cnpj = chavenfe13.getText();
      String sequencia = chavenfe14.getText();
      String motivo = jTextArea4.getText();
      
      CartaCorrecaoNFCe a = new CartaCorrecaoNFCe();
      a.recebe(chave, cnpj, sequencia, motivo);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
      InutilizaçãoNFCE.setVisible(false);
        CorreçãoNFCE.setVisible(false);
        CancelarNFCE.setVisible(true);
        ConsultarNFCE.setVisible(false);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        InutilizaçãoNFCE.setVisible(false);
        CorreçãoNFCE.setVisible(false);
        CancelarNFCE.setVisible(false);
        ConsultarNFCE.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
       String chave = chavenfe15.getText();
       String cnpj = chavenfe16.getText();
       String protocolo = chavenfe17.getText();
       String motivo = jTextArea5.getText();
       
       CancelarNFCe a = new CancelarNFCe();
       a.recebe(chave, protocolo, cnpj, motivo);
    }//GEN-LAST:event_jButton21ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Botões;
    private javax.swing.JPanel Botões1;
    private javax.swing.JPanel CancelarNFCE;
    private javax.swing.JPanel CancelarNfe;
    private javax.swing.JPanel ConsultarNFCE;
    private javax.swing.JPanel Consultarnfe;
    private javax.swing.JPanel Correção;
    private javax.swing.JPanel CorreçãoNFCE;
    private javax.swing.JPanel InutilizaçãoNFCE;
    private javax.swing.JPanel InutilizaçãoNfe;
    private javax.swing.JPanel ManifestarNFe;
    private javax.swing.JPanel Nfce;
    private javax.swing.JPanel Nfe;
    private javax.swing.JPanel PainelNotasFiscais;
    public static javax.swing.JTextPane RetornoInutilização;
    public static javax.swing.JTextPane RetornoInutilização1;
    public static javax.swing.JTextArea Retornonfe;
    public static javax.swing.JTextArea Retornonfe1;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    public static javax.swing.JTextField chavenfe;
    public static javax.swing.JTextField chavenfe1;
    public static javax.swing.JTextField chavenfe10;
    public static javax.swing.JTextField chavenfe11;
    public static javax.swing.JTextField chavenfe12;
    public static javax.swing.JTextField chavenfe13;
    public static javax.swing.JTextField chavenfe14;
    public static javax.swing.JTextField chavenfe15;
    public static javax.swing.JTextField chavenfe16;
    public static javax.swing.JTextField chavenfe17;
    public static javax.swing.JTextField chavenfe2;
    public static javax.swing.JTextField chavenfe4;
    public static javax.swing.JTextField chavenfe5;
    public static javax.swing.JTextField chavenfe6;
    public static javax.swing.JTextField chavenfe7;
    public static javax.swing.JTextField chavenfe8;
    public static javax.swing.JTextField chavenfe9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    public static javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    public static javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    public static javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane10;
    private javax.swing.JTextPane jTextPane11;
    private javax.swing.JTextPane jTextPane12;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    public static javax.swing.JTextPane jTextPane4;
    public static javax.swing.JTextPane jTextPane5;
    private javax.swing.JTextPane jTextPane6;
    private javax.swing.JTextPane jTextPane7;
    private javax.swing.JTextPane jTextPane8;
    private javax.swing.JTextPane jTextPane9;
    public static javax.swing.JTextPane retornoManifestação;
    // End of variables declaration//GEN-END:variables
}
