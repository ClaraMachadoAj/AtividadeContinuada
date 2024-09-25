package br.com.cesarschool.poo.titulos.entidades;
import java.time.LocalDate;
/*
 * Esta classe deve herdar de Ativo.
 * E deve ter os seguintes atributos:
 * valorUnitario, do tipo double.
 * 
 * Deve ter um construtor público que inicializa os atributos, 
 * e métodos set/get públicos para os atributos.
 * 
 * Deve ter um metodo público double calcularPrecoTransacao(double montante): o preço
 * da transação é o produto do montante pelo valorUnitario.
 */

public class Acao extends Ativo {

    // Atributo valorUnitario, do tipo double
    private double valorUnitario;

    // Construtor público que inicializa os atributos
    public Acao(int identificador, String nome, LocalDate dataDeValidade, double valorUnitario) {
        super(identificador, nome, dataDeValidade); // Chama o construtor da classe pai (Ativo)
        this.valorUnitario = valorUnitario;
    }

    // Metodo set para valorUnitario
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    // Metodo get para valorUnitario
    public double getValorUnitario() {
        return valorUnitario;
    }

    // Metodo público calcularPrecoTransacao(double montante)
    public double calcularPrecoTransacao(double montante) {
        return montante * valorUnitario;
    }
}
