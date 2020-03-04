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
import model.bean.Funcionario;

/**
 *
 * @author Marcos
 */
public class FuncionarioDAO {
    
    public void creat(Funcionario f){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("INSERT INTO app.bancofuncionario (nome,email,nascimento,cpf,rg,telefone1,telefone2,cargo,cargahoraria,formação,atributos,salario) VaLUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1,f.getNome());
            stmt.setString(2,f.getEmail());
            stmt.setString(3,f.getNascimento());
            stmt.setString(4,f.getCpf());
            stmt.setString(5,f.getRg());
            stmt.setString(6,f.getTelefone1());
            stmt.setString(7,f.getTelefone2());
            stmt.setString(8,f.getCargo());
            stmt.setString(9,f.getCargahoraria());
            stmt.setString(10,f.getFormação());
            stmt.setString(11,f.getAtributos());
            stmt.setString(12,f.getSalario());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public List<Funcionario> read(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM app.bancofuncionario");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Funcionario funcionario = new Funcionario();
                
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setNascimento(rs.getString("nascimento"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setTelefone1(rs.getString("telefone1"));
                funcionario.setTelefone2(rs.getString("telefone2"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setFormação(rs.getString("formação"));
                funcionario.setAtributos(rs.getString("atributos"));
                funcionario.setCargahoraria(rs.getString("cargahoraria"));
                funcionario.setSalario(rs.getString("salario"));
                
                funcionarios.add(funcionario);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return funcionarios;
        
    
    }
    
    public void update(Funcionario f){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE app.bancofuncionario SET email = ?, nascimento = ?, cpf = ?, rg = ?, "
                    + "telefone1 = ?, telefone2 = ?, cargo = ?, cargahoraria = ?, formação = ?, atributos = ?, salario = ?  WHERE nome = ? or cpf = ? or rg = ?");
            
            stmt.setString(1,f.getEmail());
            stmt.setString(2,f.getNascimento());
            stmt.setString(3,f.getCpf());
            stmt.setString(4,f.getRg());
            stmt.setString(5,f.getTelefone1());
            stmt.setString(6,f.getTelefone2());
            stmt.setString(7,f.getCargo());
            stmt.setString(8,f.getCargahoraria());
            stmt.setString(9,f.getFormação());
            stmt.setString(10,f.getAtributos());
            stmt.setString(11,f.getSalario());
            stmt.setString(12,f.getNome());
            stmt.setString(13,f.getCpf());
            stmt.setString(14,f.getRg());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atalizar: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public void delete(Funcionario f){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("DELETE FROM app.bancofuncionario WHERE nome = ? or cpf = ? or rg = ?");
            stmt.setString(1,f.getNome());
            stmt.setString(2,f.getCpf());
            stmt.setString(3,f.getRg());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    
    
    
    
}
