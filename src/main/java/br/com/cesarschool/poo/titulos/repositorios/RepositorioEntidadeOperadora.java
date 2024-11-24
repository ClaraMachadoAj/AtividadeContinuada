package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

public class RepositorioEntidadeOperadora extends RepositorioGeral<EntidadeOperadora> {

    private static final String FILE_NAME = "EntidadeOperadora.txt";

    @Override
    protected Class<EntidadeOperadora> getClasseEntidade() {
        return EntidadeOperadora.class;
    }

    @Override
    public EntidadeOperadora buscar(int idUnico) {
        return buscar((long) idUnico); // Converte int para long
    }

    public boolean excluir(long identificador) {
        return super.excluir(Integer.parseInt(String.valueOf(identificador))); // Reutiliza o método de RepositorioGeral
    }

    public EntidadeOperadora buscar(long identificador) {
        return super.buscar(String.valueOf(identificador)); // Reutiliza o método de RepositorioGeral
    }

    // Métodos adicionais para manipular arquivos (opcional, caso necessário)
    // Já implementados em RepositorioGeral
}
