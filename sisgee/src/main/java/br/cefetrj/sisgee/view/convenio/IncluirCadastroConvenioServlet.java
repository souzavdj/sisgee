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
        String agente = req.getParameter("agente");
        String cpf = req.getParameter("cpf");
        //remove caracteres especiais antes de salvar o CPF
        cpf = cpf.replaceAll("[.|-]", "");
        String nome = req.getParameter("nome");
        String pessoaContato = req.getParameter("pessoaContato");
        Date dataAssinatura = (Date) req.getAttribute("dataAssinatura");
        String telefone = req.getParameter("telefone");
        String email = req.getParameter("email");

        System.out.println("Convenio campos:");
        System.out.println("Tipo: "+tipo);
        System.out.println("Agente: "+agente);
        System.out.println("cnpj: "+cnpj);
        System.out.println("Razao: "+razaoSocial);
        System.out.println("Data: "+dataAssinatura);
        System.out.println("Tel: "+telefone);
        System.out.println("Email: "+email);
        System.out.println("Pessoa Contato: "+pessoaContato);

        String msg = "";
        Logger lg = Logger.getLogger(IncluirCadastroConvenioServlet.class);
        try {            
            if (Boolean.parseBoolean(tipo)) {
               Convenio convenio = new Convenio(dataAssinatura, cnpj, razaoSocial, true, Boolean.parseBoolean(agente), pessoaContato, email, telefone,true);
                System.out.println("NumeroConvenio: " + ConvenioUtils.gerarNumeroConvenio(dataAssinatura));
               convenio.setNumeroConvenio(ConvenioUtils.gerarNumeroConvenio(dataAssinatura));
               ConvenioServices.incluirConvenio(convenio);
            } else {
                Convenio convenio1 = new Convenio(dataAssinatura, cpf, nome, false,email, telefone,true);
                ConvenioServices.incluirConvenio(convenio1);
            }
            
            msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_convenio_servlet.msg_convenio_cadastrado");
            req.setAttribute("msg", msg);
            lg.info(msg);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);

        } catch (Exception e) {
            msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_convenio_servlet.msg_ocorreu_erro");
            req.setAttribute("msg", msg);
            lg.error("Exception ao tentar inserir um Convênio", e);
            req.getRequestDispatcher("/form_empresa.jsp").forward(req, resp);

        }

    }
}
