package br.cefetrj.sisgee.view.relatorio;

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

        String msg = null;
        String msgDataObrig = null;
        String msgCheckEstagio = null;
        String msgComparaDatas = null;
        String msgDataInicio = null;
        String msgDataTermino = null;

        Date dataInicio = null;
        Date dataTermino = null;

        Boolean estagioObrig;
        Boolean estagioNaoObrig;

        Boolean validDate = true;

        msgDataInicio = ValidaUtils.validaDate("", dataDeInicio);
        msgDataTermino = ValidaUtils.validaDate("", dataDeTermino);

        boolean isValid = true;
        int tamanho;

        if (!msgDataInicio.isEmpty()) {
            msgDataInicio = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.alert_data_inicio");
            request.setAttribute("msgDataInicio", msgDataInicio);
            validDate = false;
        }

        if (!msgDataTermino.isEmpty()) {
            msgDataTermino = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.alert_data_termino");
            request.setAttribute("msgDataTermino", msgDataTermino);
            validDate = false;
        }

        if (validDate == false) {
            if ((!dataDeInicio.isEmpty() && !msgDataInicio.isEmpty()) && (!dataDeTermino.isEmpty() && !msgDataTermino.isEmpty())) {
                msgDataInicio = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_date");
                request.setAttribute("msgDataInicio", msgDataInicio);
                msgDataTermino = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_date");
                request.setAttribute("msgDataTermino", msgDataTermino);
                msg += msgDataInicio + msgDataTermino;
            } else if (!dataDeInicio.isEmpty() && !msgDataInicio.isEmpty()) {
                msgDataInicio = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_date");
                request.setAttribute("msgDataInicio", msgDataInicio);
                msg += msgDataInicio;
            } else if (!dataDeTermino.isEmpty() && !msgDataTermino.isEmpty()) {
                msgDataTermino = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_date");
                request.setAttribute("msgDataTermino", msgDataTermino);
                msg += msgDataTermino;
            } else {
                msgDataObrig = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.msg_data_obrigatoria");
                request.setAttribute("msgDataObrig", msgDataObrig);
                msg += msgDataObrig;
            }
        }
        if ((dataDeInicio != null) && (dataDeTermino != null)) {
            if (!(dataDeInicio.isEmpty() || dataDeTermino.isEmpty()) && validDate) {
                try {
                    SimpleDateFormat format = null;
                    if (messages.getLocale().toString().equals("pt_BR")) {
                        format = new SimpleDateFormat("dd/MM/yyyy");
                    } else if (messages.getLocale().toString().equals("en_US")) {
                        format = new SimpleDateFormat("MM/dd/yyyy");
                    } else {
                        //fazer log de erro com a internacionalização
                        System.out.println("Idioma desconhecido");
                    }

                    if (format != null) {
                        dataInicio = format.parse(dataDeInicio);

                        dataTermino = format.parse(dataDeTermino);
                    } else {
                        //fazer o log de erro com a internacionalização
                        System.out.println("Sem padrão de formatação para data, Objeto format nulo");
                    }

                } catch (Exception e) {
                    //fazer log de erro com a internacionalização
                    System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
                }

                msgComparaDatas = ValidaUtils.validaDatas(dataInicio, dataTermino);
                if (msgComparaDatas != "") {
                    msgComparaDatas = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.msg_valida_datas");
                    request.setAttribute("msgComparaDatas", msgComparaDatas);
                    msg += msgComparaDatas;
                } else {
                    request.setAttribute("dataInicio", dataInicio);
                    request.setAttribute("dataTermino", dataTermino);

                }
            } else {
                msgDataObrig = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.msg_data_obrigatoria");
                request.setAttribute("msgDataObrig", msgDataObrig);
                msg += msgDataObrig;

            }

            if (estagioObrigatorio == null && estagioNaoObrigatorio == null) {
                msgCheckEstagio = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.msg_check_estagio_not_null");

                request.setAttribute("msgCheckEstagio", msgCheckEstagio);
                msg += msgCheckEstagio;

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
        } else {
            request.getRequestDispatcher("/relatorio_consolidado.jsp").forward(request, response);
        }
        if (msg != null) {
            request.getRequestDispatcher("/relatorio_consolidado.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/BuscaRelatorioConsolidadoServlet").forward(request, response);
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
