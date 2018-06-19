package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.Convenio;
import java.util.List;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
/**
 * Classe que atua no banco de dados com query especificas com o foco na tabela de convenio  
 * @author Andre Alves, Leticia Silva
 * @since 2.0 
 * 
 */
public class ConvenioDAO extends GenericDAO<Convenio> {
	
	public ConvenioDAO() {
		super(Convenio.class, PersistenceManager.getEntityManager());
	}
        
          /**
         * Serviço que busca um Convenio a partir de um cpf ou cnpj
         * @author Andre Alves
         * @param cpf_cnpj Representa o CNPJ ou CPF do conveniado. 
         * @return Um convenio (do tipo Convenio) que for retornado na query
         */
        public Convenio buscarByCpf_Cnpj(String cpf_cnpj){
		return (Convenio) manager.createQuery(
		    "SELECT c FROM Convenio c WHERE c.cpf_cnpj LIKE :cpf_cnpj")
		    .setParameter("cpf_cnpj", cpf_cnpj)
		    .getSingleResult();
	}
        
        /**
        * Recupera o maior id dos convenios e retorna um Integer, referente a ele
        * 
        * @author Leticia Silva
        * @return Integer com o maior id dos convenios.
        */
        public Integer getMaxIdConvenio () {
            String consulta = "SELECT MAX(c.idConvenio) FROM Convenio c";

            TypedQuery<Integer> query = manager.createQuery(consulta, Integer.class);
            Integer idConvenio = query.getSingleResult();
            if(idConvenio == null){
                idConvenio = 0;
            }
            
            return idConvenio;
        }
        
        /**
         * Serviço que busca um convenio a partir de um nome de conveniado especifico.
         * @param nome String nome do conveniado ligado ao objeto convenio.
         * @return Um unico Convenio com o nome do conveniado utilizado como parametro. Caso não exista retorna um null.
         */
        public Convenio buscarByNomeConveniado(String nome){
            return (Convenio) manager.createQuery("SELECT c FROM Convenio c WHERE c.nomeConveniado = :nome").setParameter("nome",nome).getSingleResult();
        }
        /**
         * Serviço que busca um convenio a partir de um número específico.
         * @param numero String numero do convenio.
         * @return Um convenio com o número especificado no parâmetro ou null caso não seja encontrado.
         */
        public Convenio buscarByNumero(String numero){
            return (Convenio) manager.createQuery("SELECT c FROM Convenio c WHERE c.numeroConvenio = :numero").setParameter("numero",numero).getSingleResult();
        }
        
        /**
         * Serviço que busca nos Convenios um convenio com um nome de conveniado parcial.
         * @param nome String, parte do nome do conveniado 
         * @return Uma lista de Convenios com parte do nome do conveniado utilizado como parametro. Caso não exista nenhum retorna um null.
         */
        public List<Convenio> buscarByNomeParcial(String nome){
            String consulta = "SELECT c FROM Convenio c WHERE c.nomeConveniado LIKE '%" +nome +"%'";
            return (List<Convenio>) manager.createQuery(consulta,Convenio.class).getResultList();
        }
        
        /**
         * Serviço que busca os convenios que contenham em seu número parte do número passado como paramentro.
         * @param numero parte numero do convenio
         * @return Uma lista de Convenios. Caso não exista nenhum retorna um null.
         */
        public List<Convenio> buscarByNumeroParcial(String numero){
            String consulta = "SELECT c FROM Convenio c WHERE c.numeroConvenio LIKE '%" +numero +"%'";
            return (List<Convenio>) manager.createQuery(consulta,Convenio.class).getResultList();
        }
        
        /**
         * Serviço que busca um convenio a partir do id passado como parametro.
         * @param idconvenio id do convenio
         * @return Um convenio (do tipo Convenio) com o id especificado ou null caso não seja encontrado.
         */
        public Convenio buscarById(String idconvenio){
            return (Convenio) manager.createQuery("SELECT c FROM Convenio c WHERE c.idconvenio = :idconvenio",Convenio.class).setParameter("idconvenio",idconvenio).getSingleResult();
        }
        
        /**
         * Serviço que busca um convenio a partir do cpf/cnpj do conveniado.
         * @param cpf_cnpj CPF ou CNPJ do conveniado
         * @return Um convenio (do tipo Convenio) de um CPF ou CNPJ especificado ou null caso não seja encontrado.
         */
        public Convenio buscarCpf_Cnpj(String cpf_cnpj){
		return (Convenio) manager.createQuery(
		    "SELECT c FROM Convenio c WHERE c.cpf_cnpj = :cpf_cnpj")
		    .setParameter("cpf_cnpj", cpf_cnpj)
		    .getSingleResult();
	}
        
        /**
         * Serviço que busca todos os convenios que venceram/vão vencer dentro de um intervalo de datas 
         * @param dataInicio Date com a data limite inicial do intervalo
         * @param dataFim Date com a data limite final do intervalo
         * @return lista de convenios vencidos/a vencer dentro do intervalo de datas
         */
        public List<Convenio> buscaVencidos(Date dataInicio,Date dataFim){
            manager.clear();
            return (List<Convenio>) manager.createQuery(
                        "SELECT c FROM Convenio c WHERE c.dataAssinatura BETWEEN :inicio AND :fim")
                        .setParameter("inicio", dataInicio)
                        .setParameter("fim", dataFim)
                        .getResultList();
            }
        
    /**
         * Serviço que busca um convenio a partir de seus 6 primeiros números.
         * @param numero String com os 6 primeiros numeros do convenio.
         * @return Um convenio com o numero especificado ou null caso não seja encontrado.
         */
        public Convenio buscarBy6Numero(String numero){
            String consulta = "SELECT c FROM Convenio c WHERE c.numeroConvenio LIKE '" +numero +"____'";
            return (Convenio) manager.createQuery(consulta).getSingleResult();
        }
}
