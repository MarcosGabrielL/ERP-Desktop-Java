/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Relatorios;

import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrinterName;
import javax.servlet.ServletContext;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.bean.Caixa;
import model.bean.PagImpress;
import model.bean.ProdImpress;
import model.bean.Relatorio;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author Marcos
 */
public class DanfeNFCe_Contigencia {
    
    private static String troco;
    private static String count;
    private static String nome;
    private static String NomeImpressora;
    private static HashMap map = new HashMap();
    
    public static void recebedados(String Impressora, String vtroco, String qitem, String Qr, String urlxml,String Nome){
    
        NomeImpressora = Impressora;
        nome = Nome;
        
        String QrCode = null;
        String XML = null;
        if(Qr==null){
         QrCode = "http://nfe.sefaz.ba.gov.br/NFCE/CA/Modulos/Geral/NFCEC_consulta_chave_acesso.aspx";
        }else{
        
            if(Qr.equals("")){
                 QrCode = "http://nfe.sefaz.ba.gov.br/NFCE/CA/Modulos/Geral/NFCEC_consulta_chave_acesso.aspx";
            }else{
                 QrCode = Qr;
            }
        }
        
        if(urlxml==null){
            XML = "C:\\Users\\Marcos\\Desktop\\Notas Fiscais\\XML_Conti\\a.xml";
        }else{
            if(urlxml.equals("")){
                XML = "C:\\Users\\Marcos\\Desktop\\Notas Fiscais\\XML_Conti\\a.xml";
            }else{
                XML = urlxml;
            }
        }
        
      if(vtroco == null){
          setTroco("0");
      }else{
          if(vtroco.equals("")){
              setTroco("0");
          }else{
              setTroco(vtroco);
          }
      }
      
      if(qitem == null){
          setCount("1");
      }else{
          if(qitem.equals("")){
              setCount("1");
          }else{
              setCount(qitem);
          }
      }
      
      XML2Relatorio(XML,QrCode);
    }
     
   //procura impressora se não achar imprime na padrão
    private static void ImpressoraSelecionada(String printerName,JasperPrint jasperPrint){     
        try {  
                 
            //Lista todas impressoras até encontrar a enviada por parametro  
            PrintService serviceFound = null;  
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null,null);  
            for(PrintService service:services){  
                            if(service.getName().trim().equals(printerName.trim()))  
                     serviceFound = service;  
                                  
            }  
              
            if (serviceFound == null){  
                    // se nao achar impressora configurada imprima na impressora padrao  
                        JasperPrintManager.printReport(jasperPrint, false);  
                          
                        throw new Exception("Impressora não encontrada! Impressão enviada para impressora padrao");  
                        }  
                                             
                        
                          
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
    
    public static void ImprimirSelecionada(String printerName,JasperPrint jasperPrint) throws JRException{
    
    //Pega os nomes de todas as impressoras
    PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);

    //Pega impressora selecionada
    String selectedPrinter = printerName; 

    System.out.println("Number of print services: " + services.length);
    PrintService selectedService = null;

    //Definir as configurações de impressão
    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
    printRequestAttributeSet.add(MediaSizeName.ISO_A4);
    printRequestAttributeSet.add(new Copies(1));
    if (jasperPrint.getOrientationValue() == net.sf.jasperreports.engine.type.OrientationEnum.LANDSCAPE) { 
      printRequestAttributeSet.add(OrientationRequested.LANDSCAPE); 
    } else { 
      printRequestAttributeSet.add(OrientationRequested.PORTRAIT); 
    } 
    PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
    printServiceAttributeSet.add(new PrinterName(selectedPrinter, null));

    JRPrintServiceExporter exporter = new JRPrintServiceExporter();
    SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
    configuration.setPrintRequestAttributeSet(printRequestAttributeSet);
    configuration.setPrintServiceAttributeSet(printServiceAttributeSet);
    configuration.setDisplayPageDialog(false);
    configuration.setDisplayPrintDialog(false);

    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
    exporter.setConfiguration(configuration);

    //Itere através da impressora disponível, e uma vez combinado com o nosso <selectedPrinter>, vá em frente e imprima!
    if(services != null && services.length != 0){
      for(PrintService service : services){
          String existingPrinter = service.getName();
          if(existingPrinter.equals(selectedPrinter))
          {
              selectedService = service;
              break;
          }
      }
    }
    if(selectedService != null)
    {   
      try{
          //Lets the printer do its magic!
          exporter.exportReport();
      }catch(Exception e){
    System.out.println("JasperReport Error: "+e.getMessage());
      }
    }else{
        ImpressoraSelecionada("c",jasperPrint);
      System.out.println("JasperReport Error: Printer not found!");
    }}
    
