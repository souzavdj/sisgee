package br.cefetrj.sisgee.control;

import java.util.List;

import br.cefetrj.sisgee.model.dao.ConvenioDAO;
import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.Convenio;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * Serviços de Convenios. Trata a lógica de negócios associada com a entidade
 * Convênio.
 *
 * @author Paulo Cantuária, André Alves
 * @since 1.0
 */
public class ConvenioServices {

    /**
     * Serviço que inclui no banco de dados um convenio
     *
     * @author Paulo Cantuária
     * @param convenio convenio que será incluido
     */
    public static void incluirConvenio(Convenio convenio) {
        GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
        PersistenceManager.getTransaction().begin();
        try {
            convenioDao.incluir(convenio);
            PersistenceManager.getTransaction().commit();
        } catch (Exception e) {
            Logger lg = Logger.getLogger(ConvenioServices.class);
            lg.error("Exception ao tentar incluir Convenio. ", e);
            PersistenceManager.getTransaction().rollback();
        }
    }
    
    /**
    * Recupera o maior id dos convênios e retorna um Integer referente a ele
    * @author André Alve
    * @return Integer com o maior id.
    */
    public static Integer getMaxIdConvenio () {
        ConvenioDAO convenioDAO = new ConvenioDAO();
        try {
            Integer maxIdConvenio = convenioDAO.getMaxIdConvenio();
            return maxIdConvenio;
        } catch (Exception e) {
            Logger lg = Logger.getLogger(ConvenioServices.class);
            lg.error("Exception ao tentar buscar o ID maximo de Convenio. ", e);
            return 0;
        }
    }    
	
	/**
	 * Recupera todos os convênios e retorna em uma lista.
         * @author Paulo Cantuária
	 * @return Lista com todos os alunos.
	 */
	public static List<Convenio> listarConvenios(){
		GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
		return convenioDao.buscarTodos();
	}
	/**
         * Serviço que busca um convenio atraves do seu id no banco de dados
         * 
         * @author Paulo Cantuária
         * @param convenio O Convenio a ser buscado.
         * @return O objeto convenio encontrado ou null caso não encontre.
         */
	public static Convenio buscarConvenio(Convenio convenio) {
		GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
		return convenioDao.buscar(convenio.getIdConvenio());
	}
        
	
        /**
         * Serviço que busca um Convenio com um CNPJ ou CPF do conveniado especifico no banco de dados
         * @author Paulo Cantuária
         * @param cpf_cnpj Representa o CNPJ ou CPF do conveniado. 
         * @return Um unico Convenio com o CNPJ ou CPF utilizado como parametro.Caso não exista retorna um null.
         */
        public static Convenio buscarConvenioByCpf_Cnpj(String cpf_cnpj) {
		ConvenioDAO convenioDao = new ConvenioDAO();
		try{
			Convenio c = convenioDao.buscarByCpf_Cnpj(cpf_cnpj);
			return c;
		}catch(Exception e){
                    Logger lg = Logger.getLogger(ConvenioServices.class);
                    lg.error("Exception ao tentar buscar Convenio por cpf ou cnpj. ", e);
                    return null;
		}
		
	}
    
