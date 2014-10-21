package dao;

import entity.Pessoa;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public Pessoa save(Pessoa pessoa, boolean isMD5) {
        if (!isMD5) {
            pessoa.setSenha(stringToMD5(pessoa.getSenha()));
        }
        return em.merge(pessoa);
    }

    public Pessoa read(Integer id) {
        return em.find(Pessoa.class, id);
    }

    public void delete(Pessoa pessoa) {
        em.remove(read(pessoa.getId()));
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

    public Pessoa authenticate(Pessoa pessoa) throws SecurityException {
        Query q = em.createQuery(authenticateQuery);
        q.setParameter("matricula_chapa", pessoa.getMatriculaChapa());
        Pessoa p_tupla = (Pessoa) q.getSingleResult();

        if (p_tupla != null) {
            verifyPassword(pessoa.getSenha(), p_tupla.getSenha());
        } else {
            throw new SecurityException("Matrícula ou chapa incorreta.");
        }

        return p_tupla;
    }

    public void verifyPassword(Pessoa pessoa) throws SecurityException {
        Pessoa p_tupla = em.find(Pessoa.class, pessoa.getId());
        verifyPassword(pessoa.getSenha(), p_tupla.getSenha());
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
