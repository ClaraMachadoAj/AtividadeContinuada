package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

public class RepositorioTituloDivida extends RepositorioGeral<TituloDivida> {

	@Override
	public Class<TituloDivida> getClasseEntidade() {
		return TituloDivida.class; // Retorna a classe da entidade
	}

	// Sobrecarga para atender ao teste que exige buscar por int
	public EntidadeOperadora buscar(int idUnico) {
		return super.buscar(String.valueOf(idUnico)); // Converte o int para String e utiliza o método genérico
	}

	// Sobrecarga para atender ao teste que exige exclusão por int
	public boolean excluir(int idUnico) {
		return super.excluir(String.valueOf(idUnico)); // Converte o int para String e utiliza o método genérico
	}
}
