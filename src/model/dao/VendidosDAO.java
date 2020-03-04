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
import model.bean.Vendidos;

/**
 *
 * @author Marcos
 */
public class VendidosDAO {
    
    public void creat(Vendidos v){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("INSERT INTO nossocliente.vendasprodutos "
                    + "(código, descrição, Tipo, Loja, Caixa, Datasaida, Quantidade, IdVenda) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            
            
            stmt.setString(1,v.getCodigo());
            stmt.setString(2,v.getDescrição());
            stmt.setString(3,v.getTipo());
            stmt.setString(4,v.getLoja());
            stmt.setString(5,v.getCaixa());
            stmt.setString(6,v.getDataSaida());
            stmt.setFloat(7,v.getQuantidade());
            stmt.setInt(8,v.getIdVenda());
            
            
            
            stmt.executeUpdate();
            
           // JOptionPane.showMessageDialog(null, "Salvo com sucesso");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro 2"+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public List<Vendidos> read(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vendidos> vendas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.vendasprodutos");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Vendidos venda = new Vendidos();
                
                venda.setCaixa(rs.getString("Caixa"));
                venda.setCodigo(rs.getString("Código"));
                venda.setDescrição(rs.getString("descrição"));
                venda.setLoja(rs.getString("Loja"));
                venda.setTipo(rs.getString("Tipo"));
                venda.setDataSaida(rs.getString("Datasaida"));
                venda.setQuantidade(rs.getInt("Quantidade"));
                
                
                vendas.add(venda);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro 3: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return vendas;
        
    
    }
    
    public void update(Vendidos v){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE nossocliente.vendasprodutos SET quantidade = quantidade + ?  WHERE código = ?");
            stmt.setFloat(1,v.getQuantidade());
            stmt.setString(2,v.getCodigo());
           
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atalizar1: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public List<Vendidos> PorArea(String tipo){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vendidos> vendidos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.vendasprodutos WHERE Tipo LIKE ? ORDER BY Quantidade");
            stmt.setString(1, ""+tipo+"");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Vendidos v = new Vendidos();
                
                v.setCodigo(rs.getString("Código"));
                v.setDescrição(rs.getString("descrição"));
                v.setTipo(rs.getString("Tipo"));
                v.setLoja(rs.getString("Loja"));
                v.setCaixa(rs.getString("Caixa"));
                v.setDataSaida(rs.getString("Datasaida"));
                v.setQuantidade(rs.getInt("Quantidade"));
                
                vendidos.add(v);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro5: 77"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return  vendidos;
        
    
    }
    
    public List<Vendidos> PorLoja(String tipo){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vendidos> vendidos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.vendasprodutos WHERE Loja LIKE ? ORDER BY Quantidade");
            stmt.setString(1, ""+tipo+"");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Vendidos v = new Vendidos();
                
                v.setCodigo(rs.getString("Código"));
                v.setDescrição(rs.getString("descrição"));
                v.setTipo(rs.getString("Tipo"));
                v.setLoja(rs.getString("Loja"));
                v.setCaixa(rs.getString("Caixa"));
                v.setDataSaida(rs.getString("Datasaida"));
                v.setQuantidade(rs.getInt("Quantidade"));
                
                vendidos.add(v);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro4: 77"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return  vendidos;
    }
    
    public List<Vendidos> PorCaixa(String loja, String tipo){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vendidos> vendidos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.vendasprodutos WHERE Loja LIKE ? AND Caixa LIKE ? ORDER BY Quantidade");
            stmt.setString(1, ""+loja+"");
            stmt.setString(2, ""+tipo+"");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Vendidos v = new Vendidos();
                
                v.setCodigo(rs.getString("Código"));
                v.setDescrição(rs.getString("descrição"));
                v.setTipo(rs.getString("Tipo"));
                v.setLoja(rs.getString("Loja"));
                v.setCaixa(rs.getString("Caixa"));
                v.setDataSaida(rs.getString("Datasaida"));
                v.setQuantidade(rs.getInt("Quantidade"));
                
                vendidos.add(v);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro8: 77"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return  vendidos;
    }
    
    public List<Vendidos> PorAreaMenos(String tipo){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vendidos> vendidos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.vendasprodutos WHERE Tipo LIKE ? ORDER BY Quantidade DESC");
            stmt.setString(1, ""+tipo+"");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Vendidos v = new Vendidos();
                
                v.setCodigo(rs.getString("Código"));
                v.setDescrição(rs.getString("descrição"));
                v.setTipo(rs.getString("Tipo"));
                v.setLoja(rs.getString("Loja"));
                v.setCaixa(rs.getString("Caixa"));
                v.setDataSaida(rs.getString("Datasaida"));
                v.setQuantidade(rs.getInt("Quantidade"));
                
                vendidos.add(v);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro7: 77"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return  vendidos;
        
    
    }
    
    public List<Vendidos> PorLojaMenos(String tipo){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vendidos> vendidos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.vendasprodutos WHERE Loja LIKE ? ORDER BY Quantidade DESC");
            stmt.setString(1, ""+tipo+"");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Vendidos v = new Vendidos();
                
                v.setCodigo(rs.getString("Código"));
                v.setDescrição(rs.getString("descrição"));
                v.setTipo(rs.getString("Tipo"));
                v.setLoja(rs.getString("Loja"));
                v.setCaixa(rs.getString("Caixa"));
                v.setDataSaida(rs.getString("Datasaida"));
                v.setQuantidade(rs.getInt("Quantidade"));
                
                vendidos.add(v);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro6: 77"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return  vendidos;
    }
    
    public List<Vendidos> PorCaixaMenos(String loja, String tipo){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vendidos> vendidos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.vendasprodutos WHERE Loja LIKE ? AND Caixa LIKE ? ORDER BY Quantidade DESC");
            stmt.setString(1, ""+loja+"");
            stmt.setString(2, ""+tipo+"");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Vendidos v = new Vendidos();
                
                v.setCodigo(rs.getString("Código"));
                v.setDescrição(rs.getString("descrição"));
                v.setTipo(rs.getString("Tipo"));
                v.setLoja(rs.getString("Loja"));
                v.setCaixa(rs.getString("Caixa"));
                v.setDataSaida(rs.getString("Datasaida"));
                v.setQuantidade(rs.getInt("Quantidade"));
                
                vendidos.add(v);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro12: 77"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return  vendidos;
    }
    
    public List<Vendidos> PorIdVenda(int IdVenda, String caixa, String Loja){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vendidos> vendidos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.vendasprodutos WHERE IdVenda = ? AND Caixa LIKE ? AND Loja LIKE ? ORDER BY Datasaida");
            stmt.setInt(1, IdVenda);
            stmt.setString(2, caixa);
            stmt.setString(3, Loja);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Vendidos v = new Vendidos();
                
                v.setCodigo(rs.getString("Código"));
                v.setDescrição(rs.getString("descrição"));
                v.setTipo(rs.getString("Tipo"));
                v.setLoja(rs.getString("Loja"));
                v.setCaixa(rs.getString("Caixa"));
                v.setDataSaida(rs.getString("Datasaida"));
                v.setQuantidade(rs.getInt("Quantidade"));
                
                vendidos.add(v);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro8: 77"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return  vendidos;
    }
    
    public void exclui(Vendidos v){
    
        Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("DELETE FROM nossocliente.vendasprodutos WHERE código = ? AND IdVenda = ?");
            
            stmt.setString(1,v.getCodigo());
            stmt.setInt(2,v.getIdVenda());
            
            stmt.executeUpdate();
            
           // JOptionPane.showMessageDialog(null, "Deletado com sucesso");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cancelar venda:"+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    
    }
    
}
