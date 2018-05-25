/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.utils;

import br.cefetrj.sisgee.model.entity.Convenio;
import org.apache.log4j.Logger;

/**
 *
 * @author vinicius
 */
public class ConvenioUtils {
    
    public String getCnpjEmpresaFormatado(String cnpjEmpresa) {
        if(cnpjEmpresa != null && cnpjEmpresa.trim().length() > 0){
            StringBuilder cnpjFormatado = new StringBuilder();
            try{
                cnpjFormatado.append(cnpjEmpresa.charAt(0));
                cnpjFormatado.append(cnpjEmpresa.charAt(1));
                cnpjFormatado.append(".");
                cnpjFormatado.append(cnpjEmpresa.charAt(2));
                cnpjFormatado.append(cnpjEmpresa.charAt(3));
                cnpjFormatado.append(cnpjEmpresa.charAt(4));
                cnpjFormatado.append(".");
                cnpjFormatado.append(cnpjEmpresa.charAt(5));
                cnpjFormatado.append(cnpjEmpresa.charAt(6));
                cnpjFormatado.append(cnpjEmpresa.charAt(7));
                cnpjFormatado.append("/");
                cnpjFormatado.append(cnpjEmpresa.charAt(8));
                cnpjFormatado.append(cnpjEmpresa.charAt(9));
                cnpjFormatado.append(cnpjEmpresa.charAt(10));
                cnpjFormatado.append(cnpjEmpresa.charAt(11));
                cnpjFormatado.append("-");
                cnpjFormatado.append(cnpjEmpresa.charAt(12));
                cnpjFormatado.append(cnpjEmpresa.charAt(13));
            }catch(IndexOutOfBoundsException e){
                Logger lg = Logger.getLogger(Convenio.class);
                lg.error("CNPJ com menos de 14 caracteres. Cnpj = " + cnpjEmpresa, e);
                return cnpjEmpresa;
            }
            return cnpjFormatado.toString();
        }else{
            return null;
        }
    }
    
    public String getCpfFormatado(String cpf) {
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
                Logger lg = Logger.getLogger(Convenio.class);
                lg.error("CPF com menos de 11 caracteres. CPF = " + cpf, e);
                return cpf;
            }
            return cpfFormatado.toString();
        }else{
            return null;
        }
    }
    
}
