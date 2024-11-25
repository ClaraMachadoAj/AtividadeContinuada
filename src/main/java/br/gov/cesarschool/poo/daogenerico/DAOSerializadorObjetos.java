package br.gov.cesarschool.poo.daogenerico;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DAOSerializadorObjetos {

	private final String nomeDiretorio;

	public DAOSerializadorObjetos(Class<?> tipoEntidade) {
		this.nomeDiretorio = "./" + tipoEntidade.getSimpleName(); // Ajuste no formato do caminho
		File diretorio = new File(nomeDiretorio);
		if (!diretorio.exists()) {
			diretorio.mkdirs(); // Cria o diretório caso não exista
		}
	}

	public boolean incluir(Entidade entidade) {
		File arquivo = new File(nomeDiretorio, entidade.getIdUnico() + ".ser");
		System.out.println("Tentando incluir arquivo no método incluir: " + arquivo.getAbsolutePath());
		System.out.println("Diretório configurado no DAO: " + nomeDiretorio);

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
		File arquivo = new File(nomeDiretorio, entidade.getIdUnico() + ".ser");
		if (!arquivo.exists()) {
			return false; // Não existe uma entidade com este identificador
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
			entidade.setDataHoraUltimaAlteracao(java.time.LocalDateTime.now());
			oos.writeObject(entidade);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluir(String idUnico) {
		// Cria o caminho do arquivo com a extensão .ser
		File arquivo = new File(nomeDiretorio, idUnico + ".ser");

		// Verifica se existe e tenta excluir
		if (arquivo.exists() && arquivo.delete()) {
			System.out.println("Arquivo excluído com sucesso: " + arquivo.getAbsolutePath());
			return true;
		} else {
			System.out.println("Erro ao excluir arquivo: " + arquivo.getAbsolutePath());
			return false;
		}
	}


	public Entidade buscar(String idUnico) {
		File arquivo = new File(nomeDiretorio, idUnico + ".ser");
		System.out.println("Tentando buscar arquivo: " + arquivo.getAbsolutePath());

		if (!arquivo.exists()) {
			System.out.println("Arquivo não encontrado: " + arquivo.getName());
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
		File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith(".ser")); // Filtra arquivos .ser
		if (arquivos != null) {
			for (File arquivo : arquivos) {
				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
					entidades.add((Entidade) ois.readObject());
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return entidades.toArray(new Entidade[0]);
	}
}
