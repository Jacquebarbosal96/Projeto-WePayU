package br.ufal.ic.p2.wepayu.models;

public class Assalariado extends Empregado {
    private double salario;

    public Assalariado() {}

    public Assalariado(String nome, String endereco, String id, String metodoPagamento, double salario) {
        super(nome, endereco, id, metodoPagamento);
        this.salario = salario;
    }
    
    public double getSalario() {
        return salario;
    }
    
    public void setSalario(double salario) {
        this.salario = salario;
    }
}