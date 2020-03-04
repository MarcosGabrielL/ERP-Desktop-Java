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
public class Imposto {
    
    private String IRPJ;
    private String CSLL;
    private String PIS;
    private String Cofins;
    private String IPI;
    private String ICMS;
    private String ISS;
    private String CPP;
    private java.sql.Date data;
    private String mês;
    private String fluxomes;

    public String getIRPJ() {
        return IRPJ;
    }

    public void setIRPJ(String IRPJ) {
        this.IRPJ = IRPJ;
    }

    public String getCSLL() {
        return CSLL;
    }

    public void setCSLL(String CSLL) {
        this.CSLL = CSLL;
    }

    public String getPIS() {
        return PIS;
    }

    public void setPIS(String PIS) {
        this.PIS = PIS;
    }

    public String getCofins() {
        return Cofins;
    }

    public void setCofins(String Cofins) {
        this.Cofins = Cofins;
    }

    public String getIPI() {
        return IPI;
    }

    public void setIPI(String IPI) {
        this.IPI = IPI;
    }

    public String getICMS() {
        return ICMS;
    }

    public void setICMS(String ICMS) {
        this.ICMS = ICMS;
    }

    public String getISS() {
        return ISS;
    }

    public void setISS(String ISS) {
        this.ISS = ISS;
    }

    public String getCPP() {
        return CPP;
    }

    public void setCPP(String CPP) {
        this.CPP = CPP;
    }

    public java.sql.Date getData() {
        return data;
    }

    public void setData(java.sql.Date data) {
        this.data = data;
    }

    public String getMês() {
        return mês;
    }

    public void setMês(String mês) {
        this.mês = mês;
    }

    public String getFluxomes() {
        return fluxomes;
    }

    public void setFluxomes(String fluxomes) {
        this.fluxomes = fluxomes;
    }
    
    
}
