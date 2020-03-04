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
import model.bean.Imposto;

/**
 *
 * @author Marcos
 */
public class ImpostoDAO {
    
    public void ArmazenarImpostos(Imposto p){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
        try {
            stmt = con.prepareStatement("INSERT INTO NOSSOCLIENTE.IMPOSTOS (IRPJ, CSLL, PIS, COFINS, IPI, ICMS, ISS, CPP, MES, FLUXOMES, \"DATA\")" +
"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1,p.getIRPJ());
            stmt.setString(2,p.getCSLL());
            stmt.setString(3,p.getPIS());
            stmt.setString(4,p.getCofins());
            stmt.setString(5,p.getIPI());
            stmt.setString(6,p.getICMS());
            stmt.setString(7,p.getISS());
            stmt.setString(8, p.getCPP());
            stmt.setString(9, p.getMês());
            stmt.setString(10, p.getFluxomes());
            stmt.setDate(11,p.getData());
            stmt.executeUpdate();
            
            
            
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados tributario: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public List<Imposto> readImpostos(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Imposto> impostos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM NOSSOCLIENTE.IMPOSTOS ORDER BY \"DATA\" desc");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Imposto imposto = new Imposto();
                
                imposto.setIRPJ(rs.getString("irpj"));
                imposto.setCSLL(rs.getString("csll"));
                imposto.setPIS(rs.getString("pis"));
                imposto.setCofins(rs.getString("cofins"));
                imposto.setIPI(rs.getString("ipi"));
                imposto.setICMS(rs.getString("icms"));
                imposto.setISS(rs.getString("iss"));
                imposto.setCPP(rs.getString("cpp"));
                imposto.setMês(rs.getString("mes"));
                imposto.setFluxomes(rs.getString("fluxomes"));
                imposto.setData(rs.getDate("data"));
                impostos.add(imposto);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Ler fluxo: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return impostos;
        
    
    }
    
    
}
