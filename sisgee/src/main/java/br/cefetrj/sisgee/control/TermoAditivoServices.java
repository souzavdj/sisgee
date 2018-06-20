package br.cefetrj.sisgee.control;

import java.util.Date;
import java.util.List;

import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.dao.TermoAditivoDAO;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import org.apache.log4j.Logger;

/**
 * Serviços de TermoAditivo. Trata a lógica de negócios associada com a entidade
 * TermoAditivo.
 *
 * @author Paulo Cantuária, Vinicius Souza
 * @since 1.0
 */
public class TermoAditivoServices {

     /**Serviço que busca a quantidade de termos aditivos em um determinado curso escolhido.
     * 
     * @author Vinicius Souza
     * @param curso nome do curso 
     * @return long com a quantidade de termos aditivos do curso escohido
     */
    public static Long buscarQuantidadeDeTermosAditivosParaNomeCurso(String curso) {
        TermoAditivoDAO termoAditivoDAO = new TermoAditivoDAO();
        try {
            Long qtdTermosEstagioAditivo = termoAditivoDAO.buscarQuantidadeDeTermosAditivosParaNomeCurso(curso);
            return qtdTermosEstagioAditivo;
        } catch (Exception e) {
            Logger lg = Logger.getLogger(TermoAditivoServices.class);
            lg.error("Exception ao tentar buscar quantidaade de Termos Aditivos pelo nome do Curso. ", e);
            return new Long (0);
            
        }
    }
    
    /**
     * Serviço que inclui no banco de dados um termoAditivo a partir de um DAO genérico
     *
     * @author Paulo Cantuária
     * @param termoAditivo termoAditivo que será incluido
     */
    public static void incluirTermoAditivo(TermoEstagio termoAditivo) {
        GenericDAO<TermoEstagio> termoAditivoDao = PersistenceManager.createGenericDAO(TermoEstagio.class);
        PersistenceManager.getTransaction().begin();
        try {
            termoAditivoDao.incluir(termoAditivo);
            PersistenceManager.getTransaction().commit();
        } catch (Exception e) {
            Logger lg = Logger.getLogger(TermoAditivoServices.class);
            lg.error("Exception ao tentar incluir Termo Aditivo. ", e);
            PersistenceManager.getTransaction().rollback();
        }
    }
    
    /**
    * Recupera o maior id dos termos de estágio e retorna um Integer referente a ele
    * 
    * @author Vinicius Souza
    * @return Integer com o maior id.
    */
    public static Integer getIdMaxTermoEstagio () {
        TermoAditivoDAO termoAditivoDAO = new TermoAditivoDAO();
        try {
            return termoAditivoDAO.getMaxIdTermoEstagio();
        }catch (Exception e) {
            Logger lg = Logger.getLogger(TermoAditivoServices.class);
            lg.error("Exception ao tentar buscar ID maximo de Termo Aditivo. ", e);
            return 0;
        } 
    }
}
