package modelo;

public class AlunoNormal extends Aluno {
    
    public AlunoNormal(String nome, String matricula, String curso) {
        super(nome, matricula, curso);
    }
    
    @Override
    public String getTipoAluno() {
        return "Normal";
    }

    @Override
    public boolean podeMatricularEmNovaDisciplina(int numeroDeDisciplinasAtuais) {
        return true; 
    }

    @Override
    public boolean recebeNotas() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " [Tipo: " + getTipoAluno() + "]";
    }
}