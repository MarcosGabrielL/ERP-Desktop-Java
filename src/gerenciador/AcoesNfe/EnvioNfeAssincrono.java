/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.AcoesNfe;

import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TRetConsReciNFe;
import javax.xml.bind.JAXBException;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.*;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.*;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS60;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;
import static gerenciador.AcoesNfe.ConsultarNFeSefaz.iniciaConfigurações;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
/**
 *
 * @author Marcos
 */
public class EnvioNfeAssincrono {
    
   public static void Emitir(){

    try {
            //Inicia As Configurações  
      ConfiguracoesIniciaisNfe config = iniciaConfigurações();  

            //TAG raiz da NF-e
            TNFe nfe = new TNFe();
            
            /*Dados da Nota Fiscal eletrônica
            infNFe   - Informações da NF-e  */
            TNFe.InfNFe infNFe = new TNFe.InfNFe();
            infNFe.setId("xxx");//Identificador da TAG a ser assinada
            infNFe.setVersao("4.00");//Versão do leiaute

            
            /*Identificação da Nota Fiscal eletrônica
            ide     - Informações de identificação da NF-e*/
            // Dados Nfe
            TNFe.InfNFe.Ide ide = new TNFe.InfNFe.Ide();
            ide.setCUF("xxx");//Código da UF do emitente do Documento Fiscal
            ide.setCNF("xxx");//Código Numérico que compõe a Chave de Acesso
            ide.setNatOp("xxx");//Descrição da Natureza da Operação
            ide.setMod("xxx");//Código do Modelo do Documento Fiscal
            ide.setSerie("xxx");//Série do Documento Fiscal
            ide.setNNF("xxx");//Número do Documento Fiscal
            ide.setDhEmi("xxx");//Data e hora de emissão do Documento
            ide.setDhSaiEnt("");//dhSaiEnt Data e hora de Saída ou da Entrada da Mercadoria
            ide.setTpNF("xxx");//Tipo de Operação
            ide.setIdDest("xxx");//Identificador de local de destino da operação
            ide.setCMunFG("xxx");//Código do Município de Ocorrência do Fato Gerador
            ide.setTpImp("xxx");//Formato de Impressão do DANFE
            ide.setTpEmis("xxx");//Tipo de Emissão da NF-e
            ide.setCDV("xxx");//Dígito Verificador da Chave de Acesso daNF-e
            ide.setTpAmb("xxx");//Identificação do Ambiente
            ide.setFinNFe("xxx");//Finalidade de emissão da NF-e
            ide.setIndFinal("xxx");//Indica operação com Consumidor final
            ide.setIndPres("xxx");//Indicador de presença do comprador no estabelecimento comercial no momento daoperação
            ide.setProcEmi("xxx");//Processo de emissão da NF-e
            ide.setVerProc("xxx");//Versão do Processo de emissão da NF-e
            infNFe.setIde(ide);

            /*Identificação do Emitente da Nota Fiscal eletrônica*/
            //emit = Identificação do emitente da NF-e
            TNFe.InfNFe.Emit emit = new TNFe.InfNFe.Emit();
            emit.setCNPJ("xxx");//CNPJ do emitente
            emit.setXNome("xxx");//Razão Social ou Nome do emitente
            emit.setXFant("xxx");//Nome fantasia
            /*enderEmit - Endereço do emitente*/
            TEnderEmi enderEmit = new TEnderEmi();
            enderEmit.setXLgr("xxx");//Logradouro
            enderEmit.setNro("xxx");//Número
            enderEmit.setXCpl("xxx");//Complemento
            enderEmit.setXBairro("xxx");//Bairro
            enderEmit.setCMun("xxx");//Código do município
            enderEmit.setXMun("xxx");//Nome do município
            enderEmit.setUF(TUfEmi.valueOf("xxx"));//Sigla da UF
            enderEmit.setCEP("xxx");//Código do CEP
            enderEmit.setCPais("xxx");//Código do País
            enderEmit.setXPais("xxx");//Nome do País
            enderEmit.setFone("xxx");//Telefone
            emit.setEnderEmit(enderEmit);
            emit.setIE("xxx");//Inscrição Estadual do Emitente
            emit.setCRT("xxx");//Código de Regime Tributário
            infNFe.setEmit(emit);

            /* Identificação do Destinatário da Nota Fiscal eletrônica*/
            //dest  - Identificação do Destinatário da NF-e
            TNFe.InfNFe.Dest dest = new TNFe.InfNFe.Dest();
            dest.setCNPJ("xxx");//CNPJ do destinatário
            dest.setXNome("xxx");//CPF do destinatário
            // enderDest    - Endereço do Destinatário da NF-e
            TEndereco enderDest = new TEndereco();
            enderDest.setXLgr("xxx");//Logradouro
            enderDest.setNro("xxx");//Número
            enderDest.setXCpl("");//Complemento
            enderDest.setXBairro("xxx");//Bairro
            enderDest.setCMun("xxx");//Código do município
            enderDest.setXMun("xxx");//Nome do município
            enderDest.setUF(TUf.valueOf("xxx"));//Sigla da UF
            enderDest.setCEP("xxx");//Código do CEP
            enderDest.setCPais("xxx");//Código do País
            enderDest.setXPais("xxx");//Nome do País
            enderDest.setFone("xxx");//Telefone
            dest.setEnderDest(enderDest);
            dest.setEmail("xxx");//Email
            dest.setIndIEDest("xxx");//Indicador da IE do Destinatário
            infNFe.setDest(dest);
            
            /*Detalhamento de Produtos e Serviços da NF-e*/
            //det   - Detalhamento de Produtos e Serviços
            TNFe.InfNFe.Det det = new TNFe.InfNFe.Det();
            det.setNItem("1"); //Número do item
            
            /*Produtos e Serviços da NF-e*/
            //prod  - Detalhamento de Produtos e Serviços
            TNFe.InfNFe.Det.Prod prod = new TNFe.InfNFe.Det.Prod();
            prod.setCProd("xxx");//Código do produto ou serviço
            prod.setCEAN("xxx"); //GTIN (Global Trade Item Number) do produto, antigo código EAN ou código de barras
            prod.setXProd("xxx"); //Descrição do produto ou serviço
            prod.setNCM("xxx");//Código NCM com 8 dígitos
            prod.setCEST("xxx");
            prod.setIndEscala("xxx");
            prod.setCFOP("xxx");//Código Fiscal de Operações e Prestações
            prod.setUCom("xxx");//Unidade Comercial
            prod.setQCom("xxx");//Quantidade Comercial
            prod.setVUnCom("xxx");//Valor Unitário de Comercialização
            prod.setVProd("xxx");//Valor Total Bruto dos Produtos ou Serviços
            prod.setCEANTrib("xxx");//GTIN (Global Trade Item Number) da unidade tributável, antigo código EAN ou código de barras 
            prod.setUTrib("xxx");//Unidade Tributável
            prod.setQTrib("xxx");//Quantidade Tributável
            prod.setVUnTrib("xxx");//Valor Unitário de tributação
            prod.setVFrete("");//Valor Total do Frete
            prod.setVSeg("");//Valor Total do Seguro
            prod.setVDesc("");//Valor do Desconto
            prod.setVOutro("");//Outras despesas acessórias
            prod.setIndTot("xxx");//Indica se valor do Item (vProd) entra no valor total da NF-e (vProd)
            det.setProd(prod);

            /*Tributos incidentes no Produto ou Serviço*/
            //imposto   - Tributos incidentes no Produto ou Serviço
            //icms  - Informações do ICMS da Operação própria e ST
            TNFe.InfNFe.Det.Imposto imposto = new TNFe.InfNFe.Det.Imposto();
            TNFe.InfNFe.Det.Imposto.ICMS icms = new TNFe.InfNFe.Det.Imposto.ICMS();
            icms.setICMS00(getICMS00());//Pega todos os dados icms 60
            icms.setICMS10(getICMS10());//Pega todos os dados icms 10
            icms.setICMS20(getICMS20());//Pega todos os dados icms 20
            icms.setICMS30(getICMS30());//Pega todos os dados icms 30
            icms.setICMS40(getICMS404150());//Pega todos os dados icms 40,41 e 50
            icms.setICMS51(getICMS51());//Pega todos os dados icms 51
            icms.setICMS60(getICMS60());//Pega todos os dados icms 60
            icms.setICMS70(getICMS70());//Pega todos os dados icms 70
            icms.setICMS90(getICMS90());//Pega todos os dados icms 90
            
            //Informações do PIS
            //pis   - Grupo PIS
            TNFe.InfNFe.Det.Imposto.PIS pis = new TNFe.InfNFe.Det.Imposto.PIS();
            pis.setPISAliq(pisAli());//Pega todos os dados do grupo tributado pela aliquota
            pis.setPISQtde(pisQtde());//Pega todos os dados do Grupo PIS tributado por Qtde
            pis.setPISOutr(PISOutr());//Pega todos os dados do Grupo PIS Outras Operações
            pis.setPISNT(PISNT());//Pega todos os dados do Grupo PIS não tributado

            /*confins   - Grupo COFINS*/
            TNFe.InfNFe.Det.Imposto.COFINS cofins = new TNFe.InfNFe.Det.Imposto.COFINS();
            cofins.setCOFINSAliq(cofinsAliq()); //pega dadps do Grupo COFINS tributado pela alíquota
            cofins.setCOFINSQtde(cofinsQtde()); //pega dadps do Grupo de COFINS tributado por Qtde
            cofins.setCOFINSNT(cofinsNt()); //pega dadps do Grupo COFINS não tributado
            cofins.setCOFINSOutr(cofinsOutr()); //pega dadps do Grupo COFINS Outras Operações

            JAXBElement<TNFe.InfNFe.Det.Imposto.ICMS> icmsElement = new JAXBElement<TNFe.InfNFe.Det.Imposto.ICMS>(new QName("ICMS"), TNFe.InfNFe.Det.Imposto.ICMS.class, icms);
            imposto.getContent().add(icmsElement);

            JAXBElement<TNFe.InfNFe.Det.Imposto.PIS> pisElement = new JAXBElement<TNFe.InfNFe.Det.Imposto.PIS>(new QName("PIS"), TNFe.InfNFe.Det.Imposto.PIS.class, pis);
            imposto.getContent().add(pisElement);

            JAXBElement<TNFe.InfNFe.Det.Imposto.COFINS> cofinsElement = new JAXBElement<TNFe.InfNFe.Det.Imposto.COFINS>(new QName("COFINS"), TNFe.InfNFe.Det.Imposto.COFINS.class, cofins);
            imposto.getContent().add(cofinsElement);

            det.setImposto(imposto);//det   - Detalhamento de Produtos e Serviços
            infNFe.getDet().add(det);//infNFe   - Informações da NF-e

            
            
            /*Total da NF-e*/
            //total - Grupo Totais da NF-e
            TNFe.InfNFe.Total total = new TNFe.InfNFe.Total();
            //ICMSTot   - Grupo Totais referentes ao ICMS
            TNFe.InfNFe.Total.ICMSTot icmstot = new TNFe.InfNFe.Total.ICMSTot();
            icmstot.setVBC("xxx");//Base de Cálculo do ICMS
            icmstot.setVICMS("xxx");//Valor Total do ICMS
            icmstot.setVICMSDeson("xxx");//Valor Total do ICMS desonerado
            icmstot.setVFCP("xxx");
            icmstot.setVFCPST("xxx");
            icmstot.setVFCPSTRet("xxx");
            icmstot.setVBCST("xxx");//Base de Cálculo do ICMS ST
            icmstot.setVST("xxx");//Valor Total do ICMS ST
            icmstot.setVProd("xxx");//Valor Total dos produtos e serviços
            icmstot.setVFrete("xxx");//Valor Total do Frete
            icmstot.setVSeg("xxx");//Valor Total do Seguro
            icmstot.setVDesc("xxx");//Valor Total do Desconto
            icmstot.setVII("xxx");//Valor Total do II
            icmstot.setVIPI("xxx");//Valor Total do IPI
            icmstot.setVIPIDevol("xxx");
            icmstot.setVPIS("xxx");//Valor do PIS
            icmstot.setVCOFINS("xxx");//Valor da COFINS
            icmstot.setVOutro("xxx");//Outras Despesas acessórias
            icmstot.setVNF("xxx");//Valor Total da NF-e
            icmstot.setVTotTrib("");//Valor aproximado total de tributos federais
            total.setICMSTot(icmstot);
            infNFe.setTotal(total);

            
            /*Informações do Transporte da NF-e*/
            //transp    - Grupo Informações do Transporte
            TNFe.InfNFe.Transp transp = new TNFe.InfNFe.Transp();
            transp.setModFrete("xxx");//Modalidade do frete
            infNFe.setTransp(transp);
            
            //infAdic   - Grupo de Informações Adicionais
            TNFe.InfNFe.InfAdic infAdic = new TNFe.InfNFe.InfAdic();
            infAdic.setInfCpl("xxx");//Informações Complementares de interesse
            infNFe.setInfAdic(infAdic);

            /*
            TNFe.InfNFe.Pag pag = new TNFe.InfNFe.Pag();
            TNFe.InfNFe.Pag.DetPag detPag = new TNFe.InfNFe.Pag.DetPag();
            detPag.setTPag("xxx");
            detPag.setVPag("xxx");
            pag.getDetPag().add(detPag);

            infNFe.setPag(pag);*/

            //MANDA TODAS INFORMAÇOES PRA NFE
            nfe.setInfNFe(infNFe);
            
            // Monta EnviNfe
            TEnviNFe enviNFe = new TEnviNFe();
            enviNFe.setVersao("4.00");
            enviNFe.setIdLote("1");/*Identificador de controle do envio do lote.
                                    Número sequencial autoincremental, de controle
                                    correspondente ao identificador único do lote
                                    enviado. A responsabilidade de gerar e controlar
                                    esse número é exclusiva do contribuinte.*/
            enviNFe.setIndSinc("1");
            enviNFe.getNFe().add(nfe);


            enviNFe = Nfe.montaNfe(enviNFe, true);

            // Envia a Nfe para a Sefaz
            TRetEnviNFe retorno = Nfe.enviarNfe(enviNFe, ConstantesUtil.NFE);

            if (!retorno.getCStat().equals(StatusEnum.LOTE_RECEBIDO.getCodigo())) {
                throw new NfeException("Status:" + retorno.getCStat() + " - Motivo:" + retorno.getXMotivo());
            }

            String recibo = retorno.getInfRec().getNRec();

            TRetConsReciNFe retornoNfe;
            while (true) {
                retornoNfe = Nfe.consultaRecibo(recibo, ConstantesUtil.NFE);
                if (retornoNfe.getCStat().equals(StatusEnum.LOTE_EM_PROCESSAMENTO.getCodigo())) {
                    System.out.println("Lote Em Processamento, vai tentar novamente apos 2 Segundo.");
                    Thread.sleep(2000);
                    continue;
                } else {
                    break;
                }
            }

            if (!retornoNfe.getCStat().equals(StatusEnum.LOTE_PROCESSADO.getCodigo())) {
                throw new NfeException("Status:" + retornoNfe.getCStat() + " - " + retornoNfe.getXMotivo());
            }
            if (!retornoNfe.getProtNFe().get(0).getInfProt().getCStat().equals(StatusEnum.AUTORIZADO.getCodigo())) {
                throw new NfeException("Status:" + retornoNfe.getProtNFe().get(0).getInfProt().getCStat() + " - " + retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo());
            }

            System.out.println("Status: " + retornoNfe.getProtNFe().get(0).getInfProt().getCStat() + " - " + retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo());
            System.out.println("Data: " + retornoNfe.getProtNFe().get(0).getInfProt().getDhRecbto());
            System.out.println("Protocolo: " + retornoNfe.getProtNFe().get(0).getInfProt().getNProt());

            System.out.println("XML Final: " + XmlUtil.criaNfeProc(enviNFe, retornoNfe.getProtNFe().get(0)));

        } catch (NfeException | JAXBException | CertificadoException | InterruptedException e) {
            System.out.println("Erro:" + e.getMessage());
        }
    
}
    
