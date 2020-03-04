package gerenciador.Caixa;


import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import model.bean.Fluxo;
import model.bean.Vendas;
import gerenciador.Caixa.Tela_Caixa;
import model.bean.Adcionais;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marcos
 */
public class OpPagamento extends javax.swing.JFrame {

    float SubTotalGeral;
    float TotalRetorno;
    float troco;
    boolean trococerto=false;
    boolean confirmacao = false;
    Vendas v = new Vendas();
    Tela_Caixa envia;
    OpPagamento1 envia1;
    Fluxo fluxo = new Fluxo();
    Adcionais add = new Adcionais();
    private JFrame parent;
    
    public void Total(float SubTotalGeral1, Fluxo f, Adcionais a){
     SubTotalGeral= SubTotalGeral1;   
      TotalRetorno = SubTotalGeral1;
      
      add.setAdicionais(a.getAdicionais());
      add.setDesconto(a.getDesconto());
              
      fluxo.setAberturaData(f.getAberturaData());
      fluxo.setFluxoCaixa(f.getFluxoCaixa());
      
    }
    
    public void Pagamento1(Vendas v, float TotalRetorno, Fluxo fluxo, boolean confirmacao, Adcionais adc) {
        
        Tela_Caixa frame = (Tela_Caixa)parent;
                        frame.setVisible(true);
                        frame.setPagamento(confirmacao);
                        frame.setVenda(v,TotalRetorno,fluxo, adc);
                        dispose();
    }
    
