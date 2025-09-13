package br.ufal.ic.p2.wepayu.Exception;

public class IDempregadoNaoPodeSerNula extends Exception {
    public IDempregadoNaoPodeSerNula() {
        super("Identificacao do empregado nao pode ser nula.");
    }
}