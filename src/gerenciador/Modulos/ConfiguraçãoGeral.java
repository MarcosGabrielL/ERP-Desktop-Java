/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.Modulos;

import Connection.Connector;
import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import static com.sun.codemodel.JOp.lt;
import gerenciador.Caixa.IniciaImpressora;
import gerenciador.Caixa.SerialComm;
import gerenciador.Caixa.Tela_Caixa;
import gerenciador.AcoesNfe.IniciaConfiguraçãoNfce;
import gerenciador.Seridor;
import static gerenciador.Seridor.DesligaServidor;
import gerenciador.TelaGestão;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import static javax.management.Query.lt;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import model.bean.Balança;
import model.bean.Fiscal;
import model.bean.Fornecedor;
import model.bean.Municipio;
import model.dao.BalançaDAO;
import model.dao.FiscalDAO;
import model.dao.MunicipioDAO;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;



/**
 *
 * @author Marcos
 */
public class ConfiguraçãoGeral extends javax.swing.JFrame {

    SerialComm serial = new SerialComm();
    Senhas envia;
    String Senha;
    static boolean carregou=false;
    Carregando a = new Carregando();
    
    public ConfiguraçãoGeral() {
        initComponents();
        estilojpanel();
        
        getContentPane().setBackground(new java.awt.Color(51,51,51));
         
        CarregaFiscal();
        
        Fiscal.setVisible(true);
        Componentes.setVisible(false);
        Banco.setVisible(false);
        Cartão.setVisible(false);
        
        this.addWindowListener(new WindowAdapter() {
	public void windowClosing(WindowEvent evt) {
		a.dispose();
                Seridor.DesligaServidor();
        }});
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
        SwingUtilities.updateComponentTreeUI(Fiscal);
        SwingUtilities.updateComponentTreeUI(Componentes);
        SwingUtilities.updateComponentTreeUI(Banco);
        SwingUtilities.updateComponentTreeUI(Cartão);
           
        
        
        
    }
     
