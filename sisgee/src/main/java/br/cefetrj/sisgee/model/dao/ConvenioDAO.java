package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
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
	
	public Convenio buscarByNumeroEmpresa(String numero, Empresa emp){
		return (Convenio) manager.createQuery(
		    "SELECT c FROM Convenio c WHERE c.numeroConvenio LIKE :numero AND c.empresa = :empresa")
		    .setParameter("numero", numero)
		    .setParameter("empresa", emp)
		    .getSingleResult();
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
}
