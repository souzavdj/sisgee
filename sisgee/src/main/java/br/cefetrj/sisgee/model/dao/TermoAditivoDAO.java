package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Implementacao do padrao DAO para pesquisa especifica do Termo Aditivo
 *
 * @author Vinicius Souza
 * @since 1.0
 *
 */
public class TermoAditivoDAO  extends GenericDAO<TermoEstagio> {
    
     /**
     * Construtor da classe que chama o super da classe mae, passando os 2 parametro
     */
    public TermoAditivoDAO() {
            super(TermoEstagio.class, PersistenceManager.getEntityManager());
    }
    
    /**Serviço que busca a quantidade de termos aditivos a partir de um determinado curso.
     * 
     * @author Vinicius Souza
     * @param curso nome do curso 
     * @return Long com a quantidade de termos aditivos do curso escohido
     */
    public Long buscarQuantidadeDeTermosAditivosParaNomeCurso(String curso) {
        
        String consulta = "SELECT COUNT(te) FROM TermoEstagio te "
                + "inner join Aluno al on te.aluno.idAluno = al.idAluno "
                + "where al.nomeCurso = "+"'"+curso+"' "
                + "and te.termoEstagioAditivo is not null";
        
        TypedQuery<Long> query = manager.createQuery(consulta, Long.class);
        Long qtdTermosEstagioAditivo = query.getSingleResult();

        return qtdTermosEstagioAditivo;
    }
    
    /**
    * Recupera o maior id dos termos de estágio e retorna um Integer referente a ele
    * 
    * @author Vinicius Souza
    * @return Integer com o maior id.
    */
    public Integer getMaxIdTermoEstagio () {
        String consulta = "SELECT MAX(te.idTermoEstagio) FROM TermoEstagio te";

        TypedQuery<Integer> query = manager.createQuery(consulta, Integer.class);
        Integer idTermosEstagio = query.getSingleResult();
        if(idTermosEstagio == null){
            idTermosEstagio = 0;
        }
        return idTermosEstagio;
    }
}
