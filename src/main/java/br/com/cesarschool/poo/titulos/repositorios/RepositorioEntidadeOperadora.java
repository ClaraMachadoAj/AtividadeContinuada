package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

public class RepositorioEntidadeOperadora extends RepositorioGeral<EntidadeOperadora> {

    private static final String FILE_NAME = "EntidadeOperadora.txt";

    @Override
    public Class<EntidadeOperadora> getClasseEntidade() {
        return EntidadeOperadora.class;
    }

    // Sobrecarga de método para buscar por identificador int
    public EntidadeOperadora buscar(int idUnico) {
        return super.buscar(String.valueOf(idUnico)); // Converte int para String
    }

    // Sobrecarga de método para buscar por identificador long
    public EntidadeOperadora buscar(long idUnico) {
        return super.buscar(String.valueOf(idUnico)); // Converte long para String
    }

    // Sobrecarga de método para excluir por identificador int
    public boolean excluir(int idUnico) {
        return super.excluir(String.valueOf(idUnico)); // Converte int para String
    }

    // Sobrecarga de método para excluir por identificador long
    public boolean excluir(long idUnico) {
        return super.excluir(String.valueOf(idUnico)); // Converte long para String
    }
}
