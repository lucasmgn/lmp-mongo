package com.escola.alunos.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Curso {
	
	public Curso() {}
	
	public Curso(String nome) {
		this.nome = nome;
	}

	private String nome;
}
