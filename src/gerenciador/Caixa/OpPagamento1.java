package gerenciador.Caixa;


import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import model.bean.Adcionais;
import model.bean.Fluxo;
import model.bean.Vendas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marcos
 */
public class OpPagamento1 extends javax.swing.JFrame {

    int quantidade;
    int i = 0;
    float SubTotalGeral;
    float TotalRetorno;
    float troco;
    boolean confirmacao = false;
    Vendas v = new Vendas();    
    private JFrame parent;
    Fluxo fluxo = new Fluxo();
    Tela_Caixa envia;
    Adcionais add = new Adcionais();
    
    void Total(float Total, Fluxo f, int q) {
       quantidade = q;
        SubTotalGeral = Total;
        TotalRetorno = Total;
        fluxo.setAberturaData(f.getAberturaData());
      fluxo.setFluxoCaixa(f.getFluxoCaixa());
        
    }
    
    void adcionais(Adcionais a) {
        add.setAdicionais(a.getAdicionais());
      add.setDesconto(a.getDesconto());
    }
    
    public OpPagamento1(JFrame p) {
        
         this.parent = p;
        initComponents();
        
        
       
        jLabel3.setText("Qual a Forma de Pagamento 1?");
        
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, 0),"Dinheiro");
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"Dinheiro");
   
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0),"Cartão");
   
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0),"CartãoA");
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"Sair");
   
   this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
   this.getRootPane().getActionMap().put("Dinheiro", new AbstractAction(){
       public void actionPerformed(ActionEvent arg0) {
           i++;
           if(i<=quantidade){ 
           dinheiro();
           }
    }
});
   this.getRootPane().getActionMap().put("Cartão", new AbstractAction(){
       public void actionPerformed(ActionEvent arg0) {
          i++;
           if(i<quantidade){
           pegavalor(2);
           }if(i==quantidade){
        cartãocredito(SubTotalGeral);
        } 
    }
    
});
   this.getRootPane().getActionMap().put("CartãoA", new AbstractAction(){
       public void actionPerformed(ActionEvent arg0) {
           i++;
           if(i<quantidade){  
            pegavalor(3);
           }if(i==quantidade){
        cartãoDebito(SubTotalGeral);
        } 
    }
});
   this.getRootPane().getActionMap().put("Sair", new AbstractAction(){
       public void actionPerformed(ActionEvent arg0) {
          
           dispose();
    }
});
   
   
    }

    public void quantidades(){
    
        if(i<quantidade){jLabel3.setText("Qual a Forma de Pagamento "+(i+1)+"?");}
      
        System.out.println("SubTotalAgora:"+SubTotalGeral);
        System.out.println("Comfirmação"+confirmacao);
        if(SubTotalGeral<=0 && confirmacao == true){  
            /*if(envia==null){
                            envia = new Tela_Caixa();
                           // envia.setVisible(true);
                           envia.setPagamento(confirmacao);
                            envia.setVenda(v,TotalRetorno,fluxo);
                                
                    }else{
                                //envia.setVisible(true);
                                envia.setState(Tela_Caixa.NORMAL);
                                envia.setPagamento(confirmacao);
                                envia.setVenda(v,TotalRetorno,fluxo); 
                                
                            }*/
                        OpPagamento frame = (OpPagamento)parent;
                        frame.setVisible(true);
                        frame.Pagamento1(v,TotalRetorno,fluxo, confirmacao,add);
            i=0;
        dispose();
        
        }
       
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
                   
                   troco = troco*(-1);
                       SubTotalGeral=troco;
                       
                    v.setTroco(troco);
                    if(i==1){v.setRecebido1(recebido);
                    v.setModoPagamento1("Dinheiro");}
                    if(i==2){v.setRecebido2(recebido);
                    v.setModoPagamento2("Dinheiro");}
                    if(i==3){v.setRecebido3(recebido);
                    v.setModoPagamento3("Dinheiro");}
                    
                    java.awt.EventQueue.invokeLater(new Runnable() {
                             public void run() {
                                 new TrocoRecebido(4,troco,"Dinheiro").setVisible(true);
                                 }
                             });
                      
                       
                           
                    
                      quantidades();
                       Sefra.dispose();
                    break;
               }else{
                   
                    
                    v.setTroco(troco);
                    if(i==1){v.setRecebido1(recebido);
                    v.setModoPagamento1("Dinheiro");}
                    if(i==2){v.setRecebido2(recebido);
                    v.setModoPagamento2("Dinheiro");}
                    if(i==3){v.setRecebido3(recebido);
                    v.setModoPagamento3("Dinheiro");}
                    confirmacao = true;
                   
                   java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrocoRecebido(2,troco,"Dinheiro").setVisible(true);
                
               
                quantidades();
                
            
                
            }
        });
                   trococerto=true;
                   Sefra.dispose();
                  dispose();
    
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
        jLabel3.setText("Valor nesta Opção");

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
  
  public void pegavalor(int i){
  
      JTextField jTextField1 = new javax.swing.JTextField();
        JLabel jLabel3 = new javax.swing.JLabel();
         JFrame Sefra = new JFrame();
        
        InputMap inputMap = Sefra.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"Recebido");  
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"Voltar"); 
        Sefra.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        Sefra.getRootPane().getActionMap().put("Recebido", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                
            
              
                    String recebidotexto = jTextField1.getText();
                    recebidotexto = recebidotexto.replaceAll(",", ".");
                    float recebido = Float.valueOf(recebidotexto);
               
                    if(recebido>0 && recebido!=0){
                    Sefra.dispose();
                  dispose();
                        if(i==2){
                  cartãocredito(recebido);
                        }
                        if(i==3){
                  cartãoDebito(recebido);
                        }
                    }
                   
                   
    
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
        jLabel3.setText("Valor nesta Opção");

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
  
  public void cartãocredito(float valorACobrar){
  
     
      
      JLabel jLabel31 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
         JFrame Sefra = new JFrame();
        
        InputMap inputMap = Sefra.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"A Receber");  
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"Cancelar");  
        Sefra.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        Sefra.getRootPane().getActionMap().put("A Receber", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
        
               
            if(valorACobrar==SubTotalGeral){
            
            v.setTroco(0);
            if(i==1){v.setRecebido1(valorACobrar);
            v.setModoPagamento1("Cartão Crédito");}
                    if(i==2){v.setRecebido2(valorACobrar);
            v.setModoPagamento2("Cartão Crédito");}
                    if(i==3){v.setRecebido3(valorACobrar);
            v.setModoPagamento3("Cartão Crédito");}
             SubTotalGeral =0;
            confirmacao = true;  
            Sefra.dispose();
            quantidades();
            java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            new TrocoRecebido(3,0,"Cartão Crédito").setVisible(true);
                            }
                        });
            
            
            
            }if(valorACobrar<SubTotalGeral){
            
                SubTotalGeral=SubTotalGeral-valorACobrar;
                
                if(i==1){v.setRecebido1(valorACobrar);
            v.setModoPagamento1("Cartão Crédito");}
                    if(i==2){v.setRecebido2(valorACobrar);
            v.setModoPagamento2("Cartão Crédito");}
                    if(i==3){v.setRecebido3(valorACobrar);
            v.setModoPagamento3("Cartão Crédito");}
              v.setTroco(0);   
                
                quantidades();
                Sefra.dispose();
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            new TrocoRecebido(4,SubTotalGeral,"Cartão Crédito").setVisible(true);
                            }
                        });
              
             
            
            }
            
                  
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
        jLabel31.setText(""+valorACobrar);
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
  public void cartãoDebito(float valorACobrar){
  
      
     
      
      JLabel jLabel31 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
         JFrame Sefra = new JFrame();
        
        InputMap inputMap = Sefra.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"A Receber");  
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"Cancelar");  
        Sefra.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        Sefra.getRootPane().getActionMap().put("A Receber", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                
            if(valorACobrar==SubTotalGeral){
            
            v.setTroco(0);
                if(i==1){v.setRecebido1(valorACobrar);
            v.setModoPagamento1("Cartão Debito");}
                    if(i==2){v.setRecebido2(valorACobrar);
            v.setModoPagamento2("Cartão Debito");}
                    if(i==3){v.setRecebido3(valorACobrar);
            v.setModoPagamento3("Cartão Debito");}
             SubTotalGeral=0;
            confirmacao = true; 
            Sefra.dispose();
            quantidades();
            java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            new TrocoRecebido(3,0,"Cartão Debito").setVisible(true);
                            }
                        });
            
            
            }if(valorACobrar<SubTotalGeral){
            
                SubTotalGeral=SubTotalGeral-valorACobrar;
                
                Sefra.dispose();
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            new TrocoRecebido(4,SubTotalGeral,"Cartão Debito").setVisible(true);
                            }
                        });
                
                
                
                
            }
            
                  
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
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Informações adicionais");
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Qual a Forma de Pagamento 1?");

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/money.png"))); // NOI18N
        jButton5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Á Vista (V)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jButton5.setContentAreaFilled(false);
        jButton5.setOpaque(true);

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/credit-card (1).png"))); // NOI18N
        jButton6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cartão (C)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jButton6.setContentAreaFilled(false);
        jButton6.setOpaque(true);

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/credit-card (2).png"))); // NOI18N
        jButton7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Débito (D)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jButton7.setContentAreaFilled(false);
        jButton7.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 20, Short.MAX_VALUE)))
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
                .addGap(45, 45, 45))
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
            java.util.logging.Logger.getLogger(OpPagamento1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OpPagamento1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OpPagamento1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OpPagamento1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OpPagamento1().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

    

    
}
