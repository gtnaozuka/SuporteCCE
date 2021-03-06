package dao;

import entity.Pessoa;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.JPAUtil;

public class PessoaDAO {

    private final EntityManager em;
    /*private static final String allQuery = "SELECT * FROM pessoa";
     private static final String listByTypeQuery = "SELECT * FROM pessoa WHERE tipo = :tipo;";
     private static final String authenticateQuery = "SELECT p FROM pessoa p WHERE p.matricula_chapa = :matricula_chapa";
     private static final String readByEmailQuery = "SELECT * FROM pessoa WHERE email = :email;";*/

    public PessoaDAO() {
        em = JPAUtil.initConnection();
    }

    public Pessoa save(Pessoa p, boolean isMD5) throws PersistenceException {
        try {
            em.getTransaction().begin();
            if (!isMD5) {
                p.setSenha(stringToMD5(p.getSenha()));
            }
            p = em.merge(p);
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
            em.clear();
            throw ex;
        }
        return p;
    }

    public Pessoa read(Integer id) {
        return em.find(Pessoa.class, id);
    }

    public void delete(Pessoa p) {
        em.getTransaction().begin();
        em.remove(read(p.getId()));
        em.getTransaction().commit();
    }

    public List<Pessoa> all() {
        Query q = em.createNamedQuery("Pessoa.findAll");
        return q.getResultList();
    }

    public List<Pessoa> listByType(Integer type) {
        Query q = em.createNamedQuery("Pessoa.listByTypeQuery");
        q.setParameter("tipo", type);
        return q.getResultList();
    }

    public Pessoa authenticate(Pessoa p) throws SecurityException {
        Query q = em.createNamedQuery("Pessoa.authenticate");
        q.setParameter("matricula_chapa", p.getMatriculaChapa());
        
        Pessoa p_tupla;
        try {
            p_tupla = (Pessoa) q.getSingleResult();
            verifyPassword(p.getSenha(), p_tupla.getSenha());
        } catch (NoResultException ex) {
            throw new SecurityException("Matrícula ou chapa incorreta.");
        }
        return p_tupla;
    }

    public Pessoa readByEmail(String email) throws NoResultException {
        Query q = em.createNamedQuery("Pessoa.readByEmailQuery");
        q.setParameter("email", email);
        return (Pessoa) q.getSingleResult();
    }

    public void verifyPassword(Pessoa p) throws SecurityException {
        Pessoa p_tupla = em.find(Pessoa.class, p.getId());
        verifyPassword(p.getSenha(), p_tupla.getSenha());
    }

    private void verifyPassword(String senha_form, String senha_tupla) throws SecurityException {
        if (!stringToMD5(senha_form).equals(senha_tupla)) {
            throw new SecurityException("Senha incorreta.");
        }
    }

    private String stringToMD5(String senha) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(senha.getBytes());
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
