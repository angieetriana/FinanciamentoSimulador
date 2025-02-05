package modelo;
import java.lang.Math;
import java.io.Serializable;


public class Apartamento extends Financiamento implements Serializable {
    //Atributos
    protected int numeroVagas;
    protected int numeroAndares;

    //Constructor
    public Apartamento(double valorImovel,int prazoFinanciamento, double taxaJurosAnual, int numeroVagas, int numeroAndares) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.numeroVagas = numeroVagas;
        this.numeroAndares = numeroAndares;
    }

    //Metodos
    //Métodos que calculam a taxa de juros mensais e a quantidade de meses na qual o financiamento sera pago
    public double taxaMensal() {
        return (super.taxaJurosAnual/12);
    }
    public double meses() {
        return (super.prazoFinanciamento*12);
    }

    //Método que calcula o valor mensal do financiamento realizando calculo individual para apartamentos
    public double calcularPagamentoMensal() {
        double taxa = taxaMensal();
        double nMeses = meses();
        double numerador = super.valorImovel * taxa * Math.pow(1 + taxa, nMeses);
        double denominador = Math.pow(1 + taxa, nMeses)-1;

        return numerador / denominador;
    }
    //Método para calcular o valor total do financiamento do apartamento
    @Override
    public double calcularTotalPagamento() {
        return (calcularPagamentoMensal() * (this.prazoFinanciamento * 12));
    }
}
