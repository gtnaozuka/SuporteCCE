package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory emf;
    private static final EntityManager em;

    static {
        emf = Persistence.createEntityManagerFactory("SuporteCCEPU");
        em = emf.createEntityManager();
    }

    public static EntityManager initConnection() {
        return em;
    }
}
