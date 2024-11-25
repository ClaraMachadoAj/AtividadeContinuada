package br.com.cesarschool.poo.titulos.repositorios;

import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.gov.cesarschool.poo.daogenerico.Entidade;

import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class RepositorioGeral<T extends Entidade> {

    protected DAOSerializadorObjetos dao;

    public RepositorioGeral() {
        this.dao = new DAOSerializadorObjetos(getClasseEntidade());
    }

    // Método abstrato que deve ser implementado pelas subclasses
    public abstract Class<T> getClasseEntidade();

    public DAOSerializadorObjetos getDao() {
        return dao;
    }

    public boolean incluir(T entidade) {
        System.out.println("Incluindo entidade com ID: " + entidade.getIdUnico());
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
        Entidade[] entidades = dao.buscarTodos();
        if (entidades == null) {
            System.out.println("Nenhuma entidade encontrada.");
            return (T[]) Array.newInstance(getClasseEntidade(), 0);
        }
        System.out.println("Entidades carregadas: " + Arrays.toString(entidades));
        return Arrays.copyOf(entidades, entidades.length, (Class<? extends T[]>) Array.newInstance(getClasseEntidade(), 0).getClass());
    }



    // Sobrecarga para buscar por identificador inteiro
    public abstract T buscar(int idUnico);

    public abstract boolean excluir(int id);
}
