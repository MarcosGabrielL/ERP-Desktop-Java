/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.AcoesNfe;

import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.envEpec.*;
import static gerenciador.AcoesNfe.ConsultarNFeSefaz.iniciaConfigurações;

import javax.xml.bind.JAXBException;
import org.ini4j.Config;
/**
 *
 * @author Marcos
 */
public class EnvioEPec {
    
    public static void main(String[] args) {

        try {

            // Inicia As Configurações
            ConfiguracoesIniciaisNfe config = iniciaConfigurações(); 

            String chave = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
            String cnpj = "99999999999998";

            String id = "ID" + ConstantesUtil.EVENTO.EPEC + chave + "01";

            TEnvEvento enviEvento = new TEnvEvento();
            enviEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_EPEC);
            enviEvento.setIdLote("1");

            TEvento eventoEpec = new TEvento();
            eventoEpec.setVersao(ConstantesUtil.VERSAO.EVENTO_EPEC);

            TEvento.InfEvento infoEvento = new TEvento.InfEvento();
            infoEvento.setId(id);
            infoEvento.setCOrgao(String.valueOf(config.getEstado().getCodigoIbge()));
            infoEvento.setTpAmb(config.getAmbiente());
            infoEvento.setCNPJ(cnpj);
            infoEvento.setChNFe(chave);
            infoEvento.setDhEvento(XmlUtil.dataNfe());
            infoEvento.setTpEvento(ConstantesUtil.EVENTO.EPEC);
            infoEvento.setNSeqEvento("1");
            infoEvento.setVerEvento(ConstantesUtil.VERSAO.EVENTO_EPEC);

            TEvento.InfEvento.DetEvento detEvento = new TEvento.InfEvento.DetEvento();
            detEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_EPEC);
            detEvento.setDescEvento("EPEC");
            detEvento.setCOrgaoAutor(String.valueOf(config.getEstado().getCodigoIbge()));
            detEvento.setTpAutor("1");
            detEvento.setVerAplic("1.0.0");
            detEvento.setDhEmi(XmlUtil.dataNfe());
            detEvento.setTpNF("1");
            detEvento.setIE("XXXXXXX");

            TEvento.InfEvento.DetEvento.Dest dest = new TEvento.InfEvento.DetEvento.Dest();
            dest.setUF(TUf.GO);
            dest.setCNPJ("99999999999999");
            dest.setIE("XXXXXXXXXXXX");
            dest.setVNF("100.00");
            dest.setVICMS("0.00");
            dest.setVST("0.00");
            detEvento.setDest(dest);
            
            

            infoEvento.setDetEvento(detEvento);
            eventoEpec.setInfEvento(infoEvento);
            enviEvento.getEvento().add(eventoEpec);

            TRetEnvEvento retorno = Nfe.enviarEpec(enviEvento, false, ConstantesUtil.NFE);

            if (!StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo().equals(retorno.getCStat())) {
                throw new NfeException("Status:" + retorno.getCStat() + " - Motivo:" + retorno.getXMotivo());

            }

            if (!StatusEnum.EVENTO_REGISTRADO_NAO_VINCULADO.getCodigo().equals(retorno.getRetEvento().get(0).getInfEvento().getCStat())) {
                throw new NfeException("Status:" + retorno.getRetEvento().get(0).getInfEvento().getCStat() + " - Motivo:" + retorno.getRetEvento().get(0).getInfEvento().getXMotivo());
            }

            System.out.println("Status:" + retorno.getRetEvento().get(0).getInfEvento().getCStat());
            System.out.println("Motivo:" + retorno.getRetEvento().get(0).getInfEvento().getXMotivo());
            System.out.println("Data:" + retorno.getRetEvento().get(0).getInfEvento().getDhRegEvento());

            // Cria TProcEventoNFe
            TProcEvento procEvento = new TProcEvento();
            procEvento.setVersao("1.00");
            procEvento.setEvento(enviEvento.getEvento().get(0));
            procEvento.setRetEvento(retorno.getRetEvento().get(0));

            System.out.println(XmlUtil.objectToXml(procEvento));

        } catch (CertificadoException | NfeException | JAXBException e) {
            System.err.println(e.getMessage());
        }

    }
    
    
}
