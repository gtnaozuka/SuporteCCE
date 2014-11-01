package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Requisicao.class)
public abstract class Requisicao_ {

	public static volatile SingularAttribute<Requisicao, Integer> id;
	public static volatile SingularAttribute<Requisicao, Integer> estado;
	public static volatile SingularAttribute<Requisicao, Pessoa> tecnicoId;
	public static volatile SingularAttribute<Requisicao, String> tipo;
	public static volatile SingularAttribute<Requisicao, Integer> fuel;
	public static volatile SingularAttribute<Requisicao, Date> dataCriacao;
	public static volatile SingularAttribute<Requisicao, Pessoa> usuarioId;
	public static volatile SingularAttribute<Requisicao, String> observacao;
	public static volatile SingularAttribute<Requisicao, String> descricao;
	public static volatile SingularAttribute<Requisicao, String> localizacao;
	public static volatile SingularAttribute<Requisicao, Integer> prioridade;

}

