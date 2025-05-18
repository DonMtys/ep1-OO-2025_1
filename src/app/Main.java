package app;

import java.util.Scanner; 

public class Main {
    
    private static Scanner scanner = new Scanner(System.in); 
//interface inicia do programa 33
    public static void main(String[] args) {
        int opcao;

        System.out.println("======================================");
        System.out.println(" Bem-vindo ao Sistema Acadêmico FCTE ");
        System.out.println("======================================");

        do {
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("1. Modo Aluno");
            System.out.println("2. Modo Disciplina/Turma");
            System.out.println("3. Modo Avaliação/Frequência");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número correspondente à opção.");
                scanner.nextLine(); 
                opcao = -1;
                continue;   
            }

            switch (opcao) {
                case 1:
                    modoAluno();
                    break;
                case 2:
                    modoDisciplinaTurma();
                    break;
                case 3:
                    modoAvaliacaoFrequencia();
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 0);

        System.out.println("Sistema encerrado.");
        scanner.close();
    }
//yh333
    public static void modoAluno() {
        System.out.println("\n--- MODO ALUNO (A Implementar) ---");
        System.out.println("Funcionalidades do Modo Aluno serão implementadas aqui.");
        System.out.println("Pressione Enter para voltar ao menu principal...");
        scanner.nextLine(); 
    }

    public static void modoDisciplinaTurma() {
        System.out.println("\n--- MODO DISCIPLINA/TURMA (A Implementar) ---");
        System.out.println("Funcionalidades do Modo Disciplina/Turma serão implementadas aqui.");
        System.out.println("Pressione Enter para voltar ao menu principal...");
        scanner.nextLine(); 
    }

    public static void modoAvaliacaoFrequencia() {
        System.out.println("\n--- MODO AVALIAÇÃO/FREQUÊNCIA (A Implementar) ---");
        System.out.println("Funcionalidades do Modo Avaliação/Frequência serão implementadas aqui.");
        System.out.println("Pressione Enter para voltar ao menu principal...");
        scanner.nextLine(); 
    }
}