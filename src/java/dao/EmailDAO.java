package dao;

import entity.Email;
import entity.Requisicao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

public class EmailDAO {
    
    private final EntityManager em;
    private static final String allQuery = "SELECT * FROM email";
    
    public EmailDAO() {
        em = JPAUtil.initConnection();
    }
    
    public void save(Email email) {
        em.getTransaction().begin();
        em.merge(email);
        em.getTransaction().commit();
    }
    
    public Email read(Integer id) {
        return em.find(Email.class, id);
    }

    public void delete(Email email) {
        em.getTransaction().begin();
        em.remove(read(email.getId()));
        em.getTransaction().commit();
    }

    public List<Requisicao> all() {
        Query q = em.createQuery(allQuery);
        return q.getResultList();
    }
}
