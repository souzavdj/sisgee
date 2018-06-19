package br.cefetrj.sisgee.control;

import java.util.Date;
import java.util.List;

import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.dao.TermoAditivoDAO;
import br.cefetrj.sisgee.model.entity.TermoEstagio;

/**
 * Serviços de TermoAditivo. Trata a lógica de negócios associada com a entidade
 * TermoAditivo.
 *
 * @author Paulo Cantuária
 * @since 1.0
 */
public class TermoAditivoServices {

    public static Long buscarQuantidadeDeTermosAditivosParaNomeCurso(String curso) {
        TermoAditivoDAO termoAditivoDAO = new TermoAditivoDAO();
        try {
            Long qtdTermosEstagioAditivo = termoAditivoDAO.buscarQuantidadeDeTermosAditivosParaNomeCurso(curso);
            return qtdTermosEstagioAditivo;
        } catch (Exception e) {
            return new Long (0);
            
        }
    }
    
    /**
     * Serviço que inclui no banco de dados um termoAditivo
     *
     * @param termoAditivo termoAditivo que será incluido
     */
    public static void incluirTermoAditivo(TermoEstagio termoAditivo) {
        GenericDAO<TermoEstagio> termoAditivoDao = PersistenceManager.createGenericDAO(TermoEstagio.class);
        PersistenceManager.getTransaction().begin();
        try {
            termoAditivoDao.incluir(termoAditivo);
            PersistenceManager.getTransaction().commit();
        } catch (Exception e) {
            PersistenceManager.getTransaction().rollback();
        }
    }
    
    public static Integer getIdMaxTermoEstagio () {
        TermoAditivoDAO termoAditivoDAO = new TermoAditivoDAO();
        try {
            return termoAditivoDAO.getMaxIdTermoEstagio();
        }catch (Exception e) {
            //Log de erro
            return 0;
        } 
    }
}
