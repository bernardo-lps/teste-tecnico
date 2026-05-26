package com.bernardo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.bernardo.demo.service.AlunoService;

@Controller
@CrossOrigin("*")
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAluno(@PathVariable Integer id){
        alunoService.deletarAluno(id);
    }

    @PostMapping("/importar/{professorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String importarAluno(@RequestParam("arquivo") MultipartFile arquivo, @PathVariable Integer professorId){
        alunoService.importarAluno(arquivo, professorId);
        return "redirect:/" + professorId + "/alunos"; 
    }

}
