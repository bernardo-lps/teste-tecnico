package com.bernardo.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bernardo.demo.dto.ProfessorRequestDTO;
import com.bernardo.demo.dto.ProfessorResponseDTO;
import com.bernardo.demo.model.Professor;
import com.bernardo.demo.repository.ProfessorRepository;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<ProfessorResponseDTO> listarProfessores(){

        List<Professor> lista = professorRepository.findAll();

        List<ProfessorResponseDTO> listaDtos = new ArrayList<>();

        for (Professor p : lista) {
            ProfessorResponseDTO dto = new ProfessorResponseDTO(p.getId(),p.getNomeProfessor(),p.getDisciplina());

            listaDtos.add(dto);
        
        }

        return listaDtos;

    }

    public ProfessorResponseDTO cadastrarProfessor(ProfessorRequestDTO dto){
        Professor p = new Professor();
        p.setNomeProfessor(dto.getNomeProfessor());
        p.setDisciplina(dto.getDisciplina());

        professorRepository.save(p);

        ProfessorResponseDTO professorResponseDTO = new ProfessorResponseDTO(p.getId(), p.getNomeProfessor(), p.getDisciplina());

        return professorResponseDTO;
    }
}
