package br.gov.cesarschool.poo.testes;

import br.com.cesarschool.poo.titulos.utils.Comparavel;
import br.gov.cesarschool.poo.daogenerico.Entidade;

public class EntidadeModelo extends Entidade implements Comparavel{
    private final int id;
    private final String nome;

    public EntidadeModelo(int id, String nome) {
        super(String.valueOf(id)); // Passando o ID como argumento para o construtor da superclasse
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String getIdUnico() {
        return String.valueOf(id);
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int comparar(Comparavel outra) {
        if (outra instanceof EntidadeModelo) {
            EntidadeModelo outroModelo = (EntidadeModelo) outra;
            return this.nome.compareTo(outroModelo.nome); // Compara pelo atributo 'nome'
        }
        throw new IllegalArgumentException("Tipo incompatível para comparação.");
    }

}
