package com.igorsantos.controleestoque.view;

import java.awt.*;
import javax.swing.*;

public class TelaCadastroProduto extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JLabel codigoLabel, descricaoLabel, quantidadeLabel, precoLabel;
    private JTextField codigoField, descricaoField, quantidadeField, precoField;
    private JButton salvarButton, cancelarButton;

    public TelaCadastroProduto() {
        super("Cadastro de Produto");

        // Configura a janela de cadastro
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Cria os componentes da tela de cadastro
        codigoLabel = new JLabel("Código:");
        descricaoLabel = new JLabel("Descrição:");
        quantidadeLabel = new JLabel("Quantidade:");
        precoLabel = new JLabel("Preço:");
        codigoField = new JTextField(10);
        descricaoField = new JTextField(30);
        quantidadeField = new JTextField(10);
        precoField = new JTextField(10);
        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");

        // Adiciona os componentes à janela de cadastro
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        container.add(codigoLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        container.add(codigoField, c);

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        container.add(descricaoLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        container.add(descricaoField, c);

        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_END;
        container.add(quantidadeLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        container.add(quantidadeField, c);

        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_END;
        container.add(precoLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        container.add(precoField, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        container.add(salvarButton, c);

        c.gridy = 5;
        container.add(cancelarButton, c);

        // Exibe a janela de cadastro
        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaCadastroProduto();
    }

}

