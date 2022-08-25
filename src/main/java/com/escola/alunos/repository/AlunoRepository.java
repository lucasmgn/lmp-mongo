package com.escola.alunos.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.escola.alunos.codec.AlunoCodec;
import com.escola.alunos.model.Aluno;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@Repository
public class AlunoRepository {

	private MongoClient cliente;
	private MongoDatabase bancoDeDados;

	public void salvar(Aluno aluno) {

		criarCodec();
		MongoCollection<Aluno> alunos = this.bancoDeDados.getCollection("alunos", Aluno.class);
		alunos.insertOne(aluno);

		cliente.close();
	}

	public List<Aluno> obterTodosAlunos() {

		criarCodec();
		MongoCollection<Aluno> alunos = this.bancoDeDados.getCollection("alunos", Aluno.class);
		MongoCursor<Aluno> iterator = alunos.find().iterator();

		List<Aluno> alunosEncontrados = new ArrayList<>();

		// Criando uma interação enquanto achar resultados
		while (iterator.hasNext()) {
			Aluno aluno = iterator.next();
			alunosEncontrados.add(aluno);
		}
		cliente.close();

		return alunosEncontrados;
	}

	// Buscando aluno por id
	public Aluno obterAlunoId(String id) {
		criarCodec();
		MongoCollection<Aluno> alunos = this.bancoDeDados.getCollection("alunos", Aluno.class);
		Aluno alunoId = alunos.find(Filters.eq("_id", new ObjectId(id))).first();

		return alunoId;
	}

	private void criarCodec() {
		// Registrando um codec
		Codec<Document> codec = MongoClientSettings.getDefaultCodecRegistry().get(Document.class);

		AlunoCodec alunoCodec = new AlunoCodec(codec);

		CodecRegistry registro = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromCodecs(alunoCodec));

		MongoClientSettings build = MongoClientSettings.builder().codecRegistry(registro).build();
		this.cliente = MongoClients.create(build);
		// Se o DB não existirm ele cria um com o nome definido
		this.bancoDeDados = cliente.getDatabase("escola-LMP");
	}
}
