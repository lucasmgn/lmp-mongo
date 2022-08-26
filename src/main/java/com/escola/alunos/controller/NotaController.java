package com.escola.alunos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.escola.alunos.model.Aluno;
import com.escola.alunos.model.Nota;
import com.escola.alunos.repository.AlunoRepository;

@Controller
public class NotaController {

	@Autowired
	private AlunoRepository repository;		
	
	@GetMapping("nota/cadastrarNota/{id}")
	public String cadastrar(@PathVariable String id, Model model) {
		 Aluno aluno = repository.obterAlunoId(id);
		 model.addAttribute("aluno", aluno);
		 model.addAttribute("notas", new Nota());
		return "nota/cadastrarNota";
	}
	
	@PostMapping("nota/salvar/{id}")
	public String salvar(@PathVariable String id, @ModelAttribute Nota nota) {
		 Aluno aluno = repository.obterAlunoId(id);
		 repository.salvar(aluno.adicionarNota(aluno, nota)); 
		return "redirect:/aluno/listar";
	}
}
