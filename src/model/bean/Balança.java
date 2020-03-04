/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Marcos
 */
public class Balança {
    
    String Comunicação;
String Porta;
String Fabricante;
String Modelo;
String Local;
int id;

    public String getLocal() {
        return Local;
    }

    public void setLocal(String Local) {
        this.Local = Local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getComunicação() {
        return Comunicação;
    }

    public void setComunicação(String Comunicação) {
        this.Comunicação = Comunicação;
    }

    public String getPorta() {
        return Porta;
    }

    public void setPorta(String Porta) {
        this.Porta = Porta;
    }

    public String getFabricante() {
        return Fabricante;
    }

    public void setFabricante(String Fabricante) {
        this.Fabricante = Fabricante;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }
    
    
    
}
