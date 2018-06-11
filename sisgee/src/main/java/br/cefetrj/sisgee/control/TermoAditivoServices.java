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
     *
     * Metodo para receber uma matriz de com conteudo do banco
     *
     * @author Marcos Eduardo
     * @param obrigatorio boolean do form para filtrar resultado
     * @param inicio date do form para filtrar resultado
     * @param termino date do form para filtrar resultado
     * @return author matriz com conteúdo obtido do banco ou null
     */
    public static List<Object[]> listarTermoAditivoFiltrado(Boolean obrigatorio, Date inicio, Date termino) {
        TermoAditivoDAO termoAditivoDAO = new TermoAditivoDAO();

        try {
            List<Object[]> author = null;

            if (obrigatorio == null) {
                author = termoAditivoDAO.buscarFiltrado(inicio, termino);
            } else {
                author = termoAditivoDAO.buscarFiltrado(obrigatorio, inicio, termino);
            }
            return author;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void inserirTermoAditivo (TermoEstagio termo) {
        TermoAditivoDAO termoAditivoDAO = new TermoAditivoDAO();
        
        termoAditivoDAO.inserirTermoAditivo(termo);
        
    }
}
