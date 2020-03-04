/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.DriverManager.getLogWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.derby.drda.NetworkServerControl;
import org.apache.derby.impl.drda.NetworkServerControlImpl;

/**
 *
 * @author Marcos
 */
public class Servidor {
    
   
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String URL = "jdbc:derby:NOSSOCLIENTE";
    private static final String USER = "nossocliente";
    private static final String PASS = "lTa3Er3o0EPlwA0";
    private static Connection con;
    
    public Servidor(){
    
        try {
            System.setProperty("startNetworkServer -h", "0.0.0.0");
            System.setProperty("derby.drda.startNetworkServer", "true");
            //cria o objeto de controle do servidor
            NetworkServerControlImpl networkServer = new NetworkServerControlImpl();
            //inicia o servidor
            networkServer.start(new PrintWriter(System.out));
            
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASS);
            JOptionPane.showMessageDialog(null, "Banco Iniciado");
            NetworkServerControl server = new NetworkServerControl(InetAddress.getByName("localhost"),1527);
            server.shutdown();
            server.start (new PrintWriter(System.out));
         JOptionPane.showMessageDialog(null, "Iniciou Servidor?");
            //startDbServer();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    
    }
    
    
 public static void startDbServer() {
     
	NetworkServerControl server;
     try {
        
     } catch (Exception ex) {
         Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
     }

}
    
    
}