    public OpPagamento(JFrame p) {
        
        this.parent = p;
        
        initComponents();
        
        
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, 0),"Dinheiro");
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"Dinheiro");
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0),"Dinheiro");
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"Sair");
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0),"Crédito");
   
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0),"Débito");
   
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0),"Qaunt");
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, 0),"Qaunt");
   this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
   this.getRootPane().getActionMap().put("Dinheiro", new AbstractAction(){
       private static final long serialVersionUID = 1L;
       @Override
       public void actionPerformed(ActionEvent arg0) {
           
          dinheiro();
          
          
            
    }
});
   this.getRootPane().getActionMap().put("Crédito", new AbstractAction(){
       private static final long serialVersionUID = 1L;
       @Override
       public void actionPerformed(ActionEvent arg0) {
          
           cartãocredito();
           
    }
});
   this.getRootPane().getActionMap().put("Débito", new AbstractAction(){
       private static final long serialVersionUID = 1L;
       @Override
       public void actionPerformed(ActionEvent arg0) {
           
           cartãoDebito();
           
    }
});
   this.getRootPane().getActionMap().put("Qaunt", new AbstractAction(){
       private static final long serialVersionUID = 1L;
       @Override
       public void actionPerformed(ActionEvent arg0) {
           jTextField1.requestFocus();
           
          
    }
});
   this.getRootPane().getActionMap().put("Sair", new AbstractAction(){
       private static final long serialVersionUID = 1L;
       @Override
       public void actionPerformed(ActionEvent arg0) {
           dispose();
           
          
    }
});
     
    }

    //Trata a cobrança em dinheiro
  public void dinheiro(){
       
      
      JTextField jTextField1 = new javax.swing.JTextField();
        JLabel jLabel3 = new javax.swing.JLabel();
         JFrame Sefra = new JFrame();
        
        InputMap inputMap = Sefra.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"Recebido");  
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"Voltar"); 
        Sefra.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        Sefra.getRootPane().getActionMap().put("Recebido", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                
             boolean trococerto=false;
                
                while(trococerto!=true){
              
                    String recebidotexto = jTextField1.getText();
                    recebidotexto = recebidotexto.replaceAll(",", ".");
                    float recebido = Float.valueOf(recebidotexto);
                  
               //abrir caixa
               troco = recebido - SubTotalGeral;
               
               
               
               if(troco<0){
                    
                    java.awt.EventQueue.invokeLater(new Runnable() {
                             public void run() {
                                 new TrocoRecebido(1,troco,"Dinheiro").setVisible(true);
                                 }
                             });
                       Sefra.dispose();
                    break;
               }else{
                   Vendas v = new Vendas();
                           
                    v.setTroco(troco);
                    v.setRecebido1(recebido);
                    v.setModoPagamento1("Dinheiro");
                    confirmacao = true;
                    //Pagou
                     java.awt.EventQueue.invokeLater(new Runnable() {
                             public void run() {
              new TrocoRecebido(2,troco,"Dinheiro").setVisible(true);
               }
                             });
                 
            
                    Sefra.dispose();
                  dispose();
                    
                   Tela_Caixa frame = (Tela_Caixa)parent;
                        frame.setVisible(true);
                        frame.setPagamento(confirmacao);
                        frame.setVenda(v,TotalRetorno,fluxo,add);
                  
                    /*if(envia==null){
                           // envia = new Tela_Caixa();
                          // envia.setPagamento(confirmacao);
                          //  envia.setVenda(v,TotalRetorno,fluxo);
                        }else{
                                parent.setVisible(true);
                                envia.setState(Tela_Caixa.NORMAL);
                                envia.setPagamento(confirmacao);
                                envia.setVenda(v,TotalRetorno,fluxo); 
                                
                            }*/
                   
                  
                   trococerto=true;
                  
    
       }}
               

                }
        });
        
        Sefra.getRootPane().getActionMap().put("Voltar", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                
                Sefra.dispose();
                setVisible(true);
            }
        });
         
        
        Sefra.setSize(new java.awt.Dimension(400, 165));
        Sefra.setAlwaysOnTop(true);
        Sefra.setResizable(false);
        Sefra.setLocationRelativeTo(null);
        Sefra.getContentPane().setBackground(new java.awt.Color(150,0,20));
        Sefra.setUndecorated(true);
        Sefra.setVisible(true);

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextField1.setText("0,00");
        jTextField1.selectAll();
        
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Recebido");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Sefra.getContentPane());
        Sefra.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

       
  }
   //Trata Venda Cartão Crédito
  public void cartãocredito(){
  
     
      
      JLabel jLabel31 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
         JFrame Sefra = new JFrame();
        
        InputMap inputMap = Sefra.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"A Receber");  
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"Cancelar");  
        Sefra.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        Sefra.getRootPane().getActionMap().put("A Receber", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                
            
            //v.setTroco(0);
            v.setRecebido1(SubTotalGeral);
            v.setModoPagamento1("Cartão Crédito");
            confirmacao = true;     
            java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            new TrocoRecebido(3,0,"Cartão Crédito").setVisible(true);
                            }
                        });
             
            
                        Tela_Caixa frame = (Tela_Caixa)parent;
                        frame.setVisible(true);
                        frame.setPagamento(confirmacao);
                        frame.setVenda(v,TotalRetorno,fluxo,add);
                      /*if(envia==null){
                           // envia = new Tela_Caixa(); 
                            }else{
                                envia.setVisible(true);
                                envia.setState(Tela_Caixa.NORMAL);
                                
                            envia.setPagamento(confirmacao);
                            envia.setVenda(v,TotalRetorno,fluxo); 
                            }*/
            
                  Sefra.dispose();
                  dispose();
            }
        });
        
        Sefra.getRootPane().getActionMap().put("Cancelar", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                
                Sefra.dispose();
                setVisible(true);
            }
        });
                  
    
        Sefra.setSize(new java.awt.Dimension(400, 165));
        Sefra.setAlwaysOnTop(true);
        Sefra.setResizable(false);
        Sefra.setLocationRelativeTo(null);
        Sefra.getContentPane().setBackground(new java.awt.Color(150,0,20));
        Sefra.setUndecorated(true);
        Sefra.setVisible(true);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel31.setText(""+SubTotalGeral);
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("   A Receber");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Sefra.getContentPane());
        Sefra.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

  
  
  
  } 
   //Trata Venda Cartão Debito
  public void cartãoDebito(){
  
      
     
      
      JLabel jLabel31 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
         JFrame Sefra = new JFrame();
        
        InputMap inputMap = Sefra.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"A Receber");  
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"Cancelar");  
        Sefra.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        Sefra.getRootPane().getActionMap().put("A Receber", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                
            
            
            
           // v.setTroco(0);
             v.setRecebido1(SubTotalGeral);
            v.setModoPagamento1("Cartão Crédito");
            confirmacao = true;
            
            java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            new TrocoRecebido(3,0,"Cartão Debito").setVisible(true);
                            }
                        });
            
            Tela_Caixa frame = (Tela_Caixa)parent;
                        frame.setVisible(true);
                        frame.setPagamento(confirmacao);
                        frame.setVenda(v,TotalRetorno,fluxo,add);
            
                    /*if(envia==null){
                           // envia = new Tela_Caixa(); 
                            }else{
                                envia.setVisible(true);
                                envia.setState(Tela_Caixa.NORMAL);
                                
                            envia.setPagamento(confirmacao);
                            envia.setVenda(v,TotalRetorno,fluxo); 
                            }*/
            
                  Sefra.dispose();
                  dispose();
            }
        });
        
        Sefra.getRootPane().getActionMap().put("Cancelar", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                
                Sefra.dispose();
                setVisible(true);
            }
        });
                  
    
        Sefra.setSize(new java.awt.Dimension(400, 165));
        Sefra.setAlwaysOnTop(true);
        Sefra.setResizable(false);
        Sefra.setLocationRelativeTo(null);
        Sefra.getContentPane().setBackground(new java.awt.Color(150,0,20));
        Sefra.setUndecorated(true);
        Sefra.setVisible(true);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel31.setText(""+SubTotalGeral);
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("   A Receber");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Sefra.getContentPane());
        Sefra.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

  
  
  
  
  
  }
   
  public void Opçoes(){
  
      while(jTextField1.getText().equals("")==false){
         
         this.dispose();
         if(jTextField1.getText().equals("1") || jTextField1.getText().equals("2") || jTextField1.getText().equals("3")){
            int i = Integer.parseInt(jTextField1.getText());
             
             if(envia==null){
                envia1 = new OpPagamento1(OpPagamento.this);
                envia1.setVisible(true);
                envia1.Total(SubTotalGeral, fluxo,i);
                envia1.adcionais(add);
                
           
        }else{
                envia1.setVisible(true);
                envia1.setState(Tela_Caixa.NORMAL);
                envia1.Total(SubTotalGeral, fluxo,i);
                envia1.adcionais(add);
               
        }  
         
      }
     break;}
  
  }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Opções de Pagamento");
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Qual Forma de Pagamento?");

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/money.png"))); // NOI18N
        jButton5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Á Vista (V)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jButton5.setContentAreaFilled(false);
        jButton5.setOpaque(true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/credit-card (1).png"))); // NOI18N
        jButton6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Crédito (C)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jButton6.setContentAreaFilled(false);
        jButton6.setOpaque(true);

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/credit-card (2).png"))); // NOI18N
        jButton7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Débito (D)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jButton7.setContentAreaFilled(false);
        jButton7.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Quantas formas de pagamento? (*)");

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
     
     
     Opçoes();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
      
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(OpPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OpPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OpPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OpPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        
       /*java.awt.EventQueue.invokeLater(new Runnable() {
           @Override 
           public void run() {
                new OpPagamento().setVisible(true);
            }
        });*/
        
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    

    
}
