/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import Connection.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Caixa;
import model.bean.Fiscal;

/**
 *
 * @author Marcos
 */
public class FiscalDAO {
    
    public List<Fiscal> read(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fiscal> fiscals = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.fiscal");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Fiscal fiscal = new Fiscal();
                
                 fiscal.setCNPJ(rs.getString("cnpj"));
                fiscal.setRazãoSocial(rs.getString("razaosocial"));
                fiscal.setRua(rs.getString("rua"));
                fiscal.setBairro(rs.getString("bairro"));
                fiscal.setNumero(rs.getString("numero"));
                fiscal.setEstado(rs.getString("estado"));
                fiscal.setUf(rs.getString("uf"));
                fiscal.setTelefone(rs.getString("telefone"));
                fiscal.setEmail(rs.getString("email"));
                fiscal.setCertificado(rs.getString("certificado"));
                fiscal.setNumeroSérie(rs.getString("numeroserie"));
                fiscal.setAmbiente(rs.getInt("ambiente"));
                fiscal.setSérie(rs.getInt("série"));
                fiscal.setCódigoRegimeTributario(rs.getString("regimetributario"));
                fiscal.setICMS(rs.getString("icms"));
                fiscal.setPIS(rs.getString("pis"));
                fiscal.setCOFINS(rs.getString("cofins"));
                fiscal.setIPI(rs.getString("ipi"));
                fiscal.setNomeFantasia(rs.getString("nomefantasia"));
                fiscal.setCidade(rs.getString("cidade"));
                fiscal.setCEP(rs.getString("cep"));
                fiscal.setCFOP(rs.getString("cfop"));
                fiscal.setCTipo(rs.getInt("ctipo"));
                fiscal.setCSenha(rs.getString("csenha"));
                fiscal.setCValidade(rs.getString("cvalidade"));
                fiscal.setCST(rs.getString("cst"));
                fiscal.setNCM(rs.getString("ncm"));
                fiscal.setInscricaoEstadual(rs.getString("inscricaoestadual"));
                fiscal.setIBPT(rs.getString("ibpt"));
                fiscal.setCodigoCity(rs.getString("codigocity"));
                fiscal.setIdToken(rs.getString("idtoken"));
                fiscal.setCSC(rs.getString("csc"));
                
                
                fiscals.add(fiscal);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler dados fiscais: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return fiscals;
        
    
    }
    
    public void update(Fiscal f){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE nossocliente.fiscal SET cnpj = ?, razaosocial = ?, rua = ?,bairro = ?,"
                    + "numero = ?,estado = ?,uf = ?,telefone = ?,email = ?,certificado = ?,numeroserie = ?,ambiente = ?,série = ?,"
                    + "regimetributario = ?,icms = ?,pis = ?,cofins = ?,ipi = ?,nomefantasia = ?,cidade = ?,cep = ?,"
                    + "cfop = ?, ctipo = ?,csenha = ?,cst = ?,ncm = ?, codigocity = ?, inscricaoestadual = ?, ibpt = ?,"
                    + "idtoken = ?, csc = ? WHERE id = 1");
            stmt.setString(1,f.getCNPJ());
            stmt.setString(2,f.getRazãoSocial());
            stmt.setString(3,f.getRua());
            stmt.setString(4,f.getBairro());
            stmt.setString(5,f.getNumero());
            stmt.setString(6,f.getEstado());
            stmt.setString(7,f.getUf());
            stmt.setString(8,f.getTelefone());
            stmt.setString(9,f.getEmail());
            stmt.setString(10,f.getCertificado());
            stmt.setString(11,f.getNumeroSérie());
            stmt.setInt(12,f.getAmbiente());
            stmt.setInt(13,f.getSérie());
            stmt.setString(14,f.getCódigoRegimeTributario());
            stmt.setString(15,f.getICMS());
            stmt.setString(16,f.getPIS());
            stmt.setString(17,f.getCOFINS());
            stmt.setString(18,f.getIPI());
            stmt.setString(19, f.getNomeFantasia());
            stmt.setString(20, f.getCidade());
            stmt.setString(21, f.getCEP());
            stmt.setString(22, f.getCFOP());
            stmt.setInt(23,f.getCTipo());
            stmt.setString(24, f.getCSenha());
            stmt.setString(25, f.getCST());
            stmt.setString(26, f.getNCM());
            stmt.setString(27, f.getCodigoCity());
            stmt.setString(28, f.getInscricaoEstadual());
            stmt.setString(29, f.getIBPT());
            stmt.setString(30, f.getIdToken());
            stmt.setString(31, f.getCSC());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados Fiscais: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public void updatevalidade(Fiscal f){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE nossocliente.fiscal SET cvalidade = ? WHERE id = 1");
            stmt.setString(1, f.getCValidade());
            stmt.executeUpdate();
            
           // JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atalizar dados Fiscais: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
  
     
}
