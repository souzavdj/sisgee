
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.view.utils.ConvenioUtils;
import br.cefetrj.sisgee.view.utils.ServletUtils;
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
 * Servlet para alterar dados do convenio
 * @author Denis
 */
@WebServlet("/AlteraConvenioServlet")
public class AlteraConvenioServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Locale locale = ServletUtils.getLocale(req);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        
        Date dataAssinatura = (Date) req.getAttribute("dataAssinatura");
        String telefone = req.getParameter("telefone");
        //remove caracteres especiais antes de salvar o telefone
        telefone = telefone.replaceAll("[(|)|-]", "");
        telefone = telefone.replaceAll(" ", "");
        String email = req.getParameter("email");
        String pessoaContato = req.getParameter("pessoaContato");
        String tipo = req.getParameter("tipo");
        String cpf_cnpj;
        if(Boolean.parseBoolean(tipo)){
            cpf_cnpj = req.getParameter("cnpj");
        }else{
            cpf_cnpj = req.getParameter("cpf");
        }
        //remove caracteres especiais antes de salvar
        cpf_cnpj = cpf_cnpj.replaceAll("[.|/|-]", "");
        
        
        String msg = "";
        Logger lg = Logger.getLogger(AlteraConvenioServlet.class);
        try{
            Convenio c = ConvenioServices.buscarConvenioByCpf_Cnpj(cpf_cnpj);
            c.setDataAssinatura(dataAssinatura);
            c.setTelefone(telefone);
            c.setEmail(email);
            c.setNumeroConvenio(ConvenioUtils.gerarNumeroConvenioAtt(dataAssinatura, c));
            if(Boolean.parseBoolean(tipo)){
                c.setPessoaContato(pessoaContato);
            }
            ConvenioServices.alterarConvenio(c);
            msg = messages.getString("br.cefetrj.sisgee.altera_covenio_servlet.msg_convenio_renovado");
            String msgConvenio = messages.getString("br.cefetrj.sisgee.incluir_cadastro_convenio_servlet.msg_convenio_num");
            msgConvenio = msgConvenio + ConvenioUtils.getNumeroConvenioFormatado(c.getNumeroConvenio());
            req.setAttribute("msg", msg);
            req.setAttribute("msgConvenio", msgConvenio);
            lg.info(msg);
            lg.info(msgConvenio);
            req.getRequestDispatcher("/convenio.jsp").forward(req, resp);
        } catch (Exception e) {
            msg = messages.getString("br.cefetrj.sisgee.altera_covenio_servlet.msg_ocorreu_erro");
            req.setAttribute("msg", msg);
            lg.error("Exception ao tentar alterar um Convênio", e);
            req.getRequestDispatcher("/form_renovar_convenio_infos.jsp").forward(req, resp);

        }
        
    }
    
}
