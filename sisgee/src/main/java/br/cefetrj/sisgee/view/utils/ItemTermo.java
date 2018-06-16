/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public ItemTermo() {
    }

    public ItemTermo(String tipo, String status, Date dataDeCadastro, String vigencia, String cnpj_cpf, String nomeConveniado) {
        this.tipo = tipo;
        this.status = status;
        this.dataDeCadastro = dataDeCadastro;
        this.vigencia = vigencia;
        this.cnpj_cpf = cnpj_cpf;
        this.nomeConveniado = nomeConveniado;
    }
    
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
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getCnpj_cpf() {
        return cnpj_cpf;
    }

    public void setCnpj_cpf(String cnpj_cpf) {
        this.cnpj_cpf = cnpj_cpf;
    }

    public String getNomeConveniado() {
        return nomeConveniado;
    }

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
