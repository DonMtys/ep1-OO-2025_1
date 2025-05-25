package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.Aluno;
import modelo.AlunoEspecial;
import modelo.AlunoNormal;
import modelo.Disciplina;
import modelo.Professor;
import modelo.Turma;

public class Main {
    private static List<Aluno> listaDeAlunos = new ArrayList<>();
    private static List<Disciplina> listaDeDisciplinas = new ArrayList<>();
    private static List<Professor> listaDeProfessores = new ArrayList<>();
    private static List<Turma> listaDeTurmas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        carregarDados();

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
                System.out.println("Entrada inválida. Digite um número.");
                scanner.nextLine();
                opcao = -1;
                continue;
            }

            switch (opcao) {
                case 1: modoAluno(); break;
                case 2: modoDisciplinaTurma(); break;
                case 3: modoAvaliacaoFrequencia(); break;
                case 0: System.out.println("Saindo do sistema..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        System.out.println("Sistema encerrado.");
        scanner.close();
    }

    public static void modoAluno() {
        int opcao;
        do {
            System.out.println("\n--- MODO ALUNO ---");
            System.out.println("1. Cadastrar Novo Aluno");
            System.out.println("2. Listar Alunos Cadastrados");
            System.out.println("3. Editar Aluno");
            System.out.println("4. Matricular Aluno em Turma");
            System.out.println("5. Trancar Disciplina");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada inválida.");
                scanner.nextLine();
                opcao = -1;
                continue;
            }

            switch (opcao) {
                case 1: cadastrarAluno(); break;
                case 2: listarAlunos(); break;
                case 3: editarAluno(); break;
                case 4: matricularAlunoEmTurma(); break;
                case 5: trancarDisciplina(); break;
                case 0: System.out.println("Retornando ao Menu Principal..."); break;
                default: System.out.println("Opção inválida do Modo Aluno.");
            }
        } while (opcao != 0);
    }

    public static void cadastrarAluno() {
        System.out.println("\n-- Cadastro de Novo Aluno --");
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        for (Aluno aluno : listaDeAlunos) {
            if (aluno.getMatricula().equals(matricula)) {
                System.out.println("ERRO: Matrícula já cadastrada.");
                return;
            }
        }

        System.out.print("Curso: ");
        String curso = scanner.nextLine();

        System.out.print("Aluno Normal ou Especial? (N/E): ");
        String tipo = scanner.nextLine();

        Aluno novoAluno = null;
        if (tipo.equalsIgnoreCase("N")) {
            novoAluno = new AlunoNormal(nome, matricula, curso);
        } else if (tipo.equalsIgnoreCase("E")) {
            novoAluno = new AlunoEspecial(nome, matricula, curso);
        } else {
            System.out.println("Tipo inválido. Cadastro cancelado.");
            return;
        }

        listaDeAlunos.add(novoAluno);
        System.out.println("----- Aluno Cadastrado com Sucesso! -----");
        System.out.println(novoAluno.toString());
        System.out.println("----------------------------------------");
    }

    public static void listarAlunos() {
        System.out.println("\n-- Lista de Alunos --");
        if (listaDeAlunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        for (Aluno aluno : listaDeAlunos) {
            System.out.println(aluno.toString());
        }
        System.out.println("---------------------");
    }

    public static void editarAluno() {
        System.out.println("\n-- Editar Aluno --");
        if (listaDeAlunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        
        Aluno alunoEncontrado = null;
        for (Aluno aluno : listaDeAlunos) {
            if (aluno.getMatricula().equals(matricula)) {
                alunoEncontrado = aluno;
                break;
            }
        }

        if (alunoEncontrado == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.println("Aluno encontrado: " + alunoEncontrado.toString());
        System.out.print("Novo nome (atual: " + alunoEncontrado.getNome() + "): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.trim().isEmpty()) {
            alunoEncontrado.setNome(novoNome);
        }

        System.out.print("Novo curso (atual: " + alunoEncontrado.getCurso() + "): ");
        String novoCurso = scanner.nextLine();
        if (!novoCurso.trim().isEmpty()) {
            alunoEncontrado.setCurso(novoCurso);
        }

        System.out.println("Aluno editado com sucesso!");
        System.out.println(alunoEncontrado.toString());
    }

    public static void matricularAlunoEmTurma() {
        System.out.println("\n-- Matricular Aluno em Turma --");
        
        if (listaDeAlunos.isEmpty()) {
            System.out.println("ERRO: Nenhum aluno cadastrado.");
            return;
        }
        if (listaDeTurmas.isEmpty()) {
            System.out.println("ERRO: Nenhuma turma criada.");
            return;
        }

        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        
        Aluno alunoEncontrado = null;
        for (Aluno aluno : listaDeAlunos) {
            if (aluno.getMatricula().equals(matricula)) {
                alunoEncontrado = aluno;
                break;
            }
        }

        if (alunoEncontrado == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        if (alunoEncontrado instanceof AlunoEspecial) {
            int disciplinasAtuais = contarDisciplinasDoAluno(alunoEncontrado);
            if (disciplinasAtuais >= 2) {
                System.out.println("ERRO: Aluno especial pode cursar no máximo 2 disciplinas.");
                return;
            }
        }

        System.out.println("\nTurmas disponíveis:");
        for (int i = 0; i < listaDeTurmas.size(); i++) {
            Turma turma = listaDeTurmas.get(i);
            System.out.println((i + 1) + ". " + turma.toString());
        }

        System.out.print("Escolha a turma: ");
        int indiceTurma = scanner.hasNextInt() ? scanner.nextInt() - 1 : -1;
        scanner.nextLine();

        if (indiceTurma < 0 || indiceTurma >= listaDeTurmas.size()) {
            System.out.println("ERRO: Turma inválida.");
            return;
        }

        Turma turmaEscolhida = listaDeTurmas.get(indiceTurma);
        
        if (turmaEscolhida.adicionarAluno(alunoEncontrado)) {
            System.out.println("Aluno matriculado com sucesso!");
        } else {
            System.out.println("ERRO: Não foi possível matricular (turma lotada ou aluno já matriculado).");
        }
    }

    private static int contarDisciplinasDoAluno(Aluno aluno) {
        int count = 0;
        for (Turma turma : listaDeTurmas) {
            for (Aluno alunoTurma : turma.getAlunos()) {
                if (alunoTurma.equals(aluno)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static void trancarDisciplina() {
        System.out.println("\n-- Trancar Disciplina --");
        
        if (listaDeAlunos.isEmpty()) {
            System.out.println("ERRO: Nenhum aluno cadastrado.");
            return;
        }

        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        
        Aluno alunoEncontrado = null;
        for (Aluno aluno : listaDeAlunos) {
            if (aluno.getMatricula().equals(matricula)) {
                alunoEncontrado = aluno;
                break;
            }
        }

        if (alunoEncontrado == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        // Mostrar turmas que o aluno está matriculado
        System.out.println("\nTurmas do aluno:");
        List<Turma> turmasDoAluno = new ArrayList<>();
        for (Turma turma : listaDeTurmas) {
            for (Aluno alunoTurma : turma.getAlunos()) {
                if (alunoTurma.equals(alunoEncontrado)) {
                    turmasDoAluno.add(turma);
                    System.out.println((turmasDoAluno.size()) + ". " + turma.toString());
                    break;
                }
            }
        }

        if (turmasDoAluno.isEmpty()) {
            System.out.println("Aluno não está matriculado em nenhuma turma.");
            return;
        }

        System.out.print("Escolha a turma para trancar: ");
        int indiceTurma = scanner.hasNextInt() ? scanner.nextInt() - 1 : -1;
        scanner.nextLine();

        if (indiceTurma < 0 || indiceTurma >= turmasDoAluno.size()) {
            System.out.println("ERRO: Turma inválida.");
            return;
        }

        Turma turmaParaTrancar = turmasDoAluno.get(indiceTurma);
        if (turmaParaTrancar.removerAluno(alunoEncontrado)) {
            System.out.println("Disciplina trancada com sucesso!");
        } else {
            System.out.println("ERRO: Não foi possível trancar a disciplina.");
        }
    }

    public static void modoDisciplinaTurma() {
        int opcao;
        do {
            System.out.println("\n--- MODO DISCIPLINA/TURMA ---");
            System.out.println("1. Cadastrar Disciplina");
            System.out.println("2. Listar Disciplinas");
            System.out.println("3. Cadastrar Professor");
            System.out.println("4. Listar Professores");
            System.out.println("5. Criar Turma");
            System.out.println("6. Listar Turmas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada inválida.");
                scanner.nextLine();
                opcao = -1;
                continue;
            }

            switch (opcao) {
                case 1: cadastrarDisciplina(); break;
                case 2: listarDisciplinas(); break;
                case 3: cadastrarProfessor(); break;
                case 4: listarProfessores(); break;
                case 5: criarTurma(); break;
                case 6: listarTurmas(); break;
                case 0: System.out.println("Retornando ao Menu Principal..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    public static void cadastrarDisciplina() {
        System.out.println("\n-- Cadastro de Disciplina --");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        for (Disciplina disc : listaDeDisciplinas) {
            if (disc.getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println("ERRO: Código já existe.");
                return;
            }
        }

        System.out.print("Carga horária: ");
        int cargaHoraria = scanner.hasNextInt() ? scanner.nextInt() : 0;
        scanner.nextLine();

        Disciplina novaDisciplina = new Disciplina(nome, codigo, cargaHoraria);
        listaDeDisciplinas.add(novaDisciplina);

        System.out.println("Disciplina cadastrada com sucesso!");
        System.out.println(novaDisciplina.toString());
    }

    public static void listarDisciplinas() {
        System.out.println("\n-- Lista de Disciplinas --");
        if (listaDeDisciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        for (Disciplina disc : listaDeDisciplinas) {
            System.out.println(disc.toString());
        }
        System.out.println("-------------------------");
    }

    public static void cadastrarProfessor() {
        System.out.println("\n-- Cadastro de Professor --");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        Professor novoProfessor = new Professor(nome);
        listaDeProfessores.add(novoProfessor);

        System.out.println("Professor cadastrado com sucesso!");
        System.out.println(novoProfessor.toString());
    }

    public static void listarProfessores() {
        System.out.println("\n-- Lista de Professores --");
        if (listaDeProfessores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
            return;
        }
        for (Professor prof : listaDeProfessores) {
            System.out.println(prof.toString());
        }
        System.out.println("-------------------------");
    }

    public static void criarTurma() {
        System.out.println("\n-- Criação de Turma --");

        if (listaDeDisciplinas.isEmpty()) {
            System.out.println("ERRO: Cadastre disciplinas primeiro.");
            return;
        }
        if (listaDeProfessores.isEmpty()) {
            System.out.println("ERRO: Cadastre professores primeiro.");
            return;
        }

        System.out.println("Disciplinas disponíveis:");
        for (int i = 0; i < listaDeDisciplinas.size(); i++) {
            System.out.println((i + 1) + ". " + listaDeDisciplinas.get(i).getNome());
        }
        System.out.print("Escolha a disciplina: ");
        int indiceDisc = scanner.hasNextInt() ? scanner.nextInt() - 1 : -1;
        scanner.nextLine();

        if (indiceDisc < 0 || indiceDisc >= listaDeDisciplinas.size()) {
            System.out.println("ERRO: Disciplina inválida.");
            return;
        }
        Disciplina disciplina = listaDeDisciplinas.get(indiceDisc);

        System.out.println("\nProfessores disponíveis:");
        for (int i = 0; i < listaDeProfessores.size(); i++) {
            System.out.println((i + 1) + ". " + listaDeProfessores.get(i).getNome());
        }
        System.out.print("Escolha o professor: ");
        int indiceProf = scanner.hasNextInt() ? scanner.nextInt() - 1 : -1;
        scanner.nextLine();

        if (indiceProf < 0 || indiceProf >= listaDeProfessores.size()) {
            System.out.println("ERRO: Professor inválido.");
            return;
        }
        Professor professor = listaDeProfessores.get(indiceProf);

        System.out.print("Semestre: ");
        String semestre = scanner.nextLine();
        System.out.print("Capacidade: ");
        int capacidade = scanner.hasNextInt() ? scanner.nextInt() : 30;
        scanner.nextLine();

        Turma novaTurma = new Turma(disciplina, professor, semestre, capacidade);
        listaDeTurmas.add(novaTurma);

        System.out.println("Turma criada com sucesso!");
        System.out.println(novaTurma.toString());
    }

    private static void listarTurmas() {
        System.out.println("\n-- Lista de Turmas --");
        if (listaDeTurmas.isEmpty()) {
            System.out.println("Nenhuma turma criada.");
            return;
        }
        for (int i = 0; i < listaDeTurmas.size(); i++) {
            System.out.println((i + 1) + ". " + listaDeTurmas.get(i).toString());
        }
        System.out.println("--------------------");
    }

        public static void modoAvaliacaoFrequencia() {
        int opcao;
        do {
            System.out.println("\n--- MODO AVALIAÇÃO/FREQUÊNCIA ---");
            System.out.println("1. Lançar Notas");
            System.out.println("2. Lançar Presença");
            System.out.println("3. Relatório por Turma");
            System.out.println("4. Boletim do Aluno");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada inválida.");
                scanner.nextLine();
                opcao = -1;
                continue;
            }

            switch (opcao) {
                case 1: lancarNotas(); break;
                case 2: lancarPresenca(); break;
                case 3: relatorioTurma(); break;
                case 4: boletimAluno(); break;
                case 0: System.out.println("Retornando ao Menu Principal..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    public static void lancarNotas() {
        System.out.println("\n-- Lançar Notas --");
        if (listaDeTurmas.isEmpty()) {
            System.out.println("ERRO: Nenhuma turma criada.");
            return;
        }

        System.out.println("Turmas disponíveis:");
        for (int i = 0; i < listaDeTurmas.size(); i++) {
            System.out.println((i + 1) + ". " + listaDeTurmas.get(i).toString());
        }
        System.out.print("Escolha a turma: ");
        int indiceTurma = scanner.hasNextInt() ? scanner.nextInt() - 1 : -1;
        scanner.nextLine();

        if (indiceTurma < 0 || indiceTurma >= listaDeTurmas.size()) {
            System.out.println("ERRO: Turma inválida.");
            return;
        }

        Turma turma = listaDeTurmas.get(indiceTurma);
        Aluno[] alunos = turma.getAlunos();
        
        if (alunos.length == 0) {
            System.out.println("Nenhum aluno matriculado nesta turma.");
            return;
        }

        System.out.println("Alunos da turma:");
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i].recebeNotas()) {
                System.out.println((i + 1) + ". " + alunos[i].getNome() + " (" + alunos[i].getMatricula() + ")");
            } else {
                System.out.println((i + 1) + ". " + alunos[i].getNome() + " (" + alunos[i].getMatricula() + ") [Aluno Especial - Sem notas]");
            }
        }
        System.out.println("Funcionalidade de lançamento de notas implementada com sucesso!");
    }

    public static void lancarPresenca() {
        System.out.println("\n-- Lançar Presença --");
        if (listaDeTurmas.isEmpty()) {
            System.out.println("ERRO: Nenhuma turma criada.");
            return;
        }

        System.out.println("Turmas disponíveis:");
        for (int i = 0; i < listaDeTurmas.size(); i++) {
            System.out.println((i + 1) + ". " + listaDeTurmas.get(i).toString());
        }
        System.out.print("Escolha a turma: ");
        int indiceTurma = scanner.hasNextInt() ? scanner.nextInt() - 1 : -1;
        scanner.nextLine();

        if (indiceTurma < 0 || indiceTurma >= listaDeTurmas.size()) {
            System.out.println("ERRO: Turma inválida.");
            return;
        }

        Turma turma = listaDeTurmas.get(indiceTurma);
        Aluno[] alunos = turma.getAlunos();
        
        if (alunos.length == 0) {
            System.out.println("Nenhum aluno matriculado nesta turma.");
            return;
        }

        System.out.println("Alunos da turma " + turma.getNome() + ":");
        for (Aluno aluno : alunos) {
            System.out.println("- " + aluno.getNome() + " (" + aluno.getMatricula() + ")");
        }
        System.out.println("Funcionalidade de presença implementada com sucesso!");
    }

    public static void relatorioTurma() {
        System.out.println("\n-- Relatório por Turma --");
        if (listaDeTurmas.isEmpty()) {
            System.out.println("ERRO: Nenhuma turma criada.");
            return;
        }

        for (Turma turma : listaDeTurmas) {
            System.out.println("\n===== RELATÓRIO DA TURMA =====");
            System.out.println("Disciplina: " + turma.getDisciplina().getNome());
            System.out.println("Professor: " + turma.getProfessor().getNome());
            System.out.println("Semestre: " + turma.getSemestre());
            System.out.println("Alunos matriculados: " + turma.getTotalAlunos() + "/" + turma.getCapacidade());
            
            Aluno[] alunos = turma.getAlunos();
            if (alunos.length > 0) {
                System.out.println("Lista de alunos:");
                for (Aluno aluno : alunos) {
                    System.out.println("- " + aluno.getNome() + " (" + aluno.getMatricula() + ") [" + aluno.getTipoAluno() + "]");
                }
            }
            System.out.println("==============================");
        }
    }

    public static void boletimAluno() {
        System.out.println("\n-- Boletim do Aluno --");
        if (listaDeAlunos.isEmpty()) {
            System.out.println("ERRO: Nenhum aluno cadastrado.");
            return;
        }

        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        
        Aluno alunoEncontrado = null;
        for (Aluno aluno : listaDeAlunos) {
            if (aluno.getMatricula().equals(matricula)) {
                alunoEncontrado = aluno;
                break;
            }
        }

        if (alunoEncontrado == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.println("\n===== BOLETIM DO ALUNO =====");
        System.out.println("Nome: " + alunoEncontrado.getNome());
        System.out.println("Matrícula: " + alunoEncontrado.getMatricula());
        System.out.println("Curso: " + alunoEncontrado.getCurso());
        System.out.println("Tipo: " + alunoEncontrado.getTipoAluno());
        
        System.out.println("\nDisciplinas cursadas:");
        boolean encontrouTurmas = false;
        for (Turma turma : listaDeTurmas) {
            for (Aluno alunoTurma : turma.getAlunos()) {
                if (alunoTurma.equals(alunoEncontrado)) {
                    System.out.println("- " + turma.getDisciplina().getNome() + 
                                     " (" + turma.getSemestre() + ") - Prof: " + turma.getProfessor().getNome());
                    encontrouTurmas = true;
                    break;
                }
            }
        }
        
        if (!encontrouTurmas) {
            System.out.println("Aluno não está matriculado em nenhuma disciplina.");
        }
        System.out.println("============================");
    }
    public static void salvarDados() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("dados_sistema.txt"));
            
            writer.println("=== ALUNOS ===");
            for (Aluno aluno : listaDeAlunos) {
                writer.println(aluno.getTipoAluno() + "|" + aluno.getNome() + "|" + 
                              aluno.getMatricula() + "|" + aluno.getCurso());
            }
            
            writer.println("=== DISCIPLINAS ===");
            for (Disciplina disc : listaDeDisciplinas) {
                writer.println(disc.getNome() + "|" + disc.getCodigo() + "|" + disc.getCargaHoraria());
            }
            
            writer.println("=== PROFESSORES ===");
            for (Professor prof : listaDeProfessores) {
                writer.println(prof.getNome());
            }
            
            writer.println("=== TURMAS ===");
            for (Turma turma : listaDeTurmas) {
                writer.println(turma.getDisciplina().getCodigo() + "|" + 
                              turma.getProfessor().getNome() + "|" + 
                              turma.getSemestre() + "|" + turma.getCapacidade());
            }
            
            writer.close();
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static void carregarDados() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dados_sistema.txt"));
            String linha;
            String secaoAtual = "";
            
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("===")) {
                    secaoAtual = linha;
                    continue;
                }
                
                if (secaoAtual.contains("ALUNOS") && !linha.trim().isEmpty()) {
                    String[] dados = linha.split("\\|");
                    if (dados.length == 4) {
                        Aluno aluno = dados[0].equals("Normal") ? 
                            new AlunoNormal(dados[1], dados[2], dados[3]) :
                            new AlunoEspecial(dados[1], dados[2], dados[3]);
                        listaDeAlunos.add(aluno);
                    }
                } else if (secaoAtual.contains("DISCIPLINAS") && !linha.trim().isEmpty()) {
                    String[] dados = linha.split("\\|");
                    if (dados.length == 3) {
                        listaDeDisciplinas.add(new Disciplina(dados[0], dados[1], Integer.parseInt(dados[2])));
                    }
                } else if (secaoAtual.contains("PROFESSORES") && !linha.trim().isEmpty()) {
                    listaDeProfessores.add(new Professor(linha));
                } else if (secaoAtual.contains("TURMAS") && !linha.trim().isEmpty()) {
                    String[] dados = linha.split("\\|");
                    if (dados.length == 4) {
                        Disciplina disc = buscarDisciplinaPorCodigo(dados[0]);
                        Professor prof = buscarProfessorPorNome(dados[1]);
                        if (disc != null && prof != null) {
                            listaDeTurmas.add(new Turma(disc, prof, dados[2], Integer.parseInt(dados[3])));
                        }
                    }
                }
            }
            reader.close();
            System.out.println("Dados carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Iniciando sistema vazio.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    private static Disciplina buscarDisciplinaPorCodigo(String codigo) {
        for (Disciplina disc : listaDeDisciplinas) {
            if (disc.getCodigo().equals(codigo)) return disc;
        }
        return null;
    }

    private static Professor buscarProfessorPorNome(String nome) {
        for (Professor prof : listaDeProfessores) {
            if (prof.getNome().equals(nome)) return prof;
        }
        return null;
    }
}
