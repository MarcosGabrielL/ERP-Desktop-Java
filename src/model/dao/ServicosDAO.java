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
import model.bean.Servicos;

/**
 *
 * @author Marcos
 */
public class ServicosDAO {
    
    public void creat(Servicos c){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("INSERT INTO nossocliente.servicos (Descrição , Data, Empresa, Telefone, "
                    + "Endereço, Bairro, Cidade, Estado, Cnpj, InscriçãoEstadual, Forma, Vencimento, Parcelas, id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
            
            stmt.setString(1,c.getDescrição());
            stmt.setDate(2,c.getData());
            stmt.setString(3,c.getEmpresa());
            stmt.setString(4,c.getTelefone());
            stmt.setString(5,c.getEndereço());
            stmt.setString(6,c.getBairro());
            stmt.setString(7,c.getCidade());
            stmt.setString(8,c.getEstado());
            stmt.setString(9,c.getCnpj());
            stmt.setString(10,c.getInscriçãoEstadual());
            stmt.setString(11,c.getForma());
            stmt.setDate(12,c.getVencimento());
            stmt.setString(13, c.getParcelas());
            stmt.setInt(14, c.getId());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar conta:  "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public List<Servicos> read(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Servicos> servicos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.servicos");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Servicos servico = new Servicos();
                
                servico.setDescrição(rs.getString("Descrição"));
                servico.setData(rs.getDate("Data"));
                servico.setEmpresa(rs.getString("Empresa"));
                servico.setTelefone(rs.getString("Telefone"));
                servico.setEndereço(rs.getString("Endereço"));
                servico.setBairro(rs.getString("Bairro"));
                servico.setCidade(rs.getString("Cidade"));
                servico.setEstado(rs.getString("Estado"));
                servico.setCnpj(rs.getString("Cnpj"));
                servico.setInscriçãoEstadual(rs.getString("InscriçãoEstadual"));
                servico.setForma(rs.getString("Forma"));
                servico.setVencimento(rs.getDate("Vencimento"));
                servico.setParcelas(rs.getString("Parcelas"));
                servico.setId(rs.getInt("id"));
                
                
                
                
                servicos.add(servico);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler contas: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return servicos;
        
    
    }
    
    public void update(Servicos c){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE nossocliente.servicos SET Data = ?, Telefone = ?, Endereço = ?,"
                    + "Bairro = ?, Cidade=?, Estado =?, Cnpj = ?, InscriçãoEstadual = ?, Forma = ?,"
                    + "Vencimento = ?, Parcelas = ?, Empresa = ? WHERE id = ?");
            stmt.setDate(1, c.getData());
            stmt.setString(2, c.getTelefone());
            stmt.setString(3, c.getEndereço());
            stmt.setString(5, c.getCidade());
            stmt.setString(4, c.getBairro());
            stmt.setString(6, c.getEstado());
            stmt.setString(7, c.getCnpj());
            stmt.setDate(10, c.getVencimento());
            stmt.setString(8, c.getInscriçãoEstadual());
            stmt.setString(9, c.getForma());
            stmt.setString(11, c.getParcelas());
            stmt.setString(12, c.getEmpresa());
            stmt.setInt(13, c.getId());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atalizar conta: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public void delete(int id){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("DELETE FROM nossocliente.servicos WHERE id = ?");
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar nota: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public int PorVEndaNumero(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int venda = 1;
        int venda2 = 1;
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.servicos");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                venda2 = rs.getInt("Id");
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
    
}
