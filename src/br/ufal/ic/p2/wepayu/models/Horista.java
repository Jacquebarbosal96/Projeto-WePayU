package br.ufal.ic.p2.wepayu.models;

import java.util.HashMap;
import java.util.Map;

public class Horista extends Empregado {
    
    private double salarioHora;
    private Map<String, Double> cartoesDePonto = new HashMap<>();

    public Horista() {}

    public Horista(String nome, String endereco, String id, String metodoPagamento, double salarioHora) {
        super(nome, endereco, id, metodoPagamento);
        this.setSalarioHora(salarioHora);
    }
    
    public double getSalarioHora() {
        return salarioHora;
    }

    public void setSalarioHora(double salarioHora) {
        this.salarioHora = salarioHora;
    }

    public void setCartoesDePonto(Map<String, Double> cartoesDePonto) {
        this.cartoesDePonto = cartoesDePonto;
    }

    public Map<String, Double> getCartoesDePonto() {
        return cartoesDePonto;
    }
    
    public void lancaCartao(String data, double horasTrabalhadas) {
        this.cartoesDePonto.put(data, horasTrabalhadas);
    }
}