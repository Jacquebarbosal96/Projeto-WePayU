package br.ufal.ic.p2.wepayu.Exception;

public class DataInicialNaoPodeSerDepois extends Exception {
    public DataInicialNaoPodeSerDepois() {
        super("Data inicial nao pode ser posterior aa data final.");
    }
}