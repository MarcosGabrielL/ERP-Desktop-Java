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
import model.bean.Fornecedor;


/**
 *
 * @author Marcos
 */
public class FornecedorDAO {
    
     public void creat(Fornecedor f){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("INSERT INTO nossocliente.fornecedores (nome,email,empresa,produtos,telefone1,telefone2,endereçoempresa) VaLUES(?,?,?,?,?,?,?)");
            stmt.setString(1,f.getNome());
            stmt.setString(2,f.getEmail());
            stmt.setString(3,f.getEmpresa());
            stmt.setString(4,f.getProdutos());
            stmt.setString(5,f.getTelefone1());
            stmt.setString(6,f.getTelefone2());
            stmt.setString(7,f.getEndereçoempresa());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public List<Fornecedor> read(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fornecedor> fornecedores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.fornecedores");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Fornecedor fornecedor = new Fornecedor();
                
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setEmpresa(rs.getString("empresa"));
                fornecedor.setProdutos(rs.getString("produtos"));
                fornecedor.setTelefone1(rs.getString("telefone1"));
                fornecedor.setTelefone2(rs.getString("telefone2"));
                fornecedor.setEndereçoempresa(rs.getString("endereçoempresa"));
                
                fornecedores.add(fornecedor);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return fornecedores;
        
    
    }
    
    public void update(Fornecedor f){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE nossocliente.fornecedores SET email = ?, produtos = ?, telefone1 = ?, telefone2 = ?, endereçoempresa = ? WHERE nome = ? or empresa = ?");
            stmt.setString(1,f.getEmail());
            stmt.setString(2,f.getProdutos());
            stmt.setString(3,f.getTelefone1());
            stmt.setString(4,f.getTelefone2());
            stmt.setString(5,f.getEndereçoempresa());
            stmt.setString(6,f.getNome());
            stmt.setString(7,f.getEmpresa());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atalizar: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public void delete(Fornecedor f){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("DELETE FROM nossocliente.fornecedores WHERE nome = ? or email = ?");
            stmt.setString(1,f.getNome());
            stmt.setString(2,f.getEmail());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
       
        
    }
    
    public List<Fornecedor> Lerpornome(String nome){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fornecedor> fornecedores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.fornecedores where nome = ?");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Fornecedor fornecedor = new Fornecedor();
                
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setEmpresa(rs.getString("empresa"));
                fornecedor.setProdutos(rs.getString("produtos"));
                fornecedor.setTelefone1(rs.getString("telefone1"));
                fornecedor.setTelefone2(rs.getString("telefone2"));
                fornecedor.setEndereçoempresa(rs.getString("endereçoempresa"));
                
                fornecedores.add(fornecedor);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return fornecedores;
        
    
    }
     
}
