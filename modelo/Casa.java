package modelo;
import java.io.Serializable;

import util.AcrescimoMaiorDoQueJurosException;

public class Casa extends Financiamento implements Serializable {
    //Atributos
    private double areaConstruida;
    private double tamanhoTerreno;

    //Constructor
    public Casa(double valorCasa, int prazoFinanciamento, double taxaJurosAnual, double areaConstruida, double tamanhoTerreno) {
        super(valorCasa, prazoFinanciamento, taxaJurosAnual);
        this.areaConstruida = areaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    //Metodos
    // Método para validar se o acréscimo é maior que os juros mensais
    private void validaAumento(double jurosMensais, double acrescimo) throws AcrescimoMaiorDoQueJurosException {
        if (acrescimo > jurosMensais) {
            throw new AcrescimoMaiorDoQueJurosException(
                    "O acréscimo de R$ " + acrescimo + " é maior que o valor dos juros mensais de R$ " + jurosMensais);
        }
    }

    //Método para calcular o pagamento mensal com juros e considerando o valor do acrescimo
    public double calcularPagamentoMensal() {
        double jurosMensais = this.taxaJurosAnual / 12;
        double parcelaMensal = this.valorImovel / (this.prazoFinanciamento * 12) * (1 + jurosMensais);

        double acrescimo = 80; // Acréscimo fixo de R$ 80

        try {
            validaAumento(jurosMensais, acrescimo);
            return parcelaMensal + acrescimo;
        } catch (AcrescimoMaiorDoQueJurosException e) {
            // Se o acréscimo for maior que os juros, substitui acréscimo pelo valor dos juros
            System.out.println(e.getMessage());
            return parcelaMensal + jurosMensais;
        }
    }

    //Método para calcular o valor total do financiamento da casa
    @Override
    public double calcularTotalPagamento () {
        return (calcularPagamentoMensal() * (this.prazoFinanciamento * 12));
        }


    }