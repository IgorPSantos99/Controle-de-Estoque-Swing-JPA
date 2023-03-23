package com.igorsantos.controleestoque.view;

import javax.swing.*;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class TelaLogin extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField usuarioField;
    private JPasswordField senhaField;
    private JButton loginButton;
    private JLabel mensagemLabel;

    public TelaLogin() {
        super("Login");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Cria os componentes da tela de login
        JLabel usuarioLabel = new JLabel("Usuário:");
        JLabel senhaLabel = new JLabel("Senha:");
        usuarioField = new JTextField(20);
        senhaField = new JPasswordField(20);
        loginButton = new JButton("Login");
        mensagemLabel = new JLabel("");

        // Adiciona os componentes à janela de login
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        container.add(usuarioLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        container.add(usuarioField, c);

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        container.add(senhaLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        container.add(senhaField, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        container.add(loginButton, c);

        c.gridy = 3;
        container.add(mensagemLabel, c);

     // Cria a tela principal
        TelaPrincipal telaPrincipal = new TelaPrincipal();

        // Adiciona o listener para o botão de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Entrada de Credenciais
                String usuario = usuarioField.getText();
                String senha = new String(senhaField.getPassword());
                boolean autenticado = autenticarUsuario(usuario, senha);

                if (autenticado) {
                    // Fecha a tela de login e abre a tela principal
                    setVisible(false);
                    telaPrincipal.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(TelaLogin.this, "Credenciais inválidas. Por favor, tente novamente.");
                }
            }
        });

        setVisible(true);
    }

    // Geralmente esse método ficaria em outra classe, porém para exemplificar
    private boolean autenticarUsuario(String usuario, String senha) {
       // Aqui podemos fazer uma chamada para um método de validação de senhas
      // como SHA256, como exemplo vamos usar "admin" e "1234"
        return "admin".equals(usuario) && "1234".equals(senha);
    }

}
