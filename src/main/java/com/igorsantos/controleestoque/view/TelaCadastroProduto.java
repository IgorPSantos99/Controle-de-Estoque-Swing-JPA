package com.igorsantos.controleestoque.view;

import java.awt.*;
import javax.swing.*;

public class TelaCadastroProduto extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JLabel codigoLabel, descricaoLabel, quantidadeLabel;
    private JTextField codigoField, descricaoField, quantidadeField;
    private JButton salvarButton, cancelarButton;

    public TelaCadastroProduto() {
        super("Cadastro de Produto");

        // Configura a janela de cadastro
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Cria os componentes da tela de cadastro
        codigoLabel = new JLabel("Código:");
        descricaoLabel = new JLabel("Descrição:");
        quantidadeLabel = new JLabel("Quantidade:");
        codigoField = new JTextField(8);
        descricaoField = new JTextField(20);
        quantidadeField = new JTextField(8);
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

}

