package entity;

import entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-05T20:56:37")
@StaticMetamodel(Requisicao.class)
public class Requisicao_ { 

    public static volatile SingularAttribute<Requisicao, Integer> id;
    public static volatile SingularAttribute<Requisicao, Integer> estado;
    public static volatile SingularAttribute<Requisicao, Usuario> tecnicoId;
    public static volatile SingularAttribute<Requisicao, Usuario> donoId;

}