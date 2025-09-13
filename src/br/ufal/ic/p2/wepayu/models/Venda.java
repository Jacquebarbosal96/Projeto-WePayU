package br.ufal.ic.p2.wepayu.models;

public class Venda {
    private String data;
    private double valor;
    private String item;
    private String empregadoId;

    public Venda() {}
    
    public Venda(String data, double valor, String item, String empregadoId) {
        this.setData(data);
        this.setValor(valor);
        this.setItem(item);
        this.setEmpregadoId(empregadoId);
    }
    
    public String showSale() {
        return "Produto: " + this.getItem() +
            "\nValor: " + this.getValor() +
            "\n Data: " + this.getData();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getEmpregadoId() {
        return empregadoId;
    }

    public void setEmpregadoId(String empregadoId) {
        this.empregadoId = empregadoId;
    }
}