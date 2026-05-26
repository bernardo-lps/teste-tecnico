package com.bernardo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bernardo.demo.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer>{
}
