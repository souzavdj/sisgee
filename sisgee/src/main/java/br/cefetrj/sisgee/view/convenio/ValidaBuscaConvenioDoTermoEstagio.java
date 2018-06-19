
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet com o objetivo de validar os campos da pesquisa do convenio no termo_estagio.jps
 * @author Andre
 * @since 1.0
 */
@WebServlet("/ValidaBuscaConvenioDoTermoEstagio")
public class ValidaBuscaConvenioDoTermoEstagio extends HttpServlet {

       private static final long serialVersionUID = 1L;
    /**
     * Metodo que recebe informações do formulario do conveino que está na pagina de termo_estagio.jsp e valida suas informações.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Locale locale = ServletUtils.getLocale(req);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        
        String numConvenio = req.getParameter("numeroConvenio");
        String nomeConveniado = req.getParameter("nomeConvenio");
        
        boolean isValid = true;
        
        String campoMsg = "";
        if(numConvenio.trim().isEmpty() && nomeConveniado.trim().isEmpty()){
            campoMsg = messages.getString("br.cefetrj.sisgee.valida_busca_convenio_servlet.msg_campos");
            req.setAttribute("campoMsg", campoMsg);
            isValid = false;
        }else if(!(numConvenio.trim().isEmpty()) && !(nomeConveniado.trim().isEmpty())){
            campoMsg = messages.getString("br.cefetrj.sisgee.valida_busca_convenio_servlet.msg_umCampo");
            req.setAttribute("campoMsg", campoMsg);
            isValid = false;
        }
        else{
            if(!(numConvenio.trim().isEmpty()) && nomeConveniado.trim().isEmpty()){
                /***
                     * Validação do  campo Número do Convênio
                     * Tipo númerico
                     * Máximo de 6 caracteres
                     */
                String numConvenioMsg ="";
                numConvenioMsg = ValidaUtils.validaTamanho("Número do Convênio", 6, numConvenio);
                if (numConvenioMsg.trim().isEmpty()) {
                    numConvenioMsg = ValidaUtils.validaInteger("Número do Convênio", numConvenio);
                    if (numConvenioMsg.trim().isEmpty()) {
                        req.setAttribute("Número do Convêniop", numConvenio);
                    }else{
                        numConvenioMsg = messages.getString(numConvenioMsg);
                        numConvenioMsg = ServletUtils.mensagemFormatada(numConvenioMsg, locale, 10);
                        req.setAttribute("numConvenioMsg", numConvenioMsg);
                        isValid = false;
                    }
                }else{
                    numConvenioMsg = messages.getString(numConvenioMsg);
                    numConvenioMsg = ServletUtils.mensagemFormatada(numConvenioMsg, locale, 6);
                    req.setAttribute("numConvenioMsg", numConvenioMsg);
                    isValid = false;
                }
            }else if(numConvenio.trim().isEmpty() && !(nomeConveniado.trim().isEmpty())){

            /***
                 * Validação do  campo Nome do Conveniado
                 * Tipo String
                 * Máximo de 100 caracteres
                 */
                String nomeConveniadoMsg = "";
                nomeConveniadoMsg = ValidaUtils.validaTamanho("Nome do Conveniado", 100, nomeConveniado);
                if (nomeConveniadoMsg.trim().isEmpty()) {
                    req.setAttribute("Nome do Convêniado", nomeConveniado);
                } else {
                    nomeConveniadoMsg = messages.getString(nomeConveniadoMsg);
                    nomeConveniadoMsg = ServletUtils.mensagemFormatada(nomeConveniadoMsg, locale, 100);
                    req.setAttribute("nomeConveniadoMsg", nomeConveniado);
                    isValid = false;
                }
            }
            
        }
        System.out.println("Entrou");
        if (isValid) {
            req.getRequestDispatcher("/BuscaConvenioDoTermoEstagioServlet").forward(req, resp);
        } else {
            String msg = messages.getString("br.cefetrj.sisgee.valida_busca_convenio_servlet.msg_atencao");
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("/form_renovar_convenio.jsp").forward(req, resp);
        }
        
    }

    
}
