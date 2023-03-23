package com.igorsantos.controleestoque;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import com.igorsantos.controleestoque.model.Produto;

public class TelaPesquisaProduto extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel pesquisaLabel;
	private JComboBox<String> pesquisaComboBox;
	private JTextField pesquisaField;
	private JButton pesquisarButton, cancelarButton;
	private JTable tabelaProdutos;
	private DefaultTableModel modeloTabela;
	private List<Produto> produtos = new ArrayList<>();

	public TelaPesquisaProduto() {
		super("Pesquisa de Produto");

		// Configura a janela de pesquisa de produtos
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);

		// Cria os componentes da tela de pesquisa
		pesquisaLabel = new JLabel("Pesquisar por:");
		pesquisaComboBox = new JComboBox<>(new String[] { "Código", "Nome" });
		pesquisaField = new JTextField(20);
		pesquisarButton = new JButton("Pesquisar");
		cancelarButton = new JButton("Cancelar");

		// Cria a tabela de produtos
		modeloTabela = new DefaultTableModel(new Object[][] {}, new String[] { "Código", "Nome", "Quantidade" });
		tabelaProdutos = new JTable(modeloTabela);
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

		// Exibe a janela de pesquisa de produtos
		setVisible(true);
	}

	// Crie um ActionListener para o botão de pesquisa
	ActionListener searchListener = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        String searchQuery = searchBox.getText(); // Obtenha o texto da caixa de pesquisa
	        List<Produto> resultados = null; // Inicialize uma lista de resultados vazia

	        if (searchQuery.length() > 0) {
	            if (isNumeric(searchQuery)) {
	                // Pesquise por código
	                resultados = buscarProdutosPorCodigo(searchQuery);
	            } else {
	                // Pesquise por nome
	                resultados = buscarProdutosPorNome(searchQuery);
	            }
	        } else {
	            // Se a caixa de pesquisa estiver vazia, exiba todos os produtos
	            resultados = buscarTodosOsProdutos();
	        }

	        // Atualize o modelo da tabela com os resultados encontrados
	        produtoTableModel.setProdutos(resultados);
	    }
	};

	// Adicione o ActionListener ao botão de pesquisa
	searchButton.addActionListener(searchListener);

	
     // Adiciona um listener para o botão de cancelar
        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dispose(); // Fecha a janela de pesquisa
            }
        });
    }

	public static void main(String[] args) {
		new TelaPesquisaProduto();
	}

}
