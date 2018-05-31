package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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
	
	public Convenio buscarByNumeroEmpresa(String numero, Empresa emp){
		return (Convenio) manager.createQuery(
		    "SELECT c FROM Convenio c WHERE c.numeroConvenio LIKE :numero AND c.empresa = :empresa")
		    .setParameter("numero", numero)
		    .setParameter("empresa", emp)
		    .getSingleResult();
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
         * @param nome  nome do conveniado ligado ao objeto convenio
         * @return Um unico Convenio com o nome do conveniado utilizado como parametro.Caso não exista retorna um null.
         */
        public Convenio buscarByNomeConveniado(String nome){
            return (Convenio) manager.createQuery("SELECT c FROM Convenio c WHERE c.nomeConveniado = :nome").setParameter("nome",nome).getSingleResult();
        }
        /**
         * Metodo que faz uma query de busca de um convenio com número específico do banco de dados.
         * @param numero numero do convenio
         * @return Um convenio de um numero especifico ou null caso não seja encontrado.
         */
        public Convenio buscarByNumero(String numero){
            return (Convenio) manager.createQuery("SELECT c FROM Convenio c WHERE c.numeroConvenio = :numero").setParameter("numero",numero).getSingleResult();
        }
}
