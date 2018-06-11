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

public class TermoEstagioDAO {
    
    
    /**
         * Metodo que retorna os Termos Estagio de Obrigatoriedade especifica em um periodo de tempo.
         * @param obrigatorio Obrigatoriedade dos Termos Estagio.
         * @param inicio Data de inicio do periodo de Tempo.
         * @param termino Data de termino do periodo de Tempo.
         * @return Lista com todos os Termos Estagio de determinada obrigatoriedade ativos em um periodo de tempo.
         */
    public List<Object[]> buscarFiltrado(Boolean obrigatorio, Date inicio, Date termino) {
        EntityManagerFactory factory
                = Persistence.createEntityManagerFactory("sisgeePU");
        EntityManager manager = factory.createEntityManager();

        Query query = manager
                .createNativeQuery("select te.idtermoestagio, cur.nomecurso, te.datarescisaotermoestagio "
                        + "from termoestagio te "
                        + "inner join aluno a "
                        + "on te.aluno_idaluno = a.idaluno "
                        + "inner join curso cur "
                        + "on a.curso_idcurso = cur.idcurso "
                        + "inner join campus c "
                        + "on cur.campus_idcampus = c.idcampus "
                        + "where te.datainiciotermoestagio > :inicio "
                        + "and te.datainiciotermoestagio < :termino "
                        + "and te.eestagioobrigatorio = :obrigatorio");

        query.setParameter("obrigatorio", obrigatorio);
        query.setParameter("inicio", inicio);
        query.setParameter("termino", termino);

        @SuppressWarnings("unchecked")
        List<Object[]> authors = query.getResultList();

        manager.close();
        factory.close();
        return authors;
    }
    
    /**
         * Método que retorna os Termos Estagio ativos em um determinado periodo de tempo.
         * @param inicio Data de inicio do periodo.
         * @param termino Data de termino do periodo.
         * @return  Termos Estagio ativos em um determinado periodo.
         */
    public List<Object[]> buscarFiltrado(Date inicio, Date termino) {
        EntityManagerFactory factory
                = Persistence.createEntityManagerFactory("sisgeePU");
        EntityManager manager = factory.createEntityManager();

        Query query = manager
                .createNativeQuery("select te.idtermoestagio, cur.nomecurso, te.datarescisaotermoestagio "
                        + "from termoestagio te "
                        + "inner join aluno a "
                        + "on te.aluno_idaluno = a.idaluno "
                        + "inner join curso cur "
                        + "on a.curso_idcurso = cur.idcurso "
                        + "inner join campus c "
                        + "on cur.campus_idcampus = c.idcampus "
                        + "where te.datainiciotermoestagio >= :inicio "
                        + "and :termino >= te.datainiciotermoestagio ");

        query.setParameter("inicio", inicio);
        query.setParameter("termino", termino);

        @SuppressWarnings("unchecked")
        List<Object[]> authors = query.getResultList();

        manager.close();
        factory.close();
        return authors;
    }
    
    public List<String> buscarTermosRelatorioConsolidadoCursos(Date inicio, Date termino) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sisgeePU");
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createNativeQuery("select al.nomecurso "
                + "from termoestagio as te "
                + "inner join aluno al on te.aluno_idaluno = al.idaluno "
                + "where te.datainiciotermoestagio >= :inicio "
                + "and :termino >= te.datainiciotermoestagio ");

        query.setParameter("inicio", inicio);
        query.setParameter("termino", termino);

        @SuppressWarnings("unchecked")
        List<String> cursos = query.getResultList();
        
        manager.close();
        factory.close();
        return cursos;
    }
    
    public List<String> buscarTermosRelatorioConsolidadoCursos(boolean obrigatorio, Date inicio, Date termino) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sisgeePU");
        EntityManager manager = factory.createEntityManager();
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
        
        manager.close();
        factory.close();
        return cursos;
    }
    
    public Long buscarQuantidadeDeTermosEstagioParaNomeCurso (String curso) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sisgeePU");
        EntityManager manager = factory.createEntityManager();
        
        String consulta = "SELECT COUNT(te) FROM TermoEstagio te "
                + "inner join Aluno al on te.aluno.idAluno = al.idAluno "
                + "where al.nomeCurso = "+"'"+curso+"'";
        
        TypedQuery<Long> query = manager.createQuery(consulta, Long.class);
        Long qtdTermosEstagio = query.getSingleResult();

        manager.close();
        factory.close();
        
        return qtdTermosEstagio;
    }
    
    public Long buscarQuantidadeDeTermosEstagioRescindidoParaNomeCurso (String curso) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sisgeePU");
        EntityManager manager = factory.createEntityManager();
        
        String consulta = "SELECT COUNT(te) FROM TermoEstagio te "
                + "inner join Aluno al on te.aluno.idAluno = al.idAluno "
                + "where al.nomeCurso = "+"'"+curso+"' "
                + "and te.dataRescisaoTermoEstagio is not null";
        
        TypedQuery<Long> query = manager.createQuery(consulta, Long.class);
        Long qtdTermosEstagioRescindido = query.getSingleResult();

        manager.close();
        factory.close();
        
        return qtdTermosEstagioRescindido;
    }
    
}