    // Cria o Danfe com os dados do xml
    public static void gerarDanfe(String url, List<ProdImpress> detalhes, List<PagImpress> pagamentos, JRDataSource jr){
        try {
            
            JRBeanCollectionDataSource itens = new JRBeanCollectionDataSource(detalhes);
            JRBeanCollectionDataSource ModoPagamentos = new JRBeanCollectionDataSource(pagamentos);
            
            String output = "C:\\Users\\Marcos\\Desktop\\Notas Fiscais\\PDF_Conti\\"+nome+".pdf"; 
            String jrurl = new File(".\\resources\\DanfeNFCeContigencia.jrxml").getAbsolutePath();
            String jrurl2Via = new File(".\\resources\\DanfeNFCeContigencia2Via.jrxml").getAbsolutePath();
           
           map.put("UrlConsulta", "http://www.sefaz.ba.gov.br/nfce/consulta"); 
           map.put("SubDataSourceDetalhes", itens);
           map.put("SubDataSourcePagamentos", ModoPagamentos);
           
           //VIA DO ESTABELECIMENTO
            // Relatório nao compilado
            JasperReport report = JasperCompileManager.compileReport(jrurl);
            JasperPrint print = JasperFillManager.fillReport(report, map, jr);
            JasperViewer jrviewer = new JasperViewer(print, true);
            jrviewer.setVisible(true);
            //Salva PDF
            JasperExportManager.exportReportToPdfFile(print, output);
            //ImpressoraSelecionada("c",print);
            ImprimirSelecionada("c",print);
            
            //Via CONSUMIDOR
            //Relatorio nao compilado
            JasperReport report2Via = JasperCompileManager.compileReport(jrurl2Via);
            JasperPrint print2Via = JasperFillManager.fillReport(report, map, jr);
            JasperViewer jrviewer2Via = new JasperViewer(print, true);
            jrviewer2Via.setVisible(true);
            //ImpressoraSelecionada("c",print); 
            
        } catch (JRException e) {
            System.out.println("Erro ao´imprimir: "+e.getMessage());
        }
    }
    
