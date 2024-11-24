package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

public class RepositorioAcao extends RepositorioGeral<Acao> {

	@Override
	public Class<Acao> getClasseEntidade() {
		return Acao.class; // Retorna a classe da entidade
	}

	// Sobrecarga para atender ao teste que exige buscar por int
	public EntidadeOperadora buscar(int idUnico) {
		return super.buscar(String.valueOf(idUnico)); // Converte o int para String
	}

	@Override
	public boolean excluir(int id) {
		return false;
	}
}
