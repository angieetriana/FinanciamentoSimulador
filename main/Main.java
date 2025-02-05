package main;

import modelo.Casa;
import modelo.Financiamento;
import modelo.Apartamento;
import modelo.Terreno;
import util.InterfaceUsuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class Main { //Inicia o codigo
    public static void main(String[] args) {
        InterfaceUsuario interFaceUsuario = new InterfaceUsuario();
        List<Financiamento> listaDeFinanciamentos = new ArrayList<>();

        // Pede os dados dos financiamentos
        int pedirTipoImovel = interFaceUsuario.pedirTipoImovel();
        interFaceUsuario.coletarAtributosEspecificos(pedirTipoImovel);
        double taxaJurosAnual = interFaceUsuario.pedirTaxaJuros();
        int prazoFinanciamentoEmAnos = interFaceUsuario.pedirPrazoFinanciamento();
        double valorImovel = interFaceUsuario.pedirValorImovel();

        // Adicionando novos financiamentos na ArrayList
        listaDeFinanciamentos.add(new Casa(200000, 15, 5.0, 150, 300));  // Financiamento 1
        listaDeFinanciamentos.add(new modelo.Casa(500000, 10, 10, 200, 500));  // Financiamento 2
        listaDeFinanciamentos.add(new modelo.Apartamento(150000, 10, 6.0, 2, 5));  // Financiamento 3
        listaDeFinanciamentos.add(new modelo.Apartamento(250000, 25, 5.5, 1, 4));  // Financiamento 4
        listaDeFinanciamentos.add(new modelo.Terreno(450000, 15, 5.5, "Residencial"));  // Financiamento 4


        double totalValorImoveis = 0;
        double totalValorFinanciamentos = 0;

        // Calcula os totais e mostra os dados de cada financiamento
        int count = 1;
        for (Financiamento financiamento : listaDeFinanciamentos) {
            System.out.println("Financiamento " + count + " – valor do imóvel: R$ " + String.format("%.2f", financiamento.getValorImovel())
                    + ", valor da parcela do financiamento: R$ " + String.format("%.2f", financiamento.calcularPagamentoMensal()));
            totalValorImoveis += financiamento.getValorImovel();
            totalValorFinanciamentos += financiamento.calcularTotalPagamento();
            count++;
        }

        Financiamento terreno = new modelo.Terreno(valorImovel, prazoFinanciamentoEmAnos, taxaJurosAnual, "");
        listaDeFinanciamentos.add(terreno);

        // Mostra o total de todos os imóveis e de todos os financiamentos
        System.out.printf("\nTotal de todos os imóveis: R$ %.2f", totalValorImoveis);
        System.out.printf("\nTotal de todos os financiamentos: R$ %.2f", totalValorFinanciamentos);

        //Salva os dados em um arquivo de texto
        try (FileWriter writer = new FileWriter("dados_financiamentos.txt")) {
            for (Financiamento financiamento : listaDeFinanciamentos) {
                writer.write(financiamento.toString());
                writer.write("\n");  // Cada financiamento em uma linha
            }
            System.out.println("\nDados salvos no arquivo texto.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Salva os dados serializados (contendo o ArrayList de financiamentos)
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("financiamentos_serializados.dat"))) {
            out.writeObject(listaDeFinanciamentos);  // Salva o ArrayList
            System.out.println("\nDados salvos no arquivo serializado.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Permite a leitura dos arquivo de texto para comprovar que foram salvos corretamente.
        try (BufferedReader reader = new BufferedReader(new FileReader("dados_financiamentos.txt"))) {
            String line;
            System.out.println("\nConteúdo do arquivo de texto:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Leitura do arquivo serializado
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("financiamentos_serializados.dat"))) {
            List<Financiamento> listaLida = (List<Financiamento>) in.readObject();  // Lê o ArrayList
            System.out.println("\nConteúdo do arquivo serializado:");
            for (Financiamento financiamento : listaLida) {
                System.out.println(financiamento.toString());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}