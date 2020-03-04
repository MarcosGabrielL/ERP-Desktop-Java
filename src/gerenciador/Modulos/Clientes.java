/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.Modulos;

import gerenciador.TelaGestão;
import java.awt.Color;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import model.bean.Cliente;
import model.dao.ClienteDAO;

/**
 *
 * @author Marcos
 */
public class Clientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form Clientes
     */
    public Clientes() {
        initComponents();
        estilojpanel();
        readjTableCCliente();
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
        SwingUtilities.updateComponentTreeUI(PainelClientes);
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
      public void  readjTableCCliente(){
        
    DefaultTableModel modelo = (DefaultTableModel)TabelaCliente.getModel();
    modelo.setNumRows(0);
    ClienteDAO cdao = new ClienteDAO();
    
    for(Cliente c: cdao.read()){
    
            modelo.addRow(new Object[]{
                c.getNome(),
                c.getNascimento(),
                c.getRua(),
                c.getBairro(),
                c.getNumero(),
                c.getCpf(),
                c.getRg(),
                c.getTelefone1(),
                c.getTelefone2(),
                c.getNota()
                
                    });
    }
    
    }
    
    public void adicionarcliente(){
        Cliente c = new Cliente();
        ClienteDAO dao = new ClienteDAO();

        
        if(isCPF(JTCPFCliente2.getText())==true){
            c.setCpf(imprimeCPF(JTCPFCliente2.getText()));
            c.setNome(JTNomeCliente2.getText());
        c.setNascimento(JTNascimentoCliente2.getText());
        c.setRua(JTRuaCliente2.getText());
        c.setBairro(JTBairroCliente2.getText());
        c.setNumero(JTNumeroCliente2.getText());
        c.setNota(JTBairroCliente4.getText());
        c.setRg(JTRGCliente2.getText());
        
                if(isTelefone(JTTelefone1Cliente2.getText())){
                    c.setTelefone1(JTTelefone1Cliente2.getText());
                    c.setTelefone2(JTTelefone2Cliente2.getText());
                
                    dao.creat(c);
                }else{
                
                    JOptionPane.showMessageDialog(null, "Telefone Invalido ");
                
                }
        
        }else{
           
            JOptionPane.showMessageDialog(null, "CPF invalido ");
        }

        JTNomeCliente2.setText("");
        JTNascimentoCliente2.setText("");
        JTRuaCliente2.setText("");
        JTBairroCliente2.setText("");
        JTNumeroCliente2.setText("");
        JTCPFCliente2.setText("");
        JTRGCliente2.setText("");
        JTTelefone1Cliente2.setText("");
        JTTelefone2Cliente2.setText("");
        JTBairroCliente4.setText("");

        readjTableCCliente();
    
    }
    public void alterarcliente(){
    
        if(JTNomeCliente2.getText().equals("")){

            javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
            javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
            javax.swing.JTable jTable1 = new javax.swing.JTable();
            javax.swing.JButton jButton1 = new javax.swing.JButton();

            JFrame sefra = new JFrame();
            sefra.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            sefra.setTitle("Atualização de Clientes");
            sefra.setAlwaysOnTop(true);
            sefra.setSize(600,500);
            sefra.setLocationRelativeTo(null);
            sefra.setVisible(true);
            sefra.getRootPane().setDefaultButton(jButton1);

            jLabel1.setText("Escolha o Cliente");

            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Nome", "Nascimento", "Rua","Bairro","Numero","CPF", "RG","Telefone 1","Telefone 2","Nota"
                }
            ));
            jScrollPane1.setViewportView(jTable1);
            DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
            modelo.setNumRows(0);
            ClienteDAO fdao = new ClienteDAO();

            for(Cliente p: fdao.read()){

                modelo.addRow(new Object[]{
                    p.getNome(),
                    p.getNascimento(),
                    p.getRua(),
                    p.getBairro(),
                    p.getNumero(),
                    p.getCpf(),
                    p.getRg(),
                    p.getTelefone1(),
                    p.getTelefone2(),
                    p.getNota()
                });
            }
            jButton1.setText("Atualizar");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    Cliente c = new Cliente();
                    ClienteDAO dao = new ClienteDAO();

                    DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();

                    int a = jTable1.getSelectedRow();
                    c.setNome(String.valueOf(jTable1.getValueAt(a,0)));
                    c.setNascimento(String.valueOf(jTable1.getValueAt(a,1)));
                    c.setRua(String.valueOf(jTable1.getValueAt(a,2)));
                    c.setBairro(String.valueOf(jTable1.getValueAt(a,3)));
                    c.setNumero(String.valueOf(jTable1.getValueAt(a,4)));
                    c.setCpf(String.valueOf(jTable1.getValueAt(a,5)));
                    c.setRg(String.valueOf(jTable1.getValueAt(a,6)));
                    c.setTelefone1(String.valueOf(jTable1.getValueAt(a,7)));
                    c.setTelefone2(String.valueOf(jTable1.getValueAt(a,8)));

                    sefra.dispose();
                    dao.update(c);

                     readjTableCCliente();

                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(sefra.getContentPane());
            sefra.getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addGap(0, 473, Short.MAX_VALUE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addContainerGap())))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(jButton1)
                    .addContainerGap())
            );

        }else{

            Cliente c = new Cliente();
            ClienteDAO dao = new ClienteDAO();

           if(isCPF(JTCPFCliente2.getText())==true){
            c.setCpf(imprimeCPF(JTCPFCliente2.getText()));
            c.setNome(JTNomeCliente2.getText());
        c.setNascimento(JTNascimentoCliente2.getText());
        c.setRua(JTRuaCliente2.getText());
        c.setBairro(JTBairroCliente2.getText());
        c.setNumero(JTNumeroCliente2.getText());
        c.setNota(JTBairroCliente4.getText());
        c.setRg(JTRGCliente2.getText());
        
                if(isTelefone(JTTelefone1Cliente2.getText())){
                    c.setTelefone1(JTTelefone1Cliente2.getText());
                    c.setTelefone2(JTTelefone2Cliente2.getText());
                
                    dao.update(c);
                }else{
                
                    JOptionPane.showMessageDialog(null, "Telefone Invalido ");
                
                }
        
        }else{
           
            JOptionPane.showMessageDialog(null, "CPF invalido ");
        }

            JTNomeCliente2.setText("");
            JTNascimentoCliente2.setText("");
            JTRuaCliente2.setText("");
            JTBairroCliente2.setText("");
            JTNumeroCliente2.setText("");
            JTCPFCliente2.setText("");
            JTRGCliente2.setText("");
            JTTelefone1Cliente2.setText("");
            JTTelefone2Cliente2.setText("");
            JTBairroCliente4.setText("");

            readjTableCCliente();

        }
    
    }
    public void removerCliete(){
    
        if(JTNomeCliente2.getText().equals("")){

            javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
            javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
            javax.swing.JTable jTable1 = new javax.swing.JTable();
            javax.swing.JButton jButton1 = new javax.swing.JButton();

            JFrame sefra = new JFrame();
            sefra.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            sefra.setTitle("Atualização de Clientes");
            sefra.setAlwaysOnTop(true);
            sefra.setSize(600,500);
            sefra.setLocationRelativeTo(null);
            sefra.setVisible(true);
            sefra.getRootPane().setDefaultButton(jButton1);

            jLabel1.setText("Escolha o Cliente");

            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Nome", "Nascimento", "Rua","Bairro","Numero","CPF", "RG","Telefone 1","Telefone 2","Nota"
                }
            ));
            jScrollPane1.setViewportView(jTable1);
            DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
            modelo.setNumRows(0);
            ClienteDAO fdao = new ClienteDAO();

            for(Cliente p: fdao.read()){

                modelo.addRow(new Object[]{
                    p.getNome(),
                    p.getNascimento(),
                    p.getRua(),
                    p.getBairro(),
                    p.getNumero(),
                    p.getCpf(),
                    p.getRg(),
                    p.getTelefone1(),
                    p.getTelefone2(),
                    p.getNota()
                });
            }
            jButton1.setText("Atualizar");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    Cliente c = new Cliente();
                    ClienteDAO dao = new ClienteDAO();

                    DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();

                    int a = jTable1.getSelectedRow();
                    c.setNome(String.valueOf(jTable1.getValueAt(a,0)));
                    c.setCpf(String.valueOf(jTable1.getValueAt(a,5)));
                    c.setRg(String.valueOf(jTable1.getValueAt(a,6)));

                    sefra.dispose();
                    dao.delete(c);

                    readjTableCCliente();

                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(sefra.getContentPane());
            sefra.getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addGap(0, 473, Short.MAX_VALUE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addContainerGap())))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(jButton1)
                    .addContainerGap())
            );

        }else{
            Cliente c = new Cliente();
            ClienteDAO dao = new ClienteDAO();

            c.setNome(JTNomeCliente2.getText());
            c.setCpf(JTCPFCliente2.getText());
            c.setRg(JTRGCliente2.getText());
            dao.delete(c);

            JTNomeCliente2.setText("");
            JTNascimentoCliente2.setText("");
            JTRuaCliente2.setText("");
            JTBairroCliente2.setText("");
            JTNumeroCliente2.setText("");
            JTCPFCliente2.setText("");
            JTRGCliente2.setText("");
            JTTelefone1Cliente2.setText("");
            JTTelefone2Cliente2.setText("");
            JTBairroCliente4.setText("");

            readjTableCCliente();
        }
        
    }
    public boolean isCPF(String CPF) {
// considera-se erro CPF's formados por uma sequencia de numeros iguais
    if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
        CPF.equals("22222222222") || CPF.equals("33333333333") ||
        CPF.equals("44444444444") || CPF.equals("55555555555") ||
        CPF.equals("66666666666") || CPF.equals("77777777777") ||
        CPF.equals("88888888888") || CPF.equals("99999999999") ||
       (CPF.length() != 11))
       return(false);

    char dig10, dig11;
    int sm, i, r, num, peso;

// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
    try {
// Calculo do 1o. Digito Verificador
      sm = 0;
      peso = 10;
      for (i=0; i<9; i++) {              
// converte o i-esimo caractere do CPF em um numero:
// por exemplo, transforma o caractere '0' no inteiro 0         
// (48 eh a posicao de '0' na tabela ASCII)         
        num = (int)(CPF.charAt(i) - 48); 
        sm = sm + (num * peso);
        peso = peso - 1;
      }

      r = 11 - (sm % 11);
      if ((r == 10) || (r == 11))
         dig10 = '0';
      else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

// Calculo do 2o. Digito Verificador
      sm = 0;
      peso = 11;
      for(i=0; i<10; i++) {
        num = (int)(CPF.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso - 1;
      }

      r = 11 - (sm % 11);
      if ((r == 10) || (r == 11))
         dig11 = '0';
      else dig11 = (char)(r + 48);

// Verifica se os digitos calculados conferem com os digitos informados.
      if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
         return(true);
      else return(false);
    } catch (InputMismatchException erro) {
        return(false);
    }
  }
    public String imprimeCPF(String CPF) {
    return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
      CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
  }
    public boolean isTelefone(String numeroTelefone) {
        return numeroTelefone.matches(".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}") ||
                numeroTelefone.matches(".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}");
    }
    public String imprimeTelefone(String Telefone) {
    return("(" + Telefone.substring(0, 2) + ")" + Telefone.substring(2, 7) + "-" + Telefone.substring(7, 11));
  }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelClientes = new javax.swing.JPanel();
        jTabbedPane17 = new javax.swing.JTabbedPane();
        jPanel88 = new javax.swing.JPanel();
        JTBairroCliente2 = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        JTTelefone1Cliente2 = new javax.swing.JTextField();
        JTNumeroCliente2 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        JTTelefone2Cliente2 = new javax.swing.JTextField();
        JTRGCliente2 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        JTCPFCliente2 = new javax.swing.JTextField();
        BAdicionarCliente2 = new javax.swing.JButton();
        jLabel72 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        BRemoverCliente2 = new javax.swing.JButton();
        BAlterarCliente2 = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        JTNomeCliente2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        JTNascimentoCliente2 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        JTRuaCliente2 = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        JTBairroCliente4 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel89 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        TabelaCliente = new javax.swing.JTable();
        BAlterarCliente3 = new javax.swing.JButton();
        BRemoverCliente3 = new javax.swing.JButton();
        jPanel44 = new javax.swing.JPanel();

        PainelClientes.setBackground(new java.awt.Color(255, 255, 255));
        PainelClientes.setToolTipText("");

        jTabbedPane17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel88.setBackground(new java.awt.Color(255, 255, 255));

        JTBairroCliente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTBairroCliente2ActionPerformed(evt);
            }
        });

        jLabel66.setText("Nome *");

        jLabel67.setText("Numero");

        JTNumeroCliente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTNumeroCliente2ActionPerformed(evt);
            }
        });

        jLabel68.setText("Nascimento");

        jLabel69.setText("CPF *");

        JTTelefone2Cliente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTTelefone2Cliente2ActionPerformed(evt);
            }
        });

        JTRGCliente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTRGCliente2ActionPerformed(evt);
            }
        });

        jLabel70.setText("Rua");

        JTCPFCliente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTCPFCliente2ActionPerformed(evt);
            }
        });

        BAdicionarCliente2.setBackground(new java.awt.Color(150, 0, 20));
        BAdicionarCliente2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BAdicionarCliente2.setForeground(new java.awt.Color(255, 255, 255));
        BAdicionarCliente2.setText("Adicionar");
        BAdicionarCliente2.setToolTipText("Preencha os campos obrigatorios e clique para adicionar");
        BAdicionarCliente2.setBorderPainted(false);
        BAdicionarCliente2.setContentAreaFilled(false);
        BAdicionarCliente2.setOpaque(true);
        BAdicionarCliente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAdicionarCliente2ActionPerformed(evt);
            }
        });

        jLabel72.setText("RG ");

        jLabel76.setText("Telefone 1 *");

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(150, 0, 20));
        jLabel77.setText("Endereço:");

        BRemoverCliente2.setBackground(new java.awt.Color(150, 0, 20));
        BRemoverCliente2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BRemoverCliente2.setForeground(new java.awt.Color(255, 255, 255));
        BRemoverCliente2.setText("Remover");
        BRemoverCliente2.setToolTipText("Forneça ao menos rg ou cpf ou nome e clique para remover");
        BRemoverCliente2.setBorderPainted(false);
        BRemoverCliente2.setContentAreaFilled(false);
        BRemoverCliente2.setOpaque(true);
        BRemoverCliente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRemoverCliente2ActionPerformed(evt);
            }
        });

        BAlterarCliente2.setBackground(new java.awt.Color(150, 0, 20));
        BAlterarCliente2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BAlterarCliente2.setForeground(new java.awt.Color(255, 255, 255));
        BAlterarCliente2.setText("Alterar");
        BAlterarCliente2.setToolTipText("Forneça cpf ou rg ou nome e clique para atualizar cadastro");
        BAlterarCliente2.setBorderPainted(false);
        BAlterarCliente2.setContentAreaFilled(false);
        BAlterarCliente2.setOpaque(true);
        BAlterarCliente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAlterarCliente2ActionPerformed(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(150, 0, 20));
        jLabel78.setText("Identificação");

        jLabel18.setText("Telefone 2");

        jLabel79.setText("Bairro");

        JTRuaCliente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTRuaCliente2ActionPerformed(evt);
            }
        });

        jLabel80.setText("Nota");

        JTBairroCliente4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTBairroCliente4ActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Apenas Numeros");

        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("Exemplo: (75) 91234-5678");

        javax.swing.GroupLayout jPanel88Layout = new javax.swing.GroupLayout(jPanel88);
        jPanel88.setLayout(jPanel88Layout);
        jPanel88Layout.setHorizontalGroup(
            jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel88Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel88Layout.createSequentialGroup()
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JTBairroCliente4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel88Layout.createSequentialGroup()
                            .addComponent(BAdicionarCliente2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BAlterarCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BRemoverCliente2))
                        .addGroup(jPanel88Layout.createSequentialGroup()
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(JTTelefone2Cliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel88Layout.createSequentialGroup()
                            .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(JTTelefone1Cliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel11))
                        .addGroup(jPanel88Layout.createSequentialGroup()
                            .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(JTNascimentoCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel88Layout.createSequentialGroup()
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(JTNomeCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel88Layout.createSequentialGroup()
                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(JTBairroCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(JTNumeroCliente2))
                        .addGroup(jPanel88Layout.createSequentialGroup()
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(JTRuaCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel88Layout.createSequentialGroup()
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JTCPFCliente2, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(JTRGCliente2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel88Layout.setVerticalGroup(
            jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel88Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JTNomeCliente2)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(JTNascimentoCliente2))
                .addGap(18, 18, 18)
                .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTRuaCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTBairroCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTNumeroCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(4, 4, 4)
                .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTCPFCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTRGCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JTTelefone1Cliente2)
                    .addGroup(jPanel88Layout.createSequentialGroup()
                        .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTTelefone2Cliente2))
                .addGap(18, 18, 18)
                .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTBairroCliente4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BAdicionarCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BAlterarCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BRemoverCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(171, 171, 171))
        );

        jTabbedPane17.addTab("Editar Clientes", jPanel88);

        TabelaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Nascimento", "Rua", "Bairro", "Numero", "CPF", "RG", "Telefone 1", "Telefone 2", "Nota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane21.setViewportView(TabelaCliente);

        BAlterarCliente3.setBackground(new java.awt.Color(150, 0, 20));
        BAlterarCliente3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BAlterarCliente3.setForeground(new java.awt.Color(255, 255, 255));
        BAlterarCliente3.setText("Alterar");
        BAlterarCliente3.setToolTipText("Forneça cpf ou rg ou nome e clique para atualizar cadastro");
        BAlterarCliente3.setBorderPainted(false);
        BAlterarCliente3.setContentAreaFilled(false);
        BAlterarCliente3.setOpaque(true);
        BAlterarCliente3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAlterarCliente3ActionPerformed(evt);
            }
        });

        BRemoverCliente3.setBackground(new java.awt.Color(150, 0, 20));
        BRemoverCliente3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BRemoverCliente3.setForeground(new java.awt.Color(255, 255, 255));
        BRemoverCliente3.setText("Remover");
        BRemoverCliente3.setToolTipText("Forneça ao menos rg ou cpf ou nome e clique para remover");
        BRemoverCliente3.setBorderPainted(false);
        BRemoverCliente3.setContentAreaFilled(false);
        BRemoverCliente3.setOpaque(true);
        BRemoverCliente3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRemoverCliente3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel89Layout = new javax.swing.GroupLayout(jPanel89);
        jPanel89.setLayout(jPanel89Layout);
        jPanel89Layout.setHorizontalGroup(
            jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel89Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                    .addGroup(jPanel89Layout.createSequentialGroup()
                        .addComponent(BAlterarCliente3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BRemoverCliente3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel89Layout.setVerticalGroup(
            jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel89Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BAlterarCliente3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BRemoverCliente3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(228, Short.MAX_VALUE))
        );

        jTabbedPane17.addTab("Lista de Clientes", jPanel89);

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 615, Short.MAX_VALUE)
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 721, Short.MAX_VALUE)
        );

        jTabbedPane17.addTab("Ranking/Score", jPanel44);

        javax.swing.GroupLayout PainelClientesLayout = new javax.swing.GroupLayout(PainelClientes);
        PainelClientes.setLayout(PainelClientesLayout);
        PainelClientesLayout.setHorizontalGroup(
            PainelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane17, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        PainelClientesLayout.setVerticalGroup(
            PainelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelClientesLayout.createSequentialGroup()
                .addComponent(jTabbedPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 761, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTBairroCliente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTBairroCliente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTBairroCliente2ActionPerformed

    private void JTNumeroCliente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTNumeroCliente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTNumeroCliente2ActionPerformed

    private void JTTelefone2Cliente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTTelefone2Cliente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTTelefone2Cliente2ActionPerformed

    private void JTRGCliente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTRGCliente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTRGCliente2ActionPerformed

    private void JTCPFCliente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTCPFCliente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTCPFCliente2ActionPerformed

    private void BAdicionarCliente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAdicionarCliente2ActionPerformed

        adicionarcliente();

    }//GEN-LAST:event_BAdicionarCliente2ActionPerformed

    private void BRemoverCliente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRemoverCliente2ActionPerformed

        removerCliete();

    }//GEN-LAST:event_BRemoverCliente2ActionPerformed

    private void BAlterarCliente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAlterarCliente2ActionPerformed

        alterarcliente();

    }//GEN-LAST:event_BAlterarCliente2ActionPerformed

    private void JTRuaCliente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTRuaCliente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTRuaCliente2ActionPerformed

    private void JTBairroCliente4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTBairroCliente4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTBairroCliente4ActionPerformed

    private void BAlterarCliente3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAlterarCliente3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BAlterarCliente3ActionPerformed

    private void BRemoverCliente3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRemoverCliente3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BRemoverCliente3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAdicionarCliente2;
    private javax.swing.JButton BAlterarCliente2;
    private javax.swing.JButton BAlterarCliente3;
    private javax.swing.JButton BRemoverCliente2;
    private javax.swing.JButton BRemoverCliente3;
    private javax.swing.JTextField JTBairroCliente2;
    private javax.swing.JTextField JTBairroCliente4;
    private javax.swing.JTextField JTCPFCliente2;
    private javax.swing.JTextField JTNascimentoCliente2;
    private javax.swing.JTextField JTNomeCliente2;
    private javax.swing.JTextField JTNumeroCliente2;
    private javax.swing.JTextField JTRGCliente2;
    private javax.swing.JTextField JTRuaCliente2;
    private javax.swing.JTextField JTTelefone1Cliente2;
    private javax.swing.JTextField JTTelefone2Cliente2;
    private javax.swing.JPanel PainelClientes;
    private javax.swing.JTable TabelaCliente;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel88;
    private javax.swing.JPanel jPanel89;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JTabbedPane jTabbedPane17;
    // End of variables declaration//GEN-END:variables
}
