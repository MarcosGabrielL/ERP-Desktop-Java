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
import model.bean.ADM;


/**
 *
 * @author Marcos
 */
public class AdmDAO {
    
    public void creat(ADM a){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("INSERT INTO nossocliente.adm (idUsuario,Senha) VaLUES(?,?)");
            stmt.setString(1,a.getUsuario());
            stmt.setString(2,a.getSenha());
            
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public List<ADM> read(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ADM> adms = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.adm");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                ADM adm = new ADM();
                
                adm.setUsuario(rs.getString("idUsuario"));
                adm.setSenha(rs.getString("Senha"));
                
                
                adms.add(adm);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return adms;
        
    
    }
    
    public void update(ADM a){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE nossocliente.adm SET Senha = ? WHERE idUsuario = ?");
            stmt.setString(1,a.getSenha());
            stmt.setString(2,a.getUsuario());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atalizar: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public void delete(ADM a){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("DELETE FROM nossocliente.adm WHERE idUsuario = ? AND Senha = ?");
            stmt.setString(1,a.getUsuario());
            stmt.setString(2,a.getSenha());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
}
