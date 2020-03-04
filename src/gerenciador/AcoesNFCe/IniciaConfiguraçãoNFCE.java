/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.AcoesNFCe;

import gerenciador.AcoesNfe.*;
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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Fiscal;
import model.dao.FiscalDAO;


/**
 *
 * @author Marcos
 */
public class IniciaConfiguraçãoNFCE {
    
  
   public static ConfiguracoesIniciaisNfe iniciaConfigurações() throws NfeException, CertificadoException, IOException {
        
         String path = new File(".\\resources\\Schemas").getAbsolutePath();
                Certificado certificado = certifidoA1Pfx();
         
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
