package br.cefetrj.sisgee.view.termoaditivo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.TermoAditivo;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.ItemTermo;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import java.util.Collections;

/**
 * Busca as informações de cada termo aditivo
 */
@WebServlet("/BuscaTermoAditivoServlet")
public class BuscaTermoAditivoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

        String msg = null;
        String idAluno = request.getParameter("idAluno");
        Integer id = null;
        ItemTermo itemTermo = new ItemTermo();
        
        System.out.println("Cheguei aqui!!!");
        msg = ValidaUtils.validaObrigatorio("Aluno", idAluno);
        if (msg.trim().isEmpty()) {
            msg = ValidaUtils.validaInteger("Aluno", idAluno);
            if (msg.trim().isEmpty()) {
                id = Integer.parseInt(idAluno);
            } else {
                msg = messages.getString(msg);
            }
        } else {
            msg = messages.getString(msg);
        }

        Aluno aluno = AlunoServices.buscarAluno(new Aluno(id));
        List<TermoEstagio> termoEstagios = aluno.getTermoEstagios();
        
        Collections.sort(termoEstagios);
        request.setAttribute("termoEstagio", termoEstagios);
        System.out.println("Qtd: "+ termoEstagios.get(0).getDataInicioTermoEstagio());
        System.out.println("Qtd: "+ termoEstagios.get(1).getDataInicioTermoEstagio());
        //TODO consertar a lógica de mensagem vazia
        if (msg != "") {
            aluno = AlunoServices.buscarAluno(new Aluno(id));
            termoEstagios = aluno.getTermoEstagios();
        }
        System.out.println("Funciona: "+ termoEstagios.get(0).getNomeSupervisor());
        /*if (termoEstagios != null) {
            for (TermoEstagio termoEstagio : termoEstagios) {
                if (termoEstagio.getDataRescisaoTermoEstagio() == null || 
                        termoEstagio.getDataRescisaoTermoEstagio().equals("")) {
                    request.setAttribute("termoAtivo", termoEstagio);
                    //request.setAttribute("termosAditivos", termoEstagio.getTermosAditivos());
                    break;
                }
            }
            //itemTermo.setItemTermoByTermo(termoEstagios.get(0));
            request.setAttribute("termoEstagio", termoEstagios);
        }*/
        
        
        //request.getSession().setAttribute("aluno", aluno);
        
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("/form_consultar_termo.jsp").forward(request, response);

    }
}
