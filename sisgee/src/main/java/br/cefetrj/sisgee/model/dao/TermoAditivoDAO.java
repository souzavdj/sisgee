package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Implementacao do padrao DAO para pesquisa especifica do Termo Aditivo
 *
 * @author Vinicius Souza
 * @since 1.0
 *
 */
public class TermoAditivoDAO  extends GenericDAO<TermoEstagio> {
    
     /**
     * Construtor da classe que chama o super da classe mae, passando os 2 parametro
     */
    public TermoAditivoDAO() {
            super(TermoEstagio.class, PersistenceManager.getEntityManager());
    }
    
    
   /**
     * Serviço que inclui no banco de dados, pelo seu DAO, um termo aditivo a partir de um termoAditivo recebido
     *
     * @author Vinicius Souza
     * @param termoAditivo termoAditivo que será incluido
     */
    public void inserirTermoAditivo (TermoEstagio termoAditivo) {
        Query query = manager.createNativeQuery("insert into TermoEstagio (idTermoEstagio, dataInicioTermoEstagio, "
                + "dataFimTermoEstagio, cargaHorariaTermoEstagio, valorBolsa, enderecoTermoEstagio, "
                + "complementoEnderecoTermoEstagio, bairroEnderecoTermoEstagio, cepEnderecoTermoEstagio, "
                + "cidadeEnderecoTermoEstagio, estadoEnderecoTermoEstagio, nomesupervisor, cargosupervisor, "
                + "motivoaditivo, eativo, eEstagioObrigatorio, agenciada, termoestagioaditivo_idtermoestagio, "
                + "professororientador_idProfessorOrientador, aluno_idAluno, convenio_idConvenio) values "
                + "(:idTermoAditivo, :dataInicio, :dataFim, :carga, :valor, :endereco, :complemento, "
                + ":bairro, :cep, :cidade, :estado, :nomeSupervisor, :cargoSupervisor, "
                + ":motivo, :eAtivo, :eObrigatorio, :agenciada, :idTermo, :idProfessor, :idAluno, :idConvenio);");

        query.setParameter("idTermoAditivo", termoAditivo.getIdTermoEstagio());
        query.setParameter("dataInicio", termoAditivo.getDataInicioTermoEstagio());
        query.setParameter("dataFim", termoAditivo.getDataFimTermoEstagio());
        query.setParameter("cargo", termoAditivo.getCargaHorariaTermoEstagio());
        query.setParameter("valor", termoAditivo.getValorBolsa());
        query.setParameter("endereco", termoAditivo.getEnderecoTermoEstagio());
        query.setParameter("complemento", termoAditivo.getComplementoEnderecoTermoEstagio());
        query.setParameter("bairro", termoAditivo.getBairroEnderecoTermoEstagio());
        query.setParameter("cep", termoAditivo.getCepEnderecoTermoEstagio());
        query.setParameter("cidade", termoAditivo.getCidadeEnderecoTermoEstagio());
        query.setParameter("estado", termoAditivo.getEstadoEnderecoTermoEstagio());
        query.setParameter("nomeSupervisor", termoAditivo.getNomeSupervisor());
        query.setParameter("cargoSupervisor", termoAditivo.getCargoSupervisor());
        query.setParameter("motivo", termoAditivo.getMotivoAditivo());
        query.setParameter("eAtivo", termoAditivo.getEAtivo());
        query.setParameter("eObrigatorio", termoAditivo.getEEstagioObrigatorio());
        query.setParameter("agenciada", termoAditivo.getAgenciada());
        query.setParameter("idTermo", termoAditivo.getTermoEstagioAditivo().getIdTermoEstagio());
        query.setParameter("idProfessor", termoAditivo.getProfessorOrientador().getIdProfessorOrientador());
        query.setParameter("idAluno", termoAditivo.getAluno().getIdAluno());
        query.setParameter("idConvenio", termoAditivo.getConvenio().getIdConvenio());
        
        query.executeUpdate();
    }

    
    /**Serviço que busca a quantidade de termos aditivos a partir de um determinado curso.
     * 
     * @author Vinicius Souza
     * @param curso nome do curso 
     * @return Long com a quantidade de termos aditivos do curso escohido
     */
    public Long buscarQuantidadeDeTermosAditivosParaNomeCurso(String curso) {
        
        String consulta = "SELECT COUNT(te) FROM TermoEstagio te "
                + "inner join Aluno al on te.aluno.idAluno = al.idAluno "
                + "where al.nomeCurso = "+"'"+curso+"' "
                + "and te.termoEstagioAditivo is not null";
        
        TypedQuery<Long> query = manager.createQuery(consulta, Long.class);
        Long qtdTermosEstagioAditivo = query.getSingleResult();

        return qtdTermosEstagioAditivo;
    }
    
    /**
    * Recupera o maior id dos termos de estágio e retorna um Integer referente a ele
    * 
    * @author Vinicius Souza
    * @return Integer com o maior id.
    */
    public Integer getMaxIdTermoEstagio () {
        String consulta = "SELECT MAX(te.idTermoEstagio) FROM TermoEstagio te";

        TypedQuery<Integer> query = manager.createQuery(consulta, Integer.class);
        Integer idTermosEstagio = query.getSingleResult();
        if(idTermosEstagio == null){
            idTermosEstagio = 0;
        }
        return idTermosEstagio;
    }
}
