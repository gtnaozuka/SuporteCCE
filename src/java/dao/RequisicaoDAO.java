package dao;

import entity.Requisicao;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

public class RequisicaoDAO {

    private final EntityManager em;
    private static final String allQuery = "SELECT * FROM requisicao";
    private static final String listByStateAndUserQuery = "SELECT * FROM requisicao WHERE estado = :estado AND usuario_id = :usuario_id;";
    private static final String listByStateQuery = "SELECT * FROM requisicao WHERE estado = :estado;";
    private static final String requestsByTechnicalAndTimeQuery = "SELECT * FROM requisicao WHERE tecnico_id = :tecnico_id AND data_criacao BETWEEN :data1 AND :data2;";
    private static final String requestsByTimeQuery = "SELECT * FROM requisicao WHERE data_criacao BETWEEN :data1 AND :data2;";

    public RequisicaoDAO() {
        em = JPAUtil.initConnection();
    }

    public Requisicao save(Requisicao requisicao) {
        return em.merge(requisicao);
    }

    public Requisicao read(Integer id) {
        return em.find(Requisicao.class, id);
    }

    public void delete(Requisicao requisicao) {
        em.remove(read(requisicao.getId()));
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
    
    public List<Requisicao> listByTechnicalAndTime(Integer technicalId, Date data1, Date data2) {
        Query q = em.createQuery(requestsByTechnicalAndTimeQuery);
        q.setParameter("tecnico_id", technicalId);
        q.setParameter("data1", data1);
        q.setParameter("data2", data2);
        return q.getResultList();
    }
    
    public List<Requisicao> listByTime(Date data1, Date data2) {
        Query q = em.createQuery(requestsByTimeQuery);
        q.setParameter("data1", data1);
        q.setParameter("data2", data2);
        return q.getResultList();
    }
}
