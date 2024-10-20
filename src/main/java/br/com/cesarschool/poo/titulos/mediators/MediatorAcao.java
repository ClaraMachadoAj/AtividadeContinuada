package br.com.cesarschool.poo.titulos.mediators;
/*
 * Deve ser um singleton.
 *
 * Deve ter um atributo repositorioAcao, do tipo RepositorioAcao, que deve
 * ser inicializado na sua declaração. Este atributo será usado exclusivamente
 * pela classe, não tendo, portanto, métodos set e get.
 *
 * Métodos:
 *
 * private String validar(Acao acao): deve validar os dados do objeto recebido nos seguintes termos:
 * identificador: deve ser maior que zero e menor que 100000 (1)
 * nome: deve ser preenchido, diferente de branco e de null (2). deve ter entre 10 e 100 caracteres (3).
 * data de validade: deve ser maior do que a data atual mais 30 dias (4).
 * valorUnitario: deve ser maior que zero (5).
 * O metodo validar deve retornar null se o objeto estiver válido, e uma mensagem pertinente (ver abaixo)
 * se algum valor de atributo estiver inválido.
 *
 * (1) - Identificador deve estar entre 1 e 99999.
 * (2) - Nome deve ser preenchido.
 * (3) - Nome deve ter entre 10 e 100 caracteres.
 * (4) - Data de validade deve ter pelo menos 30 dias na frente da data atual.
 * (5) - Valor unitário deve ser maior que zero.
 *
 * public String incluir(Acao acao): deve chamar o metodo validar. Se ele
 * retornar null, deve incluir acao no repositório. Retornos possíveis:
 * (1) null, se o retorno do validar for null e o retorno do incluir do
 * repositório for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Ação já existente", se o retorno do validar for null
 * e o retorno do repositório for false.
 *
 * public String alterar(Acao acao): deve chamar o metodo validar. Se ele
 * retornar null, deve alterar acao no repositório. Retornos possíveis:
 * (1) null, se o retorno do validar for null e o retorno do alterar do
 * repositório for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Ação inexistente", se o retorno do validar for null
 * e o retorno do repositório for false.
 *
 * public String excluir(int identificador): deve validar o identificador.
 * Se este for válido, deve chamar o excluir do repositório. Retornos possíveis:
 * (1) null, se o retorno do excluir do repositório for true.
 * (2) A mensagem "Ação inexistente", se o retorno do repositório for false
 * ou se o identificador for inválido.
 *
 * public Acao buscar(int identificador): deve validar o identificador.
 * Se este for válido, deve chamar o buscar do repositório, retornando o
 * que ele retornar. Se o identificador for inválido, retornar null.
 */

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioAcao;
import java.time.LocalDate;

public class MediatorAcao {
    private static MediatorAcao instancia;
    private final RepositorioAcao repositorioAcao = new RepositorioAcao();

    private MediatorAcao() { }

    public static synchronized MediatorAcao getInstancia() {
        if (instancia == null) {
            instancia = new MediatorAcao();
        }
        return instancia;
    }

    private String validar(Acao acao) {
        if (acao.getIdentificador() <= 0 || acao.getIdentificador() >= 10000) {
            return "Identificador deve estar entre 1 e 99999";
        } else {
            return null;
        }

        String nome;
        nome = acao.getNome();
        if (nome == null || nome.trim().isEmpty()) {
            return "Nome deve ser preenchido";
        }
        if (nome.length() < 10 || nome.length() > 100) {
            return "O nome deve ter entre 10 e 100 caracteres.";
        }

        LocalDate dataAtualMais30 = LocalDate.now().plusDays(30);
        if (acao.getDataValidade().isBefore(dataAtualMais30)) {
            return "Data de validade deve ter pelo menos 30 dias na frente da data atual";
        }

        if (acao.getValorUnitario() <= 0) {
            return "Valor unitário deve ser maior que zero";
        }

        return null;
    }

    public String incluir(Acao acao) {
        String MensagemValidacao = validar(acao);

        if (MensagemValidacao != null) {
            return MensagemValidacao;
        }

        boolean incluida = repositorioAcao.incluir(acao);

        if (incluida) {
            return null;
        } else {
            return "Ação já existente";
        }
    }

    public class Validador {
        private final RepositorioAcao repositorioAcao = new RepositorioAcao();

        private String validar(Acao acao) {
            if (acao.getIdentificador() <= 0 || acao.getIdentificador() >= 100000) {
                return "O identificador deve ser maior que 0 e menor que 100000.";
            }

            String nome = acao.getNome();
            if (nome == null || nome.trim().isEmpty()) {
                return "O nome não pode ser nulo ou vazio.";
            }

            if (nome.length() < 10 || nome.length() > 100) {
                return "O nome deve ter entre 10 e 100 caracteres.";
            }

            if (acao.getDataValidade().isBefore(LocalDate.now().plusDays(30))) {
                return "A data de validade deve ser superior a 30 dias a partir da data atual.";
            }

            if (acao.getValorUnitario() <= 0) {
                return "O valor unitário deve ser maior que zero.";
            }

            return null;
        }

        public String alterar(Acao acao) {
            String mensagemValidacao = validar(acao);

            if (mensagemValidacao != null) {
                return mensagemValidacao;
            }

            boolean alterada = repositorioAcao.alterar(acao);

            if (alterada) {
                return null;
            } else {
                return "Ação inexistente";
            }
        }
    }

    public String excluir(int Identificador) {
        if (!validarIdentificador(Identificador)) {
            return "Ação inexistente";
        }

        boolean excluida = repositorioAcao.excluir(Identificador);

        if (excluida) {
            return null;
        }

        return "Ação inexistente";
    }

    public Acao buscar(int identificador) {
        if (!validarIdentificador(identificador)) {
            return null;
        }

        return repositorioAcao.buscar(identificador);
    }

    private boolean validarIdentificador(int identificador) {
        return identificador > 0 && identificador < 100000;
    }
}