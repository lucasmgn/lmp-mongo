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
import com.escola.alunos.repository.AlunoRepository;
import com.escola.alunos.service.GeolocalizacaoService;

@Controller
public class AlunoController {

	@Autowired
	private AlunoRepository repository;
	
	@Autowired
	private GeolocalizacaoService service;

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
	
	@GetMapping("/aluno/visualizar/{id}")
	public String visualizar(@PathVariable String id, Model model) {
		Aluno aluno = repository.obterAlunoId(id);
		model.addAttribute("aluno",aluno);
		return "aluno/visualizar";
	}
	
	@GetMapping("/aluno/pesquisarnome")
	public String pesquisarNome(Model model) {
		return "aluno/pesquisarnome";
	}
	
	@GetMapping("/aluno/pesquisar")
	public String pesquisar(@RequestParam("nome") String nome, Model model) {
		List<Aluno> alunos = repository.obterAlunoNome(nome);
		model.addAttribute("alunos", alunos);
		return "aluno/pesquisarnome";
	}


	@PostMapping("/aluno/salvar")
	public String salvar(@ModelAttribute Aluno aluno) {
		try {
			List<Double> lagitudeLong = service.obterLagitudeLong(aluno.getContato());
			aluno.getContato().setCoordinates(lagitudeLong);
			repository.salvar(aluno);
		} catch (Exception e) {
			System.out.println("Endereço não localizado");
			e.printStackTrace();
		}
		return "redirect:/";
	}
}
