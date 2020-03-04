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
public class Produto {
    
   
    private String codigo;
    private String descrição;
    private String preçoUn;
    private String Ventrada;
    private float quantidade;
    private String tipo;
    private String Unidade;
    private String UnidadeTributavel;
    private String data;
    private String loja = "Sede";
    private float SubTotal;
    
    private String NCM;
    private String CST;
    private String CFOP;
    private String BaseICMS;
    private String ICMS;
    private String AliquotaICMS;
    
    private String CEST;
    
    private String CEAN;
    private String CEANTrib;
    private String QTrib;
    private String VUnTrib;

    private int Item;
    
    private String BCICMS;
    private String BCICMSST;
    private String PMVAST;
    private String PRedBCST;
    private String VBCST;
    private String PICMSST;//Valor
    private String VICMSST;//Percentual
    private String PRedBC;
    private String PDif;
    private String VICMSDif;
    private String VICMS;
    private String PCredSN;
    private String VCredICMSSN;
    
    private String CSTPIS;
    private String VBCPIS;
    private String PPIS;
    private String VPIS;
    
    private String CSTCOFINS;
    private String VBCCOFINS;
    private String PCOFINS;
    private String VCOFINS;
    
    private String AFederalN;
    private String AFederalI;
    private String AEstadual;
    private String AMunicipal;
    
    

    public String getAFederalN() {
        return AFederalN;
    }

    public void setAFederalN(String AFederalN) {
        this.AFederalN = AFederalN;
    }

    public String getAFederalI() {
        return AFederalI;
    }

    public void setAFederalI(String AFederalI) {
        this.AFederalI = AFederalI;
    }

    public String getAEstadual() {
        return AEstadual;
    }

    public void setAEstadual(String AEstadual) {
        this.AEstadual = AEstadual;
    }

    public String getAMunicipal() {
        return AMunicipal;
    }

    public void setAMunicipal(String AMunicipal) {
        this.AMunicipal = AMunicipal;
    }

    public String getVentrada() {
        return Ventrada;
    }

    public void setVentrada(String Ventrada) {
        this.Ventrada = Ventrada;
    }

    public String getCSTCOFINS() {
        return CSTCOFINS;
    }

    public void setCSTCOFINS(String CSTCOFINS) {
        this.CSTCOFINS = CSTCOFINS;
    }

    public String getVBCCOFINS() {
        return VBCCOFINS;
    }

    public void setVBCCOFINS(String VBCCOFINS) {
        this.VBCCOFINS = VBCCOFINS;
    }

    public String getPCOFINS() {
        return PCOFINS;
    }

    public void setPCOFINS(String PCOFINS) {
        this.PCOFINS = PCOFINS;
    }

    public String getVCOFINS() {
        return VCOFINS;
    }

    public void setVCOFINS(String VCOFINS) {
        this.VCOFINS = VCOFINS;
    }

    public String getCSTPIS() {
        return CSTPIS;
    }

    public void setCSTPIS(String CSTPIS) {
        this.CSTPIS = CSTPIS;
    }

    public String getVBCPIS() {
        return VBCPIS;
    }

    public void setVBCPIS(String VBCPIS) {
        this.VBCPIS = VBCPIS;
    }

    public String getPPIS() {
        return PPIS;
    }

    public void setPPIS(String PPIS) {
        this.PPIS = PPIS;
    }

    public String getVPIS() {
        return VPIS;
    }

    public void setVPIS(String VPIS) {
        this.VPIS = VPIS;
    }

    public String getPCredSN() {
        return PCredSN;
    }

    public void setPCredSN(String PCredSN) {
        this.PCredSN = PCredSN;
    }

    public String getVCredICMSSN() {
        return VCredICMSSN;
    }

    public void setVCredICMSSN(String VCredICMSSN) {
        this.VCredICMSSN = VCredICMSSN;
    }
    

    public String getPDif() {
        return PDif;
    }

    public void setPDif(String PDif) {
        this.PDif = PDif;
    }

    public String getVICMSDif() {
        return VICMSDif;
    }

