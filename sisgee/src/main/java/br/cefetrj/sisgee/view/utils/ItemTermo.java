
package br.cefetrj.sisgee.view.utils;

import br.cefetrj.sisgee.model.entity.TermoEstagio;
import java.util.Date;
import java.util.Objects;

/**
 * Classe para auxiliar na organizacao da Consulta de Termos de Estagio e Aditivos.
 * @author vinicius
 * @version 1.0
 */
public class ItemTermo {
    
    private String tipo;
    private String status;
    private Date dataDeCadastro;
    private String vigencia;
    private String cnpj_cpf;
    private String nomeConveniado;

    
    /** Construtor da classe sem parametros
    */
    public ItemTermo() {
    }

     /** Construtor da classe com 6 parametros
      * @param tipo String tipo do termo
      * @param status String status do termo
      * @param dataDeCadastro Date com a data de cadastro 
      * @param vigencia String com a vigencia
      * @param cnpj_cpf String cpf/cnpj do conveniado
      * @param nomeConveniado String com o nome do conveniado
    */
    public ItemTermo(String tipo, String status, Date dataDeCadastro, 
            String vigencia, String cnpj_cpf, String nomeConveniado) {
        this.tipo = tipo;
        this.status = status;
        this.dataDeCadastro = dataDeCadastro;
        this.vigencia = vigencia;
        this.cnpj_cpf = cnpj_cpf;
        this.nomeConveniado = nomeConveniado;
    }
    
    /** Serviço que altera/atribui o tipo do termo a patir de um termo. Estágio ou aditivo
     * 
      * @param termo TermoEstagio a ser alterado/atribuido
    */
    public void setItemTermoByTermo(TermoEstagio termo) {
        
        //Tipo
        if (termo.getTermoEstagioAditivo()==null) {
            this.tipo = "Termo Estagio";
        }else {
            this.tipo = "Termo Aditivo";
        }
        
        //Status
        if (termo.getEAtivo()) {
            this.status = "Ativo";
        }else {
            this.status = "Inativo";
        }
        
        //Data de Cadastro
        this.dataDeCadastro = termo.getDataInicioTermoEstagio();
        
        //Vigencia
        this.vigencia = ConvenioUtils.getVigencia(dataDeCadastro);
        
        //Cnpj_CPF
        if(termo.getConvenio().getIsPessoaJuridica()) {
            this.cnpj_cpf = ConvenioUtils.getCnpjEmpresaFormatado(termo.getConvenio().getCpf_cnpj());
        }else {
            this.cnpj_cpf = ConvenioUtils.getCpfFormatado(termo.getConvenio().getCpf_cnpj());
        }
        
        //nomeConveniado
        this.nomeConveniado = termo.getConvenio().getNomeConveniado();
    }
    
    
     /** Serviço que recupera o tipo do termo. Estágio ou aditivo
     * 
     * @return String com o tipo
    */
    public String getTipo() {
        return tipo;
    }

     /** Serviço que altera/atribui o tipo do termo a partir de uma string com o tipo. Estágio ou aditivo
     * 
     * @param tipo String a ser alterado/atribuido
    */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /** Serviço que recupera o status do termo
    * 
    * @return String com o status do termo
    */
    public String getStatus() {
        return status;
    }

    /** Serviço que altera/atribui o status do termo 
     *  @param status String status a ser alterado/atribuido
     */
    public void setStatus(String status) {
        this.status = status;
    }

     /** Serviço que recupera a data de cadastro do termo
    * 
    * @return Date com a data de cadastro do termo
    */
    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    /** Serviço que atribui a data de cadastro
     * 
     *  @param dataDeCadastro Date com a data de cadastro do termo
     */
    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    /** Serviço que recupera a vigencia do termo
    * 
    * @return String com a vigencia do termo
    */
    public String getVigencia() {
        return vigencia;
    }

    /** Serviço que altera/atribui a vigencia
     *  @param vigencia String vigencia a ser atribuida 
     */
    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

     /** Serviço que recupera o cpf/cnpj do conveniado
    * 
    * @return String com o status do termo
    */
    public String getCnpj_cpf() {
        return cnpj_cpf;
    }

    /** Serviço que altera/atribui o cnpj/cpf do conveniado
     *  @param cnpj_cpf String cnpj/cpf a ser atribuido
     */
    public void setCnpj_cpf(String cnpj_cpf) {
        this.cnpj_cpf = cnpj_cpf;
    }

    /** Serviço que recupera o nome do conveniado associado ao termo
    * 
    * @return String com o nome do conveniado
    */
    public String getNomeConveniado() {
        return nomeConveniado;
    }

    /** Serviço que altera/atribui o nome do conveniado associado ao termo 
     *  @param nomeConveniado String status a ser alterado/atribuido
     */
    public void setNomeConveniado(String nomeConveniado) {
        this.nomeConveniado = nomeConveniado;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.cnpj_cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemTermo other = (ItemTermo) obj;
        if (!Objects.equals(this.nomeConveniado, other.nomeConveniado)) {
            return false;
        }
        return true;
    }
    
}
