package br.ufal.ic.p2.wepayu.models;

public class CartaoDePonto {
    private double horasTrabalhadas;
    private String data;

   public CartaoDePonto() {}
   
    public CartaoDePonto(String data, double horasTrabalhadas) {
        this.setData(data);
        this.setHorasTrabalhadas(horasTrabalhadas);
    }

    
    public double getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(double horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}