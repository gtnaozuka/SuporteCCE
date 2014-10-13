package entity;

import entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-05T20:56:37")
@StaticMetamodel(Email.class)
public class Email_ { 

    public static volatile SingularAttribute<Email, Integer> id;
    public static volatile SingularAttribute<Email, Usuario> destino;
    public static volatile SingularAttribute<Email, String> corpo;
    public static volatile SingularAttribute<Email, String> assunto;

}