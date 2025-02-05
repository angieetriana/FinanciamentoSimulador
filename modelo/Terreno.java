package modelo;
import java.io.Serializable;

public class Terreno extends Financiamento implements Serializable {
    //Atributos
    protected String tipoZona;

    //Constructor
    public Terreno(double valorImovel,int prazoFinanciamento, double taxaJurosAnual, String tipoZona){
        super(valorImovel, prazoFinanciamento, taxaJurosAnual );
        this.tipoZona = tipoZona;
    }

    //Metodos
    @Override
    public double calcularPagamentoMensal(){
        double pagamentoMensal = this.valorImovel / (this.prazoFinanciamento * 12) * (1 + (this.taxaJurosAnual / 12));
        return (pagamentoMensal * 0.02) + pagamentoMensal;
    }

    @Override
    public double calcularTotalPagamento() {
        return (calcularPagamentoMensal() * (this.prazoFinanciamento * 12));
    }


}

