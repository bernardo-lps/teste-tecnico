package com.bernardo.demo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bernardo.demo.dto.AlunoDTO;
import com.bernardo.demo.model.Aluno;
import com.bernardo.demo.model.Professor;
import com.bernardo.demo.repository.AlunoRepository;
import com.bernardo.demo.repository.ProfessorRepository;
import java.time.format.DateTimeFormatter;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public List<AlunoDTO> listarAlunoProfessor(Integer professorId){

        Professor professor = professorRepository.findById(professorId)
        .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        List<Aluno> alunoProfessor = alunoRepository.findByProfessor(professor);

        List<AlunoDTO> listaDto = new ArrayList<>();
        for (Aluno a : alunoProfessor) {
            AlunoDTO dto = new AlunoDTO(a.getId(), a.getNomeAluno(), a.getMensalidade(), a.getDataVencimento());
            
            listaDto.add(dto);
        }

        return listaDto;
    }

    public void deletarAluno(Integer id){
        alunoRepository.deleteById(id);
    }

    public void importarAluno(MultipartFile arquivo, Integer professorId){

        Professor professor = professorRepository.findById(professorId)
            .orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: "+professorId));

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream()))){
            String linha;

            while((linha = reader.readLine()) != null){
                if(linha.trim().isEmpty()){
                    continue;
                }

                String[] dados = linha.split("\\|\\|");

                String nomeAluno = dados[0].trim();
                String mensalidadeTexto = dados[1].trim();
                String vencimentoTexto = dados[2].trim();

                Double mensalidade = Double.valueOf(mensalidadeTexto);
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataVencimento = LocalDate.parse
                (vencimentoTexto, formatador);

                Aluno aluno = new Aluno();
                aluno.setNomeAluno(nomeAluno);
                aluno.setMensalidade(mensalidade);
                aluno.setDataVencimento(dataVencimento);
                aluno.setProfessor(professor);

                alunoRepository.save(aluno);
            }

        }catch(Exception e){
            throw new RuntimeException("erro ao processar o arquivo txt: "+e.getMessage());
        }
    }

}
