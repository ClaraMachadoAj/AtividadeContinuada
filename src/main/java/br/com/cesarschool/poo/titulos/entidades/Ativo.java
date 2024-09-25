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
    private int identificador;
    private String nome;
    private LocalDate dataDeValidade;

    // Construtor público que inicializa os atributos
    public Ativo(int identificador, String nome, LocalDate dataDeValidade) {
        this.identificador = identificador;
        this.nome = nome;
        this.dataDeValidade = dataDeValidade;
    }

    // Metodo get para o identificador (read-only)
    public int getIdentificador() {
        return identificador;
    }

    // Métodos set e get para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Métodos set e get para dataDeValidade
    public LocalDate getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(LocalDate dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }
}

// Isso é um teste - by Lari
