package gerenciador;


import Configuração.ConfPrinc;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import model.bean.Caixa;
import model.bean.Produto;
import model.bean.Vendidos;
import model.dao.CaixaDAO;
import model.dao.ProdutoDAO;
import model.dao.VendidosDAO;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource;
import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import model.bean.Fluxo;
import model.bean.Fornecedor;
import model.bean.Meta;
import model.bean.Vendas;
import model.dao.FluxoDAO;
import model.dao.FornecedorDAO;
import model.dao.MetaDAO;
import model.dao.VendasDAO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RectangleInsets;
import org.joda.time.DateTime;
import org.joda.time.Days;
import static org.joda.time.format.ISODateTimeFormat.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import gerenciador.Modulos.Estatistica;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.InputMismatchException;
import model.bean.Cliente;
import model.bean.Despesas;
import model.bean.ServicoProduto;
import model.bean.Servicos;
import model.dao.ClienteDAO;
import model.dao.DespesasDAO;
import model.dao.ServicoProdutoDAO;
import model.dao.ServicosDAO;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import gerenciador.Modulos.Caixas;
import gerenciador.Modulos.Clientes;
import gerenciador.Modulos.ConfiguraçãoGeral;
import gerenciador.Modulos.Estoque;
import gerenciador.Modulos.Fornecedores;
import gerenciador.Modulos.Funcionarios;
import gerenciador.Modulos.Impostos1;
import gerenciador.Modulos.Logo;
import gerenciador.Modulos.NotasFiscais;
import gerenciador.Modulos.Promoções;
import gerenciador.Modulos.RH;
import gerenciador.Modulos.Suporte;
import java.awt.Cursor;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Marcos Gabriel by Softsaj
 */
public class TelaGestão extends javax.swing.JFrame {

    int quantidadedecaixas=1;
    Impostos1 impostos = new Impostos1();
    Funcionarios funcionarios = new Funcionarios();
    RH rh = new RH();
    Promoções promo = new Promoções();
    
    public TelaGestão() {
        initComponents();
         estilojpanel();
        TelaInicial();
        getContentPane().setBackground(new java.awt.Color(51,51,51));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
       // jScrollPane2.getVerticalScrollBar().setUI();
      
        
    }
    
