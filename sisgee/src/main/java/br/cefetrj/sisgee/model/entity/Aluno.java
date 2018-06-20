package br.cefetrj.sisgee.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Entidade que representa um Aluno.
 * @author Paulo Cantuaria
 * @since 1.0
 *
 */
@Entity
public class Aluno implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Integer idAluno;

    @Column(length = 10, nullable = false, unique = true)
    private String matricula;

    @OneToMany(mappedBy = "aluno")
    private List<TermoEstagio> termoEstagios;

    @Column(length = 100, nullable = false)
    private String nome;
    
    @Column(length = 100, nullable = false)
    private String nomeCampus;
    
    @Column(length = 255, nullable = false)
    private String nomeCurso;

    /**
     * Construtor da classe sem parametro
     */
    public Aluno() {
    }

    /**
     * Construtor da classe com 1 parametro, o id do aluno
     * 
     * @param idAluno Integer com o id do aluno
     */
    public Aluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    /**
     * Recupera o id do aluno 
     * @return Integer com o id do aluno
     */
    public Integer getIdAluno() {
        return idAluno;
    }

     /**
     * Alterar o id do aluno
     * @param idAluno Integer com o id do aluno
     */
    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    /**
     * Recupera a matricula do aluno 
     * @return String com a matricula do aluno
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Alterar a matricula do aluno 
     * @param matricula String com a matricula do aluno
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Recupera os termos de estágio vinculados ao aluno
     * @return lista de TermoEstagios 
     */
    public List<TermoEstagio> getTermoEstagios() {
        return termoEstagios;
    }

    /**
     * Alterar a lista de termos de estagio do aluno
     * @param termoEstagios lista de TermoEstagio 
     */
    public void setTermoEstagios(List<TermoEstagio> termoEstagios) {
        this.termoEstagios = termoEstagios;
    }

    /**
     * Recupera o nome do aluno
     * @return String com o nome do aluno
     */
    public String getNome() {
        return nome;
    }

    /**
     * Alterar o nome do aluno
     * @param nome String com o nome do aluno
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Recupera o nome do campus onde o aluno está matriculado
     * @return String com o nome do campus
     */
    public String getNomeCampus() {
        return nomeCampus;
    }

     /**
     * Alterar o nome do campus onde o aluno está matriculado
     * @param nomeCampus String com o nome do campus
     */
    public void setNomeCampus(String nomeCampus) {
        this.nomeCampus = nomeCampus;
    }
    
    /**
     * Recupera o nome do curso em que o aluno está matriculado
     * @return String com o nome do curso
     */
    public String getNomeCurso() {
        return nomeCurso;
    }

    /**
     * Alterar o nome do curso em que o aluno está matriculado
     * @param nomeCurso String com o nome do curso
     */
    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idAluno == null) ? 0 : idAluno.hashCode());
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
        Aluno other = (Aluno) obj;
        if (idAluno == null) {
            if (other.idAluno != null) {
                return false;
            }
        } else if (!idAluno.equals(other.idAluno)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return matricula;
    }

}
