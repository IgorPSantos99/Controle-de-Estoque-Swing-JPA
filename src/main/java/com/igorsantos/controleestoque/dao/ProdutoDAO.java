package com.igorsantos.controleestoque.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.igorsantos.controleestoque.model.Produto;


public class ProdutoDAO {
    
	private static String url = "jdbc:postgresql://localhost:5432/estoque_produtos";
	private static String user = "postgres";
	private static String password = "123456789";
	

	private Connection conn;
    
    
    public ProdutoDAO() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
    }

    public static List<Produto> findByCodigo(String codigo) throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM produtos WHERE codigo = ?");
        stmt.setString(1, codigo);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Produto produto = new Produto();
            produto.setCodigo(rs.getString("codigo"));
            produto.setNome(rs.getString("nome"));
            produto.setQuantidade(rs.getInt("quantidade"));
            produtos.add(produto);
        }
        rs.close();
        stmt.close();
        return produtos;
    }

    public static List<Produto> findByNome(String nome) throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/estoque_produtos", "postgres", "123456789");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM produtos WHERE nome LIKE ?");
        stmt.setString(1, "%" + nome + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Produto produto = new Produto();
            produto.setCodigo(rs.getString("codigo"));
            produto.setNome(rs.getString("nome"));
            produto.setQuantidade(rs.getInt("quantidade"));
            produtos.add(produto);
        }
        rs.close();
        stmt.close();
        return produtos;
    }

    public List<Produto> findAll() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM produtos");
        while (rs.next()) {
            Produto produto = new Produto();
            produto.setCodigo(rs.getString("codigo"));
            produto.setNome(rs.getString("nome"));
            produto.setQuantidade(rs.getInt("quantidade"));
            produtos.add(produto);
        }
        rs.close();
        stmt.close();
        return produtos;
    }
    
    public void updateProduto(Produto produto) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement("UPDATE produtos SET nome = ?, quantidade = ? WHERE codigo = ?");
        stmt.setString(1, produto.getNome());
        stmt.setInt(2, produto.getQuantidade());
        stmt.setString(3, produto.getCodigo());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
    public void insertProduto(Produto produto) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO produtos (codigo, nome, quantidade) VALUES (?, ?, ?)");
        stmt.setString(1, produto.getCodigo());
        stmt.setString(2, produto.getNome());
        stmt.setInt(3, produto.getQuantidade());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
    public void createTableProduto() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS produtos (codigo VARCHAR(10) PRIMARY KEY, nome VARCHAR(50), quantidade INTEGER)");
        stmt.close();
        conn.close();
    }
    
    public void deleteProduto(Produto produto) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM produtos WHERE codigo = ?");
        stmt.setString(1, produto.getCodigo());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
    public void closeProduto() throws SQLException {
        conn.close();
    }
    
}


