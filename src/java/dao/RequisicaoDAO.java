package dao;

import entity.Requisicao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

public class RequisicaoDAO {
    
    private final EntityManager em;
    private static final String allQuery = "SELECT * FROM requisicao";
    
    public RequisicaoDAO() {
        em = JPAUtil.initConnection();
    }
    
    public void save(Requisicao requisicao) {
        em.getTransaction().begin();
        em.merge(requisicao);
        em.getTransaction().commit();
    }
    
    public Requisicao read(Integer id) {
        return em.find(Requisicao.class, id);
    }

    public void delete(Requisicao requisicao) {
        em.getTransaction().begin();
        em.remove(read(requisicao.getId()));
        em.getTransaction().commit();
    }

    public List<Requisicao> all() {
        Query q = em.createQuery(allQuery);
        return q.getResultList();
    }
}
