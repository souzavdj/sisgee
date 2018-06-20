package br.cefetrj.sisgee.view.relatorio;

import br.cefetrj.sisgee.control.AlunoServices;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.view.utils.ValidaUtils;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

/**
 *
 * Servlet de validacao do form em relatorio_consolidado.jsp. E retorna
 * mensagens de correcao.
 *
 * @author Marcos E Carvalho
 * @since 1.0
 *
 */
@WebServlet("/ValidaRelatorioConsolidadoServlet")
public class ValidaRelatorioConsolidadoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidaRelatorioConsolidadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/relatorio_consolidado.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dataDeInicio = request.getParameter("dataDeInicio");
        String dataDeTermino = request.getParameter("dataDeTermino");
        String estagioObrigatorio = request.getParameter("estagioObrigatorio");
        String estagioNaoObrigatorio = request.getParameter("estagioNaoObrigatorio");

        Locale locale = getLocale(request);

        request.setAttribute("Locale", locale);

        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

        String msgCheckEstagio = null;

        Date dataInicio = null;
        Date dataTermino = null;

        Boolean estagioObrig;
        Boolean estagioNaoObrig;

        boolean isValid = true;

        String msgDataInicio = "";

        msgDataInicio = ValidaUtils.validaObrigatorio("dataDeInicio", dataDeInicio);
        if (msgDataInicio.trim().isEmpty()) {
            msgDataInicio = ValidaUtils.validaDate("dataDeInicio", dataDeInicio);
            if (msgDataInicio.trim().isEmpty()) {
                request.setAttribute("msgDataInicio", msgDataInicio);
            } else {
                msgDataInicio = messages.getString(msgDataInicio);
                request.setAttribute("msgDataInicio", msgDataInicio);
                isValid = false;
            }
        } else {
            msgDataInicio = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.alert_data_inicio");
            request.setAttribute("msgDataInicio", msgDataInicio);
            isValid = false;
        }

        String msgDataTermino = "";

        msgDataTermino = ValidaUtils.validaObrigatorio("dataDeTermino", dataDeTermino);
        if (msgDataTermino.trim().isEmpty()) {
            msgDataTermino = ValidaUtils.validaDate("dataDeTermino", dataDeTermino);
            if (msgDataTermino.trim().isEmpty()) {
                request.setAttribute("msgDataTermino", msgDataTermino);
            } else {
                msgDataTermino = messages.getString(msgDataTermino);
                request.setAttribute("msgDataTermino", msgDataTermino);
                isValid = false;
            }
        } else {
            msgDataTermino = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.alert_data_termino");
            request.setAttribute("msgDataTermino", msgDataTermino);
            isValid = false;
        }

        if (msgDataInicio.trim().isEmpty() && msgDataTermino.trim().isEmpty()) {
            try {
                SimpleDateFormat format = null;
                if (messages.getLocale().toString().equals("pt_BR")) {
                    format = new SimpleDateFormat("dd/MM/yyyy");
                } else if (messages.getLocale().toString().equals("en_US")) {
                    format = new SimpleDateFormat("MM/dd/yyyy");
                } else {
                    //fazer log de erro com a internacionalização
                    Logger lg = Logger.getLogger(ValidaRelatorioConsolidadoServlet.class);
                    lg.error("Idioma selecionado desconhecido. ");
                    //System.out.println("Idioma desconhecido");
                }

                if (format != null) {
                    dataInicio = format.parse(dataDeInicio);
                    dataTermino = format.parse(dataDeTermino);
                } else {
                    //fazer o log de erro com a internacionalização
                    Logger lg = Logger.getLogger(ValidaRelatorioConsolidadoServlet.class);
                    lg.error("Erro ao formatar data. ");
                    //System.out.println("Sem padrão de formatação para data, Objeto format nulo");
                }

            } catch (Exception e) {
                //fazer log de erro com a internacionalização
                Logger lg = Logger.getLogger(ValidaRelatorioConsolidadoServlet.class);
                lg.error("Exception devido a formatação da data. ", e);
                //System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
            }
            String msgComparaDatas = "";

            msgComparaDatas = ValidaUtils.validaDatas(dataInicio, dataTermino);
            if (msgComparaDatas.trim().isEmpty()) {
                request.setAttribute("dataInicio", dataInicio);
                request.setAttribute("dataTermino", dataTermino);
            } else {
                msgComparaDatas = messages.getString(msgComparaDatas);
                request.setAttribute("msgComparaDatas", msgComparaDatas);
                isValid = false;

            }
        }

        if (estagioObrigatorio == null && estagioNaoObrigatorio == null) {
            msgCheckEstagio = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.msg_check_estagio_not_null");
            request.setAttribute("msgCheckEstagio", msgCheckEstagio);
            isValid = false;

        } else {
            if (estagioObrigatorio != null) {
                estagioObrig = true;
            } else {
                estagioObrig = false;
            }
            if (estagioNaoObrigatorio != null) {
                estagioNaoObrig = true;
            } else {
                estagioNaoObrig = false;
            }

            request.setAttribute("estagioObrig", estagioObrig);
            request.setAttribute("estagioNaoObrig", estagioNaoObrig);
        }

        if (isValid) {
            request.getRequestDispatcher("/BuscaRelatorioConsolidadoServlet").forward(request, response);
        } else {
            request.getRequestDispatcher("/relatorio_consolidado.jsp").forward(request, response);

        }

    }

    /**
     *
     * Metodo para receber o local de formatacao a ser usado na aplicacao.
     *
     * @param request HttpServlet
     * @return locale
     */
    private Locale getLocale(HttpServletRequest request) throws ServletException {

        Locale locale = null;

        Object localeFound = request.getParameter("lang");
        if (localeFound == null) {
            localeFound = request.getSession().getAttribute("lang");
        }

        if (localeFound == null) {
            localeFound = request.getLocale();
        }

        if (localeFound instanceof Locale) {
            locale = (Locale) localeFound;
        } else if (localeFound instanceof String) {
            String[] parts = ((String) localeFound).split("_");
            locale = new Locale(parts[0], parts[1].toUpperCase());
        } else {
            throw new ServletException("Locale not found!!!");
        }

        return locale;
    }

}
