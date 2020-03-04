/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.Caixa;


import model.bean.Produto;
import model.bean.Vendas;
import model.bean.Adcionais;
import model.bean.DDConsumidor;

/**
 *
 * @author Marcos
 */
public class ImprimirTela {
    
    
    public void itemvendido(Produto p, int i){
   /*     
        float VlUnitario = 0;
    
        VlUnitario = Float.parseFloat(p.getPreçoUn());
   
        
    System.out.println(p.getCodigo()+"\t"+p.getDescrição()+"\t\t"+p.getQuantidade()+"\t"+p.getUnidade()+"\t"+VlUnitario+"\t\t"+p.getSubTotal());
    
    */
    }
    
    public void imprimeItemCancelado(Produto p, int i){
        
        float VlUnitario = 0;
    
      /*  VlUnitario = Float.parseFloat(p.getPreçoUn());
    
    System.out.println("CANCELADO "+p.getCodigo()+"\t"+p.getDescrição()+"\t\t"+p.getQuantidade()+"\t"+p.getUnidade()+"\t"+VlUnitario+"\t\t"+p.getSubTotal());
    */
    
    }
    
    public void cabeçalho(){
    /* System.out.println("\t\t\tCNPJ: 00.000.000/000-99 Razão Social da Empresa");
     System.out.println("\t\tAV da Tecnologia, 030, Centro, Rio de Janeiro, RJ");
     System.out.println("\tDocumento Auxiliar da Nota Fiscal de Consumidor Eletronica");
     */
    }
    
    public void DetalheProduto(){
    /*
        System.out.println("\n\nCódigo\tDescrição\t\tQtde\tUn\tVl Unit\t\tVl Total\n");
    
    */
    }
    
    public void Totais(int Fpg, int Itens, float SubTotalGeral, Adcionais adicionais, float APagar, Vendas v){
    /*
        System.out.println("tde. total de intens\t\t\t\t\t\t\t"+Itens+"\n"
                         + "Valor total R$      \t\t\t\t\t\t\t"+SubTotalGeral+"");
        if(adicionais.getDesconto()!=0){System.out.print("Desconto R$         \t\t\t\t\t\t\t"+adicionais.getDesconto()+"\n");}
        if(adicionais.getAdicionais()!=0){System.out.print("Adcionais R$        \t\t\t\t\t\t\t"+adicionais.getAdicionais()+"\n");}
        System.out.println("Valor a Pagar R$    \t\t\t\t\t\t\t"+APagar+"\n"
                         + "FORMA PAGAMENTO     \t\t\t\t\t\t\tVALOR PAGO\n");
       // if(Fpg==3){
       if(v.getModoPagamento1() != null){
        System.out.println(v.getModoPagamento1()+"\t\t\t\t\t\t\t"+v.getRecebido1()+"");}
       if(v.getModoPagamento2() != null){
        System.out.println(v.getModoPagamento2()+"\t\t\t\t\t\t\t"+v.getRecebido2()+"");}
       if(v.getModoPagamento3() != null){
        System.out.println(v.getModoPagamento3()+"\t\t\t\t\t\t\t"+v.getRecebido3()+"");}
       // }if(Fpg==2){
      //  System.out.println(v.getModoPagamento1()+"\t\t\t\t\t\t\t"+v.getRecebido1()+"");
      //  System.out.println(v.getModoPagamento2()+"\t\t\t\t\t\t\t"+v.getRecebido2()+"");
      //  }
     //   if(Fpg==1){
       // System.out.println(v.getModoPagamento1()+"\t\t\t\t\t\t\t"+v.getRecebido1());
     //   }
      System.out.println("Troco R$\t\t\t\t\t\t\t"+v.getTroco());
           */                 
    
}
    
    public void Consulta(){
  /*  System.out.println("\n\n\t\t\tConsulte pela Chavede Acesso em\n" +
                        "\t\t\twww.fazenda.rj.gov.br/nfce/consulta\n" +
                        "\t\t00000000000000000000000000000000000000000000000000");

    
    */
    }
    
    public void Consumidor(DDConsumidor c){
    /*
        if(c == null){
         System.out.println("\n\n\t\t\tCONSUMIDOR NAO IDENTIFICADO");
        }else{
        System.out.println("\n\n\t\t\tCONSUMIDOR - CPF "+c.getCPF()+" - "+c.getNome()+" - "+c.getEndreço());
        }
        
       
        */	
    
    }
    
    public void NFCe(){
    /*
        System.out.println("\t\t\tNFC-e nº 000000001   Série 001   10/03/2017 15:03:53\n"
                + "\t\t\tProtocolo de autorização: 314 1300004001 80\n"
                + "\t\t\tData de autorização 10/03/2017 15:03:53 \n\n");
    
    */
    }
    
    public void QRCode(){
       /* 
        System.out.println("			||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                        "			||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                        "			||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                        "			||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                        "			||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                        "			||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                        "			||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                        "			||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n\n");
    */
    }
    
    public void Tributos(){
    
        /*
        System.out.println("\t\tTributos Totais Incidentes (Lei Federal 12.741/2012): R$65,62");
        
        System.out.println("-------------------------------------------------------------------------------");
    */
    }
    
}
