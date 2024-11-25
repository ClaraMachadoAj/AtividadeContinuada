package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;

public class RepositorioAcao extends RepositorioGeral<Acao> {

	@Override
	public Class<Acao> getClasseEntidade() {
		return Acao.class; // Retorna a classe da entidade
	}

	@Override
	public Acao buscar(int idUnico) {
		return super.buscar(String.valueOf(idUnico)); // Converte int para String e usa a implementação de RepositorioGeral
	}

	@Override
	public boolean excluir(int id) {
		return super.excluir(String.valueOf(id)); // Converte int para String e usa a implementação de RepositorioGeral
	}
}