     //Balança
     private String portSelect() {
        if (PortasBalança.getSelectedItem() != null) {
            String itemSel = (String) PortasBalança.getSelectedItem();
            return itemSel;
        } else {
            //JOptionPane.showMessageDialog(this, "Selecione uma porta serial disponivel");
            return null;
        }
    }
     private void initPorts() {
        PortasBalança.setModel(new javax.swing.DefaultComboBoxModel(SerialComm.getListPortIdentifier()));
    }
     public void recebepeso(){
        
     float b = Float.parseFloat(recebepeso.getText());
        b=b/1000;
        jTextField10.setText(String.valueOf(b));
     }
     private void AtualizaInformaçõesBalança(){
         
         Balança b = new Balança();
         BalançaDAO bdao = new BalançaDAO();
         
        b.setId((int) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        b.setComunicação((String) jTable1.getValueAt(jTable1.getSelectedRow(), 2));
        b.setFabricante((String) jTable1.getValueAt(jTable1.getSelectedRow(), 4));
        b.setLocal((String) jTable1.getValueAt(jTable1.getSelectedRow(), 1));
        b.setModelo((String) jTable1.getValueAt(jTable1.getSelectedRow(), 5));
        b.setPorta((String) jTable1.getValueAt(jTable1.getSelectedRow(), 3));
        bdao.update(b);
         
        carregatabelabalanças();
     
     }
     private void CarregaBalança(){
     
     //carrega as informações já existentes no banco
     
     }
     private void salvarBalança(){
     
         Balança b = new Balança();
         BalançaDAO bdao = new BalançaDAO();
         
         b.setLocal(String.valueOf(jComboBox1.getSelectedItem()));
         if(jRadioButton6.isSelected()){
             b.setComunicação("Serial");
         }
         if(jRadioButton7.isSelected()){
             b.setComunicação("Paralelo");
         }
         if(jRadioButton8.isSelected()){
             b.setComunicação("USB");
         }
         b.setFabricante(String.valueOf(FabricantesBalança.getSelectedItem()));
         b.setId(bdao.idsem()+1);
         b.setModelo(String.valueOf(jComboBox6.getSelectedItem()));
         b.setPorta(String.valueOf(PortasBalança.getSelectedItem()));
         bdao.creat(b);
         
         carregatabelabalanças();
     
     }
     public void carregatabelabalanças(){
     
         DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
         modelo.setNumRows(0);
         BalançaDAO bdao = new BalançaDAO();
         
         for(Balança b: bdao.read()){
            modelo.addRow(new Object[]{
             b.getId(),
                     b.getLocal(),
                             b.getComunicação(),
                                     b.getPorta(),
                                             b.getFabricante(),
                                                     b.getModelo(),
         
            });
         }
     
     
     }
     
     
     //Fiscal
    public boolean isCNPJ(String CNPJ) {
        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
    if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
        CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
        CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
        CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
        CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
       (CNPJ.length() != 14))
       return(false);
 
    char dig13, dig14;
    int sm, i, r, num, peso;
 
        // "try" - protege o código para eventuais erros de conversao de tipo (int)
    try {
        // Calculo do 1o. Digito Verificador
      sm = 0;
      peso = 2;
      for (i=11; i>=0; i--) {
    // converte o i-ésimo caractere do CNPJ em um número:
    // por exemplo, transforma o caractere '0' no inteiro 0
    // (48 eh a posição de '0' na tabela ASCII)
        num = (int)(CNPJ.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso + 1;
        if (peso == 10)
           peso = 2;
      }
 
      r = sm % 11;
      if ((r == 0) || (r == 1))
         dig13 = '0';
      else dig13 = (char)((11-r) + 48);
 
        // Calculo do 2o. Digito Verificador
      sm = 0;
      peso = 2;
      for (i=12; i>=0; i--) {
        num = (int)(CNPJ.charAt(i)- 48);
        sm = sm + (num * peso);
        peso = peso + 1;
        if (peso == 10)
           peso = 2;
      }
 
      r = sm % 11;
      if ((r == 0) || (r == 1))
         dig14 = '0';
      else dig14 = (char)((11-r) + 48);
 
        // Verifica se os dígitos calculados conferem com os dígitos informados.
      if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
         return(true);
      else return(false);
    } catch (InputMismatchException erro) {
        return(false);
    }
  }
    public String imprimeCNPJ(String CNPJ) {
    // máscara do CNPJ: 99.999.999.9999-99
    return(CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." +
      CNPJ.substring(5, 8) + "." + CNPJ.substring(8, 12) + "-" +
      CNPJ.substring(12, 14));
  }
    public boolean isTelefone(String numeroTelefone) {
        return numeroTelefone.matches(".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}") ||
                numeroTelefone.matches(".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}");
    }
    public String imprimeTelefone(String Telefone) {
    return("(" + Telefone.substring(0, 2) + ")" + Telefone.substring(2, 7) + "-" + Telefone.substring(7, 11));
  }
    public  boolean isCep(String cep){
    return cep.matches("[0-9]{5}-[0-9]{3}");
    }
    
    private void AtualizaFiscal() throws Exception{
         Fiscal f = new Fiscal();
         FiscalDAO fdao = new FiscalDAO();
         MunicipioDAO mdao = new MunicipioDAO();
         
         //ATT 1 
         f.setIdToken(IdToken.getText());
         f.setCSC(CSC.getText());
         
         
         String cnpj = jTextCnpj.getText().replaceAll("[^0-9]", "");
         //É CNPJ Valido?
         if(isCNPJ(cnpj)){
             f.setCNPJ(imprimeCNPJ(cnpj));
         }
         //É Telefone Valido?
         if(isTelefone(jTextTelefone.getText())){
             f.setTelefone(jTextTelefone.getText());        
         }
         //É CEP Valido?
         if(isCep(JTCEP.getText())){
            f.setCEP(JTCEP.getText());
         }
         //É Iscrição EStadual?
         VerificaInscricaoEstadual n = new VerificaInscricaoEstadual();
         n.valida(jTextField3.getText(),String.valueOf(Uf.getSelectedItem()));
         f.setInscricaoEstadual(jTextField3.getText());
         
         
         //CFOP
         f.setCFOP(cfop.getText());
         f.setNCM(NCM.getText());
         f.setCST(CST.getText());
         f.setNomeFantasia(jTextFantasia.getText());
         f.setCidade(String.valueOf(jComboBox2.getSelectedItem()));
         f.setRazãoSocial(jTextRSocial.getText());
         f.setRua( JTRua.getText());
         f.setBairro(JTBairro.getText());
         f.setNumero(JTNumero.getText());
         if(String.valueOf(Uf.getSelectedItem()).equals("--")){
             JOptionPane.showInputDialog("Selecione uma Unidade Federativa - UF");
         }else{f.setUf(String.valueOf(Uf.getSelectedItem()));}
         f.setEstado(JTEstado.getText());
         f.setEmail(jTextEmail.getText());
         f.setCertificado(jTextCertificado.getText());
         f.setNumeroSérie(jTextNSerie.getText());
         if(jRadioButton1.isSelected()){
            f.setAmbiente(1);
        }if(jRadioButton2.isSelected()){
            f.setAmbiente(2);
        }if(jRadioButton3.isSelected()){
            f.setAmbiente(3);
        }if(jRadioButton4.isSelected() && jRadioButton5.isSelected()==false){
            f.setSérie(65);
        }if(jRadioButton5.isSelected() && jRadioButton4.isSelected()==false){
            f.setSérie(55);
        }if(jRadioButton5.isSelected() && jRadioButton4.isSelected()){
            f.setSérie(1);
        }
        if(String.valueOf(RegimeTributario.getSelectedItem()).equals("Simples Nacional")){
        f.setCódigoRegimeTributario("1");}
        if(String.valueOf(RegimeTributario.getSelectedItem()).equals("Regime Normal")){
        f.setCódigoRegimeTributario("3");}
        
        //Codigo da Cidade
        f.setCodigoCity(mdao.pegaCodigo(String.valueOf(jComboBox2.getSelectedItem())));
        System.err.println("Cidade:"+String.valueOf(jComboBox2.getSelectedItem()));
        
        f.setICMS(jTextICMS1.getText());
        f.setPIS(String.valueOf(CSTPIS1.getSelectedItem()).substring(0,2));
        f.setIPI(jTextIPI.getText());
        f.setCOFINS(String.valueOf(CSTCOFINS.getSelectedItem()).substring(0,2));
        
        f.setCTipo(1);
        
        f.setCSenha(jPasswordField1.getText());
        
        f.setIBPT(jTextField4.getText());
        
        //Atualizar Banco Dados
        fdao.update(f);
         
     }
    private void CarregaFiscal(){
     
     //carrega as informações já existentes no banco
     FiscalDAO fdao = new FiscalDAO();
        for(Fiscal f: fdao.read()){
            
            IdToken.setText(f.getIdToken());
            CSC.setText(f.getCSC());
            
            NCM.setText(f.getNCM());
            JTNumero.setText(f.getNumero());
            jTextField3.setText(f.getInscricaoEstadual());
            CST.setText(f.getCST());
            jTextICMS1.setText(String.valueOf(f.getICMS()));
            jTextCnpj.setText(f.getCNPJ());
            jTextTelefone.setText(f.getTelefone());
            jTextRSocial.setText(f.getRazãoSocial());
            JTRua.setText(f.getRua());
            JTBairro.setText(f.getBairro());
            //JTCidade.setText(f.getNumero());
            Uf.getModel().setSelectedItem(f.getUf());
            JTEstado.setText(f.getEstado());
            jTextEmail.setText(f.getEmail());
            jTextCertificado.setText(f.getCertificado());
            jTextNSerie.setText(f.getNumeroSérie());
                        //Ambiente
                        if(f.getAmbiente()==1){jRadioButton1.setSelected(true);}
                        if(f.getAmbiente()==2){jRadioButton2.setSelected(true);}
                        if(f.getAmbiente()==3){jRadioButton3.setSelected(true);}
                        //Serie
                        if(f.getSérie()==65){jRadioButton4.setSelected(true);}
                        if(f.getSérie()==55){jRadioButton5.setSelected(true);}
                        if(f.getSérie()==1){jRadioButton5.setSelected(true);jRadioButton4.setSelected(true);}
                        //Regime
                        if(f.getCódigoRegimeTributario().equals("1")){
                            RegimeTributario.getModel().setSelectedItem("Simples Nacional"); 
                        }
                        if(f.getCódigoRegimeTributario().equals("3")){
                            RegimeTributario.getModel().setSelectedItem("Regime Normal"); 
                        }
                     
            jTextICMS1.setText(String.valueOf(f.getICMS()));
            if(f.getPIS().equals("49")){
                CSTPIS1.setSelectedIndex(9);
            }else{
                
                if(Integer.parseInt(f.getPIS())<=0){
                  CSTPIS1.setSelectedIndex(0);  
                }else{
                    CSTPIS1.setSelectedIndex(Integer.parseInt(f.getPIS().substring(1, 2))-1);
                }
        
            }
            jTextIPI.setText(String.valueOf(f.getIPI()));
            if(f.getCOFINS().equals("49")){
                CSTCOFINS.setSelectedIndex(9);
            }else{
                if(Integer.parseInt(f.getCOFINS())<=0){
                     CSTCOFINS.setSelectedIndex(0);
                }
        CSTCOFINS.setSelectedIndex(Integer.parseInt(f.getCOFINS())-1);
            }
            jTextFantasia.setText(f.getNomeFantasia());
//            JTCidade.setText(f.getCidade());
            JTCEP.setText(f.getCEP());
            
                //CFOP
                cfop.setText(f.getCFOP());
                //Tipo
                jTextField1.setText("A1");
                jTextValidade.setText(f.getCValidade());
                jPasswordField1.setText(f.getCSenha()); 
                
                jComboBox2.getModel().setSelectedItem(f.getCidade());
                
                jTextField4.setText(f.getIBPT());
                       
        }
        
     }
    
    private void ItemUFSelecionado(){
     
     if(String.valueOf(Uf.getSelectedItem()).equals("AC")){JTEstado.setText("Acre");}
     if(String.valueOf(Uf.getSelectedItem()).equals("AL")){JTEstado.setText("Alagoas");}
     if(String.valueOf(Uf.getSelectedItem()).equals("AP")){JTEstado.setText("Amapá");}
     if(String.valueOf(Uf.getSelectedItem()).equals("AM")){JTEstado.setText("Amazonas");}
     if(String.valueOf(Uf.getSelectedItem()).equals("BA")){JTEstado.setText("Bahia");}
     if(String.valueOf(Uf.getSelectedItem()).equals("CE")){JTEstado.setText("Ceará");}
     if(String.valueOf(Uf.getSelectedItem()).equals("DF")){JTEstado.setText("Distrito Federal");}
     if(String.valueOf(Uf.getSelectedItem()).equals("ES")){JTEstado.setText("Espírito Santo");}
     if(String.valueOf(Uf.getSelectedItem()).equals("GO")){JTEstado.setText("Goiás");}
     if(String.valueOf(Uf.getSelectedItem()).equals("MA")){JTEstado.setText("Maranhão");}
     if(String.valueOf(Uf.getSelectedItem()).equals("MT")){JTEstado.setText("Mato Grosso");}
     if(String.valueOf(Uf.getSelectedItem()).equals("MS")){JTEstado.setText("Mato Grosso do Sul");}
     if(String.valueOf(Uf.getSelectedItem()).equals("MG")){JTEstado.setText("Minas Gerais");}
     if(String.valueOf(Uf.getSelectedItem()).equals("PA")){JTEstado.setText("Pará");}
     if(String.valueOf(Uf.getSelectedItem()).equals("PB")){JTEstado.setText("Paraíba");}
     if(String.valueOf(Uf.getSelectedItem()).equals("PR")){JTEstado.setText("Paraná");}
     if(String.valueOf(Uf.getSelectedItem()).equals("PE")){JTEstado.setText("Pernambuco");}
     if(String.valueOf(Uf.getSelectedItem()).equals("PI")){JTEstado.setText("Piauí");}
     if(String.valueOf(Uf.getSelectedItem()).equals("RJ")){JTEstado.setText("Rio de Janeiro");}
     if(String.valueOf(Uf.getSelectedItem()).equals("RN")){JTEstado.setText("Rio Grande do Norte");}
     if(String.valueOf(Uf.getSelectedItem()).equals("RS")){JTEstado.setText("Rio Grande do Sul");}
     if(String.valueOf(Uf.getSelectedItem()).equals("RO")){JTEstado.setText("Rondônia");}
     if(String.valueOf(Uf.getSelectedItem()).equals("RR")){JTEstado.setText("Roraima");}
     if(String.valueOf(Uf.getSelectedItem()).equals("SC")){JTEstado.setText("Santa Catarina");}
     if(String.valueOf(Uf.getSelectedItem()).equals("SP")){JTEstado.setText("São Paulo");}
     if(String.valueOf(Uf.getSelectedItem()).equals("SE")){JTEstado.setText("Sergipe");}
     if(String.valueOf(Uf.getSelectedItem()).equals("TO")){JTEstado.setText("Tocantins");}
     
     }
    public void pegaSenha(String senha){
        Senha = senha;
    
    }
    public void PopularComCidades(){
    
       // MunicipioDAO mdao = new MunicipioDAO();
        
       Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Municipio> municipios = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM NOSSOCLIENTE.\"Municipio\" Where \"Uf\" = ?");
            stmt.setString(1, ""+String.valueOf(Uf.getSelectedItem())+"");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Municipio municipio = new Municipio();
                
               // municipio.setCodigo(rs.getString("codigo"));
                jComboBox2.addItem(rs.getString("Nome"));
                //municipio.setUf(rs.getString("Uf"));
                
                
                municipios.add(municipio);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Ler produtos: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
    
    }
    
