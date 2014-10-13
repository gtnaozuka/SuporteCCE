package entity;

import entity.Email;
import entity.Requisicao;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-05T20:56:37")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, String> email;
    public static volatile ListAttribute<Usuario, Requisicao> requisicaoList;
    public static volatile SingularAttribute<Usuario, Integer> tipo;
    public static volatile SingularAttribute<Usuario, String> nome;
    public static volatile ListAttribute<Usuario, Requisicao> requisicaoList1;
    public static volatile SingularAttribute<Usuario, String> senha;
    public static volatile ListAttribute<Usuario, Email> emailList;

}