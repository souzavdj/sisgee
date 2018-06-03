package br.cefetrj.sisgee.model.dao;

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
public class TermoAditivoDAO {

    public List<Object[]> buscarFiltrado(Boolean obrigatorio, Date inicio, Date termino) {

        EntityManagerFactory factory
                = Persistence.createEntityManagerFactory("sisgeePU");
        EntityManager manager = factory.createEntityManager();

        Query query = manager
                .createNativeQuery("select cur.nomecurso, te.idtermoestagio from termoaditivo ta \r\n"
                        + "inner join termoestagio te \r\n"
                        + "on ta.termoestagio_idtermoestagio = te.idtermoestagio  \r\n"
                        + "inner join aluno a \r\n"
                        + "on te.aluno_idaluno = a.idaluno  \r\n"
                        + "inner join curso cur \r\n"
                        + "on a.curso_idcurso = cur.idcurso  \r\n"
                        + "inner join campus c \r\n"
                        + "on cur.campus_idcampus = c.idcampus  \r\n"
                        + "where te.datainiciotermoestagio >= :inicio \r\n"
                        + "and  :termino >= te.datainiciotermoestagio \r\n"
                        + "and te.eestagioobrigatorio = :obrigatorio ");

        query.setParameter("obrigatorio", obrigatorio);
        query.setParameter("inicio", inicio);
        query.setParameter("termino", termino);

        @SuppressWarnings("unchecked")
        List<Object[]> authors = query.getResultList();

        manager.close();
        factory.close();
        return authors;
    }

    public List<Object[]> buscarFiltrado(Date inicio, Date termino) {

        System.out.println("test 56");
        EntityManagerFactory factory
                = Persistence.createEntityManagerFactory("sisgeePU");
        EntityManager manager = factory.createEntityManager();

        Query query = manager
                .createNativeQuery("select cur.nomecurso, te.idtermoestagio from termoaditivo ta \r\n"
                        + "inner join termoestagio te \r\n"
                        + "on ta.termoestagio_idtermoestagio = te.idtermoestagio  \r\n"
                        + "inner join aluno a \r\n"
                        + "on te.aluno_idaluno = a.idaluno  \r\n"
                        + "inner join curso cur \r\n"
                        + "on a.curso_idcurso = cur.idcurso  \r\n"
                        + "inner join campus c \r\n"
                        + "on cur.campus_idcampus = c.idcampus  \r\n"
                        + "where te.datainiciotermoestagio >= :inicio \r\n"
                        + "and  :termino >= te.datainiciotermoestagio");

        query.setParameter("inicio", inicio);
        query.setParameter("termino", termino);

        @SuppressWarnings("unchecked")
        List<Object[]> authors = query.getResultList();

        manager.close();
        factory.close();
        return authors;
    }

    public Long buscarQuantidadeDeTermosAditivosParaNomeCurso(String curso) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sisgeePU");
        EntityManager manager = factory.createEntityManager();
        
        String consulta = "SELECT COUNT(te) FROM TermoEstagio te "
                + "inner join Aluno al on te.aluno.idAluno = al.idAluno "
                + "where al.nomeCurso = "+"'"+curso+"' "
                + "and te.termoEstagioAditivo is not null";
        
        TypedQuery<Long> query = manager.createQuery(consulta, Long.class);
        Long qtdTermosEstagioAditivo = query.getSingleResult();

        manager.close();
        factory.close();
        
        return qtdTermosEstagioAditivo;
    }
}