    public void LerTabelaIBPT() throws IOException, BiffException{
    
        new Thread() {
                @Override
                public void run() {
        
		/* pega o arquivo do Excel */
		Workbook workbook = null;
                    try {
                        workbook = Workbook.getWorkbook(new File(jTextField4.getText()));
                    } catch (IOException ex) {
                        Logger.getLogger(ConfiguraçãoGeral.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (BiffException ex) {
                        Logger.getLogger(ConfiguraçãoGeral.class.getName()).log(Level.SEVERE, null, ex);
                    }
		/* pega a primeira planilha dentro do arquivo XLS */
		Sheet sheet = workbook.getSheet(0);
		/* pega os valores das célular como se numa matriz */
		Cell código = null;
		Cell descrição = null;
		Cell federaln = null;
                Cell federali = null;
                Cell estadual = null;
                Cell municipal = null;
		/* pega os conteúdos das células */
		String stringa = null;
		String stringb = null;
		String stringc = null;
                String stringd = null;
                String stringe = null;
                String stringf = null;
                
                
		
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    PreparedStatement stmt1 = null;
    
                    try {
                        stmt1 = con.prepareStatement("TRUNCATE TABLE NOSSOCLIENTE.IBPT");
                        stmt1.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(ConfiguraçãoGeral.class.getName()).log(Level.SEVERE, null, ex);
                    }
   
        try {
            //truncar
            for (int i = 1; i<sheet.getRows(); i++){
                
			código = sheet.getCell(0, i);
			descrição = sheet.getCell(3, i);
			federaln = sheet.getCell(4, i);
			System.out.println("Coluna: Federaln Linha:"+i);
                        federali = sheet.getCell(5,i);
                        estadual = sheet.getCell(6,i);
                        municipal = sheet.getCell(7,i);
                        
                        stringa = código.getContents();
                        System.out.println("Código: "+stringa+"Linha:"+i);
			stringb = descrição.getContents();
			System.out.println("Descrição: "+stringb+"Linha:"+i);
			stringc = federaln.getContents();
			System.out.println("Federaln: "+stringc+"Linha:"+i);
                        stringd = federali.getContents();
			System.out.println("Federali: "+stringd+"Linha:"+i);
                        stringe = estadual.getContents();
			System.out.println("Estadual: "+stringe+"Linha:"+i);
                        stringf = municipal.getContents();
			System.out.println("Municipal: "+stringf+"Linha:"+i);
			
                        stmt = con.prepareStatement("INSERT INTO NOSSOCLIENTE.IBPT"
                    + "(codigo,descricao,federaln,federali,estadual,municipal) "
                    + "VALUES (?,?,?,?,?,?)");
            
            stmt.setString(1, stringa);
            stmt.setString(2, stringb);
            stmt.setString(3, stringc);
            stmt.setString(4, stringd);
            stmt.setString(5, stringe);
            stmt.setString(6, stringf);
            
            stmt.executeUpdate();
		
    }
            
            workbook.close();
            a.setVisible(false);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar configuração de IBPT:  "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
           }
            }.start();	
        
        
    }
    
    //barra de carregamento
   public void carregando(){
        new Thread() {
                @Override
                public void run() {
                }
            }.start();
   }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ComunicaçãoBalança = new javax.swing.ButtonGroup();
        AmbienteFiscal = new javax.swing.ButtonGroup();
        SérieWeb = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        Banco = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton25 = new javax.swing.JButton();
        Fiscal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextCnpj = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextRSocial = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        JTBairro = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        JTRua = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        JTEstado = new javax.swing.JTextField();
        Uf = new javax.swing.JComboBox<>();
        jLabel81 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextCertificado = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jTextNSerie = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jLabel83 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextTelefone = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextEmail = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        CST = new javax.swing.JTextField();
        jTextIPI = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jTextFantasia = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        JTNumero = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        JTCEP = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        RegimeTributario = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jTextValidade = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jTextICMS1 = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        NCM = new javax.swing.JTextField();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        cfop = new javax.swing.JTextField();
        jButton30 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        CSTCOFINS = new javax.swing.JComboBox<>();
        CSTPIS1 = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton31 = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        CSC = new javax.swing.JTextField();
        IdToken = new javax.swing.JTextField();
        Componentes = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jLabel88 = new javax.swing.JLabel();
        PortasBalança = new javax.swing.JComboBox<>();
        IniciaComunicaçãoBalança = new javax.swing.JButton();
        TesteBalança = new javax.swing.JButton();
        jTextField10 = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jRadioButton12 = new javax.swing.JRadioButton();
        jRadioButton13 = new javax.swing.JRadioButton();
        jRadioButton14 = new javax.swing.JRadioButton();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel93 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jTextField13 = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        FabricantesBalança = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        recebepeso = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton17 = new javax.swing.JButton();
        jLabel90 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        Cartão = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        paineis = new javax.swing.JPanel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        cielo = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        rede = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        stone = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        getnet = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        uol = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        paygo = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabelcielo = new javax.swing.JLabel();
        jLabelrede = new javax.swing.JLabel();
        jLabestone = new javax.swing.JLabel();
        jLabeluol = new javax.swing.JLabel();
        jLabegetnet = new javax.swing.JLabel();
        jLabelpaygo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuração Geral");

        jPanel1.setBackground(new java.awt.Color(150, 0, 20));

        jButton1.setBackground(new java.awt.Color(150, 0, 20));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/food-scale-tool (1).png"))); // NOI18N
        jButton1.setText("Componentes");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setDefaultCapable(false);
        jButton1.setFocusPainted(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(51, 51, 51));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/invoice.png"))); // NOI18N
        jButton4.setText("Fiscal");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setDefaultCapable(false);
        jButton4.setFocusPainted(false);
        jButton4.setOpaque(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(150, 0, 20));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/employee.png"))); // NOI18N
        jButton2.setText("Perfil");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 3));
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setDefaultCapable(false);
        jButton2.setFocusPainted(false);
        jButton2.setOpaque(true);

        jButton15.setBackground(new java.awt.Color(150, 0, 20));
        jButton15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/point-of-service.png"))); // NOI18N
        jButton15.setText("Maquina Cartão");
        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton15.setDefaultCapable(false);
        jButton15.setFocusPainted(false);
        jButton15.setOpaque(true);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(150, 0, 20));
        jButton16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/network.png"))); // NOI18N
        jButton16.setText("Banco de Dados");
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

