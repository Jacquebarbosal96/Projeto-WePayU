package br.ufal.ic.p2.wepayu.models;

import java.util.LinkedHashMap;
import java.util.Map;

public class Empregado {
    private String nome;
    private String endereco;
    private String id;
    private String metodoPagamento = "emMaos"; //padrao p iniciar
// banco
    private String banco;
    private String agencia;
    private String contaCorrente;

// sindicato:
    private boolean sindicalizado = false;
    private String idSindicato;
    private double taxaSindical;
    private Map<String, Double> taxasServico = new LinkedHashMap<>(); // data, valor

    
    public Empregado(){}

    public Empregado(String nome, String endereco, String id, String metodoPagamento) {
        this.nome = nome;
        this.endereco = endereco;
        this.id = id;
        this.metodoPagamento = metodoPagamento;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id=id; 
    }
    
    public String getMetodoPagamento() {
        return metodoPagamento;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

//pro banco
     public String getBanco() {
        return banco; 
        }
        public void setBanco(String banco) {
            this.banco = banco; 
            }
        public String getAgencia() {
            return agencia; 
            }
        public void setAgencia(String agencia) {
            this.agencia = agencia; 
            }
        public String getContaCorrente() {
            return contaCorrente; 
            }
        public void setContaCorrente(String contaCorrente) {
            this.contaCorrente = contaCorrente; 
            }





//pro sindicato:
    public boolean isSindicalizado() {
        return sindicalizado; 
    }

    public void setSindicalizado(boolean sindicalizado) {
        this.sindicalizado = sindicalizado; 
        }

    public String getIdSindicato() {
        return idSindicato; 
    }

    public void setIdSindicato(String idSindicato) {
        this.idSindicato = idSindicato; 
        }

    public double getTaxaSindical() {
        return taxaSindical; 
        }

    public void setTaxaSindical(double taxaSindical) {
        this.taxaSindical = taxaSindical; 
        }


    public Map<String, Double> getTaxasServico() {
        return taxasServico; 
        }

    public void setTaxasServico(Map<String, Double> taxasServico) {
        this.taxasServico = taxasServico; 
        }

    public void lancaTaxaServico(String data, double valor) {
        taxasServico.put(data, valor); 
        }
}
