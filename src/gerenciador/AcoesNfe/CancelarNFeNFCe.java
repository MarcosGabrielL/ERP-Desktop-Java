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
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TProcEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import static gerenciador.AcoesNfe.ConsultarNFeSefaz.iniciaConfigurações;
import gerenciador.Modulos.NotasFiscais;

import javax.xml.bind.JAXBException;

/**
 *
 * @author Marcos
 */
public class CancelarNFeNFCe {
    
    public static String chave;
            public static String protocolo;
                    public static String cnpj;
                            public static String motivo;
                            
    public void recebe(String chave1, String protocolo1, String cnpj1, String motivo1){
    
        chave = chave1;
        protocolo = protocolo1;
        cnpj = cnpj1;
        motivo = motivo1;
        
        recebe();
    
    
    }
    
    public static void recebe() {

        try {

            // Inicia As Configurações - ver https://github.com/Samuel-Oliveira/Java_NFe/wiki/1-:-Configuracoes
            ConfiguracoesIniciaisNfe config = iniciaConfigurações();  

            //String chave = "XXX";
            //String protocolo = "XXX";
            //String cnpj = "XXX";
            //String motivo = "XXX";

            String id = "ID" + ConstantesUtil.EVENTO.CANCELAR + chave + "01";

            TEnvEvento enviEvento = new TEnvEvento();
            enviEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO); //Versão do leiaute
            //Identificador de controle do Lote de envio do Evento.Número sequencial autoincremental único para identificação do Lote. 
            enviEvento.setIdLote("1");

            TEvento eventoCancela = new TEvento();
            eventoCancela.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);

            TEvento.InfEvento infoEvento = new TEvento.InfEvento(); //Grupo de informações do registro do Evento
            infoEvento.setId(id); //Identificador da TAG a ser assinada
            infoEvento.setChNFe(chave);//Chave de Acesso da NF-e vinculada ao Evento
            infoEvento.setCOrgao(String.valueOf(config.getEstado().getCodigoIbge()));//Código do órgão de recepção do Evento
            infoEvento.setTpAmb(config.getAmbiente());//Identificação do Ambiente: 1=Produção/2=Homologação
            infoEvento.setCNPJ(cnpj);//Informar o CNPJ ou o CPF do autor do Evento

            infoEvento.setDhEvento(XmlUtil.dataNfe());//Data e hora do evento no formato AAAA-MMDDThh:mm:ssTZD 
            infoEvento.setTpEvento(ConstantesUtil.EVENTO.CANCELAR);//Código do evento = 110111
            infoEvento.setNSeqEvento("XXX"); //Sequencial do evento para o mesmo tipo de evento.
            infoEvento.setVerEvento(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);

            TEvento.InfEvento.DetEvento detEvento = new TEvento.InfEvento.DetEvento();
            detEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
            detEvento.setDescEvento("Cancelamento");
            detEvento.setNProt(protocolo);
            detEvento.setXJust(motivo);
            infoEvento.setDetEvento(detEvento);
            eventoCancela.setInfEvento(infoEvento);
            enviEvento.getEvento().add(eventoCancela);

            TRetEnvEvento retorno = Nfe.cancelarNfe(enviEvento, false, ConstantesUtil.NFE);

            if (!StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo().equals(retorno.getCStat())) {
                NotasFiscais.jTextArea3.setText("Status:" + retorno.getCStat() + " - Motivo:" + retorno.getXMotivo());
                throw new NfeException("Status:" + retorno.getCStat() + " - Motivo:" + retorno.getXMotivo());
            }

            if (!StatusEnum.EVENTO_VINCULADO.getCodigo().equals(retorno.getRetEvento().get(0).getInfEvento().getCStat())) {
                NotasFiscais.jTextArea3.setText("Status:" + retorno.getRetEvento().get(0).getInfEvento().getCStat() + " - Motivo:"
                        + "" + retorno.getRetEvento().get(0).getInfEvento().getXMotivo());
                throw new NfeException("Status:" + retorno.getRetEvento().get(0).getInfEvento().getCStat() + " - Motivo:"
                        + "" + retorno.getRetEvento().get(0).getInfEvento().getXMotivo());
            }

            System.out.println("Status:" + retorno.getRetEvento().get(0).getInfEvento().getCStat());
            System.out.println("Motivo:" + retorno.getRetEvento().get(0).getInfEvento().getXMotivo());
            System.out.println("Data:" + retorno.getRetEvento().get(0).getInfEvento().getDhRegEvento());
            NotasFiscais.jTextArea3.setText("Status:" + retorno.getRetEvento().get(0).getInfEvento().getCStat()+"\n"
                    + "Motivo:" + retorno.getRetEvento().get(0).getInfEvento().getXMotivo()+"\n"
                    + "Data:" + retorno.getRetEvento().get(0).getInfEvento().getDhRegEvento());

            // Cria TProcEventoNFe
            TProcEvento procEvento = new TProcEvento();
            procEvento.setVersao("1.00");
            procEvento.setEvento(enviEvento.getEvento().get(0));
            procEvento.setRetEvento(retorno.getRetEvento().get(0));

            System.out.println(XmlUtil.objectToXml(procEvento));
            NotasFiscais.jTextArea3.insert("\n"+XmlUtil.objectToXml(procEvento), NotasFiscais.jTextArea3.getCaretPosition());
                    

        } catch (CertificadoException | NfeException | JAXBException e) {
            System.err.println(e.getMessage());
        }
    }
    
}
