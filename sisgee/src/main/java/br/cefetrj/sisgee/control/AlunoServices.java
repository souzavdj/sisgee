package br.cefetrj.sisgee.control;

import java.util.List;

import br.cefetrj.sisgee.model.dao.AlunoDAO;
import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.Aluno;
import org.apache.log4j.Logger;

/**
 * Serviços de alunos.
 * Trata a lógica de negócios associada com a entidade Aluno.
 * 
 * @author Paulo Cantuária
 * @since 1.0
 */
public class AlunoServices {
	
	/**
	 * Recupera todos os alunos e retorna em uma lista.
	 * @return Lista com todos os alunos.
	 */
	public static List<Aluno> listarAlunos(){
		GenericDAO<Aluno> alunoDao = PersistenceManager.createGenericDAO(Aluno.class);
		return alunoDao.buscarTodos();
	}
	/**
         * Serviço que busca um aluno do banco de dados pelo seu id.
         * @param aluno Aluno a ser pesquisado.
         * @return O aluno passado como parametro caso seja encontrado no banco.Se nçao for encontrado retorna null.
         */
	public static Aluno buscarAluno(Aluno aluno) {
		GenericDAO<Aluno> alunoDao = PersistenceManager.createGenericDAO(Aluno.class);
		return alunoDao.buscar(aluno.getIdAluno());
	}
	/**
         * Serviço que trata a inclusão de um Aluno no banco de dados.
         * @param aluno Aluno que deseja incluir no banco. 
         */
	public static void incluirAluno(Aluno aluno){
		GenericDAO<Aluno> alunoDao = PersistenceManager.createGenericDAO(Aluno.class);		
		PersistenceManager.getTransaction().begin();
		try{
			alunoDao.incluir(aluno);
			PersistenceManager.getTransaction().commit();
		}catch(Exception e){
                    Logger lg = Logger.getLogger(AlunoServices.class);
                    lg.error("Exception ao tentar incluir Aluno. ", e);
                    PersistenceManager.getTransaction().rollback();
		}
	}
	/**
         * Serviço que busca um Aluno do banco de dados com um número de matrícula específico.
         * @param matricula Matricula de um aluno.
         * @return O objeto aluno que tem essa matricula ou null caso não tenha esse aluno no banco.
         */
	public static Aluno buscarAlunoByMatricula(String matricula) {
		AlunoDAO alunoDao = new AlunoDAO();
		try{
			Aluno a = alunoDao.buscarByMatricula(matricula);
			return a;
		}catch(Exception e){
                    Logger lg = Logger.getLogger(AlunoServices.class);
                    lg.error("Exception ao tentar buscar Aluno. ", e);
                    return null;
		}
	}
	

}
