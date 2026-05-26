package com.bernardo.demo.dto;

import java.time.LocalDate;

public class AlunoDTO {

    private Integer id;
    private String nomeAluno;
    private double mensalidade;
    private LocalDate dataVencimento;

    public AlunoDTO(Integer id, String nomeAluno, double mensalidade, LocalDate dataVencimento) {
        this.id = id;
        this.nomeAluno = nomeAluno;
        this.mensalidade = mensalidade;
        this.dataVencimento = dataVencimento;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomeAluno() { return nomeAluno; }
    public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }

    public double getMensalidade() { return mensalidade; }
    public void setMensalidade(double mensalidade) { this.mensalidade = mensalidade; }

    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }
}