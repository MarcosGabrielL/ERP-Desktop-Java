/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.Caixa;



import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.dom.ConfiguracoesWebNfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.NFCeUtil;
import br.com.samuelweb.nfe.util.WebServiceUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnderEmi;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Dest;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS60;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Emit;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Ide;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.InfAdic;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Pag;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Pag.DetPag.Card;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Total;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUfEmi;
import br.inf.portalfiscal.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import static gerenciador.AcoesNFCe.ConsultarNFCe.iniciaConfigurações;
import gerenciador.AcoesNFCe.EnvioNFCe;
import gerenciador.AcoesNFCe.EnvioNFCeContigencia;
import gerenciador.AcoesNFCe.GeraChaveAcesso;
import gerenciador.AcoesNFCe.IniciaConfiguraçãoNFCE;
import gerenciador.AcoesNfe.IniciaConfiguraçãoNfce;
import gerenciador.Seridor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.bean.Caixa;
import model.bean.Fluxo;
import model.bean.Produto;
import model.bean.Vendidos;
import model.dao.CaixaDAO;
import model.dao.FluxoDAO;
import model.dao.ProdutoDAO;
import model.dao.VendidosDAO;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.text.StyleConstants;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import model.bean.Vendas;
import model.dao.VendasDAO;
import model.bean.Adcionais;
import model.bean.DDConsumidor;
import model.bean.Fiscal;
import model.bean.NotaNFCe;
import model.dao.FiscalDAO;

/**
 *
 * @author Marcos
 */
public class Tela_Caixa extends javax.swing.JFrame {

   float SubTotalProduto;
   float SubTotalGeral = 0;
   float TotalGeral;
   public static int terminal=1, Item=0; 
   int NumeroVenda, impressora; 
   public static String Loja="Sede";
   String operador = "Gerente";
   java.sql.Date Data;
   boolean quantidadekg = false;
   boolean quantidadeCx=false;
   boolean quantidadeUn=false;
   boolean pagou = false;
   Adcionais add = new Adcionais();
   Adcionais AdicionaisCompra = new Adcionais();
   DDConsumidor consumidor = new DDConsumidor();
   Vendas v = new Vendas();
   OpPagamento envia;
   ConfiguraçõesCaixa config;
   OutrosVa enviaAdd;
   CPFnaNota enviaCPF;
   ImprimirTela imprime = new ImprimirTela();
   Fluxo fluxo = new Fluxo();
   float TotalCompra;
   float TotalFinal;
   List<Produto> produtos = new ArrayList<>();
   
   
   String pesove;
   SerialComm serial = new SerialComm();
   
   //Valores nfe
   public static String Id,Versão,Uf,Cnf,NatOp,Serie,NNF,DhEmi,CMun,TpEmis,CDV,TpAmb,FinNFe,CNPJ,XNome,XFant;
   public static String XLgr,Nro,Bairro,XMun,UF,CEP,Fone,IE,CRT,CPF,Nome,IdToken,CSC;
   public static ConfiguracoesIniciaisNfe configuraçãoNFCE;
   public static boolean contigencia;
   String Operador;
   String Caixa;
   Caixa caixa = new Caixa();
   
   //recebe a data na qual o caixa foi aberto 
   public void recebedata(Fluxo f){
   
       Data=f.getAberturaData();
       fluxo.setAberturaData(f.getAberturaData());
       
       
   }
   public void recebeCaixa(Caixa c){
       //Loja = c.getLoja();
       //Caixa = String.valueOf(c.getIdCaixa());
       //Operador = c.getOperador();
       caixa = c;
       Loja = caixa.getLoja();
       System.err.println();
                
                
                chamaconfiguração();
   
   }
   public void operador(String text, String valueOf) {
       Operador = text;
       Caixa = valueOf;
       jLabel1.setText("<html> Operador:"+Operador+"<br/> Caxia: "+Caixa+"</html>");
    }
   
    public Tela_Caixa() {
        
         initComponents(); //Desenha
         setExtendedState(MAXIMIZED_BOTH); //Maximiza
         estilotabela();//Carrega os estilos
         TCodigo.requestFocus(); //Move o cursor pro código
         TQuantidade.setText("1"); // seta unidades como 1
         DataHora();
         
         this.setIconImage(new ImageIcon(getClass().getResource("/gerenciador/Imagens/Logo 1.png")).getImage());
         
       try {
           iniciaConfigurações();
       } catch (NfeException ex) {
           System.out.println("Erro Iniciar configuração caixa:" + ex.getMessage());
       } catch (CertificadoException ex) {
           System.out.println("Erro Iniciar configuração caixa:" + ex.getMessage());
       } catch (IOException ex) {
          System.out.println("Erro Iniciar configuração caixa:" + ex.getMessage());
       }
         
       //Inicia Configuração fiscal e pega valores normais a todas as notas
       PegaValoresFiscais();
               
           
    }
    
    
    public void chamaconfiguração(){
    
        new Thread() {
                @Override
                public void run() {
        if(config==null){
            
                config = new ConfiguraçõesCaixa(Tela_Caixa.this,caixa);
                config.setVisible(true);
                
           
        }else{
                config.setVisible(true);
                config.setState(Tela_Caixa.NORMAL);
               
        } 
                }
        }.start();
        
       IniciaComunicação();
    
    }
    
    public void IniciaComunicação(){
    
        //Atualiza nome da impressora para impressão
         CaixaDAO cdao = new CaixaDAO();
        for(Caixa c : cdao.read()){
            if(c.getIdCaixa()==caixa.getIdCaixa() && c.getLoja().equals(caixa.getLoja())){
            
             caixa.setNomeImpressora(c.getNomeImpressora());
            }  
        }
        
        //Atualiza porta da balança e inicia comunicação
        
        if(config==null){
                config = new ConfiguraçõesCaixa(Tela_Caixa.this,caixa);
                config.iniciacomunicabalanca();
           
        }else{
                config.setState(Tela_Caixa.NORMAL);
                config.iniciacomunicabalanca();
        } 
    }
    
    public void pegaimpressora(Caixa c){
    
        caixa.setNomeImpressora(c.getNomeImpressora());
        
    }
    
    public static void AssinaSSL(String caminhoCertificado, String senhaCertificado) {
        System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
        System.clearProperty("javax.net.ssl.keyStore");
        System.clearProperty("javax.net.ssl.keyStorePassword");
        System.clearProperty("javax.net.ssl.trustStore");
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.keyStore", caminhoCertificado);
        System.setProperty("javax.net.ssl.keyStorePassword", senhaCertificado);
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.setProperty("javax.net.ssl.trustStore", "cacerts");
        
    }
    
