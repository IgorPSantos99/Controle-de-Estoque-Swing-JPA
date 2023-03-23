package com.igorsantos.controleestoque.util;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.igorsantos.controleestoque.model.Produto;

public class ProdutoTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	private List<Produto> produtos;

	public ProdutoTableModel(List<Produto> produtos) {
	    this.produtos = produtos;
	    addColumn("Código");
	    addColumn("Nome");
	    addColumn("Quantidade");
	}

	@Override
	public int getRowCount() {
	    if (produtos == null) {
	        return 0;
	    }
	    return produtos.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
	    Produto produto = produtos.get(row);

	    switch (column) {
	        case 0:
	            return produto.getCodigo();
	        case 1:
	            return produto.getNome();
	        case 2:
	            return produto.getQuantidade();
	        default:
	            throw new IllegalArgumentException("Coluna inválida: " + column);
	    }
	}

	public void setProdutos(List<Produto> produtos) {
	    this.produtos = produtos;
	    fireTableDataChanged();
	}


}
