
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
 * Classe responsavel por verificar se as informações dos campos da pagina de cadastrar convenio estão corretas.Se as informações forem validas vai para o IncluirConvenioServlet.Se não forem validas aparecerá na pagina de cadastro de convenio mensagens de erro especificias.
 *  
 * @author Andre
 * @version 1
 * 
 */
@WebServlet("/ValidarCadastroConvenioServlet")
public class ValidarCadastroConvenioServlet extends HttpServlet {

    /**
     * 
     * Metodo que recebe informações do formulario da pagina de cadastrar convenio e valida as informações.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
                Locale locale = ServletUtils.getLocale(req);
		ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
                String tipo = req.getParameter("Tipo");
                
                boolean isValid = true;
        
                 /**
		 * Validação do campo Agente Integração, usando métodos da Classe
		 * ValidaUtils. Deve ser campo booleano
		 */
		String tipoMsg = "";
		tipoMsg = ValidaUtils.validaObrigatorio("Tipo de Pessoa", tipo);
		if (tipoMsg.trim().isEmpty()) {
			tipoMsg = ValidaUtils.validaBoolean("Tipo de Pessoa", tipo);
			if (tipoMsg.trim().isEmpty()) {
				Boolean obrigatorio = Boolean.parseBoolean(tipo);
				req.setAttribute("obrigatorio", obrigatorio);
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
            
            System.out.println("entrou");
            
            if (isValid) {
                    req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                    String msg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_atencao");
                    req.setAttribute("msg", msg);
                    req.getRequestDispatcher("/form_convenio.jsp").forward(req, resp);

            }
    
    
    }

    

}
