package br.cefetrj.sisgee.model.entity;

import java.io.Serializable;
import java.io.StringReader;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.annotation.JsonbTransient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Paulo Cantuária
 * @since 1.0
 *
 */
@Entity
public class Convenio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer idConvenio;

    @Column(length = 11, nullable = false)
    private String numeroConvenio;

    /*@JsonbTransient
    @ManyToOne()
    @JoinColumn(nullable = false)
    private Empresa empresa;*/
    
    @JsonbTransient
    @OneToMany(mappedBy = "convenio", fetch = FetchType.LAZY)
    private List<TermoEstagio> termoEstagios;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAssinatura;

    @Column(length = 14, nullable = false, unique = true)
    private String cpf_cnpj;

    @Column(length = 100, nullable = false)
    private String nomeConveniado;
    
    @Column(nullable = false)
    private boolean isPessoaJuridica;
    
    @Column(nullable = false)
    private boolean isAgenteIntegracao;

    @Column(length = 100)
    private String pessoaContato;

    @Column(length = 255)
    private String email;

    @Column(length = 15)
    private String telefone;
    
    @Column(nullable = false)
    private boolean eAtivo;

    public Convenio() {
    }

    public Convenio(String numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
    }

    public Convenio(Date dataAssinatura, String cpf_cnpj, String nomeConveniado, boolean isPessoaJuridica, String email, String telefone) {
        this.dataAssinatura = dataAssinatura;
        this.cpf_cnpj = cpf_cnpj;
        this.nomeConveniado = nomeConveniado;
        this.isPessoaJuridica = isPessoaJuridica;
        this.email = email;
        this.telefone = telefone;
        this.eAtivo = true;
    }

    public Convenio(Date dataAssinatura, String cpf_cnpj, String nomeConveniado, boolean isPessoaJuridica, boolean isAgenteIntegracao, String pessoaContato, String email, String telefone) {
        this.dataAssinatura = dataAssinatura;
        this.cpf_cnpj = cpf_cnpj;
        this.nomeConveniado = nomeConveniado;
        this.isPessoaJuridica = isPessoaJuridica;
        this.isAgenteIntegracao = isAgenteIntegracao;
        this.pessoaContato = pessoaContato;
        this.email = email;
        this.telefone = telefone;
        this.eAtivo = true;
    }

    public Integer getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Integer idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getNumeroConvenio() {
        return numeroConvenio;
    }

    public void setNumeroConvenio(String numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
    }

    public List<TermoEstagio> getTermoEstagio() {
        return termoEstagios;
    }

    public void setTermoEstagio(List<TermoEstagio> termoEstagios) {
        this.termoEstagios = termoEstagios;
    }

    public Date getDataAssinatura() {
        return dataAssinatura;
    }

    public void setDataAssinatura(Date dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getNomeConveniado() {
        return nomeConveniado;
    }

    public void setNomeConveniado(String nomeConveniado) {
        this.nomeConveniado = nomeConveniado;
    }

    public boolean getIsPessoaJuridica() {
        return isPessoaJuridica;
    }

    public void setIsPessoaJuridica(boolean isPessoaJuridica) {
        this.isPessoaJuridica = isPessoaJuridica;
    }
    
    public boolean getIsAgenteIntegracao() {
        return isAgenteIntegracao;
    }

    public void setIsAgenteIntegracao(boolean isAgenteIntegracao) {
        this.isAgenteIntegracao = isAgenteIntegracao;
    }

    public String getPessoaContato() {
        return pessoaContato;
    }

    public void setPessoaContato(String pessoaContato) {
        this.pessoaContato = pessoaContato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean getEAtivo() {
        return eAtivo;
    }

    public void setEAtivo(boolean eAtivo) {
        this.eAtivo = eAtivo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idConvenio == null) ? 0 : idConvenio.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Convenio other = (Convenio) obj;
        if (idConvenio == null) {
            if (other.idConvenio != null) {
                return false;
            }
        } else if (!idConvenio.equals(other.idConvenio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return numeroConvenio;
    }

    public JsonObject toJsonObj() {
        Jsonb jsonb = JsonbBuilder.create();
        String result = jsonb.toJson(this);

        JsonReader jsonReader = Json.createReader(new StringReader(result));
        JsonObject jobj = jsonReader.readObject();
        return jobj;
    }

    
}
