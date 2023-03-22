package com.igorsantos.controleestoque.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaPesquisaProduto extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JLabel pesquisaLabel;
    private JTextField pesquisaField;
    private JButton pesquisarButton, cancelarButton;
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;

    public TelaPesquisaProduto() {
        super("Pesquisa de Produto");

        // Configura a janela de pesquisa de produtos
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Cria os componentes da tela de pesquisa
        pesquisaLabel = new JLabel("Pesquisar:");
        pesquisaField = new JTextField(20);
        pesquisarButton = new JButton("Pesquisar");
        cancelarButton = new JButton("Cancelar");

        // Cria a tabela de produtos
        modeloTabela = new DefaultTableModel(new Object[][] {}, new String[] {"Código", "Nome", "Preço", "Quantidade"});
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
        container.add(pesquisaField, c);

        c.gridx = 2;
        c.anchor = GridBagConstraints.LINE_START;
        container.add(pesquisarButton, c);

        c.gridx = 3;
        c.anchor = GridBagConstraints.LINE_START;
        container.add(cancelarButton, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 0, 0, 0);
        container.add(scrollPane, c);

        // Adiciona um listener para o botão de pesquisa
        pesquisarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Aqui você pode chamar a lógica de pesquisa do sistema de controle de estoque
                // e atualizar a tabela com os resultados encontrados
                // Exemplo:
                modeloTabela.setRowCount(0); // limpa a tabela antes de adicionar novos resultados
                Object[] produto1 = {"001", "Produto A", 10.0, 20};
                modeloTabela.addRow(produto1);
                Object[] produto2 = {"002", "Produto B", 5.0, 15};
                modeloTabela.addRow(produto2);
            }
        });

        // Exibe a janela de pesquisa de produtos
        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaPesquisaProduto();
    }

}

