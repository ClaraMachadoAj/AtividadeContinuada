package br.gov.cesarschool.poo.testes;

import java.io.File;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioAcao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioGeral;
import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;

public class TesteRepositorioAcao extends TesteGeral {
	private static final RepositorioAcao DAO = new RepositorioAcao();
	private static final String NOME_DIR_ACAO = PONTO + SEP_ARQUIVO + Acao.class.getSimpleName();
	
	@Test
	public void testDAO00() {
		Assertions.assertTrue(DAO instanceof RepositorioGeral);
		DAOSerializadorObjetos dao = DAO.getDao();
		Assertions.assertNotNull(dao);
	}

	@Test
	public void testDAO01() {
		excluirArquivosDiretorio(NOME_DIR_ACAO);
		Acao acao = new Acao(1, "A1", LocalDate.now(), 100.0);
		Assertions.assertTrue(DAO.incluir(acao));		
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR_ACAO), 1);

		String caminhoEsperado = obterNomeArquivo(NOME_DIR_ACAO, acao);
		System.out.println("Caminho esperado pelo teste: " + caminhoEsperado);

		Assertions.assertTrue(new File(obterNomeArquivo(NOME_DIR_ACAO, acao)).exists());
		Acao acao1 = DAO.buscar(acao.getIdentificador());
		Assertions.assertNotNull(acao1);
		Assertions.assertNotNull(acao1.getDataHoraInclusao());
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(acao, acao1));								
	}
	@Test
	public void testDAO02() {
		excluirArquivosDiretorio(NOME_DIR_ACAO);
		Acao acao = new Acao(2, "A2", LocalDate.now(), 101.0);
		Assertions.assertTrue(DAO.incluir(acao));
		Assertions.assertFalse(DAO.incluir(acao));
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR_ACAO), 1); 
	}
	@Test
	public void testDAO05() {
		excluirArquivosDiretorio(NOME_DIR_ACAO);
		int id = 6;
		Acao acao = new Acao(id, "A6", LocalDate.now(), 106.0);		
		Assertions.assertTrue(DAO.incluir(acao));
		Assertions.assertTrue(DAO.excluir(id));
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR_ACAO), 0);
		Acao acaoBusc = DAO.buscar(id);
		Assertions.assertNull(acaoBusc);
	}
	@Test
	public void testDAO06() {
		excluirArquivosDiretorio(NOME_DIR_ACAO);		
		Acao acao = new Acao(7, "A7", LocalDate.now(), 107.0);		
		Assertions.assertTrue(DAO.incluir(acao));
		Assertions.assertFalse(DAO.excluir(8));
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR_ACAO), 1);
	}
}