    public static void iniciaConfigurações() throws NfeException, CertificadoException, IOException {
        
         
                Certificado certificado = certifidoA1Pfx();
         
	configuraçãoNFCE = IniciaConfiguraçãoNFCE.iniciaConfigurações();/*ConfiguracoesIniciaisNfe.iniciaConfiguracoes(Estados.BA , 
                ConstantesUtil.AMBIENTE.HOMOLOGACAO,
                certificado,
                "C:\\Users\\Marcos\\Documents\\Plymouth Tecnologia\\gerenciador de vendas\\Gerenciador 4.0 Supermercado Simples\\schemas");
                    */
	String ip = "192.168.0.1";
	int porta = 3128;
	String usuario = "";
	String senha = "";

	configuraçãoNFCE.setProxy(ip, porta, usuario , senha);
        
       

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
        AssinaSSL(caminhoCertificado,senha);

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
            
            
            
            CPF = consumidor.getCPF();//CPF do destinatário
            Nome = consumidor.getNome();//CPF do destinatário
    
    }
    //Recebe dados do consumidor se CPF na nota
    public void pegaConsumidor(DDConsumidor c){
    
        consumidor.setCPF(c.getCPF());
        consumidor.setNome(c.getNome());
        consumidor.setEndreço(c.getEndreço());
        CPF = c.getCPF();
        Nome = c.getNome();
        BotãoPagamento();
        
    }
    // recebe valores adcionais do frame e atualiza Total
    public void pegaAdd(Adcionais a){
        
        add.setAdicionais(a.getAdicionais());
        add.setDesconto(a.getDesconto());
        
        DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        modelo.addRow(new Object[]{
                            "", //item
                         "", //codigo
                        "Adicionais", //descrição
                        "", //peso
                        "", //unidade
                        add.getAdicionais(), //valor Unitario
                        add.getAdicionais() //SubTotal
                        });
        modelo.addRow(new Object[]{
                            "", //item
                         "", //codigo
                        "Frete", //descrição
                        "", //peso
                        "", //unidade
                        add.getFrete(), //valor Unitario
                        add.getFrete() //SubTotal
                        });
        modelo.addRow(new Object[]{
                            "", //item
                         "", //codigo
                        "Outros", //descrição
                        "", //peso
                        "", //unidade
                        add.getOutros(), //valor Unitario
                        add.getOutros()//SubTotal
                        });
        modelo.addRow(new Object[]{
                            "", //item
                         "", //codigo
                        "Seguro", //descrição
                        "", //peso
                        "", //unidade
                        add.getSeguro(), //valor Unitario
                        add.getSeguro()//SubTotal
                        });
        modelo.addRow(new Object[]{
                            "", //item
                         "", //codigo
                        "Desconto", //descrição
                        "", //peso
                        "", //unidade
                        add.getDesconto(), //valor Unitario
                        add.getDesconto()//SubTotal
                        });
                        
    
       TotalCompra = SubTotalGeral + add.getAdicionais() - add.getDesconto();
       BigDecimal bd = new BigDecimal(TotalCompra).setScale(3, RoundingMode.HALF_EVEN);
       JTotal.setText(""+bd.doubleValue());
    
    }
    //recebe Modo de Pagamento
    //       Troco
    //       Recebido
    //       Fluxo
    public void setVenda(Vendas pagamento, float SubTotalGeral,  Fluxo f, Adcionais add) {
        for(int i=1;i<4;i++){
            if(i==1){
        v.setModoPagamento1(pagamento.getModoPagamento1());
        v.setRecebido1(pagamento.getRecebido1());
        v.setTroco(pagamento.getTroco());}
            if(i==2){
        v.setModoPagamento2(pagamento.getModoPagamento2());
        v.setRecebido2(pagamento.getRecebido2());
        v.setTroco(pagamento.getTroco());}
            if(i==3){
        v.setModoPagamento3(pagamento.getModoPagamento3());
        v.setRecebido3(pagamento.getRecebido3());
        v.setTroco(pagamento.getTroco());}
        }
        
        
        if(pagou == true){
            
            TotalGeral = TotalGeral + SubTotalGeral;
            
           // JOptionPane.showMessageDialog(null,"Valor da Venda em setVenda:"+SubTotalGeral);
            String Fluxo = f.getFluxoCaixa();
            Float fluxofloat = Float.parseFloat(Fluxo);
            fluxo.setFluxoCaixa(String.valueOf(fluxofloat + SubTotalGeral));
            fluxo.setAberturaData(f.getAberturaData());
           // JOptionPane.showMessageDialog(null,"ValorFluxo em setVenda:"+fluxo.getFluxoCaixa());
           
           
           TotalFinal = SubTotalGeral;
           AdicionaisCompra = add;
           SalvaValorVenda(v, SubTotalGeral, fluxo, add);
         
        }
        
    }
    
    
    //vê se a compra foi paga
    public void setPagamento(boolean cofnirmacao){
    
        pagou = cofnirmacao;
         
       
        
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
    //Busca e Preenche os campos com as informações do Produto
    public boolean preencher(String desc){
    
        boolean boleano=false;
       ProdutoDAO pdao = new ProdutoDAO(); //instancia
       
       for(Produto p: pdao.readdesc(desc)){ //chama função acha produto com codigo recebido
           
        TCodigo.setText(p.getCodigo());  //Seta codigo no campo codigo
        TDescricao.setText(p.getDescrição()); // Seta DEscrição no Campo descrição
        
        if(p.getUnidade().equals("")){ //Se campo unidade estiver vazia, produto foi cadastrado incorretamente
           JOptionPane.showMessageDialog(null, "Produto não cadastrado Corretamente"); 
        }if(p.getUnidade().equals("Kg")){  
            quantidadekg=true;
            quantidadeCx=false;
            quantidadeUn=false;
            TUnidade.setText("Kg"); //Kg é setado como unidade do produto
            TValor.setText(p.getPreçoUn());//Se campo = Kg, o valor do Kg é setado no campo valor
        }if(p.getUnidade().equals("Cx")){
            quantidadekg=false;
            quantidadeCx=true;
            quantidadeUn=false;
            TUnidade.setText("Cx"); //Cx é setado como unidade do produto
            TValor.setText(p.getPreçoUn());  //Se campo = Cx, o valor da Cx é setado no campo valor
        }if(p.getUnidade().equals("Un")){
            quantidadekg=false;
            quantidadeCx=false;
            quantidadeUn=true;
            TUnidade.setText("Un"); //Un é setado como unidade do produto
            TValor.setText(p.getPreçoUn()); //Se campo = Kg, o valor da Unidade é setado no campo valor
        }
        
        
        //Se for encontrado todas as informações referentes ao produto, boleano retorna verdadeiro
        if(TValor.getText()!=""){
            boleano=true;
        }else{
            boleano=false;
        }
       }
       
       
           
           return boleano;
    }
    //Calcula os totais, e preenche a tabela com os valores
    private void Codigokeypressed(){
      
        TDescricao.setText("");
       TValor.setText("");
            
       boolean boleano=false;
       ProdutoDAO pdao = new ProdutoDAO(); //instancia
       Produto produto = new Produto();
       
       for(Produto p: pdao.readdesc( TCodigo.getText())){ //chama função acha produto com codigo recebido
           
        TCodigo.setText(p.getCodigo());  //Seta codigo no campo codigo
        TDescricao.setText(p.getDescrição()); // Seta DEscrição no Campo descrição
        
        if(p.getUnidade().equals("")){ //Se campo unidade estiver vazia, produto foi cadastrado incorretamente
           JOptionPane.showMessageDialog(null, "Produto não cadastrado Corretamente"); 
        }if(p.getUnidade().equals("Kg")){  
            quantidadekg=true;
            TUnidade.setText(p.getUnidade()); //Kg é setado como unidade do produto
            TValor.setText(p.getPreçoUn());//Se campo = Kg, o valor do Kg é setado no campo valor
        }else{
            quantidadekg=false;
            TUnidade.setText(p.getUnidade()); //Cx é setado como unidade do produto
            TValor.setText(p.getPreçoUn());  //Se campo = Cx, o valor da Cx é setado no campo valor
        }/*if(p.getUnidade().equals("Cx")){
            
            quantidadeCx=true;
            quantidadeUn=false;
            
        }if(p.getUnidade().equals("Un") || p.getUnidade().equals("UN")){
            quantidadekg=false;
            quantidadeCx=false;
            quantidadeUn=true;
            TUnidade.setText("UN"); //Un é setado como unidade do produto
            TValor.setText(p.getPreçoUn()); //Se campo = Kg, o valor da Unidade é setado no campo valor
        }*/
        
            //Pega todas as informações do produto
            produto.setCodigo(p.getCodigo());
            produto.setDescrição(p.getDescrição());
            //produto.setQuantidade(p.getQuantidade());
            produto.setUnidade(p.getUnidade());
            produto.setPreçoUn(p.getPreçoUn());
            produto.setSubTotal(p.getSubTotal());
            produto.setAliquotaICMS(p.getAliquotaICMS());
            produto.setBaseICMS(p.getBaseICMS());
            produto.setCEAN(p.getCEAN());
            produto.setCEANTrib(p.getCEANTrib());
            produto.setCEST(p.getCEST());
            produto.setCFOP(p.getCFOP());
            produto.setCST(p.getCST());
            produto.setData(p.getData());
            produto.setICMS(p.getICMS());
            produto.setNCM(p.getNCM());
            produto.setQTrib(p.getQTrib());
            produto.setUnidade(p.getUnidade());
            produto.setUnidadeTributavel(p.getUnidadeTributavel());
            produto.setVUnTrib(p.getVUnTrib());
            produto.setBCICMS(p.getBCICMS());
            produto.setBCICMSST(p.getBCICMSST());
            produto.setPMVAST(p.getPMVAST());
            produto.setPRedBCST(p.getPRedBCST());
            produto.setVBCST(p.getVBCST());
            produto.setPICMSST(p.getPICMSST());
            produto.setPRedBC(p.getPRedBC());
            produto.setVICMSST(p.getVICMSST());
            produto.setPDif(p.getPDif());
            produto.setVICMSDif(p.getVICMSDif());
            produto.setVICMS(p.getVICMS());
            produto.setVCredICMSSN(p.getVCredICMSSN());
            produto.setPCredSN(p.getPCredSN());
            produto.setCSTPIS(p.getCSTPIS());
            produto.setVBCPIS(p.getVBCPIS());
            produto.setPPIS(p.getPPIS());
            produto.setVPIS(p.getVPIS());
            produto.setCSTCOFINS(p.getCSTCOFINS());
            produto.setVBCCOFINS(p.getVBCCOFINS());
            produto.setPCOFINS(p.getPCOFINS());
            produto.setVCOFINS(p.getVCOFINS());
            produto.setVentrada(p.getVentrada());
            produto.setAFederalN(p.getAFederalN());
            produto.setAFederalI(p.getAFederalI());
            produto.setAEstadual(p.getAEstadual());
            produto.setAMunicipal(p.getAMunicipal());
            produto.setTipo(p.getTipo());
            produto.setValidade(p.getValidade());
            //produto.setItem(Item);
            if(p.getQuantidade()<=0){
                JOptionPane.showMessageDialog(null, "Produto em falta no estoque! Contate o Gerente ou supervisor de vendas!");
            }
        
        //Se for encontrado todas as informações referentes ao produto, boleano retorna verdadeiro
        if(TValor.getText()!=""){
            boleano=true;
        }else{
            boleano=false;
        }
       }
       
            //Caso todas as informações tiverem sido encontradas
                if(boleano==true){
            
                    DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
                    float Quantidade = Float.parseFloat(TQuantidade.getText());
                    float ValorDaUnidade = Float.parseFloat(TValor.getText());
                   
                    int unidade = 0;
                   
                    //Se o valor do produto esteja relacionado ao Kg, precisamos obter o peso deste
                    if(quantidadekg==true){
                       
                        PegaPeso();
                        float peso = Float.parseFloat(TPeso.getText());
                        if(TPeso.getText().equals("0")){
                            JOptionPane.showInputDialog("Peso não encontrado");
                        }else{
                        //Se peso lido, multiplica este pelo seu valor unitario, que nos dar o sub total
                        SubTotalProduto = peso*ValorDaUnidade;
                       //Adciona todos os valores referentes a venda do produto na tabela na Tela Caixa
                        modelo.addRow(new Object[]{
                            Item=Item+1, //item
                         TCodigo.getText(), //codigo
                        TDescricao.getText(), //descrição
                        TPeso.getText(), //peso
                        TUnidade.getText(), //unidade
                        TValor.getText(), //valor Unitario
                        String.valueOf(SubTotalProduto) //SubTotal
                        });
                        
                        produto.setQuantidade(Float.parseFloat(TPeso.getText()));
                        produto.setItem(Item);
                        }
                        
                        
                    }else{
                        //Se não for produto precificado pelo peso
                        SubTotalProduto = Quantidade*ValorDaUnidade;
                        produto.setQuantidade(Quantidade);
                        produto.setSubTotal(SubTotalProduto);
                       //Adciona todos os valores referentes a venda do produto na tabela na Tela Caixa 
                        modelo.addRow(new Object[]{
                             Item=Item+1, //item
                         TCodigo.getText(), //codigo
                        TDescricao.getText(), //descrição
                        TQuantidade.getText(), //quantidade
                        TUnidade.getText(), //unidade
                        TValor.getText(), //valor Unitario
                        String.valueOf(SubTotalProduto) //SubTotal
                    });
                    produto.setItem(Item);
                    }
                    
                    try{
                        produtos.add(produto);
                    //produtos.add(produto);
                    }catch(NullPointerException e){
                        JOptionPane.showMessageDialog(null, "Erro: "+e);
                    }
                    SalvaItemVendido(produto,unidade);
                    
                    //Calcula o Valor momentanio Total da compra 
                    SubTotalGeral = SubTotalGeral + SubTotalProduto;
                    
                    //Limpa as Areas para novo produto
                   TCodigo.setText("");
                   TPeso.setText("0");
                   TQuantidade.setText("1");
                   //Atualiza os valores Sub total e Total
                   BigDecimal bd = new BigDecimal(SubTotalGeral).setScale(3, RoundingMode.HALF_EVEN);
                    JTotal.setText(""+bd.doubleValue());
                   TSubTotal.setText(""+SubTotalProduto);
                }
    }
    //Quando Botão Busca é selecionado, abre-se uma janela para buscar o produto pela descrição
    public void Busca() {
        
        Info.setText("Buscando Produto...");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarProduto().setVisible(true);
            }
        });
        
        
    }   
    //salva a venda de cada item individualmente
    public void SalvaItemVendido(Produto produto, int unidade){
    
        //pega o numero da venda
        VendasDAO vdao = new VendasDAO();
        if(SubTotalGeral==0){
        NumeroVenda = vdao.PorVEndaNumero() +1;
        }
        
        //atualiza estoque
       ProdutoDAO pdao = new ProdutoDAO();
       System.out.println("Quantidade: "+produto.getQuantidade());
       pdao.attestoque(produto.getCodigo(),produto.getQuantidade());
       
        //Salva Produto Vendido
        Locale locale = new Locale("pt","BR");
        GregorianCalendar calendar = new GregorianCalendar(); 
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss",locale);
        SimpleDateFormat formatadordia = new SimpleDateFormat("dd/MM/yyyy", locale);
        VendidosDAO vendidos = new VendidosDAO();
        Vendidos produtosvendidos = new Vendidos();
       //Seta as informações necessarias 
       //Salva o item vendido com o mesmo id de venda
        produtosvendidos.setCodigo(produto.getCodigo());
        produtosvendidos.setDescrição(produto.getDescrição());
        produtosvendidos.setTipo(produto.getUnidade());
        produtosvendidos.setCaixa(""+terminal);
        produtosvendidos.setLoja(Loja);
        produtosvendidos.setQuantidade(produto.getQuantidade());
        produtosvendidos.setDataSaida(formatador.format(calendar.getTime()).toString());
        produtosvendidos.setIdVenda(NumeroVenda);
        vendidos.creat(produtosvendidos);
        
        //imprime item vendido
        imprime.itemvendido(produto, unidade);
        
        
        //prepara o ambiente para o proximo item
           
            TValor.setText("");
            TDescricao.setText("");
            TQuantidade.setText("1");
            TPeso.setText("0");
            TCodigo.requestFocus();
            
    
    }
    //Seleciona o item a cancelar venda, exclui do banco de dados e atualiza tabela e totais
    public void CancelaItem(){
    
        Info.setText("Cancelando Item...");
        TCodigo.setText("");
        
        JLabel jLabel1 = new javax.swing.JLabel();
        JTextField jTextField1 = new javax.swing.JTextField();
        JLabel jLabel2 = new javax.swing.JLabel();
        JFrame Sefra = new JFrame();
        
        InputMap inputMap = Sefra.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"PegaItem"); 
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0),"PegaItem"); 
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"Cancelar"); 
        Sefra.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        Sefra.getRootPane().getActionMap().put("PegaItem", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                
            if(Item==0){
               JOptionPane.showMessageDialog(null, "Não Existem Produtos Vendidos");
               Sefra.dispose();
            }else{
                Sefra.dispose();
                DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
                
               int unidade=0; 
               int linha = Integer.parseInt(jTextField1.getText())-1;
               int quantidade = Integer.parseInt(String.valueOf(jTable1.getModel().getValueAt(linha,3)));
               String codigo = String.valueOf(jTable1.getModel().getValueAt(linha,1));
               String descrição = String.valueOf(jTable1.getModel().getValueAt(linha,2));
               String unidadeproduto = String.valueOf(jTable1.getModel().getValueAt(linha,4));
               String preço = String.valueOf(jTable1.getModel().getValueAt(linha,5));
               float TotalProduto = Float.parseFloat(String.valueOf(jTable1.getModel().getValueAt(linha,6)));
               
               //Pega informações do produto
               Produto produto = new Produto();
               produto.setItem(Integer.parseInt(jTextField1.getText()));
               produto.setCodigo(codigo);
               produto.setQuantidade(quantidade);
               produto.setDescrição(descrição);
               produto.setUnidade(unidadeproduto);
               produto.setPreçoUn(preço);
               produto.setSubTotal(TotalProduto);
                
                
                
         //reverte estoque
       ProdutoDAO pdao = new ProdutoDAO();
       pdao.reverteestoque(produto.getCodigo(),produto.getQuantidade());
       
        //Apaga Produto Produto Vendido
        VendidosDAO vendidos = new VendidosDAO();
        Vendidos produtosvendidos = new Vendidos();
       //Seta as informações necessarias 
       //Salva o item vendido com o mesmo id de venda
        produtosvendidos.setCodigo(produto.getCodigo());
        produtosvendidos.setIdVenda(NumeroVenda);
        vendidos.exclui(produtosvendidos);
        
        //Remove produtos da lista de produtos vendidos
        for(int i = 0; i < produtos.size(); i++){
            
        Produto p = produtos.get(i);
        System.err.println("Produto da lista:"+p.getDescrição() +"-- Item:"+p.getItem());
        System.err.println("Produto excluido:"+produto.getDescrição() +"-- Item:"+produto.getItem());

        if(p.getItem() == produto.getItem()){
            // Encontrou o produto excluido
            // Remove.
            produtos.remove(p);
            // Sai do loop.
            break;
        }
        }
        
            
            //Atualiza tabela
            //Atualiza a quantidade de itens
            Item=Item-1;
            //PEgo todos os valores de cada linha e adciono outra linha igual com item atualizado
           modelo.setNumRows(0);
           int i = 0;
           float preço1= 0;
       for(Produto p: produtos){
           p.setItem(i+1);
           float SubTotal = Float.parseFloat(p.getPreçoUn())*p.getQuantidade();
        modelo.addRow(new Object[]{
                             i+1, //item
                         p.getCodigo(), //codigo
                         p.getDescrição(), //descrição
                         p.getQuantidade(), //quantidade
                         p.getUnidade(), //unidade
                         p.getPreçoUn(), //valor Unitario
                         SubTotal//SubTotal
                    });
        i++;
         }
            
            
            
            //imprime item vendido
        imprime.imprimeItemCancelado(produto, unidade);
        
        
            //prepara o ambiente para o proximo item 
           SubTotalGeral = SubTotalGeral - TotalProduto;
           TSubTotal.setText("");
           BigDecimal bd = new BigDecimal(SubTotalGeral).setScale(3, RoundingMode.HALF_EVEN);
                    JTotal.setText(""+bd.doubleValue());
            TCodigo.requestFocus();
            
            }
         
        }
        });
        
        Sefra.getRootPane().getActionMap().put("Cancelar", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                
            Sefra.dispose();
                   TCodigo.requestFocusInWindow();
    
                }
        });
        
        Sefra.setSize(new java.awt.Dimension(270, 137));
        Sefra.setAlwaysOnTop(true);
        Sefra.setResizable(false);
        Sefra.setLocationRelativeTo(null);
        Sefra.getContentPane().setBackground(new java.awt.Color(255,255,255));
        Sefra.setUndecorated(true);
        Sefra.setVisible(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(150, 0, 20));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Qual Item Deseja Excluir?");

        jLabel2.setForeground(new java.awt.Color(150, 0, 20));
        jLabel2.setText("Somente Numeros");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Sefra.getContentPane());
        Sefra.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        
          jTextField1.setText("Proximo Produto...");
    
    }
    //Cancela Venda e deixa caixa livre
    public void CancelarVenda(){
        
        if(Item==0){
               JOptionPane.showMessageDialog(null, "Não Existem Produtos Vendidos");
               Info.setText("Caixa Livre");
            }else{
                Info.setText("Cancelando Venda...Aguarde!");
                DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
                
               //Percorre toda a tabela
               for(int linha = 0; linha<jTable1.getRowCount() ; linha++){
                    float quantidade = Float.parseFloat(String.valueOf(jTable1.getModel().getValueAt(linha,3)));
                    String codigo = String.valueOf(jTable1.getModel().getValueAt(linha,1));
                    String descrição = String.valueOf(jTable1.getModel().getValueAt(linha,2));
                    String unidadeproduto = String.valueOf(jTable1.getModel().getValueAt(linha,4));
                    String preço = String.valueOf(jTable1.getModel().getValueAt(linha,5));
                    float TotalProduto = Float.parseFloat(String.valueOf(jTable1.getModel().getValueAt(linha,6)));

                    //Pega informações do produto
                    Produto produto = new Produto();
                    produto.setCodigo(codigo);
                    produto.setQuantidade(quantidade);
                    produto.setDescrição(descrição);
                    produto.setUnidade(unidadeproduto);
                    produto.setPreçoUn(preço);
                    produto.setSubTotal(TotalProduto);

                    //reverte estoque
                    ProdutoDAO pdao = new ProdutoDAO();
                    pdao.reverteestoque(produto.getCodigo(),produto.getQuantidade());

                    //Apaga Produto Vendido
                    VendidosDAO vendidos = new VendidosDAO();
                    Vendidos produtosvendidos = new Vendidos();
                    
                    //Exclui os itens vendidos com o mesmo id de venda
                     produtosvendidos.setCodigo(produto.getCodigo());
                     produtosvendidos.setIdVenda(NumeroVenda);
                     vendidos.exclui(produtosvendidos);
                     
                
                 //prepara o ambiente para o proximo item 
                SubTotalGeral = 0;
                TSubTotal.setText("");
                BigDecimal bd = new BigDecimal(SubTotalGeral).setScale(3, RoundingMode.HALF_EVEN);
                    JTotal.setText(""+bd.doubleValue());
                 TCodigo.requestFocus();
                 SubTotalGeral = 0;
                TotalCompra = 0;
                add.setAdicionais(0);
                add.setDesconto(0);
             }
                //Zera a Tabela
                modelo.setNumRows(0);
               //Libera Caixa
               Info.setText("Caixa Livre");
        }
    }
    //chama o frame tipo de pagamento
    public void BotãoPagamento(){
        
        new Thread() {
                @Override
                public void run() {
        
        Info.setText("Finalizando Venda...");
        
       // JOptionPane.showMessageDialog(null,"ValorFluxo em BotãoPagamento:"+TotalGeral);
           // JOptionPane.showMessageDialog(null,"Valor da Venda em BotãoPagamento:"+SubTotalGeral);
        fluxo.setAberturaData(Data);
        fluxo.setFluxoCaixa(String.valueOf(TotalGeral));
       
        if(TotalCompra<=SubTotalGeral){
        
        TotalCompra = SubTotalGeral;
        }
                //Chama palheta de opções pagamento
        if(envia==null){
                envia = new OpPagamento(Tela_Caixa.this);
                envia.setVisible(true);
                envia.Total(TotalCompra, fluxo, add);
                
           
        }else{
                envia.setVisible(true);
                envia.setState(Tela_Caixa.NORMAL);
                envia.Total(TotalCompra, fluxo, add);
               
        } 
                }
        }.start();
    
        
    
    }
    //chama frame que pega informações do consumidor
    public void CPFnaNota(){
        Info.setText("Finalizando Venda...");
    if(enviaCPF==null){
                enviaCPF = new CPFnaNota(Tela_Caixa.this);
                enviaCPF.setVisible(true);
        }else{
                enviaCPF.setVisible(true);
                enviaCPF.setState(Tela_Caixa.NORMAL);
        }   
    }
    //Recebe valores adicionais 
    public void Adcionais(){
    
        Info.setText("Obtendo Adicinoais da Venda!");
        TCodigo.setText("");
     if(enviaAdd==null){
                enviaAdd = new OutrosVa(Tela_Caixa.this);
                enviaAdd.setVisible(true);
        }else{
                envia.setVisible(true);
                envia.setState(Tela_Caixa.NORMAL);
        }   
    
    }
    //Fecha o caixa e armazena dados totais do fluxo de caixa
    private void armazenafluxo(){
    
        Info.setText("Armazenando Fluxo Diario...Não Desligue a maquina!");
                Fluxo c = new Fluxo();
                FluxoDAO dao = new FluxoDAO();
                
                 
        Date data = new Date();
        java.sql.Date a = new java.sql.Date(data.getTime());    

                c.setIdCaixa(terminal);   
                c.setLoja(Loja);
                c.setOperador(operador);
                c.setAberturaData(fluxo.getAberturaData());
                c.setFechamentoData(a);
                c.setFluxoCaixa(fluxo.getFluxoCaixa());
               
                dao.ArmazenarFluxo(c);
                
                Date dataHoraAtual = new Date();
                String cData = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
                String cHora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
                
                Seridor.DesligaServidor();
                dispose();
                
    }
    //finaliza a venda e salva valores
    public void SalvaValorVenda(Vendas v,float ACobrar, Fluxo fluxo, Adcionais a){
        
        Info.setText("Salvando Venda...");
        
        //JOptionPane.showMessageDialog(null,"ValorFluxo em SalvaValorVenda:"+fluxo.getFluxoCaixa());
           // JOptionPane.showMessageDialog(null,"Valor da Venda em SalvaValorVenda:"+ACobrar);
        
        int quantidade = 0;
        
         TCodigo.requestFocusInWindow();
        JTotal.setText("");
        TSubTotal.setText("");
        DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        modelo.setNumRows(0);
        
        
        //Salva Venda
        Locale locale = new Locale("pt","BR");
        GregorianCalendar calendar = new GregorianCalendar(); 
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss",locale);
        SimpleDateFormat formatadordia = new SimpleDateFormat("dd/MM/yyyy", locale);
        VendasDAO vendas = new VendasDAO();
        
        int NumeroVenda = vendas.PorVEndaNumero() +1;
       
        v.setIdVendas(NumeroVenda);
        v.setCaixa(""+terminal);
        v.setLoja(Loja);
        v.setDataVenda(formatador.format(calendar.getTime()).toString());
        v.setValor("" + ACobrar);
        v.setDiaVenda(formatadordia.format(calendar.getTime()).toString());
        vendas.ArmazenarVenda(v);
        
        //Imprime Informações de Totais
        //Foram usadas três opções pagamento
            if(v.getModoPagamento1()!=null && v.getModoPagamento2()==null && v.getModoPagamento3()==null ){
                quantidade = 1;
            }
            //Foram usadas duas opções
            if(v.getModoPagamento1()!=null && v.getModoPagamento2()!=null && v.getModoPagamento3()==null ){
                quantidade = 2;
            }
            //Foi usada 1 opção
            if(v.getModoPagamento1()!=null && v.getModoPagamento2()!=null && v.getModoPagamento3()!=null ){
                quantidade = 3;
            }
         float desconto = 0;
      
        imprime.Totais(quantidade, Item, SubTotalGeral,a,ACobrar,v);
        imprime.Consulta();
        imprime.Consumidor(consumidor);
        imprime.NFCe();
        imprime.QRCode();
        imprime.Tributos();
        
        SubTotalGeral = 0;
        TotalCompra = 0;
        add.setAdicionais(0);
        add.setDesconto(0);
        
        //Envia Valores para Nota
        PegaValores(quantidade,Item,SubTotalGeral,a,ACobrar,v,consumidor);
        
    }
    //Pega peso madado pela balança e joga no campo peso
    private void PegaPeso(){
        Info.setText("Pegando peso da balança...");
        float b = Float.parseFloat(jLabel16.getText());
        b=b/1000;
        TPeso.setText(String.valueOf(b));
    }
    //Painel de verificação com senha para fechamento do caixa
    public void FecharCaixa(){
    
        
        
        JPanel PainelAcessoCaixa = new JPanel();
        JLabel AcessarConta1 = new JLabel();
        JLabel LUsuario1 = new JLabel();
        JTextField TEmpresaCnpj = new JTextField();
        JLabel LSenha1 = new JLabel();
        JButton BAcessar1 = new JButton();
        JLabel LSenha2 = new JLabel();
        JPasswordField TSenha1 = new JPasswordField();
        JTextField TEmpresaCnpj1 = new JTextField();
        JLabel LUsuario2 = new JLabel();
        JTextField TUsuario1 = new JTextField();
        JLabel jLabel1 = new JLabel();
        JFrame Sefra = new JFrame();
        
        
        Sefra.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Sefra.setTitle("Buscar Produto");
        Sefra.setAlwaysOnTop(false);
        Sefra.setResizable(false); 
        Sefra.setSize(400, 500);
        Sefra.setLocationRelativeTo(null);
        Sefra.setVisible(true);
        Sefra.getRootPane().setDefaultButton(BAcessar1);
        Sefra.getContentPane().setBackground(new java.awt.Color(255,255,255));
        Sefra.getContentPane().setLayout(null);

        PainelAcessoCaixa.setBackground(new java.awt.Color(255, 255, 255));
        PainelAcessoCaixa.setMaximumSize(new java.awt.Dimension(271, 355));

        AcessarConta1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AcessarConta1.setForeground(new java.awt.Color(102, 102, 102));
        AcessarConta1.setText("Fechar");
        AcessarConta1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        LUsuario1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LUsuario1.setForeground(new java.awt.Color(153, 153, 153));
        LUsuario1.setText("Empresa/CNPJ");
        

        TEmpresaCnpj.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TEmpresaCnpj.setEditable(false);

        LSenha1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha1.setForeground(new java.awt.Color(153, 153, 153));
        LSenha1.setText("Caixa");

        BAcessar1.setBackground(new java.awt.Color(204, 204, 204));
        BAcessar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BAcessar1.setText("Acessar");
        BAcessar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaixaDAO dao = new CaixaDAO();
                boolean SenhaCorreta = false;
        for(Caixa p: dao.read()){

            if(Integer.parseInt(TUsuario1.getText()) == p.getIdCaixa() && TSenha1.getText().equals(p.getSenha())){
                    SenhaCorreta = true;
            }  
            if(SenhaCorreta){
                    
                Sefra.dispose();
                armazenafluxo();
                TotalGeral = 0;
                
                
                
                //dispose();
                    

            }else{

                JOptionPane.showMessageDialog(null, "Senha incorreta");}

        }
            }
        });

        
        

        LSenha2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LSenha2.setForeground(new java.awt.Color(153, 153, 153));
        LSenha2.setText("Senha");

        TEmpresaCnpj1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
       TEmpresaCnpj1.setEditable(false);

        LUsuario2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LUsuario2.setForeground(new java.awt.Color(153, 153, 153));
        LUsuario2.setText("Senha Cx Empresa");

        TUsuario1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TUsuario1.setText("1");
        TUsuario1.setEditable(false);
        
        javax.swing.GroupLayout PainelAcessoCaixaLayout = new javax.swing.GroupLayout(PainelAcessoCaixa);
        PainelAcessoCaixa.setLayout(PainelAcessoCaixaLayout);
        PainelAcessoCaixaLayout.setHorizontalGroup(
            PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelAcessoCaixaLayout.createSequentialGroup()
                
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AcessarConta1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(BAcessar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TEmpresaCnpj)
                        .addComponent(LUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelAcessoCaixaLayout.createSequentialGroup()
                            .addComponent(LUsuario2)
                            .addGap(8, 8, 8)
                            .addComponent(TEmpresaCnpj1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(TUsuario1)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        PainelAcessoCaixaLayout.setVerticalGroup(
            PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelAcessoCaixaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AcessarConta1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    )
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TEmpresaCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PainelAcessoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TEmpresaCnpj1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BAcessar1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        Sefra.getContentPane().add(PainelAcessoCaixa);
        PainelAcessoCaixa.setBounds(64, 11, 271, 365);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/shopping-1165437_1920.jpg"))); // NOI18N
        Sefra.getContentPane().add(jLabel1);
        jLabel1.setBounds(-730, -220, 1140, 700);
    
    }
    
    //Vê se conecta com a Sefaz
    public boolean interntet(){
         boolean comunicando = false;
        TRetConsStatServ retorno;
        
      try {
          //Tenta conectar a Sefaz
          // retorno = Nfe.statusServico(ConstantesUtil.NFE);
           //System.err.println("Status:" + retorno.getCStat());
       
           
            InetAddress endereco = InetAddress.getByName("www.google.com");
            int timeout = 30000;
            if (endereco.isReachable(timeout)){
                comunicando = true;
            }
              
        } catch (UnknownHostException ex) {
             Tela_Caixa.Info.setText("<html>Erro ao conectar à internet:\n"+ex.getMessage()+"\nIniciando Módulo de Contigência</html>");
                return false;
       } catch (IOException ex) {
            Tela_Caixa.Info.setText("<html>Erro ao conectar à internet:\n"+ex.getMessage()+"\nIniciando Módulo de Contigência</html>");
                return false;
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
        
        NotaNFCe nota = new NotaNFCe();
        
        //Vê se existe internet
        if(interntet()==true){
            nota.setForma("1");//normal
            setContigencia(false);
        }else{
            nota.setForma("9");//contigencia
            setContigencia(true);
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
            System.out.println("Serie:"+Serie);
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
    
            if(contigencia==true){
            EnvioNFCeContigencia.EnviaValores(caixa,IdToken,CSC,nota,venda,configuraçãoNFCE,produtos,quantidade,Item,SubTotalGeral,a,ACobrar,Id,Versão,Uf,Cnf,NatOp,Serie,NNF,DhEmi,CMun,TpEmis,CDV,TpAmb,FinNFe,CNPJ,XNome,XFant,XLgr,Nro,Bairro,XMun,UF,CEP,Fone,IE,CRT,CPF,Nome);
            Tela_Caixa.Info.setText("<html>Emitindo Nota Fiscal \n Eletrônica Em Contigencia\n...Aguarde</html>");
            }else{
            EnvioNFCe.EnviaValores(caixa,IdToken,CSC,nota,venda,configuraçãoNFCE,produtos,quantidade,Item,SubTotalGeral,a,ACobrar,Id,Versão,Uf,Cnf,NatOp,Serie,NNF,DhEmi,CMun,TpEmis,CDV,TpAmb,FinNFe,CNPJ,XNome,XFant,XLgr,Nro,Bairro,XMun,UF,CEP,Fone,IE,CRT,CPF,Nome);
            Tela_Caixa.Info.setText("<html>Emitindo Nota Fiscal \n Eletrônica...Aguarde</html>");
                }
                }
        }.start();
    }
    
   //Imprime o danfe na impressora não fiscal
    public static void Tramite(int i){
        
         
        
        if(i==1){
         Tela_Caixa.Info.setText("<html>Aguardando Retorno SEFAZ\n ...AGUARDE!!</html>");
        }
        if(i==2){
         Tela_Caixa.Info.setText("<html>Salvando Nota\n ...Aguarde</html>");
        }
        if(i==3){
         Tela_Caixa.Info.setText("<html>Imprimindo Nota\n ...Aguarde</html>");
        }
    }
        
    public static void RatearFrete(Adcionais Adicionais){}
    public static void DataHora(){
        new Thread() {
                @Override
                public void run() {
                                        while(true){
                                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
                                        Date date = new Date(); 
                                        jLabel4.setText(dateFormat.format(date));
                                        }
                                    }
                    }.start();
    }

    public static boolean isContigencia() {
        return contigencia;
    }

    public static void setContigencia(boolean contigencia) {
        Tela_Caixa.contigencia = contigencia;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        TDescricao = new javax.swing.JTextField();
        TQuantidade = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TCodigo = new javax.swing.JTextField();
        TValor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TSubTotal = new javax.swing.JTextField();
        Fechar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        JTotal = new javax.swing.JTextField();
        Pagamento = new javax.swing.JButton();
        Balança = new javax.swing.JButton();
        Cpfnota = new javax.swing.JButton();
        Adicionais = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TPeso = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        TUnidade = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Info = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Caixa");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jLabel6.setBackground(new java.awt.Color(204, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel6.setText("Qtde.");

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

        TQuantidade.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        TQuantidade.setForeground(new java.awt.Color(51, 51, 51));
        TQuantidade.setText("1");
        TQuantidade.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));
        TQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TQuantidadeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TQuantidadeFocusLost(evt);
            }
        });
        TQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TQuantidadeActionPerformed(evt);
            }
        });
        TQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TQuantidadeKeyPressed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setText("Código");

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel9.setText("Valor R$");

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

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setText("Descrição");

        TSubTotal.setEditable(false);
        TSubTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        TSubTotal.setForeground(new java.awt.Color(51, 51, 51));
        TSubTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));

        Fechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/logout.png"))); // NOI18N
        Fechar.setToolTipText("SAIR");
        Fechar.setBorderPainted(false);
        Fechar.setContentAreaFilled(false);
        Fechar.setFocusPainted(false);
        Fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FecharActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("Sub-Total");

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel8.setText("Total");

        JTotal.setEditable(false);
        JTotal.setBackground(new java.awt.Color(140, 0, 10));
        JTotal.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        JTotal.setForeground(new java.awt.Color(255, 255, 255));

        Pagamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/credit-card.png"))); // NOI18N
        Pagamento.setToolTipText("Forma de Pagamento");
        Pagamento.setBorderPainted(false);
        Pagamento.setContentAreaFilled(false);
        Pagamento.setFocusPainted(false);
        Pagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PagamentoActionPerformed(evt);
            }
        });

        Balança.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/food-scale-tool.png"))); // NOI18N
        Balança.setToolTipText("BALANÇA");
        Balança.setBorderPainted(false);
        Balança.setContentAreaFilled(false);
        Balança.setFocusPainted(false);
        Balança.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BalançaActionPerformed(evt);
            }
        });

        Cpfnota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/id-card (1).png"))); // NOI18N
        Cpfnota.setToolTipText("CPF NA NOTA");
        Cpfnota.setBorderPainted(false);
        Cpfnota.setContentAreaFilled(false);
        Cpfnota.setFocusPainted(false);
        Cpfnota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CpfnotaActionPerformed(evt);
            }
        });

        Adicionais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/more-button-of-circular-shape.png"))); // NOI18N
        Adicionais.setToolTipText("Adcionais");
        Adicionais.setBorderPainted(false);
        Adicionais.setContentAreaFilled(false);
        Adicionais.setFocusPainted(false);
        Adicionais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdicionaisActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Código", "Produto", "Qtd", "Unid.", "Valor", "Sub-Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(25);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        jPanel2.setBackground(new java.awt.Color(140, 0, 10));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("<html> Operador:  Funcionario  <br/> Caxia: 1</html>");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Data/Hora: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("15:26 01/06/18");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Status Impressora:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Porta: ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Status Balança:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("       ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(1, 1, 1))
        );

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

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/subtracting-button.png"))); // NOI18N
        jButton7.setToolTipText("Cancelar Item");
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/magnifier.png"))); // NOI18N
        jButton1.setToolTipText("Buscar Produto");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setDefaultCapable(false);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/cancelarvenda.png"))); // NOI18N
        jButton2.setToolTipText("Cancelar Venda");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setDefaultCapable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/settings-work-tool.png"))); // NOI18N
        jButton3.setToolTipText("Configurações");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setDefaultCapable(false);
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Info.setBackground(new java.awt.Color(102, 102, 102));
        Info.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        Info.setForeground(new java.awt.Color(255, 255, 255));
        Info.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Info.setText("CAIXA LIVRE");
        Info.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(TCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton1))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TValor))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                .addComponent(TQuantidade))
                            .addGap(63, 63, 63)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TPeso)))
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Fechar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Pagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Balança, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Cpfnota, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Adicionais, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(TDescricao)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(TSubTotal)
                                .addComponent(JTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))))
                    .addComponent(Info, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TCodigo)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Info, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Fechar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Pagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Balança, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Cpfnota, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Adicionais, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TDescricaoActionPerformed

    private void TQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TQuantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TQuantidadeActionPerformed

    private void TCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCodigoActionPerformed
         
    }//GEN-LAST:event_TCodigoActionPerformed

    private void TValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TValorActionPerformed

    //Botão Fechar Caixa
    private void FecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FecharActionPerformed

        FecharCaixa();

    }//GEN-LAST:event_FecharActionPerformed
    //Botão Finalizar chama tela com opções pagamento
    private void PagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PagamentoActionPerformed
      
        BotãoPagamento();
        
        
    }//GEN-LAST:event_PagamentoActionPerformed

    private void BalançaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BalançaActionPerformed
        
        // AtivaBalança();
    }//GEN-LAST:event_BalançaActionPerformed

    private void CpfnotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CpfnotaActionPerformed
       CPFnaNota();
    }//GEN-LAST:event_CpfnotaActionPerformed

    private void AdicionaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdicionaisActionPerformed
       Adcionais();
    }//GEN-LAST:event_AdicionaisActionPerformed

    private void TCodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TCodigoFocusGained
 Codigokeypressed();
  TQuantidade.setText(TQuantidade.getText().replace("q", ""));
  
 if(jTable1.getRowCount()==0){
     
        if(Info.getText().equals("Aguardando Retorno SEFAZ\n ...AGUARDE!!") || Info.getText().equals("Salvando Nota\n ...Aguarde") || Info.getText().equals("Imprimindo Nota\n ...Aguarde") ){
            
        }else{
        Info.setText("Caixa Livre");
        produtos.clear();
        }
    }else{
 Info.setText("Proximo Item...");
            }
    }//GEN-LAST:event_TCodigoFocusGained

    private void TCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCodigoKeyPressed
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){Info.setText("Finalizando Compra...");TCodigo.setText("");BotãoPagamento();}
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){Info.setText("Fechando Caixa...");TCodigo.setText("");FecharCaixa();}
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){Info.setText("Finalizando Compra...");TCodigo.setText("");BotãoPagamento();}
        if(evt.getKeyCode() == KeyEvent.VK_A){Info.setText("Obtendo Adicionais...");Adcionais();}
        if(evt.getKeyCode() == KeyEvent.VK_C){Info.setText("Finalizando Compra...");CPFnaNota();}
        if(evt.getKeyCode() == KeyEvent.VK_B){Info.setText("Buscando Produto...");Busca();}
        if(evt.getKeyCode() == KeyEvent.VK_X){Info.setText("Cancelando Item...");CancelaItem();}
       //  if(evt.getKeyCode() == KeyEvent.VK_P){TCodigo.setText("");AtivaBalança();}
       if(evt.getKeyCode() == KeyEvent.VK_DELETE){Info.setText("Cancelando Venda...");TCodigo.setText("");CancelarVenda();}
        if(evt.getKeyCode() == KeyEvent.VK_Q){
         
         TQuantidade.requestFocusInWindow();
         
         
        }
         
    }//GEN-LAST:event_TCodigoKeyPressed

    private void TCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCodigoKeyReleased
       Codigokeypressed();
    }//GEN-LAST:event_TCodigoKeyReleased

    private void TCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TCodigoFocusLost
       
    }//GEN-LAST:event_TCodigoFocusLost

    private void TPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TPesoActionPerformed
        TCodigo.requestFocus();
    }//GEN-LAST:event_TPesoActionPerformed

    private void TUnidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TUnidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TUnidadeActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       CancelaItem();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void TPesoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPesoKeyReleased
       
       TCodigo.requestFocusInWindow();
    }//GEN-LAST:event_TPesoKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      Busca();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      CancelarVenda();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        chamaconfiguração();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void TQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TQuantidadeKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_Q){
         TCodigo.requestFocusInWindow();
        
        }
    }//GEN-LAST:event_TQuantidadeKeyPressed

    private void TQuantidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TQuantidadeFocusGained
      TCodigo.setText("");
      TQuantidade.selectAll();
    }//GEN-LAST:event_TQuantidadeFocusGained

    private void TQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TQuantidadeFocusLost
       TCodigo.setText("");
    }//GEN-LAST:event_TQuantidadeFocusLost

     
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Adicionais;
    private javax.swing.JButton Balança;
    private javax.swing.JButton Cpfnota;
    private javax.swing.JButton Fechar;
    public static javax.swing.JLabel Info;
    private javax.swing.JTextField JTotal;
    private javax.swing.JButton Pagamento;
    public static javax.swing.JTextField TCodigo;
    private javax.swing.JTextField TDescricao;
    public static javax.swing.JTextField TPeso;
    private javax.swing.JTextField TQuantidade;
    private javax.swing.JTextField TSubTotal;
    private javax.swing.JTextField TUnidade;
    private javax.swing.JTextField TValor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    public static javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    public static javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    

    
}
