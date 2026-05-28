import java.util.Scanner;
import java.util.ArrayList;
public class SistemaFolhaPagamentos {
    static Scanner ler = new Scanner(System.in);
    static ArrayList<Colaborador> listaCadastro = new ArrayList<>();
    static double salarioBase = 2000.00;
    static class Colaborador {
        //FUNCIONÁRIO PADRÃO    
        String nome;
        int matricula;
        double valorTotal;
        double percentualComissao;
        int qtdePecas;
        double valorPeca;
        Colaborador(String nome, int mat) {
            this.nome = nome;
            this.matricula = mat;
        }

        //FUNCIONÁRIO COMISSIONADO
        Colaborador(String nome, int mat, double valorT, double percentualC){
            this.nome = nome;
            this.matricula = mat;
            this.valorTotal = valorT;
            this.percentualComissao = percentualC;
        }

        //FUNCIONÁRIO PRODUÇÃO
        Colaborador(String nome, int mat, int pecas, double valorP){
            this.nome = nome;
            this.matricula = mat;
            this.qtdePecas = pecas;
            this.valorPeca = valorP;
        }


    }
    public static void main(String[] args) {
        int escolha;
        
        do {
            menu();
            escolha = ler.nextInt();
            ler.nextLine();
            if (escolha > 4 || escolha < 0) {
                System.out.println("Opção Incorreta!");
                System.out.println("Digite Novamente!");
                continue;
            }
            switch (escolha) {
                case 1:
                    System.out.println("Padrão");
                    cadastroPadrao();
                    break;
                    
                case 2:
                    System.out.println("Comissionado");
                    cadastroComissionado();
                    break;
                
                case 3:
                    System.out.println("Produção");
                    cadastrarProducao();;
                    break;
                
                case 4:
                    lista();
                    break;
                
                case 0:
                    System.out.println("Saindo...");
                    break;
            
                default:
                    System.out.println("Opção Inválida");
                    break;
            }
        } while (escolha != 0);

    }

    static int escolha;
    public static void menu () {
        System.out.println("=".repeat(20));
        System.out.println("Folha de Pagamento:");
        System.out.println("-".repeat(20));
        System.out.println("Aperte 1 para cadastrar funcionário Padrão:");
        System.out.println("-".repeat(50));
        System.out.println("Aperte 2 para cadastrar funcionário Comissionado:");
        System.out.println("-".repeat(50));
        System.out.println("Aperte 3 para cadastrar funcionário Produção:");
        System.out.println("-".repeat(50));
        System.out.println("Aperte 4 para mostrar a lista do funcionários cadastrados.");
        System.out.println("-".repeat(50));
        System.out.println("Aperte 0 para sair:");
        System.out.println("=".repeat(20));
    }

    static void cadastroPadrao() {
        System.out.println("Nome: ");
        String n = ler.nextLine();
        
        System.out.println("Matrícula: ");
        int m = ler.nextInt();
        ler.nextLine();

        System.out.println("Funcionário Cadastrado!");

        Colaborador c = new Colaborador(n, m);
        listaCadastro.add(c);
    } 

    static void cadastroComissionado () {
        System.out.print("Nome: ");
        String n = ler.nextLine();
       
        System.out.println("Matrícula: ");
        int m = ler.nextInt();
        ler.nextLine();

        System.out.println("Valor das Vendas:");
        String vd = ler.nextLine().replaceAll("[^0-9.]", "");
        double v = Double.parseDouble(vd);
        System.out.println("Percentual da Comissaão:");
        String percentualComissao = ler.nextLine().replaceAll("[^0-9]", "");
        double pc = Double.parseDouble(percentualComissao);
        System.out.println("Funcionário Cadastrado!");

        Colaborador c = new Colaborador(n, m, v, pc);
        listaCadastro.add(c);
    }

    static void cadastrarProducao () {
        System.out.println("Nome: ");
        String n = ler.nextLine();
        System.out.println("Matrícula: ");
        int m = ler.nextInt();
        ler.nextLine();

        System.out.println("Quantidade de Peças: ");
        int p = ler.nextInt();
        ler.nextLine();
        
        System.out.println("Valor da Peça: ");
        String vdp = ler.nextLine().replaceAll("[^0-9]", "");
        double vp = Double.parseDouble(vdp);
        System.out.println("Funcionário Cadastrado!");

        Colaborador c = new Colaborador(n, m, p, vp);
        listaCadastro.add(c);
    }

    static void lista () {

        // Sem nenhum cadastro
        if (listaCadastro.isEmpty()) {
            System.out.println("Nenhum Funcionário cadastrado.");
            return;
        }
        // Plural/singular
        System.out.println("=".repeat(30));
        if (listaCadastro.size() == 1) {
            System.out.println(listaCadastro.size() + " Pessoa Cadastrada.");
        }else {
            System.out.println(listaCadastro.size() + " Pessoas Cadastradas.");
        }

        // LISTA
        for (Colaborador c : listaCadastro) {
            double salarioTotal = salarioBase;

            // COMISSIONADO
            if (c.valorTotal > 0) {
               double comissao = (c.valorTotal * c.percentualComissao  / 100);
               salarioTotal = comissao + salarioBase; 

               System.out.println("COMISSIONADO");
               System.out.println("Nome: " + c.nome);
               System.out.println("Matrícula: " + c.matricula);
               System.out.println("Salário Fixo: " + "R$ " + salarioBase + "R$");
               System.out.printf("Comissão: R$ %.2f (%.2f%%)%n", comissao, c.percentualComissao);
               System.out.printf("Salário Final: R$ %.2f%n", salarioTotal); 
               System.out.println("=".repeat(30));

            // PRODUÇÃO   
            } else if (c.qtdePecas > 0) {
                double bonus = (c.valorPeca * c.qtdePecas);
                salarioTotal = bonus + salarioBase;

                System.out.println("PRODUÇÃO"); 
                System.out.println("Nome: " + c.nome);
                System.out.println("Matrícula: " + c.matricula); 
                System.out.println("Salário Fixo: " + "R$ " + salarioBase + "R$");
                System.out.printf("Produtividade: %.2f R$ (%d peças x R$ %.2f)%n", 
                bonus, c.qtdePecas, c.valorPeca);
                System.out.printf("Salário Final: R$ %.2f%n", salarioTotal); 
                System.out.println("=".repeat(30));

            // PADRÃO    
            } else {
                salarioTotal = salarioBase; 

                System.out.println("PADRÃO");
                System.out.println("Nome: " + c.nome);
                System.out.println("Matrícula: " + c.matricula);
                System.out.println("Salário Fixo: " + "R$ " + salarioBase);
                System.out.println("Extras: 0.0 R$");
                System.out.printf("Salário Final: R$ %.2f%n", salarioTotal);
                System.out.println("=".repeat(30));
            }
            System.out.println();
            
        }
    }
}
