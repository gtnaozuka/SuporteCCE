package dao;

import entity.Pessoa;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

public class PessoaDAO {

    private final EntityManager em;
    private static final String allQuery = "SELECT * FROM pessoa";
    private static final String listByTypeQuery = "SELECT * FROM pessoa WHERE tipo = :tipo;";
    private static final String authenticateQuery = "SELECT * FROM pessoa WHERE matricula_chapa = :matricula_chapa;";

    public PessoaDAO() {
        em = JPAUtil.initConnection();
    }

    public Pessoa save(Pessoa pessoa) {
        //em.getTransaction().begin();
        return em.merge(pessoa);
        //em.getTransaction().commit();
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

    public List<Pessoa> listByType(Integer type) {
        Query q = em.createQuery(listByTypeQuery);
        q.setParameter("tipo", type);
        return q.getResultList();
    }

    public Pessoa authenticate(Pessoa p) throws SecurityException {
        Query q = em.createQuery(authenticateQuery);
        q.setParameter("matricula_chapa", p.getMatriculaChapa());
        Pessoa p_tupla = (Pessoa) q.getSingleResult();

        if (p_tupla != null) {
            MessageDigest md5;
            try {
                md5 = MessageDigest.getInstance("MD5");
                md5.update(p.getSenha().getBytes());
                if (!new BigInteger(1, md5.digest()).toString(16).equals(p_tupla.getSenha())) {
                    throw new SecurityException();
                }
            } catch (NoSuchAlgorithmException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            throw new SecurityException();
        }

        return p_tupla;
    }
}