   public void TelaInicial(){
       
       Logo f = new Logo();
        jDesktopPane1.add(f);
        ((BasicInternalFrameUI)f.getUI()).setNorthPane(null); //retirar o painel superior
        f.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        try {
            f.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.setVisible(true);
   
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
        SwingUtilities.updateComponentTreeUI(jScrollPane2);
           
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Bestatistica = new javax.swing.JButton();
        Bfinanceiro = new javax.swing.JButton();
        Bfornecedores = new javax.swing.JButton();
        Bentregas = new javax.swing.JButton();
        Bestoque = new javax.swing.JButton();
        Bfuncionarios = new javax.swing.JButton();
        Bclientes = new javax.swing.JButton();
        Brh = new javax.swing.JButton();
        Bsegurança = new javax.swing.JButton();
        Becommerce = new javax.swing.JButton();
        Bpromoções = new javax.swing.JButton();
        Bimpostos = new javax.swing.JButton();
        Bajuda = new javax.swing.JButton();
        Bsuporte = new javax.swing.JButton();
        Bimpressora = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Bsuporte1 = new javax.swing.JButton();
        Bsuporte2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("eGDV");

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jScrollPane2.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane2.setBorder(null);
        jScrollPane2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        Bestatistica.setBackground(new java.awt.Color(150, 0, 20));
        Bestatistica.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bestatistica.setForeground(new java.awt.Color(255, 255, 255));
        Bestatistica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/pie-chart.png"))); // NOI18N
        Bestatistica.setText("   Estatisticas");
        Bestatistica.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        Bestatistica.setBorderPainted(false);
        Bestatistica.setContentAreaFilled(false);
        Bestatistica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bestatistica.setDefaultCapable(false);
        Bestatistica.setFocusPainted(false);
        Bestatistica.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Bestatistica.setOpaque(true);
        Bestatistica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BestatisticaActionPerformed(evt);
            }
        });

        Bfinanceiro.setBackground(new java.awt.Color(150, 0, 20));
        Bfinanceiro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bfinanceiro.setForeground(new java.awt.Color(255, 255, 255));
        Bfinanceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/dollar-symbol.png"))); // NOI18N
        Bfinanceiro.setText("      Financeiro");
        Bfinanceiro.setBorderPainted(false);
        Bfinanceiro.setContentAreaFilled(false);
        Bfinanceiro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bfinanceiro.setDefaultCapable(false);
        Bfinanceiro.setFocusPainted(false);
        Bfinanceiro.setOpaque(true);
        Bfinanceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BfinanceiroActionPerformed(evt);
            }
        });

        Bfornecedores.setBackground(new java.awt.Color(150, 0, 20));
        Bfornecedores.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bfornecedores.setForeground(new java.awt.Color(255, 255, 255));
        Bfornecedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/delivery-truck.png"))); // NOI18N
        Bfornecedores.setText(" Fornecedores ");
        Bfornecedores.setBorderPainted(false);
        Bfornecedores.setContentAreaFilled(false);
        Bfornecedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bfornecedores.setDefaultCapable(false);
        Bfornecedores.setFocusPainted(false);
        Bfornecedores.setOpaque(true);
        Bfornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BfornecedoresActionPerformed(evt);
            }
        });

        Bentregas.setBackground(new java.awt.Color(204, 204, 204));
        Bentregas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bentregas.setForeground(new java.awt.Color(255, 255, 255));
        Bentregas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/delivery.png"))); // NOI18N
        Bentregas.setText("        Entregas");
        Bentregas.setBorderPainted(false);
        Bentregas.setContentAreaFilled(false);
        Bentregas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bentregas.setDefaultCapable(false);
        Bentregas.setFocusPainted(false);
        Bentregas.setOpaque(true);
        Bentregas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BentregasActionPerformed(evt);
            }
        });

        Bestoque.setBackground(new java.awt.Color(150, 0, 20));
        Bestoque.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bestoque.setForeground(new java.awt.Color(255, 255, 255));
        Bestoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/box.png"))); // NOI18N
        Bestoque.setText("         Estoque");
        Bestoque.setBorderPainted(false);
        Bestoque.setContentAreaFilled(false);
        Bestoque.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bestoque.setDefaultCapable(false);
        Bestoque.setFocusPainted(false);
        Bestoque.setOpaque(true);
        Bestoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BestoqueActionPerformed(evt);
            }
        });

        Bfuncionarios.setBackground(new java.awt.Color(204, 204, 204));
        Bfuncionarios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bfuncionarios.setForeground(new java.awt.Color(255, 255, 255));
        Bfuncionarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/specialist-user.png"))); // NOI18N
        Bfuncionarios.setText("  Funcionarios");
        Bfuncionarios.setBorderPainted(false);
        Bfuncionarios.setContentAreaFilled(false);
        Bfuncionarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bfuncionarios.setDefaultCapable(false);
        Bfuncionarios.setFocusPainted(false);
        Bfuncionarios.setOpaque(true);
        Bfuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BfuncionariosActionPerformed(evt);
            }
        });

        Bclientes.setBackground(new java.awt.Color(150, 0, 20));
        Bclientes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bclientes.setForeground(new java.awt.Color(255, 255, 255));
        Bclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/user.png"))); // NOI18N
        Bclientes.setText("         Clientes");
        Bclientes.setBorderPainted(false);
        Bclientes.setContentAreaFilled(false);
        Bclientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bclientes.setDefaultCapable(false);
        Bclientes.setFocusPainted(false);
        Bclientes.setOpaque(true);
        Bclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BclientesActionPerformed(evt);
            }
        });

        Brh.setBackground(new java.awt.Color(204, 204, 204));
        Brh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Brh.setForeground(new java.awt.Color(255, 255, 255));
        Brh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/teamwork.png"))); // NOI18N
        Brh.setText("                RH");
        Brh.setBorderPainted(false);
        Brh.setContentAreaFilled(false);
        Brh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Brh.setDefaultCapable(false);
        Brh.setFocusPainted(false);
        Brh.setOpaque(true);
        Brh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrhActionPerformed(evt);
            }
        });

        Bsegurança.setBackground(new java.awt.Color(204, 204, 204));
        Bsegurança.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bsegurança.setForeground(new java.awt.Color(255, 255, 255));
        Bsegurança.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/surveillance-camera.png"))); // NOI18N
        Bsegurança.setText("      Segurança");
        Bsegurança.setBorderPainted(false);
        Bsegurança.setContentAreaFilled(false);
        Bsegurança.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bsegurança.setDefaultCapable(false);
        Bsegurança.setFocusPainted(false);
        Bsegurança.setOpaque(true);
        Bsegurança.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BsegurançaActionPerformed(evt);
            }
        });

        Becommerce.setBackground(new java.awt.Color(204, 204, 204));
        Becommerce.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Becommerce.setForeground(new java.awt.Color(255, 255, 255));
        Becommerce.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/laptop.png"))); // NOI18N
        Becommerce.setText("   E-commerce");
        Becommerce.setBorderPainted(false);
        Becommerce.setContentAreaFilled(false);
        Becommerce.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Becommerce.setDefaultCapable(false);
        Becommerce.setFocusPainted(false);
        Becommerce.setOpaque(true);
        Becommerce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BecommerceActionPerformed(evt);
            }
        });

        Bpromoções.setBackground(new java.awt.Color(204, 204, 204));
        Bpromoções.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bpromoções.setForeground(new java.awt.Color(255, 255, 255));
        Bpromoções.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/megaphone.png"))); // NOI18N
        Bpromoções.setText("     Promoções");
        Bpromoções.setBorderPainted(false);
        Bpromoções.setContentAreaFilled(false);
        Bpromoções.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bpromoções.setDefaultCapable(false);
        Bpromoções.setFocusPainted(false);
        Bpromoções.setOpaque(true);
        Bpromoções.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BpromoçõesActionPerformed(evt);
            }
        });

        Bimpostos.setBackground(new java.awt.Color(150, 0, 20));
        Bimpostos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bimpostos.setForeground(new java.awt.Color(255, 255, 255));
        Bimpostos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/tax.png"))); // NOI18N
        Bimpostos.setText("      Impostos");
        Bimpostos.setBorderPainted(false);
        Bimpostos.setContentAreaFilled(false);
        Bimpostos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bimpostos.setDefaultCapable(false);
        Bimpostos.setFocusPainted(false);
        Bimpostos.setOpaque(true);
        Bimpostos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BimpostosActionPerformed(evt);
            }
        });

        Bajuda.setBackground(new java.awt.Color(150, 0, 20));
        Bajuda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bajuda.setForeground(new java.awt.Color(255, 255, 255));
        Bajuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/info.png"))); // NOI18N
        Bajuda.setText("             Ajuda");
        Bajuda.setBorderPainted(false);
        Bajuda.setContentAreaFilled(false);
        Bajuda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bajuda.setDefaultCapable(false);
        Bajuda.setFocusPainted(false);
        Bajuda.setOpaque(true);
        Bajuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BajudaActionPerformed(evt);
            }
        });

        Bsuporte.setBackground(new java.awt.Color(150, 0, 20));
        Bsuporte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bsuporte.setForeground(new java.awt.Color(255, 255, 255));
        Bsuporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/call-center-worker-with-headset.png"))); // NOI18N
        Bsuporte.setText("    Suport 24h");
        Bsuporte.setToolTipText("");
        Bsuporte.setBorderPainted(false);
        Bsuporte.setContentAreaFilled(false);
        Bsuporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bsuporte.setDefaultCapable(false);
        Bsuporte.setFocusPainted(false);
        Bsuporte.setOpaque(true);
        Bsuporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BsuporteActionPerformed(evt);
            }
        });

        Bimpressora.setBackground(new java.awt.Color(150, 0, 20));
        Bimpressora.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bimpressora.setForeground(new java.awt.Color(255, 255, 255));
        Bimpressora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/printer-printing-document (1).png"))); // NOI18N
        Bimpressora.setText("    Impressora");
        Bimpressora.setBorderPainted(false);
        Bimpressora.setContentAreaFilled(false);
        Bimpressora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bimpressora.setDefaultCapable(false);
        Bimpressora.setFocusPainted(false);
        Bimpressora.setOpaque(true);
        Bimpressora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BimpressoraActionPerformed(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setToolTipText("");
        jButton20.setBorderPainted(false);
        jButton20.setContentAreaFilled(false);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/header.png"))); // NOI18N

        Bsuporte1.setBackground(new java.awt.Color(150, 0, 20));
        Bsuporte1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bsuporte1.setForeground(new java.awt.Color(255, 255, 255));
        Bsuporte1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/shopping-cart.png"))); // NOI18N
        Bsuporte1.setText("             Caixas");
        Bsuporte1.setBorderPainted(false);
        Bsuporte1.setContentAreaFilled(false);
        Bsuporte1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bsuporte1.setDefaultCapable(false);
        Bsuporte1.setFocusPainted(false);
        Bsuporte1.setOpaque(true);
        Bsuporte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bsuporte1ActionPerformed(evt);
            }
        });

        Bsuporte2.setBackground(new java.awt.Color(150, 0, 20));
        Bsuporte2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bsuporte2.setForeground(new java.awt.Color(255, 255, 255));
        Bsuporte2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/adf.png"))); // NOI18N
        Bsuporte2.setText("   Notas Fiscais");
        Bsuporte2.setBorderPainted(false);
        Bsuporte2.setContentAreaFilled(false);
        Bsuporte2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bsuporte2.setDefaultCapable(false);
        Bsuporte2.setFocusPainted(false);
        Bsuporte2.setOpaque(true);
        Bsuporte2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bsuporte2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bestatistica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bfinanceiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bfornecedores, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
            .addComponent(Bentregas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bestoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bfuncionarios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bclientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Brh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bimpostos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bpromoções, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Becommerce, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bsegurança, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bajuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bsuporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bimpressora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(Bsuporte1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bsuporte2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Bestatistica, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(Bfornecedores)
                .addGap(3, 3, 3)
                .addComponent(Bestoque)
                .addGap(3, 3, 3)
                .addComponent(Bclientes)
                .addGap(3, 3, 3)
                .addComponent(Bimpressora)
                .addGap(3, 3, 3)
                .addComponent(Bfinanceiro)
                .addGap(3, 3, 3)
                .addComponent(Bfuncionarios)
                .addGap(3, 3, 3)
                .addComponent(Bentregas)
                .addGap(3, 3, 3)
                .addComponent(Brh)
                .addGap(3, 3, 3)
                .addComponent(Bimpostos)
                .addGap(3, 3, 3)
                .addComponent(Bpromoções)
                .addGap(3, 3, 3)
                .addComponent(Becommerce)
                .addGap(3, 3, 3)
                .addComponent(Bsegurança)
                .addGap(3, 3, 3)
                .addComponent(Bsuporte2)
                .addGap(3, 3, 3)
                .addComponent(Bsuporte1)
                .addGap(3, 3, 3)
                .addComponent(Bajuda)
                .addGap(3, 3, 3)
                .addComponent(Bsuporte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jButton20))
        );

        jPanel5.setBackground(new java.awt.Color(150, 0, 20));
        jPanel5.setToolTipText("");

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/shut-down-icon.png"))); // NOI18N
        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton15.setFocusPainted(false);

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/cart-of-ecommerce.png"))); // NOI18N
        jButton16.setBorderPainted(false);
        jButton16.setContentAreaFilled(false);
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton16.setFocusPainted(false);

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/alarm.png"))); // NOI18N
        jButton17.setBorderPainted(false);
        jButton17.setContentAreaFilled(false);
        jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton17.setFocusPainted(false);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("eGDV 4.0");

        jButton18.setBackground(new java.awt.Color(150, 0, 20));
        jButton18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gerenciador/Imagens/employee.png"))); // NOI18N
        jButton18.setText("Configurações Gerais");
        jButton18.setContentAreaFilled(false);
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton18.setDefaultCapable(false);
        jButton18.setFocusPainted(false);
        jButton18.setOpaque(true);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 567, Short.MAX_VALUE)
                .addComponent(jButton18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton15)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
            .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDesktopPane1))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDesktopPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BestatisticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BestatisticaActionPerformed

        Bestatistica.setBackground(new java.awt.Color(51,51,51));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
        
        Estatistica estatistica = new Estatistica();
        jDesktopPane1.add(estatistica);
        ((BasicInternalFrameUI)estatistica.getUI()).setNorthPane(null); //retirar o painel superior
        estatistica.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        try {
            estatistica.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
        estatistica.setVisible(true);
        
    }//GEN-LAST:event_BestatisticaActionPerformed

    private void BfinanceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BfinanceiroActionPerformed
        Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(51,51,51));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
       
    }//GEN-LAST:event_BfinanceiroActionPerformed

    private void BclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BclientesActionPerformed
        
        Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(51,51,51));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
        
        Clientes f = new Clientes();
        jDesktopPane1.add(f);
        ((BasicInternalFrameUI)f.getUI()).setNorthPane(null); //retirar o painel superior
        //f.setBorder(null);//retirar bordas
        f.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        try {
            f.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.setVisible(true);
   
    }//GEN-LAST:event_BclientesActionPerformed

    private void BecommerceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BecommerceActionPerformed
       
        Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(51,51,51));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
        
    }//GEN-LAST:event_BecommerceActionPerformed

    private void BajudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BajudaActionPerformed
        
        Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(51,51,51));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
        
         /*Wiki f = new Wiki();
        jDesktopPane1.add(f);
        ((BasicInternalFrameUI)f.getUI()).setNorthPane(null); //retirar o painel superior
        //f.setBorder(null);//retirar bordas
        f.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        try {
            f.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.setVisible(true);*/
    }//GEN-LAST:event_BajudaActionPerformed

    private void BsuporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BsuporteActionPerformed
        
        
        Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(51,51,51));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
        
        Suporte f = new Suporte();
        jDesktopPane1.add(f);
        ((BasicInternalFrameUI)f.getUI()).setNorthPane(null); //retirar o painel superior
        //f.setBorder(null);//retirar bordas
        f.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        try {
            f.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.setVisible(true);
    
    }//GEN-LAST:event_BsuporteActionPerformed

    private void BimpressoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BimpressoraActionPerformed
        
        Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(51,51,51));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
        
    }//GEN-LAST:event_BimpressoraActionPerformed

    private void BfornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BfornecedoresActionPerformed
        
        Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(51,51,51));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
        
        
        
        Fornecedores f = new Fornecedores();
        jDesktopPane1.add(f);
        ((BasicInternalFrameUI)f.getUI()).setNorthPane(null); //retirar o painel superior
        f.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        try {
            f.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.setVisible(true);
       
    }//GEN-LAST:event_BfornecedoresActionPerformed

    private void BentregasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BentregasActionPerformed
        Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(51,51,51));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
    }//GEN-LAST:event_BentregasActionPerformed

    private void BestoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BestoqueActionPerformed
        
        Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(51,51,51));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
        
        Estoque f = new Estoque();
        jDesktopPane1.add(f);
        ((BasicInternalFrameUI)f.getUI()).setNorthPane(null); //retirar o painel superior
        f.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        try {
            f.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.setVisible(true);
    }//GEN-LAST:event_BestoqueActionPerformed

    private void BfuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BfuncionariosActionPerformed
       Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(51,51,51));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
        
        try {
        jDesktopPane1.add(funcionarios);
        ((BasicInternalFrameUI)funcionarios.getUI()).setNorthPane(null); //retirar o painel superior
        funcionarios.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        funcionarios.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
        }
        funcionarios.setVisible(true);
        
    }//GEN-LAST:event_BfuncionariosActionPerformed

    private void BrhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrhActionPerformed
        
        Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(51,51,51));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
        
        
        jDesktopPane1.add(rh);
        ((BasicInternalFrameUI)rh.getUI()).setNorthPane(null); //retirar o painel superior
        rh.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        try {
            rh.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
        rh.setVisible(true);
    }//GEN-LAST:event_BrhActionPerformed

    private void BimpostosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BimpostosActionPerformed
        
        Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(51,51,51));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
        
        
        try {
        jDesktopPane1.add(impostos);
        ((BasicInternalFrameUI)impostos.getUI()).setNorthPane(null); //retirar o painel superior
        impostos.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        impostos.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
        }
        impostos.setVisible(true);
        
        
        
    }//GEN-LAST:event_BimpostosActionPerformed

    private void BpromoçõesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BpromoçõesActionPerformed
        Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(51,51,51));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
        
        try {
        jDesktopPane1.add(promo);
        ((BasicInternalFrameUI)promo.getUI()).setNorthPane(null); //retirar o painel superior
        promo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        promo.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
        }
        promo.setVisible(true);
        
    }//GEN-LAST:event_BpromoçõesActionPerformed

    private void BsegurançaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BsegurançaActionPerformed
       Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(51,51,51));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
    }//GEN-LAST:event_BsegurançaActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfiguraçãoGeral().setVisible(true);
            }});
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
       
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void Bsuporte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bsuporte1ActionPerformed
      Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(51,51,51));
        Bsuporte2.setBackground(new java.awt.Color(150,0,20));
        
        
        
        Caixas f = new Caixas();
        jDesktopPane1.add(f);
        ((BasicInternalFrameUI)f.getUI()).setNorthPane(null); //retirar o painel superior
        f.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        try {
            f.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.setVisible(true);
    }//GEN-LAST:event_Bsuporte1ActionPerformed

    private void Bsuporte2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bsuporte2ActionPerformed
        Bestatistica.setBackground(new java.awt.Color(150,0,20));
        Bclientes.setBackground(new java.awt.Color(150,0,20));
        Bajuda.setBackground(new java.awt.Color(150,0,20));
        Becommerce.setBackground(new java.awt.Color(150,0,20));
        Bentregas.setBackground(new java.awt.Color(150,0,20));
        Bestoque.setBackground(new java.awt.Color(150,0,20));
        Bfinanceiro.setBackground(new java.awt.Color(150,0,20));
        Bfornecedores.setBackground(new java.awt.Color(150,0,20));
        Bfuncionarios.setBackground(new java.awt.Color(150,0,20));
        Bimpostos.setBackground(new java.awt.Color(150,0,20));
        Bimpressora.setBackground(new java.awt.Color(150,0,20));
        Bpromoções.setBackground(new java.awt.Color(150,0,20));
        Brh.setBackground(new java.awt.Color(150,0,20));
        Bsegurança.setBackground(new java.awt.Color(150,0,20));
        Bsuporte.setBackground(new java.awt.Color(150,0,20));
        Bsuporte1.setBackground(new java.awt.Color(150,0,20));
        Bsuporte2.setBackground(new java.awt.Color(51,51,51));
        
        
        
        NotasFiscais f = new NotasFiscais();
        jDesktopPane1.add(f);
        ((BasicInternalFrameUI)f.getUI()).setNorthPane(null); //retirar o painel superior
        f.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        try {
            f.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaGestão.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.setVisible(true);
    }//GEN-LAST:event_Bsuporte2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaGestão.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaGestão.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaGestão.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaGestão.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaGestão().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bajuda;
    private javax.swing.JButton Bclientes;
    private javax.swing.JButton Becommerce;
    private javax.swing.JButton Bentregas;
    private javax.swing.JButton Bestatistica;
    private javax.swing.JButton Bestoque;
    private javax.swing.JButton Bfinanceiro;
    private javax.swing.JButton Bfornecedores;
    private javax.swing.JButton Bfuncionarios;
    private javax.swing.JButton Bimpostos;
    private javax.swing.JButton Bimpressora;
    private javax.swing.JButton Bpromoções;
    private javax.swing.JButton Brh;
    private javax.swing.JButton Bsegurança;
    private javax.swing.JButton Bsuporte;
    private javax.swing.JButton Bsuporte1;
    private javax.swing.JButton Bsuporte2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton20;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
