package br.com.cesarschool.poo.titulos.mediators;

/*
 * Deve ser um singleton.
 *
 * Deve ter um atributo repositorioTituloDivida, do tipo RepositorioTituloDivida, que deve
 * ser inicializado na sua declaração. Este atributo será usado exclusivamente
 * pela classe, não tendo, portanto, métodos set e get.
 *
 * Métodos:
 *
 * private String validar(TituloDivida titulo): deve validar os dados do objeto recebido nos seguintes termos:
 * identificador: deve ser maior que zero e menor que 100000 (1)
 * nome: deve ser preenchido, diferente de branco e de null (2). deve ter entre 10 e 100 caracteres (3).
 * data de validade: deve ser maior do que a data atual mais 180 dias (4).
 * valorUnitario: deve ser maior que zero (5).
 * O metodo validar deve retornar null se o objeto estiver válido, e uma mensagem pertinente (ver abaixo)
 * se algum valor de atributo estiver inválido.
 *
 * (1) - Identificador deve estar entre 1 e 99999.
 * (2) - Nome deve ser preenchido.
 * (3) - Nome deve ter entre 10 e 100 caracteres.
 * (4) - Data de validade deve ter pelo menos 180 dias na frente da data atual.
 * (5) - Taxa de juros deve ser maior ou igual a zero.
 *
 * public String incluir(TituloDivida titulo): deve chamar o metodo validar. Se ele
 * retornar null, deve incluir titulo no repositório. Retornos possíveis:
 * (1) null, se o retorno do validar for null e o retorno do incluir do
 * repositório for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Título já existente", se o retorno do validar for null
 * e o retorno do repositório for false.
 *
 * public String alterar(TituloDivida titulo): deve chamar o método validar. Se ele
 * retornar null, deve alterar titulo no repositório. Retornos possíveis:
 * (1) null, se o retorno do validar for null e o retorno do alterar do
 * repositório for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Título inexistente", se o retorno do validar for null
 * e o retorno do repositório for false.
 *
 * public String excluir(int identificador): deve validar o identificador.
 * Se este for válido, deve chamar o excluir do repositório. Retornos possíveis:
 * (1) null, se o retorno do excluir do repositório for true.
 * (2) A mensagem "Título inexistente", se o retorno do repositório for false
 * ou se o identificador for inválido.
 *
 * public TituloDivida buscar(int identificador): deve validar o identificador.
 * Se este for válido, deve chamar o buscar do repositório, retornando o
 * que ele retornar. Se o identificador for inválido, retornar null.
 */


import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTituloDivida;
import java.time.LocalDate;

public class MediatorTituloDivida {

    // Construtor privado para impedir criação de instâncias fora da classe
    private MediatorTituloDivida() {}

    // Classe interna estática que armazena a instância única (Lazy Holder Singleton)
    private static class SingletonHolder {
        // Instância única de MediatorTituloDivida
        private static final MediatorTituloDivida instance = new MediatorTituloDivida();
    }

    // Metodo que retorna a instância única de MediatorTituloDivida
    public static MediatorTituloDivida getInstance() {
        return SingletonHolder.instance;
    }

    private final RepositorioTituloDivida repositorioTituloDivida = getInstance().repositorioTituloDivida;

    // Metodo privado para validar o TituloDivida
    private String validar(TituloDivida titulo){
        //(1)
        if (titulo.getIdentificador() <= 0 || titulo.getIdentificador() >= 100000) {
            return "Identificador deve estar entre 1 e 99999.";
        }
        //(2)
        if (titulo.getNome() == null || titulo.getNome().isBlank()) {
            return "Nome deve ser preenchido.";
        }
        //(3)
        if (titulo.getNome().length() < 10 || titulo.getNome().length() > 100) {
            return "Nome deve ter entre 10 e 100 caracteres.";
        }
        //(4)
        if (titulo.getDataDeValidade().isBefore(LocalDate.now().plusDays(180))) {
            return "Data de validade deve ter pelo menos 180 dias na frente da data atual.";
        }
        //(5)
        if (titulo.getTaxaJuros() < 0) {
            return "Taxa de juros deve ser maior ou igual a zero.";
        }

        return null; // Objeto válido
    }

    // Metodo para incluir um TituloDivida
    public String incluir(TituloDivida titulo){
        String validacao = validar(titulo);
        if (validacao != null){
            return validacao;
        }

        boolean sucesso = repositorioTituloDivida.incluir(titulo);
        return sucesso ? null : "Título já existente!";
    }

    // Metodo para alterar um TituloDivida
    public String alterar(TituloDivida titulo) {
        String validacao = validar(titulo);
        if (validacao != null) {
            return validacao;
        }
        boolean sucesso = repositorioTituloDivida.alterar(titulo);
        return sucesso ? null : "Título inexistente!";
    }

    // metodo para excluir um TituloDivida pelo identificador
    public String excluir(int identificador) {
        if (identificador <= 0 || identificador >= 100000) {
            return "Identificador deve estar entre 1 e 99999.";
        }
        boolean sucesso = repositorioTituloDivida.excluir(identificador);
        return sucesso ? null : "Título inexistente!";
    }

    // Metodo para buscar um TituloDivida pelo identificador
    public TituloDivida buscar(int identificador) {
        if (identificador <= 0 || identificador >= 100000) {
            return null;
        }
        return repositorioTituloDivida.buscar(identificador);
    }


}