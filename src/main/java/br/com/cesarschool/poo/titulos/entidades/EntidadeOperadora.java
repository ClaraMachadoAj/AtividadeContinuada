/*
 * Esta classe deve ter os seguintes atributos:
 * identificador, do tipo long        OK
 * nome, do tipo String               OK
 * autorizadoAcao, do tipo double     OK
 * saldoAcao, do tipo double          OK
 * saldoTituloDivida, do tipo double  OK
 * 
 * Deve ter um construtor público que inicializa os atributos identificador, nome               OK
 * e autorizadoAcao. Deve ter métodos set/get públicos para os atributos identificador, nome    OK
 * e autorizadoAcao. O atributo identificador é read-only fora da classe.                       OK
 * 
 * Os atributos saldoAcao e saldoTituloDivida devem ter apenas métodos get públicos.            OK
 * 
 * Outros métodos públicos:
 * 
 *  void creditarSaldoAcao(double valor): deve adicionar valor ao saldoAcao                     OK
 *  void debitarSaldoAcao(double valor): deve diminuir valor de saldoAcao                       OK
 *  void creditarSaldoTituloDivida(double valor): deve adicionar valor ao saldoTituloDivida     OK
 *  void debitarSaldoTituloDivida(double valor): deve diminuir valor de saldoTituloDivida       OK
 */

package br.com.cesarschool.poo.titulos.entidades;

import br.gov.cesarschool.poo.daogenerico.Entidade;

public class EntidadeOperadora extends Entidade {
    private static final long serialVersionUID = 1L;

    private final long identificador;
    private String nome;
    private double autorizadoAcao;
    private double saldoAcao;
    private double saldoTituloDivida;

    public EntidadeOperadora(long identificador, String nome, double autorizadoAcao) {
        super(String.valueOf(identificador));
        this.identificador = identificador;
        this.nome = nome;
        this.autorizadoAcao = autorizadoAcao;
        this.saldoAcao = 0.0;
        this.saldoTituloDivida = 0.0;
    }

    @Override
    public String getIdUnico() {
        return String.valueOf(identificador);
    }

    // Métodos get/set e outros
    public long getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getAutorizadoAcao() {
        return autorizadoAcao;
    }

    public void setAutorizadoAcao(double autorizadoAcao) {
        this.autorizadoAcao = autorizadoAcao;
    }

    public double getSaldoAcao() {
        return saldoAcao;
    }

    public void creditarSaldoAcao(double valor) {
        saldoAcao += valor;
    }

    public void debitarSaldoAcao(double valor) {
        if (valor <= saldoAcao) {
            saldoAcao -= valor;
        }
    }

    public double getSaldoTituloDivida() {
        return saldoTituloDivida;
    }

    public void creditarSaldoTituloDivida(double valor) {
        saldoTituloDivida += valor;
    }

    public void debitarSaldoTituloDivida(double valor) {
        if (valor <= saldoTituloDivida) {
            saldoTituloDivida -= valor;
        }
    }

    }
