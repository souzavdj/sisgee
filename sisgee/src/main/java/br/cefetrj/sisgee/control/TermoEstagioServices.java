package br.cefetrj.sisgee.control;

import java.util.Date;
import java.util.List;

import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.dao.TermoEstagioDAO;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.TermoEstagio;

/**
 * Serviços de TermoEstagio. Trata a lógica de negócios associada com a entidade
 * TermoEstagio.
 *
 * @author Paulo Cantuária, Augusto Jose
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

    public static TermoEstagio buscarTermoEstagio(Integer idTermoEstagio) {
        GenericDAO<TermoEstagio> termoEstagioDao = PersistenceManager.createGenericDAO(TermoEstagio.class);
        return termoEstagioDao.buscar(idTermoEstagio);
    }

    public static List<String> buscarTermosRelatorioConsolidadoCursos(boolean obrigatorio, Date inicio, Date termino) {
        TermoEstagioDAO termoEstagioDAO = new TermoEstagioDAO();
        try {
            List<String> cursos = termoEstagioDAO.buscarTermosRelatorioConsolidadoCursos(obrigatorio, inicio, termino);
            return cursos;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<String> buscarTermosRelatorioConsolidadoCursos(Date inicio, Date termino) {
        TermoEstagioDAO termoEstagioDAO = new TermoEstagioDAO();
        try {
            List<String> cursos = termoEstagioDAO.buscarTermosRelatorioConsolidadoCursos(inicio, termino);
            return cursos;
        } catch (Exception e) {
            return null;
        }
    }

    public static Long buscarQuantidadeDeTermosEstagioParaNomeCurso(String curso) {
        TermoEstagioDAO termoEstagioDAO = new TermoEstagioDAO();
        try {
            Long qtdTermosEstagio = termoEstagioDAO.buscarQuantidadeDeTermosEstagioParaNomeCurso(curso);
            return qtdTermosEstagio;
        } catch (Exception e) {
            return new Long(0);
        }
    }

    public static Long buscarQuantidadeDeTermosEstagioRescindidoParaNomeCurso(String curso) {
        TermoEstagioDAO termoEstagioDAO = new TermoEstagioDAO();
        try {
            Long qtdTermosEstagioRescindido = termoEstagioDAO.buscarQuantidadeDeTermosEstagioRescindidoParaNomeCurso(curso);
            return qtdTermosEstagioRescindido;
        } catch (Exception e) {
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
            e.printStackTrace();
            PersistenceManager.getTransaction().rollback();
        }

    }

    /**
     * Insere um Termo de Estágio no Banco de Dados.
     *
     * @param termoEstagio O Termo Estagio a ser inserido.
     * @param c O convenio ao qual o Termo Estagio estará ligado.
     * @param a O Agente aluno ao qual o Termo Estagio estará ligado.
     *
     */
    public static void incluirTermoEstagio(TermoEstagio termoEstagio) {
        System.out.println("Antes");
        GenericDAO<TermoEstagio> termoEstagioDao = PersistenceManager.createGenericDAO(TermoEstagio.class);
        PersistenceManager.getTransaction().begin();
        try {
            termoEstagioDao.incluir(termoEstagio);
            PersistenceManager.getTransaction().commit();
            System.out.println("Depois");
            
        } catch (Exception e) {
            System.out.println("Errou");
            e.printStackTrace();
            PersistenceManager.getTransaction().rollback();
        }

    }
}
