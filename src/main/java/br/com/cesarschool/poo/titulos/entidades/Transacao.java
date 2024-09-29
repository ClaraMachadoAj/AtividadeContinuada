/*
 * Esta classe deve ter os seguintes atributos:
 * entidadeCredito, do tipo EntidadeOperadora      OK
 * entidadeDebito, do tipo EntidadeOperadora       OK
 * acao, do tipo Acao                              OK
 * tituloDivida, do tipo TituloDivida              OK
 * valorOperacao, do tipo double                   OK
 * dataHoraOperacao, do tipo LocalDateTime         OK
 *  
 * Deve ter um construtor p�blico que inicializa os atributos.       OK
 * Deve ter m�todos get/set p�blicos para todos os atributos, que    OK
 * s�o read-only fora da classe.                                     OK
 * 
 *  
 */

package br.com.cesarschool.poo.titulos.entidades;

import java.time.LocalDateTime;

public class Transacao {

    // ATRIBUTOS

    private final EntidadeOperadora entidadeCredito;
    private final EntidadeOperadora entidadeDebito;
    private final Acao acao;
    private final TituloDivida tituloDivida;
    private final double valorOperacao;
    private final LocalDateTime dataHoraOperacao;


    public Transacao(EntidadeOperadora entidadeCredito, EntidadeOperadora entidadeDebito, Acao acao,
                     TituloDivida tituloDivida, double valorOperacao, LocalDateTime dataHoraOperacao) {
        this.entidadeCredito = entidadeCredito;
        this.entidadeDebito = entidadeDebito;
        this.acao = acao;
        this.tituloDivida = tituloDivida;
        this.valorOperacao = valorOperacao;
        this.dataHoraOperacao = dataHoraOperacao;
    }

    // MÉTODOS

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
