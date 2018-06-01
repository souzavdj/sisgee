package br.cefetrj.sisgee.control;

import java.util.List;

import br.cefetrj.sisgee.model.dao.ConvenioDAO;
import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import java.util.Date;

/**
 * Serviços de Convenios. 
 * Trata a lógica de negócios associada com a entidade Convênio.
 * 
 * @author Paulo Cantuária
 * @since 1.0
 */
public class ConvenioServices {
	
	/**
	 * Recupera todos os convênios e retorna em uma lista.
	 * 
	 * @return lista com todos os alunos
	 */
	public static List<Convenio> listarConvenios(){
		GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
		return convenioDao.buscarTodos();
	}
	/**
         * Serviço que busca um convenio atraves do seu id no banco de dados
         * @param convenio
         * @return o objeto convenio encontrado ou null caso não encontre.
         */
	public static Convenio buscarConvenio(Convenio convenio) {
		GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
		return convenioDao.buscar(convenio.getIdConvenio());
	}
	/**
         * Serviço que inclui no banco de dados um convenio 
         * @param convenio convenio que será incluido
         */
	public static void incluirConvenio(Convenio convenio){
		GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);		
		PersistenceManager.getTransaction().begin();
		try{
			convenioDao.incluir(convenio);
			PersistenceManager.getTransaction().commit();
		}catch(Exception e){
			PersistenceManager.getTransaction().rollback();
		}
	}
	public static Convenio buscarConvenioByNumeroEmpresa(String numero, Empresa emp) {
		ConvenioDAO convenioDao = new ConvenioDAO();
		try{
			Convenio c = convenioDao.buscarByNumeroEmpresa(numero, emp);
			return c;
		}catch(Exception e){
			return null;
		}
	}
        /**
         * Serviço que busca no banco de dados um convenio atraves de seu número.
         * @param numero convenio
         * @return Um convenio de um numero especifico ou null caso não seja encontrado. 
         */
        public static Convenio buscarConvenioByNumero(String numero) {
		ConvenioDAO convenioDao = new ConvenioDAO();
		try{
			Convenio c = convenioDao.buscarByNumero(numero);
			return c;
		}catch(Exception e){
			return null;
		}
	}
        /**
         * Serviço que busca um Convenio com uma razão social ou o nome de pessoa fisica especifico no banco de dados
         * @param nomeConveniado Representa o nome da pessoa fisica ou a razão social da pessoa juridica. 
         * @return Um unico Convenio com o nome do conveniado utilizado como parametro.Caso não exista retorna um null.
         */
        public static Convenio buscarConvenioByNomeConveniado(String nomeConveniado){
            ConvenioDAO convenioDao = new ConvenioDAO();
            try{
                Convenio c= convenioDao.buscarByNomeConveniado(nomeConveniado);
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
