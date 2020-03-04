/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.Modulos;

import Connection.Connector;
import gerenciador.TelaGestão;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.bean.Cest;
import model.bean.Fiscal;
import model.bean.IBPT;
import model.bean.Municipio;
import model.bean.Produto;
import model.dao.CestDAO;
import model.dao.FiscalDAO;
import model.dao.IBPTDAO;
import model.dao.ProdutoDAO;

/**
 *
 * @author Marcos
 */
public class Estoque extends javax.swing.JInternalFrame {

    public static String Regime;
    public Estoque() {
        initComponents();
        descricao.setDocument(new CustomDocument());
         readjTableCProdutos();
       readTabelaArea();
        estilojpanel();
        carregaValoresPadrão();
        UnidadeComercial.getEditor().getEditorComponent().setBackground(Color.white);
    }

    public void carregaValoresPadrão(){
        
        FiscalDAO fdao = new FiscalDAO();
        for(Fiscal f: fdao.read()){
           // if(f.getNCM().equals("")){Jncm.setText("1904.10.00");}else{Jncm.setText(f.getNCM());}
            if(f.getCST().equals("")){Jcst.setText("0103");}else{Jcst.setText(f.getCST());}
            if(f.getCFOP().equals("")){Jcfop.setText("5.102");}else{Jcfop.setText(f.getCFOP());}
           // if(f.getICMS().equals("")){Jicms.setText("18");}else{Jicms.setText(f.getICMS());}
        Regime = f.getCódigoRegimeTributario();
        
        if(f.getPIS().equals("49")){
                CSTPIS.setSelectedIndex(9);
            }else{
                if(f.getPIS().equals("99")){
                    CSTPIS.setSelectedIndex(10);
                }else{
                    if(Integer.parseInt(f.getPIS())<=0){
                      CSTPIS.setSelectedIndex(0);  
                    }else{
                        CSTPIS.setSelectedIndex(Integer.parseInt(f.getPIS().substring(1, 2))-1);
                    }
                }
            }
        
            if(f.getCOFINS().equals("49")){
                CSTCOFINS.setSelectedIndex(9);
            }else{
                
                if(f.getCOFINS().equals("99")){
                    CSTCOFINS.setSelectedIndex(10);
                }else{
                    if(Integer.parseInt(f.getCOFINS())<=0){
                         CSTCOFINS.setSelectedIndex(0);
                    }else{
                         CSTCOFINS.setSelectedIndex(Integer.parseInt(f.getCOFINS())-1);
                    }
                }
            }
        
        
        Locale locale = new Locale("pt","BR");
            GregorianCalendar calendar = new GregorianCalendar();
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss",locale);
            Date d = new Date();
            jTextField1.setText(formatador.format(d.getTime()));
            
            
        
    //preparaproximo();
    
    }
    }
    
    private void estilojpanel(){
    
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(PainelEstoque);
           
            
        
        
        
    }
    public void readjTableCProdutos(){
        
        DefaultTableModel modelo = (DefaultTableModel)jTable8.getModel();
        modelo.setNumRows(0);
        
        String preço = null;
        
        ProdutoDAO mdao = new ProdutoDAO();
        for(Produto m: mdao.read()){
            preço =  m.getPreçoUn();
        
            modelo.addRow(new Object[]{
                m.getCodigo(),
                m.getDescrição(),
                m.getTipo(),
                m.getUnidade(),
                preço,
                m.getQuantidade(),
                m.getValidade(),
                m.getData(),
                m.getLoja(),
                m.getNCM(),
                m.getCST(),
                m.getCFOP(),
                m.getBaseICMS(),
                m.getICMS(),
                m.getAliquotaICMS(),
                m.getCEST()
                        
                    });
    }
    
    }
    public void readTabelaArea(){
    
    DefaultTableModel modelo = (DefaultTableModel)TSemTipo.getModel();
    modelo.setNumRows(0);
    ProdutoDAO pdao = new ProdutoDAO();
    
    for(Produto p: pdao.procurarTipo("Sem Tipo")){
        
        String preço =  p.getPreçoUn();
        
            modelo.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                preço,
                p.getQuantidade()
                
                    });
    }
    
    DefaultTableModel modelo2 = (DefaultTableModel)jTable10.getModel();
    modelo2.setNumRows(0);
    
    for(Produto p: pdao.procurarTipo("Hortfrut")){
    String preço = null;
        
            modelo2.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                p.getPreçoUn(),
                p.getQuantidade()
                
                    });
    }
    
    DefaultTableModel modelo3 = (DefaultTableModel)jTable12.getModel();
    modelo3.setNumRows(0);
    
    for(Produto p: pdao.procurarTipo("Limpeza")){
    
            modelo3.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                p.getPreçoUn(),
                p.getQuantidade()
                
                    });
    }
    
    DefaultTableModel modelo4 = (DefaultTableModel)jTable13.getModel();
    modelo4.setNumRows(0);
    
    for(Produto p: pdao.procurarTipo("Higiene")){
    
            
            modelo4.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                p.getPreçoUn(),
                p.getQuantidade()
                
                    });
    }
    
    DefaultTableModel modelo5 = (DefaultTableModel)jTable14.getModel();
    modelo5.setNumRows(0);
    
    for(Produto p: pdao.procurarTipo("Perfumaria")){
    
            modelo5.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                p.getPreçoUn(),
                p.getQuantidade()
                
                    });
    }
    
    DefaultTableModel modelo6 = (DefaultTableModel)jTable15.getModel();
    modelo6.setNumRows(0);
    
    for(Produto p: pdao.procurarTipo("Utilidades Domésticas")){
    
        
            modelo6.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                p.getPreçoUn(),
                p.getQuantidade()
                
                    });
    }
    
    DefaultTableModel modelo7 = (DefaultTableModel)jTable16.getModel();
    modelo7.setNumRows(0);
    
    for(Produto p: pdao.procurarTipo("Campo e Lazer")){
    
        
            
            
            modelo7.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                p.getPreçoUn(),
                p.getQuantidade()
                
                    });
    }
    
    DefaultTableModel modelo8 = (DefaultTableModel)jTable17.getModel();
    modelo8.setNumRows(0);
    
    for(Produto p: pdao.procurarTipo("Padaria")){
     
            
            modelo8.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                p.getPreçoUn(),
                p.getQuantidade()
                
                    });
    }
    
    DefaultTableModel modelo9 = (DefaultTableModel)jTable18.getModel();
    modelo9.setNumRows(0);
    
    for(Produto p: pdao.procurarTipo("Açougue")){
    
            modelo9.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                p.getPreçoUn(),
                p.getQuantidade()
                
                    });
    }
    
    DefaultTableModel modelo10 = (DefaultTableModel)jTable19.getModel();
    modelo10.setNumRows(0);
    
    for(Produto p: pdao.procurarTipo("Enlatados")){
    
       
            modelo10.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                p.getPreçoUn(),
                p.getQuantidade()
                
                    });
    }
    
    DefaultTableModel modelo11 = (DefaultTableModel)jTable20.getModel();
    modelo11.setNumRows(0);
    
    for(Produto p: pdao.procurarTipo("Doces")){
    
            modelo11.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                p.getPreçoUn(),
                p.getQuantidade()
                
                    });
    }
    
    DefaultTableModel modelo12 = (DefaultTableModel)jTable21.getModel();
    modelo12.setNumRows(0);
    
    for(Produto p: pdao.procurarTipo("Massas e Biscoitos")){
        
            modelo12.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                p.getPreçoUn(),
                p.getQuantidade()
                
                    });
    }
    
    DefaultTableModel modelo13 = (DefaultTableModel)jTable22.getModel();
    modelo13.setNumRows(0);
    
    for(Produto p: pdao.procurarTipo("Cereais")){
    
        
            modelo13.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                p.getPreçoUn(),
                p.getQuantidade()
                
                    });
    }
    
    DefaultTableModel modelo14 = (DefaultTableModel)jTable23.getModel();
    modelo14.setNumRows(0);
    
    for(Produto p: pdao.procurarTipo("Outros")){
    
            
            
            modelo14.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                p.getPreçoUn(),
                p.getQuantidade()
                
                    });
    }
    
    DefaultTableModel modelo15 = (DefaultTableModel)jTable24.getModel();
    modelo15.setNumRows(0);
    
    for(Produto p: pdao.procurarTipo("Higiene e limpeza do lar")){
    
            modelo15.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                p.getPreçoUn(),
                p.getQuantidade()
                
                    });
    }
    
    }
    public void readTabelaEstoqueEspecifico(){
    
        DefaultTableModel modelo = (DefaultTableModel)TabelaEstoqueEspecifico.getModel();
    modelo.setNumRows(0);
    ProdutoDAO pdao = new ProdutoDAO();
    
    String a = JTCodigoEspecifico.getText();
    System.out.print(""+a);
    for(Produto p: pdao.readdesc(a)){
    
        String preço = null;
        preço =  p.getPreçoUn();
        
            modelo.addRow(new Object[]{
                p.getCodigo(),
                p.getDescrição(),
                preço,
                p.getQuantidade(),
                
                    });
    }
    
    JTCodigoEspecifico.setText("");
    
    }
    
    public boolean isValidBarCodeEAN(String barCode) {
        
        
        int digit;
        int calculated;
        String ean;
        String checkSum = "131313131313";
        String checkSum8 ="313131313131";
        int sum = 0;
        if(barCode.equals("Sem GTIN")){
            return true;
        }
        if (barCode.length() == 13) {
            digit = Integer.parseInt("" + barCode.charAt(barCode.length() - 1));            
            ean = barCode.substring(0, barCode.length() - 1);            
            for (int i = 0; i <= ean.length() - 1; i++) {
                sum += (Integer.parseInt("" + ean.charAt(i))) * (Integer.parseInt("" + checkSum.charAt(i)));
            }            
            calculated = 10 - (sum % 10);            
            return (digit == calculated);
        } else {
            if (barCode.length() == 8) {
            digit = Integer.parseInt("" + barCode.charAt(barCode.length() - 1));            
            ean = barCode.substring(0, barCode.length() - 1);            
            for (int i = 0; i <= ean.length() - 1; i++) {
                sum += (Integer.parseInt("" + ean.charAt(i))) * (Integer.parseInt("" + checkSum8.charAt(i)));
            }            
            calculated = 10 - (sum % 10);            
            return (digit == calculated);
            }else{ 
                return false;
            }
        }
    }
    
    

