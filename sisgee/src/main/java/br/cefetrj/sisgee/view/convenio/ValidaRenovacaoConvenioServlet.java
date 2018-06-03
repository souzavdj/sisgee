/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author denis
 */
@WebServlet("/ValidaRenovacaoConvenioServlet")
public class ValidaRenovacaoConvenioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Locale locale = ServletUtils.getLocale(req);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

        String dataAssinaturaConvenio = req.getParameter("dataAssinatura");
        String telefone = req.getParameter("telefone");
        String email = req.getParameter("email");
        String pessoaContato = req.getParameter("pessoaContato");
        String tipo = req.getParameter("tipo");
        

        int tamanho;
        boolean isValid = true;
        
        
        /**
             * Validação do campo Data de Assinatura usando os métodos da Classe
             * ValidaUtils Campo obrigatório; 
             * Tipo Date.
             */
        Date dataAssinatura = null;
        String dataAssinaturaMsg = "";

        dataAssinaturaMsg = ValidaUtils.validaObrigatorio("Data de Assintura", dataAssinaturaConvenio);
        if (dataAssinaturaMsg.trim().isEmpty()) {
            dataAssinaturaMsg = ValidaUtils.validaDate("Data de Assintura", dataAssinaturaConvenio);
            if (dataAssinaturaMsg.trim().isEmpty()) {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                    dataAssinatura = format.parse(dataAssinaturaConvenio);
                    req.setAttribute("dataAssinatura", dataAssinatura);
                } catch (Exception e) {
                    //TODO trocar saída de console por Log
                    System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
                    isValid = false;
                }
            } else {
                dataAssinaturaMsg = messages.getString(dataAssinaturaMsg);
                req.setAttribute("dataInicioMsg", dataAssinaturaMsg);
                isValid = false;
                //TODO Fazer log
                System.out.println(dataAssinaturaMsg);
            }
        } else {
            dataAssinaturaMsg = messages.getString(dataAssinaturaMsg);
            req.setAttribute("dataAssinaturaMsg", dataAssinaturaMsg);
            isValid = false;
            //TODO Fazer log
            System.out.println(dataAssinaturaMsg);
        }
        
        /**
         * Validação do campo Email usando os métodos da Classe ValidaUtils
         * Campo não obrigatório; Tamanho máximo de 50 caracteres.
         */
        String emailMsg = "";
        tamanho = 50;
        emailMsg = ValidaUtils.validaTamanho("Email", tamanho, email);
        if (emailMsg.trim().isEmpty()) {
            emailMsg = ValidaUtils.validaNaoInteger("Email", email);
            if (emailMsg.trim().isEmpty()) {
                req.setAttribute("Email", email);
            } else {
                emailMsg = messages.getString(emailMsg);
                emailMsg = ServletUtils.mensagemFormatada(emailMsg, locale, tamanho);
                req.setAttribute("emailMsg", emailMsg);
                isValid = false;
            }
        } else {
            emailMsg = messages.getString(emailMsg);
            emailMsg = ServletUtils.mensagemFormatada(emailMsg, locale, tamanho);
            req.setAttribute("emailMsg", emailMsg);
            isValid = false;
        }

        /**
         * Validação do campo Telefone usando os métodos da Classe ValidaUtils
         * Campo não obrigatório; Tamanho máximo de 11 caracteres.
         */
        String telefoneMsg = "";
        tamanho = 11;
        telefone = telefone.replaceAll("[(|)|-]", "");
        telefoneMsg = ValidaUtils.validaTelefone("Telefone", telefone);
        if (telefoneMsg.trim().isEmpty()) {
            telefoneMsg = ValidaUtils.validaTamanho("Telefone", tamanho, telefone);
            if (telefoneMsg.trim().isEmpty()) {
                req.setAttribute("Telefone", telefone);
            } else {
                telefoneMsg = messages.getString(telefoneMsg);
                telefoneMsg = ServletUtils.mensagemFormatada(telefoneMsg, locale, tamanho);
                req.setAttribute("telefoneMsg", telefoneMsg);
                isValid = false;
            }
        } else {
            telefoneMsg = messages.getString(telefoneMsg);
            telefoneMsg = ServletUtils.mensagemFormatada(telefoneMsg, locale, tamanho);
            req.setAttribute("telefoneMsg", telefoneMsg);
            isValid = false;

        }
        
        if(Boolean.parseBoolean(tipo)){
        /**
             * Validação do campo Pessoa de Contato usando os métodos da Classe
             * ValidaUtils Campo não obrigatório; Tamanho máximo de 150 caracteres.
             */
            String pessoaContatoMsg = "";
            tamanho = 50;
                pessoaContatoMsg = ValidaUtils.validaTamanho("Pessoa de Contato", tamanho, pessoaContato);
                if (pessoaContatoMsg.trim().isEmpty()) {                    
                    pessoaContatoMsg = ValidaUtils.validaNaoInteger("Pessoa de Contato", pessoaContato);
                    if (pessoaContatoMsg.trim().isEmpty()) {  
                    req.setAttribute("Pessoa de Contato", pessoaContato);
                    }
                    else{
                       pessoaContatoMsg = messages.getString(pessoaContatoMsg);
                        pessoaContatoMsg = ServletUtils.mensagemFormatada(pessoaContatoMsg, locale, tamanho);
                        req.setAttribute("pessoaContatoMsg", pessoaContatoMsg);
                        isValid = false; 
                    }
                } else {
                    pessoaContatoMsg = messages.getString(pessoaContatoMsg);
                    pessoaContatoMsg = ServletUtils.mensagemFormatada(pessoaContatoMsg, locale, tamanho);
                    req.setAttribute("pessoaContatoMsg", pessoaContatoMsg);
                    isValid = false;
         }
        }      
        if (isValid) {
            req.getRequestDispatcher("/AlteraConvenioServlet").forward(req, resp);
        } else {
            String msg = messages.getString("br.cefetrj.sisgee.validar_cadastro_convenio_servlet.msg_atencao");
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("/form_renovar_convenio_infos.jsp").forward(req, resp);
        }
    }

}
