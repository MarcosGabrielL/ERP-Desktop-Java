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
import model.bean.Cliente;


/**
 *
 * @author Marcos
 */
public class ClienteDAO {
    
     public void creat(Cliente c){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("INSERT INTO nossocliente.bancocliente (nome,nascimento,rua,bairro,numero,cpf,rg,telefone1,telefone2,nota) VaLUES(?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1,c.getNome());
            stmt.setString(2,c.getNascimento());
            stmt.setString(3,c.getRua());
            stmt.setString(4,c.getBairro());
            stmt.setString(5,c.getNumero());
            stmt.setString(6,c.getCpf());
            stmt.setString(7,c.getRg());
            stmt.setString(8,c.getTelefone1());
            stmt.setString(9,c.getTelefone2());
            stmt.setString(10,c.getNota());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
                    } catch (SQLException ex ) {
                        JOptionPane.showMessageDialog(null, "Erro "+ex);
                    }catch (java.lang.NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Erro "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public List<Cliente> read(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.bancocliente");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Cliente cliente = new Cliente();
                
                cliente.setNome(rs.getString("nome"));
                cliente.setNascimento(rs.getString("nascimento"));
                cliente.setRua(rs.getString("rua"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setRg(rs.getString("rg"));
                cliente.setTelefone1(rs.getString("telefone1"));
                cliente.setTelefone2(rs.getString("telefone2"));
                cliente.setNota(rs.getString("nota"));
                
                clientes.add(cliente);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return clientes;
        
    
    }
    
    public void update(Cliente c){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE nossocliente.bancocliente SET nome = ?,nascimento = ?, rua = ?, bairro = ?, numero = ?, cpf = ?, rg = ?, telefone1 = ?, telefone2 = ? WHERE cpf = ? or rg = ?");
            stmt.setString(1,c.getNome());
            stmt.setString(2,c.getNascimento());
            stmt.setString(3,c.getRua());
            stmt.setString(4,c.getBairro());
            stmt.setString(5,c.getNumero());
            stmt.setString(6,c.getCpf());
            stmt.setString(7,c.getRg());
            stmt.setString(8,c.getTelefone1());
            stmt.setString(9,c.getTelefone2());
            stmt.setString(10,c.getCpf());
            stmt.setString(11,c.getRg());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atalizar: "+ex);
            }catch (java.lang.NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Erro "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public boolean updateNota(Cliente c){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE nossocliente.bancocliente SET nota = nota + ? WHERE cpf = ? or rg = ?");
            stmt.setString(1,c.getNota());
            stmt.setString(2,c.getCpf());
            stmt.setString(3,c.getRg());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            boolean teste = true;
            return teste;
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atalizar: "+ex);
            boolean teste = false;
            return teste;
            }catch (java.lang.NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Erro "+ex);
                        boolean teste = false;
            return teste;
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public void delete(Cliente c){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("DELETE FROM nossocliente.bancocliente WHERE cpf = ? OR rg = ? OR nome = ?");
            stmt.setString(1,c.getCpf());
            stmt.setString(2,c.getRg());
            stmt.setString(3,c.getNome());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    
}
