package br.cefetrj.sisgee.model.entity;

import br.cefetrj.sisgee.control.ConvenioServices;
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
import javax.persistence.OneToMany;
import java.util.Date;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidade que representa um Convenio.
 * @author Paulo Cantuária
 * @since 1.0
 *
 */
@Entity
public class Convenio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id 
    //@GeneratedValue
    private Integer idConvenio;

    @Column(length = 10, nullable = false)
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

    
    /**
     * Construtor da classe sem parametro
     */
    public Convenio() {  }
    
     /**
     * Construtor da classe com 1 parametro, o id do convenio
     * 
     * @param id inteiro com o id do convenio
     */
    public Convenio(int id){
        this.idConvenio=id;
    }
    
    /**
     * Construtor da classe com 1 parametro, o numero do convenio
     * 
     * @param numeroConvenio String com o numero do convenio
     */
    public Convenio(String numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
    }

     /**
     * Construtor da classe com 6 parametros.
     * 
     * @param dataAssinatura Datte com a data da assinatura do convenio
     * @param cpf_cnpj String com o cpf/cnpj do conveniado
     * @param nomeConveniado Strinv com o nome do conveniado
     * @param isPessoaJuridica boolean informando se é pessoa juridica
     * @param email String com o email do conveniado
     * @param telefone String com o telefone do conveniado
     */
    public Convenio(Date dataAssinatura, String cpf_cnpj, String nomeConveniado, boolean isPessoaJuridica, String email, String telefone) {
        this.dataAssinatura = dataAssinatura;
        this.cpf_cnpj = cpf_cnpj;
        this.nomeConveniado = nomeConveniado;
        this.isPessoaJuridica = isPessoaJuridica;
        this.email = email;
        this.telefone = telefone;
        this.eAtivo = true;
        this.idConvenio = ConvenioServices.getMaxIdConvenio()+1;
    }

     /**
     * Construtor da classe com 8 parametros.
     * 
     * @param dataAssinatura Datte com a data da assinatura do convenio
     * @param cpf_cnpj String com o cpf/cnpj do conveniado
     * @param nomeConveniado Strinv com o nome do conveniado
     * @param isPessoaJuridica boolean informando se é pessoa juridica
     * @param isAgenteIntegracao boolean informando se é agente de integração
     * @param pessoaContato String com a informação da pessoa de contato
     * @param email String com o email do conveniado
     * @param telefone String com o telefone do conveniado
     */
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
        this.idConvenio = ConvenioServices.getMaxIdConvenio()+1;
    }

     /**
     * Recupera o id do convenio 
     * @return Integer com o id do convenio
     */
    public Integer getIdConvenio() {
        return idConvenio;
    }
    
     /**
     * Alterar o id do convenio
     * @param idConvenio Integer com o id do convenio
     */
    public void setIdConvenio(Integer idConvenio) {
        this.idConvenio = idConvenio;
    }

    /**
     * Recupera o numero do convenio 
     * @return String com o numero do convenio
     */
    public String getNumeroConvenio() {
        return numeroConvenio;
    }

    /**
     * Alterar o numero do convenio 
     * @param numeroConvenio String com o numero do convenio
     */
    public void setNumeroConvenio(String numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
    }

    /**
     * Recupera uma lista com os termos de estagio do conveniado
     * @return lista de TermoEstagio com os termos de estagio
     */
    public List<TermoEstagio> getTermoEstagio() {
        return termoEstagios;
    }

    /**
     * Alterar a lista com os termos de estagio do conveniado
     * @param termoEstagios lista de TermoEstagio com os termos de estagio
     */
    public void setTermoEstagio(List<TermoEstagio> termoEstagios) {
        this.termoEstagios = termoEstagios;
    }

     /**
     * Recupera a data de assinatura do convenio
     * @return Date com a data de assinatura
     */
    public Date getDataAssinatura() {
        return dataAssinatura;
    }

     /**
     * Alterar a data de assinatura do convenio
     * @param dataAssinatura Date com a data de assinatura
     */
    public void setDataAssinatura(Date dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

     /**
     * Recupera o cpf/cnpj do conveniado
     * @return String com o cpf ou cnpj
     */
    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

     /**
     * Alterar o cpf/cnpj do conveniado
     * @param cpf_cnpj String com o cpf ou cnpj
     */
    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

     /**
     * Recupera o nome do conveniado
     * @return String com o nome
     */
    public String getNomeConveniado() {
        return nomeConveniado;
    }

     /**
     * Alterar o nome do conveniado
     * @param nomeConveniado String com o nome
     */
    public void setNomeConveniado(String nomeConveniado) {
        this.nomeConveniado = nomeConveniado;
    }

    
     /**
     * Recupera a informacao que diz se o conveniado é pessoa jurídica ou não
     * @return boolean informando se é pessoa juridica ou nao
     */
    public boolean getIsPessoaJuridica() {
        return isPessoaJuridica;
    }

    /**
     * Alterar a informacao que diz se o conveniado é pessoa jurídica ou não
     * @param isPessoaJuridica boolean informando se é pessoa juridica ou nao
     */
    public void setIsPessoaJuridica(boolean isPessoaJuridica) {
        this.isPessoaJuridica = isPessoaJuridica;
    }
    
    /**
     * Recupera a informacao que diz se o conveniado é agente de integração ou não
     * @return boolean informando se o conveniado é agente de integração ou não
     */
    public boolean getIsAgenteIntegracao() {
        return isAgenteIntegracao;
    }

    /**
     * Alterar a informacao que diz se o conveniado é pessoa jurídica ou não
     * @param isAgenteIntegracao boolean informando o conveniado é pessoa jurídica ou não
     */
    public void setIsAgenteIntegracao(boolean isAgenteIntegracao) {
        this.isAgenteIntegracao = isAgenteIntegracao;
    }

     /**
     * Recupera a informacao da pessoa de contato
     * @return String com a informacao da pessoa de contato
     */
    public String getPessoaContato() {
        return pessoaContato;
    }

     /**
     * Alterar a informacao da pessoa de contato
     * @param pessoaContato String, informacao da pessoa de contato
     */
    public void setPessoaContato(String pessoaContato) {
        this.pessoaContato = pessoaContato;
    }

     /**
     * Recupera o email do conveniado
     * @return String com o email
     */
    public String getEmail() {
        return email;
    }

     /**
     * Alterar o email do conveniado
     * @param email String com o email
     */
    public void setEmail(String email) {
        this.email = email;
    }

     /**
     * Recupera o telefone do conveniado
     * @return String com o telefone
     */
    public String getTelefone() {
        return telefone;
    }

     /**
     * Alterar o telefone do conveniado
     * @param telefone String com o telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Recupera a informação sobre ser ativo ou não
     * @return boolean com a informação
     */
    public boolean getEAtivo() {
        return eAtivo;
    }

    
    /**
     * Alterar a informação sobre ser ativo ou não
     * @param eAtivo boolean com a informação
     */
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

    /**
     * Método que utiliza Json para consulta
     * @return JsonObject com um json a partir de um acesso utilizando JsonReader
     */
    public JsonObject toJsonObj() {
        Jsonb jsonb = JsonbBuilder.create();
        String result = jsonb.toJson(this);

        JsonReader jsonReader = Json.createReader(new StringReader(result));
        JsonObject jobj = jsonReader.readObject();
        return jobj;
    }

    
   
}
