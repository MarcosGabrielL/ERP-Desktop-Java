/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador;

/**
 *
 * @author Softsaj
 */
public class Gerenciador30 {

    
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela_Login_Sistema().setVisible(true);
            }
        });
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Seridor().setVisible(true);
            }
        });
    }
    
}
