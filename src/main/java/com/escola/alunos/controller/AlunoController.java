package com.escola.alunos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.escola.alunos.model.Aluno;
import com.escola.alunos.repository.AlunoRepository;

@Controller
public class AlunoController {

	@Autowired
	private AlunoRepository repository;

	// Vai para a tela de cadastro
	@GetMapping("/aluno/cadastrar")
	public String cadastrar(Model model) {
		model.addAttribute("aluno", new Aluno());
		return "aluno/cadastrar";
	}

	// Listando alunos
	@GetMapping("/aluno/listar")
	public String listar(Model model) {
		List<Aluno> alunos = repository.obterTodosAlunos();
		model.addAttribute("alunos", alunos);
		return "aluno/listar";
	}

	@PostMapping("/aluno/salvar")
	public String salvar(@ModelAttribute Aluno aluno) {
		repository.salvar(aluno);
		return "redirect:/";
	}
}
