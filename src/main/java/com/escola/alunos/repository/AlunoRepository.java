package com.escola.alunos.repository;

import org.springframework.stereotype.Repository;

import com.escola.alunos.model.Aluno;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Repository
public class AlunoRepository {
	
	public void salvar(Aluno aluno) {
		try(MongoClient cliente = MongoClients.create()){
			 MongoDatabase bancoDeDados = cliente.getDatabase("test");
			 MongoCollection<Aluno> alunos = bancoDeDados.getCollection("alunos", Aluno.class);
			 alunos.insertOne(aluno);
		}
	}
}
