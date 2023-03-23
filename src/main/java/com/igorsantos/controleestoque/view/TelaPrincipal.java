package com.igorsantos.controleestoque.view;

import com.igorsantos.controleestoque.model.Produto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;

    private List<Produto> listaDeProdutos;

    public TelaPrincipal() {
        super("Sistema de Controle de Estoque");

        // Criar lista vazia de produtos
        listaDeProdutos = new ArrayList<>();

        // Configurar janela principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Criar tabela de produtos
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Código");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Quantidade");

        tabelaProdutos = new JTable(modeloTabela);
        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);

     // Adicionar tabela à janela principal
        getContentPane().add(scrollPane);

        // Configurar menu
        JMenuBar menuBar = new JMenuBar();

        JMenu cadastroMenu = new JMenu("Cadastro");

        JMenuItem cadastrarProdutoItem = new JMenuItem("Cadastrar Produto");
        cadastrarProdutoItem.addActionListener(event -> {
            TelaCadastroProduto telaCadastroProduto = new TelaCadastroProduto();
            telaCadastroProduto.setVisible(true);
            atualizarTabela();
        });

        cadastroMenu.add(cadastrarProdutoItem);

        JMenu entradaSaidaMenu = new JMenu("Entrada/Saída");

        JMenuItem entradaSaidaItem = new JMenuItem("Realizar Entrada/Saída");
        entradaSaidaItem.addActionListener(event -> {
            TelaEntradaSaidaProduto telaEntradaSaidaProduto = new TelaEntradaSaidaProduto();
            telaEntradaSaidaProduto.setVisible(true);
            atualizarTabela();
        });
        
        entradaSaidaMenu.add(entradaSaidaItem);
        
        JMenu pesquisaProdutoMenu = new JMenu("Pesquisa");

        JMenuItem pesquisaProdutoItem = new JMenuItem("Pesquisa de Produto");
        pesquisaProdutoItem.addActionListener(event -> {
            TelaPesquisaProduto telaPesquisaProduto = null;
			try {
				telaPesquisaProduto = new TelaPesquisaProduto();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
			}
            telaPesquisaProduto.setVisible(true);
            atualizarTabela();
        });
        pesquisaProdutoMenu.add(pesquisaProdutoItem);

        menuBar.add(cadastroMenu);
        menuBar.add(entradaSaidaMenu);
        menuBar.add(pesquisaProdutoMenu);

        setJMenuBar(menuBar);

        // Atualizar tabela com dados iniciais
        atualizarTabela();
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0); // Limpar dados da tabela

        for (Produto produto : listaDeProdutos) {
            Object[] rowData = {produto.getCodigo(), produto.getNome(), produto.getQuantidade()};
            modeloTabela.addRow(rowData);
        }
    }    
}