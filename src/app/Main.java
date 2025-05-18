package app;

import modelo.Aluno;
import modelo.AlunoNormal;
import modelo.AlunoEspecial;
import modelo.Disciplina;
import modelo.Professor;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Listas para armazenar os objetos do sistema
    private static List<Aluno> listaDeAlunos = new ArrayList<>();
    private static List<Disciplina> listaDeDisciplinas = new ArrayList<>();
    private static List<Professor> listaDeProfessores = new ArrayList<>();    private static Scanner scanner = new Scanner(System.in); // Scanner para entrada do usuário

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
                scanner.nextLine(); // Consome a quebra de linha
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número correspondente à opção.");
                scanner.nextLine(); // Limpa a entrada inválida
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
                    break; // Adicionei o break que estava faltando aqui
                case 0:
                    System.out.println("Saindo do sistema...");
                    // Futuramente: Adicionar lógica para salvar dados em arquivos
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 0);

        System.out.println("Sistema encerrado.");
        scanner.close();
    }

    // --- MODO ALUNO ---
    public static void modoAluno() {
        int opcaoModoAluno;
        do {
            System.out.println("\n--- MODO ALUNO ---");
            System.out.println("1. Cadastrar Novo Aluno");
            System.out.println("2. Listar Alunos Cadastrados");
            // Futuramente: Adicionar opções para Editar Aluno, Matricular, Trancar
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

            switch (opcaoModoAluno) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida do Modo Aluno.");
            }
        } while (opcaoModoAluno != 0);
    }

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
        } else if (tipoInput.equalsIgnoreCase("E")) {
            novoAluno = new AlunoEspecial(nome, matricula, curso);
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

    // --- MODO DISCIPLINA/TURMA ---
    public static void modoDisciplinaTurma() {
        int opcaoModoDT;
        do {
            System.out.println("\n--- MODO DISCIPLINA/TURMA ---");
            System.out.println("1. Cadastrar Nova Disciplina");
            System.out.println("2. Listar Disciplinas Cadastradas");
            System.out.println("3. Cadastrar Novo Professor");
            System.out.println("4. Listar Professores Cadastrados");
            System.out.println("5. Criar Nova Turma");
            System.out.println("6. Listar Turmas Criadas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcaoModoDT = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número.");
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
                case 3:
                    cadastrarProfessor(); // Corrigido (se antes chamava cadastrarAluno)
                    break;
                case 4:
                    listarProfessores();
                    break;
                case 5:
                    criarTurma();
                    break;
                case 6:
                    listarTurmas(); // Garanta que este método existe e tem a lógica correta
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida do Modo Disciplina/Turma.");
            }
        } while (opcaoModoDT != 0);
    }

    private static void listarTurmas() {
		
	}

	public static void cadastrarDisciplina() {
        System.out.println("\n-- Cadastro de Nova Disciplina --");
        System.out.print("Nome da disciplina: ");
        String nome = scanner.nextLine();

        System.out.print("Código da disciplina: ");
        String codigo = scanner.nextLine();
        for (Disciplina discExistente : listaDeDisciplinas) {
            if (discExistente.getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println("ERRO: Código de disciplina '" + codigo + "' já cadastrado para: " + discExistente.getNome());
                return;
            }
        }

        System.out.print("Carga horária (em horas): ");
        int cargaHoraria = 0;
        if (scanner.hasNextInt()) {
            cargaHoraria = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Carga horária inválida. Será definida como 0.");
            scanner.nextLine();
        }

        Disciplina novaDisciplina = new Disciplina(nome, codigo, cargaHoraria);

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

    public static void cadastrarProfessor() {
        System.out.println("\n-- Cadastro de Novo Professor --");
        System.out.print("Nome do professor: ");
        String nome = scanner.nextLine();

        Professor novoProfessor = new Professor(nome); // Usa o construtor que recebe nome
        listaDeProfessores.add(novoProfessor);

        System.out.println("----- Professor Cadastrado com Sucesso! -----");
        System.out.println(novoProfessor.toString());
        System.out.println("--------------------------------------------");
    }

    public static void listarProfessores() {
        System.out.println("\n-- Lista de Professores Cadastrados --");
        if (listaDeProfessores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado ainda.");
            return;
        }
        for (Professor prof : listaDeProfessores) {
            System.out.println(prof.toString());
        }
        System.out.println("--------------------------------------");
    }

    public static void criarTurma() {
        System.out.println("\n-- Criação de Nova Turma --");

        if (listaDeDisciplinas.isEmpty()) {
            System.out.println("ERRO: Nenhuma disciplina cadastrada. Cadastre disciplinas primeiro.");
            return;
        }
        if (listaDeProfessores.isEmpty()) {
            System.out.println("ERRO: Nenhum professor cadastrado. Cadastre professores primeiro.");
            return;
        }

        System.out.println("Disciplinas disponíveis:");
        for (int i = 0; i < listaDeDisciplinas.size(); i++) {
            System.out.println((i + 1) + ". " + listaDeDisciplinas.get(i).getNome() + " (" + listaDeDisciplinas.get(i).getCodigo() + ")");
        }
        System.out.print("Escolha o número da disciplina para a turma: ");
        int indiceDisciplina = -1;
        if (scanner.hasNextInt()) {
            indiceDisciplina = scanner.nextInt() - 1;
            scanner.nextLine();
        } else {
            System.out.println("Entrada inválida para disciplina.");
            scanner.nextLine();
            return;
        }

        if (indiceDisciplina < 0 || indiceDisciplina >= listaDeDisciplinas.size()) {
            System.out.println("ERRO: Escolha de disciplina inválida.");
            return;
        }
        Disciplina disciplinaEscolhida = listaDeDisciplinas.get(indiceDisciplina);

        System.out.println("\nProfessores disponíveis:");
        for (int i = 0; i < listaDeProfessores.size(); i++) {
            System.out.println((i + 1) + ". " + listaDeProfessores.get(i).getNome());
        }
        System.out.print("Escolha o número do professor para a turma: ");
        int indiceProfessor = -1;
        if (scanner.hasNextInt()) {
            indiceProfessor = scanner.nextInt() - 1;
            scanner.nextLine();
        } else {
            System.out.println("Entrada inválida para professor.");
            scanner.nextLine();
            return;
        }

        if (indiceProfessor < 0 || indiceProfessor >= listaDeProfessores.size()) {
            System.out.println("ERRO: Escolha de professor inválida.");
            return;
        }
        Professor professorEscolhido = listaDeProfessores.get(indiceProfessor);

        System.out.print("Semestre (ex: 2025.1): ");
        String semestre = scanner.nextLine();
        System.out.print("Horário (ex: Seg 14h-16h, Qua 14h-16h): ");
        String horario = scanner.nextLine();
        System.out.print("Capacidade máxima de alunos: ");
        int capacidade = 0;
        if (scanner.hasNextInt()) {
            capacidade = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Capacidade inválida. Definindo como 0.");
            scanner.nextLine();
        }

        System.out.print("A turma é presencial? (s/n): ");
        boolean presencial = scanner.nextLine().equalsIgnoreCase("s");
        String sala = "N/A";
        if (presencial) {
            System.out.print("Sala: ");
            sala = scanner.nextLine();
        }

        System.out.println("Formas de avaliação disponíveis:");
        System.out.println("1. Media Final = (P1 + P2 + P3 + L + S) / 5");
        System.out.println("2. Media Final = (P1 + P2 * 2 + P3 * 3 + L + S) / 8");
        System.out.print("Escolha a forma de avaliação (1 ou 2): ");
        String formaAvaliacao = "Forma não definida";
        if (scanner.hasNextInt()) {
            int escolhaForma = scanner.nextInt();
            scanner.nextLine();
            if (escolhaForma == 1) {
                formaAvaliacao = "Media Simples (P1+P2+P3+L+S)/5";
            } else if (escolhaForma == 2) {
                formaAvaliacao = "Media Ponderada (P1+P2*2+P3*3+L+S)/8";
            } else {
                System.out.println("Escolha de forma de avaliação inválida. Usando padrão.");
            }
        } else {
            System.out.println("Entrada inválida para forma de avaliação. Usando padrão.");
        }            scanner.nextLine();
        }

 
    public static void modoAvaliacaoFrequencia() {
        System.out.println("\n--- MODO AVALIAÇÃO/FREQUÊNCIA (A Implementar) ---");
        System.out.println("Funcionalidades do Modo Avaliação/Frequência serão implementadas aqui.");
        System.out.println("Pressione Enter para voltar ao menu principal...");
        scanner.nextLine();
    }
}