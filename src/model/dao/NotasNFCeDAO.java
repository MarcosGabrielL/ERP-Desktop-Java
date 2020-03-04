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
import model.bean.NotaNFCe;

/**
 *
 * @author Marcos
 */
public class NotasNFCeDAO {
    
    public void ArmazenarNota(NotaNFCe m){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
                
    
        try {
            stmt = con.prepareStatement("INSERT INTO nossocliente.nfces (\"Numero\",\"Serie\",\"Forma\",\"Loja\",\"Caixa\",\"Valor\")"
                    + " VALUES(?,?,?,?,?,?)");
            stmt.setString(1,m.getNumero());
            stmt.setString(2,m.getSerie());
            stmt.setString(3,m.getForma());
            stmt.setString(4, m.getLoja());
            stmt.setString(5, m.getCaixa());
            stmt.setString(6, m.getValor());
            stmt.executeUpdate();
            
            
            
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar Nota: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
     public List<NotaNFCe> LerNotas(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<NotaNFCe> notas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.nfces ORDER BY \"Numero\"");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                NotaNFCe nota = new NotaNFCe();
                
                nota.setNumero(rs.getString("numero"));
                nota.setSerie(rs.getString("serie"));
                nota.setData(rs.getString("data"));
                nota.setForma(rs.getString("forma"));
                nota.setLoja(rs.getString("loja"));
                nota.setCaixa(rs.getString("caixa"));
                nota.setValor(rs.getString("valor"));
                
                notas.add(nota);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar notas: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return notas;
        
    
    }
     
     public int Numero(String loja, String caixa,String Serie){
         int maxn = 0;
         int novomax = 0 ;
     Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("Select Max(\"Numero\") FROM NOSSOCLIENTE.NFCES where \"Loja\" = ? and \"Caixa\" = ? and \"Serie\" = ?");
            stmt.setString(1,loja);
            stmt.setString(2,caixa);
            stmt.setString(3, Serie);
            rs = stmt.executeQuery();
            
                NotaNFCe nota = new NotaNFCe();
                if (rs.next()) {
                    if(Integer.valueOf(rs.getString(1)) == null){
                        maxn = 0;
                    }else{
                    maxn = Integer.valueOf(rs.getString(1));
                    }
                
                //novomax = maxn+1;
               
                }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar numero: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
     
     return maxn;
     }
     
     public int Serie(String loja, String caixa){
    
      int maxn = 0;
         int novomax = 0 ;
     Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("Select Max(\"Serie\") FROM NOSSOCLIENTE.NFCES where \"Loja\" = ?, \"Caixa\" = ?");
            stmt.setString(1,loja);
            stmt.setString(2,caixa);
            rs = stmt.executeQuery();
            
            
                if (rs.next()) {
                maxn = Integer.valueOf(rs.getInt(1));
                //novomax = maxn+1;
               
                }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar serie: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return maxn;
        
    
    }
    
      public int Id(){
        
        int maxn = 0;
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("Select Max(\"Id\") FROM NOSSOCLIENTE.NFCES");
            rs = stmt.executeQuery();
            
                if (rs.next()) {
                    if(Integer.valueOf(rs.getInt(1)) == null){
                        maxn = 1;
                    }else{
                    maxn = Integer.valueOf(rs.getInt(1));
                    }
                
                //novomax = maxn+1;
               
                }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar id: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
     
     return maxn;
     }
}
