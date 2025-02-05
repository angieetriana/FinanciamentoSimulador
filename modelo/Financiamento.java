package modelo;
import java.io.Serializable;



public abstract class Financiamento implements Serializable {
        // Atributos
        protected double valorImovel;
        protected int prazoFinanciamento;
        protected double taxaJurosAnual;

        //Metodos get
        public double getValorImovel() {
                return this.valorImovel;
        }
        public int getPrazoFinanciamento() {
                return this.prazoFinanciamento;
        }
        public double getTaxaJurosAnual() {
                return this.taxaJurosAnual;
        }

        //Construtor
        public Financiamento(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaDeJurosAnual){
                this.valorImovel = valorDesejadoImovel;
                this.prazoFinanciamento = prazoFinanciamentoAnos;
                this.taxaJurosAnual = taxaDeJurosAnual/100;
        }
        //Metodos
        public abstract double calcularPagamentoMensal();

        public abstract double calcularTotalPagamento();

        public void mostrarDadosFinanciamento() {
            System.out.printf("Valor total do financiamento: %.2f", calcularTotalPagamento());
            System.out.println("Valor do imovel: " + getValorImovel());
        }

        public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(this.getValorImovel()).append("\n");
                sb.append(this.calcularTotalPagamento()).append("\n");
                return sb.toString();
        }
}
