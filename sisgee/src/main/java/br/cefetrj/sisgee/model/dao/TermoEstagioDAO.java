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
 * Implementacao do padrao DAO para pesquisa especifica do Termo Estagio
 *
 * @author Marcos Eduardo
 * @since 1.0
 *
 */

public class TermoEstagioDAO extends GenericDAO<TermoEstagio>{
    
    public TermoEstagioDAO() {
            super(TermoEstagio.class, PersistenceManager.getEntityManager());
    }
    
    public List<String> buscarTermosRelatorioConsolidadoCursos(Date inicio, Date termino) {
        Query query = manager.createNativeQuery("select al.nomecurso "
                + "from termoestagio as te "
                + "inner join aluno al on te.aluno_idaluno = al.idaluno "
                + "where te.datainiciotermoestagio >= :inicio "
                + "and :termino >= te.datainiciotermoestagio ");

        query.setParameter("inicio", inicio);
        query.setParameter("termino", termino);

        @SuppressWarnings("unchecked")
        List<String> cursos = query.getResultList();
        
        return cursos;
    }
    
    public List<String> buscarTermosRelatorioConsolidadoCursos(boolean obrigatorio, Date inicio, Date termino) {
        Query query = manager.createNativeQuery("select al.nomecurso "
                + "from termoestagio as te "
                + "inner join aluno al on te.aluno_idaluno = al.idaluno "
                + "where datainiciotermoestagio >= :inicio "
                + "and :termino >= te.datainiciotermoestagio "
                + "and eestagioobrigatorio = :obrigatorio");

        query.setParameter("obrigatorio", obrigatorio);
        query.setParameter("inicio", inicio);
        query.setParameter("termino", termino);

        @SuppressWarnings("unchecked")
        List<String> cursos = query.getResultList();
        
        return cursos;
    }
    
    public Long buscarQuantidadeDeTermosEstagioParaNomeCurso (String curso) {
        
        String consulta = "SELECT COUNT(te) FROM TermoEstagio te "
                + "inner join Aluno al on te.aluno.idAluno = al.idAluno "
                + "where al.nomeCurso = "+"'"+curso+"'"
                + "and te.termoEstagioAditivo is null";
        
        TypedQuery<Long> query = manager.createQuery(consulta, Long.class);
        Long qtdTermosEstagio = query.getSingleResult();

        return qtdTermosEstagio;
    }
    
    public Long buscarQuantidadeDeTermosEstagioRescindidoParaNomeCurso (String curso) {
        
        String consulta = "SELECT COUNT(te) FROM TermoEstagio te "
                + "inner join Aluno al on te.aluno.idAluno = al.idAluno "
                + "where al.nomeCurso = "+"'"+curso+"' "
                + "and te.dataRescisaoTermoEstagio is not null";
        
        TypedQuery<Long> query = manager.createQuery(consulta, Long.class);
        Long qtdTermosEstagioRescindido = query.getSingleResult();

        return qtdTermosEstagioRescindido;
    }
    
}