   public static TNFe.InfNFe.Det.Imposto.ICMS.ICMS00 getICMS00(){
   
       
       TNFe.InfNFe.Det.Imposto.ICMS.ICMS00 icms00 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS00();
       icms00.setOrig("xxx"); //Origem da mercadoria (0)
            icms00.setCST("xxx"); //Tributação do ICMS
            icms00.setModBC("");//Modalidade de determinação da BC do ICMS
            icms00.setVBC("");//Valor da BC do ICMS
            icms00.setPICMS("");//Alíquota do imposto
            icms00.setVICMS("");//Valor do ICMS
       
   return icms00;
   }
   public static TNFe.InfNFe.Det.Imposto.ICMS.ICMS10 getICMS10(){
   
       
       TNFe.InfNFe.Det.Imposto.ICMS.ICMS10 icms10 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS10();
            icms10.setOrig("xxx"); //Origem da mercadoria (0)
            icms10.setCST("xxx"); //Tributação do ICMS
            icms10.setModBC("");//Modalidade de determinação da BC do ICMS
            icms10.setVBC("");//Valor da BC do ICMS
            icms10.setPICMS("");//Alíquota do imposto
            icms10.setVICMS("");//Valor do ICMS
            icms10.setModBCST("");//Modalidade de determinação da BC do ICMS ST
            icms10.setPMVAST("");//Percentual da margem de valor Adicionado do ICMS ST
            icms10.setPRedBCST("");//Percentual da Redução de BC do ICMS ST
            icms10.setVBCST("");//Valor da BC do ICMS ST
            icms10.setPICMSST("");//Valor do ICMS ST
            
   return icms10;
   }
   public static TNFe.InfNFe.Det.Imposto.ICMS.ICMS20 getICMS20(){
   
       
       TNFe.InfNFe.Det.Imposto.ICMS.ICMS20 icms20 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS20();
            icms20.setOrig("xxx"); //Origem da mercadoria (0)
            icms20.setCST("xxx"); //Tributação do ICMS
            icms20.setModBC("");//Modalidade de determinação da BC do ICMS
            icms20.setPRedBC("");//Percentual da Redução de BC
            icms20.setVBC("");//Valor da BC do ICMS
            icms20.setPICMS("");//Alíquota do imposto
            icms20.setVICMS("");//Valor do ICMS
            icms20.setVICMSDeson("");//Valor do ICMS desonerado
            icms20.setMotDesICMS("");//Motivo da desoneração do ICMS
            
            
   return icms20;
   }
   public static TNFe.InfNFe.Det.Imposto.ICMS.ICMS30 getICMS30(){
   
       
       TNFe.InfNFe.Det.Imposto.ICMS.ICMS30 icms30 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS30();
            icms30.setOrig("xxx"); //Origem da mercadoria (0)
            icms30.setCST("xxx"); //Tributação do ICMS
            icms30.setModBCST("");//Modalidade de determinação da BC do ICMS
            icms30.setPMVAST("");//Percentual da margem de valor Adicionado
            icms30.setPRedBCST("");//Percentual da Redução de BC do ICMS ST
            icms30.setVBCST("");//Valor da BC do ICMS ST
            icms30.setPICMSST("");//Alíquota do imposto do ICMS ST
            icms30.setVICMSST("");//Valor do ICMS ST
            icms30.setVICMSDeson("");//Valor do ICMS desonerado
            icms30.setMotDesICMS("");//Motivo da desoneração do ICMS
            
            
   return icms30;
   }
   public static TNFe.InfNFe.Det.Imposto.ICMS.ICMS40 getICMS404150(){
   
       
       TNFe.InfNFe.Det.Imposto.ICMS.ICMS40 icms40 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS40();
            icms40.setOrig("xxx"); //Origem da mercadoria (0)
            icms40.setCST("xxx"); //Tributação do ICMS
            icms40.setVICMSDeson("");//Valor do ICMS
            icms40.setMotDesICMS("");//Motivo da desoneração do ICMS
            
            
   return icms40;
   }
   public static TNFe.InfNFe.Det.Imposto.ICMS.ICMS51 getICMS51(){
   
       
       TNFe.InfNFe.Det.Imposto.ICMS.ICMS51 icms51 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS51();
            icms51.setOrig("xxx"); //Origem da mercadoria (0)
            icms51.setCST("xxx"); //Tributação do ICMS
            icms51.setModBC("");//Modalidade de determinação da BC do
            icms51.setPRedBC("");//Percentual da Redução de BC
            icms51.setVBC("");//Valor da BC do ICMS
            icms51.setPICMS("");//Alíquota do imposto
            icms51.setVICMSOp("");//Valor do ICMS da Operação
            icms51.setPDif("");//Percentual do diferimento
            icms51.setVICMSDif("");//Valor do ICMS diferido
            icms51.setVICMS("");//Valor do ICMS
            
   return icms51;
   }
   public static TNFe.InfNFe.Det.Imposto.ICMS.ICMS60 getICMS60(){
    
        
            TNFe.InfNFe.Det.Imposto.ICMS.ICMS60 icms60 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS60();
            icms60.setOrig("xxx"); //Origem da mercadoria (0)
            icms60.setCST("xxx"); //Tributação do ICMS
            icms60.setVBCSTRet("xxx"); //Valor da BC do ICMS ST retido
            icms60.setVICMSSTRet("xxx");//Valor do ICMS ST retido
    
    
    return icms60;
    
    }
   public static TNFe.InfNFe.Det.Imposto.ICMS.ICMS70 getICMS70(){
    
        
            TNFe.InfNFe.Det.Imposto.ICMS.ICMS70 icms70 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS70();
            icms70.setOrig("xxx"); //Origem da mercadoria (0)
            icms70.setCST("xxx"); //Tributação do ICMS
            icms70.setModBC("");//Modalidade de determinação da BC do
            icms70.setPRedBC("");//Percentual da Redução de BC
            icms70.setVBC("");//Valor da BC do ICMS
            icms70.setPICMS("");//Alíquota do imposto
            icms70.setVICMS("");//Valor do ICMS
            icms70.setModBCST("");//Modalidade de determinação da BC do ICMS
            icms70.setPMVAST("");//Percentual da margem de valor Adicionado
            icms70.setVBCST("");//Valor da BC do ICMS ST
            icms70.setPICMSST("");//Alíquota do imposto do ICMS ST
            icms70.setVICMSST("");//Valor do ICMS ST
            icms70.setVICMSDeson("");//Valor do ICMS desonerado
            icms70.setMotDesICMS("");//Motivo da desoneração do ICMS
            
    
    
    return icms70;
    
    }
   public static TNFe.InfNFe.Det.Imposto.ICMS.ICMS90 getICMS90(){
    
        
            TNFe.InfNFe.Det.Imposto.ICMS.ICMS90 icms90 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS90();
            icms90.setOrig("xxx"); //Origem da mercadoria (0)
            icms90.setCST("xxx"); //Tributação do ICMS
            icms90.setModBC("");//Modalidade de determinação da BC do
            icms90.setPRedBC("");//Percentual da Redução de BC
            icms90.setVBC("");//Valor da BC do ICMS
            icms90.setPICMS("");//Alíquota do imposto
            icms90.setVICMS("");//Valor do ICMS
            icms90.setModBCST("");//Modalidade de determinação da BC do ICMS
            icms90.setPMVAST("");//Percentual da margem de valor Adicionado
            icms90.setPRedBCST("");//Percentual da Redução de BC do ICMS ST
            icms90.setVBCST("");//Valor da BC do ICMS ST
            icms90.setPICMSST("");//Alíquota do imposto do ICMS ST
            icms90.setVICMSST("");//Valor do ICMS ST
            icms90.setVICMSDeson("");//Valor do ICMS desonerado
            icms90.setMotDesICMS("");//Motivo da desoneração do ICMS
            
    
    
    return icms90;
    
    }
   
