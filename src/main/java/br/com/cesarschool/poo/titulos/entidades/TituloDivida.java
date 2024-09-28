package br.com.cesarschool.poo.titulos.entidades;

import java.time.LocalDate;

/*
 * Esta classe deve herdar de Ativo.
 * E deve ter os seguintes atributos:
 * taxaJuros, do tipo double.
 * 
 * Deve ter um construtor público que inicializa os atributos, 
 * e métodos set/get públicos para os atributos.
 * 
 * Deve ter um metodo público double calcularPrecoTransacao(double montante): o preço
 * da transação é montante vezes (1 - taxaJuros/100.0).
 */

public class TituloDivida extends Ativo {

    // Atributo taxaJuros, do tipo double, que representa a taxa de juros aplicada.
    private double taxaJuros;

    // Construtor público que inicializa os atributos, incluindo taxaJuros.
    public TituloDivida(int identificador, String nome, LocalDate dataValidade, double taxaJuros) {
        // Chama o construtor da classe pai (Ativo).
        super(identificador, nome, dataValidade);
        // Inicializa o atributo taxaJuros com o valor passado como parâmetro.
        this.taxaJuros = taxaJuros;
    }

    // Metodo getter para o atributo taxaJuros.
    public double getTaxaJuros() {
        return taxaJuros;
    }

    // Metodo setter para o atributo taxaJuros.
    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    // Metodo público que calcula o preço da transação.
    public double calcularPrecoTransacao(double montante) {
        return montante * (1 - taxaJuros / 100.0);
    }
}
