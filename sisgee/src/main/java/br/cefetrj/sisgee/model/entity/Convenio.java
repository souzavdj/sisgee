package br.cefetrj.sisgee.model.entity;

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
public class Convenio {

    @Id
    @GeneratedValue
    private Integer idConvenio;

    @Column(length = 10, nullable = false)
    private String numeroConvenio;

    /*@JsonbTransient
    @ManyToOne()
    @JoinColumn(nullable = false)
    private Empresa empresa;*/
    
    @JsonbTransient
    @OneToMany(mappedBy = "convenio", fetch = FetchType.LAZY)
    private List <TermoEstagio> termoEstagios;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAssinatura;
    
    @OneToOne()
    private Pessoa conveniado;
    
    public Convenio() {}

    public Convenio(String numeroConvenio) {
            this.numeroConvenio = numeroConvenio;
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
        
        
        
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idConvenio == null) ? 0 : idConvenio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Convenio other = (Convenio) obj;
		if (idConvenio == null) {
			if (other.idConvenio != null)
				return false;
		} else if (!idConvenio.equals(other.idConvenio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return numeroConvenio;
	}
        
        public JsonObject toJsonObj(){
            Jsonb jsonb = JsonbBuilder.create();
            String result = jsonb.toJson(this);
            
            JsonReader jsonReader = Json.createReader(new StringReader(result));
            JsonObject jobj = jsonReader.readObject();
            return jobj;
        }


}
