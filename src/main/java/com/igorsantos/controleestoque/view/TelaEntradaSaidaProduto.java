package com.igorsantos.controleestoque.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.igorsantos.controleestoque.model.Produto;

public class TelaEntradaSaidaProduto extends JFrame {
	private static final long serialVersionUID = 1L;

	private JRadioButton entradaRadioButton, saidaRadioButton;
	private ButtonGroup grupoRadioButtons;
	private JLabel codigoLabel, nomeLabel, quantidadeLabel;
	private JTextField codigoField, nomeField, quantidadeField;
	private JButton confirmarButton, cancelarButton;
	private JComboBox<String> operacaoComboBox;

	public TelaEntradaSaidaProduto() {
		super("Entrada/Saída de Produto");

		// Configura a janela de entrada/saída de produtos
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 200);
		setLocationRelativeTo(null);

		// Cria os componentes da tela de entrada/saída
		entradaRadioButton = new JRadioButton("Entrada");
		saidaRadioButton = new JRadioButton("Saída");
		grupoRadioButtons = new ButtonGroup();
		grupoRadioButtons.add(entradaRadioButton);
		grupoRadioButtons.add(saidaRadioButton);

		codigoLabel = new JLabel("Código:");
		nomeLabel = new JLabel("Nome:");
		quantidadeLabel = new JLabel("Quantidade:");

		codigoField = new JTextField(10);
		nomeField = new JTextField(20);
		quantidadeField = new JTextField(5);

		confirmarButton = new JButton("Confirmar");
		cancelarButton = new JButton("Cancelar");

		// Adiciona os componentes à janela de entrada/saída
		Container container = getContentPane();
		container.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		container.add(entradaRadioButton, c);

		c.gridx = 1;
		c.anchor = GridBagConstraints.LINE_START;
		container.add(saidaRadioButton, c);

		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_END;
		container.add(codigoLabel, c);

		c.gridx = 1;
		c.anchor = GridBagConstraints.LINE_START;
		container.add(codigoField, c);

		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_END;
		container.add(nomeLabel, c);

		c.gridx = 1;
		c.anchor = GridBagConstraints.LINE_START;
		container.add(nomeField, c);

		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.LINE_END;
		container.add(quantidadeLabel, c);

		c.gridx = 1;
		c.anchor = GridBagConstraints.LINE_START;
		container.add(quantidadeField, c);

		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.LINE_END;
		container.add(confirmarButton, c);

		c.gridx = 1;
		c.anchor = GridBagConstraints.LINE_START;
		container.add(cancelarButton, c);

		// Adiciona um listener para o botão de confirmação
		confirmarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String operacao = "";
				if (entradaRadioButton.isSelected()) {
					operacao = "entrada";
				} else if (saidaRadioButton.isSelected()) {
					operacao = "saída";
				}
				if (!entradaRadioButton.isSelected() && !saidaRadioButton.isSelected()) {
					JOptionPane.showMessageDialog(null, "Selecione Entrada ou Saída", "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String codigo = codigoField.getText();
				String nome = nomeField.getText();
				String quantidadeStr = quantidadeField.getText();
				Integer quantidade = 0;
				try {
		            quantidade = Integer.parseInt(quantidadeStr);
		        } catch (NumberFormatException e) {
		            JOptionPane.showMessageDialog(null, "Quantidade inválida! Por favor, digite um número inteiro.");
		            return;
		        }

				// Logica de Entrada/Saida.

				Produto[] listaDeProdutos = new Produto[10];

				confirmarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						String codigo = codigoField.getText();
						String nome = nomeField.getText();
						int quantidade = Integer.parseInt(quantidadeField.getText());
						String operacao = (String) operacaoComboBox.getSelectedItem();
						
						// Entrada
						if (operacao.equals("entrada")) {
							boolean encontrado = false;
							for (Produto produto : listaDeProdutos) {
								if (produto != null && produto.getCodigo().equals(codigo)) {
									produto.setQuantidade(produto.getQuantidade() + quantidade);
									encontrado = true;
									break;
								}
							}
							if (!encontrado) {
								int posicao = -1;
								for (int i = 0; i < listaDeProdutos.length; i++) {
									if (listaDeProdutos[i] == null) {
										posicao = i;
										break;
									}
								}
								if (posicao != -1) {
									Produto novoProduto = new Produto(codigo, nome, quantidade);
									listaDeProdutos[posicao] = novoProduto;
								}
							}
							// Saida
						} else if (operacao.equals("saída")) {
							boolean encontrado = false;
							for (Produto produto : listaDeProdutos) {
								if (produto != null && produto.getCodigo().equals(codigo)) {
									if (produto.getQuantidade() >= quantidade) {
										produto.setQuantidade(produto.getQuantidade() - quantidade);
										encontrado = true;
										break;
									} else {
										JOptionPane.showMessageDialog(null, "Quantidade insuficiente em estoque!");
										return;
									}
								}
							}
							if (!encontrado) {
								JOptionPane.showMessageDialog(null, "Produto não encontrado em estoque!");
								return;
							}
						}

						// Exibe uma mensagem de confirmação
						String mensagem = String.format(
								"%s de %d unidades do produto %s (código %s) realizada com sucesso!",
								operacao.substring(0, 1).toUpperCase() + operacao.substring(1), quantidade, nome,
								codigo);
						JOptionPane.showMessageDialog(TelaEntradaSaidaProduto.this, mensagem);

						// Limpa os campos
						codigoField.setText("");
						nomeField.setText("");
						quantidadeField.setText("");
					}
				});

				// Exibe uma mensagem de confirmação
				String mensagem = String.format("%s de %d unidades do produto %s (código %s) realizada com sucesso!",
						operacao.substring(0, 1).toUpperCase() + operacao.substring(1), quantidade, nome, codigo);
				JOptionPane.showMessageDialog(TelaEntradaSaidaProduto.this, mensagem);

				// Limpa os campos
				codigoField.setText("");
				nomeField.setText("");
				quantidadeField.setText("");
			}
		});

		// Adiciona um listener para o botão de cancelamento
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				codigoField.setText("");
				nomeField.setText("");
				quantidadeField.setText("");
			}
		});
	}
}
