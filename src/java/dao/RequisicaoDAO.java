package dao;

import entity.Pessoa;
import entity.Requisicao;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

public class RequisicaoDAO {

    private final EntityManager em;
    /*private static final String allQuery = "SELECT * FROM requisicao";
    private static final String listByStateAndUserQuery = "SELECT * FROM requisicao WHERE estado = :estado AND usuario_id = :usuario_id;";
    private static final String listByStateQuery = "SELECT * FROM requisicao WHERE estado = :estado;";
    private static final String requestsByTechnicalAndTimeQuery = "SELECT * FROM requisicao WHERE tecnico_id = :tecnico_id AND data_criacao BETWEEN :data1 AND :data2;";
    private static final String requestsByTimeQuery = "SELECT * FROM requisicao WHERE data_criacao BETWEEN :data1 AND :data2;";*/

    public RequisicaoDAO() {
        em = JPAUtil.initConnection();
    }

    public Requisicao save(Requisicao requisicao) {
        em.getTransaction().begin();
        Requisicao r = em.merge(requisicao);
        em.getTransaction().commit();
        return r;
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
        Query q = em.createNamedQuery("Requisicao.findAll");
        return q.getResultList();
    }

    public List<Requisicao> listByStateAndUser(Integer state, Pessoa usuario_id) {
        Query q = em.createNamedQuery("Requisicao.listByStateAndUserQuery");
        q.setParameter("estado", state);
        q.setParameter("usuario_id", usuario_id);
        return q.getResultList();
    }

    public List<Requisicao> listByState(Integer state) {
        Query q = em.createNamedQuery("Requisicao.listByStateQuery");
        q.setParameter("estado", state);
        return q.getResultList();
    }
    
    public List<Requisicao> listByTechnicalAndTime(Pessoa technicalId, Date data1, Date data2) {
        Query q = em.createNamedQuery("Requisicao.requestsByTechnicalAndTimeQuery");
        q.setParameter("tecnico_id", technicalId);
        q.setParameter("data1", data1);
        q.setParameter("data2", data2);
        return q.getResultList();
    }
    
    public List<Requisicao> listByTime(Date data1, Date data2) {
        Query q = em.createNamedQuery("Requisicao.requestsByTimeQuery");
        q.setParameter("data1", data1);
        q.setParameter("data2", data2);
        return q.getResultList();
    }
}
