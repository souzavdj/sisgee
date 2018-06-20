package br.cefetrj.sisgee.view.relatorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.log4j.Logger;

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

     /**
     *
     * Metodo que recebe informações para busca de relatorio conveniado
     *
     * @param request é a requisição que o servidor recebe do navegador
     * @param response é a resposta que o servidor envia ao navegador
     * @throws ServletException exceção do Servlet
     * @throws IOException exceção de IO
     */
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
        
        Logger lg = Logger.getLogger(BuscaRelatorioConsolidadoServlet.class);

        if (estagioObrig == true && estagioNaoObrig == true) {
            cursos = TermoEstagioServices.buscarTermosRelatorioConsolidadoCursos(dataInicio, dataTermino);
            for (int i = 0; i < cursos.size(); i++) {
                for (int j = i + 1; j < cursos.size(); j++) {
                    if (cursos.get(i).equals(cursos.get(j))) {
                        cursos.remove(j);
                        j--;
                    }
                }
            }
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
                for (int i = 0; i < cursos.size(); i++) {
                    for (int j = i + 1; j < cursos.size(); j++) {
                        if (cursos.get(i).equals(cursos.get(j))) {
                            cursos.remove(j);
                            j--;
                        }
                    }
                }
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
                for (int i = 0; i < cursos.size(); i++) {
                    for (int j = i + 1; j < cursos.size(); j++) {
                        if (cursos.get(i).equals(cursos.get(j))) {
                            cursos.remove(j);
                            j--;
                        }
                    }
                }
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
            //usar log info eu acho
            //System.out.println("Nenhum registro encontrado nesse período de tempo");
            String msgRelatorio = messages.getString("br.cefetrj.sisgee.relatorio.busca_relatorio_consolidado_servlet.nenhum_resultado");
            lg.info(msgRelatorio);
            request.setAttribute("msgRelatorio", msgRelatorio);
        }

        request.setAttribute("relatorio", listaItemRelatorio);
        //if (request.getMethod().equals("POST")) {
            request.getRequestDispatcher("/relatorio_consolidado.jsp").forward(request, response);
        //}else {
        //    request.getRequestDispatcher("/form_imprimir_relatorio_consolidado.jsp").forward(request, response); 
        //}
        
    }

}
