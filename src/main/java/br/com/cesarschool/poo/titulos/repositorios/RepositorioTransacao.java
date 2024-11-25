package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import java.util.Arrays;

public class RepositorioTransacao extends RepositorioGeral<Transacao> {

	private static final String FILE_NAME = "Transacao.txt";

	@Override
	public Class<Transacao> getClasseEntidade() {
		return Transacao.class;
	}

	@Override
	public Transacao buscar(int idUnico) {
		// Conversão do ID de int para String para garantir compatibilidade
		return super.buscar(String.valueOf(idUnico));
	}

	@Override
	public boolean excluir(int id) {
		// Chamada ao método de exclusão da classe pai com conversão
		return super.excluir(String.valueOf(id));
	}

	public Transacao[] buscarPorEntidadeCredora(long identificadorEntidadeCredito) {
		// Busca transações filtrando pela entidade credora
		return Arrays.stream(buscarTodos())
				.filter(transacao -> transacao.getEntidadeCredito().getIdentificador() == identificadorEntidadeCredito)
				.toArray(Transacao[]::new);
	}

	public Transacao[] buscarPorEntidadeDevedora(long identificadorEntidadeDebito) {
		// Busca transações filtrando pela entidade devedora
		return Arrays.stream(buscarTodos())
				.filter(transacao -> transacao.getEntidadeDebito().getIdentificador() == identificadorEntidadeDebito)
				.toArray(Transacao[]::new);
	}
}
