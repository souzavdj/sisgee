/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.apache.log4j.Logger;

/**
 *
 * @author vinicius
 */
//@Entity
public class PessoaFisica extends Pessoa implements Serializable{
    
    @Column(length = 100, nullable = false)
    private String nome;
    
    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    public PessoaFisica() {
    }

    public PessoaFisica(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public PessoaFisica(String nome, String cpf, String email, String telefone, Convenio convenioPessoa) {
        super(email, telefone, convenioPessoa);
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getCpfFormatado() {
            if(cpf != null && cpf.trim().length() > 0){
                StringBuilder cpfFormatado = new StringBuilder();
                try{
                    cpfFormatado.append(cpf.charAt(0));
                    cpfFormatado.append(cpf.charAt(1));
                    cpfFormatado.append(cpf.charAt(2));
                    cpfFormatado.append(".");
                    cpfFormatado.append(cpf.charAt(3));
                    cpfFormatado.append(cpf.charAt(4));
                    cpfFormatado.append(cpf.charAt(5));
                    cpfFormatado.append(".");
                    cpfFormatado.append(cpf.charAt(6));
                    cpfFormatado.append(cpf.charAt(7));
                    cpfFormatado.append(cpf.charAt(8));
                    cpfFormatado.append("-");
                    cpfFormatado.append(cpf.charAt(9));
                    cpfFormatado.append(cpf.charAt(10));
                }catch(IndexOutOfBoundsException e){
                    Logger lg = Logger.getLogger(PessoaFisica.class);
                    lg.error("CPF com menos de 11 caracteres. CPF = " + cpf + " idPessoa = " + this.getIdPessoa(), e);
                    return cpf;
                }
                return cpfFormatado.toString();
            }else{
                return null;
            }
    }
}
