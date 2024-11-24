package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTransacao extends RepositorioGeral<Transacao> {

	private static final String FILE_NAME = "Transacao.txt";
	private static final DateTimeFormatter DATE_TIME_FORMATTER =
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public Class<Transacao> getClasseEntidade() {
		return Transacao.class;
	}

	@Override
	public EntidadeOperadora buscar(int idUnico) {
		return null;
	}

	@Override
	public boolean excluir(int id) {
		return false;
	}

	// Método para buscar transações pelo identificador da entidade credora
	public Transacao[] buscarPorEntidadeCredora(long identificadorEntidadeCredito) {
		return buscarTransacoes(identificadorEntidadeCredito, true);
	}

	// Método para buscar transações pelo identificador da entidade devedora
	public Transacao[] buscarPorEntidadeDevedora(long identificadorEntidadeDebito) {
		return buscarTransacoes(identificadorEntidadeDebito, false);
	}

	// Método auxiliar para buscar transações de acordo com o tipo de entidade
	private Transacao[] buscarTransacoes(long identificador, boolean isCredito) {
		List<Transacao> transacoes = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				Transacao transacao = parseLinhaParaTransacao(linha);
				if (transacao != null) {
					long id = isCredito
							? transacao.getEntidadeCredito().getIdentificador()
							: transacao.getEntidadeDebito().getIdentificador();

					if (id == identificador) {
						transacoes.add(transacao);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return transacoes.toArray(new Transacao[0]);
	}

	// Método auxiliar para converter uma linha de texto em uma transação
	private Transacao parseLinhaParaTransacao(String linha) {
		try {
			String[] campos = linha.split(";");

			EntidadeOperadora entidadeCredito = new EntidadeOperadora(
					Long.parseLong(campos[0]), campos[1], Double.parseDouble(campos[2])
			);
			entidadeCredito.creditarSaldoAcao(Double.parseDouble(campos[3]));
			entidadeCredito.creditarSaldoTituloDivida(Double.parseDouble(campos[4]));

			EntidadeOperadora entidadeDebito = new EntidadeOperadora(
					Long.parseLong(campos[5]), campos[6], Double.parseDouble(campos[7])
			);
			entidadeDebito.creditarSaldoAcao(Double.parseDouble(campos[8]));
			entidadeDebito.creditarSaldoTituloDivida(Double.parseDouble(campos[9]));

			Acao acao = campos[10].equals("null") ? null : new Acao(
					Integer.parseInt(campos[10]), campos[11],
					LocalDate.parse(campos[12], DATE_FORMATTER),
					Double.parseDouble(campos[13])
			);

			TituloDivida tituloDivida = campos[14].equals("null") ? null : new TituloDivida(
					Integer.parseInt(campos[14]), campos[15],
					LocalDate.parse(campos[16], DATE_FORMATTER),
					Double.parseDouble(campos[17])
			);

			double valorOperacao = Double.parseDouble(campos[18]);
			LocalDateTime dataHoraOperacao = LocalDateTime.parse(campos[19], DATE_TIME_FORMATTER);

			return new Transacao(entidadeCredito, entidadeDebito, acao, tituloDivida, valorOperacao, dataHoraOperacao);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
