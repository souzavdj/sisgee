package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.Convenio;
import java.util.List;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
/**
 * Classe que atua no banco de dados com query especificas com o foco na tabela de convenio  
 * @author Andre
 * @since 27/05/2018
 * 
 */
public class ConvenioDAO extends GenericDAO<Convenio> {
	
	public ConvenioDAO() {
		super(Convenio.class, PersistenceManager.getEntityManager());
	}
        
        public Convenio buscarByCpf_Cnpj(String cpf_cnpj){
		return (Convenio) manager.createQuery(
		    "SELECT c FROM Convenio c WHERE c.cpf_cnpj LIKE :cpf_cnpj")
		    .setParameter("cpf_cnpj", cpf_cnpj)
		    .getSingleResult();
	}
        
        public Integer getMaxIdConvenio () {
            String consulta = "SELECT MAX(c.idConvenio) FROM Convenio c";

            TypedQuery<Integer> query = manager.createQuery(consulta, Integer.class);
            Integer qtdTermosEstagio = query.getSingleResult();
            if(qtdTermosEstagio == null){
                qtdTermosEstagio = 0;
            }
            
            return qtdTermosEstagio;
        }
        
        /**
         * Metodo que faz uma query que busca na tabela do convenio um convenio com um nome de conveniado especifico.
         * @param nome  Nome do conveniado ligado ao objeto convenio.
         * @return Um unico Convenio com o nome do conveniado utilizado como parametro.Caso não exista retorna um null.
         */
        public Convenio buscarByNomeConveniado(String nome){
            return (Convenio) manager.createQuery("SELECT c FROM Convenio c WHERE c.nomeConveniado = :nome").setParameter("nome",nome).getSingleResult();
        }
        /**
         * Metodo que faz uma query de busca de um convenio com número específico do banco de dados.
         * @param numero Numero do convenio.
         * @return Um convenio de um numero especifico ou null caso não seja encontrado.
         */
        public Convenio buscarByNumero(String numero){
            return (Convenio) manager.createQuery("SELECT c FROM Convenio c WHERE c.numeroConvenio = :numero").setParameter("numero",numero).getSingleResult();
        }
        
        /**
         * Metodo que faz uma query que busca na tabela do convenio um convenio com um nome de conveniado parcial.
         * @param nome  parte do nome do conveniado 
         * @return Uma lista de Convenios com parte do nome do conveniado utilizado como parametro.Caso não exista nenhum retorna um null.
         */
        public List<Convenio> buscarByNomeParcial(String nome){
            String consulta = "SELECT c FROM Convenio c WHERE c.nomeConveniado LIKE '%" +nome +"%'";
            return (List<Convenio>) manager.createQuery(consulta,Convenio.class).getResultList();
        }
        
        /**
         * Metodo que faz uma query de busca de um convenio com número específico do banco de dados.
         * @param numero parte numero do convenio
         * @return Uma lista de Convenios com parte do numero do conenio utilizado como parametro.Caso não exista nenhum retorna um null.
         */
        public List<Convenio> buscarByNumeroParcial(String numero){
            String consulta = "SELECT c FROM Convenio c WHERE c.numeroConvenio LIKE '%" +numero +"%'";
            return (List<Convenio>) manager.createQuery(consulta,Convenio.class).getResultList();
        }
        
        /**
         * Metodo que faz uma query de busca de um convenio com o id.
         * @param id id do convenio
         * @return Um convenio de um id especifico ou null caso não seja encontrado.
         */
        public Convenio buscarById(String idconvenio){
            return (Convenio) manager.createQuery("SELECT c FROM Convenio c WHERE c.idconvenio = :idconvenio",Convenio.class).setParameter("idconvenio",idconvenio).getSingleResult();
        }
        
        public Convenio buscarCpf_Cnpj(String cpf_cnpj){
		return (Convenio) manager.createQuery(
		    "SELECT c FROM Convenio c WHERE c.cpf_cnpj = :cpf_cnpj")
		    .setParameter("cpf_cnpj", cpf_cnpj)
		    .getSingleResult();
	}
        
        /**
         * Metodo que faz uma query de busca de todos os convenio dentro de um intervalo de duas datas 
         * @param dataInicio
         * @param dataFim
         * @return lista de convenios dentro do intervalo de datas
         */
        public List<Convenio> buscaVencidos(Date dataInicio,Date dataFim){
            return (List<Convenio>) manager.createQuery(
                        "SELECT c FROM Convenio c WHERE c.dataAssinatura BETWEEN :inicio AND :fim")
                        .setParameter("inicio", dataInicio)
                        .setParameter("fim", dataFim)
                        .getResultList();
            }
        
    /**
         * Metodo que faz uma query de busca de um convenio usando os 6 primeiros números.
         * @param numero Numero do convenio.
         * @return Um convenio de um numero especifico ou null caso não seja encontrado.
         */
        public Convenio buscarBy6Numero(String numero){
            String consulta = "SELECT c FROM Convenio c WHERE c.numeroConvenio LIKE '" +numero +"____'";
            return (Convenio) manager.createQuery(consulta).getSingleResult();
        }
}
