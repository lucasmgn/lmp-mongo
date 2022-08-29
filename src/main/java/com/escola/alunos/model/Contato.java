package com.escola.alunos.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contato {

	private String endereco;
	private List<Double> coordinates;
	private String type = "Point";
	
	public Contato() {}
	
	public Contato(String endereco, List<Double> coordinates) {
		this.endereco = endereco;
		this.coordinates= coordinates;
	}
}
