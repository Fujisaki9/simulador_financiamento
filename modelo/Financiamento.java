package modelo;

import java.io.Serializable;

public abstract class Financiamento implements Serializable {

    // Constante
    private static final long serialVersionUID = 1L;

    // Atributos
    protected double valorImovel;
    protected int prazoFinanciamento;
    protected double taxaJurosAnual;

    // Construtor
    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public abstract double calcularPagamentoTotal();

    public abstract String toString();

    /**
    * Calcula o pagamento mensal do financiamento.
    * Fórmula padrão utilizada por subclasses que não sobrescrevem este metodo.
    *
    * @return valor da parcela mensal.
    */
    public double calcularPagamentoMensal() {
        return (this.valorImovel / (this.prazoFinanciamento * 12)) * (1 + (this.taxaJurosAnual / 12));
    }

    // Getters
    public double getValorImovel() {return this.valorImovel;}

    public int getPrazoFinanciamento() {return this.prazoFinanciamento;}

    public double getTaxaJurosAnual() {return this.taxaJurosAnual;}

    // Getters Utilitários
    public int getPrazoMeses() {return this.prazoFinanciamento * 12;}

    public double getTaxaJurosMensal() {return (this.taxaJurosAnual / 100) / 12;}

    // Setters
    public void setValorImovel(double valorImovel) {this.valorImovel = valorImovel;}

    public void setPrazoFinanciamento(int prazoFinanciamento) {this.prazoFinanciamento = prazoFinanciamento;}

    public void setTaxaJurosAnual(double taxaJurosAnual) {this.taxaJurosAnual = taxaJurosAnual;}
}