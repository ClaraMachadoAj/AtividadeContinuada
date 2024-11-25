package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

public class RepositorioTituloDivida extends RepositorioGeral<TituloDivida> {

	@Override
	public Class<TituloDivida> getClasseEntidade() {
		return TituloDivida.class; // Retorna a classe da entidade
	}

	@Override
	public TituloDivida buscar(int idUnico) {
		return super.buscar(String.valueOf(idUnico)); // Converte int para String e usa a implementação de RepositorioGeral
	}

	@Override
	public boolean excluir(int id) {
		return super.excluir(String.valueOf(id)); // Converte int para String e usa a implementação de RepositorioGeral
	}
}
