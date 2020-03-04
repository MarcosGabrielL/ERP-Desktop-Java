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
import model.bean.Balança;
import model.bean.IBPT;

/**
 *
 * @author Marcos
 */
public class IBPTDAO {
    
    public void creat(IBPT i){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("INSERT INTO NOSSOCLIENTE.IBPT"
                    + "(codigo,descricao,federaln,federali,estadual,municipal) "
                    + "VALUES (?,?,?,?,?,?)");
            
            stmt.setString(1, i.getCodigo());
            stmt.setString(2, i.getDescricao());
            stmt.setString(3, i.getFederaln());
            stmt.setString(4, i.getFederali());
            stmt.setString(5, i.getEstadual());
            stmt.setString(6, i.getMunicipal());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar configuração de IBPT:  "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public List<IBPT> read(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<IBPT> ibpts = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM NOSSOCLIENTE.IBPT");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                IBPT ibpt = new IBPT();
                
                ibpt.setCodigo(rs.getString("codigo"));
                ibpt.setDescricao(rs.getString("descricao"));
                ibpt.setFederaln(rs.getString("federaln"));
                ibpt.setFederali(rs.getString("federali"));
                ibpt.setEstadual(rs.getString("estadual"));
                ibpt.setMunicipal(rs.getString("municipal"));
                
                ibpts.add(ibpt);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler dados de IBPT: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return ibpts;
        
    
    }
    
   
    
}
