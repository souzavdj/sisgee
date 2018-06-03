/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.view.utils.ConvenioUtils;
import br.cefetrj.sisgee.view.utils.ServletUtils;
//import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
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
@WebServlet("/IncluirCadastroConvenioServlet")
public class IncluirCadastroConvenioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Locale locale = ServletUtils.getLocale(req);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        String tipo = req.getParameter("tipo");
        String cnpj = req.getParameter("cnpj");
        //remove caracteres especiais antes de salvar o CNPJ
        cnpj = cnpj.replaceAll("[.|/|-]", "");
        String razaoSocial = req.getParameter("razaoSocial");
        razaoSocial = razaoSocial.toUpperCase(locale);
        String agente = req.getParameter("agente");
        String cpf = req.getParameter("cpf");
        //remove caracteres especiais antes de salvar o CPF
        cpf = cpf.replaceAll("[.|-]", "");
        String nome = req.getParameter("nome");
        nome = nome.toUpperCase(locale);
        String pessoaContato = req.getParameter("pessoaContato");
        pessoaContato = pessoaContato.toUpperCase(locale);
        Date dataAssinatura = (Date) req.getAttribute("dataAssinatura");
        String telefone = req.getParameter("telefone");
        String email = req.getParameter("email");
        Convenio convenio;

        String msg = "";
        Logger lg = Logger.getLogger(IncluirCadastroConvenioServlet.class);
        try {            
            if (Boolean.parseBoolean(tipo)) {
               convenio = new Convenio(dataAssinatura, cnpj, razaoSocial, true, Boolean.parseBoolean(agente), pessoaContato, email, telefone);
               String numeroConvenio = ConvenioUtils.gerarNumeroConvenio(dataAssinatura);
               numeroConvenio = numeroConvenio.replace("/", "");               
               convenio.setNumeroConvenio(numeroConvenio);
               ConvenioServices.incluirConvenio(convenio);
            } else {
                convenio = new Convenio(dataAssinatura, cpf, nome, false,email, telefone);
                String numeroConvenio = ConvenioUtils.gerarNumeroConvenio(dataAssinatura);
                numeroConvenio = numeroConvenio.replace("/", "");
                convenio.setNumeroConvenio(numeroConvenio);
                ConvenioServices.incluirConvenio(convenio);
            }
            
            msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_convenio_servlet.msg_convenio_cadastrado");
            String msgConvenio = messages.getString("br.cefetrj.sisgee.incluir_cadastro_convenio_servlet.msg_convenio_num");
            msgConvenio = msgConvenio + ConvenioUtils.getNumeroCovenioFormatado(convenio.getNumeroConvenio());
            req.setAttribute("msg", msg);
            req.setAttribute("msgConvenio", msgConvenio);
            lg.info(msg);
            lg.info(msgConvenio);
            req.getRequestDispatcher("/convenio.jsp").forward(req, resp);

        } catch (Exception e) {
            msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_convenio_servlet.msg_ocorreu_erro");
            req.setAttribute("msg", msg);
            lg.error("Exception ao tentar inserir um Convênio", e);
            req.getRequestDispatcher("/form_convenio.jsp").forward(req, resp);

        }

    }
}
