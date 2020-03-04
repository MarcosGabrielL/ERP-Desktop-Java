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
public class Caixa {

    public int getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(int idCaixa) {
        this.idCaixa = idCaixa;
    }

    public String getOperador() {
        return Operador;
    }

    public void setOperador(String Operador) {
        this.Operador = Operador;
    }

    public String getAberturaData() {
        return AberturaData;
    }

    public void setAberturaData(String AberturaData) {
        this.AberturaData = AberturaData;
    }

    public String getFechamentoData() {
        return FechamentoData;
    }

    public void setFechamentoData(String FechamentoData) {
        this.FechamentoData = FechamentoData;
    }

    public String getLoja() {
        return Loja;
    }

    public void setLoja(String Loja) {
        this.Loja = Loja;
    }

    public String getEmpresaCNPJ() {
        return EmpresaCNPJ;
    }

    public void setEmpresaCNPJ(String EmpresaCNPJ) {
        this.EmpresaCNPJ = EmpresaCNPJ;
    }

    public String getEmpresaLogin() {
        return EmpresaLogin;
    }

    public void setEmpresaLogin(String EmpresaLogin) {
        this.EmpresaLogin = EmpresaLogin;
    }

    public String getEmpresaSenha() {
        return EmpresaSenha;
    }

    public void setEmpresaSenha(String EmpresaSenha) {
        this.EmpresaSenha = EmpresaSenha;
    }

    public String getFluxoCaixa() {
        return FluxoCaixa;
    }

    public void setFluxoCaixa(String FluxoCaixa) {
        this.FluxoCaixa = FluxoCaixa;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public int getImpressora() {
        return Impressora;
    }

    public void setImpressora(int Impressora) {
        this.Impressora = Impressora;
    }

    public String getSIni() {
        return SIni;
    }

    public void setSIni(String SIni) {
        this.SIni = SIni;
    }

    public String getSFim() {
        return SFim;
    }

    public void setSFim(String SFim) {
        this.SFim = SFim;
    }
    
    private int Impressora;
    private int idCaixa;
    private String Operador;
    private String AberturaData;
    private String FechamentoData;
    private String Loja;
    private String EmpresaCNPJ;
    private String EmpresaLogin;
    private String EmpresaSenha;
    private String FluxoCaixa;
    private String Senha;
    private String SIni;
    private String SFim;
    
    private String NomeImpressora;
    private String ModeloImpressora;
    private String MarcaImpressora;
    private String PortaBalanca;
    private String FabricanteBalanca;
    private String ModeloBalanca;
    private String ComunicaçãoBalança;

    public String getNomeImpressora() {
        return NomeImpressora;
    }

    public void setNomeImpressora(String NomeImpressora) {
        this.NomeImpressora = NomeImpressora;
    }

    public String getModeloImpressora() {
        return ModeloImpressora;
    }

    public void setModeloImpressora(String ModeloImpressora) {
        this.ModeloImpressora = ModeloImpressora;
    }

    public String getMarcaImpressora() {
        return MarcaImpressora;
    }

    public void setMarcaImpressora(String MarcaImpressora) {
        this.MarcaImpressora = MarcaImpressora;
    }

    public String getPortaBalanca() {
        return PortaBalanca;
    }

    public void setPortaBalanca(String PortaBalanca) {
        this.PortaBalanca = PortaBalanca;
    }

    public String getFabricanteBalanca() {
        return FabricanteBalanca;
    }

    public void setFabricanteBalanca(String FabricanteBalanca) {
        this.FabricanteBalanca = FabricanteBalanca;
    }

    public String getModeloBalanca() {
        return ModeloBalanca;
    }

    public void setModeloBalanca(String ModeloBalanca) {
        this.ModeloBalanca = ModeloBalanca;
    }

    public String getComunicaçãoBalança() {
        return ComunicaçãoBalança;
    }

    public void setComunicaçãoBalança(String ComunicaçãoBalança) {
        this.ComunicaçãoBalança = ComunicaçãoBalança;
    }
    
    
    

}