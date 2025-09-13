package br.ufal.ic.p2.wepayu.Exception;

public class EmpregadoNexiste extends Exception {
    public EmpregadoNexiste() {
        super("Empregado nao existe.");
    }
}