        jLabel40.setBackground(new java.awt.Color(51, 51, 51));
        jLabel40.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        Banco.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Ip do Server (PC ADministrador)");

        jButton23.setText("Fazer Backup");

        jButton24.setText("Carregar Backup");

        jButton25.setText("Buscar");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton25))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton24)))
                .addContainerGap(531, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton25))
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton23)
                    .addComponent(jButton24))
                .addContainerGap(511, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel7);

        javax.swing.GroupLayout BancoLayout = new javax.swing.GroupLayout(Banco);
        Banco.setLayout(BancoLayout);
        BancoLayout.setHorizontalGroup(
            BancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
        );
        BancoLayout.setVerticalGroup(
            BancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );

        jScrollPane1.setBorder(null);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Rua");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 119, 28));

        jTextCnpj.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextCnpj.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jTextCnpj, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 45, 140, 28));

        jLabel2.setText("Ex: 00.000.000/0001-99");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 79, 165, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Cidade");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, 28));

        jTextRSocial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextRSocial.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jTextRSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 240, 28));

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(150, 0, 20));
        jLabel77.setText("Endereço:");
        jPanel3.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 120, 18));

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(150, 0, 20));
        jLabel78.setText("Identificação");
        jPanel3.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 120, 34));

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setText("Numero");
        jPanel3.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 119, 28));

        JTBairro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTBairro.setForeground(new java.awt.Color(102, 102, 102));
        JTBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTBairroActionPerformed(evt);
            }
        });
        jPanel3.add(JTBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, 140, 28));

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel79.setText("Bairro");
        jPanel3.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 119, 28));

        JTRua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTRua.setForeground(new java.awt.Color(102, 102, 102));
        JTRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTRuaActionPerformed(evt);
            }
        });
        jPanel3.add(JTRua, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 190, 28));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Inscrição Estadual");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 120, -1));

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel80.setText("Estado");
        jPanel3.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 119, 28));

        JTEstado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTEstado.setForeground(new java.awt.Color(102, 102, 102));
        JTEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTEstadoActionPerformed(evt);
            }
        });
        jPanel3.add(JTEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 140, 28));

        Uf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        Uf.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                UfItemStateChanged(evt);
            }
        });
        Uf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UfActionPerformed(evt);
            }
        });
        jPanel3.add(Uf, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, -1, 30));

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(150, 0, 20));
        jLabel81.setText("Certificado Digital:");
        jPanel3.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(441, 5, 120, 34));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Certificado");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 155, -1));

        jTextCertificado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextCertificado.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jTextCertificado, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 370, 28));

        jButton3.setBackground(new java.awt.Color(150, 0, 20));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/magnifieruuyty.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setDefaultCapable(false);
        jButton3.setFocusPainted(false);
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 90, -1, -1));

        jTextNSerie.setEditable(false);
        jTextNSerie.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextNSerie.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jTextNSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 140, 28));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("CSC");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 160, 40, 30));

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(150, 0, 20));
        jLabel82.setText("Web Service:");
        jPanel3.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 155, 20));

        AmbienteFiscal.add(jRadioButton1);
        jRadioButton1.setText("Produção");
        jPanel3.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Ambiente");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, 155, -1));

        AmbienteFiscal.add(jRadioButton2);
        jRadioButton2.setText("Homologação");
        jPanel3.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 210, -1, -1));

        AmbienteFiscal.add(jRadioButton3);
        jRadioButton3.setText("Não Usar");
        jPanel3.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 210, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Série");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 155, 25));

        jRadioButton4.setText("65 (NFC-e)");
        jPanel3.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, -1, -1));

        jRadioButton5.setText("55 (NF-e)");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 240, -1, -1));

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(150, 0, 20));
        jLabel83.setText("Contato:");
        jPanel3.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 120, 26));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Telefone");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 120, 28));

        jTextTelefone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextTelefone.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jTextTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, 141, 28));

        jLabel10.setText("Ex: 44570-000");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 165, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("E-mail");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 120, 28));

        jTextEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextEmail.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jTextEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 550, 250, 28));

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(150, 0, 20));
        jLabel84.setText("Tributos:");
        jPanel3.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 270, 155, 26));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Regime Tributario");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, 150, 28));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("ICMS Padrão (%)");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 430, 155, 28));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("IPI Padrão (%)");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 460, 155, 28));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("CST PIS Padrão ");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 490, 155, 28));

        CST.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CST.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(CST, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 400, 162, 28));

        jTextIPI.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextIPI.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jTextIPI, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 460, 162, 28));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("CST COFINS Padrão");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 520, 155, 28));

        jButton5.setBackground(new java.awt.Color(150, 0, 20));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/actualize-arrows-couple-in-circle.png"))); // NOI18N
        jButton5.setText("Atualizar");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setDefaultCapable(false);
        jButton5.setOpaque(true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(477, 625, 120, 40));

        jButton6.setBackground(new java.awt.Color(150, 0, 20));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/icon.png"))); // NOI18N
        jButton6.setText("Cancelar");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setDefaultCapable(false);
        jButton6.setOpaque(true);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 625, 140, 40));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Nome Fantasia");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jTextFantasia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFantasia.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jTextFantasia, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 240, 28));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Razão Social");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, 28));

        JTNumero.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTNumero.setForeground(new java.awt.Color(102, 102, 102));
        JTNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTNumeroActionPerformed(evt);
            }
        });
        jPanel3.add(JTNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, 141, 28));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("CEP");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, 28));

        JTCEP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTCEP.setForeground(new java.awt.Color(102, 102, 102));
        JTCEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTCEPActionPerformed(evt);
            }
        });
        jPanel3.add(JTCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 141, 28));

        jLabel21.setText("Ex: empresa@mail.com");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 580, 165, -1));

        jLabel22.setText("Ex: (75) 98812-3456");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 165, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(java.awt.Color.red);
        jLabel23.setText("*");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setForeground(java.awt.Color.red);
        jLabel24.setText("*");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 210, 30, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setForeground(java.awt.Color.red);
        jLabel25.setText("*");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 30, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setForeground(java.awt.Color.red);
        jLabel26.setText("*");
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 320, 30, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setForeground(java.awt.Color.red);
        jLabel27.setText("*");
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 360, 30, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setForeground(java.awt.Color.red);
        jLabel28.setText("*");
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, 30, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setForeground(java.awt.Color.red);
        jLabel29.setText("*");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 400, 30, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setForeground(java.awt.Color.red);
        jLabel30.setText("*");
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, 30, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setForeground(java.awt.Color.red);
        jLabel31.setText("*");
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 70, 30, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setForeground(java.awt.Color.red);
        jLabel32.setText("* = Campos obrigatórios");
        jPanel3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 170, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setForeground(java.awt.Color.red);
        jLabel33.setText("*");
        jPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 240, 30, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(java.awt.Color.red);
        jLabel34.setText("*");
        jPanel3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 340, 30, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("CST Padrão");
        jPanel3.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, 155, 28));

        RegimeTributario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Simples Nacional", "Regime Normal" }));
        jPanel3.add(RegimeTributario, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 310, 162, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setForeground(java.awt.Color.red);
        jLabel37.setText("*");
        jPanel3.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 310, 30, -1));

        jButton7.setBackground(new java.awt.Color(150, 0, 20));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/magnifieruuyty.png"))); // NOI18N
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setDefaultCapable(false);
        jButton7.setFocusPainted(false);
        jButton7.setOpaque(true);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 70, 30, 28));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Validade");
        jPanel3.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, 60, -1));

        jPasswordField1.setText("jPasswordField1");
        jPanel3.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, 140, 28));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("Tipo");
        jPanel3.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 50, 20));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Numero de Série");
        jPanel3.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 155, -1));

        jTextValidade.setEditable(false);
        jTextValidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextValidade.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jTextValidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, 140, 28));

        jTextField1.setEditable(false);
        jTextField1.setText("A1");
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 30, -1));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("CFOP Padrão");
        jPanel3.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, 155, 28));

        jTextICMS1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextICMS1.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jTextICMS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, 162, 28));

        jButton26.setBackground(new java.awt.Color(51, 51, 51));
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setText("Tabela CFOP");
        jButton26.setContentAreaFilled(false);
        jButton26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton26.setOpaque(true);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 340, 103, 22));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("NCM Ou CSOSN Padrão");
        jPanel3.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 370, 155, 28));

        NCM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NCM.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(NCM, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, 162, 28));

        jButton27.setBackground(new java.awt.Color(51, 51, 51));
        jButton27.setForeground(new java.awt.Color(255, 255, 255));
        jButton27.setText("Tabela ICMS");
        jButton27.setContentAreaFilled(false);
        jButton27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton27.setOpaque(true);
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 430, 103, 22));

        jButton28.setBackground(new java.awt.Color(150, 0, 20));
        jButton28.setForeground(new java.awt.Color(255, 255, 255));
        jButton28.setText("Consultar NCM");
        jButton28.setToolTipText("Consultar NCM");
        jButton28.setBorderPainted(false);
        jButton28.setContentAreaFilled(false);
        jButton28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton28.setDefaultCapable(false);
        jButton28.setFocusPainted(false);
        jButton28.setOpaque(true);
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 370, 103, 22));

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
        jPanel3.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 130, 20, 30));

        cfop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cfop.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(cfop, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 340, 162, 28));

        jButton30.setBackground(new java.awt.Color(51, 51, 51));
        jButton30.setForeground(new java.awt.Color(255, 255, 255));
        jButton30.setText("Tabela CST");
        jButton30.setContentAreaFilled(false);
        jButton30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton30.setOpaque(true);
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 400, 103, 22));

        jPanel3.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 190, 30));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("CNPJ");
        jPanel3.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 52, 119, -1));

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 140, 30));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setForeground(java.awt.Color.red);
        jLabel45.setText("*");
        jPanel3.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 30, -1));

        CSTCOFINS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01 – Operação Tributável com Alíquota Básica", "02 – Operação Tributável com Alíquota por Unidade de Medida de Produto", "03 – Operação Tributável com Alíquota por Unidade de Medida de Produto", "04 – Operação Tributável Monofásica – Revenda a Alíquota Zero", "05 – Operação Tributável por Substituição Tributária", "06 – Operação Tributável a Alíquota Zero", "07 – Operação Isenta da Contribuição", "08 – Operação sem Incidência da Contribuição", "09 – Operação com Suspensão da Contribuição", "49 – Outras Operações de Saída" }));
        jPanel3.add(CSTCOFINS, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 520, 162, 27));

        CSTPIS1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01 – Operação Tributável com Alíquota Básica", "02 – Operação Tributável com Alíquota por Unidade de Medida de Produto", "03 – Operação Tributável com Alíquota por Unidade de Medida de Produto", "04 – Operação Tributável Monofásica – Revenda a Alíquota Zero", "05 – Operação Tributável por Substituição Tributária", "06 – Operação Tributável a Alíquota Zero", "07 – Operação Isenta da Contribuição", "08 – Operação sem Incidência da Contribuição", "09 – Operação com Suspensão da Contribuição", "49 – Outras Operações de Saída" }));
        jPanel3.add(CSTPIS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 490, 162, 27));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("Carregar Tabela IBPT");
        jPanel3.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 570, 140, 30));
        jPanel3.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 570, 370, 27));

        jButton31.setBackground(new java.awt.Color(120, 0, 20));
        jButton31.setForeground(new java.awt.Color(255, 255, 255));
        jButton31.setText("Buscar");
        jButton31.setBorderPainted(false);
        jButton31.setContentAreaFilled(false);
        jButton31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton31.setDefaultCapable(false);
        jButton31.setFocusPainted(false);
        jButton31.setOpaque(true);
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 570, -1, 27));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setText("Senha");
        jPanel3.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 155, 20));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("IdToken");
        jPanel3.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 155, 20));
        jPanel3.add(CSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 160, 140, 27));
        jPanel3.add(IdToken, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 160, 140, 27));

        jScrollPane1.setViewportView(jPanel3);

        javax.swing.GroupLayout FiscalLayout = new javax.swing.GroupLayout(Fiscal);
        Fiscal.setLayout(FiscalLayout);
        FiscalLayout.setHorizontalGroup(
            FiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
        );
        FiscalLayout.setVerticalGroup(
            FiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
        );

        Componentes.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(150, 0, 20));
        jLabel85.setText("Balança");

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel87.setText("Comunicação");

        ComunicaçãoBalança.add(jRadioButton6);
        jRadioButton6.setText("Serial");

        ComunicaçãoBalança.add(jRadioButton7);
        jRadioButton7.setText("Paralelo");

        ComunicaçãoBalança.add(jRadioButton8);
        jRadioButton8.setText("USB");

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel88.setText("Porta");

        IniciaComunicaçãoBalança.setText("Iniciar Comunicação");
        IniciaComunicaçãoBalança.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciaComunicaçãoBalançaActionPerformed(evt);
            }
        });

        TesteBalança.setText("Testar");
        TesteBalança.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TesteBalançaActionPerformed(evt);
            }
        });

        jTextField10.setEditable(false);
        jTextField10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField10.setForeground(new java.awt.Color(255, 0, 0));

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(150, 0, 20));
        jLabel91.setText("Leitor Código de Barras");

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel92.setText("Comunicação");

        jRadioButton12.setText("Serial");

        jRadioButton13.setText("Paralelo");

        jRadioButton14.setText("USB");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel93.setText("Porta");

        jButton11.setText("Iniciar Comunicação");

        jButton12.setText("Testar");

        jTextField13.setEditable(false);

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel89.setText("Fabricante");

        FabricantesBalança.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TOLEDO", "FILIZOLA" }));

        jButton9.setBackground(new java.awt.Color(150, 0, 20));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/actualize-arrows-couple-in-circle.png"))); // NOI18N
        jButton9.setText("Atualizar");
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setDefaultCapable(false);
        jButton9.setOpaque(true);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(150, 0, 20));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/icon.png"))); // NOI18N
        jButton10.setText("Excluir");
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setDefaultCapable(false);
        jButton10.setOpaque(true);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(150, 0, 20));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/actualize-arrows-couple-in-circle.png"))); // NOI18N
        jButton13.setText("Atualizar");
        jButton13.setBorderPainted(false);
        jButton13.setContentAreaFilled(false);
        jButton13.setDefaultCapable(false);
        jButton13.setOpaque(true);

        jButton14.setBackground(new java.awt.Color(150, 0, 20));
        jButton14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/icon.png"))); // NOI18N
        jButton14.setText("Cancelar");
        jButton14.setBorderPainted(false);
        jButton14.setContentAreaFilled(false);
        jButton14.setDefaultCapable(false);
        jButton14.setOpaque(true);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Modelo");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        recebepeso.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        recebepeso.setForeground(new java.awt.Color(255, 255, 255));
        recebepeso.setText("jLabel18");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Local", "Comunicação", "Porta", "Fabricante", "Modelo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        jButton17.setBackground(new java.awt.Color(150, 0, 20));
        jButton17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/add-button-with-plus-symbol-in-a-black-circle.png"))); // NOI18N
        jButton17.setText("Inserir");
        jButton17.setBorderPainted(false);
        jButton17.setContentAreaFilled(false);
        jButton17.setDefaultCapable(false);
        jButton17.setOpaque(true);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel90.setText("Local");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Balcão", "Caixa", "Açougue", "Padaria" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton17))
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(IniciaComunicaçãoBalança)
                                .addGap(30, 30, 30)
                                .addComponent(TesteBalança, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton7))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PortasBalança, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(FabricantesBalança, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton8))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton10))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recebepeso)))
                .addGap(138, 138, 138)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jButton11)
                                        .addGap(30, 30, 30)
                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jRadioButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jRadioButton13))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton14))
                            .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(135, 135, 135))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14)
                        .addGap(151, 151, 151))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton6)
                            .addComponent(jRadioButton7)
                            .addComponent(jRadioButton8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PortasBalança)
                            .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FabricantesBalança, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IniciaComunicaçãoBalança, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TesteBalança, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton17)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel91)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton12)
                            .addComponent(jRadioButton13)
                            .addComponent(jRadioButton14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox5)
                            .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton14)
                            .addComponent(jButton13))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(recebepeso)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton9))
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel5);

        javax.swing.GroupLayout ComponentesLayout = new javax.swing.GroupLayout(Componentes);
        Componentes.setLayout(ComponentesLayout);
        ComponentesLayout.setHorizontalGroup(
            ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        ComponentesLayout.setVerticalGroup(
            ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        Cartão.setBackground(new java.awt.Color(51, 51, 51));
        Cartão.setOpaque(false);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/cielo.jpg"))); // NOI18N
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setDefaultCapable(false);
        jButton8.setFocusPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/rede.jpg"))); // NOI18N
        jButton18.setBorderPainted(false);
        jButton18.setContentAreaFilled(false);
        jButton18.setDefaultCapable(false);
        jButton18.setFocusPainted(false);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/stone.jpg"))); // NOI18N
        jButton19.setBorderPainted(false);
        jButton19.setContentAreaFilled(false);
        jButton19.setDefaultCapable(false);
        jButton19.setFocusPainted(false);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/uol.jpg"))); // NOI18N
        jButton20.setBorderPainted(false);
        jButton20.setContentAreaFilled(false);
        jButton20.setDefaultCapable(false);
        jButton20.setFocusPainted(false);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/getnet.jpg"))); // NOI18N
        jButton21.setBorderPainted(false);
        jButton21.setContentAreaFilled(false);
        jButton21.setDefaultCapable(false);
        jButton21.setFocusPainted(false);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/paygo.jpg"))); // NOI18N
        jButton22.setBorderPainted(false);
        jButton22.setContentAreaFilled(false);
        jButton22.setDefaultCapable(false);
        jButton22.setFocusPainted(false);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        paineis.setBackground(new java.awt.Color(255, 255, 255));

        cielo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/maquina.png"))); // NOI18N

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(150, 0, 20));
        jLabel86.setText("Configuração TEF (Transferência Eletrônica de Fundos)");

        javax.swing.GroupLayout cieloLayout = new javax.swing.GroupLayout(cielo);
        cielo.setLayout(cieloLayout);
        cieloLayout.setHorizontalGroup(
            cieloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cieloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        cieloLayout.setVerticalGroup(
            cieloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cieloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(cieloLayout.createSequentialGroup()
                .addComponent(jLabel86)
                .addGap(0, 398, Short.MAX_VALUE))
        );

        rede.setBackground(new java.awt.Color(255, 255, 255));

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/produto-pinpad.png"))); // NOI18N

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(150, 0, 20));
        jLabel94.setText("Configuração TEF (Transferência Eletrônica de Fundos)  - Pin Pad");

        javax.swing.GroupLayout redeLayout = new javax.swing.GroupLayout(rede);
        rede.setLayout(redeLayout);
        redeLayout.setHorizontalGroup(
            redeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, redeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 295, Short.MAX_VALUE)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        redeLayout.setVerticalGroup(
            redeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(redeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(redeLayout.createSequentialGroup()
                .addComponent(jLabel94)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        stone.setBackground(new java.awt.Color(255, 255, 255));

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/maquina-stone-tef.png"))); // NOI18N

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(150, 0, 20));
        jLabel95.setText("Configuração TEF (Transferência Eletrônica de Fundos)");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("CNPJ da Credenciadora de cartão de Cartão");

        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout stoneLayout = new javax.swing.GroupLayout(stone);
        stone.setLayout(stoneLayout);
        stoneLayout.setHorizontalGroup(
            stoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stoneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(stoneLayout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 445, Short.MAX_VALUE)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        stoneLayout.setVerticalGroup(
            stoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stoneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(stoneLayout.createSequentialGroup()
                .addComponent(jLabel95)
                .addGap(18, 18, 18)
                .addGroup(stoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getnet.setBackground(new java.awt.Color(255, 255, 255));

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/maquininha-single-TEF-min.png"))); // NOI18N

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(150, 0, 20));
        jLabel96.setText("Configuração TEF (Transferência Eletrônica de Fundos)");

        javax.swing.GroupLayout getnetLayout = new javax.swing.GroupLayout(getnet);
        getnet.setLayout(getnetLayout);
        getnetLayout.setHorizontalGroup(
            getnetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, getnetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        getnetLayout.setVerticalGroup(
            getnetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(getnetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(getnetLayout.createSequentialGroup()
                .addComponent(jLabel96)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        uol.setBackground(new java.awt.Color(255, 255, 255));

        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/tefuol.jpg"))); // NOI18N

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(150, 0, 20));
        jLabel97.setText("Configuração TEF (Transferência Eletrônica de Fundos)");

        javax.swing.GroupLayout uolLayout = new javax.swing.GroupLayout(uol);
        uol.setLayout(uolLayout);
        uolLayout.setHorizontalGroup(
            uolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uolLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        uolLayout.setVerticalGroup(
            uolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uolLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(uolLayout.createSequentialGroup()
                .addComponent(jLabel97)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        paygo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/TEF_pinpad.png"))); // NOI18N

        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(150, 0, 20));
        jLabel98.setText("Configuração TEF (Transferência Eletrônica de Fundos)");

        javax.swing.GroupLayout paygoLayout = new javax.swing.GroupLayout(paygo);
        paygo.setLayout(paygoLayout);
        paygoLayout.setHorizontalGroup(
            paygoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paygoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 309, Short.MAX_VALUE)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        paygoLayout.setVerticalGroup(
            paygoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paygoLayout.createSequentialGroup()
                .addComponent(jLabel98)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(paygoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLayeredPane2.setLayer(cielo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rede, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(stone, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(getnet, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(uol, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(paygo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cielo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rede, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(stone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(getnet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(uol, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(paygo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cielo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rede, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(stone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(getnet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(uol, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(paygo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout paineisLayout = new javax.swing.GroupLayout(paineis);
        paineis.setLayout(paineisLayout);
        paineisLayout.setHorizontalGroup(
            paineisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2)
        );
        paineisLayout.setVerticalGroup(
            paineisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paineisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane2))
        );

        jLabelcielo.setBackground(new java.awt.Color(255, 255, 255));
        jLabelcielo.setOpaque(true);

        jLabelrede.setBackground(new java.awt.Color(51, 51, 51));
        jLabelrede.setOpaque(true);

        jLabestone.setBackground(new java.awt.Color(51, 51, 51));
        jLabestone.setOpaque(true);

        jLabeluol.setBackground(new java.awt.Color(51, 51, 51));
        jLabeluol.setOpaque(true);

        jLabegetnet.setBackground(new java.awt.Color(51, 51, 51));
        jLabegetnet.setOpaque(true);

        jLabelpaygo.setBackground(new java.awt.Color(51, 51, 51));
        jLabelpaygo.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabelcielo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelrede, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabestone, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabeluol, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabegetnet, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelpaygo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(paineis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelcielo, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelrede, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabestone, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabeluol, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabegetnet, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelpaygo, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(paineis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CartãoLayout = new javax.swing.GroupLayout(Cartão);
        Cartão.setLayout(CartãoLayout);
        CartãoLayout.setHorizontalGroup(
            CartãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CartãoLayout.setVerticalGroup(
            CartãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLayeredPane1.setLayer(Banco, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Fiscal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Componentes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Cartão, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Fiscal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Componentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Cartão, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Banco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 612, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Fiscal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Componentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Cartão, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Banco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        CarregaFiscal();
        
        Fiscal.setVisible(true);
        Componentes.setVisible(false);
        Banco.setVisible(false);
         Cartão.setVisible(false);
        jButton4.setBackground(new java.awt.Color(51,51,51));
        jButton1.setBackground(new java.awt.Color(150,0,20));
        jButton15.setBackground(new java.awt.Color(150,0,20));
        jButton16.setBackground(new java.awt.Color(150,0,20));
        jButton4.repaint();
        jButton1.repaint();
        jButton15.repaint();
        jButton16.repaint();
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void JTEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTEstadoActionPerformed

    private void JTRuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTRuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTRuaActionPerformed

    private void JTBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTBairroActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        initPorts();
        carregatabelabalanças();
        
        Fiscal.setVisible(false);
        Componentes.setVisible(true);
        Banco.setVisible(false);
        Cartão.setVisible(false);
        jButton4.setBackground(new java.awt.Color(151,0,20));
        jButton1.setBackground(new java.awt.Color(51,51,51));
        jButton15.setBackground(new java.awt.Color(150,0,20));
        jButton16.setBackground(new java.awt.Color(150,0,20));
        jButton4.repaint();
        jButton1.repaint();
        jButton15.repaint();
        jButton16.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
       
         Fiscal.setVisible(false);
        Componentes.setVisible(false);
       Banco.setVisible(false);
        Cartão.setVisible(true);
        jButton4.setBackground(new java.awt.Color(150,0,20));
        jButton1.setBackground(new java.awt.Color(150,0,20));
        jButton15.setBackground(new java.awt.Color(51,51,51));
        jButton16.setBackground(new java.awt.Color(150,0,20));
        jButton4.repaint();
        jButton1.repaint();
        jButton15.repaint();
        jButton16.repaint();
        
        
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        
         Fiscal.setVisible(false);
        Componentes.setVisible(false);
         Banco.setVisible(true);
         Cartão.setVisible(false);
        jButton4.setBackground(new java.awt.Color(150,0,20));
        jButton1.setBackground(new java.awt.Color(150,0,20));
        jButton15.setBackground(new java.awt.Color(150,0,20));
        jButton16.setBackground(new java.awt.Color(51,51,51));
        jButton4.repaint();
        jButton1.repaint();
        jButton15.repaint();
        jButton16.repaint();
        
    }//GEN-LAST:event_jButton16ActionPerformed

    private void IniciaComunicaçãoBalançaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciaComunicaçãoBalançaActionPerformed
       serial.close();
        serial.execute(portSelect());
    }//GEN-LAST:event_IniciaComunicaçãoBalançaActionPerformed

    private void TesteBalançaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TesteBalançaActionPerformed
      recebepeso();
        try {
            serial.enviaComando(05);
        } catch (IOException ex) {
            Logger.getLogger(IniciaImpressora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TesteBalançaActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       AtualizaInformaçõesBalança();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       CarregaBalança();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            AtualizaFiscal();
            //Ler informações
            IniciaConfiguraçãoNfce ini = new IniciaConfiguraçãoNfce();
        } catch (Exception ex) {
            Logger.getLogger(ConfiguraçãoGeral.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      CarregaFiscal();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void JTNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTNumeroActionPerformed

    private void JTCEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTCEPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTCEPActionPerformed

    private void UfItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_UfItemStateChanged
      ItemUFSelecionado();
      PopularComCidades();
    }//GEN-LAST:event_UfItemStateChanged

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       JFileChooser file = new JFileChooser(); 
       file.setDialogTitle("Selecione o certificado");
         file.setFileSelectionMode(JFileChooser.FILES_ONLY);
         file.addChoosableFileFilter(new FileNameExtensionFilter(
                "Certificado Digital (*.pfx)", "pfx"));
         int i= file.showSaveDialog(null);
       if (i==1){
           jTextCertificado.setText("");
       } else {
           File arquivo = file.getSelectedFile();
          jTextCertificado.setText(arquivo.getPath());
          //PedirSenha
          if(envia==null){
                envia = new Senhas(ConfiguraçãoGeral.this);
                envia.setVisible(true);
        }else{
                envia.setVisible(true);
                envia.setState(ConfiguraçãoGeral.NORMAL);   
        }   
          
       }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        salvarBalança();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       jLabelcielo.setBackground(new java.awt.Color(255,255,255));
       jLabelrede.setBackground(new java.awt.Color(51,51,51));
       jLabestone.setBackground(new java.awt.Color(51,51,51));
       jLabegetnet.setBackground(new java.awt.Color(51,51,51));
       jLabeluol.setBackground(new java.awt.Color(51,51,51));
       jLabelpaygo.setBackground(new java.awt.Color(51,51,51));
       
       cielo.setVisible(true);
       rede.setVisible(false);
       stone.setVisible(false);
       getnet.setVisible(false);
       uol.setVisible(false);
       paygo.setVisible(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        jLabelcielo.setBackground(new java.awt.Color(51,51,51));
       jLabelrede.setBackground(new java.awt.Color(255,255,255));
       jLabestone.setBackground(new java.awt.Color(51,51,51));
       jLabegetnet.setBackground(new java.awt.Color(51,51,51));
       jLabeluol.setBackground(new java.awt.Color(51,51,51));
       jLabelpaygo.setBackground(new java.awt.Color(51,51,51));
       
       cielo.setVisible(false);
       rede.setVisible(true);
       stone.setVisible(false);
       getnet.setVisible(false);
       uol.setVisible(false);
       paygo.setVisible(false);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        jLabelcielo.setBackground(new java.awt.Color(51,51,51));
       jLabelrede.setBackground(new java.awt.Color(51,51,51));
       jLabestone.setBackground(new java.awt.Color(255,255,255));
       jLabegetnet.setBackground(new java.awt.Color(51,51,51));
       jLabeluol.setBackground(new java.awt.Color(51,51,51));
       jLabelpaygo.setBackground(new java.awt.Color(51,51,51));
       
       cielo.setVisible(false);
       rede.setVisible(false);
       stone.setVisible(true);
       getnet.setVisible(false);
       uol.setVisible(false);
       paygo.setVisible(false);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
       jLabelcielo.setBackground(new java.awt.Color(51,51,51));
       jLabelrede.setBackground(new java.awt.Color(51,51,51));
       jLabestone.setBackground(new java.awt.Color(51,51,51));
       jLabegetnet.setBackground(new java.awt.Color(51,51,51));
       jLabeluol.setBackground(new java.awt.Color(255,255,255));
       jLabelpaygo.setBackground(new java.awt.Color(51,51,51));
       
       cielo.setVisible(false);
       rede.setVisible(false);
       stone.setVisible(false);
       getnet.setVisible(false);
       uol.setVisible(true);
       paygo.setVisible(false);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
         jLabelcielo.setBackground(new java.awt.Color(51,51,51));
       jLabelrede.setBackground(new java.awt.Color(51,51,51));
       jLabestone.setBackground(new java.awt.Color(51,51,51));
       jLabegetnet.setBackground(new java.awt.Color(255,255,255));
       jLabeluol.setBackground(new java.awt.Color(51,51,51));
       jLabelpaygo.setBackground(new java.awt.Color(51,51,51));
       
       cielo.setVisible(false);
       rede.setVisible(false);
       stone.setVisible(false);
       getnet.setVisible(true);
       uol.setVisible(false);
       paygo.setVisible(false);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
         jLabelcielo.setBackground(new java.awt.Color(51,51,51));
       jLabelrede.setBackground(new java.awt.Color(51,51,51));
       jLabestone.setBackground(new java.awt.Color(51,51,51));
       jLabegetnet.setBackground(new java.awt.Color(51,51,51));
       jLabeluol.setBackground(new java.awt.Color(51,51,51));
       jLabelpaygo.setBackground(new java.awt.Color(255,255,255));
       
       cielo.setVisible(false);
       rede.setVisible(false);
       stone.setVisible(false);
       getnet.setVisible(false);
       uol.setVisible(false);
       paygo.setVisible(true);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CFOP().setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ICMS().setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CST().setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed

        
        if (jPasswordField1.getEchoChar()!=(char)0){
                jPasswordField1.setEchoChar((char)0);
           }else{
                jPasswordField1.setEchoChar('\u2022');
           }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CST().setVisible(true);
            }
       });
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        
        JFileChooser file = new JFileChooser(); 
       file.setDialogTitle("Selecione a Tabela IBPT");
         file.setFileSelectionMode(JFileChooser.FILES_ONLY);
         file.addChoosableFileFilter(new FileNameExtensionFilter(
                "Tabela IBPT (*.pfx)", "pfx"));
         int i= file.showSaveDialog(null);
       if (i==1){
           jTextField4.setText("");
       } else {
           File arquivo = file.getSelectedFile();
          jTextField4.setText(arquivo.getPath());
          
          
                
                a.setVisible(true);
            
            
          
        try {
            LerTabelaIBPT();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar tabela: "+ex);
        } catch (BiffException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar tabela: "+ex);
        }
        
       }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void UfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UfActionPerformed

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
            java.util.logging.Logger.getLogger(ConfiguraçãoGeral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfiguraçãoGeral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfiguraçãoGeral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfiguraçãoGeral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfiguraçãoGeral().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup AmbienteFiscal;
    private javax.swing.JPanel Banco;
    private javax.swing.JTextField CSC;
    private javax.swing.JTextField CST;
    private javax.swing.JComboBox<String> CSTCOFINS;
    private javax.swing.JComboBox<String> CSTPIS1;
    private javax.swing.JPanel Cartão;
    private javax.swing.JPanel Componentes;
    private javax.swing.ButtonGroup ComunicaçãoBalança;
    private javax.swing.JComboBox<String> FabricantesBalança;
    private javax.swing.JPanel Fiscal;
    private javax.swing.JTextField IdToken;
    private javax.swing.JButton IniciaComunicaçãoBalança;
    private javax.swing.JTextField JTBairro;
    private javax.swing.JTextField JTCEP;
    private javax.swing.JTextField JTEstado;
    private javax.swing.JTextField JTNumero;
    private javax.swing.JTextField JTRua;
    private javax.swing.JTextField NCM;
    private javax.swing.JComboBox<String> PortasBalança;
    private javax.swing.JComboBox<String> RegimeTributario;
    private javax.swing.ButtonGroup SérieWeb;
    private javax.swing.JButton TesteBalança;
    private javax.swing.JComboBox<String> Uf;
    private javax.swing.JTextField cfop;
    private javax.swing.JPanel cielo;
    private javax.swing.JPanel getnet;
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabegetnet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabelcielo;
    private javax.swing.JLabel jLabelpaygo;
    private javax.swing.JLabel jLabelrede;
    private javax.swing.JLabel jLabeluol;
    private javax.swing.JLabel jLabestone;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton13;
    private javax.swing.JRadioButton jRadioButton14;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextCertificado;
    private javax.swing.JTextField jTextCnpj;
    private javax.swing.JTextField jTextEmail;
    private javax.swing.JTextField jTextFantasia;
    private javax.swing.JTextField jTextField1;
    public static javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextICMS1;
    private javax.swing.JTextField jTextIPI;
    private javax.swing.JTextField jTextNSerie;
    private javax.swing.JTextField jTextRSocial;
    private javax.swing.JTextField jTextTelefone;
    private javax.swing.JTextField jTextValidade;
    private javax.swing.JPanel paineis;
    private javax.swing.JPanel paygo;
    public static javax.swing.JLabel recebepeso;
    private javax.swing.JPanel rede;
    private javax.swing.JPanel stone;
    private javax.swing.JPanel uol;
    // End of variables declaration//GEN-END:variables
}
