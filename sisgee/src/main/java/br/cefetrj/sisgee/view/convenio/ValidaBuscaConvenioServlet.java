
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * Servlet para validar informações para a busca do convenio
 * 
 * @author Denis
 * @since 2.0
 */

@WebServlet("/ValidaBuscaConvenioServlet")
public class ValidaBuscaConvenioServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    /**
     *
     * Metodo que recebe informações do formulario da pagina de renovacao de
     * convenio e valida as informações.
     *
     * @param req é a requisição que o servidor recebe do navegador
     * @param resp é a resposta que o servidor envia ao navegador
     * @throws ServletException exceção do Servlet
     * @throws IOException exceção de IO
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        Locale locale = ServletUtils.getLocale(req);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        Logger lg = Logger.getLogger(ValidaBuscaConvenioServlet.class);
        
        String numConvenio = req.getParameter("numConvenio");
        String nomeConveniado = req.getParameter("nomeConveniado");
        boolean isValid = true;
        
        String campoMsg = "";
        if(numConvenio.trim().isEmpty() && nomeConveniado.trim().isEmpty()){
            campoMsg = messages.getString("br.cefetrj.sisgee.valida_busca_convenio_servlet.msg_campos");
            req.setAttribute("campoMsg", campoMsg);
            lg.info(campoMsg);
            isValid = false;
        }else if(!(numConvenio.trim().isEmpty()) && !(nomeConveniado.trim().isEmpty())){
            campoMsg = messages.getString("br.cefetrj.sisgee.valida_busca_convenio_servlet.msg_umCampo");
            req.setAttribute("campoMsg", campoMsg);
            lg.info(campoMsg);
            isValid = false;
        }
        
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
                req.setAttribute("Número do Convênio", numConvenio);
            }else{
                numConvenioMsg = messages.getString(numConvenioMsg);
                numConvenioMsg = ServletUtils.mensagemFormatada(numConvenioMsg, locale, 6);
                req.setAttribute("numConvenioMsg", numConvenioMsg);
                lg.info(numConvenioMsg);
                isValid = false;
            }
        }else{
            numConvenioMsg = messages.getString(numConvenioMsg);
            numConvenioMsg = ServletUtils.mensagemFormatada(numConvenioMsg, locale, 6);
            req.setAttribute("numConvenioMsg", numConvenioMsg);
            lg.info(numConvenioMsg);
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
                req.setAttribute("nomeConveniadoMsg", nomeConveniadoMsg);
                lg.info(nomeConveniadoMsg);
                isValid = false;
            }
        }
        
        if (isValid) {
            req.getRequestDispatcher("/BuscaConvenioServlet").forward(req, resp);
        } else {
            String msg = messages.getString("br.cefetrj.sisgee.valida_busca_convenio_servlet.msg_atencao");
            req.setAttribute("msg", msg);
            lg.info(msg);
            req.getRequestDispatcher("/form_renovar_convenio.jsp").forward(req, resp);
        }
        
    }
}
