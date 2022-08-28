package com.escola.alunos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("nota/iniciarpesquisa")
	public String iniciarpesquisa() {
		return "nota/pesquisar";
	}
	
	@GetMapping("nota/pesquisar")
	public String pesquisarPorNota(@RequestParam("classificacao") String classificacao,
			@RequestParam("notacorte") String notacorte, Model model) {
		
		List<Aluno> alunos = repository.obterAlunoNota(classificacao, Double.parseDouble(notacorte));
		
		model.addAttribute("alunos", alunos);
		
		return "nota/pesquisar";
	}
	
	@PostMapping("nota/salvar/{id}")
	public String salvar(@PathVariable String id, @ModelAttribute Nota nota) {
		 Aluno aluno = repository.obterAlunoId(id);
		 repository.salvar(aluno.adicionarNota(aluno, nota)); 
		return "redirect:/aluno/listar";
	}
	
}
