package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;

public class RepositorioAcao extends RepositorioGeral<Acao> {

	@Override
	protected Class<Acao> getClasseEntidade() {
		return Acao.class; // Retorna a classe da entidade
	}
}