public void preparaproximo(){
        
        Jcest.setEditable(false);
        QuantTrib.setText("1");
        cod.setText("");
        cean.setText("");
        descricao.setText("");
        preço.setText("");
        quan.setText("");
        Jbase.setText("");
        GTNItribu.setText("");
        Jncm.setText("");
        Jcest.setText("");
        Jicms.setText("0.00");
        ValorTribUnidadeTributaria.setText("");
        cean.requestFocusInWindow();
        TipoProduto.setSelectedIndex(0);
        jDateChooser2.setDate(new Date());
        BCICMS.setSelectedIndex(0);
        BCICMSST.setSelectedIndex(0);
        PMVAST.setText("000,00");
        PRedBCST.setText("000,00");
        VBCST.setText("000,00");
        PICMSST.setText("");
        PRedBC.setText("");
        VICMSST.setSelectedIndex(0);
        PDif.setSelectedIndex(0);
        VICMSDif.setText("");
        VICMS.setText("");
        PCredSN.setText("");
        VCredICMSSN.setText("");
        jLabel60.setForeground(new java.awt.Color(102,102,102));
        
        CSTPIS.setSelectedIndex(0);
        VBCPIS.setText("");
        PPIS.setText("00,00");
        VPIS.setText("");
        
        AFederalN.setText("");
        AFederalI.setText("");
        AEstadual.setText("");
        AMunicipal.setText("");
        carregaValoresPadrão();

        readjTableCProdutos();
    
    }
    public void AdicionarProdutos(){
    
        Produto p = new Produto();
            ProdutoDAO dao = new ProdutoDAO();
            boolean para = false;
            
            //att 8
        p.setAFederalN(AFederalN.getText().replace(",","."));
        p.setAFederalI(AFederalI.getText().replace(",","."));
        p.setAEstadual(AEstadual.getText().replace(",","."));
        p.setAMunicipal(AMunicipal.getText().replace(",","."));
            
            //att 7
        p.setVentrada(preço.getText().replaceAll(",","."));
            
            //att 6
        p.setCSTPIS(String.valueOf(CSTPIS.getSelectedItem()).substring(0,2).replaceAll(",","."));
        p.setVBCPIS(VBCPIS.getText().replace(",","."));
        p.setPPIS(PPIS.getText().replace(",","."));
        p.setVPIS(VPIS.getText().replace(",","."));
        p.setCSTPIS(String.valueOf(CSTPIS.getSelectedItem()).substring(0,2).replaceAll(",","."));
        p.setVBCPIS(VBCPIS.getText().replaceAll(",","."));
        p.setPPIS(PPIS.getText().replace(",","."));
        p.setVPIS(VPIS.getText().replace(",","."));
        p.setCSTCOFINS(String.valueOf(CSTCOFINS.getSelectedItem()).substring(0,2).replaceAll(",","."));
        p.setVBCCOFINS(VBCCOFINS.getText().replace(",","."));
        p.setPCOFINS(PCOFINS.getText().replace(",","."));
        p.setVCOFINS(VCOFINS.getText().replace(",","."));
            
            //att 5
        p.setPCredSN(PCredSN.getText().replaceAll(",","."));
        p.setVCredICMSSN(VCredICMSSN.getText().replaceAll(",","."));
            
            //att 1
            p.setNCM(lpadTo(Jncm.getText().replace(".",""),8,'0'));
            System.out.println(Jncm.getText().replace(".",""));
            p.setCST(Jcst.getText().replace(",","."));
            p.setCFOP(Jcfop.getText().replace(",",".").replace(".",""));
            p.setBaseICMS(Jbase.getText().replace(",","."));
            p.setICMS(Jicms.getText().replace(",","."));
            p.setAliquotaICMS(String.valueOf(Aliquota.getSelectedItem()).replace(",","."));
            p.setUnidadeTributavel(UnidadeTributavel.getText().replace(",","."));
            
            //att 2
            if(cean.getText().equals("SEM GTIN")){
                p.setCEAN("SEM GTIN");
            }else{
                if(cean.getText().equals("")){
                     JOptionPane.showMessageDialog(null, "Para Produtos sem GTIN cadastre-o como 'SEM GTNI'.");
                     p.setCEAN("SEM GTIN");
                 }else{
                     if(isValidBarCodeEAN(cean.getText())==false){
                         JOptionPane.showMessageDialog(null, "Codigo de Barras Invalido! Verifique com a Empresa!");
                         para = true;
                      }else{
                         p.setCEAN(cean.getText());
                         p.setCEANTrib(GTNItribu.getText());
                      } 
                }
            }
            
             if(para==false){
            p.setQTrib(QuantTrib.getText().replace(",","."));
            p.setVUnTrib(ValorTribUnidadeTributaria.getText().replace(",","."));
            
            p.setLoja(String.valueOf(cod1.getText()).replace(",","."));
            p.setCodigo(cod.getText().replace(",","."));
            p.setDescrição(descricao.getText().replace(",","."));
            p.setUnidade(UnidadeComercial.getSelectedItem().toString());
            p.setPreçoUn(ValorVenda.getText().replace(",","."));
            p.setQuantidade(Float.parseFloat(quan.getText().replace(",",".")));
            p.setTipo(TipoProduto.getSelectedItem().toString());
            p.setCEST(Jcest.getText().replace(".",""));
            //System.err.println(Jcest.getText());
            
            Locale locale = new Locale("pt","BR");
            GregorianCalendar calendar = new GregorianCalendar();
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy",locale);
            String validade = formatador.format(jDateChooser2.getDate()).toString();
            Date d = new Date();
            String data = formatador.format(d.getTime());
            p.setData(data);
            p.setValidade(validade);

            //att 4
            if(String.valueOf(BCICMS.getSelectedItem()).equals("Selecione Quando for necessário")){
        p.setBCICMS("");
        }
        if(String.valueOf(BCICMS.getSelectedItem()).equals("0=Margem Valor Agregado (%)")){
        p.setBCICMS("0");
        }
        if(String.valueOf(BCICMS.getSelectedItem()).equals("1=Pauta (Valor)")){
        p.setBCICMS("1");
        }
        if(String.valueOf(BCICMS.getSelectedItem()).equals("2=Preço Tabelado Máx. (valor)")){
        p.setBCICMS("2");
        }
        if(String.valueOf(BCICMS.getSelectedItem()).equals("3=Valor da operação")){
        p.setBCICMS("3");
        }
        if(String.valueOf(BCICMSST.getSelectedItem()).equals("Selecione Quando for necessário")){
        p.setBCICMS("");
        }
        if(String.valueOf(BCICMSST.getSelectedItem()).equals("0=Preço tabelado ou máximo sugerido")){
        p.setBCICMS("0");
        }
        if(String.valueOf(BCICMSST.getSelectedItem()).equals("1=Lista Negativa (valor)")){
        p.setBCICMS("1");
        }
        if(String.valueOf(BCICMSST.getSelectedItem()).equals("2=Lista Positiva (valor)")){
        p.setBCICMS("2");
        }
        if(String.valueOf(BCICMSST.getSelectedItem()).equals("3=Lista Neutra (valor)")){
        p.setBCICMS("3");
        }
        if(String.valueOf(BCICMSST.getSelectedItem()).equals("4=Margem Valor Agregado (%)")){
        p.setBCICMS("4");
        }
        if(String.valueOf(BCICMSST.getSelectedItem()).equals("5=Pauta (valor")){
        p.setBCICMS("5");
        }
             p.setPMVAST(PMVAST.getText().replaceAll(",","."));
        p.setPRedBCST(PRedBCST.getText().replaceAll(",","."));
        p.setVBCST(VBCST.getText().replaceAll(",","."));
        p.setPICMSST(PICMSST.getText().replaceAll(",","."));
        p.setPRedBC(PRedBC.getText().replaceAll(",","."));
        if(String.valueOf(VICMSST.getSelectedItem()).equals("0.00")){
            p.setVICMSST("");
        }else{
        p.setVICMSST(String.valueOf(VICMSST.getSelectedItem()).replaceAll(",","."));
        }
        
        if(String.valueOf(PDif.getSelectedItem()).equals("0.00")){
            p.setPDif("");
        }else{
        p.setPDif(String.valueOf(PDif.getSelectedItem()).replaceAll(",","."));
        }
        
        p.setVICMSDif(VICMSDif.getText().replaceAll(",","."));
        p.setVICMS(VICMS.getText().replaceAll(",","."));
             
        dao.creat(p);

        //prepara proximo
        preparaproximo();
        }
    
    }
    public void ApagarProduto(){
    
        if(cod.getText().equals("")){
            Produto p = new Produto();
            ProdutoDAO dao = new ProdutoDAO();
            DefaultTableModel modelo = (DefaultTableModel)jTable8.getModel();

            int a = jTable8.getSelectedRow();
            p.setCodigo(String.valueOf(jTable8.getValueAt(a,0)));
            dao.delete(p);

            preparaproximo();

        }else{
            Produto p = new Produto();
            ProdutoDAO dao = new ProdutoDAO();

            p.setCodigo(cod.getText());
            dao.delete(p);

             preparaproximo();
        }
    
    }
    public void AtualizaProduto(){
    
            Produto p = new Produto();
            ProdutoDAO dao = new ProdutoDAO();
            boolean para = false;
            
            //att 8
        p.setAFederalN(AFederalN.getText().replace(",","."));
        p.setAFederalI(AFederalI.getText().replace(",","."));
        p.setAEstadual(AEstadual.getText().replace(",","."));
        p.setAMunicipal(AMunicipal.getText().replace(",","."));
            
            //att 7
        p.setVentrada(preço.getText().replaceAll(",","."));
            
            //att 6
        p.setCSTPIS(String.valueOf(CSTPIS.getSelectedItem()).substring(0,2).replaceAll(",","."));
        p.setVBCPIS(VBCPIS.getText().replace(",","."));
        p.setPPIS(PPIS.getText().replace(",","."));
        p.setVPIS(VPIS.getText().replace(",","."));
        p.setCSTPIS(String.valueOf(CSTPIS.getSelectedItem()).substring(0,2).replaceAll(",","."));
        p.setVBCPIS(VBCPIS.getText().replaceAll(",","."));
        p.setPPIS(PPIS.getText().replace(",","."));
        p.setVPIS(VPIS.getText().replace(",","."));
        p.setCSTCOFINS(String.valueOf(CSTCOFINS.getSelectedItem()).substring(0,2).replaceAll(",","."));
        p.setVBCCOFINS(VBCCOFINS.getText().replace(",","."));
        p.setPCOFINS(PCOFINS.getText().replace(",","."));
        p.setVCOFINS(VCOFINS.getText().replace(",","."));
            
            //att 5
        p.setPCredSN(PCredSN.getText().replaceAll(",","."));
        p.setVCredICMSSN(VCredICMSSN.getText().replaceAll(",","."));
            
            //att 1
            p.setNCM(lpadTo(Jncm.getText().replace(".",""),8,'0'));
            System.out.println(Jncm.getText().replace(".",""));
            p.setCST(Jcst.getText().replace(",","."));
            p.setCFOP(Jcfop.getText().replace(",",".").replace(".",""));
            p.setBaseICMS(Jbase.getText().replace(",","."));
            p.setICMS(Jicms.getText().replace(",","."));
            p.setAliquotaICMS(String.valueOf(Aliquota.getSelectedItem()).replace(",","."));
            p.setUnidadeTributavel(UnidadeTributavel.getText().replace(",","."));
            
            //att 2
             if(cean.getText().equals("SEM GTIN")){
                p.setCEAN("SEM GTIN");
                p.setCEANTrib("SEM GTIN");
            }else{
                if(cean.getText().equals("")){
                     JOptionPane.showMessageDialog(null, "Para Produtos sem GTIN cadastre-o como 'SEM GTNI'.");
                     p.setCEAN("SEM GTIN");
                     p.setCEANTrib("SEM GTIN");
                 }else{
                     if(isValidBarCodeEAN(cean.getText())==false){
                         JOptionPane.showMessageDialog(null, "Codigo de Barras Invalido! Verifique com a Empresa!");
                         para = true;
                      }else{
                         p.setCEAN(cean.getText());
                         p.setCEANTrib(GTNItribu.getText());
                      } 
                }
            }
            
             if(para==false){
            p.setQTrib(QuantTrib.getText().replace(",","."));
            p.setVUnTrib(ValorTribUnidadeTributaria.getText().replace(",","."));
            
            p.setLoja(String.valueOf(cod1.getText()).replace(",","."));
            p.setCodigo(cod.getText().replace(",","."));
            p.setDescrição(descricao.getText().replace(",","."));
            p.setUnidade(UnidadeComercial.getSelectedItem().toString());
            p.setPreçoUn(ValorVenda.getText().replace(",","."));
            p.setQuantidade(Float.parseFloat(quan.getText().replace(",",".")));
            p.setTipo(TipoProduto.getSelectedItem().toString());
            p.setCEST(Jcest.getText().replace(".",""));
            //System.err.println(Jcest.getText());
            
            Locale locale = new Locale("pt","BR");
            GregorianCalendar calendar = new GregorianCalendar();
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy",locale);
            String validade = formatador.format(jDateChooser2.getDate()).toString();
            Date d = new Date();
            String data = formatador.format(d.getTime());
            p.setData(data);
            p.setValidade(validade);

            //att 4
            if(String.valueOf(BCICMS.getSelectedItem()).equals("Selecione Quando for necessário")){
        p.setBCICMS("");
        }
        if(String.valueOf(BCICMS.getSelectedItem()).equals("0=Margem Valor Agregado (%)")){
        p.setBCICMS("0");
        }
        if(String.valueOf(BCICMS.getSelectedItem()).equals("1=Pauta (Valor)")){
        p.setBCICMS("1");
        }
        if(String.valueOf(BCICMS.getSelectedItem()).equals("2=Preço Tabelado Máx. (valor)")){
        p.setBCICMS("2");
        }
        if(String.valueOf(BCICMS.getSelectedItem()).equals("3=Valor da operação")){
        p.setBCICMS("3");
        }
        if(String.valueOf(BCICMSST.getSelectedItem()).equals("Selecione Quando for necessário")){
        p.setBCICMS("");
        }
        if(String.valueOf(BCICMSST.getSelectedItem()).equals("0=Preço tabelado ou máximo sugerido")){
        p.setBCICMS("0");
        }
        if(String.valueOf(BCICMSST.getSelectedItem()).equals("1=Lista Negativa (valor)")){
        p.setBCICMS("1");
        }
        if(String.valueOf(BCICMSST.getSelectedItem()).equals("2=Lista Positiva (valor)")){
        p.setBCICMS("2");
        }
        if(String.valueOf(BCICMSST.getSelectedItem()).equals("3=Lista Neutra (valor)")){
        p.setBCICMS("3");
        }
        if(String.valueOf(BCICMSST.getSelectedItem()).equals("4=Margem Valor Agregado (%)")){
        p.setBCICMS("4");
        }
        if(String.valueOf(BCICMSST.getSelectedItem()).equals("5=Pauta (valor")){
        p.setBCICMS("5");
        }
             p.setPMVAST(PMVAST.getText().replaceAll(",","."));
        p.setPRedBCST(PRedBCST.getText().replaceAll(",","."));
        p.setVBCST(VBCST.getText().replaceAll(",","."));
        p.setPICMSST(PICMSST.getText().replaceAll(",","."));
        p.setPRedBC(PRedBC.getText().replaceAll(",","."));
        if(String.valueOf(VICMSST.getSelectedItem()).equals("0.00")){
            p.setVICMSST("");
        }else{
        p.setVICMSST(String.valueOf(VICMSST.getSelectedItem()).replaceAll(",","."));
        }
        
        if(String.valueOf(PDif.getSelectedItem()).equals("0.00")){
            p.setPDif("");
        }else{
        p.setPDif(String.valueOf(PDif.getSelectedItem()).replaceAll(",","."));
        }
        
        p.setVICMSDif(VICMSDif.getText().replaceAll(",","."));
        p.setVICMS(VICMS.getText().replaceAll(",","."));
            dao.update(p);
            //prepara ambiente
            preparaproximo();
             }
    
    }
    
    public void PegaValores(){
    
        int a = jTable8.getSelectedRow();
       
        ProdutoDAO pdao = new ProdutoDAO();
        
        for(Produto p: pdao.readdesc(String.valueOf(jTable8.getValueAt(a,0)))){
         
            //att 8
            AFederalN.setText(p.getAFederalN());
            AFederalI.setText(p.getAFederalI());
            AEstadual.setText(p.getAEstadual());
            AMunicipal.setText(p.getAMunicipal());
            
            //att7
            preço.setText(p.getVentrada());
            ValorVenda.setText(p.getPreçoUn());
            
            //att 6
            if(p.getCSTPIS() != null){
            if(p.getCSTPIS().equals("49")){
                CSTPIS.setSelectedIndex(9);
            }else{
                
                if(p.getCSTPIS().equals("99")){
                    CSTPIS.setSelectedIndex(10);
                }else{
                    if(Integer.parseInt(p.getCSTPIS())<=0){
                        CSTPIS.setSelectedIndex(0);
                    }else{
                    CSTPIS.setSelectedIndex(Integer.parseInt(p.getCSTPIS())-1);
                    }
                }   
            }}
        VBCPIS.setText(p.getVBCPIS());
        PPIS.setText(p.getPPIS());
        VPIS.setText(p.getVPIS());
        if(p.getCSTCOFINS() != null){
        if(p.getCSTCOFINS().equals("49")){
                CSTCOFINS.setSelectedIndex(9);
            }else{
                if(p.getCSTCOFINS().equals("99")){
                    CSTCOFINS.setSelectedIndex(10);
                }else{
                    if(Integer.parseInt(p.getCSTCOFINS())<=0){
                         CSTCOFINS.setSelectedIndex(0);
                    }else{
                        CSTCOFINS.setSelectedIndex(Integer.parseInt(p.getCSTCOFINS())-1);
                    }
                }
            }}
        VBCCOFINS.setText(p.getVBCCOFINS());
        PCOFINS.setText(p.getPCOFINS());
        VCOFINS.setText(p.getVCOFINS());
            
            //att 5
        PCredSN.setText(p.getPCredSN());
        VCredICMSSN.setText(p.getVCredICMSSN());
         
        Jncm.setText(p.getNCM());
       Jcst.setText(p.getCST());
        Jcfop.setText(p.getCFOP());
        Jbase.setText(p.getBaseICMS());
        Jicms.setText(p.getICMS());
        Jcest.setText(p.getCEST());
        cean.setText(p.getCEAN());
        descricao.setText(p.getDescrição());
        TipoProduto.getModel().setSelectedItem(p.getTipo());
        UnidadeComercial.getModel().setSelectedItem(p.getUnidade());
        //preço.setText(p.getPreçoUn());
        quan.setText(String.valueOf(p.getQuantidade()));
        QuantTrib.setText(p.getQTrib());
        GTNItribu.setText(p.getCEANTrib());
        ValorTribUnidadeTributaria.setText(p.getVUnTrib());
        cod.setText(p.getCodigo());
        Aliquota.getModel().setSelectedItem(String.valueOf(p.getAliquotaICMS()));
        UnidadeTributavel.setText(p.getUnidadeTributavel());
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {      
                jDateChooser2.setDate((Date)formatter.parse(p.getValidade()));
            } catch (ParseException ex) {
                Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
            }
                if(p.getBCICMS() != null){
            if(p.getBCICMS().equals("")){
            BCICMS.getModel().setSelectedItem("Selecione Quando for necessário");
            }
            if(p.getBCICMS().equals("0")){
            BCICMS.getModel().setSelectedItem("0=Margem Valor Agregado (%)");
            }
            if(p.getBCICMS().equals("1")){
            BCICMS.getModel().setSelectedItem("1=Pauta (Valor)");
            }
            if(p.getBCICMS().equals("2")){
            BCICMS.getModel().setSelectedItem("2=Preço Tabelado Máx. (valor)");
            }
            if(p.getBCICMS().equals("3")){
            BCICMS.getModel().setSelectedItem("3=Valor da operação");
            }}
                if(p.getBCICMSST() != null){
                if(p.getBCICMSST().equals("")){
                    BCICMSST.getModel().setSelectedItem("Selecione Quando for necessário");
                }
                if(p.getBCICMSST().equals("0")){
                    BCICMSST.getModel().setSelectedItem("0=Preço tabelado ou máximo sugerido");
                }
                if(p.getBCICMSST().equals("1")){
                    BCICMSST.getModel().setSelectedItem("1=Lista Negativa (valor)");
                }
                if(p.getBCICMSST().equals("2")){
                    BCICMSST.getModel().setSelectedItem("2=Lista Positiva (valor)");
                }
                if(p.getBCICMSST().equals("3")){
                    BCICMSST.getModel().setSelectedItem("3=Lista Neutra (valor)");
                }
                if(p.getBCICMSST().equals("4")){
                    BCICMSST.getModel().setSelectedItem("4=Margem Valor Agregado (%)");
                }
                if(p.getBCICMSST().equals("5")){
                    BCICMSST.getModel().setSelectedItem("5=Pauta (valor)");
                }}
            PMVAST.setText(p.getPMVAST());
            PRedBCST.setText(p.getPRedBCST());
            VBCST.setText(p.getVBCST());
            PICMSST.setText(p.getPICMSST());
            PRedBC.setText(p.getPRedBC());
            VICMSST.getModel().setSelectedItem(p.getVICMSST());
            
           PDif.getModel().setSelectedItem(p.getPDif());
           VICMSDif.setText(p.getVICMSDif());
           VICMS.setText(p.getVICMS());
        }
        
        
        //jDateChooser2.setDateFormatString(String.valueOf(jTable8.getValueAt(a,6)));
   
    }
    public static String lpadTo(String input, int width, char ch) {  
        String strPad = "";  
  
        StringBuffer sb = new StringBuffer(input.trim());  
        while (sb.length() < width)  
            sb.insert(0,ch);  
        strPad = sb.toString();  
          
        if (strPad.length() > width) {  
            strPad = strPad.substring(0,width);  
        }  
        return strPad;  
    } 
    public void GerarCodigo(){
    
        int idqfalta = 0;
        int i = 1;
        String a;
        int id = 0;
        
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM NOSSOCLIENTE.PRODUTOS order by codigo");
            rs = stmt.executeQuery();
            
            while(rs.next()){
        
                a = String.valueOf(i);
               // System.err.println(lpadTo(a, 13, '0'));
               // System.err.println(rs.getString("codigo"));
                if(rs.getString("codigo").equals(lpadTo(a, 13, '0'))==false){
                idqfalta=i;
                System.out.println("Não tem id:"+(i));
                System.err.println("Novo Id: "+lpadTo(String.valueOf(i), 13, '0'));
                cod.setText(lpadTo(String.valueOf(i), 13, '0'));
                break;
                }
            i++;
            }
            if(id==i){
                idqfalta = i;
                System.out.println("Id que falta:"+i);
                
            }
            
        }catch (SQLException ex) {
        }catch (NumberFormatException ex){
            
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
    
    }
    public void pegajcst(){
    
       if(Jcst.getText().equals("010")){jLabel60.setForeground(Color.BLACK); Jcest.setEditable(true);}
       if(Jcst.getText().equals("030")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("060")){jLabel60.setForeground(Color.BLACK); Jcest.setEditable(true);}
       if(Jcst.getText().equals("070")){ jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("090")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("110")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("130")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("160")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("170")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("190")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("210")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("230")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("260")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("270")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("290")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("310")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("330")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("360")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("370")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("390")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("410")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("430")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("460")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("470")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("490")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("510")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("530")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("560")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("570")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("590")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("610")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("630")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("660")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("670")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("690")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("710")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("730")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("760")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("770")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().equals("790")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().substring(1,4).equals("201")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().substring(1,4).equals("202")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().substring(1,4).equals("203")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().substring(1,4).equals("500")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       if(Jcst.getText().substring(1,4).equals("900")){jLabel60.setForeground(Color.BLACK);Jcest.setEditable(true);}
       
               
       FiscalDAO fdao = new FiscalDAO();
       for(Fiscal f: fdao.read()){
       Regime = f.getCódigoRegimeTributario();
       }
       
       
       //É Simples NAcional?
       if(Regime.equals("1")){
           //É Simples Nacional
           
           if(Jcst.getText().substring(1,4).equals("101")){
           
        //p.getPCredSN()
        nPCredSN.setForeground(Color.BLACK);
        PCredSN.setEditable(true);
        //p.getVCredICMSSN()
        nVCredICMSSN.setForeground(Color.BLACK);
        VCredICMSSN.setEditable(true);
        //Sem uso
             nBCICMS.setForeground(new java.awt.Color(102,102,102));
                BCICMS.setEnabled(false);
            nPRedBC.setForeground(new java.awt.Color(102,102,102));
                PRedBC.setEditable(false);
            nBCICMSST.setForeground(new java.awt.Color(102,102,102));
                BCICMSST.setEnabled(false);
            nPMVAST.setForeground(new java.awt.Color(102,102,102));
                PMVAST.setEditable(false);
            nPRedBCST.setForeground(new java.awt.Color(102,102,102));
                PRedBCST.setEditable(false);
            nVICMSST.setForeground(new java.awt.Color(102,102,102));
                VICMSST.setEditable(false);
            nVBCST.setForeground(new java.awt.Color(102,102,102));
                VBCST.setEditable(false);
            nPICMSST.setForeground(new java.awt.Color(102,102,102));
                PICMSST.setEditable(false);
            nPDif.setForeground(new java.awt.Color(102,102,102));
                PDif.setEditable(false);
            nVICMSDif.setForeground(new java.awt.Color(102,102,102));
                VICMSDif.setEditable(false);
            nVICMS.setForeground(new java.awt.Color(102,102,102));
                VICMS.setEditable(false);
           }
           if(Jcst.getText().substring(1,4).equals("102")){
           
               //Sem uso
             nBCICMS.setForeground(new java.awt.Color(102,102,102));
                BCICMS.setEnabled(false);
            nPRedBC.setForeground(new java.awt.Color(102,102,102));
                PRedBC.setEditable(false);
            nBCICMSST.setForeground(new java.awt.Color(102,102,102));
                BCICMSST.setEnabled(false);
            nPMVAST.setForeground(new java.awt.Color(102,102,102));
                PMVAST.setEditable(false);
            nPRedBCST.setForeground(new java.awt.Color(102,102,102));
                PRedBCST.setEditable(false);
            nVICMSST.setForeground(new java.awt.Color(102,102,102));
                VICMSST.setEditable(false);
            nVBCST.setForeground(new java.awt.Color(102,102,102));
                VBCST.setEditable(false);
            nPICMSST.setForeground(new java.awt.Color(102,102,102));
                PICMSST.setEditable(false);
            nPDif.setForeground(new java.awt.Color(102,102,102));
                PDif.setEditable(false);
            nVICMSDif.setForeground(new java.awt.Color(102,102,102));
                VICMSDif.setEditable(false);
            nVICMS.setForeground(new java.awt.Color(102,102,102));
                VICMS.setEditable(false);
            nPCredSN.setForeground(new java.awt.Color(102,102,102));
                PCredSN.setEditable(false);
            nVCredICMSSN.setForeground(new java.awt.Color(102,102,102));
                VCredICMSSN.setEditable(false);
           }
           if(Jcst.getText().substring(1,4).equals("201")){
           
            //p.getBCICMS()  
            nBCICMS.setForeground(Color.BLACK);
                BCICMS.setEnabled(true);
            //p.getPMVAST()
            nPMVAST.setForeground(Color.BLACK);
                PMVAST.setEditable(true);
           //p.getPRedBCST()
           nPRedBCST.setForeground(Color.BLACK);
                PRedBCST.setEditable(true);
           //p.getVBCST()
           nVBCST.setForeground(Color.BLACK);
                VBCST.setEditable(true);
           //p.getVICMSST()
           nVICMSST.setForeground(Color.BLACK);
                VICMSST.setEditable(true);
           //p.getPICMSST()
           nPICMSST.setForeground(Color.BLACK);
                PICMSST.setEditable(true);
           //p.getPCredSN()
           nPCredSN.setForeground(Color.BLACK);
                PCredSN.setEditable(true);
           //p.getVCredICMSSN()
           nVCredICMSSN.setForeground(Color.BLACK);
                VCredICMSSN.setEditable(true);
           
           //Sem uso
             
            nPRedBC.setForeground(new java.awt.Color(102,102,102));
                PRedBC.setEditable(false);
            nBCICMSST.setForeground(new java.awt.Color(102,102,102));
                BCICMSST.setEnabled(false);
            nPDif.setForeground(new java.awt.Color(102,102,102));
                PDif.setEditable(false);
            nVICMSDif.setForeground(new java.awt.Color(102,102,102));
                VICMSDif.setEditable(false);
            nVICMS.setForeground(new java.awt.Color(102,102,102));
                VICMS.setEditable(false);
            
            
           
           }
           if(Jcst.getText().substring(1,4).equals("202")){
               
             
        //p.getBCICMS()
        nBCICMS.setForeground(Color.BLACK);
                BCICMS.setEnabled(true);
       //p.getPMVAST()
       nPMVAST.setForeground(Color.BLACK);
                PMVAST.setEditable(true);
        //p.getPRedBCST()
        nPRedBCST.setForeground(Color.BLACK);
                PRedBCST.setEditable(true);
        //p.getVBCST()
        nVBCST.setForeground(Color.BLACK);
                VBCST.setEditable(true);
        //p.getVICMSST()
        nVICMSST.setForeground(Color.BLACK);
                VICMSST.setEditable(true);
        //p.getPICMSST()
           nPICMSST.setForeground(Color.BLACK);
                PICMSST.setEditable(true);
                
            //Sem uso
             
            nPRedBC.setForeground(new java.awt.Color(102,102,102));
                PRedBC.setEditable(false);
            nBCICMSST.setForeground(new java.awt.Color(102,102,102));
                BCICMSST.setEnabled(false);
            nPDif.setForeground(new java.awt.Color(102,102,102));
                PDif.setEditable(false);
            nVICMSDif.setForeground(new java.awt.Color(102,102,102));
                VICMSDif.setEditable(false);
            nVICMS.setForeground(new java.awt.Color(102,102,102));
                VICMS.setEditable(false);
            nPCredSN.setForeground(new java.awt.Color(102,102,102));
                PCredSN.setEditable(false);
            nVCredICMSSN.setForeground(new java.awt.Color(102,102,102));
                VCredICMSSN.setEditable(false);
           
           }
           if(Jcst.getText().substring(1,4).equals("500")){
               
               nVICMSST.setForeground(Color.BLACK);
               VICMSST.setEditable(true);
            nVBCST.setForeground(Color.BLACK);
                VBCST.setEditable(true);
            nPICMSST.setForeground(Color.BLACK);
                PICMSST.setEditable(true);
          
               //Sem uso
             nBCICMS.setForeground(new java.awt.Color(102,102,102));
                BCICMS.setEnabled(false);
            nPRedBC.setForeground(new java.awt.Color(102,102,102));
                PRedBC.setEditable(false);
            nBCICMSST.setForeground(new java.awt.Color(102,102,102));
                BCICMSST.setEnabled(false);
            nPMVAST.setForeground(new java.awt.Color(102,102,102));
                PMVAST.setEditable(false);
            nPRedBCST.setForeground(new java.awt.Color(102,102,102));
                PRedBCST.setEditable(false);
           // nVICMSST.setForeground(new java.awt.Color(102,102,102));
              //  VICMSST.setEditable(false);
           // nVBCST.setForeground(new java.awt.Color(102,102,102));
             //   VBCST.setEditable(false);
          //  nPICMSST.setForeground(new java.awt.Color(102,102,102));
             //   PICMSST.setEditable(false);
            nPDif.setForeground(new java.awt.Color(102,102,102));
                PDif.setEditable(false);
            nVICMSDif.setForeground(new java.awt.Color(102,102,102));
                VICMSDif.setEditable(false);
            nVICMS.setForeground(new java.awt.Color(102,102,102));
                VICMS.setEditable(false);
            nPCredSN.setForeground(new java.awt.Color(102,102,102));
                PCredSN.setEditable(false);
            nVCredICMSSN.setForeground(new java.awt.Color(102,102,102));
                VCredICMSSN.setEditable(false);
           }
           if(Jcst.getText().substring(1,4).equals("900")){
               
               //p.getBCICMS()
            nBCICMS.setForeground(Color.BLACK);
                BCICMS.setEnabled(true);
            nPRedBC.setForeground(Color.BLACK);
                PRedBC.setEditable(true);
            nBCICMSST.setForeground(Color.BLACK);
                BCICMSST.setEnabled(true);
            nPMVAST.setForeground(Color.BLACK);
                PMVAST.setEditable(true);
            nPRedBCST.setForeground(Color.BLACK);
                PRedBCST.setEditable(true);
            nVICMSST.setForeground(Color.BLACK);
                VICMSST.setEditable(true);
            nVBCST.setForeground(Color.BLACK);
                VBCST.setEditable(true);
            nPICMSST.setForeground(Color.BLACK);
                PICMSST.setEditable(true);
            nPDif.setForeground(Color.BLACK);
                PDif.setEditable(true);
            nVICMSDif.setForeground(Color.BLACK);
                VICMSDif.setEditable(true);
            nVICMS.setForeground(Color.BLACK);
                VICMS.setEditable(true);
            nPCredSN.setForeground(Color.BLACK);
                PCredSN.setEditable(true);
            nVCredICMSSN.setForeground(Color.BLACK);
                VCredICMSSN.setEditable(true);
           }
           
       }else{
           //Não é simples nacional
           if(Jcst.getText().substring(1,3).equals("00")){
            //p.getBCICMS()
            nBCICMS.setForeground(Color.BLACK);
                BCICMS.setEnabled(true);
            
            //Sem uso
            nPCredSN.setForeground(new java.awt.Color(102,102,102));
                PCredSN.setEditable(false);
            nVCredICMSSN.setForeground(new java.awt.Color(102,102,102));
                VCredICMSSN.setEditable(false);
            nPRedBC.setForeground(new java.awt.Color(102,102,102));
                PRedBC.setEditable(false);
            nBCICMSST.setForeground(new java.awt.Color(102,102,102));
                BCICMSST.setEnabled(false);
            nPMVAST.setForeground(new java.awt.Color(102,102,102));
                PMVAST.setEditable(false);
            nPRedBCST.setForeground(new java.awt.Color(102,102,102));
                PRedBCST.setEditable(false);
            nVICMSST.setForeground(new java.awt.Color(102,102,102));
                VICMSST.setEditable(false);
            nVBCST.setForeground(new java.awt.Color(102,102,102));
                VBCST.setEditable(false);
            nPICMSST.setForeground(new java.awt.Color(102,102,102));
                PICMSST.setEditable(false);
            nPDif.setForeground(new java.awt.Color(102,102,102));
                PDif.setEditable(false);
            nVICMSDif.setForeground(new java.awt.Color(102,102,102));
                VICMSDif.setEditable(false);
            nVICMS.setForeground(new java.awt.Color(102,102,102));
                VICMS.setEditable(false);
                
           }
           if(Jcst.getText().substring(1,3).equals("10")){
              
            //p.getBCICMS()
            nBCICMS.setForeground(Color.BLACK);
                BCICMS.setEnabled(true);
            //p.getBCICMSST()
            nBCICMSST.setForeground(Color.BLACK);
                BCICMSST.setEnabled(true);
            //p.getPMVAST()
            nPMVAST.setForeground(Color.BLACK);
                PMVAST.setEditable(true);
            //p.getPRedBCST())
            nPRedBCST.setForeground(Color.BLACK);
                PRedBCST.setEditable(true);
            //p.getVBCST())
            nVBCST.setForeground(Color.BLACK);
                VBCST.setEditable(true);
            //p.getPICMSST()
            nPICMSST.setForeground(Color.BLACK);
                PICMSST.setEditable(true);
            //p.getVICMSST()
            nVICMSST.setForeground(Color.BLACK);
                VICMSST.setEditable(true);
           
            //Sem uso
           nPRedBC.setForeground(new java.awt.Color(102,102,102));
                PRedBC.setEditable(false);
            nPDif.setForeground(new java.awt.Color(102,102,102));
                PDif.setEditable(false);
            nVICMSDif.setForeground(new java.awt.Color(102,102,102));
                VICMSDif.setEditable(false);
            nVICMS.setForeground(new java.awt.Color(102,102,102));
                VICMS.setEditable(false);
                nPCredSN.setForeground(new java.awt.Color(102,102,102));
                PCredSN.setEditable(false);
            nVCredICMSSN.setForeground(new java.awt.Color(102,102,102));
                VCredICMSSN.setEditable(false);
           }
           if(Jcst.getText().substring(1,3).equals("20")){
               
               //p.getBCICMS()
            nBCICMS.setForeground(Color.BLACK);
                BCICMS.setEnabled(true);
               //p.getPRedBC()
               nPRedBC.setForeground(Color.BLACK);
                PRedBC.setEditable(true);
            //Sem uso
            nBCICMSST.setForeground(new java.awt.Color(102,102,102));
                BCICMSST.setEnabled(false);
            nPMVAST.setForeground(new java.awt.Color(102,102,102));
                PMVAST.setEditable(false);
            nPRedBCST.setForeground(new java.awt.Color(102,102,102));
                PRedBCST.setEditable(false);
            nVICMSST.setForeground(new java.awt.Color(102,102,102));
                VICMSST.setEditable(false);
            nVBCST.setForeground(new java.awt.Color(102,102,102));
                VBCST.setEditable(false);
            nPICMSST.setForeground(new java.awt.Color(102,102,102));
                PICMSST.setEditable(false);
            nPDif.setForeground(new java.awt.Color(102,102,102));
                PDif.setEditable(false);
            nVICMSDif.setForeground(new java.awt.Color(102,102,102));
                VICMSDif.setEditable(false);
            nVICMS.setForeground(new java.awt.Color(102,102,102));
                VICMS.setEditable(false);
                nPCredSN.setForeground(new java.awt.Color(102,102,102));
                PCredSN.setEditable(false);
            nVCredICMSSN.setForeground(new java.awt.Color(102,102,102));
                VCredICMSSN.setEditable(false);
        
           }
           if(Jcst.getText().substring(1,3).equals("30")){
           
            
            
               //p.getBCICMSST()
            nBCICMSST.setForeground(Color.BLACK);
                BCICMSST.setEnabled(true);
               //p.getPMVAST()
            nPMVAST.setForeground(Color.BLACK);
                PMVAST.setEditable(true);
               //p.getPRedBCST()
            nPRedBCST.setForeground(Color.BLACK);
                PRedBCST.setEditable(true);
               //p.getVBCST()
                nVBCST.setForeground(Color.BLACK);
                VBCST.setEditable(true);
               //p.getVICMSST()
            nVICMSST.setForeground(Color.BLACK);
                VICMSST.setEditable(true);
               //p.getPICMSST()
            nPICMSST.setForeground(Color.BLACK);
                PICMSST.setEditable(true);
            
            //Sem uso
            nBCICMS.setForeground(new java.awt.Color(102,102,102));
                BCICMS.setEnabled(false);
            nPRedBC.setForeground(new java.awt.Color(102,102,102));
                PRedBC.setEditable(false);
            nPDif.setForeground(new java.awt.Color(102,102,102));
                PDif.setEditable(false);
            nVICMSDif.setForeground(new java.awt.Color(102,102,102));
                VICMSDif.setEditable(false);
            nVICMS.setForeground(new java.awt.Color(102,102,102));
                VICMS.setEditable(false);
                nPCredSN.setForeground(new java.awt.Color(102,102,102));
                PCredSN.setEditable(false);
            nVCredICMSSN.setForeground(new java.awt.Color(102,102,102));
                VCredICMSSN.setEditable(false);
           }
           if(Jcst.getText().substring(1,3).equals("40") || Jcst.getText().substring(1,3).equals("41") || Jcst.getText().substring(1,3).equals("50")){
           
            //Sem uso
             nBCICMS.setForeground(new java.awt.Color(102,102,102));
                BCICMS.setEnabled(false);
            nPRedBC.setForeground(new java.awt.Color(102,102,102));
                PRedBC.setEditable(false);
            nBCICMSST.setForeground(new java.awt.Color(102,102,102));
                BCICMSST.setEnabled(false);
            nPMVAST.setForeground(new java.awt.Color(102,102,102));
                PMVAST.setEditable(false);
            nPRedBCST.setForeground(new java.awt.Color(102,102,102));
                PRedBCST.setEditable(false);
            nVICMSST.setForeground(new java.awt.Color(102,102,102));
                VICMSST.setEditable(false);
            nVBCST.setForeground(new java.awt.Color(102,102,102));
                VBCST.setEditable(false);
            nPICMSST.setForeground(new java.awt.Color(102,102,102));
                PICMSST.setEditable(false);
            nPDif.setForeground(new java.awt.Color(102,102,102));
                PDif.setEditable(false);
            nVICMSDif.setForeground(new java.awt.Color(102,102,102));
                VICMSDif.setEditable(false);
            nVICMS.setForeground(new java.awt.Color(102,102,102));
                VICMS.setEditable(false);
           nPCredSN.setForeground(new java.awt.Color(102,102,102));
                PCredSN.setEditable(false);
            nVCredICMSSN.setForeground(new java.awt.Color(102,102,102));
                VCredICMSSN.setEditable(false);
           }
           if(Jcst.getText().substring(1,3).equals("51")){
           
                //p.getBCICMS()
                nBCICMS.setForeground(Color.BLACK);
                BCICMS.setEnabled(true);
                //p.getPRedBC()
                nPRedBC.setForeground(Color.BLACK);
                PRedBC.setEditable(true);
                //p.getPDif()
                nPDif.setForeground(Color.BLACK);
                PDif.setEditable(true);
                //p.getVICMSDif()
                nVICMSDif.setForeground(Color.BLACK);
                VICMSDif.setEditable(true);
                //p.getVICMS()
                nVICMS.setForeground(Color.BLACK);
                VICMS.setEditable(true);
            
            //Sem uso
            nBCICMSST.setForeground(new java.awt.Color(102,102,102));
                BCICMSST.setEnabled(false);
            nPMVAST.setForeground(new java.awt.Color(102,102,102));
                PMVAST.setEditable(false);
            nPRedBCST.setForeground(new java.awt.Color(102,102,102));
                PRedBCST.setEditable(false);
            nVICMSST.setForeground(new java.awt.Color(102,102,102));
                VICMSST.setEditable(false);
            nVBCST.setForeground(new java.awt.Color(102,102,102));
                VBCST.setEditable(false);
            nPICMSST.setForeground(new java.awt.Color(102,102,102));
                PICMSST.setEditable(false);
            nPCredSN.setForeground(new java.awt.Color(102,102,102));
                PCredSN.setEditable(false);
            nVCredICMSSN.setForeground(new java.awt.Color(102,102,102));
                VCredICMSSN.setEditable(false);
           
           }
           if(Jcst.getText().substring(1,3).equals("60")){
           
            nVICMSST.setForeground(Color.BLACK);
               VICMSST.setEditable(true);
            nVBCST.setForeground(Color.BLACK);
                VBCST.setEditable(true);
            nPICMSST.setForeground(Color.BLACK);
                PICMSST.setEditable(true);
               
            //Sem uso
             nBCICMS.setForeground(new java.awt.Color(102,102,102));
                BCICMS.setEnabled(false);
            nPRedBC.setForeground(new java.awt.Color(102,102,102));
                PRedBC.setEditable(false);
            nBCICMSST.setForeground(new java.awt.Color(102,102,102));
                BCICMSST.setEnabled(false);
            nPMVAST.setForeground(new java.awt.Color(102,102,102));
                PMVAST.setEditable(false);
            nPRedBCST.setForeground(new java.awt.Color(102,102,102));
                PRedBCST.setEditable(false);
            
            nPDif.setForeground(new java.awt.Color(102,102,102));
                PDif.setEditable(false);
            nVICMSDif.setForeground(new java.awt.Color(102,102,102));
                VICMSDif.setEditable(false);
            nVICMS.setForeground(new java.awt.Color(102,102,102));
                VICMS.setEditable(false);
            nPCredSN.setForeground(new java.awt.Color(102,102,102));
                PCredSN.setEditable(false);
            nVCredICMSSN.setForeground(new java.awt.Color(102,102,102));
                VCredICMSSN.setEditable(false);
           
           }
           if(Jcst.getText().substring(1,3).equals("70")){
               
           
            //p.getBCICMS()   
           nBCICMS.setForeground(Color.BLACK);
                BCICMS.setEnabled(true);
            //p.getPRedBC()
            nPRedBC.setForeground(Color.BLACK);
                PRedBC.setEditable(true);
            //p.getBCICMSST()
            nBCICMSST.setForeground(Color.BLACK);
                BCICMSST.setEnabled(true);
            //p.getPMVAST()
            nPMVAST.setForeground(Color.BLACK);
                PMVAST.setEditable(true);
            //p.getVBCST()
            nPRedBCST.setForeground(Color.BLACK);
                PRedBCST.setEditable(true);
            //p.getVICMSST()
            nVICMSST.setForeground(Color.BLACK);
                VICMSST.setEditable(true);
            //p.getPICMSST()                
            nPICMSST.setForeground(Color.BLACK);
                PICMSST.setEditable(true);
                
            //Sem uso
            nVBCST.setForeground(new java.awt.Color(102,102,102));
                VBCST.setEditable(false);
            nPDif.setForeground(new java.awt.Color(102,102,102));
                PDif.setEditable(false);
            nVICMSDif.setForeground(new java.awt.Color(102,102,102));
                VICMSDif.setEditable(false);
            nVICMS.setForeground(new java.awt.Color(102,102,102));
                VICMS.setEditable(false);
            nPCredSN.setForeground(new java.awt.Color(102,102,102));
                PCredSN.setEditable(false);
            nVCredICMSSN.setForeground(new java.awt.Color(102,102,102));
                VCredICMSSN.setEditable(false);
           
           }
           if(Jcst.getText().substring(1,3).equals("90")){
           
            //p.getBCICMS()
               nBCICMS.setForeground(Color.BLACK);
                BCICMS.setEnabled(true);
            //p.getPRedBC()
            nPRedBC.setForeground(Color.BLACK);
                PRedBC.setEditable(true);
            //p.getBCICMSST()
            nBCICMSST.setForeground(Color.BLACK);
                BCICMSST.setEnabled(true);
            //p.getPMVAST()
            nPMVAST.setForeground(Color.BLACK);
                PMVAST.setEditable(true);
            //p.getPRedBCST()
            nPRedBCST.setForeground(Color.BLACK);
                PRedBCST.setEditable(true);
            //p.getVBCST()
            nVBCST.setForeground(Color.BLACK);
                VBCST.setEditable(true);
            //p.getVICMSST()
            nVICMSDif.setForeground(Color.BLACK);
                VICMSDif.setEditable(true);
            //p.getPICMSST()
            nPICMSST.setForeground(Color.BLACK);
                PICMSST.setEditable(true);
            
            //Sem uso
            nVICMSST.setForeground(new java.awt.Color(102,102,102));
                VICMSST.setEditable(false);
            nPDif.setForeground(new java.awt.Color(102,102,102));
                PDif.setEditable(false);
            nVICMS.setForeground(new java.awt.Color(102,102,102));
                VICMS.setEditable(false);
           nPCredSN.setForeground(new java.awt.Color(102,102,102));
                PCredSN.setEditable(false);
            nVCredICMSSN.setForeground(new java.awt.Color(102,102,102));
                VCredICMSSN.setEditable(false);
           }
       }
       
    }
    public void attICMSVtrib(){
    
        try{
            if(Jbase.getText().equals("")==false){
            String ali = String.valueOf(Aliquota.getSelectedItem());
        Float aliquota = Float.valueOf(ali.replaceAll("%", ""));
        
        DecimalFormat decimalFormat = new DecimalFormat( "0.00" );
        //decimalFormat.setMinimumFractionDigits(1);
        
       String ICMS = decimalFormat.format(Float.valueOf(Jbase.getText())*aliquota/100);
       
       Float Icms = Float.parseFloat(ICMS.replaceAll(",", "."));
       Float qTri = Float.parseFloat(QuantTrib.getText());
       Float ValorUnTrib = Float.valueOf(Jbase.getText())/qTri; // divisão do valor do produto pela quantidade tributável
        ValorTribUnidadeTributaria.setText(decimalFormat.format(ValorUnTrib));
        
        Jicms.setText(ICMS.replaceAll(",", "."));
            }
        }catch(NumberFormatException e){
        }
    
    }
    public void minimo(){
        float PIS;
        float ICMS;
        float COFINS;
        float PUC;
        DecimalFormat decimalFormat = new DecimalFormat( "000000.00" );
        if(preço.getText().equals("")==false){
        PUC =  Float.parseFloat(preço.getText().replaceAll(",", "."));}else{PUC=0;}
        if(Jicms.getText().equals("")==false){
        ICMS = Float.parseFloat(Jicms.getText().replaceAll(",", "."));}else{ICMS=0;}
        if(VPIS.getText().equals("")==false){
        PIS = Float.parseFloat(VPIS.getText().replaceAll(",", "."));}else{PIS=0;}
        if(VCOFINS.getText().equals("")==false){
        COFINS = Float.parseFloat(VCOFINS.getText().replaceAll(",", "."));}else{COFINS =0;}
        float CAM = PUC + ICMS + PIS + COFINS;//custo de aquisição de mercadoria:
        float MargemLucro = 0;
        if(ValorVenda.getText().equals("")==false){
            MargemLucro = ((Float.parseFloat(ValorVenda.getText())/CAM)*100)-100;
            jLabel19.setText("ML = "+MargemLucro);
        }
        
        jLabel18.setText("Minimo de: "+CAM+" Para Margem de lucro: 0%");
    }
    
    public void calculoibpt(){
    
        IBPTDAO idao = new IBPTDAO();
        DecimalFormat decimalFormat = new DecimalFormat( "0.00" );
        float valor = 0;
        float Afederali = 0;
        float Afederaln = 0;
        float Aestadual = 0;
        float Amunicipal = 0;
        
        String ncm = Jncm.getText().replace(".", "").replace(" ", "");
        //System.out.print("NCM: "+ncm);
       // if(ncm.length() >= 8){
        //ncm = ncm.substring(1,ncm.length());
        //}
        
        for(IBPT i : idao.read()){
            
            if(i.getCodigo().equals(ncm)){
                //System.err.println("NCM: "+i.getCodigo());
                valor = Float.parseFloat(ValorVenda.getText());
                //System.err.println("Valor:"+valor);
                Afederali = Float.parseFloat(i.getFederali());
                //System.err.println("Aliquota Federal Iportação: "+Afederali);
                Afederaln = Float.parseFloat(i.getFederaln());
                //System.err.println("Aliquota Federal Nacional: "+Afederaln);
                Aestadual = Float.parseFloat(i.getEstadual());
                //System.err.println("Aliquota Estadual: "+Aestadual);
                Amunicipal = Float.parseFloat(i.getMunicipal());
                //System.err.print("Aliquota Municipal: "+Amunicipal);
            
            break; }
        }
        
        float ifederali = (valor*Afederali)/100;
        //System.err.print("Imposto Federal I:"+ifederali);
        float ifederaln = (valor*Afederaln)/100;
        //System.err.println("Imposto Federal N:"+ifederaln);
        float iestadual = (valor*Aestadual)/100;
       // System.err.print("Imposto Estadual: "+iestadual);
        float imunicipal = (valor*Amunicipal)/100;
        //System.err.print("Imposto Municipal: "+imunicipal);
        
        //System.err.println("Origem: "+Jcst.getText().substring(0,1).equals("0"));
        if(Jcst.getText().substring(0,1).equals("0") || Jcst.getText().substring(0,1).equals("3") || Jcst.getText().substring(0,1).equals("4") || Jcst.getText().substring(0,1).equals("5")){
        AFederalN.setText(""+decimalFormat.format(ifederali));
        AFederalI.setText("0.00");
        }else{
        AFederalN.setText(""+decimalFormat.format(ifederali));
        AFederalI.setText(""+decimalFormat.format(ifederaln));
        }
        AEstadual.setText(""+decimalFormat.format(iestadual));
        AMunicipal.setText(""+decimalFormat.format(imunicipal));
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelEstoque = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel32 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel27 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        descricao = new javax.swing.JTextField();
        TipoProduto = new javax.swing.JComboBox<>();
        preço = new javax.swing.JTextField();
        quan = new javax.swing.JTextField();
        BCProduto = new javax.swing.JButton();
        BAProduto = new javax.swing.JButton();
        BUProduto = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel51 = new javax.swing.JLabel();
        UnidadeComercial = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        cod1 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        Jncm = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        Jcst = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        Jcfop = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        Jicms = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        Jbase = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        Aliquota = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        cean = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        Jcest = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        UnidadeTributavel = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        GTNItribu = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        QuantTrib = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ValorTribUnidadeTributaria = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        cod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        nBCICMS = new javax.swing.JLabel();
        BCICMS = new javax.swing.JComboBox<>();
        nBCICMSST = new javax.swing.JLabel();
        BCICMSST = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        nPMVAST = new javax.swing.JLabel();
        PMVAST = new javax.swing.JTextField();
        nPRedBCST = new javax.swing.JLabel();
        PRedBCST = new javax.swing.JTextField();
        nVBCST = new javax.swing.JLabel();
        VBCST = new javax.swing.JTextField();
        nPICMSST = new javax.swing.JLabel();
        PICMSST = new javax.swing.JTextField();
        nPRedBC = new javax.swing.JLabel();
        PRedBC = new javax.swing.JTextField();
        nVICMSST = new javax.swing.JLabel();
        VICMSST = new javax.swing.JComboBox<>();
        nPDif = new javax.swing.JLabel();
        nVICMSDif = new javax.swing.JLabel();
        nVICMS = new javax.swing.JLabel();
        VICMSDif = new javax.swing.JTextField();
        VICMS = new javax.swing.JTextField();
        PDif = new javax.swing.JComboBox<>();
        nPCredSN = new javax.swing.JLabel();
        PCredSN = new javax.swing.JTextField();
        VCredICMSSN = new javax.swing.JTextField();
        nVCredICMSSN = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        CSTPIS = new javax.swing.JComboBox<>();
        VPIS = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        VBCPIS = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        PPIS = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        CSTCOFINS = new javax.swing.JComboBox<>();
        VCOFINS = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        VBCCOFINS = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        PCOFINS = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        ValorVenda = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        AMunicipal = new javax.swing.JTextField();
        AFederalN = new javax.swing.JTextField();
        AFederalI = new javax.swing.JTextField();
        AEstadual = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jPanel61 = new javax.swing.JPanel();
        jTabbedPane15 = new javax.swing.JTabbedPane();
        jPanel64 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        TSemTipo = new javax.swing.JTable();
        jPanel67 = new javax.swing.JPanel();
        THortifruit = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jPanel68 = new javax.swing.JPanel();
        TLimpeza = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jPanel71 = new javax.swing.JPanel();
        THLimpeza = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        jPanel76 = new javax.swing.JPanel();
        TPerfumaria = new javax.swing.JScrollPane();
        jTable14 = new javax.swing.JTable();
        jPanel77 = new javax.swing.JPanel();
        TUtilidadesDomesticas = new javax.swing.JScrollPane();
        jTable15 = new javax.swing.JTable();
        jPanel78 = new javax.swing.JPanel();
        TCampoLazer = new javax.swing.JScrollPane();
        jTable16 = new javax.swing.JTable();
        jPanel79 = new javax.swing.JPanel();
        TPadaria = new javax.swing.JScrollPane();
        jTable17 = new javax.swing.JTable();
        jPanel80 = new javax.swing.JPanel();
        TAçougue = new javax.swing.JScrollPane();
        jTable18 = new javax.swing.JTable();
        jPanel81 = new javax.swing.JPanel();
        TEnlatados = new javax.swing.JScrollPane();
        jTable19 = new javax.swing.JTable();
        jPanel82 = new javax.swing.JPanel();
        TDoces = new javax.swing.JScrollPane();
        jTable20 = new javax.swing.JTable();
        jPanel83 = new javax.swing.JPanel();
        TMassas = new javax.swing.JScrollPane();
        jTable21 = new javax.swing.JTable();
        jPanel84 = new javax.swing.JPanel();
        TCereais = new javax.swing.JScrollPane();
        jTable22 = new javax.swing.JTable();
        jPanel85 = new javax.swing.JPanel();
        TOutros = new javax.swing.JScrollPane();
        jTable23 = new javax.swing.JTable();
        jPanel86 = new javax.swing.JPanel();
        TOutros1 = new javax.swing.JScrollPane();
        jTable24 = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        TabelaEstoqueEspecifico = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        JTCodigoEspecifico = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane30 = new javax.swing.JScrollPane();
        jPanel31 = new javax.swing.JPanel();
        jPanel111 = new javax.swing.JPanel();
        jLabel131 = new javax.swing.JLabel();
        jPanel112 = new javax.swing.JPanel();
        jLabel134 = new javax.swing.JLabel();
        jPanel113 = new javax.swing.JPanel();
        jLabel135 = new javax.swing.JLabel();
        jPanel114 = new javax.swing.JPanel();
        jLabel136 = new javax.swing.JLabel();
        jPanel115 = new javax.swing.JPanel();
        jLabel137 = new javax.swing.JLabel();
        jPanel116 = new javax.swing.JPanel();
        jLabel138 = new javax.swing.JLabel();
        jPanel117 = new javax.swing.JPanel();
        jLabel139 = new javax.swing.JLabel();
        jPanel118 = new javax.swing.JPanel();
        jLabel140 = new javax.swing.JLabel();
        jScrollPane31 = new javax.swing.JScrollPane();
        jTable27 = new javax.swing.JTable();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        jTextField40 = new javax.swing.JTextField();
        jTextField41 = new javax.swing.JTextField();
        jDateChooser9 = new com.toedter.calendar.JDateChooser();
        jDateChooser10 = new com.toedter.calendar.JDateChooser();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel149 = new javax.swing.JLabel();
        jTextField42 = new javax.swing.JTextField();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jScrollPane36 = new javax.swing.JScrollPane();
        jTable28 = new javax.swing.JTable();
        jScrollPane38 = new javax.swing.JScrollPane();
        jTable30 = new javax.swing.JTable();
        jLabel150 = new javax.swing.JLabel();
        jTextField43 = new javax.swing.JTextField();
        jLabel151 = new javax.swing.JLabel();
        jTextField44 = new javax.swing.JTextField();
        jLabel152 = new javax.swing.JLabel();
        jTextField45 = new javax.swing.JTextField();
        jLabel153 = new javax.swing.JLabel();
        jDateChooser11 = new com.toedter.calendar.JDateChooser();
        jLabel154 = new javax.swing.JLabel();
        jDateChooser12 = new com.toedter.calendar.JDateChooser();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        jScrollPane39 = new javax.swing.JScrollPane();
        jTable31 = new javax.swing.JTable();
        jTextField34 = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jPanel28 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel112 = new javax.swing.JLabel();
        jScrollPane28 = new javax.swing.JScrollPane();
        jTable25 = new javax.swing.JTable();

        setAutoscrolls(true);

        PainelEstoque.setBackground(new java.awt.Color(255, 255, 255));
        PainelEstoque.setToolTipText("");
        PainelEstoque.setAutoscrolls(true);

        jTabbedPane5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setToolTipText("");

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Qtde. Comercial");
        jPanel27.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 305, -1, 27));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("Un. Tributária");
        jPanel27.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 105, 126, 27));

        descricao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        descricao.setForeground(new java.awt.Color(51, 51, 51));
        descricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descricaoActionPerformed(evt);
            }
        });
        jPanel27.add(descricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 5, 560, 27));

        TipoProduto.setEditable(true);
        TipoProduto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TipoProduto.setForeground(new java.awt.Color(51, 51, 51));
        TipoProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sem tipo", "Hortifruti", "Higiene e limpeza do lar", "Higiene e limpeza pessoal", "Perfumaria", "Utilidades domésticas", "Campo e lazer", "Padaria", "Açougue", "Enlatados", "Doces", "Massas e biscoitos", "Cereais", "Outros" }));
        jPanel27.add(TipoProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 372, 169, 27));

        preço.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        preço.setForeground(new java.awt.Color(51, 51, 51));
        preço.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                preçoFocusLost(evt);
            }
        });
        preço.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preçoActionPerformed(evt);
            }
        });
        preço.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                preçoKeyReleased(evt);
            }
        });
        jPanel27.add(preço, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 201, 170, 27));

        quan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        quan.setForeground(new java.awt.Color(51, 51, 51));
        jPanel27.add(quan, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 306, 170, 27));

        BCProduto.setBackground(new java.awt.Color(150, 0, 20));
        BCProduto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BCProduto.setForeground(new java.awt.Color(255, 255, 255));
        BCProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/add-button-with-plus-symbol-in-a-black-circle.png"))); // NOI18N
        BCProduto.setText("Cadastrar");
        BCProduto.setToolTipText("Preencha os campos e clique para cadastrar");
        BCProduto.setBorderPainted(false);
        BCProduto.setContentAreaFilled(false);
        BCProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BCProduto.setOpaque(true);
        BCProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BCProdutoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BCProdutoMouseExited(evt);
            }
        });
        BCProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCProdutoActionPerformed(evt);
            }
        });
        jPanel27.add(BCProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 670, 120, 32));

        BAProduto.setBackground(new java.awt.Color(150, 0, 20));
        BAProduto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BAProduto.setForeground(new java.awt.Color(255, 255, 255));
        BAProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/icon.png"))); // NOI18N
        BAProduto.setText("    Apagar");
        BAProduto.setToolTipText("Forneça o código e clique para apagar profuto");
        BAProduto.setBorderPainted(false);
        BAProduto.setContentAreaFilled(false);
        BAProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BAProduto.setOpaque(true);
        BAProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BAProdutoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BAProdutoMouseExited(evt);
            }
        });
        BAProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAProdutoActionPerformed(evt);
            }
        });
        jPanel27.add(BAProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 670, 120, 32));

        BUProduto.setBackground(new java.awt.Color(150, 0, 20));
        BUProduto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BUProduto.setForeground(new java.awt.Color(255, 255, 255));
        BUProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/pencil.png"))); // NOI18N
        BUProduto.setText(" Atualizar");
        BUProduto.setToolTipText("Preencha todos os campos e clique para atualizar");
        BUProduto.setBorderPainted(false);
        BUProduto.setContentAreaFilled(false);
        BUProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BUProduto.setOpaque(true);
        BUProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BUProdutoMouseEntered(evt);
            }
        });
        BUProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUProdutoActionPerformed(evt);
            }
        });
        jPanel27.add(BUProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 670, 120, 32));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Código produto");
        jLabel40.setToolTipText("");
        jPanel27.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 102, 122, 28));

        jTable8.setAutoCreateRowSorter(true);
        jTable8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Tipo", "Unidade", "Preço", "Quantidade", "Validade", "DataEntrada", "Loja", "NCM", "CST", "CFOP", "BASEICMS", "ICMS", "Aliquota ICMS", "Cest"
            }
        ));
        jTable8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable8MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable8);
        if (jTable8.getColumnModel().getColumnCount() > 0) {
            jTable8.getColumnModel().getColumn(0).setPreferredWidth(160);
            jTable8.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable8.getColumnModel().getColumn(6).setPreferredWidth(120);
            jTable8.getColumnModel().getColumn(7).setPreferredWidth(120);
        }

        jPanel27.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 710, 1050, 430));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setText("Valor Venda");
        jPanel27.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 133, 27));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("Validade");
        jPanel27.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 273, 125, 27));

        jDateChooser2.setForeground(new java.awt.Color(51, 51, 51));
        jPanel27.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 273, 121, 27));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setText("Un. Comercial");
        jPanel27.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 343, 125, -1));

        UnidadeComercial.setEditable(true);
        UnidadeComercial.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        UnidadeComercial.setForeground(new java.awt.Color(51, 51, 51));
        UnidadeComercial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UN", "CX", "KG", "Lt", "TON", "DUZIA", "1000Un", "QUILAT", "METRO", "M3", "PARES", "M2" }));
        UnidadeComercial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UnidadeComercial.setFocusable(false);
        jPanel27.add(UnidadeComercial, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 339, 170, 27));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setText("Loja");
        jLabel52.setToolTipText("");
        jPanel27.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 130, 27));

        cod1.setEditable(false);
        cod1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cod1.setForeground(new java.awt.Color(51, 51, 51));
        cod1.setText("Sede");
        cod1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cod1ActionPerformed(evt);
            }
        });
        jPanel27.add(cod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 168, 170, 27));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setText("Data Entrada");
        jPanel27.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 244, 125, -1));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setText("NCM");
        jPanel27.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 405, -1, 19));

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(51, 51, 51));
        jTextField1.setText("22/12/2018 - 18:56:39");
        jPanel27.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 240, 170, 27));

        Jncm.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Jncm.setForeground(new java.awt.Color(51, 51, 51));
        Jncm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JncmKeyReleased(evt);
            }
        });
        jPanel27.add(Jncm, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 405, 105, 27));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel55.setText("CST/CSOSN Saida");
        jPanel27.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 71, -1, 27));

        Jcst.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Jcst.setForeground(new java.awt.Color(51, 51, 51));
        Jcst.setText("0102");
        Jcst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JcstMouseEntered(evt);
            }
        });
        Jcst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcstActionPerformed(evt);
            }
        });
        Jcst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JcstKeyReleased(evt);
            }
        });
        jPanel27.add(Jcst, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 72, 60, 27));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setText("CFOP de Saida");
        jPanel27.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 453, -1, 28));

        Jcfop.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Jcfop.setForeground(new java.awt.Color(51, 51, 51));
        Jcfop.setText("5.102");
        jPanel27.add(Jcfop, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 453, 60, 27));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setText("ICMS");
        jPanel27.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 355, -1, 27));

        Jicms.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Jicms.setForeground(new java.awt.Color(51, 51, 51));
        Jicms.setText("0");
        Jicms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JicmsMouseEntered(evt);
            }
        });
        Jicms.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JicmsKeyReleased(evt);
            }
        });
        jPanel27.add(Jicms, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 361, 60, 27));

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setText("Base ICMS Próprio");
        jPanel27.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 295, -1, -1));

        Jbase.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Jbase.setForeground(new java.awt.Color(51, 51, 51));
        Jbase.setToolTipText("Base de Cálculo = (Valor mercadoria + frete + IPI + outras despesas) x MVA");
        jPanel27.add(Jbase, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 295, 60, 27));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("Alíquota ICMS");
        jPanel27.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 322, -1, 27));

        Aliquota.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Aliquota.setForeground(new java.awt.Color(51, 51, 51));
        Aliquota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0.00", "1.00", "2.00", "3.00", "4.00", "5.00", "6.00", "7.00", "8.00", "9.00", "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00", "18.00", "19.00", "20.00", "21.00", "22.00", "23.00", "24.00", "25.00", "26.00", "27.00", "28.00", "29.00", "30.00", "31.00", "32.00", "33.00", "34.00", "35.00", "36.00", "37.00", "38.00", "39.00", "40.00", "41.00", "42.00", "43.00", "44.00", "45.00", "46.00", "47.00", "48.00", "49.00", "50.00", "51.00", "52.00", "53.00", "54.00", "55.00", "56.00", "57.00", "58.00", "59.00", "60.00", "61.00", "62.00", "63.00", "64.00", "65.00", "66.00", "67.00", "68.00", "69.00", "70.00", "71.00", "72.00", "73.00", "74.00", "75.00", "76.00", "77.00", "78.00", "79.00", "80.00", "81.00", "82.00", "83.00", "84.00", "85.00", "86.00", "87.00", "88.00", "89.00", "90.00", "91.00", "92.00", "93.00", "94.00", "95.00", "96.00", "97.00", "98.00", "99.00", "100.00" }));
        Aliquota.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AliquotaItemStateChanged(evt);
            }
        });
        Aliquota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AliquotaMouseEntered(evt);
            }
        });
        Aliquota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AliquotaActionPerformed(evt);
            }
        });
        jPanel27.add(Aliquota, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 328, 60, 27));

        jButton1.setBackground(new java.awt.Color(150, 0, 20));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("?");
        jButton1.setToolTipText("Consultar NCM");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setDefaultCapable(false);
        jButton1.setFocusPainted(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 406, 42, 27));

        jButton2.setBackground(new java.awt.Color(150, 0, 20));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("?");
        jButton2.setToolTipText("Consultar CFOP");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setDefaultCapable(false);
        jButton2.setFocusPainted(false);
        jButton2.setOpaque(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 453, 42, 27));

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Tabela de CFOPs");
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setOpaque(true);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 670, 143, -1));

        jButton4.setBackground(new java.awt.Color(51, 51, 51));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Tabela de CST");
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setOpaque(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 670, 143, -1));

        jButton5.setBackground(new java.awt.Color(51, 51, 51));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Tabela de ICMS");
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setOpaque(true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 670, 143, -1));

        cean.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cean.setForeground(new java.awt.Color(51, 51, 51));
        cean.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ceanKeyReleased(evt);
            }
        });
        jPanel27.add(cean, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 70, 170, 27));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setText("CEST");
        jPanel27.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 241, 84, 28));

        Jcest.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Jcest.setForeground(new java.awt.Color(51, 51, 51));
        Jcest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JcestMouseEntered(evt);
            }
        });
        jPanel27.add(Jcest, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 241, 105, 27));

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setText("Tipo");
        jPanel27.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 376, 130, -1));

        UnidadeTributavel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        UnidadeTributavel.setForeground(new java.awt.Color(51, 51, 51));
        UnidadeTributavel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                UnidadeTributavelMouseEntered(evt);
            }
        });
        jPanel27.add(UnidadeTributavel, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 106, 61, 27));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Código Tributavel");
        jPanel27.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 174, -1, 27));

        GTNItribu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        GTNItribu.setForeground(new java.awt.Color(51, 51, 51));
        jPanel27.add(GTNItribu, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 174, 153, 27));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Qtde. Tributavel");
        jPanel27.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 139, 122, 27));

        QuantTrib.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        QuantTrib.setForeground(new java.awt.Color(51, 51, 51));
        QuantTrib.setText("1");
        QuantTrib.setToolTipText("Quantas Unidades Tributaveis comporta por Unidade Comercial?\nPor Exemplo: 1 caixa de Leite contem 12 unidade tributaveis");
        QuantTrib.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuantTribMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QuantTribMouseEntered(evt);
            }
        });
        QuantTrib.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                QuantTribKeyReleased(evt);
            }
        });
        jPanel27.add(QuantTrib, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 140, 60, 27));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Valor Tributavel");
        jPanel27.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 207, 122, 27));

        ValorTribUnidadeTributaria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ValorTribUnidadeTributaria.setForeground(new java.awt.Color(51, 51, 51));
        ValorTribUnidadeTributaria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ValorTribUnidadeTributariaMouseEntered(evt);
            }
        });
        jPanel27.add(ValorTribUnidadeTributaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 208, 61, 27));

        jButton6.setBackground(new java.awt.Color(150, 0, 20));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("?");
        jButton6.setToolTipText("Consultar CFOP");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setDefaultCapable(false);
        jButton6.setFocusPainted(false);
        jButton6.setOpaque(true);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 242, 42, 27));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("Código Barras");
        jLabel41.setToolTipText("");
        jPanel27.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 125, 28));

        cod.setEditable(false);
        cod.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cod.setForeground(new java.awt.Color(51, 51, 51));
        jPanel27.add(cod, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 104, 170, 27));

        jLabel4.setForeground(new java.awt.Color(150, 0, 20));
        jLabel4.setText("Obrigatorio para produto descrito na tabela do convênio ICMS 92/15 ");
        jPanel27.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 275, -1, -1));

        jButton7.setBackground(new java.awt.Color(150, 0, 20));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Gerar");
        jButton7.setToolTipText("Consultar CFOP");
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setDefaultCapable(false);
        jButton7.setFocusPainted(false);
        jButton7.setOpaque(true);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 134, -1, 27));

        jButton8.setBackground(new java.awt.Color(150, 0, 20));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Limpar Campos");
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.setDefaultCapable(false);
        jButton8.setFocusPainted(false);
        jButton8.setOpaque(true);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 670, 140, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(150, 0, 20));
        jLabel5.setText("Apenas cobrindo ICMS, PIS e Cofins...");
        jPanel27.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 220, -1));

        nBCICMS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nBCICMS.setText("Modalidade de determinação da BC do ICMS");
        jPanel27.add(nBCICMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 390, -1, 27));

        BCICMS.setEditable(true);
        BCICMS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BCICMS.setForeground(new java.awt.Color(51, 51, 51));
        BCICMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione Quando for necessário", "0=Margem Valor Agregado (%)", "1=Pauta (Valor)", "2=Preço Tabelado Máx. (valor)", "3=Valor da operação" }));
        jPanel27.add(BCICMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 420, 230, 27));

        nBCICMSST.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nBCICMSST.setForeground(new java.awt.Color(102, 102, 102));
        nBCICMSST.setText("Modalidade de determinação da BC do ICMS ST");
        jPanel27.add(nBCICMSST, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 105, -1, 27));

        BCICMSST.setEditable(true);
        BCICMSST.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BCICMSST.setForeground(new java.awt.Color(51, 51, 51));
        BCICMSST.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione Quando for necessário", "0=Preço tabelado ou máximo sugerido", "1=Lista Negativa (valor)", "2=Lista Positiva (valor)", "3=Lista Neutra (valor)", "4=Margem Valor Agregado (%)", "5=Pauta (valor)" }));
        BCICMSST.setEnabled(false);
        jPanel27.add(BCICMSST, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 139, 230, 27));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setText("Descrição");
        jLabel30.setToolTipText("");
        jPanel27.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 84, 27));

        jButton9.setText("Buscar Prodtuos");
        jPanel27.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 10, -1, -1));

        nPMVAST.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nPMVAST.setForeground(new java.awt.Color(102, 102, 102));
        nPMVAST.setText("Percentual MVA do ICMS ST (%)");
        jPanel27.add(nPMVAST, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 174, -1, 27));

        PMVAST.setEditable(false);
        PMVAST.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PMVAST.setForeground(new java.awt.Color(51, 51, 51));
        jPanel27.add(PMVAST, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 174, 60, 27));

        nPRedBCST.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nPRedBCST.setForeground(new java.awt.Color(102, 102, 102));
        nPRedBCST.setText("Percentual Redução BC do ICMS ST(%)");
        jPanel27.add(nPRedBCST, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 208, -1, 27));

        PRedBCST.setEditable(false);
        PRedBCST.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PRedBCST.setForeground(new java.awt.Color(51, 51, 51));
        jPanel27.add(PRedBCST, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 208, 60, 27));

        nVBCST.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nVBCST.setForeground(new java.awt.Color(102, 102, 102));
        nVBCST.setText("Base do ICMS ST");
        jPanel27.add(nVBCST, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 273, -1, 27));

        VBCST.setEditable(false);
        VBCST.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        VBCST.setForeground(new java.awt.Color(51, 51, 51));
        VBCST.setToolTipText("Valor mercadoria + frete + IPI + outras despesas) x MVA");
        jPanel27.add(VBCST, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 273, 60, 27));

        nPICMSST.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nPICMSST.setForeground(new java.awt.Color(102, 102, 102));
        nPICMSST.setText("ICMS ST");
        jPanel27.add(nPICMSST, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 305, 72, 27));

        PICMSST.setEditable(false);
        PICMSST.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PICMSST.setForeground(new java.awt.Color(51, 51, 51));
        PICMSST.setToolTipText("(BASE ICMS ST x ALIQUOTA ICMS) - ICMS ");
        jPanel27.add(PICMSST, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 305, 60, 27));

        nPRedBC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nPRedBC.setForeground(new java.awt.Color(102, 102, 102));
        nPRedBC.setText("Percentual Redução BC do ICMS (%)");
        jPanel27.add(nPRedBC, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 71, -1, 27));

        PRedBC.setEditable(false);
        PRedBC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PRedBC.setForeground(new java.awt.Color(51, 51, 51));
        jPanel27.add(PRedBC, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 71, 60, 27));

        nVICMSST.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nVICMSST.setForeground(new java.awt.Color(102, 102, 102));
        nVICMSST.setText("Alíquota ICMS ST");
        jPanel27.add(nVICMSST, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 241, -1, 27));

        VICMSST.setEditable(true);
        VICMSST.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        VICMSST.setForeground(new java.awt.Color(51, 51, 51));
        VICMSST.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0.00", "1.00", "2.00", "3.00", "4.00", "5.00", "6.00", "7.00", "8.00", "9.00", "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00", "18.00", "19.00", "20.00", "21.00", "22.00", "23.00", "24.00", "25.00", "26.00", "27.00", "28.00", "29.00", "30.00", "31.00", "32.00", "33.00", "34.00", "35.00", "36.00", "37.00", "38.00", "39.00", "40.00", "41.00", "42.00", "43.00", "44.00", "45.00", "46.00", "47.00", "48.00", "49.00", "50.00", "51.00", "52.00", "53.00", "54.00", "55.00", "56.00", "57.00", "58.00", "59.00", "60.00", "61.00", "62.00", "63.00", "64.00", "65.00", "66.00", "67.00", "68.00", "69.00", "70.00", "71.00", "72.00", "73.00", "74.00", "75.00", "76.00", "77.00", "78.00", "79.00", "80.00", "81.00", "82.00", "83.00", "84.00", "85.00", "86.00", "87.00", "88.00", "89.00", "90.00", "91.00", "92.00", "93.00", "94.00", "95.00", "96.00", "97.00", "98.00", "99.00", "100.00" }));
        VICMSST.setEnabled(false);
        VICMSST.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                VICMSSTItemStateChanged(evt);
            }
        });
        VICMSST.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                VICMSSTMouseEntered(evt);
            }
        });
        jPanel27.add(VICMSST, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 241, 60, 27));

        nPDif.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nPDif.setForeground(new java.awt.Color(102, 102, 102));
        nPDif.setText("Percentual do diferimento");
        jPanel27.add(nPDif, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 337, -1, 27));

        nVICMSDif.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nVICMSDif.setForeground(new java.awt.Color(102, 102, 102));
        nVICMSDif.setText("Valor do ICMS diferido");
        jPanel27.add(nVICMSDif, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 370, -1, 27));

        nVICMS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nVICMS.setForeground(new java.awt.Color(102, 102, 102));
        nVICMS.setText("Valor do ICMS");
        jPanel27.add(nVICMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 402, -1, 27));

        VICMSDif.setEditable(false);
        jPanel27.add(VICMSDif, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 370, 60, 27));

        VICMS.setEditable(false);
        jPanel27.add(VICMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 402, 60, 27));

        PDif.setEditable(true);
        PDif.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PDif.setForeground(new java.awt.Color(51, 51, 51));
        PDif.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0.00", "1.00", "2.00", "3.00", "4.00", "5.00", "6.00", "7.00", "8.00", "9.00", "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00", "18.00", "19.00", "20.00", "21.00", "22.00", "23.00", "24.00", "25.00", "26.00", "27.00", "28.00", "29.00", "30.00", "31.00", "32.00", "33.00", "34.00", "35.00", "36.00", "37.00", "38.00", "39.00", "40.00", "41.00", "42.00", "43.00", "44.00", "45.00", "46.00", "47.00", "48.00", "49.00", "50.00", "51.00", "52.00", "53.00", "54.00", "55.00", "56.00", "57.00", "58.00", "59.00", "60.00", "61.00", "62.00", "63.00", "64.00", "65.00", "66.00", "67.00", "68.00", "69.00", "70.00", "71.00", "72.00", "73.00", "74.00", "75.00", "76.00", "77.00", "78.00", "79.00", "80.00", "81.00", "82.00", "83.00", "84.00", "85.00", "86.00", "87.00", "88.00", "89.00", "90.00", "91.00", "92.00", "93.00", "94.00", "95.00", "96.00", "97.00", "98.00", "99.00", "100.00" }));
        PDif.setEnabled(false);
        PDif.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                PDifItemStateChanged(evt);
            }
        });
        PDif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PDifMouseEntered(evt);
            }
        });
        jPanel27.add(PDif, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 337, 60, 27));

        nPCredSN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nPCredSN.setForeground(new java.awt.Color(102, 102, 102));
        nPCredSN.setText("Alíquota Cálc. Crédito");
        jPanel27.add(nPCredSN, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 434, 160, 27));

        PCredSN.setEditable(false);
        PCredSN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PCredSN.setForeground(new java.awt.Color(51, 51, 51));
        jPanel27.add(PCredSN, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 434, 60, 27));

        VCredICMSSN.setEditable(false);
        VCredICMSSN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        VCredICMSSN.setForeground(new java.awt.Color(51, 51, 51));
        jPanel27.add(VCredICMSSN, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 466, 60, 27));

        nVCredICMSSN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nVCredICMSSN.setForeground(new java.awt.Color(102, 102, 102));
        nVCredICMSSN.setText("ICMS Aproveitavel");
        jPanel27.add(nVCredICMSSN, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 466, 140, 27));

        jLabel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações Fiscais do Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(120, 0, 20))); // NOI18N
        jPanel27.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 40, 730, 460));

        jLabel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(120, 0, 20))); // NOI18N
        jPanel27.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 40, 330, 460));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Valor do PIS");
        jPanel27.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 626, -1, 27));

        CSTPIS.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        CSTPIS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01 – Operação Tributável com Alíquota Básica", "02 – Operação Tributável com Alíquota por Unidade de Medida de Produto", "03 – Operação Tributável com Alíquota por Unidade de Medida de Produto", "04 – Operação Tributável Monofásica – Revenda a Alíquota Zero", "05 – Operação Tributável por Substituição Tributária", "06 – Operação Tributável a Alíquota Zero", "07 – Operação Isenta da Contribuição", "08 – Operação sem Incidência da Contribuição", "09 – Operação com Suspensão da Contribuição", "49 – Outras Operações de Saída", "99 –  Outras Operações" }));
        jPanel27.add(CSTPIS, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 530, 190, 27));

        VPIS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        VPIS.setForeground(new java.awt.Color(51, 51, 51));
        VPIS.setText("0");
        VPIS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                VPISKeyReleased(evt);
            }
        });
        jPanel27.add(VPIS, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 626, 102, 27));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Tributação municipal");
        jPanel27.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 626, -1, 27));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Base do PIS");
        jPanel27.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 562, -1, 27));

        VBCPIS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        VBCPIS.setForeground(new java.awt.Color(51, 51, 51));
        jPanel27.add(VBCPIS, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 562, 102, 27));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Alíquota do PIS");
        jPanel27.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 594, -1, 27));

        PPIS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PPIS.setForeground(new java.awt.Color(51, 51, 51));
        PPIS.setText("00.00");
        jPanel27.add(PPIS, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 594, 102, 27));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Valor do COFINS");
        jPanel27.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 620, -1, 27));

        CSTCOFINS.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        CSTCOFINS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01 – Operação Tributável com Alíquota Básica", "02 – Operação Tributável com Alíquota por Unidade de Medida de Produto", "03 – Operação Tributável com Alíquota por Unidade de Medida de Produto", "04 – Operação Tributável Monofásica – Revenda a Alíquota Zero", "05 – Operação Tributável por Substituição Tributária", "06 – Operação Tributável a Alíquota Zero", "07 – Operação Isenta da Contribuição", "08 – Operação sem Incidência da Contribuição", "09 – Operação com Suspensão da Contribuição", "49 – Outras Operações de Saída", "99 – Outras Operações" }));
        jPanel27.add(CSTCOFINS, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 530, 190, 27));

        VCOFINS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        VCOFINS.setForeground(new java.awt.Color(51, 51, 51));
        VCOFINS.setText("0");
        VCOFINS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VCOFINSKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                VCOFINSKeyReleased(evt);
            }
        });
        jPanel27.add(VCOFINS, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 620, 102, 27));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("CST do COFINS");
        jPanel27.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 530, -1, 27));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Base do COFINS");
        jPanel27.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 560, -1, 27));

        VBCCOFINS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        VBCCOFINS.setForeground(new java.awt.Color(51, 51, 51));
        jPanel27.add(VBCCOFINS, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 560, 102, 27));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Alíquota do COFINS");
        jPanel27.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 590, -1, 27));

        PCOFINS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PCOFINS.setForeground(new java.awt.Color(51, 51, 51));
        PCOFINS.setText("00.00");
        jPanel27.add(PCOFINS, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 590, 102, 27));

        jLabel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações PIS/COFINS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(120, 0, 20))); // NOI18N
        jPanel27.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 510, 700, 150));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setText("Preço Entrada");
        jPanel27.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 133, 27));

        ValorVenda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ValorVenda.setForeground(new java.awt.Color(51, 51, 51));
        ValorVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ValorVendaKeyReleased(evt);
            }
        });
        jPanel27.add(ValorVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 437, 105, 27));

        jLabel8.setForeground(new java.awt.Color(150, 0, 20));
        jLabel8.setText("Se não houver codigo de barras ");
        jPanel27.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 137, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(150, 0, 20));
        jLabel18.setText("Minimo de:");
        jPanel27.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 320, 20));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(120, 0, 20));
        jLabel19.setText("ML=00.00%");
        jPanel27.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, 70, 20));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("CST do PIS");
        jPanel27.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 530, -1, 27));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Tributação Federal Produtos Nacionais");
        jPanel27.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 530, -1, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Tributação Federal Produtos Importados");
        jPanel27.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 562, -1, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Tributação Estadual");
        jPanel27.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 594, -1, 30));
        jPanel27.add(AMunicipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 626, 60, 27));
        jPanel27.add(AFederalN, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 530, 60, 27));
        jPanel27.add(AFederalI, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 562, 60, 27));
        jPanel27.add(AEstadual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 594, 60, 27));

        jLabel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor aproximado total de tributos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(120, 0, 20))); // NOI18N
        jPanel27.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 510, 360, 150));

        jScrollPane1.setViewportView(jPanel27);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Editar Produtos", jPanel32);

        jPanel61.setBackground(new java.awt.Color(255, 255, 255));

        jPanel64.setBackground(new java.awt.Color(255, 255, 255));

        TSemTipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        jScrollPane12.setViewportView(TSemTipo);

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane15.addTab("Sem Tipo", jPanel64);

        jPanel67.setBackground(new java.awt.Color(255, 255, 255));

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        THortifruit.setViewportView(jTable10);

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(THortifruit, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(THortifruit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane15.addTab("Hortifruit", jPanel67);

        jPanel68.setBackground(new java.awt.Color(255, 255, 255));

        jTable12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        TLimpeza.setViewportView(jTable12);

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TLimpeza, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TLimpeza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane15.addTab("Limpeza", jPanel68);

        jPanel71.setBackground(new java.awt.Color(255, 255, 255));

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        THLimpeza.setViewportView(jTable13);

        javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
        jPanel71.setLayout(jPanel71Layout);
        jPanel71Layout.setHorizontalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(THLimpeza, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel71Layout.setVerticalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(THLimpeza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane15.addTab("Higiene e Limpeza Pessoal", jPanel71);

        jPanel76.setBackground(new java.awt.Color(255, 255, 255));

        jTable14.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        TPerfumaria.setViewportView(jTable14);

        javax.swing.GroupLayout jPanel76Layout = new javax.swing.GroupLayout(jPanel76);
        jPanel76.setLayout(jPanel76Layout);
        jPanel76Layout.setHorizontalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel76Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TPerfumaria, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel76Layout.setVerticalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel76Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TPerfumaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane15.addTab("Perfumaria", jPanel76);

        jPanel77.setBackground(new java.awt.Color(255, 255, 255));

        jTable15.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        TUtilidadesDomesticas.setViewportView(jTable15);

        javax.swing.GroupLayout jPanel77Layout = new javax.swing.GroupLayout(jPanel77);
        jPanel77.setLayout(jPanel77Layout);
        jPanel77Layout.setHorizontalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TUtilidadesDomesticas, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel77Layout.setVerticalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TUtilidadesDomesticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane15.addTab("Utilidades Domesticas", jPanel77);

        jPanel78.setBackground(new java.awt.Color(255, 255, 255));

        jTable16.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        TCampoLazer.setViewportView(jTable16);

        javax.swing.GroupLayout jPanel78Layout = new javax.swing.GroupLayout(jPanel78);
        jPanel78.setLayout(jPanel78Layout);
        jPanel78Layout.setHorizontalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel78Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TCampoLazer, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel78Layout.setVerticalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel78Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TCampoLazer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane15.addTab("Campo e lazer", jPanel78);

        jPanel79.setBackground(new java.awt.Color(255, 255, 255));

        jTable17.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        TPadaria.setViewportView(jTable17);

        javax.swing.GroupLayout jPanel79Layout = new javax.swing.GroupLayout(jPanel79);
        jPanel79.setLayout(jPanel79Layout);
        jPanel79Layout.setHorizontalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel79Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TPadaria, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel79Layout.setVerticalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel79Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TPadaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane15.addTab("Padaria", jPanel79);

        jPanel80.setBackground(new java.awt.Color(255, 255, 255));

        jTable18.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        TAçougue.setViewportView(jTable18);

        javax.swing.GroupLayout jPanel80Layout = new javax.swing.GroupLayout(jPanel80);
        jPanel80.setLayout(jPanel80Layout);
        jPanel80Layout.setHorizontalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TAçougue, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel80Layout.setVerticalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TAçougue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane15.addTab("Açougue", jPanel80);

        jPanel81.setBackground(new java.awt.Color(255, 255, 255));

        jTable19.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        TEnlatados.setViewportView(jTable19);

        javax.swing.GroupLayout jPanel81Layout = new javax.swing.GroupLayout(jPanel81);
        jPanel81.setLayout(jPanel81Layout);
        jPanel81Layout.setHorizontalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel81Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TEnlatados, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel81Layout.setVerticalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel81Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TEnlatados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane15.addTab("Enlatados", jPanel81);

        jPanel82.setBackground(new java.awt.Color(255, 255, 255));

        jTable20.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        TDoces.setViewportView(jTable20);

        javax.swing.GroupLayout jPanel82Layout = new javax.swing.GroupLayout(jPanel82);
        jPanel82.setLayout(jPanel82Layout);
        jPanel82Layout.setHorizontalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel82Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TDoces, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel82Layout.setVerticalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel82Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TDoces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane15.addTab("Doces", jPanel82);

        jPanel83.setBackground(new java.awt.Color(255, 255, 255));

        jTable21.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        TMassas.setViewportView(jTable21);

        javax.swing.GroupLayout jPanel83Layout = new javax.swing.GroupLayout(jPanel83);
        jPanel83.setLayout(jPanel83Layout);
        jPanel83Layout.setHorizontalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel83Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TMassas, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel83Layout.setVerticalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel83Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TMassas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane15.addTab("Massas e biscoitos", jPanel83);

        jPanel84.setBackground(new java.awt.Color(255, 255, 255));

        jTable22.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        TCereais.setViewportView(jTable22);

        javax.swing.GroupLayout jPanel84Layout = new javax.swing.GroupLayout(jPanel84);
        jPanel84.setLayout(jPanel84Layout);
        jPanel84Layout.setHorizontalGroup(
            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel84Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TCereais, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel84Layout.setVerticalGroup(
            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel84Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TCereais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane15.addTab("Cereais", jPanel84);

        jPanel85.setBackground(new java.awt.Color(255, 255, 255));

        jTable23.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        TOutros.setViewportView(jTable23);

        javax.swing.GroupLayout jPanel85Layout = new javax.swing.GroupLayout(jPanel85);
        jPanel85.setLayout(jPanel85Layout);
        jPanel85Layout.setHorizontalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel85Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TOutros, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel85Layout.setVerticalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel85Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TOutros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane15.addTab("Outros", jPanel85);

        jTable24.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        TOutros1.setViewportView(jTable24);

        javax.swing.GroupLayout jPanel86Layout = new javax.swing.GroupLayout(jPanel86);
        jPanel86.setLayout(jPanel86Layout);
        jPanel86Layout.setHorizontalGroup(
            jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel86Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TOutros1, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel86Layout.setVerticalGroup(
            jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel86Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TOutros1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane15.addTab("Higiene e limpeza do lar", jPanel86);

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane15)
                .addContainerGap())
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane15)
                .addContainerGap())
        );

        jTabbedPane5.addTab("Estoque Por Area", jPanel61);

        TabelaEstoqueEspecifico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descrição", "Preço", "Quantidade"
            }
        ));
        jScrollPane7.setViewportView(TabelaEstoqueEspecifico);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(120, 0, 20));
        jLabel31.setText("Codigo do Produto:");

        jButton26.setBackground(new java.awt.Color(150, 0, 20));
        jButton26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setText("Buscar");
        jButton26.setBorderPainted(false);
        jButton26.setContentAreaFilled(false);
        jButton26.setOpaque(true);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTCodigoEspecifico, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 483, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTCodigoEspecifico, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1079, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel18);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Estoque Especifico", jPanel17);

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setToolTipText("");

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setToolTipText("");

        jPanel111.setBackground(new java.awt.Color(150, 0, 20));

        jLabel131.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel131.setForeground(new java.awt.Color(255, 255, 255));
        jLabel131.setText("A receber");

        javax.swing.GroupLayout jPanel111Layout = new javax.swing.GroupLayout(jPanel111);
        jPanel111.setLayout(jPanel111Layout);
        jPanel111Layout.setHorizontalGroup(
            jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel111Layout.createSequentialGroup()
                .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 252, Short.MAX_VALUE))
        );
        jPanel111Layout.setVerticalGroup(
            jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel131, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        jPanel112.setBackground(new java.awt.Color(150, 0, 20));

        jLabel134.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel134.setForeground(new java.awt.Color(255, 255, 255));
        jLabel134.setText("Entradas");

        javax.swing.GroupLayout jPanel112Layout = new javax.swing.GroupLayout(jPanel112);
        jPanel112.setLayout(jPanel112Layout);
        jPanel112Layout.setHorizontalGroup(
            jPanel112Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel112Layout.createSequentialGroup()
                .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel112Layout.setVerticalGroup(
            jPanel112Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel134, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        jPanel113.setBackground(new java.awt.Color(150, 0, 20));

        jLabel135.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel135.setForeground(new java.awt.Color(255, 255, 255));
        jLabel135.setText("Saidas");

        javax.swing.GroupLayout jPanel113Layout = new javax.swing.GroupLayout(jPanel113);
        jPanel113.setLayout(jPanel113Layout);
        jPanel113Layout.setHorizontalGroup(
            jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel113Layout.createSequentialGroup()
                .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel113Layout.setVerticalGroup(
            jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel135, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        jPanel114.setBackground(new java.awt.Color(215, 23, 49));

        jLabel136.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel136.setForeground(new java.awt.Color(255, 255, 255));
        jLabel136.setText("Carregamentos a Receber");

        javax.swing.GroupLayout jPanel114Layout = new javax.swing.GroupLayout(jPanel114);
        jPanel114.setLayout(jPanel114Layout);
        jPanel114Layout.setHorizontalGroup(
            jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel114Layout.createSequentialGroup()
                .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel114Layout.setVerticalGroup(
            jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel136, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        jPanel115.setBackground(new java.awt.Color(215, 23, 49));

        jLabel137.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel137.setForeground(new java.awt.Color(255, 255, 255));
        jLabel137.setText("Produtos");

        javax.swing.GroupLayout jPanel115Layout = new javax.swing.GroupLayout(jPanel115);
        jPanel115.setLayout(jPanel115Layout);
        jPanel115Layout.setHorizontalGroup(
            jPanel115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel115Layout.createSequentialGroup()
                .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel115Layout.setVerticalGroup(
            jPanel115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel137, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        jPanel116.setBackground(new java.awt.Color(215, 23, 49));

        jLabel138.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel138.setForeground(new java.awt.Color(255, 255, 255));
        jLabel138.setText("Carregamentos Recebidos");

        javax.swing.GroupLayout jPanel116Layout = new javax.swing.GroupLayout(jPanel116);
        jPanel116.setLayout(jPanel116Layout);
        jPanel116Layout.setHorizontalGroup(
            jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel116Layout.createSequentialGroup()
                .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel116Layout.setVerticalGroup(
            jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel138, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        jPanel117.setBackground(new java.awt.Color(215, 23, 49));

        jLabel139.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel139.setForeground(new java.awt.Color(255, 255, 255));
        jLabel139.setText("Produtos");

        javax.swing.GroupLayout jPanel117Layout = new javax.swing.GroupLayout(jPanel117);
        jPanel117.setLayout(jPanel117Layout);
        jPanel117Layout.setHorizontalGroup(
            jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel117Layout.createSequentialGroup()
                .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel117Layout.setVerticalGroup(
            jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel139, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        jPanel118.setBackground(new java.awt.Color(215, 23, 49));

        jLabel140.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel140.setForeground(new java.awt.Color(255, 255, 255));
        jLabel140.setText("Produtos");

        javax.swing.GroupLayout jPanel118Layout = new javax.swing.GroupLayout(jPanel118);
        jPanel118.setLayout(jPanel118Layout);
        jPanel118Layout.setHorizontalGroup(
            jPanel118Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel118Layout.createSequentialGroup()
                .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 295, Short.MAX_VALUE))
        );
        jPanel118Layout.setVerticalGroup(
            jPanel118Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel140, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        jTable27.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane31.setViewportView(jTable27);

        jLabel142.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel142.setForeground(new java.awt.Color(120, 0, 20));
        jLabel142.setText("CNPJ");
        jLabel142.setToolTipText("");

        jLabel143.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel143.setForeground(new java.awt.Color(120, 0, 20));
        jLabel143.setText("Empresa");
        jLabel143.setToolTipText("");

        jLabel144.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel144.setForeground(new java.awt.Color(120, 0, 20));
        jLabel144.setText("Descrição");
        jLabel144.setToolTipText("");

        jLabel145.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel145.setForeground(new java.awt.Color(120, 0, 20));
        jLabel145.setText("Data compra");
        jLabel145.setToolTipText("");

        jLabel146.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel146.setForeground(new java.awt.Color(120, 0, 20));
        jLabel146.setText("Previsão de chegada");
        jLabel146.setToolTipText("");

        jLabel148.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel148.setForeground(new java.awt.Color(120, 0, 20));
        jLabel148.setText("Forma Pagamento");
        jLabel148.setToolTipText("");

        jTextField39.setText("jTextField34");

        jTextField40.setText("jTextField34");

        jTextField41.setText("jTextField34");

        jSpinner2.setModel(new javax.swing.SpinnerListModel(new String[] {"A vista", "Boleto", "Cheque", "Cartão"}));

        jLabel149.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel149.setForeground(new java.awt.Color(120, 0, 20));
        jLabel149.setText("Parcelas");
        jLabel149.setToolTipText("");

        jTextField42.setText("jTextField38");

        jButton38.setText("Recebido");

        jButton39.setText("Remover");

        jButton40.setText("Adicionar");

        jTable28.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane36.setViewportView(jTable28);

        jTable30.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane38.setViewportView(jTable30);

        jLabel150.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel150.setForeground(new java.awt.Color(120, 0, 20));
        jLabel150.setText("Empresa");
        jLabel150.setToolTipText("");

        jTextField43.setText("jTextField34");

        jLabel151.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel151.setForeground(new java.awt.Color(120, 0, 20));
        jLabel151.setText("CNPJ");
        jLabel151.setToolTipText("");

        jTextField44.setText("jTextField34");

        jLabel152.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel152.setForeground(new java.awt.Color(120, 0, 20));
        jLabel152.setText("Descrição");
        jLabel152.setToolTipText("");

        jTextField45.setText("jTextField34");

        jLabel153.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel153.setForeground(new java.awt.Color(120, 0, 20));
        jLabel153.setText("Data compra");
        jLabel153.setToolTipText("");

        jLabel154.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel154.setForeground(new java.awt.Color(120, 0, 20));
        jLabel154.setText("Chegada");
        jLabel154.setToolTipText("");

        jLabel155.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel155.setForeground(new java.awt.Color(120, 0, 20));
        jLabel155.setText("Forma Pagamento");
        jLabel155.setToolTipText("");

        jLabel156.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel156.setForeground(new java.awt.Color(120, 0, 20));
        jLabel156.setText("Parcelas");
        jLabel156.setToolTipText("");

        jTable31.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane39.setViewportView(jTable31);

        jTextField34.setText("jTextField34");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane38)
                    .addComponent(jScrollPane36, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton38))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel118, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel113, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel116, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel112, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel114, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(jLabel148)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel149)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel145, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateChooser10, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                    .addComponent(jDateChooser9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel154, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateChooser12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooser11, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel111, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(jLabel155)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel156)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(jButton40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton39)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane31, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                            .addComponent(jPanel115, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel117, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel111, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane36, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel115, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel145, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(jDateChooser9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel146, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(jDateChooser10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel148, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton40)
                    .addComponent(jButton39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel112, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel116, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel117, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel153, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel154, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel155, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel156, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField34))))
                    .addComponent(jScrollPane39, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel118, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane38, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
        );

        jScrollPane30.setViewportView(jPanel31);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane30, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane30, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Entrada e Saida", jPanel33);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setToolTipText("");

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 Semana", "4 Semanas", "3 Meses", "6 Meses", "1 Ano" }));

        jLabel112.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(120, 0, 20));
        jLabel112.setText("Tempo para Vencimento");
        jLabel112.setToolTipText("");

        jTable25.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane28.setViewportView(jTable25);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane28, javax.swing.GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 1175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane10.setViewportView(jPanel28);

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Validade", jPanel29);

        javax.swing.GroupLayout PainelEstoqueLayout = new javax.swing.GroupLayout(PainelEstoque);
        PainelEstoque.setLayout(PainelEstoqueLayout);
        PainelEstoqueLayout.setHorizontalGroup(
            PainelEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
        );
        PainelEstoqueLayout.setVerticalGroup(
            PainelEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1051, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 646, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        readTabelaEstoqueEspecifico();
    }//GEN-LAST:event_jButton26ActionPerformed

    private void PDifMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PDifMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_PDifMouseEntered

    private void PDifItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_PDifItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_PDifItemStateChanged

    private void VICMSSTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VICMSSTMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_VICMSSTMouseEntered

    private void VICMSSTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_VICMSSTItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_VICMSSTItemStateChanged

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        preparaproximo();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        GerarCodigo();

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarCest().setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton6ActionPerformed

    private void ValorTribUnidadeTributariaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ValorTribUnidadeTributariaMouseEntered
        attICMSVtrib();
    }//GEN-LAST:event_ValorTribUnidadeTributariaMouseEntered

    private void QuantTribKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QuantTribKeyReleased
        attICMSVtrib();
    }//GEN-LAST:event_QuantTribKeyReleased

    private void QuantTribMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuantTribMouseEntered
        attICMSVtrib();
    }//GEN-LAST:event_QuantTribMouseEntered

    private void QuantTribMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuantTribMouseClicked
        GTNItribu.setText("");
    }//GEN-LAST:event_QuantTribMouseClicked

    private void UnidadeTributavelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UnidadeTributavelMouseEntered
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM NOSSOCLIENTE.UNTRIBUTAVEL where ncm like ?");
            stmt.setString(1, ""+Jncm.getText()+"");
            rs = stmt.executeQuery();

            while(rs.next()){

                // municipio.setCodigo(rs.getString("codigo"));
                UnidadeTributavel.setText(rs.getString("untrib"));
                //municipio.setUf(rs.getString("Uf"));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Ler produtos: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);

        }
    }//GEN-LAST:event_UnidadeTributavelMouseEntered

    private void JcestMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JcestMouseEntered
        pegajcst();
    }//GEN-LAST:event_JcestMouseEntered

    private void ceanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ceanKeyReleased

        //Preencher areas com valores se produto já existe
        ProdutoDAO pdao = new ProdutoDAO();
        boolean existe = false;

        for(Produto p: pdao.readdesc(cean.getText())){
            //Se existe seta existe true
            cod.setText(p.getCodigo());
            if(p.getCodigo()!=null){
                existe = true;
            }
            Jncm.setText(p.getNCM());
            Jcst.setText(p.getCST());
            Jcfop.setText(p.getCFOP());
            Jbase.setText(p.getBaseICMS());
            Jicms.setText(p.getICMS());
            Jcest.setText(p.getCEST());
            // cean.setText(p.getCEAN());
            descricao.setText(p.getDescrição());
            TipoProduto.getModel().setSelectedItem(p.getTipo());
            UnidadeComercial.getModel().setSelectedItem(p.getUnidade());
            preço.setText(p.getPreçoUn());
            quan.setText(String.valueOf(p.getQuantidade()));
            QuantTrib.setText(p.getQTrib());
            GTNItribu.setText(p.getCEANTrib());
            ValorTribUnidadeTributaria.setText(p.getVUnTrib());
            Aliquota.getModel().setSelectedItem(String.valueOf(p.getAliquotaICMS()));
            UnidadeTributavel.setText(p.getUnidadeTributavel());
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                jDateChooser2.setDate((Date)formatter.parse(p.getValidade()));
            } catch (ParseException ex) {
                Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //se existe true não roda if abaixo
        if(existe==false){
            if(cod.getText().equals("")==true){
                cod.setText(cean.getText());
                GTNItribu.setText(cean.getText());
            }
        }
    }//GEN-LAST:event_ceanKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ICMS().setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CST2().setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CFOP().setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            java.awt.Desktop.getDesktop().browse( new java.net.URI( "https://www.sefaz.pe.gov.br/Legislacao/Tributaria/Documents/Legislacao/Tabelas/CFOP.htm" ) );
        } catch (URISyntaxException ex) {

        }   catch (IOException ex) {
            Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarNCM().setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton1ActionPerformed

    private void AliquotaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AliquotaMouseEntered
        attICMSVtrib();
    }//GEN-LAST:event_AliquotaMouseEntered

    private void AliquotaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AliquotaItemStateChanged

        String ali = String.valueOf(Aliquota.getSelectedItem());
        Float aliquota = Float.valueOf(ali.replaceAll("%", ""));

        DecimalFormat decimalFormat = new DecimalFormat( "0.00" );
        //decimalFormat.setMinimumFractionDigits(1);

        String ICMS = decimalFormat.format(Float.valueOf(Jbase.getText())*aliquota/100);

        Float Icms = Float.parseFloat(ICMS.replaceAll(",", "."));
        Float qTri = Float.parseFloat(QuantTrib.getText());
        Float ValorUnTrib = Float.valueOf(Jbase.getText())/qTri; // divisão do valor do produto pela quantidade tributável
        ValorTribUnidadeTributaria.setText(decimalFormat.format(ValorUnTrib));

        Jicms.setText(ICMS.replaceAll(",", "."));
        //attICMSVtrib();
        minimo();
    }//GEN-LAST:event_AliquotaItemStateChanged

    private void JicmsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JicmsMouseEntered
        attICMSVtrib();
    }//GEN-LAST:event_JicmsMouseEntered

    private void JcstKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JcstKeyReleased
        pegajcst();
    }//GEN-LAST:event_JcstKeyReleased

    private void JcstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JcstActionPerformed

    private void JcstMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JcstMouseEntered
        // pegajcst();
    }//GEN-LAST:event_JcstMouseEntered

    private void JncmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JncmKeyReleased

       calculoibpt();
    }//GEN-LAST:event_JncmKeyReleased

    private void cod1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cod1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cod1ActionPerformed

    private void jTable8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable8MouseClicked

        PegaValores();

    }//GEN-LAST:event_jTable8MouseClicked

    private void BUProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUProdutoActionPerformed

        AtualizaProduto();
    }//GEN-LAST:event_BUProdutoActionPerformed

    private void BUProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BUProdutoMouseEntered
        // jTextField1.setText("Complete Todos os campos!");
    }//GEN-LAST:event_BUProdutoMouseEntered

    private void BAProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAProdutoActionPerformed

        ApagarProduto();
    }//GEN-LAST:event_BAProdutoActionPerformed

    private void BAProdutoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BAProdutoMouseExited
        // jTextField1.setText("Passe o mause para mais informações");
    }//GEN-LAST:event_BAProdutoMouseExited

    private void BAProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BAProdutoMouseEntered
        //jTextField1.setText("Somente o codigo é necessario para essa operação");
    }//GEN-LAST:event_BAProdutoMouseEntered

    private void BCProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCProdutoActionPerformed

        AdicionarProdutos();
    }//GEN-LAST:event_BCProdutoActionPerformed

    private void BCProdutoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BCProdutoMouseExited
        //jTextField1.setText("Passe o mause para mais informações");
    }//GEN-LAST:event_BCProdutoMouseExited

    private void BCProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BCProdutoMouseEntered
        //        jTextField1.setText("Complete Todos os campos. Espaços de preço e Quantidade não devem estar vazios.");
    }//GEN-LAST:event_BCProdutoMouseEntered

    private void preçoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preçoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_preçoActionPerformed

    private void preçoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_preçoFocusLost
       // Jbase.setText(preço.getText());
    }//GEN-LAST:event_preçoFocusLost

    private void descricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descricaoActionPerformed

    private void AliquotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AliquotaActionPerformed
       
    }//GEN-LAST:event_AliquotaActionPerformed

    private void preçoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_preçoKeyReleased
       Jbase.setText(preço.getText());
       VBCPIS.setText(preço.getText());
       VBCCOFINS.setText(preço.getText());
       minimo();
    }//GEN-LAST:event_preçoKeyReleased

    private void ValorVendaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ValorVendaKeyReleased
     minimo();
     calculoibpt();
     if(QuantTrib.getText().equals("1")){
     ValorTribUnidadeTributaria.setText(ValorVenda.getText().replaceAll(",", "."));
     }
     else{
     ValorTribUnidadeTributaria.setText(String.valueOf(Float.parseFloat(ValorVenda.getText().replaceAll(",", "."))/Float.parseFloat(QuantTrib.getText())));
     }
    }//GEN-LAST:event_ValorVendaKeyReleased

    private void JicmsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JicmsKeyReleased
        minimo();
    }//GEN-LAST:event_JicmsKeyReleased

    private void VPISKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VPISKeyReleased
        minimo();
    }//GEN-LAST:event_VPISKeyReleased

    private void VCOFINSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VCOFINSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_VCOFINSKeyPressed

    private void VCOFINSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VCOFINSKeyReleased
        minimo();
    }//GEN-LAST:event_VCOFINSKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AEstadual;
    private javax.swing.JTextField AFederalI;
    private javax.swing.JTextField AFederalN;
    private javax.swing.JTextField AMunicipal;
    private javax.swing.JComboBox<String> Aliquota;
    private javax.swing.JButton BAProduto;
    private javax.swing.JComboBox<String> BCICMS;
    private javax.swing.JComboBox<String> BCICMSST;
    private javax.swing.JButton BCProduto;
    private javax.swing.JButton BUProduto;
    private javax.swing.JComboBox<String> CSTCOFINS;
    private javax.swing.JComboBox<String> CSTPIS;
    private javax.swing.JTextField GTNItribu;
    private javax.swing.JTextField JTCodigoEspecifico;
    private javax.swing.JTextField Jbase;
    public static javax.swing.JTextField Jcest;
    private javax.swing.JTextField Jcfop;
    private javax.swing.JTextField Jcst;
    private javax.swing.JTextField Jicms;
    public static javax.swing.JTextField Jncm;
    private javax.swing.JTextField PCOFINS;
    private javax.swing.JTextField PCredSN;
    private javax.swing.JComboBox<String> PDif;
    private javax.swing.JTextField PICMSST;
    private javax.swing.JTextField PMVAST;
    private javax.swing.JTextField PPIS;
    private javax.swing.JTextField PRedBC;
    private javax.swing.JTextField PRedBCST;
    private javax.swing.JPanel PainelEstoque;
    private javax.swing.JTextField QuantTrib;
    private javax.swing.JScrollPane TAçougue;
    private javax.swing.JScrollPane TCampoLazer;
    private javax.swing.JScrollPane TCereais;
    private javax.swing.JScrollPane TDoces;
    private javax.swing.JScrollPane TEnlatados;
    private javax.swing.JScrollPane THLimpeza;
    private javax.swing.JScrollPane THortifruit;
    private javax.swing.JScrollPane TLimpeza;
    private javax.swing.JScrollPane TMassas;
    private javax.swing.JScrollPane TOutros;
    private javax.swing.JScrollPane TOutros1;
    private javax.swing.JScrollPane TPadaria;
    private javax.swing.JScrollPane TPerfumaria;
    private javax.swing.JTable TSemTipo;
    private javax.swing.JScrollPane TUtilidadesDomesticas;
    private javax.swing.JTable TabelaEstoqueEspecifico;
    private javax.swing.JComboBox<String> TipoProduto;
    private javax.swing.JComboBox<String> UnidadeComercial;
    public static javax.swing.JTextField UnidadeTributavel;
    private javax.swing.JTextField VBCCOFINS;
    private javax.swing.JTextField VBCPIS;
    private javax.swing.JTextField VBCST;
    private javax.swing.JTextField VCOFINS;
    private javax.swing.JTextField VCredICMSSN;
    private javax.swing.JTextField VICMS;
    private javax.swing.JTextField VICMSDif;
    private javax.swing.JComboBox<String> VICMSST;
    private javax.swing.JTextField VPIS;
    private javax.swing.JTextField ValorTribUnidadeTributaria;
    private javax.swing.JTextField ValorVenda;
    private javax.swing.JTextField cean;
    private javax.swing.JTextField cod;
    private javax.swing.JTextField cod1;
    private javax.swing.JTextField descricao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser10;
    private com.toedter.calendar.JDateChooser jDateChooser11;
    private com.toedter.calendar.JDateChooser jDateChooser12;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel112;
    private javax.swing.JPanel jPanel113;
    private javax.swing.JPanel jPanel114;
    private javax.swing.JPanel jPanel115;
    private javax.swing.JPanel jPanel116;
    private javax.swing.JPanel jPanel117;
    private javax.swing.JPanel jPanel118;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel86;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane36;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JScrollPane jScrollPane39;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTabbedPane jTabbedPane15;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable14;
    private javax.swing.JTable jTable15;
    private javax.swing.JTable jTable16;
    private javax.swing.JTable jTable17;
    private javax.swing.JTable jTable18;
    private javax.swing.JTable jTable19;
    private javax.swing.JTable jTable20;
    private javax.swing.JTable jTable21;
    private javax.swing.JTable jTable22;
    private javax.swing.JTable jTable23;
    private javax.swing.JTable jTable24;
    private javax.swing.JTable jTable25;
    private javax.swing.JTable jTable27;
    private javax.swing.JTable jTable28;
    private javax.swing.JTable jTable30;
    private javax.swing.JTable jTable31;
    private javax.swing.JTable jTable8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JLabel nBCICMS;
    private javax.swing.JLabel nBCICMSST;
    private javax.swing.JLabel nPCredSN;
    private javax.swing.JLabel nPDif;
    private javax.swing.JLabel nPICMSST;
    private javax.swing.JLabel nPMVAST;
    private javax.swing.JLabel nPRedBC;
    private javax.swing.JLabel nPRedBCST;
    private javax.swing.JLabel nVBCST;
    private javax.swing.JLabel nVCredICMSSN;
    private javax.swing.JLabel nVICMS;
    private javax.swing.JLabel nVICMSDif;
    private javax.swing.JLabel nVICMSST;
    private javax.swing.JTextField preço;
    private javax.swing.JTextField quan;
    // End of variables declaration//GEN-END:variables
}
