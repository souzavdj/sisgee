package br.cefetrj.sisgee.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.apache.log4j.Logger;

/**
 * 
 * @author Paulo Cantuária
 * @since 1.0
 * 
 */
//@Entity
public class Empresa extends Pessoa implements Serializable{
        
	//@Id
	//@GeneratedValue
	private Integer idEmpresa;

	//@Column(length = 14, nullable = false, unique = true)
	private String cnpjEmpresa;

	//@Column(length = 100, nullable = false, unique = true)
	private String nomeEmpresa;
        
        //@Column(nullable = false)
	private boolean isAgenteIntegracao;
        
        //@Column(length = 100)
        private String pessoaContato;

        //@JsonbTransient
	//@OneToMany(mappedBy = "empresa")
	private List<Convenio> convenios;

	
	public Empresa(){}
	
	public Empresa(String cnpjEmpresa, String nomeEmpresa){
		this.cnpjEmpresa = cnpjEmpresa;
		this.nomeEmpresa = nomeEmpresa;

	}
	
	public Empresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getCnpjEmpresa() {
		return cnpjEmpresa;
	}
        
        public String getCnpjEmpresaFormatado() {
            if(cnpjEmpresa != null && cnpjEmpresa.trim().length() > 0){
                StringBuilder cnpjFormatado = new StringBuilder();
                try{
                    cnpjFormatado.append(cnpjEmpresa.charAt(0));
                    cnpjFormatado.append(cnpjEmpresa.charAt(1));
                    cnpjFormatado.append(".");
                    cnpjFormatado.append(cnpjEmpresa.charAt(2));
                    cnpjFormatado.append(cnpjEmpresa.charAt(3));
                    cnpjFormatado.append(cnpjEmpresa.charAt(4));
                    cnpjFormatado.append(".");
                    cnpjFormatado.append(cnpjEmpresa.charAt(5));
                    cnpjFormatado.append(cnpjEmpresa.charAt(6));
                    cnpjFormatado.append(cnpjEmpresa.charAt(7));
                    cnpjFormatado.append("/");
                    cnpjFormatado.append(cnpjEmpresa.charAt(8));
                    cnpjFormatado.append(cnpjEmpresa.charAt(9));
                    cnpjFormatado.append(cnpjEmpresa.charAt(10));
                    cnpjFormatado.append(cnpjEmpresa.charAt(11));
                    cnpjFormatado.append("-");
                    cnpjFormatado.append(cnpjEmpresa.charAt(12));
                    cnpjFormatado.append(cnpjEmpresa.charAt(13));
                }catch(IndexOutOfBoundsException e){
                    Logger lg = Logger.getLogger(Empresa.class);
                    lg.error("CNPJ com menos de 14 caracteres. Cnpj = " + cnpjEmpresa + " empresaId = " + idEmpresa, e);
                    return cnpjEmpresa;
                }
                return cnpjFormatado.toString();
            }else{
                return null;
            }
	}

	public void setCnpjEmpresa(String cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public List<Convenio> getConvenios() {
		return convenios;
	}

	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
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
        
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEmpresa == null) ? 0 : idEmpresa.hashCode());
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
		Empresa other = (Empresa) obj;
		if (idEmpresa == null) {
			if (other.idEmpresa != null)
				return false;
		} else if (!idEmpresa.equals(other.idEmpresa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeEmpresa;
	}

}