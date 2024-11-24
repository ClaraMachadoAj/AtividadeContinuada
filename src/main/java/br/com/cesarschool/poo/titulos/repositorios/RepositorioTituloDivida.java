package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

public class RepositorioTituloDivida extends RepositorioGeral<TituloDivida> {

	private static final String FILE_NAME = "TituloDivida.txt";

	@Override
	public Class<TituloDivida> getClasseEntidade() {
		return TituloDivida.class;
	}

	// Sobrecarga para buscar por identificador inteiro
	@Override
	public TituloDivida buscar(int idUnico) {
		return buscar(String.valueOf(idUnico)); // Reutiliza o método genérico
	}

	// Implementações específicas, se necessárias, reutilizando métodos da superclasse
}
