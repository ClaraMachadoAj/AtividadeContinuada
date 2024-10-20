
/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas.
 *
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12 
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

package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEntidadeOperadora {

    private static final String FILE_NAME = "EntidadeOperadora.txt";

    // Metodo para incluir uma nova EntidadeOperadora

    public boolean incluir(EntidadeOperadora entidade) {
        List<EntidadeOperadora> entidades = lerEntidadesDoArquivo();
        // confere se já existe uma EntidadeOperadora com o msm identificador
        for (EntidadeOperadora e : entidades) {
            if (e.getIdentificador() == entidade.getIdentificador()) {
                return false; // Identificador repetido
            }
        }
        // Adiciona a nova entidade
        entidades.add(entidade);
        return salvarEntidadesNoArquivo(entidades);
    }

    // Metodo para alterar uma EntidadeOperadora existente

    public boolean alterar(EntidadeOperadora entidade) {
        List<EntidadeOperadora> entidades = lerEntidadesDoArquivo();
        for (int i = 0; i < entidades.size(); i++) {
            if (entidades.get(i).getIdentificador() == entidade.getIdentificador()) {
                entidades.set(i, entidade);
                return salvarEntidadesNoArquivo(entidades);
            }
        }
        return false; // Entidade não encontrada
    }

    // Metodo para excluir uma EntidadeOperadora por identificador

    public boolean excluir(long identificador) {
        List<EntidadeOperadora> entidades = lerEntidadesDoArquivo();
        for (int i = 0; i < entidades.size(); i++) {
            if (entidades.get(i).getIdentificador() == identificador) {
                entidades.remove(i);
                return salvarEntidadesNoArquivo(entidades);
            }
        }
        return false; // Entidade não encontrada
    }

    // Metodo para buscar uma EntidadeOperadora por identificador

    public EntidadeOperadora buscar(long identificador) {
        List<EntidadeOperadora> entidades = lerEntidadesDoArquivo();
        for (EntidadeOperadora entidade : entidades) {
            if (entidade.getIdentificador() == identificador) {
                return entidade;
            }
        }
        return null; // Não encontrada
    }

    // Metodo auxiliar para ler todas as EntidadesOperadoras do arquivo

    private List<EntidadeOperadora> lerEntidadesDoArquivo() {
        List<EntidadeOperadora> entidades = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                long identificador = Long.parseLong(campos[0]);
                String nome = campos[1];
                double autorizadoAcao = Double.parseDouble(campos[2]);
                double saldoAcao = Double.parseDouble(campos[3]);
                double saldoTituloDivida = Double.parseDouble(campos[4]);
                EntidadeOperadora entidade = new EntidadeOperadora(identificador, nome, autorizadoAcao);
                // Se necessário, definir os saldos aqui
                entidades.add(entidade);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Tratar a exceção apropriadamente
        }
        return entidades;
    }

    // Metodo auxiliar para salvar as EntidadesOperadoras no arquivo

    private boolean salvarEntidadesNoArquivo(List<EntidadeOperadora> entidades) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (EntidadeOperadora entidade : entidades) {
                String linha = entidade.getIdentificador() + ";" + entidade.getNome() + ";" +
                        entidade.getAutorizadoAcao() + ";" +
                        entidade.getSaldoAcao() + ";" +
                        entidade.getSaldoTituloDivida();
                writer.write(linha);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace(); // ????????w
            return false;
        }
    }
}
