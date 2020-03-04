/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Date;

/**
 *
 * @author Marcos
 */
public class Meta {

    public String getLoja() {
        return Loja;
    }

    public void setLoja(String Loja) {
        this.Loja = Loja;
    }

    public String getMetaSemanal() {
        return MetaSemanal;
    }

    public void setMetaSemanal(String MetaSemanal) {
        this.MetaSemanal = MetaSemanal;
    }

    public String getMetaMensal() {
        return MetaMensal;
    }

    public void setMetaMensal(String MetaMensal) {
        this.MetaMensal = MetaMensal;
    }

    public String getMetaAnual() {
        return MetaAnual;
    }

    public void setMetaAnual(String MetaAnual) {
        this.MetaAnual = MetaAnual;
    }
    
    private java.sql.Date DataCriação;

    public java.sql.Date getDataCriação() {
        return DataCriação;
    }

    public void setDataCriação(java.sql.Date DataCriação) {
        this.DataCriação = DataCriação;
    }


    public float getStatusSemanal() {
        return StatusSemanal;
    }

    public void setStatusSemanal(float StatusSemanal) {
        this.StatusSemanal = StatusSemanal;
    }

    public float getStatusMensal() {
        return StatusMensal;
    }

    public void setStatusMensal(float StatusMensal) {
        this.StatusMensal = StatusMensal;
    }

    public float getStatusAnual() {
        return StatusAnual;
    }

    public void setStatusAnual(float StatusAnual) {
        this.StatusAnual = StatusAnual;
    }
    
    
    
    private java.sql.Date DataFimSemanal;

    public java.sql.Date getDataFimSemanal() {
        return DataFimSemanal;
    }

    public void setDataFimSemanal(java.sql.Date DataFimSemanal) {
        this.DataFimSemanal = DataFimSemanal;
    }

    public java.sql.Date getDataFimMensal() {
        return DataFimMensal;
    }

    public void setDataFimMensal(java.sql.Date DataFimMensal) {
        this.DataFimMensal = DataFimMensal;
    }

    public java.sql.Date getDataFimAnual() {
        return DataFimAnual;
    }

    public void setDataFimAnual(java.sql.Date DataFimAnual) {
        this.DataFimAnual = DataFimAnual;
    }
    private java.sql.Date DataFimMensal;
    private java.sql.Date DataFimAnual;
    private float StatusSemanal;
    private float StatusMensal;
    private float StatusAnual;
    private String Loja;
    private String MetaSemanal;
    private String MetaMensal;
    private String MetaAnual;
    
    
}
