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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Fluxo;
import model.bean.Vendidos;

/**
 *
 * @author Marcos
 */
public class FluxoDAO {
    
    
    public void ArmazenarFluxo(Fluxo p){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
                
    
        try {
            stmt = con.prepareStatement("INSERT INTO nossocliente.fluxo (Caixa,Loja,Operador,Abertura_data,Fechamento_data,Fluxo_de_caixa) VALUES(?,?,?,?,?,?)");
            stmt.setInt(1,p.getIdCaixa());
            stmt.setString(2,p.getLoja());
            stmt.setString(3,p.getOperador());
            stmt.setDate(4,p.getAberturaData());
            stmt.setDate(5,p.getFechamentoData());
            stmt.setString(6,p.getFluxoCaixa());
            stmt.executeUpdate();
            
            
            
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public List<Fluxo> readfluxo(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fluxo> fluxos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.fluxo ORDER BY Fechamento_data");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Fluxo fluxo = new Fluxo();
                
                fluxo.setIdCaixa(rs.getInt("Caixa"));
                fluxo.setLoja(rs.getString("Loja"));
                fluxo.setOperador(rs.getString("Operador"));
                fluxo.setAberturaData(rs.getDate("Abertura_data"));
                fluxo.setFechamentoData(rs.getDate("Fechamento_data"));
                fluxo.setFluxoCaixa(rs.getString("Fluxo_de_Caixa"));
                fluxos.add(fluxo);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Ler fluxo: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return fluxos;
        
    
    }
    
     public List<Fluxo> Especifico(String loja, String caixa, java.sql.Date data){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fluxo> fluxo = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.fluxo WHERE Loja LIKE ? AND Caixa = ? AND Abertura_data = ?");
            stmt.setString(1, loja);
            stmt.setInt(2, Integer.parseInt(caixa));
            stmt.setDate(3, data);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                
                Fluxo f = new Fluxo();
                
                f.setIdCaixa(rs.getInt("Caixa"));
                f.setLoja(rs.getString("Loja"));
                f.setOperador(rs.getString("Operador"));
                f.setAberturaData(rs.getDate("Abertura_data"));
                f.setFechamentoData(rs.getDate("Fechamento_data"));
                f.setFluxoCaixa(rs.getString("Fluxo_de_Caixa"));
                
                fluxo.add(f);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler fluxo:"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return  fluxo;
    }
     
     public float FluxoSoma(String loja, java.sql.Date data1, java.sql.Date data2){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fluxo> fluxo = new ArrayList<>();
        
        float numero1 = 0;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.fluxo WHERE Loja LIKE ? AND Abertura_data between ? and ?");
            stmt.setString(1, loja);
            stmt.setDate(2,data1);
            stmt.setDate(3,data2);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                
                
                numero1 = numero1 + Float.parseFloat(rs.getString("Fluxo_de_Caixa"));
               
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: 77"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
             System.out.print(numero1+" ");
             System.out.print(loja+" ");
             System.out.print(data1+" ");
             System.out.print(data2+" ");
             
        }
        
        return  numero1;
    }
    
     public List<Fluxo> EspecificoLoja(String loja){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fluxo> fluxo = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.fluxo WHERE Loja LIKE ?");
            stmt.setString(1, loja);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                
                Fluxo f = new Fluxo();
                
                f.setIdCaixa(rs.getInt("Caixa"));
                f.setLoja(rs.getString("Loja"));
                f.setOperador(rs.getString("Operador"));
                f.setAberturaData(rs.getDate("Abertura_data"));
                f.setFechamentoData(rs.getDate("Fechamento_data"));
                f.setFluxoCaixa(rs.getString("Fluxo_de_Caixa"));
                
                fluxo.add(f);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: 77"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return  fluxo;
    }
     
     public float FluxoSomaTotal(java.sql.Date data1, java.sql.Date data2){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fluxo> fluxo = new ArrayList<>();
        
        float numero1 = 0;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.fluxo WHERE Abertura_data between ? and ?");
            stmt.setDate(1,data1);
            stmt.setDate(2,data2);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                
                
                numero1 = numero1 + Float.parseFloat(rs.getString("Fluxo_de_Caixa"));
               
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: 77"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
             
             
        }
        
        return  numero1;
    }
}
