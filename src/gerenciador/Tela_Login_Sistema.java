/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador;

import Connection.Servidor;
import gerenciador.Caixa.ConfiguraçõesCaixa;
import gerenciador.Caixa.ImprimirTela;
import gerenciador.Caixa.Tela_Caixa;
import gerenciador.Modulos.ConfiguraçãoGeral;
import gerenciador.Modulos.EmitirNFE;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.bean.ADM;
import model.bean.Caixa;
import model.bean.Fluxo;
import model.dao.AdmDAO;
import model.dao.CaixaDAO;
import model.dao.FluxoDAO;

/**
 *
 * @author Marcos
 */
public class Tela_Login_Sistema extends javax.swing.JFrame {

   Tela_Caixa envia;
   ImprimirTela imprime = new ImprimirTela();
   String Tipo = "caixa";
   
    public Tela_Login_Sistema() {
        initComponents();
        PainelAcessoPainel.setVisible(false);
        PainelAcessoCaixa.setVisible(false);
        PainelEscolha.setVisible(true);
        LabelFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/desktop-3168223_1920.jpg")));
        estilojpanel();
        
        //Altera icone
        this.setIconImage(new ImageIcon(getClass().getResource("/gerenciador/Imagens/Logo 1.png")).getImage());
        
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"Dinheiro");
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0),"Dinheiro");
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"Fechar");
   this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
   this.getRootPane().getActionMap().put("Dinheiro", new AbstractAction(){
       private static final long serialVersionUID = 1L;
       @Override
       public void actionPerformed(ActionEvent arg0) {
           
          if(PainelAcessoPainel.isVisible()){
          BAcessar.doClick();}
        if(PainelAcessoCaixa.isVisible()){
        BAcessar1.doClick();}
          
            
    }
});
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
        SwingUtilities.updateComponentTreeUI(PainelAcessoPainel);
        SwingUtilities.updateComponentTreeUI(PainelAcessoCaixa);
           
        
        
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        PainelAcessoCaixa = new javax.swing.JPanel();
        AcessarConta1 = new javax.swing.JLabel();
        LSenha1 = new javax.swing.JLabel();
        BAcessar1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        LSenha2 = new javax.swing.JLabel();
        TSenha1 = new javax.swing.JPasswordField();
        LSenha3 = new javax.swing.JLabel();
        LSenha4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton29 = new javax.swing.JButton();
        TSenha2 = new javax.swing.JTextField();
        PainelAcessoPainel = new javax.swing.JPanel();
        AcessarConta = new javax.swing.JLabel();
        LUsuario = new javax.swing.JLabel();
        TUsuario = new javax.swing.JTextField();
        LSenha = new javax.swing.JLabel();
        BAcessar = new javax.swing.JButton();
        BEsqueci = new javax.swing.JButton();
        TSenha = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        PainelEscolha = new javax.swing.JPanel();
        AcessarConta2 = new javax.swing.JLabel();
        BAcessar2 = new javax.swing.JButton();
        BAcessar3 = new javax.swing.JButton();
        BAcessar4 = new javax.swing.JButton();
        BAcessar5 = new javax.swing.JButton();
        AcessarConta4 = new javax.swing.JLabel();
        AcessarConta5 = new javax.swing.JLabel();
        AcessarConta6 = new javax.swing.JLabel();
        AcessarConta7 = new javax.swing.JLabel();
        LabelFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Geral do Sistema");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(271, 360));
        jLayeredPane1.setRequestFocusEnabled(false);

        PainelAcessoCaixa.setBackground(new java.awt.Color(255, 255, 255));
        PainelAcessoCaixa.setMaximumSize(new java.awt.Dimension(271, 355));

        AcessarConta1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AcessarConta1.setForeground(new java.awt.Color(102, 102, 102));
        AcessarConta1.setText("Acessar Caixa");
        AcessarConta1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        LSenha1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha1.setForeground(new java.awt.Color(153, 153, 153));
        LSenha1.setText("Caixa");

        BAcessar1.setBackground(new java.awt.Color(204, 204, 204));
        BAcessar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BAcessar1.setText("Acessar");
        BAcessar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BAcessar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAcessar1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/back-arrow.png"))); // NOI18N
        jButton2.setToolTipText("Voltar");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setDefaultCapable(false);
        jButton2.setFocusPainted(false);
        jButton2.setPreferredSize(new java.awt.Dimension(25, 25));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        LSenha2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha2.setForeground(new java.awt.Color(153, 153, 153));
        LSenha2.setText("Senha");

        LSenha3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha3.setForeground(new java.awt.Color(153, 153, 153));
        LSenha3.setText("Loja");

        LSenha4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha4.setForeground(new java.awt.Color(153, 153, 153));
        LSenha4.setText("Operador");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Sede" }));
        jComboBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/eye.png"))); // NOI18N
        jButton29.setBorderPainted(false);
        jButton29.setContentAreaFilled(false);
        jButton29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton29.setDefaultCapable(false);
        jButton29.setFocusPainted(false);
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        TSenha2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout PainelAcessoCaixaLayout = new javax.swing.GroupLayout(PainelAcessoCaixa);
        PainelAcessoCaixa.setLayout(PainelAcessoCaixaLayout);
        PainelAcessoCaixaLayout.setHorizontalGroup(
            PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelAcessoCaixaLayout.createSequentialGroup()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AcessarConta1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LSenha4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                            .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                                    .addComponent(TSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(8, 8, 8))
                        .addComponent(BAcessar1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                        .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LSenha3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        PainelAcessoCaixaLayout.setVerticalGroup(
            PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AcessarConta1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LSenha3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LSenha4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TSenha2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                        .addComponent(TSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BAcessar1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        PainelAcessoPainel.setBackground(new java.awt.Color(255, 255, 255));
        PainelAcessoPainel.setMaximumSize(new java.awt.Dimension(271, 355));
        PainelAcessoPainel.setPreferredSize(new java.awt.Dimension(271, 360));

        AcessarConta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AcessarConta.setForeground(new java.awt.Color(102, 102, 102));
        AcessarConta.setText(" Acessar Painel");
        AcessarConta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        LUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LUsuario.setForeground(new java.awt.Color(153, 153, 153));
        LUsuario.setText("Usuário");

        TUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TUsuarioActionPerformed(evt);
            }
        });

        LSenha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha.setForeground(new java.awt.Color(153, 153, 153));
        LSenha.setText("Senha");

        BAcessar.setBackground(new java.awt.Color(204, 204, 204));
        BAcessar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BAcessar.setText("Acessar");
        BAcessar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BAcessar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAcessarActionPerformed(evt);
            }
        });

        BEsqueci.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        BEsqueci.setText(">>> Esqueci ou não tenho a senha/Usuario");
        BEsqueci.setBorderPainted(false);
        BEsqueci.setContentAreaFilled(false);
        BEsqueci.setFocusPainted(false);
        BEsqueci.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BEsqueci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BEsqueciMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BEsqueciMouseExited(evt);
            }
        });
        BEsqueci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEsqueciActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/back-arrow.png"))); // NOI18N
        jButton1.setToolTipText("VOLTAR");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setDefaultCapable(false);
        jButton1.setFocusPainted(false);
        jButton1.setPreferredSize(new java.awt.Dimension(25, 25));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelAcessoPainelLayout = new javax.swing.GroupLayout(PainelAcessoPainel);
        PainelAcessoPainel.setLayout(PainelAcessoPainelLayout);
        PainelAcessoPainelLayout.setHorizontalGroup(
            PainelAcessoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelAcessoPainelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PainelAcessoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BAcessar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TUsuario)
                    .addComponent(LUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(BEsqueci, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelAcessoPainelLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AcessarConta, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PainelAcessoPainelLayout.setVerticalGroup(
            PainelAcessoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelAcessoPainelLayout.createSequentialGroup()
                .addGroup(PainelAcessoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelAcessoPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AcessarConta, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(LUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BAcessar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(BEsqueci)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        PainelEscolha.setBackground(new java.awt.Color(255, 255, 255));
        PainelEscolha.setPreferredSize(new java.awt.Dimension(271, 360));
        PainelEscolha.setRequestFocusEnabled(false);

        AcessarConta2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AcessarConta2.setForeground(new java.awt.Color(102, 102, 102));
        AcessarConta2.setText("    O que deseja Acessar?");

        BAcessar2.setBackground(new java.awt.Color(255, 255, 255));
        BAcessar2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BAcessar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/Painel.png"))); // NOI18N
        BAcessar2.setToolTipText("PAINEL");
        BAcessar2.setBorderPainted(false);
        BAcessar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BAcessar2.setFocusPainted(false);
        BAcessar2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BAcessar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAcessar2ActionPerformed(evt);
            }
        });

        BAcessar3.setBackground(new java.awt.Color(255, 255, 255));
        BAcessar3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BAcessar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/nfce.png"))); // NOI18N
        BAcessar3.setToolTipText("CAIXA");
        BAcessar3.setBorderPainted(false);
        BAcessar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BAcessar3.setFocusPainted(false);
        BAcessar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAcessar3ActionPerformed(evt);
            }
        });

        BAcessar4.setBackground(new java.awt.Color(255, 255, 255));
        BAcessar4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BAcessar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/settings.png"))); // NOI18N
        BAcessar4.setToolTipText("CONFIGURAÇÃO");
        BAcessar4.setBorderPainted(false);
        BAcessar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BAcessar4.setFocusPainted(false);
        BAcessar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAcessar4ActionPerformed(evt);
            }
        });

        BAcessar5.setBackground(new java.awt.Color(255, 255, 255));
        BAcessar5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BAcessar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/nfe.png"))); // NOI18N
        BAcessar5.setToolTipText("Balcão");
        BAcessar5.setBorderPainted(false);
        BAcessar5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BAcessar5.setFocusPainted(false);
        BAcessar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAcessar5ActionPerformed(evt);
            }
        });

        AcessarConta4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AcessarConta4.setForeground(new java.awt.Color(102, 102, 102));
        AcessarConta4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AcessarConta4.setText("Painel");

        AcessarConta5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AcessarConta5.setForeground(new java.awt.Color(102, 102, 102));
        AcessarConta5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AcessarConta5.setText("Caixa");

        AcessarConta6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AcessarConta6.setForeground(new java.awt.Color(102, 102, 102));
        AcessarConta6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AcessarConta6.setText("Ajustes");

        AcessarConta7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AcessarConta7.setForeground(new java.awt.Color(102, 102, 102));
        AcessarConta7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AcessarConta7.setText("NF-e");

        javax.swing.GroupLayout PainelEscolhaLayout = new javax.swing.GroupLayout(PainelEscolha);
        PainelEscolha.setLayout(PainelEscolhaLayout);
        PainelEscolhaLayout.setHorizontalGroup(
            PainelEscolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEscolhaLayout.createSequentialGroup()
                .addGroup(PainelEscolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelEscolhaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AcessarConta2, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
                    .addGroup(PainelEscolhaLayout.createSequentialGroup()
                        .addGroup(PainelEscolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PainelEscolhaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(AcessarConta6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(AcessarConta7, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PainelEscolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PainelEscolhaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(BAcessar2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(33, 33, 33)
                                    .addComponent(BAcessar3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PainelEscolhaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(AcessarConta4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(33, 33, 33)
                                    .addComponent(AcessarConta5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PainelEscolhaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(BAcessar4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(33, 33, 33)
                                    .addComponent(BAcessar5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PainelEscolhaLayout.setVerticalGroup(
            PainelEscolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEscolhaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AcessarConta2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelEscolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AcessarConta4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AcessarConta5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelEscolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BAcessar2, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(BAcessar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(PainelEscolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AcessarConta6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AcessarConta7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelEscolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BAcessar4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BAcessar5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(PainelAcessoCaixa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(PainelAcessoPainel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(PainelEscolha, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(PainelAcessoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PainelAcessoPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PainelEscolha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(PainelAcessoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PainelAcessoPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PainelEscolha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(264, 79, 271, 360);

        LabelFundo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/computer-3246121_1920.jpg"))); // NOI18N
        LabelFundo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        LabelFundo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LabelFundo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        getContentPane().add(LabelFundo);
        LabelFundo.setBounds(-380, 0, 1500, 840);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TUsuarioActionPerformed

    private void BEsqueciMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BEsqueciMouseEntered
        BEsqueci.setText("<html><u>>>> Esqueci ou não tenho a senha/Usuario</u>");
    }//GEN-LAST:event_BEsqueciMouseEntered

    private void BEsqueciMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BEsqueciMouseExited
        BEsqueci.setText(">>> Esqueci ou não tenho a senha/Usuario");
    }//GEN-LAST:event_BEsqueciMouseExited

    private void BEsqueciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEsqueciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BEsqueciActionPerformed

    private void BAcessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAcessarActionPerformed
      
       getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                AdmDAO dao = new AdmDAO();
                
                for(ADM p: dao.read()){
    
                 if(TUsuario.getText().equals(p.getUsuario()) && TSenha.getText().equals(p.getSenha())){
                     
                     dispose();
                     
                     if(Tipo.equals("Config")){
                          java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfiguraçãoGeral().setVisible(true);
            }
        });
                         
                     }else{
                     java.awt.EventQueue.invokeLater(new Runnable() {
                                    public void run() {
                                        new TelaGestão().setVisible(true);
                                            }
                                        });
                     }
                 
                 }else{
                     JOptionPane.showMessageDialog(null, "Senha incorreta");}
                     
            
                }
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        
    }//GEN-LAST:event_BAcessarActionPerformed

    private void BAcessar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAcessar1ActionPerformed

        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        CaixaDAO dao = new CaixaDAO();
        FluxoDAO ff = new FluxoDAO();
        Fluxo fluxo = new Fluxo();
        Boolean SenhaCorreta = false;
        Date a = new Date();
        java.sql.Date dataSql = new java.sql.Date(a.getTime());
                for(Caixa p: dao.read()){
    
                 if(Integer.parseInt(String.valueOf(jComboBox1.getSelectedItem())) == p.getIdCaixa() && TSenha1.getText().equals(p.getSenha())){
                   SenhaCorreta = true;
                    
                 }
                 if(SenhaCorreta){
                        Fluxo c = new Fluxo();
                        c.setAberturaData(dataSql);
                        
                        Caixa f = new Caixa();
                        f.setIdCaixa(Integer.parseInt(String.valueOf(jComboBox1.getSelectedItem())));
                        f.setLoja(String.valueOf(jComboBox2.getSelectedItem()));
                        f.setOperador(TSenha2.getText());
                     
                     if(Tipo.equals("Balcao")){
                         
                         java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                    new EmitirNFE().setVisible(true);
                                }
                            });
                         
                     }else{
                         
                        if(envia==null){
                           envia = new Tela_Caixa();
                           envia.setVisible(true);
                           envia.recebedata(c);
                           envia.operador(TSenha2.getText(),String.valueOf(jComboBox1.getSelectedItem()));
                           envia.recebeCaixa(f);
                           imprime.cabeçalho();
                            imprime.DetalheProduto();
                            }else{
                            envia.setVisible(true);
                                envia.setState(Tela_Caixa.NORMAL);
                                envia.operador(TSenha2.getText(),String.valueOf(jComboBox1.getSelectedItem()));
                                envia.recebeCaixa(f);
                            envia.recebedata(c);
                            imprime.cabeçalho();
        imprime.DetalheProduto();
                            }}
                dispose();
                break;
                 }else{
                     
                     JOptionPane.showMessageDialog(null, "Senha incorreta");}
                     
            
                }
    }//GEN-LAST:event_BAcessar1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     PainelAcessoPainel.setVisible(false);
        PainelAcessoCaixa.setVisible(false);
        PainelEscolha.setVisible(true);
        LabelFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/desktop-3168223_1920.jpg")));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BAcessar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAcessar2ActionPerformed
        PainelAcessoPainel.setVisible(true);
        PainelAcessoCaixa.setVisible(false);
        PainelEscolha.setVisible(false);
        LabelFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/computer-3246121_1920.jpg")));
        AcessarConta.setText("Acessar Painel");
    }//GEN-LAST:event_BAcessar2ActionPerformed

    private void BAcessar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAcessar3ActionPerformed
        PainelAcessoPainel.setVisible(false);
        PainelAcessoCaixa.setVisible(true);
        PainelEscolha.setVisible(false);
        LabelFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/shopping-1165437_1920.jpg")));
        AcessarConta1.setText("Acessar Caixa");
    }//GEN-LAST:event_BAcessar3ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
       
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
       
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        PainelAcessoPainel.setVisible(false);
        PainelAcessoCaixa.setVisible(false);
        PainelEscolha.setVisible(true);
        LabelFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/desktop-3168223_1920.jpg")));
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
       
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed

        if (TSenha1.getEchoChar()!=(char)0){
            TSenha1.setEchoChar((char)0);
        }else{
            TSenha1.setEchoChar('\u2022');
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged

        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        CaixaDAO cdao = new CaixaDAO();
      
       for(Caixa c: cdao.readdesc(String.valueOf(jComboBox2.getSelectedItem()), Integer.parseInt(String.valueOf(jComboBox1.getSelectedItem())))){
           
           TSenha2.setText(c.getOperador());
           getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
       }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void BAcessar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAcessar4ActionPerformed
      
        PainelAcessoPainel.setVisible(true);
        PainelAcessoCaixa.setVisible(false);
        PainelEscolha.setVisible(false);
        LabelFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/config.png")));
        Tipo = "Config";
        AcessarConta.setText("Acessar Configurações");
       
        
    }//GEN-LAST:event_BAcessar4ActionPerformed

    private void BAcessar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAcessar5ActionPerformed
      PainelAcessoPainel.setVisible(false);
        PainelAcessoCaixa.setVisible(true);
        PainelEscolha.setVisible(false);
        LabelFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/atacadista.jpg")));
        Tipo = "Balcao";
        AcessarConta1.setText("Acessar Ponto Venda");
    }//GEN-LAST:event_BAcessar5ActionPerformed

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
            java.util.logging.Logger.getLogger(Tela_Login_Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela_Login_Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela_Login_Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela_Login_Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

       
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AcessarConta;
    private javax.swing.JLabel AcessarConta1;
    private javax.swing.JLabel AcessarConta2;
    private javax.swing.JLabel AcessarConta4;
    private javax.swing.JLabel AcessarConta5;
    private javax.swing.JLabel AcessarConta6;
    private javax.swing.JLabel AcessarConta7;
    private javax.swing.JButton BAcessar;
    private javax.swing.JButton BAcessar1;
    private javax.swing.JButton BAcessar2;
    private javax.swing.JButton BAcessar3;
    private javax.swing.JButton BAcessar4;
    private javax.swing.JButton BAcessar5;
    private javax.swing.JButton BEsqueci;
    private javax.swing.JLabel LSenha;
    private javax.swing.JLabel LSenha1;
    private javax.swing.JLabel LSenha2;
    private javax.swing.JLabel LSenha3;
    private javax.swing.JLabel LSenha4;
    private javax.swing.JLabel LUsuario;
    private javax.swing.JLabel LabelFundo;
    private javax.swing.JPanel PainelAcessoCaixa;
    private javax.swing.JPanel PainelAcessoPainel;
    private javax.swing.JPanel PainelEscolha;
    private javax.swing.JPasswordField TSenha;
    private javax.swing.JPasswordField TSenha1;
    private javax.swing.JTextField TSenha2;
    private javax.swing.JTextField TUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton29;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLayeredPane jLayeredPane1;
    // End of variables declaration//GEN-END:variables
}
