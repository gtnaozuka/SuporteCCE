package entity;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.listByTypeQuery", query = "SELECT p FROM Pessoa p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Pessoa.authenticate", query = "SELECT p FROM Pessoa p WHERE p.matriculaChapa = :matricula_chapa"),
    @NamedQuery(name = "Pessoa.readByEmailQuery", query = "SELECT p FROM Pessoa p WHERE p.email = :email")})
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "matricula_chapa")
    private String matriculaChapa;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @Column(name = "nome")
    @Expose
    private String nome;
    @Column(name = "departamento")
    private String departamento;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    @OneToMany(mappedBy = "tecnicoId")
    private List<Requisicao> requisicaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioId")
    private List<Requisicao> requisicaoList1;

    public Pessoa() {
    }

    public Pessoa(Integer id) {
        this.id = id;
    }

    public Pessoa(Integer id, String matriculaChapa, String senha, String nome, String email, int tipo) {
        this.id = id;
        this.matriculaChapa = matriculaChapa;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatriculaChapa() {
        return matriculaChapa;
    }

    public void setMatriculaChapa(String matriculaChapa) {
        this.matriculaChapa = matriculaChapa;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public List<Requisicao> getRequisicaoList() {
        return requisicaoList;
    }

    public void setRequisicaoList(List<Requisicao> requisicaoList) {
        this.requisicaoList = requisicaoList;
    }

    public List<Requisicao> getRequisicaoList1() {
        return requisicaoList1;
    }

    public void setRequisicaoList1(List<Requisicao> requisicaoList1) {
        this.requisicaoList1 = requisicaoList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entity.Pessoa[ id=" + id + " ]";
    }
    
}
