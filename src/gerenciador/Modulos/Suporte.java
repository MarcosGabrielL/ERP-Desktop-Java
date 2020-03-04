/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.Modulos;

import gerenciador.TelaGestão;
import java.awt.Color;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Marcos
 */
public class Suporte extends javax.swing.JInternalFrame {

    /**
     * Creates new form Suporte
     */
    public Suporte() {
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
        SwingUtilities.updateComponentTreeUI(PainelSuporte);
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
    
    public void email(){
    Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put("mail.smtp.starttls.required",true);
 
            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("softsaj.bug@gmail.com", "Marcos&123");
                             }
                        });
 
            /** Ativa Debug para sessão */
            session.setDebug(true);
 
            try {
 
                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("softsaj.bug@gmail.com")); //Remetente
 
                  Address[] toUser = InternetAddress //Destinatário(s)
                             .parse("softsaj.tecnico@gmail.com");  
 
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject(jTextField36.getText());//Assunto
                  message.setText(jTextArea2.getText());
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);
 
                  JOptionPane.showMessageDialog(null, "E-mail enviado com sucesso!");
 
             } catch (MessagingException e) {
                 JOptionPane.showMessageDialog(null, "Erro:  "+e);
                  throw new RuntimeException(e);
                  
                 
            }
      }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelSuporte = new javax.swing.JPanel();
        jTabbedPane13 = new javax.swing.JTabbedPane();
        jPanel55 = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        LSenha8 = new javax.swing.JLabel();
        LSenha9 = new javax.swing.JLabel();
        LSenha10 = new javax.swing.JLabel();
        jButton32 = new javax.swing.JButton();
        jLabel102 = new javax.swing.JLabel();
        jPanel56 = new javax.swing.JPanel();
        jButton31 = new javax.swing.JButton();
        jLabel147 = new javax.swing.JLabel();
        LSenha149 = new javax.swing.JLabel();
        LSenha150 = new javax.swing.JLabel();
        jTextField36 = new javax.swing.JTextField();
        jScrollPane27 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        PainelSuporte.setBackground(new java.awt.Color(255, 255, 255));
        PainelSuporte.setToolTipText("");

        jTabbedPane13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel55.setBackground(new java.awt.Color(255, 255, 255));
        jPanel55.setToolTipText("");

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(51, 51, 51));
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel101.setText("Telefones");
        jLabel101.setAutoscrolls(true);

        LSenha8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha8.setForeground(new java.awt.Color(150, 0, 20));
        LSenha8.setText("Visite nosso site e inicie uma conversas online 24h ->>");

        LSenha9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha9.setForeground(new java.awt.Color(150, 0, 20));
        LSenha9.setText("75 9 82576108");

        LSenha10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha10.setForeground(new java.awt.Color(150, 0, 20));
        LSenha10.setText("71 9 99609228");

        jButton32.setBackground(new java.awt.Color(150, 0, 20));
        jButton32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton32.setForeground(new java.awt.Color(255, 255, 255));
        jButton32.setText("Ir Para Site");
        jButton32.setBorderPainted(false);
        jButton32.setContentAreaFilled(false);
        jButton32.setOpaque(true);
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(51, 51, 51));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("Chat Online");
        jLabel102.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel55Layout.createSequentialGroup()
                        .addComponent(LSenha8, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LSenha9, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LSenha10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel55Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LSenha8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton32))
                .addGap(18, 18, 18)
                .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LSenha9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LSenha10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel55Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(211, Short.MAX_VALUE)))
        );

        jTabbedPane13.addTab("Chat / Telefones", jPanel55);

        jPanel56.setBackground(new java.awt.Color(255, 255, 255));
        jPanel56.setToolTipText("");

        jButton31.setBackground(new java.awt.Color(150, 0, 20));
        jButton31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton31.setForeground(new java.awt.Color(255, 255, 255));
        jButton31.setText("Enviar");
        jButton31.setBorderPainted(false);
        jButton31.setContentAreaFilled(false);
        jButton31.setOpaque(true);
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jLabel147.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel147.setForeground(new java.awt.Color(51, 51, 51));
        jLabel147.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel147.setText("Envie-nos um E-mail");
        jLabel147.setAutoscrolls(true);

        LSenha149.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha149.setForeground(new java.awt.Color(150, 0, 20));
        LSenha149.setText("Assunto");

        LSenha150.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha150.setForeground(new java.awt.Color(150, 0, 20));
        LSenha150.setText("Mensagem");

        jTextField36.setText("Seu Cnpj/cpf - Código Erro - breve descrição");

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(50);
        jTextArea2.setText("Seu Email: \n\n\nComentarios:");
        jScrollPane27.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel147, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane27)
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LSenha150, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel56Layout.createSequentialGroup()
                                .addComponent(LSenha149, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LSenha149, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(LSenha150, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane27, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane13.addTab("E-mail", jPanel56);

        javax.swing.GroupLayout PainelSuporteLayout = new javax.swing.GroupLayout(PainelSuporte);
        PainelSuporte.setLayout(PainelSuporteLayout);
        PainelSuporteLayout.setHorizontalGroup(
            PainelSuporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
        );
        PainelSuporteLayout.setVerticalGroup(
            PainelSuporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane13)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelSuporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelSuporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed

        try {
            try {
                java.awt.Desktop.getDesktop().browse( new java.net.URI( "https://www.softsaj.com.br/") );
            } catch (IOException ex) {
                Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        email();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton31ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LSenha10;
    private javax.swing.JLabel LSenha149;
    private javax.swing.JLabel LSenha150;
    private javax.swing.JLabel LSenha8;
    private javax.swing.JLabel LSenha9;
    private javax.swing.JPanel PainelSuporte;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JTabbedPane jTabbedPane13;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField36;
    // End of variables declaration//GEN-END:variables
}
