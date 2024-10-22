package org.cesarschool.telas;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntdadeOperadora;
import java.util.Scanner;

public class TelaCrudConsole {
    private final MediatorEntdadeOperadora mediator = MediatorEntdadeOperadora.getInstancia();
    private final Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        while (true) {
            System.out.println("\n--- Menu CRUD Entidade Operadora ---");
            System.out.println("1. Incluir Entidade");
            System.out.println("2. Alterar Entidade");
            System.out.println("3. Excluir Entidade");
            System.out.println("4. Buscar Entidade");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1 -> incluirEntidade();
                case 2 -> alterarEntidade();
                case 3 -> excluirEntidade();
                case 4 -> buscarEntidade();
                case 5 -> {
                    System.out.println("Encerrando...");
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void incluirEntidade() {
        System.out.print("Digite o identificador: ");
        long identificador = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o valor autorizado para ações: ");
        double autorizadoAcao = scanner.nextDouble();

        EntidadeOperadora entidade = new EntidadeOperadora(identificador, nome, autorizadoAcao);
        String mensagem = mediator.incluir(entidade);

        if (mensagem == null) {
            System.out.println("Entidade incluída com sucesso!");
        } else {
            System.out.println(mensagem);
        }
    }

    private void alterarEntidade() {
        System.out.print("Digite o identificador da entidade: ");
        long identificador = scanner.nextLong();
        scanner.nextLine();
        EntidadeOperadora entidadeExistente = mediator.buscar((int) identificador);

        if (entidadeExistente == null) {
            System.out.println("Entidade não encontrada!");
            return;
        }

        System.out.print("Digite o novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o novo valor autorizado para ações: ");
        double autorizadoAcao = scanner.nextDouble();

        EntidadeOperadora novaEntidade = new EntidadeOperadora(identificador, nome, autorizadoAcao);
        String mensagem = mediator.alterar(novaEntidade);

        if (mensagem == null) {
            System.out.println("Entidade alterada com sucesso!");
        } else {
            System.out.println(mensagem);
        }
    }

    private void excluirEntidade() {
        System.out.print("Digite o identificador da entidade: ");
        long identificador = scanner.nextLong();
        scanner.nextLine();
        String mensagem = mediator.excluir((int) identificador);

        if (mensagem == null) {
            System.out.println("Entidade excluída com sucesso!");
        } else {
            System.out.println(mensagem);
        }
    }

    private void buscarEntidade() {
        System.out.print("Digite o identificador da entidade: ");
        long identificador = scanner.nextLong();
        scanner.nextLine();
        EntidadeOperadora entidade = mediator.buscar((int) identificador);

        if (entidade == null) {
            System.out.println("Entidade não encontrada!");
        } else {
            System.out.println("ID: " + entidade.getIdentificador());
            System.out.println("Nome: " + entidade.getNome());
            System.out.println("Autorizado para Ações: " + entidade.getAutorizadoAcao());
        }
    }
}
