package com.escola.alunos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.escola.alunos.model.Aluno;
import com.escola.alunos.repository.AlunoRepository;

@Controller
public class GeolocalizacaoController {
	
	@Autowired
	private AlunoRepository repository;
	
	@GetMapping("/geolocalizacao/iniciarpesquisa")
	public String inicializarPesquisa(Model model) {
		List<Aluno> alunos = repository.obterTodosAlunos();
		
		model.addAttribute("alunos",alunos);
		
		return "geolocalizacao/pesquisar";
	}
	
	@GetMapping("/geolocalizacao/pesquisar")
	public String pesquisa(@RequestParam("alunoId") String alunoId, Model model) {
		Aluno aluno = repository.obterAlunoId(alunoId);
		
		List<Aluno> alunosProximos = repository.pesquisarPorGeolocalizacao(aluno);
				
		model.addAttribute("alunosProximos", alunosProximos);
		
		return "geolocalizacao/pesquisar";
	}
}