    /**
     * Serviço que busca no banco de dados um convenio atraves de seu número.
     * 
     *@author Paulo Cantuária
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
            Logger lg = Logger.getLogger(ConvenioServices.class);
            lg.error("Exception ao tentar buscar Convenio pelo numero. ", e);
            return null;
        }
    }

    /**
     * Serviço que busca um Convenio com uma razão social ou o nome de pessoa
     * fisica especifico no banco de dados
     *
     * @author André Alves
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
            Logger lg = Logger.getLogger(ConvenioServices.class);
            lg.error("Exception ao tentar buscar Convenio pelo nome do conveniado. ", e);
            return null;
        }
    }
    
    /**
     * Serviço que busca no banco de dados convenios atraves de parte do seu número.
     *
     * @author Paulo Cantuária
     * @param numero  parte do numero do convenio
     * @return lista de convenios que contém aquele número
     */
    public static List<Convenio> buscarByNumeroParcial(String numero) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        try {
            List<Convenio> c = convenioDao.buscarByNumeroParcial(numero);            
            return c;
        } catch (Exception e) {
            Logger lg = Logger.getLogger(ConvenioServices.class);
            lg.error("Exception ao tentar buscar Convenio pelo numero parcial. ", e);
            return null;
        }
    }
    
    /**
     * Serviço que busca no banco de dados convenios atraves de parte do seu nome.
     * 
     * @author Paulo Cantuária
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
            Logger lg = Logger.getLogger(ConvenioServices.class);
            lg.error("Exception ao tentar buscar Convenio pelo nome parcial. ", e);
            return null;
        }
    }
    
    /**
     * Serviço que busca no banco de dados um convenio atraves de seu id.
     *
     * @author Paulo Cantuária
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
            Logger lg = Logger.getLogger(ConvenioServices.class);
            lg.error("Exception ao tentar buscar Convenio pelo ID. ", e);
            return null;
        }
    }
    
    /**
     * Serviço que inclui no banco de dados um convenio
     *
     * @author Paulo Cantuária
     * @param convenio convenio que será incluido
     */
    public static void alterarConvenio(Convenio convenio) {
        GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
        PersistenceManager.getTransaction().begin();
        try {
            convenioDao.alterar(convenio);
            PersistenceManager.getTransaction().commit();
        } catch (Exception e) {
            Logger lg = Logger.getLogger(ConvenioServices.class);
            lg.error("Exception ao tentar alterar Convenio. ", e);
            PersistenceManager.getTransaction().rollback();
        }
    }
    
    
        /**
         * Serviço que busca um Convenio com um CNPJ ou CPF do conveniado especifico no banco de dados
         * @author Paulo Cantuária
         * @param cpf_cnpj Representa o CNPJ ou CPF do conveniado. 
         * @return Um unico Convenio com o CNPJ ou CPF utilizado como parametro.Caso não exista retorna um null.
         */
        public static Convenio buscarConvenioCpf_Cnpj(String cpf_cnpj) {
		ConvenioDAO convenioDao = new ConvenioDAO();
		try{
			Convenio c = convenioDao.buscarCpf_Cnpj(cpf_cnpj);
			return c;
		}catch(Exception e){
                    Logger lg = Logger.getLogger(ConvenioServices.class);
                    lg.error("Exception ao tentar buscar Convenio pelo cpf ou cnpj. ", e);
                    return null;
		}
	}

     
    /**
    * Serviço que busca um convenio que vence entre as datas especificados no parâmentro.
    * @author André Alves
    * No caso, o que muda é somente o mês e o ano. Os dias são´o primeiro e o útimo da data escolhido. 
    * @param dataInicio Data escolhida como a mínima para o vencimento (primeiro dia do mes)
    * @param dataFim Data escolhida como a máxima para o vencimento (último dia do mes)
    * @return Lista de convenios que satisfaz a busca. Caso não exista retorna um null.
    */
    public static List<Convenio> buscarListaDeVencidos(Date dataInicio,Date dataFim){
        ConvenioDAO convenioDao = new ConvenioDAO();
        try{
            List<Convenio> vencidos= convenioDao.buscaVencidos(dataInicio,dataFim);
            return vencidos;
        }catch(Exception e){
            Logger lg = Logger.getLogger(ConvenioServices.class);
            lg.error("Exception ao tentar buscar Convenios Vencidos. ", e);
            return null;
        }
    }
    
    /**
     * Serviço que busca no banco de dados um convenio atraves dos seus 6 primeiros números.
     *
     * @author André Alves
     * @param numero convenio
     * @return Um convenio de um numero especifico ou null caso não seja
     * encontrado.
     */
    public static Convenio buscarBy6Numero(String numero) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        try {
            Convenio c = convenioDao.buscarBy6Numero(numero);
            return c;
        } catch (Exception e) {
            Logger lg = Logger.getLogger(ConvenioServices.class);
            lg.error("Exception ao tentar buscar Convenio por numeros. ", e);
            return null;
        }
    }

}

