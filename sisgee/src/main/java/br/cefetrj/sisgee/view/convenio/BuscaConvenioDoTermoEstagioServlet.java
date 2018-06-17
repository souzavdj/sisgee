/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;


import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.ConvenioUtils;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
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
 * @since 1.0
 */
@WebServlet("/BuscaConvenioDoTermoEstagioServlet")
public class BuscaConvenioDoTermoEstagioServlet extends HttpServlet {
     private static final long serialVersionUID = 1L;
    

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String numConvenio = req.getParameter("numeroConvenio");
        String nomeConveniado = req.getParameter("nomeConvenio");
        String idConvenio = "";
        String tipo="";
        String agente="";
        String razao="";
        String numero="";
        Convenio buscado = null;
        
        System.out.println("NomeConveniado:" + nomeConveniado);
        System.out.println("NumeroConveniado:" + numConvenio);
        if(!numConvenio.trim().isEmpty()){
            System.out.println("Entrou no busca convenio pelo numero");
            buscado = ConvenioServices.buscarConvenioByNumero(numConvenio.trim());
          
        }else{
            System.out.println("Entrou no busca convenio pelo nome");
            buscado = ConvenioServices.buscarConvenioByNomeConveniado(nomeConveniado);
          
        }
       
        System.out.println("Passou :" +buscado);
        if (buscado != null) {
            idConvenio = Integer.toString(buscado.getIdConvenio());
            tipo=Boolean.toString(buscado.getIsPessoaJuridica());
            agente = Boolean.toString(buscado.getIsAgenteIntegracao());
            razao = buscado.getNomeConveniado();
            if(buscado.getIsAgenteIntegracao()){
                numero = ConvenioUtils.getCnpjEmpresaFormatado(buscado.getCpf_cnpj());
                
            }else{
                numero = ConvenioUtils.getCpfFormatado(buscado.getCpf_cnpj());
               
            }
            
        }

        //JSON
        JsonObject model = Json.createObjectBuilder()
                .add("idConvenio", idConvenio)
                .add("agente", agente)
                .add("tipo", tipo)
                .add("cpf_cnpj", numero)
                .add("razaoSocial",razao)
                .add("agenciada",razao)
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
