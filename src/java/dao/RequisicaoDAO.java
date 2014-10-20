package dao;

import entity.Requisicao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

public class RequisicaoDAO {
    
    private final EntityManager em;
    private static final String allQuery = "SELECT * FROM requisicao";
    private static final String listByStateAndUserQuery = "SELECT * FROM requisicao WHERE estado = :estado AND usuario_id = :usuario_id;";
    private static final String listByStateQuery = "SELECT * FROM requisicao WHERE estado = :estado;";
    
    public RequisicaoDAO() {
        em = JPAUtil.initConnection();
    }
    
    public Requisicao save(Requisicao requisicao) {
        //em.getTransaction().begin();
        return em.merge(requisicao);
        //em.getTransaction().commit();
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
    
    public List<Requisicao> listByStateAndUser(Integer state, Integer usuario_id) {
        Query q = em.createQuery(listByStateAndUserQuery);
        q.setParameter("estado", state);
        q.setParameter("usuario_id", usuario_id);
        return q.getResultList();
    }
    
    public List<Requisicao> listByState(Integer state) {
        Query q = em.createQuery(listByStateQuery);
        q.setParameter("estado", state);
        return q.getResultList();
    }
}
