package com.bernardo.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bernardo.demo.model.Aluno;
import com.bernardo.demo.model.Professor;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{

    List<Aluno> findByProfessor(Professor professor);
}
