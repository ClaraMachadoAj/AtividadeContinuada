package br.com.cesarschool.poo.titulos.repositorios;

import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.gov.cesarschool.poo.daogenerico.Entidade;

public abstract class RepositorioGeral<T extends Entidade> {

    protected DAOSerializadorObjetos dao;

    public RepositorioGeral() {
        this.dao = new DAOSerializadorObjetos(getClasseEntidade());
    }

    protected abstract Class<T> getClasseEntidade();

    public boolean incluir(T entidade) {
        return dao.incluir(entidade);
    }

    public boolean alterar(T entidade) {
        return dao.alterar(entidade);
    }

    public boolean excluir(String idUnico) {
        return dao.excluir(idUnico);
    }

    public T buscar(String idUnico) {
        return (T) dao.buscar(idUnico);
    }

    public T[] buscarTodos() {
        return (T[]) dao.buscarTodos();
    }
}
