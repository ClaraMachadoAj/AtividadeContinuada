package org.cesarschool.telas;

import java.util.HashMap;
import java.util.Map;

public class EntidadeMediator {

    // Simulando um "banco de dados" com um mapa
    private Map<String, Entidade> repositorio = new HashMap<>();

    public String incluir(Entidade ent) {
        String msg = validar(ent);
        if (msg == null) {
            if (repositorio.containsKey(ent.getCodigo())) {
                return "Entidade já existente!";
            }
            repositorio.put(ent.getCodigo(), ent);
            return ent.getCodigo();  // Retorna o código da nova entidade
        }
        return msg;
    }

    public String alterar(Entidade ent) {
        String msg = validar(ent);
        if (msg == null) {
            if (!repositorio.containsKey(ent.getCodigo())) {
                return "Entidade inexistente!";
            }
            repositorio.put(ent.getCodigo(), ent);
            return null;
        }
        return msg;
    }

    private String validar(Entidade ent) {
        if (ent.getNome() == null || ent.getNome().trim().isEmpty()) {
            return "Nome deve ser preenchido!";
        }
        return null;
    }

    public Entidade buscar(String codigo) {
        return repositorio.get(codigo);
    }
}
