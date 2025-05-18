package modelo;

import java.util.ArrayList; 
import java.util.List;    

public abstract class Aluno {
    private String nome;
    private String matricula;
    private String curso;
  

    public Aluno(String nome, String matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }
//1
    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Matrícula: " + matricula + ", Nome: " + nome + ", Curso: " + curso;
    }

    public abstract String getTipoAluno();

    public abstract boolean podeMatricularEmNovaDisciplina(int numeroDeDisciplinasAtuais);
    public abstract boolean recebeNotas();

}