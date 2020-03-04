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
public class Servicos {

    public String getDescrição() {
        return Descrição;
    }

    public void setDescrição(String Descrição) {
        this.Descrição = Descrição;
    }


    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String Empresa) {
        this.Empresa = Empresa;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getEndereço() {
        return Endereço;
    }

    public void setEndereço(String Endereço) {
        this.Endereço = Endereço;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String Cnpj) {
        this.Cnpj = Cnpj;
    }

    public String getInscriçãoEstadual() {
        return InscriçãoEstadual;
    }

    public void setInscriçãoEstadual(String InscriçãoEstadual) {
        this.InscriçãoEstadual = InscriçãoEstadual;
    }

    public String getForma() {
        return Forma;
    }

    public void setForma(String Forma) {
        this.Forma = Forma;
    }


    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

    public Date getVencimento() {
        return Vencimento;
    }

    public void setVencimento(Date Vencimento) {
        this.Vencimento = Vencimento;
    }
    
    public String getParcelas() {
        return Parcelas;
    }

    public void setParcelas(String Parcelas) {
        this.Parcelas = Parcelas;
    }
    
    private String Parcelas;
    private String Empresa;
    private String Telefone;
    private String Endereço;
    private String Bairro;
    private String Cidade;
    private String Estado;
    private String Cnpj;
    private String InscriçãoEstadual;
    private String Forma;
    private java.sql.Date Vencimento;
    private String Descrição;
    private java.sql.Date Data;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
