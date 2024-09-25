package br.com.cesarschool.poo.titulos.entidades;

import java.time.LocalDate;

/*
 * Esta classe deve ter os seguintes atributos:
 * identificador, do tipo int
 * nome, do tipo String
 * data de validade, do tipo LocalDate
 * 
 * Deve ter um construtor público que inicializa os atributos, 
 * e métodos set/get públicos para os atributos. O atributo identificador
 * é read-only fora da classe.
 */
public class Ativo {
    int identificador;
    String nome;
    LocalDate dataDeValidade;

}
