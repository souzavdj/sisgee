package br.cefetrj.sisgee.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

/**
 * Implementação do padrão DAO conforme mostrado nas aulas
 * @author Paulo Cantuária
 * @since 1.0
 *
 * @param <T> Tipo da classe
 */
public class GenericDAO<T> {

	protected EntityManager manager;
	protected Class<T> t;
	
         /**
          * Construtor da classe, com 2 parâmetros 
          * 
          * @param T tipo do objeto
          * @param manager Entity Manager
          */
	GenericDAO(Class<T> t, EntityManager manager){
		this.t = t;
		this.manager = manager;
	}
        
         /**
          * Método que busca todas as entidades de um tipo T.
          * @return Todas as Entidades do tipo T.
          */	
	public List<T> buscarTodos(){
		@SuppressWarnings("unchecked")
		List<T> lista = manager.createQuery("from " + t.getName()).getResultList();
				
		return lista;
	}
	
        /**
         * Método que busca uma Entidade pelo ID.
         * @param id ID a ser pesquisado.
         * @return Entidade com ID respectivo ou null.
         */        
	public T buscar(Integer id){
		return manager.find(t, id);
	}
	
        /**
         * Método que insere uma Entidade no Banco de Dados.
         * @param entidade Entidade a ser inserida.
         */        
	public void incluir(T entidade){
		manager.persist(entidade);
	}
	
        /**
         * Método que remove uma Entidade do Banco de Dados.
         * @param entidade Entidade a ser removida.
         */        
	public void excluir(T entidade){
		manager.remove(entidade);
	}
	
        /**
         * Método que altera uma Entidade no Banco de Dados.
         * @param entidade Entidade a ser alterada.
         */        
	public void alterar(T entidade){
		manager.merge(entidade);
	}
	
	
	
}
