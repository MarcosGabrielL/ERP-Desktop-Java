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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Fluxo;
import model.bean.Produto;

/**
 *
 * @author Marcos
 */
public class ProdutoDAO {
    
    public void creat(Produto p){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("INSERT INTO nossocliente.produtos (codigo,descrição,quantidade,tipo,preçoun,"
                    + "unidade,loja,dataentrada,validade,ncm,cst,cfop,baseicms,icms,aliquotaicms,cest,unidadetrib,"
                    + "cean,ceantrib,qtrib,vuntrib,bcicms,bcicmsst,pmvast,predbcst,vbcst,picmsst,predbc,vicmsst,"
                    + "pdif,vicmsdif,vicms,vcredicmssn,pcredsn,cstpis,vbcpis,ppis,vpis,cstcofins,vbccofins,pcofins,vcofins,"
                    + "ventrada,afederaln,afederali,aestadual,amunicipal) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1,p.getCodigo());
            stmt.setString(2,p.getDescrição());
            stmt.setString(5,p.getPreçoUn());
            stmt.setFloat(3,p.getQuantidade());
            stmt.setString(4,p.getTipo());
            stmt.setString(6,p.getUnidade());
            stmt.setString(7,p.getLoja());
            stmt.setString(8,p.getData());
            stmt.setString(9,p.getValidade());
            stmt.setString(10,p.getNCM());
            stmt.setString(11,p.getCST());
            stmt.setString(12,p.getCFOP());
            stmt.setString(13,p.getBaseICMS());
            stmt.setString(14,p.getICMS());
            stmt.setString(15,p.getAliquotaICMS());
            stmt.setString(16,p.getCEST());
            stmt.setString(17,p.getUnidadeTributavel());
            stmt.setString(18,p.getCEAN());
            stmt.setString(19,p.getCEANTrib());
            stmt.setString(20, p.getQTrib());
            stmt.setString(21, p.getVUnTrib());
            stmt.setString(22, p.getBCICMS());
            stmt.setString(23, p.getBCICMSST());
            stmt.setString(24, p.getPMVAST());
            stmt.setString(25, p.getPRedBCST());
            stmt.setString(26, p.getVBCST());
            stmt.setString(27, p.getPICMSST());
            stmt.setString(28, p.getPRedBC());
            stmt.setString(29, p.getVICMSST());
            stmt.setString(30, p.getPDif());
            stmt.setString(31, p.getVICMSDif());
            stmt.setString(32, p.getVICMS());
            stmt.setString(33, p.getVCredICMSSN());
            stmt.setString(34, p.getPCredSN());
            stmt.setString(35, p.getCSTPIS());
            stmt.setString(36, p.getVBCPIS());
            stmt.setString(37, p.getPPIS());
            stmt.setString(38, p.getVPIS());
            stmt.setString(39, p.getCSTCOFINS());
            stmt.setString(40, p.getVBCCOFINS());
            stmt.setString(41, p.getPCOFINS());
            stmt.setString(42, p.getVCOFINS());
            stmt.setString(43, p.getVentrada());
            stmt.setString(44, p.getAFederalN());
            stmt.setString(45, p.getAFederalI());
            stmt.setString(46, p.getAEstadual());
            stmt.setString(47, p.getAMunicipal());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar produto: " + ex);
            }catch (java.lang.NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Erro ao criar produto: "+ex);            
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public List<Produto> read(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Produto produto = new Produto();
                
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescrição(rs.getString("descrição"));
                produto.setQuantidade(rs.getFloat("quantidade"));
                produto.setTipo(rs.getString("tipo"));
                produto.setPreçoUn(rs.getString("preçoun"));
                produto.setUnidade(rs.getString("unidade"));
                produto.setLoja(rs.getString("loja"));
                produto.setData(rs.getString("dataentrada"));
                produto.setValidade(rs.getString("validade"));
                produto.setNCM(rs.getString("ncm"));
                produto.setCST(rs.getString("cst"));
                produto.setCFOP(rs.getString("cfop"));
                produto.setBaseICMS(rs.getString("baseicms"));
                produto.setICMS(rs.getString("icms"));
                produto.setAliquotaICMS(rs.getString("aliquotaicms"));
                produto.setCEST(rs.getString("cest"));
                produto.setUnidadeTributavel(rs.getString("unidadetrib"));
                produto.setCEAN(rs.getString("cean"));
                produto.setCEANTrib(rs.getString("ceantrib"));
                produto.setQTrib(rs.getString("qtrib"));
                produto.setVUnTrib(rs.getString("vuntrib"));
                produto.setBCICMS(rs.getString("bcicms"));
                produto.setBCICMSST(rs.getString("bcicmsst"));
                produto.setPMVAST(rs.getString("pmvast"));
                produto.setPRedBCST(rs.getString("predbcst"));
                produto.setVBCST(rs.getString("vbcst"));
                produto.setPICMSST(rs.getString("picmsst"));
                produto.setPRedBC(rs.getString("predbc"));
                produto.setVICMSST(rs.getString("vicmsst"));
                produto.setPDif(rs.getString("pdif"));
                produto.setVICMSDif(rs.getString("vicmsdif"));
                produto.setVICMS(rs.getString("vicms"));
                produto.setVCredICMSSN(rs.getString("vcredicmssn"));
                produto.setPCredSN(rs.getString("pcredsn"));
                produto.setCSTPIS(rs.getString("cstpis"));
                produto.setVBCPIS(rs.getString("vbcpis"));
                produto.setPPIS(rs.getString("ppis"));
                produto.setVPIS(rs.getString("vpis"));
                produto.setCSTCOFINS(rs.getString("cstcofins"));
                produto.setVBCCOFINS(rs.getString("vbccofins"));
                produto.setPCOFINS(rs.getString("pcofins"));
                produto.setVCOFINS(rs.getString("vcofins"));
                produto.setVentrada(rs.getString("ventrada"));
                produto.setAFederalN(rs.getString("afederaln"));
                produto.setAFederalI(rs.getString("afederali"));
                produto.setAEstadual(rs.getString("aestadual"));
                produto.setAMunicipal(rs.getString("amunicipal"));
                
                produtos.add(produto);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Ler produtos: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return produtos;
        
    
    }
    
    public void update(Produto p){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE nossocliente.produtos SET descrição = ? ,"
                                                                         + "preçoUn = ?, "
                                                                         + "Unidade = ?, "
                                                                         + "Dataentrada = ?, "
                                                                         + "quantidade = ?,"
                                                                         + "validade = ?, "
                                                                         + "tipo = ?, "
                    + "loja = ?, ncm = ?, cst = ?,cfop = ?,baseicms = ?,icms = ?,aliquotaicms = ?,cest = ?, unidadetrib = ?,"
                    + "cean = ?,ceantrib = ?,qtrib = ?, vuntrib = ?, bcicms = ?, bcicmsst = ?, pmvast = ?,predbcst = ?, vbcst = ?,"
                    + "picmsst = ?, predbc = ?, vicmsst = ?, pdif = ?, vicmsdif = ?, vicms = ?, vcredicmssn = ?, pcredsn = ?, "
                    + "cstpis = ?, vbcpis = ?, ppis = ?, vpis = ?, cstcofins = ?, vbccofins = ?, pcofins = ?, vcofins = ?,"
                    + "ventrada = ?, afederaln = ?, afederali = ?, aestadual = ?, amunicipal = ? WHERE codigo = ?");
            stmt.setString(1,p.getDescrição());
            stmt.setString(2,p.getPreçoUn());
            stmt.setString(3,p.getUnidade());
            stmt.setString(4,p.getData());
            stmt.setFloat(5, p.getQuantidade());
            stmt.setString(6,p.getValidade());
            stmt.setString(7,p.getTipo());
            stmt.setString(8,p.getLoja());
            stmt.setString(9,p.getNCM());
            stmt.setString(10,p.getCST());
            stmt.setString(11,p.getCFOP());
            stmt.setString(12,p.getBaseICMS());
            stmt.setString(13,p.getICMS());
            stmt.setString(14,p.getAliquotaICMS());
            stmt.setString(15, p.getCEST());
            stmt.setString(16, p.getUnidadeTributavel());
            stmt.setString(17,p.getCEAN());
            stmt.setString(18,p.getCEANTrib());
            stmt.setString(19,p.getQTrib());
            stmt.setString(20,p.getVUnTrib());
            stmt.setString(21, p.getBCICMS());
            stmt.setString(22, p.getBCICMSST());
            stmt.setString(23, p.getPMVAST());
            stmt.setString(24, p.getPRedBCST());
            stmt.setString(25, p.getVBCST());
            stmt.setString(26, p.getPICMSST());
            stmt.setString(27, p.getPRedBC());
            stmt.setString(28, p.getVICMSST());
            stmt.setString(29, p.getPDif());
            stmt.setString(30, p.getVICMSDif());
            stmt.setString(31, p.getVICMS());
            stmt.setString(32, p.getVCredICMSSN());
            stmt.setString(33, p.getPCredSN());
            stmt.setString(34, p.getCSTPIS());
            stmt.setString(35, p.getVBCPIS());
            stmt.setString(36, p.getPPIS());
            stmt.setString(37, p.getVPIS());
            stmt.setString(38, p.getCSTCOFINS());
            stmt.setString(39, p.getVBCCOFINS());
            stmt.setString(40, p.getPCOFINS());
            stmt.setString(41, p.getVCOFINS());
            stmt.setString(42, p.getVentrada());
            stmt.setString(43, p.getAFederalN());
            stmt.setString(44, p.getAFederalI());
            stmt.setString(45, p.getAEstadual());
            stmt.setString(46, p.getAMunicipal());
            stmt.setString(47,p.getCodigo());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar:"+ex);
            }catch (java.lang.NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public void delete(Produto p){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("DELETE FROM nossocliente.produtos WHERE codigo = ?");
            stmt.setString(1,p.getCodigo());
            
            stmt.executeUpdate();
            
            //JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public List<Produto> readdesc(String desc){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE codigo LIKE ?");
            stmt.setString(1, ""+desc+"");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Produto produto = new Produto();
                
                 produto.setCodigo(rs.getString("codigo"));
                produto.setDescrição(rs.getString("descrição"));
                produto.setQuantidade(rs.getFloat("quantidade"));
                produto.setTipo(rs.getString("tipo"));
                produto.setPreçoUn(rs.getString("preçoun"));
                produto.setUnidade(rs.getString("unidade"));
                produto.setLoja(rs.getString("loja"));
                produto.setData(rs.getString("dataentrada"));
                produto.setValidade(rs.getString("validade"));
                produto.setNCM(rs.getString("ncm"));
                produto.setCST(rs.getString("cst"));
                produto.setCFOP(rs.getString("cfop"));
                produto.setBaseICMS(rs.getString("baseicms"));
                produto.setICMS(rs.getString("icms"));
                produto.setAliquotaICMS(rs.getString("aliquotaicms"));
                produto.setCEST(rs.getString("cest"));
                produto.setUnidadeTributavel(rs.getString("unidadetrib"));
                produto.setCEAN(rs.getString("cean"));
                produto.setCEANTrib(rs.getString("ceantrib"));
                produto.setQTrib(rs.getString("qtrib"));
                produto.setVUnTrib(rs.getString("vuntrib"));
                produto.setBCICMS(rs.getString("bcicms"));
                produto.setBCICMSST(rs.getString("bcicmsst"));
                produto.setPMVAST(rs.getString("pmvast"));
                produto.setPRedBCST(rs.getString("predbcst"));
                produto.setVBCST(rs.getString("vbcst"));
                produto.setPICMSST(rs.getString("picmsst"));
                produto.setPRedBC(rs.getString("predbc"));
                produto.setVICMSST(rs.getString("vicmsst"));
                produto.setPDif(rs.getString("pdif"));
                produto.setVICMSDif(rs.getString("vicmsdif"));
                produto.setVICMS(rs.getString("vicms"));
                produto.setVCredICMSSN(rs.getString("vcredicmssn"));
                produto.setPCredSN(rs.getString("pcredsn"));
                produto.setCSTPIS(rs.getString("cstpis"));
                produto.setVBCPIS(rs.getString("vbcpis"));
                produto.setPPIS(rs.getString("ppis"));
                produto.setVPIS(rs.getString("vpis"));
                produto.setCSTCOFINS(rs.getString("cstcofins"));
                produto.setVBCCOFINS(rs.getString("vbccofins"));
                produto.setPCOFINS(rs.getString("pcofins"));
                produto.setVCOFINS(rs.getString("vcofins"));
                produto.setVentrada(rs.getString("ventrada"));
                produto.setAFederalN(rs.getString("afederaln"));
                produto.setAFederalI(rs.getString("afederali"));
                produto.setAEstadual(rs.getString("aestadual"));
                produto.setAMunicipal(rs.getString("amunicipal"));
                
                produtos.add(produto);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler por codigo:"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return produtos;
        
    
    }
    
    public List<Produto> readdescordem(String desc){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE codigo LIKE ? order by quantidade");
            stmt.setString(1, ""+desc+"");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Produto produto = new Produto();
                
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescrição(rs.getString("descrição"));
                produto.setTipo(rs.getString("tipo"));
                produto.setPreçoUn(rs.getString("preçoUn"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setUnidade(rs.getString("Unidade"));
                produtos.add(produto);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler por codigo:"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return produtos;
        
    
    }
    
    public String readtipo(String tipo){
    
    Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String unidade="";
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE tipo LIKE ?");
            stmt.setString(1, ""+tipo+"");
            rs = stmt.executeQuery();
           
                Produto produto = new Produto();
                
                
                unidade = rs.getString("Unidade");
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar por tipo"+ex);
            }catch (java.lang.NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Erro ao buscar por tipo "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return unidade;
    
    
    }
    
    public List<Produto> readQuantidade(){
    Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE quantidade <= 10");
           
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Produto produto = new Produto();
                
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescrição(rs.getString("descrição"));
                produto.setPreçoUn(rs.getString("preço"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Ler produtos em baixa (<10) fg"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return produtos;
    
    
    
    }
    
    public List<Produto> readQTipo(String tipo){
        
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE quantidade <= 10 AND tipo LIKE ?");
            stmt.setString(1, ""+tipo+"");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Produto produto = new Produto();
                
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescrição(rs.getString("descrição"));
                produto.setPreçoUn(rs.getString("preço"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
                
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler produtos em baixa por tipo"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return produtos;
    
    }
    
    public List<Produto> readUnidade(String codigo){
        
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE codigo LIKE ?");
            stmt.setString(1, ""+codigo+"");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Produto produto = new Produto();
                
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescrição(rs.getString("descrição"));
                produto.setPreçoUn(rs.getString("preço"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
                
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro5: tre"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return produtos;
    
    }
    
    public List<Produto> Procurar(String descrição){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE descrição LIKE ?");
            stmt.setString(1, ""+descrição+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Produto produto = new Produto();
                
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescrição(rs.getString("descrição"));
                produto.setTipo(rs.getString("tipo"));
                produto.setPreçoUn(rs.getString("preçoUn"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setUnidade(rs.getString("Unidade"));
                produtos.add(produto);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Err4"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return produtos;
    
    
    
    }
    
    public void attestoque(String desc, float quanti){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE nossocliente.produtos SET quantidade = quantidade - ? WHERE codigo = ?");
            
            stmt.setFloat(1,quanti);
            stmt.setString(2,desc);
            
            stmt.executeUpdate();
            
           // JOptionPane.showMessageDialog(null, "Estoque atualizado");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atalizar: frg"+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public void reverteestoque(String desc, float quanti){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE nossocliente.produtos SET quantidade = quantidade + ? WHERE codigo = ?");
            
            stmt.setFloat(1,quanti);
            stmt.setString(2,desc);
            
            stmt.executeUpdate();
            
           JOptionPane.showMessageDialog(null, "Estoque revertido");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao reverter estoque:"+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
        
    }
    
    public String sabertipo(String tipo){
    
    Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String unidade="";
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE codigo LIKE ?");
            stmt.setString(1, ""+tipo+"");
            rs = stmt.executeQuery();
           
            while(rs.next()){
                Produto produto = new Produto();
                
                
                unidade = rs.getString("tipo");
            }
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Er5"+ex);
            ex.printStackTrace();
            }catch (java.lang.NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Er8"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return unidade;
    
    
    }
    
    public String saberPreço(String codigo, String unidade){
    
    Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String preço="";
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE codigo LIKE ?");
            stmt.setString(1, ""+codigo+"");
            rs = stmt.executeQuery();
           
            while(rs.next()){
                Produto produto = new Produto();
                
                if(unidade.equals("")){
           JOptionPane.showMessageDialog(null, "Produto não cadastrado Corretamente"); 
        }else{
           preço = rs.getString("preçoUn");
        }
                
               
            }
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Er5:"+ex);
            ex.printStackTrace();
            }catch (java.lang.NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Er8:"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return preço;
    
    
    }
    
    public String saberUnidade(String codigo){
    
    Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String unidade="";
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE codigo LIKE ?");
            stmt.setString(1, ""+codigo+"");
            rs = stmt.executeQuery();
           
            while(rs.next()){
                Produto produto = new Produto();
                
                
                unidade = String.valueOf(rs.getString("Unidade"));
            }
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Er5"+ex);
            ex.printStackTrace();
            }catch (java.lang.NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Er8"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return unidade;
    
    
    }
    
    public List<Produto> procurarLoja(String loja){
        
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE Loja LIKE ? order by quantidade");
            stmt.setString(1, loja);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Produto produto = new Produto();
                
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescrição(rs.getString("descrição"));
                produto.setUnidade(rs.getString("Unidade"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
                
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produtos em baixa por loja"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return produtos;
    
    }
    
    public List<Produto> procurarLojaEQuantidade(String loja, int Quantidade){
        
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE Loja LIKE ? AND quantidade <= ? order by quantidade");
            stmt.setString(1, ""+loja+"");
            stmt.setInt(2, Quantidade);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Produto produto = new Produto();
                
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescrição(rs.getString("descrição"));
                produto.setUnidade(rs.getString("Unidade"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
                
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produtos em baixa por quantidade e loja"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return produtos;
    
    }
    
    public List<Produto> procurarLojaEQuantidadeETipo(String loja, int Quantidade, String tipo){
        
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE Loja LIKE ? AND quantidade <= ? AND tipo LIKE ? order by quantidade");
            stmt.setString(1, loja);
            stmt.setInt(2, Quantidade);
            stmt.setString(3, tipo);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Produto produto = new Produto();
                
              produto.setCodigo(rs.getString("codigo"));
                produto.setDescrição(rs.getString("descrição"));
                produto.setUnidade(rs.getString("Unidade"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
                
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produtos em baixa por quantidade, loja e tipo"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return produtos;
    
    }
    
    public List<Produto> procurarQuantidadeETipo(int Quantidade, String tipo){
        
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE quantidade <= ? AND tipo LIKE ? order by quantidade");
            stmt.setInt(1, Quantidade);
            stmt.setString(2, tipo);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Produto produto = new Produto();
                
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescrição(rs.getString("descrição"));
                produto.setUnidade(rs.getString("Unidade"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
                
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produtos em baixa por quantidade e tipo"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return produtos;
    
    }
    
    public List<Produto> procurarQuantidade(int Quantidade){
        
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE quantidade <= ? order by quantidade");
            stmt.setInt(1, Quantidade);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Produto produto = new Produto();
                
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescrição(rs.getString("descrição"));
                produto.setUnidade(rs.getString("Unidade"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
                
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produtos em baixa por quantidade"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return produtos;
    
    }
    
    public List<Produto> procurarLojaETipo(String loja, String tipo){
        
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE Loja LIKE ? AND tipo LIKE ? order by quantidade");
            stmt.setString(1, loja);
            stmt.setString(2, tipo);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Produto produto = new Produto();
                
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescrição(rs.getString("descrição"));
                produto.setUnidade(rs.getString("Unidade"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
                
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produtos em baixa por tipo e loja"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return produtos;
    
    }
    
    public List<Produto> procurarTipo(String tipo){
        
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM nossocliente.produtos WHERE tipo LIKE ? order by quantidade");
            stmt.setString(1, tipo);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Produto produto = new Produto();
                
                produto.setCodigo(rs.getString("codigo"));
                produto.setUnidade(rs.getString("unidade"));
                produto.setPreçoUn(rs.getString("preçoun"));
                produto.setDescrição(rs.getString("descrição"));
                produto.setUnidade(rs.getString("Unidade"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
                
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produtos em baixa por tipo"+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return produtos;
    
    }
    
    public int idsem(){
        int idqfalta = 0;
        int i = 0;
        int id = 0;
        
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM NOSSOCLIENTE.PRODUTOS order by codigo");
            rs = stmt.executeQuery();
            
            while(rs.next()){
        
                id = Integer.parseInt(rs.getString("codigo"));
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
