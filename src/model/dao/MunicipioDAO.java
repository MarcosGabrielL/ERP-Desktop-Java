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
import model.bean.Municipio;
import model.bean.Produto;

/**
 *
 * @author Marcos
 */
public class MunicipioDAO {
    
      public List<Municipio> read(String Uf){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Municipio> municipios = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM NOSSOCLIENTE.\"Municipio\" Where \"Uf\" = ?");
            stmt.setString(1, ""+Uf+"");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Municipio municipio = new Municipio();
                
               // municipio.setCodigo(rs.getString("codigo"));
                municipio.setNome(rs.getString("Nome"));
                //municipio.setUf(rs.getString("Uf"));
                
                
                municipios.add(municipio);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Ler Municipios: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return municipios;
        
    
    }
    
public String pegaCodigo(String Nome){

        String cod = null;
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM NOSSOCLIENTE.\"Municipio\" Where \"Nome\" LIKE ?");
            stmt.setString(1, ""+Nome+"");
            rs = stmt.executeQuery();
            
             while(rs.next()){
            
                cod = String.valueOf(rs.getString("codigo"));
             }
             
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar Codigo: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        System.err.print(cod);
        return cod;     

}


}