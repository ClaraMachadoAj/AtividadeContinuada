package br.com.cesarschool.poo.titulos.utils;

import br.com.cesarschool.poo.titulos.entidades.Transacao;

public class ComparadorTransacaoPorNomeCredora extends ComparadorPadrao {
    @Override
    public int comparar(Comparavel c1, Comparavel c2) {
        if (!(c1 instanceof Transacao) || !(c2 instanceof Transacao)) {
            throw new IllegalArgumentException("Os objetos comparados devem ser do tipo Transacao.");
        }
        Transacao t1 = (Transacao) c1;
        Transacao t2 = (Transacao) c2;

        // Verifica se as entidades de crédito existem antes de tentar acessar os nomes
        if (t1.getEntidadeCredito() == null || t2.getEntidadeCredito() == null) {
            throw new NullPointerException("A entidade de crédito não pode ser nula.");
        }

        // Compara os nomes das entidades credoras
        return t1.getEntidadeCredito().getNome().compareToIgnoreCase(t2.getEntidadeCredito().getNome());
    }
}
