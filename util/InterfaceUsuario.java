package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfaceUsuario {
    private static Scanner sc = new Scanner(System.in);

    //Metodos
    public int pedirTipoImovel() { //Pergunta qual o tipo de imovel que o usuario quer financiar
        int tipoImovel;
        do {
            System.out.println("Qual o tipo de imóvel que deseja financiar? Digite o número da opção:");
            System.out.println("1. Casa");
            System.out.println("2. Apartamento");
            System.out.println("3. Terreno");
            tipoImovel = sc.nextInt();
            if (tipoImovel < 1 || tipoImovel > 3) {
                System.out.println("Opção inválida. Tente novamente.");
            }
        } while (tipoImovel < 1 || tipoImovel > 3);
        return tipoImovel;
    }

    public void coletarAtributosEspecificos(int tipoImovel) {
        switch (tipoImovel) {
            case 1 -> {
                System.out.println("Informe o tamanho da área construída (em m²):");
                double tamanhoArea = sc.nextDouble();
                System.out.println("Informe o tamanho do terreno (em m²):");
                double tamanhoTerreno = sc.nextDouble();
                System.out.printf("Dados da casa registrados: Área construída = %.2f m², Terreno = %.2f m².%n", tamanhoArea, tamanhoTerreno);
            }
            case 2 -> {
                System.out.println("Informe o número de vagas na garagem:");
                int numeroVagas = sc.nextInt();
                System.out.println("Informe o número de andares do prédio:");
                int numeroAndares = sc.nextInt();
                System.out.printf("Dados do apartamento registrados: Vagas = %d, Andares = %d.%n", numeroVagas, numeroAndares);
            }
            case 3 -> {
                System.out.println("Informe o tipo de zona (Residencial ou Comercial):");
                sc.nextLine(); // Consumir o newline que sobra do nextInt()
                String tipoZona = sc.nextLine();
                System.out.printf("Dados do terreno registrados: Tipo de zona = %s.%n", tipoZona);
            }
            default -> System.out.println("Erro: tipo de imóvel inválido.");
        }
    }

    public double pedirValorImovel() { // Obtem valor do imovel e valida se o numero sera aceito ou nao
        double valorImovel;
        do {
            try {
                System.out.println("Qual o valor do imovel?");
                valorImovel = sc.nextDouble();
                if (valorImovel <= 0) { // validacao do valor fornecido pelo usuario, se for negativo ou zero nao sera aceito
                    System.out.println("Valor invalido! Valor do imovel nao pode ser negativo. Digite um valor valido.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número decimal.");
                sc.nextLine();
                valorImovel = 0;
            }
        } while (valorImovel <= 0); // Repete enquanto o valor for negativo
        return valorImovel;
    }

    public int pedirPrazoFinanciamento() { // Obtem valor do finaciamento e valida se o numero sera aceito ou nao
        int prazoFinancimento;
        do {
            try {
                System.out.println("Qual o prazo do financiamento em anos?");
                prazoFinancimento = sc.nextInt();
                if (prazoFinancimento <= 0) { // validacao do valor fornecido pelo usuario, se for negativo  ou zero nao sera aceito
                    System.out.println("Valor invalido! Prazo nao pode ser negativo. Digite um valor valido.");
                } else if (prazoFinancimento > 30) { // Limite de 30 anos para realizar o financiamento
                    throw new IllegalArgumentException("O prazo de financiamento não pode exceder 30 anos."); //Erro caso o financiamento for maior que 30 anos
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); //Imprime o tipo de erro capturado e mostra na tela
                prazoFinancimento = 0;
            } catch (Exception e) { //Captura erros genericos e permite que o programa continue no loop
                System.out.println("Entrada inválida! Por favor, insira um número inteiro.");
                sc.nextLine();
                prazoFinancimento = 0;
            }
        } while (prazoFinancimento <= 0); // Repete enquanto o valor for negativo
        return prazoFinancimento;
    }

    public double pedirTaxaJuros() { // Obtém valor da taxa e valida se o número será aceito ou não
        double taxaJuros;
        do {
            try {
                System.out.println("Qual a taxa de juros?");
                taxaJuros = sc.nextDouble();
                if (taxaJuros <= 0) {
                    System.out.println("Valor inválido! A taxa de juros não pode ser negativa ou zero. Digite um valor válido.");
                }
            } catch (InputMismatchException e) { //Captura erros genericos e permite que o programa continue no loop
                System.out.println("Entrada inválida! Por favor, insira um número inteiro.");
                sc.nextLine();
                taxaJuros = 0;
                }
        } while (taxaJuros <= 0); // Repete enquanto o valor for negativo ou zero
        return taxaJuros;
    }
}

