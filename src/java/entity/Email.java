package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "email")
@NamedQueries({
    @NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e")})
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "assunto")
    private String assunto;
    @Basic(optional = false)
    @Column(name = "corpo")
    private String corpo;
    @JoinColumn(name = "destino", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Pessoa destino;

    public Email() {
    }

    public Email(Integer id) {
        this.id = id;
    }

    public Email(Integer id, String assunto, String corpo) {
        this.id = id;
        this.assunto = assunto;
        this.corpo = corpo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public Pessoa getDestino() {
        return destino;
    }

    public void setDestino(Pessoa destino) {
        this.destino = destino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entity.Email[ id=" + id + " ]";
    }
}
