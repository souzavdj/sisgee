/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.relatorio;

import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.view.utils.ItemRelatorio;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vinicius
 */
@WebServlet("/ImprimirRelatorioConsolidadoServlet")
public class ImprimirRelatorioConsolidadoServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dataDeInicio = req.getParameter("dataInicio");
        String dataDeTermino = req.getParameter("dataTermino");
        String eObrigatorio = req.getParameter("estagioObrig");
        String naoObrigatorio = req.getParameter("estagioNaoObrig");
        String idioma = req.getParameter("Locale");
        Date dataInicio = null;
        Date dataTermino = null;
        try {
            SimpleDateFormat format = null;
            if (idioma.equals("pt_BR")) {
                format = new SimpleDateFormat("dd/MM/yyyy");
            } else if (idioma.equals("en_US")) {
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
        
        Boolean estagioObrig = Boolean.parseBoolean(eObrigatorio);
        Boolean estagioNaoObrig = Boolean.parseBoolean(naoObrigatorio);
        
        List<Object[]> termosEstagioLista = null;
        List<Object[]> termosAditivoLista = null;
        List<String> cursos = null;
        List<Long> qtdTermosEstagio = null;
        List<Long> qtdTermosAdivos = null;
        List<Long> qtdTermosRescindido = null;
        
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
            System.out.println("Nenhum registro encontrado nesse período de tempo");
            //String msgRelatorio = messages.getString("br.cefetrj.sisgee.relatorio.busca_relatorio_consolidado_servlet.nenhum_resultado");
            req.setAttribute("msgRelatorio", "Não há termos");
        }

        req.setAttribute("relatorio", listaItemRelatorio);
        req.getRequestDispatcher("/form_imprimir_relatorio_consolidado.jsp").forward(req, resp); 
    }

}
