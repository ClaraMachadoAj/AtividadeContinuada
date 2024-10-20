package br.com.cesarschool.poo.titulos.repositorios;

/*
 * Deve gravar em e ler de um arquivo texto chamado TituloDivida.txt os dados dos objetos do tipo
 * TituloDivida. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, taxaJuros).
 *
    1;BRASIL;2024-12-12;10.5
    2;EUA;2026-01-01;1.5
    3;FRANCA;2027-11-11;2.5
 *
 * A inclusão deve adicionar uma nova linha ao arquivo. Não é permitido incluir
 * identificador repetido. Neste caso, o metodo deve retornar false. Inclusão com
 * sucesso, retorno true.
 *
 * A alteração deve substituir a linha atual por uma nova linha. A linha deve ser
 * localizada por identificador que, quando não encontrado, enseja retorno false.
 * Alteração com sucesso, retorno true.
 *
 * A exclusão deve apagar a linha atual do arquivo. A linha deve ser
 * localizada por identificador que, quando não encontrado, enseja retorno false.
 * Exclusão com sucesso, retorno true.
 *
 * A busca deve localizar uma linha por identificador, materializar e retornar um
 * objeto. Caso o identificador não seja encontrado no arquivo, retornar null.
 */



import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTituloDivida {
	private static final String FILE_NAME = "TituloDivida.txt";

	// Metodo para incluir um novo título no arquivo
	public boolean incluir(TituloDivida tituloDivida) {
		if (buscar(tituloDivida.getIdentificador()) != null) {
			return false; // Identificador já existe
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
			String linha = formatarLinha(tituloDivida);
			writer.write(linha);
			writer.newLine();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Metodo para alterar um título existente no arquivo
	public boolean alterar(TituloDivida tituloDivida) {
		List<String> linhas = lerTodasLinhas();
		boolean alterado = false;

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
			for (String linha : linhas) {
				if (linha.startsWith(tituloDivida.getIdentificador() + ";")) {
					linha = formatarLinha(tituloDivida);
					alterado = true;
				}
				writer.write(linha);
				writer.newLine();
			}
			return alterado;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Metodo para excluir um título existente no arquivo
	public boolean excluir(int identificador) {
		List<String> linhas = lerTodasLinhas();
		boolean excluido = false;

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
			for (String linha : linhas) {
				if (linha.startsWith(identificador + ";")) {
					excluido = true;
				} else {
					writer.write(linha);
					writer.newLine();
				}
			}
			return excluido;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Metodo para buscar um título pelo identificador
	public TituloDivida buscar(int identificador) {
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				if (linha.startsWith(identificador + ";")) {
					return parseLinhaParaTitulo(linha);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Metodo auxiliar para formatar um objeto TituloDivida para uma linha de texto
	private String formatarLinha(TituloDivida tituloDivida) {
		return String.format("%d;%s;%s;%.2f",
				tituloDivida.getIdentificador(),
				tituloDivida.getNome(),
				tituloDivida.getDataDeValidade(),
				tituloDivida.getTaxaJuros());
	}


	// Metodo auxiliar para converter uma linha de texto em um objeto TituloDivida
	private TituloDivida parseLinhaParaTitulo(String linha) {
		String[] campos = linha.split(";");
		int identificador = Integer.parseInt(campos[0]);
		String nome = campos[1];
		LocalDate dataValidade = LocalDate.parse(campos[2]);
		double taxaJuros = Double.parseDouble(campos[3]);
		return new TituloDivida(identificador, nome, dataValidade, taxaJuros);
	}

	// Metodo auxiliar para ler todas as linhas do arquivo
	private List<String> lerTodasLinhas() {
		List<String> linhas = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				linhas.add(linha);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linhas;
	}
}
