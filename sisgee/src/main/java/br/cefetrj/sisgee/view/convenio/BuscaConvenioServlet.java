/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import java.io.IOException;
import java.util.List;
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

@WebServlet("/BuscaConvenioServlet")
public class BuscaConvenioServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Locale locale = ServletUtils.getLocale(req);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        
        List<Convenio> listaBusca = null;
        
        String numConvenio = req.getParameter("numConvenio");
        String nomeConvenio = req.getParameter("nomeConveniado");
        nomeConvenio = nomeConvenio.toUpperCase();
        
        
        if(!numConvenio.trim().isEmpty()){
            listaBusca = ConvenioServices.buscarByNumeroParcial(numConvenio);
            req.setAttribute("listaBusca", listaBusca);
        }else{
            listaBusca = ConvenioServices.buscarByNomeParcial(nomeConvenio);
            req.setAttribute("listaBusca", listaBusca);
        }
        
        if(listaBusca.isEmpty()){
            String msgBusca = messages.getString("br.cefetrj.sisgee.busca_convenio_servlet.msg_erroBusca");
            req.setAttribute("msgBusca",msgBusca);
        }
            
        req.getRequestDispatcher("/form_renovar_convenio.jsp").forward(req, resp);
        
    }

    
    
    
   
    
}
