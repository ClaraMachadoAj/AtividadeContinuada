package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAdicao extends JFrame {

    private JTextField txtPrimeiroNumero;
    private JTextField txtSegundoNumero;
    private JTextField txtResultado;

    public TelaAdicao() {
        setTitle("Tela de Adição");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Usando layout absoluto, como no exemplo SWT

        // Primeiro número
        JLabel lblPrimeiroNumero = new JLabel("Primeiro Número:");
        lblPrimeiroNumero.setBounds(30, 30, 120, 30);
        add(lblPrimeiroNumero);

        txtPrimeiroNumero = new JTextField();
        txtPrimeiroNumero.setBounds(160, 30, 100, 30);
        add(txtPrimeiroNumero);

        // Segundo número
        JLabel lblSegundoNumero = new JLabel("Segundo Número:");
        lblSegundoNumero.setBounds(30, 80, 120, 30);
        add(lblSegundoNumero);

        txtSegundoNumero = new JTextField();
        txtSegundoNumero.setBounds(160, 80, 100, 30);
        add(txtSegundoNumero);

        // Resultado
        JLabel lblResultado = new JLabel("Resultado:");
        lblResultado.setBounds(30, 130, 120, 30);
        add(lblResultado);

        txtResultado = new JTextField();
        txtResultado.setBounds(160, 130, 100, 30);
        txtResultado.setEditable(false);
        add(txtResultado);

        // Botão Somar
        JButton btnSomar = new JButton("Somar");
        btnSomar.setBounds(70, 200, 100, 30);
        btnSomar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double n1 = Double.parseDouble(txtPrimeiroNumero.getText());
                    double n2 = Double.parseDouble(txtSegundoNumero.getText());
                    double soma = n1 + n2;
                    txtResultado.setText(String.valueOf(soma));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira números válidos.");
                }
            }
        });
        add(btnSomar);

        // Botão Limpar
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(200, 200, 100, 30);
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtPrimeiroNumero.setText("");
                txtSegundoNumero.setText("");
                txtResultado.setText("");
            }
        });
        add(btnLimpar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaAdicao tela = new TelaAdicao();
            tela.setVisible(true);
        });
    }
}