    public static void XML2Relatorio(String xml_Nfce, String Qr){
        
        //qrCode
        //Caminho do QrCode
        String qrcode = Qr;
        String prestador_fantasia = "";
        String tomador_fantasia = ""; // nome fantasia do tomador/cliente
        String path = System.getProperty("user.dir");
        //Path danfe_nfce_80 = Paths.get(path, "resources","DanfeNFCe.jasper");
        String url = null; //danfe_nfce_80.toAbsolutePath().toString();
        
        // Default impressao em papel 80mm para quando for compilar
        // String jrxml = "/home/junior/JaspersoftWorkspace/MyReports/danfe_nfce_80.jrxml";
        
        try{
            
             //Pega o XML completo da nota gerada
            File inputFile = new File(xml_Nfce);
            //File inputFile = new File("C:\\Users\\Marcos\\Desktop\\Notas Fiscais\\XML\\a.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xml = builder.parse(inputFile);
            xml.getDocumentElement().normalize();
            
            //Cria o objeto que armazenara os dados pros fields
            Relatorio dadosrelatorio = new Relatorio();
            
            // Dados do emitente vindos do xml
            NodeList emitList = xml.getElementsByTagName("emit");
            Node emitNode = emitList.item(0);
            Element emitElement = (Element) emitNode;
            //emitenteRazaoSocial
            dadosrelatorio.setEmitenteRazaoSocial(emitElement.getElementsByTagName("xNome").item(0).getTextContent());
            //emitenteCnpj
            dadosrelatorio.setEmitenteCnpj(emitElement.getElementsByTagName("CNPJ").item(0).getTextContent());
            //emitenteEndereco
            dadosrelatorio.setEmitenteEndereco(emitElement.getElementsByTagName("xLgr").item(0).getTextContent());
            //emitenteenderecoNro
            dadosrelatorio.setEmitenteEnderecoNro(emitElement.getElementsByTagName("nro").item(0).getTextContent());
            //emitentebairro
            dadosrelatorio.setEmitenteBairro(emitElement.getElementsByTagName("xBairro").item(0).getTextContent());
            //emitenteMunicipio
            dadosrelatorio.setEmitenteMunicipio(emitElement.getElementsByTagName("xMun").item(0).getTextContent());
            //emitenteUf
            dadosrelatorio.setEmitenteUf(emitElement.getElementsByTagName("UF").item(0).getTextContent());
            //emitenteCep
            dadosrelatorio.setEmitenteCep(emitElement.getElementsByTagName("CEP").item(0).getTextContent());
            //EmitenteTelefone
            dadosrelatorio.setEmitenteTelefone(emitElement.getElementsByTagName("fone").item(0).getTextContent());
            
            // Dados do Destinatário vindo do xml
            NodeList destList = xml.getElementsByTagName("dest");
            if(destList.getLength() > 0){
                Node destNode = destList.item(0);
                Element destElement = (Element) destNode;
                try {
                    
                    //destinatarioCpf
                    dadosrelatorio.setDestinatarioCpf("CPF: "+destElement.getElementsByTagName("CPF").item(0).getTextContent());
                    //dadosrelatorio.setDestinatarioEndereco(destElement.getElementsByTagName("xLgr").item(0).getTextContent());
                    //dadosrelatorio.setDestinatarioEnderecoNro(destElement.getElementsByTagName("nro").item(0).getTextContent());
                } catch (Exception e) {
                    dadosrelatorio.setDestinatarioCnpj("CNPJ: "+destElement.getElementsByTagName("CNPJ").item(0).getTextContent());
                }
                    //destinaraioRazaoSocial
                    dadosrelatorio.setDestinatarioRazaoSocial(destElement.getElementsByTagName("xNome").item(0).getTextContent());
                       
            }       

            //Lista com parametros do produtos para mandar para o dataset detalhes
            List <ProdImpress> itens = new ArrayList<>();
            // Dados dos Itens vindo do xml
            NodeList itensList = xml.getElementsByTagName("det");
            System.err.println("itensList.getLength(): "+itensList.getLength());
            for(int i = 0; i < itensList.getLength(); i++){
                Node itensNode = itensList.item(i);
                Element itensElement = (Element) itensNode;
                ProdImpress item = new ProdImpress();
                //nItem
                item.setnItem(itensElement.getAttribute("nItem"));
                //codigo
                item.setCodigo(itensElement.getElementsByTagName("cProd").item(0).getTextContent());
                //descricao
                item.setDescricao(itensElement.getElementsByTagName("xProd").item(0).getTextContent());
                System.out.println("Produto: "+item.getDescricao());
                //valorTotal
                item.setValorTotal(itensElement.getElementsByTagName("vProd").item(0).getTextContent());
                //unidadeTributaria
                item.setUnidadeTributaria(itensElement.getElementsByTagName("uTrib").item(0).getTextContent());
                //valorUnitarioComercial
                item.setValorUnitarioComercial(itensElement.getElementsByTagName("vUnCom").item(0).getTextContent());
                //quantidadeComercial
                item.setQuantidadeComercial(itensElement.getElementsByTagName("qCom").item(0).getTextContent());
                //valorUnitarioTributario
                item.setValorUnitarioTributario(itensElement.getElementsByTagName("vUnTrib").item(0).getTextContent());
                //quantidadeTributaria
                item.setQuantidadeTributaria(itensElement.getElementsByTagName("qTrib").item(0).getTextContent());
                //unidadeComercial
                item.setUnidadeComercial(itensElement.getElementsByTagName("uCom").item(0).getTextContent());
                itens.add(item);
            }
             
             
            // Dados da nota vindos do XML
            // Informações de Totais
            NodeList totList = xml.getElementsByTagName("ICMSTot");
            Node totNode = totList.item(0);
            Element totElement = (Element) totNode;
            //valorTotalNota
            dadosrelatorio.setValorTotalNota(totElement.getElementsByTagName("vNF").item(0).getTextContent());
            //ValorTotalProduto
            dadosrelatorio.setValorTotalProduto(totElement.getElementsByTagName("vProd").item(0).getTextContent());
            
            // Numero, Serie, Emissao
            NodeList ideList = xml.getElementsByTagName("ide");
            Node ideNode = ideList.item(0);
            Element ideElement = (Element) ideNode;
            //nNF
            dadosrelatorio.setnNF(ideElement.getElementsByTagName("nNF").item(0).getTextContent());          // Numero
            //serie
            dadosrelatorio.setSerie(ideElement.getElementsByTagName("serie").item(0).getTextContent());        // serie
            //dataEmissao
            String data = ideElement.getElementsByTagName("dhEmi").item(0).getTextContent(); 
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");   
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = sdf.parse(data);
            dadosrelatorio.setDataEmissao(f.format(date));  // Data/hora
            //tpEmis
            dadosrelatorio.setTpEmis(ideElement.getElementsByTagName("tpEmis").item(0).getTextContent()); 
            
            // Chave, Protocolo, Data/Hora Autorização
            NodeList protList = xml.getElementsByTagName("infProt");
            Node protNode = protList.item(0);
            Element protElement = (Element) protNode;
            //**********************************ChaveAcesso
            try{
                dadosrelatorio.setChaveAcesso(protElement.getAttribute("chNFe"));
            }catch(Exception e){
                dadosrelatorio.setChaveAcesso("Nfe29181011951317000120650010000000581633262341");
            }
            //**********************************dhRecbto
            try{
                String dhRecbto = protElement.getElementsByTagName("dhRecbto").item(0).getTextContent();
                Date data_recibo = sdf.parse(dhRecbto);
                dadosrelatorio.setDhRecbto(f.format(data_recibo));
           }catch(Exception e){
                Date data_recibo = sdf.parse(data);
                dadosrelatorio.setDhRecbto(f.format(data_recibo));
           }
            //**********************************nProt
            try{
                dadosrelatorio.setnProt(protElement.getElementsByTagName("nProt").item(0).getTextContent()); 
            }catch(Exception e){
            dadosrelatorio.setnProt("123456789101112"); // Protocolo
            }
            //Troco
            dadosrelatorio.setTroco(getTroco());
            //desconto
            dadosrelatorio.setDesconto("0");
            //detCount
            dadosrelatorio.setDetCount(getCount());
            
            
            //Lista com parametros do produtos para mandar para o dataset detalhes
            List <PagImpress> pagamentos = new ArrayList<>();
            //Dados de Pagamento vindo do XML
            NodeList pagList = xml.getElementsByTagName("pag");
            for(int i = 0; i < pagList.getLength(); i++){
              
                Node pagNode = pagList.item(0);
                Element pagElement = (Element) pagNode;
                PagImpress detPag = new PagImpress(); 
            
            // Grupo obrigatório para a NFC-e, a critério da UF. Não informar para a NF-e.
            String tPag = "";
            try {
                tPag = pagElement.getElementsByTagName("tPag").item(0).getTextContent();
            switch (tPag){
                case "01":
                    tPag = "Dinheiro";
                    break;
                case "02":
                    tPag = "Cheque";
                    break;
                case "03":
                    tPag = "Cartão de Crédito";
                    break;
                case "04":
                    tPag = "Cartão de Débito";
                    break;
                case "05":
                    tPag = "Crédito Loja";
                    break;
                case "10":
                    tPag = "Vale Alimentação";
                    break;
                case "11":
                    tPag = "Vale Refeição";
                    break;
                case "12":
                    tPag = "Vale Presente";
                    break;
                case "13":
                    tPag = "Vale Combustível";
                    break;
                case "99":
                    tPag = "Outros";
                    break;
                default:
                    tPag = "Outros";

            }
            } catch (Exception e) {
                System.out.println("Grupo obrigatório para a NFC-e, a critério da UF. Não informar para a NF-e: " + e);
            }
            detPag.settPag(tPag);
            detPag.setvPag(pagElement.getElementsByTagName("vPag").item(0).getTextContent());
            pagamentos.add(detPag);
            }
            
            // Tributos aprox
            try {
                //informacoesComplementares2
                dadosrelatorio.setInformacoesComplementares2("Tributos Incidentes (Lei Federal 12.741/2012) - Total R$ " +totElement.getElementsByTagName("vTotTrib").item(0).getTextContent());
            } catch (Exception e) {
                System.out.println("Valor total de tributos: " + e);
            }
            
            //QrCode
            InputStream inputStreamDaImagem = null;
			try {  
				File file = new File(qrcode); 
				if(file.exists())//testa se imagem existe
				inputStreamDaImagem = new FileInputStream(file);     
			} catch (FileNotFoundException e) {     
			   e.printStackTrace();     
			}
                        new String(qrcode.getBytes("UTF-8"));
            dadosrelatorio.setQrCode(new String(qrcode.getBytes("UTF-8")));
            
            Collection <Relatorio>  campos = new ArrayList<Relatorio>();
            campos.add(dadosrelatorio);
            JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(campos);
  
               gerarDanfe(url, itens, pagamentos, jr); 
               
               
        } catch (SAXException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Ler XML:  "+ex);
            System.out.println("Erro ao Ler XML:  "+ex);
        } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Ler XML:  "+ex);
            System.out.println("Erro ao Ler XML:  "+ex);
        } catch (ParseException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao Ler XML:  "+ex);
            System.out.println("Erro ao Ler XML:  "+ex);
        } catch (ParserConfigurationException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao Ler XML:  "+ex);
            System.out.println("Erro ao Ler XML:  "+ex);
        }
            
    }

    public static String getTroco() {
        return troco;
    }

    public static void setTroco(String troco) {
        DanfeNFCe_Contigencia.troco = troco;
    }

    public static String getCount() {
        return count;
    }

    public static void setCount(String count) {
        DanfeNFCe_Contigencia.count = count;
    }
    
}
    

