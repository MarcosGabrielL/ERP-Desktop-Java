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
import model.bean.Fiscal;

/**
 *
 * @author Marcos
 */
public class BalançaDAO {
    
    
     public void creat(Balança b){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("INSERT INTO nossocliente.balancas"
                    + "(comunicacao,porta,fabricante,modelo,local,id) "
                    + "VALUES (?,?,?,?,?,?)");
            
            stmt.setString(1,b.getComunicação());
            stmt.setString(2,b.getPorta());
            stmt.setString(3,b.getFabricante());
            stmt.setString(4,b.getModelo());
            stmt.setString(5,b.getLocal());
            stmt.setInt(6,b.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar configuração da balança:  "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public List<Balança> read(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Balança> balanças = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.balancas");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Balança balança = new Balança();
                
                 balança.setComunicação(rs.getString("comunicacao"));
                balança.setPorta(rs.getString("porta"));
                balança.setFabricante(rs.getString("fabricante"));
                balança.setModelo(rs.getString("modelo"));
                balança.setLocal(rs.getString("local"));
                balança.setId(rs.getInt("id"));
                
                balanças.add(balança);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler dados de balanças: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return balanças;
        
    
    }
    
    public void update(Balança b){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE nossocliente.balancas SET comunicacao = ?,porta = ?,fabricante = ?,modelo = ?,"
                    + "local = ? WHERE id = ?");
            stmt.setString(1, b.getComunicação());
            stmt.setString(2,b.getPorta());
            stmt.setString(3,b.getFabricante());
            stmt.setString(4,b.getModelo());
            stmt.setString(5,b.getLocal());
            stmt.setInt(6,b.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atalizar dados da balança: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public int idsem(){
        int idqfalta = 0;
        int i = 0;
        int id = 0;
        
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Balança> balanças = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.balancas ORDER BY id");
            rs = stmt.executeQuery();
            
            while(rs.next()){
        
                id = rs.getInt("Id");
                if(id!=i){
                System.out.println("Não tem id:"+id);
                idqfalta=i;
                }
            i++;
            }
            if(id==i){
                idqfalta = i;
            }
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler dados de balanças: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return idqfalta;
    }
    
}
