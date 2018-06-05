package br.cefetrj.sisgee.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Implementação do encapsulamento da persistência
 * conforme mostrado nas aulas
 * @author paducantuaria
 * @since 1.0
 */
public class PersistenceManager {
	
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory("sisgeePU");
	private static EntityManager manager = factory.createEntityManager();
	/**
         * Método que fornece um EntityManager.
         * @return Manager.
         */
	static EntityManager getEntityManager(){
		return manager;
	}
	
	/**
         * Método que cria um GenericDAO para uma classe especifica.
         * @param <T> Entidade do GenericDAO.
         * @param t Classe para qual será usado o GenericDAO.
         * @return Um GernericDAO especifico de uma classe.
         */
        
	public static <T> GenericDAO<T> createGenericDAO(Class<T> t) {
		return new GenericDAO<T>(t, manager);
	}
	
        /**
         * Método que fornece uma Transação.
         * @return Transaction.
         */
        
	public static EntityTransaction getTransaction(){
		return manager.getTransaction();
	}

}
