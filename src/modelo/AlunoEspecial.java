package modelo;

public class AlunoEspecial extends Aluno { 
    public AlunoEspecial(String nome, String matricula, String curso) {
        super(nome, matricula, curso); 
    }

    @Override
    public String getTipoAluno() {
        return "Especial"; 
    }

    @Override
    public boolean podeMatricularEmNovaDisciplina(int numeroDeDisciplinasAtuais) {
      
        return numeroDeDisciplinasAtuais < 2;
    }

    @Override
    public boolean recebeNotas() {
        return false;
    }
}