package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.Aluno;

public class AlunoDAO extends GenericDAO<Aluno> {

    public AlunoDAO() {
        super(Aluno.class, PersistenceManager.getEntityManager());
    }

    /**
     * Método que busca um Aluno pela sua Matricula.
     *
     * @param matricula Matricula do Aluno a ser pesquisado.
     * @return O Aluno correspondente ou null.
     */
    public Aluno buscarByMatricula(String matricula) {
        this.manager.clear();
        return (Aluno) manager.createQuery(
                "SELECT a FROM Aluno a WHERE a.matricula LIKE :matricula")
                .setParameter("matricula", matricula)
                .getSingleResult();
    }

}
