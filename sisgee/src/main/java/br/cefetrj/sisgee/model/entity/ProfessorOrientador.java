package br.cefetrj.sisgee.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entidade que representa um Professor Orientador.
 * @author Joao
 * @since 2.0
 */

@Entity
public class ProfessorOrientador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer idProfessorOrientador;

    @Column(length = 80, nullable = false)
    private String nomeProfessorOrientador;

    @OneToMany(mappedBy = "professorOrientador")
    private List<TermoEstagio> termoEstagios;

      
    /**
     * Construtor da classe sem parametro
     */
    public ProfessorOrientador() {
    }

     /**
     * Construtor da classe com 1 parametro, o id do professor orientador
     * 
     * @param idProfessorOrientador Ineteger com o id do  professor orientador
     */
    public ProfessorOrientador(Integer idProfessorOrientador) {
        this.idProfessorOrientador = idProfessorOrientador;
    }

     /**
     * Recupera o id do professor orientador 
     * @return Integer com o id do professor orientador 
     */
    public Integer getIdProfessorOrientador() {
        return idProfessorOrientador;
    }

    /**
     * Alterar o id do professor orientador 
     * @param idProfessorOrientador Integer com o id do professor orientador 
     */
    public void setIdProfessorOrientador(Integer idProfessorOrientador) {
        this.idProfessorOrientador = idProfessorOrientador;
    }

     /**
     * Recupera o nome do professor orientador 
     * @return String com o nome do professor orientador 
     */
    public String getNomeProfessorOrientador() {
        return nomeProfessorOrientador;
    }

     /**
     * Alterar o nome do professor orientador 
     * @param nomeProfessorOrientador String com o nome do professor orientador 
     */
    public void setNomeProfessorOrientador(String nomeProfessorOrientador) {
        this.nomeProfessorOrientador = nomeProfessorOrientador;
    }

     /**
     * Recupera os termos de estágio nos quais o professor orientador está associado
     * @return lista com os termos de estágio 
     */
    public List<TermoEstagio> getTermoEstagios() {
        return termoEstagios;
    }

     /**
     * Alterar os termos de estágio os quais o professor orientador está associado
     * @param termoEstagios lista com os termos de estágio 
     */
    public void setTermoEstagios(List<TermoEstagio> termoEstagios) {
        this.termoEstagios = termoEstagios;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idProfessorOrientador == null) ? 0 : idProfessorOrientador.hashCode());
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
        ProfessorOrientador other = (ProfessorOrientador) obj;
        if (idProfessorOrientador == null) {
            if (other.idProfessorOrientador != null) {
                return false;
            }
        } else if (!idProfessorOrientador.equals(other.idProfessorOrientador)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomeProfessorOrientador;
    }
}
