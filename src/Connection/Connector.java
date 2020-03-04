/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.derby.drda.NetworkServerControl;
import org.apache.derby.impl.drda.NetworkServerControlImpl;

/**
 *
 * @author Marcos
 */
public class Connector {
    
     public static ResultSet res;
   // public static Connection conn;
    public static PreparedStatement pst;
    public static Statement sta;
    public static String sSQL;  
    public static String url = "jdbc:derby://localhost:1527/NOSSOCLIENTE";
    private static boolean iniciado = false;
    
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String URL = url;
    private static final String USER = "nossocliente";
    private static final String PASS = "lTa3Er3o0EPlwA0";
    
    public static Connection getConnection(){
       
        /*if(iniciado==false){
        
            try {
        System.setProperty("derby.system.home", "\\MyDB\\.netbeans-derby");
        System.out.println("Banco Iniciado");
        //Servidor.startDbServer();
        iniciado = true;
        } catch (Exception ex) {
        System.out.println("Não conseguiu iniciar banco de dados.");
        iniciado = false;

        }
        }*/
        
        try {
            Class.forName(DRIVER);
            return java.sql.DriverManager.getConnection(URL, USER, PASS);
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conecção com servidor: ",ex);
        }}

public static void closeConnection(Connection con){

     try {
        if(con!=null){
            con.close();
        } 
     }catch (SQLException ex) {
            //menssagem
     }
    
}

public static void closeConnection(Connection con, PreparedStatement stmt){

    closeConnection(con);
    
    
        try {
            if(stmt!=null){
            stmt.close();
            }   
        }catch (SQLException ex) {
            //menssagem
        }
    
}

public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){

    closeConnection(con, stmt);
    
    
        try {
            if(rs!=null){
            rs.close();
            }   
        }catch (SQLException ex) {
            //menssagem
        }


}

public static void Fazbackup(Connection con, String diretorio){

        Date hoje = new Date();
        SimpleDateFormat df;
        df = new SimpleDateFormat("dd/MM/yyyy");
        String data = df.format(hoje);
        data = data.replaceAll("/","-");
        try {
            
            String backupdirectory = diretorio + "/backups/" + data;
            CallableStatement cs = con.prepareCall ("CALL SYSCS_UTIL.SYSCS_BACKUP_DATABASE (?)");
            cs.setString (1, backupdirectory);
            cs.execute ();
            cs.close ();
          JOptionPane.showMessageDialog(null, "Backup feito com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no backup"+ ex);
        }


}

public static void LerBackup(String diretorio){

    String a = diretorio;
    
    try {
         Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            DriverManager.getConnection("jdbc:derby:BancoGerenciador;restoreFrom="+a);
           JOptionPane.showMessageDialog(null, "Backing Up Realizado com sucesso!");
        } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, "Erro no backing Up"+ ex);
        } catch (IllegalAccessException ex) {
           JOptionPane.showMessageDialog(null, "Erro no backing Up"+ ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no backing Up"+ ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro no backing Up"+ ex);
        } 




}

}