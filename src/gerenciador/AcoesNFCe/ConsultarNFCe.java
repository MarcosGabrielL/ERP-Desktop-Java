/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.AcoesNFCe;
import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.consSitNFe.TConsSitNFe;
import br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import gerenciador.Modulos.NotasFiscais;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import javax.xml.bind.JAXBException;
import model.bean.Fiscal;
import model.dao.FiscalDAO;
/**
 *
 * @author Marcos
 */
public class ConsultarNFCe {
    
    public static String chave;
    public void recebechave(String chave1){
    
    chave = chave1;
    
        try {
            consulta();
        } catch (CertificadoException ex) {
            Logger.getLogger(ConsultarNFCe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void consulta() throws CertificadoException {

        try {  
      //Inicia As Configurações  
      ConfiguracoesIniciaisNfe config = iniciaConfigurações();  
  
      TConsSitNFe consSitNFe = new TConsSitNFe();  
      consSitNFe.setVersao(config.getVersaoNfe());  
      consSitNFe.setTpAmb(config.getAmbiente());  
      consSitNFe.setXServ("CONSULTAR");  
      //Substitua os X Pela Chave que deseja Consultar                 s
      //consSitNFe.setChNFe("29180713695713000169650040000202631000237946");  
      consSitNFe.setChNFe(chave);
  
      TRetConsSitNFe retorno = Nfe.consultaXml(consSitNFe.getChNFe(),ConstantesUtil.NFCE); 
      System.out.println("Status:" + retorno.getCStat());  
      System.out.println("Motivo:" + retorno.getXMotivo());  
      System.out.println("Data:" + retorno.getDhRecbto());  
      NotasFiscais.Retornonfe1.setText("Status:" + retorno.getCStat()+"\n"
              + "Motivo:" + retorno.getXMotivo()+"\n"
               + "Data:" + retorno.getDhRecbto());
     
      
      //Transforma O ProtNfe do Retorno em XML  
      String xmlProtNfe = XmlUtil.objectToXml(retorno.getProtNFe());  
      //System.out.println(xmlProtNfe); 
      NotasFiscais.Retornonfe1.insert("Retorno:"+xmlProtNfe,NotasFiscais.Retornonfe1.getCaretPosition());
              
      
  
  } catch (NfeException | JAXBException | NullPointerException e) {  
      System.out.println("Erro:" + e.getMessage()); 
      JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
  }  

    }
    
    public static ConfiguracoesIniciaisNfe iniciaConfigurações() throws NfeException, CertificadoException {
        
        ConfiguracoesIniciaisNfe config = null;
            // Certificado Arquivo, Parametros: -Caminho Certificado, - Senha
	 try{
            Certificado certificado = certifidoA1Pfx();
            System.out.println("Nome Certificado :" +certificado.getNome());
            System.out.println("Dias Restantes Certificado :" +certificado.getDiasRestantes());
            System.out.println("Validade Certificado :" +certificado.getVencimento());
            
             String path = new File(".\\resources\\Schemas").getAbsolutePath();
	config = ConfiguracoesIniciaisNfe.iniciaConfiguracoes(Estados.BA , 
                ConstantesUtil.AMBIENTE.HOMOLOGACAO,
                certificado,
                path);

	String ip = "192.168.0.1";
	int porta = 3128;
	String usuario = "";
	String senha = "";

	config.setProxy(ip, porta, usuario , senha);
        
        boolean contigencia=false;
        
        if(contigencia){
            config.setContigenciaSCAN(true);
        }
        
        }catch (CertificadoException e){
            JOptionPane.showMessageDialog(null, "Erro ao ler certificado:"+e);
        }
         
         return config;
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

}
