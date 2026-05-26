package com.bernardo.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bernardo.demo.dto.ProfessorRequestDTO;
import com.bernardo.demo.repository.ProfessorRepository;
import com.bernardo.demo.service.AlunoService;
import com.bernardo.demo.service.ProfessorService;

@Controller
@CrossOrigin("*")
@RequestMapping("/professor") 
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public String listarProfessores(Model model) {
        model.addAttribute("professores", professorService.listarProfessores());
        return "index";
    }

    @GetMapping("/{professorId}/alunos")
    public String listarAlunoProfessor(@PathVariable Integer professorId, Model model) {
        var professor = professorRepository.findById(professorId)
            .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        model.addAttribute("alunos", alunoService.listarAlunoProfessor(professorId));
        model.addAttribute("professorId", professorId);
        model.addAttribute("nomeProfessor", professor.getNomeProfessor());
        model.addAttribute("disciplina", professor.getDisciplina());
        return "alunos-professor";
    }

    @GetMapping("/novo")
    public String paginaNovoProfessor() {
        return "novo-professor";
    }

    @PostMapping("/cadastro")
    public String cadastrarProfessor(@RequestParam String nomeProfessor,
                                     @RequestParam String disciplina) {
        professorService.cadastrarProfessor(new ProfessorRequestDTO(nomeProfessor, disciplina));
        return "redirect:/professor";
    }
}
