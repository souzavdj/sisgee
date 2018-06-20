package br.cefetrj.sisgee.view.termoaditivo;

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

import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.view.utils.ServletUtils;

/**
 * Servlet para tratar da inclusão do termo aditivo servlet
 *
 * @author Paulo Cantuária
 * @since 1.0
 *
 */
@WebServlet("/IncluirTermoAditivoServlet")
public class IncluirTermoAditivoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

        Date dataFimTermoAditivo = (Date) request.getAttribute("dataFim");
        Integer cargaHorariaTermoAditivo = (Integer) request.getAttribute("cargaHoraria");
        Float valorBolsaTermoAditivo = (Float) request.getAttribute("valor");
        String enderecoTermoAditivo = (String) request.getAttribute("enderecoTermoAditivo");
        ProfessorOrientador professorOrientador = new ProfessorOrientador((Integer) request.getAttribute("idProfessor"));

        String registroAditivoConcluido = "";
        String msgOcorreuErro = "";
        Logger lg = Logger.getLogger(IncluirTermoAditivoServlet.class);
        try {
            registroAditivoConcluido = messages.getString("br.cefetrj.sisgee.incluir_termo_aditivo_servlet.msg_registroAditivoConcluido");
            request.setAttribute("msg", registroAditivoConcluido);
            lg.info(registroAditivoConcluido);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            msgOcorreuErro = messages.getString("br.cefetrj.sisgee.incluir_termo_aditivo_servlet.msg_ocorreuErro");
            request.setAttribute("msg", msgOcorreuErro);
            lg.error("Exception ao tentar inserir o Termo Aditivo", e);
            request.getRequestDispatcher("FormTermoAditivoServlet").forward(request, response);
        }
    }

}
