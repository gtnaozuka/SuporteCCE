package entity;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "requisicao")
@NamedQueries({
    @NamedQuery(name = "Requisicao.findAll", query = "SELECT r FROM Requisicao r"),
    @NamedQuery(name = "Requisicao.listByStateAndUserQuery", query = "SELECT r FROM Requisicao r WHERE r.estado = :estado AND r.usuarioId = :usuario_id ORDER BY r.dataCriacao DESC"),
    @NamedQuery(name = "Requisicao.listByStateQuery", query = "SELECT r FROM Requisicao r WHERE r.estado = :estado ORDER BY r.dataCriacao DESC"),
    @NamedQuery(name = "Requisicao.requestsByTechnicalAndTimeQuery", query = "SELECT r FROM Requisicao r WHERE r.tecnicoId = :tecnico_id AND r.dataCriacao BETWEEN :data1 AND :data2"),
    @NamedQuery(name = "Requisicao.requestsByTimeQuery", query = "SELECT r FROM Requisicao r WHERE r.dataCriacao BETWEEN :data1 AND :data2"),
})
public class Requisicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @Expose
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tipo")
    @Expose
    private String tipo;
    @Basic(optional = false)
    @Column(name = "localizacao")
    @Expose
    private String localizacao;
    @Column(name = "fuel")
    @Expose
    private Integer fuel;
    @Basic(optional = false)
    @Column(name = "descricao")
    @Expose
    private String descricao;
    @Basic(optional = false)
    @Column(name = "estado")
    @Expose
    private int estado;
    @Basic(optional = false)
    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    @Expose
    private Date dataCriacao;
    @Column(name = "prioridade")
    @Expose
    private Integer prioridade;
    @Column(name = "observacao")
    @Expose
    private String observacao;
    @JoinColumn(name = "tecnico_id", referencedColumnName = "id")
    @ManyToOne
    private Pessoa tecnicoId;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Expose
    private Pessoa usuarioId;

    public Requisicao() {
    }

    public Requisicao(Integer id) {
        this.id = id;
    }

    public Requisicao(Integer id, String tipo, String localizacao, String descricao, int estado, Date dataCriacao) {
        this.id = id;
        this.tipo = tipo;
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.estado = estado;
        this.dataCriacao = dataCriacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Integer getFuel() {
        return fuel;
    }

    public void setFuel(Integer fuel) {
        this.fuel = fuel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Pessoa getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(Pessoa tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public Pessoa getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Pessoa usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Requisicao)) {
            return false;
        }
        Requisicao other = (Requisicao) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entity.Requisicao[ id=" + id + " ]";
    }
    
}
