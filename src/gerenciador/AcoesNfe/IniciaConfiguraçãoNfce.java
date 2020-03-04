/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.AcoesNfe;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Fiscal;
import model.dao.FiscalDAO;

/**
 *
 * @author Marcos
 */
public class IniciaConfiguraçãoNfce {
    
   public static Certificado certificado;
   
    public IniciaConfiguraçãoNfce(){
    
        try {
            iniciaConfigurações();
        } catch (NfeException ex) {
            Logger.getLogger(IniciaConfiguraçãoNfce.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificadoException ex) {
            Logger.getLogger(IniciaConfiguraçãoNfce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static ConfiguracoesIniciaisNfe iniciaConfigurações() throws NfeException, CertificadoException {
        
           // A1Pfx a = new A1Pfx();
           
            
            // Certificado Arquivo, Parametros: -Caminho Certificado, - Senha
	 AiPfx();
         String path = new File(".\\resources\\Schemas").getAbsolutePath();
	ConfiguracoesIniciaisNfe config = ConfiguracoesIniciaisNfe.iniciaConfiguracoes(Estados.BA , 
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

	return config;
            
        
}
    
    public static void AiPfx(){
        
        Fiscal f = new Fiscal();
         FiscalDAO fdao = new FiscalDAO();
        
        try{
             certificado = certifidoA1Pfx();
            System.out.println("Nome Certificado :" +certificado.getNome());
            System.out.println("Dias Restantes Certificado :" +certificado.getDiasRestantes());
            System.out.println("Validade Certificado :" +certificado.getVencimento());
            //atualiza data vencimento
            f.setCValidade(String.valueOf(certificado.getVencimento()));
            fdao.updatevalidade(f);
            System.out.println("Token Certificado :" +certificado.getSerialToken());
             System.out.println("Protocolo Certificado :" +certificado.getSslProtocol());
            System.out.println("Tipo Certificado :" +certificado.getTipo()); 
            System.out.println("TllA3 :" +certificado.getDllA3()); 
            System.out.println("TllA3 :" +certificado.getMarcaA3()); 
            

            //PARA REGISTRAR O CERTIFICADO NA SESSAO, FAÇA SOMENTE EM PROJETOS EXTERNO
            //JAVA NFE, CTE E OUTRAS APIS MINHAS JA CONTEM ESTA INICIALIZAÇÃO
            //CertificadoService.inicializaCertificado(certificado, new FileInputStream(new File("caminhoCacert")));
            
        }catch (CertificadoException e){
            JOptionPane.showMessageDialog(null, "Erro ao ler certificado:"+e);
        }
    
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
