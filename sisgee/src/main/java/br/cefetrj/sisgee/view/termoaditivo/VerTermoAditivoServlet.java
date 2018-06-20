package br.cefetrj.sisgee.view.termoaditivo;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import org.apache.log4j.Logger;

/**
 * Servlet criada para visualização dos termos aditivos na tela
 *
 * @author Paulo Cantuária
 *
 */
@WebServlet("/VerTermoAditivoServlet")
public class VerTermoAditivoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        Logger lg = Logger.getLogger(VerTermoAditivoServlet.class);

        String idTermoEstagio = request.getParameter("idTermoEstagio");
        Integer id = null;
        TermoEstagio termoEstagio = null;

        String msg = "";
        String campo = "Termo Aditivo";
        boolean isValid = true;
        
        msg = ValidaUtils.validaObrigatorio("campo", idTermoEstagio);
        if (msg.trim().isEmpty()) {
            msg = ValidaUtils.validaInteger(campo, idTermoEstagio);
            if (msg.trim().isEmpty()) {
                id = Integer.parseInt(idTermoEstagio);
                termoEstagio = TermoEstagioServices.buscarTermoEstagio(id);
                if (termoEstagio != null) {
                    request.setAttribute("termoEstagio", termoEstagio);
                } else {
                    isValid = false;
                    msg = messages.getString("br.cefetrj.sisgee.ver_termo_aditivo_servlet.id_termo_invalido");
                    lg.info(msg);
                }
            } else {
                isValid = false;
                msg = messages.getString(msg);
                lg.info(msg);
            }
        } else {
            isValid = false;
            msg = messages.getString(msg);
            lg.info(msg);
        }

        if (isValid) {
            request.getRequestDispatcher("/form_visualizar_termos.jsp").forward(request, response);
        }
    }

}
