package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;

public class TelaExemploEntidade extends JFrame {

    private EntidadeMediator mediator = new EntidadeMediator();
    private JTextField txtCodigo, txtNome, txtRenda;
    private JButton btnNovo, btnBuscar, btnIncluirAlterar, btnCancelar, btnLimpar;

    public TelaExemploEntidade() {
        setTitle("Cadastro de Entidades");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Código
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(30, 30, 70, 25);
        add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(110, 30, 150, 25);
        add(txtCodigo);

        // Nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 80, 70, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(110, 80, 150, 25);
        txtNome.setEnabled(false);
        add(txtNome);

        // Renda
        JLabel lblRenda = new JLabel("Renda:");
        lblRenda.setBounds(30, 130, 70, 25);
        add(lblRenda);

        txtRenda = new JTextField();
        txtRenda.setBounds(110, 130, 150, 25);
        txtRenda.setEnabled(false);
        add(txtRenda);

        // Botão Novo
        btnNovo = new JButton("Novo");
        btnNovo.setBounds(300, 30, 100, 30);
        btnNovo.addActionListener(e -> habilitarCampos(true));
        add(btnNovo);

        // Botão Buscar
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(420, 30, 100, 30);
        btnBuscar.addActionListener(e -> {
            Entidade ent = mediator.buscar(txtCodigo.getText());
            if (ent == null) {
                JOptionPane.showMessageDialog(this, "Entidade não encontrada!");
            } else {
                txtNome.setText(ent.getNome());
                txtRenda.setText(String.valueOf(ent.getRenda()));
                btnIncluirAlterar.setText("Alterar");
                habilitarCampos(true);
            }
        });
        add(btnBuscar);

        // Botão Incluir/Alterar
        btnIncluirAlterar = new JButton("Incluir");
        btnIncluirAlterar.setBounds(110, 200, 100, 30);
        btnIncluirAlterar.setEnabled(false);
        btnIncluirAlterar.addActionListener(e -> {
            try {
                Entidade ent = new Entidade(
                        txtCodigo.getText(),
                        txtNome.getText(),
                        Double.parseDouble(txtRenda.getText())
                );
                String msg;
                if (btnIncluirAlterar.getText().equals("Incluir")) {
                    msg = mediator.incluir(ent);
                    if (msg != null && !msg.equals(ent.getCodigo())) {
                        JOptionPane.showMessageDialog(this, msg);
                    } else {
                        JOptionPane.showMessageDialog(this, "Entidade incluída com sucesso! Código: " + msg);
                        limparCampos();
                        habilitarCampos(false);
                    }
                } else {
                    msg = mediator.alterar(ent);
                    if (msg != null) {
                        JOptionPane.showMessageDialog(this, msg);
                    } else {
                        JOptionPane.showMessageDialog(this, "Entidade alterada com sucesso!");
                        limparCampos();
                        habilitarCampos(false);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Renda deve ser um número válido!");
            }
        });
        add(btnIncluirAlterar);

        // Botão Cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(230, 200, 100, 30);
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(e -> {
            limparCampos();
            habilitarCampos(false);
        });
        add(btnCancelar);

        // Botão Limpar
        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(350, 200, 100, 30);
        btnLimpar.addActionListener(e -> limparCampos());
        add(btnLimpar);
    }

    private void habilitarCampos(boolean habilitar) {
        txtNome.setEnabled(habilitar);
        txtRenda.setEnabled(habilitar);
        btnIncluirAlterar.setEnabled(habilitar);
        btnCancelar.setEnabled(habilitar);
        btnNovo.setEnabled(!habilitar);
        btnBuscar.setEnabled(!habilitar);
        txtCodigo.setEnabled(!habilitar);
    }

    private void limparCampos() {
        txtCodigo.setText("");
        txtNome.setText("");
        txtRenda.setText("");
        btnIncluirAlterar.setText("Incluir");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaExemploEntidade tela = new TelaExemploEntidade();
            tela.setVisible(true);
        });
    }
}
