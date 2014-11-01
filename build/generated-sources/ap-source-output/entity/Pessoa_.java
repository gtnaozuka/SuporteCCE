package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ {

	public static volatile SingularAttribute<Pessoa, Integer> id;
	public static volatile SingularAttribute<Pessoa, String> email;
	public static volatile ListAttribute<Pessoa, Requisicao> requisicaoList;
	public static volatile SingularAttribute<Pessoa, String> departamento;
	public static volatile SingularAttribute<Pessoa, Integer> tipo;
	public static volatile ListAttribute<Pessoa, Requisicao> requisicaoList1;
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, String> senha;
	public static volatile SingularAttribute<Pessoa, String> matriculaChapa;

}

