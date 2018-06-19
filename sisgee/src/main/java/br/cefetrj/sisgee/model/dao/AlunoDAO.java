package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.Aluno;

/*
* Dao do Aluno com foco na tabela (e classe) 'Aluno'. Extende DAO genérico
*
* @author Letícia Silva
* @since 2.0
*/

public class AlunoDAO extends GenericDAO<Aluno> {

    public AlunoDAO() {
        super(Aluno.class, PersistenceManager.getEntityManager());
    }

    /**
    * Método que busca um Aluno pela sua Matricula.
    * @param matricula String com a matricula do Aluno a ser pesquisado.
    * @return O Aluno correspondente ou, caso não exista, retorna null.
    */
    public Aluno buscarByMatricula(String matricula) {
        this.manager.clear();
        return (Aluno) manager.createQuery(
                "SELECT a FROM Aluno a WHERE a.matricula LIKE :matricula")
                .setParameter("matricula", matricula)
                .getSingleResult();
    }
}
