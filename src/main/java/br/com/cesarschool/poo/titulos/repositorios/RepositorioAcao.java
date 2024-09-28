/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, valorUnitario)
 * 
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12 
 * 
 * A inclusão deve adicionar uma nova linha ao arquivo. Não é permitido incluir 
 * identificador repetido. Neste caso, o método deve retornar false. Inclusão com 
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
package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAcao {

	private static final String FILE_NAME = "Acao.txt";
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	// Metodo para incluir uma nova ação
	public boolean incluir(Acao acao) {
		List<Acao> acoes = lerAcoesDoArquivo();
		// Verifica se já existe uma ação com o mesmo identificador
		for (Acao a : acoes) {
			if (a.getIdentificador() == acao.getIdentificador()) {
				return false;
			}
		}
		// Adiciona a nova ação
		acoes.add(acao);
		return salvarAcoesNoArquivo(acoes);
	}

	// Metodo para alterar uma ação existente
	public boolean alterar(Acao acao) {
		List<Acao> acoes = lerAcoesDoArquivo();
		for (int i = 0; i < acoes.size(); i++) {
			if (acoes.get(i).getIdentificador() == acao.getIdentificador()) {
				acoes.set(i, acao);
				return salvarAcoesNoArquivo(acoes);
			}
		}
		return false;
	}

	// Metodo para excluir uma ação por identificador
	public boolean excluir(int identificador) {
		List<Acao> acoes = lerAcoesDoArquivo();
		for (int i = 0; i < acoes.size(); i++) {
			if (acoes.get(i).getIdentificador() == identificador) {
				acoes.remove(i);
				return salvarAcoesNoArquivo(acoes);
			}
		}
		return false;
	}

	// Metodo para buscar uma ação por identificador
	public Acao buscar(int identificador) {
		List<Acao> acoes = lerAcoesDoArquivo();
		for (Acao acao : acoes) {
			if (acao.getIdentificador() == identificador) {
				return acao;
			}
		}
		return null;
	}

	// Metodo auxiliar para ler todas as ações do arquivo
	private List<Acao> lerAcoesDoArquivo() {
		List<Acao> acoes = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				String[] campos = linha.split(";");
				int identificador = Integer.parseInt(campos[0]);
				String nome = campos[1];
				LocalDate dataDeValidade = LocalDate.parse(campos[2], DATE_FORMAT);
				double valorUnitario = Double.parseDouble(campos[3]);
				acoes.add(new Acao(identificador, nome, dataDeValidade, valorUnitario));
			}
		} catch (IOException e) {
			e.printStackTrace(); // Tratar a exceção apropriadamente
		}
		return acoes;
	}

	// Metodo auxiliar para salvar as ações no arquivo
	private boolean salvarAcoesNoArquivo(List<Acao> acoes) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
			for (Acao acao : acoes) {
				String linha = acao.getIdentificador() + ";" + acao.getNome() + ";" +
						acao.getDataDeValidade().format(DATE_FORMAT) + ";" +
						acao.getValorUnitario();
				writer.write(linha);
				writer.newLine();
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace(); // Tratar a exceção apropriadamente
			return false;
		}
	}
}