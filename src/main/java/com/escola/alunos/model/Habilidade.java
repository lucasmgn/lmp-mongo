package com.escola.alunos.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Habilidade {

	private String nome;
	private String nivel;
	
	public Habilidade() {}

	public Habilidade(String nome, String nivel) {
		this.nome = nome;
		this.nivel = nivel;
	}
}
