/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author denis
 */

@WebServlet("/ValidaBuscaConvenioServlet")
public class ValidaBuscaConvenioServlet extends HttpServlet {
    
    /**
     *
     * Metodo que recebe informações do formulario da pagina de renovacao de
     * convenio e valida as informações.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        Locale locale = ServletUtils.getLocale(req);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        
        String numConvenio = req.getParameter("numConvenio");
        String nomeConveniado = req.getParameter("nomeConveniado");
        boolean isValid = true;
        
        String campoMsg = "";
        if(numConvenio.trim().isEmpty()) && nomeConveniado.trim().isEmpty()){
            campoMsg = messages.getString(campoMsg);
            req.setAttribute("campoMsg", campoMsg);
            isValid = false;
        }else{
                
        }
        
        /**
         * Validação do campo Numero Convenio, usando métodos da Classe ValidaUtils. Deve
         * ser campo booleano
         */
        String tipoMsg = "";
        tipoMsg = ValidaUtils.validaObrigatorio("Tipo de Pessoa", tipo);
        if (tipoMsg.trim().isEmpty()) {
            tipoMsg = ValidaUtils.validaBoolean("Tipo de Pessoa", tipo);
            if (tipoMsg.trim().isEmpty()) {
                Boolean tipoB = Boolean.parseBoolean(tipo);
                req.setAttribute("tipo", tipoB);
            } else {
                tipoMsg = messages.getString(tipoMsg);
                req.setAttribute("tipoMsg", tipoMsg);
                isValid = false;
            }
        } else {
            tipoMsg = messages.getString(tipoMsg);
            req.setAttribute("tipoMsg", tipoMsg);
            isValid = false;         
        }
        
    
    }
}
