/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;


import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet focado na busca de convenio do termo_estagio.jsp
 * @author Andre
 * @since 1.0
 */
@WebServlet("/BuscaConvenioDoTermoEstagioServlet")
public class BuscaConvenioDoTermoEstagioServlet extends HttpServlet {
     private static final long serialVersionUID = 1L;
    
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Locale locale = ServletUtils.getLocale(req);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        
        Convenio convenioBuscado = null;
        
        String numConvenio = req.getParameter("numConvenio");
        String nomeConvenio = req.getParameter("nomeConveniado");
        nomeConvenio = nomeConvenio.toUpperCase();
        
        
        if(!numConvenio.trim().isEmpty()){
            convenioBuscado = ConvenioServices.buscarConvenioByNumero(numConvenio);
            
        }else{
            convenioBuscado = ConvenioServices.buscarConvenioByNomeConveniado(nomeConvenio);
            
        }
        
        if(convenioBuscado == null){
            String msgBusca = messages.getString("br.cefetrj.sisgee.busca_convenio_servlet.msg_erroBusca");
            req.setAttribute("msgBusca",msgBusca);
        }else{
            
        }
            
        req.getRequestDispatcher("/form_termo_estagio.jsp").forward(req, resp);
        
    }
     
     
   
}
