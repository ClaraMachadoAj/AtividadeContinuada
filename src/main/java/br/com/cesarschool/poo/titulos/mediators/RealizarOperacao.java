package br.com.cesarschool.poo.titulos.mediators;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaAcao extends JFrame {
    private MediatorAcao mediator = new MediatorAcao();

    public TelaAcao() {
        setTitle("CRUD de Ações");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu com opções do CRUD
        String[] opcoes = {"Incluir", "Alterar", "Excluir", "Buscar", "Sair"};
        JComboBox<String> menu = new JComboBox<>(opcoes);

        // Campos de entrada
        JTextField campoId = new JTextField(10);
        JTextField campoNome = new JTextField(10);
        JTextField campoValor = new JTextField(10);
        JButton botaoExecutar = new JButton("Executar");

        // Área para mostrar mensagens
        JTextArea areaMensagens = new JTextArea(5, 30);
        areaMensagens.setEditable(false);

        // Painel principal para organizar os componentes
        JPanel painel = new JPanel(new FlowLayout());
        painel.add(new JLabel("ID:"));
        painel.add(campoId);
        painel.add(new JLabel("Nome:"));
        painel.add(campoNome);
        painel.add(new JLabel("Valor:"));
        painel.add(campoValor);
        painel.add(menu);
        painel.add(botaoExecutar);

        // Adiciona os componentes à janela
        add(painel, BorderLayout.NORTH);
        add(new JScrollPane(areaMensagens), BorderLayout.CENTER);

        // Ação do botão para executar as operações do CRUD
        botaoExecutar.addActionListener(e -> {
            String operacao = (String) menu.getSelectedItem();
            int id = Integer.parseInt(campoId.getText());
            String nome = campoNome.getText();
            double valor = Double.parseDouble(campoValor.getText());

            String mensagem = switch (operacao) {
                case "Incluir" -> mediator.incluirAcao(new Acao(id, nome, valor));
                case "Alterar" -> mediator.alterarAcao(id, nome, valor);
                case "Excluir" -> mediator.excluirAcao(id);
                case "Buscar" -> {
                    var acao = mediator.buscarAcao(id);
                    if (acao.isPresent()) yield acao.get().toString();
                    else yield "Ação não encontrada.";
                }
                case "Sair" -> {
                    System.exit(0);
                    yield "Encerrando...";
                }
                default -> "Operação inválida.";
            };

            areaMensagens.setText(mensagem);
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaAcao::new);
    }
}
