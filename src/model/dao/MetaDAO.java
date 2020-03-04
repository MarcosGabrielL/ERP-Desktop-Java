/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import Connection.Connector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Caixa;
import model.bean.Fluxo;
import model.bean.Meta;
import model.bean.Vendas;

/**
 *
 * @author Marcos
 */
public class MetaDAO {
    
     public void ArmazenarMeta(Meta m){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
                
    
        try {
            stmt = con.prepareStatement("INSERT INTO nossocliente.metas (Loja, MetaSemanal,MetaMensal,MetaAnual,DataCriação,DataFimMetaSemanal,DataFimMetaMensal,DataFimMetaAnual,StatusSemanal,StatusMensal,StatusAnual) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1,m.getLoja());
            stmt.setString(2,m.getMetaSemanal());
            stmt.setString(3,m.getMetaMensal());
            stmt.setString(4,m.getMetaAnual());
            stmt.setDate(5, m.getDataCriação());
            stmt.setDate(6, m.getDataFimSemanal());
            stmt.setDate(7, m.getDataFimMensal());
            stmt.setDate(8, m.getDataFimAnual());
            stmt.setFloat(9,m.getStatusSemanal());
            stmt.setFloat(10,m.getStatusMensal());
            stmt.setFloat(11,m.getStatusAnual());
            stmt.executeUpdate();
            
            
            
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar Meta: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
     public List<Meta> LerMeta(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Meta> metas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.metas ORDER BY DataCriação");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Meta meta = new Meta();
                
               ;
                meta.setLoja(rs.getString("Loja"));
                meta.setMetaSemanal(rs.getString("MetaSemanal"));
                meta.setMetaMensal(rs.getString("MetaMensal"));
                meta.setMetaAnual(rs.getString("MetaAnual"));
                meta.setDataCriação(rs.getDate("DataCriação"));
                meta.setDataFimSemanal(rs.getDate("DataFimMetaSemanal"));
                meta.setDataFimMensal(rs.getDate("DataFimMetaMensal"));
                meta.setDataFimAnual(rs.getDate("DataFimMetaAnual"));
                meta.setStatusSemanal(rs.getFloat("StatusSemanal"));
                meta.setStatusMensal(rs.getFloat("StatusMensal"));
                meta.setStatusAnual(rs.getFloat("StatusAnual"));
                metas.add(meta);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar metas: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return metas;
        
    
    }
    
     public void update(Meta m){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE nossocliente.metas SET MetaSemanal = ?, MetaMensal = ?, MetaAnual = ? WHERE Loja = ?");
            stmt.setString(1,m.getMetaSemanal());
            stmt.setString(2,m.getMetaMensal());
            stmt.setString(3,m.getMetaAnual());
            stmt.setString(4,m.getLoja());
             
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atalizar Meta: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
     
     public void updateStatus(Meta m){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE nossocliente.metas SET StatusSemanal = ?, StatusMensal = ?, StatusAnual = ? WHERE Loja = ?");
            stmt.setFloat(1,m.getStatusSemanal());
            stmt.setFloat(2,m.getStatusMensal());
            stmt.setFloat(3,m.getStatusAnual());
            stmt.setString(4,m.getLoja());
             
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atalizar status: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
     
     public List<Meta> LerStatus(String Loja){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Meta> metas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.metas Where Loja = ? ORDER BY DataCriação");
            stmt.setString(1, ""+Loja+"");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
            Meta meta = new Meta();
               
                meta.setStatusSemanal(rs.getFloat("StatusSemanal"));
                meta.setStatusMensal(rs.getFloat("StatusMensal"));
                meta.setStatusAnual(rs.getFloat("StatusAnual"));
                metas.add(meta);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar status: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return metas;
        
    
    }
     
     public void delete(String Loja){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("DELETE FROM nossocliente.metas WHERE Loja = ?");
            stmt.setString(1,Loja);
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar Meta: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
}