   public static TNFe.InfNFe.Det.Imposto.PIS.PISAliq pisAli(){
   
   
       TNFe.InfNFe.Det.Imposto.PIS.PISAliq pisAliq = new TNFe.InfNFe.Det.Imposto.PIS.PISAliq();
            pisAliq.setCST("xxx");//Valor da Base de Cálculo do PIS
            pisAliq.setVBC("xxx");//Valor da Base de Cálculo do PIS
            pisAliq.setPPIS("xxx");//Alíquota do PIS (em percentual)
            pisAliq.setVPIS("xxx");//Valor do PIS
   
   
   
   return pisAliq;
   }
   public static TNFe.InfNFe.Det.Imposto.PIS.PISQtde pisQtde(){
   
   
       TNFe.InfNFe.Det.Imposto.PIS.PISQtde pisqtde = new TNFe.InfNFe.Det.Imposto.PIS.PISQtde();
            pisqtde.setCST("xxx");//Valor da Base de Cálculo do PIS
            pisqtde.setQBCProd("");//Quantidade Vendida
            pisqtde.setVAliqProd("xxx");//Alíquota do PIS (em reais)
            pisqtde.setVPIS("xxx");//Valor do PIS
   
   
   
   return pisqtde;
   }
   public static TNFe.InfNFe.Det.Imposto.PIS.PISNT PISNT(){
   
   
       TNFe.InfNFe.Det.Imposto.PIS.PISNT naot = new TNFe.InfNFe.Det.Imposto.PIS.PISNT();
            naot.setCST("xxx");//Valor da Base de Cálculo do PIS
            
   
   
   return naot;
   }
   public static TNFe.InfNFe.Det.Imposto.PIS.PISOutr PISOutr(){
   
   
       TNFe.InfNFe.Det.Imposto.PIS.PISOutr pisoutr = new TNFe.InfNFe.Det.Imposto.PIS.PISOutr();
            pisoutr.setCST("xxx");//Valor da Base de Cálculo do PIS
            pisoutr.setPPIS("");//Alíquota do PIS (em percentual)
            pisoutr.setVBC("");//Valor da Base de Cálculo do PIS
            pisoutr.setQBCProd("");//Quantidade Vendida
            pisoutr.setVAliqProd("xxx");//Alíquota do PIS (em reais)
            pisoutr.setVPIS("xxx");//Valor do PIS
   
   
   
   return pisoutr;
   }
   public static TNFe.InfNFe.Det.Imposto.PISST pisSST(){
   
   
       TNFe.InfNFe.Det.Imposto.PISST pisoutr = new TNFe.InfNFe.Det.Imposto.PISST();
            
            pisoutr.setPPIS("");//Alíquota do PIS (em percentual)
            pisoutr.setVBC("");//Valor da Base de Cálculo do PIS
            pisoutr.setQBCProd("");//Quantidade Vendida
            pisoutr.setVAliqProd("xxx");//Alíquota do PIS (em reais)
            pisoutr.setVPIS("xxx");//Valor do PIS
   
   
   
   return pisoutr;
   }
   
