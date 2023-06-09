package com.igorsantos.controleestoque.view;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;
import com.igorsantos.controleestoque.model.Produto;
import com.igorsantos.controleestoque.util.ProdutoTableModel;
import com.igorsantos.controleestoque.dao.*;

public class TelaPesquisaProduto extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel pesquisaLabel;
	private JComboBox<String> pesquisaComboBox;
	private JTextField pesquisaField;
	private JButton pesquisarButton, cancelarButton;
	private JTable tabelaProdutos;
	private List<Produto> produtos = new ArrayList<>();
	private ProdutoTableModel produtoTableModel;

	public TelaPesquisaProduto() throws SQLException {
		super("Pesquisa de Produto");

		// Configura a janela de pesquisa de produtos
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);

		// Cria os componentes da tela de pesquisa
		pesquisaLabel = new JLabel("Pesquisar por:");
		pesquisaComboBox = new JComboBox<>(new String[] { "Código", "Nome" });
		pesquisaField = new JTextField(20);
		pesquisarButton = new JButton("Pesquisar");
		cancelarButton = new JButton("Cancelar");
		
		// Carrega os produtos
		carregarProdutos();
		
		// Cria a tabela de produtos
		produtoTableModel = new ProdutoTableModel(produtos);
		tabelaProdutos = new JTable(produtoTableModel);
		tabelaProdutos.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
		

		// Adiciona os componentes à janela de pesquisa
		Container container = getContentPane();
		container.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		container.add(pesquisaLabel, c);

		c.gridx = 1;
		c.anchor = GridBagConstraints.LINE_START;
		container.add(pesquisaComboBox, c);

		c.gridx = 2;
		container.add(pesquisaField, c);

		c.gridx = 3;
		container.add(pesquisarButton, c);

		c.gridx = 4;
		container.add(cancelarButton, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 0, 0, 0);
		container.add(scrollPane, c);

		// Adiciona um listener para o botão de pesquisa
		pesquisarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String pesquisa = pesquisaField.getText();
				String tipoPesquisa = (String) pesquisaComboBox.getSelectedItem();
				realizarPesquisa(pesquisa, tipoPesquisa);
			}
		});

		// Adiciona um listener para o botão de cancelar
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose(); // Fecha a janela de pesquisa
			}
		});

		// Exibe a janela de pesquisa de produtos
		setVisible(true);
	}
	

	// Método para realizar a pesquisa
	private void realizarPesquisa(String pesquisa, String tipoPesquisa) {
		List<Produto> resultados = new ArrayList<>();
		
		try {
			if (tipoPesquisa.equals("Código")) {
			resultados = ProdutoDAO.findByCodigo(pesquisa);
		} 
			else if (tipoPesquisa.equals("Nome")) {
			resultados = ProdutoDAO.findByNome(pesquisa);
		}

		// Atualiza a tabela de produtos com os resultados da pesquisa
		produtoTableModel.setProdutos(resultados);
	} catch (SQLException e) {
    	JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
    }
			
		}
	
	private void carregarProdutos() throws SQLException {
	    produtos.add(new Produto("0001", "Ryzen 7 7600X", 2));
	    produtos.add(new Produto("0002", "Ryzen 5 5600G", 2)); 
	    produtos.add(new Produto("0003", "Ryzen 9 7950X", 10));
	}	
}
