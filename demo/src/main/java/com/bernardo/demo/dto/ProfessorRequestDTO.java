package com.bernardo.demo.dto;

public class ProfessorRequestDTO {

    String nomeProfessor;
    String disciplina;

    public ProfessorRequestDTO(String nomeProfessor, String disciplina) {
        this.nomeProfessor = nomeProfessor;
        this.disciplina = disciplina;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }
    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }
    public String getDisciplina() {
        return disciplina;
    }
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    
}
