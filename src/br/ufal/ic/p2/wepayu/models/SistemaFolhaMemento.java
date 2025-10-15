package br.ufal.ic.p2.wepayu.models;

import java.util.HashMap;
import java.util.Map;

public class SistemaFolhaMemento {
    private Map<String, String> agendasDePagamento;
    private final Map<String, Empregado> empregados;
    private final int proximoId;

public SistemaFolhaMemento(Map<String, Empregado> empregados, int proximoId, Map<String, String> agendasDePagamento) {
    this.empregados = new HashMap<>();
    for (Map.Entry<String, Empregado> entry : empregados.entrySet()) {
        this.empregados.put(entry.getKey(), (Empregado) entry.getValue().clone());
    }
    this.proximoId = proximoId;
    this.agendasDePagamento = new HashMap<>(agendasDePagamento);
}

    public Map<String, Empregado> getEmpregados() {
        return this.empregados;
    }

    public int getProximoId() {
        return this.proximoId;
    }

    public Map<String, String> getAgendasDePagamento() {
    return agendasDePagamento;
}
}