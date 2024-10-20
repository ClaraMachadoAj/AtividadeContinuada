
/*
 * Deve gravar em e ler de um arquivo texto chamado Transacao.txt os dados dos objetos do tipo
 * Transacao. Seguem abaixo exemplos de linhas 
 * De entidadeCredito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De entidadeDebito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De acao: identificador, nome, dataValidade, valorUnitario OU null
 * De tituloDivida: identificador, nome, dataValidade, taxaJuros OU null. 
 * valorOperacao, dataHoraOperacao
 * 
 *   002192;BCB;true;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;1;PETROBRAS;2024-12-12;30.33;null;100000.0;2024-01-01 12:22:21 
 *   002192;BCB;false;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;null;3;FRANCA;2027-11-11;2.5;100000.0;2024-01-01 12:22:21
 *
 * A inclusão deve adicionar uma nova linha ao arquivo. 
 * 
 * A busca deve retornar um array de transações cuja entidadeCredito tenha identificador igual ao
 * recebido como parâmetro.  
 */

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

public class RepositorioTransacao {
	private static final String FILE_NAME = "Transacao.txt";
	private static final DateTimeFormatter DATE_TIME_FORMATTER =
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern("yyyy-MM-dd");

	// Metodo para incluir uma nova transação no arquivo
	public void incluir(Transacao transacao) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
			String linha = formatarTransacao(transacao);
			writer.write(linha);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Metodo para buscar transações pelo identificador da entidade credora
	public Transacao[] buscarPorEntidadeCredora(long identificadorEntidadeCredito) {
		List<Transacao> transacoes = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				Transacao transacao = parseLinhaParaTransacao(linha);
				if (transacao != null &&
						transacao.getEntidadeCredito().getIdentificador() == identificadorEntidadeCredito) {
					transacoes.add(transacao);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return transacoes.toArray(new Transacao[0]);
	}

	// Metodo auxiliar para formatar uma transação em uma linha de texto
	private String formatarTransacao(Transacao transacao) {
		String acaoFormatada = transacao.getAcao() != null ? formatarAcao(transacao.getAcao()) : "null";
		String tituloDividaFormatado = transacao.getTituloDivida() != null ? formatarTituloDivida(transacao.getTituloDivida()) : "null";

		return String.format("%d;%s;%.2f;%.2f;%.2f;%d;%s;%.2f;%.2f;%.2f;%s;%s;%.2f;%s",
				transacao.getEntidadeCredito().getIdentificador(),
				transacao.getEntidadeCredito().getNome(),
				transacao.getEntidadeCredito().getAutorizadoAcao(),
				transacao.getEntidadeCredito().getSaldoAcao(),
				transacao.getEntidadeCredito().getSaldoTituloDivida(),

				transacao.getEntidadeDebito().getIdentificador(),
				transacao.getEntidadeDebito().getNome(),
				transacao.getEntidadeDebito().getAutorizadoAcao(),
				transacao.getEntidadeDebito().getSaldoAcao(),
				transacao.getEntidadeDebito().getSaldoTituloDivida(),

				acaoFormatada,
				tituloDividaFormatado,
				transacao.getValorOperacao(),
				transacao.getDataHoraOperacao().format(DATE_TIME_FORMATTER));
	}

	// Metodo auxiliar para converter uma linha de texto em uma transação
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

	// Metodo para formatar uma ação em uma string
	private String formatarAcao(Acao acao) {
		return String.format("%d;%s;%s;%.2f",
				acao.getIdentificador(),
				acao.getNome(),
				acao.getDataValidade().format(DATE_FORMATTER),
				acao.getValorUnitario());
	}

	// Metodo para formatar um título de dívida em uma string
	private String formatarTituloDivida(TituloDivida tituloDivida) {
		return String.format("%d;%s;%s;%.2f",
				tituloDivida.getIdentificador(),
				tituloDivida.getNome(),
				tituloDivida.getDataValidade().format(DATE_FORMATTER),
				tituloDivida.getTaxaJuros());
	}
}
