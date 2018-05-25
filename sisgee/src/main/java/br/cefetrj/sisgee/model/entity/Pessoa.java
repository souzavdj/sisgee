package br.cefetrj.sisgee.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author paducantuaria
 * @since 1.0
 */
//@MappedSuperclass
public class Pessoa {

    //@Id
    //@GeneratedValue
    private Long idPessoa;

    //@Column(length = 255)
    private String email;

    //@Column(length = 15)
    private String telefone;
    
    //@OneToOne(mappedBy = "conveniado")
    private Convenio convenioPessoa;
    
    /*@OneToMany(mappedBy = "pessoa")
	private List<Aluno> alunos;*/

    public Pessoa() {}

    public Pessoa(Convenio convenioPessoa) {
        this.convenioPessoa = convenioPessoa;
    }
    
    public Pessoa(String email, String telefone, Convenio convenioPessoa) {
        this.email = email;
        this.telefone = telefone;
        this.convenioPessoa = convenioPessoa;
    }
    
    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    /*
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}*/

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPessoa == null) ? 0 : idPessoa.hashCode());
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
        Pessoa other = (Pessoa) obj;
        if (idPessoa == null) {
            if (other.idPessoa != null) {
                return false;
            }
        } else if (!idPessoa.equals(other.idPessoa)) {
            return false;
        }
        return true;
    }

}
