/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Date;
import java.text.DateFormat;

/**
 *
 * @author Marcos
 */
public class Fluxo {

    public int getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(int idCaixa) {
        this.idCaixa = idCaixa;
    }

    public String getLoja() {
        return Loja;
    }

    public void setLoja(String Loja) {
        this.Loja = Loja;
    }

    public String getOperador() {
        return Operador;
    }

    public void setOperador(String Operador) {
        this.Operador = Operador;
    }

    public String getFluxoCaixa() {
        return FluxoCaixa;
    }

    public void setFluxoCaixa(String FluxoCaixa) {
        this.FluxoCaixa = FluxoCaixa;
    }
    
    private int idCaixa;
    private String Loja;
    private String Operador;

   private java.sql.Date AberturaData;

    public Date getAberturaData() {
        return AberturaData;
    }

    public void setAberturaData(Date AberturaData) {
        this.AberturaData = AberturaData;
    }

   

    
    
    private java.sql.Date FechamentoData;

    public Date getFechamentoData() {
        return FechamentoData;
    }

    public void setFechamentoData(Date FechamentoData) {
        this.FechamentoData = FechamentoData;
    }

    private String FluxoCaixa;

    
    
    
    
    
}
