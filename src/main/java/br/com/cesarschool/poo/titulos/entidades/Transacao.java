package br.com.cesarschool.poo.titulos.entidades;

import br.gov.cesarschool.poo.daogenerico.Entidade;
import br.com.cesarschool.poo.titulos.utils.Comparavel;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transacao extends Entidade implements Serializable, Comparavel {
    private static final long serialVersionUID = 1L;

    private final EntidadeOperadora entidadeCredito;
    private final EntidadeOperadora entidadeDebito;
    private final Acao acao;
    private final TituloDivida tituloDivida;
    private final double valorOperacao;
    private final LocalDateTime dataHoraOperacao;

    public Transacao(EntidadeOperadora entidadeCredito, EntidadeOperadora entidadeDebito, Acao acao,
                     TituloDivida tituloDivida, double valorOperacao, LocalDateTime dataHoraOperacao) {
        super("Transacao");
        this.entidadeCredito = entidadeCredito;
        this.entidadeDebito = entidadeDebito;
        this.acao = acao;
        this.tituloDivida = tituloDivida;
        this.valorOperacao = valorOperacao;
        this.dataHoraOperacao = dataHoraOperacao;
    }

    @Override
    public int comparar(Comparavel c) {
        if (c instanceof Transacao) {
            Transacao outra = (Transacao) c;
            return outra.dataHoraOperacao.compareTo(this.dataHoraOperacao);
        }
        throw new IllegalArgumentException("Comparação com tipo incompatível.");
    }

    @Override
    public String getIdUnico() {
        return String.format("%s_%s_%s_%s",
                entidadeCredito != null ? entidadeCredito.getIdUnico() : "null",
                entidadeDebito != null ? entidadeDebito.getIdUnico() : "null",
                acao != null ? acao.getIdUnico() : tituloDivida.getIdUnico(),
                dataHoraOperacao.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
    }

    // Getters para os atributos
    public EntidadeOperadora getEntidadeCredito() {
        return entidadeCredito;
    }

    public EntidadeOperadora getEntidadeDebito() {
        return entidadeDebito;
    }

    public Acao getAcao() {
        return acao;
    }

    public TituloDivida getTituloDivida() {
        return tituloDivida;
    }

    public double getValorOperacao() {
        return valorOperacao;
    }

    public LocalDateTime getDataHoraOperacao() {
        return dataHoraOperacao;
    }
}
