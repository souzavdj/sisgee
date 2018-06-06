/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author denis
 */
@WebServlet("/ValidaRenovacaoConvenioServlet")
public class ValidaRenovacaoConvenioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/form_renovar_convenio_infos.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Locale locale = ServletUtils.getLocale(req);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

        String dataAssinaturaConvenio = req.getParameter("dataAssinatura");
        String telefone = req.getParameter("telefone");
        String email = req.getParameter("email");
        String pessoaContato = req.getParameter("pessoaContato");
        String tipo = req.getParameter("tipo");
        req.setAttribute("tipo", tipo);
        Logger lg = Logger.getLogger(ValidaRenovacaoConvenioServlet.class);
        Convenio c;
        
        if (Boolean.parseBoolean(tipo)) {
            String agente = req.getParameter("agente");
            req.setAttribute("agente", agente);

            String razaoSocial = req.getParameter("razaoSocial");
            req.setAttribute("nomeConveniado", razaoSocial);

            String cnpj = req.getParameter("cnpj");
            req.setAttribute("cpf_cnpj", cnpj);
            c = ConvenioServices.buscarConvenioCpf_Cnpj(cnpj.replaceAll("[.|/|-]", ""));
        } else {
            String nome = req.getParameter("nome");
            req.setAttribute("nomeConveniado", nome);

            String cpf = req.getParameter("cpf");
            req.setAttribute("cpf_cnpj", cpf);
            c = ConvenioServices.buscarConvenioCpf_Cnpj(cpf.replaceAll("[.|/|-]", ""));
        }

        int tamanho;
        boolean isValid = true;

        /**
         * Validação do campo Data de Assinatura usando os métodos da Classe
         * ValidaUtils Campo obrigatório; Tipo Date.
         */
        Date dataAssinatura = null;
        String dataAssinaturaMsg = "";

        dataAssinaturaMsg = ValidaUtils.validaObrigatorio("Data de Assintura", dataAssinaturaConvenio);
        if (dataAssinaturaMsg.trim().isEmpty()) {
            dataAssinaturaMsg = ValidaUtils.validaDate("Data de Assinatura", dataAssinaturaConvenio);
            if (dataAssinaturaMsg.trim().isEmpty()) {
                try {
                    SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
                    dataAssinatura = formata.parse(dataAssinaturaConvenio);
                    Date dataAtual = formata.parse(formata.format(c.getDataAssinatura()));
                    dataAssinaturaMsg = ValidaUtils.validaDataRenovacao(dataAtual, dataAssinatura);
                    if (dataAssinaturaMsg.trim().isEmpty()) {
                        req.setAttribute("dataAssinatura", dataAssinatura);
                    } else {
                        dataAssinaturaMsg = messages.getString(dataAssinaturaMsg);
                        req.setAttribute("dataAssinaturaMsg", dataAssinaturaMsg);
                        req.setAttribute("dataAssinatura", dataAssinatura);
                        isValid = false;
                    }

                } catch (Exception e) {
                    lg.error("Data em formato incorreto, mesmo após validação na classe ValidaUtils", e);
                    isValid = false;
                }
            } else {
                dataAssinaturaMsg = messages.getString(dataAssinaturaMsg);
                req.setAttribute("dataAssinaturaMsg", dataAssinaturaMsg);
                req.setAttribute("dataAssinatura", dataAssinatura);
                isValid = false;
            }
        } else {
            dataAssinaturaMsg = messages.getString(dataAssinaturaMsg);
            req.setAttribute("dataAssinaturaMsg", dataAssinaturaMsg);
            req.setAttribute("dataAssinatura", dataAssinatura);
            isValid = false;
        }

        /**
         * Validação do campo Telefone usando os métodos da Classe ValidaUtils
         * Campo não obrigatório; Tamanho máximo de 11 caracteres.
         */
        if (telefone.trim().isEmpty()) {
            req.setAttribute("telefone", telefone);
        } else {
            String telefoneMsg = "";
            tamanho = 11;
            telefone = telefone.replaceAll("[(|)|-]", "");
            telefone = telefone.replaceAll(" ", "");
            telefoneMsg = ValidaUtils.validaTelefone("Telefone", telefone);
            if (telefoneMsg.trim().isEmpty()) {
                telefoneMsg = ValidaUtils.validaTamanho("Telefone", tamanho, telefone);
                if (telefoneMsg.trim().isEmpty()) {
                    req.setAttribute("telefone", telefone);
                } else {
                    telefoneMsg = messages.getString(telefoneMsg);
                    telefoneMsg = ServletUtils.mensagemFormatada(telefoneMsg, locale, tamanho);
                    req.setAttribute("telefone", telefone);
                    req.setAttribute("telefoneMsg", telefoneMsg);
                    isValid = false;
                }
            } else {
                telefoneMsg = messages.getString(telefoneMsg);
                telefoneMsg = ServletUtils.mensagemFormatada(telefoneMsg, locale, tamanho);
                req.setAttribute("telefone", telefone);
                req.setAttribute("telefoneMsg", telefoneMsg);
                isValid = false;
            }

        }

        /**
         * Validação do campo Email usando os métodos da Classe ValidaUtils
         * Campo não obrigatório; Tamanho máximo de 50 caracteres.
         */
        if (email.trim().isEmpty()) {
            req.setAttribute("email", email);
        } else {
            String emailMsg = "";
            tamanho = 50;
            emailMsg = ValidaUtils.validaTamanho("Email", tamanho, email);
            if (emailMsg.trim().isEmpty()) {
                emailMsg = ValidaUtils.validaNaoInteger("Email", email);
                if (emailMsg.trim().isEmpty()) {
                    req.setAttribute("email", email);
                } else {
                    emailMsg = messages.getString(emailMsg);
                    emailMsg = ServletUtils.mensagemFormatada(emailMsg, locale, tamanho);
                    req.setAttribute("email", email);
                    req.setAttribute("emailMsg", emailMsg);
                    isValid = false;
                }
            } else {
                emailMsg = messages.getString(emailMsg);
                emailMsg = ServletUtils.mensagemFormatada(emailMsg, locale, tamanho);
                req.setAttribute("email", email);
                req.setAttribute("emailMsg", emailMsg);
                isValid = false;
            }
        }

        if (Boolean.parseBoolean(tipo)) {
            /**
             * Validação do campo Pessoa de Contato usando os métodos da Classe
             * ValidaUtils Campo não obrigatório; Tamanho máximo de 150
             * caracteres.
             */
            if (pessoaContato.trim().isEmpty()) {
                req.setAttribute("pessoaContato", pessoaContato);
            } else {
                String pessoaContatoMsg = "";
                tamanho = 50;
                pessoaContatoMsg = ValidaUtils.validaTamanho("Pessoa de Contato", tamanho, pessoaContato);
                if (pessoaContatoMsg.trim().isEmpty()) {
                    pessoaContatoMsg = ValidaUtils.validaNaoInteger("Pessoa do Contato", pessoaContato);
                    if (pessoaContatoMsg.trim().isEmpty()) {
                        req.setAttribute("pessoaContato", pessoaContato);
                    } else {
                        pessoaContatoMsg = messages.getString(pessoaContatoMsg);
                        pessoaContatoMsg = ServletUtils.mensagemFormatada(pessoaContatoMsg, locale, tamanho);
                        req.setAttribute("pessoaContato", pessoaContato);
                        req.setAttribute("pessoaContatoMsg", pessoaContatoMsg);
                        isValid = false;
                    }
                } else {
                    pessoaContatoMsg = messages.getString(pessoaContatoMsg);
                    pessoaContatoMsg = ServletUtils.mensagemFormatada(pessoaContatoMsg, locale, tamanho);
                    req.setAttribute("pessoaContato", pessoaContato);
                    req.setAttribute("pessoaContatoMsg", pessoaContatoMsg);
                    isValid = false;
                }
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