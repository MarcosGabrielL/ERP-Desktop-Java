/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.Modulos;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnderEmi;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEndereco;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUf;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUfEmi;
import br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TRetConsReciNFe;
import br.inf.portalfiscal.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import gerenciador.AcoesNfe.ActionIniciaConfiguraçãoNfe;
import static gerenciador.AcoesNfe.ConsultarNFeSefaz.iniciaConfigurações;
import static gerenciador.Seridor.DesligaServidor;
import gerenciador.TelaGestão;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import model.bean.Fiscal;
import model.dao.FiscalDAO;

/**
 *
 * @author Marcos
 */
public class EmitirNFE extends javax.swing.JFrame {

    //Valores nfe
   public static String Id,Versão,Uf,Cnf,NatOp,Serie,NNF,DhEmi,CMun,TpEmis,CDV,TpAmb,FinNFe,CNPJ,XNome,XFant;
   public static String XLgr,Nro,Bairro,XMun,UF,CEP,Fone,IE,CRT,CPF,Nome,IdToken,CSC;
   public static ConfiguracoesIniciaisNfe configuraçãoNFE;
    
    public EmitirNFE() {
        initComponents();
        estilojpanel();
        estilotabela();//Carrega os estilos
        this.setIconImage(new ImageIcon(getClass().getResource("/gerenciador/Imagens/Logo 1.png")).getImage());
        getContentPane().setBackground(new java.awt.Color(51,51,51));
        
        Vendas.setVisible(true);
        Orçamento.setVisible(false);
        jButton2.setBackground(new java.awt.Color(150,0,20));
        jButton1.setBackground(new java.awt.Color(51,51,51));
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
       try {
           iniciaConfigurações();
       } catch (NfeException ex) {
           Logger.getLogger(EmitirNFE.class.getName()).log(Level.SEVERE, null, ex);
       } catch (CertificadoException ex) {
           Logger.getLogger(EmitirNFE.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(EmitirNFE.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
       addWindowListener(new WindowAdapter() {
	public void windowClosing(WindowEvent evt) {
		
                if (JOptionPane.showConfirmDialog(null,"Deseja sair")==JOptionPane.OK_OPTION){
                    DesligaServidor();
                    dispose();	
                }
                    
        }
        });
       
    }
    
    
     public static void iniciaConfigurações() throws NfeException, CertificadoException, IOException {
        
         
                Certificado certificado = certifidoA1Pfx();
         
	configuraçãoNFE = ActionIniciaConfiguraçãoNfe.iniciaConfigurações();/*ConfiguracoesIniciaisNfe.iniciaConfiguracoes(Estados.BA , 
                ConstantesUtil.AMBIENTE.HOMOLOGACAO,
                certificado,
                "C:\\Users\\Marcos\\Documents\\Plymouth Tecnologia\\gerenciador de vendas\\Gerenciador 4.0 Supermercado Simples\\schemas");
                    */
	String ip = "192.168.0.1";
	int porta = 3128;
	String usuario = "";
	String senha = "";

	configuraçãoNFE.setProxy(ip, porta, usuario , senha);
        
       

	//return configuraçãoNFCE;
            
        
}
    
     private static Certificado certifidoA1Pfx() throws CertificadoException {
         
         FiscalDAO fdao = new FiscalDAO();
         String caminhoCertificado = null;
         String senha = null;
         
         for(Fiscal f: fdao.read()){
         caminhoCertificado = f.getCertificado();
          senha = f.getCSenha();
         }
        

        return CertificadoService.certificadoPfx(caminhoCertificado, senha);
    }
 
    //Pega os dados fiscais para Notas Fiscais 
    public void PegaValoresFiscais(){
    
        
            FiscalDAO fdao = new FiscalDAO();
            String c;
            for(Fiscal f:fdao.read()){
                
                IdToken = f.getIdToken();
                CSC = f.getCSC();
                
                CMun  = f.getCodigoCity();//Código do Município de Ocorrência do Fato Gerador
               // System.out.println("Código do municipio(TelaCAIXA):"+CMun);
                TpAmb = String.valueOf(f.getAmbiente());//Identificação do Ambiente
                c = f.getCNPJ();
                CNPJ = c.replaceAll("\\D","");//CNPJ do emitente
                XNome = f.getRazãoSocial();//Razão Social ou Nome do emitente
                XFant = f.getNomeFantasia();//Nome fantasia
                
                /*enderEmit - Endereço do emitente*/
            XLgr = f.getRua();//Logradouro
            Nro = f.getNumero();//Número
            Bairro = f.getBairro();//Bairro
            XMun = f.getCidade();//Nome do município
            UF = f.getUf();//Sigla da UF
            CEP = f.getCEP();//Código do CEP
            Fone = f.getTelefone();//Telefone
            IE = f.getInscricaoEstadual();//Inscrição Estadual do Emitente
            CRT = f.getCódigoRegimeTributario();//Código de Regime Tributário
            }
            FinNFe = "1";//Finalidade de emissão da NF-e
            
            
            
            //CPF = consumidor.getCPF();//CPF do destinatário
            //Nome = consumidor.getNome();//CPF do destinatário
    
    }
    
    //Vê se conecta com a Sefaz
    public boolean interntet(){
         boolean comunicando = false;
        TRetConsStatServ retorno;
        
       try {
           retorno = Nfe.statusServico(ConstantesUtil.NFE);
           System.err.println("Status:" + retorno.getCStat());
       } catch (NfeException ex) {
           JOptionPane.showMessageDialog(null,"Erro ao conectar com a Sefaz! \nErro:"+ex+"\nIniciando Módulo EPEC de Contigência","Erro Conexão",JOptionPane.ERROR_MESSAGE);
       }
       
       
        try {
            
            InetAddress endereco = InetAddress.getByName("www.google.com");
            int timeout = 30000;
            if (endereco.isReachable(timeout)){
                comunicando = true;
            }
              
        } catch (UnknownHostException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao conectar à internet:"+ex+"\nIniciando Módulo EPEC de Contigência", "Erro Conexão",JOptionPane.ERROR_MESSAGE);
       } catch (IOException ex) {
           Logger.getLogger(EmitirNFE.class.getName()).log(Level.SEVERE, null, ex);
       }
    
        return comunicando;
    }
    //pega todos os valores necessarios para compor a nota fiscal
    public void PegaValores(int quantidade,int Item, Float SubTotalGeral,Adcionais a,Float ACobrar, Vendas v,DDConsumidor consumidor){
    
        new Thread() {
                @Override
                public void run() {
        //quantidade: - Quantidades diferentes de pagamento
        //Item: - Quantidade de Itens
        //SubTotalGeral: - Total Geral da Compra
        //a: - Adicionais (Frete, Seguro, Etc)
        //ACobrar: - Valor Total com adicionais
        //v: - Informações de formas de pagamento e troco
        //consumidor: - Dados do consumidor
        
        int quantidade1 = quantidade;
        int item = Item;
        Float SubTotalGeral1 = SubTotalGeral;
        Adcionais Add = a;
        Float ACobrar1 = ACobrar;
        Vendas venda = v;
        boolean contigencia = false;
        
        NotaNFCe nota = new NotaNFCe();
        
        //Vê se existe internet
        if(interntet()==true){
            nota.setForma("1");//normal
        }else{
            nota.setForma("9");//contigencia
            contigencia = true;
        }
        //Manda pra Gerar Chave valor da forma
        GeraChaveAcesso traschave = new GeraChaveAcesso();
        //Identificador da TAG a ser assinada
        /*Informar a Chave de Acesso precedida do literal ‘NFe’*/
            nota.setCaixa(String.valueOf(terminal));
            nota.setLoja(Loja);
        Id = traschave.getnfce(nota);
            nota.setNumero(Id.substring(28,37));
            nota.setSerie(Id.substring(25,28));
        Versão = "4.00";//Versão do leiaute
        
            Uf    = Id.substring(3,5);//Código da UF do emitente do Documento Fiscal
            Cnf   = Id.substring(38,46);//Código Numérico que compõe a Chave de Acesso
            NatOp = "Venda";//Descrição da Natureza da Operação
            Serie = Id.substring(25,28);//Série do Documento Fiscal
            NNF   = Id.substring(28,37);//Número do Documento Fiscal
                
            TpEmis = nota.getForma();//Tipo de Emissão da NF-e
            CDV = Id.substring(46, 47);//Dígito Verificador da Chave de Acesso daNF-e
            
            Locale locale = new Locale("pt","BR");
                GregorianCalendar calendar = new GregorianCalendar();
                SimpleDateFormat formatador = new SimpleDateFormat("YYYY-MM-dd hh:mm:ssXXX",locale);
                Date d = new Date();
                String data = formatador.format(d.getTime());
                data = data.replaceAll(" ", "T");
            DhEmi = data;//Data e hora de emissão do Documento
            nota.setData(DhEmi);
            nota.setValor(String.valueOf(ACobrar1));
    
            if(contigencia){
            EnvioNFCeContigencia.EnviaValores(IdToken,CSC,nota,venda,configuraçãoNFCE,produtos,quantidade,Item,SubTotalGeral,a,ACobrar,Id,Versão,Uf,Cnf,NatOp,Serie,NNF,DhEmi,CMun,TpEmis,CDV,TpAmb,FinNFe,CNPJ,XNome,XFant,XLgr,Nro,Bairro,XMun,UF,CEP,Fone,IE,CRT,CPF,Nome);
            }else{
            EnvioNFCe.EnviaValores(IdToken,CSC,nota,venda,configuraçãoNFCE,produtos,quantidade,Item,SubTotalGeral,a,ACobrar,Id,Versão,Uf,Cnf,NatOp,Serie,NNF,DhEmi,CMun,TpEmis,CDV,TpAmb,FinNFe,CNPJ,XNome,XFant,XLgr,Nro,Bairro,XMun,UF,CEP,Fone,IE,CRT,CPF,Nome);
            //EnvioNFCe.Envia();
                }
                }
        }.start();
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
        SwingUtilities.updateComponentTreeUI(jPanel3);
         SwingUtilities.updateComponentTreeUI(jPanel4);
        
        
        
    }
    // plota a tabela com um estilo personalizado
    private void estilotabela(){
        //Tamanho horizontal coluna
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(2);//item
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150); //Código
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(300); //Descrição 
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(5); //Quantidade
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(2); //unidade
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(5); //Valor
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(5); //Sub-Total
    
        //Modifica titulo da jtable
        JTableHeader Theader = jTable1.getTableHeader();
        Theader.setBackground(new java.awt.Color(51,51,51)); // change the Background color
        Theader.setForeground(new java.awt.Color(120,0,20)); // change the Foreground
        
        Theader.setFont(new Font("Tahome", Font.BOLD, 16)); // font name style size
        ((DefaultTableCellRenderer)Theader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER); // center header tex
        
        
        
        DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        modelo.setNumRows(0);  //Zera a tabela
        jTable1.setRowHeight(40); // Tamanho vertical da tabela
    
    
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        Vendas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        TCodigo = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        TDescricao = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TValor = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        TUnidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TQuantidade = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        TPeso = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        Orçamento = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));

        jPanel3.setBackground(new java.awt.Color(150, 0, 20));

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Realizar Venda");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setDefaultCapable(false);
        jButton1.setFocusPainted(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setOpaque(true);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(150, 0, 20));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Orçamento");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setDefaultCapable(false);
        jButton2.setFocusPainted(false);
        jButton2.setOpaque(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Vendas.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBorder(null);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Descrição", "NCM", "CST", "CFOP", "Tipo", "Unidade", "Quantidade", "Valor Un", "Valor Total", "ICMS", "Alíquota ICMS", "Base Aliquota"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(2).setHeaderValue("NCM");
            jTable1.getColumnModel().getColumn(3).setHeaderValue("CST");
            jTable1.getColumnModel().getColumn(4).setHeaderValue("CFOP");
            jTable1.getColumnModel().getColumn(5).setHeaderValue("Tipo");
            jTable1.getColumnModel().getColumn(6).setHeaderValue("Unidade");
            jTable1.getColumnModel().getColumn(7).setHeaderValue("Quantidade");
            jTable1.getColumnModel().getColumn(8).setHeaderValue("Valor Un");
            jTable1.getColumnModel().getColumn(9).setHeaderValue("Valor Total");
            jTable1.getColumnModel().getColumn(10).setHeaderValue("ICMS");
            jTable1.getColumnModel().getColumn(11).setHeaderValue("Alíquota ICMS");
            jTable1.getColumnModel().getColumn(12).setHeaderValue("Base Aliquota");
        }

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setText("Código");

        TCodigo.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        TCodigo.setForeground(new java.awt.Color(51, 51, 51));
        TCodigo.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));
        TCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TCodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TCodigoFocusLost(evt);
            }
        });
        TCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCodigoActionPerformed(evt);
            }
        });
        TCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TCodigoKeyReleased(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/magnifier.png"))); // NOI18N
        jButton3.setToolTipText("Buscar Produto");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setDefaultCapable(false);
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setText("Descrição");

        TDescricao.setEditable(false);
        TDescricao.setBackground(new java.awt.Color(255, 255, 255));
        TDescricao.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        TDescricao.setForeground(new java.awt.Color(51, 51, 51));
        TDescricao.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));
        TDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TDescricaoActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel9.setText("Valor R$");

        TValor.setEditable(false);
        TValor.setBackground(new java.awt.Color(255, 255, 255));
        TValor.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        TValor.setForeground(new java.awt.Color(51, 51, 51));
        TValor.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));
        TValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TValorActionPerformed(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(204, 255, 255));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel13.setText("Unidade");

        TUnidade.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        TUnidade.setForeground(new java.awt.Color(51, 51, 51));
        TUnidade.setText("Un");
        TUnidade.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));
        TUnidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TUnidadeActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(204, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel6.setText("Quantidade");

        TQuantidade.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        TQuantidade.setForeground(new java.awt.Color(51, 51, 51));
        TQuantidade.setText("1");
        TQuantidade.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));
        TQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TQuantidadeActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(204, 255, 255));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel12.setText("Peso");

        TPeso.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        TPeso.setForeground(new java.awt.Color(51, 51, 51));
        TPeso.setText("0");
        TPeso.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));
        TPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TPesoActionPerformed(evt);
            }
        });
        TPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TPesoKeyReleased(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/magnifier.png"))); // NOI18N
        jButton4.setToolTipText("Buscar Produto");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setDefaultCapable(false);
        jButton4.setFocusPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1)
                .addGap(9, 9, 9))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TValor, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TPeso)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(TCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(12, 12, 12)
                        .addComponent(TDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)))
                .addGap(31, 31, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel6);

        javax.swing.GroupLayout VendasLayout = new javax.swing.GroupLayout(Vendas);
        Vendas.setLayout(VendasLayout);
        VendasLayout.setHorizontalGroup(
            VendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        VendasLayout.setVerticalGroup(
            VendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
        );

        Orçamento.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout OrçamentoLayout = new javax.swing.GroupLayout(Orçamento);
        Orçamento.setLayout(OrçamentoLayout);
        OrçamentoLayout.setHorizontalGroup(
            OrçamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1005, Short.MAX_VALUE)
        );
        OrçamentoLayout.setVerticalGroup(
            OrçamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 591, Short.MAX_VALUE)
        );

        jLayeredPane1.setLayer(Vendas, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Orçamento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Vendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Orçamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Vendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Orçamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        Vendas.setVisible(true);
        Orçamento.setVisible(false);
        jButton2.setBackground(new java.awt.Color(150,0,20));
        jButton1.setBackground(new java.awt.Color(51,51,51));
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       Vendas.setVisible(false);
        Orçamento.setVisible(true);
        jButton2.setBackground(new java.awt.Color(51,51,51));
        jButton1.setBackground(new java.awt.Color(150,0,20));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TCodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TCodigoFocusGained
      
    }//GEN-LAST:event_TCodigoFocusGained

    private void TCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TCodigoFocusLost

    }//GEN-LAST:event_TCodigoFocusLost

    private void TCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCodigoActionPerformed

    }//GEN-LAST:event_TCodigoActionPerformed

    private void TCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCodigoKeyPressed

        

    }//GEN-LAST:event_TCodigoKeyPressed

    private void TCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCodigoKeyReleased
       
    }//GEN-LAST:event_TCodigoKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void TDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TDescricaoActionPerformed

    private void TValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TValorActionPerformed

    private void TUnidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TUnidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TUnidadeActionPerformed

    private void TQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TQuantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TQuantidadeActionPerformed

    private void TPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TPesoActionPerformed
        TCodigo.requestFocus();
    }//GEN-LAST:event_TPesoActionPerformed

    private void TPesoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPesoKeyReleased

        TCodigo.requestFocusInWindow();
    }//GEN-LAST:event_TPesoKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(EmitirNFE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmitirNFE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmitirNFE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmitirNFE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmitirNFE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Orçamento;
    public static javax.swing.JTextField TCodigo;
    private javax.swing.JTextField TDescricao;
    public static javax.swing.JTextField TPeso;
    private javax.swing.JTextField TQuantidade;
    private javax.swing.JTextField TUnidade;
    private javax.swing.JTextField TValor;
    private javax.swing.JPanel Vendas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
