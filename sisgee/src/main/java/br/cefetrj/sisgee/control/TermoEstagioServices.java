package br.cefetrj.sisgee.control;


import java.util.Date;
import java.util.List;

import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.dao.TermoEstagioDAO;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import org.apache.log4j.Logger;

/**
 * Serviços de TermoEstagio. Trata a lógica de negócios associada com a entidade
 * TermoEstagio.
 *
 * @author Paulo Cantuária, Augusto Jose,  André Alves, Leticia Silva
 * @since 1.0
 */
public class TermoEstagioServices {

    /**
     * Recupera todos os Termos de Estágio e retorna em uma lista.
     *
     * @return lista com todos os Termos de Estágio
     */
    public static List<TermoEstagio> listarTermoEstagio() {
        GenericDAO<TermoEstagio> termoEstagioDao = PersistenceManager.createGenericDAO(TermoEstagio.class);
        return termoEstagioDao.buscarTodos();
    }

    /**
     * Serviço que busca um Termo de Estágio a partir do seu id
     * 
     * @author André Alves
     * @param idTermoEstagio Integer com o id do termo estagio que se quer buscar
     * @return Um termo estagio - TermoEstagio
     */
    public static TermoEstagio buscarTermoEstagio(Integer idTermoEstagio) {
        GenericDAO<TermoEstagio> termoEstagioDao = PersistenceManager.createGenericDAO(TermoEstagio.class);
        return termoEstagioDao.buscar(idTermoEstagio);
    }
    
    
     /**
     * Serviço que busca Termos Relatórios Consolidados dos cursos, a partir de 3 parâmetro definidos pelo usuário
     * e retorna uma lista.
     * 
     * @author André Alves
     * @param obrigatorio boolean informando se é obrigatório ou não 
     * (nesse caso, é verdadeiro, para diferenciar do outro método na quantidade de parâmetros)
     * @param inicio Date com a data inicial do intervalo que se deseja pesquisar
     * @param termino Date com a data final (término) do intervalo que se deseja pesquisar
     * @return lista de String com os cursos retornados na busca
     */
    public static List<String> buscarTermosRelatorioConsolidadoCursos(boolean obrigatorio, Date inicio, Date termino) {
        TermoEstagioDAO termoEstagioDAO = new TermoEstagioDAO();
        try {
            List<String> cursos = termoEstagioDAO.buscarTermosRelatorioConsolidadoCursos(obrigatorio, inicio, termino);
            return cursos;
        } catch (Exception e) {
            Logger lg = Logger.getLogger(TermoEstagioServices.class);
            lg.error("Exception ao tentar buscar Termos Relatorio Consolidado. ", e);
            return null;
        }
    }

    /**
     * Serviço que busca Termos Relatórios Consolidados dos cursos, a partir de 2 parâmetro definidos pelo usuário
     * e retorna uma lista.
     * 
     * @author André Alves
     * @param inicio Date com a data inicial do intervalo que se deseja pesquisar
     * @param termino Date com a data final (término) do intervalo que se deseja pesquisar
     * @return lista de String com os cursos retornados
     */
    public static List<String> buscarTermosRelatorioConsolidadoCursos(Date inicio, Date termino) {
        TermoEstagioDAO termoEstagioDAO = new TermoEstagioDAO();
        try {
            List<String> cursos = termoEstagioDAO.buscarTermosRelatorioConsolidadoCursos(inicio, termino);
            return cursos;
        } catch (Exception e) {
            Logger lg = Logger.getLogger(TermoEstagioServices.class);
            lg.error("Exception ao tentar buscar Termos Relatorio Consolidado. ", e);
            return null;
        }
    }

    /**
     * Serviço que busca a quantidade de termos de estágio de determinado curso, escolhido pelo usuário
     * 
     * @author André Alves
     * @param curso String nome do curso para busca 
     * @return Long com a quantidade de termos de estágio no curso escolhido
     */
    public static Long buscarQuantidadeDeTermosEstagioParaNomeCurso(String curso) {
        TermoEstagioDAO termoEstagioDAO = new TermoEstagioDAO();
        try {
            Long qtdTermosEstagio = termoEstagioDAO.buscarQuantidadeDeTermosEstagioParaNomeCurso(curso);
            return qtdTermosEstagio;
        } catch (Exception e) {
            Logger lg = Logger.getLogger(TermoEstagioServices.class);
            lg.error("Exception ao tentar buscar quantidade de Termos Estagio. ", e);
            return new Long(0);
        }
    }

    /**
     * Serviço que busca a quantidade de Termos Estágios rescindidos, a partir do nome do curso
     * 
     * @author André Alves
     * @param curso String nome do curso para busca 
     * @return Long com a quantidade de termos de estágio rescindidos no curso escolhido
     */
    public static Long buscarQuantidadeDeTermosEstagioRescindidoParaNomeCurso(String curso) {
        TermoEstagioDAO termoEstagioDAO = new TermoEstagioDAO();
        try {
            Long qtdTermosEstagioRescindido = termoEstagioDAO.buscarQuantidadeDeTermosEstagioRescindidoParaNomeCurso(curso);
            return qtdTermosEstagioRescindido;
        } catch (Exception e) {
            Logger lg = Logger.getLogger(TermoEstagioServices.class);
            lg.error("Exception ao tentar buscar quantidade de Termos Estagio Rescindidos. ", e);
            return new Long(0);
        }
    }

    /**
     * Altera as informações de um Termo Estagio.
     *
     * @param termoEstagio O Termo Estagio a ser alterado.
     */
    public static void alterarTermoEstagio(TermoEstagio termoEstagio) {

        GenericDAO<TermoEstagio> termoEstagioDao = PersistenceManager.createGenericDAO(TermoEstagio.class);

        try {
            PersistenceManager.getTransaction().begin();
            termoEstagioDao.alterar(termoEstagio);
            PersistenceManager.getTransaction().commit();
        } catch (Exception e) {
            Logger lg = Logger.getLogger(TermoEstagioServices.class);
            lg.error("Exception ao tentar alterar Termo Estagio. ", e);
            e.printStackTrace();
            PersistenceManager.getTransaction().rollback();
        }

    }

    /**
     * Insere um Termo de Estágio no Banco de Dados.
     * 
     * @author Letícia Silva 
     * @param termoEstagio O Termo Estagio a ser inserido, do tipo TermoEstagio.
     */
    public static void incluirTermoEstagio(TermoEstagio termoEstagio) {
        GenericDAO<TermoEstagio> termoEstagioDao = PersistenceManager.createGenericDAO(TermoEstagio.class);
        PersistenceManager.getTransaction().begin();
        try {
            termoEstagioDao.incluir(termoEstagio);
            PersistenceManager.getTransaction().commit();
            
        } catch (Exception e) {
            Logger lg = Logger.getLogger(TermoEstagioServices.class);
            lg.error("Exception ao tentar icluir Termos Estagio. ", e);
            PersistenceManager.getTransaction().rollback();
        }

    }
    
    public static Integer getMaxIdTermoEstagio () {
        TermoEstagioDAO termoEstagioDAO = new TermoEstagioDAO();
        try {
            Integer maxIdTermoEstagio = termoEstagioDAO.getMaxIdTermoEstagio();
            return maxIdTermoEstagio;
        } catch (Exception e) {
            Logger lg = Logger.getLogger(TermoEstagioServices.class);
            lg.error("Exception ao tentar buscar o ID maximo de Termo Estagio. ", e);
            return 0;
        }
    }
}
