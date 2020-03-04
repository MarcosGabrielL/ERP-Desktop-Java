/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.AcoesNFCe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import model.bean.Caixa;
import model.bean.Fiscal;
import model.bean.NotaNFCe;
import model.dao.CaixaDAO;
import model.dao.FiscalDAO;
import model.dao.NotasNFCeDAO;

/**
 *
 * @author Marcos
 */
public class GeraChaveAcesso {
    
    public static String uf;
    public static String cnpj;
    public static String forma;
    //public static String numero = "000000001";
    //public static String seriee = "001";
    public static String chaveretorno;
    public static String NumeroNota;
    public static String SerieNota;
    
    public String getnfce(NotaNFCe nota){
    
        try {  
            
            //Pega Numero da nota e série
            getNumeroeSerie(nota);
            
            //Gera Numero aleatorio para código numerico aleatorio
            Random r = new Random();
            
            //Ler dados do emissor: Estado e CNPJ
            FiscalDAO fdao = new FiscalDAO();
            for(Fiscal f:fdao.read()){
            uf = f.getUf();
            cnpj = f.getCNPJ();
            }
            
            
            String cUF = getuf(); // Código da UF do emitente do Documento Fiscal.  
            String dataAAMM = getdata(); // Ano e Mês de emissão da NF-e.  
            String cnpjCpf = cnpj; // CNPJ do emitente.  
            String mod = "65"; // Modelo do Documento Fiscal.  
            String tpEmis = nota.getForma(); // Forma de emissão da NF-e  
            String nNF = NumeroNota; // Número do Documento Fiscal.
            String serie = SerieNota; // Série do Documento Fiscal.  
            String cNF = String.valueOf(r.nextInt(100000000)); // Código Numérico que compõe a Chave de Acesso.  
              
           // System.err.print("Numero: "+getNumero());
            
            StringBuilder chave = new StringBuilder();  
            chave.append(lpadTo(cUF, 2, '0'));  
            chave.append(lpadTo(dataAAMM, 4, '0'));  
            chave.append(lpadTo(cnpjCpf.replaceAll("\\D",""), 14, '0'));  
            chave.append(lpadTo(mod, 2, '0'));  
            chave.append(lpadTo(serie, 3, '0'));  
            chave.append(lpadTo(String.valueOf(nNF), 9, '0'));  
            chave.append(lpadTo(tpEmis, 1, '0'));  
            chave.append(lpadTo(cNF, 8, '0'));   
            chave.append(modulo11(chave.toString()));  
  
            chave.insert(0, "NFe");  
            
            chaveretorno = chave.toString();
            info("Chave NF-e: " + chave.toString());  
        } catch (Exception e) {  
            error(e.toString());  
        }  
        
        return chaveretorno;
    }  
     
    //Pega o código do Estado
    public static String getuf(){
        
        String cod=null;
        
        if(uf.equals("BA")){cod="29";}
        if(uf.equals("AC")){cod="12";}
        if(uf.equals("AL")){cod="27";}
        if(uf.equals("AP")){cod="16";}
        if(uf.equals("AM")){cod="13";}
        if(uf.equals("CE")){cod="23";}
        if(uf.equals("DF")){cod="53";}
        if(uf.equals("ES")){cod="32";}
        if(uf.equals("GO")){cod="52";}
        if(uf.equals("MA")){cod="21";}
        if(uf.equals("MT")){cod="51";}
        if(uf.equals("MS")){cod="50";}
        if(uf.equals("MG")){cod="31";}
        if(uf.equals("PA")){cod="15";}
        if(uf.equals("PB")){cod="25";}
        if(uf.equals("PR")){cod="41";}
        if(uf.equals("PE")){cod="26";}
        if(uf.equals("PI")){cod="22";}
        if(uf.equals("RJ")){cod="33";}
        if(uf.equals("RN")){cod="24";}
        if(uf.equals("RS")){cod="43";}
        if(uf.equals("RO")){cod="11";}
        if(uf.equals("RR")){cod="14";}
        if(uf.equals("SC")){cod="42";}
        if(uf.equals("SP")){cod="35";}
        if(uf.equals("SE")){cod="28";}
        if(uf.equals("TO")){cod="17";}
    
        return cod;
    }
    //pega data
    public static String getdata(){
        String dat = null;
        
        Locale locale = new Locale("pt","BR");
            GregorianCalendar calendar = new GregorianCalendar();
            SimpleDateFormat formatador = new SimpleDateFormat("YYMM",locale);
            Date d = new Date();
            dat = formatador.format(d.getTime());
    
    return dat;
    }
    //pega numero e serie da nota
    public static void getNumeroeSerie(NotaNFCe nota){
    
        String SérieAtual = null;
        NotasNFCeDAO notadao = new NotasNFCeDAO();
        CaixaDAO cdao = new CaixaDAO();
        for(Caixa c: cdao.readdesc(nota.getLoja(), Integer.parseInt(nota.getCaixa()))){
            SérieAtual = c.getSIni();
        }
        //int SérieAtual = notadao.Serie(nota.getLoja(), nota.getCaixa());
        int Maiornumero = notadao.Numero(nota.getLoja(), nota.getCaixa(), SérieAtual);
        if(Maiornumero>=999999899){
            NumeroNota = "1";
            SerieNota = String.valueOf(SérieAtual+1);
            
        }else{
            NumeroNota= String.valueOf(Maiornumero+1);
         SerieNota = String.valueOf(SérieAtual);
         
        }
         
    }
   
    public static int modulo11(String chave) {  
        int total = 0;  
        int peso = 2;  
              
        for (int i = 0; i < chave.length(); i++) {  
            total += (chave.charAt((chave.length()-1) - i) - '0') * peso;  
            peso ++;  
            if (peso == 10)  
                peso = 2;  
        }  
        int resto = total % 11;  
        return (resto == 0 || resto == 1) ? 0 : (11 - resto);  
    }  
  
    public static String lpadTo(String input, int width, char ch) {  
        String strPad = "";  
  
        StringBuffer sb = new StringBuffer(input.trim());  
        while (sb.length() < width)  
            sb.insert(0,ch);  
        strPad = sb.toString();  
          
        if (strPad.length() > width) {  
            strPad = strPad.substring(0,width);  
        }  
        return strPad;  
    }  
  
    /** 
     * Log ERROR. 
     * @param error 
     */  
    private static void error(String error) {  
        System.out.println("| ERROR: " + error);  
    }  
  
    /** 
     * Log INFO. 
     * @param info 
     */  
    private static void info(String info) {  
        System.out.println("| INFO: " + info);  
    }  
    
}
