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
 * @author Marcos Eduardo
 * @since 1.0
 *
 */
public class TermoAditivoDAO  extends GenericDAO<TermoEstagio> {
    
    public TermoAditivoDAO() {
            super(TermoEstagio.class, PersistenceManager.getEntityManager());
    }

    public Long buscarQuantidadeDeTermosAditivosParaNomeCurso(String curso) {
        
        String consulta = "SELECT COUNT(te) FROM TermoEstagio te "
                + "inner join Aluno al on te.aluno.idAluno = al.idAluno "
                + "where al.nomeCurso = "+"'"+curso+"' "
                + "and te.termoEstagioAditivo is not null";
        
        TypedQuery<Long> query = manager.createQuery(consulta, Long.class);
        Long qtdTermosEstagioAditivo = query.getSingleResult();

        return qtdTermosEstagioAditivo;
    }
    
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
