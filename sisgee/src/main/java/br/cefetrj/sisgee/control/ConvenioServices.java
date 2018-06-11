package br.cefetrj.sisgee.control;

import java.util.List;

import br.cefetrj.sisgee.model.dao.ConvenioDAO;
import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.Convenio;
import java.util.Date;

/**
 * Serviços de Convenios. Trata a lógica de negócios associada com a entidade
 * Convênio.
 *
 * @author Paulo Cantuária
 * @since 1.0
 */
public class ConvenioServices {

    /**
     * Serviço que inclui no banco de dados um convenio
     *
     * @param convenio convenio que será incluido
     */
    public static void incluirConvenio(Convenio convenio) {
        GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
        PersistenceManager.getTransaction().begin();
        try {
            convenioDao.incluir(convenio);
            PersistenceManager.getTransaction().commit();
        } catch (Exception e) {
            PersistenceManager.getTransaction().rollback();
        }
    }
    
    public static Integer getMaxIdConvenio () {
        ConvenioDAO convenioDAO = new ConvenioDAO();
        try {
            Integer maxIdConvenio = convenioDAO.getMaxIdConvenio();
            return maxIdConvenio;
        } catch (Exception e) {
            return 0;
        }
    }    
	
	/**
	 * Recupera todos os convênios e retorna em uma lista.
	 * @return Lista com todos os alunos.
	 */
	public static List<Convenio> listarConvenios(){
		GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
		return convenioDao.buscarTodos();
	}
	/**
         * Serviço que busca um convenio atraves do seu id no banco de dados
         * @param convenio O Convenio a ser buscado.
         * @return O objeto convenio encontrado ou null caso não encontre.
         */
	public static Convenio buscarConvenio(Convenio convenio) {
		GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
		return convenioDao.buscar(convenio.getIdConvenio());
	}
        
	
        /**
         * Serviço que busca um Convenio com um CNPJ ou CPF do conveniado especifico no banco de dados
         * @param cnpj_cpf Representa o CNPJ ou CPF do conveniado. 
         * @return Um unico Convenio com o CNPJ ou CPF utilizado como parametro.Caso não exista retorna um null.
         */
        public static Convenio buscarConvenioByCpf_Cnpj(String cpf_cnpj) {
		ConvenioDAO convenioDao = new ConvenioDAO();
		try{
			Convenio c = convenioDao.buscarByCpf_Cnpj(cpf_cnpj);
			return c;
		}catch(Exception e){
			return null;
		}
		
	}
    
    /**
     * Serviço que busca no banco de dados um convenio atraves de seu número.
     *
     * @param numero convenio
     * @return Um convenio de um numero especifico ou null caso não seja
     * encontrado.
     */
    public static Convenio buscarConvenioByNumero(String numero) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        try {
            Convenio c = convenioDao.buscarByNumero(numero);
            return c;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Serviço que busca um Convenio com uma razão social ou o nome de pessoa
     * fisica especifico no banco de dados
     *
     * @param nomeConveniado Representa o nome da pessoa fisica ou a razão
     * social da pessoa juridica.
     * @return Um unico Convenio com o nome do conveniado utilizado como
     * parametro.Caso não exista retorna um null.
     */
    public static Convenio buscarConvenioByNomeConveniado(String nomeConveniado) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        try {
            Convenio c = convenioDao.buscarByNomeConveniado(nomeConveniado);
            return c;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Serviço que busca no banco de dados convenios atraves de parte do seu número.
     *
     * @param numero  parte do numero do convenio
     * @return lista de convenios que contém aquele número
     */
    public static List<Convenio> buscarByNumeroParcial(String numero) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        try {
            List<Convenio> c = convenioDao.buscarByNumeroParcial(numero);            
            return c;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Serviço que busca no banco de dados convenios atraves de parte do seu nome.
     * 
     * @param nomeConveniado Representa parte do nome da pessoa fisica ou a razão
     * social da pessoa juridica.
     * @return lista de convenios que contém parte deste nome
     * 
     */
    public static List<Convenio> buscarByNomeParcial(String nomeConveniado) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        try {
            List<Convenio> c = convenioDao.buscarByNomeParcial(nomeConveniado);
            return c;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Serviço que busca no banco de dados um convenio atraves de seu id.
     *
     * @param id convenio
     * @return Um convenio de um numero especifico ou null caso não seja
     * encontrado.
     */
    public static Convenio buscarConvenioById(String id) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        try {
            Convenio c = convenioDao.buscarById(id);
            return c;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Serviço que inclui no banco de dados um convenio
     *
     * @param convenio convenio que será incluido
     */
    public static void alterarConvenio(Convenio convenio) {
        GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
        PersistenceManager.getTransaction().begin();
        try {
            convenioDao.alterar(convenio);
            PersistenceManager.getTransaction().commit();
        } catch (Exception e) {
            PersistenceManager.getTransaction().rollback();
        }
    }
    
    
    /**
         * Serviço que busca um Convenio com um CNPJ ou CPF do conveniado especifico no banco de dados
         * @param cnpj_cpf Representa o CNPJ ou CPF do conveniado. 
         * @return Um unico Convenio com o CNPJ ou CPF utilizado como parametro.Caso não exista retorna um null.
         */
        public static Convenio buscarConvenioCpf_Cnpj(String cpf_cnpj) {
		ConvenioDAO convenioDao = new ConvenioDAO();
		try{
			Convenio c = convenioDao.buscarCpf_Cnpj(cpf_cnpj);
			return c;
		}catch(Exception e){
			return null;
		}
	}

    public static List<Convenio> buscarListaDeVencidos(Date dataInicio,Date dataFim){
        ConvenioDAO convenioDao = new ConvenioDAO();
        try{
            List<Convenio> vencidos= convenioDao.buscaVencidos(dataInicio,dataFim);
            return vencidos;
        }catch(Exception e){
            return null;
        }
    }

}

