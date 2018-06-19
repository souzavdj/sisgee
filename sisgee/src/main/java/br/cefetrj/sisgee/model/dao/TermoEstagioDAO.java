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
 * @author Denis Crispe, Vinicius Souza
 * @since 1.0
 *
 */

public class TermoEstagioDAO extends GenericDAO<TermoEstagio>{
    
     /**
     * Construtor da classe que chama o super da classe mae, passando os 2 parametro
     */
    public TermoEstagioDAO() {
            super(TermoEstagio.class, PersistenceManager.getEntityManager());
    }
    
    
    /**Serviço que busca, a partir de 2 parametros, os nomes dos cursos de termos de relatórios consolidados a partir de um determinado intervalo de datas.
     * 
     * @author Vinicius Souza
     * @param inicio Date com a data limite inicial do intervalo
     * @param termino Date com a data limite final do intervalo
     * @return lista com os nomes dos cursos 
     */
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
    
     /**Serviço que busca, a partir de 3 parametros, os nomes dos cursos de termos de relatórios consolidados a partir de um determinado intervalo de datas.
     * 
     * @author Vinicius Souza
     * @param obrigatorio boolean com a informação se é obrigatorio ou não.
     * @param inicio Date com a data limite inicial do intervalo
     * @param termino Date com a data limite final do intervalo
     * @return lista com os nomes dos cursos 
     */
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
    
    
    /**
     * Serviço que busca a quantidade de termos de estágio de determinado curso
     * 
     * @param curso String nome do curso para a busca 
     * @return Long com a quantidade de termos de estágio no curso escolhido
     */
    public Long buscarQuantidadeDeTermosEstagioParaNomeCurso (String curso) {
        
        String consulta = "SELECT COUNT(te) FROM TermoEstagio te "
                + "inner join Aluno al on te.aluno.idAluno = al.idAluno "
                + "where al.nomeCurso = "+"'"+curso+"'"
                + "and te.termoEstagioAditivo is null";
        
        TypedQuery<Long> query = manager.createQuery(consulta, Long.class);
        Long qtdTermosEstagio = query.getSingleResult();

        return qtdTermosEstagio;
    }
    
    /**
     * Serviço que busca a quantidade de Termos Estágios rescindidos, a partir do nome do curso
     * 
     * @author Denis Crispe
     * @param curso String nome do curso para a busca 
     * @return Long com a quantidade de termos de estágio rescindidos
     */
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