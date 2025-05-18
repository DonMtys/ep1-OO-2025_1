package modelo;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private String codigo;
    private int cargaHoraria;
    private List<String> preRequisitos;

    public Disciplina(String nome, String codigo, int cargaHoraria) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.preRequisitos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public List<String> getPreRequisitos() {
        return preRequisitos;
    }

    public void adicionarPreRequisito(String codigoPreRequisito) {
        if (codigoPreRequisito != null && !codigoPreRequisito.trim().isEmpty()) {
            this.preRequisitos.add(codigoPreRequisito.trim());
        }
    }

    @Override
    public String toString() {
        return "Disciplina [Nome: " + nome + ", Código: " + codigo +
               ", Carga Horária: " + cargaHoraria + "h, Pré-requisitos: " + preRequisitos + "]";
    }
}