   public static TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq cofinsAliq(){
       
        TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq cofinsAliq = new TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq();
            cofinsAliq.setCST("xxx");//Código de Situação Tributária da COFINS
            cofinsAliq.setVBC("xxx");//Valor da Base de Cálculo da COFINS
            cofinsAliq.setPCOFINS("xxx");//Alíquota da COFINS (em percentual)
            cofinsAliq.setVCOFINS("xxx");//Valor da COFINS
   
   return cofinsAliq;
   }
   public static TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde cofinsQtde(){
       
        TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde cofinsQtde = new TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde();
            cofinsQtde.setCST("xxx");//Código de Situação Tributária da COFINS
            cofinsQtde.setQBCProd("xxx");//Quantidade Vendida
            cofinsQtde.setVAliqProd("xxx");//Alíquota da COFINS (em reais)
            cofinsQtde.setVCOFINS("xxx");//Valor da COFINS
   
   return cofinsQtde;
   }
   public static TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT cofinsNt(){
       
        TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT cofinsNt = new TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT();
            cofinsNt.setCST("xxx");//Código de Situação Tributária da COFINS
            
   
   return cofinsNt;
   }
   public static TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr cofinsOutr(){
       
        TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr cofinsOutr = new TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr();
            cofinsOutr.setCST("xxx");//Código de Situação Tributária da COFINS
            cofinsOutr.setVBC("xxx");//Valor da Base de Cálculo da COFINS
            cofinsOutr.setPCOFINS("xxx");//Alíquota da COFINS (em percentual)
            cofinsOutr.setQBCProd("");//Quantidade Vendida
            cofinsOutr.setVAliqProd("");//Alíquota da COFINS (em reais)
            cofinsOutr.setVCOFINS("xxx");//Valor da COFINS
   
   return cofinsOutr;
   }
   
  
}
