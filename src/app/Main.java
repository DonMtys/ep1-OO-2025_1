package app;



import modelo.Aluno;
import modelo.AlunoNormal;
import modelo.AlunoEspecial;
import modelo.Disciplina;
import java.util.List;        
import java.util.ArrayList; 
import java.util.Scanner; 

public class Main {
    //A baixo a interface da primeira Parte 
	 private static List<Aluno> listaDeAlunos = new ArrayList<>();
	 private static List<Disciplina> listaDeDisciplinas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in); 
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
//Aqui nós vai configura o codigo para o modoAluno 
    public static void modoAluno() {
        int opcaoModoAluno;
        do {
            System.out.println("\n--- MODO ALUNO ---");
            System.out.println("1. Cadastrar Novo Aluno");
            System.out.println("2. Listar Alunos Cadastrados"); 
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcaoModoAluno = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                System.out.println("Entrada inválida.");
                scanner.nextLine();
                opcaoModoAluno = -1;
                continue;
            }
//aqui a gente vai fazer a escolha do usuario para que possa escolhe uma das opão a cima 
            switch (opcaoModoAluno) {
                case 1:
                    cadastrarAluno(); // CHAMADA para o método que vamos definir abaixo
                    break;
                case 2:
                    listarAlunos();   // CHAMADA para o método que vamos definir abaixo
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida do Modo Aluno.");
            }
        } while (opcaoModoAluno != 0);
    }

//aqui faz o cadastro do Aluno     
    public static void cadastrarAluno() {
        System.out.println("\n-- Cadastro de Novo Aluno --");
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        for (Aluno alunoExistente : listaDeAlunos) {
            if (alunoExistente.getMatricula().equals(matricula)) {
                System.out.println("ERRO: Matrícula já cadastrada para o aluno: " + alunoExistente.getNome());
                return;
            }
        }

        System.out.print("Curso: ");
        String curso = scanner.nextLine();

        System.out.print("O aluno é Normal ou Especial? (Digite N para Normal, E para Especial): ");
        String tipoInput = scanner.nextLine();

        Aluno novoAluno = null;

        if (tipoInput.equalsIgnoreCase("N")) {
            novoAluno = new AlunoNormal(nome, matricula, curso);
            System.out.println("Aluno Normal sendo criado."); 
        } else if (tipoInput.equalsIgnoreCase("E")) {
            novoAluno = new AlunoEspecial(nome, matricula, curso);
            System.out.println("Aluno Especial sendo criado.");
        } else {
            System.out.println("Tipo de aluno inválido. Cadastro cancelado.");
            return; 
        }

        listaDeAlunos.add(novoAluno); 
        System.out.println("----- Aluno Cadastrado com Sucesso! -----");
        System.out.println(novoAluno.toString()); 
        System.out.println("----------------------------------------");
    }
    public static void listarAlunos() {
        System.out.println("\n-- Lista de Alunos Cadastrados --");
        if (listaDeAlunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado ainda.");
            return;
        }
        for (Aluno aluno : listaDeAlunos) {
            System.out.println(aluno.toString());
        }
        System.out.println("---------------------------------");
    }
    // modo disciplina criado para o usuario configura 
    public static void modoDisciplinaTurma() {
        int opcaoModoDT;
        do {
            System.out.println("\n--- MODO DISCIPLINA/TURMA ---");
            System.out.println("1. Cadastrar Nova Disciplina");
            System.out.println("2. Listar Disciplinas Cadastradas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcaoModoDT = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada inválida.");
                scanner.nextLine(); 
                opcaoModoDT = -1;
                continue;
            }

            switch (opcaoModoDT) {
                case 1:
                    cadastrarDisciplina(); 
                    break;
                case 2:
                    listarDisciplinas();   
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida do Modo Disciplina/Turma.");
            }
        } while (opcaoModoDT != 0);
    }
    
    public static void cadastrarDisciplina() {
        System.out.println("\n-- Cadastro de Nova Disciplina --");
        System.out.print("Nome da disciplina: ");
        String nome = scanner.nextLine();

        System.out.print("Código da disciplina: ");
        String codigo = scanner.nextLine();     
        for (Disciplina discExistente : listaDeDisciplinas) {
            if (discExistente.getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println("ERRO: Código de disciplina '" + codigo + "' já cadastrado para a disciplina: " + discExistente.getNome());
                return; 
            }
        }

        System.out.print("Carga horária (em horas): ");
        int cargaHoraria = 0;
        if (scanner.hasNextInt()) {
            cargaHoraria = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Carga horária inválida. Será definida como 0. Você pode editar depois.");
            scanner.nextLine();
        }

        Disciplina novaDisciplina = new Disciplina(nome, codigo, cargaHoraria);

        // Perguntar sobre pré-requisitos
        System.out.print("Deseja adicionar pré-requisitos para esta disciplina? (s/n): ");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("s")) {
            String codPreRequisito;
            System.out.println("Digite os códigos das disciplinas pré-requisito. Digite 'fim' para terminar.");
            do {
                System.out.print("Código do pré-requisito (ou 'fim'): ");
                codPreRequisito = scanner.nextLine();
                if (!codPreRequisito.equalsIgnoreCase("fim") && !codPreRequisito.isEmpty()) {
                    novaDisciplina.adicionarPreRequisito(codPreRequisito);
                    System.out.println("Pré-requisito '" + codPreRequisito + "' adicionado.");
                }
            } while (!codPreRequisito.equalsIgnoreCase("fim"));
        }

        listaDeDisciplinas.add(novaDisciplina);
        System.out.println("----- Disciplina Cadastrada com Sucesso! -----");
        System.out.println(novaDisciplina.toString()); 
        System.out.println("---------------------------------------------");
    }

    public static void listarDisciplinas() {
        System.out.println("\n-- Lista de Disciplinas Cadastradas --");
        if (listaDeDisciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada ainda.");
            return;
        }
        for (Disciplina disciplina : listaDeDisciplinas) {
            System.out.println(disciplina.toString()); 
        }
        System.out.println("------------------------------------");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public static void modoAvaliacaoFrequencia() {
        System.out.println("\n--- MODO AVALIAÇÃO/FREQUÊNCIA (A Implementar) ---");
        System.out.println("Funcionalidades do Modo Avaliação/Frequência serão implementadas aqui.");
        System.out.println("Pressione Enter para voltar ao menu principal...");
        scanner.nextLine(); 
    }
}