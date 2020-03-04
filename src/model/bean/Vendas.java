/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Marcos
 */
public class Vendas {

    public int getIdVendas() {
        return IdVendas;
    }

    public void setIdVendas(int IdVendas) {
        this.IdVendas = IdVendas;
    }

    public String getCaixa() {
        return Caixa;
    }

    public void setCaixa(String Caixa) {
        this.Caixa = Caixa;
    }

    public String getLoja() {
        return Loja;
    }

    public void setLoja(String Loja) {
        this.Loja = Loja;
    }

    public String getDataVenda() {
        return DataVenda;
    }

    public void setDataVenda(String DataVenda) {
        this.DataVenda = DataVenda;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }

    public String getDiaVenda() {
        return DiaVenda;
    }

    public void setDiaVenda(String DiaVenda) {
        this.DiaVenda = DiaVenda;
    }
    
    public float getTroco() {
        return troco;
    }

    public void setTroco(float troco) {
        this.troco = troco;
    }

    public float getRecebido1() {
        return recebido1;
    }

    public void setRecebido1(float recebido1) {
        this.recebido1 = recebido1;
    }

    public float getRecebido2() {
        return recebido2;
    }

    public void setRecebido2(float recebido2) {
        this.recebido2 = recebido2;
    }

    public float getRecebido3() {
        return recebido3;
    }

    public void setRecebido3(float recebido3) {
        this.recebido3 = recebido3;
    }

    public String getModoPagamento1() {
        return ModoPagamento1;
    }

    public void setModoPagamento1(String ModoPagamento1) {
        this.ModoPagamento1 = ModoPagamento1;
    }

    public String getModoPagamento2() {
        return ModoPagamento2;
    }

    public void setModoPagamento2(String ModoPagamento2) {
        this.ModoPagamento2 = ModoPagamento2;
    }

    public String getModoPagamento3() {
        return ModoPagamento3;
    }

    public void setModoPagamento3(String ModoPagamento3) {
        this.ModoPagamento3 = ModoPagamento3;
    }
    
    

   
   private String DiaVenda; 
   private int IdVendas;
   private String Caixa;
   private String Loja;
   private String DataVenda;
   private String Valor;
   private float recebido1; 
   private float recebido2; 
   private float recebido3; 
   private float troco;
   private String ModoPagamento1;
   private String ModoPagamento2;
   private String ModoPagamento3;

    
}