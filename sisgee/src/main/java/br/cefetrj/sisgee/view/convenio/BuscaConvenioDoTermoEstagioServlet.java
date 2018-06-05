/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet focado na busca de convenio do termo_estagio.jsp
 * @author Andre
 */
@WebServlet("/BuscaConvenioDoTermoEstagioServlet")
public class BuscaConvenioDoTermoEstagioServlet extends HttpServlet {
     private static final long serialVersionUID = 1L;

    /**
     * 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */ 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matricula = req.getParameter("matricula");
        String idAluno = "";
        String nome = "";
        String nomeCurso = "";
        String nomeCampus = "";
        String idTermoEstagioAtivo = "";
        
        Aluno aluno = AlunoServices.buscarAlunoByMatricula(matricula.trim());
        if (aluno != null) {
            nome = aluno.getNome();
            nomeCurso = aluno.getNomeCurso();
            nomeCampus = aluno.getNomeCampus();
            idAluno = Integer.toString(aluno.getIdAluno());

            List<TermoEstagio> termos = aluno.getTermoEstagios();
            if (termos != null) {
                for (TermoEstagio termo : termos) {
                    if (termo.getDataRescisaoTermoEstagio() == null || termo.getEAtivo()) {
                        idTermoEstagioAtivo = (termo.getIdTermoEstagio() != null
                                ? termo.getIdTermoEstagio().toString()
                                : "");
                        termo.getDataInicioTermoEstagio();
                        termo.getConvenio().getCpf_cnpj();
                        termo.getConvenio().getNomeConveniado();

                    }
                }
            }
            
        }

        //JSON
        JsonObject model = Json.createObjectBuilder()
                .add("idAluno", idAluno)
                .add("nome", nome)
                .add("nomeCurso", nomeCurso)
                .add("nomeCampus", nomeCampus)
                .add("idTermoEstagioAtivo", idTermoEstagioAtivo)
                .build();

        StringWriter stWriter = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(stWriter);
        jsonWriter.writeObject(model);
        jsonWriter.close();
        String jsonData = stWriter.toString();

        resp.setContentType("application/json");
        resp.getWriter().print(jsonData);
    }
     
     
   
}
