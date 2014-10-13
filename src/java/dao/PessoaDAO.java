package dao;

import entity.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

public class PessoaDAO {

    private final EntityManager em;
    private static final String allQuery = "SELECT * FROM pessoa";

    public PessoaDAO() {
        em = JPAUtil.initConnection();
    }

    public void save(Pessoa pessoa) {
        em.getTransaction().begin();
        em.merge(pessoa);
        em.getTransaction().commit();
    }
    
    public Pessoa read(Integer id) {
        return em.find(Pessoa.class, id);
    }

    public void delete(Pessoa pessoa) {
        em.getTransaction().begin();
        em.remove(read(pessoa.getId()));
        em.getTransaction().commit();
    }

    public List<Pessoa> all() {
        Query q = em.createQuery(allQuery);
        return q.getResultList();
    }
}
