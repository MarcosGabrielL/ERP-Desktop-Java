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
import model.bean.Fluxo;
import model.bean.Vendas;

/**
 *
 * @author Marcos
 */
public class VendasDAO {
    
    public void ArmazenarVenda(Vendas v){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
                
    
        try {
            stmt = con.prepareStatement("INSERT INTO nossocliente.vendas (IdVendas,Caixa,Loja,DataVenda,Valor,DiaVenda) VALUES(?,?,?,?,?,?)");
            stmt.setInt(1,v.getIdVendas());
            stmt.setString(2,v.getCaixa());
            stmt.setString(3,v.getLoja());
            stmt.setString(4,v.getDataVenda());
            stmt.setString(5,v.getValor());
            stmt.setString(6, v.getDiaVenda());
            stmt.executeUpdate();
            
            
           // JOptionPane.showMessageDialog(null, "Venda Salva");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao armazenar venda: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public List<Vendas> readVenda(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vendas> vendas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.vendas ORDER BY idvendas");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Vendas venda = new Vendas();
                Fluxo fluxo = new Fluxo();
                
                venda.setIdVendas(rs.getInt("IdVendas"));
                venda.setCaixa(rs.getString("Caixa"));
                venda.setLoja(rs.getString("Loja"));
                venda.setDataVenda(rs.getString("DataVenda"));
                venda.setValor(rs.getString("Valor"));
                venda.setDiaVenda(rs.getString("DiaVenda"));
                vendas.add(venda);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar vendas: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return vendas;
        
    
    }
    
     public int PorVEndaNumero(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int venda = 1;
        int venda2 = 1;
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.vendas");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                venda2 = rs.getInt("IdVendas");
                if(venda <= venda2){
                
                venda=venda2;
                }
                 
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro5: 77"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return  venda;
        
    
    }
     
      public List<Vendas> readVenda1(String dia){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vendas> vendas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.vendas WHERE DiaVenda LIKE ? ORDER BY IdVendas");
            stmt.setString(1, dia);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Vendas venda = new Vendas();
                
                
                venda.setIdVendas(rs.getInt("IdVendas"));
                venda.setCaixa(rs.getString("Caixa"));
                venda.setLoja(rs.getString("Loja"));
                venda.setDataVenda(rs.getString("DataVenda"));
                venda.setValor(rs.getString("Valor"));
                vendas.add(venda);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pegar vendas do dia: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return vendas;
        
    
    }
}
