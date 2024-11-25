package br.com.cesarschool.poo.titulos.relatorios;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;
import br.com.cesarschool.poo.titulos.utils.ComparadorTransacaoPorNomeCredora;
import br.com.cesarschool.poo.titulos.utils.Ordenador;

public class RelatorioTransacaoBroker {
    private final RepositorioTransacao repositorioTransacao;

    public RelatorioTransacaoBroker() {
        this.repositorioTransacao = new RepositorioTransacao();
    }

    public Transacao[] relatorioTransacaoPorNomeEntidadeCredora() {
        // Busca todas as transações no repositório
        Transacao[] transacoes = repositorioTransacao.buscarTodos(); // Buscar todas as transações
        Ordenador.ordenar(transacoes, new ComparadorTransacaoPorNomeCredora());
        return transacoes;
    }

    public Transacao[] relatorioTransacaoPorDataHora() {
        // Busca todas as transações no repositório
        Transacao[] transacoes = repositorioTransacao.buscarTodos(); // Buscar todas as transações
        Ordenador.ordenar(transacoes); // Ordena as transações
        return transacoes;
    }
}
