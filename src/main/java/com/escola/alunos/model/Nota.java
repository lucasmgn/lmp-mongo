package com.escola.alunos.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Nota {
	
	private Double valor;
	
	public Nota(Double valor) {
		this.valor = valor;
	}
	
	public Nota() {}
}
