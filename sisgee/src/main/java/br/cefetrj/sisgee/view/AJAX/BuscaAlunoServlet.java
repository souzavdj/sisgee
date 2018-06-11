package br.cefetrj.sisgee.view.AJAX;

import java.io.IOException;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import java.util.List;

/**
 * Servlet para trazer os dados do Aluno por meio de requisição AJAX.
 *
 * @author Augusto Jose
 * @since 1.0
 *
 */
@WebServlet("/BuscaAlunoServlet")
public class BuscaAlunoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String matricula = request.getParameter("matricula");
        String idAluno = "";
        String nome = "";
        String nomeCurso = "";
        String nomeCampus = "";
        String idTermoEstagioAtivo = "";
        String idTermoEstagioInativo = "";
        
        Aluno aluno = AlunoServices.buscarAlunoByMatricula(matricula.trim());
        if (aluno != null) {
            nome = aluno.getNome();
            nomeCurso = aluno.getNomeCurso();
            nomeCampus = aluno.getNomeCampus();
            idAluno = Integer.toString(aluno.getIdAluno());
            int qtdtermos = 0;
            
            List<TermoEstagio> termos = aluno.getTermoEstagios();
            int pos [] = new int[termos.size()];
            int contPos = 0;
            if (termos != null) {
                for (int i = 0; i < termos.size(); i++) {
                    if (termos.get(i).getTermoEstagioAditivo() == null) {
                        qtdtermos++;
                        pos[contPos] = i;
                        contPos++;
                    }
                }
            }
            if(qtdtermos == 1) {
                idTermoEstagioAtivo = (termos.get(0).getIdTermoEstagio() != null
                            ? termos.get(0).getIdTermoEstagio().toString()
                            : "");
                termos.get(0).getDataInicioTermoEstagio();
                termos.get(0).getConvenio().getCpf_cnpj();
                termos.get(0).getConvenio().getNomeConveniado();
            }else if (qtdtermos > 1) {
                boolean temTermoAtivo = false; 
                for (int i = 0; i < qtdtermos; i++) {
                    if (termos.get(pos[i]).getEAtivo()) {
                        idTermoEstagioAtivo = termos.get(pos[i]).getIdTermoEstagio().toString();
                        termos.get(pos[i]).getDataInicioTermoEstagio();
                        termos.get(pos[i]).getConvenio().getCpf_cnpj();
                        termos.get(pos[i]).getConvenio().getNomeConveniado();
                        temTermoAtivo = true;
                    }
                }
                if (temTermoAtivo==false) {
                    idTermoEstagioInativo = termos.get(qtdtermos-1).getIdTermoEstagio().toString();
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
                .add("idTermoEstagioInativo", idTermoEstagioInativo)
                .build();

        StringWriter stWriter = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(stWriter);
        jsonWriter.writeObject(model);
        jsonWriter.close();
        String jsonData = stWriter.toString();

        response.setContentType("application/json");
        response.getWriter().print(jsonData);
    }

}
