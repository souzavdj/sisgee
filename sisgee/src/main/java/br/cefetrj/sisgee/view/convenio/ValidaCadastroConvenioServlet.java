/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.AgenteIntegracaoServices;
import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.model.entity.AgenteIntegracao;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author denis
 */
public class ValidaCadastroConvenioServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    @Override   
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        Locale locale = ServletUtils.getLocale(req);
	ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        
        
	String tipo = req.getParameter("tipo");
        String agente = req.getParameter("agente");
        String cnpj = req.getParameter("cnpj");
	String razaoSocial = req.getParameter("razaoSocial");
        String cpf = req.getParameter("cpf");
        String nome = req.getParameter("nome");
        
        boolean isValid = true;
        int tamanho;
        /**
		 * Validação do campo Empresa, usando métodos da Classe
		 * ValidaUtils. Deve ser campo booleano
		 */
		String tipoMsg = "";
		tipoMsg = ValidaUtils.validaObrigatorio("Empresa", tipo);
		if (tipoMsg.trim().isEmpty()) {
			tipoMsg = ValidaUtils.validaBoolean("Empresa", tipo);
			if (tipoMsg.trim().isEmpty()) {
				Boolean obrigatorio = Boolean.parseBoolean(tipo);
				req.setAttribute("obrigatorio", obrigatorio);
			} else {
				tipoMsg = messages.getString(tipoMsg);
				req.setAttribute("empresaMsg", tipoMsg);
				isValid = false;
			}
		} else {
			tipoMsg = messages.getString(tipoMsg);
			req.setAttribute("empresaMsg", tipoMsg);
			isValid = false;
		}
                
        
        if(true){//se for empresa
        /**
		 * Validação do campo Agente Integração, usando métodos da Classe
		 * ValidaUtils. Deve ser campo booleano
		 */
		String agenteMsg = "";
		agenteMsg = ValidaUtils.validaObrigatorio("Agente Integração", agente);
		if (agenteMsg.trim().isEmpty()) {
			agenteMsg = ValidaUtils.validaBoolean("Agente Integração", agente);
			if (agenteMsg.trim().isEmpty()) {
				Boolean obrigatorio = Boolean.parseBoolean(agente);
				req.setAttribute("obrigatorio", obrigatorio);
			} else {
				agenteMsg = messages.getString(agenteMsg);
				req.setAttribute("agenteMsg", agenteMsg);
				isValid = false;
			}
		} else {
			agenteMsg = messages.getString(agenteMsg);
			req.setAttribute("agenteMsg", agenteMsg);
			isValid = false;
		}
                
                /**
		 * Validação do CNPJ da empresa usando os métodos da Classe ValidaUtils
		 * Campo obrigatório;
		 * Tamanho de 14 caracteres;
		 * CNPJ repetido.
		 */
		String cnpjMsg = "";
		tamanho = 14;
		cnpjMsg = ValidaUtils.validaObrigatorio("CNPJ", cnpj);	
		if (cnpjMsg.trim().isEmpty()) {
                        //remove caracteres especiais antes de vazer a validação numérica do CNPJ
                        cnpj = cnpj.replaceAll("[.|/|-]", "");
			cnpjMsg = ValidaUtils.validaInteger("CNPJ", cnpj);			
			if (cnpjMsg.trim().isEmpty()) {
				cnpjMsg = ValidaUtils.validaTamanhoExato("CNPJ", tamanho, cnpj);
					if (cnpjMsg.trim().isEmpty()) {
						Empresa e = EmpresaServices.buscarEmpresaByCnpj(cnpj);
						if (e == null) {
							if(!(cnpjMsg.trim().isEmpty())){
								req.setAttribute("cnpj", cnpj);
							}						
						}
						else {
							cnpjMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
							req.setAttribute("cnpjMsg", cnpjMsg);
							isValid = false;
						}
					}
					else{
						cnpjMsg = messages.getString(cnpjMsg);
						cnpjMsg = ServletUtils.mensagemFormatada(cnpjMsg, locale, tamanho);
						req.setAttribute("cnpjMsg", cnpjMsg);
						isValid = false;
					}
				}
				else{
					cnpjMsg = messages.getString(cnpjMsg);
					req.setAttribute("cnpjMsg", cnpjMsg);
					isValid = false;
				}
		}
		else {
			cnpjMsg = messages.getString(cnpjMsg);
			req.setAttribute("cnpjMsg", cnpjMsg);
			isValid = false;
		}
                
            /**
		 * Validação da Razão Social do Cadastro Empresa usando mÃ©todos da Classe ValidaUtils. 
		 * Campo obrigatÃ³rio;
		 * Tamanho mÃ¡ximo de 100 caracteres;
		 * Razão Social já existente.
		 */
		String razaoSocialMsg = "";
		razaoSocialMsg = ValidaUtils.validaObrigatorio("Razão Social", razaoSocial);
		if (razaoSocialMsg.trim().isEmpty()) {
			razaoSocialMsg = ValidaUtils.validaTamanho("Razão Social", 100, razaoSocial);
			if (razaoSocialMsg.trim().isEmpty()) {
				Empresa e = EmpresaServices.buscarEmpresaByNome(razaoSocial);
				if (e == null) {
					if(!(razaoSocialMsg.trim().isEmpty())){
					req.setAttribute("razaoSocial", razaoSocial);
                                        }
				}
				else{
					razaoSocialMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
					req.setAttribute("razaoSocialMsg", razaoSocialMsg);
					isValid = false;
				}
			}
			else {
				razaoSocialMsg = messages.getString(razaoSocialMsg);
				razaoSocialMsg = ServletUtils.mensagemFormatada(razaoSocialMsg, locale, tamanho);
				req.setAttribute("razaoSocialMsg", razaoSocialMsg);
				isValid = false;
			}
		}
		else {
			razaoSocialMsg = messages.getString(razaoSocialMsg);
			req.setAttribute("razaoSocialMsg", razaoSocialMsg);
			isValid = false;
		}    
        }else{
            /**
		 * Validação do CPF da pessoa usando os métodos da Classe ValidaUtils
		 * Campo obrigatório;
		 * Tamanho de 11 caracteres;
		 * CPF repetido.
		 */
		String cpfMsg = "";
		tamanho = 11;
		cpf = ValidaUtils.validaObrigatorio("CPF", cpf);	
		if (cpfMsg.trim().isEmpty()) {
                        //remove caracteres especiais antes de vazer a validação numérica do CNPJ
                        cpf = cpf.replaceAll("[.|-]", "");
			cpfMsg = ValidaUtils.validaInteger("CPF", cpf);			
			if (cpfMsg.trim().isEmpty()) {
				cpfMsg = ValidaUtils.validaTamanhoExato("CPF", tamanho, cpf);
					if (cpfMsg.trim().isEmpty()) {
						Empresa e = EmpresaServices.buscarEmpresaByCnpj(cnpj);
						if (e == null) {
							if(!(cpfMsg.trim().isEmpty())){
								req.setAttribute("cpf", cpf);
							}						
						}
						else {
							cpfMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
							req.setAttribute("cpfMsg", cpfMsg);
							isValid = false;
						}
					}
					else{
						cpfMsg = messages.getString(cpfMsg);
						cpfMsg = ServletUtils.mensagemFormatada(cpfMsg, locale, tamanho);
						req.setAttribute("cpfMsg", cpfMsg);
						isValid = false;
					}
				}
				else{
					cpfMsg = messages.getString(cpfMsg);
					req.setAttribute("cpfMsg", cpfMsg);
					isValid = false;
				}
		}
		else {
			cpfMsg = messages.getString(cpfMsg);
			req.setAttribute("cpfMsg", cpfMsg);
			isValid = false;
		}
            
        }
        
    }
}
