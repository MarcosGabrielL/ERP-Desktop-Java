/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.Modulos;

import gerenciador.TelaGestão;
import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.bean.Fluxo;
import model.bean.Meta;
import model.bean.Produto;
import model.bean.Vendas;
import model.bean.Vendidos;
import model.dao.FluxoDAO;
import model.dao.MetaDAO;
import model.dao.ProdutoDAO;
import model.dao.VendasDAO;
import model.dao.VendidosDAO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Marcos
 */
public class Estatistica extends javax.swing.JInternalFrame {

    /**
     * Creates new form Estatistica
     */
    public Estatistica() {
        initComponents();
        carregarmetas();
        graficosporloja();
        estilojpanel();
    }

    private void preenchertabelatipo(){
         
        DefaultTableModel modelo = (DefaultTableModel)jTable4.getModel();
           VendidosDAO vdao = new VendidosDAO();
           
           
           if(jComboBox8.getSelectedItem().toString().equals("Sem tipo")){
           modelo.setNumRows(0);
           }else{
               modelo.setNumRows(0);
       for(Vendidos v: vdao.PorArea(jComboBox8.getSelectedItem().toString())){
        modelo.addRow(new Object[]{
                         v.getDescrição(),
                         v.getTipo(),
                         v.getLoja(),
                         v.getCaixa(),
                         v.getQuantidade()
                    });
        
         }
           
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
        SwingUtilities.updateComponentTreeUI(PainelEstatistica);
           UIManager.put("PainelEstatistica.borderColor", Color.BLACK);
            UIManager.put("PainelEstatistica.darkShadow", ColorUIResource.BLACK);
            UIManager.put("PainelEstatistica.light", ColorUIResource.BLACK);
            UIManager.put("jPainelEstatistica.highlight", ColorUIResource.BLACK);
            UIManager.put("PainelEstatistica.focus", ColorUIResource.BLACK);
            UIManager.put("PainelEstatistica.unselectedBackground", ColorUIResource.BLACK);
            UIManager.put("PainelEstatistica.selectHighlight", ColorUIResource.BLACK);
            UIManager.put("PainelEstatistica.tabAreaBackground", ColorUIResource.BLACK);
            UIManager.put("PainelEstatistica.borderHightlightColor", ColorUIResource.BLACK);
        
        
        
    }
    private void estilotabelaemestatistica(){
    //Tamanho horizontal coluna
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable4.getColumnModel().getColumn(1).setPreferredWidth(20); 
        jTable4.getColumnModel().getColumn(2).setPreferredWidth(5);
        jTable4.getColumnModel().getColumn(3).setPreferredWidth(2);
        jTable4.getColumnModel().getColumn(4).setPreferredWidth(5);
    
        //Modifica titulo da jtable
        JTableHeader Theader = jTable4.getTableHeader();
       
       // change the Foreground
        
        Theader.setFont(new Font("Tahome", Font.BOLD, 12)); // font name style size
        ((DefaultTableCellRenderer)Theader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER); // center header tex
        
        
        DefaultTableModel modelo = (DefaultTableModel)jTable4.getModel();
        modelo.setNumRows(0);  //Zera a tabela
        jTable4.setRowHeight(40); // Tamanho vertical da tabela
        
        //Tamanho horizontal coluna
        jTable7.getColumnModel().getColumn(0).setPreferredWidth(100);
         jTable7.getColumnModel().getColumn(1).setPreferredWidth(20); 
        jTable7.getColumnModel().getColumn(2).setPreferredWidth(5);
        jTable7.getColumnModel().getColumn(3).setPreferredWidth(2);
        jTable7.getColumnModel().getColumn(4).setPreferredWidth(5);
    
        //Modifica titulo da jtable
        JTableHeader Theader1 = jTable7.getTableHeader();
       
       // change the Foreground
        
        Theader1.setFont(new Font("Tahome", Font.BOLD, 12)); // font name style size
        ((DefaultTableCellRenderer)Theader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER); // center header tex
        
        
        DefaultTableModel modelo1 = (DefaultTableModel)jTable7.getModel();
        modelo1.setNumRows(0);  //Zera a tabela
        jTable7.setRowHeight(40); // Tamanho vertical da tabela
        
        
                //Tamanho horizontal coluna
        jTable11.getColumnModel().getColumn(0).setPreferredWidth(2);
        jTable11.getColumnModel().getColumn(1).setPreferredWidth(20); 
        jTable11.getColumnModel().getColumn(2).setPreferredWidth(30);
        jTable11.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable11.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable11.getColumnModel().getColumn(5).setPreferredWidth(5);
    
        //Modifica titulo da jtable
        JTableHeader Theader2 = jTable11.getTableHeader();
       
       // change the Foreground
        
        Theader2.setFont(new Font("Tahome", Font.BOLD, 12)); // font name style size
        ((DefaultTableCellRenderer)Theader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER); // center header tex
        
        
        DefaultTableModel modelo2 = (DefaultTableModel)jTable11.getModel();
        modelo2.setNumRows(0);  //Zera a tabela
        jTable11.setRowHeight(25); // Tamanho vertical da tabela
        
        //Tamanho horizontal coluna
       
        
    }
    private void preenchertabelasede(){
    
        DefaultTableModel modelo = (DefaultTableModel)jTable4.getModel();
           VendidosDAO vdao = new VendidosDAO();
           modelo.setNumRows(0);
       for(Vendidos v: vdao.PorLoja(jTextField4.getText())){
        modelo.addRow(new Object[]{
                         v.getDescrição(),
                         v.getTipo(),
                         v.getLoja(),
                         v.getCaixa(),
                         v.getQuantidade()
                    });
        
         }
    
    }
    private void preenchertabelacaixa(){
        
        DefaultTableModel modelo = (DefaultTableModel)jTable4.getModel();
           VendidosDAO vdao = new VendidosDAO();
           
           modelo.setNumRows(0);
       for(Vendidos v: vdao.PorCaixa(jTextField4.getText(),jComboBox10.getSelectedItem().toString())){
        modelo.addRow(new Object[]{
                         v.getDescrição(),
                         v.getTipo(),
                         v.getLoja(),
                         v.getCaixa(),
                         v.getQuantidade()
                    });
        
         }
    
    }
    private void PreencherTabelaMenosTipo(){
    
        DefaultTableModel modelo = (DefaultTableModel)jTable7.getModel();
           VendidosDAO vdao = new VendidosDAO();
           
           
           if(jComboBox9.getSelectedItem().toString().equals("Sem tipo")){
           modelo.setNumRows(0);
           }else{
               modelo.setNumRows(0);
       for(Vendidos v: vdao.PorAreaMenos(jComboBox9.getSelectedItem().toString())){
        modelo.addRow(new Object[]{
                         v.getDescrição(),
                         v.getTipo(),
                         v.getLoja(),
                         v.getCaixa(),
                         v.getQuantidade()
                    });
        
         }
           
           }
    
    
    
    }
    private void PreencherTabelaMenosLoja(){
    
        DefaultTableModel modelo = (DefaultTableModel)jTable7.getModel();
           VendidosDAO vdao = new VendidosDAO();
           modelo.setNumRows(0);
       for(Vendidos v: vdao.PorLojaMenos(jTextField5.getText())){
        modelo.addRow(new Object[]{
                         v.getDescrição(),
                         v.getTipo(),
                         v.getLoja(),
                         v.getCaixa(),
                         v.getQuantidade()
                    });
        
         }
    
    }
    private void PreencherTabelaMenosCaixa(){
    
        DefaultTableModel modelo = (DefaultTableModel)jTable7.getModel();
           VendidosDAO vdao = new VendidosDAO();
           
           modelo.setNumRows(0);
       for(Vendidos v: vdao.PorCaixaMenos(jTextField5.getText(),jComboBox15.getSelectedItem().toString())){
        modelo.addRow(new Object[]{
                         v.getDescrição(),
                         v.getTipo(),
                         v.getLoja(),
                         v.getCaixa(),
                         v.getQuantidade()
                    });
        
         }
    
    }
    private void PreencherTabelaFluxoEspecifico(){
    
       /* DefaultTableModel modelo = (DefaultTableModel)jTable5.getModel();
           FluxoDAO dao = new FluxoDAO();
           
           
           modelo.setNumRows(0);
       for(Fluxo f: dao.Especifico(jTextField8.getText(), jComboBox13.getSelectedItem().toString(), jFormattedTextField1.getText())){
        modelo.addRow(new Object[]{
                         f.getIdCaixa(),
                         f.getLoja(),
                         f.getOperador(),
                         f.getAberturaData(),
                         f.getFechamentoData(),
                         f.getFluxoCaixa()
                    });
        
         }
    */
    
    }
    private void  PesquisaFluxoCaixa(){
        Locale locale = new Locale("pt","BR");
        GregorianCalendar calendar = new GregorianCalendar(); 
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy",locale); 
        SimpleDateFormat formatador1 = new SimpleDateFormat("yyyy-dd-aa",locale); 
    
        DefaultTableModel modelo = (DefaultTableModel)jTable11.getModel();
        modelo.setNumRows(0);
        
        String data = formatador.format(jDateChooser1.getDate()).toString();
        
       String loja = jTextField8.getText();
       String caixa = jComboBox13.getSelectedItem().toString();
       java.sql.Date dia = null;
       java.util.Date d = jDateChooser1.getDate();
        if (d == null) {
            System.out.println("Nenhuma Data selecionada!");
        } else {
            dia = new java.sql.Date(d.getTime());
            // Do something with sqldate
        }
        String diaa = formatador1.format(jDateChooser1.getDate());
System.out.println(diaa);
System.out.println(dia);


       FluxoDAO fdao = new FluxoDAO();
     
       for(Fluxo f: fdao.Especifico(loja, caixa, dia)){
       
           modelo.addRow(new Object[]{
                         f.getIdCaixa(),
                         f.getLoja(),
                         f.getOperador(),
                         f.getAberturaData(),
                         f.getFechamentoData(),
                         f.getFluxoCaixa()
                    });
       
       }
       System.out.println(data);
       BuscarVendasdofluxo(data);
       
    }
    private void FluxoMovimentação(){
        
        VendidosDAO vdao = new VendidosDAO();
        ProdutoDAO pdao = new ProdutoDAO();
        Produto p = new Produto();
        DefaultTableModel modelo = (DefaultTableModel)jTable9.getModel();
        modelo.setNumRows(0);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    
        String caixa  = (String) (jTable5.getValueAt(jTable5.getSelectedRow(), 0));
        String loja  = (String) (jTable5.getValueAt(jTable5.getSelectedRow(), 1));
        int idVenda  = (int) (jTable5.getValueAt(jTable5.getSelectedRow(), 3));
        
        for(Vendidos v: vdao.PorIdVenda(idVenda,caixa, loja)){
        
            
            
            
            modelo.addRow(new Object[]{
                        v.getCodigo(),
                        v.getDescrição(),
                        v.getTipo(),
                        pdao.saberUnidade(v.getCodigo()),
                        pdao.saberPreço(v.getCodigo(), pdao.saberUnidade(v.getCodigo())),
                        v.getQuantidade(),
                        v.getDataSaida()
                    });
    }
        
    }
    private void BuscarVendasdofluxo(String dia){
    
        DefaultTableModel modelo = (DefaultTableModel)jTable5.getModel();
        modelo.setNumRows(0);
        VendasDAO vdao = new VendasDAO();
    
         for(Vendas v: vdao.readVenda1(dia)){
       
           modelo.addRow(new Object[]{
                         v.getCaixa(),
                         v.getLoja(),
                         v.getDataVenda(),
                         v.getIdVendas(),
                         v.getValor()
                    });
      
       }
    
    }
    private void graficosporloja(){
      
        FluxoDAO fdao = new FluxoDAO();
        VendidosDAO vdao = new VendidosDAO();
        float SemTipo = 0;
        float Hortifruti =0;
        float HigieneLar = 0;
        float HigienePessoal=0;
        float Perfumaria=0;
        float Utilidades=0;
        float Campo=0;
        float Padaria=0;
        float Açougue=0;
        float Enlatados=0;
        float Doces=0;
        float Massas =0;
        float Frios=0;
        float Cereais=0;
        float Bebidas=0;
        float Ferramentas=0;
        float Outros=0;
        float Venda1 = 0;
        float Venda2 = 0;
        float Venda3 = 0;
        float Venda4 = 0;
        float Venda5 = 0;
        float Venda6 = 0;
        float Venda7 = 0;
        float Venda8 = 0;
        float Venda9 = 0;
        float Venda10 = 0;
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(Fluxo f:fdao.readfluxo()){
            dataset.setValue(Float.parseFloat(f.getFluxoCaixa()),"Fluxo dia", f.getFechamentoData().toString());
           
        }
        JFreeChart criarGrafico = ChartFactory.createLineChart("Historico de vendas","dia","Valor", dataset, PlotOrientation.VERTICAL, true,true,false);
        ChartPanel myChartPanel = new ChartPanel(criarGrafico, true);  
        jPanel23.add(myChartPanel);
        myChartPanel.setSize(500, 368); //setei o tamanho do grafico conforme o painel que usarei
        myChartPanel.setVisible(true); 
        jPanel23.removeAll(); //removi todos os componentes que podem estar no meu painel
        jPanel23.add(myChartPanel);
        jPanel23.revalidate(); // revalidei meu painel, para que ele se atualize
        jPanel23.repaint();
        jPanel23.updateUI();
        
            
        DefaultPieDataset pizza = new DefaultPieDataset();
        for(Vendidos v: vdao.read()){
            if(v.getTipo().equals("Sem tipo")){
                SemTipo = SemTipo + v.getQuantidade();
            }
            if(v.getTipo().equals("Hortifruti")){
                Hortifruti = Hortifruti + v.getQuantidade();
            }
            if(v.getTipo().equals("Higiene e limpeza do lar")){
                HigieneLar = HigieneLar + v.getQuantidade();
            }
            if(v.getTipo().equals("Higiene e limpeza pessoal")){
                HigienePessoal = HigienePessoal + v.getQuantidade();
            }
            if(v.getTipo().equals("Perfumaria")){
                Perfumaria = Perfumaria + v.getQuantidade();
            }
            if(v.getTipo().equals("Utilidades domésticas")){
                Utilidades = Utilidades + v.getQuantidade();
            }
            if(v.getTipo().equals("Campo e lazer")){
                Campo = Campo + v.getQuantidade();
            }
            if(v.getTipo().equals("Padaria")){
                Padaria = Padaria + v.getQuantidade();
                System.out.print(Padaria);
            }
            if(v.getTipo().equals("Açougue")){
                Açougue = Açougue + v.getQuantidade();
            }
            if(v.getTipo().equals("Enlatados")){
                Enlatados = Enlatados + v.getQuantidade();
            }
            if(v.getTipo().equals("Doces")){
                Doces = Doces + v.getQuantidade();
            }
            if(v.getTipo().equals("Massas e biscoitos")){
                Massas = Massas + + v.getQuantidade();
            }
            if(v.getTipo().equals("Frios")){
                Frios = Frios + v.getQuantidade();
            }
            if(v.getTipo().equals("Cereais")){
                Cereais = Cereais + v.getQuantidade();
            }
            if(v.getTipo().equals("Bebidas")){
                Bebidas = Bebidas + v.getQuantidade();
            }
            if(v.getTipo().equals("Ferramentas")){
                Ferramentas = Ferramentas + v.getQuantidade();
            }
            if(v.getTipo().equals("Outros")){
                Outros = Outros + v.getQuantidade();
            }
        
       
        }
        pizza.setValue("Sem Tipo",SemTipo);
        pizza.setValue("Hortifruti",Hortifruti);
        pizza.setValue("Higiene e Limpeza do Lar",HigieneLar);
        pizza.setValue("Higiene e Limpeza Pessoal",HigienePessoal);
        pizza.setValue("Perfumaria",Perfumaria);
        pizza.setValue("Utilidades Domesticas",Utilidades);
        pizza.setValue("Padaria",Padaria);
        pizza.setValue("Campo e Lazer",Campo);
        pizza.setValue("Açougue",Açougue);
        pizza.setValue("Enlatados",Enlatados);
        pizza.setValue("Doces",Doces);
        pizza.setValue("Massas e Biscoitos",Massas);
        pizza.setValue("Frios",Frios);
        pizza.setValue("Cereais",Cereais);
        pizza.setValue("Bebidas",Bebidas);
        pizza.setValue("Ferramentas",Ferramentas);
        pizza.setValue("Outros",Outros);
        JFreeChart grafico = ChartFactory.createPieChart("Areas Mais vendidas",pizza, false,true,false);
        ChartPanel painel = new ChartPanel(grafico);
        PiePlot plot = (PiePlot) grafico.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setOutlineVisible(false);
        jPanel65.add(painel);
        painel.setSize(870, 399); //setei o tamanho do grafico conforme o painel que usarei
        painel.setVisible(true); 
        jPanel65.removeAll(); //removi todos os componentes que podem estar no meu painel
        jPanel65.add(painel);
        jPanel65.revalidate(); // revalidei meu painel, para que ele se atualize
        jPanel65.repaint();
        jPanel65.updateUI();
        
    }
    private void carregarmetas(){
    
    jProgressBar1.setUI(new ProgressCircleUI2());
    jProgressBar2.setUI(new ProgressCircleUI2());
    jProgressBar3.setUI(new ProgressCircleUI2());
    
        DefaultTableModel modelo = (DefaultTableModel)jTable2.getModel();
        modelo.setNumRows(0);
        
        MetaDAO mdao = new MetaDAO();
        for(Meta m: mdao.LerMeta()){
            modelo.addRow(new Object[]{
                m.getLoja(),
                m.getMetaSemanal(),
                m.getMetaMensal(),
                m.getMetaAnual(),
                m.getDataCriação(),
                m.getDataFimSemanal(),
                m.getDataFimMensal(),
                m.getDataFimAnual()
                        
                    });
    }
        
        
    Locale locale = new Locale("pt","BR");
        GregorianCalendar calendar = new GregorianCalendar(); 
            SimpleDateFormat formatador = new SimpleDateFormat("yyyy-mm-dd ",locale); 
                String DiadeHoje = formatador.format(calendar.getTime()).toString();
    for(int i=0; i<jTable2.getRowCount();i++){
    
             /*String dataCriação =  (String) (jTable2.getValueAt(i, 4));
             String FimMetaSemanal =  (String) (jTable2.getValueAt(i, 4));
             String dataCriação =  (String) (jTable2.getValueAt(i, 4));
             String pattern = "MMM dd HH:mm:ss zzzz yyyy";
             DateFormat df = new SimpleDateFormat(pattern);
             Date date = df.parse(data);
            
            
             int dias = Days.daysBetween(, ).getDays();*/
             
             if(DiadeHoje.equals(jTable2.getValueAt(i,5).toString())){
             //acabou o tempo para semana
             //colocar fundo vermelho
                      jProgressBar1.setVisible(false);
             
             }if(DiadeHoje.equals(jTable2.getValueAt(i,6).toString())){
             //acabou o tempo para semana
             //colocar fundo vermelho
                      jProgressBar2.setVisible(false);
             
             }if(DiadeHoje.equals(jTable2.getValueAt(i,7).toString())){
             //acabou o tempo para semana
             //colocar fundo vermelho
                      jProgressBar3.setVisible(false);
             
             }
    
        }
    
    }
    private void definirmeta(){
        
        Locale locale = new Locale("pt","BR");
        GregorianCalendar calendar = new GregorianCalendar(); 
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy",locale); 
        
        
        Meta m = new Meta();
        MetaDAO mdao = new MetaDAO();
        
        String Loja = jTextField11.getText();
                String MetaMensal = jTextField12.getText();
                        String MetaSemanal = jTextField13.getText();
                                String MetaAnual = jTextField14.getText();
                                
        java.util.Date data = new java.util.Date();                       
        java.sql.Date DataCriação = new java.sql.Date(data.getTime());
        
        Date data7 = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(data7);
        c.add(Calendar.DATE, +7);
        data7 = c.getTime();
        java.sql.Date data7Depois = new java.sql.Date(data7.getTime());
        
        
        Date data30 = new Date();
        Calendar d = Calendar.getInstance();
        d.setTime(data30);
        d.add(Calendar.DATE, +30);
        data30 = d.getTime();
        java.sql.Date data30Depois = new java.sql.Date(data30.getTime());
        
        Date data365 = new Date();
        Calendar e = Calendar.getInstance();
        e.setTime(data365);
        e.add(Calendar.DATE, +365);
        data365 = e.getTime();
        java.sql.Date data365Depois = new java.sql.Date(data365.getTime());
        
       
        
        m.setLoja(Loja);
        m.setMetaMensal(MetaMensal);
        m.setMetaSemanal(MetaSemanal);
        m.setMetaAnual(MetaAnual);
        m.setDataCriação(DataCriação);
        m.setDataFimSemanal(data7Depois );
        m.setDataFimMensal(data30Depois );
        m.setDataFimAnual(data365Depois);
        m.setStatusSemanal(0);
        m.setStatusMensal(0);
        m.setStatusAnual(0);
        mdao.ArmazenarMeta(m);
    
    }
    private void pegarstatusmetas(String Loja, String MetaMensal, String MetaSemanal, String MetaAnual, String DataCriação, String DataFimMetaSemanal, String DataFimMetaMensal, String DataFimMetaAnual){
       
        FluxoDAO fdao = new FluxoDAO();
        Fluxo f = new Fluxo();
        MetaDAO mdao = new MetaDAO();
        Meta m = new Meta();
        
        float MetaSemanalfloat = Float.parseFloat(MetaSemanal);
        float MetaMensalfloat = Float.parseFloat(MetaMensal);
        float MetaAnualfloat = Float.parseFloat(MetaAnual);
        List<String> status = new ArrayList();
        
        
                String dataString = DataCriação;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date DataCriaçãoData = null;  
        try {
            DataCriaçãoData = new java.sql.Date(sdf.parse(dataString).getTime());
            String testaDataSQL = sdf.format(DataCriaçãoData.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                String dataString1 = DataFimMetaSemanal;
		java.sql.Date DataFimMetaSemanalData = null;  
        try {
            DataFimMetaSemanalData = new java.sql.Date(sdf.parse(dataString1).getTime());
            String testaDataSQL = sdf.format(DataFimMetaSemanalData.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                String dataString2 = DataFimMetaMensal;
		java.sql.Date DataFimMetaMensalData = null;  
        try {
            DataFimMetaMensalData = new java.sql.Date(sdf.parse(dataString2).getTime());
            String testaDataSQL = sdf.format(DataFimMetaMensalData.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                String dataString3 = DataFimMetaAnual;
		java.sql.Date DataFimMetaAnualData = null;  
        try {
            DataFimMetaAnualData = new java.sql.Date(sdf.parse(dataString3).getTime());
            String testaDataSQL = sdf.format(DataFimMetaAnualData.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
		
        
        float FluxoAteAgoraSemanal = fdao.FluxoSoma(Loja, DataCriaçãoData, DataFimMetaSemanalData);
        System.out.println("O fluxo até agora semanal é: "+FluxoAteAgoraSemanal);
         float FluxoAteAgoraMensal = fdao.FluxoSoma(Loja, DataCriaçãoData, DataFimMetaMensalData);
         System.out.println("O fluxo até agora mensal é: "+FluxoAteAgoraMensal);
          float FluxoAteAgoraAnual = fdao.FluxoSoma(Loja, DataCriaçãoData, DataFimMetaAnualData);
          System.out.println("O fluxo até agora Anual é: "+FluxoAteAgoraAnual);
          
          float statusSemanalFloat = FluxoAteAgoraSemanal/MetaSemanalfloat;
          System.out.println("O status até agora semanal é: "+ statusSemanalFloat);
          float statusMensalFloat = FluxoAteAgoraMensal/MetaMensalfloat;
          System.out.println("O status até agora mensal é: "+ statusMensalFloat);
          float statusAnualFloat = FluxoAteAgoraAnual/MetaAnualfloat;
          System.out.println("O status até agora anual é: "+ statusAnualFloat);
          m.setStatusSemanal(statusSemanalFloat);
          m.setStatusMensal(statusMensalFloat);
          m.setStatusAnual(statusAnualFloat);
          m.setLoja(Loja);
          
          mdao.updateStatus(m);
          
    }
    private void carregarProgresso(){
        
    pegarstatusmetas(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString(),jTable2.getValueAt(jTable2.getSelectedRow(), 2).toString(),jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString(),jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString(),jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString(),jTable2.getValueAt(jTable2.getSelectedRow(), 5).toString(),jTable2.getValueAt(jTable2.getSelectedRow(), 6).toString(),jTable2.getValueAt(jTable2.getSelectedRow(), 7).toString() );
   
    MetaDAO mdao = new MetaDAO();
    
    for(Meta m: mdao.LerStatus((String) jTable2.getValueAt(jTable2.getSelectedRow(), 0))){
    
        int statusSemanalInt = (int) (m.getStatusSemanal()*100);
    jProgressBar1.setValue(statusSemanalInt);
        int statusMensalInt = (int) (m.getStatusMensal()*100);
    jProgressBar2.setValue(statusMensalInt);
        int statusAnualInt = (int) (m.getStatusAnual()*100);
    jProgressBar3.setValue(statusAnualInt);
    
    
    }
      
    
    }
    private void editarmetas(){
         DefaultTableModel modelo = (DefaultTableModel)jTable2.getModel();
         MetaDAO mdao = new MetaDAO();
         Meta m = new Meta();
         
        JLabel jLabel71 = new javax.swing.JLabel();
        javax.swing.JTextField lojae = new javax.swing.JTextField();
        javax.swing.JTextField metae = new javax.swing.JTextField();
        JLabel jLabel73 = new javax.swing.JLabel();
        javax.swing.JTextField meta2 = new javax.swing.JTextField();
        JLabel jLabel74 = new javax.swing.JLabel();
        javax.swing.JTextField meta3 = new javax.swing.JTextField();
        JLabel jLabel75 = new javax.swing.JLabel();
        JLabel AcessarConta1 = new javax.swing.JLabel();
        javax.swing.JButton jButton1 = new javax.swing.JButton();
        JFrame Sefra = new JFrame();
        
        
        Sefra.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Sefra.setTitle("Editar Meta");
        Sefra.setAlwaysOnTop(false);
        Sefra.setSize(300, 270);
        Sefra.setLocationRelativeTo(null);
        Sefra.setVisible(true);
        Sefra.getRootPane().setDefaultButton(jButton1);
        Sefra.getContentPane().setBackground(new java.awt.Color(255,255,255));

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(51, 51, 51));
        jLabel71.setText("Loja");
        jLabel71.setAutoscrolls(true);

        lojae.setText("Sede");
        lojae.setEditable(false);

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(51, 51, 51));
        jLabel73.setText("Meta Mensal");
        jLabel73.setAutoscrolls(true);

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(51, 51, 51));
        jLabel74.setText("Meta Semanal");
        jLabel74.setAutoscrolls(true);

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(51, 51, 51));
        jLabel75.setText("Meta Anual");
        jLabel75.setAutoscrolls(true);

        AcessarConta1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AcessarConta1.setForeground(new java.awt.Color(102, 102, 102));
        AcessarConta1.setText("Editar Metas");
        AcessarConta1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton1.setText("Atualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
         lojae.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
         
            m.setMetaSemanal(metae.getText());
            m.setMetaMensal(meta2.getText());
            m.setMetaAnual(meta3.getText());
            m.setLoja(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
            mdao.update(m);
                
                carregarmetas();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Sefra.getContentPane());
        Sefra.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(meta3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lojae, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(metae, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(meta2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AcessarConta1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(AcessarConta1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lojae)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(metae)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(meta2)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(meta3)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        
    
    
    }
    private void excluirmeta(){
    
        MetaDAO mdao = new MetaDAO();
        mdao.delete(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
        carregarmetas();
    
    }
    private void buscarprodutosbaixaestatistica(){
     
        if(jTextField9.getText().equals("") && jTextField7.getText().equals("") && jComboBox14.getSelectedItem().toString().equals("Selecione")){
                        DefaultTableModel modelo = (DefaultTableModel)jTable6.getModel();
                        modelo.setNumRows(0);
         
             }if(jTextField9.getText().equals("")==false && jTextField7.getText().equals("") && jComboBox14.getSelectedItem().toString().equals("Selecione") ){
                                DefaultTableModel modelo = (DefaultTableModel)jTable6.getModel();
                                modelo.setNumRows(0);

                                   ProdutoDAO pdao = new ProdutoDAO();
                                      for(Produto p: pdao.procurarLoja(jTextField9.getText())){
                                          modelo.addRow(new Object[]{
                                                        p.getCodigo(),
                                                        p.getDescrição(),
                                                        p.getUnidade(),
                                                        p.getTipo(),
                                                        p.getQuantidade()
                                                   });
                                          //jTable1.addRowSelectionInterval(0,0);
                                        }
             }if(jTextField9.getText().equals("")==false && jTextField7.getText().equals("")==false && jComboBox14.getSelectedItem().toString().equals("Selecione") ){
                                DefaultTableModel modelo = (DefaultTableModel)jTable6.getModel();
                                modelo.setNumRows(0);

                                   ProdutoDAO pdao = new ProdutoDAO();
                                      for(Produto p: pdao.procurarLojaEQuantidade(jTextField9.getText(), Integer.parseInt(jTextField7.getText()))){
                                          modelo.addRow(new Object[]{
                                                        p.getCodigo(),
                                                        p.getDescrição(),
                                                        p.getUnidade(),
                                                        p.getTipo(),
                                                        p.getQuantidade()
                                                   });
                                    }
            }if(jTextField9.getText().equals("")==false && jTextField7.getText().equals("")==false && jComboBox14.getSelectedItem().toString().equals("Selecione")==false ){
                                DefaultTableModel modelo = (DefaultTableModel)jTable6.getModel();
                                modelo.setNumRows(0);

                                   ProdutoDAO pdao = new ProdutoDAO();
                                      for(Produto p: pdao.procurarLojaEQuantidadeETipo(jTextField9.getText(), Integer.parseInt(jTextField7.getText()), jComboBox14.getSelectedItem().toString())){
                                          modelo.addRow(new Object[]{
                                                        p.getCodigo(),
                                                        p.getDescrição(),
                                                        p.getUnidade(),
                                                        p.getTipo(),
                                                        p.getQuantidade()
                                                   });
                                    }
            
            }if(jTextField9.getText().equals("") && jTextField7.getText().equals("")==false && jComboBox14.getSelectedItem().toString().equals("Selecione")==false ){
                                 DefaultTableModel modelo = (DefaultTableModel)jTable6.getModel();
                                modelo.setNumRows(0);

                                   ProdutoDAO pdao = new ProdutoDAO();
                                      for(Produto p: pdao.procurarQuantidadeETipo(Integer.parseInt(jTextField7.getText()), jComboBox14.getSelectedItem().toString())){
                                          modelo.addRow(new Object[]{
                                                        p.getCodigo(),
                                                        p.getDescrição(),
                                                        p.getUnidade(),
                                                        p.getTipo(),
                                                        p.getQuantidade()
                                                   });
                                    }    
            }if(jTextField9.getText().equals("") && jTextField7.getText().equals("") && jComboBox14.getSelectedItem().toString().equals("Selecione")==false ){
                                DefaultTableModel modelo = (DefaultTableModel)jTable6.getModel();
                                modelo.setNumRows(0);

                                   ProdutoDAO pdao = new ProdutoDAO();
                                      for(Produto p: pdao.readQTipo(jComboBox14.getSelectedItem().toString())){
                                          modelo.addRow(new Object[]{
                                                        p.getCodigo(),
                                                        p.getDescrição(),
                                                        p.getUnidade(),
                                                        p.getTipo(),
                                                        p.getQuantidade()
                                                   });
                                    }    
            }if(jTextField9.getText().equals("")==false && jTextField7.getText().equals("") && jComboBox14.getSelectedItem().toString().equals("Selecione")==false){
                                DefaultTableModel modelo = (DefaultTableModel)jTable6.getModel();
                                modelo.setNumRows(0);

                                   ProdutoDAO pdao = new ProdutoDAO();
                                      for(Produto p: pdao.procurarLojaETipo(jTextField9.getText(), jComboBox14.getSelectedItem().toString())){
                                          modelo.addRow(new Object[]{
                                                        p.getCodigo(),
                                                        p.getDescrição(),
                                                        p.getUnidade(),
                                                        p.getTipo(),
                                                        p.getQuantidade()
                                                   });
                                    } 
            }if(jTextField9.getText().equals("") && jTextField7.getText().equals("")==false && jComboBox14.getSelectedItem().toString().equals("Selecione")){
                                DefaultTableModel modelo = (DefaultTableModel)jTable6.getModel();
                                modelo.setNumRows(0);

                                   ProdutoDAO pdao = new ProdutoDAO();
                                      for(Produto p: pdao.procurarTipo(jComboBox14.getSelectedItem().toString())){
                                          modelo.addRow(new Object[]{
                                                        p.getCodigo(),
                                                        p.getDescrição(),
                                                        p.getUnidade(),
                                                        p.getTipo(),
                                                        p.getQuantidade()
                                                   });
                                    } 
            }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelEstatistica = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jTextField4 = new javax.swing.JTextField();
        jPanel69 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jPanel70 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jComboBox15 = new javax.swing.JComboBox<>();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jTextField5 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jButton24 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel60 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel63 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jLabel62 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel74 = new javax.swing.JPanel();
        AcessarConta1 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        LUsuario2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jPanel63 = new javax.swing.JPanel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jPanel72 = new javax.swing.JPanel();
        jProgressBar3 = new javax.swing.JProgressBar();
        jPanel75 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel30 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jPanel73 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel65 = new javax.swing.JPanel();

        PainelEstatistica.setBackground(new java.awt.Color(255, 255, 255));
        PainelEstatistica.setPreferredSize(new java.awt.Dimension(862, 695));

        jTabbedPane1.setBackground(new java.awt.Color(51, 51, 51));
        jTabbedPane1.setForeground(new java.awt.Color(51, 51, 51));
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setAutoscrolls(true);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTabbedPane1.setInheritsPopupMenu(true);
        jTabbedPane1.setRequestFocusEnabled(false);
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setToolTipText("");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(150, 0, 20));
        jLabel12.setText("Ver por Area");
        jLabel12.setToolTipText("");

        jComboBox8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sem tipo", "Hortifruti", "Higiene e limpeza do lar", "Higiene e limpeza pessoal", "Perfumaria", "Utilidades domésticas", "Campo e lazer", "Padaria", "Açougue", "Enlatados", "Doces", "Massas e biscoitos", "Frios", "Cereais", "Bebidas", "Ferramentas", "Outros" }));
        jComboBox8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox8ItemStateChanged(evt);
            }
        });
        jComboBox8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox8KeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(150, 0, 20));
        jLabel13.setText("Ver por Loja");
        jLabel13.setToolTipText("");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(150, 0, 20));
        jLabel14.setText("Ver por Caixa");
        jLabel14.setToolTipText("");

        jComboBox10.setEditable(true);
        jComboBox10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        jComboBox10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox10ItemStateChanged(evt);
            }
        });

        jTable4.setBackground(new java.awt.Color(240, 240, 240));
        jTable4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Descrição", "Area", "Loja", "Caixa", "Vendidos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setRequestFocusEnabled(false);
        jScrollPane4.setViewportView(jTable4);

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField4.setText("Sede");
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
        jPanel69.setLayout(jPanel69Layout);
        jPanel69Layout.setHorizontalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );
        jPanel69Layout.setVerticalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 169, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("   Menos Vendidos   ", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setToolTipText("");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(150, 0, 20));
        jLabel15.setText("Ver por Area");
        jLabel15.setToolTipText("");

        jComboBox9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sem tipo", "Hortifruti", "Higiene e limpeza do lar", "Higiene e limpeza pessoal", "Perfumaria", "Utilidades domésticas", "Campo e lazer", "Padaria", "Açougue", "Enlatados", "Doces", "Massas e biscoitos", "Frios", "Cereais", "Bebidas", "Ferramentas", "Outros" }));
        jComboBox9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox9ItemStateChanged(evt);
            }
        });
        jComboBox9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox9KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
        jPanel70.setLayout(jPanel70Layout);
        jPanel70Layout.setHorizontalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );
        jPanel70Layout.setVerticalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(150, 0, 20));
        jLabel19.setText("Ver por Loja");
        jLabel19.setToolTipText("");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(150, 0, 20));
        jLabel59.setText("Ver por Caixa");
        jLabel59.setToolTipText("");

        jComboBox15.setEditable(true);
        jComboBox15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        jComboBox15.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox15ItemStateChanged(evt);
            }
        });

        jTable7.setBackground(new java.awt.Color(240, 240, 240));
        jTable7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Descrição", "Area", "Loja", "Caixa", "Vendidos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable7.setRequestFocusEnabled(false);
        jScrollPane13.setViewportView(jTable7);

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField5.setText("Sede");
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane13)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("   Mais Vendidos   ", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setToolTipText("");

        jButton24.setBackground(new java.awt.Color(150, 0, 20));
        jButton24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/magnifieruuyty.png"))); // NOI18N
        jButton24.setText("Pesquisar");
        jButton24.setBorderPainted(false);
        jButton24.setContentAreaFilled(false);
        jButton24.setOpaque(true);
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(150, 0, 20));
        jLabel16.setText("Loja");
        jLabel16.setAutoscrolls(true);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(150, 0, 20));
        jLabel17.setText("Caixa");
        jLabel17.setAutoscrolls(true);

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Caixa", "Loja", "Hora da Venda", "Numero da venda", "Valor R$"
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(51, 51, 51));
        jLabel60.setText("Pesquisa especifica");
        jLabel60.setAutoscrolls(true);

        jTextField8.setEditable(false);
        jTextField8.setText("Sede");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(150, 0, 20));
        jLabel63.setText("Dia");
        jLabel63.setAutoscrolls(true);

        jTable9.setBackground(new java.awt.Color(102, 102, 102));
        jTable9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable9.setForeground(new java.awt.Color(255, 255, 255));
        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "código", "Descrição", "Tipo", "Unidade", "Preço", "Quantidade", "Hhora Saida"
            }
        ));
        jScrollPane15.setViewportView(jTable9);

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(51, 51, 51));
        jLabel62.setText("Movimentação");
        jLabel62.setAutoscrolls(true);

        jTable11.setBackground(new java.awt.Color(51, 51, 51));
        jTable11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable11.setForeground(new java.awt.Color(255, 255, 255));
        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Caixa", "Loja", "Operador", "Abertura Data", "Fechamento Data", "Fluxo R$"
            }
        ));
        jTable11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable11MouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(jTable11);

        jLabel3.setText("Selecione a venda ao lado para ");

        jLabel7.setText("ver produtos e informações");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jComboBox13, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPane17))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton24)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("   Fluxo de Caixas   ", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setToolTipText("");

        jPanel74.setBackground(new java.awt.Color(255, 255, 255));
        jPanel74.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel74FocusGained(evt);
            }
        });

        AcessarConta1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AcessarConta1.setForeground(new java.awt.Color(51, 51, 51));
        AcessarConta1.setText("Criar Metas");
        AcessarConta1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(150, 0, 20));
        jLabel71.setText("Loja");
        jLabel71.setAutoscrolls(true);

        jTextField11.setText("Sede");

        jTextField12.setText("6750");

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(150, 0, 20));
        jLabel73.setText("Meta Mensal");
        jLabel73.setAutoscrolls(true);

        jTextField13.setText("1687.50");

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(150, 0, 20));
        jLabel74.setText("Meta Semanal");
        jLabel74.setAutoscrolls(true);

        jTextField14.setText("81000");

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(150, 0, 20));
        jLabel75.setText("Meta Anual");
        jLabel75.setAutoscrolls(true);

        jToggleButton2.setBackground(new java.awt.Color(150, 0, 20));
        jToggleButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jToggleButton2.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/add-button-with-plus-symbol-in-a-black-circle.png"))); // NOI18N
        jToggleButton2.setText("Definir Meta");
        jToggleButton2.setBorderPainted(false);
        jToggleButton2.setContentAreaFilled(false);
        jToggleButton2.setOpaque(true);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        LUsuario2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LUsuario2.setForeground(new java.awt.Color(153, 153, 153));
        LUsuario2.setText("Metas de Venda");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Loja", "Semanal", "Mensal", "Anual", "Data Criação", "Final Semanal", "Final Mensal", "Final Anual"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jButton21.setBackground(new java.awt.Color(150, 0, 20));
        jButton21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/pencil.png"))); // NOI18N
        jButton21.setText("Editar");
        jButton21.setBorderPainted(false);
        jButton21.setContentAreaFilled(false);
        jButton21.setOpaque(true);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(150, 0, 20));
        jButton22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/icon.png"))); // NOI18N
        jButton22.setText("Excluir");
        jButton22.setBorderPainted(false);
        jButton22.setContentAreaFilled(false);
        jButton22.setOpaque(true);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jPanel63.setBackground(new java.awt.Color(255, 255, 255));
        jPanel63.setPreferredSize(new java.awt.Dimension(200, 200));

        jProgressBar2.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar2.setForeground(new java.awt.Color(51, 51, 51));
        jProgressBar2.setBorderPainted(false);
        jProgressBar2.setStringPainted(true);

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel72.setBackground(new java.awt.Color(255, 255, 255));
        jPanel72.setPreferredSize(new java.awt.Dimension(200, 200));

        jProgressBar3.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar3.setForeground(new java.awt.Color(51, 51, 51));
        jProgressBar3.setBorderPainted(false);
        jProgressBar3.setStringPainted(true);

        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
        jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar3, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel72Layout.setVerticalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addComponent(jProgressBar3, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel75.setBackground(new java.awt.Color(255, 255, 255));
        jPanel75.setPreferredSize(new java.awt.Dimension(200, 200));

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setForeground(new java.awt.Color(51, 51, 51));
        jProgressBar1.setBorderPainted(false);
        jProgressBar1.setStringPainted(true);

        javax.swing.GroupLayout jPanel75Layout = new javax.swing.GroupLayout(jPanel75);
        jPanel75.setLayout(jPanel75Layout);
        jPanel75Layout.setHorizontalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel75Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel75Layout.setVerticalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel75Layout.createSequentialGroup()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(153, 153, 153));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Progresso Meta Semanal");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Progresso Meta Mensal");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(153, 153, 153));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Progresso Meta Anual");

        javax.swing.GroupLayout jPanel74Layout = new javax.swing.GroupLayout(jPanel74);
        jPanel74.setLayout(jPanel74Layout);
        jPanel74Layout.setHorizontalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel74Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel74Layout.createSequentialGroup()
                                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel74Layout.createSequentialGroup()
                                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel74Layout.createSequentialGroup()
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel74Layout.createSequentialGroup()
                                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jToggleButton2))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel74Layout.createSequentialGroup()
                                .addComponent(jButton21)
                                .addGap(70, 70, 70)
                                .addComponent(jButton22))
                            .addComponent(LUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)))
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel74Layout.createSequentialGroup()
                                    .addComponent(AcessarConta1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(184, 184, 184))
                                .addGroup(jPanel74Layout.createSequentialGroup()
                                    .addComponent(jPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel74Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel74Layout.setVerticalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel74Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AcessarConta1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(LUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField12)
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField13)
                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField14)
                            .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton2))
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton21)
                            .addComponent(jButton22))))
                .addGap(33, 33, 33)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel10)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(158, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("   Meta de Venda   ", jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setToolTipText("");

        jPanel60.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanel60PropertyChange(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(150, 0, 20));
        jLabel20.setText("Escolha a Loja");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(150, 0, 20));
        jLabel21.setText("Escolha a area");

        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Sem tipo", "Hortifruti", "Higiene e limpeza do lar", "Higiene e limpeza pessoal", "Perfumaria", "Utilidades domésticas", "Campo e lazer", "Padaria", "Açougue", "Enlatados", "Doces", "Massas e biscoitos", "Frios", "Cereais", "Bebidas", "Ferramentas", "Outros" }));
        jComboBox14.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox14ItemStateChanged(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(150, 0, 20));
        jLabel22.setText("Minimo de produtos");

        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });

        jTextField9.setEditable(false);
        jTextField9.setText("Sede");
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField9KeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(150, 0, 20));
        jLabel8.setText("Preencha de acordo com o desejado");

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Caixa", "Produto", "Unidade", "Tipo", "Quantidade"
            }
        ));
        jScrollPane6.setViewportView(jTable6);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 284, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("   Produtos em falta   ", jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setToolTipText("");

        jPanel73.setBackground(new java.awt.Color(255, 255, 255));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setPreferredSize(new java.awt.Dimension(500, 368));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 368, Short.MAX_VALUE)
        );

        jPanel65.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 870, Short.MAX_VALUE)
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
        jPanel73.setLayout(jPanel73Layout);
        jPanel73Layout.setHorizontalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel73Layout.setVerticalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane16.setViewportView(jPanel73);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("   Gráficos   ", jPanel10);

        javax.swing.GroupLayout PainelEstatisticaLayout = new javax.swing.GroupLayout(PainelEstatistica);
        PainelEstatistica.setLayout(PainelEstatisticaLayout);
        PainelEstatisticaLayout.setHorizontalGroup(
            PainelEstatisticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        PainelEstatisticaLayout.setVerticalGroup(
            PainelEstatisticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelEstatistica, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(PainelEstatistica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox8ItemStateChanged
        preenchertabelatipo();
    }//GEN-LAST:event_jComboBox8ItemStateChanged

    private void jComboBox8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox8KeyReleased

    }//GEN-LAST:event_jComboBox8KeyReleased

    private void jComboBox10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox10ItemStateChanged
        preenchertabelacaixa();
    }//GEN-LAST:event_jComboBox10ItemStateChanged

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        preenchertabelasede();
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jComboBox9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox9ItemStateChanged
        PreencherTabelaMenosTipo();
    }//GEN-LAST:event_jComboBox9ItemStateChanged

    private void jComboBox9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox9KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox9KeyReleased

    private void jComboBox15ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox15ItemStateChanged
        PreencherTabelaMenosCaixa();
    }//GEN-LAST:event_jComboBox15ItemStateChanged

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        PreencherTabelaMenosLoja();
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed

        PesquisaFluxoCaixa();
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        FluxoMovimentação();
    }//GEN-LAST:event_jTable5MouseClicked

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTable11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable11MouseClicked

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        definirmeta();
        carregarmetas();
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        carregarProgresso();
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        editarmetas();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        excluirmeta();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jPanel74FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel74FocusGained

    }//GEN-LAST:event_jPanel74FocusGained

    private void jComboBox14ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox14ItemStateChanged
        buscarprodutosbaixaestatistica();
    }//GEN-LAST:event_jComboBox14ItemStateChanged

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        buscarprodutosbaixaestatistica();
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased
        buscarprodutosbaixaestatistica();
    }//GEN-LAST:event_jTextField9KeyReleased

    private void jPanel60PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanel60PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel60PropertyChange

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AcessarConta1;
    private javax.swing.JLabel LUsuario2;
    private javax.swing.JPanel PainelEstatistica;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton24;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox15;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToggleButton jToggleButton2;
    // End of variables declaration//GEN-END:variables
}
