package com.igorsantos.controleestoque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.igorsantos.controleestoque.model.Produto;

public class ProdutoDAO {

	
	
	
	public List<Produto> buscarProdutosPorCodigo(String codigo) {
	    List<Produto> produtos = new ArrayList<>();
	   
	    try {
	        Connection conn = getConnection();
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
	        conn.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return produtos;
	}

	public List<Produto> buscarProdutosPorNome(String nome) {
	    List<Produto> produtos = new ArrayList<>();
	    try {
	        Connection conn = getConnection();
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
	        conn.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return produtos;
	}

	public List<Produto> buscarTodosOsProdutos() {
	    List<Produto> produtos = new ArrayList<>();
	    try {
	        Connection conn = getConnection();
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
	        conn.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return produtos;
	}	
	
	
}
