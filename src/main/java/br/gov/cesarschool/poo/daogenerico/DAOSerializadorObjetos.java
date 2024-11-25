package br.gov.cesarschool.poo.daogenerico;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DAOSerializadorObjetos {

	private final String nomeDiretorio;

	public DAOSerializadorObjetos(Class<?> tipoEntidade) {
		this.nomeDiretorio = "./" + tipoEntidade.getSimpleName(); // Define o diretório
		File diretorio = new File(nomeDiretorio);
		if (!diretorio.exists()) {
			diretorio.mkdirs(); // Cria o diretório caso não exista
		}
	}

	public boolean incluir(Entidade entidade) {
		File arquivo = new File(nomeDiretorio, entidade.getIdUnico());
		System.out.println("Tentando incluir arquivo no método incluir: " + arquivo.getAbsolutePath());

		if (arquivo.exists()) {
			System.out.println("Arquivo já existe: " + arquivo.getName());
			return false; // Já existe uma entidade com o mesmo identificador
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
			entidade.setDataHoraInclusao(java.time.LocalDateTime.now());
			oos.writeObject(entidade);
			System.out.println("Arquivo salvo com sucesso: " + arquivo.getAbsolutePath());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean alterar(Entidade entidade) {
		File arquivo = new File(nomeDiretorio, entidade.getIdUnico());
		if (!arquivo.exists()) {
			System.out.println("Arquivo não encontrado para alterar: " + arquivo.getAbsolutePath());
			return false;
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
			entidade.setDataHoraUltimaAlteracao(java.time.LocalDateTime.now());
			oos.writeObject(entidade);
			System.out.println("Arquivo alterado com sucesso: " + arquivo.getAbsolutePath());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluir(String idUnico) {
		File arquivo = new File(nomeDiretorio, idUnico);

		if (arquivo.exists() && arquivo.delete()) {
			System.out.println("Arquivo excluído com sucesso: " + arquivo.getAbsolutePath());
			return true;
		} else {
			System.out.println("Erro ao excluir arquivo: " + arquivo.getAbsolutePath());
			return false;
		}
	}

	public Entidade buscar(String idUnico) {
		File arquivo = new File(nomeDiretorio, idUnico);
		System.out.println("Tentando buscar arquivo: " + arquivo.getAbsolutePath());

		if (!arquivo.exists()) {
			System.out.println("Arquivo não encontrado: " + arquivo.getAbsolutePath());
			return null;
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
			Entidade entidade = (Entidade) ois.readObject();
			System.out.println("Entidade deserializada com sucesso: " + entidade);
			return entidade;
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Erro ao deserializar o arquivo: " + arquivo.getAbsolutePath());
			e.printStackTrace();
			return null;
		}
	}

	public Entidade[] buscarTodos() {
		List<Entidade> entidades = new ArrayList<>();
		File diretorio = new File(nomeDiretorio);

		// Filtrar todos os arquivos no diretório
		File[] arquivos = diretorio.listFiles();
		if (arquivos != null) {
			for (File arquivo : arquivos) {
				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
					entidades.add((Entidade) ois.readObject());
				} catch (IOException | ClassNotFoundException e) {
					System.err.println("Erro ao deserializar arquivo: " + arquivo.getAbsolutePath());
					e.printStackTrace();
				}
			}
		}
		System.out.println("Total de entidades carregadas: " + entidades.size());
		return entidades.toArray(new Entidade[0]);
	}
}
