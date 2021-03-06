
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
import org.apache.log4j.Logger;

/** 
 * Servlet para recuperar os dados do Convenio 
 * @author Denis
 * @since 2.0
 */

@WebServlet("/BuscaConvenioServlet")
public class BuscaConvenioServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
     /**
     *
     * Metodo que recebe informações para busca de convenio
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
        Logger lg = Logger.getLogger(BuscaConvenioServlet.class);
        
        List<Convenio> listaBusca = null;
        Convenio x = null;
        
        String numConvenio = req.getParameter("numConvenio");
        String nomeConvenio = req.getParameter("nomeConveniado");
        nomeConvenio = nomeConvenio.toUpperCase();
        
        
        if(!numConvenio.trim().isEmpty()){
            String num = String.format("%06d", Integer.parseInt(numConvenio));
            x = (Convenio) ConvenioServices.buscarBy6Numero(num);
            System.out.println(x.getNomeConveniado());
            req.setAttribute("con", x);
        }else{
            listaBusca = ConvenioServices.buscarByNomeParcial(nomeConvenio);
            req.setAttribute("listaBusca", listaBusca);
        }
        
        if(listaBusca == null && x == null){
            String msgBusca = messages.getString("br.cefetrj.sisgee.busca_convenio_servlet.msg_erroBusca");
            req.setAttribute("msgBusca",msgBusca);
            lg.info(msgBusca);
        }
            
        req.getRequestDispatcher("/form_renovar_convenio.jsp").forward(req, resp);
        
    }

    
    
    
   
    
}
