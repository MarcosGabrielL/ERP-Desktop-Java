/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.Modulos;

import gerenciador.TelaGestão;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.bean.Fluxo;
import model.bean.Imposto;
import model.dao.FluxoDAO;
import model.dao.ImpostoDAO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Marcos
 */
public class Impostos1 extends javax.swing.JInternalFrame {

    /**
     * Creates new form Impostos1
     */
    public Impostos1() {
        initComponents();
        
        desenhaFluxoDiario();
        Carrega_Gráficos();
        estilojpanel();
        
        initUI();
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
        SwingUtilities.updateComponentTreeUI(jPanel1);
             
        
    }
    
    public void Carrega_Gráficos(){
    
         new Thread() {
                @Override
                public void run() {
                    
                    int DiasPassados = 0;
                    String Hoje = null;
                    String PrimeiroDiaMes = null;
                    boolean SalvaLucroBrutoMensal = false;
                    Date a = new Date();
                    Date b = new Date();
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Hoje = DateFormat.format(a);
                java.sql.Date DateHoje = null;  
                    try {
                        DateHoje = new java.sql.Date(DateFormat.parse(Hoje).getTime());
                    } catch (ParseException ex) {
                        Logger.getLogger(Impostos1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                Calendar d = Calendar.getInstance();
                d.setTime(a);
                DiasPassados = Integer.parseInt(Hoje.substring(8,10));
                
                int NumeroDias = d.getActualMaximum( Calendar.DAY_OF_MONTH );
                if(DiasPassados == NumeroDias){
                    SalvaLucroBrutoMensal = true;
                }
                DiasPassados = DiasPassados-1;
                d.add(Calendar.DATE, -DiasPassados);
                b = d.getTime();
                Date dataMAX30Antes = new Date(b.getTime());
                PrimeiroDiaMes = DateFormat.format(dataMAX30Antes);
                java.sql.Date DatePrimeiro = null;  
                    try {
                        DatePrimeiro = new java.sql.Date(DateFormat.parse(PrimeiroDiaMes).getTime());
                    } catch (ParseException ex) {
                        Logger.getLogger(Impostos1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                System.out.println("Data de Hoje: "+Hoje);
                System.out.println("Primeiro dia do Mês: "+PrimeiroDiaMes);
                
                //Pega dia 1 ano + dias do mês atrás
                Date data365 = new Date();
                Calendar e = Calendar.getInstance();
                e.setTime(data365);
                int anopassado = 365+NumeroDias;
                e.add(Calendar.DATE, -anopassado);
                data365 = e.getTime();
                java.sql.Date data365Antes = new java.sql.Date(data365.getTime());
                
                
                  
                FluxoDAO fdao = new FluxoDAO();
                
                desenhaFluxoDiarioMensal(DatePrimeiro,DateHoje);
               
                float FluxoAteAgoraMensal = fdao.FluxoSomaTotal(DatePrimeiro, DateHoje);
                System.out.println("O fluxo até agora mensal é: "+FluxoAteAgoraMensal);
                float FluxoAteAgoraAnual = fdao.FluxoSomaTotal(data365Antes, DateHoje);
                System.out.println("O fluxo até agora Anual é: "+FluxoAteAgoraAnual);
                
                /*O Simples Nacional implica o recolhimento mensal, mediante documento Único de arrecadação,
                    dos seguintes impostos e contribuições: */
                float IRPJ = 0; //as alíquotas de IRPJ para comércio e indústria variam entre 0,27% e 0,54%.
                float CSLL = 0;
                float PIS = 0;
                float Cofins = 0;
                float IPI = 0;
                float ICMS= 0;
                float ISS = 0;
                float CPP = 0;
                
//                  Receita 12 meses  Alíquota  IRPJ	CSLL	Cofins	PIS/Pasep	CPP	ICMS
                if(FluxoAteAgoraAnual<180000){
                    //Até 180.000,00	4,00%
                                             // 5,50%   3,50%	12,74%	2,76%	41,50%	34,00%
                float valorDAS = (float) (FluxoAteAgoraMensal*0.04);
                IRPJ = (float) (valorDAS*0.055);
                CSLL = (float) (valorDAS*0.035);
                Cofins = (float) (valorDAS*0.1274);
                PIS = (float) (valorDAS*0.0276);
                CPP = (float) (0.415*valorDAS);
                ICMS = (float) (0.34*valorDAS);
                
                jTextField1.setText(String.valueOf(IRPJ).replace(",","."));
                jTextField2.setText(String.valueOf(CSLL).replace(",","."));
                jTextField3.setText(String.valueOf(PIS).replace(",","."));
                jTextField4.setText(String.valueOf(Cofins).replace(",","."));
                jTextField5.setText(String.valueOf(IPI).replace(",","."));
                jTextField6.setText(String.valueOf(ICMS).replace(",","."));
                jTextField7.setText(String.valueOf(ISS).replace(",","."));
                jTextField8.setText(String.valueOf(CPP).replace(",","."));
               
                
                }
                if(FluxoAteAgoraAnual>=180000 && FluxoAteAgoraAnual<360000){
     //De 180.000,01 a 360.000,00	7,30%
                                             // 5,50%   3,50%	12,74%	2,76%	41,50%	34,00%
                float valorDAS = (float) (FluxoAteAgoraMensal*0.073);
                IRPJ = (float) (valorDAS*0.055);
                CSLL = (float) (valorDAS*0.035);
                Cofins = (float) (valorDAS*0.1274);
                PIS = (float) (valorDAS*0.0276);
                CPP = (float) (0.415*valorDAS);
                ICMS = (float) (0.34*valorDAS);
                
                jTextField1.setText(String.valueOf(IRPJ).replace(",","."));
                jTextField2.setText(String.valueOf(CSLL).replace(",","."));
                jTextField3.setText(String.valueOf(PIS).replace(",","."));
                jTextField4.setText(String.valueOf(Cofins).replace(",","."));
                jTextField5.setText(String.valueOf(IPI).replace(",","."));
                jTextField6.setText(String.valueOf(ICMS).replace(",","."));
                jTextField7.setText(String.valueOf(ISS).replace(",","."));
                jTextField8.setText(String.valueOf(CPP).replace(",","."));
                }
                if(FluxoAteAgoraAnual>=360000 && FluxoAteAgoraAnual<720000){
     //De 360.000,00 até 720000	9,50%
                                             //5,50%	3,50%	12,74%	2,76%	42,00%	33,50%
                float valorDAS = (float) (FluxoAteAgoraMensal*0.073);
                IRPJ = (float) (valorDAS*0.055);
                CSLL = (float) (valorDAS*0.035);
                Cofins = (float) (valorDAS*0.1274);
                PIS = (float) (valorDAS*0.0276);
                CPP = (float) (0.42*valorDAS);
                ICMS = (float) (0.355*valorDAS);
                
                jTextField1.setText(String.valueOf(IRPJ).replace(",","."));
                jTextField2.setText(String.valueOf(CSLL).replace(",","."));
                jTextField3.setText(String.valueOf(PIS).replace(",","."));
                jTextField4.setText(String.valueOf(Cofins).replace(",","."));
                jTextField5.setText(String.valueOf(IPI).replace(",","."));
                jTextField6.setText(String.valueOf(ICMS).replace(",","."));
                jTextField7.setText(String.valueOf(ISS).replace(",","."));
                jTextField8.setText(String.valueOf(CPP).replace(",","."));
                }
                if(FluxoAteAgoraAnual>720000 && FluxoAteAgoraAnual<1800000){
     //De 720000 a 1800000	10,70%%
                                             //5,50%	3,50%	12,74%	2,76%	42,00%	33,50%
                float valorDAS = (float) (FluxoAteAgoraMensal*0.073);
                IRPJ = (float) (valorDAS*0.055);
                CSLL = (float) (valorDAS*0.035);
                Cofins = (float) (valorDAS*0.1274);
                PIS = (float) (valorDAS*0.0276);
                CPP = (float) (0.42*valorDAS);
                ICMS = (float) (0.355*valorDAS);
                
                jTextField1.setText(String.valueOf(IRPJ).replace(",","."));
                jTextField2.setText(String.valueOf(CSLL).replace(",","."));
                jTextField3.setText(String.valueOf(PIS).replace(",","."));
                jTextField4.setText(String.valueOf(Cofins).replace(",","."));
                jTextField5.setText(String.valueOf(IPI).replace(",","."));
                jTextField6.setText(String.valueOf(ICMS).replace(",","."));
                jTextField7.setText(String.valueOf(ISS).replace(",","."));
                jTextField8.setText(String.valueOf(CPP).replace(",","."));
                }
                if(FluxoAteAgoraAnual>1800000 && FluxoAteAgoraAnual<3600000){
     //De 1.800.000,01 a 3.600.000,00	14,30%
                                             //5,50%	3,50%	12,74%	2,76%	42,00%	33,50%
                float valorDAS = (float) (FluxoAteAgoraMensal*0.073);
                IRPJ = (float) (valorDAS*0.055);
                CSLL = (float) (valorDAS*0.035);
                Cofins = (float) (valorDAS*0.1274);
                PIS = (float) (valorDAS*0.0276);
                CPP = (float) (0.42*valorDAS);
                ICMS = (float) (0.355*valorDAS);
                
                jTextField1.setText(String.valueOf(IRPJ).replace(",","."));
                jTextField2.setText(String.valueOf(CSLL).replace(",","."));
                jTextField3.setText(String.valueOf(PIS).replace(",","."));
                jTextField4.setText(String.valueOf(Cofins).replace(",","."));
                jTextField5.setText(String.valueOf(IPI).replace(",","."));
                jTextField6.setText(String.valueOf(ICMS).replace(",","."));
                jTextField7.setText(String.valueOf(ISS).replace(",","."));
                jTextField8.setText(String.valueOf(CPP).replace(",","."));
                }
                if(FluxoAteAgoraAnual>3600000 && FluxoAteAgoraAnual<4800000){
     //De 3.600.000,01 a 4.800.000,00	14,30%
                                             //13,50%	10,00%	28,27%	6,13%	42,10%
                float valorDAS = (float) (FluxoAteAgoraMensal*0.073);
                IRPJ = (float) (valorDAS*0.055);
                CSLL = (float) (valorDAS*0.035);
                Cofins = (float) (valorDAS*0.1274);
                PIS = (float) (valorDAS*0.0276);
                CPP = (float) (0.42*valorDAS);
                ICMS = (float) (0.355*valorDAS);
                
                jTextField1.setText(String.valueOf(IRPJ).replace(",","."));
                jTextField2.setText(String.valueOf(CSLL).replace(",","."));
                jTextField3.setText(String.valueOf(PIS).replace(",","."));
                jTextField4.setText(String.valueOf(Cofins).replace(",","."));
                jTextField5.setText(String.valueOf(IPI).replace(",","."));
                jTextField6.setText(String.valueOf(ICMS).replace(",","."));
                jTextField7.setText(String.valueOf(ISS).replace(",","."));
                jTextField8.setText(String.valueOf(CPP).replace(",","."));
                }
                
             if(SalvaLucroBrutoMensal){
                Imposto i = new Imposto();
                ImpostoDAO idao = new ImpostoDAO();
                
                i.setIRPJ(jTextField1.getText());
                i.setCSLL(jTextField2.getText());
                i.setPIS(jTextField3.getText());
                i.setCofins(jTextField4.getText());
                i.setIPI(jTextField5.getText());
                i.setICMS(jTextField6.getText());
                i.setISS(jTextField7.getText());
                i.setCPP(jTextField8.getText());
                    Date dia = new Date();
                    SimpleDateFormat DFormat = new SimpleDateFormat("MMMMM");
                    String Mês = DFormat.format(dia);
                i.setMês(Mês);
                i.setData(DateHoje);
                idao.ArmazenarImpostos(i);
             }
                
                }
            }.start();
    }
   
    private void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setSize(1106,343);
        chartPanel.setVisible(true);
        jPanel8.add(chartPanel);
        //chartPanel.setSize(jPanel6.getWidth(),jPanel6.getHeight()); 
        jPanel8.updateUI();

        //setTitle("Impostos por mês (Ultimos 12)");
        //setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    
    }
    
    private XYDataset createDataset() {

        int i=12;
        ImpostoDAO idao = new ImpostoDAO();
        
       /*XYSeries series1 = new XYSeries("ICMS");
        series1.add(01, 250);
        series1.add(02, 350);
        series1.add(03, 450);
        series1.add(04, 550);
        series1.add(05, 356);
        series1.add(06, 200);
        series1.add(07, 250);
        series1.add(8, 350);
        series1.add(9, 450);
        series1.add(10, 550);
        series1.add(11, 356);
        series1.add(12, 200);
        
        XYSeries series2 = new XYSeries("PIS");
        series2.add(01, 567);
        series2.add(02, 612);
        series2.add(03, 800);
        series2.add(04, 980);
        series2.add(05, 1210);
        series2.add(06, 2350); 
        series2.add(07, 567);
        series2.add(8, 612);
        series2.add(9, 800);
        series2.add(10, 980);
        series2.add(11, 1210);
        series2.add(12, 2350); 
        
        XYSeries series3 = new XYSeries("COFINS");
        series3.add(01, 50);
        series3.add(02, 100);
        series3.add(03, 200);
        series3.add(04, 300);
        series3.add(05, 100);
        series3.add(06, 50);   
        series3.add(07, 50);
        series3.add(8, 100);
        series3.add(9, 200);
        series3.add(10, 300);
        series3.add(11, 100);
        series3.add(12, 50);   */
       
       XYSeries series1 = new XYSeries("ICMS");
       XYSeries series2 = new XYSeries("PIS");
       XYSeries series3 = new XYSeries("COFINS");
       XYSeries series4 = new XYSeries("CPP");
       XYSeries series5 = new XYSeries("CSLL");
       XYSeries series6 = new XYSeries("IPI");
       XYSeries series7 = new XYSeries("ISS");
           for(Imposto a:idao.readImpostos()){
        series1.add(i,Float.valueOf(a.getICMS()));
        series2.add(i, Float.valueOf(a.getPIS()));
        series3.add(i, Float.valueOf(a.getCofins()));
        series4.add(i, Float.valueOf(a.getCPP()));
        series5.add(i, Float.valueOf(a.getCSLL()));
        series6.add(i, Float.valueOf(a.getIPI()));
        series7.add(i, Float.valueOf(a.getISS()));
        if(i==1){break;}
        i--;
                }
       

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        dataset.addSeries(series4);
        dataset.addSeries(series5);
        dataset.addSeries(series6);
        dataset.addSeries(series7);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Impostos por mês (Ultimos 12)", 
                "Mês", 
                "Valor Pago (R$)", 
                dataset, 
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false 
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f)); 
        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesStroke(2, new BasicStroke(2.0f)); 
        renderer.setSeriesPaint(3, Color.BLACK);
        renderer.setSeriesStroke(3, new BasicStroke(2.0f)); 
        renderer.setSeriesPaint(4, Color.YELLOW);
        renderer.setSeriesStroke(4, new BasicStroke(2.0f)); 
        renderer.setSeriesPaint(5, Color.ORANGE);
        renderer.setSeriesStroke(5, new BasicStroke(2.0f));
        renderer.setSeriesPaint(6, Color.PINK);
        renderer.setSeriesStroke(6, new BasicStroke(2.0f)); 

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Impostos por mês (Ultimos 12)",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;

    }
    
    public void desenhaFluxoDiario(){
    
        FluxoDAO fdao = new FluxoDAO();
     DefaultCategoryDataset dataset = new DefaultCategoryDataset();
     List<String> a = new ArrayList();
        for(Fluxo f:fdao.readfluxo()){
            
           dataset.setValue(Float.parseFloat(f.getFluxoCaixa()),"Historico de Vendas 12 meses", f.getFechamentoData().toString());
            //System.err.println("Fluxo: "+Float.parseFloat(f.getFluxoCaixa())+"\nData: "+f.getFechamentoData().toString()+"2");
            
        }
        JFreeChart criarGrafico = ChartFactory.createBarChart3D("Historico de vendas","Fluxos de Caixas","Valor", dataset, PlotOrientation.VERTICAL, true,true,false);
        ChartPanel myChartPanel = new ChartPanel(criarGrafico, true);
        myChartPanel.setSize(550, 350);
        myChartPanel.setVisible(true);
        final CategoryPlot plot = criarGrafico.getCategoryPlot();
        final CategoryItemRenderer renderer1 = plot.getRenderer();
        
        renderer1.setSeriesPaint(0, Color.blue);
        plot.setRenderer(renderer1);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        
        jPanel5.add(myChartPanel);
        jPanel5.updateUI();
        
        
    }
    
    public void desenhaFluxoDiarioMensal(Date inicio, Date hoje){
    
         FluxoDAO fdao = new FluxoDAO();
     DefaultCategoryDataset dataset = new DefaultCategoryDataset();
     List<String> a = new ArrayList();
        for(Fluxo f:fdao.readfluxo()){
            
            dataset.setValue(Float.parseFloat(f.getFluxoCaixa()),"Historico de Vendas Mês Atual", f.getFechamentoData().toString());
            //System.err.println("Fluxo: "+Float.parseFloat(f.getFluxoCaixa())+"\nData: "+f.getFechamentoData().toString()+"2");
            
        }
        //dataset.addValue(8.00,"Fluxo dia","4");
        //dataset.addValue(25.00,"Fluxo dia","5");
        //dataset.addValue(100.00,"Fluxo dia","6");
        
        JFreeChart criarGrafico = ChartFactory.createLineChart("Historico de vendas","dia","Valor", dataset, PlotOrientation.VERTICAL, true,true,false);
        //CategoryPlot a = (CategoryPlot)criarGrafico.getPlot();
        //a.setForegroundAlpha(TOP_ALIGNMENT);
        ChartPanel myChartPanel = new ChartPanel(criarGrafico, true);
       // myChartPanel.setSize(jPanel4.getWidth(),jPanel4.getHeight());
        myChartPanel.setSize(550, 350);
        myChartPanel.setVisible(true);
        
        final CategoryPlot plot = criarGrafico.getCategoryPlot();
        final CategoryItemRenderer renderer1 = plot.getRenderer();
        
        renderer1.setSeriesPaint(0, Color.blue);
        plot.setRenderer(renderer1);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
        
        jPanel6.add(myChartPanel);
        jPanel6.updateUI();
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        Label1 = new javax.swing.JLabel();
        Label2 = new javax.swing.JLabel();
        Label3 = new javax.swing.JLabel();
        Label4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        JGeral = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        JCST = new javax.swing.JPanel();
        JPIS = new javax.swing.JPanel();
        JCOFINS = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setBackground(new java.awt.Color(150, 0, 20));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Visão Geral");
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

        jButton2.setBackground(new java.awt.Color(150, 0, 20));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Detalhes por cst");
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

        jButton3.setBackground(new java.awt.Color(150, 0, 20));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Detalhes por PIS");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setDefaultCapable(false);
        jButton3.setFocusPainted(false);
        jButton3.setOpaque(true);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(150, 0, 20));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Detalhes por COFINS");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setDefaultCapable(false);
        jButton4.setFocusPainted(false);
        jButton4.setOpaque(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Label1.setBackground(new java.awt.Color(255, 255, 255));
        Label1.setOpaque(true);

        Label2.setBackground(new java.awt.Color(51, 51, 51));
        Label2.setOpaque(true);

        Label3.setBackground(new java.awt.Color(51, 51, 51));
        Label3.setOpaque(true);

        Label4.setBackground(new java.awt.Color(51, 51, 51));
        Label4.setOpaque(true);

        jScrollPane1.setBorder(null);

        JGeral.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("IRPJ – Imposto de Renda de Pessoa Jurídica");
        jLabel1.setToolTipText("Este é o Imposto de Renda da empresa e é calculado sobre o faturamento que ela teve nos últimos 12 meses. A alíquota é de 15% sobre o lucro real, presumido ou arbitrado. Para os participantes do Simples Nacional, a taxa varia de 0% a 0,54% para as atividades de comércio ou indústria e pode chegar a 0,84% para prestadoras de serviço. É importante ressaltar que as prestadoras de serviço que se enquadram nas determinações do Anexo IV da Lei Complementar 123 podem pagar até 6,2% de alíquota, dependendo do faturamento anual (que pode ser de até R$ 3,6 milhões).");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("CSLL – Contribuição Social sobre o Lucro Líquido");
        jLabel2.setToolTipText("Esta contribuição é destinada para a contribuição social e acompanha o regime de tributação escolhido para o recolhimento do IRPJ. A taxa é de 9% para as empresas, com exceção das instituições financeiras, de seguros privados e de capitalização, casos em que a alíquota chega a 15%. Para os participantes do Simples que atuam no comércio e na indústria, a taxa é igual à do IRPJ e pode chegar a 0,79% para prestadores de serviços e 2,53% para as que estão inclusas nas determinações do Anexo IV.");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("PIS/PASEP – Programa de Integração Social / Programa de Formação do Patrimônio do Servidor Público");
        jLabel3.setToolTipText("O Programa de Integração Social (PIS) e o Programa de Formação do Patrimônio do Servidor Público (Pasep) são benefícios pagos para o trabalhador de empresa privada e do setor público, respectivamente. Essa contribuição federal é paga mensalmente sobre o faturamento da empresa ou da folha de pagamento, variando de acordo com a atividade exercida. Paras os inscritos no Simples Nacional, a alíquota chega a 0,38% no comércio e na indústria e a 0,57% para serviços e para aquelas que estão enquadradas no Anexo IV");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Cofins – Contribuição para o Financiamento da Seguridade Social");
        jLabel4.setToolTipText("Também é um imposto federal, calculado sobre a receita bruta das empresas, e é destinado para fundos de previdência e assistência social e da saúde pública. O cálculo depende do regime tributário em que a empresa está inscrita e se ela é optante ou não da incidência cumulativa, de modo que a alíquota pode ser de 3% ou 7,6%. Para os optantes do Simples Nacional, a taxa vai a 1,6% para o comércio e a indústria, a 2,42% para serviços e a 2,63% para serviços do anexo IV.");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("IPI – Imposto sobre Produtos Industrializados");
        jLabel5.setToolTipText("O IPI é o tributo cobrado das empresas que produzem ou importam produtos, por conta do desembaraço aduaneiro de itens vindos do exterior ou a saída deles do estabelecimento industrial. A alíquota é calculada sobre o preço de venda do produto e as taxas são definidas na Tabela do IPI (TIPI). Para o Simples, existe uma taxa padrão de 0,5%, cobrada apenas das indústrias.");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("ICMS – Imposto sobre Circulação de Mercadorias e Serviços");
        jLabel6.setToolTipText("Este imposto estadual é cobrado sempre que há movimentação de produtos entre as unidades federativas. Cada estado tem sua alíquota, que varia de 7% a 18%. Para saber melhor sobre o assunto é importante conferir o site do governo de cada estado. Os inscritos no regime do Simples Nacional pagam uma alíquota de 1,25% a 3,95% se forem do comércio ou da indústria.");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("ISS – Imposto sobre Serviços");
        jLabel7.setToolTipText("O ISS é um imposto municipal, cuja base de cálculo é o valor do serviço prestado. As regras variam de acordo com cada município e a alíquota pode chegar a 5%. No caso do Simples Nacional, essa taxa varia de 2% a 4,65% quando o faturamento chega a R$ 1,8 milhão. Quem apresenta faturamento maior que esse valor deve pagar uma taxa de 5%. É importante lembrar que profissionais autônomos também devem pagar esse imposto.");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("CPP – Contribuição Patronal Previdenciária");
        jLabel8.setToolTipText("Este é outro imposto pago pelas empresas para a seguridade social. A alíquota fica entre 2,75% e 4,6% para comércio e indústria e entre 4% e 7,83% para serviços no caso dos optantes do Simples Nacional. Para os demais regimes tributários, a taxa é de 20%, calculada sobre a folha de pagamento.");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(150, 0, 20));
        jLabel9.setText("Visão Geral dos Valores a Pagar");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(150, 0, 20));
        jLabel10.setText("O Simples Nacional implica o recolhimento mensal, mediante DAS, dos seguintes impostos e contribuições: ");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(150, 0, 20));
        jLabel12.setText("Mensal");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(150, 0, 20));
        jLabel13.setText("Mensal");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(150, 0, 20));
        jLabel14.setText("Mensal");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(150, 0, 20));
        jLabel15.setText("Mensal");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(150, 0, 20));
        jLabel16.setText("Mensal");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(150, 0, 20));
        jLabel17.setText("Mensal");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jPanel5.setPreferredSize(new java.awt.Dimension(550, 350));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Selecione a Loja");

        jPanel6.setPreferredSize(new java.awt.Dimension(550, 350));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sede" }));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(150, 0, 20));
        jLabel22.setText("Gráficos");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(150, 0, 20));
        jLabel19.setText("Mensal");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(150, 0, 20));
        jLabel20.setText("Mensal");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JGeralLayout = new javax.swing.GroupLayout(JGeral);
        JGeral.setLayout(JGeralLayout);
        JGeralLayout.setHorizontalGroup(
            JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JGeralLayout.createSequentialGroup()
                        .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JGeralLayout.createSequentialGroup()
                                    .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(JGeralLayout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel19))
                                        .addGroup(JGeralLayout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel20))
                                        .addGroup(JGeralLayout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel12))
                                        .addGroup(JGeralLayout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel13))
                                        .addGroup(JGeralLayout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel14))
                                        .addGroup(JGeralLayout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel15))
                                        .addGroup(JGeralLayout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel16))
                                        .addGroup(JGeralLayout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel17))
                                        .addComponent(jLabel10))
                                    .addGap(78, 78, 78)
                                    .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JGeralLayout.createSequentialGroup()
                                    .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(JGeralLayout.createSequentialGroup()
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel22)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        JGeralLayout.setVerticalGroup(
            JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel13)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel14)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel15)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel16)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel17)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JGeralLayout.createSequentialGroup()
                        .addGroup(JGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JGeralLayout.createSequentialGroup()
                                .addComponent(jComboBox1)
                                .addGap(1, 1, 1)))
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout JCSTLayout = new javax.swing.GroupLayout(JCST);
        JCST.setLayout(JCSTLayout);
        JCSTLayout.setHorizontalGroup(
            JCSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 940, Short.MAX_VALUE)
        );
        JCSTLayout.setVerticalGroup(
            JCSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 967, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JPISLayout = new javax.swing.GroupLayout(JPIS);
        JPIS.setLayout(JPISLayout);
        JPISLayout.setHorizontalGroup(
            JPISLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 940, Short.MAX_VALUE)
        );
        JPISLayout.setVerticalGroup(
            JPISLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 967, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JCOFINSLayout = new javax.swing.GroupLayout(JCOFINS);
        JCOFINS.setLayout(JCOFINSLayout);
        JCOFINSLayout.setHorizontalGroup(
            JCOFINSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 940, Short.MAX_VALUE)
        );
        JCOFINSLayout.setVerticalGroup(
            JCOFINSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 967, Short.MAX_VALUE)
        );

        jLayeredPane1.setLayer(JGeral, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(JCST, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(JPIS, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(JCOFINS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JGeral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(JCST, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(JPIS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(JCOFINS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JGeral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(JCST, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(JPIS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(JCOFINS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 940, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLayeredPane1))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 967, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLayeredPane1))
        );

        jScrollPane1.setViewportView(jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Label1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Label2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Label3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Label4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label2, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label4, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Label1.setBackground(new java.awt.Color(255,255,255));
        Label2.setBackground(new java.awt.Color(51,51,51));
        Label3.setBackground(new java.awt.Color(51,51,51));
        Label4.setBackground(new java.awt.Color(51,51,51));

        JGeral.setVisible(true);
        JCST.setVisible(false);
        JPIS.setVisible(false);
        JCOFINS.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Label1.setBackground(new java.awt.Color(51,51,51));
        Label2.setBackground(new java.awt.Color(255,255,255));
        Label3.setBackground(new java.awt.Color(51,51,51));
        Label4.setBackground(new java.awt.Color(51,51,51));

        JGeral.setVisible(false);
        JCST.setVisible(true);
        JPIS.setVisible(false);
        JCOFINS.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        Label1.setBackground(new java.awt.Color(51,51,51));
        Label2.setBackground(new java.awt.Color(51,51,51));
        Label3.setBackground(new java.awt.Color(255,255,255));
        Label4.setBackground(new java.awt.Color(51,51,51));

        JGeral.setVisible(false);
        JCST.setVisible(false);
        JPIS.setVisible(true);
        JCOFINS.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        Label1.setBackground(new java.awt.Color(51,51,51));
        Label2.setBackground(new java.awt.Color(51,51,51));
        Label3.setBackground(new java.awt.Color(51,51,51));
        Label4.setBackground(new java.awt.Color(255,255,255));

        JGeral.setVisible(false);
        JCST.setVisible(false);
        JPIS.setVisible(false);
        JCOFINS.setVisible(true);                  // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JCOFINS;
    private javax.swing.JPanel JCST;
    private javax.swing.JPanel JGeral;
    private javax.swing.JPanel JPIS;
    private javax.swing.JLabel Label1;
    private javax.swing.JLabel Label2;
    private javax.swing.JLabel Label3;
    private javax.swing.JLabel Label4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
