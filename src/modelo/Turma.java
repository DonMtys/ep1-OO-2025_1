package modelo;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private Disciplina disciplina;
    private Professor professor;
    private String semestre;
    private int capacidade;
    private List<Aluno> alunos;
    
    public Turma(Disciplina disciplina, Professor professor, String semestre, int capacidade) {
        this.disciplina = disciplina;
        this.professor = professor;
        this.semestre = semestre;
        this.capacidade = capacidade;
        this.alunos = new ArrayList<>();
    }
    
    public Disciplina getDisciplina() { return disciplina; }
    public Professor getProfessor() { return professor; }
    public String getSemestre() { return semestre; }
    public int getCapacidade() { return capacidade; }
    public int getTotalAlunos() { return alunos.size(); }
    public Aluno[] getAlunos() { return alunos.toArray(new Aluno[0]); }
    public String getNome() { return disciplina.getNome() + " - " + semestre; }
    
    public boolean adicionarAluno(Aluno aluno) {
        if (alunos.size() >= capacidade) return false;
        if (alunos.contains(aluno)) return false;
        return alunos.add(aluno);
    }
    
    public boolean removerAluno(Aluno aluno) {
        return alunos.remove(aluno);
    }
    
    @Override
    public String toString() {
        return disciplina.getNome() + " - " + professor.getNome() + 
               " (" + semestre + ") [" + alunos.size() + "/" + capacidade + "]";
    }
}