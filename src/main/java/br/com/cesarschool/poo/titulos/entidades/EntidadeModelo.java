package br.com.cesarschool.poo.titulos.entidades;

import br.com.cesarschool.poo.titulos.utils.Comparavel;

public class EntidadeModelo implements Comparavel {
    private final int id;
    private final String nome;

    public EntidadeModelo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getIdUnico() {
        return String.valueOf(id);
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int comparar(Comparavel outra) {
        EntidadeModelo outroModelo = (EntidadeModelo) outra;
        return this.nome.compareTo(outroModelo.getNome());
    }
}
