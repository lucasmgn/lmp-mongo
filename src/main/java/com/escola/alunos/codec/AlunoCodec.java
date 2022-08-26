package com.escola.alunos.codec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import com.escola.alunos.model.Aluno;
import com.escola.alunos.model.Curso;
import com.escola.alunos.model.Habilidade;
import com.escola.alunos.model.Nota;

public class AlunoCodec implements CollectibleCodec<Aluno> {

	private Codec<Document> codec;

	public AlunoCodec(Codec<Document> codec) {
		this.codec = codec;
	}

	@Override
	public void encode(BsonWriter writer, Aluno aluno, EncoderContext encoderContext) {
		ObjectId id = aluno.getId();
		String nome = aluno.getNome();
		Date dataNascimento = aluno.getDataNascimento();
		Curso curso = aluno.getCurso();
		List<Habilidade> habilidades = aluno.getHabilidades();
		List<Nota> notas = aluno.getNotas();
		
		Document document = new Document();
		document.put("_id", id);
		document.put("nome", nome);
		document.put("data_nascimento", dataNascimento);
		document.put("curso", new Document("nome", curso.getNome()));
		
		if(habilidades != null) {
			List<Document> habilidadesDocument = new ArrayList<>();
			for (Habilidade habilidade : habilidades) {
				habilidadesDocument.add(new Document("nome", habilidade.getNome())
						.append("nível", habilidade.getNivel()));
			}
			document.put("habilidades", habilidadesDocument);
		}
		
		if(notas != null) {
			List<Double> notasDocument = new ArrayList<>();
			for (Nota nota : notas) {
				notasDocument.add(nota.getValor());
			}
			document.put("notas", notasDocument);
		}

		codec.encode(writer, document, encoderContext);
	}

	@Override
	public Class<Aluno> getEncoderClass() {
		return Aluno.class;
	}

	@Override
	public Aluno decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = codec.decode(reader, decoderContext);

		Aluno aluno = new Aluno();

		aluno.setId(document.getObjectId("_id"));
		aluno.setNome(document.getString("nome"));
		aluno.setDataNascimento(document.getDate("data_nascimento"));
		Document curso = (Document) document.get("curso");
		if (curso != null) {
			String nomeCurso = curso.getString("nome");
			aluno.setCurso(new Curso(nomeCurso));
		}
		
		List<Double> notas = (List<Double>) document.get("notas");
		if(notas != null) {
			List<Nota> notasAluno = new ArrayList<>();
			for (Double nota : notas) {
				notasAluno.add(new Nota(nota));
			}
			aluno.setNotas(notasAluno);
		}
		
		List<Document> habilidades = (List<Document>) document.get("habilidades");
		if(habilidades != null) {
			List<Habilidade> habilidadesAluno = new ArrayList<>();
			for (Document documentHabilidade : habilidades) {
				habilidadesAluno.add(new Habilidade(documentHabilidade.getString("nome"),
						documentHabilidade.getString("nivel")));
			}
			
			aluno.setHabilidades(habilidadesAluno);
		}

		return aluno;
	}

	@Override
	public Aluno generateIdIfAbsentFromDocument(Aluno aluno) {
		return documentHasId(aluno) ? aluno.criarId() : aluno;
	}

	@Override
	public boolean documentHasId(Aluno aluno) {
		return aluno.getId() == null;
	}

	@Override
	public BsonValue getDocumentId(Aluno aluno) {
		if (!documentHasId(aluno)) {
			throw new IllegalStateException("Esse Document não tem id");
		}
		return new BsonString(aluno.getId().toHexString());
	}

}
