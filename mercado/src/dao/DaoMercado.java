/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Mercado;
import java.sql.Date;
import  java.time.format.DateTimeFormatter;
import java.time.LocalDate;
/**
 *
 * @author Administrador
 */
public class DaoMercado {
    public static boolean inserir(Mercado objeto) {
        String sql = "INSERT INTO mercado (codigo, nome_fantasia, razao_social, fundacao, nr_funcionario, valor_bolsa) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.setString(2, objeto.getNome_fantasia());
            ps.setString(3, objeto.getRazao_social());
            ps.setDate(4, Date.valueOf(objeto.getFundacao()));
            ps.setInt(5, objeto.getNr_funcionario());
            ps.setDouble(6, objeto.getValor_bolsa());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     public static void main(String[] args) {
        Mercado objeto = new Mercado();
        objeto.setCodigo(1);
        objeto.setNome_fantasia("aaaa");
        objeto.setRazao_social("aaaa");
        objeto.setFundacao(LocalDate.parse("11/01/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy"))); 
        objeto.setNr_funcionario(30);
        objeto.setValor_bolsa(225.50);
        
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
     public static boolean alterar(Mercado objeto) {
        String sql = "UPDATE mercado SET nome_fantasia = ?, razao_social = ?, fundacao = ?, nr_funcionario = ?, valor_bolsa = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome_fantasia()); 
            ps.setString(2, objeto.getRazao_social());
            ps.setDate(3, Date.valueOf(objeto.getFundacao())); //fazer a seguinte importação: java.sql.Date 
            ps.setInt(4, objeto.getNr_funcionario());
            ps.setDouble(5, objeto.getValor_bolsa());
            ps.setInt(6, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
      public static boolean excluir(Mercado objeto) {
        String sql = "DELETE FROM mercado WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
