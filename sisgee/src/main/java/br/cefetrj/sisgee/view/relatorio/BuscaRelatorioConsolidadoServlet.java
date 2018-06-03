package br.cefetrj.sisgee.view.relatorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.view.utils.ItemRelatorio;
import java.text.SimpleDateFormat;

/**
 * Servlet para buscar e processar os dados obtidos do banco.
 *
 * @author Marcos E Carvalho
 * @since 1.0
 *
 */

@WebServlet("/BuscaRelatorioConsolidadoServlet")
public class BuscaRelatorioConsolidadoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscaRelatorioConsolidadoServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Date dataInicio = (Date) request.getAttribute("dataInicio");
        Date dataTermino = (Date) request.getAttribute("dataTermino");
        Boolean estagioObrig = (Boolean) request.getAttribute("estagioObrig");
        Boolean estagioNaoObrig = (Boolean) request.getAttribute("estagioNaoObrig");

        Locale locale = (Locale) request.getAttribute("Locale");

        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

        List<Object[]> termosEstagioLista = null;
        List<Object[]> termosAditivoLista = null;
        List<String> cursos = null;
        List<Long> qtdTermosEstagio = null;
        List<Long> qtdTermosAdivos = null;
        List<Long> qtdTermosRescindido = null;

        if (estagioObrig == true && estagioNaoObrig == true) {
            cursos = TermoEstagioServices.buscarTermosRelatorioConsolidadoCursos(dataInicio, dataTermino);
            qtdTermosEstagio = new ArrayList<Long>();
            qtdTermosAdivos = new ArrayList<Long>();
            qtdTermosRescindido = new ArrayList<Long>();
            for (int i = 0; i < cursos.size(); i++) {
                qtdTermosEstagio.add(TermoEstagioServices.buscarQuantidadeDeTermosEstagioParaNomeCurso(cursos.get(i)));
                qtdTermosAdivos.add(TermoAditivoServices.buscarQuantidadeDeTermosAditivosParaNomeCurso(cursos.get(i)));
                qtdTermosRescindido.add(TermoEstagioServices.buscarQuantidadeDeTermosEstagioRescindidoParaNomeCurso(cursos.get(i)));
            }
        } else {

            if (estagioObrig == true && estagioNaoObrig == false) {
                cursos = TermoEstagioServices.buscarTermosRelatorioConsolidadoCursos(estagioObrig, dataInicio, dataTermino);

                qtdTermosEstagio = new ArrayList<Long>();
                qtdTermosAdivos = new ArrayList<Long>();
                qtdTermosRescindido = new ArrayList<Long>();
                for (int i = 0; i < cursos.size(); i++) {
                    qtdTermosEstagio.add(TermoEstagioServices.buscarQuantidadeDeTermosEstagioParaNomeCurso(cursos.get(i)));
                    qtdTermosAdivos.add(TermoAditivoServices.buscarQuantidadeDeTermosAditivosParaNomeCurso(cursos.get(i)));
                    qtdTermosRescindido.add(TermoEstagioServices.buscarQuantidadeDeTermosEstagioRescindidoParaNomeCurso(cursos.get(i)));
                }

            }

            if (estagioObrig == false && estagioNaoObrig == true) {
                cursos = TermoEstagioServices.buscarTermosRelatorioConsolidadoCursos(estagioObrig, dataInicio, dataTermino);

                qtdTermosEstagio = new ArrayList<Long>();
                qtdTermosAdivos = new ArrayList<Long>();
                qtdTermosRescindido = new ArrayList<Long>();
                for (int i = 0; i < cursos.size(); i++) {
                    qtdTermosEstagio.add(TermoEstagioServices.buscarQuantidadeDeTermosEstagioParaNomeCurso(cursos.get(i)));
                    qtdTermosAdivos.add(TermoAditivoServices.buscarQuantidadeDeTermosAditivosParaNomeCurso(cursos.get(i)));
                    qtdTermosRescindido.add(TermoEstagioServices.buscarQuantidadeDeTermosEstagioRescindidoParaNomeCurso(cursos.get(i)));
                }
            }
        }

        List<ItemRelatorio> listaItemRelatorio = new ArrayList<ItemRelatorio>();

        if (!(cursos.isEmpty())) {
            for (int i = 0; i < cursos.size(); i++) {
                ItemRelatorio ir = new ItemRelatorio(cursos.get(i), qtdTermosEstagio.get(i).intValue(), qtdTermosRescindido.get(i).intValue());
                ir.setQntTermoAditivo(qtdTermosAdivos.get(i).intValue());
                listaItemRelatorio.add(ir);
            }

        } else {
            System.out.println("Nenhum registro encontrado nesse período de tempo");
            String msgRelatorio = messages.getString("br.cefetrj.sisgee.relatorio.busca_relatorio_consolidado_servlet.nenhum_resultado");
            request.setAttribute("msgRelatorio", msgRelatorio);
        }

        request.setAttribute("relatorio", listaItemRelatorio);
        //request.getSession().setAttribute("relatorio", listaItemRelatorio);

        request.getRequestDispatcher("/relatorio_consolidado.jsp").forward(request, response);

    }

}
