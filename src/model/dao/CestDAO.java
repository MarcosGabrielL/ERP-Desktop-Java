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
import model.bean.Cest;
import model.bean.NotaNFCe;

/**
 *
 * @author Marcos
 */
public class CestDAO {
    
    public List<Cest> LerCEST(String desc){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cest> cests = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM NOSSOCLIENTE.CESTDESCNCM where descricao like ?");
            stmt.setString(1, "%"+desc+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Cest cest = new Cest();
                
                cest.setCest(rs.getString("cest"));
                cest.setNcm(rs.getString("ncm"));
                cest.setDescricao(rs.getString("descricao"));
                
                cests.add(cest);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar notas: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return cests;
        
    
    }
    
}