    public void setVICMSDif(String VICMSDif) {
        this.VICMSDif = VICMSDif;
    }

    public String getVICMS() {
        return VICMS;
    }

    public void setVICMS(String VICMS) {
        this.VICMS = VICMS;
    }
    

    public String getVICMSST() {
        return VICMSST;
    }

    public void setVICMSST(String VICMSST) {
        this.VICMSST = VICMSST;
    }

    public String getPRedBC() {
        return PRedBC;
    }

    public void setPRedBC(String PRedBC) {
        this.PRedBC = PRedBC;
    }
    

    public String getPICMSST() {
        return PICMSST;
    }

    public void setPICMSST(String PICMSST) {
        this.PICMSST = PICMSST;
    }
    
    public String getVBCST() {
        return VBCST;
    }

    public void setVBCST(String VBCST) {
        this.VBCST = VBCST;
    }

    public String getPRedBCST() {
        return PRedBCST;
    }

    public void setPRedBCST(String PRedBCST) {
        this.PRedBCST = PRedBCST;
    }
    
    public String getPMVAST() {
        return PMVAST;
    }

    public void setPMVAST(String PMVAST) {
        this.PMVAST = PMVAST;
    }

    public String getBCICMS() {
        return BCICMS;
    }

    public void setBCICMS(String BCICMS) {
        this.BCICMS = BCICMS;
    }

    public String getBCICMSST() {
        return BCICMSST;
    }

    public void setBCICMSST(String BCICMSST) {
        this.BCICMSST = BCICMSST;
    }
    
    

    public int getItem() {
        return Item;
    }

    public void setItem(int Item) {
        this.Item = Item;
    }
    
    
    
    public String getCEAN() {
        return CEAN;
    }

    public void setCEAN(String CEAN) {
        this.CEAN = CEAN;
    }

    public String getCEANTrib() {
        return CEANTrib;
    }

    public void setCEANTrib(String CEANTrib) {
        this.CEANTrib = CEANTrib;
    }

    public String getQTrib() {
        return QTrib;
    }

    public void setQTrib(String QTrib) {
        this.QTrib = QTrib;
    }

    public String getVUnTrib() {
        return VUnTrib;
    }

    public void setVUnTrib(String VUnTrib) {
        this.VUnTrib = VUnTrib;
    }

    public String getUnidadeTributavel() {
        return UnidadeTributavel;
    }

    public void setUnidadeTributavel(String UnidadeTributavel) {
        this.UnidadeTributavel = UnidadeTributavel;
    }

    
    public String getCEST() {
        return CEST;
    }

    public void setCEST(String CEST) {
        this.CEST = CEST;
    }
    

    public String getNCM() {
        return NCM;
    }

    public void setNCM(String NCM) {
        this.NCM = NCM;
    }

    public String getCST() {
        return CST;
    }

    public void setCST(String CST) {
        this.CST = CST;
    }

    public String getCFOP() {
        return CFOP;
    }

    public void setCFOP(String CFOP) {
        this.CFOP = CFOP;
    }

    public String getBaseICMS() {
        return BaseICMS;
    }

    public void setBaseICMS(String BaseICMS) {
        this.BaseICMS = BaseICMS;
    }

    public String getICMS() {
        return ICMS;
    }

    public void setICMS(String ICMS) {
        this.ICMS = ICMS;
    }

    public String getAliquotaICMS() {
        return AliquotaICMS;
    }

    public void setAliquotaICMS(String AliquotaICMS) {
        this.AliquotaICMS = AliquotaICMS;
    }
    
    
    

    public float getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(float SubTotal) {
        this.SubTotal = SubTotal;
    }
    
    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }
    private String validade;
    

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }
    

    public String getUnidade() {
        return Unidade;
    }

    public void setUnidade(String Unidade) {
        this.Unidade = Unidade;
    }
    
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

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

    public String getPreçoUn() {
        return preçoUn;
    }

    public void setPreçoUn(String preçoUn) {
        this.preçoUn = preçoUn;
    }
    
    

    
    
    
}
