package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.AgenteIntegracao;

public class AgenteIntegracaoDAO extends GenericDAO<AgenteIntegracao> {

	public AgenteIntegracaoDAO() {
		super(AgenteIntegracao.class, PersistenceManager.getEntityManager());
	}
	/**
         * Método que busca um Agente Integração pelo CNPJ.
         * @param cnpj CNPJ do Agente a ser buscado.
         * @return O Agente correspondente ou null.
         */
	public AgenteIntegracao buscarByCnpj(String cnpj){
		return (AgenteIntegracao) manager.createQuery(
		   "SELECT e FROM AgenteIntegracao e WHERE e.cnpjAgenteIntegracao LIKE :cnpj")
		   .setParameter("cnpj", cnpj)
		   .getSingleResult();
	}
	/**
         * Método que busca um Agente pelo nome.
         * @param nome Nome do Agente a ser buscado.
         * @return Agente correspondente ou null.
         */
	public AgenteIntegracao buscarByNome(String nome){
		return (AgenteIntegracao) manager.createQuery(
		   "SELECT e FROM AgenteIntegracao e WHERE e.nomeAgenteIntegracao LIKE :nome")
		   .setParameter("nome", nome)
		   .getSingleResult();
	}

}
