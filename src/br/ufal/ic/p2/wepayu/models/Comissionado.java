package br.ufal.ic.p2.wepayu.models;

import java.util.LinkedHashMap;
import java.util.Map;

public class Comissionado extends Assalariado {
    private double comissao;

    private Map<String, Double> vendas = new LinkedHashMap<>();

    public Comissionado() {}

    public Comissionado(String nome, String endereco, String id, String metodoPagamento, double salario, double comissao) {
        super(nome, endereco, id, metodoPagamento, salario);
        this.comissao = comissao;
    }

    public double getComissao() {
        return this.comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public void lancaVenda(String data, double valor) {
        vendas.put(data, valor); 
    }

    public Map<String, Double> getVendas() {
        return this.vendas;
    }

    public void setVendas(Map<String, Double> vendas) {
        this.vendas = vendas;
    }
}
