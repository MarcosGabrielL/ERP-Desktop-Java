/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Date;

/**
 *
 * @author Marcos
 */
public class Vendidos {

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getLoja() {
        return Loja;
    }

    public void setLoja(String Loja) {
        this.Loja = Loja;
    }

    public String getCaixa() {
        return Caixa;
    }

    public void setCaixa(String Caixa) {
        this.Caixa = Caixa;
    }

    

    private String codigo;
    private String descrição;
    private String Tipo;
    private String Loja;
    private String Caixa;
    private String DataSaida;
    private int IdVenda;
    private float quantidade;

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }


    public String getDataSaida() {
        return DataSaida;
    }

    public void setDataSaida(String DataSaida) {
        this.DataSaida = DataSaida;
    }
   
    public int getIdVenda() {
        return IdVenda;
    }

    public void setIdVenda(int IdVenda) {
        this.IdVenda = IdVenda;
    }

    
    

}
