package com.escola.alunos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aluno {
	
	private ObjectId id;
	private String nome;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataNascimento;
	
	private List<Nota> notas;
	private Curso curso;
	private List<Habilidade> habilidades;
	private Contato contato;
	
	public Aluno criarId() {
		setId(new ObjectId());
		return this;
	}
	
	//Adicionar habilidade
	public Aluno adicionarHabilidade(Aluno aluno, Habilidade habilidade) {
		List<Habilidade> habilidadesList = aluno.getHabilidades();
		habilidadesList.add(habilidade);
		aluno.setHabilidades(habilidadesList);
		
		return aluno;
	}
	
	public List<Habilidade> getHabilidades(){
		if(habilidades == null) {
			habilidades = new ArrayList<Habilidade>();
		}
		return habilidades;
	}
	
	public Aluno adicionarNota(Aluno aluno, Nota nota) {
		List<Nota> notasList = aluno.getNotas();
		notasList.add(nota);
		aluno.setNotas(notasList);
		
		return aluno;
	}
	
	public List<Nota> getNotas(){
		if(notas == null) {
			notas = new ArrayList<Nota>();
		}
		return notas;
	}
}
