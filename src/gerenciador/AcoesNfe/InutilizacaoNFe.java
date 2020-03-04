/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.AcoesNfe;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.inutNFe.TProcInutNFe;
import br.inf.portalfiscal.nfe.schema_4.inutNFe.TRetInutNFe;
import static gerenciador.AcoesNfe.ConsultarNFeSefaz.iniciaConfigurações;
import gerenciador.Modulos.NotasFiscais;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
/**
 *
 * @author Marcos
 */
public class InutilizacaoNFe {
    public static String id;
    public static String motivo;
    
    public void recebechave(String id1, String motivo1) throws CertificadoException{
    
    id = id1;
    motivo = motivo1;
        try {
            inutiliza();
        } catch (JAXBException ex) {
            Logger.getLogger(InutilizacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void inutiliza() throws JAXBException {

        StringBuilder sb = new StringBuilder();
        
        try {

            // Inicia As Configurações - ver https://github.com/Samuel-Oliveira/Java_NFe/wiki/1-:-Configuracoes
            ConfiguracoesIniciaisNfe config = iniciaConfigurações();  

            //String id = "XXXXX";
            //String motivo = "XXXX";

            TRetInutNFe retorno = Nfe.inutilizacao(id, motivo, ConstantesUtil.NFE, true);
            TRetInutNFe.InfInut infRetorno = retorno.getInfInut();
            System.out.println("Status:" + infRetorno.getCStat());
            System.out.println("Motivo:" + infRetorno.getXMotivo());
            System.out.println("Data:" + infRetorno.getDhRecbto());
            sb.append("\nStatus:" + infRetorno.getCStat());
            sb.append("\nMotivo:" + infRetorno.getXMotivo());
            sb.append("\nData:" + infRetorno.getDhRecbto());

            // Criação do ProcInutNfe
            TProcInutNFe procInutNFe = new TProcInutNFe();
            procInutNFe.setInutNFe(Nfe.criaObjetoInutilizacao(id, motivo, ConstantesUtil.NFE));
            procInutNFe.setRetInutNFe(retorno);
            procInutNFe.setVersao(ConstantesUtil.VERSAO.INUTILIZACAO);

            System.out.println(XmlUtil.objectToXml(procInutNFe));
            sb.append("\n"+XmlUtil.objectToXml(procInutNFe));
            //JOptionPane.showMessageDialog(null, sb.toString());
            NotasFiscais.RetornoInutilização.setText(sb.toString());
            
        } catch (CertificadoException | NfeException e) {
            System.err.println(e.getMessage());
        }

    }
    
}

