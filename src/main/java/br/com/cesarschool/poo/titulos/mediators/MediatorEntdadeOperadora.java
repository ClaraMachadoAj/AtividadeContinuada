package br.com.cesarschool.poo.titulos.mediators;
/*
 * Deve ser um singleton.
 *
 * Deve ter um atributo repositorioEntidadeOperadora, do tipo RepositorioEntidadeOperadora, que deve
 * ser inicializado na sua declaração. Este atributo será usado exclusivamente
 * pela classe, não tendo, portanto, métodos set e get.
 *
 * Métodos:
 *
 * pivate String validar(EntidadeOperadora): deve validar os dados do objeto recebido nos seguintes termos:
 * identificador: deve ser maior que zero e menor que 100000 (1)
 * nome: deve ser preenchido, diferente de branco e de null (2). deve ter entre 5 e 60 caracteres (3).
 * data de validade: deve ser maior do que a data atual mais 180 dias (4).
 * valorUnitario: deve ser maior que zero (5).
 * O metodo validar deve retornar null se o objeto estiver válido, e uma mensagem pertinente (ver abaixo)
 * se algum valor de atributo estiver inválido.
 *
 * (1) - Identificador deve estar entre 100 e 1000000.
 * (2) - Nome deve ser preenchido.
 * (3) - Nome deve ter entre 10 e 100 caracteres.
 *
 * public String incluir(EntidadeOperadora entidade): deve chamar o método validar. Se ele
 * retornar null, deve incluir entidade no repositório. Retornos possíveis:
 * (1) null, se o retorno do validar for null e o retorno do incluir do
 * repositório for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Entidade já existente", se o retorno do validar for null
 * e o retorno do repositório for false.
 *
 * public String alterar(EntidadeOperadora entidade): deve chamar o método validar. Se ele
 * retornar null, deve alterar entidade no repositório. Retornos possíveis:
 * (1) null, se o retorno do validar for null e o retorno do alterar do
 * repositório for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Entidade inexistente", se o retorno do validar for null
 * e o retorno do repositório for false.
 *
 * public String excluir(int identificador): deve validar o identificador.
 * Se este for válido, deve chamar o excluir do repositório. Retornos possíveis:
 * (1) null, se o retorno do excluir do repositório for true.
 * (2) A mensagem "Entidade inexistente", se o retorno do repositório for false
 * ou se o identificador for inválido.
 *
 * public EntidadeOperadora buscar(int identificador): deve validar o identificador.
 * Se este for válido, deve chamar o buscar do repositório, retornando o
 * que ele retornar. Se o identificador for inválido, retornar null.
 */
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;
import java.time.LocalDate;

public class MediatorEntdadeOperadora {
    private static MediatorEntdadeOperadora instancia;
    private final RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();

    private MediatorEntdadeOperadora() {
    }

    public static synchronized MediatorEntdadeOperadora getInstancia() {
        if (instancia == null) {
            instancia = new MediatorEntdadeOperadora();
        }
        return instancia;
    }

    private String validar(EntidadeOperadora entidade) {
        if (entidade.getIdentificador() <= 100 || entidade.getIdentificador() >= 1000000) {
            return "Identificador deve estar entre 100 e 1000000.";
        }

        String nome = entidade.getNome();
        if (nome == null || nome.trim().isEmpty()) {
            return "Nome deve ser preenchido.";
        }

        if (nome.length() < 10 || nome.length() > 100) {
            return "Nome deve ter entre 10 e 100 caracteres.";
        }

        /*LocalDate dataAtualMais180 = LocalDate.now().plusDays(180);
        if (entidade.getDataDeValidade().isBefore(dataAtualMais180)) {
            return "Data de validade deve ser superior a 180 dias a partir da data atual.";
        }

        if (entidade.getValorUnitario() <= 0) {
            return "Valor unitário deve ser maior que zero.";
        }*/

        return null;
    }

    public String incluir(EntidadeOperadora entidade) {
        String mensagemValidacao = validar(entidade);

        if (mensagemValidacao != null) {
            return mensagemValidacao; // (2)
        }

        boolean incluida = repositorioEntidadeOperadora.incluir(entidade);

        if (incluida) {
            return null; // (1)
        } else {
            return "Entidade já existente"; // (3)
        }
    }

    public String alterar(EntidadeOperadora entidade) {
        String mensagemValidacao = validar(entidade);

        if (mensagemValidacao != null) {
            return mensagemValidacao; // (2)
        }

        boolean alterada = repositorioEntidadeOperadora.alterar(entidade);

        if (alterada) {
            return null; // (1)
        } else {
            return "Entidade inexistente"; // (3)
        }
    }

    // Metodo para excluir uma EntidadeOperadora por identificador
    public String excluir(int identificador) {
        if (!validarIdentificador(identificador)) {
            return "Entidade inexistente.";
        }

        boolean excluida = repositorioEntidadeOperadora.excluir(identificador);
        return excluida ? null : "Entidade inexistente.";
    }

    // Metodo para buscar uma EntidadeOperadora por identificador
    public EntidadeOperadora buscar(int identificador) {
        if (!validarIdentificador(identificador)) {
            return null;
        }
        return repositorioEntidadeOperadora.buscar(identificador);
    }

    // Metodo para validar o identificador
    private boolean validarIdentificador(long identificador) {
        return identificador >= 100 && identificador <= 1000000;
    }

}